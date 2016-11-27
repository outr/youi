package io.youi

import com.outr.scribe.Logging
import org.scalajs.dom._
import org.scalajs.dom.raw.HTMLElement

import scala.annotation.tailrec
import scala.language.implicitConversions

package object html extends Logging {
  def byTag[T <: HTMLElement](tagName: String): Vector[T] = {
    document.getElementsByTagName(tagName).toVector.map(_.asInstanceOf[T])
  }
  def byClass[T <: HTMLElement](className: String): Vector[T] = {
    document.getElementsByClassName(className).toVector.map(_.asInstanceOf[T])
  }

  def byId[T <: HTMLElement](id: String): T = Option(document.getElementById(id).asInstanceOf[T]) match {
    case Some(t) => t
    case None => {
      val message = s"Unable to find element by id '$id'."
      logger.error(message)
      throw new RuntimeException(message)
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

  implicit class HTMLElementExtras(e: HTMLElement) {
    def byTag[T <: HTMLElement](tagName: String): Vector[T] = {
      e.getElementsByTagName(tagName).toVector.map(_.asInstanceOf[T])
    }
    def byClass[T <: HTMLElement](className: String): Vector[T] = {
      e.getElementsByClassName(className).toVector.map(_.asInstanceOf[T])
    }
    def parentByTag[T <: HTMLElement](tagName: String): Option[T] = findParentRecursive[T](e, (p: HTMLElement) => {
      p.tagName == tagName
    })
    def parentByClass[T <: HTMLElement](className: String): Option[T] = findParentRecursive[T](e, (p: HTMLElement) => {
      p.classList.contains(className)
    })

    private def findParentRecursive[T <: HTMLElement](e: HTMLElement, finder: HTMLElement => Boolean): Option[T] = Option(e.parentElement) match {
      case None => None
      case Some(p) => if (finder(p)) {
        Some(p.asInstanceOf[T])
      } else {
        findParentRecursive(p, finder)
      }
    }
  }
}
