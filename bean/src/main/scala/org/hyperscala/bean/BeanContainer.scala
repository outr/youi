package org.hyperscala.bean

import org.powerscala.property.{StandardProperty, Property, PropertyParent}

import org.powerscala.reflect._
import org.hyperscala.editor.{BooleanEditor, EnumEntryEditor, InputEditor, ValueEditor}
import org.powerscala.property.backing.CaseValueVariableBacking
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.persistence.{IntPersistence, StringPersistence, ValuePersistence}
import org.powerscala.EnumEntry

trait BeanContainer[T] extends PropertyParent with ValueEditor[T] with BeanFieldContainer {
  override type F = BasicBeanField

  def default: T
  def parentContainer: BeanContainer[_]

  def manifest: Manifest[T]

  val property = Property[T]("property", default)
  val fields = manifest.erasure.caseValues.map(caseValue => {
    val d = default match {
      case null => caseValue.valueType.defaultForType
      case _ => caseValue[Any](default.asInstanceOf[AnyRef])
    }
    createBeanField(caseValue, d)
  }).collect {
    case Some(beanField) => beanField
  }

  def createBeanField(caseValue: CaseValue, default: Any): scala.Option[F] = {
    // Create the property
    val backing = new CaseValueVariableBacking[T, Any](BeanContainer.this.property, caseValue)
    val property = Property[Any](caseValue.name, default, backing)
    BeanContainer.this.property.listeners.synchronous {
      case evt: PropertyChangeEvent => property.fireChanged()
    }

    // Create the ValueEditor
    val manifest = Manifest.classType[Any](caseValue.valueType.javaClass)
    val editor = createEditor(property)(manifest)

    // Create the BeanField
    createField(caseValue, editor)
  }

  def createField(caseValue: CaseValue, editor: ValueEditor[Any]): scala.Option[F] = {
    // Create the BeanField
    Some(BasicBeanField(caseValue, editor))
  }

  def createEditor[C](property: StandardProperty[C])(implicit manifest: Manifest[C]): ValueEditor[C] = if (parentContainer != null) {
    parentContainer.createEditor[C](property)(manifest)
  } else {
    manifest.erasure.getSimpleName match {
      case "String" => new InputEditor[C](property)(StringPersistence.asInstanceOf[ValuePersistence[C]], manifest)
      case "boolean" => {
        val editor = new BooleanEditor(property.asInstanceOf[StandardProperty[Boolean]])
        editor.asInstanceOf[ValueEditor[C]]
      }
      case "int" => new InputEditor[C](property)(IntPersistence.asInstanceOf[ValuePersistence[C]], manifest)
      case _ if (classOf[EnumEntry[_]].isAssignableFrom(manifest.erasure)) => new EnumEntryEditor[C](property)(manifest)
      case _ if (manifest.erasure.isCase) => {
        val bean = new BeanDiv[C](this, property())(manifest)
        bean.property.bind(property)
        property.bind(bean.property)
        bean
      }
      case s => throw new RuntimeException("Unsupported field %s with type of %s".format(property.name(), s))
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
}

trait BeanFieldContainer {
  type F <: BeanField
}