package io.youi.component

import io.youi.component.extras.{ComponentPosition, ComponentSize}
import io.youi.event.{EventSupport, Events, HTMLEvents}
import io.youi.spatial.{MutableSize, Size}
import io.youi.style.Visibility
import io.youi.{MapStore, Store, Unique, Updatable}
import io.youi.task.TaskSupport
import io.youi.theme.{ComponentTheme, Theme}
import reactify._

import scala.annotation.tailrec
import scala.concurrent.duration._

/**
  * Component represents the root type for all on-screen elements. This includes both HTML and Canvas.
  */
trait Component extends TaskSupport with ComponentTheme {
  private var _initialized: Boolean = false

  lazy val store: Store = new MapStore

  private lazy val transform = rateLimited(10.millis)(updateTransform())
  private lazy val rendering = rateLimited(10.millis)(updateRendering())

  override def invalidateTransform(): Unit = transform.flag()

  override def invalidateRendering(): Unit = rendering.flag()

  override def updateTasks(): Boolean = visible()

  /**
    * Position information for placement of this component on the screen.
    */
  def position: ComponentPosition

  /**
    * Size information for determining the dimensions of this component.
    */
  def size: ComponentSize

  /**
    * Generated unique identifier for this element.
    */
  lazy val id: Var[String] = Var(s"$componentType-${Unique(length = 4, characters = Unique.Readable).toLowerCase}")

  /**
    * Parent to this element.
    */
  lazy val parent: Var[Option[Component]] = Var(None)

  lazy val root: Var[Option[Component]] = Var(parent().flatMap(_.root()))

  /**
    * Defines if this component should be managed by layout managers (defaults to true)
    */
  val includeInLayout: Var[Boolean] = Var(true)

  /**
    * Value determining the visibility of this element based on multiple criteria
    */
  val visible: Val[Boolean] = Val(visibility() == Visibility.Visible && parent().exists(_.visible()))

  /**
    * List of `Updatable` instances derived from the `updatables` method.
    */
  private lazy val internalUpdatables: Val[List[Updatable]] = Val(updatables)

  invalidateTransform()

  override protected def defaultParentTheme: Theme = Component

  /**
    * Events functionality for monitoring and even firing events on this component.
    */
  def event: EventSupport

  /**
    * The type of Component. This is useful for client-side introspection and logging. Each custom Component instance
    * should represent a unique `type`.
    */
  def componentType: String

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

  protected val measuredSize: MutableSize = Size.mutable()
  override def updateTransform(): Unit = {
    super.updateTransform()

    measure(measuredSize)
    size.measured.width.static(measuredSize.width)
    size.measured.height.static(measuredSize.height)
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

  protected def measure(size: Size): Size

  def hasParent(parent: Component): Boolean = this.parent() match {
    case Some(p) => if (parent == p) {
      true
    } else {
      p.hasParent(parent)
    }
    case None => false
  }

  override def toString: String = id()
}

object Component extends ComponentTheme {
  override protected def defaultParentTheme: Theme = Theme

  def childrenFor(component: Component): Vector[Component] = component.childComponents
}