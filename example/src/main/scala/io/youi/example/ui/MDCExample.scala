package io.youi.example.ui

import rapid.Task
import io.youi.dom
import io.youi.component.{Container, TextView}
import io.youi.component.types.Display
import io.youi.example.screen.UIExampleScreen
import io.youi.material._
import spice.net._

class MDCExample extends UIExampleScreen {
  override def title: String = "MDC Example"
  override def path: URLPath = path"/examples/mdc.html"

  override def createUI(): Task[Unit] = for {
    _ <- MaterialComponents.waitForLoaded()
  } yield {
    // --- Tab Bar ---
    val tabBar = new MDCTabBar
    val buttonsTab = new MDCTab("Buttons")
    buttonsTab.setInitiallyActive()
    tabBar.tabs.children += buttonsTab
    tabBar.tabs.children += new MDCTab("Inputs")
    tabBar.tabs.children += new MDCTab("Feedback")
    tabBar.tabs.children += new MDCTab("Layout")
    container.children += tabBar
    tabBar.init()

    // --- Panels ---
    val buttonsPanel = new Container
    val inputsPanel = new Container
    val feedbackPanel = new Container
    val layoutPanel = new Container

    inputsPanel.display @= Display.None
    feedbackPanel.display @= Display.None
    layoutPanel.display @= Display.None

    val panels = List(buttonsPanel, inputsPanel, feedbackPanel, layoutPanel)
    tabBar.onActivated.attach { index =>
      panels.zipWithIndex.foreach { case (panel, i) =>
        panel.display @= (if (i == index) Display.Block else Display.None)
      }
    }

    // ===================
    // BUTTONS TAB
    // ===================
    buildButtonsTab(buttonsPanel)

    // ===================
    // INPUTS TAB
    // ===================
    buildInputsTab(inputsPanel)

    // ===================
    // FEEDBACK TAB
    // ===================
    buildFeedbackTab(feedbackPanel)

    // ===================
    // LAYOUT TAB
    // ===================
    buildLayoutTab(layoutPanel)

    container.children += buttonsPanel
    container.children += inputsPanel
    container.children += feedbackPanel
    container.children += layoutPanel
  }

  private def sectionHeading(text: String): TextView = new TextView {
    content @= text
    font.size @= 18.0
    font.weight @= "500"
    element.style.display = "block"
    element.style.marginTop = "16px"
    element.style.marginBottom = "8px"
  }

  private def spacer(): Container = {
    val c = new Container
    c.element.style.height = "16px"
    c
  }

  private def buildButtonsTab(panel: Container): Unit = {
    // Button variants
    val buttonCard = new MDCCard
    buttonCard.element.style.padding = "16px"
    buttonCard.element.style.marginBottom = "16px"

    buttonCard.children += sectionHeading("Buttons")

    buttonCard.children += new MDCButton("Raised") { raised @= true }
    buttonCard.children += new MDCButton("Outlined") { outlined @= true }
    buttonCard.children += new MDCButton("Unelevated") { unelevated @= true }
    buttonCard.children += new MDCButton("Default")
    buttonCard.children += new MDCButton("With Icon", leading = Material.Icons.Action.Favorite) { raised @= true }

    panel.children += buttonCard

    // Icon buttons
    val iconCard = new MDCCard
    iconCard.element.style.padding = "16px"
    iconCard.element.style.marginBottom = "16px"

    iconCard.children += sectionHeading("Icon Buttons")
    iconCard.children += new MDCIconButton(Material.Icons.Action.Bookmarks)
    iconCard.children += new MDCIconButton(Material.Icons.Action.Favorite)
    iconCard.children += new MDCIconButton(Material.Icons.Action.Search)

    val toggle = new MDCIconButtonToggle
    toggle.on @= Material.Icons.Action.Favorite
    toggle.off @= Material.Icons.Action.FavoriteBorder
    iconCard.children += toggle

    panel.children += iconCard

    // FAB
    val fabCard = new MDCCard
    fabCard.element.style.padding = "16px"
    fabCard.element.style.marginBottom = "16px"

    fabCard.children += sectionHeading("Floating Action Buttons")
    fabCard.children += new MDCFAB(Material.Icons.Content.Add)
    fabCard.children += new MDCFAB(Material.Icons.Content.Add, mini = true)
    fabCard.children += new MDCFAB(Material.Icons.Content.Add, label = "Create")

    panel.children += fabCard
  }

