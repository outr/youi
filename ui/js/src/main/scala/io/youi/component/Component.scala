package io.youi.component

import io.youi.{Unique, Updatable}
import io.youi.task.TaskSupport
import io.youi.theme.ComponentTheme
import reactify._

import scala.annotation.tailrec

/**
  * Component represents the root type for all on-screen elements. This includes both HTML and Canvas.
  */
trait Component extends TaskSupport with ComponentTheme {
  private var _initialized: Boolean = false

  /**
    * Generated unique identifier for this element.
    */
  lazy val id: Var[String] = Var(s"${`type`}.${Unique(length = 4, characters = Unique.Readable).toLowerCase}")

  /**
    * Parent to this element.
    */
  lazy val parent: Var[Option[Component]] = Var(None)

  /**
    * List of `Updatable` instances derived from the `updatables` method.
    */
  private lazy val internalUpdatables: Val[List[Updatable]] = Val(updatables)

  /**
    * Position information for placement of this component on the screen.
    */
  lazy val position: ComponentPosition = new ComponentPosition(this)

  /**
    * Size information for determining the dimensions of this component.
    */
  lazy val size: ComponentSize = new ComponentSize(this)

  /**
    * Theme associated with this Component.
    */
  def theme: Var[_ <: ComponentTheme]

  /**
    * The type of Component. This is useful for client-side introspection and logging. Each custom Component instance
    * should represent a unique `type`.
    */
  def `type`: String

  /**
    * True if this Component's `init` method has been invoked.
    */
  def initialized: Boolean = _initialized

  /**
    * List of `Updatable` instances this Component represents.
    */
  protected def updatables: List[Updatable] = Nil

  /**
    * Called automatically the first time this Component is connected to the document.
    */
  protected def init(): Unit = {}

  override def update(delta: Double): Unit = {
    // Make sure we initialize before we do anything else
    if (!initialized) {
      init()
      _initialized = true
    }

    super.update(delta)

    updateUpdatables(delta, internalUpdatables())
  }

  @tailrec
  private def updateUpdatables(delta: Double, updatables: List[Updatable]): Unit = if (updatables.nonEmpty) {
    updatables.head.update(delta)
    updateUpdatables(delta, updatables.tail)
  }

  protected def childComponents: Vector[Component] = Vector.empty

  override def toString: String = id()
}

object Component extends ComponentTheme {
  def childrenFor(component: Component): Vector[Component] = component.childComponents
}

class ComponentPosition(component: Component) {
  lazy val x: Var[Double] = component.prop(0.0, updatesTransform = true)
  lazy val y: Var[Double] = component.prop(0.0, updatesTransform = true)

  lazy val left: Var[Double] = x
  lazy val center: Dep[Double, Double] = Dep(left, component.size.width / 2.0)
  lazy val right: Dep[Double, Double] = Dep(left, component.size.width)

  lazy val top: Var[Double] = y
  lazy val middle: Dep[Double, Double] = Dep(top, component.size.height / 2.0)
  lazy val bottom: Dep[Double, Double] = Dep(top, component.size.height)
}

class ComponentSize(component: Component) {
  object measured {
    val width: Var[Double] = component.prop(0.0, updatesRendering = true)
    val height: Var[Double] = component.prop(0.0, updatesRendering = true)
  }

  def reset(width: Boolean = true, height: Boolean = true): Unit = {
    if (width) this.width.set(measured.width())
    if (height) this.height.set(measured.height())
  }

  lazy val width: Var[Double] = component.prop(measured.width, updatesRendering = true)
  lazy val height: Var[Double] = component.prop(measured.height, updatesRendering = true)

  lazy val center: Val[Double] = Val(width / 2.0)
  lazy val middle: Val[Double] = Val(height / 2.0)
}