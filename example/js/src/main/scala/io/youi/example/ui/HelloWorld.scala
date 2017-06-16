package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.BasicText

object HelloWorld extends UIExampleScreen with UIScreen {
  override def name: String = "Hello World"
  override def path: String = "/examples/hello.html"

  override def createUI(): Unit = {
    val text = new BasicText {
      value := "Hello, World!"
      font.size := 48.0
      fill := Color.DarkBlue

      position.x.attachAndFire(x => scribe.info(s"X: $x"))
      position.y.attachAndFire(y => scribe.info(s"Y: $y"))
      size.width.attachAndFire(w => scribe.info(s"W: $w"))
      size.height.attachAndFire(h => scribe.info(s"H: $h"))
      size.measured.width.attachAndFire(w => scribe.info(s"MW: $w"))
      size.measured.height.attachAndFire(h => scribe.info(s"MH: $h"))
      renderer.position.center.attachAndFire(c => scribe.info(s"C: $c"))

      position.center := renderer.position.center
      position.middle := renderer.position.middle

      event.click.on {
        val x = position.x()
        val y = position.y()
        val w = size.width()
        val h = size.height()
        val mw = size.measured.width()
        val mh = size.measured.height()
        scribe.info(s"X: $x")
        scribe.info(s"Y: $y")
        scribe.info(s"W: $w")
        scribe.info(s"H: $h")
        scribe.info(s"MW: $mw")
        scribe.info(s"MH: $mh")
      }
    }
    container.children += text
  }
}