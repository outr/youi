package io.youi.tabulator

import tabulator.{CellComponent, ColumnDefinition}

import scala.scalajs.js

case class Column(
  field: String,
  title: String,
  width: Option[Int] = None,
  minWidth: Option[Int] = None,
  maxWidth: Option[Int] = None,
  visible: Boolean = true,
  frozen: Boolean = false,
  hozAlign: HozAlign = HozAlign.Left,
  vertAlign: VertAlign = VertAlign.Top,
  headerHozAlign: Option[HozAlign] = None,
  cssClass: Option[String] = None,
  formatter: Option[Formatter] = None,
  editor: Option[Editor] = None,
  validator: Option[String] = None,
  sorter: Option[Sorter] = None,
  headerSort: Boolean = true,
  headerSortStartingDir: Option[SortDir] = None,
  headerFilter: Option[HeaderFilter] = None,
  headerFilterPlaceholder: Option[String] = None,
  columns: Option[Seq[Column]] = None
)

enum HozAlign:
  case Left, Center, Right

enum VertAlign:
  case Top, Middle, Bottom

enum SortDir:
  case Asc, Desc

enum Sorter:
  case String, Number, AlphaNum, Boolean, Exists, Date, Time, DateTime, Array
  case Custom(name: scala.Predef.String)

enum Editor:
  case Input, TextArea, Number, Checkbox, Star, Date, Time, DateTime
  case List(values: Seq[scala.Predef.String])
  case Custom(name: scala.Predef.String)

enum Formatter:
  case PlainText, TextArea, Html, Money, Image, Link
  case Tick, TickCross, Star, Toggle, Color, ButtonTick, ButtonCross, RowNum, Handle
  case DateTime(inputFormat: scala.Predef.String, outputFormat: scala.Predef.String, invalidPlaceholder: scala.Predef.String = "")
  case Progress(min: Double = 0, max: Double = 100, colors: Seq[scala.Predef.String] = Seq("red", "orange", "green"), height: Int = 16)
  case Custom(fn: CellValue => scala.Predef.String)

case class CellValue(value: Any, rowData: Map[String, Any], field: String)

enum HeaderFilter:
  case Input, Number, Checkbox, Star, Date, Time, DateTime
  case List(values: Map[scala.Predef.String, scala.Predef.String])
  case Custom(name: scala.Predef.String)

enum Selectable:
  case False, True, Highlight
  case Count(n: Int)

enum DownloadFormat:
  case CSV, JSON, XLSX, HTML, PDF

case class CellEdit(field: String, value: Any, oldValue: Any, rowData: Map[String, Any])

