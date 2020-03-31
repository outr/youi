package io.youi.component

import io.youi.component.feature.{HeightFeature, WidthFeature}
import io.youi.component.types.Prop
import io.youi.dom
import org.scalajs.dom.html

import scala.util.Try

class IFrame(val iframe: html.IFrame = dom.create.iframe) extends Component(iframe) with WidthFeature with HeightFeature {
  override protected def component: Component = this

  lazy val src: Prop[String] = new Prop[String](iframe.src, iframe.src_=)
  override lazy val width: Prop[Double] = new Prop[Double](Try(iframe.width.toDouble).getOrElse(0), i => iframe.width = i.toString(), measure.trigger)
  override lazy val height: Prop[Double] = new Prop[Double](Try(iframe.height.toDouble).getOrElse(0), i => iframe.height = i.toString(), measure.trigger)
}