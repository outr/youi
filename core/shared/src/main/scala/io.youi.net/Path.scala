package io.youi.net

import io.youi.util.URLUtil

case class Path(parts: List[String]) {
  lazy val encoded: String = absolute.parts.map(URLUtil.encode).mkString("/", "/", "")

  lazy val absolute: Path = {
    var entries = Vector.empty[String]
    parts.foreach {
      case ".." => entries = entries.dropRight(1)
      case "." => // Ignore
      case part => entries = entries :+ part
    }
    Path(entries.toList)
  }

  def append(path: String): Path = if (path.startsWith("/")) {
    Path.parse(path)
  } else {
    val left = if (parts.last != "") {
      parts.dropRight(1)
    } else {
      parts
    }
    val right = Path.parse(path, absolutize = false)
    Path(left ::: right.parts)
  }

  override def toString: String = encoded
}

object Path {
  val Empty = Path(Nil)

  def parse(path: String, absolutize: Boolean = true): Path = {
    val updated = if (path.startsWith("/")) {
      path.substring(1)
    } else {
      path
    }
    Path(updated.split('/').toList) match {
      case p if absolutize => p.absolute
      case p => p
    }
  }
}