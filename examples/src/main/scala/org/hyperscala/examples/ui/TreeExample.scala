package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.ui.jsTree

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TreeExample extends Example {
  val tree = new tag.Div(id = "tree") {
    contents += new tag.Ul {
      contents += new tag.Li(content = l("One"), rel = )
      contents += new tag.Li(content = l("Two"))
      contents += new tag.Li(content = l("Three"))
      contents += new tag.Li(content = l("Four"))
      contents += new tag.Li {
        contents += l("Five")
        contents += new tag.Ul {
          contents += new tag.Li(content = l("Six"))
          contents += new tag.Li(content = l("Seven"))
          contents += new tag.Li(content = l("Eight"))
          contents += new tag.Li(content = l("Nine"))
          contents += new tag.Li(content = l("Ten"))
        }
      }
    }
  }
  jsTree(tree)
  contents += tree

  def l(name: String) = new tag.A(href = "#", content = name)
}