private[tabulator] object ColumnConversions:
  def columnToJS(col: Column): ColumnDefinition =
    val d = js.Dynamic.literal(
      field = col.field,
      title = col.title
    )
    col.width.foreach(w => d.width = w.asInstanceOf[js.Any])
    col.minWidth.foreach(w => d.minWidth = w.asInstanceOf[js.Any])
    col.maxWidth.foreach(w => d.maxWidth = w.asInstanceOf[js.Any])
    if !col.visible then d.visible = false.asInstanceOf[js.Any]
    if col.frozen then d.frozen = true.asInstanceOf[js.Any]
    if col.hozAlign != HozAlign.Left then d.hozAlign = hozAlignToString(col.hozAlign).asInstanceOf[js.Any]
    if col.vertAlign != VertAlign.Top then d.vertAlign = vertAlignToString(col.vertAlign).asInstanceOf[js.Any]
    col.headerHozAlign.foreach(a => d.headerHozAlign = hozAlignToString(a).asInstanceOf[js.Any])
    col.cssClass.foreach(c => d.cssClass = c.asInstanceOf[js.Any])
    col.formatter.foreach(f => applyFormatter(d, f))
    col.editor.foreach(e => applyEditor(d, e))
    col.validator.foreach(v => d.validator = v.asInstanceOf[js.Any])
    col.sorter.foreach(s => d.sorter = sorterToString(s).asInstanceOf[js.Any])
    if !col.headerSort then d.headerSort = false.asInstanceOf[js.Any]
    col.headerSortStartingDir.foreach(dir => d.headerSortStartingDir = sortDirToString(dir).asInstanceOf[js.Any])
    col.headerFilter.foreach(hf => applyHeaderFilter(d, hf))
    col.headerFilterPlaceholder.foreach(p => d.headerFilterPlaceholder = p.asInstanceOf[js.Any])
    col.columns.foreach { nested =>
      d.columns = js.Array(nested.map(columnToJS)*).asInstanceOf[js.Any]
    }
    d.asInstanceOf[ColumnDefinition]

  def columnsToJS(cols: Seq[Column]): js.Array[ColumnDefinition] =
    js.Array(cols.map(columnToJS)*)

  def rowToJS(row: Map[String, Any]): js.Object =
    val d = js.Dynamic.literal()
    row.foreach { (k, v) =>
      d.updateDynamic(k)(anyToJS(v))
    }
    d.asInstanceOf[js.Object]

  def rowsToJS(rows: Seq[Map[String, Any]]): js.Array[js.Object] =
    js.Array(rows.map(rowToJS)*)

  def jsToRow(obj: js.Object): Map[String, Any] =
    val keys = js.Object.keys(obj)
    val d = obj.asInstanceOf[js.Dynamic]
    keys.map { k =>
      k -> jsToAny(d.selectDynamic(k))
    }.toMap

  def jsToRows(arr: js.Array[js.Object]): Seq[Map[String, Any]] =
    arr.toSeq.map(jsToRow)

  private def anyToJS(v: Any): js.Any = v match
    case null          => null.asInstanceOf[js.Any]
    case s: String     => s.asInstanceOf[js.Any]
    case i: Int        => i.asInstanceOf[js.Any]
    case d: Double     => d.asInstanceOf[js.Any]
    case f: Float      => f.toDouble.asInstanceOf[js.Any]
    case l: Long       => l.toDouble.asInstanceOf[js.Any]
    case b: Boolean    => b.asInstanceOf[js.Any]
    case seq: Seq[?]   => js.Array(seq.map(x => anyToJS(x.asInstanceOf[Any]))*).asInstanceOf[js.Any]
    case m: Map[?, ?]  => rowToJS(m.asInstanceOf[Map[String, Any]]).asInstanceOf[js.Any]
    case other         => other.asInstanceOf[js.Any]

  private[tabulator] def jsToAny(v: js.Any): Any =
    if v == null || js.isUndefined(v) then null
    else
      (js.typeOf(v): @unchecked) match
        case "string"  => v.asInstanceOf[String]
        case "number"  => v.asInstanceOf[Double]
        case "boolean" => v.asInstanceOf[Boolean]
        case "object"  =>
          if js.Array.isArray(v) then
            v.asInstanceOf[js.Array[js.Any]].toSeq.map(jsToAny)
          else
            jsToRow(v.asInstanceOf[js.Object])
        case _ => v

  def selectableToJS(s: Selectable): js.Any = s match
    case Selectable.False        => false.asInstanceOf[js.Any]
    case Selectable.True         => true.asInstanceOf[js.Any]
    case Selectable.Highlight    => "highlight".asInstanceOf[js.Any]
    case Selectable.Count(n)     => n.asInstanceOf[js.Any]

  def sortDirToString(d: SortDir): String = d match
    case SortDir.Asc  => "asc"
    case SortDir.Desc => "desc"

  def downloadFormatToString(f: DownloadFormat): String = f match
    case DownloadFormat.CSV  => "csv"
    case DownloadFormat.JSON => "json"
    case DownloadFormat.XLSX => "xlsx"
    case DownloadFormat.HTML => "html"
    case DownloadFormat.PDF  => "pdf"

  private def hozAlignToString(a: HozAlign): String = a match
    case HozAlign.Left   => "left"
    case HozAlign.Center => "center"
    case HozAlign.Right  => "right"

  private def vertAlignToString(a: VertAlign): String = a match
    case VertAlign.Top    => "top"
    case VertAlign.Middle => "middle"
    case VertAlign.Bottom => "bottom"

  private def sorterToString(s: Sorter): String = s match
    case Sorter.String    => "string"
    case Sorter.Number    => "number"
    case Sorter.AlphaNum  => "alphanum"
    case Sorter.Boolean   => "boolean"
    case Sorter.Exists    => "exists"
    case Sorter.Date      => "date"
    case Sorter.Time      => "time"
    case Sorter.DateTime  => "datetime"
    case Sorter.Array     => "array"
    case Sorter.Custom(n) => n

  private def applyFormatter(d: js.Dynamic, f: Formatter): Unit = f match
    case Formatter.PlainText   => d.formatter = "plaintext".asInstanceOf[js.Any]
    case Formatter.TextArea    => d.formatter = "textarea".asInstanceOf[js.Any]
    case Formatter.Html        => d.formatter = "html".asInstanceOf[js.Any]
    case Formatter.Money       => d.formatter = "money".asInstanceOf[js.Any]
    case Formatter.Image       => d.formatter = "image".asInstanceOf[js.Any]
    case Formatter.Link        => d.formatter = "link".asInstanceOf[js.Any]
    case Formatter.Tick        => d.formatter = "tick".asInstanceOf[js.Any]
    case Formatter.TickCross   => d.formatter = "tickCross".asInstanceOf[js.Any]
    case Formatter.Star        => d.formatter = "star".asInstanceOf[js.Any]
    case Formatter.Toggle      => d.formatter = "toggle".asInstanceOf[js.Any]
    case Formatter.Color       => d.formatter = "color".asInstanceOf[js.Any]
    case Formatter.ButtonTick  => d.formatter = "buttonTick".asInstanceOf[js.Any]
    case Formatter.ButtonCross => d.formatter = "buttonCross".asInstanceOf[js.Any]
    case Formatter.RowNum      => d.formatter = "rownum".asInstanceOf[js.Any]
    case Formatter.Handle      => d.formatter = "handle".asInstanceOf[js.Any]
    case Formatter.DateTime(inputFmt, outputFmt, invalid) =>
      d.formatter = "datetime".asInstanceOf[js.Any]
      val params = js.Dynamic.literal(
        inputFormat = inputFmt.asInstanceOf[js.Any],
        outputFormat = outputFmt.asInstanceOf[js.Any]
      )
      if invalid.nonEmpty then params.invalidPlaceholder = invalid.asInstanceOf[js.Any]
      d.formatterParams = params
    case Formatter.Progress(min, max, colors, height) =>
      // Use custom formatter with fixed pixel height to avoid height:100% circular dependency
      val colorArr = colors
      val fn: js.Function2[CellComponent, js.Any, String] = { (cell: CellComponent, _: js.Any) =>
        val value = cell.getValue().asInstanceOf[Number].doubleValue()
        val pct = Math.min(100.0, Math.max(0.0, ((value - min) / (max - min)) * 100.0))
        val color =
          if colorArr.length == 1 then colorArr.head
          else if colorArr.length == 2 then
            if pct < 50 then colorArr(0) else colorArr(1)
          else if colorArr.length >= 3 then
            if pct < 33.3 then colorArr(0) else if pct < 66.6 then colorArr(1) else colorArr(2)
          else "green"
        s"""<div style="height:${height}px;background:#ddd;border-radius:3px;overflow:hidden;"><div style="height:100%;width:$pct%;background:$color;"></div></div>"""
      }
      d.formatter = fn.asInstanceOf[js.Any]
    case Formatter.Custom(fn) =>
      val jsFn: js.Function2[CellComponent, js.Any, String] = { (cell: CellComponent, _: js.Any) =>
        val rowObj = cell.getRow().getData()
        val cellValue = CellValue(
          value = jsToAny(cell.getValue()),
          rowData = jsToRow(rowObj),
          field = cell.getField()
        )
        fn(cellValue)
      }
      d.formatter = jsFn.asInstanceOf[js.Any]

  private def applyEditor(d: js.Dynamic, e: Editor): Unit = e match
    case Editor.Input    => d.editor = "input".asInstanceOf[js.Any]
    case Editor.TextArea => d.editor = "textarea".asInstanceOf[js.Any]
    case Editor.Number   => d.editor = "number".asInstanceOf[js.Any]
    case Editor.Checkbox => d.editor = "checkbox".asInstanceOf[js.Any]
    case Editor.Star     => d.editor = "star".asInstanceOf[js.Any]
    case Editor.Date     => d.editor = "date".asInstanceOf[js.Any]
    case Editor.Time     => d.editor = "time".asInstanceOf[js.Any]
    case Editor.DateTime => d.editor = "datetime".asInstanceOf[js.Any]
    case Editor.List(values) =>
      d.editor = "list".asInstanceOf[js.Any]
      d.editorParams = js.Dynamic.literal(values = js.Array(values*)).asInstanceOf[js.Any]
    case Editor.Custom(name) =>
      d.editor = name.asInstanceOf[js.Any]

  private def applyHeaderFilter(d: js.Dynamic, hf: HeaderFilter): Unit = hf match
    case HeaderFilter.Input    => d.headerFilter = "input".asInstanceOf[js.Any]
    case HeaderFilter.Number   => d.headerFilter = "number".asInstanceOf[js.Any]
    case HeaderFilter.Checkbox => d.headerFilter = "checkbox".asInstanceOf[js.Any]
    case HeaderFilter.Star     => d.headerFilter = "star".asInstanceOf[js.Any]
    case HeaderFilter.Date     => d.headerFilter = "date".asInstanceOf[js.Any]
    case HeaderFilter.Time     => d.headerFilter = "time".asInstanceOf[js.Any]
    case HeaderFilter.DateTime => d.headerFilter = "datetime".asInstanceOf[js.Any]
    case HeaderFilter.List(values) =>
      d.headerFilter = "list".asInstanceOf[js.Any]
      val valuesObj = js.Dynamic.literal()
      values.foreach { (k, v) =>
        valuesObj.updateDynamic(k)(v.asInstanceOf[js.Any])
      }
      d.headerFilterParams = js.Dynamic.literal(values = valuesObj).asInstanceOf[js.Any]
    case HeaderFilter.Custom(name) =>
      d.headerFilter = name.asInstanceOf[js.Any]
