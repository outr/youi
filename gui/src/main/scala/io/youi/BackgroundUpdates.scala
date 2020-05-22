package io.youi

import org.scalajs.dom.window

object BackgroundUpdates extends UpdateSupport {
  override protected def run(): Unit = window.setTimeout(() => updateFunction(System.currentTimeMillis().toDouble), 100)
}