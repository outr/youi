package org.hyperscala.selector

import org.powerscala.StringUtil
import org.hyperscala.html.attributes.Direction

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class PseudoClass(value: String) {
  lazy val name = StringUtil.toCamelCase(value)
  lazy val label = StringUtil.generateLabel(name)
}

object PseudoClass {
  val Active = PseudoClass("active")
  val After = PseudoClass("after")
  val Before = PseudoClass("before")
  val Checked = PseudoClass("checked")
  val Choices = PseudoClass("choices")
  val Default = PseudoClass("default")

  def Dir(direction: Direction) = PseudoClass(direction.value)

  val Disabled = PseudoClass("disabled")
  val Empty = PseudoClass("empty")
  val Enabled = PseudoClass("enabled")
  val Even = NthChild("event")
  val FirstChild = PseudoClass("first-child")
  val FirstLetter = PseudoClass("first-child")
  val FirstLine = PseudoClass("first-line")
  val FirstOfType = PseudoClass("first-of-type")
  val Focus = PseudoClass("focus")
  val Hover = PseudoClass("hover")
  val Indeterminate = PseudoClass("indeterminate")
  val InRange = PseudoClass("in-range")
  val Invalid = PseudoClass("invalid")

  def Lang(language: String) = PseudoClass(s"lang($language)")

  val LastChild = PseudoClass("last-child")
  val LastOfType = PseudoClass("last-of-type")
  val Left = PseudoClass("left")
  val Link = PseudoClass("link")

  def Not(selector: Selector) = PseudoClass(selector.value)

  def NthChild(s: String): PseudoClass = PseudoClass(s"nth-child($s)")

  def NthChild(groupedBy: Int, index: Int): PseudoClass = NthChild(s"${groupedBy}n+$index")

  def NthLastChild(s: String): PseudoClass = PseudoClass(s"nth-last-child($s)")

  def NthLastChild(groupedBy: Int, index: Int): PseudoClass = NthLastChild(s"${groupedBy}n+$index")

  def NthLastOfType(s: String): PseudoClass = PseudoClass(s"nth-last-of-type($s)")

  def NthLastOfType(groupedBy: Int, index: Int): PseudoClass = NthLastOfType(s"${groupedBy}n+$index")

  def NthOfType(s: String): PseudoClass = PseudoClass(s"nth-of-type($s)")

  def NthOfType(groupedBy: Int, index: Int): PseudoClass = NthOfType(s"${groupedBy}n+$index")

  val Odd = NthChild("odd")
  val OnlyChild = PseudoClass("only-child")
  val OnlyOfType = PseudoClass("only-of-type")
  val Optional = PseudoClass("optional")
  val OutOfRange = PseudoClass("out-of-range")
  val ReadOnly = PseudoClass("read-only")
  val ReadWrite = PseudoClass("read-write")
  val RepeatIndex = PseudoClass("repeat-index")
  val RepeatItem = PseudoClass("repeat-item")
  val Required = PseudoClass("required")
  val Right = PseudoClass("right")
  val Root = PseudoClass("root")
  val Scope = PseudoClass("scope")
  val Selection = PseudoClass("selection")
  val Target = PseudoClass("target")
  val Valid = PseudoClass("valid")
  val Value = PseudoClass("value")
  val Visited = PseudoClass("visited")

  val values = Vector(
    Active,
    After,
    Before,
    Checked,
    Choices,
    Default,
    Disabled,
    Empty,
    Enabled,
    Even,
    FirstChild,
    FirstLetter,
    FirstLine,
    FirstOfType,
    Focus,
    Hover,
    Indeterminate,
    InRange,
    Invalid,
    LastChild,
    LastOfType,
    Left,
    Link,
    Odd,
    OnlyChild,
    OnlyOfType,
    Optional,
    OutOfRange,
    ReadOnly,
    ReadWrite,
    RepeatIndex,
    RepeatItem,
    Required,
    Right,
    Root,
    Scope,
    Selection,
    Target,
    Valid,
    Value,
    Visited
  )
}