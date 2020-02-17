package io.youi

import io.youi.net.URL

import scala.scalajs.js

case class HistoryStateChange(url: URL, stateType: StateType, state: js.Any = null)
