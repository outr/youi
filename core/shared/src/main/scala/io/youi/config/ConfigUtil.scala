package io.youi.config

import java.util.Properties

import io.circe._

import scala.annotation.tailrec
import scala.collection.JavaConverters._

object ConfigUtil {
  def map2Json(map: Map[String, String]): Json = {
    var json = Json.obj()
    map.foreach {
      case (key, value) => json = json.deepMerge(createJson(key, string2JSON(value)))
    }
    json
  }

  def properties2Json(properties: Properties): Json = {
    val map = properties.asScala.map {
      case (key, value) => key.toString -> value.toString
    }.toMap
    map2Json(map)
  }

  @tailrec
  final def args2Json(args: Seq[String], json: Json = Json.obj(), index: Int = 1): Json = if (args.isEmpty) {
    json
  } else {
    var seq = args
    val first = seq.head
    seq = seq.tail
    var i = index

    val j: Json = if (first.startsWith("-")) {
      val n = if (first.startsWith("--")) {
        first.substring(2)
      } else {
        first.substring(1)
      }
      val equalsIndex = n.indexOf('=')
      if (equalsIndex > 0) {
        createJson(n.substring(0, equalsIndex), string2JSON(n.substring(equalsIndex + 1)))
      } else if (seq.isEmpty) {
        createJson(n, Json.True)
      } else {
        val second = seq.head
        seq = seq.tail
        createJson(n, string2JSON(second))
      }
    } else {
      i += 1
      Json.obj(s"arg$index" -> string2JSON(first))
    }
    args2Json(seq, json.deepMerge(j), i)
  }

  private val IntegerRegex = """(\d+)""".r
  private val DecimalRegex = """(\d*[.]\d*)""".r

  def string2JSON(s: String): Json = s match {
    case "true" => Json.True
    case "false" => Json.False
    case IntegerRegex(i) => Json.fromInt(i.toInt)
    case DecimalRegex(d) => Json.fromBigDecimal(BigDecimal(d))
    case _ => Json.fromString(s)
  }

  def createJson(name: String, value: Json): Json = {
    val index = name.indexOf('.')
    if (index == -1) {
      Json.obj(name -> value)
    } else {
      val n = name.substring(0, index)
      Json.obj(n -> createJson(name.substring(index + 1), value))
    }
  }
}