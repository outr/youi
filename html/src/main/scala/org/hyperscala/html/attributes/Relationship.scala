package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class Relationship(val value: String) extends EnumEntryAttributeValue

object Relationship extends Enumerated[Relationship] with EnumEntryPersistence[Relationship] {
  val NoReferrer = new Relationship("noreferrer")
  val License = new Relationship("license")
  val Bookmark = new Relationship("bookmark")
  val Alternate = new Relationship("alternate")
  val Prefetch = new Relationship("prefetch")
  val Tag = new Relationship("tag")
  val NoFollow = new Relationship("nofollow")
  val Author = new Relationship("author")
  val Search = new Relationship("search")
  val Help = new Relationship("help")
  val Next = new Relationship("next")
  val Previous = new Relationship("prev")
}