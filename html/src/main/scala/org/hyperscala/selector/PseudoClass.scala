package org.hyperscala.selector

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.html.attributes.Direction

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class PseudoClass private(value: String) extends EnumEntry

object PseudoClass extends Enumerated[PseudoClass] {
  val active = PseudoClass("active")
  val after = PseudoClass("after")
  val before = PseudoClass("before")
  val checked = PseudoClass("checked")
  val choices = PseudoClass("choices")
  val default = PseudoClass("default")
  def dir(direction: Direction) = PseudoClass(direction.value)
  val disabled = PseudoClass("disabled")
  val empty = PseudoClass("empty")
  val enabled = PseudoClass("enabled")
  val even = nthChild("event")
  val firstChild = PseudoClass("first-child")
  val firstLetter = PseudoClass("first-child")
  val firstLine = PseudoClass("first-line")
  val firstOfType = PseudoClass("first-of-type")
  val focus = PseudoClass("focus")
  val hover = PseudoClass("hover")
  val indeterminate = PseudoClass("indeterminate")
  val inRange = PseudoClass("in-range")
  val invalid = PseudoClass("invalid")
  def lang(language: String) = PseudoClass(s"lang($language)")
  val lastChild = PseudoClass("last-child")
  val lastOfType = PseudoClass("last-of-type")
  val left = PseudoClass("left")
  val link = PseudoClass("link")
  def not(selector: Selector) = PseudoClass(selector.value)
  def nthChild(s: String): PseudoClass = PseudoClass(s"nth-child($s)")
  def nthChild(groupedBy: Int, index: Int): PseudoClass = nthChild(s"${groupedBy}n+$index")
  def nthLastChild(s: String): PseudoClass = PseudoClass(s"nth-last-child($s)")
  def nthLastChild(groupedBy: Int, index: Int): PseudoClass = nthLastChild(s"${groupedBy}n+$index")
  def nthLastOfType(s: String): PseudoClass = PseudoClass(s"nth-last-of-type($s)")
  def nthLastOfType(groupedBy: Int, index: Int): PseudoClass = nthLastOfType(s"${groupedBy}n+$index")
  def nthOfType(s: String): PseudoClass = PseudoClass(s"nth-of-type($s)")
  def nthOfType(groupedBy: Int, index: Int): PseudoClass = nthOfType(s"${groupedBy}n+$index")
  val odd = nthChild("odd")
  val onlyChild = PseudoClass("only-child")
  val onlyOfType = PseudoClass("only-of-type")
  val optional = PseudoClass("optional")
  val outOfRange = PseudoClass("out-of-range")
  val readOnly = PseudoClass("read-only")
  val readWrite = PseudoClass("read-write")
  val repeatIndex = PseudoClass("repeat-index")
  val repeatItem = PseudoClass("repeat-item")
  val required = PseudoClass("required")
  val right = PseudoClass("right")
  val root = PseudoClass("root")
  val scope = PseudoClass("scope")
  val selection = PseudoClass("selection")
  val target = PseudoClass("target")
  val valid = PseudoClass("valid")
  val value = PseudoClass("value")
  val visited = PseudoClass("visited")
}