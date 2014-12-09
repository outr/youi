package org.hyperscala.selector

import org.hyperscala.IdentifiableTag
import org.hyperscala.html.{HTMLTag, HTMLTagType}
import org.hyperscala.persistence.ValuePersistence

import scala.annotation.tailrec

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Selector {
  def root: Selector = parentSelector match {
    case Some(p) => p.root
    case None => this
  }
  def parentSelector: Option[Selector]
  final lazy val value: String = parentSelector match {
    case Some(p) => s"${p.value}$thisValue"
    case None => thisValue
  }
  def quoted = true

  final def matches(t: HTMLTag): Boolean = if (thisMatches(t)) {
    parentSelector match {
      case Some(p) => p.matches(t)
      case None => true
    }
  } else {
    false
  }

  def thisValue: String
  def thisMatches(t: HTMLTag): Boolean

  def content = if (quoted) s"'$value'" else value

  def parseJson(map: Map[String, Any]) = throw new RuntimeException("Unable to generate Selector from JSON.")

  def generate() = content

  def toList = buildList(this)

  /**
   * Generates a flat itemized list broken out by multiple separators (,)
   */
  def items = value.split(",").map(s => Selector(s.trim)).toList

  def duplicate(parent: Option[Selector]): Selector

  def addRoot(newRoot: Selector): Selector = parentSelector match {
    case Some(p) => duplicate(Some(p.addRoot(newRoot)))
    case None => duplicate(Some(newRoot))
  }

  @tailrec
  private def buildList(selector: Selector, list: List[Selector] = Nil): List[Selector] = {
    val updated = selector :: list
    selector.parentSelector match {
      case None => updated
      case Some(p) => buildList(p, updated)
    }
  }
}

object Selector extends ValuePersistence[List[Selector]] {
  lazy val all = AllSelector()
  def clazz(className: String, parent: Option[Selector] = None) = ClassSelector(className, parent)
  def element[T <: HTMLTag](implicit manifest: Manifest[T]) = {
    ElementSelector(HTMLTagType.byClass(manifest.runtimeClass.asInstanceOf[Class[T]]).get)
  }
//  def descendant(parent: Selector, child: Selector) = DescendantSelector(parent, child)
//  def child(parent: Selector, child: Selector) = ChildSelector(parent, child)
  def pseudo(clazz: PseudoClass, parent: Option[Selector] = None) = PseudoClassSelector(clazz, parent)
//  def preceding(selector: Selector, sibling: Selector) = PrecedingSelector(selector, sibling)
  def attribute(attribute: String, parent: Option[Selector]) = AttributeExistsSelector(attribute, parent)
  def attribute(attribute: String, matcher: AttributeMatcher, attributeValue: String, parent: Option[Selector]) = {
    AttributeSelector(attribute, matcher, attributeValue, parent)
  }
  def id(id: String) = IdSelector(id)
  def id(t: HTMLTag) = TagIdSelector(t)
  def multiple(selectors: Selector*) = {
    var current: Selector = selectors.head
    selectors.tail.foreach {
      case s => {
        val multiple = MultipleSelector(Some(current))
        current = s.addRoot(multiple)
      }
    }
    current
  }

  def fromString(s: String, name: String, clazz: Class[_]) = throw new UnsupportedOperationException("Not supported")

  def toString(t: List[Selector], name: String, clazz: Class[_]) = {
    t.map(sss => sss.value).mkString(", ")
  }

  private val AllSelectorRegex = """[*](.*)""".r
  private val ClassSelectorRegex = """[.]([a-zA-Z0-9-_]*)(.*)""".r
  private val ElementSelectorRegex = """([a-zA-Z0-9-]+)(.*)""".r
  private val IdSelectorRegex = """[#]([a-zA-Z0-9-_]*)(.*)""".r
  private val ChildSelectorRegex = """[ ]?[>][ ]?(.*)""".r
  private val PseudoSelectorRegex = """[ ]?[:][ ]?([a-zA-Z0-9-_]*)(.*)""".r
  private val PrecedingSelectorRegex = """[ ]*[+][ ]*(.*)""".r
  private val DescendantSelectorRegex = """[ ](.*)""".r
  private val AttributeValueSelectorRegex = """\[(\S*?)(=|~=|\|=)\"(.+)\"\](.*)""".r
  private val AttributeExistsSelectorRegex = """\[(\S*?)\](.*)""".r
  private val MultipleSelectorRegex = """[ ]?,[ ]?(.*)""".r

