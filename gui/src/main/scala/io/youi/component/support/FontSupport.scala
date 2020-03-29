package io.youi.component.support

import io.youi.component.types.{Prop, SizeProperty, TextAlign}
import io.youi.component.Component
import io.youi.font.{GoogleFont, GoogleFontWeight}

import scala.concurrent.{ExecutionContext, Future}

trait FontSupport {
  this: Component =>

  object font {
    lazy val family: Prop[String] = new Prop[String](element.style.fontFamily, element.style.fontFamily_=, measure.trigger)
    object weight extends Prop[String](element.style.fontWeight, element.style.fontWeight_=, measure.trigger) {
      def @=(fontWeight: GoogleFontWeight): Unit = {
        scribe.info(s"Setting font: ${fontWeight.font.family}")
        family @= fontWeight.font.family
        scribe.info(s"Setting font name: ${fontWeight.name}")
        this @= fontWeight.name
      }
      def !(fontWeight: Future[GoogleFontWeight])(implicit ec: ExecutionContext): Unit = fontWeight.foreach(fw => this @= fw)
    }
    lazy val size: SizeProperty = new SizeProperty(element.style.fontSize, element.style.fontSize_=, measure.trigger)

    def !(font: Future[GoogleFont])(implicit ec: ExecutionContext): Unit = family ! font.map(_.family)
    def @=(font: GoogleFont): Unit = family @= font.family
  }
  lazy val textAlign: Prop[TextAlign] = Prop.stringify(element.style.textAlign, element.style.textAlign_=, TextAlign, TextAlign.Initial)
}