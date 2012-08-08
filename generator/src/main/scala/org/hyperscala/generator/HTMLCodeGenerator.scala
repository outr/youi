package org.hyperscala.generator

import util.parsing.json.JSON
import io.Source
import java.io.{FileWriter, File}
import annotation.tailrec

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HTMLCodeGenerator {
  val BaseOutput = new File("core/src/main/scala/org/hyperscala")
  val BaseHTMLOutput = new File("html/src/main/scala/org/hyperscala")
  val EnumTemplate = loadString("enum.template")
  val EnumInstantiatableTemplate = loadString("instantiatableEnum.template")
  val ConstraintsTemplate = loadString("constraints.template")
  val GlobalTemplate = loadString("global.template")
  val TagTemplate = loadString("tag.template")
  val PersistenceTemplate = loadString("persistence.template")
  val StyleSheetTemplate = loadString("stylesheet.template")

  var globalConstructorValues = List.empty[(String, String)]
  var tagClassNames = List.empty[String]

  def main(args: Array[String]): Unit = {
    val json = JSON.parseFull(loadString("meta.json")).get.asInstanceOf[Map[String, Any]]
    tagClassNames = json("tags").asInstanceOf[List[Map[String, Any]]].map(m => m("name").asInstanceOf[String]).toList
    processEnums(json("enums").asInstanceOf[List[Map[String, Any]]])
    processPersistence(json("enums").asInstanceOf[List[Map[String, Any]]])
    processConstraints(json("constraints").asInstanceOf[List[String]])
    processGlobal(json("global").asInstanceOf[Map[String, String]])
    processTags(json("tags").asInstanceOf[List[Map[String, Any]]])
    processStyleSheet(json("css").asInstanceOf[Map[String, String]])
  }

  @tailrec
  def processEnums(enums: List[Map[String, Any]]): Unit = {
    if (enums.nonEmpty) {
      val head = enums.head
      val name = head("name")
      val values = head("values").asInstanceOf[Map[String, String]]
      val custom = head.getOrElse("custom", Nil).asInstanceOf[List[String]]
      val definitions = values.map {
        case (key, value) => "val %s = new %s(\"%s\")".format(key, name, value)
      }.toList
      val body = (definitions ::: custom).mkString("\n  ")
      val instantiatable = head.getOrElse("instantiatable", false).asInstanceOf[Boolean]
      val instantiationTemplateRef = head.getOrElse("instantiationTemplate", null).asInstanceOf[String]
      val instantiationTemplate = instantiationTemplateRef match {
        case null => "name"
        case t => "\"%s\".format(name)".format(t)
      }
      val sub = head.getOrElse("sub", "html").asInstanceOf[String]
      val content = if (instantiatable) {
        EnumInstantiatableTemplate.format(sub, name, body, instantiationTemplate)
      } else {
        EnumTemplate.format(sub, name, body)
      }
      val file = new File(BaseHTMLOutput, "%s/attributes/%s.scala".format(sub, name))
      writeFile(content, file)
      processEnums(enums.tail)
    }
  }

  def processPersistence(enums: List[Map[String, Any]]): Unit = {
    val predefined = List("BooleanPersistence",
                          "CharPersistence",
                          "ColorPersistence",
                          "IntPersistence",
                          "DoublePersistence",
                          "LanguagePersistence",
                          "ListStringPersistence",
                          "StyleSheetPersistence",
                          "JavaScriptContentPersistence")
    val values = (predefined ::: enums.map(m => m("name").asInstanceOf[String])).sorted
    val implicits = values.map(n => {
      val variable = if (n.endsWith("Persistence")) {
        n
      } else {
        "%sPersistence".format(n)
      }
      "implicit val %s = %s".format(variable.charAt(0).toLower + variable.substring(1), n)
    }).mkString("\n  ")
    val content = PersistenceTemplate.format(implicits)
    val file = new File(BaseHTMLOutput, "persistence/package.scala")
    writeFile(content, file)
  }

  @tailrec
  def processConstraints(constraints: List[String]): Unit = {
    if (constraints.nonEmpty) {
      val head = constraints.head
      val content = ConstraintsTemplate.format(head)
      val file = new File(BaseHTMLOutput, "html/constraints/%s.scala".format(head))
      writeFile(content, file)
      processConstraints(constraints.tail)
    }
  }

  def processGlobal(global: Map[String, String]) = {
    val body = generateAttributes(global)
    globalConstructorValues = ("name" -> "String") :: generateConstructorValues(global)
    val content = GlobalTemplate.format(body, tagClassNames.map(s => "\"%s\" -> classOf[%s]".format(s.toLowerCase, s)).mkString(",\n                                  "))
    val file = new File(BaseHTMLOutput, "html/HTMLTag.scala")
    writeFile(content, file)
  }

  @tailrec
  def processTags(tags: List[Map[String, Any]]): Unit = {
    if (tags.nonEmpty) {
      val tag = tags.head
      val name = tag("name").asInstanceOf[String]
      val htmlName = name.toLowerCase
      var xtnd = List.empty[String]
      val childTrait = tag.get("childTrait").asInstanceOf[Option[String]]
      childTrait match {
        case Some(traitName) => xtnd = "Container[%s]".format(traitName) :: xtnd
        case None =>
      }
      tag.get("traits") match {
        case Some(traits) => xtnd = traits.asInstanceOf[List[String]] ::: xtnd
        case None =>
      }
      xtnd = "HTMLTag" :: xtnd
      val xtndContent = xtnd.reverse.mkString(" with ")
      val imports = tag.getOrElse("imports", Nil).asInstanceOf[List[String]].map(s => "import %s\n".format(s)).mkString
      val attributeMap = tag.getOrElse("attributes", Map.empty[String, String]).asInstanceOf[Map[String, String]]
      val attributes = generateAttributes(attributeMap)
      val extraConstructorValues = if (xtnd.contains("Textual")) {
        List("content" -> "String")
      } else if (childTrait != None) {
        List("content" -> "%s".format(childTrait.get))
      } else {
        Nil
      }
      val constructorValues = globalConstructorValues ::: generateConstructorValues(attributeMap) ::: extraConstructorValues
      val constructorArgs = constructorValues.map {
        case (n, c) => "%s: %s = null".format(n, c)
      }.mkString(",\n           ")
      val constructorUpdates = constructorValues.collect {
        case (n, c) if (n == "content" && childTrait != None) => "if (content != null) contents += content"
        case (n, c) => "up(this.%1$s, %1$s)".format(n)
      }.mkString("\n    ")
      val constructor = "def this(%s) = {\n    this()\n    %s\n  }".format(constructorArgs, constructorUpdates)
      val content = TagTemplate.format(imports, name, xtndContent, htmlName, constructor, attributes)
      val file = new File(BaseHTMLOutput, "html/%s.scala".format(name))
      writeFile(content, file)
      processTags(tags.tail)
    }
  }

  private def generateConstructorValues(attributes: Map[String, Any]) = {
    attributes.toList.sortBy(t => t._1).map {
      case (key, value) => {
        val classType = value match {
          case m: Map[_, _] => m.asInstanceOf[Map[String, String]]("class").toString
          case "Boolean" => "java.lang.Boolean"
          case "Char" => "java.lang.Character"
          case "Int" => "java.lang.Integer"
          case _ => value.toString
        }
        key -> classType
      }
    }
  }

  private def generateAttributes(attributes: Map[String, Any]) = {
    var enums = List.empty[String]
    val body = attributes.toList.sortBy(t => t._1).map {
      case (key, value) => {
        val htmlKey = key.toLowerCase match {
          case "mimetype" => "type"
          case "buttontype" => "type"
          case "inputtype" => "type"
          case "clazz" => "class"
          case k => k
        }
        var inclusionMode: String = null
        val classType = value match {
          case s: String => s
          case m: Map[_, _] => {
            val valueMap = m.asInstanceOf[Map[String, String]]
            valueMap.get("inclusion") match {
              case Some(i) => inclusionMode = i
              case None =>
            }
            valueMap("class")
          }
        }
        val default = classType match {
          case "Char" => "-1.toChar"
          case "List[String]" => "Nil"
          case "Int" => "-1"
          case "Boolean" => "false"
          case "String" => "null"
          case "StyleSheet" => "new StyleSheet()"
          case _ => {
            enums = classType :: enums
            "null"
          }
        }
        if (inclusionMode != null) {
          "val %s = PropertyAttribute[%s](\"%s\", %s, inclusion = InclusionMode.%s)".format(key, classType, htmlKey, default, inclusionMode)
        } else {
          "val %s = PropertyAttribute[%s](\"%s\", %s)".format(key, value, htmlKey, default)
        }
      }
    }.mkString("\n  ")
    body
  }

  def processStyleSheet(map: Map[String, String]) = {
    val topLevel = SSProperty(null, null, null)
    map.foreach {
      case (name, classType) => {
        val levels = name.split('-')
        var current = topLevel
        levels.foreach {
          case level => {
            current.children.find(ssp => ssp.variableName == sspVariableName(current, level)) match {
              case Some(ssp) => current = ssp
              case None => {
                val child = SSProperty(current, level)
                current.children = child :: current.children
                current = child
              }
            }
          }
        }
        current.value = classType
      }
    }
    val b = new StringBuilder
    topLevel.children = topLevel.children.sortBy(ssp => ssp.variableName)
    topLevel.children.foreach(ssp => generateStyleSheetRecursive(ssp, b, 1))
    val content = StyleSheetTemplate.format(b.toString())
    val file = new File(BaseHTMLOutput, "css/StyleSheet.scala")
    writeFile(content, file)
  }

  def generateStyleSheetRecursive(ssp: SSProperty, b: StringBuilder, depth: Int): Unit = {
    (0 until depth).foreach(index => b.append("  "))
    if (ssp.variableName == "name") {
      b.append("override ")
    }
    b.append("val ")
    b.append(ssp.variableName)
    b.append(" = ")
    if (ssp.children.nonEmpty) {
      ssp.children = ssp.children.sortBy(child => child.variableName)
      if (ssp.value != null) {
        b.append("new StyleSheetAttribute[%s](\"%s\", null) {\n".format(ssp.value, ssp.fullName))
      } else {
        b.append("new AnyRef {\n")
      }
      ssp.children.foreach {
        case child => generateStyleSheetRecursive(child, b, depth + 1)
      }
      (0 until depth).foreach(index => b.append("  "))
      b.append("}\n")
    } else {
      b.append("StyleSheetAttribute[%s](\"%s\", null)\n".format(ssp.value, ssp.fullName))
    }
  }

  case class SSProperty(parent: SSProperty, name: String, var value: String = null, var children: List[SSProperty] = Nil) {
    lazy val variableName = sspVariableName(parent, name)
    def fullName: String = if (parent != null && parent.name != null) {
      "%s-%s".format(parent.fullName, name)
    } else {
      name
    }
  }

  def sspVariableName(parent: SSProperty, name: String) = name.replaceAll("@", "") match {
    case "type" => "%sType".format(parent.name)
    case "new" => "%sNew".format(parent.name)
    case n => n
  }

  private def loadString(uri: String) = {
    Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(uri)).mkString
  }

  def writeFile(content: String, file: File) = {
    file.getParentFile.mkdirs()
    val writer = new FileWriter(file)
    try {
      writer.write(content)
    } finally {
      writer.flush()
      writer.close()
    }
  }
}
