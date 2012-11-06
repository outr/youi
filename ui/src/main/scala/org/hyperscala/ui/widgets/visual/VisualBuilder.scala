package org.hyperscala.ui.widgets.visual

import `type`.VisualType
import org.powerscala.property.StandardProperty

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class VisualBuilder[T](_labeled: Boolean = true,
                            _label: String = null,
                            _editable: Boolean = true,
                            _editing: Boolean = false,
                            _selection: List[T] = Nil,
                            _default: Option[T] = None,
                            _valueType: Class[_] = null,
                            _masked: Boolean = false,
                            _validations: List[T => Option[T]] = Nil,
                            _visualType: Option[VisualType[T]] = None,
                            _bindProperty: StandardProperty[_] = null,
                            _bindHierarchy: String = null)
                           (implicit val manifest: Manifest[T]) extends VisualDetails[T] {
  def clazz = manifest.erasure

  def labeled = _labeled
  def labeled(l: Boolean) = copy(_labeled = l)
  def label = _label
  def label(l: String) = copy(_label = l, _labeled = true)
  def editable = _editable
  def editable(e: Boolean) = copy(_editable = e)
  def isEditing = _editing
  def editing = copy(_editing = true, _editable = true)
  def selection = _selection
  def selection(s: List[T]) = copy(_selection = s)
  def default = _default
  def default(d: T) = copy(_default = Some(d))
  def valueType = _valueType
  def valueType(v: Class[_]) = copy(_valueType = v)
  def masked = _masked
  def masked(m: Boolean) = copy(_masked = m)
  def validations = _validations
  def validations(v: List[T => Option[T]]) = copy(_validations = v)
  def visualType = _visualType
  def visualType(v: VisualType[T]) = copy(_visualType = Some(v))
  def bindProperty = _bindProperty
  def bindProperty(b: StandardProperty[T]) = copy(_bindProperty = b)
  def bindHierarchy = _bindHierarchy
  def bindHierarchy(b: String) = copy(_bindHierarchy = b)

  def build() = new StandardVisual[T](this)
}