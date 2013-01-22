package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.ui.DynamicContent
import io.Source
import org.hyperscala.event.{ChangeEvent, ClickEvent, JavaScriptEvent}
import org.hyperscala.web.site.realtime.Realtime
import org.hyperscala.web.site.{Website, Webpage}
import org.powerscala.property.StandardProperty
import annotation.tailrec
import org.hyperscala.web.module.jQuery

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class ChatExample extends Webpage {
  Webpage().require(Realtime)

  val nickname = new StandardProperty[String]

  body.style.fontFamily = "Helvetica, sans-serif"

  val chatMain = DynamicContent(ChatExample.Main)

  val chatName = chatMain.load[tag.Input]("chatName")
  val message = chatMain.load[tag.TextArea]("chatMessage")
  val submit = chatMain.load[tag.Button]("submit")
  val messages = chatMain.load[tag.Div]("messages")

  chatName.event.change := JavaScriptEvent()
  message.event.change := JavaScriptEvent()
  submit.event.click := JavaScriptEvent()

  chatName.listeners.synchronous {
    case evt: ChangeEvent => updateNickname()
  }
  submit.listeners.synchronous {
    case evt: ClickEvent => sendMessage()
  }

  body.contents += chatMain

  ChatExample.chatHistory.foreach {   // Load history
    case (nick, text) => messages.contents += new ChatEntry(nick, text)
  }
  updateNickname()

  def sendMessage() = {
    ChatExample.sendMessage(nickname(), message.value())
    message.value := ""
    jQuery.focus(message)
  }

  def updateNickname() = {
    val current = chatName.value() match {
      case "" => "guest"
      case v => v
    }
    if (current != nickname()) {
      nickname := ChatExample.generateNick(current)
      chatName.value := nickname()
    }
  }
}

object ChatExample {
  val Main = Source.fromURL(getClass.getClassLoader.getResource("chat.html")).mkString
  val Entry = Source.fromURL(getClass.getClassLoader.getResource("chat_entry.html")).mkString

  private var history = List.empty[(String, String)]

  def instances = Website().sessions.valuesByType[ChatExample].toList
  @tailrec
  def generateNick(nickname: String, increment: Int = 0): String = {
    val nick = increment match {
      case 0 => nickname
      case _ => "%s%s".format(nickname, increment)
    }
    if (instances.find(c => c.nickname() == nick).isEmpty) {
      nick
    } else {
      generateNick(nickname, increment + 1)
    }
  }
  def sendMessage(nickname: String, message: String) = synchronized {
    instances.foreach {
      case chat => chat.context {
        chat.messages.contents += new ChatEntry(nickname, message)
      }
    }
    history = (nickname, message) :: history
  }
  def chatHistory = history.reverse
}

class ChatEntry(name: String, message: String) extends DynamicContent {
  def content = ChatExample.Entry

  val chatName = load[tag.Div]("chatName", reId = true)
  val chatBody = load[tag.Div]("chatBody", reId = true)

  chatName.contents.replaceWith(name)
  chatBody.contents.replaceWith(message)
}