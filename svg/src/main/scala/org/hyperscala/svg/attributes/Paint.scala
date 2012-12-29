package org.hyperscala.svg.attributes

import org.powerscala.Color
import org.hyperscala.svg.SVGTag
import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed trait Paint {
  def value: String
}

object Paint extends ValuePersistence[Paint] {
  val None = PaintNone
  val Current = PaintCurrent
  def Color(color: Color) = PaintColor(color)
  def IRI(iri: String) = PaintIRI(iri)
  def Ref(tag: SVGTag) = IRI("#%s".format(tag.identity))

  def fromString(s: String, clazz: Class[_]) = throw new UnsupportedOperationException("Paint reading not supported")

  def toString(t: Paint, clazz: Class[_]) = t.value
}

object PaintNone extends Paint {
  val value = "none"
}

object PaintCurrent extends Paint {
  val value = "currentColor"
}

case class PaintColor(color: Color) extends Paint {
  def value = color.hex.rgb
}

case class PaintIRI(iri: String) extends Paint {
  def value = "url(%s)".format(iri)
}