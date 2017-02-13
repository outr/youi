package io.youi

import org.scalajs.dom._

import io.youi.dom._

/**
  * Instantiate this to find, parse, and remove all activate tags in the body of the HTML.
  */
trait ActivationSupport {
  protected def activationRoot: html.Element = document.body
  private lazy val instructions: List[ActivateInstruction] = ActivationSupport.parse(activationRoot)

  def activate(): Unit = instructions.foreach(_.activate())
  def deactivate(): Unit = instructions.foreach(_.deactivate())
}

object ActivationSupport {
  private val ConditionalRegex = """(.+) \? (.+) \: (.+)""".r
  private val ConditionalPartialRegex = """(.+) \? (.+)""".r
  private val SetTitleRegex = """title = "(.*)"""".r
  private val AddClassRegex = """(.+)[.]addClass\("(.+)"\)""".r
  private val RemoveClassRegex = """(.+)[.]removeClass\("(.+)"\)""".r
  private val ReplaceContentWithRegex = """(.+)[.]replaceContentWith\((.+)\)""".r
  private val AlertRegex = """alert\("(.+)"\)""".r

  private val HasClassConditionRegex = """(.+).hasClass\("(.+)"\)""".r

  private def parse(root: html.Element): List[ActivateInstruction] = {
    val tags = root.byTag[html.Element]("activate")
    val activate = tags.map(_.innerHTML.trim).mkString("\n").split('\n').map(_.trim).toList
    val instructions = activate.flatMap(parseInstruction)
    tags.foreach(_.remove())
    instructions
  }

  private def parseInstruction(line: String): Option[ActivateInstruction] = line.trim match {
    case "" => None
    case s if s.startsWith("//") => None
    case ConditionalRegex(condition, trueInstruction, falseInstruction) => {
      val ti = parseInstruction(trueInstruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$trueInstruction]"))
      val fi = parseInstruction(falseInstruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$falseInstruction]"))
      Some(parseCondition(condition, ti, Some(fi)))
    }
    case ConditionalPartialRegex(condition, trueInstruction) => {
      val ti = parseInstruction(trueInstruction).getOrElse(throw new RuntimeException(s"Failed to parse instruction: [$trueInstruction]"))
      Some(parseCondition(condition, ti, None))
    }
    case SetTitleRegex(title) => Some(new SetTitleInstruction(title))
    case AddClassRegex(selector, className) => Some(new AddClassInstruction(selector, className))
    case RemoveClassRegex(selector, className) => Some(new RemoveClassInstruction(selector, className))
    case AlertRegex(message) => Some(new AlertInstruction(message))
    case _ => throw new RuntimeException(s"Unknown instruction: [$line]")
  }

  private def parseCondition(instruction: String,
                             trueInstruction: ActivateInstruction,
                             falseInstruction: Option[ActivateInstruction]): ConditionalInstruction = instruction match {
    case HasClassConditionRegex(selector, className) => new HasClassInstruction(selector, className, trueInstruction, falseInstruction)
    case _ => throw new RuntimeException(s"Unknown condition: [$instruction]")
  }
}

trait ActivateInstruction {
  def activate(): Unit

  def deactivate(): Unit
}

abstract class ConditionalInstruction(trueInstruction: ActivateInstruction,
                                      falseInstruction: Option[ActivateInstruction]) extends ActivateInstruction {
  def condition: Boolean

  override def activate(): Unit = if (condition) {
    trueInstruction.activate()
  } else {
    falseInstruction.foreach(_.activate())
  }

  override def deactivate(): Unit = if (condition) {
    trueInstruction.deactivate()
  } else {
    falseInstruction.foreach(_.deactivate())
  }
}

class HasClassInstruction(selector: String,
                          className: String,
                          trueInstruction: ActivateInstruction,
                          falseInstruction: Option[ActivateInstruction]) extends ConditionalInstruction(trueInstruction, falseInstruction) {
  override def condition: Boolean = dom.bySelector[html.Element](selector).forall(_.classList.contains(className))
}

class SetTitleInstruction(title: String) extends ActivateInstruction {
  private var previousTitle: String = document.title

  override def activate(): Unit = {
    previousTitle = document.title
    document.title = title
  }

  override def deactivate(): Unit = {
    document.title = previousTitle
  }
}

class AddClassInstruction(selector: String, className: String) extends ActivateInstruction {
  override def activate(): Unit = dom.bySelector[html.Element](selector).foreach(_.classList.add(className))

  override def deactivate(): Unit = dom.bySelector[html.Element](selector).foreach(_.classList.remove(className))
}

class RemoveClassInstruction(selector: String, className: String) extends ActivateInstruction {
  override def activate(): Unit = dom.bySelector[html.Element](selector).foreach(_.classList.remove(className))

  override def deactivate(): Unit = dom.bySelector[html.Element](selector).foreach(_.classList.add(className))
}

class AlertInstruction(message: String) extends ActivateInstruction {
  override def activate(): Unit = window.alert(message)

  override def deactivate(): Unit = {}
}