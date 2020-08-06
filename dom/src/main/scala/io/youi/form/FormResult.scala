package io.youi.form

import io.youi.History
import org.scalajs.dom.window

case class FormResult(messages: List[FormMessage] = Nil,
                      fieldErrors: List[FieldMessage] = Nil,
                      clearForm: Boolean = false,
                      reEnable: Boolean = true,
                      redirect: Option[Redirect] = None,
                      action: () => Unit = () => ()) {
  def message(message: FormMessage): FormResult = copy(messages = messages ::: List(message))
  def field(reference: FieldReference, message: String): FormResult = {
    copy(fieldErrors = fieldErrors ::: List(FieldMessage(reference, message)))
  }
  def clear: FormResult = copy(clearForm = true)
  def retain: FormResult = copy(clearForm = false)
  def enable: FormResult = copy(reEnable = true)
  def disable: FormResult = copy(reEnable = false)
  def redirect(redirect: Redirect): FormResult = copy(redirect = Some(redirect))
  def noRedirect: FormResult = copy(redirect = None)
  def action(f: => Unit): FormResult = copy(action = () => f)

  def invoke(form: FormSupport): Unit = {
    messages.foreach(_.invoke(form))
    fieldErrors.foreach(_.invoke(form))
    if (clearForm) {
      form.clear()
    }
    if (reEnable) {
      form.enable()
    }
    redirect.foreach { r =>
      val url = r.url(History.url())
      val f = () => if (r._push) {
        History.push(url)
      } else {
        History.set(url)
      }
      r.delay match {
        case Some(delay) => {
          window.setTimeout(() => {
            f()
          }, delay.toMillis.toDouble)
        }
        case None => f()
      }
    }
    action()
  }
}

object FormResult extends FormResult(Nil, Nil, false, true, None, () => ())