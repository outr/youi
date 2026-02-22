package io.youi

import org.scalajs.dom.Element

object Template {
  // Scala 2 macro implementations are not available in Scala 3.
  // For now, resolve elements from the active document at runtime.
  def byId[E <: Element](path: String, id: String, appName: String): E = dom.oneBySelector(s"#$id")
  def byClass[E <: Element](path: String, className: String, appName: String): List[E] = dom.bySelector[E](s".$className").toList
  def byTag[E <: Element](path: String, tagName: String, appName: String): List[E] = dom.bySelector[E](tagName).toList
}