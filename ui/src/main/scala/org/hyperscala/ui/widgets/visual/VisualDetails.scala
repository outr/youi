package org.hyperscala.ui.widgets.visual

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait VisualDetails[T] {
  def clazz: Class[_]
  def valueType: Class[_]
  def selection: List[T]
  def masked: Boolean
}

case class VisualTypeDetails[T](clazz: Class[_],
                                valueType: Class[_] = null,
                                selection: List[T] = Nil,
                                masked: Boolean = false) extends VisualDetails[T]