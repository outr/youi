package org.hyperscala.realtime.dsl

import org.powerscala.reflect.EnhancedClass
import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class JavaScript extends Instruction {
  private var instructions = List.empty[Instruction]
  private var initialized = false

  def context = null

  protected[realtime] def +=(instruction: Instruction) = synchronized {
    instructions = instruction :: instructions
  }

  implicit def thisJavaScript = this

  def write(b: StringBuilder) = synchronized {
    if (!initialized) {
      val c: EnhancedClass = getClass
      c.fields.filter(f => f.hasType(classOf[Var[_]])).foreach {
        case f => {
          val variable = f[Var[Any]](this)
          variable._name = f.name
        }
      }
      initialized = true
    }
    instructions.reverse.foreach(i => i.write(b))
  }

  def toJS = {
    val b = new StringBuilder
    write(b)
    new JavaScriptString(b.toString())
  }
}