package org.hyperscala

import event.TagCreated
import org.powerscala.property.PropertyParent
import org.powerscala.naming.NamingFilter
import persistence._
import org.powerscala.event.Listenable
import org.powerscala.Storage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Tag extends PropertyParent with Markup with Listenable with Storage[Any] {
  implicit val booleanPersistence = BooleanPersistence
  implicit val stringPersistence = StringPersistence

  val name = PropertyAttribute[String]("name", null)
  val renderTag = PropertyAttribute[Boolean]("render", true, inclusion = InclusionMode.Exclude)

  override def render = renderTag()

  /**
   * Recursively walks up the DOM validating the 'render' state on its parents returning true only if all parents have
   * the 'render' value set to true.
   */
  def renderable: Boolean = if (render) {
    parent match {
      case p: Tag => p.renderable
      case _ => true
    }
  } else {
    false
  }

  def outputString: String

  lazy val xmlAttributes = new NamingFilter[XMLAttribute](this)

  /**
   * Updates attribute with value if it's not null.
   */
  protected def up[T](attribute: PropertyAttribute[T], value: T) = {
    if (value != null) {
      attribute := value
    }
  }

  protected def up(attribute: PropertyAttribute[Double], value: java.lang.Double) = {
    if (value != null) {
      attribute := value.doubleValue()
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

  def byId[T <: Tag](id: String)(implicit manifest: Manifest[T]) = hierarchy.findFirst[T](t => t match {
    case it: IdentifiableTag => it.id() == id
    case _ => false
  })(manifest)

  def getById[T <: Tag](id: String)(implicit manifest: Manifest[T]) = {
    byId[T](id)(manifest).getOrElse(throw new NullPointerException("Unable to find '%s' by id.".format(id)))
  }

  fire(TagCreated(this))
}