  private def buildInputsTab(panel: Container): Unit = {
    // Text fields
    val textCard = new MDCCard
    textCard.element.style.padding = "16px"
    textCard.element.style.marginBottom = "16px"

    textCard.children += sectionHeading("Text Fields")

    val filled = new MDCTextField
    filled.label @= "Filled"
    textCard.children += filled

    textCard.children += spacer()

    val outlined = new MDCTextField
    outlined.outlined @= true
    outlined.label @= "Outlined"
    textCard.children += outlined

    panel.children += textCard

    // Checkbox + Radio
    val selectionCard = new MDCCard
    selectionCard.element.style.padding = "16px"
    selectionCard.element.style.marginBottom = "16px"

    selectionCard.children += sectionHeading("Checkboxes")

    val cb1 = new MDCCheckbox
    cb1.checked @= true
    val ff1 = new MDCFormField
    ff1.labelText @= "Checked"
    ff1.setInput(cb1)
    selectionCard.children += ff1

    val cb2 = new MDCCheckbox
    val ff2 = new MDCFormField
    ff2.labelText @= "Unchecked"
    ff2.setInput(cb2)
    selectionCard.children += ff2

    selectionCard.children += sectionHeading("Radio Buttons")

    val r1 = new MDCRadio
    r1.name @= "demo-radio"
    r1.value @= "one"
    r1.checked @= true
    val rf1 = new MDCFormField
    rf1.labelText @= "Option One"
    rf1.setInput(r1)
    selectionCard.children += rf1

    val r2 = new MDCRadio
    r2.name @= "demo-radio"
    r2.value @= "two"
    val rf2 = new MDCFormField
    rf2.labelText @= "Option Two"
    rf2.setInput(r2)
    selectionCard.children += rf2

    panel.children += selectionCard

    // Switch
    val switchCard = new MDCCard
    switchCard.element.style.padding = "16px"
    switchCard.element.style.marginBottom = "16px"

    switchCard.children += sectionHeading("Switch")

    val switchLabel = new TextView { content @= "Off" }
    val sw = new MDCSwitch
    sw.selected.attach { sel =>
      switchLabel.content @= (if (sel) "On" else "Off")
    }
    switchCard.children += sw
    switchCard.children += switchLabel

    panel.children += switchCard

    // Slider
    val sliderCard = new MDCCard
    sliderCard.element.style.padding = "16px"
    sliderCard.element.style.marginBottom = "16px"

    sliderCard.children += sectionHeading("Slider")

    val sliderLabel = new TextView { content @= "Value: 50" }
    val slider = new MDCSlider
    slider.value @= 50.0
    slider.element.style.width = "300px"
    slider.value.attach { v =>
      sliderLabel.content @= s"Value: ${v.toInt}"
    }
    sliderCard.children += slider
    sliderCard.children += sliderLabel

    panel.children += sliderCard

    // Select
    val selectCard = new MDCCard
    selectCard.element.style.padding = "16px"
    selectCard.element.style.marginBottom = "16px"

    selectCard.children += sectionHeading("Select")

    val selectLabel = new TextView { content @= "Selected: (none)" }
    val select = new MDCSelect
    select.label @= "Pick a fruit"
    select.addOption("", "")
    select.addOption("apple", "Apple")
    select.addOption("banana", "Banana")
    select.addOption("cherry", "Cherry")
    select.init()
    select.onChange.attach { v =>
      selectLabel.content @= s"Selected: $v"
    }
    selectCard.children += select
    selectCard.children += spacer()
    selectCard.children += selectLabel

    panel.children += selectCard
  }

