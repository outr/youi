package io.youi.app.screen

import io.youi.{HistoryStateChange, StateType, http}
import io.youi.net.{Parameters, Path, URL, URLMatcher}

trait PathActivation extends URLActivation {
  def path: Path
  def stateType: StateType = StateType.Push
  def clearParams: Boolean = false

  override lazy val matcher: URLMatcher = http.path.exact(path)

  override def updateURL(current: URL): Option[HistoryStateChange] = if (current.path != path) {
    val url = if (clearParams) {
      current.copy(path = path, parameters = Parameters.empty)
    } else {
      current.withPath(path)
    }
    Some(HistoryStateChange(url, stateType))
  } else {
    None
  }
}