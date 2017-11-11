//package io.youi.theme
//
//import io.youi.Color
//import io.youi.component.DrawableComponent
//import io.youi.paint._
//import reactify.Var
//
//trait RectangularSelectionTheme extends DrawableComponentTheme {
//  override protected def defaultThemeParent: Option[Theme] = Some(DrawableComponent)
//
//  private[theme] def prnt[T](f: RectangularSelectionTheme => T, default: => T): T = parentTheme().collect {
//    case p: RectangularSelectionTheme => p
//  }.map(f).getOrElse(default)
//
//  object selection {
//    val x1: Var[Double] = prop(prnt(_.selection.x1, 0.0), updatesRendering = true)
//    val y1: Var[Double] = prop(prnt(_.selection.y1, 0.0), updatesRendering = true)
//    val x2: Var[Double] = prop(prnt(_.selection.x2, 0.0), updatesRendering = true)
//    val y2: Var[Double] = prop(prnt(_.selection.y2, 0.0), updatesRendering = true)
//    val width: Var[Double] = prop(prnt(_.selection.width, x2 - x1), updatesRendering = true)
//    val height: Var[Double] = prop(prnt(_.selection.height, y2 - y1), updatesRendering = true)
//    val edgeDistance: Var[Double] = prop(prnt(_.selection.edgeDistance, 5.0), updatesRendering = true)
//    object aspectRatio extends Var[Option[Double]](() => None) {
//      def bySize(width: Double, height: Double): Unit = set(Some(width / height))
//    }
//
//    width := x2 - x1
//    height := y2 - y1
//
//    val minX: Var[Double] = Var(edgeDistance)
//    val minY: Var[Double] = Var(edgeDistance)
//    val maxX: Var[Double] = Var(0.0)
//    val maxY: Var[Double] = Var(0.0)
//    val minWidth: Var[Double] = Var(30.0)
//    val minHeight: Var[Double] = Var(30.0)
//
//    def set(x1: Double, y1: Double, x2: Double, y2: Double): Unit = {
//      this.x1 := x1
//      this.y1 := y1
//      this.x2 := x2
//      this.y2 := y2
//    }
//    def maximize(): Unit = {
//      x1.static(minX)
//      y1.static(minY)
//      x2.static(maxX)
//      y2.static(maxY)
//    }
//    val fill: Var[Paint] = prop(prnt(_.selection.fill, Paint.none), updatesRendering = true)
//    val stroke: Var[Stroke] = prop(prnt(_.selection.stroke, Stroke(Color.Red, None, 1.0)), updatesRendering = true)
//  }
//  object blocks {
//    val size: Var[Double] = Var(10.0)
//    val fill: Var[Paint] = prop(prnt(_.blocks.fill, Color.Blue), updatesRendering = true)
//    val stroke: Var[Stroke] = prop(prnt(_.blocks.stroke, Stroke.none), updatesRendering = true)
//  }
//  object dashes {
//    val fill: Var[Paint] = prop(prnt(_.dashes.fill, Paint.none), updatesRendering = true)
//    val stroke: Var[Stroke] = prop(prnt(_.dashes.stroke, Stroke(Color.White, None, 0.5, List(4.0, 4.0), 2.0)), updatesRendering = true)
//    object shadow {
//      val enabled: Var[Boolean] = prop(prnt(_.dashes.shadow.enabled, true), updatesRendering = true)
//      val paint: Var[Paint] = prop(prnt(_.dashes.shadow.paint, Color.Black.withAlpha(0.5)), updatesRendering = true)
//      val lineWidth: Var[Double] = prop(prnt(_.dashes.shadow.lineWidth, 0.5), updatesRendering = true)
//      object offset {
//        val x: Var[Double] = prop(prnt(_.dashes.shadow.offset.x, 1.0), updatesRendering = true)
//        val y: Var[Double] = prop(prnt(_.dashes.shadow.offset.y, 1.0), updatesRendering = true)
//      }
//    }
//  }
//  object modal {
//    val fill: Var[Paint] = prop(prnt(_.modal.fill, Color.Black.withAlpha(0.5)), updatesRendering = true)
//    val stroke: Var[Stroke] = prop(prnt(_.modal.stroke, Stroke.none), updatesRendering = true)
//  }
//
//  lazy val overflow: Var[Paint] = Var(prnt(_.overflow, Color.White))
//}