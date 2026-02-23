package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.chat.{ChatMessage, ChatView, MessageBubble}
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
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
    chatView.font.size @= 14.0
    chatView.size.width := container.size.width
    chatView.size.height := container.size.height
    chatView.addMessage(ChatMessage("Welcome to YouI Chat! How can I help you today?", role = "assistant"))
    container.children += chatView

    // Simulate streaming assistant response for demo purposes
    chatView.messageSent.attach { msg =>
      val response = s"You said: \"${msg.text}\". This is a simulated streaming response to demonstrate LLM-style chat."
      val bubble = chatView.addMessage(ChatMessage("", role = "assistant"))
      val words = response.split(" ")
      words.zipWithIndex.foreach { case (word, i) =>
        js.timers.setTimeout(i * 80.0) {
          bubble.appendText(if (i == 0) word else s" $word")
        }
      }
    }
  }
}
