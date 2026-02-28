package youi.form

case class FieldMessage(reference: FieldReference, message: String) {
  def invoke(form: FormSupport): Unit = {
    val input = form.input.all().find { i =>
      reference match {
        case FieldReference.Name(n) => i.name == n
        case FieldReference.Id(id) => i.element.id == id
      }
    }.getOrElse(throw new RuntimeException(s"Unable to find $reference for $message"))
    input.error.show(message)
  }
}