  private def buildFeedbackTab(panel: Container): Unit = {
    // Snackbar
    val snackCard = new MDCCard
    snackCard.element.style.padding = "16px"
    snackCard.element.style.marginBottom = "16px"

    snackCard.children += sectionHeading("Snackbar")

    val snackbar = new MDCSnackbar
    snackbar.labelText @= "This is a snackbar message"
    snackbar.actionText @= "Dismiss"

    val snackBtn = new MDCButton("Show Snackbar") { raised @= true }
    snackBtn.element.addEventListener("click", (_: org.scalajs.dom.Event) => snackbar.open())
    snackCard.children += snackBtn
    // Snackbar must be in DOM to display
    snackCard.children += snackbar

    panel.children += snackCard

    // Dialog
    val dialogCard = new MDCCard
    dialogCard.element.style.padding = "16px"
    dialogCard.element.style.marginBottom = "16px"

    dialogCard.children += sectionHeading("Dialog")

    val dialogResult = new TextView { content @= "Result: (none)" }

    val dialog = new MDCDialog
    dialog.titleText @= "Confirm Action"
    dialog.contentText @= "Are you sure you want to proceed with this action?"
    dialog.addAction("Cancel", "cancel")
    dialog.addAction("OK", "accept", raised = true)
    dialog.onClosed.attach { action =>
      dialogResult.content @= s"Result: $action"
    }

    val dialogBtn = new MDCButton("Open Dialog") { raised @= true }
    dialogBtn.element.addEventListener("click", (_: org.scalajs.dom.Event) => dialog.open())
    dialogCard.children += dialogBtn
    dialogCard.children += spacer()
    dialogCard.children += dialogResult
    // Dialog must be in DOM
    dialogCard.children += dialog

    panel.children += dialogCard

    // Progress indicators
    val progressCard = new MDCCard
    progressCard.element.style.padding = "16px"
    progressCard.element.style.marginBottom = "16px"

    progressCard.children += sectionHeading("Linear Progress")

    val determinateProgress = new MDCProgressBar
    determinateProgress.value @= 0.6
    progressCard.children += determinateProgress

    progressCard.children += spacer()

    val indeterminateProgress = new MDCProgressBar
    indeterminateProgress.value @= -1.0
    progressCard.children += indeterminateProgress

    progressCard.children += sectionHeading("Circular Progress")

    val circDeterminate = new MDCCircularProgress
    circDeterminate.value @= 0.7
    progressCard.children += circDeterminate

    val circIndeterminate = new MDCCircularProgress
    circIndeterminate.value @= -1.0
    progressCard.children += circIndeterminate

    panel.children += progressCard

    // Tooltip
    val tooltipCard = new MDCCard
    tooltipCard.element.style.padding = "16px"
    tooltipCard.element.style.marginBottom = "16px"

    tooltipCard.children += sectionHeading("Tooltip")

    val tooltipBtn = new MDCIconButton(Material.Icons.Action.Info)
    val tooltip = new MDCTooltip("More information here")
    tooltip.attachToAnchor(tooltipBtn)
    tooltipCard.children += tooltipBtn
    tooltipCard.children += tooltip

    panel.children += tooltipCard

    // Tooltip adapter must be initialized after the element is in the document DOM
    org.scalajs.dom.window.setTimeout(() => tooltip.init(), 0)
  }

  private def buildLayoutTab(panel: Container): Unit = {
    // Top App Bar
    val appBarCard = new MDCCard
    appBarCard.element.style.padding = "16px"
    appBarCard.element.style.marginBottom = "16px"

    appBarCard.children += sectionHeading("Top App Bar")

    val topBar = new MDCTopAppBar
    topBar.heading @= "Sample App"
    topBar.menu.event.click.on {
      topBar.collapsed @= !topBar.collapsed()
    }
    topBar.controls.children += new MDCIconButton(Material.Icons.Action.Search)
    topBar.controls.children += new MDCIconButton(Material.Icons.Navigation.MoreVert)
    appBarCard.children += topBar

    panel.children += appBarCard

    // Chips
    val chipCard = new MDCCard
    chipCard.element.style.padding = "16px"
    chipCard.element.style.marginBottom = "16px"

    chipCard.children += sectionHeading("Chips")

    val chipSet = new MDCChipSet
    chipSet.children += new MDCChip("Chip One")
    val chipTwo = new MDCChip("Chip Two")
    chipTwo.leading.value @= Material.Icons.Navigation.ArrowBackIos
    chipTwo.trailing.value @= Material.Icons.Navigation.ArrowForward
    chipTwo.event.click.on {
      chipSet.children += new MDCChip("Chip Again!")
    }
    chipSet.children += chipTwo
    chipSet.children += new MDCChip("Chip Three")
    chipCard.children += chipSet

    panel.children += chipCard

    // Cards
    val cardCard = new MDCCard
    cardCard.element.style.padding = "16px"
    cardCard.element.style.marginBottom = "16px"

    cardCard.children += sectionHeading("Cards")

    val outlined = new MDCCard(isOutlined = true)
    outlined.element.style.padding = "16px"
    outlined.element.style.marginBottom = "8px"
    outlined.children += new TextView { content @= "This is an outlined card" }
    cardCard.children += outlined

    val elevated = new MDCCard
    elevated.element.style.padding = "16px"
    elevated.children += new TextView { content @= "This is an elevated card" }
    cardCard.children += elevated

    panel.children += cardCard

    // Nested Tab Bar
    val nestedTabCard = new MDCCard
    nestedTabCard.element.style.padding = "16px"
    nestedTabCard.element.style.marginBottom = "16px"

    nestedTabCard.children += sectionHeading("Nested Tab Bar")

    val nestedTabBar = new MDCTabBar
    val nTab1 = new MDCTab("Alpha")
    nTab1.setInitiallyActive()
    nestedTabBar.tabs.children += nTab1
    nestedTabBar.tabs.children += new MDCTab("Beta")
    nestedTabBar.tabs.children += new MDCTab("Gamma")
    nestedTabCard.children += nestedTabBar
    nestedTabBar.init()

    val nestedLabel = new TextView { content @= "Active tab: Alpha" }
    nestedTabBar.onActivated.attach { index =>
      val names = List("Alpha", "Beta", "Gamma")
      nestedLabel.content @= s"Active tab: ${names(index)}"
    }
    nestedTabCard.children += nestedLabel

    panel.children += nestedTabCard
  }
}
