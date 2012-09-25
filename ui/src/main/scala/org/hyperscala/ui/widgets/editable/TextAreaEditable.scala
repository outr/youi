package org.hyperscala.ui.widgets.editable

import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait TextAreaEditable {
  this: Editable[_, tag.TextArea] =>

  val editor = new tag.TextArea

//  /**
//   * Defines whether line-breaks in the content should be converted to HTML breaks.
//   *
//   * Defaults to true.
//   */
//  def supportLineBreaks = true
//
//  override def visualize(value: T) = toString(value) match {
//    case null => null
//    case s if (supportLineBreaks) => s.replaceAll("\n", "<br/>")
//    case s => s
//  }
}

/**
trait InputEditable {
  this: Editable[_, tag.Input] =>

  val editor = new tag.Input
}*/