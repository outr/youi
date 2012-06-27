package org.hyperscala.js

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object JsFunction {
  def f0(name: String)(f: => Any) = {
    Instruction(output = Some("function %s() {\r\n%s%s}\r\n".format(name, Instruction.functionOutput {
      f
    }, JavaScriptContext().tabs)))
  }

  def f1[T](name: String)(f: TypedVar[T] => Any) = {
    val arg1 = ExistingVariable[T]("arg1")
    Instruction(output = Some("function %s(arg1) {\r\n%s%s}\r\n".format(name, Instruction.functionOutput {
      f(arg1)
    }, JavaScriptContext().tabs)))
  }

  def f2[T1, T2](name: String)(f: (TypedVar[T1], TypedVar[T2]) => Any) = {
    val arg1 = ExistingVariable[T1]("arg1")
    val arg2 = ExistingVariable[T2]("arg2")
    Instruction(output = Some("function %s(arg1, arg2) {\r\n%s%s}\r\n".format(name, Instruction.functionOutput {
      f(arg1, arg2)
    }, JavaScriptContext().tabs)))
  }

  def f3[T1, T2, T3](name: String)(f: (TypedVar[T1], TypedVar[T2], TypedVar[T3]) => Any) = {
    val arg1 = ExistingVariable[T1]("arg1")
    val arg2 = ExistingVariable[T2]("arg2")
    val arg3 = ExistingVariable[T3]("arg3")
    Instruction(output = Some("function %s(arg1, arg2, arg3) {\r\n%s%s}\r\n".format(name, Instruction.functionOutput {
      f(arg1, arg2, arg3)
    }, JavaScriptContext().tabs)))
  }

  def f4[T1, T2, T3, T4](name: String)(f: (TypedVar[T1], TypedVar[T2], TypedVar[T3], TypedVar[T4]) => Any) = {
    val arg1 = ExistingVariable[T1]("arg1")
    val arg2 = ExistingVariable[T2]("arg2")
    val arg3 = ExistingVariable[T3]("arg3")
    val arg4 = ExistingVariable[T4]("arg4")
    Instruction(output = Some("function %s(arg1, arg2, arg3, arg4) {\r\n%s%s}\r\n".format(name, Instruction.functionOutput {
      f(arg1, arg2, arg3, arg4)
    }, JavaScriptContext().tabs)))
  }
}