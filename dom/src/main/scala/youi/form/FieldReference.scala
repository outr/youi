package youi.form

sealed trait FieldReference

object FieldReference {
  case class Name(name: String) extends FieldReference
  case class Id(id: String) extends FieldReference
}