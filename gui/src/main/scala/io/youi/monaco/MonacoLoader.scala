package io.youi.monaco

import io.youi.dom
import rapid.Task
import spice.net._

import scala.concurrent.duration._
import scala.scalajs.js

object MonacoLoader {
  private val monacoVersion = "0.52.0"
  private val cdnBase = s"https://cdn.jsdelivr.net/npm/monaco-editor@$monacoVersion/min"
  private val loaderUrl = url"https://cdn.jsdelivr.net/npm/monaco-editor@0.52.0/min/vs/loader.js"

  private var _loaded: Boolean = false
  def loaded: Boolean = _loaded

  def load(): Task[Unit] = if (_loaded) {
    Task.unit
  } else {
    dom.addScript(loaderUrl).flatMap { _ =>
      val require = js.Dynamic.global.require
      require.config(js.Dynamic.literal(
        paths = js.Dynamic.literal(
          vs = s"$cdnBase/vs"
        )
      ))
      require(
        js.Array("vs/editor/editor.main"),
        { () =>
          _loaded = true
        }: js.Function0[Unit],
        { (err: js.Any) =>
          scribe.error(s"Monaco AMD require error: $err")
        }: js.Function1[js.Any, Unit]
      )
      waitForLoaded()
    }
  }

  private def waitForLoaded(): Task[Unit] = if (_loaded) {
    Task.unit
  } else {
    Task.sleep(100.millis).flatMap(_ => waitForLoaded())
  }
}
