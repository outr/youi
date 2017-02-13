package io.youi.template

import io.youi.app.ClientApplication
import io.youi.dom
import org.scalajs.dom._
import io.youi.dom._

import scala.scalajs.js.annotation.JSExportTopLevel

object ClientTemplateApplication extends TemplateApplication with ClientApplication {
  @JSExportTopLevel("application")
  def main(): Unit = {
    val pages = dom.byId[html.Input]("template_pages").value.split(';').toSet
    fixAnchors(pages)

    activate()

    println("Template initialized!")
  }

  private def fixAnchors(pages: Set[String]): Unit = {
    dom.byTag[html.Anchor]("a").foreach { anchor =>
      val host = window.location.href.substring(0, window.location.href.lastIndexOf('/') + 1)
      val hrefOption = anchor.href match {
        case h if h.startsWith(host) && !h.endsWith("#") => Some(h.substring(host.length))
        case h => None
      }
      hrefOption match {
        case Some(href) => {
          if (pages.contains(href)) {
            anchor.href = s"/${href.substring(0, href.length - 5)}"
          }
        }
        case None => // Ignore
      }
    }
  }

  private val ConditionalRegex = """(.+) \? (.+) \: (.+)""".r
  private val SetTitleRegex = """title = "(.*)"""".r
  private val AddClassRegex = """(.+)[.]addClass\("(.+)"\)""".r
  private val RemoveClassRegex = """(.+)[.]removeClass\("(.+)"\)""".r
  private val ReplaceContentWithRegex = """(.+)[.]replaceContentWith\((.+)\)""".r
  private val AlertRegex = """alert\("(.+)"\)""".r

  private val HasClassConditionRegex = """(.+).hasClass\("(.+)"\)""".r

  private def activate(): Unit = {
    val tags = dom.byTag[html.Element]("activate")
    val activate = tags.map(_.innerHTML.trim).mkString("\n").split('\n').map(_.trim).toList
    activate.foreach(activateInstruction)
    tags.foreach(_.remove())
  }

  private def activateInstruction(instruction: String): Unit = instruction match {
    case ConditionalRegex(condition, trueInstruction, falseInstruction) => if (conditionInstruction(condition)) {
      activateInstruction(trueInstruction)
    } else {
      activateInstruction(falseInstruction)
    }
    case SetTitleRegex(title) => document.title = title
    case AddClassRegex(selector, className) => {
      val elements = dom.bySelector[html.Element](selector)
      elements.foreach(_.classList.add(className))
    }
    case RemoveClassRegex(selector, className) => {
      val elements = dom.bySelector[html.Element](selector)
      elements.foreach(_.classList.remove(className))
    }
    case ReplaceContentWithRegex(selector1, selector2) => {
      val elements = dom.bySelector[html.Element](selector1)
      val replacement = dom.oneBySelector[html.Element](selector2)
      elements.foreach(_.innerHTML = replacement.innerHTML)
    }
    case AlertRegex(message) => window.alert(message)
    case _ => println(s"Unknown Instruction: $instruction")
  }

  private def conditionInstruction(instruction: String): Boolean = instruction match {
    case HasClassConditionRegex(selector, className) => {
      dom.bySelector[html.Element](selector).forall(_.classList.contains(className))
    }
    case _ => {
      println(s"Unknown Condition: $instruction. Returning false!")
      false
    }
  }
}