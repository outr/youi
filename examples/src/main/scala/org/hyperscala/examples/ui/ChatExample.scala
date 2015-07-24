package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime.{Realtime, RealtimeEvent}
import org.hyperscala.ui.dynamic.{DynamicContent, DynamicTag, DynamicTagged}
import org.hyperscala.web._
import org.powerscala.Unique
import org.powerscala.property.Property

import scala.annotation.tailrec
import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class ChatExample extends Webpage with Example {
  require(Realtime)

  body.contents += new tag.P {
    contents += "Simple real-time chat example with basic user management and history."
  }

  val nickname = new Property[String]

  val chatMain = DynamicContent.url(ChatExample.Main, null)

  val chatName = chatMain.load[tag.Input]("chatName")
  val message = chatMain.load[tag.TextArea]("chatMessage")
  message.autoFocus := true
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

  body.contents += chatMain

  ChatExample.chatHistory.foreach {   // Load history
    case (nick, text) => messages.contents += new ChatEntry(nick, text)
  }

  connected[Website] {
    case website => updateNickname()
  }

  def sendMessage() = {
    if (message.value() != null && message.value().nonEmpty) {
      ChatExample.sendMessage(this.website, nickname(), message.value())
    }
    message.value := ""
    eval($(message).focus())
  }

  def updateNickname() = {
    val website = this.website
    val sessionNickname = website.session.getOrElse[String]("chatNickname", null)
    val current = chatName.value() match {
      case "" | null => website.session.getOrElse[String]("chatNickname", "guest")
      case v => v
    }
    if (current != nickname() && current != sessionNickname) {
      nickname := ChatExample.generateNick(website, current)
    } else {
      nickname := current
    }
    chatName.value := nickname()
    website.session("chatNickname") = nickname()
  }
}

object ChatExample {
  val Main = getClass.getClassLoader.getResource("chat.html")
  val Entry = getClass.getClassLoader.getResource("chat_entry.html")

  private var history = List.empty[(String, String)]

  def instances(website: Website) = website.pages.byType[ChatExample].toList

  @tailrec
  def generateNick(website: Website, nickname: String, increment: Int = 0): String = {
    val nick = increment match {
      case 0 => nickname
      case _ => s"$nickname$increment"
    }
    if (!instances(website).exists(c => c.nickname() == nick)) {
      nick
    } else {
      generateNick(website, nickname, increment + 1)
    }
  }
  def sendMessage(website: Website, nickname: String, message: String) = synchronized {
    instances(website).foreach {
      case chat => chat.messages.contents.insert(0, new ChatEntry(nickname, message))
    }
    history = ((nickname, message) :: history).take(20)
  }
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