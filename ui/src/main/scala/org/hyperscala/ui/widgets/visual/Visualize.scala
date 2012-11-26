package org.hyperscala.ui.widgets.visual

import org.powerscala.reflect._
import org.powerscala.property._

import org.hyperscala.html._
import org.hyperscala.css.attributes.Clear

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class Visualize(_labeled: Boolean = true,
                     _required: Boolean = false,
                     _editable: Boolean = true,
                     _editing: Boolean = false,
                     _fields: List[VisualBuilder[_]] = Nil) {
  def clazz[T](className: String = null,
               bindProperty: StandardProperty[_] = null,
               basePath: String = null,
               valueUpdatesProperty: Boolean = true,
               propertyUpdatesValue: Boolean = true,
               group: String = null)(implicit manifest: Manifest[T]): Visualize = {
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
        instance = instance.clazz[Any](name, bindProperty, hierarchy, valueUpdatesProperty, propertyUpdatesValue, cv.name)(Manifest.classType[Any](cv.valueType.javaClass))
      } else {
        instance = instance.caseValue(name, cv, bindProperty, hierarchy, valueUpdatesProperty, propertyUpdatesValue, group)
      }
    })
    instance
  }

  def caseValue(name: String,
                cv: CaseValue,
                bindProperty: StandardProperty[_] = null,
                hierarchy: String = null,
                valueUpdatesProperty: Boolean = true,
                propertyUpdatesValue: Boolean = true,
                group: String = null): Visualize = {
    val builder = Visual[Any]()(Manifest.classType[Any](cv.valueType.javaClass))
                    .name(name)
                    .label(cv.label)
                    .labeled(_labeled)
                    .required(_required)
                    .editable(_editable)
                    .editing
                    .bind(bindProperty, hierarchy, valueUpdatesProperty, propertyUpdatesValue)
                    .group(group)
    field[Any](builder)
  }

  def field[T](builder: VisualBuilder[T]): Visualize = copy(_fields = (builder :: _fields.reverse).reverse)

  def field[T](name: String)(modifier: VisualBuilder[T] => VisualBuilder[T]): Visualize = {
    val original = _fields.find(vb => vb.name == name).getOrElse(throw new NullPointerException("Unable to find field by name: %s".format(name))).asInstanceOf[VisualBuilder[T]]
    val modified = modifier(original)
    copy(_fields = _fields.map(vb => if (vb.name == original.name) modified else vb))
  }

  def groups() = _fields.collect {
    case vb if (vb.group != null) => vb.group
  }.distinct

  def group(name: String) = _fields.collect {
    case vb if (vb.group == name) => vb
  }

  def renameGroup(currentName: String, newName: String) = {
    copy(_fields = _fields.map(vb => if (vb.group == currentName) vb.group(newName) else vb))
  }

  def build() = new tag.Div with Visualized {
    val groupNames = Visualize.this.groups()
    val visuals = _fields.map(vb => vb.build())
    val map = (_fields zip visuals).toMap
    val groups = groupNames.map(name => name -> buildGroup(name, group(name).map(vb => map(vb)))).toMap

    private var unusedGroups = groupNames.toSet
    _fields.foreach {
      case vb if (vb.group == null) => {
        contents += map(vb)
      }
      case vb if (unusedGroups.contains(vb.group)) => {
        contents += groups(vb.group)
        unusedGroups -= vb.group
      }
      case _ => // Group already used
    }
  }

  def buildGroup(groupName: String, fields: List[Visual[_]]) = new tag.FieldSet with Visualized {
    style.clear := Clear.Both

    val group = groupName
    val visuals = fields

    contents += new tag.Legend(content = "%s:".format(groupName))
    contents.addAll(visuals: _*)
  }
}

trait Visualized {
  def visuals: List[Visual[_]]
  def visual(name: String) = visuals.find(v => name == v.name())
}