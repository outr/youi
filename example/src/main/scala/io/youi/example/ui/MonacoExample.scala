package io.youi.example.ui

import rapid.Task
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import io.youi.monaco.{MonacoEditorView, MonacoLoader}
import reactify.stateful2Value
import spice.net._

class MonacoExample extends UIExampleScreen {
  override def title: String = "Monaco Editor"
  override def path: URLPath = path"/examples/monaco.html"

  override def createUI(): Task[Unit] = MonacoLoader.load().map { _ =>
    val editor = new MonacoEditorView
    editor.size.width := container.size.width
    editor.size.height := container.size.height
    editor.theme @= (if (ExampleApp.darkMode()) "vs-dark" else "vs")
    editor.value @= sampleCode
    container.children += editor
    editor.initialize()

    ExampleApp.darkMode.attach { isDark =>
      editor.theme @= (if (isDark) "vs-dark" else "vs")
    }
  }

  private val sampleCode: String =
    """object HelloWorld {
      |  def main(args: Array[String]): Unit = {
      |    val greeting = "Hello from YouI + Monaco!"
      |    println(greeting)
      |
      |    val numbers = (1 to 10).toList
      |    val doubled = numbers.map(_ * 2)
      |    println(s"Doubled: $doubled")
      |
      |    // Pattern matching
      |    val result = doubled.headOption match {
      |      case Some(n) if n > 5 => s"$n is greater than 5"
      |      case Some(n)          => s"$n is 5 or less"
      |      case None             => "empty list"
      |    }
      |    println(result)
      |
      |    // Case class and collections
      |    case class Person(name: String, age: Int)
      |
      |    val people = List(
      |      Person("Alice", 30),
      |      Person("Bob", 25),
      |      Person("Charlie", 35)
      |    )
      |
      |    val names = people
      |      .filter(_.age >= 30)
      |      .map(_.name)
      |      .mkString(", ")
      |    println(s"30 or older: $names")
      |  }
      |}
      |""".stripMargin
}
