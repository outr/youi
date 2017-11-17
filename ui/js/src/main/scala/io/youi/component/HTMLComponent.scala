package io.youi.component

import org.scalajs.dom.html

trait HTMLComponent extends Component {
  protected def element: html.Element

}