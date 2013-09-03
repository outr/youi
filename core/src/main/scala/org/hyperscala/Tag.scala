package org.hyperscala

import event.TagCreated
import persistence._
import org.powerscala.{TypeFilteredIterator, Storage}
import org.powerscala.hierarchy.ParentLike
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.jdom2.Attribute

import org.powerscala.reflect._
import org.powerscala.property.PropertyLike

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Tag extends Markup with AttributeContainer[PropertyAttribute[_]] {
  implicit val thisTag = this

  implicit val booleanPersistence = BooleanPersistence
  implicit val stringPersistence = StringPersistence

  def xmlAttributes = attributes.values

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

  override def getAttribute(name: String) = {
    val option = super.getAttribute(name)
    if (option.isDefined) {
      option
    } else if (name.startsWith("data-")) {
      Some(createAttribute[String](name, null))
    } else {
      val attributeName = name match {
        case "type" if List("link", "script").contains(xmlLabel) => "mimeType"
        case "type" => s"${xmlLabel}Type"
        case "class" => "clazz"
        case "for" => "forElement"
        case "title" => "titleText"
        case s if s.startsWith("on") => s.substring(2) + "Event"
        case s => s
      }
      attributeFields.get(attributeName.toLowerCase) match {
        case Some(field) => {
          field[PropertyLike[_]](this, computeIfLazy = true)    // Make sure it's loaded if it's lazy
          super.getAttribute(name)
        }
        case None => None
      }
    }
  }

  protected def attributeFromXML(a: Attribute): Boolean = {
    getAttribute(a.getName) match {
      case Some(propertyAttribute) => {   // Found the attribute property
        propertyAttribute.read(this, a.getValue)
        true
      }
      case None => false
    }
  }

  def dataAttribute(name: String) = getAttribute(s"data-$name") match {
    case Some(propertyAttribute) => propertyAttribute.asInstanceOf[PropertyAttribute[String]]
    case None => createAttribute[String](s"data-$name", null)
  }

  def data(name: String) = getAttribute(s"data-$name").map(pa => pa.asInstanceOf[PropertyAttribute[String]].value)

  def data(name: String, value: String) = dataAttribute(name).value = value

  def attribute[T](name: String, create: Boolean = false)(implicit persister: ValuePersistence[T], manifest: Manifest[T]) = getAttribute(name) match {
    case Some(propertyAttribute) => Some(propertyAttribute.asInstanceOf[PropertyAttribute[T]])
    case None => if (create) {
      Some(createAttribute[T](name, manifest.runtimeClass.defaultForType[T]))
    } else {
      None
    }
  }

  private def createAttribute[T](name: String, value: T, inclusionMode: InclusionMode = InclusionMode.NotEmpty)
                        (implicit persister: ValuePersistence[T], manifest: Manifest[T]) = {
    PropertyAttribute[T](name, value, inclusionMode)
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
        val fieldsMap = tag.getClass.fields.filter(f => f.returnType.hasType(classOf[PropertyLike[_]])).map(f => f.name.toLowerCase -> f).toMap
        map += tag.xmlLabel -> fieldsMap
        fieldsMap
      }
    }
  }
}