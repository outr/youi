package org.hyperscala.svg

import attributes.{Transform, XMLSpace}
import org.hyperscala.{IdentifiableTag, PropertyAttribute}
import org.hyperscala.io.HTMLWriter

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait SVGTag extends IdentifiableTag {
  val xmlBase = PropertyAttribute[String]("xml:base", null)
  val xmlLang = PropertyAttribute[String]("xml:lang", null)
  val xmlSpace = PropertyAttribute[XMLSpace]("xml:space", null)
  val clazz = PropertyAttribute[List[String]]("class", Nil)
  val style = PropertyAttribute[String]("style", null)
  val externalResourcesRequired = PropertyAttribute[Boolean]("externalResourcesRequired", false)
  val transform = PropertyAttribute[List[Transform]]("transform", Nil)

  def byTag[T <: SVGTag](implicit manifest: Manifest[T]) = hierarchy.findAll[T](t => true)(manifest)

  def outputString = {
    val b = new StringBuilder
    val writer: String => Unit = (s: String) => b.append(s)
    val htmlWriter = HTMLWriter(writer)
    write(htmlWriter)
    b.toString()
  }

  def rendered() = {}
}