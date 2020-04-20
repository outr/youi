package io.youi.component.feature

import io.youi.component.types.{Prop, SizeProperty}
import io.youi.font.{GoogleFont, GoogleFontWeight}

import scala.concurrent.{ExecutionContext, Future}

class FontFeature(override val parent: FeatureParent) extends Feature {
  lazy val family: Prop[String] = new Prop[String](parent.css.fontFamily, parent.css.fontFamily_=, parent.measureComponent)
  object weight extends Prop[String](parent.css.fontWeight, parent.css.fontWeight_=, parent.measureComponent) {
    def @=(fontWeight: GoogleFontWeight): Unit = {
      family @= fontWeight.font.family
      this @= fontWeight.name
    }
    def !(fontWeight: Future[GoogleFontWeight])(implicit ec: ExecutionContext): Unit = fontWeight.foreach(fw => this @= fw)
  }
  lazy val size: SizeProperty = new SizeProperty(parent.css.fontSize, parent.css.fontSize_=, parent.measureComponent)

  def !(font: Future[GoogleFont])(implicit ec: ExecutionContext): Unit = family ! font.map(_.family)
  def @=(font: GoogleFont): Unit = family @= font.family
}