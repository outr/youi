package org.hyperscala.bean

import org.powerscala.property.{StandardProperty, Property, PropertyParent}

import org.powerscala.reflect._
import org.hyperscala.editor.{InputEditor, ValueEditor}
import org.powerscala.property.backing.CaseValueVariableBacking
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.persistence.{StringPersistence, ValuePersistence}

trait BeanContainer[T] extends PropertyParent with ValueEditor[T] {
  def default: T

  def manifest: Manifest[T]

  val property = Property[T]("property", default)
  val fields = manifest.erasure.caseValues.map(caseValue => {
    val d = default match {
      case null => caseValue.valueType.defaultForType
      case _ => caseValue[Any](default.asInstanceOf[AnyRef])
    }
    createField(caseValue, d)
  }).collect {
    case Some(beanField) => beanField
  }

  def createField[C](caseValue: CaseValue, default: C): scala.Option[BeanField[T, C]] = {
    // Create the property
    val backing = new CaseValueVariableBacking[T, C](BeanContainer.this.property, caseValue)
    val property = Property[C](caseValue.name, default, backing)
    BeanContainer.this.property.listeners.synchronous {
      case evt: PropertyChangeEvent => property.fireChanged()
    }

    // Create the ValueEditor
    val manifest = Manifest.classType[C](caseValue.valueType.javaClass.asInstanceOf[Class[C]])
    val editor = createEditor(property)(manifest)

    // Create the BeanField
    Some(BasicBeanField[T, C](BeanContainer.this, caseValue, editor))
  }

  def createEditor[C](property: StandardProperty[C])(implicit manifest: Manifest[C]): ValueEditor[C] = manifest.erasure.getSimpleName match {
    case "String" => new InputEditor[C](property)(StringPersistence.asInstanceOf[ValuePersistence[C]], manifest)
    case _ if (manifest.erasure.isCase) => {
      val bean = new BeanDiv[C](property())(manifest)
      bean.property.bind(property)
      property.bind(bean.property)
      bean
    }
  }

  def showFields(fieldNames: String*): Unit = fieldNames.foreach {
    case fieldName => field(fieldName).renderTag := false
  }

  def hideFields(fieldNames: String*): Unit = fieldNames.foreach {
    case fieldName => field(fieldName).renderTag := false
  }

  def field(fieldName: String): BeanField[_, _] = {
    val index = fieldName.indexOf('.')
    if (index > -1) {
      val name = fieldName.substring(0, index)
      fieldByName(name) match {
        case basic: BasicBeanField[_, _] => basic.field match {
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

  def clear() = {
    fields.foreach {
      case f => f.field.clear()
    }
  }
}
