package org.hyperscala.io

import org.jdom2._
import java.io.{OutputStreamWriter, BufferedWriter, OutputStream}
import scala.collection.JavaConversions._
import annotation.tailrec

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class HTMLOutputter(tab: String = "\t", newLine: String = "\r\n") {
  val expandedTags = Set("title", "script", "div")

  def apply(content: Content, writer: OutputWriter = HTMLOutputter.PrintWriter, depth: Int = 0): Unit = content match {
    case element: Element => {
      writeTabs(writer, depth)
      writer.write("<")
      writer.write(element.getName)
      element.getAttributes.map(a => "%s=\"%s\"".format(a.getName, a.getValue)).mkString(" ") match {
        case "" => // No attributes
        case attributes => writer.write(" %s".format(attributes))
      }
      val children = element.getContent.toList
      if (children.isEmpty && !expandedTags.contains(element.getName)) {
        writer.write("/>")
        writer.write(newLine)
      } else {
        writer.write(">")
        writer.write(newLine)
        writeChildren(children, writer, depth + 1)
        writeTabs(writer, depth)
        writer.write("</%s>".format(element.getName))
        writer.write(newLine)
      }
    }
    case text: Text => {
      writeTabs(writer, depth)
      writer.write(text.getTextTrim)
      writer.write(newLine)
    }
  }

  @tailrec
  private def writeChildren(children: List[Content], writer: OutputWriter, depth: Int): Unit = {
    if (children.nonEmpty) {
      val child = children.head
      apply(child, writer, depth)
      writeChildren(children.tail, writer, depth)
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
  val PrintWriter = new OutputWriter {
    def write(s: String) = print(s)

    def close() = {}
  }
  def OutputStreamWriter(output: OutputStream) = StreamWriter(output)
}

case class StreamWriter(output: OutputStream) extends OutputWriter {
  val writer = new BufferedWriter(new OutputStreamWriter(output))

  def write(s: String) = writer.write(s)

  def close() = {
    writer.flush()
    writer.close()
  }
}

trait OutputWriter {
  def write(s: String): Unit

  def close(): Unit
}