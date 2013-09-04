package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.examples.Example

import org.hyperscala.realtime.dsl._
import org.hyperscala.event.Key

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeDSLExample extends Example {
  val input = new tag.Input(id = "my_input")
  contents += input

  $(input).keyup(onKey(Key.Escape) {
    println("Escape was pressed!")
  })
}
