package io.youi.component.bootstrap

import io.youi.component.extras.Classifiable
import io.youi.style.FontFamily
import io.youi.{Color, dom}
import io.youi.theme.bootstrap.ButtonTheme
import org.scalajs.dom._
import reactify.Var

class Button(override val element: html.Button) extends BootstrapComponent[html.Button] with ButtonTheme {
  def this() = {
    this(dom.create[html.Button]("button"))
  }

  override lazy val theme: Var[ButtonTheme] = Var(Button)
  override def componentType: String = "bootstrap.Button"

  element.classList.add("btn")

  // TODO: extract into mix-in
  connect[FontFamily](font.family, None, ff => element.style.fontFamily = ff.value, resetMeasured())
  connect(font.size, None, (v: Double) => element.style.fontSize = s"${v}px", resetMeasured())
  connect[Color](color, None, (c: Color) => element.style.color = c.toRGBA, resetMeasured())

  lazy val value: Var[String] = connect[String](Var(""), None, element.textContent = _, resetMeasured())

  val `type`: Var[ButtonType] = classify(Var(ButtonType.Primary), ButtonType)
}

object Button extends ButtonTheme

sealed abstract class ButtonType(key: String) {
  private val className: String = s"btn-$key"
}

object ButtonType extends Classifiable[ButtonType] {
  case object Primary extends ButtonType("primary")
  case object Secondary extends ButtonType("secondary")
  case object Success extends ButtonType("success")
  case object Danger extends ButtonType("danger")
  case object Warning extends ButtonType("warning")
  case object Info extends ButtonType("info")
  case object Light extends ButtonType("light")
  case object Dark extends ButtonType("dark")
  case object Link extends ButtonType("link")

  private val map = List(Primary, Secondary, Success, Danger, Warning, Info, Light, Dark, Link)
    .map(bt => bt.className -> bt)
    .toMap

  override def fromString(value: String): Option[ButtonType] = map.get(value)

  override def toString(value: ButtonType): String = value.className
}