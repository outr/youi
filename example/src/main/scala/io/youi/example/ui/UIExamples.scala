package io.youi.example.ui

import rapid.Task
import io.youi.Color
import io.youi.component.{Component, Container}
import io.youi.component.support.{ContainerSupport, ContentSupport, MarginSupport, OverflowSupport}
import io.youi.component.types.{Border, BorderStyle, Display, Overflow}
import io.youi.event.EventSupport
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import io.youi.material.{MDCCard, MDCChip, MDCChipSet, MaterialComponents}
import io.youi.paint.Paint
import io.youi.theme.{ElementState, Theme}
import io.youi.{dom, ui}
import reactify.Var
import spice.net._

class UIExamples extends UIExampleScreen {
  override def title: String = "UI Examples"
  override def path: URLPath = path"/ui-examples.html"

  override protected lazy val container: Container & MarginSupport & OverflowSupport = {
    val c = new Container with MarginSupport with OverflowSupport
    c.id @= title
    c
  }

  private case class ExampleEntry(screen: UIExampleScreen, description: String, category: String)

  private lazy val entries: List[ExampleEntry] = List(
    ExampleEntry(ExampleApp.flow, "Automatic wrapping flow layout for dynamic content", "Layout"),
    ExampleEntry(ExampleApp.grid, "CSS Grid-based responsive layout system", "Layout"),
    ExampleEntry(ExampleApp.horizontal, "Horizontal arrangement of child components", "Layout"),
    ExampleEntry(ExampleApp.vertical, "Vertical stacking layout for components", "Layout"),
    ExampleEntry(ExampleApp.mdc, "Material Design Components showcase", "Components"),
    ExampleEntry(ExampleApp.textInput, "Text input fields with validation and events", "Components"),
    ExampleEntry(ExampleApp.select, "Dropdown selection component", "Components"),
    ExampleEntry(ExampleApp.popup, "Popup overlay and tooltip positioning", "Components"),
    ExampleEntry(ExampleApp.modal, "Modal dialog with backdrop overlay", "Components"),
    ExampleEntry(ExampleApp.sidebar, "Slide-out sidebar navigation panel", "Components"),
    ExampleEntry(ExampleApp.scrolling, "Scrollable content containers", "Components"),
    ExampleEntry(ExampleApp.canvas, "Direct HTML5 Canvas drawing API", "Graphics"),
    ExampleEntry(ExampleApp.drawable, "Custom drawable components with text rendering", "Graphics"),
    ExampleEntry(ExampleApp.image, "Image loading and display", "Graphics"),
    ExampleEntry(ExampleApp.imageChange, "Dynamic image source swapping", "Graphics"),
    ExampleEntry(ExampleApp.svgImage, "SVG vector image rendering", "Graphics"),
    ExampleEntry(ExampleApp.animatedImage, "Animated sprite sheet playback", "Graphics"),
    ExampleEntry(ExampleApp.scale9, "Scale-9 grid image scaling", "Graphics"),
    ExampleEntry(ExampleApp.text, "Text view with font styling", "Text"),
    ExampleEntry(ExampleApp.heavyText, "Large-scale text stress test", "Text"),
    ExampleEntry(ExampleApp.animation, "Property animation and easing", "Animation"),
    ExampleEntry(ExampleApp.parallax, "Parallax scrolling depth effect", "Animation"),
    ExampleEntry(ExampleApp.snap, "Snap-to-grid positioning with animation", "Animation"),
    ExampleEntry(ExampleApp.gestures, "Touch and pointer gesture recognition", "Interactive"),
    ExampleEntry(ExampleApp.selection, "Drag-select and multi-selection support", "Interactive"),
    ExampleEntry(ExampleApp.dataTransfer, "Clipboard and data transfer operations", "Interactive"),
    ExampleEntry(ExampleApp.drop, "Drag-and-drop file handling", "Interactive"),
    ExampleEntry(ExampleApp.video, "HTML5 video playback component", "Media"),
    ExampleEntry(ExampleApp.chat, "Real-time chat view integration", "Integrations"),
    ExampleEntry(ExampleApp.monacoEditor, "Monaco code editor embedding", "Integrations"),
    ExampleEntry(ExampleApp.xterm, "XTerm.js terminal emulator", "Integrations"),
    ExampleEntry(ExampleApp.tabulator, "Interactive data table with sorting, filtering, and editing", "Integrations"),
    ExampleEntry(ExampleApp.hello, "Minimal hello world example", "Other"),
    ExampleEntry(ExampleApp.materialIcons, "Material Design icon browser", "Other"),
    ExampleEntry(ExampleApp.phosphor, "Phosphor icon set showcase", "Other"),
    ExampleEntry(ExampleApp.measured, "Component measurement and sizing", "Other")
  )

