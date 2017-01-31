package io.youi.example

import com.outr.scribe.Logging
import io.youi.Template
import io.youi.app.ClientApplication
import io.youi.dom._
import org.scalajs.dom._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.JSApp

object ClientExampleApplication extends JSApp with ExampleApplication with ClientApplication with Logging {
  def connectedInput: html.Input = byId[html.Input]("communicationConnected")
  def connectedButton: html.Button = byId[html.Button]("communicationConnectButton")
  def reverseInput: html.Input = byId[html.Input]("communicationReverse")
  def reverseButton: html.Button = byId[html.Button]("communicationReverseButton")
  def timeInput: html.Input = byId[html.Input]("communicationTime")
  def timeButton: html.Button = byId[html.Button]("communicationTimeButton")
  def nameInput: html.Input = byId[html.Input]("communicationName")
  def nameButton: html.Button = byId[html.Button]("communicationNameButton")
  def counterInput: html.Input = byId[html.Input]("communicationCounter")
  def counterButton: html.Button = byId[html.Button]("communicationCounterButton")

  def content: html.Div = Template.existingById[html.Div]("template.html", "content")
  def entries: List[html.Div] = Template.existingByClass[html.Div]("template.html", "entry")

  override def main(): Unit = {
    val content1 = content
    content1.setAttribute("id", "content1")
    document.body.appendChild(content1)

    val content2 = content
    content2.setAttribute("id", "content2")
    document.body.appendChild(content2)

    entries.foreach { entry =>
      document.body.appendChild(entry)
    }

    connection.connected.attachAndFire { c =>
      connectedInput.value = if (c) "Yes" else "No"
    }
    connectedButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      if (connection.connected()) {
        disconnect()
      } else {
        connect()
      }
    })

    reverseButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      val value = reverseInput.value.trim
      if (value.isEmpty) {
        window.alert("Reverse value must not be empty!")
      } else {
        comm().reverse(value).foreach { reversed =>
          reverseInput.value = reversed
        }
      }
    })

    timeButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      comm().time.foreach { time =>
        timeInput.value = time.toString
      }
    })

    nameInput.value = comm().name().getOrElse("")
    nameButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      comm().name := Some(nameInput.value)
    })

    counterButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      comm().counter.foreach { count =>
        counterInput.value = count.toString
      }
    })
  }
}