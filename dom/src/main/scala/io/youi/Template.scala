package io.youi

import org.scalajs.dom.Element
import scala.language.experimental.macros

object Template {
  def byId[E <: Element](path: String, id: String, appName: String): E = macro TemplateMacros.existingById[E]
  def byClass[E <: Element](path: String, className: String, appName: String): List[E] = macro TemplateMacros.existingByClass[E]
  def byTag[E <: Element](path: String, tagName: String, appName: String): List[E] = macro TemplateMacros.existingByTag[E]
}