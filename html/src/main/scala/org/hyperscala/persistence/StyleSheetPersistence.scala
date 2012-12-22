package org.hyperscala.persistence



/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
/*object StyleSheetPersistence extends ValuePersistence[Map[String, Style[_]]] {
  def fromString(s: String, clazz: Class[_]) = if (s.trim.length > 0) {
    val ss = new StyleSheet()
    apply(ss, s)
    ss
  } else {
    null
  }

  def toString(t: StyleSheet, clazz: Class[_]) = t.properties.collect {
    case p: StyleSheetAttribute[_] if (p.modified) => {
      val option = Page() match {
        case null => Some(p)
        case page => page.intercept.renderAttribute.fire(p)
      }
      option.map(pa => "%s: %s".format(p.name(), p.attributeValue))
    }
  }.flatten.mkString("; ")

  def apply(ss: StyleSheet, s: String) = {
    try {
      s.split(";").map(s => s.split(":")).map(a => a(0).trim -> a(1).trim).foreach {
        case (key, value) => ss.properties.get(key) match {
          case Some(p) => {
            p.asInstanceOf[StyleSheetAttribute[_]].attributeValue = value
          }
          case None => throw new RuntimeException("No support for custom StyleSheet attributes: [%s] = [%s]".format(key, value))
        }
      }
    } catch {
      case t: Throwable => System.err.println("Error parsing CSS: [%s]".format(s))
    }
  }
}*/
