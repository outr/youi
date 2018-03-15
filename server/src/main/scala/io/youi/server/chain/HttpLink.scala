package io.youi.server.chain

import io.youi.http.HttpConnection
import io.youi.server.handler.HttpHandler

import scala.annotation.tailrec

trait HttpLink extends HttpHandler {
  override final def handle(connection: HttpConnection): Unit = link(connection)

  def link(connection: HttpConnection): Result

  def chain(children: HttpLink*): HttpChain = new HttpChain(this, children.toList)
}

sealed trait Result

case object Continue extends Result
case object Stop extends Result

class HttpChain(parent: HttpLink, children: List[HttpLink]) extends HttpLink {
  override def link(connection: HttpConnection): Result = parent.link(connection) match {
    case Continue => linkChildren(connection, children)
    case Stop => Stop
  }

  @tailrec
  private def linkChildren(connection: HttpConnection, children: List[HttpLink]): Result = if (children.isEmpty) {
    // Finished
    Continue
  } else {
    val child = children.head
    if (child.link(connection) == Stop) {
      // Stop
      Stop
    } else {
      linkChildren(connection, children.tail)
    }
  }
}

