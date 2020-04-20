package io.youi.paint

trait PatternPaint extends Paint {
  def createPattern(): CanvasPattern

  override def asJS(context: Context): js.Any = if (context.canvas.width > 0 && context.canvas.height > 0) {
    createPattern()
  } else {
    ""
  }

  override def asCSS(): String = ???
}
