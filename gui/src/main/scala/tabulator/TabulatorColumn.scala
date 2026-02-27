package tabulator

import scala.scalajs.js

trait ColumnDefinition extends js.Object {
  // General
  var title: js.UndefOr[String] = js.undefined
  var field: js.UndefOr[String] = js.undefined
  var visible: js.UndefOr[Boolean] = js.undefined
  var cssClass: js.UndefOr[String] = js.undefined
  var variableHeight: js.UndefOr[Boolean] = js.undefined

  // Layout
  var hozAlign: js.UndefOr[String] = js.undefined
  var vertAlign: js.UndefOr[String] = js.undefined
  var headerHozAlign: js.UndefOr[String] = js.undefined
  var width: js.UndefOr[js.Any] = js.undefined
  var minWidth: js.UndefOr[Int] = js.undefined
  var maxWidth: js.UndefOr[Int] = js.undefined
  var widthGrow: js.UndefOr[Int] = js.undefined
  var widthShrink: js.UndefOr[Int] = js.undefined
  var resizable: js.UndefOr[js.Any] = js.undefined
  var frozen: js.UndefOr[Boolean] = js.undefined
  var responsive: js.UndefOr[Int] = js.undefined
  var rowHandle: js.UndefOr[Boolean] = js.undefined

  // Formatting
  var formatter: js.UndefOr[js.Any] = js.undefined
  var formatterParams: js.UndefOr[js.Any] = js.undefined
  var formatterPrint: js.UndefOr[js.Any] = js.undefined
  var formatterPrintParams: js.UndefOr[js.Any] = js.undefined
  var formatterClipboard: js.UndefOr[js.Any] = js.undefined
  var formatterClipboardParams: js.UndefOr[js.Any] = js.undefined
  var formatterHtmlOutput: js.UndefOr[js.Any] = js.undefined
  var formatterHtmlOutputParams: js.UndefOr[js.Any] = js.undefined

  // Editing
  var editable: js.UndefOr[js.Any] = js.undefined
  var editor: js.UndefOr[js.Any] = js.undefined
  var editorParams: js.UndefOr[js.Any] = js.undefined
  var editorEmptyValue: js.UndefOr[js.Any] = js.undefined
  var validator: js.UndefOr[js.Any] = js.undefined

  // Sorting
  var sorter: js.UndefOr[js.Any] = js.undefined
  var sorterParams: js.UndefOr[js.Any] = js.undefined
  var headerSort: js.UndefOr[Boolean] = js.undefined
  var headerSortStartingDir: js.UndefOr[String] = js.undefined
  var headerSortTristate: js.UndefOr[Boolean] = js.undefined

  // Filtering
  var headerFilter: js.UndefOr[js.Any] = js.undefined
  var headerFilterPlaceholder: js.UndefOr[String] = js.undefined
  var headerFilterParams: js.UndefOr[js.Any] = js.undefined
  var headerFilterFunc: js.UndefOr[js.Any] = js.undefined
  var headerFilterFuncParams: js.UndefOr[js.Any] = js.undefined
  var headerFilterLiveFilter: js.UndefOr[Boolean] = js.undefined
  var headerFilterEmptyCheck: js.UndefOr[js.Function] = js.undefined

  // Calculations
  var topCalc: js.UndefOr[js.Any] = js.undefined
  var topCalcParams: js.UndefOr[js.Any] = js.undefined
  var topCalcFormatter: js.UndefOr[js.Any] = js.undefined
  var topCalcFormatterParams: js.UndefOr[js.Any] = js.undefined
  var bottomCalc: js.UndefOr[js.Any] = js.undefined
  var bottomCalcParams: js.UndefOr[js.Any] = js.undefined
  var bottomCalcFormatter: js.UndefOr[js.Any] = js.undefined
  var bottomCalcFormatterParams: js.UndefOr[js.Any] = js.undefined

  // Mutators
  var mutator: js.UndefOr[js.Function] = js.undefined
  var mutatorParams: js.UndefOr[js.Any] = js.undefined
  var mutatorData: js.UndefOr[js.Function] = js.undefined
  var mutatorDataParams: js.UndefOr[js.Any] = js.undefined
  var mutatorEdit: js.UndefOr[js.Function] = js.undefined
  var mutatorEditParams: js.UndefOr[js.Any] = js.undefined
  var mutatorClipboard: js.UndefOr[js.Function] = js.undefined
  var mutatorClipboardParams: js.UndefOr[js.Any] = js.undefined
  var mutatorImport: js.UndefOr[js.Function] = js.undefined
  var mutatorImportParams: js.UndefOr[js.Any] = js.undefined
  var mutateLink: js.UndefOr[js.Any] = js.undefined

  // Accessors
  var accessor: js.UndefOr[js.Function] = js.undefined
  var accessorParams: js.UndefOr[js.Any] = js.undefined
  var accessorData: js.UndefOr[js.Function] = js.undefined
  var accessorDataParams: js.UndefOr[js.Any] = js.undefined
  var accessorDownload: js.UndefOr[js.Function] = js.undefined
  var accessorDownloadParams: js.UndefOr[js.Any] = js.undefined
  var accessorClipboard: js.UndefOr[js.Function] = js.undefined
  var accessorClipboardParams: js.UndefOr[js.Any] = js.undefined

