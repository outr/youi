package org.hyperscala.bean

import org.hyperscala.html._
import constraints.BodyChild
import org.powerscala.property.Property
import org.powerscala.reflect._
import org.powerscala.property.event.PropertyChangeEvent
import org.powerscala.property.backing.CaseValueVariableBacking

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class BeanForm[T](value: T)(implicit manifest: Manifest[T]) extends Form {
  method := "post"

  val property = Property[T]("property", value)

  val fields = manifest.erasure.caseValues.map(caseValue => createField(caseValue)).collect {
    case Some(beanField) => beanField
  }
  val button = createButton()

  protected def createField(caseValue: CaseValue): scala.Option[BeanField[T, _]] = caseValue.valueType.javaClass.getSimpleName match {
    case "String" => Some(StringField[T](this, caseValue, caseValue[String](value.asInstanceOf[AnyRef])))
    // TODO: support other field types
  }

  protected def createButton() = new Button(buttonType = "submit", content = "Submit")

  fields.foreach {
    case field => contents += field
  }
  contents += button
}

trait BeanField[P, T] extends BodyChild {
  def form: BeanForm[P]
  def caseValue: CaseValue
  def default: T
  val backing = new CaseValueVariableBacking[P, T](form.property, caseValue)
  val property = Property[T]("property", default, backing)
  form.property.listeners.synchronous {
    case evt: PropertyChangeEvent => property.fireChanged()
  }

  def generateLabelString = caseValue.name.flatMap(c => if (c.isUpper || c.isDigit) " %s".format(c) else c.toString).capitalize
}

trait BasicBeanField[P, T] extends Div with BeanField[P, T] {
  val label = new Label(forElement = caseValue.name, content = generateLabelString)
  def field: BodyChild

  field.name := caseValue.name
  field.id := caseValue.name

  contents += label
  contents += field
}

case class StringField[P](form: BeanForm[P], caseValue: CaseValue, default: String) extends BasicBeanField[P, String] {
  lazy val field = new Input(width = "50")

  field.value.bind(property)
  property.bind(field.value)

  property.fireChanged()
}