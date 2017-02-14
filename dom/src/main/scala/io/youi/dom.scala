package io.youi

import org.scalajs.dom._
import org.scalajs.dom.ext._
import org.scalajs.dom.html.Div
import org.scalajs.dom.raw.HTMLElement

import scala.language.implicitConversions

object dom extends ExtendedElement(None) {
  def bySelector[T <: Element](selectors: String, root: Option[Element] = None): Vector[T] = {
    root.map(_.querySelectorAll(selectors)).getOrElse(document.querySelectorAll(selectors)).toVector.asInstanceOf[Vector[T]]
  }

  def firstBySelector[T <: Element](selectors: String, root: Option[Element] = None): Option[T] = {
    bySelector[T](selectors, root).headOption
  }

  def oneBySelector[T <: Element](selectors: String, root: Option[Element] = None): T = {
    firstBySelector[T](selectors, root).getOrElse(throw new RuntimeException(s"Unable to find element by selector: $selectors."))
  }

  def create[T <: Element](tagName: String): T = document.createElement(tagName).asInstanceOf[T]

  def fromString[T <: Element](htmlString: String): List[T] = {
    val container: Div = create[Div]("div")
    container.innerHTML = htmlString
    container.childNodes.toList.collect {
      case e: Element => {
        container.removeChild(e)
        e.asInstanceOf[T]
      }
    }
  }

  implicit class StringExtras(s: String) {
    def toElement[T <: Element]: T = {
      val temp = document.createElement("div")
      temp.innerHTML = s
      val child = temp.firstChild.asInstanceOf[Element]
      child.asInstanceOf[T]
    }
  }

  implicit class ElementExtras(e: Element) extends ExtendedElement(Some(e)) {
    def parentByTag[T <: HTMLElement](tagName: String): Option[T] = findParentRecursive[T](e.asInstanceOf[HTMLElement], (p: HTMLElement) => {
      p.tagName == tagName
    })
    def parentByClass[T <: HTMLElement](className: String): Option[T] = findParentRecursive[T](e.asInstanceOf[HTMLElement], (p: HTMLElement) => {
      p.classList.contains(className)
    })

    private def findParentRecursive[T <: HTMLElement](e: HTMLElement, finder: HTMLElement => Boolean): Option[T] = {
      Option(e.parentElement).flatMap { p =>
        if (finder(p)) {
          Some(p.asInstanceOf[T])
        } else {
          findParentRecursive[T](p, finder)
        }
      }
    }

    def insertAfter(previous: Element): Unit = Option(previous.nextElementSibling) match {
      case Some(next) => previous.parentNode.insertBefore(e, next)
      case None => previous.parentNode.appendChild(e)
    }

    def insertFirst(parent: Element): Unit = Option(parent.firstElementChild) match {
      case Some(first) => parent.insertBefore(e, first)
      case None => parent.appendChild(e)
    }

    def remove(): Unit = Option(e.parentNode).foreach(_.removeChild(e))
  }

  implicit def domListToIterator[T](list: DOMList[T]): Iterator[T] = new Iterator[T] {
    private var position = -1

    override def hasNext: Boolean = list.length > position + 1

    override def next(): T = {
      position += 1
      list.item(position)
    }
  }
}

class ExtendedElement(element: Option[Element]) {
  def byTag[T <: Element](tagName: String): Vector[T] = dom.bySelector[T](tagName, element)
  def byClass[T <: Element](className: String): Vector[T] = dom.bySelector[T](s".$className", element)
  def getById[T <: Element](id: String): Option[T] = dom.firstBySelector[T](s"#$id", element)
  def byId[T <: Element](id: String): T = dom.oneBySelector[T](s"#$id", element)
  def bySelector[T <: Element](selectors: String): Vector[T] = dom.bySelector[T](selectors, element)
  def firstBySelector[T <: Element](selectors: String): Option[T] = dom.firstBySelector[T](selectors, element)
  def oneBySelector[T <: Element](selectors: String): T = dom.oneBySelector[T](selectors, element)
}