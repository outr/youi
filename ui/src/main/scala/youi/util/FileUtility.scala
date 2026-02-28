package youi.util

import rapid.Task
import rapid.task.Completable
import org.scalajs.dom.{Event, File, FileReader, URL}

object FileUtility {
  def loadDataURL(file: File, useFileReader: Boolean = false): Task[String] = if (useFileReader) {
    val reader = new FileReader
    val c: Completable[String] = Task.completable[String]
    reader.addEventListener("load", (_: Event) => {
      c.success(reader.result.asInstanceOf[String])
    })
    reader.readAsDataURL(file)
    c
  } else {
    Task.pure(URL.createObjectURL(file))
  }

  def loadText(file: File): Task[String] = {
    val reader = new FileReader
    val c: Completable[String] = Task.completable[String]
    reader.addEventListener("load", (_: Event) => {
      c.success(reader.result.asInstanceOf[String])
    })
    reader.readAsText(file)
    c
  }
}
