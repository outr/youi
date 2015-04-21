package org.hyperscala.html.attributes

import org.hyperscala.persistence.CaseClassPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
case class Relationship(value: String) extends AttributeValue

object Relationship extends CaseClassPersistence[Relationship] {
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
}