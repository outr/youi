package io.youi.app.screen
import io.youi.app.{HistoryStateChange, StateType}
import io.youi.net.URL

trait PathActivation extends URLActivation {
  this: Screen =>

  def path: String
  def stateType: StateType = StateType.Push
  def clearParams: Boolean = false

  override def isURLMatch(url: URL): Boolean = url.path.decoded == path

  override def updateURL(current: URL): Option[HistoryStateChange] = if (current.path.decoded != path) {
    val url = if (clearParams) {
      current.replacePathAndParams(path)
    } else {
      current.withPath(path)
    }
    Some(HistoryStateChange(current.withPath(path), stateType))
  } else {
    None
  }
}