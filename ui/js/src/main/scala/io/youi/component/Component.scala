package io.youi.component

import io.youi.component.extras.{ComponentPosition, ComponentSize}
import io.youi.event.Events
import io.youi.{MapStore, Store, Unique, Updatable}
import io.youi.task.TaskSupport
import io.youi.theme.ComponentTheme
import reactify._

import scala.annotation.tailrec

/**
  * Component represents the root type for all on-screen elements. This includes both HTML and Canvas.
  */
trait Component extends TaskSupport with ComponentTheme {
  private var _initialized: Boolean = false

  protected[component] def connect[T](v: Var[T], set: T => Unit, onChange: => Unit = ()): Var[T] = {
    set(v())
    v.attach { t =>
      set(t)
      onChange
    }
    v
  }

  lazy val store: Store = new MapStore

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
    * Events functionality for monitoring and even firing events on this component.
    */
  lazy val event: Events = new Events(this)

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

  object sibling {
    def previous(): Option[Component] = parent().flatMap { p =>
      val children = p.childComponents
      val index = children.indexOf(Component.this)
      if (index > 0) {
        Some(children(index - 1))
      } else {
        None
      }
    }

    def next(): Option[Component] = parent().flatMap { p =>
      val children = p.childComponents
      val index = children.indexOf(Component.this)
      if (index < children.size - 1) {
        Some(children(index + 1))
      } else {
        None
      }
    }
  }

  protected def measuredWidth: Double
  protected def measuredHeight: Double

  override def toString: String = id()
}

object Component extends ComponentTheme {
  def childrenFor(component: Component): Vector[Component] = component.childComponents

  object measured {
    def width(component: Component): Double = component.measuredWidth
    def height(component: Component): Double = component.measuredHeight
  }
}