package org.hyperscala.bean

import org.powerscala.property.{StandardProperty, Property, PropertyParent}

import org.powerscala.reflect._
import org.hyperscala.editor._
import org.powerscala.property.backing.CaseValueVariableBacking
import org.hyperscala.persistence.{DoublePersistence, IntPersistence, StringPersistence, ValuePersistence}
import org.powerscala.EnumEntry
import org.hyperscala.html.Input
import org.powerscala.property.event.PropertyChangeEvent
import scala.Some
import org.powerscala.reflect.CaseValue

trait BeanContainer[T] extends PropertyParent with ValueEditor[T] with BeanFieldContainer {
  override type F = BasicBeanField

  def default: T
  def parentContainer: BeanContainer[_]
  def containerName: String

  /**
   * Defines the field names to include and in what order. If empty the fields and order are determined by class.
   *
   * Defaults to empty.
   */
  def fieldNames = List.empty[String]

  def manifest: Manifest[T]

  val property = Property[T]("property", default)
  protected var _nextTab = 0
  val fields = manifest.erasure.caseValues.map(caseValue => {
    val d = default match {
      case null => caseValue.valueType.defaultForType
      case _ => caseValue[Any](default.asInstanceOf[AnyRef])
    }
    val beanName = generateBeanName(caseValue)
    createBeanField(beanName, caseValue, d, this.asInstanceOf[BeanContainer[Any]])
  }).collect {
    case Some(beanField) => {
      beanField
    }
  }

  protected def assignTabs(fields: List[BeanField]): Unit = {
    if (fields.nonEmpty) {
      val field = fields.head
      field.field match {
        case container: BeanContainer[_] => assignTabs(container.fields)
        case container: ListEditor[_] => {
          container.valueEditor match {
            case valueContainer: BeanContainer[_] => assignTabs(valueContainer.fields)
            case ve => ve.tabIndex := nextTab
          }
          container.button.tabIndex := nextTab
        }
        case f => f.tabIndex := nextTab
      }
      assignTabs(fields.tail)
    }
  }

  def nextTab: Int = if (parentContainer != null) {
    parentContainer.nextTab
  } else {
    _nextTab += 1
    _nextTab
  }

  private def generateBeanName(caseValue: CaseValue) = hierarchicalName match {
    case null => caseValue.name
    case hn => "%s%s".format(hn, caseValue.name.capitalize)
  }

  def hierarchicalName: String = {
    if (parentContainer != null && parentContainer.hierarchicalName != null) {
      "%s%s".format(parentContainer.hierarchicalName, containerName.capitalize)
    } else {
      containerName
    }
  }

  def createBeanField(beanName: String, caseValue: CaseValue, default: Any, container: BeanContainer[Any]): scala.Option[F] = if (parentContainer != null) {
    parentContainer.createBeanField(beanName, caseValue, default, container)
  } else {
    // Create the property
    val backing = new CaseValueVariableBacking[Any, Any](container.property, caseValue)
    val property = Property[Any](beanName, default, backing)
    BeanContainer.this.property.listeners.synchronous {
      case evt: PropertyChangeEvent => property.fireChanged()
    }

    // Create the ValueEditor
    val manifest = Manifest.classType[Any](caseValue.valueType.javaClass)
    val editor = createEditor(beanName, property, container)(manifest)

    // Create the BeanField
    createField(beanName, caseValue, editor, container)
  }

  def createField(beanName: String, caseValue: CaseValue, editor: ValueEditor[Any], container: BeanContainer[Any]): scala.Option[F] = {
    // Create the BeanField
    val beanField = BasicBeanField(beanName, caseValue, editor)
    Some(beanField)
  }

  def createEditor[C](beanName: String, property: StandardProperty[C], container: BeanContainer[Any])(implicit manifest: Manifest[C]): ValueEditor[C] = {
    manifest.erasure.getSimpleName match {
      case "String" => new InputEditor[C](property)(StringPersistence.asInstanceOf[ValuePersistence[C]], manifest)
      case "boolean" => {
        val editor = new BooleanEditor(property.asInstanceOf[StandardProperty[Boolean]])
        editor.asInstanceOf[ValueEditor[C]]
      }
      case "int" => new InputEditor[C](property)(IntPersistence.asInstanceOf[ValuePersistence[C]], manifest)
      case "double" => new InputEditor[C](property)(DoublePersistence.asInstanceOf[ValuePersistence[C]], manifest)
      case _ if (classOf[EnumEntry[_]].isAssignableFrom(manifest.erasure)) => new EnumEntryEditor[C](property)(manifest)
      case _ if (manifest.erasure.isCase) => {
        val bean = new BeanDiv[C](property.name(), container, property())(manifest)
        bean.property.bind(property)
        property.bind(bean.property)
        bean
      }
      case s => {
        val sp = property
        new Input with ValueEditor[C] {
          def property = sp

          disabled := true

          property.onChange {
            value := (property() match {
              case null => ""
              case p => p.toString
            })
          }
        }
      }
    }
  }

  def showFields(fieldNames: String*): Unit = fieldNames.foreach {
    case fieldName => field(fieldName).renderTag := false
  }

  def hideFields(fieldNames: String*): Unit = fieldNames.foreach {
    case fieldName => field(fieldName).renderTag := false
  }

  def field(fieldName: String): F = {
    val index = fieldName.indexOf('.')
    if (index > -1) {
      val name = fieldName.substring(0, index)
      fieldByName(name) match {
        case basic: BasicBeanField => basic.field match {
          case container: BeanContainer[_] => container.field(fieldName.substring(index + 1))
          case beanEditor: ListBeanEditor[_] => beanEditor.valueEditor.field(fieldName.substring(index + 1))
          case f => throw new RuntimeException("%s is not a BeanContainer - %s".format(name, f.getClass))
        }
        case f => throw new RuntimeException("%s is not a BasicBeanField - %s".format(name, f.getClass))
      }
    } else {
      fieldByName(fieldName)
    }
  }

  private def fieldByName(name: String) = {
    fields.find(bf => bf.caseValue.name == name).getOrElse(throw new RuntimeException("Unable to find %s".format(name)))
  }

  override def validate() = {
    super.validate() match {
      case Some(error) => Some(error)
      case None => fields.map(f => f.field.validate()).collectFirst {
        case Some(error) => error
      }
    }
  }
}

trait BeanFieldContainer {
  type F <: BeanField
}