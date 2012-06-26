package com.outr.webframework.js

trait Var extends Instruction {
}

object Var {
  def format(template: String, args: Var*) = {
    var t = template
    args.foreach {
      case arg => {
        t = t.replaceFirst("%s", "' + " + arg.reference.get + " + '")
      }
    }
    Constant(t)
  }
}