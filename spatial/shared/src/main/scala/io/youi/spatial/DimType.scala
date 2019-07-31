package io.youi.spatial

sealed trait DimType

object DimType {
  case object Auto extends DimType
  case object Manual extends DimType
}