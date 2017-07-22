package io.youi.app.screen

import io.youi.{HistoryStateChange, StateType, http}
import io.youi.net.{URL, URLMatcher}

trait PathActivation extends URLActivation {
  def path: String
  def stateType: StateType = StateType.Push
  def clearParams: Boolean = false

  override val matcher: URLMatcher = http.path.exact(path)

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