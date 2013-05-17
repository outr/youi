package org.hyperscala

import event.TagCreated
import persistence._
import org.powerscala.{TypeFilteredIterator, Storage}
import org.powerscala.hierarchy.ParentLike
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.jdom2.Attribute

import org.powerscala.reflect._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Tag extends Markup with Storage[Any] {
  implicit val thisTag = this

  implicit val booleanPersistence = BooleanPersistence
  implicit val stringPersistence = StringPersistence

  private var _attributes: Map[String, PropertyAttribute[_]] = _
  def addAttribute(attribute: PropertyAttribute[_]) = synchronized {
    if (_attributes == null) {
      _attributes = Map.empty
    }
    _attributes += attribute.name -> attribute
  }
  def getAttribute[T](name: String) = if (_attributes != null) {
    _attributes.get(name).asInstanceOf[Option[PropertyAttribute[T]]]
  } else {
    None
  }
  def xmlAttributes = _attributes match {
    case null => Nil
    case _ => _attributes.values
  }

  lazy val tagCreated = new StandardHierarchyEventProcessor[TagCreated]("tagCreated")

  lazy val name = PropertyAttribute[String]("name", null)
  lazy val renderTag = PropertyAttribute[Boolean]("render", true, inclusion = InclusionMode.Exclude)

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

  /**
   * Updates attribute with value if it's not null.
   */
  protected def up[T](attribute: => PropertyAttribute[T], value: T) = {
    if (value != null) {
      attribute := value
    }
  }

  protected def up(attribute: => PropertyAttribute[Double], value: java.lang.Double) = {
    if (value != null) {
      attribute := value.doubleValue()
    }
  }

  protected def up(attribute: => PropertyAttribute[Int], value: java.lang.Integer) = {
    if (value != null) {
      attribute := value.intValue()
    }
  }

  protected def up(attribute: => PropertyAttribute[Char], value: java.lang.Character) = {
    if (value != null) {
      attribute := value.charValue()
    }
  }

  protected def up(attribute: => PropertyAttribute[Boolean], value: java.lang.Boolean) = {
    if (value != null) {
      attribute := value.booleanValue()
    }
  }

  private lazy val attributeFields = Tag.attributeFields(this)

  protected def attributeFromXML(a: Attribute): Boolean = {
    getAttribute(a.getName) match {
      case Some(propertyAttribute) => {
        propertyAttribute.read(this, a.getValue)
        true
      }
      case None => {
        val attributeName = a.getName match {
          case "type" => s"${xmlLabel}Type"
          case "class" => "clazz"
          case "for" => "forElement"
          case s => s
        }
        attributeFields.get(attributeName.toLowerCase) match {
          case Some(field) => {
            val propertyAttribute = field[PropertyAttribute[_]](this, computeIfLazy = true)
            propertyAttribute.read(this, a.getValue)
            true
          }
          case None => false
        }
      }
    }
  }

  def byId[T <: Tag](id: String)(implicit manifest: Manifest[T]) = TypeFilteredIterator[T](ParentLike.descendants(this)).find {
    case it: IdentifiableTag => it.id() == id
    case _ => false
  }

  def getById[T <: Tag](id: String)(implicit manifest: Manifest[T]) = {
    byId[T](id)(manifest).getOrElse(throw new NullPointerException("Unable to find '%s' by id.".format(id)))
  }

  def byTag[T <: Tag](implicit manifest: Manifest[T]) = TypeFilteredIterator[T](ParentLike.descendants(this)).toStream

  tagCreated.fire(TagCreated(this))
}

object Tag {
  private var map = Map.empty[String, Map[String, EnhancedField]]

  // Improve performance by re-using list for same tag
  private def attributeFields(tag: Tag) = synchronized {
    map.get(tag.xmlLabel) match {
      case Some(fieldsMap) => fieldsMap
      case None => {
        val fieldsMap = tag.getClass.fields.filter(f => f.returnType.hasType(classOf[PropertyAttribute[_]])).map(f => f.name.toLowerCase -> f).toMap
        map += tag.xmlLabel -> fieldsMap
        fieldsMap
      }
    }
  }
}