package org.hyperscala.persistence

import org.hyperscala.css.{StyleSheetAttribute, StyleSheet}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object StyleSheetPersistence extends ValuePersistence[StyleSheet] {
  def fromString(s: String, clazz: Class[_]) = if (s.trim.length > 0) {
    val ss = new StyleSheet()
    apply(ss, s)
    ss
  } else {
    null
  }

  def toString(t: StyleSheet, clazz: Class[_]) = t.properties.collect {
    case p: StyleSheetAttribute[_] if (p.modified) => "%s: %s".format(p.name(), p.attributeValue)
  }.mkString("; ")

  def apply(ss: StyleSheet, s: String) = {
    s.split(";").map(s => s.split(":")).map(a => a(0).trim -> a(1).trim).foreach {
      case (key, value) => ss.properties.get(key) match {
        case Some(p) => {
          p.asInstanceOf[StyleSheetAttribute[_]].attributeValue = value
        }
        case None => throw new RuntimeException("No support for custom StyleSheet attributes: [%s] = [%s]".format(key, value))
      }
    }
  }
}
