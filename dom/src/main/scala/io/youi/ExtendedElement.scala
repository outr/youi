package io.youi

import org.scalajs.dom.Element

class ExtendedElement(element: Option[Element]) {
  def byTag[T <: Element](tagName: String): Vector[T] = bySelector[T](tagName)
  def oneByTag[T <: Element](tagName: String): T = oneBySelector[T](tagName)
  def byClass[T <: Element](className: String): Vector[T] = bySelector[T](s".$className")
  def oneByClass[T <: Element](className: String): T = oneBySelector[T](s".$className")
  def byName[T <: Element](name: String): Vector[T] = bySelector[T](s"""[name="$name"]""")
  def oneByName[T <: Element](name: String): T = oneBySelector[T](s"""[name="$name"]""")
  def getById[T <: Element](id: String): Option[T] = firstBySelector[T](s"#$id")
  def byId[T <: Element](id: String): T = oneBySelector[T](s"#$id")
  def bySelector[T <: Element](selectors: String): Vector[T] = dom.bySelector[T](selectors, element)
  def firstBySelector[T <: Element](selectors: String): Option[T] = dom.firstBySelector[T](selectors, element)
  def oneBySelector[T <: Element](selectors: String): T = dom.oneBySelector[T](selectors, element)
}
