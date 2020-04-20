package io.youi

object BackgroundUpdates extends UpdateSupport {
  override protected def run(): Unit = window.setTimeout(() => updateFunction(System.currentTimeMillis()), 100)
}
