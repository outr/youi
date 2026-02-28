package youi.component

import youi.component.support.ContainerSupport
import youi.dom
import youi.form.{FormInput, FormResult, FormSupport}
import org.scalajs.dom.html
import rapid.Task

abstract class FormContainer(formElement: html.Form = dom.create[html.Form]("form"))
    extends Component(formElement) with ContainerSupport {

  protected object formDelegate extends FormSupport {
    override val form: html.Form = formElement
    override protected def process(): Task[FormResult] = FormContainer.this.process()
  }

  private val formSubmit = dom.create[html.Input]("input")
  formSubmit.`type` = "submit"
  formSubmit.style.display = "none"
  formElement.appendChild(formSubmit)

  formDelegate.initForm()

  def form: html.Form = formElement
  def formInput: formDelegate.input.type = formDelegate.input
  def formAlert: formDelegate.alert.type = formDelegate.alert

  def wrap(component: Component): FormInput = formDelegate.input(component.element)
  def wrapAll(components: Component*): List[FormInput] = components.toList.map(wrap)

  def enable(): Unit = formDelegate.enable()
  def disable(): Unit = formDelegate.disable()
  def clearForm(): Unit = formDelegate.clear()
  def validate(): Boolean = formDelegate.validate()
  def submit(): Unit = formDelegate.submit()

  protected def process(): Task[FormResult]
}
