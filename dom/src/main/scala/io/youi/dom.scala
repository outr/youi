package io.youi

import org.scalajs.dom._
import org.scalajs.dom.ext._
import org.scalajs.dom.raw.HTMLElement

import scala.language.implicitConversions

object dom {
  def byTag[T <: Element](tagName: String): Vector[T] = document.body.byTag[T](tagName)
  def byClass[T <: Element](className: String): Vector[T] = document.body.byClass[T](className)
  def getById[T <: Element](id: String): Option[T] = Option(document.getElementById(id).asInstanceOf[T])
  def byId[T <: Element](id: String): T = {
    getById[T](id).getOrElse(throw new RuntimeException(s"Unable to find element by id '$id'."))
  }

  def create[T <: Element](tagName: String): T = document.createElement(tagName).asInstanceOf[T]

  implicit class StringExtras(s: String) {
    def toElement[T <: html.Element]: T = {
      val temp = document.createElement("div")
      temp.innerHTML = s
      val child = temp.firstChild.asInstanceOf[html.Element]
      child.asInstanceOf[T]
    }
  }

  implicit class ElementExtras(e: Element) {
    def byTag[T <: Element](tagName: String): Vector[T] = {
      e.getElementsByTagName(tagName).toVector.map(_.asInstanceOf[T])
    }
    def byClass[T <: Element](className: String): Vector[T] = {
      e.getElementsByClassName(className).toVector.map(_.asInstanceOf[T])
    }
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