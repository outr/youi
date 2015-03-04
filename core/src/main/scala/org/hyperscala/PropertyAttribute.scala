package org.hyperscala

import io.HTMLWriter
import org.powerscala.property.Property
import persistence.ValuePersistence
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class PropertyAttribute[T](val name: String,
                           default: T,
                           var inclusion: InclusionMode = InclusionMode.ModifiedAndNotEmpty,
                           val dynamic: Boolean = false)
                          (implicit val persister: ValuePersistence[T], parent: Listenable, manifest: Manifest[T])
                           extends Property[T](default = Some(default))(parent, manifest) with XMLAttribute {
  parent match {
    case tag: Tag => {
      tag.addAttribute(this)
      tag.connected[Page] {
        case page => page.intercept.initAttribute.fire(this)    // Fire the initialization to be intercepted
      }
    }
    case _ => // Parent is not a tag
  }

  def attributeValue = persister.toString(value, name, manifest.runtimeClass)
  def attributeValue_=(value: String) = this := persister.fromString(value, name, manifest.runtimeClass)

  def modified = value != default

  def shouldRender = include

  def write(markup: Markup, writer: HTMLWriter) = if (shouldRender) {
    val paOption = markup.root[Page] match {
      case Some(page) => page.intercept.renderAttribute.fire(this)
      case None => Some(this)
    }
    paOption match {
      case Some(pa) if pa() != null => {
        val a = pa.asInstanceOf[PropertyAttribute[T]]
        val value = a.persister.toString(a(), a.name, a.manifest.runtimeClass)
        writer.write(s""" ${pa.name}="${xml.Utility.escape(value)}"""")
      }
    }
  }

  def read(markup: Markup, newValue: String) = {
    value = persister.fromString(newValue, name, manifest.runtimeClass)
  }

  private def include = inclusion match {
    case InclusionMode.Always => true
    case InclusionMode.Exclude => false
    case InclusionMode.NotEmpty => attributeValue != null && attributeValue.length > 0
    case InclusionMode.Modified => modified
    case InclusionMode.ModifiedAndNotEmpty => modified && attributeValue != null && attributeValue.nonEmpty
  }

  override def toString() = s"PropertyAttribute(name: $name, attributeValue: $attributeValue)"
}

object PropertyAttribute {
  def apply[T](name: String, default: T, inclusion: InclusionMode = InclusionMode.ModifiedAndNotEmpty, dynamic: Boolean = false)
              (implicit persister: ValuePersistence[T], parent: Markup, manifest: Manifest[T]) = {
    new PropertyAttribute[T](name, default, inclusion, dynamic)(persister, parent, manifest)
  }
}