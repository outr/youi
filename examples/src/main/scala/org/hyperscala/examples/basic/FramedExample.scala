package org.hyperscala.examples.basic

import org.hyperscala.html._

import org.hyperscala.web.{Framed, Webpage}
import org.hyperscala.examples.Example
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FramedExample extends Example {
  contents += new Framed(new InlinePage) {
    width := "300"
    height := "300"
  }
}

class InlinePage extends Webpage {
  body.style.backgroundColor := Color.LightBlue
  body.contents += new tag.H1(content = "Inline Page")
}