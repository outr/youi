package io.youi.communication

import java.nio.ByteBuffer

import scala.concurrent.Promise

trait ByteBufferWriter {
  val promise: Promise[Unit] = Promise[Unit]()

  def fileName: String
  def actualFileName: String
  def written: Long
  def remaining: Long
  def write(bb: ByteBuffer): Unit
  def close(): Unit
}