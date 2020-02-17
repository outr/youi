package io.youi

sealed trait StateType

object StateType {
  case object Push extends StateType
  case object Replace extends StateType
  case object Set extends StateType
  case object Pop extends StateType
}