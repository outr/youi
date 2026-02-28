package youi.example.ui

import rapid.Task
import youi._
import youi.chat.{ChatMessage, ChatTheme, ChatView}
import youi.example.screen.UIExampleScreen
import youi.font.GoogleFont
import spice.net._

import scala.scalajs.js

class ChatExample extends UIExampleScreen {
  override def title: String = "Chat"
  override def path: URLPath = path"/examples/chat.html"

  override def createUI(): Task[Unit] = for {
    fnt <- GoogleFont.`Roboto`.load()
  } yield {
    val chatView = new ChatView
    chatView.font.family @= fnt.family
    chatView.font.size := ChatTheme.fontSize
    chatView.size.width := container.size.width
    chatView.size.height := container.size.height
    chatView.addMessage(ChatMessage.Text(ChatMessage.nextId(), "Welcome to YouI Chat! How can I help you today?", role = "assistant"))
    container.children += chatView

    // Simulate streaming assistant response for demo purposes
    chatView.messageSent.attach { msg =>
      val text = msg match {
        case t: ChatMessage.Text => t.text
        case i: ChatMessage.WithImage => i.text
        case _ => ""
      }
      val response = s"You said: \"$text\". This is a simulated streaming response to demonstrate LLM-style chat."
      val handle = chatView.addStreamingMessage()
      val words = response.split(" ")
      words.zipWithIndex.foreach { case (word, i) =>
        js.timers.setTimeout(i * 80.0) {
          handle.appendText(if (i == 0) word else s" $word")
        }
      }
    }
  }
}
