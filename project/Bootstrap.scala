import sbt._
import sbt.Keys._
import com.typesafe.sbt.web.Import._

/**
 * Task for auto-generating a Scala object containing all glyphicons. The
 * glyphicons are extracted directly from the Bootstrap LESS sources.
 */
object Bootstrap {
  def between(subject: String, left: String, right: String): Option[String] = {
    val ofs = subject.indexOf(left)
    val end = subject.indexOf(right, ofs + left.length + 1)
    if (ofs == -1 || end == -1) None
    else Some(subject.slice(ofs + left.length, end))
  }

  def objectName(cssTag: String): String =
    cssTag.foldLeft("") { case (acc, cur) =>
      if (acc.length == 0) "" + cur.toUpper
      else if (acc.last == '-') acc.dropRight(1) + cur.toUpper
      else acc + cur
    }

  def generateBootstrap(sourceGen: File, webJars: File): Seq[File] = {
    val prefix = "glyphicon"

    val file = sourceGen / "org" / "hyperscala" / "bootstrap" / "component" / "Glyphicon.scala"

    val objects = io.Source.fromFile(webJars / "lib" / "bootstrap" / "less" / "glyphicons.less")
      .getLines()
      .filter(_.startsWith(s".$prefix-"))
      .map { line =>
      val icon =
        between(line, "-", "{")
          .orElse(between(line, "-", ","))
          .get.trim
      val obj = objectName(icon)

      s"""
       case object $obj extends Glyphicon("$prefix-$icon")
       """
    }
      .mkString("\n")

    IO.write(file,
      """
      package org.hyperscala.bootstrap.component
      import org.powerscala.enum.{Enumerated, EnumEntry}
      import org.hyperscala.html.HTMLTag
      sealed abstract class Glyphicon(val className: String) extends EnumEntry {
        def apply[T <: HTMLTag](t: T) = {
          t.clazz := t.clazz().filter(s => s.startsWith("glyphicon"))
          t.clazz += "glyphicon"
          t.clazz += s"glyphicon-$className"
          t
        }
        def create() = apply(new org.hyperscala.html.tag.Span)
      }
      """ +
        s"""
      object Glyphicon extends Enumerated[Glyphicon] {
        $objects
        val values = findValues.toVector
      }
      """
    )

    Seq(file)
  }

  val extractGlyphicons = {
    val source = sourceManaged in Compile
    val zipped = source.zip(WebKeys.webJarsDirectory in Assets)
    zipped.map { case (src, web) =>
      generateBootstrap(src, web)
    }
  }.dependsOn(WebKeys.webJars in Assets)
}