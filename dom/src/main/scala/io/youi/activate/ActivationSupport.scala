package io.youi.activate

import io.youi.dom._
import org.scalajs.dom._

/**
  * Instantiate this to find, parse, and remove all activate tags in the body of the HTML.
  */
trait ActivationSupport {
  protected def activationRoot: html.Element = document.body
  private lazy val instructions: List[ActivateInstruction] = ActivationSupport.parse(testing, activationRoot)

  protected def testing: Boolean = false

  object activation {
    def activate(): Unit = {
      val local = instructions                          // Necessary before global to make sure all instructions are parsed
      ActivationSupport.global.foreach(_.activate())
      local.foreach(_.activate())
    }

    def deactivate(): Unit = {
      ActivationSupport.global.foreach(_.deactivate())
      instructions.foreach(_.deactivate())
    }
  }
}

object ActivationSupport {
  var debug: Boolean = false

  private var global: List[ActivateInstruction] = Nil

  private val ConditionalRegex = """(.+) \? (.+) \: (.+)""".r
  private val ConditionalTrueRegex = """(.+) \? (.+)""".r
  private val ConditionalFalseRegex = """(.+) \: (.+)""".r
  private val SetTitleRegex = """title = "(.*)"""".r
  private val AddClassRegex = """(.+)[.]addClass\("(.+)"\)""".r
  private val RemoveClassRegex = """(.+)[.]removeClass\("(.+)"\)""".r
  private val ReplaceContentWithRegex = """(.+)[.]replaceContentWith\((.+)\)""".r
  private val AlertRegex = """alert\("(.+)"\)""".r
  private val CallRegex = """call\((.+)\)""".r
  private val TestRegex = """test\((.+)\)""".r
  private val LinkRegex = """link\("(.+)"\)""".r
  private val OnClickRegex = """(.+).onClick\((.+)\)""".r
  private val CommentRegex = """(\s*)//(.*)""".r

  private val HasClassConditionRegex = """(.+).hasClass\("(.+)"\)""".r

  private def parse(testing: Boolean, root: html.Element): List[ActivateInstruction] = {
    val tags = root.byTag[html.Element]("activate")
    var local: List[ActivateInstruction] = Nil
    tags.foreach { tag =>
      val lines = tag.innerHTML.trim.split('\n').map(_.trim).toList
      val instructions = lines.flatMap(i => parseInstruction(testing, i))

      if (tag.getAttribute("global") == "true") {
        global = global ::: instructions
      } else {
        local = local ::: instructions
      }
    }
    tags.foreach(_.remove())
    local
  }

  private def parseInstruction(testing: Boolean, line: String): Option[ActivateInstruction] = line.trim match {
    case "" => None
    case s if s.startsWith("//") => None
    case TestRegex(instruction) => if (testing) {
      parseInstruction(testing, instruction)
    } else {
      None
    }
    case ConditionalRegex(condition, trueInstruction, falseInstruction) => {
      val ti = parseInstruction(testing, trueInstruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$trueInstruction]"))
      val fi = parseInstruction(testing, falseInstruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$falseInstruction]"))
      Some(parseCondition(condition, Some(ti), Some(fi)))
    }
    case ConditionalTrueRegex(condition, trueInstruction) => {
      val ti = parseInstruction(testing, trueInstruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$trueInstruction]"))
      Some(parseCondition(condition, Some(ti), None))
    }
    case ConditionalFalseRegex(condition, falseInstruction) => {
      val fi = parseInstruction(testing, falseInstruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$falseInstruction]"))
      Some(parseCondition(condition, None, Some(fi)))
    }
    case SetTitleRegex(title) => Some(new SetTitleInstruction(title))
    case AddClassRegex(selector, className) => Some(new AddClassInstruction(selector, className))
    case RemoveClassRegex(selector, className) => Some(new RemoveClassInstruction(selector, className))
    case AlertRegex(message) => Some(new AlertInstruction(message))
    case CallRegex(code) => Some(new Call(code))
    case LinkRegex(path) => Some(new Link(path))
    case OnClickRegex(selector, instruction) => {
      val i = parseInstruction(testing, instruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$instruction]"))
      Some(new OnClick(selector, i))
    }
    case CommentRegex(_) => None
    case _ => throw new RuntimeException(s"Unknown instruction: [$line]")
  }

  private def parseCondition(instruction: String,
                             trueInstruction: Option[ActivateInstruction],
                             falseInstruction: Option[ActivateInstruction]): ConditionalInstruction = instruction match {
    case HasClassConditionRegex(selector, className) => new HasClassInstruction(selector, className, trueInstruction, falseInstruction)
    case _ => throw new RuntimeException(s"Unknown condition: [$instruction]")
  }

  def apply(): ActivationSupport = new ActivationSupport {}
}