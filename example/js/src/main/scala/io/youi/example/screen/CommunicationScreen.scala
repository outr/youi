package io.youi.example.screen

import io.youi.Template
import io.youi.app.screen.{ContentScreen, PathActivation, Screen}
import io.youi.dom._
import io.youi.example.ClientExampleApplication.{comm, connect, connection, disconnect}
import org.scalajs.dom.{Event, document, html, window}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object CommunicationScreen extends ExampleScreen {
  def connectedInput: html.Input = content.byId[html.Input]("communicationConnected")
  def connectedButton: html.Button = content.byId[html.Button]("communicationConnectButton")
  def reverseInput: html.Input = content.byId[html.Input]("communicationReverse")
  def reverseButton: html.Button = content.byId[html.Button]("communicationReverseButton")
  def timeInput: html.Input = content.byId[html.Input]("communicationTime")
  def timeButton: html.Button = content.byId[html.Button]("communicationTimeButton")
  def nameInput: html.Input = content.byId[html.Input]("communicationName")
  def nameButton: html.Button = content.byId[html.Button]("communicationNameButton")
  def counterInput: html.Input = content.byId[html.Input]("communicationCounter")
  def counterButton: html.Button = content.byId[html.Button]("communicationCounterButton")
  def broadcastInput: html.Input = content.byId[html.Input]("communicationBroadcast")
  def broadcastButton: html.Button = content.byId[html.Button]("communicationBroadcastButton")

  override protected def contentSelector: String = "#exampleScreen"
  override def path: String = "/communication.html"

  override protected def load(): Future[Unit] = super.load().map { _ =>
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
    comm().name.attach { name =>
      val s = name.getOrElse("")
      if (nameInput.value != s) {
        nameInput.value = s
      }
    }

    counterButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      comm().counter.foreach { count =>
        counterInput.value = count.toString
      }
    })

    broadcastButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      comm().broadcast(broadcastInput.value)
    })
  }
}
