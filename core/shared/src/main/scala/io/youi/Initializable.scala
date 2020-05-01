package io.youi

trait Initializable {
  private var initialized = false

  final def init(): Unit = synchronized {
    if (!initialized) {
      initialize()
      initialized = true
    }
  }

  protected def initialize(): Unit
}
