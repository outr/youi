package io.youi

import org.scalajs.dom._
import org.scalajs.dom.ext._
import org.scalajs.dom.html.Div
import org.scalajs.dom.raw.HTMLElement

import scala.collection.mutable
import scala.concurrent.{Future, Promise}
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

  private lazy val addedScripts = mutable.Map((document.head.byTag[html.Script]("script").toList ::: byTag[html.Script]("script").toList).collect {
    case script if Option(script.src).nonEmpty && script.src.nonEmpty => script.src
  }.map(_ -> Future.successful(())): _*)

  def addScript(url: String, addToHead: Boolean = false): Future[Unit] = addedScripts.get(url) match {
    case Some(f) => f
    case None => {
      val promise = Promise[Unit]
      val script = create[html.Script]("script")
      script.addEventListener("load", (_: Event) => {
        promise.success(())
      })
      script.src = url
      if (addToHead) {
        document.head.appendChild(script)
      } else {
        document.body.appendChild(script)
      }
      val future = promise.future
      addedScripts += url -> future
      future
    }
  }

  def addCSS(css: String): Unit = {
    val style = create[html.Style]("style")
    style.innerHTML = css
    document.head.appendChild(style)
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

    def removeIds(): Unit = {
      e.removeAttribute("id")
      e.bySelector[html.Element]("[id]").foreach(_.removeAttribute("id"))
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

  def jumpToTop(): Unit = window.scroll(0, 0)

  def stopAllMedia(except: Option[html.Element] = None): Unit = {
    dom.bySelector[html.Video]("video").foreach { video =>
      if (!except.contains(video)) {
        video.pause()
      }
    }
    dom.bySelector[html.Audio]("audio").foreach { audio =>
      if (!except.contains(audio)) {
        audio.pause()
      }
    }
  }

  def autoStopOnPlay(): Unit = {
    dom.bySelector[html.Video]("video").foreach { video =>
      video.onplay = (_: Event) => {
        stopAllMedia(Some(video))
      }
    }
    dom.bySelector[html.Audio]("audio").foreach { audio =>
      audio.onplay = (_: Event) => {
        stopAllMedia(Some(audio))
      }
    }
  }
}