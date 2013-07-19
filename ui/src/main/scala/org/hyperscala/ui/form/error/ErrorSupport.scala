package org.hyperscala.ui.form.error

import org.hyperscala.html.HTMLTag
import scala.collection.mutable.ListBuffer

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait ErrorSupport {
  private var errors = ListBuffer.empty[ErrorMessage]

  final def add(message: String, elements: HTMLTag*) = synchronized {
    errors += ErrorMessage(message, elements.toList)
  }

  final def clear() = synchronized {
    errors.clear()
  }

  final def display() = {
    clearDisplay()
    errors.foreach {
      case error => displayError(error)
    }
  }

  protected def clearDisplay(): Unit = {}

  protected def displayError(error: ErrorMessage): Unit = {}
}

case class ErrorMessage(message: String, elements: List[HTMLTag])