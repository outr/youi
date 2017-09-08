package io.youi.datatransfer

import org.scalajs.dom.File

case class DataTransferFile(file: File, fileName: String, path: List[String])
