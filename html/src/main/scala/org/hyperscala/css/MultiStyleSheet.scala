package org.hyperscala.css

import org.powerscala.property.WriteProperty

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class MultiStyleSheet(styleSheets: List[StyleSheet]) extends WriteProperty[StyleAssignment[_]] {
  override def apply(assignment: StyleAssignment[_]): Unit = update[Any](assignment.style.asInstanceOf[Style[Any]], assignment.value.asInstanceOf[Any])

  def apply[T](style: Style[T]): Option[T] = if (styleSheets.nonEmpty) {
    val value = styleSheets.head.get(style)
    if (styleSheets.forall(ss => ss.get(style) == value)) {
      value
    } else {
      None
    }
  } else {
    None
  }

  def update[T](style: Style[T], value: T) = {
    styleSheets.foreach(ss => ss.update(style, value))
  }

  override def toString() = s"MultiStyleSheet(${styleSheets.mkString(", ")})"
}

object MultiStyleSheet {
  def apply(styleSheets: StyleSheet*): MultiStyleSheet = apply(styleSheets.toList)
}