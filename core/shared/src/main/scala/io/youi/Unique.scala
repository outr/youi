package io.youi

import java.util.concurrent.ThreadLocalRandom

object Unique {
  val LettersLower = "abcdefghijklmnopqrstuvwxyz"
  val LettersUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  val Numbers = "0123456789"
  val Readable = "ABCDEFGHJKLMNPQRSTWXYZ23456789"
  val Hexadecimal = s"${Numbers}abcdef"
  val LettersAndNumbers = s"$LettersLower$Numbers"
  val AllLettersAndNumbers = s"$LettersLower$LettersUpper$Numbers"

  private def r: ThreadLocalRandom = ThreadLocalRandom.current()

  /**
    * Generates a unique String using the characters supplied at the length defined.
    *
    * @param length the length of the resulting String. Defaults to 32.
    * @param characters the characters for use in the String. Defaults to AllLettersAndNumbers.
    * @return a unique String
    */
  def apply(length: Int = 32, characters: String = AllLettersAndNumbers): String = {
    val charMax = characters.length
    (0 until length).map(i => characters.charAt(r.nextInt(charMax))).mkString
  }

  /**
    * Convenience functionality to generate a UUID (https://en.wikipedia.org/wiki/Universally_unique_identifier)
    *
    * 32 characters of unique hexadecimal values with dashes representing 36 total characters
    */
  def uuid: String = {
    val a = apply(8, Hexadecimal)
    val b = apply(4, Hexadecimal)
    val c = apply(3, Hexadecimal)
    val d = apply(1, "89ab")
    val e = apply(3, Hexadecimal)
    val f = apply(12, Hexadecimal)
    s"$a-$b-4$c-$d$e-$f"
  }

  /**
    * Returns the number of possible values for a specific length and characters.
    */
  def poolSize(length: Int = 32, characters: String = AllLettersAndNumbers): Long = {
    math.pow(characters.length, length).toLong
  }
}