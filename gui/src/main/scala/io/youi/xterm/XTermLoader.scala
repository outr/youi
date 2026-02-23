package io.youi.xterm

import io.youi.dom
import rapid.Task
import spice.net._

object XTermLoader {
  private val xtermVersion = "5.3.0"
  private val fitAddonVersion = "0.8.0"
  private val cssUrl = url"https://cdn.jsdelivr.net/npm/xterm@5.3.0/css/xterm.css"
  private val xtermUrl = url"https://cdn.jsdelivr.net/npm/xterm@5.3.0/lib/xterm.min.js"
  private val fitAddonUrl = url"https://cdn.jsdelivr.net/npm/xterm-addon-fit@0.8.0/lib/xterm-addon-fit.min.js"

  private var _loaded: Boolean = false
  def loaded: Boolean = _loaded

  def load(): Task[Unit] = if (_loaded) {
    Task.unit
  } else {
    dom.addCSS(cssUrl)
    dom.addScript(xtermUrl).flatMap { _ =>
      dom.addScript(fitAddonUrl).map { _ =>
        _loaded = true
      }
    }
  }
}
