package io.youi

import io.youi.ui.support.ContainerSupport
import org.scalajs.dom._

import scala.language.implicitConversions

package object ui extends Component(document.body) with ContainerSupport {
  implicit def component2Element(component: Component): html.Element = component.element
  implicit def t2Opt[T](value: T): Option[T] = Option(value)
}