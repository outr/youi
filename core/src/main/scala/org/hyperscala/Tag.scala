package org.hyperscala

import org.powerscala.property.PropertyParent
import org.powerscala.naming.NamingFilter
import persistence._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Tag extends PropertyParent with Markup {
  implicit val stringPersistence = StringPersistence

  val name = PropertyAttribute[String]("name", null)

  protected lazy val xmlAttributes = new NamingFilter[XMLAttribute](this)

  /**
   * Updates attribute with value if it's not null.
   */
  protected def up[T](attribute: PropertyAttribute[T], value: T) = {
    if (value != null) {
      attribute := value
    }
  }

  protected def up(attribute: PropertyAttribute[Int], value: java.lang.Integer) = {
    if (value != null) {
      attribute := value.intValue()
    }
  }

  protected def up(attribute: PropertyAttribute[Char], value: java.lang.Character) = {
    if (value != null) {
      attribute := value.charValue()
    }
  }

  protected def up(attribute: PropertyAttribute[Boolean], value: java.lang.Boolean) = {
    if (value != null) {
      attribute := value.booleanValue()
    }
  }
}