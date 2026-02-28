package youi.component

import rapid.Task
import youi._
import youi.component.types.PositionType
import youi.image.Image

/**
  * Renders an image using 9-slice scaling. Corners remain unscaled, edges scale
  * in one axis, and the center scales in both axes.
  *
  * @param sourceImage the source image to slice
  * @param x1 left boundary of the center slice (pixels from left)
  * @param x2 right boundary of the center slice (pixels from left)
  * @param y1 top boundary of the center slice (pixels from top)
  * @param y2 bottom boundary of the center slice (pixels from top)
  */
class Scale9(val sourceImage: Image,
             val x1: Double,
             val x2: Double,
             val y1: Double,
             val y2: Double) extends Container {

  private val imgW: Double = sourceImage.width
  private val imgH: Double = sourceImage.height
  private val leftW: Double = x1
  private val rightW: Double = imgW - x2
  private val topH: Double = y1
  private val bottomH: Double = imgH - y2

  private def makeCell(): ImageView = new ImageView {
    position.`type` @= PositionType.Absolute
  }

  private val tlView = makeCell()
  private val tView  = makeCell()
  private val trView = makeCell()
  private val lView  = makeCell()
  private val cView  = makeCell()
  private val rView  = makeCell()
  private val blView = makeCell()
  private val bView  = makeCell()
  private val brView = makeCell()

  // Corner positions (static)
  tlView.position.left @= 0.0
  tlView.position.top  @= 0.0

  tView.position.left := leftW
  tView.position.top  @= 0.0

  trView.position.left := size.width - rightW
  trView.position.top  @= 0.0

  lView.position.left @= 0.0
  lView.position.top  := topH

  cView.position.left := leftW
  cView.position.top  := topH

  rView.position.left := size.width - rightW
  rView.position.top  := topH

  blView.position.left @= 0.0
  blView.position.top  := size.height - bottomH

  bView.position.left := leftW
  bView.position.top  := size.height - bottomH

  brView.position.left := size.width - rightW
  brView.position.top  := size.height - bottomH

  // Fixed-size corners (top row)
  tlView.height @= topH
  tView.height  @= topH
  trView.height @= topH

  // Fixed-size corners (bottom row)
  blView.height @= bottomH
  bView.height  @= bottomH
  brView.height @= bottomH

  // Fixed-size columns (left)
  tlView.width @= leftW
  lView.width  @= leftW
  blView.width @= leftW

  // Fixed-size columns (right)
  trView.width @= rightW
  rView.width  @= rightW
  brView.width @= rightW

  // Stretchy widths (center column)
  tView.width := size.width - leftW - rightW
  cView.width := size.width - leftW - rightW
  bView.width := size.width - leftW - rightW

  // Stretchy heights (middle row)
  lView.height := size.height - topH - bottomH
  cView.height := size.height - topH - bottomH
  rView.height := size.height - topH - bottomH

  children ++= Seq(tlView, tView, trView, lView, cView, rView, blView, bView, brView)

  /** Clips the source image into 9 regions and assigns them to the cell ImageViews. */
  def init(): Task[Unit] = for {
    tl <- sourceImage.clip(0,   0,    x1,   y1)
    tc <- sourceImage.clip(x1,  0,    x2,   y1)
    tr <- sourceImage.clip(x2,  0,    imgW, y1)
    l  <- sourceImage.clip(0,   y1,   x1,   y2)
    c  <- sourceImage.clip(x1,  y1,   x2,   y2)
    r  <- sourceImage.clip(x2,  y1,   imgW, y2)
    bl <- sourceImage.clip(0,   y2,   x1,   imgH)
    bc <- sourceImage.clip(x1,  y2,   x2,   imgH)
    br <- sourceImage.clip(x2,  y2,   imgW, imgH)
  } yield {
    tlView.image @= tl
    tView.image  @= tc
    tView.width  := size.width - leftW - rightW  // re-assert reactive binding after image assignment
    trView.image @= tr
    lView.image  @= l
    lView.height := size.height - topH - bottomH
    cView.image  @= c
    cView.width  := size.width - leftW - rightW
    cView.height := size.height - topH - bottomH
    rView.image  @= r
    rView.height := size.height - topH - bottomH
    blView.image @= bl
    bView.image  @= bc
    bView.width  := size.width - leftW - rightW
    brView.image @= br
  }
}