  private lazy val categories: List[String] = entries.map(_.category).distinct

  // Layout-only CSS (no colors — colors managed via Theme objects)
  private val cssString: String =
    """
      |.youi-welcome-hero {
      |  padding: 48px 24px;
      |  text-align: center;
      |  color: white;
      |  margin-bottom: 24px;
      |  transition: background 0.3s ease;
      |}
      |.youi-welcome-hero h1 {
      |  font-family: 'Roboto', sans-serif;
      |  font-size: 32px;
      |  font-weight: 300;
      |  margin: 0 0 8px 0;
      |}
      |.youi-welcome-hero p {
      |  font-family: 'Roboto', sans-serif;
      |  font-size: 16px;
      |  font-weight: 300;
      |  opacity: 0.85;
      |  margin: 0;
      |}
      |.youi-filter-bar {
      |  display: flex;
      |  flex-wrap: wrap;
      |  justify-content: center;
      |  gap: 8px;
      |  padding: 0 24px 16px 24px;
      |  max-width: 1200px;
      |  margin: 0 auto;
      |}
      |.youi-count-label {
      |  text-align: center;
      |  font-family: 'Roboto', sans-serif;
      |  font-size: 14px;
      |  padding: 0 24px 24px 24px;
      |  transition: color 0.3s ease;
      |}
      |.youi-card-grid {
      |  display: grid;
      |  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
      |  gap: 16px;
      |  max-width: 1200px;
      |  margin: 0 auto;
      |  padding: 0 24px 48px 24px;
      |}
      |.youi-category-section {
      |  grid-column: 1 / -1;
      |  font-family: 'Roboto', sans-serif;
      |  font-size: 20px;
      |  font-weight: 500;
      |  padding: 16px 0 4px 0;
      |  margin-top: 8px;
      |  transition: color 0.3s ease, border-color 0.3s ease;
      |}
      |.youi-category-section:first-child {
      |  margin-top: 0;
      |}
      |.youi-example-card {
      |  cursor: pointer;
      |  transition: box-shadow 0.2s ease, transform 0.2s ease, background-color 0.3s ease;
      |  padding: 20px;
      |  border-radius: 8px;
      |}
      |.youi-example-card:hover {
      |  box-shadow: 0 4px 16px rgba(0,0,0,0.15);
      |  transform: translateY(-2px);
      |}
      |.youi-card-category {
      |  display: inline-block;
      |  font-family: 'Roboto', sans-serif;
      |  font-size: 11px;
      |  font-weight: 500;
      |  text-transform: uppercase;
      |  letter-spacing: 0.5px;
      |  padding: 2px 8px;
      |  border-radius: 4px;
      |  margin-bottom: 8px;
      |  transition: color 0.3s ease, background-color 0.3s ease;
      |}
      |.youi-card-title {
      |  font-family: 'Roboto', sans-serif;
      |  font-size: 16px;
      |  font-weight: 500;
      |  margin: 0 0 6px 0;
      |  transition: color 0.3s ease;
      |}
      |.youi-card-description {
      |  font-family: 'Roboto', sans-serif;
      |  font-size: 14px;
      |  font-weight: 400;
      |  margin: 0;
      |  line-height: 1.4;
      |  transition: color 0.3s ease;
      |}
      |.youi-filter-chip {
      |  display: inline-block;
      |  font-family: 'Roboto', sans-serif;
      |  font-size: 13px;
      |  font-weight: 500;
      |  padding: 6px 16px;
      |  border-radius: 16px;
      |  cursor: pointer;
      |  transition: all 0.2s ease;
      |  user-select: none;
      |}
      |.youi-filter-chip--active {
      |  color: white !important;
      |}
    """.stripMargin

