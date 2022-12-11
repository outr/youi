//package io.youi.example.ui.hypertext
//
//import io.youi._
//import io.youi.datatransfer.DataTransferManager
//import io.youi.hypertext.Button
//import org.scalajs.dom._
//
//import scala.concurrent.Future
//
//object DataTransferExample extends HTMLScreen {
//  override def name: String = "Data Transfer Manager"
//
//  override protected def load(): Future[Unit] = super.load().map { _ =>
//    val dtm = new DataTransferManager
//    dtm.addDragTarget(document.body)
//    dtm.fileReceived.attach { file =>
//      scribe.info(s"Received: $file")
//    }
//    dtm.folderFeatureMissing.attach { file =>
//      scribe.info(s"Failed to upload folder: ${file.name}")
//    }
//    dtm.overlay.classOnVisible(document.body, "highlight")
//    val input = dtm.addInput()
//    container.children += new Button {
//      text := "Upload a file or directory..."
//      position.center := ui.center
//      position.middle := ui.middle
//
//      event.click.attach { _ =>
//        input.open()
//      }
//    }
//  }
//
//  override def path: String = "/examples/html/datatransfer.html"
//}