package io.youi.app.screen

import io.youi.{HistoryStateChange, StateType}
import spice.net.{Parameters, URLPath, URL, URLMatcher}
import spice.http

trait PathActivation extends URLActivation {
  def path: URLPath
  def stateType: StateType = StateType.Push
  def clearParams: Boolean = false

  override lazy val matcher: URLMatcher = new URLMatcher {
    override def matches(url: URL): Boolean = http.paths.exact(path).matches(url)
  }

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