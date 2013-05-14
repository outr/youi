package org.hyperscala.ui.widgets

import org.hyperscala.html._
import org.powerscala.property._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.css.attributes._
import org.hyperscala.event.JavaScriptEvent
import org.powerscala.reflect._

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait ListEditor[T] extends tag.Div {
  def manifest: Manifest[T]
  def createEditor(): BodyChild

  val defaultValue = manifest.runtimeClass.defaultForType[T]

  val list = new Property[List[T]](default = Some(Nil))(this, Manifest.classType[List[T]](classOf[List[T]])) with ListProperty[T] {
    override def +=(t: T) = if (!value.contains(t)) {   // No duplicates allowed
      super.+=(t)
    }
  }
  val current = new Property[T]()(this, manifest)

  val listDiv = new tag.Div
  val editorDiv = new tag.Div {
    style.clear = Clear.Both
    style.float = Float.Left
  }
  val editorButtons = new tag.Div {
    style.float = Float.Left
  }
  val editor = createEditor()

  list.changing.on {
    case evt => if (evt == null) {
      None      // Nulls not allowed
    } else {
      Some(evt)
    }
  }
  list.change.on {
    case evt => updateList()
  }

  override protected def initialize() {
    super.initialize()

    setup()
  }

  def setup() = {
    contents += listDiv
    contents += editorDiv
    contents += editorButtons

    editorDiv.contents += editor

    editorButtons.contents += new tag.Button(content = "Add") {
      clickEvent := JavaScriptEvent()

      clickEvent.on {
        case evt => addCurrent()
      }
    }
  }

  /**
   * Adds the current item to the list. If the current item is editing an existing item it will replace it in the list.
   */
  def addCurrent() = if (current() != null) {
    list += current()
    current := defaultValue
  }

  // TODO: add editing support for editable types

  /**
   * Reloads the visual list of items from the "list" property. Automatically invoked when "list" changes.
   */
  def updateList() = {
    val l = list()

    // Remove items from the list
    listDiv.contents.foreach {
      case editor: ListEditorItem[_] => if (!l.contains(editor.value)) {
        editor.removeFromParent()
      }
    }
    // Add items not in the list
    l.foreach {
      case value => if (editorItemByValue(value).isEmpty) {
        listDiv.contents += createListItem(value)
      }
    }
    // Verify the correct ordering
    l.zipWithIndex.foreach {
      case (value, index) => {
        val editorItem = editorItemByValue(value).get
        if (listDiv.contents.indexOf(editorItem) != index) {
          println("Correcting the index of %s".format(value))
          editorItem.removeFromParent()
          listDiv.contents.insert(index, editorItem)
        }
      }
    }
  }

  private def editorItemByValue(value: T) = listDiv.contents.find(c => c.asInstanceOf[ListEditorItem[T]].value == value)

  def createListItem(value: T): ListEditorItem[T] = new DefaultListEditorItem[T](value, this)
}

trait ListEditorItem[T] extends BodyChild {
  def value: T
}

class DefaultListEditorItem[T](val value: T, editor: ListEditor[T]) extends tag.Div with ListEditorItem[T] {
  contents += value.toString
  contents += new tag.Button(content = "Delete") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => delete()
    }
  }

  def delete() = editor.list -= value
}