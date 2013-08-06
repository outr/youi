package org.hyperscala.ui.form

import org.powerscala.property.Property

import org.hyperscala.html._
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.reflect._
import org.hyperscala.ui.convert.Converter
import org.powerscala.log.Logging
import org.hyperscala.ui.form.error.ErrorSupport
import org.hyperscala.web.site.Webpage
import org.hyperscala.ui.BusyDialog

/**
 * CaseForm allows convenient wrapping around an HTML Form to easier access and validate the fields within.
 *
 * @author Matt Hicks <matt@outr.com>
 */
abstract class CaseForm[T](val form: tag.Form,
                           errorSupport: ErrorSupport,
                           realTime: Boolean = false)(implicit manifest: Manifest[T]) extends Logging {
  Webpage().require(BusyDialog)

  val property = Property[T]()

  def processingLabel = "Processing..."

  property.change.on {
    case evt => refreshFormFromProperty()
  }
  form.submitEvent.on {
    case evt => if (validate()) {
      submitForm()
    }
  }

  private def submitForm() = {
    BusyDialog(processingLabel) {
      submit()
    }
  }

  private val clazz: EnhancedClass = manifest.runtimeClass
  private var fieldMap = createCaseFields()
  private var validators = List.empty[() => ValidationResponse]

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
   * Adds a validator for the supplied fields (may be empty) and returns an error message if validation failed
   * or None.
   *
   * @param fields the fields the validation is applied to. May be None.
   * @param f the validator function
   */
  def validator(fields: String*)(f: => Option[String]) = synchronized {
    val caseFields = fields.map(name => field[Any](name))
    val v = () => {
      val result: Option[String] = f

      result match {
        case Some(error) => ValidationResponse(error, caseFields.map(cf => cf.field): _*)
        case None => ValidationResponse.Valid
      }
    }
    validators = (v :: validators.reverse).reverse
  }

  /**
   * Receives the value of the field during validation.
   *
   * @param field to validate against
   * @param f the validation function returns Some(errorMessage) or None if validation passed
   * @tparam V the type of the value expected from field
   */
  def fieldValidator[V](field: String)(f: V => Option[String]) = synchronized {
    val caseValue = clazz.caseValue(field).getOrElse(throw new NullPointerException(s"No field found for $field"))
    validator(field) {
      val value = caseValue[V](property().asInstanceOf[AnyRef])
      f(value)
    }
  }

  /**
   * Executes all validators and returns true if validation was successful.
   *
   * @return true if no validation errors occurred
   */
  def validate() = {
    errorSupport.clear()

    refreshPropertyFromForm()

    var validated = true
    validators.map(v => v()).collect {
      case response if response.error != null => {
        errorSupport.add(response.error, response.fields: _*)
        validated = false
      }
    }

    errorSupport.display()

    validated
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

  /**
   * Invoked upon successful validation upon form submit or after field changes (if realTime is true).
   */
  def submit(): Unit

  private def createCaseFields(): Map[String, CaseFormField[Any]] = {
    clazz.caseValues.map {
      case cv => {
        val name = cv.name
        val field = form.byId[FormField](name).getOrElse(throw new RuntimeException(s"Unable to find FormField with id of '$name' in $clazz"))
        field.value.change.on {   // TODO: verify against all FormFields that changes actually fire
          case evt => {
            val validated = validate()    // Runs validations every time a value changes
            if (validated && realTime) {
              submitForm()
            }
          }
        }
        val converter = Converter[Any](cv.valueType.javaClass.asInstanceOf[Class[Any]])
        if (converter.isEmpty) {
          warn(s"Unable to find acceptable converter for ${cv.valueType} ($name)")
        }
        name -> CaseFormField[Any](name, field, converter, cv)
      }
    }.toMap
  }
}

case class ValidationResponse(error: String, fields: FormField*)

object ValidationResponse {
  val Valid = ValidationResponse(null)
}

case class CaseFormField[V](name: String, field: FormField, converter: Option[Converter[V]], caseValue: CaseValue) {
  def value = converter match {
    case Some(c) => c.string2Value(field.value(), caseValue.valueType)
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