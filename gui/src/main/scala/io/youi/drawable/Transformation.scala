package io.youi.drawable

trait Transformation extends Drawable {
  modified := drawable.modified

  protected def transform(context: Context): Unit
  protected def drawable: Drawable

  override def draw(context: Context, x: Double, y: Double): Unit = {
    context.save()
    transform(context)
    drawable.draw(context, x, y)
    context.restore()
  }
}

object Transformation {
  def apply(x: Double = 0.0,
            y: Double = 0.0,
            pivotX: Double = 0.0,
            pivotY: Double = 0.0,
            rotation: Double = 0.0)
           (drawable: Drawable): Transformation = {
    val d = drawable
    new Transformation {
      override protected def drawable: Drawable = d

      override protected def transform(context: Context): Unit = {
        context.translate(x, y)
        context.translate(pivotX, pivotY)
        context.rotate(rotation * (math.Pi * 2.0))
        context.translate(-pivotX, -pivotY)
      }
    }
  }

  def transform(context: Context,
                x: Double = 0.0,
                y: Double = 0.0,
                pivotX: Double = 0.0,
                pivotY: Double = 0.0,
                rotation: Double = 0.0,
                manageState: Boolean = true)
               (drawable: Drawable = Drawable.None): Unit = {
    if (manageState) context.save()
    context.translate(x, y)
    context.translate(pivotX, pivotY)
    context.rotate(rotation * (math.Pi * 2.0))
    context.translate(-pivotX, -pivotY)
    drawable.draw(context, x, y)
    if (manageState) context.restore()
  }
}