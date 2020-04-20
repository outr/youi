package io.youi.path

case class Fill(paint: Paint, path: Option[Path] = None, apply: Boolean = true) extends PathAction {
  modified := paint.modified

  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = {
    Fill.draw(context, paint, path, apply)
  }
}

object Fill {
  def draw(context: Context,
           paint: Paint,
           path: Option[Path] = None,
           apply: Boolean = true): Unit = if (paint.nonEmpty) {
    path match {
      case Some(p) => {
        context.fill(paint, apply = false)
        context.ctx.asInstanceOf[js.Dynamic].fill(p.path2d)
      }
      case None => context.fill(paint, apply)
    }
  }
}