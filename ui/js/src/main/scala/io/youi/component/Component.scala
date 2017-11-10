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
  /**
    * Generated unique identifier for this element.
    */
  lazy val id: Var[String] = Var(s"${`type`}.${Unique(length = 4, characters = Unique.Readable).toLowerCase}")

  /**
    * Parent to this element.
    */
  lazy val parent: Var[Option[Component]] = Var(None)

  // TODO: Val[Option[UI]]

  /**
    * List of `Updatable` instances derived from the `updatables` method.
    */
  private lazy val internalUpdatables: Val[List[Updatable]] = Val(updatables)

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
    * List of `Updatable` instances this Component represents.
    */
  protected def updatables: List[Updatable] = List(background(), border().paint)

  /**
    * Called automatically the first time this Component is connected to the document.
    */
  protected def init(): Unit = {}

  override def update(delta: Double): Unit = {
    super.update(delta)

    updateUpdatables(delta, internalUpdatables())
  }

  @tailrec
  private def updateUpdatables(delta: Double, updatables: List[Updatable]): Unit = if (updatables.nonEmpty) {
    updatables.head.update(delta)
    updateUpdatables(delta, updatables.tail)
  }

  override def toString: String = id()
}

object Component extends ComponentTheme