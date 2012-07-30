package org.hyperscala.html.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class Relationship(val value: String) extends EnumEntry[Relationship] with AttributeValue

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