package io.youi.component.support

import io.youi.component.types.{Prop, SizeProperty}
import io.youi.component.Component
import io.youi.font.{GoogleFont, GoogleFontWeight}

import scala.concurrent.{ExecutionContext, Future}

trait FontSupport {
  this: Component =>

  object font {
    lazy val family: Prop[String] = new Prop[String](element.style.fontFamily, element.style.fontFamily_=, measure)
    object weight extends Prop[String](element.style.fontWeight, element.style.fontWeight_=, measure) {
      def @=(fontWeight: GoogleFontWeight): Unit = {
        family @= fontWeight.font.family
        this @= fontWeight.name
      }
      def !(fontWeight: Future[GoogleFontWeight])(implicit ec: ExecutionContext): Unit = fontWeight.foreach(fw => this @= fw)
    }
    lazy val size: SizeProperty = new SizeProperty(element.style.fontSize, element.style.fontSize_=, measure)

    def !(font: Future[GoogleFont])(implicit ec: ExecutionContext): Unit = family ! font.map(_.family)
    def @=(font: GoogleFont): Unit = family @= font.family
  }
}