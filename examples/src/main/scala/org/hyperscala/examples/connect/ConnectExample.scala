package org.hyperscala.examples.connect

import org.hyperscala.examples.Example
import org.hyperscala.connect.Connect
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ConnectExample extends Example {
  page.require(Connect)

  page.head.contents += new tag.Script {
    contents += JavaScriptString(
      """
        |HyperscalaConnect.on('pong', function(data) {
        | console.log('Received pong from server: ' + data);
        |});
        |HyperscalaConnect.send('ping', 'Hello World!');
      """.stripMargin)
  }

  Connect.on("ping") {
    case data => {
      println("Received ping, sending pong...")
      Connect.send("pong", data)
    }
  }

  contents += new tag.Button(content = "Send Ping") {
    clickEvent := JavaScriptString("HyperscalaConnect.send('ping', 'button test');")
  }
}