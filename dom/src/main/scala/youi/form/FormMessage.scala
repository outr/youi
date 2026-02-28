package youi.form

import scala.concurrent.duration.FiniteDuration

object FormMessage {
  case class Success(message: String,
                     clearFirst: Boolean = true,
                     removeAfter: Option[FiniteDuration] = None) extends FormMessage {
    override def invoke(form: FormSupport): Unit = form.alert.success(message, clearFirst, removeAfter)
  }
  case class Info(message: String,
                  clearFirst: Boolean = true,
                  removeAfter: Option[FiniteDuration] = None) extends FormMessage {
    override def invoke(form: FormSupport): Unit = form.alert.info(message, clearFirst, removeAfter)
  }
  case class Warning(message: String,
                     clearFirst: Boolean = true,
                     removeAfter: Option[FiniteDuration] = None) extends FormMessage {
    override def invoke(form: FormSupport): Unit = form.alert.warning(message, clearFirst, removeAfter)
  }
  case class Danger(message: String,
                    clearFirst: Boolean = true,
                    removeAfter: Option[FiniteDuration] = None) extends FormMessage {
    override def invoke(form: FormSupport): Unit = form.alert.danger(message, clearFirst, removeAfter)
  }
}

sealed trait FormMessage {
  def invoke(form: FormSupport): Unit
}