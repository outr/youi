package org.hyperscala.io

import org.jdom2._
import java.io.{OutputStreamWriter, BufferedWriter, OutputStream}
import annotation.tailrec

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class HTMLOutputter(tab: String = "\t", newLine: String = "\n") {
  val expandedTags = Set("title", "script", "div", "a", "select", "textarea")

  def apply(content: Content, writer: OutputWriter = HTMLOutputter.PrintWriter, depth: Int = 0, includeWhitespace: Boolean = true): Unit = content match {
    case element: Element => {
      if (includeWhitespace) {
        writeTabs(writer, depth)
      }
      writer.write("<")
      writer.write(element.getName)
      element.getAttributes.map(a => "%s=\"%s\"".format(a.getName, a.getValue)).mkString(" ") match {
        case "" => // No attributes
        case attributes => writer.write(" %s".format(attributes))
      }
      val children = element.getContent.toList
      if (children.isEmpty && !expandedTags.contains(element.getName)) {
        writer.write("/>")
        if (includeWhitespace) {
          writer.write(newLine)
        }
      } else {
        writer.write(">")
        val childrenHasText = hasText(children)
        if (!childrenHasText) {
          writer.write(newLine)
        }
        writeChildren(children, writer, depth + 1, !childrenHasText)
        if (!childrenHasText) {
          writeTabs(writer, depth)
        }
        writer.write("</%s>".format(element.getName))
        if (includeWhitespace) {
          writer.write(newLine)
        }
      }
    }
    case text: Text => {
//      writeTabs(writer, depth)
      writer.write(text.getText)
//      writer.write(newLine)
    }
  }

  @tailrec
  private def hasText(list: List[Content]): Boolean = {
    if (list.isEmpty) {
      false
    } else {
      if (list.head.isInstanceOf[Text]) {
        true
      } else {
        hasText(list.tail)
      }
    }
  }

  @tailrec
  private def writeChildren(children: List[Content], writer: OutputWriter, depth: Int, includeWhitespace: Boolean): Unit = {
    if (children.nonEmpty) {
      val child = children.head
      apply(child, writer, depth, includeWhitespace)
      writeChildren(children.tail, writer, depth, includeWhitespace)
    }
  }

  @tailrec
  private def writeTabs(writer: OutputWriter, depth: Int): Unit = {
    if (depth > 0) {
      writer.write(tab)
      writeTabs(writer, depth - 1)
    }
  }
}

object HTMLOutputter {
  def apply(output: OutputStream) = StreamWriter(output)
  def apply(output: StringBuilder) = StringBuilderWriter(output)
  val PrintWriter = new OutputWriter {
    def write(s: String) = print(s)

    def close() = {}
  }
}

case class StreamWriter(output: OutputStream) extends OutputWriter {
  val writer = new BufferedWriter(new OutputStreamWriter(output))

  def write(s: String) = writer.write(s)

  def close() = {
    writer.flush()
    writer.close()
  }
}

case class StringBuilderWriter(output: StringBuilder) extends OutputWriter {
  def write(s: String) = output.append(s)

  def close() = {}
}

trait OutputWriter {
  def write(s: String): Unit

  def close(): Unit
}