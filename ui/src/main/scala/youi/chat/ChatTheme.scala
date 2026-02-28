package youi.chat

import youi.theme.Theme
import youi.ui
import org.scalajs.dom

object ChatTheme {
  private var installed = false

  /** Narrow viewport threshold in pixels */
  val narrowBreakpoint: Double = 600.0

  /** Whether the viewport is currently narrow (mobile/portrait) */
  def isNarrow: Boolean = ui.size.width() <= narrowBreakpoint

  /** Reactive font size for chat body text */
  def fontSize: Double = if (isNarrow) 16.0 else 14.0

  /** Reactive font size for timestamps */
  def timestampFontSize: Double = if (isNarrow) 12.0 else 11.0

  /** Reactive font size for meta text (group toggle, load earlier) */
  def metaFontSize: Double = if (isNarrow) 14.0 else 12.0

  def installDefaults(): Unit = if (!installed) {
    installed = true

    // CSS variables for properties that need responsive @media overrides at the stylesheet level.
    // Only includes values that can't be expressed via the framework's reactive property system
    // (percentages, compound padding values, etc.)
    val style = dom.document.createElement("style").asInstanceOf[dom.html.Style]
    style.textContent =
      """:root {
        |  --chat-bubble-max-width: 70%;
        |  --chat-bubble-padding: 10px;
        |  --chat-gap: 8px;
        |}
        |@media (max-width: 600px) {
        |  :root {
        |    --chat-bubble-max-width: 90%;
        |    --chat-bubble-padding: 10px 12px;
        |    --chat-gap: 6px;
        |  }
        |}""".stripMargin
    dom.document.head.appendChild(style)

    // Reactively update Theme font sizes when viewport width changes
    ui.size.width.attach { _ =>
      updateResponsiveSizes()
    }
    updateResponsiveSizes()
  }

  /** Update all Theme font sizes based on current viewport width */
  private def updateResponsiveSizes(): Unit = {
    val fs = fontSize
    val ts = timestampFontSize
    val ms = metaFontSize
    userBubble.font.size @= fs
    assistantBubble.font.size @= fs
    systemBubble.font.size @= fs
    timestamp.font.size @= ts
    groupToggle.font.size @= ms
    loadEarlier.font.size @= ms
  }

  val userBubble: Theme = Theme.create(".chat-bubble-user") { t =>
    t.backgroundColor @= youi.Color.SteelBlue
    t.color @= youi.Color.White
    t.css.setProperty("border-radius", "12px")
    t.css.setProperty("padding", "var(--chat-bubble-padding, 10px)")
    t.css.setProperty("max-width", "var(--chat-bubble-max-width, 70%)")
    t.css.setProperty("box-sizing", "border-box")
    t.css.setProperty("overflow-wrap", "break-word")
    t.display @= youi.component.types.Display.InlineBlock
  }

  val assistantBubble: Theme = Theme.create(".chat-bubble-assistant") { t =>
    t.css.setProperty("background", "transparent")
    t.css.setProperty("color", "inherit")
    t.css.setProperty("padding", "var(--chat-bubble-padding, 10px) 0")
    t.css.setProperty("box-sizing", "border-box")
    t.css.setProperty("overflow-wrap", "break-word")
    t.display @= youi.component.types.Display.Block
    t.css.setProperty("width", "100%")
  }

  val systemBubble: Theme = Theme.create(".chat-bubble-system") { t =>
    t.backgroundColor @= youi.Color.fromHex("888888")
    t.color @= youi.Color.White
    t.css.setProperty("border-radius", "12px")
    t.css.setProperty("padding", "var(--chat-bubble-padding, 10px)")
    t.css.setProperty("max-width", "var(--chat-bubble-max-width, 70%)")
    t.css.setProperty("box-sizing", "border-box")
    t.css.setProperty("overflow-wrap", "break-word")
    t.display @= youi.component.types.Display.InlineBlock
    t.css.setProperty("font-style", "italic")
  }

  val groupToggle: Theme = Theme.create(".chat-group-toggle") { t =>
    t.css.setProperty("color", "#6ca0dc")
    t.cursor @= youi.component.types.Cursor.Pointer
    t.css.setProperty("padding", "4px 0")
  }

  val loadEarlier: Theme = Theme.create(".chat-load-earlier") { t =>
    t.css.setProperty("color", "#6ca0dc")
    t.cursor @= youi.component.types.Cursor.Pointer
    t.css.setProperty("text-align", "center")
    t.css.setProperty("padding", "8px")
  }

  val timestamp: Theme = Theme.create(".chat-timestamp") { t =>
    t.opacity @= 0.6
    t.display @= youi.component.types.Display.Block
  }

  def bubbleClassForRole(role: String): String = role match {
    case "user"      => "chat-bubble-user"
    case "system"    => "chat-bubble-system"
    case _           => "chat-bubble-assistant"
  }

  // Force initialization of all Theme objects by touching them
  def init(): Unit = {
    installDefaults()
    val _ = (userBubble, assistantBubble, systemBubble, groupToggle, loadEarlier, timestamp)
  }
}
