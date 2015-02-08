package org.hyperscala.jquery.ui.event

import org.hyperscala.event.BrowserEvent
import org.hyperscala.html.HTMLTag

/**
 * ButtonClicked is fired when a button is clicked on a Dialog.
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class ButtonClicked(tag: HTMLTag, name: String) extends BrowserEvent
