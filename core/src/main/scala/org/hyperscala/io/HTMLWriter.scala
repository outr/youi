package org.hyperscala.io

import annotation.tailrec

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class HTMLWriter(writer: String => Unit, tab: String = "  ", newLine: String = "\n") {
  private var _tabs: Int = 0

  def tabbed(f: => Any) = {
    _tabs += 1
    f
    _tabs -= 1
  }

  @tailrec
  final def writeTabs(tabs: Int = _tabs): Unit = {
    if (tabs > 0) {
      write(tab)

      writeTabs(tabs - 1)
    }
  }

  def write(s: String) = writer(s)

  def writeLine(s: String) = {
    write(s)
    write(newLine)
  }
}