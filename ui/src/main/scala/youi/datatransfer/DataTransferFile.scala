package youi.datatransfer

import org.scalajs.dom.File

case class DataTransferFile(file: File, path: String, fileName: String = "") {
  lazy val effectiveFileName: String = if (fileName.nonEmpty) fileName else file.name
}
