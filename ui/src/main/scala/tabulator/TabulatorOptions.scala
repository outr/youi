package tabulator

import scala.scalajs.js

trait TabulatorOptions extends js.Object {
  // General
  var height: js.UndefOr[js.Any] = js.undefined
  var minHeight: js.UndefOr[js.Any] = js.undefined
  var maxHeight: js.UndefOr[js.Any] = js.undefined
  var placeholder: js.UndefOr[js.Any] = js.undefined
  var footerElement: js.UndefOr[js.Any] = js.undefined
  var reactiveData: js.UndefOr[Boolean] = js.undefined
  var textDirection: js.UndefOr[String] = js.undefined
  var index: js.UndefOr[String] = js.undefined

  // Column configuration
  var columns: js.UndefOr[js.Array[ColumnDefinition]] = js.undefined
  var columnDefaults: js.UndefOr[ColumnDefinition] = js.undefined
  var autoColumns: js.UndefOr[js.Any] = js.undefined
  var layout: js.UndefOr[String] = js.undefined
  var layoutColumnsOnNewData: js.UndefOr[Boolean] = js.undefined
  var responsiveLayout: js.UndefOr[js.Any] = js.undefined
  var responsiveLayoutCollapseStartOpen: js.UndefOr[Boolean] = js.undefined
  var responsiveLayoutCollapseUseFormatters: js.UndefOr[Boolean] = js.undefined
  var responsiveLayoutCollapseFormatter: js.UndefOr[js.Function] = js.undefined
  var movableColumns: js.UndefOr[Boolean] = js.undefined
  var headerVisible: js.UndefOr[Boolean] = js.undefined
  var resizableColumnFit: js.UndefOr[Boolean] = js.undefined
  var resizableColumnGuide: js.UndefOr[Boolean] = js.undefined
  var columnHeaderVertAlign: js.UndefOr[String] = js.undefined

  // Row configuration
  var rowHeader: js.UndefOr[js.Any] = js.undefined
  var rowHeight: js.UndefOr[Int] = js.undefined
  var rowFormatter: js.UndefOr[js.Function] = js.undefined
  var rowFormatterPrint: js.UndefOr[js.Any] = js.undefined
  var rowFormatterClipboard: js.UndefOr[js.Any] = js.undefined
  var rowFormatterHtmlOutput: js.UndefOr[js.Any] = js.undefined
  var addRowPos: js.UndefOr[String] = js.undefined
  var movableRows: js.UndefOr[Boolean] = js.undefined
  var movableRowsConnectedTables: js.UndefOr[js.Any] = js.undefined
  var movableRowsSender: js.UndefOr[js.Any] = js.undefined
  var movableRowsReceiver: js.UndefOr[js.Any] = js.undefined
  var resizableRows: js.UndefOr[Boolean] = js.undefined
  var frozenRows: js.UndefOr[js.Any] = js.undefined
  var frozenRowsField: js.UndefOr[String] = js.undefined

  // Data configuration
  var data: js.UndefOr[js.Array[js.Object]] = js.undefined
  var ajaxURL: js.UndefOr[String] = js.undefined
  var ajaxParams: js.UndefOr[js.Any] = js.undefined
  var ajaxConfig: js.UndefOr[js.Any] = js.undefined
  var ajaxContentType: js.UndefOr[js.Any] = js.undefined
  var ajaxURLGenerator: js.UndefOr[js.Function] = js.undefined
  var ajaxRequestFunc: js.UndefOr[js.Function] = js.undefined
  var filterMode: js.UndefOr[String] = js.undefined
  var sortMode: js.UndefOr[String] = js.undefined
  var progressiveLoad: js.UndefOr[String] = js.undefined
  var progressiveLoadDelay: js.UndefOr[Int] = js.undefined
  var progressiveLoadScrollMargin: js.UndefOr[Int] = js.undefined
  var dataLoader: js.UndefOr[Boolean] = js.undefined
  var dataLoaderLoading: js.UndefOr[js.Any] = js.undefined
  var dataLoaderError: js.UndefOr[js.Any] = js.undefined
  var dataSendParams: js.UndefOr[js.Object] = js.undefined

