package io.youi.theme

import io.youi.Color
import io.youi.component.{DrawableComponent, PaintTheme, StrokeTheme}
import io.youi.style.Paint
import reactify.Var

trait RectangularSelectionTheme extends DrawableComponentTheme {
  override def defaultParent: Option[Theme] = Some(DrawableComponent)

  private def prnt[T](f: RectangularSelectionTheme => T, default: => T): T = parent.collect {
    case p: RectangularSelectionTheme => p
  }.map(f).getOrElse(default)

  object selection extends PaintTheme {
    override val fill: Var[Paint] = Var(prnt(_.selection.fill, Paint.none))
    override object stroke extends StrokeTheme {
      override val paint: Var[Paint] = Var(prnt(_.selection.stroke.paint, Color.Red))
      override val lineWidth: Var[Double] = Var(prnt(_.selection.stroke.lineWidth, 1.0))
      override val lineDash: Var[List[Double]] = Var(prnt(_.selection.stroke.lineDash, Nil))
    }
  }
  object blocks extends PaintTheme {
    override val fill: Var[Paint] = Var(prnt(_.blocks.fill, Color.Blue))
    override object stroke extends StrokeTheme {
      override val paint: Var[Paint] = Var(prnt(_.blocks.stroke.paint, Paint.none))
      override val lineWidth: Var[Double] = Var(prnt(_.blocks.stroke.lineWidth, 1.0))
      override val lineDash: Var[List[Double]] = Var(prnt(_.blocks.stroke.lineDash, Nil))
    }
  }
  object dashes extends PaintTheme {
    override val fill: Var[Paint] = Var(prnt(_.dashes.fill, Paint.none))
    override object stroke extends StrokeTheme {
      override val paint: Var[Paint] = Var(prnt(_.dashes.stroke.paint, Color.Blue.withAlpha(0.5)))
      override val lineWidth: Var[Double] = Var(prnt(_.dashes.stroke.lineWidth, 1.0))
      override val lineDash: Var[List[Double]] = Var(prnt(_.dashes.stroke.lineDash, List(5.0, 10.0)))
    }
  }
  object modal extends PaintTheme {
    override val fill: Var[Paint] = Var(prnt(_.modal.fill, Color.Black.withAlpha(0.5)))
    override object stroke extends StrokeTheme {
      override val paint: Var[Paint] = Var(prnt(_.modal.stroke.paint, Paint.none))
      override val lineWidth: Var[Double] = Var(prnt(_.modal.stroke.lineWidth, 1.0))
      override val lineDash: Var[List[Double]] = Var(prnt(_.modal.stroke.lineDash, Nil))
    }
  }
}