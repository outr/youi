package org.hyperscala.selector

import org.hyperscala.persistence.ValuePersistence
import org.hyperscala.html.HTMLTag
import org.hyperscala.html.HTMLTagType

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Selector {
  def value: String
}

object Selector extends ValuePersistence[List[Selector]] {
  def all = AllSelector
  def clazz(className: String) = ClassSelector(className)
  def element[T <: HTMLTag](implicit manifest: Manifest[T]) = {
    ElementSelector(HTMLTagType.byClass(manifest.runtimeClass.asInstanceOf[Class[T]]).get)
  }
  def descendant(parent: Selector, child: Selector) = DescendantSelector(parent, child)
  def child(parent: Selector, child: Selector) = ChildSelector(parent, child)
  def pseudo(selector: Selector, clazz: PseudoClass) = PseudoClassSelector(selector, clazz)
  def preceding(selector: Selector, sibling: Selector) = PrecedingSelector(selector, sibling)
  def attribute(selector: Selector, attribute: String) = AttributeExistsSelector(selector, attribute)
  def attribute(selector: Selector, attribute: String, matcher: AttributeMatcher, attributeValue: String) = {
    AttributeSelector(selector, attribute, matcher, attributeValue)
  }
  def id(id: String) = IdSelector(id)

  def fromString(s: String, name: String, clazz: Class[_]) = throw new UnsupportedOperationException("Not supported")

  def toString(t: List[Selector], name: String, clazz: Class[_]) = {
    t.map(sss => sss.value).mkString(", ")
  }

  private val AllSelectorRegex = """[*](.*)""".r
  private val ClassSelectorRegex = """[.](\w*)(.*)""".r
  private val ElementSelectorRegex = """(\w*)(.*)""".r
  private val IdSelectorRegex = """[#](\w*)(.*)""".r
  private val ChildSelectorRegex = """[ ]*[>][ ]*(.*)""".r
  private val PseudoSelectorRegex = """[ ]*[:][ ]*(\w*)(.*)""".r
  private val PrecedingSelectorRegex = """[ ]*[+][ ]*(.*)""".r
  private val DescendantSelectorRegex = """[ ]*(.*)""".r

  def apply(selectorString: String, parent: Selector = null): Selector = {
    val (selector, more) = parent match {
      case null => selectorString match {
        case AllSelectorRegex(s) => AllSelector -> s
        case ClassSelectorRegex(className, s) => ClassSelector(className) -> s
        case IdSelectorRegex(id, s) => IdSelector(id) -> s
        case ElementSelectorRegex(element, s) if HTMLTagType(element) != null => ElementSelector(HTMLTagType(element)) -> s
      }
      case _ => selectorString match {
        case ChildSelectorRegex(s) => ChildSelector(parent, apply(s)) -> ""
        case PseudoSelectorRegex(pseudo, s) => PseudoClassSelector(parent, PseudoClass(pseudo)) -> s
        case PrecedingSelectorRegex(s) => PrecedingSelector(parent, apply(s)) -> ""
        case DescendantSelectorRegex(s) => DescendantSelector(parent, apply(s)) -> ""
      }
    }
    if (more.trim.nonEmpty) {
      apply(more, selector)
    } else {
      selector
    }
  }
}

object AllSelector extends Selector {
  val value = "*"
}

case class ClassSelector(className: String) extends Selector {
  def value = s".$className"
}

case class ElementSelector[T <: HTMLTag](tagType: HTMLTagType[T]) extends Selector {
  def value = tagType.htmlName
}

case class DescendantSelector(parent: Selector, child: Selector) extends Selector {
  def value = s"${parent.value} ${child.value}"
}

case class ChildSelector(parent: Selector, child: Selector) extends Selector {
  def value = s"${parent.value} > ${child.value}"
}

case class PseudoClassSelector(selector: Selector, clazz: PseudoClass) extends Selector {
  def value = s"${selector.value}:${clazz.value}"
}

case class PrecedingSelector(selector: Selector, sibling: Selector) extends Selector {
  def value = s"${selector.value} + ${sibling.value}"
}

case class AttributeExistsSelector(selector: Selector, attribute: String) extends Selector {
  def value = s"${selector.value}[$attribute]"
}

case class AttributeSelector(selector: Selector, attribute: String, matcher: AttributeMatcher, attributeValue: String) extends Selector {
  def value = "%s[%s%s\"%s\"]".format(selector.value, attribute, matcher.value, attributeValue)
}

case class IdSelector(id: String) extends Selector {
  def value = s"#$id"
}