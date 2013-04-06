package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.ui.widgets.{CSSLeaf, CSSBranch, CSSTree}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TreeExample extends Example {
//  val tree = new tag.Div(id = "tree") {
//    contents += new tag.Ul {
//      contents += new tag.Li(content = l("One"), role = "leaf")
//      contents += new tag.Li(content = l("Two"), role = "leaf")
//      contents += new tag.Li(content = l("Three"))
//      contents += new tag.Li(content = l("Four"))
//      contents += new tag.Li {
//        contents += l("Five")
//        contents += new tag.Ul {
//          contents += new tag.Li(content = l("Six"))
//          contents += new tag.Li(content = "Seven")
//          contents += new tag.Li(content = l("Eight"))
//          contents += new tag.Li(content = l("Nine"))
//          contents += new tag.Li(content = l("Ten"))
//        }
//      }
//    }
//  }
//  jsTree(tree, jsTree.Type(name = "default"), jsTree.Type(name = "leaf", allowSelect = false, hoverNode = false))
//  contents += tree
//
//  def l(name: String) = new tag.A(href = "#", content = name)

  contents += new CSSTree {
    this += new CSSLeaf("One")
    this += new CSSLeaf("Two")
    this += new CSSLeaf("Three")
    this += new CSSLeaf("Four")
    this += new CSSBranch("Five") {
      this += new CSSLeaf("Six")
      this += new CSSLeaf(new tag.A(href = "#", content = "Seven"))
      this += new CSSBranch("Eight") {
        this += new CSSLeaf(new tag.Button(content = "Apple"))
        this += new CSSLeaf("Orange")
        this += new CSSLeaf("Banana")
      }
      this += new CSSLeaf("Nine")
      this += new CSSLeaf("Ten")
    }
  }
}
