package io.youi.example

import com.outr.scribe.Logging
import io.youi.dom._
import org.scalajs.dom._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.JSApp

object ClientExample extends JSApp with Logging {
  def connectedInput: html.Input = byId[html.Input]("communicationConnected")
  def connectedButton: html.Button = byId[html.Button]("communicationConnectButton")
  def reverseInput: html.Input = byId[html.Input]("communicationReverse")
  def reverseButton: html.Button = byId[html.Button]("communicationReverseButton")
  def timeInput: html.Input = byId[html.Input]("communicationTime")
  def timeButton: html.Button = byId[html.Button]("communicationTimeButton")

  override def main(): Unit = {
    ClientExampleCommunication.connected.attachAndFire { c =>
      connectedInput.value = if (c) "Yes" else "No"
    }
    connectedButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      if (ClientExampleCommunication.connected()) {
        ClientExampleCommunication.disconnect()
      } else {
        ClientExampleCommunication.connect()
      }
    })

    reverseButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      val value = reverseInput.value.trim
      if (value.isEmpty) {
        window.alert("Reverse value must not be empty!")
      } else {
        ClientExampleCommunication.communication.reverse(value).foreach { reversed =>
          reverseInput.value = reversed
        }
      }
    })

    timeButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      ClientExampleCommunication.communication.time.foreach { time =>
        timeInput.value = time.toString
      }
    })
  }
}