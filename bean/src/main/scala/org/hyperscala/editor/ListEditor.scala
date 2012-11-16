package org.hyperscala.editor

import org.powerscala.property._
import org.hyperscala.html._
import attributes.ButtonType
import constraints.BodyChild
import org.hyperscala.css.attributes.Clear
import org.powerscala.reflect._
import org.hyperscala.javascript.JavaScriptString
import tag.{Input, Img, Button, Div}
import validation.ValidationFailed
import org.hyperscala.web.HTMLPage
import org.hyperscala.web.live.{ClickEvent, LiveEvent, LivePage}
import org.hyperscala.event.FormSubmit

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ListEditor[T] extends Div with ValueEditor[List[T]] {
  def property: StandardProperty[List[T]]
  def valueEditor: ValueEditor[T]
  def filterOut(value: T) = property().contains(value)
  def visualizer(value: T) = String.valueOf(value)
  def default: T = manifest.erasure.defaultForType.asInstanceOf[T]

  val items = new Div
  contents += items
  valueEditor.style.clear := Clear.Both
  contents += valueEditor
  val button = new Button(id = "%sAdd".format(property.name()), content = "Add", buttonType = ButtonType.Button) {
    if (!HTMLPage().isInstanceOf[LivePage]) {
      event.click := JavaScriptString("$(this).closest('form').submit();")
    }
    listeners.synchronous {
      case evt: FormSubmit => addItem()
    }
  }
  contents += button

  valueEditor.listeners.synchronous.filter.descendant(includeCurrent = true) {
    case evt: FormSubmit => addItem()
  }

  updateItems()

//  if (HTMLPage().isInstanceOf[LivePage]) {
    hierarchy.process[Input] {
      case input => input.event.keyPress := JavaScriptString("if (event.keyCode == 13) { $(this).trigger('change'); $('#%s').click(); return false; }".format(button.id()))
    }
//  }

  def addItem() = if (valueEditor.property() != null) {
    valueEditor.validate() match {
      case Some(error) => fire(ValidationFailed(error))
      case None => {
        valueEditor.property() match {
          case v if (!filterOut(v)) => updateList(v)
          case _ => // Don't add
        }
        valueEditor.property := default
        updateItems()
      }
    }
  }

  def updateList(item: T) = {
    property := (item :: property().reverse).reverse
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

      val cleaned = s.capitalize.collect {
        case c if (c.isLetterOrDigit) => c
      }
      contents += new Button(id = "%sItem%s".format(property.name(), cleaned), buttonType = ButtonType.Button) {
        if (!HTMLPage().isInstanceOf[LivePage]) {
          event.click := JavaScriptString("$(this).focus(); $(this).closest('form').submit();")
        }
        style.margin.left := 10.px

        def remove() = {
          property := property().filterNot(t => t == item)
          updateItems()
        }

        contents += new Img(src = "/delete.png") {
          event.click := LiveEvent()

          listeners.synchronous {
            case evt: ClickEvent => remove()    // Workaround for Chrome
          }
        }
        listeners.synchronous {
          case evt: FormSubmit => {
            remove()
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