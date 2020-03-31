package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.{Border, ColumnFill, Prop, SizeProperty}

class ColumnFeature(override val component: Component) extends Feature {
  lazy val count: Prop[Int] = new Prop[Int](-1, i => component.element.style.columnCount = i)
  lazy val fill: Prop[ColumnFill] = Prop.stringify[ColumnFill](component.element.style.columnFill, component.element.style.columnFill_=, ColumnFill, ColumnFill.Initial)
  lazy val gap: SizeProperty = new SizeProperty("", s => component.element.style.columnGap = s)
  lazy val rule: Prop[Border] = Prop.stringify(component.element.style.columnRule, component.element.style.columnRule_=, Border, Border.undefined)
  lazy val width: SizeProperty = new SizeProperty("", s => component.element.style.columnWidth = s)
}