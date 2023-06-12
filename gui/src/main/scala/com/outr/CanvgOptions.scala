package com.outr

import scala.scalajs.js

trait CanvgOptions extends js.Object {
  var log: js.UndefOr[Boolean] = js.undefined
  var ignoreMouse: js.UndefOr[Boolean] = js.undefined
  var ignoreAnimation: js.UndefOr[Boolean] = js.undefined
  var ignoreDimensions: js.UndefOr[Boolean] = js.undefined
  var ignoreClear: js.UndefOr[Boolean] = js.undefined
  var offsetX: js.UndefOr[Int] = js.undefined
  var offsetY: js.UndefOr[Int] = js.undefined
  var scaleWidth: js.UndefOr[Int] = js.undefined
  var scaleHeight: js.UndefOr[Int] = js.undefined
  var renderCallback: js.UndefOr[js.Function] = js.undefined
  var forceRedraw: js.UndefOr[js.Function0[Boolean]] = js.undefined
  var useCORS: js.UndefOr[Boolean] = js.undefined
}
