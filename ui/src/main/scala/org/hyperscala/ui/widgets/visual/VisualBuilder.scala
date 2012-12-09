package org.hyperscala.ui.widgets.visual

import `type`.VisualType
import org.powerscala.property.StandardProperty

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class VisualBuilder[T](_name: String = null,
                            _labeled: Boolean = true,
                            _label: String = null,
                            _required: Boolean = false,
                            _editable: Boolean = true,
                            _editing: Boolean = false,
                            _selection: List[T] = null,
                            _nullAllowed: Boolean = true,
                            _default: Option[T] = None,
                            _itemizedType: VisualBuilder[_] = null,
                            _masked: Boolean = false,
                            _multiLine: Boolean = false,
                            _validations: List[T => Either[Option[T], String]] = Nil,
                            _validateOnChange: Boolean = false,
                            _visualType: Option[VisualType[T]] = None,
                            _group: String = null,
                            _bindProperty: StandardProperty[_] = null,
                            _bindHierarchy: String = null,
                            bindValueUpdatesProperty: Boolean = true,
                            bindPropertyUpdatesValue: Boolean = true)
                           (implicit val manifest: Manifest[T]) {
  def clazz = manifest.erasure

  def name = _name
  def name(_name: String) = copy(_name = _name)
  def labeled = _labeled
  def labeled(l: Boolean) = copy(_labeled = l)
  def label = _label
  def label(l: String) = copy(_label = l, _labeled = true)
  def isRequired = _required
  def required = copy(_required = true)
  def required(_required: Boolean) = copy(_required = _required)
  def editable = _editable
  def editable(e: Boolean) = copy(_editable = e)
  def isEditing = _editing
  def editing(e: Boolean) = copy(_editing = e)
  def selection = _selection
  def selection(s: List[T]) = copy(_selection = s)
  def nullAllowed = _nullAllowed
  def nullAllowed(b: Boolean) = copy(_nullAllowed = b)
  def default = _default
  def default(d: T) = copy(_default = Some(d))
  def itemizedType = _itemizedType
  def itemizedType[V](f: VisualBuilder[V] => VisualBuilder[V] = (vb: VisualBuilder[V]) => vb)
                     (implicit itemManifest: Manifest[V]): VisualBuilder[T] = {
    val original = Visual[V]()(itemManifest)
    copy(_itemizedType = f(original))
  }
  def mask = masked(m = true)
  def masked = _masked
  def masked(m: Boolean) = copy(_masked = m)
  def multiLine = _multiLine
  def multiLine(b: Boolean) = copy(_multiLine = b)
  def validation(v: T => Either[Option[T], String]) = copy(_validations = (v :: _validations.reverse).reverse)
  def validations = _validations
  def validations(v: List[T => Either[Option[T], String]]) = copy(_validations = v)
  def validateOnChange = _validateOnChange
  def validateOnChange(b: Boolean) = copy(_validateOnChange = b)
  def visualType = _visualType
  def visualType(v: VisualType[T]) = copy(_visualType = Some(v))
  def group = _group
  def group(_group: String) = copy(_group = _group)
  def bindProperty = _bindProperty
  def bindHierarchy = _bindHierarchy
  def bind(b: StandardProperty[_], hierarchy: String, valueUpdatesProperty: Boolean = true, propertyUpdatesValue: Boolean = true) = {
    copy(_bindProperty = b,
         _bindHierarchy = hierarchy,
         bindValueUpdatesProperty = valueUpdatesProperty,
         bindPropertyUpdatesValue = propertyUpdatesValue)
  }

  def build() = new StandardVisual[T](this)
}