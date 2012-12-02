package org.hyperscala

import io.HTMLWriter
import org.powerscala.property.{PropertyParent, StandardProperty}
import org.powerscala.property.backing.{VariableBacking, Backing}
import persistence.ValuePersistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class PropertyAttribute[T](_name: String, default: T, val inclusion: InclusionMode = InclusionMode.NotEmpty, backing: Backing[T] = new VariableBacking[T])
                             (implicit persister: ValuePersistence[T], parent: PropertyParent, manifest: Manifest[T])
                             extends StandardProperty[T](_name, default, backing)(parent, manifest) with XMLAttribute {
  Page() match {
    case null => // May not be part of a page
    case page => page.intercept.initAttribute.fire(this)   // Fire the initialization to be intercepted
  }

  // TODO: remove this
  def attributeValue = persister.toString(value, manifest.erasure)

  // TODO: remove this
  def attributeValue_=(value: String) = this := persister.fromString(value, manifest.erasure)

  def shouldRender = include

  def write(markup: Markup, writer: HTMLWriter) = if (shouldRender) {
    Page() match {
      case null => writer.write(" %s=\"%s\"".format(name(), attributeValue))
      case page => page.intercept.renderAttribute.fire(this) match {
        case Some(pa) => writer.write(" %s=\"%s\"".format(pa.name(), pa.attributeValue))
        case None => // Told not to render by intercept
      }
    }
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
    val firstLoad = useLazy
    useLazy = false
    super.apply(v)
    if (firstLoad) {
      lazyLoaded()
    }
  }

  /**
   * Called when a value has been assigned to this LazyProperty and is no longer lazy.
   */
  def lazyLoaded(): Unit = {}
}

object PropertyAttribute {
  def apply[T](name: String, default: T, inclusion: InclusionMode = InclusionMode.NotEmpty, backing: Backing[T] = new VariableBacking[T])
              (implicit persister: ValuePersistence[T], parent: PropertyParent, manifest: Manifest[T]) = {
    new PropertyAttribute[T](name, default, inclusion, backing)(persister, parent, manifest)
  }
}