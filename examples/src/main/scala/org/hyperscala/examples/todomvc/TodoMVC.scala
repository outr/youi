package org.hyperscala.examples.todomvc

import org.hyperscala.event.Key
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.html.attributes.InputType
import org.hyperscala.html.extension.ClassBooleanProperty
import org.hyperscala.javascript.dsl._
import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime._
import org.hyperscala.web.Website
import org.powerscala.property.Property

class TodoMVC(website: Website) extends RealtimeWebpage with Example {
  val input = new tag.Input(id = "new-todo", placeHolder = "What needs to be done?", autoFocus = true)
  val list = new tag.Ul(id = "todo-list")
  val count = new tag.Span(id = "todo-count", content = "<strong>1</strong> item left")
  val clearCompletedButton = new tag.Button(id = "clear-completed", content = "Clear completed (1)").onClick {
    clearCompleted()
  }

  input.keyUpEvent := RealtimeEvent(preventDefault = false, fireChange = true)
  $(input).keyUp(onKey(Key.Return)(input.callback {
    if (input.value().trim.nonEmpty) {
      list.contents.insert(0, new TodoItem(this, input.value(), complete = false))
      input.value := ""
      update()
    }
  })).send(this)

  list.contents += new TodoItem(this, "Create a TodoMVC template", complete = true)
  list.contents += new TodoItem(this, "Rule the web", complete = false)

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
      contents += count
      contents += new tag.Ul(id = "filters") {
        def selected(listClass: List[String], id: String) = {
          byType[tag.A].foreach(a => if (a.identity == id) a.clazz += "selected" else a.clazz -= "selected")
          list.clazz := listClass
        }
        contents += new tag.Li {
          contents += new tag.A(id = "selectedLink", clazz = List("selected"), href = "#", content = "All").onClick {
            selected(Nil, "selectedLink")
          }
        }
        contents += new tag.Li {
          contents += new tag.A(id = "activeLink", href = "#/active", content = "Active").onClick {
            selected(List("active"), "activeLink")
          }
        }
        contents += new tag.Li {
          contents += new tag.A(id = "completedLink", href = "#/completed", content = "Completed").onClick {
            selected(List("completed"), "completedLink")
          }
        }
      }
      contents += clearCompletedButton
    }
  }
  body.contents += new tag.Footer(id = "info") {
    contents += new tag.P(content = "Double-click to edit a todo")
    contents += new tag.P(content = """Part of <a href="http://www.todomvc.com">TodoMVC</a>""")
  }

  update()

  def active = list.byType[TodoItem].filter(ti => !ti.completed())
  def completed = list.byType[TodoItem].filter(ti => ti.completed())

  def clearCompleted(): Unit = {
    completed.foreach(_.removeFromParent())
    update()
  }

  def update() = {
    val active = this.active.size
    count.contents.replaceWith(new tag.Strong(content = active.toString), if (active == 1) " item left" else " items left")
    clearCompletedButton.contents.replaceWith(s"Clear completed (${completed.size})")
  }
}

class TodoItem(mvc: TodoMVC, initialValue: String, complete: Boolean) extends tag.Li {
  val completed = new ClassBooleanProperty(this, enabled = Some("completed"))
  completed := complete
  val value = Property[String](default = Some(initialValue))

  val checkBox = new tag.Input(clazz = List("toggle"), inputType = InputType.CheckBox, checked = completed())
  val label = new tag.Label
  val input = new tag.Input(clazz = List("edit"))
  val destroy = new tag.Button(clazz = List("destroy"))

  $(input).keyUp(onKey(Key.Return)(input.callback {
    display()
  })).send(mvc)

  doubleClickEvent.onRealtime {
    case evt => edit()
  }
  checkBox.checked.bind(completed)
  completed.bind(checkBox.checked)
  completed.change.on {
    case evt => mvc.update()
  }
  value.change.on {
    case evt => {
      label.contents.replaceWith(new tag.Span(content = evt.newValue))
      input.value := evt.newValue
    }
  }
  destroy.onClick {
    removeFromParent()
    mvc.update()
  }

  contents += new tag.Div(clazz = List("view")) {
    contents += checkBox
    contents += label
    contents += destroy
  }
  contents += input

  def edit() = {
    clazz += "editing"
  }

  def display() = {
    label.contents.replaceWith(input.value())
    clazz -= "editing"
  }
}