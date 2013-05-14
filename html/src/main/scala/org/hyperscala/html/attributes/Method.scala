package org.hyperscala.html.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class Method(val value: String) extends EnumEntry with AttributeValue

object Method extends Enumerated[Method] with EnumEntryPersistence[Method] {
  val Get = new Method("get")
  val Put = new Method("put")
  val Trace = new Method("trace")
  val Connect = new Method("connect")
  val Head = new Method("head")
  val Delete = new Method("delete")
  val Patch = new Method("patch")
  val Post = new Method("post")
  val Options = new Method("options")
}