package io.youi.theme

import io.youi.Color
import io.youi.component.draw.{LineCap, LineJoin}
import io.youi.component.{DrawableComponent, PaintTheme, StrokeTheme}
import io.youi.style.Paint
import reactify.Var

trait RectangularSelectionTheme extends DrawableComponentTheme {
  override def defaultParent: Option[Theme] = Some(DrawableComponent)

  private def prnt[T](f: RectangularSelectionTheme => T, default: => T): T = parent().collect {
    case p: RectangularSelectionTheme => p
  }.map(f).getOrElse(default)

  object selection extends PaintTheme {
    override val fill: Var[Paint] = Var(prnt(_.selection.fill, Paint.none))
    override object stroke extends StrokeTheme {
      override val paint: Var[Paint] = Var(prnt(_.selection.stroke.paint, Color.Red))
      override val lineWidth: Var[Double] = Var(prnt(_.selection.stroke.lineWidth, 1.0))
      override val lineDash: Var[List[Double]] = Var(prnt(_.selection.stroke.lineDash, Nil))
      override val lineDashOffset: Var[Double] = Var(prnt(_.selection.stroke.lineDashOffset, 0.0))
      override val lineCap: Var[LineCap] = Var(prnt(_.selection.stroke.lineCap, LineCap.Butt))
      override val lineJoin: Var[LineJoin] = Var(prnt(_.selection.stroke.lineJoin, LineJoin.Miter))
    }
  }
  object blocks extends PaintTheme {
    override val fill: Var[Paint] = Var(prnt(_.blocks.fill, Color.Blue))
    override object stroke extends StrokeTheme {
      override val paint: Var[Paint] = Var(prnt(_.blocks.stroke.paint, Paint.none))
      override val lineWidth: Var[Double] = Var(prnt(_.blocks.stroke.lineWidth, 1.0))
      override val lineDash: Var[List[Double]] = Var(prnt(_.blocks.stroke.lineDash, Nil))
      override val lineDashOffset: Var[Double] = Var(prnt(_.blocks.stroke.lineDashOffset, 0.0))
      override val lineCap: Var[LineCap] = Var(prnt(_.blocks.stroke.lineCap, LineCap.Butt))
      override val lineJoin: Var[LineJoin] = Var(prnt(_.blocks.stroke.lineJoin, LineJoin.Miter))
    }
  }
  object dashes extends PaintTheme {
    override val fill: Var[Paint] = Var(prnt(_.dashes.fill, Paint.none))
    override object stroke extends StrokeTheme {
      override val paint: Var[Paint] = Var(prnt(_.dashes.stroke.paint, Color.White))
      override val lineWidth: Var[Double] = Var(prnt(_.dashes.stroke.lineWidth, 0.5))
      override val lineDash: Var[List[Double]] = Var(prnt(_.dashes.stroke.lineDash, List(4.0, 4.0)))
      override val lineDashOffset: Var[Double] = Var(prnt(_.dashes.stroke.lineDashOffset, 2.0))
      override val lineCap: Var[LineCap] = Var(prnt(_.dashes.stroke.lineCap, LineCap.Butt))
      override val lineJoin: Var[LineJoin] = Var(prnt(_.dashes.stroke.lineJoin, LineJoin.Miter))
    }
    object shadow {
      val enabled: Var[Boolean] = Var(prnt(_.dashes.shadow.enabled, true))
      object offset {
        val x: Var[Double] = Var(prnt(_.dashes.shadow.offset.x, 1.0))
        val y: Var[Double] = Var(prnt(_.dashes.shadow.offset.y, 1.0))
      }
      val paint: Var[Paint] = Var(prnt(_.dashes.shadow.paint, Color.Black.withAlpha(0.5)))
      val lineWidth: Var[Double] = Var(prnt(_.dashes.shadow.lineWidth, 0.5))
    }
  }
  lazy val overflow: Var[Paint] = Var(prnt(_.overflow, Color.White))
  object modal extends PaintTheme {
    override val fill: Var[Paint] = Var(prnt(_.modal.fill, Color.Black.withAlpha(0.5)))
    override object stroke extends StrokeTheme {
      override val paint: Var[Paint] = Var(prnt(_.modal.stroke.paint, Paint.none))
      override val lineWidth: Var[Double] = Var(prnt(_.modal.stroke.lineWidth, 1.0))
      override val lineDash: Var[List[Double]] = Var(prnt(_.modal.stroke.lineDash, Nil))
      override val lineDashOffset: Var[Double] = Var(prnt(_.modal.stroke.lineDashOffset, 0.0))
      override val lineCap: Var[LineCap] = Var(prnt(_.modal.stroke.lineCap, LineCap.Butt))
      override val lineJoin: Var[LineJoin] = Var(prnt(_.modal.stroke.lineJoin, LineJoin.Miter))
    }
  }
}