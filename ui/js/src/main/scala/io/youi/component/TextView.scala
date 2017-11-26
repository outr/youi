package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.dom
import io.youi.theme.TextViewTheme
import org.scalajs.dom.html
import reactify.Var

class TextView(protected val element: html.Element = dom.create[html.Span]("span")) extends HTMLComponent[html.Element] {
  override lazy val theme: Var[TextViewTheme] = Var(TextView)
  override def `type`: String = "TextView"

  lazy val value: Var[String] = Var(element.textContent)

  override protected def init(): Unit = {
    super.init()

    value.attachAndFire { v =>
      element.textContent = v
    }
  }
}

object TextView extends TextViewTheme