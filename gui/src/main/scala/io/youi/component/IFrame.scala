package io.youi.component

import io.youi.component.feature.{FeatureParent, HeightFeature, WidthFeature}
import io.youi.component.types.Prop
import io.youi.dom
import io.youi.theme.Theme
import org.scalajs.dom.html

import scala.scalajs.js.|
import scala.util.Try

class IFrame(val iframe: html.IFrame = dom.create.iframe) extends Component(iframe) with WidthFeature with HeightFeature {
  override protected def parent: FeatureParent = this

  lazy val src: Prop[String] = new Prop[String](iframe.src, iframe.src_=)
  override lazy val width: Prop[Double] = new Prop[Double](Try(iframe.width.toDouble).getOrElse(0), i => iframe.width = i.toString, measureComponent)
  override lazy val height: Prop[Double] = new Prop[Double](Try(iframe.height.toDouble).getOrElse(0), i => iframe.height = i.toString, measureComponent)
}