package io.youi

import io.youi.theme.Stringify

sealed abstract class Cursor(val value: String)

case class ImageCursor(url: String) extends Cursor(s"url($url)")

object Cursor extends Stringify[Cursor] {
  case object None extends Cursor("none")
  case object Auto extends Cursor("auto")
  case object Inherit extends Cursor("inherit")
  case object Alias extends Cursor("alias")
  case object Cell extends Cursor("cell")
  case object ContextMenu extends Cursor("context-menu")
  case object Copy extends Cursor("copy")
  case object CrossHair extends Cursor("crosshair")
  case object Default extends Cursor("default")
  case object Grab extends Cursor("grab")
  case object Grabbing extends Cursor("grabbing")
  case object Help extends Cursor("help")
  case object Move extends Cursor("move")
  case object Pointer extends Cursor("pointer")
  case object Progress extends Cursor("progress")
  case object ResizeNorth extends Cursor("n-resize")
  case object ResizeEast extends Cursor("e-resize")
  case object ResizeSouth extends Cursor("s-resize")
  case object ResizeWest extends Cursor("w-resize")
  case object ResizeNorthSouth extends Cursor("ns-resize")
  case object ResizeEastWest extends Cursor("ew-resize")
  case object ResizeNorthEast extends Cursor("ne-resize")
  case object ResizeNorthWest extends Cursor("nw-resize")
  case object ResizeSouthEast extends Cursor("se-resize")
  case object ResizeSouthWest extends Cursor("sw-resize")
  case object ResizeNorthEastSouthWest extends Cursor("nesw-resize")
  case object ResizeNorthWestSouthEast extends Cursor("nwse-resize")
  case object Text extends Cursor("text")
  case object Wait extends Cursor("wait")
  case object ZoomIn extends Cursor("zoom-in")
  case object ZoomOut extends Cursor("zoom-out")

  lazy val map: Map[String, Cursor] = List(
    None,
    Auto,
    Inherit,
    Alias,
    Cell,
    ContextMenu,
    Copy,
    CrossHair,
    Default,
    Grab,
    Grabbing,
    Help,
    Move,
    Pointer,
    Progress,
    ResizeNorth,
    ResizeEast,
    ResizeSouth,
    ResizeWest,
    ResizeNorthSouth,
    ResizeEastWest,
    ResizeNorthEast,
    ResizeNorthWest,
    ResizeSouthEast,
    ResizeSouthWest,
    ResizeNorthEastSouthWest,
    ResizeNorthWestSouthEast,
    Text,
    Wait,
    ZoomIn,
    ZoomOut
  ).map(c => c.value -> c).toMap

  override def fromString(value: String): Option[Cursor] = map.get(value)

  override def toString(value: Cursor): Option[String] = Some(value.value)
}