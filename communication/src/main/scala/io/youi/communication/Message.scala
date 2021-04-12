package io.youi.communication

import java.util.concurrent.atomic.AtomicLong

import fabric.rw._

case class Message(id: Long,
                   `type`: MessageType,
                   name: Option[String] = None,
                   method: Option[String] = None,
                   params: Option[Json] = None,
                   returnValue: Option[Json] = None,
                   bytes: Option[Long] = None,
                   errorMessage: Option[String] = None)

object Message {
  private val idGenerator = new AtomicLong(0L)

  implicit val rw: ReaderWriter[Message] = ccRW

  def invoke(name: String, method: String, params: Json): Message = Message(
    id = idGenerator.incrementAndGet(),
    `type` = MessageType.Invoke,
    name = Some(name),
    method = Some(method),
    params = Some(params)
  )
  def response(id: Long, name: String, method: String, returnValue: Json): Message = Message(
    id = id,
    `type` = MessageType.Response,
    name = Some(name),
    method = Some(method),
    returnValue = Some(returnValue)
  )
  def uploadStart(fileName: String, bytes: Long): Message = Message(
    id = idGenerator.incrementAndGet(),
    `type` = MessageType.UploadStart,
    name = Some(fileName),
    bytes = Some(bytes)
  )
  def uploadComplete(id: Long, fileName: String): Message = Message(
    id = id,
    `type` = MessageType.UploadComplete,
    name = Some(fileName)
  )
  def error(id: Long, message: String): Message = Message(
    id = id,
    `type` = MessageType.Error,
    errorMessage = Some(message)
  )
}