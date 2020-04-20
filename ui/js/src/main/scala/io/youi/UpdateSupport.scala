package io.youi

trait UpdateSupport extends TaskSupport {
  private var lastUpdate: Double = 0.0

  val timeStamp: Val[Double] = Var(0.0)

  protected val updateFunction: Double => Unit = (highResTimeStamp: Double) => {
    val delta = if (lastUpdate == 0.0) {
      0.0
    } else {
      (highResTimeStamp - lastUpdate) / 1000.0
    }
    try {
      this.timeStamp.asInstanceOf[Var[Double]] @= highResTimeStamp
      update(delta)
    } finally {
      lastUpdate = highResTimeStamp
      run()
    }
  }

  run()

  protected def run(): Unit
}
