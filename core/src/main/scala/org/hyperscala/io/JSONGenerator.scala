package org.hyperscala.io

import org.objectweb.asm.{ClassReader, Type}
import org.objectweb.asm.tree.{LocalVariableNode, MethodNode, ClassNode}
import org.hyperscala.tags.{Div, Tag}

import scala.collection.JavaConversions._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object JSONGenerator {
  val exclusions = List("accessKey", "clazz", "contextEditable", "contextMenu", "dir", "draggable", "dropZone", "hidden", "id", "lang", "spellCheck", "style", "tabIndex", "title", "content")

  def main(args: Array[String]): Unit = {
    val b = new StringBuilder()
    b.append("{\r\n")
    applyStyleSheet(b)
    b.append("\t\"tags\": [\r\n")
    var first = true
    WebPageImporter.classes("org.hyperscala.tags").foreach {
      case className if (!className.endsWith("$") && className.indexOf('$') == -1) => {
        val clazz = Class.forName("org.hyperscala.tags.%s".format(className))
        if (classOf[Tag].isAssignableFrom(clazz) && clazz != classOf[Tag]) {
          val classLoader = clazz.getClassLoader
          val declaringType = Type.getType(clazz)
          val url = "%s.class".format(declaringType.getInternalName)
          val classNode = new ClassNode()
          val input = classLoader.getResourceAsStream(url)
          try {
            val classReader = new ClassReader(input)
            classReader.accept(classNode, 0)      // Visit ClassNode to populate the data
          } finally {
            input.close()
          }
          val constructors = clazz.getConstructors.collect {
            case c if (c.getParameterTypes.length > 0) => c
          }
          if (constructors.length != 1) {
            throw new RuntimeException("%s has %s matching constructors!".format(className, constructors.length))
          }
          val constructor = constructors.head
          val descriptor = Type.getConstructorDescriptor(constructor)
          val methodNode = classNode.methods.asInstanceOf[java.util.List[MethodNode]].find(m => m.name == "<init>" && m.desc == descriptor).get
          val localVariables = methodNode.localVariables.asInstanceOf[java.util.List[LocalVariableNode]]
          val args = constructor.getParameterTypes.toList.zipWithIndex.map {
            case (c, index) => localVariables.get(index + 1).name -> c
          }.collect {
            case t if (!exclusions.contains(t._1)) => t
          }
          if (first) {
            first = false
          } else {
            b.append(",\r\n")
          }
          b.append("\t\t{\r\n")
          b.append("\t\t\t\"name\": \"%s\",\r\n".format(clazz.getSimpleName))
          b.append("\t\t\t\"attributes\": {\r\n")
          val attributes = args.map {
            case (name, c) => "\t\t\t\t\"%s\": \"%s\"".format(name, c.getName)
          }.mkString(",\r\n")
          b.append(attributes)
          b.append("\r\n")
          b.append("\t\t\t}\r\n")
          b.append("\t\t}")
        }
      }
      case _ =>
    }
    b.append("\r\n\t]\r\n")
    b.append("}\r\n")
    println(b)
  }

  private def applyStyleSheet(b: StringBuilder) = {
    b.append("\t\"css\": {\r\n")
    val div = new Div
    val styles = div.style.contents.map {
      case sp => {
        val classType = sp.manifest.erasure.getName match {
          case "java.lang.String" => "String"
          case "org.powerscala.Color" => "Color"
          case name if (name.startsWith("org.hyperscala.style.")) => name.substring(name.lastIndexOf('.') + 1)
          case name => throw new RuntimeException("Unsupported class type: %s".format(name))
        }
        "\t\t\"%s\": \"%s\"".format(sp._name, classType)
      }
    }.mkString(",\r\n")
    b.append("%s\r\n".format(styles))
    b.append("\t},\r\n")
  }
}