  // Sort
  var initialSort: js.UndefOr[js.Array[SorterDefinition]] = js.undefined
  var sortOrderReverse: js.UndefOr[Boolean] = js.undefined
  var headerSortElement: js.UndefOr[js.Any] = js.undefined
  var headerSortClickElement: js.UndefOr[String] = js.undefined
  var columnHeaderSortMulti: js.UndefOr[Boolean] = js.undefined

  // Filter
  var initialFilter: js.UndefOr[js.Array[FilterDefinition]] = js.undefined
  var initialHeaderFilter: js.UndefOr[js.Array[js.Object]] = js.undefined
  var headerFilterLiveFilterDelay: js.UndefOr[Int] = js.undefined

  // Group
  var groupBy: js.UndefOr[js.Any] = js.undefined
  var groupValues: js.UndefOr[js.Any] = js.undefined
  var groupHeader: js.UndefOr[js.Any] = js.undefined
  var groupStartOpen: js.UndefOr[js.Any] = js.undefined
  var groupToggleElement: js.UndefOr[js.Any] = js.undefined
  var groupClosedShowCalcs: js.UndefOr[Boolean] = js.undefined
  var groupUpdateOnCellEdit: js.UndefOr[Boolean] = js.undefined

  // Pagination
  var pagination: js.UndefOr[Boolean] = js.undefined
  var paginationMode: js.UndefOr[String] = js.undefined
  var paginationSize: js.UndefOr[Int] = js.undefined
  var paginationSizeSelector: js.UndefOr[js.Any] = js.undefined
  var paginationElement: js.UndefOr[js.Any] = js.undefined
  var paginationButtonCount: js.UndefOr[Int] = js.undefined
  var paginationAddRow: js.UndefOr[String] = js.undefined
  var paginationCounter: js.UndefOr[js.Any] = js.undefined
  var paginationInitialPage: js.UndefOr[Int] = js.undefined

  // Selection
  var selectableRows: js.UndefOr[js.Any] = js.undefined
  var selectableRowsRangeMode: js.UndefOr[String] = js.undefined
  var selectableRowsPersistence: js.UndefOr[Boolean] = js.undefined
  var selectableRowsCheck: js.UndefOr[js.Function] = js.undefined
  var selectableRange: js.UndefOr[js.Any] = js.undefined
  var selectableRangeColumns: js.UndefOr[Boolean] = js.undefined
  var selectableRangeRows: js.UndefOr[Boolean] = js.undefined
  var selectableRangeClearCells: js.UndefOr[Boolean] = js.undefined
  var selectableRangeClearCellsValue: js.UndefOr[js.Any] = js.undefined

  // Editing
  var editTriggerEvent: js.UndefOr[String] = js.undefined
  var editorEmptyValue: js.UndefOr[js.Any] = js.undefined
  var tabEndNewRow: js.UndefOr[js.Any] = js.undefined
  var validationMode: js.UndefOr[String] = js.undefined

  // History
  var history: js.UndefOr[Boolean] = js.undefined
  var keybindings: js.UndefOr[js.Any] = js.undefined

  // Clipboard
  var clipboard: js.UndefOr[js.Any] = js.undefined
  var clipboardCopyRowRange: js.UndefOr[js.Any] = js.undefined
  var clipboardCopyFormatter: js.UndefOr[js.Any] = js.undefined
  var clipboardCopyHeader: js.UndefOr[Boolean] = js.undefined
  var clipboardCopyStyled: js.UndefOr[Boolean] = js.undefined
  var clipboardPasteParser: js.UndefOr[js.Any] = js.undefined
  var clipboardPasteAction: js.UndefOr[js.Any] = js.undefined

  // Data Tree
  var dataTree: js.UndefOr[Boolean] = js.undefined
  var dataTreeFilter: js.UndefOr[Boolean] = js.undefined
  var dataTreeSort: js.UndefOr[Boolean] = js.undefined
  var dataTreeChildField: js.UndefOr[String] = js.undefined
  var dataTreeCollapseElement: js.UndefOr[js.Any] = js.undefined
  var dataTreeExpandElement: js.UndefOr[js.Any] = js.undefined
  var dataTreeStartExpanded: js.UndefOr[js.Any] = js.undefined
  var dataTreeChildIndent: js.UndefOr[Int] = js.undefined
  var dataTreeElementColumn: js.UndefOr[js.Any] = js.undefined
  var dataTreeBranchElement: js.UndefOr[js.Any] = js.undefined
  var dataTreeSelectPropagate: js.UndefOr[Boolean] = js.undefined

