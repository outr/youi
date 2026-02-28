package youi.form

trait Validation {
  def validate(input: FormInput): ValidationResult

  def failure(message: String): ValidationResult = ValidationResult.Failure(message)
  def success: ValidationResult = ValidationResult.Success
  def condition(f: => Boolean)(message: => String): ValidationResult = if (f) {
    success
  } else {
    failure(message)
  }
}

object Validation {
  case class Conditional(cond: FormInput => Boolean, message: String => String) extends Validation {
    override def validate(input: FormInput): ValidationResult = condition(cond(input))(message(input.name.capitalize))
  }

  val NonEmpty: Validation = Conditional(_.option.nonEmpty, name => s"$name must not be empty")
  val Email: Validation = {
    val regex = """^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$"""
    Conditional(input => input.value.matches(regex), name => s"$name must be a valid email address")
  }

  def Length(minimum: Int = 0, maximum: Int = Int.MaxValue): Validation = {
    Conditional(input => (minimum to maximum).contains(input.value.length), name => s"$name must be between $minimum and $maximum length")
  }
  def EqualTo(that: FormInput): Validation = Conditional(_.option == that.option, name => s"$name must be equal to ${that.name.capitalize}")
  def NotEqual(bad: => String, message: String => String): Validation = Conditional(!_.option.contains(bad), message)
  def Regex(regex: String, message: String => String): Validation = Conditional(_.value.matches(regex), message)
}