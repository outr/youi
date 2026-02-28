package youi.material

case class MaterialIcon(name: String) {
  def isEmpty: Boolean = name.isEmpty
  def nonEmpty: Boolean = name.nonEmpty
}