  // Print
  var printAsHtml: js.UndefOr[Boolean] = js.undefined
  var printStyled: js.UndefOr[Boolean] = js.undefined
  var printRowRange: js.UndefOr[js.Any] = js.undefined
  var printConfig: js.UndefOr[PrintConfig] = js.undefined
  var printHeader: js.UndefOr[js.Any] = js.undefined
  var printFooter: js.UndefOr[js.Any] = js.undefined

  // Download
  var downloadConfig: js.UndefOr[DownloadConfig] = js.undefined
  var downloadRowRange: js.UndefOr[js.Any] = js.undefined

  // Import
  var importFormat: js.UndefOr[js.Any] = js.undefined
  var importReader: js.UndefOr[js.Any] = js.undefined
  var importFileValidator: js.UndefOr[js.Function] = js.undefined
  var importDataValidator: js.UndefOr[js.Function] = js.undefined

  // Persistence
  var persistence: js.UndefOr[js.Any] = js.undefined
  var persistenceID: js.UndefOr[String] = js.undefined
  var persistenceMode: js.UndefOr[js.Any] = js.undefined
  var persistenceWriterFunc: js.UndefOr[js.Function] = js.undefined
  var persistenceReaderFunc: js.UndefOr[js.Function] = js.undefined

  // Locale
  var locale: js.UndefOr[js.Any] = js.undefined
  var langs: js.UndefOr[js.Object] = js.undefined

  // Spreadsheet
  var spreadsheet: js.UndefOr[Boolean] = js.undefined
  var spreadsheetRows: js.UndefOr[Int] = js.undefined
  var spreadsheetColumns: js.UndefOr[Int] = js.undefined
  var spreadsheetData: js.UndefOr[js.Array[js.Array[js.Any]]] = js.undefined
  var spreadsheetColumnDefinition: js.UndefOr[ColumnDefinition] = js.undefined
  var spreadsheetOutputFull: js.UndefOr[Boolean] = js.undefined
  var spreadsheetSheets: js.UndefOr[js.Array[SpreadsheetSheetDefinition]] = js.undefined
  var spreadsheetSheetTabs: js.UndefOr[Boolean] = js.undefined

  // Menu/Popup
  var rowContextMenu: js.UndefOr[js.Any] = js.undefined
  var rowClickMenu: js.UndefOr[js.Any] = js.undefined
  var rowDblClickMenu: js.UndefOr[js.Any] = js.undefined
  var groupContextMenu: js.UndefOr[js.Any] = js.undefined
  var groupClickMenu: js.UndefOr[js.Any] = js.undefined
  var groupDblClickMenu: js.UndefOr[js.Any] = js.undefined
  var rowContextPopup: js.UndefOr[js.Any] = js.undefined
  var rowClickPopup: js.UndefOr[js.Any] = js.undefined
  var rowDblClickPopup: js.UndefOr[js.Any] = js.undefined
  var groupContextPopup: js.UndefOr[js.Any] = js.undefined
  var groupClickPopup: js.UndefOr[js.Any] = js.undefined
  var groupDblClickPopup: js.UndefOr[js.Any] = js.undefined

  // Rendering
  var renderVertical: js.UndefOr[js.Any] = js.undefined
  var renderVerticalBuffer: js.UndefOr[js.Any] = js.undefined
  var renderHorizontal: js.UndefOr[js.Any] = js.undefined

