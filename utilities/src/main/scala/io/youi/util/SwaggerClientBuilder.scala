package io.youi.util

import io.circe.parser._
import java.io.File
import java.net.URL

import io.circe.Json
import org.powerscala.io._
import profig.JsonUtil

object SwaggerClientBuilder {
  def main(args: Array[String]): Unit = {
    val url = new URL("https://raw.githubusercontent.com/arangodb/arangodb/devel/js/apps/system/_admin/aardvark/APP/api-docs.json")
    val json = parse(IO.stream(url.openStream(), new StringBuilder).toString).getOrElse(throw new RuntimeException("Failed to parse!"))
    val directory = new File("/home/mhicks/projects/open/scarango/core/src/main/scala/")
    IO.delete(directory)
    val b = new SwaggerClientBuilder(directory, "com.outr.arango.api", json)
    b.createClasses()
    b.createEndPoints()
    b.structure.write()
  }
}

class SwaggerClientBuilder(directory: File, packageName: String, swagger: Json) {
  private val SnakeSplitter = "[-._]?([a-zA-Z0-9]+)".r
  private val NameSplitter = "[-._ ]([a-zA-Z0-9])".r

  private val WordSwap = Map(
    "api" -> "API"
  )
  private val NameSwap = Map(
    "type" -> "`type`",
    "new" -> "`new`",
    "Json Request Body" -> "body"
  )
  private val TypeSwap = Map(
    "bool" -> "Boolean",
    "boolean" -> "Boolean",
    "booleanboolean" -> "Boolean",
    "integer" -> "Int",
    "integerinteger" -> "Int",
    "integerint64" -> "Long",
    "integeruint64" -> "Long",
    "number" -> "Double",
    "numberfloat" -> "Double",
    "string" -> "String",
    "stringstring" -> "String",
    "array" -> "List[String]",
    "arraystring" -> "List[String]",
    "arrayinteger" -> "List[Int]",
    "object" -> "io.circe.Json",
    "arrayobject" -> "List[io.circe.Json]"
  )
  private val basePath = (swagger \\ "basePath").head.asString.get
  private val definitions = (swagger \\ "definitions").head.asObject.get
  private val paths = (swagger \\ "paths").head.asObject.get

  private val packageDirectory: File = new File(directory, packageName.replace('.', '/'))

  private val structure: Structure = new Structure

  def createEndPoints(): Unit = paths.toList.foreach {
    case (n, j) => j.asObject.get.toList.foreach {
      case (m, json) => createEndPoint(n, m, json)
    }
  }

  def createEndPoint(path: String, method: String, json: Json): Unit = try {
    val description = (json \\ "description").head.asString.getOrElse("").trim
    val parameters = (json \\ "parameters").headOption.flatMap(_.asArray.map(_.toList)).getOrElse(Nil).map { j =>
      JsonUtil.fromJson[Parameter](j)
    }
    val args = parameters.map { p =>
      s"${p.fixedName}: ${p.derivedType}"
    }
    val responses = (json \\ "responses").head
    val response = (responses \\ "200").headOption.map(j => JsonUtil.fromJson[Response](j)).getOrElse(Response("", None))
    val responseType = response.schema.map { s =>
      className(s.name)
    }.getOrElse("ArangoResponse")
    val OptionRegex = """Option\[(.+)\] = None""".r
    val paramEntries = parameters.filterNot(_.in == "body").map { p =>
      p.derivedType match {
        case OptionRegex(t) => s""".param[Option[$t]]("${p.name}", ${p.name}, None)"""
        case _ => s""".params("${p.name}" -> ${p.name}.toString)"""
      }
    }
    val call = parameters.find(_.in == "body") match {
      case Some(p) => s".restful[${p.derivedType}, $responseType](${p.fixedName})"
      case None => s".call[$responseType]"
    }
    val build = List(s".method(HttpMethod.${method.capitalize})") ::: paramEntries ::: List(call)
    val code = s"""/**
                  |  * ${description.replaceAll("\n", "\n  * ")}
                  |  */
                  |  def $method(${args.mkString(", ")}): Future[$responseType] = client
                  |    ${build.mkString("\n    ")}
                  |""".stripMargin.trim
    val fullPath = (path.split('/').toList ::: List(method)).filterNot(_.trim.isEmpty)
    structure.define(fullPath, code)
  } catch {
    case t: Throwable => throw new RuntimeException(s"Failed to create end-point for $path: $json", t)
  }

  def createClasses(): List[String] = definitions.toList.map {
    case (n, j) => {
      packageDirectory.mkdirs()
      val clazz = className(n)
      val source = createClass(clazz, j)
      val file = new File(packageDirectory, s"$clazz.scala")
      IO.stream(source, file)
      s"$packageName.$clazz"
    }
  }

