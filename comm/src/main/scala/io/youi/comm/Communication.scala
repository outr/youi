package io.youi.comm

import cats.effect.{ContextShift, IO}
import io.youi.client._
import io.youi.http.{ConnectionStatus, WebSocket}
import io.youi.net.Path
import profig.Json
import reactify.{Val, Var}
import scribe.Execution.global

import scala.concurrent.ExecutionContext

trait Communication {

}

trait HttpClientCommunicator extends Communicator {
  private implicit def cs: ContextShift[IO] = IO.contextShift(implicitly[ExecutionContext])

  protected def client: HttpClient

  override def method(endpoint: String, send: Json): IO[Json] = IO.fromFuture(
    IO {
      client
        .path(Path.parse(endpoint), append = true)
        .json(send)
        .send()
        .map(_.content.map(c => Json.parse(c.asString)).getOrElse(Json.obj()))
    }
  )
}

trait WebSocketCommunicator extends Communicator {
  protected val webSocket: Var[Option[WebSocket]] = Var(None)
  protected val status: Val[ConnectionStatus] = Val(webSocket().map(_.status()).getOrElse(ConnectionStatus.Closed))

}