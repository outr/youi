//package io.youi.component.bootstrap
//
//import io.youi.style.FontFamily
//import io.youi.Color
//import io.youi.dom._
//import io.youi.theme.Theme
//import io.youi.theme.bootstrap.ButtonTheme
//import org.scalajs.dom._
//import reactify.Var
//
//class Button(override val element: html.Button) extends BootstrapComponent[html.Button] with ButtonTheme {
//  def this() = {
//    this(create[html.Button]("button"))
//  }
//
//  // TODO: clean this up
//  override protected def defaultThemeParent: Option[Theme] = Some(Button)
//  override lazy val theme: Var[ButtonTheme] = Var(this)
//  override def componentType: String = "bootstrap.Button"
//
//  element.classList.add("btn")
//
//  // TODO: extract into mix-in
//  connect[FontFamily](font.family, None, ff => element.style.fontFamily = ff.value, resetMeasured())
//  connect(font.size, None, (v: Double) => element.style.fontSize = s"${v}px", resetMeasured())
//  connect[Color](color, None, (c: Color) => element.style.color = c.toRGBA, resetMeasured())
//
//  lazy val value: Var[String] = connect[String](Var(""), None, element.textContent = _, resetMeasured())
//
//  val `type`: Var[ButtonType] = classify(Var(ButtonType.Primary), ButtonType)
//  val buttonSize: Var[ButtonSize] = classify(Var(ButtonSize.Normal), ButtonSize)
//  val block: Var[Boolean] = classifyFlag(Var(false), on = Some("btn-block"))
//}
//
//object Button extends ButtonTheme {
//  def existing(id: String, in: html.Element = document.body): Button = {
//    val e = in.byId[html.Button](id)
//    val b = new Button(e)
//    b
//  }
//}