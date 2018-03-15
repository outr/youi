package spec

import io.youi.http.Method
import io.youi.net.ContentType
import io.youi.server.Server
import io.youi.server.chain._
import org.scalatest.{Matchers, WordSpec}

class ChainSpec extends WordSpec with Matchers {
  "Http chaining" should {
    "properly DSL" in {
      ChainServer.handlers.size should be(1)
    }
  }
}

object ChainServer extends Server {
  ipAllow("127.0.0.1", "192.168.1.1").chain(
    methodAllow(Method.Get).chain(
      pathAllow("/hello/world.txt").chain(
        respond("Hello, World!", ContentType.`text/plain`)
      ),
      pathAllow("/hello/world.html").chain(
        respond(<html>
          <head>
            <title>Hello, World!</title>
          </head>
          <body>
            <h1>Hello, World!</h1>
          </body>
        </html>, ContentType.`text/html`)
      )
    )
  ).add()
}