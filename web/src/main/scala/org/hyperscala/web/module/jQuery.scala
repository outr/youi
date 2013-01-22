package org.hyperscala.web.module

import org.hyperscala.module._
import org.hyperscala.html.HTMLTag
import org.hyperscala.web.site.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQuery extends Interface {
  def name = "jquery"

  def blur(tag: HTMLTag) = call(tag, "blur()")

  def change(tag: HTMLTag) = call(tag, "change()")

  def focus(tag: HTMLTag) = call(tag, "focus()")

  def select(tag: HTMLTag) = call(tag, "select()")

  def submit(tag: HTMLTag) = call(tag, "submit()")

  def call(tag: HTMLTag, function: String) = {
    Webpage().sendJavaScript("$('#%s').%s;".format(tag.identity, function), forId = tag.identity, head = false)
  }
}