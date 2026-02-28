package youi.app.screen

import rapid.Task
import org.scalajs.dom.html

trait LoadingTransitionSupport extends ScreenManager {
  protected def loadingElement: html.Element

  override protected def beforeScreenChange(oldScreen: Screen, newScreen: Screen): Task[Unit] = Task {
    loadingElement.classList.remove("hidden")
  }

  override protected def afterScreenChange(oldScreen: Screen, newScreen: Screen): Task[Unit] = Task {
    loadingElement.classList.add("hidden")
  }
}
