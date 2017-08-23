package io.youi.example.ui.hypertext

import io.youi.{Color, hypertext}
import io.youi.hypertext.border.BorderStyle
import io.youi.hypertext.layout.GridLayout
import io.youi.hypertext.{Component, Container}
import io.youi.spatial.Point
import org.scalajs.dom._

import scala.concurrent.Future

object SelectionExample extends HTMLScreen {
  override def name: String = "Selection Example"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val boxes = Color.all.take(60).zipWithIndex.map {
      case (color, index) => createBox(s"Box $index", color)
    }

    val layoutContainer = new Container {
      layoutManager := Some(new GridLayout(columns = 3, xOffset = 15.0, yOffset = 15.0, verticalPadding = 15.0, horizontalPadding = 15.0))

      position.left := 300.0

      boxes.foreach(children += _)
    }
    container.children += layoutContainer

    val boxesSet = boxes.toSet
    val selection = new hypertext.Selection(document.body, boxesSet) {
      override def pointFor(box: Component, point: Point): Point = {
        val rect = box.element.getBoundingClientRect()
        val cx = rect.left + (rect.width / 2.0)
        val cy = rect.top + (rect.height / 2.0)
        point.set(cx, cy)
      }

      override def added(element: Component): Unit = {
        super.added(element)

        element.border.color := Some(Color.HotPink)
      }

      override def removed(element: Component): Unit = {
        super.removed(element)

        element.border.color := Some(Color.Black)
      }
    }
    selection.current.attach { selected =>
      scribe.info(s"Selected: ${selected.map(_.id()).mkString(", ")}")
    }
    container.children += selection
  }

  override protected def activate() = super.activate().map { _ =>
    document.body.style.overflowY = "auto"
  }

  override protected def deactivate() = super.deactivate().map { _ =>
    document.body.style.overflowY = "hidden"
  }

  private def createBox(name: String, c: Color): Component = new Container {
    id := name
    size.width := 350.0
    size.height := 250.0
    backgroundColor := c
    border.color := Some(Color.Black)
    border.size := Some(2.0)
    border.style := Some(BorderStyle.Solid)
    border.radius := 5.0
  }

  override def path: String = "/examples/html/selection.html"
}