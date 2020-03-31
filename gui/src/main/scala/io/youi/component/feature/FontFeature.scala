package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.{Prop, SizeProperty}
import io.youi.font.{GoogleFont, GoogleFontWeight}

import scala.concurrent.{ExecutionContext, Future}

class FontFeature(override val component: Component) extends Feature {
  lazy val family: Prop[String] = new Prop[String](component.element.style.fontFamily, component.element.style.fontFamily_=, component.measure.trigger)
  object weight extends Prop[String](component.element.style.fontWeight, component.element.style.fontWeight_=, component.measure.trigger) {
    def @=(fontWeight: GoogleFontWeight): Unit = {
      family @= fontWeight.font.family
      this @= fontWeight.name
    }
    def !(fontWeight: Future[GoogleFontWeight])(implicit ec: ExecutionContext): Unit = fontWeight.foreach(fw => this @= fw)
  }
  lazy val size: SizeProperty = new SizeProperty(component.element.style.fontSize, component.element.style.fontSize_=, component.measure.trigger)

  def !(font: Future[GoogleFont])(implicit ec: ExecutionContext): Unit = family ! font.map(_.family)
  def @=(font: GoogleFont): Unit = family @= font.family
}