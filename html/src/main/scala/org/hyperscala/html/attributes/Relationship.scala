package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Relationship private(val value: String) extends EnumEntryAttributeValue

object Relationship extends Enumerated[Relationship] with EnumEntryPersistence[Relationship] {
  val Alternate = new Relationship("alternate")
  val Archives = new Relationship("archives")
  val Author = new Relationship("author")
  val Bookmark = new Relationship("bookmark")
  val External = new Relationship("external")
  val First = new Relationship("first")
  val Help = new Relationship("help")
  val Icon = new Relationship("icon")
  val Index = new Relationship("index")
  val Last = new Relationship("last")
  val License = new Relationship("license")
  val Next = new Relationship("next")
  val NoFollow = new Relationship("nofollow")
  val NoReferrer = new Relationship("noreferrer")
  val PingBack = new Relationship("pingback")
  val Prefetch = new Relationship("prefetch")
  val Previous = new Relationship("prev")
  val Search = new Relationship("search")
  val StyleSheet = new Relationship("stylesheet")
  val SideBar = new Relationship("sidebar")
  val Tag = new Relationship("tag")
  val Up = new Relationship("up")

  override def apply(name: String) = get(name) match {
    case Some(r) => r
    case None => new Relationship(name)
  }
}