package io.youi.communication

import fabric.rw._

sealed trait MessageType {
  def name: String
  def running: Boolean
}

object MessageType {
  implicit val rw: ReaderWriter[MessageType] = ReaderWriter(_.name, v => byName(v.asStr.value))

  case object Invoke extends MessageType {
    override def name: String = "invoke"
    override def running: Boolean = true
  }
  case object Response extends MessageType {
    override def name: String = "response"
    override def running: Boolean = false
  }
  case object UploadStart extends MessageType {
    override def name: String = "uploadStart"
    override def running: Boolean = true
  }
  case object UploadComplete extends MessageType {
    override def name: String = "uploadComplete"
    override def running: Boolean = false
  }
  case object Error extends MessageType {
    override def name: String = "error"
    override def running: Boolean = false
  }

  def byName(name: String): MessageType = name match {
    case "invoke" => Invoke
    case "response" => Response
    case "uploadStart" => UploadStart
    case "uploadComplete" => UploadComplete
    case "error" => Error
  }
}