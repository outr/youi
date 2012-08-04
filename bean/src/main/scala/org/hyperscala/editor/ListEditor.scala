package org.hyperscala.editor

import org.powerscala.property._
import org.hyperscala.html._
import attributes.ButtonType
import org.powerscala.event.ActionEvent
import org.hyperscala.css.attributes.Clear
import org.powerscala.reflect._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ListEditor[T] extends Div with ValueEditor[List[T]] {
  def property: StandardProperty[List[T]]
  def valueEditor: ValueEditor[T]
  def filterOut(value: T) = property().contains(value)
  def visualizer(value: T) = value.toString
  def manifest: Manifest[T]
  def default: T = manifest.erasure.defaultForType.asInstanceOf[T]

  val items = new Div {
  }
  contents += items
  valueEditor.style.clear := Clear.Both
  contents += valueEditor
  contents += new Button(id = "%sAdd".format(property.name()), content = "Add", buttonType = ButtonType.Submit) {
    listeners.synchronous {
      case evt: ActionEvent if (evt.action == "submit") => addItem()
    }
  }

  valueEditor.listeners.synchronous.filter.descendant(includeCurrent = true) {
    case evt: ActionEvent if (evt.action == "submit") => addItem()
  }

  def addItem() = {
    valueEditor.property() match {
      case v if (!filterOut(v)) => property := (v :: property().reverse).reverse
      case _ => // Don't add
    }
    valueEditor.property := default
    updateItems()
  }

  def updateItems(): Unit = {
    items.contents.clear()
    property().foreach {
      case v => items.contents += new Div {
        style.clear := Clear.Both
        val s = visualizer(v)
        contents += s

        contents += new Button(id = "%sItem.%s".format(property.name(), s), buttonType = ButtonType.Submit) {
          contents += new Img(src = "/delete.png")
          listeners.synchronous {
            case evt: ActionEvent if (evt.action == "submit") => {
              property := property().filterNot(t => t == v)
              updateItems()
            }
          }
        }
      }
    }
  }
}