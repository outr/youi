package io.youi.component.types

import io.youi.Stringify

object ColumnFill extends Stringify[ColumnFill] {
  case object Auto extends ColumnFill("auto")
  case object Balance extends ColumnFill("balance")
  case object BalanceAll extends ColumnFill("balance-all")
  case object Inherit extends ColumnFill("inherit")
  case object Initial extends ColumnFill("initial")
  case object Unset extends ColumnFill("unset")

  private lazy val map = List(Auto, Balance, BalanceAll, Inherit, Initial, Unset)
    .map(s => s.value -> s)
    .toMap

  override def fromString(value: String): Option[ColumnFill] = map.get(value)

  override def toString(value: ColumnFill): Option[String] = Some(value.value)
}

sealed abstract class ColumnFill(val value: String)