package org.hyperscala.svg

import org.hyperscala.{BodyContent, Container, WebAttribute}
import org.hyperscala.javascript.events.EventSupport
import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait SvgTag extends Container[SvgTag] with BodyContent with EventSupport {
  implicit def c2s(color: Color) = color.name match {
    case null => color.hex.rgb
    case n => n
  }

  val id = WebAttribute[String]("id")
  val xmlBase = WebAttribute[String]("xml:base")
  val xmlLang = WebAttribute[String]("xml:lang")
  val xmlSpace = WebAttribute[String]("xml:space")
}
