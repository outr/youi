package io.youi.util

import java.io.File
import java.net

import io.youi.net.URL
import io.youi.stream._
import profig.{Profig, JsonUtil}

object GoogleFontBuilder {
  private lazy val apiKey: String = Profig("googleApiKey").as[String](throw new RuntimeException("No configuration setting present for googleApiKey."))
  private lazy val url = URL(s"https://www.googleapis.com/webfonts/v1/webfonts?key=$apiKey")

  private val base = "http://fonts.gstatic.com/s"

  def main(args: Array[String]): Unit = {
    Profig.merge(args)
    Profig.loadDefaults()

    val jsonString = IO.stream(new net.URL(url.toString), new StringBuilder).toString
    val list = JsonUtil.fromJsonString[WebFontList](jsonString)

    val file = new File("ui/shared/src/main/scala/io/youi/font/GoogleFont.scala")
    val fonts = list.items.map { font =>
      val subsets = font.subsets.map { sub =>
        s"""      lazy val `$sub`: GoogleFontSubset = new GoogleFontSubset("$sub")"""
      }

      s"""  object `${font.family}` extends GoogleFont {
         |    override lazy val family: String = "${font.family}"
         |    override lazy val category: String = "${font.category}"
         |    override object subsets extends GoogleFontSubsets {
         |${subsets.mkString("\n")}
         |
         |      override lazy val all: Set[GoogleFontSubset] = Set(${font.subsets.map(s => s"`$s`").mkString(", ")})
         |    }
         |${font.toTemplate}
         |
         |    override lazy val weights: List[GoogleFontWeight] = List(${font.variants.map(s => s"`$s`").mkString(", ")})
         |  }""".stripMargin
    }
    val template =
      s"""package io.youi.font
         |
         |import io.youi.net.URL
         |
         |trait GoogleFont {
         |  def family: String
         |  def category: String
         |  def subsets: GoogleFontSubsets
         |  def weights: List[GoogleFontWeight]
         |}
         |
         |object GoogleFont {
         |  private val base: String = s"https://fonts.gstatic.com/s"
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
      s"""    lazy val `$variant`: GoogleFontWeight = GoogleFontWeight(this, "$variant", URL(s"$$base${url.substring(base.length)}"))"""
    }.mkString("\n")
  }
}