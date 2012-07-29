package org.hyperscala.generator

import util.parsing.json.JSON
import io.Source
import java.io.{FileWriter, File}
import annotation.tailrec

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HTMLCodeGenerator {
  val BaseOutput = new File("html/src/main/scala/org/hyperscala")
  val EnumTemplate = loadString("enum.template")
  val EnumInstantiatableTemplate = loadString("instantiatableEnum.template")
  val ConstraintsTemplate = loadString("constraints.template")
  val GlobalTemplate = loadString("global.template")
  val TagTemplate = loadString("tag.template")

  var globalConstructorValues = List.empty[(String, String)]

  def main(args: Array[String]): Unit = {
    val json = JSON.parseFull(loadString("meta.json")).get.asInstanceOf[Map[String, Any]]
    processEnums(json("enums").asInstanceOf[List[Map[String, Any]]])
    processConstraints(json("constraints").asInstanceOf[List[String]])
    processGlobal(json("global").asInstanceOf[Map[String, String]])
    processTags(json("tags").asInstanceOf[List[Map[String, Any]]])
  }

  @tailrec
  def processEnums(enums: List[Map[String, Any]]): Unit = {
    if (enums.nonEmpty) {
      val head = enums.head
      val name = head("name")
      val values = head("values").asInstanceOf[Map[String, String]]
      val body = values.map {
        case (key, value) => "val %s = new %s(\"%s\")".format(key, name, value)
      }.mkString("\r\n  ")
      val instantiatable = head.getOrElse("instantiatable", false).asInstanceOf[Boolean]
      val content = if (instantiatable) {
        EnumInstantiatableTemplate.format(name, body)
      } else {
        EnumTemplate.format(name, body)
      }
      val file = new File(BaseOutput, "html/attributes/%s.scala".format(name))
      writeFile(content, file)
      processEnums(enums.tail)
    }
  }

  @tailrec
  def processConstraints(constraints: List[String]): Unit = {
    if (constraints.nonEmpty) {
      val head = constraints.head
      val content = ConstraintsTemplate.format(head)
      val file = new File(BaseOutput, "html/constraints/%s.scala".format(head))
      writeFile(content, file)
      processConstraints(constraints.tail)
    }
  }

  def processGlobal(global: Map[String, String]) = {
    val body = generateAttributes(global)
    globalConstructorValues = generateConstructorValues(global)
    val content = GlobalTemplate.format(body)
    val file = new File(BaseOutput, "html/HTMLTag.scala")
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
      }.mkString(",\r\n           ")
      val constructorUpdates = constructorValues.collect {
        case (n, c) if (n == "content" && childTrait != None) => "if (content != null) contents += content"
        case (n, c) => "up(this.%1$s, %1$s)".format(n)
      }.mkString("\r\n    ")
      val constructor = "def this(%s) = {\r\n    this()\r\n    %s\r\n  }".format(constructorArgs, constructorUpdates)
      val content = TagTemplate.format(name, xtndContent, htmlName, constructor, attributes)
      val file = new File(BaseOutput, "html/%s.scala".format(name))
      writeFile(content, file)
      processTags(tags.tail)
    }
  }

  private def generateConstructorValues(attributes: Map[String, String]) = {
    attributes.toList.sortBy(t => t._1).map {
      case (key, value) => {
        val classType = value match {
          case "Boolean" => "java.lang.Boolean"
          case "Char" => "java.lang.Character"
          case "Int" => "java.lang.Integer"
          case _ => value
        }
        key -> classType
      }
    }
  }

  private def generateAttributes(attributes: Map[String, String]) = {
    var enums = List.empty[String]
    var body = attributes.toList.sortBy(t => t._1).map {
      case (key, value) => {
        val htmlKey = key.toLowerCase match {
          case "mimetype" => "type"
          case "buttontype" => "type"
          case "inputtype" => "type"
          case "clazz" => "class"
          case k => k
        }
        val default = value match {
          case "Char" => "-1.toChar"
          case "List[String]" => "Nil"
          case "Int" => "-1"
          case "Boolean" => "false"
          case "String" => "null"
          case _ => {
            enums = value :: enums
            "null"
          }
        }
        "val %s = PropertyAttribute[%s](\"%s\", %s)".format(key, value, htmlKey, default)
      }
    }.mkString("\r\n  ")
    if (enums.nonEmpty) {
      enums = enums.reverse
      val enumBody = enums.map {
        case "Language" => "implicit val languagePersistence = LanguagePersistence"
        case n => "implicit val %sPersistence = %s".format(n.charAt(0).toLower + n.substring(1), n)
      }.mkString("\r\n  ")
      body = "%s\r\n\r\n  %s".format(enumBody, body)
    }
    body
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
