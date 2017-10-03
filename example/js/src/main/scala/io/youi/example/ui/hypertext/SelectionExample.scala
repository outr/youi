package io.youi.example.ui.hypertext

import io.youi.{Color, hypertext}
import io.youi.hypertext.border.BorderStyle
import io.youi.hypertext.{Component, Container}
import io.youi.layout.GridLayout
import io.youi.spatial.BoundingBox
import org.scalajs.dom._

import scala.collection.immutable.ListSet
import scala.concurrent.Future

object SelectionExample extends HTMLScreen {
  override def name: String = "Selection Example"

  private var selection: hypertext.Selection[Component] = _

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val boxes = Color.all.take(60).zipWithIndex.map {
      case (color, index) => createBox(s"Box $index", color)
    }

    val layoutContainer = new Container {
      layout := new GridLayout {
        columns := 3
        config.default.margin.left := Some(15.0)
        config.default.margin.top := Some(15.0)
      }

      position.left := 300.0

      boxes.foreach(children += _)
    }
    container.children += layoutContainer

    val boxesSet = ListSet(boxes: _*)
    selection = new hypertext.Selection(document.body, boxesSet, includeChildTargets = true) {
      override def boxFor(element: Component): BoundingBox = {
        val rect = element.element.getBoundingClientRect()
        BoundingBox(rect.left, rect.top, rect.right, rect.bottom)
      }

      override def highlightAdded(element: Component): Unit = {
        super.highlightAdded(element)

        element.border.color := Some(Color.HotPink)
      }

      override def highlightRemoved(element: Component, isSelected: Boolean): Unit = {
        super.highlightRemoved(element, isSelected)

        if (isSelected) {
          element.border.color := Some(Color.Blue)
        } else {
          element.border.color := Some(Color.Black)
        }
      }

      override def selectionAdded(element: Component): Unit = {
        super.selectionAdded(element)

        element.border.color := Some(Color.Blue)
      }

      override def selectionRemoved(element: Component): Unit = {
        super.selectionRemoved(element)

        element.border.color := Some(Color.Black)
      }
    }
    selection.selected.attach { selected =>
      scribe.info(s"Selected: ${selected.map(_.id()).mkString(", ")}")
    }
    container.children += selection
  }

  override protected def activate() = super.activate().map { _ =>
    document.body.style.overflowY = "auto"
    selection.enabled := true
  }

  override protected def deactivate() = super.deactivate().map { _ =>
    document.body.style.overflowY = "hidden"
    selection.enabled := false
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