package youi

import org.scalajs.dom._

import scala.language.implicitConversions

package object component {
  implicit def component2Element(component: Component): html.Element = component.element
}