  // Theme objects for color management
  private lazy val heroTheme = Theme.bySelector(".youi-welcome-hero")
  private lazy val countLabelTheme = Theme.bySelector(".youi-count-label")
  private lazy val categorySectionTheme = Theme.bySelector(".youi-category-section")
  private lazy val cardTheme = Theme.bySelector(".youi-example-card")
  private lazy val cardCategoryTheme = Theme.bySelector(".youi-card-category")
  private lazy val cardTitleTheme = Theme.bySelector(".youi-card-title")
  private lazy val cardDescTheme = Theme.bySelector(".youi-card-description")
  private lazy val chipTheme = Theme.bySelector(".youi-filter-chip")
  private lazy val chipHoverTheme = Theme.bySelector(".youi-filter-chip:hover")
  private lazy val chipActiveTheme = Theme.bySelector(".youi-filter-chip--active")
  private lazy val chipActiveHoverTheme = Theme.bySelector(".youi-filter-chip--active:hover")

  private def applyThemeColors(isDark: Boolean): Unit = {
    if (isDark) {
      heroTheme.background @= Paint.linear(0, 0, 1, 1).distributeColors(
        Color.fromHex("0d1117"), Color.fromHex("1a237e"), Color.fromHex("283593")
      )
      countLabelTheme.color @= Color.fromHex("9e9e9e")
      categorySectionTheme.color @= Color.fromHex("7986cb")
      categorySectionTheme.border.bottom @= Border(2.0, BorderStyle.Solid, Color.fromHex("333333"))
      cardTheme.backgroundColor @= ExampleApp.surfaceColor()
      cardCategoryTheme.color @= Color.fromHex("7986cb")
      cardCategoryTheme.backgroundColor @= Color.fromHex("1a237e")
      cardTitleTheme.color @= ExampleApp.textColor()
      cardDescTheme.color @= Color.fromHex("9e9e9e")
      chipTheme.backgroundColor @= Color.fromHex("2c2c2c")
      chipTheme.color @= ExampleApp.textColor()
      chipTheme.border.bottom @= Border(1.0, BorderStyle.Solid, Color.fromHex("555555"))
      chipTheme.css.border = s"1px solid ${Color.fromHex("555555").toRGBA}"
      chipHoverTheme.color @= Color.fromHex("7986cb")
      chipHoverTheme.css.borderColor = Color.fromHex("7986cb").toRGBA
      chipActiveTheme.backgroundColor @= Color.fromHex("5c6bc0")
      chipActiveTheme.css.borderColor = Color.fromHex("5c6bc0").toRGBA
      chipActiveHoverTheme.backgroundColor @= Color.fromHex("1a237e")
      chipActiveHoverTheme.css.borderColor = Color.fromHex("1a237e").toRGBA
    } else {
      heroTheme.background @= Paint.linear(0, 0, 1, 1).distributeColors(
        Color.fromHex("1a237e"), Color.fromHex("3949ab"), Color.fromHex("5c6bc0")
      )
      countLabelTheme.color @= ExampleApp.secondaryText()
      categorySectionTheme.color @= Color.fromHex("1a237e")
      categorySectionTheme.border.bottom @= Border(2.0, BorderStyle.Solid, Color.fromHex("e8eaf6"))
      cardTheme.backgroundColor @= ExampleApp.surfaceColor()
      cardCategoryTheme.color @= ExampleApp.accentColor()
      cardCategoryTheme.backgroundColor @= Color.fromHex("e8eaf6")
      cardTitleTheme.color @= ExampleApp.textColor()
      cardDescTheme.color @= ExampleApp.secondaryText()
      chipTheme.backgroundColor @= ExampleApp.surfaceColor()
      chipTheme.color @= Color.fromHex("333333")
      chipTheme.css.border = s"1px solid ${Color.fromHex("cccccc").toRGBA}"
      chipHoverTheme.color @= ExampleApp.accentColor()
      chipHoverTheme.css.borderColor = ExampleApp.accentColor().toRGBA
      chipActiveTheme.backgroundColor @= ExampleApp.accentColor()
      chipActiveTheme.css.borderColor = ExampleApp.accentColor().toRGBA
      chipActiveHoverTheme.backgroundColor @= Color.fromHex("1a237e")
      chipActiveHoverTheme.css.borderColor = Color.fromHex("1a237e").toRGBA
    }
  }

