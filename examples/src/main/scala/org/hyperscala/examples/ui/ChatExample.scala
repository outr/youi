package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.web.site.{SessionContextualizable, Website, Webpage}
import org.powerscala.property.Property
import annotation.tailrec
import org.hyperscala.jquery.jQuery
import org.hyperscala.realtime.Realtime
import org.hyperscala.ui.dynamic.{DynamicContent, DynamicString}
import language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class ChatExample extends Webpage {
  Webpage().require(Realtime)

  val nickname = new Property[String]

  body.style.fontFamily = "Helvetica, sans-serif"

  val chatMain = DynamicContent.url(ChatExample.Main, null)

  val chatName = chatMain.load[tag.Input]("chatName")
  val message = chatMain.load[tag.TextArea]("chatMessage")
  val submit = chatMain.load[tag.Button]("submit")
  val messages = chatMain.load[tag.Div]("messages")

  chatName.changeEvent := JavaScriptEvent()
  message.changeEvent := JavaScriptEvent()
  submit.clickEvent := JavaScriptEvent()

  chatName.changeEvent.on {
    case evt => updateNickname()
  }
  submit.clickEvent.on {
    case evt => sendMessage()
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
  val Main = getClass.getClassLoader.getResource("chat.html")
  val Entry = getClass.getClassLoader.getResource("chat_entry.html")

  val pages = new SessionContextualizable[ChatExample]

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
    pages.contextualize {
      case chat => chat.messages.contents += new ChatEntry(nickname, message)
    }
    history = (nickname, message) :: history
  }
  def chatHistory = history.reverse
}

class ChatEntry(name: String, message: String) extends DynamicContent(null) {
  def dynamicString = DynamicString.url("chat_entry.html", ChatExample.Entry)

  val chatName = load[tag.Div]("chatName", reId = true)
  val chatBody = load[tag.Div]("chatBody", reId = true)

  chatName.contents.replaceWith(name)
  chatBody.contents.replaceWith(message)
}