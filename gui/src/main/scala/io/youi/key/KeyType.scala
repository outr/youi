package io.youi.key

case class KeyType private(name: String)

object KeyType {
  val Lowercase: KeyType = KeyType("Lowercase")
  val Uppercase: KeyType = KeyType("Uppercase")
  val Numeric: KeyType = KeyType("Numeric")
  val Symbols: KeyType = KeyType("Symbols")
  val Special: KeyType = KeyType("Special")
  val Modifier: KeyType = KeyType("Modifier")
  val Whitespace: KeyType = KeyType("Whitespace")
  val Navigation: KeyType = KeyType("Navigation")
  val Editing: KeyType = KeyType("Editing")
  val UI: KeyType = KeyType("UI")
  val Device: KeyType = KeyType("Device")
  val IME: KeyType = KeyType("IME")
  val Function: KeyType = KeyType("Function")
  val Phone: KeyType = KeyType("Phone")
  val Multimedia: KeyType = KeyType("Multimedia")
  val Audio: KeyType = KeyType("Audio")
  val TV: KeyType = KeyType("TV")
  val Media: KeyType = KeyType("Media")
  val Speech: KeyType = KeyType("Speech")
  val Document: KeyType = KeyType("Document")
  val Application: KeyType = KeyType("Application")
  val Browser: KeyType = KeyType("Browser")
}