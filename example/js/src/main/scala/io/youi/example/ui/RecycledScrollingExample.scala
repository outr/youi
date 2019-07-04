package io.youi.example.ui

import io.youi.Color
import io.youi.component.{Component, Container, HTMLTextView}
import io.youi.example.screen.UIExampleScreen
import io.youi.net._
import io.youi.style.{Display, HTMLBorder, HTMLBorderStyle, Visibility}
import reactify.Var

import scala.concurrent.Future
import scribe.Execution.global

class RecycledScrollingExample extends UIExampleScreen {
  override def title: String = "Recycled Scrolling Example"
  override def path: Path = path"/examples/recycled-scrolling.html"

  override def createUI(): Future[Unit] = {
    val scroller = new RecycledScroller[Int, NumberComponent](NumberComponentRenderer)
    scroller.position.center := container.size.center
    scroller.position.middle := container.size.middle
    scroller.size.width := 1000.0
    scroller.size.height := 900.0
    scroller.background := Color.LightGray
    scroller.batchedData := BatchedData((0 until 5000).toList)
    container.children += scroller

    Future.successful(())
  }

  class NumberComponent extends Container {
    val value: Var[Int] = Var(0)

    size.width := parent().map(_.size.width()).getOrElse(0.0)
    size.height := 50.0
    htmlBorder.radius := 5.0
    htmlBorder := HTMLBorder(1.0, HTMLBorderStyle.Dashed, Color.DarkRed)

    val label = new HTMLTextView
    label.position.left := 20.0
    label.position.middle := size.middle
    label.value := s"Number: ${value()} with lots of extra text"
    label.font.size := 42.0
    children += label
  }

  object NumberComponentRenderer extends RecycledRenderer[Int, NumberComponent] {
    override def createComponent: NumberComponent = new NumberComponent

    override def setData(data: Int, component: NumberComponent): Unit = component.value := data
  }
}

class RecycledScroller[T, C <: Component](renderer: RecycledRenderer[T, C]) extends Container {
  val batchedData: Var[BatchedData[T]] = Var(BatchedData.empty[T])

  private var pool = List.empty[C]
  private def acquire: C = pool.headOption match {
    case Some(c) => {
      pool = pool.tail
      c
    }
    case None => renderer.createComponent
  }
  private def release(c: C): Unit = {
    pool = c :: pool
  }

  batchedData.attachAndFire { data =>
    if (data.nonEmpty) {
      data.get(0, 1).map { list =>
        val test = acquire
        test.position.bottom := size.height
        renderer.setData(list.head, test)
        children += test
      }
    }
  }
}

trait RecycledRenderer[T, C <: Component] {
  def createComponent: C
  def setData(data: T, component: C): Unit
  def show(component: C): Unit = component.display := Display.Block
  def hide(component: C): Unit = component.display := Display.None
}

trait BatchedData[T] {
  def total: Int
  def isEmpty: Boolean = total == 0
  def nonEmpty: Boolean = total > 0
  def get(start: Int, end: Int): Future[List[T]]
}

object BatchedData {
  def empty[T]: BatchedData[T] = new BatchedData[T] {
    override def total: Int = 0

    override def get(start: Int, end: Int): Future[List[T]] = throw new RuntimeException("Cannot get data from empty batch")
  }

  def apply[T](data: Seq[T]): BatchedData[T] = new BatchedData[T] {
    override def total: Int = data.length

    override def get(start: Int, end: Int): Future[List[T]] = Future.successful(data.slice(start, end).toList)
  }
}