package youi.tabulator

import youi.component.Component
import youi.component.support.FontSupport
import youi.dom
import reactify.{Channel, Var}
import tabulator._

import scala.scalajs.js

class TabulatorView extends Component(dom.create.div) with FontSupport {
  import ColumnConversions._

  // Inner div where Tabulator is actually constructed — isolated from SizeProperty styles
  private val _innerDiv: org.scalajs.dom.html.Div = dom.create.div
  _innerDiv.style.width = "100%"
  _innerDiv.style.height = "100%"
  element.appendChild(_innerDiv)

  // Configuration — pure Scala types
  val layout: Var[String] = Var("fitColumns")
  val pagination: Var[Boolean] = Var(false)
  val paginationSize: Var[Int] = Var(10)
  val movableColumns: Var[Boolean] = Var(false)
  val movableRows: Var[Boolean] = Var(false)
  val selectable: Var[Selectable] = Var(Selectable.False)
  val groupBy: Var[Option[String]] = Var(None)
  val tableHistory: Var[Boolean] = Var(false)
  val clipboard: Var[Boolean] = Var(false)
  val dataTree: Var[Boolean] = Var(false)
  val headerVisible: Var[Boolean] = Var(true)
  val placeholder: Var[String] = Var("No Data Available")
  val reactiveData: Var[Boolean] = Var(false)

  // Event channels — pure Scala types
  val onTableBuilt: Channel[Unit] = Channel[Unit]
  val onRowClick: Channel[Map[String, Any]] = Channel[Map[String, Any]]
  val onRowDblClick: Channel[Map[String, Any]] = Channel[Map[String, Any]]
  val onCellEdited: Channel[CellEdit] = Channel[CellEdit]
  val onCellEditCancelled: Channel[CellEdit] = Channel[CellEdit]
  val onDataChanged: Channel[Unit] = Channel[Unit]
  val onPageLoaded: Channel[Int] = Channel[Int]
  val onScrollVertical: Channel[Double] = Channel[Double]
  val onScrollHorizontal: Channel[Double] = Channel[Double]

  private var _tabulator: Option[Tabulator] = None
  private var _built: Boolean = false
  private var _pendingOptions: Option[TabulatorOptions] = None

  // Escape hatches — only JS-exposing methods
  def getTabulator: Option[Tabulator] = _tabulator
  def isBuilt: Boolean = _built

  // Deferred size listener — registered once, constructs Tabulator on first valid height
  size.height.attach { h =>
    if (h > 0) {
      _pendingOptions match {
        case Some(opts) =>
          _pendingOptions = None
          constructTabulator(opts)
        case None =>
          if (_built) _tabulator.foreach(_.redraw(true))
      }
    }
  }
  size.width.attach(_ => if (_built) _tabulator.foreach(_.redraw(true)))

  // --- Public API: Initialization ---

  def initialize(columns: Seq[Column], data: Seq[Map[String, Any]] = Seq.empty): Unit = {
    val opts = buildOptions()
    opts.asInstanceOf[js.Dynamic].columns = columnsToJS(columns)
    opts.asInstanceOf[js.Dynamic].data = rowsToJS(data)
    initializeRaw(opts)
  }

  def initializeRaw(options: TabulatorOptions): Unit = {
    _tabulator.foreach(_.destroy())
    _tabulator = None
    _built = false

    val currentHeight = element.offsetHeight
    if (currentHeight > 0) {
      constructTabulator(options)
    } else {
      _pendingOptions = Some(options)
    }
  }

  // --- Public API: Data ---

  def setData(data: Seq[Map[String, Any]]): Unit = _tabulator.foreach(_.setData(rowsToJS(data)))
  def replaceData(data: Seq[Map[String, Any]]): Unit = _tabulator.foreach(_.replaceData(rowsToJS(data)))
  def updateData(data: Seq[Map[String, Any]]): Unit = _tabulator.foreach(_.updateData(rowsToJS(data)))
  def addRow(data: Map[String, Any], atTop: Boolean = false): Unit = {
    val pos: js.UndefOr[Boolean] = if (atTop) true else js.undefined
    _tabulator.foreach(_.addRow(rowToJS(data), pos, js.undefined))
  }
  def updateRow(index: Any, data: Map[String, Any]): Unit = _tabulator.foreach(_.updateRow(index.asInstanceOf[js.Any], rowToJS(data)))
  def deleteRow(index: Any): Unit = _tabulator.foreach(_.deleteRow(index.asInstanceOf[js.Any]))
  def clearData(): Unit = _tabulator.foreach(_.clearData())
  def getData(): Seq[Map[String, Any]] = _tabulator.map(t => jsToRows(t.getData())).getOrElse(Seq.empty)
  def getDataCount(): Int = _tabulator.map(_.getDataCount()).getOrElse(0)

  // --- Public API: Selection ---

  def selectAll(): Unit = _tabulator.foreach(_.selectRow())
  def deselectAll(): Unit = _tabulator.foreach(_.deselectRow())
  def getSelectedData(): Seq[Map[String, Any]] = _tabulator.map(t => jsToRows(t.getSelectedData())).getOrElse(Seq.empty)

  // --- Public API: Sort/Filter ---

