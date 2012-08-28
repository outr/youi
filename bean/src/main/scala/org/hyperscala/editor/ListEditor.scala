package org.hyperscala.editor

import org.powerscala.property._
import org.hyperscala.html._
import attributes.ButtonType
import constraints.BodyChild
import org.hyperscala.css.attributes.Clear
import org.powerscala.reflect._
import org.hyperscala.web.event.FormSubmit
import org.hyperscala.javascript.JavaScriptString
import tag.{Img, Button, Div}
import validation.ValidationFailed

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

  val items = new Div
  contents += items
  valueEditor.style.clear := Clear.Both
  contents += valueEditor
  val button = new Button(id = "%sAdd".format(property.name()), content = "Add", buttonType = ButtonType.Button) {
    event.click := JavaScriptString("$(this).closest('form').submit();")
    listeners.synchronous {
      case evt: FormSubmit => addItem()
    }
  }
  contents += button

  valueEditor.listeners.synchronous.filter.descendant(includeCurrent = true) {
    case evt: FormSubmit => addItem()
  }

  updateItems()

  def addItem() = {
    valueEditor.validate() match {
      case Some(error) => fire(ValidationFailed(error))
      case None => {
        valueEditor.property() match {
          case v if (!filterOut(v)) => property := (v :: property().reverse).reverse
          case _ => // Don't add
        }
        valueEditor.property := default
        updateItems()
      }
    }
  }

  def updateItems(): Unit = {
    items.contents.clear()
    property().foreach {
      case v => items.contents += createItem(v)
    }
  }

  def createItem(item: T): BodyChild = {
    new Div {
      style.clear := Clear.Both
      val s = visualizer(item)
      contents += s

      contents += new Button(id = "%sItem%s".format(property.name(), s.capitalize), buttonType = ButtonType.Button) {
        event.click := JavaScriptString("$(this).focus(); $(this).closest('form').submit();")
        style.margin.left := 10.px
        contents += new Img(src = "/delete.png")
        listeners.synchronous {
          case evt: FormSubmit => {
            property := property().filterNot(t => t == item)
            updateItems()
            // TODO: support focusing back to the valueEditor - this refers to the button
//            hierarchy.ancestor[ActionForm]((f: ActionForm) => true) match {
//              case Some(f) => f.focus(valueEditor)
//              case None => println("Not an action form!")
//            }
          }
        }
      }
    }
  }
}