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
    markup.root[Page] match {
      case Some(page) => {
        page.intercept.renderAttribute.fire(PropertyAttribute.this) match {
          case Some(pa) if pa() != null => writer.write(" %s=\"%s\"".format(pa.name, persister.toString(pa().asInstanceOf[T], pa.name, manifest.runtimeClass)))
          case _ => // Told not to render by intercept
        }
      }
      case None => writer.write(" %s=\"%s\"".format(name, persister.toString(apply(), name, manifest.runtimeClass)))
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
}

object PropertyAttribute {
  def apply[T](name: String, default: T, inclusion: InclusionMode = InclusionMode.ModifiedAndNotEmpty, dynamic: Boolean = false)
              (implicit persister: ValuePersistence[T], parent: Markup, manifest: Manifest[T]) = {
    new PropertyAttribute[T](name, default, inclusion, dynamic)(persister, parent, manifest)
  }
}