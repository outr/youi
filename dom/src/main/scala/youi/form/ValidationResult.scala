package youi.form

sealed trait ValidationResult {
  def asOption: Option[String]
}

object ValidationResult {
  case class Failure(message: String) extends ValidationResult {
    override def asOption: Option[String] = Some(message)
  }
  case object Success extends ValidationResult {
    override def asOption: Option[String] = None
  }
}