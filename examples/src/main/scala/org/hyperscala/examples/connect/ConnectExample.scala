package org.hyperscala.examples.connect

import org.hyperscala.examples.Example
import org.hyperscala.connect.Connect
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.html._
import org.hyperscala.web._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ConnectExample extends Example {
  this.require(Connect)

  connected[Webpage[Session]] {
    case webpage => {
      webpage.head.contents += new tag.Script {
        contents += JavaScriptString(
          """
            |HyperscalaConnect.on('pong', function(data) {
            | console.log('Received pong from server: ' + data);
            |});
            |HyperscalaConnect.send('ping', 'Hello World!');
          """.stripMargin)
      }

      contents += new tag.P {
        contents += "Connect provides the foundational support needed for the Realtime communication in Hyperscala, and can be utilized directly for a looser coupling of client to server communication."
      }

      Connect.on(webpage, "ping") {
        case data => {
          println("Received ping, sending pong...")
          Connect.send(webpage, "pong", data)
        }
      }
    }
  }

  contents += new tag.Button(content = "Send Ping") {
    clickEvent := JavaScriptString("HyperscalaConnect.send('ping', 'button test');")
  }
}