package org.hyperscala.selector

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.html.attributes.Direction

/**
 * @author Matt Hicks <matt@outr.com>
 */
class PseudoClass private(val value: String) extends EnumEntry

object PseudoClass extends Enumerated[PseudoClass] {
  val Active = new PseudoClass("active")
  val After = new PseudoClass("after")
  val Before = new PseudoClass("before")
  val Checked = new PseudoClass("checked")
  val Choices = new PseudoClass("choices")
  val Default = new PseudoClass("default")
  def Dir(direction: Direction) = new PseudoClass(direction.value)
  val Disabled = new PseudoClass("disabled")
  val Empty = new PseudoClass("empty")
  val Enabled = new PseudoClass("enabled")
  val Even = NthChild("event")
  val FirstChild = new PseudoClass("first-child")
  val FirstLetter = new PseudoClass("first-child")
  val FirstLine = new PseudoClass("first-line")
  val FirstOfType = new PseudoClass("first-of-type")
  val Focus = new PseudoClass("focus")
  val Hover = new PseudoClass("hover")
  val Indeterminate = new PseudoClass("indeterminate")
  val InRange = new PseudoClass("in-range")
  val Invalid = new PseudoClass("invalid")
  def Lang(language: String) = new PseudoClass(s"lang($language)")
  val LastChild = new PseudoClass("last-child")
  val LastOfType = new PseudoClass("last-of-type")
  val Left = new PseudoClass("left")
  val Link = new PseudoClass("link")
  def Not(selector: Selector) = new PseudoClass(selector.value)
  def NthChild(s: String): PseudoClass = new PseudoClass(s"nth-child($s)")
  def NthChild(groupedBy: Int, index: Int): PseudoClass = NthChild(s"${groupedBy}n+$index")
  def NthLastChild(s: String): PseudoClass = new PseudoClass(s"nth-last-child($s)")
  def NthLastChild(groupedBy: Int, index: Int): PseudoClass = NthLastChild(s"${groupedBy}n+$index")
  def NthLastOfType(s: String): PseudoClass = new PseudoClass(s"nth-last-of-type($s)")
  def NthLastOfType(groupedBy: Int, index: Int): PseudoClass = NthLastOfType(s"${groupedBy}n+$index")
  def NthOfType(s: String): PseudoClass = new PseudoClass(s"nth-of-type($s)")
  def NthOfType(groupedBy: Int, index: Int): PseudoClass = NthOfType(s"${groupedBy}n+$index")
  val Odd = NthChild("odd")
  val OnlyChild = new PseudoClass("only-child")
  val OnlyOfType = new PseudoClass("only-of-type")
  val Optional = new PseudoClass("optional")
  val OutOfRange = new PseudoClass("out-of-range")
  val ReadOnly = new PseudoClass("read-only")
  val ReadWrite = new PseudoClass("read-write")
  val RepeatIndex = new PseudoClass("repeat-index")
  val RepeatItem = new PseudoClass("repeat-item")
  val Required = new PseudoClass("required")
  val Right = new PseudoClass("right")
  val Root = new PseudoClass("root")
  val Scope = new PseudoClass("scope")
  val Selection = new PseudoClass("selection")
  val Target = new PseudoClass("target")
  val Valid = new PseudoClass("valid")
  val Value = new PseudoClass("value")
  val Visited = new PseudoClass("visited")
}