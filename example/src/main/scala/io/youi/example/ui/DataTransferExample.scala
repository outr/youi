package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.{Container, TextView}
import io.youi.component.support.{BorderSupport, PaddingSupport}
import io.youi.component.types.{Border, BorderStyle, Cursor}
import io.youi.datatransfer.DataTransferManager
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.layout.{LayoutSupport, VerticalLayout}
import spice.net._

class DataTransferExample extends UIExampleScreen {
  override def title: String = "Data Transfer"
  override def path: URLPath = path"/examples/data-transfer.html"

  override def createUI(): Task[Unit] = Task {
    val status = new TextView {
      content @= "Drop files below or click the button to browse"
      font.size @= 16.0
      color @= Color.DarkSlateGray
      position.center := container.size.center
      position.top @= 10.0
    }

    val dropZone = new Container with BorderSupport with PaddingSupport {
      background @= Color.AliceBlue
      size.width @= 500.0
      size.height @= 200.0
      border @= Border(2.0, BorderStyle.Dashed, Color.SteelBlue)
      border.radius @= 8.0
      position.center := container.size.center
      position.top @= 50.0
    }

    val dropLabel = new TextView {
      content @= "Drop files here"
      font.size @= 24.0
      color @= Color.SteelBlue
      position.center := dropZone.size.center
      position.middle := dropZone.size.middle
    }
    dropZone.children += dropLabel

    DataTransferManager.addDragTarget(dropZone.element)

    val browseButton = new TextView with EventSupport with BorderSupport with PaddingSupport {
      content @= "Browse Files"
      font.size @= 16.0
      color @= Color.White
      backgroundColor @= Color.SteelBlue
      border.radius @= 6.0
      padding.top @= 8.0
      padding.bottom @= 8.0
      padding.left @= 16.0
      padding.right @= 16.0
      cursor @= Cursor.Pointer
      position.center := container.size.center
      position.top @= 270.0
    }

    val fileInput = DataTransferManager.addInput(multiple = true)
    browseButton.event.click.on {
      fileInput.click()
    }

    val fileList = new Container with LayoutSupport {
      layout @= Some(VerticalLayout(4.0))
      size.width @= 500.0
      position.center := container.size.center
      position.top @= 320.0
    }

    DataTransferManager.fileReceived.attach { dtf =>
      val sizeKB = (dtf.file.size / 1024.0).round
      val entry = new TextView {
        content @= s"${dtf.path} — ${sizeKB} KB"
        font.size @= 14.0
        color @= Color.DarkSlateGray
      }
      fileList.children += entry
      status.content @= s"${fileList.children.length} file(s) received"
    }

    container.children ++= Seq(status, dropZone, browseButton, fileList)
  }
}
