package io.youi.component

import reactify._

class Scale9 extends AbstractContainer {
  override type Child = Image

  def this(texture: Texture) = {
    this()
    this.texture := texture
  }

  val texture: Var[Texture] = Var(Texture.Empty)
  val x1: Var[Double] = Var(0.0)
  val y1: Var[Double] = Var(0.0)
  val x2: Var[Double] = Var(0.0)
  val y2: Var[Double] = Var(0.0)

  val leftWidth: Val[Double] = x1.asVal
  val rightWidth: Val[Double] = Val(texture.width - x2)
  val centerWidth: Val[Double] = Val(size.width - (leftWidth + rightWidth))
  val topHeight: Val[Double] = y1.asVal
  val bottomHeight: Val[Double] = Val(texture.height - y2)
  val middleHeight: Val[Double] = Val(size.height - (topHeight + bottomHeight))

  private val topLeft = new Image {
    position.left := 0.0
    position.top := 0.0
    size.width := x1
    size.height := y1
    texture := Scale9.this.texture().clipped.fromPoints(0.0, 0.0, x1, y1)
  }
  private val top = new Image {
    position.left := x1
    position.top := 0.0
    size.width := centerWidth
    size.height := y1
    texture := Scale9.this.texture().clipped.fromPoints(x1, 0.0, x2, y1)
  }
  private val topRight = new Image {
    position.right := Scale9.this.size.width
    position.top := 0.0
    size.width := rightWidth
    size.height := y1
    texture := Scale9.this.texture().clipped.fromPoints(x2, 0.0, Scale9.this.texture().width, y1)
  }
  private val left = new Image {
    position.left := 0.0
    position.top := y1
    size.width := x1
    size.height := middleHeight
    texture := Scale9.this.texture().clipped.fromPoints(0.0, y1, x1, y2)
  }
  private val center = new Image {
    position.left := x1
    position.top := y1
    size.width := centerWidth
    size.height := middleHeight
    texture := Scale9.this.texture().clipped.fromPoints(x1, y1, x2, y2)
  }
  private val right = new Image {
    position.right := Scale9.this.size.width
    position.top := y1
    size.width := rightWidth
    size.height := middleHeight
    texture := Scale9.this.texture().clipped.fromPoints(x2, y1, Scale9.this.texture.width, y2)
  }
  private val bottomLeft = new Image {
    position.left := 0.0
    position.bottom := Scale9.this.size.height
    size.width := x1
    size.height := bottomHeight
    texture := Scale9.this.texture().clipped.fromPoints(0.0, y2, x1, Scale9.this.texture.height)
  }
  private val bottom = new Image {
    position.left := x1
    position.bottom := Scale9.this.size.height
    size.width := centerWidth
    size.height := bottomHeight
    texture := Scale9.this.texture().clipped.fromPoints(x1, y2, x2, Scale9.this.texture.height)
  }
  private val bottomRight = new Image {
    position.right := Scale9.this.size.width
    position.bottom := Scale9.this.size.height
    size.width := rightWidth
    size.height := bottomHeight
    texture := Scale9.this.texture().clipped.fromPoints(x2, y2, Scale9.this.texture.width, Scale9.this.texture.height)
  }

  childEntries ++= List(topLeft, top, topRight, left, center, right, bottomLeft, bottom, bottomRight)
}
