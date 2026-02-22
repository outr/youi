package io.youi

import rapid.Task
import rapid.task.Completable
import io.youi.util.Time
import org.scalajs.dom.{DOMList, Element, ErrorEvent, Event, HTMLElement, Node, Text, document, html, window}
import org.scalajs.dom.html.Div
import spice.net._

import scala.collection.mutable
import scala.concurrent.duration.DurationInt
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

  object create {
    def apply[T <: html.Element](tagName: String): T = document.createElement(tagName).asInstanceOf[T]

    def anchor: html.Anchor = create[html.Anchor]("a")
    def br: html.BR = create[html.BR]("br")
    def button: html.Button = create[html.Button]("button")
    def canvas: html.Canvas = create[html.Canvas]("canvas")
    def code: html.Element = create[html.Element]("code")
    def datalist: html.DataList = create[html.DataList]("datalist")
    def div: html.Div = create[html.Div]("div")
    def em: html.Element = create[html.Element]("em")
    def hr: html.HR = create[html.HR]("hr")
    def image: html.Image = create[html.Image]("img")
    def i: html.Element = create[html.Element]("i")
    def iframe: html.IFrame = create[html.IFrame]("iframe")
    def input: html.Input = create[html.Input]("input")
    def label: html.Label = create[html.Label]("label")
    def option: html.Option = create[html.Option]("option")
    def pre: html.Pre = create[html.Pre]("pre")
    def script: html.Script = create[html.Script]("script")
    def select: html.Select = create[html.Select]("select")
    def style: html.Style = create[html.Style]("style")
    def span: html.Span = create[html.Span]("span")
    def text(value: String): Text = document.createTextNode(value)
    def textArea: html.TextArea = create[html.TextArea]("textarea")
  }

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

  private def loadedScripts: List[String] = (document.head.byTag[html.Script]("script").toList ::: byTag[html.Script]("script").toList).collect {
    case script if Option(script.src).nonEmpty && script.src.nonEmpty => script.src
  }

  sealed trait ScriptStatus

  object ScriptStatus {
    case object NotAdded extends ScriptStatus
    case object Loading extends ScriptStatus
    case object Loaded extends ScriptStatus
  }

  private lazy val addedScripts: mutable.Map[String, ScriptStatus] = mutable.Map(
    loadedScripts.map { script =>
      script -> ScriptStatus.Loaded
    }*
  )

  private def scriptStatus(url: URL): ScriptStatus = addedScripts.getOrElse(url.toString(), ScriptStatus.NotAdded)

  def addScript(url: URL, addToHead: Boolean = false): Task[Unit] = scriptStatus(url) match {
    case ScriptStatus.NotAdded =>
      val script = create.script
      script.addEventListener("load", (_: Event) => {
        addedScripts += url.toString() -> ScriptStatus.Loaded
      })
      script.src = url.toString()
      if (addToHead) {
        document.head.appendChild(script)
      } else {
        document.body.appendChild(script)
      }
      Time.waitFor(scriptStatus(url) == ScriptStatus.Loaded, timeout = 30.seconds)
    case ScriptStatus.Loading =>
      Time.waitFor(scriptStatus(url) == ScriptStatus.Loaded, timeout = 30.seconds)
    case ScriptStatus.Loaded => Task.unit
  }

  def getCSSVariable(name: String): String = {
    val varName = if (name.startsWith("--")) name else s"--$name"
    window.getComputedStyle(document.documentElement).getPropertyValue(varName)
  }

  def setCSSVariable(name: String, value: String): Unit = {
    val varName = if (name.startsWith("--")) name else s"--$name"
    document.documentElement.asInstanceOf[html.Element].style.setProperty(varName, value)
  }

  def addCSS(css: String): Unit = {
    val style = create.style
    style.innerHTML = css
    document.head.appendChild(style)
  }

  def addCSS(url: URL): Unit = {
    val link = create[html.Link]("link")
    link.href = url.toString
    link.setAttribute("rel", "stylesheet")
    link.setAttribute("as", "style")
    link.setAttribute("crossorigin", "crossorigin")
    document.head.appendChild(link)
  }

  /**
    * Forces loading of the supplied image URL in a hidden iframe.
    *
    * @param url the image URL to load
    * @return Either[Throwable, URL]
    */
  def reloadImage(url: URL): Task[Either[Throwable, URL]] = {
    val iframe = create.iframe
    iframe.style.display = "none"
    document.body.appendChild(iframe)
    var counter = 0
    val c: Completable[Either[Throwable, URL]] = Task.completable[Either[Throwable, URL]]
    iframe.addEventListener("load", (_: Event) => {
      if (counter == 0) {
        counter += 1
        iframe.contentWindow.location.reload()
      } else {
        c.success(Right(url))
      }
    }, useCapture = false)
    val src = if (url.base != History.url().base) { // Cross-Domain
      History.url().withPath(path"/wrap-image").withParam("src", url.toString).toString
    } else {
      url.toString
    }
    iframe.src = src
    iframe.addEventListener("error", (evt: ErrorEvent) => {
      c.success(Left(new RuntimeException(s"Error loading: $url - ${evt.message}")))
    }, useCapture = false)
    c
  }

  implicit class StringExtras(s: String) {
    def toElement[T <: Element]: T = {
      val temp = document.createElement("div")
      temp.innerHTML = s
      val child = temp.firstChild.asInstanceOf[Element]
      child.asInstanceOf[T]
    }
  }

  // Existing HTML features that Scala.js doesn't already provide
  implicit def element2Features(element: html.Element): ElementFeatures = element.asInstanceOf[ElementFeatures]

  implicit class ElementExtras[E <: Element](e: E) extends ExtendedElement(Some(e)) {
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

    def insertAfter(previous: Node): Unit = Option(previous.nextSibling) match {
      case Some(next) => previous.parentNode.insertBefore(e, next)
      case None => previous.parentNode.appendChild(e)
    }

    def insertFirst(parent: Node): Unit = Option(parent.firstChild) match {
      case Some(first) => parent.insertBefore(e, first)
      case None => parent.appendChild(e)
    }

    def addChildren(children: Element*): E = {
      children.foreach { child =>
        e.appendChild(child)
      }
      e
    }

    def verifyChildrenInOrder(children: Element*): E = {
      if (children.nonEmpty) {
        val collection = e.children
        val head = children.head
        var lastIndex = collection.indexOf(head)
        if (lastIndex == -1) {
          head.insertFirst(e)
          lastIndex = 0
        }
        children.tail.foreach { child =>
          lastIndex = collection.indexOf(child) match {
            case n if n == -1 || n < lastIndex => {
              if (n != -1) child.remove()
              e.appendChild(child)
              collection.indexOf(child)
            }
            case n => n
          }
        }
      }
      e
    }

    def addClasses(classes: String*): E = {
      classes.foreach(c => e.classList.add(c))
      e
    }

    def removeClasses(classes: String*): E = {
      classes.foreach(c => e.classList.remove(c))
      e
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
      list(position)
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
