package io.youi.gui

import org.scalajs.dom.html
import org.scalajs.dom.html.Element
import reactify.Var
import scribe.Priority

import scala.annotation.tailrec

class Component(val element: html.Element) {
  lazy val id: Var[String] = prop[String](element.id, element.id_=)

  protected def prop[T](get: => T, set: T => Unit): Var[T] = {
    val v = Var[T](get)
    v.attach(set)
    v
  }
}

object Component {
  private var creators: List[ComponentCreator] = Nil

  register(ComponentCreator.DefaultComponentCreator)

  @tailrec
  final def createFor(element: html.Element,
                      creators: List[ComponentCreator] = creators): Component = if (creators.isEmpty) {
    throw new RuntimeException(s"No creator found for element: ${element.nodeName}")
  } else {
    val creator = creators.head
    creator.createFor(element) match {
      case Some(c) => c
      case None => createFor(element, creators.tail)
    }
  }

  def register(creator: ComponentCreator): Unit = {
    creators = (creator :: creators).sorted
  }

  def clear(): Unit = creators = Nil
}

trait ComponentCreator extends Ordering[ComponentCreator] {
  def priority: Priority = Priority.Normal

  def createFor(element: html.Element): Option[Component]

  override def compare(x: ComponentCreator, y: ComponentCreator): Int = x.priority.compareTo(y.priority)
}

object ComponentCreator {
  case object DefaultComponentCreator extends ComponentCreator {
    override def priority: Priority = Priority.Lowest

    override def createFor(element: Element): Option[Component] = Some(new Component(element))
  }
}