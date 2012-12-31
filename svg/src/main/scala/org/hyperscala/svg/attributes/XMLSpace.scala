package org.hyperscala.svg.attributes


/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class XMLSpace extends AttributeEntry[XMLSpace](parent = XMLSpace)

object XMLSpace extends AttributeObject[XMLSpace] {
  val Default = new XMLSpace
  val Preserve = new XMLSpace
}