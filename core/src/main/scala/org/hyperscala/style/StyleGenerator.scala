package org.hyperscala.style

import io.Source

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object StyleGenerator extends CSSHierarchy(null, null) {
  implicit val ordering = new Ordering[CSSHierarchy] {
    def compare(x: CSSHierarchy, y: CSSHierarchy) = x.name.compare(y.name)
  }

  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("cssproperties.txt")
    source.getLines().foreach(processLine)
    children.values.toList.sorted.foreach(h => generate(h))
  }

  def processLine(line: String) = {
    val propertyName = line.substring(0, line.indexOf(' '))
    val entries = propertyName.split('-')
    var hierarchy: CSSHierarchy = this
    for (entry <- entries) {
      hierarchy = hierarchy.children.get(entry) match {
        case None => {
          val newHierarchy = new CSSHierarchy(entry, hierarchy)
          hierarchy.children += entry -> newHierarchy
          newHierarchy
        }
        case Some(h) => h
      }
    }
    hierarchy.isProperty = true
  }

  def generate(hierarchy: CSSHierarchy): Unit = {
    val name = hierarchy.name.replaceAll("@", "") match {
      case "type" => hierarchy.parent.name + "Type"
      case s => s
    }
    val cssName = hierarchy.cssName
    if (hierarchy.isProperty && hierarchy.children.isEmpty) {
      println("val %s = p[String](\"%s\")".format(name, cssName))
    } else {
      if (hierarchy.isProperty) {
        println("object %s extends StyleProperty[String](\"%s\") {".format(name, cssName))
      } else {
        println("object %s {".format(name))
      }
      for (child <- hierarchy.children.values.toList.sorted) {
        generate(child)
      }
      println("}")
    }
  }
}

// animation 	A shorthand property for all the animation properties below, except the animation-play-state property 	3

class CSSHierarchy(val name: String, val parent: CSSHierarchy) {
  var isProperty: Boolean = false
  var children = Map.empty[String, CSSHierarchy]

  def cssName: String = parent match {
    case null => name
    case _ if (parent.cssName == null) => name
    case p => "%s-%s".format(p.cssName, name)
  }
}