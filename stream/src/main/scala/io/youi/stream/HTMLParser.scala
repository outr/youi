package io.youi.stream

import java.io.{File, FileInputStream, InputStream}

import scala.annotation.tailrec

import com.outr.scribe._

object HTMLParser {
  private val SelfClosingTagRegex = """(?s)<(\S+)(.*)/>""".r
  private val OpenTagRegex = """(?s)<(\S+)(.*)>""".r
  private val CloseTagRegex = """(?s)</(\S+).*>""".r

  /**
    * If set to true the stored tag attributes will be limited to those in validAttributes.
    *
    * Defaults to false.
    */
  var filterAttributes = false
  /**
    * The set of attributes to limit to if filterAttributes is set to true.
    *
    * Defaults to "id" and "class".
    */
  var validAttributes = Set("id", "class")

  def main(args: Array[String]): Unit = {
    complex()
  }

  def complex(): Unit = {
    val file = new File("complex.html")
    println(file.getAbsolutePath)
    val streamable = apply(file)

    val deltas = List(
      Delta.ReplaceContent(ByTag("title"), "Modified Title"),
      Delta.ReplaceContent(ById("list"), ""),
      Delta.Grouped(ByClass("type1"),
        Delta.Template(ByClass("type2"), List(
          Delta.ReplaceContent(ByClass("heading"), "Modified Type 2 Heading"),
          Delta.ReplaceContent(ByClass("body"), "Modified Type 2 Body")
        )),
        Delta.Template(ByClass("type1"), List(
          Delta.ReplaceContent(ByClass("heading"), "Modified Type 1 Heading"),
          Delta.ReplaceContent(ByClass("body"), "Modified Type 1 Body")
        )),
        Delta.Template(ByClass("type1"), List(
          Delta.ReplaceContent(ByClass("heading"), "Modified Type 1 Heading B"),
          Delta.ReplaceContent(ByClass("body"), "Modified Type 1 Body B")
        )),
        Delta.Template(ByClass("type2"), List(
          Delta.ReplaceContent(ByClass("heading"), "Modified Type 2 Heading B"),
          Delta.ReplaceContent(ByClass("body"), "Modified Type 2 Body B")
        ))
      ),
      Delta.ReplaceAttribute(ById("googleLink"), "href", "http://google.com")
    )
    val html = streamable.stream(deltas)
    println(html)
  }

  def simple(): Unit = {
    val file = new File("simple.html")
    val streamable = apply(file)

    case class ItemData(name: String, value: String)

    val data = List(
      ItemData("First", "This is the first value."),
      ItemData("Second", "This is the second value."),
      ItemData("Third", "This is the third value.")
    )

    val deltas = List(
      Delta.ReplaceContent(ByTag("title"), "Modified Title"),
      Delta.Replace(ById("head"), "<h1>Heading</h1>"),
      Delta.InsertLastChild(ById("body"), "<b>Last Entry</b>"),
      Delta.InsertAfter(ById("footer"), "<h5>Copyright (c)</h5>"),
      Delta.ReplaceContent(ById("footer"), "The updated footer"),
      Delta.Repeat(ByClass("item"), data, (d: ItemData) => List(
        Delta.ReplaceContent(ByClass("itemName"), d.name),
        Delta.ReplaceContent(ByClass("itemValue"), d.value)
      ))
    )
    val html = streamable.stream(deltas, Some(ById("body")))
    println(html)
  }

  def apply(file: File): StreamableHTML = {
    val cacheBuilder = new CacheBuilder {
      private var lastModified: Long = 0L

      override def isStale: Boolean = lastModified != file.lastModified()

      override def buildCache(): CachedInformation = {
        logger.info(s"Updated file cache for ${file.getName}...")
        val input = new FileInputStream(file)
        try {
          val parser = new HTMLParser(input)
          val tags = parser.parse()
          var byId = Map.empty[String, OpenTag]
          var byClass = Map.empty[String, Set[OpenTag]]
          var byTag = Map.empty[String, Set[OpenTag]]
          tags.foreach { tag =>
            if (tag.attributes.contains("id")) {
              byId += tag.attributes("id") -> tag
            }
            tag.attributes.getOrElse("class", "").split(" ").foreach { className =>
              val cn = className.trim
              if (cn.nonEmpty) {
                var classTags = byClass.getOrElse(cn, Set.empty[OpenTag])
                classTags += tag
                byClass += cn -> classTags
              }
            }
            var tagsByName = byTag.getOrElse(tag.tagName, Set.empty[OpenTag])
            tagsByName += tag
            byTag += tag.tagName -> tagsByName
          }
          lastModified = file.lastModified()
          CachedInformation(byId, byClass, byTag)
        } finally {
          input.close()
        }
      }
    }
    new StreamableHTML(file, cacheBuilder)
  }
}

class HTMLParser(input: InputStream) {
  import HTMLParser._

  private var position = 0
  private val b = new StringBuilder
  private var quotes = false
  private var tagStart = -1
  private var tagEnd = -1

  private var open = List.empty[OpenTag]
  private var tags = Set.empty[OpenTag]

  @tailrec
  final def parse(): Set[OpenTag] = input.read() match {
    case -1 => tags   // Finished
    case i => {
      val c = i.toChar
      process(c)
      parse()
    }
  }

  private def process(c: Char): Unit = {
    b.append(c)
    if (c == '"') {
      if (quotes) {
        quotes = false
      } else {
        quotes = true
      }
    } else if (c == '\n' || c == '\r') {
      quotes = false
    } else if (c == '<' && !quotes) {
      b.clear()
      b.append(c)
      tagStart = position
    } else if (tagStart != -1 && c == '>' && !quotes) {
      tagEnd = position + 1
      parseTag()
      b.clear()
      tagStart = -1
    }

    position += 1
  }

  private def parseTag(): Unit = b.toString() match {
    case s if s.startsWith("<!--") || s.endsWith("-->") => // Ignore
    case CloseTagRegex(tagName) => {
      val closeTag = CloseTag(tagName, tagStart, tagEnd)
      val openTag = closeUntil(tagName).copy(close = Some(closeTag))
      tags += openTag
    }
    case SelfClosingTagRegex(tagName, attributes) => {
      val a = parseAttributes(attributes)
      val tag = OpenTag(tagName, a, tagStart, tagEnd, close = None)
      tags += tag
    }
    case OpenTagRegex(tagName, attributes) => {
      val a = parseAttributes(attributes)
      val tag = OpenTag(tagName, a, tagStart, tagEnd, close = None)
      open = tag :: open
    }
  }

  private def parseAttributes(attributes: String): Map[String, String] = {
    val sb = new StringBuilder
    var quoted = false
    var key = ""
    var map = Map.empty[String, String]
    attributes.zipWithIndex.foreach {
      case (c, index) => {
        if (c == '"') {
          if (quoted) {
            map += key -> sb.toString()
            quoted = false
            sb.clear()
          } else {
            quoted = true
          }
        } else if ((c == '=' || c == ' ') && !quoted) {
          if (sb.nonEmpty) {
            key = sb.toString()
            sb.clear()
          }
        } else {
          sb.append(c)
        }
      }
    }
    if (filterAttributes) {
      map.filter(t => validAttributes.contains(t._1))
    } else {
      map
    }
  }

  @tailrec
  private def closeUntil(tagName: String): OpenTag = {
    if (open.isEmpty) {
      throw new RuntimeException(s"Missing close tag for $tagName!")
    }
    val t = open.head
    open = open.tail
    if (t.tagName.equalsIgnoreCase(tagName)) {
      t
    } else {
      tags += t
      closeUntil(tagName)
    }
  }
}