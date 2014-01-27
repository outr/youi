package org.hyperscala.html

import org.hyperscala.{Container, PropertyAttribute}
import org.hyperscala.html.tag.Text
import org.powerscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
object HTMLCloner {
  val NullifyIdHandler = (id: String) => null
  val UniqueIdHandler = (id: String) => Unique()

  def clone(t: HTMLTag, idHandler: String => String = NullifyIdHandler): HTMLTag = {
    val cloned = t match {
      case text: Text => new Text()
      case _ => HTMLTag.create(t.xmlLabel)
    }

    // Apply each property
    t.attributes.foreach {
      case (name, attribute) => {
        if (name == "id") {
          cloned.id := idHandler(t.id())
        } else {
          cloned.getAttribute(name) match {
            case Some(ca) => ca.asInstanceOf[PropertyAttribute[Any]] := attribute()
            case None => throw new RuntimeException(s"Unable to find property $name in tag ${t.xmlLabel}.")
          }
        }
      }
    }

    // Apply style
    if (t.isStyleDefined) {
      cloned.style(t.style)
    }

    // Apply children
    if (t.isInstanceOf[Container[_]]) {
      val originalContainer = t.asInstanceOf[Container[HTMLTag]]
      val clonedContainer = cloned.asInstanceOf[Container[HTMLTag]]
      originalContainer.contents.foreach {
        case child => clonedContainer.contents += clone(child, idHandler)
      }
    }

    cloned
  }
}
