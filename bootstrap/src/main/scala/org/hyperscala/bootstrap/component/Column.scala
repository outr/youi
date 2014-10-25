package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.{AbstractClassProperty, ClassName, ClassOptionProperty}
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Column extends tag.Div {
  def this(large: Option[Int] = None,
           largeOffset: Option[Int] = None,
           medium: Option[Int] = None,
           mediumOffset: Option[Int] = None,
           small: Option[Int] = None,
           smallOffset: Option[Int] = None,
           extraSmall: Option[Int] = None,
           extraSmallOffset: Option[Int] = None) = {
    this()
    if (large.nonEmpty) this.large := large
    if (largeOffset.nonEmpty) this.largeOffset := largeOffset
    if (medium.nonEmpty) this.medium := medium
    if (mediumOffset.nonEmpty) this.mediumOffset := mediumOffset
    if (small.nonEmpty) this.small := small
    if (smallOffset.nonEmpty) this.smallOffset := smallOffset
    if (extraSmall.nonEmpty) this.extraSmall := extraSmall
    if (extraSmallOffset.nonEmpty) this.extraSmallOffset := extraSmallOffset
  }

  lazy val large = new AbstractClassProperty[Option[Int]](this, None) {
    override def toClass(c: Option[Int]) = c.map(columns => s"col-lg-$columns")
  }
  lazy val largeOffset = new AbstractClassProperty[Option[Int]](this, None) {
    override def toClass(c: Option[Int]) = c.map(offset => s"col-lg-offset-$offset")
  }
  lazy val medium = new AbstractClassProperty[Option[Int]](this, None) {
    override def toClass(c: Option[Int]) = c.map(columns => s"col-md-$columns")
  }
  lazy val mediumOffset = new AbstractClassProperty[Option[Int]](this, None) {
    override def toClass(c: Option[Int]) = c.map(offset => s"col-md-offset-$offset")
  }
  lazy val small = new AbstractClassProperty[Option[Int]](this, None) {
    override def toClass(c: Option[Int]) = c.map(columns => s"col-sm-$columns")
  }
  lazy val smallOffset = new AbstractClassProperty[Option[Int]](this, None) {
    override def toClass(c: Option[Int]) = c.map(offset => s"col-sm-offset-$offset")
  }
  lazy val extraSmall = new AbstractClassProperty[Option[Int]](this, None) {
    override def toClass(c: Option[Int]) = c.map(columns => s"col-xs-$columns")
  }
  lazy val extraSmallOffset = new AbstractClassProperty[Option[Int]](this, None) {
    override def toClass(c: Option[Int]) = c.map(offset => s"col-xs-offset-$offset")
  }
}