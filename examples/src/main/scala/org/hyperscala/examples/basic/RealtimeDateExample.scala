package org.hyperscala.examples.basic

import java.util.Calendar

import org.hyperscala.examples.Example
import org.hyperscala.realtime.Realtime

import org.hyperscala.html._
import org.powerscala.Updatable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeDateExample extends Example with Updatable {
  this.require(Realtime)

  contents += new tag.P(content = "Updates the clock every five seconds on the server.")

  val div = new tag.Div
  contents += div

  updateTime()

  override def update(delta: Double) = {
    super.update(delta)

    updateTime()
  }

  def updateTime() = {
    div.contents.replaceWith(new tag.H1(content = Calendar.getInstance().getTime.toString))
  }
}