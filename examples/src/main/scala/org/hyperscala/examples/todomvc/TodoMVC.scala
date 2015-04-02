package org.hyperscala.examples.todomvc

import org.hyperscala.event.Key
import org.hyperscala.html._
import attributes.InputType
import org.hyperscala.html.extension.ClassBooleanProperty
import org.hyperscala.jquery.dsl._
import org.hyperscala.javascript.dsl._
import org.hyperscala.realtime._
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.property.Property

class TodoMVC(website: Website) extends Webpage {
  require(Realtime)
  this.connectForm()

  val input = new tag.Input(id = "new-todo", placeHolder = "What needs to be done?", autoFocus = true)
  val list = new tag.Ul(id = "todo-list")
  val count = new tag.Span(id = "todo-count") {
    contents += new tag.Strong(content = "1")
    contents += " item left"
  }
  val clearCompletedButton = new tag.Button(id = "clear-completed", content = "Clear completed (1)") {
    clickEvent.onRealtime {
      case evt => clearCompleted()
    }
  }

  input.keyUpEvent := RealtimeEvent(preventDefault = false, fireChange = true)
  this.eval($(input).keyUp(onKey(Key.Return)(input.callback {
    if (input.value().trim.nonEmpty) {
      list.contents.insert(0, new TodoItem(this, input.value(), completed = false))
      input.value := ""
      update()
    }
  })))

  list.contents += new TodoItem(this, "Create a TodoMVC template", completed = true)
  list.contents += new TodoItem(this, "Rule the web", completed = false)

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
        def selected(listClass: List[String], link: tag.A) = {
          byType[tag.A].foreach(a => if (a == link) a.clazz += "selected" else a.clazz -= "selected")
          list.clazz := listClass
        }
        contents += new tag.Li {
          contents += new tag.A(clazz = List("selected"), href = "#", content = "All") {
            clickEvent.onRealtime {
              case evt => selected(Nil, this)
            }
          }
        }
        contents += new tag.Li {
          contents += new tag.A(href = "#/active", content = "Active") {
            clickEvent.onRealtime {
              case evt => selected(List("active"), this)
            }
          }
        }
        contents += new tag.Li {
          contents += new tag.A(href = "#/completed", content = "Completed") {
            clickEvent.onRealtime {
              case evt => selected(List("completed"), this)
            }
          }
        }
      }
      contents += clearCompletedButton
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

class TodoItem(mvc: TodoMVC) extends tag.Li {
  val completed = new ClassBooleanProperty(this, enabled = Some("completed"))
  val value = Property[String]()

  val checkBox = new tag.Input(clazz = List("toggle"), inputType = InputType.CheckBox, checked = completed())
  val label = new tag.Label
  val input = new tag.Input(clazz = List("edit"))
  val destroy = new tag.Button(clazz = List("destroy"))

  def this(mvc: TodoMVC, value: String, completed: Boolean) = {
    this(mvc)
    this.value := value
    this.completed := completed
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
  destroy.clickEvent.onRealtime {
    case evt => {
      removeFromParent()
      mvc.update()
    }
  }

  contents += new tag.Div(clazz = List("view")) {
    contents += checkBox
    contents += label
    contents += destroy
  }
  contents += input
}