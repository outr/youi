package org.hyperscala.js

trait Instruction {
  if (JavaScriptContext() != null) {
    JavaScriptContext().add(this)
  }

  def reference: Option[String] = None

  def output: Option[String] = None

  protected def functionOutput(f: => Any) = Instruction.functionOutput(f)
}

object Instruction {
  def apply(reference: Option[String] = None,
            output: Option[String] = None) = new ExplicitInstruction(reference, output)

  def functionOutput(f: => Any) = {
    val context = JavaScript(f)
    context.toJS
  }
}

class ExplicitInstruction(override val reference: Option[String] = None,
                          override val output: Option[String] = None) extends Instruction