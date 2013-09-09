package org.hyperscala.realtime.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Instruction {
  def context: JavaScript

  if (context != null) {
    context += this
  }

  def write(b: StringBuilder): Unit
}