package org.hyperscala.ui.widgets.visual

import org.powerscala.reflect._
import org.powerscala.property.StandardProperty

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class Visualize(_labeled: Boolean = true,
                     _required: Boolean = false,
                     _editable: Boolean = true,
                     _editing: Boolean = false,
                     _fields: List[VisualBuilder[_]] = Nil) {

  // TODO: support groupings
  def clazz[T](className: String = null,
               bindProperty: StandardProperty[_] = null,
               basePath: String = null,
               valueUpdatesProperty: Boolean = true,
               propertyUpdatesValue: Boolean = true)(implicit manifest: Manifest[T]): Visualize = {
    val cn = className match {
      case null => manifest.erasure.getSimpleName
      case _ => className
    }
    var instance = this
    manifest.erasure.caseValues.map(cv => {
      val name = "%s.%s".format(cn, cv.name)
      val hierarchy = if (basePath == null) {
        cv.name
      } else {
        "%s.%s".format(basePath, cv.name)
      }
      if (cv.valueType.isCase) {
        instance = instance.clazz[Any](null, bindProperty, hierarchy, valueUpdatesProperty, propertyUpdatesValue)
      } else {
        instance = instance.caseValue(name, cv, bindProperty, hierarchy, valueUpdatesProperty, propertyUpdatesValue)
      }
    })
    instance
  }

  def caseValue(name: String,
                cv: CaseValue,
                bindProperty: StandardProperty[_] = null,
                hierarchy: String = null,
                valueUpdatesProperty: Boolean = true,
                propertyUpdatesValue: Boolean = true): Visualize = {
    val builder = Visual[Any]()(Manifest.classType[Any](cv.valueType.javaClass))
                    .name(name)
                    .label(cv.label)
                    .labeled(_labeled)
                    .required(_required)
                    .editable(_editable)
                    .editing
                    .bind(bindProperty, hierarchy, valueUpdatesProperty, propertyUpdatesValue)
    field[Any](builder)
  }

  def field[T](builder: VisualBuilder[T]): Visualize = copy(_fields = (builder :: _fields.reverse).reverse)

  def field[T](name: String)(modifier: VisualBuilder[T] => VisualBuilder[T]): Visualize = {
    val original = _fields.find(vb => vb.name == name).getOrElse(throw new NullPointerException("Unable to find field by name: %s".format(name))).asInstanceOf[VisualBuilder[T]]
    val modified = modifier(original)
    copy(_fields = _fields.map(vb => if (vb.name == original.name) modified else vb))
  }

  def build() = new tag.Div with Visualized {
    val visuals = _fields.map(vb => vb.build())

    contents.addAll(visuals: _*)
  }
}

trait Visualized {
  def visuals: List[Visual[_]]
  def visual(name: String) = visuals.find(v => name == v.name())
}