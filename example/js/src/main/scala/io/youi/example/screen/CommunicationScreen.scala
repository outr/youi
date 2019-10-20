package io.youi.example.screen

import java.nio.ByteBuffer

import io.youi.Template
import io.youi.app.screen.PreloadedContentScreen
import io.youi.dom._
import io.youi.example.ClientExampleApplication
import io.youi.net._
import org.scalajs.dom.{Event, FileReader, html, window}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.typedarray.{ArrayBuffer, TypedArrayBuffer}

object CommunicationScreen extends ExampleScreen with PreloadedContentScreen {
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
  def uploadInput: html.Input = content.byId[html.Input]("communicationUpload")
  def uploadButton: html.Button = content.byId[html.Button]("communicationUploadButton")

  override def path: Path = path"/communication.html"

  override protected def preloadScreen: html.Element = Template.byTag[html.Element](
    "example/jvm/src/main/resources/content/templates/communication.html",
    "screen",
    "youi"
  ).head

  override protected def load(): Future[Unit] = super.load().map { _ =>
    configure()
  }

  private def configure(): Unit = {
    connection.status.attachAndFire { status =>
      connectedInput.value = status.name
    }
    connectedButton.addEventListener("click", (_: Event) => {
      ClientExampleApplication.connect()
    })

    reverseButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      val value = reverseInput.value.trim
      if (value.isEmpty) {
        window.alert("Reverse value must not be empty!")
      } else {
        connection.server.reverse(value).foreach { reversed =>
          reverseInput.value = reversed
        }
      }
    })

    timeButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      connection.server.time.foreach { time =>
        timeInput.value = time.toString
      }
    })

    /*nameInput.value = hookup.name().getOrElse("")
    nameButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      hookup.name @= Some(nameInput.value)
    })
    hookup.name.attach { name =>
      val s = name.getOrElse("")
      if (nameInput.value != s) {
        nameInput.value = s
      }
    }*/

    counterButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      connection.server.counter.foreach { count =>
        counterInput.value = count.toString
      }
    })

    broadcastButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      connection.server.broadcast(broadcastInput.value)
    })

    uploadButton.addEventListener("click", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
      val file = uploadInput.files.item(0)
      val webSocket = connection.webSocket().get

      val fileReader = new FileReader
      fileReader.onload = (_: Event) => {
        val arrayBuffer = fileReader.result.asInstanceOf[ArrayBuffer]
        scribe.info(s"Sending: ${file.name} / ${arrayBuffer.byteLength} bytes")
        val nameBytes = file.name.getBytes("UTF-8")
        val nameLength = ByteBuffer.allocate(java.lang.Integer.BYTES)
        nameLength.putInt(nameBytes.length)
        nameLength.flip()
        val byteLength = ByteBuffer.allocate(java.lang.Long.BYTES)
        byteLength.putLong(file.size.toLong)
        byteLength.flip()
        webSocket.send.binary @= nameLength
        webSocket.send.binary @= ByteBuffer.wrap(nameBytes)
        webSocket.send.binary @= byteLength
        webSocket.send.binary @= TypedArrayBuffer.wrap(arrayBuffer)
      }
      fileReader.readAsArrayBuffer(file)
    })
  }
}