package org.hyperscala.ui.form

import org.powerscala.property.Property

import org.hyperscala.html._
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.reflect._
import org.hyperscala.ui.convert.Converter

/**
 * CaseForm allows convenient wrapping around an HTML Form to easier access and validate the fields within.
 *
 * @author Matt Hicks <matt@outr.com>
 */
class CaseForm[T](val form: tag.Form)(implicit manifest: Manifest[T]) {
  val property = Property[T]()

  property.change.on {
    case evt => refreshFormFromProperty()
  }

  private val clazz: EnhancedClass = manifest.runtimeClass
  private var fieldMap = createCaseFields()
  private var validators = List.empty[() => Option[String]]

  /**
   * Retrieve the CaseFormField by field name.
   *
   * @param name the name of the field name in the case class
   * @tparam V the value type represented for this field
   * @return CaseFormField[V]
   */
  def field[V](name: String) = fieldMap(name).asInstanceOf[CaseFormField[V]]

  /**
   * Updates a CaseFormField by name.
   *
   * @param name the name of the field name in the case class
   * @param updateFunction the function that receives the current CaseFormField and returns the updated version
   * @tparam V the value type represented for this field
   */
  def update[V](name: String)(updateFunction: CaseFormField[V] => CaseFormField[V]) = synchronized {
    val f = field(name)
    val updated = updateFunction(f.asInstanceOf[CaseFormField[V]])
    fieldMap += updated.name -> updated.asInstanceOf[CaseFormField[Any]]
  }

  /**
   * Clears the errors from the display.
   */
  def clearErrors() = {}

  /**
   * Called during validation if there is an error on a specific field.
   *
   * @param field CaseFormField the error occurred on
   * @param error the error message
   */
  def fieldError(field: CaseFormField[_], error: String) = {}

  /**
   * Called after validation completes to display error messages.
   *
   * @param messages the error messages that should be displayed
   */
  def errors(messages: String*) = {}

  /**
   * Adds a validator for the supplied fields (may be empty) and returns an error message if validation failed
   * or None.
   *
   * @param fields the fields the validation is applied to. May be None.
   * @param f the validator function
   */
  def validator(fields: String*)(f: () => Option[String]) = synchronized {
    val caseFields = fields.map(name => field[Any](name))
    val v = () => {
      val result = f()

      result match {
        case Some(error) => caseFields.foreach(cf => fieldError(cf, error))
        case None => // No error
      }

      result
    }
    validators = (v :: validators.reverse).reverse
  }

  /**
   * Executes all validators and returns true if validation was successful.
   *
   * @return true if no validation errors occurred
   */
  def validate() = {
    clearErrors()

    validators.map(v => v()).flatten match {
      case Nil => true
      case messages => {
        errors(messages: _*)
        false
      }
    }
  }

  private val refreshing = new AtomicBoolean(false)

  protected def fieldParseError[V](value: String, runtimeClass: Class[V]): Option[V] = {
    None
  }

  def refreshPropertyFromForm() = if (refreshing.compareAndSet(false, true)) {
    try {
      val valuesMap = fieldMap.values.map(cff => cff.value.map(v => cff.name -> v)).flatten.toMap
      property := clazz.create[T](valuesMap)
    } finally {
      refreshing.set(false)
    }
  }

  def refreshFormFromProperty() = if (refreshing.compareAndSet(false, true)) {
    try {
      val p = property().asInstanceOf[AnyRef]
      fieldMap.values.foreach(cff => cff.updateValueFromCaseClass(p))
    } finally {
      refreshing.set(false)
    }
  }

  private def createCaseFields() = {
    clazz.caseValues.map {
      case cv => {
        val name = cv.name
        val field = form.byId[FormField](name).getOrElse(throw new RuntimeException(s"Unable to find $name in $clazz"))
        val converter = Converter[Any](cv.valueType.javaClass.asInstanceOf[Class[Any]])
        name -> CaseFormField[Any](name, field, converter, cv)
      }
    }.toMap
  }
}

case class CaseFormField[V](name: String, field: FormField, converter: Option[Converter[V]], caseValue: CaseValue) {
  def value = converter match {
    case Some(c) => c.string2Value(field.value())
    case None => None
  }

  def value_=(v: V) = converter match {
    case Some(c) => field.value := c.value2String(v)
    case None => // No converter
  }

  def updateValueFromCaseClass(cc: AnyRef) = {
    val v = caseValue[V](cc)
    value = v
  }
}