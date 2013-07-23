package org.hyperscala.css

import org.hyperscala.html.{HTMLTag, HTMLTagType}
import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait StyleSheetSelector {
  def value: String
}

object StyleSheetSelector extends ValuePersistence[List[StyleSheetSelector]] {
  def all = AllSelector
  def element[T <: HTMLTag](implicit manifest: Manifest[T]) = {
    ElementSelector(HTMLTagType.byClass(manifest.runtimeClass.asInstanceOf[Class[T]]).get)
  }
  def descendant(parent: StyleSheetSelector, child: StyleSheetSelector) = DescendantSelector(parent, child)
  def child(parent: StyleSheetSelector, child: StyleSheetSelector) = ChildSelector(parent, child)
  def pseudo(selector: StyleSheetSelector, clazz: PseudoClass) = PseudoClassSelector(selector, clazz)
  def preceding(selector: StyleSheetSelector, sibling: StyleSheetSelector) = PrecedingSelector(selector, sibling)
  def attribute(selector: StyleSheetSelector, attribute: String) = AttributeExistsSelector(selector, attribute)
  def attribute(selector: StyleSheetSelector, attribute: String, matcher: AttributeMatcher, attributeValue: String) = {
    AttributeSelector(selector, attribute, matcher, attributeValue)
  }
  def id(selector: StyleSheetSelector, id: String) = IdSelector(selector, id)

  def fromString(s: String, name: String, clazz: Class[_]) = throw new UnsupportedOperationException("Not supported")

  def toString(t: List[StyleSheetSelector], name: String, clazz: Class[_]) = {
    t.map(sss => sss.value).mkString(", ")
  }
}

object AllSelector extends StyleSheetSelector {
  val value = "*"
}

case class ElementSelector[T <: HTMLTag](tagType: HTMLTagType[T]) extends StyleSheetSelector {
  def value = tagType.htmlName
}

case class DescendantSelector(parent: StyleSheetSelector, child: StyleSheetSelector) extends StyleSheetSelector {
  def value = s"${parent.value} ${child.value}"
}

case class ChildSelector(parent: StyleSheetSelector, child: StyleSheetSelector) extends StyleSheetSelector {
  def value = s"${parent.value} > ${child.value}"
}

case class PseudoClassSelector(selector: StyleSheetSelector, clazz: PseudoClass) extends StyleSheetSelector {
  def value = s"${selector.value}:${clazz.value}"
}

case class PrecedingSelector(selector: StyleSheetSelector, sibling: StyleSheetSelector) extends StyleSheetSelector {
  def value = s"${selector.value} + ${sibling.value}"
}

case class AttributeExistsSelector(selector: StyleSheetSelector, attribute: String) extends StyleSheetSelector {
  def value = s"${selector.value}[$attribute]"
}

case class AttributeSelector(selector: StyleSheetSelector, attribute: String, matcher: AttributeMatcher, attributeValue: String) extends StyleSheetSelector {
  def value = "%s[%s%s\"%s\"]".format(selector.value, attribute, matcher.value, attributeValue)
}

case class IdSelector(selector: StyleSheetSelector, id: String) extends StyleSheetSelector {
  def value = s"${selector.value}#$id"
}