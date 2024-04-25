package typekit

import scala.scalajs.js

trait WebFontConfiguration extends js.Object {
  var timeout: js.UndefOr[Long] = js.undefined

  var google: js.UndefOr[GoogleConfig] = js.undefined
  var typekit: js.UndefOr[TypekitConfig] = js.undefined
  var custom: js.UndefOr[CustomConfig] = js.undefined
  var fontdeck: js.UndefOr[FontdeckConfig] = js.undefined
  var monotype: js.UndefOr[MonotypeConfig] = js.undefined

  // Events
  var loading: js.UndefOr[js.Function0[Unit]] = js.undefined
  var active: js.UndefOr[js.Function0[Unit]] = js.undefined
  var inactive: js.UndefOr[js.Function0[Unit]] = js.undefined
  var fontloading: js.UndefOr[js.Function0[Unit]] = js.undefined
  var fontactive: js.UndefOr[js.Function0[Unit]] = js.undefined
  var fontinactive: js.UndefOr[js.Function0[Unit]] = js.undefined
}
