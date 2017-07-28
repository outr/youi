package io.youi.util

import java.io.File

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.powerscala.io._

import scala.collection.JavaConverters._

object KeyBuilder {
  private var names = Set.empty[String]
  private var map = Map.empty[Title, List[KeyValue]]

  def main(args: Array[String]): Unit = {
    loadCharacters()
    loadNumbers()
    loadSymbols()
    loadFromMDN()

    val file = new File("ui/shared/src/main/scala/io/youi/Key.scala")

    val grouped = map.toList.sortBy(_._1.index)

    val lowerCase = map(Title("Lowercase")).reverse.map { value =>
      val name = value.value.replaceAllLiterally(" ", "")
      val char = value.value.charAt(0)
      s"""  val $name: CharacterKey = new CharacterKey('$char', "${value.description}", KeyType.Lowercase, Key.${char.toUpper})"""
    }.mkString("\n")
    val upperCase = map(Title("Uppercase")).reverse.map { value =>
      val name = value.value.replaceAllLiterally(" ", "")
      val char = value.value.charAt(0)
      s"""  val $name: CharacterKey = new CharacterKey('$char', "${value.description}", KeyType.Uppercase, Key.${char.toLower})"""
    }.mkString("\n")
    val symbols = map(Title("Symbols")).reverse.map { value =>
      val name = value.description.replaceAllLiterally(" ", "")
      val char = value.value.charAt(0)
      val charString = if (char == '\\') "'\\\\'" else s"'$char'"
      s"""  val $name: SymbolKey = new SymbolKey($charString, "${value.description}")"""
    }.mkString("\n")
    val others = grouped.collect {
      case (title, list) if !Set("Lowercase", "Uppercase", "Symbols").contains(title.name) => title.name -> list.reverse.map { value =>
        val name = value.value.replaceAllLiterally(" ", "") match {
          case "0" => "Zero"
          case "1" => "One"
          case "2" => "Two"
          case "3" => "Three"
          case "4" => "Four"
          case "5" => "Five"
          case "6" => "Six"
          case "7" => "Seven"
          case "8" => "Eight"
          case "9" => "Nine"
          case "" => "Space"
          case s => s
        }
        val description = value.description.replaceAllLiterally("\"", "\\\"")
        s"""  val $name: Key = new Key("${value.value}", "$description", KeyType.${title.name})"""
      }
    }.map {
      case (title, lines) =>
        s"""  // $title
           |${lines.mkString("\n")}
         """.stripMargin
    }.mkString("\n")

    val keyTypes = grouped.map {
      case (title, _) => s"""  val ${title.name} = KeyType("${title.name}")"""
    }.mkString("\n")

    val template =
      s"""package io.youi
         |
         |class Key private[youi](val value: String, val description: String, val `type`: KeyType) {
         |  Key.register(this)
         |}
         |
         |class CharacterKey private[youi](val char: Char,
         |                                 description: String,
         |                                 `type`: KeyType,
         |                                 reverse: => CharacterKey) extends Key(char.toString, description, `type`) {
         |  def isLower: Boolean = char.isLower
         |  def isUpper: Boolean = char.isUpper
         |  def toUpper: CharacterKey = if (isUpper) this else reverse
         |  def toLower: CharacterKey = if (isLower) this else reverse
         |}
         |
         |class SymbolKey private[youi](val char: Char,
         |                              description: String) extends Key(char.toString, description, KeyType.Symbols)
         |
         |object Key {
         |  private var map = Map.empty[String, Key]
         |
         |  // Lowercase
         |$lowerCase
         |
         |  // Uppercase
         |$upperCase
         |
         |  // Symbols
         |$symbols
         |
         |$others
         |
         |  private def register(key: Key): Unit = {
         |    map += key.value -> key
         |  }
         |
         |  def get(value: String): Option[Key] = map.get(value)
         |
         |  def apply(value: String): Key = get(value).getOrElse(throw new RuntimeException(s"Unable to find Key for '$$value'."))
         |}
         |
         |case class KeyType private(name: String)
         |
         |object KeyType {
         |$keyTypes
         |}""".stripMargin
    IO.stream(template, file)
  }

  private var titleIndex: Int = 0

  private def nextTitleIndex(): Int = try {
    titleIndex
  } finally {
    titleIndex += 1
  }