  def createClass(clazz: String, json: Json): String = {
    val required = (json \\ "required").headOption.map(_.asArray.get.flatMap(_.asString).toSet).getOrElse(Set.empty)
    val properties = (json \\ "properties").headOption.flatMap(_.asObject.map(_.toList)).getOrElse(Nil).map {
      case (n, j) => {
        (j \\ "$ref").headOption.flatMap(_.asString) match {
          case Some(ref) => {
            val refName = Schema(Some(ref), None).name
            Property(n, "", None, className(refName), required.contains(n), builtIn = false)
          }
          case None => {
            JsonUtil.fromJson[Property](j).copy(name = n, required = required.contains(n))
          }
        }
      }
    }.sortBy(!_.required)
    val pre = s"case class $clazz("
    val spacer = pre.replaceAll(".", " ")
    val params = properties.map(paramFrom)
    def description(p: Property): String = p.description.trim match {
      case "" => "*** No description ***"
      case d => d.replaceAll("\n", "\n  *        ")
    }
    s"""package $packageName
       |
       |/**
       |  * $clazz
       |  *
       |  * ${properties.map(p => s"@param ${p.name} ${description(p)}").mkString("\n  * ")}
       |  *
       |  * WARNING: This code is generated by youi-plugin's generateHttpClient. Do not modify directly.
       |  */
       |case class $clazz(${params.mkString(s",\n$spacer")})
     """.stripMargin.trim
  }

  def className(name: String): String = SnakeSplitter.replaceAllIn(name, m => {
    val value = m.group(1)
    WordSwap.getOrElse(value, value.capitalize)
  })

  def fixName(name: String): String = {
    NameSplitter.replaceAllIn(NameSwap.getOrElse(name, name), m => {
      m.group(1).toUpperCase
    }).replaceAll("[\\[\\]*]", "")
  }

  def generateType(name: String, `type`: String, format: Option[String], builtIn: Boolean, required: Boolean): String = {
    val merged = s"${`type`}${format.getOrElse("")}"
    val t = if (builtIn) {
      TypeSwap.getOrElse(merged, throw new RuntimeException(s"Unsupported type: [$merged] for $name"))
    } else {
      `type`
    }
    if (required) {
      t
    } else {
      s"Option[$t] = None"
    }
  }

  def paramFrom(property: Property): String = {
    val name = fixName(property.name)
    val `type` = generateType(property.name, property.`type`, property.format, property.builtIn, property.required)
    s"$name: ${`type`}"
  }

  case class Property(name: String = "", description: String, format: Option[String], `type`: String, required: Boolean = false, builtIn: Boolean = true)

  case class Parameter(in: String, name: String, required: Boolean = false, schema: Option[Schema], description: Option[String], `type`: Option[String], format: Option[String]) {
    lazy val fixedName: String = fixName(name)
    lazy val derivedType: String = generateType(
      name = fixedName,
      `type` = `type`.getOrElse(className(schema.get.name)),
      format = format,
      builtIn = `type`.nonEmpty,
      required = required
    )
  }

  case class Response(description: String, schema: Option[Schema])

  case class Schema(`$ref`: Option[String], `type`: Option[String]) {
    def name: String = `$ref` match {
      case Some(r) => r.substring(r.lastIndexOf('/') + 1)
      case None => TypeSwap(`type`.getOrElse(throw new RuntimeException("No $ref or `type` for Schema")))
    }
  }

  class Structure {
    private var map = Map.empty[String, Structure]
    private var code = Set.empty[String]

    def define(path: List[String], code: String): Unit = if (path.isEmpty) {
      this.code += code
    } else {
      val entry = path.head
      val child = map.getOrElse(entry, new Structure)
      child.define(path.tail, code)
      map += entry -> child
    }

    def write(prefix: List[String] = Nil): Unit = {
      if (prefix.nonEmpty) {
        val cn = className(prefix.reverse.map(_.capitalize).mkString(""))
        val entries = if (map.nonEmpty) {
          map.keys.toList.map { k =>
            val name = className(k)
            s"val ${name.charAt(0).toLower}${name.substring(1)} = new ${className(s"$cn${k.capitalize}")}(client)"
          }.mkString("\n  ")
        } else {
          ""
        }
        val imports = if (code.nonEmpty) {
          """
            |import io.youi.client.HttpClient
            |import io.youi.http.HttpMethod
            |import scala.concurrent.Future
            |import scribe.Execution.global
          """.stripMargin
        } else {
          """
            |import io.youi.client.HttpClient
          """.stripMargin
        }
        val methods = if (code.nonEmpty) code.mkString(if (entries.nonEmpty) "\n\n  " else "", "\n\n  ", "") else ""
        val source =
          s"""package $packageName
             |$imports
             |class $cn(client: HttpClient) {
             |  $entries$methods
             |}
         """.stripMargin.trim
        val file = new File(packageDirectory, s"$cn.scala")
        assert(!file.exists(), s"File already exists: ${file.getAbsolutePath}")
        IO.stream(source, file)
      }
      map.foreach {
        case (entry, child) => child.write(entry :: prefix)
      }
    }
  }
}