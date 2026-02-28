package youi.key

class CharacterKey private[youi](val char: Char,
                                 description: String,
                                 `type`: KeyType,
                                 reverse: => CharacterKey) extends Key(char.toString, description, `type`) {
  def isLower: Boolean = char.isLower
  def isUpper: Boolean = char.isUpper
  def toUpper: CharacterKey = if (isUpper) this else reverse
  def toLower: CharacterKey = if (isLower) this else reverse

  override def toString: String = s"CharacterKey(char: $char, description: $description)"
}
