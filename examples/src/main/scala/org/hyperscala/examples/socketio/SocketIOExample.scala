package org.hyperscala.examples.socketio

import org.hyperscala.examples.Example
import org.hyperscala.socketio.{SocketIOConnection, SocketIO}
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SocketIOExample extends Example {
  page.require(SocketIO)

  val connection = new SocketIOConnection()
  connection.connect()

  page.head.contents += new tag.Script {
    contents += JavaScriptString(
      """
        |socket.on('news', function(data) {
        | console.log(data);
        | socket.emit('my other event', { my: 'data' });
        |});
      """.stripMargin)
  }
}
