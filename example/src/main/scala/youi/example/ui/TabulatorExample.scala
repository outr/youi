package youi.example.ui

import rapid.Task
import youi._
import youi.component.{Button, Container}
import youi.component.support.{BorderSupport, ContentSupport, PaddingSupport}
import youi.component.types.{Border, BorderStyle, Cursor}
import youi.dom
import youi.event.EventSupport
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.layout.{FlowLayout, LayoutSupport}
import youi.tabulator._
import spice.net._
import reactify.stateful2Value

class TabulatorExample extends UIExampleScreen {
  override def title: String = "Tabulator"
  override def path: URLPath = path"/examples/tabulator.html"

  override def createUI(): Task[Unit] = TabulatorLoader.load().map { _ =>
    val columns = Seq(
      Column("id", "ID", width = Some(60), hozAlign = HozAlign.Center, sorter = Some(Sorter.Number)),
      Column("name", "Name", editor = Some(Editor.Input), headerFilter = Some(HeaderFilter.Input),
        headerFilterPlaceholder = Some("Search..."), validator = Some("required")),
      Column("age", "Age", width = Some(80), hozAlign = HozAlign.Center,
        editor = Some(Editor.Number), sorter = Some(Sorter.Number)),
      Column("gender", "Gender", width = Some(110),
        editor = Some(Editor.List(Seq("male", "female", "non-binary"))),
        headerFilter = Some(HeaderFilter.List(Map("" -> "All", "male" -> "Male", "female" -> "Female", "non-binary" -> "Non-binary")))),
      Column("rating", "Rating", width = Some(120), hozAlign = HozAlign.Center,
        sorter = Some(Sorter.Number), editor = Some(Editor.Star),
        formatter = Some(Formatter.Progress(min = 0, max = 5))),
      Column("dob", "Date of Birth", hozAlign = HozAlign.Center, sorter = Some(Sorter.Date),
        formatter = Some(Formatter.DateTime("yyyy-MM-dd", "MMM dd, yyyy", "(invalid)")))
    )

    val data = Seq(
      Map("id" -> 1, "name" -> "Alice Johnson", "age" -> 29, "gender" -> "female", "rating" -> 4, "dob" -> "1995-03-14"),
      Map("id" -> 2, "name" -> "Bob Smith", "age" -> 34, "gender" -> "male", "rating" -> 3, "dob" -> "1990-07-22"),
      Map("id" -> 3, "name" -> "Carol Williams", "age" -> 42, "gender" -> "female", "rating" -> 5, "dob" -> "1982-11-08"),
      Map("id" -> 4, "name" -> "David Brown", "age" -> 27, "gender" -> "male", "rating" -> 2, "dob" -> "1997-01-30"),
      Map("id" -> 5, "name" -> "Eva Martinez", "age" -> 31, "gender" -> "female", "rating" -> 4, "dob" -> "1993-06-17"),
      Map("id" -> 6, "name" -> "Frank Lee", "age" -> 45, "gender" -> "male", "rating" -> 5, "dob" -> "1979-09-03"),
      Map("id" -> 7, "name" -> "Grace Kim", "age" -> 28, "gender" -> "female", "rating" -> 3, "dob" -> "1996-12-25"),
      Map("id" -> 8, "name" -> "Henry Davis", "age" -> 38, "gender" -> "male", "rating" -> 4, "dob" -> "1986-04-11"),
      Map("id" -> 9, "name" -> "Iris Chen", "age" -> 33, "gender" -> "female", "rating" -> 5, "dob" -> "1991-08-19"),
      Map("id" -> 10, "name" -> "Jack Wilson", "age" -> 26, "gender" -> "male", "rating" -> 2, "dob" -> "1998-02-07"),
      Map("id" -> 11, "name" -> "Kate Taylor", "age" -> 37, "gender" -> "female", "rating" -> 4, "dob" -> "1987-05-29"),
      Map("id" -> 12, "name" -> "Leo Garcia", "age" -> 41, "gender" -> "male", "rating" -> 3, "dob" -> "1983-10-14"),
      Map("id" -> 13, "name" -> "Mia Anderson", "age" -> 24, "gender" -> "female", "rating" -> 5, "dob" -> "2000-01-01"),
      Map("id" -> 14, "name" -> "Noah Thomas", "age" -> 30, "gender" -> "male", "rating" -> 4, "dob" -> "1994-07-07"),
      Map("id" -> 15, "name" -> "Olivia Jackson", "age" -> 35, "gender" -> "female", "rating" -> 3, "dob" -> "1989-03-21"),
      Map("id" -> 16, "name" -> "Paul White", "age" -> 48, "gender" -> "male", "rating" -> 5, "dob" -> "1976-11-15"),
      Map("id" -> 17, "name" -> "Quinn Harris", "age" -> 29, "gender" -> "non-binary", "rating" -> 4, "dob" -> "1995-06-30"),
      Map("id" -> 18, "name" -> "Rachel Martin", "age" -> 32, "gender" -> "female", "rating" -> 3, "dob" -> "1992-09-12"),
      Map("id" -> 19, "name" -> "Sam Robinson", "age" -> 44, "gender" -> "male", "rating" -> 2, "dob" -> "1980-04-05"),
      Map("id" -> 20, "name" -> "Tina Clark", "age" -> 36, "gender" -> "female", "rating" -> 5, "dob" -> "1988-08-23")
    )

    // -- Toolbar --
    val toolbar = new Container with LayoutSupport with PaddingSupport {
      layout @= Some(FlowLayout(horizontalSpacing = 8.0, verticalSpacing = 8.0))
      padding @= 8.0
      position.top @= 10.0
      position.left @= 10.0
      size.width := container.size.width
    }
    container.children += toolbar

    def mkButton(label: String)(action: => Unit): Button = {
      val btn = new Button with ContentSupport with EventSupport with BorderSupport with PaddingSupport {
        content @= label
        font.size @= 13.px
        font.family @= "'Roboto', sans-serif"
        padding.top @= 6.0
        padding.bottom @= 6.0
        padding.left @= 14.0
        padding.right @= 14.0
        border @= Border(1.0, BorderStyle.Solid, Color.fromHex("cccccc"))
        border.radius @= 4.0
        cursor @= Cursor.Pointer
        event.click.on { action }
      }
      toolbar.children += btn
      btn
    }

    // -- Table --
    val table = new TabulatorView
    table.font.family @= "'Roboto', sans-serif"
    table.position.center := container.position.center
    table.position.middle := container.position.middle
    table.size.width := container.size.width - 100.0
    table.size.height := container.size.height - 150.0
    table.pagination @= true
    table.paginationSize @= 50
    table.tableHistory @= true
    table.movableColumns @= true
    table.selectable @= Selectable.Highlight
    container.children += table

    // Toolbar buttons
    var nextId = 21
    mkButton("Add Row") {
      nextId += 1
      table.addRow(Map("id" -> nextId, "name" -> s"New Person $nextId", "age" -> 25, "gender" -> "male", "rating" -> 3, "dob" -> "2000-01-01"))
    }
    mkButton("Delete Selected") {
      table.getTabulator.foreach { tab =>
        tab.getSelectedRows().foreach(_.delete())
      }
    }
    mkButton("Download CSV") {
      table.download(DownloadFormat.CSV, "data.csv")
    }
    mkButton("Undo") { table.undo() }
    mkButton("Redo") { table.redo() }

    table.initialize(columns, data)

    // -- Dark mode theme swap --
    ExampleApp.darkMode.attachAndFire { isDark =>
      if (isDark) {
        TabulatorLoader.removeCSSByUrl(TabulatorLoader.cssUrl)
        dom.addCSS(TabulatorLoader.darkCssUrl)
      } else {
        TabulatorLoader.removeCSSByUrl(TabulatorLoader.darkCssUrl)
        dom.addCSS(TabulatorLoader.cssUrl)
      }
    }
  }
}
