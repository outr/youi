package org.hyperscala.examples.ui

import com.outr.net.http.session.Session
import org.hyperscala.css.attributes._
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.hyperscala.ui.module.Monitor
import org.hyperscala.web._
import org.powerscala.property.Property

import scala.annotation.tailrec

/**
 * @author Matt Hicks <matt@outr.com>
 */
class GalleryExample extends Example {
  this.require(Monitor)
  this.require(Gritter)

  val preferredHeight = 250

  contents += new tag.P {
    contents += "Gallery provides a server-side determination of sizing and row count."
  }

  val div = new tag.Div(id = "gallery")
  contents += div

  connected[Webpage] {
    case webpage => Monitor.sync(webpage, div.style.width, 1.0)
  }

  div.style.width.change.on {
    case evt => updateDimensions()
  }

  (0 until 50).foreach {
    case index => {
      val width = math.round(200 * math.random).toInt + 50
      val height = math.round(300 * math.random).toInt + 100
      val imageURL = s"http://lorempixel.com/$width/$height/"
      div.contents += new Sized(width, height) {
//        contents += new tag.A(href = imageURL) {
          contents += new tag.Img(src = imageURL, alt = s"Image ${index + 1}") {
            style.width := 100.pct
            style.height := 100.pct
          }
          //        if (index == 0) {
          //          contents += new tag.Div(clazz = List("caption")) {
          //            contents += "This is a "
          //            contents += new tag.Span(content = "custom caption!") {
          //              style.color := Color.Red
          //            }
          //            contents += new tag.Span(content = "Nice!") {
          //              style.float := Float.Right
          //            }
          //          }
          //        }
        }
//      }
    }
  }

  def updateDimensions() = {
    val entries = div.byTag[Sized].toVector
//    div.byTag[Sized].foreach {
//      case sized => sized.height := preferredHeight
//    }
    val unused = layoutRows(entries)
    Gritter.add(this.webpage, "Excluded Results", s"Excluded last $unused entries.")
  }

  @tailrec
  private def layoutRows(entries: Vector[Sized]): Int = {
    val maxWidth = div.style.width().asInstanceOf[PixelLength].number - 1.0
    val (index, width) = filledRow(entries, preferredHeight, maxWidth)
    val (row, leftOvers) = entries.splitAt(index)
    val adjust = maxWidth / width
    if (adjust > 2.0 && leftOvers.isEmpty) {    // Hide the last entries
      row.foreach {
        case s => s.hide()
      }
      row.length
    } else {
      val newHeight = (preferredHeight * adjust).toInt
      row.foreach {
        case s => s.updateHeight(newHeight)
      }
//      println(s"Layout Rows: $index / $width of $maxWidth - Rows: ${row.length}, Preferred: $preferredHeight, Adjusted: $newHeight - Percentage: $adjust")
      if (leftOvers.isEmpty) {
        0         // Finished, used all
      } else {
        layoutRows(leftOvers)
      }
    }
  }

  def filledRow(entries: Vector[Sized], height: Int, maxWidth: Double) = {
    var index = 0
    var width = 0.0
    var filling = true
    entries.foreach {
      case s if filling => {
        val entryWidth = s.widthForHeight(height)
        if (width + entryWidth > maxWidth) {
          filling = false
        } else {
          index += 1
          width += entryWidth
        }
      }
      case s => // Ignore
    }
    index -> width
  }
}

class Sized(val preferredWidth: Int, val preferredHeight: Int) extends tag.Div {
  val ratio = preferredWidth.toDouble / preferredHeight.toDouble
//  val width = Property(default = Some(preferredWidth))
//  val height = Property(default = Some(preferredHeight))
//  height.change.on {
//    case evt => update()
//  }

  style.float := Float.Left

  def widthForHeight(height: Double) = height * ratio
  def updateHeight(height: Double) = {
    style.width := (height * ratio).px
    style.height := height.px
    style.display := Display.Block
  }
  def hide() = style.display := Display.None

//  private def update() = {
//    width := math.round(height() * ratio).toInt
//    println(s"New Size: ${width()} x ${height()}, Preferred: $preferredWidth x $preferredHeight")
//    style.width := width().px
//    style.height := height().px
//  }
}