  // Row events
  var rowClick: js.UndefOr[js.Function] = js.undefined
  var rowDblClick: js.UndefOr[js.Function] = js.undefined
  var rowContext: js.UndefOr[js.Function] = js.undefined
  var rowTap: js.UndefOr[js.Function] = js.undefined
  var rowDblTap: js.UndefOr[js.Function] = js.undefined
  var rowTapHold: js.UndefOr[js.Function] = js.undefined
  var rowAdded: js.UndefOr[js.Function] = js.undefined
  var rowUpdated: js.UndefOr[js.Function] = js.undefined
  var rowDeleted: js.UndefOr[js.Function] = js.undefined
  var rowMoved: js.UndefOr[js.Function] = js.undefined
  var rowSelectionChanged: js.UndefOr[js.Function] = js.undefined
  var rowSelected: js.UndefOr[js.Function] = js.undefined
  var rowDeselected: js.UndefOr[js.Function] = js.undefined
  var rowMouseEnter: js.UndefOr[js.Function] = js.undefined
  var rowMouseLeave: js.UndefOr[js.Function] = js.undefined
  var rowMouseOver: js.UndefOr[js.Function] = js.undefined
  var rowMouseOut: js.UndefOr[js.Function] = js.undefined
  var rowMouseMove: js.UndefOr[js.Function] = js.undefined

  // Cell events
  var cellClick: js.UndefOr[js.Function] = js.undefined
  var cellDblClick: js.UndefOr[js.Function] = js.undefined
  var cellContext: js.UndefOr[js.Function] = js.undefined
  var cellTap: js.UndefOr[js.Function] = js.undefined
  var cellDblTap: js.UndefOr[js.Function] = js.undefined
  var cellTapHold: js.UndefOr[js.Function] = js.undefined
  var cellEditing: js.UndefOr[js.Function] = js.undefined
  var cellEdited: js.UndefOr[js.Function] = js.undefined
  var cellEditCancelled: js.UndefOr[js.Function] = js.undefined
  var cellMouseEnter: js.UndefOr[js.Function] = js.undefined
  var cellMouseLeave: js.UndefOr[js.Function] = js.undefined
  var cellMouseOver: js.UndefOr[js.Function] = js.undefined
  var cellMouseOut: js.UndefOr[js.Function] = js.undefined
  var cellMouseMove: js.UndefOr[js.Function] = js.undefined

  // Data events
  var dataLoading: js.UndefOr[js.Function] = js.undefined
  var dataLoaded: js.UndefOr[js.Function] = js.undefined
  var dataChanged: js.UndefOr[js.Function] = js.undefined
  var dataFiltered: js.UndefOr[js.Function] = js.undefined
  var dataSorted: js.UndefOr[js.Function] = js.undefined

  // Column events
  var columnMoved: js.UndefOr[js.Function] = js.undefined
  var columnResized: js.UndefOr[js.Function] = js.undefined
  var columnTitleChanged: js.UndefOr[js.Function] = js.undefined
  var columnVisibilityChanged: js.UndefOr[js.Function] = js.undefined

  // Table events
  var tableBuilt: js.UndefOr[js.Function] = js.undefined
  var renderStarted: js.UndefOr[js.Function] = js.undefined
  var renderComplete: js.UndefOr[js.Function] = js.undefined
  var htmlImporting: js.UndefOr[js.Function] = js.undefined
  var htmlImported: js.UndefOr[js.Function] = js.undefined

  // Page events
  var pageLoaded: js.UndefOr[js.Function] = js.undefined

  // Scroll events
  var scrollVertical: js.UndefOr[js.Function] = js.undefined
  var scrollHorizontal: js.UndefOr[js.Function] = js.undefined

  // Validation events
  var validationFailed: js.UndefOr[js.Function] = js.undefined

  // History events
  var historyUndo: js.UndefOr[js.Function] = js.undefined
  var historyRedo: js.UndefOr[js.Function] = js.undefined

  // Group events
  var dataGrouped: js.UndefOr[js.Function] = js.undefined
  var groupVisibilityChanged: js.UndefOr[js.Function] = js.undefined
  var groupClick: js.UndefOr[js.Function] = js.undefined
  var groupDblClick: js.UndefOr[js.Function] = js.undefined
  var groupContext: js.UndefOr[js.Function] = js.undefined
  var groupTap: js.UndefOr[js.Function] = js.undefined
  var groupDblTap: js.UndefOr[js.Function] = js.undefined
  var groupTapHold: js.UndefOr[js.Function] = js.undefined
}
