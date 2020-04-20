package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.{Border, ColumnFill, Prop, SizeProperty}
import io.youi.theme.Theme

import scala.scalajs.js.|

class ColumnFeature(override val parent: FeatureParent) extends Feature {
  lazy val count: Prop[Int] = new Prop[Int](-1, i => parent.css.columnCount = i)
  lazy val fill: Prop[ColumnFill] = Prop.stringify[ColumnFill](parent.css.columnFill, parent.css.columnFill_=, ColumnFill, ColumnFill.Initial)
  lazy val gap: SizeProperty = new SizeProperty("", s => parent.css.columnGap = s)
  lazy val rule: Prop[Border] = Prop.stringify(parent.css.columnRule, parent.css.columnRule_=, Border, Border.undefined)
  lazy val width: SizeProperty = new SizeProperty("", s => parent.css.columnWidth = s)
}