package youi

sealed trait Direction

sealed trait Compass extends Direction {
  def horizontal: Horizontal
  def vertical: Vertical
}

object Compass {
  case object Center extends Compass {
    override def horizontal: Horizontal = Horizontal.Center
    override def vertical: Vertical = Vertical.Middle
  }
  case object North extends Compass {
    override def horizontal: Horizontal = Horizontal.Center
    override def vertical: Vertical = Vertical.Top
  }
  case object South extends Compass {
    override def horizontal: Horizontal = Horizontal.Center
    override def vertical: Vertical = Vertical.Bottom
  }
  case object East extends Compass {
    override def horizontal: Horizontal = Horizontal.Right
    override def vertical: Vertical = Vertical.Middle
  }
  case object West extends Compass {
    override def horizontal: Horizontal = Horizontal.Left
    override def vertical: Vertical = Vertical.Middle
  }
  case object NorthWest extends Compass {
    override def horizontal: Horizontal = Horizontal.Left
    override def vertical: Vertical = Vertical.Top
  }
  case object NorthEast extends Compass {
    override def horizontal: Horizontal = Horizontal.Right
    override def vertical: Vertical = Vertical.Top
  }
  case object SouthWest extends Compass {
    override def horizontal: Horizontal = Horizontal.Left
    override def vertical: Vertical = Vertical.Bottom
  }
  case object SouthEast extends Compass {
    override def horizontal: Horizontal = Horizontal.Right
    override def vertical: Vertical = Vertical.Bottom
  }
}

sealed abstract class Horizontal(val compass: Compass) extends Direction

object Horizontal {
  case object Left extends Horizontal(Compass.West)
  case object Center extends Horizontal(Compass.Center)
  case object Right extends Horizontal(Compass.East)
}

sealed abstract class Vertical(val compass: Compass) extends Direction

object Vertical {
  case object Top extends Vertical(Compass.North)
  case object Middle extends Vertical(Compass.Center)
  case object Bottom extends Vertical(Compass.South)
}