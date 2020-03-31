package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.{Border, ColumnFill, Prop, SizeProperty}

class ColumnFeature(component: Component) extends Feature(component) {
  lazy val count: Prop[Int] = new Prop[Int](-1, i => element.style.columnCount = i)
  lazy val fill: Prop[ColumnFill] = Prop.stringify[ColumnFill](element.style.columnFill, element.style.columnFill_=, ColumnFill, ColumnFill.Initial)
  lazy val gap: SizeProperty = new SizeProperty("", s => element.style.columnGap = s)
  lazy val rule: Prop[Border] = Prop.stringify(element.style.columnRule, element.style.columnRule_=, Border, Border.undefined)
  lazy val width: SizeProperty = new SizeProperty("", s => element.style.columnWidth = s)
}