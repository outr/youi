package io.youi

import spice.net.URL

import scala.scalajs.js

case class HistoryStateChange(url: URL, stateType: StateType, state: js.Any = null)
