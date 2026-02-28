package tabulator

import scala.scalajs.js

trait SorterDefinition extends js.Object {
  var column: js.UndefOr[String] = js.undefined
  var dir: js.UndefOr[String] = js.undefined
}

trait FilterDefinition extends js.Object {
  var field: js.UndefOr[String] = js.undefined
  var `type`: js.UndefOr[String] = js.undefined
  var value: js.UndefOr[js.Any] = js.undefined
}

trait DownloadConfig extends js.Object {
  var columnHeaders: js.UndefOr[Boolean] = js.undefined
  var columnGroups: js.UndefOr[Boolean] = js.undefined
  var rowHeaders: js.UndefOr[Boolean] = js.undefined
  var rowGroups: js.UndefOr[Boolean] = js.undefined
  var columnCalcs: js.UndefOr[Boolean] = js.undefined
  var dataTree: js.UndefOr[Boolean] = js.undefined
}

trait PrintConfig extends js.Object {
  var columnHeaders: js.UndefOr[Boolean] = js.undefined
  var columnGroups: js.UndefOr[Boolean] = js.undefined
  var rowHeaders: js.UndefOr[Boolean] = js.undefined
  var rowGroups: js.UndefOr[Boolean] = js.undefined
  var columnCalcs: js.UndefOr[Boolean] = js.undefined
  var dataTree: js.UndefOr[Boolean] = js.undefined
}

trait AjaxConfig extends js.Object {
  var method: js.UndefOr[String] = js.undefined
  var headers: js.UndefOr[js.Object] = js.undefined
  var credentials: js.UndefOr[String] = js.undefined
}

trait SpreadsheetSheetDefinition extends js.Object {
  var title: js.UndefOr[String] = js.undefined
  var key: js.UndefOr[js.Any] = js.undefined
  var data: js.UndefOr[js.Array[js.Array[js.Any]]] = js.undefined
  var columns: js.UndefOr[js.Array[ColumnDefinition]] = js.undefined
}
