package io.youi.path

trait PathBuilder {
  def begin: Path = withAction(BeginPath)
  def close: Path = withAction(ClosePath)
  def curve(x1: Double, y1: Double, x2: Double, y2: Double, x: Double, y: Double): Path = {
    withAction(CurveTo(x1, y1, x2, y2, x, y))
  }
  def line(x: Double, y: Double): Path = withAction(LineTo(x, y))
  def move(x: Double, y: Double): Path = withAction(MoveTo(x, y))
  def quadratic(x1: Double, y1: Double, x: Double, y: Double): Path = withAction(QuadraticCurveTo(x1, y1, x, y))
  def rect(x: Double, y: Double, width: Double, height: Double): Path = withAction(Rectangle(x, y, width, height))
  def roundedRect(x: Double, y: Double, width: Double, height: Double, radius: Double = 5.0): Path = {
    withAction(RoundedRectangle(x, y, width, height, radius))
  }

  def withAction(action: PathAction): Path
}