  private def loadCharacters(): Unit = {
    val lowerCaseTitle = Title("Lowercase")
    val upperCaseTitle = Title("Uppercase")
    var lc = List.empty[KeyValue]
    var uc = List.empty[KeyValue]
    "abcdefghijklmnopqrstuvwxyz".foreach { lowerCase =>
      lc = KeyValue(lowerCase.toString, s"Lowercase character '$lowerCase'.") :: lc
      val upperCase = lowerCase.toUpper
      uc = KeyValue(upperCase.toString, s"Uppercase character '$upperCase'.") :: uc
    }
    map += lowerCaseTitle -> lc
    map += upperCaseTitle -> uc
  }

  private def loadNumbers(): Unit = {
    val title = Title("Numeric")
    var list = List.empty[KeyValue]
    (0 to 9).foreach { index =>
      list = KeyValue(index.toString, s"Numeric value '$index'.") :: list
    }
    map += title -> list
  }

  private def loadSymbols(): Unit = {
    map += Title("Symbols") -> List(
      "~" -> "Tilde",
      "!" -> "Exclamation",
      "@" -> "At",
      "#" -> "Hash",
      "$" -> "Dollar",
      "%" -> "Percent",
      "^" -> "Circumflex",
      "&" -> "Ampersand",
      "*" -> "Asterisk",
      "(" -> "Open Paren",
      ")" -> "Close Paren",
      "_" -> "Underscore",
      "+" -> "Plus",
      "\"" -> "Double Quote",
      "|" -> "Pipe",
      "-" -> "Minus",
      "{" -> "Open Curly Bracket",
      "}" -> "Close Curly Bracket",
      "," -> "Comma",
      "." -> "Period",
      "/" -> "Forward Slash",
      "`" -> "Back Quote",
      "[" -> "Open Bracket",
      "]" -> "Close Bracket",
      "\\" -> "Back Slash",
      ":" -> "Colon",
      ";" -> "Semicolon",
      "<" -> "Less Than",
      ">" -> "Greater Than",
      "=" -> "Equals",
      "?" -> "Question Mark"
    ).map(t => KeyValue(t._1, t._2))
  }

  private def loadFromMDN(): Unit = {
    val doc = Jsoup.connect("https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/key/Key_Values").get()
    doc.select(".standard-table").asScala.foreach { table =>
      val title = Title(findTitle(table).text().trim match {
        case s => s.substring(0, s.indexOf(' '))
      })
      val rows = table.select("tr")
      val firstHeader = rows.get(0).select("th")
      if (firstHeader.size() > 0 && firstHeader.get(0).text() == "KeyboardEvent.key Value") {
        rows.asScala.toList.foreach { row =>
          val columns = row.select("td")
          if (columns.size() >= 2) {
            clean(columns.get(0).text().trim).foreach { value =>
              var list = map.getOrElse(title, Nil)
              val description = columns.get(1).text().trim
              if (names.contains(value)) {
                scribe.warn(s"Ignoring duplicate name: $value.")
              } else {
                list = KeyValue(value, description) :: list
                map += title -> list
                names += value
              }
            }
          }
        }
      }
    }
  }

  private val SingleRegex = """"([a-zA-Z0-9]+)"""".r
  private val ExplanationRegex = """"([a-zA-Z0-9 ]+)" \[\d\]""".r
  private val ExplanationMistakeRegex = """"([a-zA-Z0-9 ]+)" \[\d\]f""".r
  private def clean(value: String): Option[String] = value match {
    case SingleRegex(v) => Some(v)
    case ExplanationRegex(v) => Some(v)
    case ExplanationMistakeRegex(v) => Some(v)
    case """"0" through "9"""" => None
    case _ => throw new RuntimeException(s"Unsupported value: $value")
  }

  private def findTitle(element: Element): Element = {
    val previous = element.previousElementSibling()
    if (previous.tagName() == "h2") {
      previous
    } else {
      findTitle(previous)
    }
  }

  class Title(val name: String) {
    val index: Int = nextTitleIndex()

    override def toString: String = s"$name ($index)"
  }

  object Title {
    def apply(name: String): Title = map.keys.find(_.name == name).getOrElse(new Title(name))
  }

  case class KeyValue(value: String, description: String)
}