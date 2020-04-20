package io.youi.key

class SymbolKey private[youi](val char: Char,
                              description: String) extends Key(char.toString, description, KeyType.Symbols) {
  override def toString: String = s"SymbolKey(char: $char, description: $description)"
}
