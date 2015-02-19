package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
object parent extends ExistingStatement[HTMLTag]("parent") {
  lazy val document = new document("parent")
}