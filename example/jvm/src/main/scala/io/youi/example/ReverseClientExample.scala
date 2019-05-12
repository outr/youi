package io.youi.example

import com.outr.hookup.{Hookup, HookupSupport}
import io.circe.parser._
import io.youi.net.URL
import io.youi.server.WebSocketClient
import io.youi.util.Time

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object ReverseClientExample {
  val connection: WebSocketClient = new WebSocketClient(URL("http://localhost:8080/communication"))
  val hookup: SimpleHookup = Hookup.client[SimpleHookup]

  // TODO: Do this with Communication paradigm
  // Connect IO
  connection.receive.text.attach { s =>
    parse(s) match {
      case Left(pf) => throw pf
      case Right(json) => hookup.io.input.set(json)
    }
  }
  hookup.io.output.attach { json =>
    connection.send.text := json.spaces2
  }

  def main(args: Array[String]): Unit = {
    connection.connect()
    try {
      val future = hookup.simple.reverse("This is a test!")
      val result = Await.result(future, 5.seconds)
      scribe.info(s"Receive: $result")
    } finally {
      connection.dispose()
    }
//    reRun()

//    Thread.sleep(120000)
  }

  private def reRun(): Future[Unit] = hookup.simple.reverse("This is a test!").flatMap { result =>
    scribe.info(s"Receive: $result")
    Time.delay(2.seconds).flatMap(_ => reRun())
  }

  trait SimpleHookup extends Hookup {
    val simple: SimpleCommunication with HookupSupport = auto[SimpleCommunication]
  }
}