  // Header
  var headerVertical: js.UndefOr[js.Any] = js.undefined
  var editableTitle: js.UndefOr[Boolean] = js.undefined
  var titleFormatter: js.UndefOr[js.Any] = js.undefined
  var titleFormatterParams: js.UndefOr[js.Any] = js.undefined
  var headerWordWrap: js.UndefOr[Boolean] = js.undefined
  var headerTooltip: js.UndefOr[js.Any] = js.undefined

  // Menus/Popups
  var contextMenu: js.UndefOr[js.Any] = js.undefined
  var clickMenu: js.UndefOr[js.Any] = js.undefined
  var dblClickMenu: js.UndefOr[js.Any] = js.undefined
  var contextPopup: js.UndefOr[js.Any] = js.undefined
  var clickPopup: js.UndefOr[js.Any] = js.undefined
  var dblClickPopup: js.UndefOr[js.Any] = js.undefined
  var headerMenu: js.UndefOr[js.Any] = js.undefined
  var headerClickMenu: js.UndefOr[js.Any] = js.undefined
  var headerContextMenu: js.UndefOr[js.Any] = js.undefined
  var headerPopup: js.UndefOr[js.Any] = js.undefined
  var headerClickPopup: js.UndefOr[js.Any] = js.undefined
  var headerContextPopup: js.UndefOr[js.Any] = js.undefined

  // Cell events
  var cellClick: js.UndefOr[js.Function] = js.undefined
  var cellDblClick: js.UndefOr[js.Function] = js.undefined
  var cellContext: js.UndefOr[js.Function] = js.undefined
  var cellTap: js.UndefOr[js.Function] = js.undefined
  var cellDblTap: js.UndefOr[js.Function] = js.undefined
  var cellTapHold: js.UndefOr[js.Function] = js.undefined
  var cellMouseEnter: js.UndefOr[js.Function] = js.undefined
  var cellMouseLeave: js.UndefOr[js.Function] = js.undefined
  var cellMouseOver: js.UndefOr[js.Function] = js.undefined
  var cellMouseOut: js.UndefOr[js.Function] = js.undefined
  var cellMouseMove: js.UndefOr[js.Function] = js.undefined
  var cellMouseDown: js.UndefOr[js.Function] = js.undefined
  var cellMouseUp: js.UndefOr[js.Function] = js.undefined
  var cellEditing: js.UndefOr[js.Function] = js.undefined
  var cellEdited: js.UndefOr[js.Function] = js.undefined
  var cellEditCancelled: js.UndefOr[js.Function] = js.undefined

  // Header events
  var headerClick: js.UndefOr[js.Function] = js.undefined
  var headerDblClick: js.UndefOr[js.Function] = js.undefined
  var headerContext: js.UndefOr[js.Function] = js.undefined
  var headerTap: js.UndefOr[js.Function] = js.undefined
  var headerDblTap: js.UndefOr[js.Function] = js.undefined
  var headerTapHold: js.UndefOr[js.Function] = js.undefined
  var headerMouseEnter: js.UndefOr[js.Function] = js.undefined
  var headerMouseLeave: js.UndefOr[js.Function] = js.undefined
  var headerMouseOver: js.UndefOr[js.Function] = js.undefined
  var headerMouseOut: js.UndefOr[js.Function] = js.undefined
  var headerMouseMove: js.UndefOr[js.Function] = js.undefined
  var headerMouseDown: js.UndefOr[js.Function] = js.undefined
  var headerMouseUp: js.UndefOr[js.Function] = js.undefined

  // Export
  var download: js.UndefOr[js.Any] = js.undefined
  var titleDownload: js.UndefOr[String] = js.undefined
  var htmlOutput: js.UndefOr[js.Any] = js.undefined
  var print: js.UndefOr[js.Any] = js.undefined
  var clipboard: js.UndefOr[js.Any] = js.undefined
  var tooltip: js.UndefOr[js.Any] = js.undefined

  // Column groups (nested columns)
  var columns: js.UndefOr[js.Array[ColumnDefinition]] = js.undefined
}

@js.native
trait ColumnComponent extends js.Object {
  def getElement(): org.scalajs.dom.Element = js.native
  def getTable(): Tabulator = js.native
  def getDefinition(): ColumnDefinition = js.native
  def updateDefinition(definition: ColumnDefinition): js.Promise[Unit] = js.native
  def getField(): String = js.native
  def getCells(): js.Array[CellComponent] = js.native
  def getNextColumn(): js.UndefOr[ColumnComponent] = js.native
  def getPrevColumn(): js.UndefOr[ColumnComponent] = js.native
  def isVisible(): Boolean = js.native
  def show(): Unit = js.native
  def hide(): Unit = js.native
  def toggle(): Unit = js.native
  def getWidth(): Int = js.native
  def setWidth(width: js.Any): Unit = js.native
  def delete(): js.Promise[Unit] = js.native
  def scrollTo(position: js.UndefOr[String] = js.undefined, ifVisible: js.UndefOr[Boolean] = js.undefined): js.Promise[Unit] = js.native
  def move(toColumn: js.Any, after: js.UndefOr[Boolean] = js.undefined): Unit = js.native
  def getSubColumns(): js.Array[ColumnComponent] = js.native
  def getParentColumn(): js.UndefOr[ColumnComponent] = js.native
  def headerFilterFocus(): Unit = js.native
  def setHeaderFilterValue(value: js.Any): Unit = js.native
  def getHeaderFilterValue(): js.Any = js.native
  def reloadHeaderFilter(): Unit = js.native
  def validate(): js.Any = js.native
  def popup(contents: js.Any, position: js.UndefOr[String] = js.undefined): Unit = js.native
}
