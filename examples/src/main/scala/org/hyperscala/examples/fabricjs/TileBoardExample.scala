package org.hyperscala.examples.fabricjs

import org.hyperscala.examples.Example
import org.hyperscala.fabricjs.game.TileBoard
import org.hyperscala.fabricjs.{FabricJS, Image, StaticCanvas}
import org.hyperscala.html._
import org.hyperscala.web.Webpage
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TileBoardExample extends Webpage with Example {
  require(FabricJS)

  val canvas = new tag.Canvas(id = "board", width = 1120, height = 600)
  val fabricCanvas = new StaticCanvas(canvas)
  val tileBoard = new TileBoard[Image](fabricCanvas, 64, 64)

  canvas.style.backgroundColor := Color.AliceBlue
  canvas.style.border := "1px solid black"

  body.contents += canvas
}
