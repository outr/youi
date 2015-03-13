package org.hyperscala.fabricjs.game

import com.outr.net.http.session.Session
import org.hyperscala.css.attributes.{LengthType, NumericLength, Length}
import org.hyperscala.fabricjs.StaticCanvas
import org.hyperscala.ui.module.Monitor
import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TileBoard[Tile <: Object](canvas: StaticCanvas, tileWidth: Double, tileHeight: Double) {
  canvas.canvas.connected[Webpage[Session]] {
    case webpage => {
      webpage.require(Monitor)
      println(s"Monitoring...")

      Monitor.sync(webpage, canvas.canvas.style.width, 1.0)
      Monitor.sync(webpage, canvas.canvas.style.height, 1.0)
    }
  }

  canvas.canvas.style.width.change.on {
    case evt => sizeChanged()
  }
  canvas.canvas.style.height.change.on {
    case evt => sizeChanged()
  }

  protected def sizeChanged() = if (isValid(canvas.canvas.style.width()) && isValid(canvas.canvas.style.height())) {
    val width = canvas.canvas.style.width().number
    val height = canvas.canvas.style.height().number
    val cols = math.ceil(width / tileWidth)
    val rows = math.ceil(height / tileHeight)
    // TODO: cols and rows should be an odd number since first tile should be centered
    println(s"Columns: ${cols} (${cols * tileWidth}), Rows: ${rows} (${rows * tileHeight})")
  }

  private def isValid(l: Length) = l != null && l.isInstanceOf[NumericLength] && l.asInstanceOf[NumericLength].lengthType == "px"
}
