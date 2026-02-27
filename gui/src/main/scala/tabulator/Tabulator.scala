package tabulator

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("Tabulator")
class Tabulator(element: org.scalajs.dom.Element, options: js.UndefOr[TabulatorOptions] = js.undefined) extends js.Object {
  // Data
  def setData(data: js.Array[js.Object]): js.Promise[Unit] = js.native
  def replaceData(data: js.Array[js.Object]): js.Promise[Unit] = js.native
  def updateData(data: js.Array[js.Object]): js.Promise[Unit] = js.native
  def addData(data: js.Array[js.Object], pos: js.UndefOr[Boolean] = js.undefined, index: js.UndefOr[js.Any] = js.undefined): js.Promise[js.Array[RowComponent]] = js.native
  def updateOrAddData(data: js.Array[js.Object]): js.Promise[js.Array[RowComponent]] = js.native
  def clearData(): Unit = js.native
  def getData(range: js.UndefOr[String] = js.undefined): js.Array[js.Object] = js.native
  def getDataCount(range: js.UndefOr[String] = js.undefined): Int = js.native
  def searchRows(field: String, `type`: String, value: js.Any): js.Array[RowComponent] = js.native
  def searchData(field: String, `type`: String, value: js.Any): js.Array[js.Object] = js.native

  // Row ops
  def addRow(data: js.Object, pos: js.UndefOr[Boolean] = js.undefined, index: js.UndefOr[js.Any] = js.undefined): js.Promise[RowComponent] = js.native
  def updateRow(row: js.Any, data: js.Object): js.Promise[Unit] = js.native
  def updateOrAddRow(row: js.Any, data: js.Object): js.Promise[Unit] = js.native
  def deleteRow(row: js.Any): js.Promise[Unit] = js.native
  def getRows(range: js.UndefOr[String] = js.undefined): js.Array[RowComponent] = js.native
  def getRow(row: js.Any): RowComponent = js.native
  def getRowPosition(row: js.Any): js.UndefOr[Int] = js.native
  def getRowFromPosition(pos: Int): RowComponent = js.native
  def scrollToRow(row: js.Any, position: js.UndefOr[String] = js.undefined, ifVisible: js.UndefOr[Boolean] = js.undefined): js.Promise[Unit] = js.native
  def moveRow(from: js.Any, to: js.Any, after: js.UndefOr[Boolean] = js.undefined): Unit = js.native

  // Column ops
  def getColumns(): js.Array[ColumnComponent] = js.native
  def getColumn(column: js.Any): ColumnComponent = js.native
  def addColumn(definition: js.Object, before: js.UndefOr[Boolean] = js.undefined, position: js.UndefOr[js.Any] = js.undefined): js.Promise[Unit] = js.native
  def deleteColumn(column: js.Any): js.Promise[Unit] = js.native
  def setColumns(definitions: js.Array[js.Object]): Unit = js.native
  def showColumn(column: js.Any): Unit = js.native
  def hideColumn(column: js.Any): Unit = js.native
  def toggleColumn(column: js.Any): Unit = js.native
  def scrollToColumn(column: js.Any, position: js.UndefOr[String] = js.undefined, ifVisible: js.UndefOr[Boolean] = js.undefined): js.Promise[Unit] = js.native

  // Sort
  def setSort(sorters: js.Any, dir: js.UndefOr[String] = js.undefined): Unit = js.native
  def getSorters(): js.Array[js.Object] = js.native
  def clearSort(): Unit = js.native

  // Filter
  def setFilter(field: js.Any, `type`: js.UndefOr[String] = js.undefined, value: js.UndefOr[js.Any] = js.undefined, params: js.UndefOr[js.Object] = js.undefined): Unit = js.native
  def addFilter(field: js.Any, `type`: js.UndefOr[String] = js.undefined, value: js.UndefOr[js.Any] = js.undefined, params: js.UndefOr[js.Object] = js.undefined): Unit = js.native
  def removeFilter(field: js.Any, `type`: js.UndefOr[String] = js.undefined, value: js.UndefOr[js.Any] = js.undefined): Unit = js.native
  def getFilters(includeHeader: js.UndefOr[Boolean] = js.undefined): js.Array[js.Object] = js.native
  def getHeaderFilters(): js.Array[js.Object] = js.native
  def setHeaderFilterValue(column: js.Any, value: js.Any): Unit = js.native
  def getHeaderFilterValue(column: js.Any): js.Any = js.native
  def setHeaderFilterFocus(column: js.Any): Unit = js.native
  def clearFilter(includeHeader: js.UndefOr[Boolean] = js.undefined): Unit = js.native
  def clearHeaderFilter(): Unit = js.native
  def refreshFilter(): Unit = js.native

