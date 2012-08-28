package org.hyperscala.bean

import org.hyperscala.html._
import attributes.ButtonType
import org.hyperscala.editor.ListEditor
import org.hyperscala.web.ActionForm
import org.hyperscala.css.attributes.Clear
import org.powerscala.property._
import tag.{Footer, Form, Button}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class BeanForm[T](_name: String, val default: T)(implicit val manifest: Manifest[T])
        extends Form(method = "post", id = _name, name = _name) with BeanContainer[T] with ActionForm {
  def parentContainer = null
  def containerName: String = null

  val button = createButton()

  assignTabs(fields)
  button.tabIndex := nextTab

  protected def createButton() = new Button(buttonType = ButtonType.Submit, content = "Submit")

  fields.foreach {
    case field => contents += field
  }
  focus(fields.head.field)

  val footer = new Footer {
    style.clear := Clear.Both

    contents += button
  }
  contents += footer

  override def autoFocus(tag: HTMLTag): Unit = tag match {
    case listEditor: ListEditor[_] => autoFocus(listEditor.valueEditor)
    case _ => super.autoFocus(tag)
  }
}