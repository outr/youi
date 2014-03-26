package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.Website
import org.powerscala.property.Property
import org.hyperscala.realtime.{RealtimeEvent, Realtime}
import org.hyperscala.ui.dynamic.{DynamicTagged, DynamicTag, DynamicContent}
import language.reflectiveCalls
import org.hyperscala.jquery.dsl._
import org.powerscala.Unique
import org.hyperscala.examples.Example
import org.hyperscala.web._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class ChatExample extends Example {
  this.require(Realtime)

  contents += new tag.P {
    contents += "Simple real-time chat example with basic user management and history."
  }

  val nickname = new Property[String]

  val chatMain = DynamicContent.url(ChatExample.Main, null)

  val chatName = chatMain.load[tag.Input]("chatName")
  val message = chatMain.load[tag.TextArea]("chatMessage")
  val submit = chatMain.load[tag.Button]("submit")
  val messages = chatMain.load[tag.Div]("messages")

  chatName.changeEvent := RealtimeEvent()
  message.changeEvent := RealtimeEvent()
  submit.clickEvent := RealtimeEvent()

  chatName.changeEvent.on {
    case evt => updateNickname()
  }
  submit.clickEvent.on {
    case evt => sendMessage()
  }

  contents += chatMain

  ChatExample.chatHistory.foreach {   // Load history
    case (nick, text) => messages.contents += new ChatEntry(nick, text)
  }
  updateNickname()

  throw new RuntimeException("Broken!")

  def sendMessage() = {
//    ChatExample.sendMessage(nickname(), message.value())
    message.value := ""
    Realtime.send(this.webpage, $(message).focus())
  }

  def updateNickname() = {
    val current = chatName.value() match {
      case "" => "guest"
      case v => v
    }
    if (current != nickname()) {
//      nickname := ChatExample.generateNick(current)
      chatName.value := nickname()
    }
  }
}

object ChatExample {
  val Main = getClass.getClassLoader.getResource("chat.html")
  val Entry = getClass.getClassLoader.getResource("chat_entry.html")

  private var history = List.empty[(String, String)]

//  def instances = Website().sessions.valuesByType[ChatExample].toList
//  @tailrec
//  def generateNick(nickname: String, increment: Int = 0): String = {
//    val nick = increment match {
//      case 0 => nickname
//      case _ => "%s%s".format(nickname, increment)
//    }
//    if (instances.find(c => c.nickname() == nick).isEmpty) {
//      nick
//    } else {
//      generateNick(nickname, increment + 1)
//    }
//  }
//  def sendMessage(nickname: String, message: String) = synchronized {
//    Website().pages[ExamplePage].foreach {
//      case page => page.example match {
//        case chat: ChatExample => Webpage.contextualize(page) {
//          chat.messages.contents.insert(0, new ChatEntry(nickname, message))
//        }
//        case _ => // Ignore non chat examples
//      }
//    }
//    history = ((nickname, message) :: history).take(20)
//  }
  def chatHistory = history
}

class ChatEntry(name: String, message: String) extends tag.Div with DynamicTagged[tag.Div] {
  def dynamicTag = DynamicTag.url("chat_entry.html", ChatExample.Entry)

  val chatName = getById[tag.Div]("chatName")
  val chatBody = getById[tag.Div]("chatBody")

  chatName.id := Unique()
  chatBody.id := Unique()

  chatName.contents.replaceWith(name)
  chatBody.contents.replaceWith(message)
}