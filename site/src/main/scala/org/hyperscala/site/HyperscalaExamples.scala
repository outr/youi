package org.hyperscala.site

import org.hyperscala.bootstrap.component._
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object HyperscalaExamples extends HyperscalaPage {
  main.contents += new tag.H1(content = "Hyperscala Examples")

  val groups = HyperscalaSite.examples.map(ep => ep.group).distinct
  val map = HyperscalaSite.examples.groupBy(ep => ep.group)
  groups.foreach {
    case group => {
      val pages = map(group).sortBy(e => e.name)
      val title = new tag.Span {
        contents += new tag.Span(content = s"$group&#160;&#160;", clazz = List("example-heading"))
        contents += new Badge(pages.length.toString)
      }
      main.contents += new Panel(title, panelType = PanelType.Primary) with CollapsiblePanel {
        override def collapsed = true

        body.contents += new Row {
          pages.foreach {
            case entry => contents += new ExampleEntryDisplay(entry)
          }
//          pages.grouped(math.max(1, (pages.length + 2) / 3)).foreach {
//            case entries => contents += new Column(medium = Some(4)) {
//              contents += new ListGroup {
//                entries.foreach {
//                  case entry => addLink(entry.path, entry.name)
//                }
//              }
//            }
//          }
        }
      }
    }
  }
}

class ExampleEntryDisplay(entry: ExampleEntry) extends tag.Div(clazz = List("example-entry")) {
  contents += new tag.A(href = entry.path, content = entry.name)
}