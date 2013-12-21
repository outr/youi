package org.hyperscala.selector

import org.hyperscala.persistence.ValuePersistence
import org.hyperscala.html.HTMLTag
import org.hyperscala.html.HTMLTagType
import org.hyperscala.IdentifiableTag
import org.powerscala.json.Jsonify

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Selector extends Jsonify {
  def value: String
  def quoted = true

  def matches(t: HTMLTag): Boolean

  def content = if (quoted) s"'$value'" else value

  def parseJson(map: Map[String, Any]) = throw new RuntimeException("Unable to generate Selector from JSON.")

  def generate() = content
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
  def id(t: HTMLTag) = TagIdSelector(t)
  def multiple(selectors: Selector*) = MultipleSelector(selectors.toList)

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
        case _ => StringSelector(selectorString) -> ""
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

  def matches(t: HTMLTag) = true
}

case class StringSelector(value: String, override val quoted: Boolean = true) extends Selector {
  def matches(t: HTMLTag) = false
}

case class ClassSelector(className: String) extends Selector {
  def value = s".$className"

  def matches(t: HTMLTag) = t.clazz().contains(className)
}

case class ElementSelector[T <: HTMLTag](tagType: HTMLTagType[T]) extends Selector {
  def value = tagType.htmlName

  def matches(t: HTMLTag) = tagType.clazz.isAssignableFrom(t.getClass)    // TODO: verify this works
}

case class DescendantSelector(parent: Selector, child: Selector) extends Selector {
  def value = s"${parent.value} ${child.value}"

  // TODO: fix matching support
  def matches(t: HTMLTag) = t.parent != null && parent.matches(t.parent.asInstanceOf[HTMLTag]) && child.matches(t)
}

case class ChildSelector(parent: Selector, child: Selector) extends Selector {
  def value = s"${parent.value} > ${child.value}"

  def matches(t: HTMLTag) = t.parent != null && parent.matches(t.parent.asInstanceOf[HTMLTag]) && child.matches(t)
}

case class PseudoClassSelector(selector: Selector, clazz: PseudoClass) extends Selector {
  def value = s"${selector.value}:${clazz.value}"

  def matches(t: HTMLTag) = selector.matches(t)
}

case class PrecedingSelector(selector: Selector, sibling: Selector) extends Selector {
  def value = s"${selector.value} + ${sibling.value}"

  def matches(t: HTMLTag) = throw new NotImplementedError("Matching support not implemented for this selector")
}

case class AttributeExistsSelector(selector: Selector, attribute: String) extends Selector {
  def value = if (selector != null) {
    s"${selector.value}[$attribute]"
  } else {
    s"[$attribute]"
  }

  def matches(t: HTMLTag) = t.attributes.contains(attribute)
}

case class AttributeSelector(selector: Selector, attribute: String, matcher: AttributeMatcher, attributeValue: String) extends Selector {
  def value = if (selector != null) {
    s"""${selector.value}[$attribute${matcher.value}"$attributeValue"]"""
  } else {
    s"""[$attribute${matcher.value}"$attributeValue"]"""
  }

  def matches(t: HTMLTag) = t.attributes.get(attribute) match {
    case Some(a) => a.attributeValue == attributeValue
    case None => false
  }
}

case class IdSelector(id: String) extends Selector {
  def value = s"#$id"

  def matches(t: HTMLTag) = t.id() == id
}

case class TagIdSelector(tag: IdentifiableTag) extends Selector {
  def value = s"#${tag.identity}"

  def matches(t: HTMLTag) = tag.identity == t.id()
}

case class MultipleSelector(selectors: List[Selector]) extends Selector {
  def value = selectors.map(s => s.value).mkString(", ")

  def matches(t: HTMLTag) = selectors.find(s => s.matches(t)).nonEmpty
}