  final def apply(selectorString: String, parent: Option[Selector] = None): Selector = selectorString match {
    case null | "" => parent.getOrElse(throw new RuntimeException(s"Unable to parse selector from: [$selectorString]."))
    case AllSelectorRegex(extra) => apply(extra, Some(AllSelector(parent)))
    case ClassSelectorRegex(className, extra) => apply(extra, Some(ClassSelector(className, parent)))
    case IdSelectorRegex(id, extra) => apply(extra, Some(IdSelector(id, parent)))
    case ElementSelectorRegex(element, extra) => apply(extra, Some(ElementSelector(HTMLTagType.get(element).getOrElse(throw new RuntimeException(s"Unable to find tag type from: [$element].")), parent)))
    case ChildSelectorRegex(extra) => apply(extra, Some(ChildSelector(parent)))
    case PseudoSelectorRegex(pseudo, extra) => apply(extra, Some(PseudoClassSelector(PseudoClass(pseudo), parent)))
    case PrecedingSelectorRegex(extra) => apply(extra, Some(PrecedingSelector(parent)))
    case DescendantSelectorRegex(extra) => apply(extra, Some(DescendantSelector(parent)))
    case AttributeValueSelectorRegex(attribute, matcher, value, extra) => apply(extra, Some(AttributeSelector(attribute, AttributeMatcher(matcher), value, parent)))
    case AttributeExistsSelectorRegex(attribute, extra) => apply(extra, Some(AttributeExistsSelector(attribute, parent)))
    case MultipleSelectorRegex(extra) => apply(extra, Some(MultipleSelector(parent)))
  }

  def get(selectorString: String) = if (selectorString != null && selectorString.trim.nonEmpty) {
    Some(apply(selectorString))
  } else {
    None
  }
}

case class AllSelector(parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = "*"
  def thisMatches(t: HTMLTag) = true
  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

case class ClassSelector(className: String, parentSelector: Option[Selector] = None) extends Selector {
  if (!ClassSelector.isValid(className)) throw new RuntimeException(s"Invalid class selector: $className")

  def thisValue = s".$className"

  def thisMatches(t: HTMLTag) = t.clazz().contains(className)
  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

object ClassSelector {
  val Regex = """-?[_a-zA-Z]+[_a-zA-Z0-9-]*"""

  def isValid(className: String) = className.matches(Regex)
}

case class ElementSelector[T <: HTMLTag](tagType: HTMLTagType[T], parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = tagType.htmlName

  def thisMatches(t: HTMLTag) = tagType.clazz.isAssignableFrom(t.getClass)    // TODO: verify this works

  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

case class DescendantSelector(parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = " "

  // TODO: fix matching support
  def thisMatches(t: HTMLTag) = false

  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

case class ChildSelector(parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = " > "

  def thisMatches(t: HTMLTag) = false    // TODO: support properly

  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

case class PseudoClassSelector(clazz: PseudoClass, parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = s":${clazz.value}"

  def thisMatches(t: HTMLTag) = true

  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

case class PrecedingSelector(parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = " + "

  def thisMatches(t: HTMLTag) = throw new NotImplementedError("Matching support not implemented for this selector")

  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

case class AttributeExistsSelector(attribute: String, parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = s"[$attribute]"

  def thisMatches(t: HTMLTag) = t.attributes.contains(attribute)

  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

case class AttributeSelector(attribute: String, matcher: AttributeMatcher, attributeValue: String, parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = s"""[$attribute${matcher.value}"$attributeValue"]"""

  def thisMatches(t: HTMLTag) = t.attributes.get(attribute) match {
    case Some(a) => a.attributeValue == attributeValue
    case None => false
  }

  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

case class IdSelector(id: String, parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = s"#$id"

  def thisMatches(t: HTMLTag) = t.id() == id

  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

case class TagIdSelector(tag: IdentifiableTag, parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = s"#${tag.identity}"

  def thisMatches(t: HTMLTag) = tag.identity == t.id()

  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}

case class MultipleSelector(parentSelector: Option[Selector] = None) extends Selector {
  def thisValue = ", "

  def thisMatches(t: HTMLTag) = false // TODO: implement

  def duplicate(parent: Option[Selector]) = copy(parentSelector = parent)
}