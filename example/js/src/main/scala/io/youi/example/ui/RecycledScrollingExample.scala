package io.youi.example.ui

import io.youi.component.bootstrap.Button
import io.youi.component.recycled.{BatchedData, BetterRecycledScroller, RecycledRenderer, RecycledScroller}
import io.youi.{Color, Key}
import io.youi.component.{Container, HTMLTextInput, HTMLTextView}
import io.youi.example.screen.UIExampleScreen
import io.youi.net._
import io.youi.style.{HTMLBorder, HTMLBorderStyle, InputType, Position}
import reactify._

import scala.concurrent.Future

class RecycledScrollingExample extends UIExampleScreen {
  override def title: String = "Recycled Scrolling Example"
  override def path: Path = path"/examples/recycled-scrolling.html"

  private val one = BatchedData(List(0))
  private val five = BatchedData(List(0, 1, 2, 3, 4))
  private val oneHundred = BatchedData((0 until 100).toList)
  private val tenThousand = BatchedData((0 until 10000).toList)

  override def createUI(): Future[Unit] = {
    val scroller = new BetterRecycledScroller[Int, NumberComponent](NumberComponentRenderer, cacheSize = 10000, pageSize = 10000) {
      position.center := container.size.center
      position.middle := container.size.middle
      size.width @= 1000.0
      size.height @= 500.0
      background @= Color.LightGray
      batch.data @= tenThousand

      batch.position.attach { p =>
        scribe.info(s"Position set to $p")
      }
    }

    val inputSlider: HTMLTextInput = new HTMLTextInput {
      `type` @= InputType.Range
      min @= "0"
      max @= scroller.batch.data.total.toString
      position.left := scroller.position.left
      position.bottom := scroller.position.top
      size.width @= 200.0
      size.height @= 25.0

      var modifying = false
      scroller.batch.position.attachAndFire { p =>
        if (!modifying) {
          modifying = true
          value @= p.toString
          modifying = false
        }
      }

      value.attach { v =>
        if (!modifying) {
          modifying = true
          scroller.batch.position @= v.toInt
          modifying = false
        }
      }
    }
    val textTotal = new HTMLTextView {
      position.right := scroller.position.right
      position.bottom := scroller.position.top
      font.size @= 24.0
      value := s"of ${scroller.batch.data.total}"
    }
    val inputPosition = new HTMLTextInput {
      position.right := textTotal.position.left - 10.0
      position.bottom := scroller.position.top - 5.0
      size.width @= 50.0
      size.height @= 25.0
      scroller.batch.position.attachAndFire { p =>
        value @= p.toString
      }

      event.key.up.attach { evt =>
        if (evt.key == Key.Enter) {
          if (value().nonEmpty && value().forall(_.isDigit)) {
            scroller.batch.position.static(value().toInt)
          }
        }
      }
    }

    val setNone = new Button {
      value @= "Set None"
      position.left := scroller.position.left
      position.top := scroller.position.bottom + 10.0
      event.click.on {
        scroller.batch.data @= BatchedData.empty
      }
    }

    val set1 = new Button {
      value @= "Set 1"
      position.left := setNone.position.right + 10.0
      position.top := scroller.position.bottom + 10.0
      event.click.on {
        scroller.batch.data @= one
      }
    }

    val set5 = new Button {
      value @= "Set 5"
      position.left := setNone.position.right + 10.0
      position.top := scroller.position.bottom + 10.0
      event.click.on {
        scroller.batch.data @= five
      }
    }

    val set100 = new Button {
      value @= "Set 100"
      position.left := set5.position.right + 10.0
      position.top := scroller.position.bottom + 10.0
      event.click.on {
        scroller.batch.data @= oneHundred
      }
    }

    val set10k = new Button {
      value @= "Set 10,000"
      position.left := set100.position.right + 10.0
      position.top := scroller.position.bottom + 10.0
      event.click.on {
        scroller.batch.data @= tenThousand
      }
    }

    container.children ++= List(scroller, inputSlider, textTotal, inputPosition, setNone, set1, set5, set100, set10k)

    Future.successful(())
  }

  class NumberComponent extends Container {
    val value: Var[Int] = Var(0)

    position.`type` @= Position.Absolute
    size.width @= 1000.0
    size.height := 50.0 + (value() / 200.0)
    htmlBorder.radius @= 5.0
    htmlBorder @= HTMLBorder(1.0, HTMLBorderStyle.Dashed, Color.DarkRed)

    val label = new HTMLTextView
    label.position.left @= 20.0
    label.position.middle := size.middle
    label.value := s"Number: ${value() + 1} with extra text"
    label.font.size @= 42.0
    children += label
  }

  object NumberComponentRenderer extends RecycledRenderer[Int, NumberComponent] {
    override def createComponent: NumberComponent = new NumberComponent

    override def setData(data: Int, component: NumberComponent): Unit = component.value @= data

    override def getData(component: NumberComponent): Int = component.value()

    override def loading(component: NumberComponent): Unit = component.value @= -1
  }
}