  // Pagination
  def setPage(page: js.Any): js.Promise[Unit] = js.native
  def setPageToRow(row: js.Any): js.Promise[Unit] = js.native
  def setPageSize(size: Int): Unit = js.native
  def getPage(): js.Any = js.native
  def getPageMax(): js.Any = js.native
  def getPageSize(): Int = js.native
  def previousPage(): js.Promise[Unit] = js.native
  def nextPage(): js.Promise[Unit] = js.native

  // Group
  def setGroupBy(groups: js.Any): Unit = js.native
  def setGroupValues(values: js.Any): Unit = js.native
  def setGroupStartOpen(values: js.Any): Unit = js.native
  def setGroupHeader(values: js.Any): Unit = js.native
  def getGroups(): js.Array[GroupComponent] = js.native

  // Selection
  def selectRow(row: js.UndefOr[js.Any] = js.undefined): Unit = js.native
  def deselectRow(row: js.UndefOr[js.Any] = js.undefined): Unit = js.native
  def getSelectedRows(): js.Array[RowComponent] = js.native
  def getSelectedData(): js.Array[js.Object] = js.native

  // Layout
  def redraw(force: js.UndefOr[Boolean] = js.undefined): Unit = js.native
  def blockRedraw(): Unit = js.native
  def restoreRedraw(): Unit = js.native
  def setHeight(height: js.Any): Unit = js.native

  // History
  def undo(): Boolean = js.native
  def redo(): Boolean = js.native
  def getHistoryUndoSize(): Int = js.native
  def getHistoryRedoSize(): Int = js.native
  def clearHistory(): Unit = js.native

  // Download/Export
  def download(`type`: js.Any, filename: js.UndefOr[String] = js.undefined, options: js.UndefOr[js.Object] = js.undefined, range: js.UndefOr[String] = js.undefined): Unit = js.native
  def downloadToTab(`type`: String, options: js.UndefOr[js.Object] = js.undefined, range: js.UndefOr[String] = js.undefined): Unit = js.native
  def getHtml(range: js.UndefOr[String] = js.undefined, styled: js.UndefOr[Boolean] = js.undefined, config: js.UndefOr[js.Object] = js.undefined): String = js.native

  // Clipboard
  def copyToClipboard(range: js.UndefOr[String] = js.undefined): Unit = js.native

  // Navigation
  def navigatePrev(): Unit = js.native
  def navigateNext(): Unit = js.native
  def navigateLeft(): Unit = js.native
  def navigateRight(): Unit = js.native
  def navigateUp(): Unit = js.native
  def navigateDown(): Unit = js.native

  // Lifecycle
  def destroy(): Unit = js.native
  def on(event: String, callback: js.Function): Unit = js.native
  def off(event: String, callback: js.UndefOr[js.Function] = js.undefined): Unit = js.native

  // Validation
  def validate(): js.Any = js.native
  def clearCellValidation(cells: js.UndefOr[js.Any] = js.undefined): Unit = js.native

  // Alert
  def alert(message: String, style: js.UndefOr[String] = js.undefined): Unit = js.native
  def clearAlert(): Unit = js.native

  // Print
  def print(range: js.UndefOr[String] = js.undefined, style: js.UndefOr[Boolean] = js.undefined, config: js.UndefOr[js.Object] = js.undefined): Unit = js.native

  // Calculations
  def recalc(): Unit = js.native
  def getCalcResults(): js.Object = js.native
}

object Tabulator {
  def findTable(query: js.Any): js.Array[Tabulator] = js.Dynamic.global.Tabulator.findTable(query).asInstanceOf[js.Array[Tabulator]]
}
