package org.hyperscala.selector

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.html.attributes.Direction

/**
 * @author Matt Hicks <matt@outr.com>
 */
class PseudoClass private(val value: String) extends EnumEntry

object PseudoClass extends Enumerated[PseudoClass] {
  val active = new PseudoClass("active")
  val after = new PseudoClass("after")
  val before = new PseudoClass("before")
  val checked = new PseudoClass("checked")
  val choices = new PseudoClass("choices")
  val default = new PseudoClass("default")
  def dir(direction: Direction) = new PseudoClass(direction.value)
  val disabled = new PseudoClass("disabled")
  val empty = new PseudoClass("empty")
  val enabled = new PseudoClass("enabled")
  val even = nthChild("event")
  val firstChild = new PseudoClass("first-child")
  val firstLetter = new PseudoClass("first-child")
  val firstLine = new PseudoClass("first-line")
  val firstOfType = new PseudoClass("first-of-type")
  val focus = new PseudoClass("focus")
  val hover = new PseudoClass("hover")
  val indeterminate = new PseudoClass("indeterminate")
  val inRange = new PseudoClass("in-range")
  val invalid = new PseudoClass("invalid")
  def lang(language: String) = new PseudoClass(s"lang($language)")
  val lastChild = new PseudoClass("last-child")
  val lastOfType = new PseudoClass("last-of-type")
  val left = new PseudoClass("left")
  val link = new PseudoClass("link")
  def not(selector: Selector) = new PseudoClass(selector.value)
  def nthChild(s: String): PseudoClass = new PseudoClass(s"nth-child($s)")
  def nthChild(groupedBy: Int, index: Int): PseudoClass = nthChild(s"${groupedBy}n+$index")
  def nthLastChild(s: String): PseudoClass = new PseudoClass(s"nth-last-child($s)")
  def nthLastChild(groupedBy: Int, index: Int): PseudoClass = nthLastChild(s"${groupedBy}n+$index")
  def nthLastOfType(s: String): PseudoClass = new PseudoClass(s"nth-last-of-type($s)")
  def nthLastOfType(groupedBy: Int, index: Int): PseudoClass = nthLastOfType(s"${groupedBy}n+$index")
  def nthOfType(s: String): PseudoClass = new PseudoClass(s"nth-of-type($s)")
  def nthOfType(groupedBy: Int, index: Int): PseudoClass = nthOfType(s"${groupedBy}n+$index")
  val odd = nthChild("odd")
  val onlyChild = new PseudoClass("only-child")
  val onlyOfType = new PseudoClass("only-of-type")
  val optional = new PseudoClass("optional")
  val outOfRange = new PseudoClass("out-of-range")
  val readOnly = new PseudoClass("read-only")
  val readWrite = new PseudoClass("read-write")
  val repeatIndex = new PseudoClass("repeat-index")
  val repeatItem = new PseudoClass("repeat-item")
  val required = new PseudoClass("required")
  val right = new PseudoClass("right")
  val root = new PseudoClass("root")
  val scope = new PseudoClass("scope")
  val selection = new PseudoClass("selection")
  val target = new PseudoClass("target")
  val valid = new PseudoClass("valid")
  val value = new PseudoClass("value")
  val visited = new PseudoClass("visited")
}