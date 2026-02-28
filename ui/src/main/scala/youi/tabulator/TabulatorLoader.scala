package youi.tabulator

import youi.dom
import rapid.Task
import spice.net._

import scala.scalajs.js

object TabulatorLoader {
  private val version = "6.3.1"
  private val cdnBase = s"https://unpkg.com/tabulator-tables@$version/dist"
  val cssUrl: URL = url"https://unpkg.com/tabulator-tables@6.3.1/dist/css/tabulator.min.css"
  val darkCssUrl: URL = url"https://unpkg.com/tabulator-tables@6.3.1/dist/css/tabulator_midnight.min.css"
  private val jsUrl: URL = url"https://unpkg.com/tabulator-tables@6.3.1/dist/js/tabulator.min.js"
  private val luxonUrl: URL = url"https://cdn.jsdelivr.net/npm/luxon@3.5.0/build/global/luxon.min.js"

  private var _loaded: Boolean = false
  def loaded: Boolean = _loaded

  private def hideAmdDefine(): js.UndefOr[js.Dynamic] = {
    if (js.typeOf(js.Dynamic.global.define) != "undefined") {
      val saved = js.Dynamic.global.define
      js.Dynamic.global.define = js.undefined
      saved.asInstanceOf[js.UndefOr[js.Dynamic]]
    } else {
      js.undefined
    }
  }

  private def restoreAmdDefine(saved: js.UndefOr[js.Dynamic]): Unit = {
    saved.foreach { d =>
      js.Dynamic.global.define = d
    }
  }

  def load(): Task[Unit] = if (_loaded) {
    Task.unit
  } else {
    dom.addCSS(cssUrl)
    val savedDefine = hideAmdDefine()
    dom.addScript(luxonUrl).flatMap { _ =>
      dom.addScript(jsUrl).map { _ =>
        restoreAmdDefine(savedDefine)
        _loaded = true
      }
    }
  }

  def removeCSSByUrl(url: URL): Unit = {
    val urlStr = url.toString
    val links = org.scalajs.dom.document.head.querySelectorAll("link[rel=stylesheet]")
    for (i <- 0 until links.length) {
      val link = links(i).asInstanceOf[org.scalajs.dom.html.Link]
      if (link.href == urlStr) {
        link.parentNode.removeChild(link)
      }
    }
  }
}
