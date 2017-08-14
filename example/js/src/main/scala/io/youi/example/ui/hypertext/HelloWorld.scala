package io.youi.example.ui.hypertext

import io.youi.UI
import io.youi.datatransfer.DataTransferManager
import io.youi.example.ui.UIExampleScreen
import io.youi.hypertext.Button

import scala.concurrent.Future
import org.scalajs.dom._

object HelloWorld extends HTMLScreen {
  override def name: String = "HTML Hello World"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val dtm = new DataTransferManager
    dtm.addDragTarget(document.body)
    dtm.fileReceived.attach { file =>
      scribe.info(s"Received: $file")
    }
    val input = dtm.addInput()
    container.children += new Button {
      text := "Say 'Hello World'"
      position.center := UI.position.center
      position.middle := UI.position.middle

      event.click.attach { _ =>
        scribe.info(s"Hello, World! ${position.x()}x${position.y()}, Size: ${size.actual.width()}x${size.actual.height()}")
        input.open()
      }
    }
  }

  override def path: String = "/examples/html/hello.html"
}