  override def createUI(): Task[Unit] = for {
    _ <- MaterialComponents.waitForLoaded()
  } yield {
    container.overflow.y @= Overflow.Auto

    dom.addCSS(cssString)
    applyThemeColors(false)

    ExampleApp.darkMode.attachAndFire { isDark =>
      applyThemeColors(isDark)
    }

    // Hero section
    val hero = new Component(dom.create.div) with ContentSupport
    hero.element.className = "youi-welcome-hero"
    hero.content @=
      """<h1>YouI Examples</h1>
        |<p>Explore interactive demos of the YouI framework</p>""".stripMargin
    container.children += hero

    // Filter bar
    val filterBar = new Component(dom.create.div)
    filterBar.element.className = "youi-filter-bar"
    container.children += filterBar

    val activeFilter: Var[String] = Var("All")

    // Count label
    val countLabel = new Component(dom.create.div) with ContentSupport
    countLabel.element.className = "youi-count-label"
    container.children += countLabel

    // Card grid
    val gridContainer = new Component(dom.create.div) with ContainerSupport
    gridContainer.element.className = "youi-card-grid"
    container.children += gridContainer

    // Build section headers and cards
    case class SectionRef(category: String, header: Component, cards: List[CardRef])
    case class CardRef(entry: ExampleEntry, card: Component)

    val sections = categories.map { cat =>
      val header = new Component(dom.create.div) with ContentSupport
      header.element.className = "youi-category-section"
      header.content @= cat
      gridContainer.children += header

      val catEntries = entries.filter(_.category == cat)
      val cardRefs = catEntries.map { entry =>
        val card = new MDCCard(true) with EventSupport
        card.element.classList.add("youi-example-card")

        val inner = dom.create.div
        inner.innerHTML =
          s"""<span class="youi-card-category">${entry.category}</span>
             |<p class="youi-card-title">${entry.screen.title}</p>
             |<p class="youi-card-description">${entry.description}</p>""".stripMargin
        card.element.appendChild(inner)

        card.event.click.on {
          ExampleApp.active @= entry.screen
        }

        gridContainer.children += card
        CardRef(entry, card)
      }

      SectionRef(cat, header, cardRefs)
    }

    def updateFilter(filter: String): Unit = {
      activeFilter @= filter
      val showAll = filter == "All"
      var visibleCount = 0

      sections.foreach { section =>
        val showSection = showAll || section.category == filter
        section.header.display @= (if (showSection) Display.Block else Display.None)
        section.cards.foreach { cardRef =>
          val show = showAll || cardRef.entry.category == filter
          cardRef.card.display @= (if (show) Display.Block else Display.None)
          if (show) visibleCount += 1
        }
      }

      val countText = if (showAll) {
        s"${entries.size} examples across ${categories.size} categories"
      } else {
        s"$visibleCount examples in $filter"
      }
      countLabel.content @= countText

      // Update chip styles
      filterBar.element.querySelectorAll(".youi-filter-chip").foreach { node =>
        val el = node.asInstanceOf[org.scalajs.dom.html.Element]
        val chipFilter = el.getAttribute("data-filter")
        if (chipFilter == filter) {
          el.classList.add("youi-filter-chip--active")
        } else {
          el.classList.remove("youi-filter-chip--active")
        }
      }
    }

    // Build filter chips
    val allFilters = "All" :: categories
    allFilters.foreach { filter =>
      val chip = dom.create.span
      chip.className = "youi-filter-chip"
      chip.textContent = filter
      chip.setAttribute("data-filter", filter)
      if (filter == "All") chip.classList.add("youi-filter-chip--active")
      chip.addEventListener("click", (_: org.scalajs.dom.MouseEvent) => {
        updateFilter(filter)
      })
      filterBar.element.appendChild(chip)
    }

    // Initial count
    countLabel.content @= s"${entries.size} examples across ${categories.size} categories"
  }
}
