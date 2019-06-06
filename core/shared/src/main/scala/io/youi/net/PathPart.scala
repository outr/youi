package io.youi.net

sealed trait PathPart extends Any {
  def value: String
}

object PathPart {
  private val ArgumentPartRegex1 = """[:](.+)""".r
  private val ArgumentPartRegex2 = """[{](.+)[}]""".r

  def apply(value: String): Option[PathPart] = value match {
    case null | "" => None
    case ".." => Some(UpLevelPathPart)
    case "." => Some(SameLevelPathPart)
    case ArgumentPartRegex1(name) => Some(new ArgumentPathPart(name))
    case ArgumentPartRegex2(name) => Some(new ArgumentPathPart(name))
    case s => Some(new LiteralPathPart(s))
  }

  def equals(p1: PathPart, p2: PathPart): Boolean = if (p1 == p2) {
    true
  } else {
    p1 match {
      case _: LiteralPathPart => p2 match {
        case _: ArgumentPathPart => true
        case _ => false
      }
      case _: ArgumentPathPart => p2 match {
        case _: LiteralPathPart => true
        case _ => false
      }
      case _ => false
    }
  }
}

object UpLevelPathPart extends PathPart {
  override def value: String = ".."
}

object SameLevelPathPart extends PathPart {
  override def value: String = "."
}

class LiteralPathPart(val value: String) extends AnyVal with PathPart

class ArgumentPathPart(val name: String) extends AnyVal with PathPart {
  override def value: String = s":$name"
}