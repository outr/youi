//package io.youi.theme
//
//import io.youi.Color
//import io.youi.component.DrawableComponent
//import io.youi.component.font.Font
//import io.youi.style.Paint
//import reactify.Var
//
//trait TextTheme extends DrawableComponentTheme {
//  override def defaultParent: Option[Theme] = Some(DrawableComponent)
//
//  private def prnt[T](f: TextTheme => T, default: => T): T = parent().collect {
//    case p: TextTheme => p
//  }.map(f).getOrElse(default)
//
//  object font {
//    val file: Var[Font] = Var(prnt(_.font.file, Font.empty))
//    val size: Var[Double] = Var(prnt(_.font.size, 26.0))
//    val kerning: Var[Boolean] = Var(prnt(_.font.kerning, true))
//  }
//  object selection {
//    val enabled: Var[Boolean] = Var(prnt(_.selection.enabled, true))
//    val fill: Var[Paint] = Var(prnt(_.selection.fill, Color.LightBlue))
//    val stroke: Var[Paint] = Var(prnt(_.selection.stroke, Paint.none))
//  }
//}