package io.youi.component.types

import io.youi.Stringify

sealed abstract class Display(val value: String)

object Display extends Stringify[Display] {
  case object Block extends Display("block")
  case object Inline extends Display("inline")
  case object RunIn extends Display("run-in")
  case object Flow extends Display("flow")
  case object FlowRoot extends Display("flow-root")
  case object Table extends Display("table")
  case object Flex extends Display("flex")
  case object Grid extends Display("grid")
  case object Ruby extends Display("ruby")
  case object BlockFlow extends Display("block flow")
  case object InlineTable extends Display("inline table")
  case object FlexRunIn extends Display("flex run-in")
  case object ListItem extends Display("list-item")
  case object ListItemBlock extends Display("list-item block")
  case object ListItemInline extends Display("list-item inline")
  case object ListItemFlow extends Display("list-item flow")
  case object ListItemFlowRoot extends Display("list-item flow-root")
  case object ListItemBlockFlow extends Display("list-item block flow")
  case object ListItemBlockFlowRoot extends Display("list-item block flow-root")
  case object FlowListItemBlock extends Display("flow list-item block")
  case object TableRowGroup extends Display("table-row-group")
  case object TableHeaderGroup extends Display("table-header-group")
  case object TableFooterGroup extends Display("table-footer-group")
  case object TableRow extends Display("table-row")
  case object TableCell extends Display("table-cell")
  case object TableColumnGroup extends Display("table-column-group")
  case object TableColumn extends Display("table-column")
  case object TableCaption extends Display("table-caption")
  case object RubyBase extends Display("ruby-base")
  case object RubyText extends Display("ruby-text")
  case object RubyBaseContainer extends Display("ruby-base-container")
  case object RubyTextContainer extends Display("ruby-text-container")
  case object Contents extends Display("contents")
  case object None extends Display("none")
  case object Inherit extends Display("inherit")
  case object Initial extends Display("initial")
  case object Unset extends Display("unset")

  lazy val map: Map[String, Display] = List(
    Block, Inline, RunIn, Flow, FlowRoot, Table, Flex, Grid, Ruby, BlockFlow, InlineTable, FlexRunIn, ListItem,
    ListItemBlock, ListItemInline, ListItemFlow, ListItemFlowRoot, ListItemBlockFlow, ListItemBlockFlowRoot,
    FlowListItemBlock, TableRowGroup, TableHeaderGroup, TableFooterGroup, TableRow, TableCell, TableColumnGroup,
    TableColumn, TableCaption, RubyBase, RubyText, RubyBaseContainer, RubyTextContainer, Contents, None, Inherit,
    Initial, Unset
  ).map(d => d.value -> d).toMap

  override def fromString(value: String): Option[Display] = map.get(value.toLowerCase)

  override def toString(value: Display): Option[String] = if (value == Initial) {
    Option.empty[String]
  } else {
    Some(value.value)
  }
}