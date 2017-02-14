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
    def activate(): Unit = instructions.foreach(_.activate())

    def deactivate(): Unit = instructions.foreach(_.deactivate())
  }
}

object ActivationSupport {
  private val ConditionalRegex = """(.+) \? (.+) \: (.+)""".r
  private val ConditionalPartialRegex = """(.+) \? (.+)""".r
  private val SetTitleRegex = """title = "(.*)"""".r
  private val AddClassRegex = """(.+)[.]addClass\("(.+)"\)""".r
  private val RemoveClassRegex = """(.+)[.]removeClass\("(.+)"\)""".r
  private val ReplaceContentWithRegex = """(.+)[.]replaceContentWith\((.+)\)""".r
  private val AlertRegex = """alert\("(.+)"\)""".r
  private val TestLinkRegex = """(.+)[.]testLink\("(.+)"\)""".r

  private val HasClassConditionRegex = """(.+).hasClass\("(.+)"\)""".r

  private def parse(testing: Boolean, root: html.Element): List[ActivateInstruction] = {
    val tags = root.byTag[html.Element]("activate")
    val activate = tags.map(_.innerHTML.trim).mkString("\n").split('\n').map(_.trim).toList
    val instructions = activate.flatMap(i => parseInstruction(testing, i))
    tags.foreach(_.remove())
    instructions
  }

  private def parseInstruction(testing: Boolean, line: String): Option[ActivateInstruction] = line.trim match {
    case "" => None
    case s if s.startsWith("//") => None
    case ConditionalRegex(condition, trueInstruction, falseInstruction) => {
      val ti = parseInstruction(testing, trueInstruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$trueInstruction]"))
      val fi = parseInstruction(testing, falseInstruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$falseInstruction]"))
      Some(parseCondition(condition, ti, Some(fi)))
    }
    case ConditionalPartialRegex(condition, trueInstruction) => {
      val ti = parseInstruction(testing, trueInstruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$trueInstruction]"))
      Some(parseCondition(condition, ti, None))
    }
    case SetTitleRegex(title) => Some(new SetTitleInstruction(title))
    case AddClassRegex(selector, className) => Some(new AddClassInstruction(selector, className))
    case RemoveClassRegex(selector, className) => Some(new RemoveClassInstruction(selector, className))
    case AlertRegex(message) => Some(new AlertInstruction(message))
    case TestLinkRegex(selector, path) => if (testing) {
      Some(new TestLink(selector, path))
    } else {
      None
    }
    case _ => throw new RuntimeException(s"Unknown instruction: [$line]")
  }

  private def parseCondition(instruction: String,
                             trueInstruction: ActivateInstruction,
                             falseInstruction: Option[ActivateInstruction]): ConditionalInstruction = instruction match {
    case HasClassConditionRegex(selector, className) => new HasClassInstruction(selector, className, trueInstruction, falseInstruction)
    case _ => throw new RuntimeException(s"Unknown condition: [$instruction]")
  }

  def apply(): ActivationSupport = new ActivationSupport {}
}