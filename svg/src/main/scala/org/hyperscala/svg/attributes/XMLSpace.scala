package org.hyperscala.svg.attributes


/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class XMLSpace extends AttributeEntry[XMLSpace](parent = XMLSpace)

object XMLSpace extends AttributeObject[XMLSpace] {
  case object Default extends XMLSpace
  case object Preserve extends XMLSpace

  val values = findValues.toVector
}