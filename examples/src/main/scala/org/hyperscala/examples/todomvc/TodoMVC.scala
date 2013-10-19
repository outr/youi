package org.hyperscala.examples.todomvc

import org.hyperscala.html._
import attributes.InputType
import tag._
import org.hyperscala.web.Webpage

object TodoMVC extends Webpage {
  title := "Webframework - TodoMVC"
  head.contents += new Link(rel = "stylesheet", href = "css/base.css")
  head.contents += new Link(rel = "stylesheet", href = "css/app.css")

  body.contents += new Section(id = "todoapp") {
    contents += new Header(id = "header") {
      contents += new H1(content = "todos")
      contents += new Input(id = "new-todo", placeHolder = "What needs to be done?", autoFocus = true)
    }
    contents += new Section(id = "main") {
      contents += new Input(id = "toggle-all", inputType = InputType.CheckBox)
      contents += new Label(forElement = "toggle-all", content = "Mark all as complete")
      contents += new Ul(id = "todo-list") {
        contents += new Li(clazz = List("completed")) {
          contents += new Div(clazz = List("view")) {
            contents += new Input(clazz = List("toggle"), inputType = InputType.CheckBox, checked = true)
            contents += new Label(content = "Create a TodoMVC template")
            contents += new Button(clazz = List("destroy"))
          }
          contents += new Input(clazz = List("edit"), value = "Create a TodoMVC template")
        }
        contents += new Li {
          contents += new Div(clazz = List("view")) {
            contents += new Input(clazz = List("toggle"), inputType = InputType.CheckBox)
            contents += new Label(content = "Rule the web")
            contents += new Button(clazz = List("destroy"))
          }
          contents += new Input(clazz = List("edit"), value = "Rule the web")
        }
      }
    }
    contents += new Footer(id = "footer") {
      contents += new Span(id = "todo-count") {
        contents += new Strong(content = "1")
        contents += " item left"
      }
      contents += new Ul(id = "filters") {
        contents += new Li {
          contents += new A(clazz = List("selected"), href = "#", content = "All")
        }
        contents += new Li {
          contents += new A(href = "#/active", content = "Active")
        }
        contents += new Li {
          contents += new A(href = "#/completed", content = "Completed")
        }
      }
      contents += new Button(id = "clear-completed", content = "Clear completed (1)")
    }
  }
  body.contents += new Footer(id = "info") {
    contents += new P(content = "Double-click to edit a todo")
    contents += new P {
      contents += "Template by "
      contents += new A(href = "http://github.com/sindresorhus", content = "Sindre Sorhus")
    }
    contents += new P {
      contents += "Created by "
      contents += new A(href = "http://www.matthicks.com", content = "Matt Hicks")
    }
    contents += new P {
      contents += "Part of "
      contents += new A(href = "http://www.todomvc.com", content = "TodoMVC")
    }
  }
  body.contents += new Script(src = "js/base.js")
  body.contents += new Script(src = "js/app.js")
}
