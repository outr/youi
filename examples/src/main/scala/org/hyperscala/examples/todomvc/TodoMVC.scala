package org.hyperscala.examples.todomvc

import org.hyperscala.event.Key
import org.hyperscala.html._
import attributes.InputType
import org.hyperscala.html.extension.ClassBooleanProperty
import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime._
import org.hyperscala.realtime.dsl._
import org.hyperscala.web.{Website, Webpage}
import com.outr.net.http.session.Session
import org.powerscala.property.Property

class TodoMVC[S <: Session](website: Website[S]) extends Webpage[S](website) {
  require(Realtime)
  Realtime.connectForm(this)

  val input = new tag.Input(id = "new-todo", placeHolder = "What needs to be done?", autoFocus = true)
  val list = new tag.Ul(id = "todo-list")

  input.keyUpEvent := RealtimeEvent(preventDefault = false, fireChange = true)
  $(input).keyUp(onKey(this, Key.Return) {
    if (input.value().trim.nonEmpty) {
      list.contents.insert(0, new TodoItem(input.value()))
      input.value := ""
    }
  }).send(this)

  list.contents += new TodoItem("Create a TodoMVC template", completed = true)
  list.contents += new TodoItem("Rule the web")

  title := "Webframework - TodoMVC"
  head.contents += new tag.Link(href = "/css/base.css")

  body.contents += new tag.Section(id = "todoapp") {
    contents += new tag.Header(id = "header") {
      contents += new tag.H1(content = "todos")
      contents += input
    }
    contents += new tag.Section(id = "main") {
      contents += new tag.Input(id = "toggle-all", inputType = InputType.CheckBox)
      contents += new tag.Label(forElement = "toggle-all", content = "Mark all as complete")
      contents += list
    }
    contents += new tag.Footer(id = "footer") {
      contents += new tag.Span(id = "todo-count") {
        contents += new tag.Strong(content = "1")
        contents += " item left"
      }
      contents += new tag.Ul(id = "filters") {
        contents += new tag.Li {
          contents += new tag.A(clazz = List("selected"), href = "#", content = "All")
        }
        contents += new tag.Li {
          contents += new tag.A(href = "#/active", content = "Active")
        }
        contents += new tag.Li {
          contents += new tag.A(href = "#/completed", content = "Completed")
        }
      }
      contents += new tag.Button(id = "clear-completed", content = "Clear completed (1)")
    }
  }
  body.contents += new tag.Footer(id = "info") {
    contents += new tag.P(content = "Double-click to edit a todo")
    contents += new tag.P {
      contents += "Template by "
      contents += new tag.A(href = "http://github.com/sindresorhus", content = "Sindre Sorhus")
    }
    contents += new tag.P {
      contents += "Created by "
      contents += new tag.A(href = "http://www.matthicks.com", content = "Matt Hicks")
    }
    contents += new tag.P {
      contents += "Part of "
      contents += new tag.A(href = "http://www.todomvc.com", content = "TodoMVC")
    }
  }
}

class TodoItem extends tag.Li {
  val completed = new ClassBooleanProperty(this, enabled = Some("completed"))
  val value = Property[String]()

  val checkBox = new tag.Input(clazz = List("toggle"), inputType = InputType.CheckBox, checked = completed())
  val label = new tag.Label
  val input = new tag.Input(clazz = List("edit"))
  val destroy = new tag.Button(clazz = List("destroy"))

  def this(value: String, completed: Boolean = false) = {
    this()
    this.value := value
    this.completed := completed
  }

  checkBox.checked.bind(completed)
  completed.bind(checkBox.checked)
  value.change.on {
    case evt => {
      label.contents.replaceWith(new tag.Span(content = evt.newValue))
      input.value := evt.newValue
    }
  }
  destroy.clickEvent.onRealtime {
    case evt => removeFromParent()
  }

  contents += new tag.Div(clazz = List("view")) {
    contents += checkBox
    contents += label
    contents += destroy
  }
  contents += input
}