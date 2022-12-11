package io.youi.app.screen

import io.youi.{HistoryStateChange, StateType}
import spice.net.{Parameters, Path, URL, URLMatcher}
import spice.http

trait PathActivation extends URLActivation {
  def path: Path
  def stateType: StateType = StateType.Push
  def clearParams: Boolean = false

  override lazy val matcher: URLMatcher = http.paths.exact(path)

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