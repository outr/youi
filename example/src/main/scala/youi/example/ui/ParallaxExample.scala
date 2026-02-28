package youi.example.ui

import rapid.Task
import youi._
import youi.component.support.{OverflowSupport, ScrollSupport}
import youi.component.types.{Overflow, PositionType}
import youi.component.{Container, TextView}
import youi.example.screen.UIExampleScreen
import youi.font.GoogleFont
import spice.net._

class ParallaxExample extends UIExampleScreen {
  override def title: String = "Parallax Example"
  override def path: URLPath = path"/examples/parallax.html"

  override def createUI(): Task[Unit] = GoogleFont.`Lobster`.`regular`.load().map { fnt =>
    // Tall scrollable container that fills the content area
    val scrollContainer = new Container with ScrollSupport with OverflowSupport {
      size.width  := container.size.width
      size.height := container.size.height
      overflow    @= Overflow.Auto
    }

    // Inner content taller than the viewport to enable scrolling
    val inner = new Container {
      size.width  := scrollContainer.size.width
      size.height @= 4000.0
    }
    scrollContainer.children += inner

    // Parallax text: fixed position within the scrollable area, moves with scroll
    val textView = new TextView {
      content @= "Hello, World"
      font.weight @= fnt
      // Font size varies with scroll position
      font.size := {
        val maxScroll = 4000.0 - scrollContainer.size.height()
        val p = if (maxScroll > 0) scrollContainer.scroll.y() / maxScroll else 0.0
        64.0 + 24.0 * ((p - 0.5) * 2.0)
      }
      // Color shifts from dark blue to red as you scroll
      color := {
        val maxScroll = 4000.0 - scrollContainer.size.height()
        val p = if (maxScroll > 0) scrollContainer.scroll.y() / maxScroll else 0.0
        Color.DarkBlue.withRed(p)
      }
      position.`type` @= PositionType.Sticky
      position.top    @= 20.0
      position.center := scrollContainer.size.center
    }
    inner.children += textView

    container.children += scrollContainer
  }
}
