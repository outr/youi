package io.youi.component.bootstrap

import io.youi.component.Container
import io.youi.dom
import io.youi.theme.Theme
import org.scalajs.dom.html

class Column(element: html.Element = dom.create[html.Div]("div"),
             extraSmall: Option[Int] = None,
             small: Option[Int] = None,
             medium: Option[Int] = None,
             large: Option[Int] = None,
             extraLarge: Option[Int] = None) extends Container(element) with BootstrapComponent[html.Element] {
  assert(extraSmall.nonEmpty || small.nonEmpty || medium.nonEmpty || large.nonEmpty || extraLarge.nonEmpty, "At least one column length must be set")
  validate("extra small", extraSmall)
  validate("small", small)
  validate("medium", medium)
  validate("large", large)
  validate("extra large", extraLarge)

  private def validate(name: String, v: Option[Int]): Unit = v.foreach { n =>
    assert(n > 0 && n <= 12, s"$name has an invalid size: $n (must be between 1 and 12)")
  }

  override protected def defaultParentTheme: Theme = Container
  override def componentType: String = "bootstrap.Column"

  override protected def init(): Unit = {
    super.init()

    List(
      extraSmall.map("" -> _),
      small.map("sm-" -> _),
      medium.map("md-" -> _),
      large.map("lg-" -> _),
      extraLarge.map("xl-" -> _)
    ).flatten.foreach {
      case (s, v) => classList += s"col-$s$v"
    }
  }
}
