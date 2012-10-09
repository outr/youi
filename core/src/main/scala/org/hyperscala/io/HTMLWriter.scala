package org.hyperscala.io

import annotation.tailrec
import java.io.OutputStream

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait HTMLWriter {
  private var _tabs: Int = 0

  def tabbed(f: => Any) = {
    _tabs += 1
    f
    _tabs -= 1
  }

  def tab: String
  def newLine: String

  @tailrec
  final def writeTabs(tabs: Int = _tabs): Unit = {
    if (tabs > 0) {
      write(tab)

      writeTabs(tabs - 1)
    }
  }

  def write(s: String): Unit

  def writeLine(s: String) = {
    write(s)
    write(newLine)
  }
}

object HTMLWriter {
  def apply(output: OutputStream = null, tabString: String = "  ", newLineString: String = "\n") = output match {
    case null => new StringBuilderHTMLWriter {
      def tab = tabString

      def newLine = newLineString
    }
    case _ => new OutputStreamHTMLWriter {
      def outputStream = output

      def tab = tabString

      def newLine = newLineString
    }
  }
}