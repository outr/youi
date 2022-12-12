package io.youi.example.ui

import cats.effect.IO
import io.youi.{Color, dom}
import io.youi.example.screen.UIExampleScreen
import io.youi.material._
import spice.net._

class MDCExample extends UIExampleScreen {
  override def title: String = "MDC Example"
  override def path: Path = path"/examples/mdc.html"

  override def createUI(): IO[Unit] = for {
    _ <- MaterialComponents.waitForLoaded()
  } yield {
    MaterialComponents.theme.primary := Color.DarkBlue //ClientExampleApplication.colors.blue.dark

    val chipSet = new MDCChipSet
    chipSet.children += new MDCChip("Chip One")
    val two = new MDCChip("Chip Two")
    two.leading.value @= Material.Icons.Navigation.ArrowBackIos
    two.trailing.value @= Material.Icons.Navigation.ArrowForward
    two.event.click.on {
      chipSet.children += new MDCChip("Chip Again!")
    }
    chipSet.children += two
    chipSet.children += new MDCChip("Chip Three")
    container.children += chipSet

    val button = new MDCButton
    button.label @= "My Button"
    container.children += button

    val textField = new MDCTextField
    textField.outlined @= true
    textField.label @= "Username Test"
    container.children += textField

    val iconButton = new MDCIconButton
    container.children += iconButton

    val iconButtonToggle = new MDCIconButtonToggle
    container.children += iconButtonToggle

    val progress = new MDCProgressBar
    progress.value @= -1.0
    container.children += progress

    container.children += dom.create.br
    container.children += dom.create.br

    val topBar = new MDCTopAppBar
    topBar.menu.event.click.on {
      topBar.collapsed @= !topBar.collapsed()
    }
    topBar.main.children += new MDCTextField {
      label @= "Testing"
    }
    topBar.controls.children += new MDCIconButton(Material.Icons.Action.Bookmarks)
    topBar.controls.children += new MDCIconButton(Material.Icons.Action.Eject)
    container.children += topBar
  }
}