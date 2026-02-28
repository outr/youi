package youi.xterm

import youi.dom
import rapid.Task
import spice.net._

import scala.scalajs.js

object XTermLoader {
  private val xtermVersion = "5.3.0"
  private val fitAddonVersion = "0.8.0"
  private val cssUrl = url"https://cdn.jsdelivr.net/npm/xterm@5.3.0/css/xterm.css"
  private val xtermUrl = url"https://cdn.jsdelivr.net/npm/xterm@5.3.0/lib/xterm.min.js"
  private val fitAddonUrl = url"https://cdn.jsdelivr.net/npm/xterm-addon-fit@0.8.0/lib/xterm-addon-fit.min.js"

  private var _loaded: Boolean = false
  def loaded: Boolean = _loaded

  // Temporarily hide AMD define so UMD scripts fall through to global assignment
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
    dom.addScript(xtermUrl).flatMap { _ =>
      dom.addScript(fitAddonUrl).map { _ =>
        restoreAmdDefine(savedDefine)
        _loaded = true
      }
    }
  }
}
