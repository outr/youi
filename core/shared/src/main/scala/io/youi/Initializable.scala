package io.youi

trait Initializable {
  private var initialized = false

  final def init(): Unit = synchronized {
    if (!initialized) {
      initialized = true
      initialize()
    }
  }

  protected def initialize(): Unit
}