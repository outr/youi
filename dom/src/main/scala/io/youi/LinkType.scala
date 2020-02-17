package io.youi

sealed trait LinkType

object LinkType {
  case object Empty extends LinkType
  case object JavaScript extends LinkType
  case object Hash extends LinkType
  case object Internal extends LinkType
  case object External extends LinkType
}