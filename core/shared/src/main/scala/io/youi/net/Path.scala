package io.youi.net

import scala.reflect.macros.blackbox

case class Path(parts: List[PathPart]) extends Location {
  lazy val absolute: Path = {
    var entries = Vector.empty[PathPart]
    parts.foreach {
      case UpLevelPathPart => entries = entries.dropRight(1)
      case SameLevelPathPart => // Ignore
      case part => entries = entries :+ part
    }
    Path(entries.toList)
  }
  lazy val encoded: String = absolute.parts.map(_.value).map(URL.encode).mkString("/", "/", "")
  lazy val decoded: String = absolute.parts.map(_.value).mkString("/", "/", "")

  lazy val arguments: List[String] = parts.collect {
    case part: ArgumentPathPart => part.name
  }

  def withArguments(arguments: Map[String, String]): Path = copy(parts = parts.map {
    case part: ArgumentPathPart if arguments.contains(part.name) => new LiteralPathPart(arguments(part.name))
    case part => part
  })

  def withParams(params: (String, String)*): String = if (params.nonEmpty) {
    s"$this?${params.map(t => s"${t._1}=${t._2}").mkString("&")}"
  } else {
    toString
  }

  def extractArguments(literal: Path): Map[String, String] = {
    assert(parts.length == literal.parts.length, s"Literal path must have the same number of parts as the one being extracted for")
    parts.zip(literal.parts).flatMap {
      case (p1, p2) => p1 match {
        case ap: ArgumentPathPart => Some(ap.name -> p2.value)
        case _ => None
      }
    }.toMap
  }

  def append(path: String): Path = if (path.startsWith("/")) {
    Path.parse(path)
  } else {
    val left = parts.dropRight(1)
    val right = Path.parse(path, absolutize = false)
    Path(left ::: right.parts)
  }

  override def equals(obj: Any): Boolean = obj match {
    case that: Path if this.parts.length == that.parts.length => {
      this.parts.zip(that.parts).forall {
        case (thisPart, thatPart) => PathPart.equals(thisPart, thatPart)
      }
    }
    case _ => false
  }

  override def toString: String = encoded
}

object Path {
  val empty = Path(Nil)

  def parse(path: String, absolutize: Boolean = true): Path = {
    val updated = if (path.startsWith("/")) {
      path.substring(1)
    } else {
      path
    }
    val parts = updated.split('/').toList.map(URL.decode).flatMap(PathPart.apply)
    Path(parts) match {
      case p if absolutize => p.absolute
      case p => p
    }
  }

  def interpolate(c: blackbox.Context)(args: c.Expr[Any]*): c.Expr[Path] = {
    import c.universe._

    implicit val liftablePathPart: Liftable[PathPart] = new Liftable[PathPart] {
      override def apply(value: PathPart): c.universe.Tree = {
        q"""io.youi.net.PathPart(${value.value}).getOrElse(throw new RuntimeException("Invalid PathPart value"))"""
      }
    }

    c.prefix.tree match {
      case Apply(_, List(Apply(_, rawParts))) => {
        val parts = rawParts map { case t @ Literal(Constant(const: String)) => (const, t.pos) }

        val b = new StringBuilder
        parts.zipWithIndex.foreach {
          case ((raw, _), index) => {
            if (index > 0) {
              c.abort(c.enclosingPosition, "Path interpolation can only contain string literals. Use Path.parse for runtime parsing.")
            }
            b.append(raw)
          }
        }
        val path = Path.parse(b.toString())
        c.Expr[Path](q"Path(List(..${path.parts}))")
      }
      case _ => c.abort(c.enclosingPosition, "Bad usage of Path interpolation.")
    }
  }
}