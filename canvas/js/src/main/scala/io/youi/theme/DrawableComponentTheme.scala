//package io.youi.theme
//
//import io.youi.component.draw.{LineCap, LineJoin}
//import io.youi.component.{CanvasComponent, PaintTheme, StrokeTheme}
//import io.youi.style.Paint
//import reactify.Var
//
//trait DrawableComponentTheme extends CanvasComponentTheme with PaintTheme {
//  override def defaultParent: Option[Theme] = Some(CanvasComponent)
//
//  private def prnt[T](f: DrawableComponentTheme => T, default: => T): T = parent().collect {
//    case p: DrawableComponentTheme => p
//  }.map(f).getOrElse(default)
//
//  override val fill: Var[Paint] = Var(prnt(_.fill, Paint.none))
//  override object stroke extends StrokeTheme {
//    override val paint: Var[Paint] = Var(prnt(_.stroke.paint, Paint.none))
//    override val lineWidth: Var[Double] = Var(prnt(_.stroke.lineWidth, 1.0))
//    override val lineDash: Var[List[Double]] = Var(prnt(_.stroke.lineDash, Nil))
//    override val lineDashOffset: Var[Double] = Var(prnt(_.stroke.lineDashOffset, 0.0))
//    override val lineCap: Var[LineCap] = Var(prnt(_.stroke.lineCap, LineCap.Butt))
//    override val lineJoin: Var[LineJoin] = Var(prnt(_.stroke.lineJoin, LineJoin.Miter))
//  }
//  val background: Var[Paint] = Var(prnt(_.background, Paint.none))
//}
