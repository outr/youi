package io.youi.form

object FormMessage {
  case class Success(message: String, clearFirst: Boolean = true) extends FormMessage {
    override def invoke(form: FormSupport): Unit = form.alert.success(message, clearFirst)
  }
  case class Info(message: String, clearFirst: Boolean = true) extends FormMessage {
    override def invoke(form: FormSupport): Unit = form.alert.info(message, clearFirst)
  }
  case class Warning(message: String, clearFirst: Boolean = true) extends FormMessage {
    override def invoke(form: FormSupport): Unit = form.alert.warning(message, clearFirst)
  }
  case class Danger(message: String, clearFirst: Boolean = true) extends FormMessage {
    override def invoke(form: FormSupport): Unit = form.alert.danger(message, clearFirst)
  }
}

sealed trait FormMessage {
  def invoke(form: FormSupport): Unit
}