  def setSort(field: String, dir: SortDir = SortDir.Asc): Unit = _tabulator.foreach(_.setSort(field.asInstanceOf[js.Any], sortDirToString(dir)))
  def clearSort(): Unit = _tabulator.foreach(_.clearSort())
  def setFilter(field: String, comparison: String, value: Any): Unit = _tabulator.foreach(_.setFilter(field.asInstanceOf[js.Any], comparison, anyToJSPublic(value)))
  def clearFilter(includeHeader: Boolean = false): Unit = _tabulator.foreach(_.clearFilter(includeHeader))
  def clearHeaderFilter(): Unit = _tabulator.foreach(_.clearHeaderFilter())

  // --- Public API: Download ---

  def download(format: DownloadFormat, filename: String = "data"): Unit = _tabulator.foreach(_.download(downloadFormatToString(format).asInstanceOf[js.Any], filename))

  // --- Public API: Pagination ---

  def setPage(page: Int): Unit = _tabulator.foreach(_.setPage(page.asInstanceOf[js.Any]))
  def nextPage(): Unit = _tabulator.foreach(_.nextPage())
  def previousPage(): Unit = _tabulator.foreach(_.previousPage())
  def setPageSize(size: Int): Unit = _tabulator.foreach(_.setPageSize(size))

  // --- Public API: History ---

  def undo(): Boolean = _tabulator.exists(_.undo())
  def redo(): Boolean = _tabulator.exists(_.redo())
  def getHistoryUndoSize(): Int = _tabulator.map(_.getHistoryUndoSize()).getOrElse(0)
  def getHistoryRedoSize(): Int = _tabulator.map(_.getHistoryRedoSize()).getOrElse(0)
  def clearHistory(): Unit = _tabulator.foreach(_.clearHistory())

  // --- Public API: Misc ---

  def redraw(force: Boolean = false): Unit = if (_built) _tabulator.foreach(_.redraw(force))
  def copyToClipboard(): Unit = _tabulator.foreach(_.copyToClipboard())
  def validate(): Boolean = _tabulator.exists(t => t.validate().asInstanceOf[Any] == true)
  def recalc(): Unit = _tabulator.foreach(_.recalc())
  def focus(): Unit = element.focus()

  def dispose(): Unit = {
    _tabulator.foreach(_.destroy())
    _tabulator = None
    _pendingOptions = None
  }

  // --- Internal ---

  private def anyToJSPublic(v: Any): js.Any = v match {
    case null       => null.asInstanceOf[js.Any]
    case s: String  => s.asInstanceOf[js.Any]
    case i: Int     => i.asInstanceOf[js.Any]
    case d: Double  => d.asInstanceOf[js.Any]
    case b: Boolean => b.asInstanceOf[js.Any]
    case other      => other.asInstanceOf[js.Any]
  }

  private def cellComponentToEdit(cell: CellComponent): CellEdit = {
    CellEdit(
      field = cell.getField(),
      value = ColumnConversions.jsToAny(cell.getValue()),
      oldValue = ColumnConversions.jsToAny(cell.getOldValue()),
      rowData = jsToRow(cell.getRow().getData())
    )
  }

  private def constructTabulator(options: TabulatorOptions): Unit = {
    val tab = new Tabulator(_innerDiv, options)
    _tabulator = Some(tab)

    tab.on("tableBuilt", (() => {
      _built = true
      onTableBuilt @= ()
    }): js.Function0[Unit])
    tab.on("rowClick", ((e: org.scalajs.dom.Event, row: RowComponent) => onRowClick @= jsToRow(row.getData())): js.Function2[org.scalajs.dom.Event, RowComponent, Unit])
    tab.on("rowDblClick", ((e: org.scalajs.dom.Event, row: RowComponent) => onRowDblClick @= jsToRow(row.getData())): js.Function2[org.scalajs.dom.Event, RowComponent, Unit])
    tab.on("cellEdited", ((cell: CellComponent) => onCellEdited @= cellComponentToEdit(cell)): js.Function1[CellComponent, Unit])
    tab.on("cellEditCancelled", ((cell: CellComponent) => onCellEditCancelled @= cellComponentToEdit(cell)): js.Function1[CellComponent, Unit])
    tab.on("dataChanged", ((_: js.Array[js.Object]) => onDataChanged @= ()): js.Function1[js.Array[js.Object], Unit])
    tab.on("pageLoaded", ((page: Int) => onPageLoaded @= page): js.Function1[Int, Unit])
    tab.on("scrollVertical", ((top: Double) => onScrollVertical @= top): js.Function1[Double, Unit])
    tab.on("scrollHorizontal", ((left: Double) => onScrollHorizontal @= left): js.Function1[Double, Unit])
  }

  private def buildOptions(): TabulatorOptions = {
    val opts = js.Dynamic.literal(
      layout = layout().asInstanceOf[js.Any],
      pagination = pagination().asInstanceOf[js.Any],
      paginationSize = paginationSize().asInstanceOf[js.Any],
      movableColumns = movableColumns().asInstanceOf[js.Any],
      movableRows = movableRows().asInstanceOf[js.Any],
      selectableRows = selectableToJS(selectable()),
      history = tableHistory().asInstanceOf[js.Any],
      clipboard = clipboard().asInstanceOf[js.Any],
      dataTree = dataTree().asInstanceOf[js.Any],
      headerVisible = headerVisible().asInstanceOf[js.Any],
      placeholder = placeholder().asInstanceOf[js.Any],
      reactiveData = reactiveData().asInstanceOf[js.Any]
    )
    groupBy().foreach(v => opts.groupBy = v.asInstanceOf[js.Any])
    opts.asInstanceOf[TabulatorOptions]
  }
}
