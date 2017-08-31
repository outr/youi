package io.youi.designer

import com.outr.psd.PSD
import org.scalajs.dom._

import scala.scalajs._
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.JSON

object Test extends js.JSApp {
  @JSExportTopLevel("application")
  def main(): Unit = {
    println("PSD import tester...")
    val dropZone = document.getElementById("dropzone")
    dropZone.addEventListener("dragover", (evt: DragEvent) => {
      evt.stopPropagation()
      evt.preventDefault()
      evt.dataTransfer.dropEffect = "copy"
    }, useCapture = true)

    val canvas = document.createElement("canvas").asInstanceOf[html.Canvas]

    dropZone.addEventListener("drop", (evt: DragEvent) => {
      evt.stopPropagation()
      evt.preventDefault()
      val file = evt.dataTransfer.files.item(0)
      PSD.fromFile(file).toFuture.foreach { psd =>
        try {
          println(s"Processing ${file.name}...")
          val tree = psd.tree()
          val image = document.getElementById("image")
          image.appendChild(psd.image.toPng())

          val data = document.getElementById("data")
          data.innerHTML = JSON.stringify(tree.export(), space = 2)

          val images = document.getElementById("images")
          tree.descendants().toList.foreach { node =>
            if (node.isGroup()) {
              val div = document.createElement("div").asInstanceOf[html.Div]
              div.innerHTML += s"<h3>${node.name} (group)</h3>"
              images.appendChild(div)
            } else {
              val div = document.createElement("div").asInstanceOf[html.Div]
              div.innerHTML += s"<h3>${node.name}</h3>"
              val export = node.export()
              val png = node.toPng()
              export.text.toOption match {
                case Some(text) => {
                  div.innerHTML += s"""<h4><b>Text:</b> "${text.value}" (${text.font.name}, ${text.font.sizes}, ${text.font.colors}, ${text.font.alignment})</h4>"""
                }
                case None => {
                  png.addEventListener("load", (evt: Event) => {
                    canvas.width = png.width
                    canvas.height = png.height
                    val ctx = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
                    ctx.drawImage(png, 0, 0, png.width, png.height)
                    val imageData = ctx.getImageData(0, 0, png.width, png.height)

                    val colors = imageData.data.toIterable.grouped(4).map { i =>
                      val v = i.toVector
                      Pixel(v(0), v(1), v(2), v(3))
                    }.toSet

                    val text = if (colors.size < 10) {
                      s"${colors.mkString(", ")} (${colors.size})"
                    } else {
                      colors.size.toString
                    }
                    div.innerHTML += s"""<h4><b>Colors:</b> $text</h4>"""
                  })
                }
              }
              div.appendChild(png)
              images.appendChild(div)
            }
          }
        } catch {
          case t: Throwable => t.printStackTrace()
        }
      }
    }, useCapture = true)
  }
}

case class Pixel(red: Int, green: Int, blue: Int, alpha: Int) {
  override def toString: String = s"Pixel(red: $red, green: $green, blue: $blue, alpha: $alpha)"
}