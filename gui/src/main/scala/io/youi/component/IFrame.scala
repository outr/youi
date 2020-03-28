package io.youi.component

import io.youi.component.types.Prop
import io.youi.dom
import org.scalajs.dom.html

import scala.util.Try

class IFrame(val iframe: html.IFrame = dom.create.iframe) extends Component(iframe) {
  lazy val src: Prop[String] = new Prop[String](iframe.src, iframe.src_=)
  lazy val width: Prop[Int] = new Prop[Int](Try(iframe.width.toInt).getOrElse(0), i => iframe.width = i.toString(), measure)
  lazy val height: Prop[Int] = new Prop[Int](Try(iframe.height.toInt).getOrElse(0), i => iframe.height = i.toString(), measure)
}