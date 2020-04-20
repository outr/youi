package io.youi.component

import io.youi.dom
import org.scalajs.dom.html

class TextInputList[T](val list: DataList[T], element: html.Input = dom.create.input) extends TextInput(element) {
//  element.setAttribute("list", list.id())
}