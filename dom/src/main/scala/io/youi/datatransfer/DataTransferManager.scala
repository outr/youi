package io.youi.datatransfer

import io.youi.dom
import io.youi.dom._
import org.scalajs.dom._
import reactify._

import scala.language.implicitConversions
import scala.scalajs.js

class DataTransferManager {
  implicit def file2Extras(file: File): FileExtras = file.asInstanceOf[FileExtras]

  object overlay {
    val visible: Var[Boolean] = Var(false)
    val current: Var[Option[DragTarget]] = Var(None)

    def classOnVisible(target: html.Element, className: String): Unit = visible.attachAndFire {
      case true => target.classList.add(className)
      case false => target.classList.remove(className)
    }
  }
  val fileReceived: Channel[DataTransferFile] = Channel[DataTransferFile]

  def addDragTarget(element: html.Element): DragTarget = new DragTarget(element, this)

  def addInput(input: html.Input = dom.create[html.Input]("input"),
               modify: Boolean = true,
               folderSupport: Boolean = true): FileInput = new FileInput(input, modify, folderSupport, this)

  def process(files: FileList): Unit = files.toList.foreach { file =>
    // TODO: detect directory upload - check file length and mimetype?
    val fullName = try {
      file.webkitRelativePath
    } catch {
      case _: Throwable => file.name
    }
    scribe.info(s"File: ${file.name} / ${file.size} / $fullName")
    val (path, fileName) = fullName match {
      case n if n.indexOf('/') != -1 => {
        val index = n.lastIndexOf('/')
        val pathString = n.substring(0, index)
        pathString.split('/').toList -> n.substring(index + 1)
      }
      case n => Nil -> n
    }
    fileReceived := DataTransferFile(file, fileName, path)
  }
}