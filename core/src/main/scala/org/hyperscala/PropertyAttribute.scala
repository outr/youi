package org.hyperscala

import org.powerscala.property.{PropertyParent, StandardProperty}
import org.powerscala.property.backing.{VariableBacking, Backing}
import persistence.ValuePersistence
import org.jdom2.Element

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class PropertyAttribute[T](_name: String, default: T, inclusion: InclusionMode = InclusionMode.NotEmpty, backing: Backing[T] = new VariableBacking[T])
                             (implicit persister: ValuePersistence[T], parent: PropertyParent, val manifest: Manifest[T])
                             extends StandardProperty[T](_name, default, backing)(parent) with XMLAttribute {
  // TODO: remove this
  def attributeValue = persister.toString(value, manifest.erasure)

  // TODO: remove this
  def attributeValue_=(value: String) = this := persister.fromString(value, manifest.erasure)

  def shouldRender = include

  def write(markup: Markup, element: Element) = if (shouldRender) {
    element.setAttribute(name(), attributeValue)
  }

  def read(markup: Markup, value: String) = {
    attributeValue = value
  }

  private def include = inclusion match {
    case InclusionMode.Always => true
    case InclusionMode.Exclude => false
    case InclusionMode.NotEmpty => modified && (value == true || (attributeValue != null && attributeValue.length > 0))
    case InclusionMode.Modified => modified
  }
}

// TODO: migrate to powerscala
trait LazyProperty[T] extends StandardProperty[T] {
  private var useLazy = true
  protected def lazyValue: T

  /**
   * true if the lazy property has already been loaded
   */
  def loaded = !useLazy

  override def apply() = {
    if (useLazy) {
      apply(lazyValue)
    }
    super.apply()
  }

  override def apply(v: T) = {
    useLazy = false
    super.apply(v)
  }
}

object PropertyAttribute {
  def apply[T](name: String, default: T, inclusion: InclusionMode = InclusionMode.NotEmpty, backing: Backing[T] = new VariableBacking[T])
              (implicit persister: ValuePersistence[T], parent: PropertyParent, manifest: Manifest[T]) = {
    new PropertyAttribute[T](name, default, inclusion, backing)(persister, parent, manifest)
  }
}