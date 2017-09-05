package io.youi.util

import java.io.File
import java.net

import io.youi.net.URL
import org.powerscala.io._
import profig.{Config, ConfigApplication, JsonUtil}

object GoogleFontBuilder extends ConfigApplication {
  private lazy val apiKey: String = Config("googleApiKey").as[Option[String]].getOrElse(throw new RuntimeException("No configuration setting present for googleApiKey."))
  private lazy val url = URL(s"https://www.googleapis.com/webfonts/v1/webfonts?key=$apiKey")

  private val base = "http://fonts.gstatic.com/s"

  override def main(args: Array[String]): Unit = start(args)

  override protected def run(): Unit = {
    val jsonString = IO.stream(new net.URL(url.toString), new StringBuilder).toString
    val list = JsonUtil.fromJsonString[WebFontList](jsonString)

    val file = new File("canvas/shared/src/main/scala/io/youi/font/GoogleFont.scala")
    val fonts = list.items.map { font =>
      s"""  object `${font.family}` {
         |    val category: String = "${font.category}"
         |    val subsets: Set[String] = Set(${font.subsets.map(s => s""""$s"""").mkString(", ")})
         |${font.toTemplate}
         |  }""".stripMargin
    }
    val template =
      s"""package io.youi.font
         |
         |import io.youi.net.URL
         |
         |object GoogleFont {
         |  private val base: String = "$base"
         |
         |${fonts.mkString("\n")}
         |}
       """.stripMargin
    IO.stream(template, file)
  }

  case class WebFontList(items: List[WebFont])

  case class WebFont(family: String,
                     category: String,
                     variants: List[String],
                     subsets: List[String],
                     version: String,
                     files: Map[String, String]) {
    def toTemplate: String = variants.map { variant =>
      val url = files(variant)
      assert(url.startsWith(base))
      s"""    def `$variant`: URL = URL(s"$$base${url.substring(base.length)}")"""
    }.mkString("\n")
  }
}