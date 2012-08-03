package org.hyperscala.editor

import org.powerscala.property._
import org.hyperscala.html._
import org.powerscala.event.ActionEvent
import org.hyperscala.css.attributes.Clear

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
//class ListEditor[T](val property: StandardProperty[List[T]],
//                    valueEditor: ValueEditor[T],
//                    filterOut: T => Boolean = (t: T) => false,
//                    visualizer: T => String = (t: T) => t.toString)
//                   (implicit manifest: Manifest[T]) extends Div with ValueEditor[List[T]] {
trait ListEditor[T] extends Div with ValueEditor[List[T]] {
  def property: StandardProperty[List[T]]
  def valueEditor: ValueEditor[T]
  def filterOut(value: T) = false
  def visualizer(value: T) = value.toString
  def manifest: Manifest[T]

  val items = new Div {
  }
  contents += items
  valueEditor.style.clear := Clear.Both
  contents += valueEditor
  contents += new Button(id = "%sAdd".format(property.name()), content = "Add", buttonType = "submit")

  listeners.synchronous.filter.descendant() {
    case evt: ActionEvent if (evt.action == "submit") => {
      valueEditor.property() match {
        case v if (!filterOut(v)) => property := (v :: property().reverse).reverse
        case _ => // Don't add
      }
      clear()
    }
  }

  def updateItems(): Unit = {
    items.contents.clear()
    property().foreach {
      case v => items.contents += new Div {
        style.clear := Clear.Both
        val s = visualizer(v)
        contents += s

        contents += new Button(id = "%sItem.%s".format(property.name(), s), buttonType = "submit") {
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

  def clear() = {
    updateItems()
    valueEditor.clear()
  }
}