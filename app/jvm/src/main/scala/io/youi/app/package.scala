package io.youi

import io.youi.http.HttpConnection
import io.youi.server.dsl.{ConnectionFilter, DeltaKey, FilterResponse}
import io.youi.stream.{ByTag, Delta}

import scala.concurrent.Future

package object app {
  object Application extends ConnectionFilter {
    override def filter(connection: HttpConnection): Future[FilterResponse] = Future.successful {
      val server = connection.server.asInstanceOf[ServerApplication]

      val jsDeps = if (server.applicationJSDepsContent.nonEmpty) {
        s"""<script src="${server.applicationJSDepsPath}"></script>"""
      } else {
        ""
      }
      val applicationDeltas = List(
        Delta.InsertLastChild(ByTag("body"),
          s"""
             |$jsDeps
             |<script src="${server.applicationJSPath}"></script>
             |<script>
             |  application();
             |</script>
           """.stripMargin
        )
      )
      val deltas = connection.store.getOrElse[List[Delta]](DeltaKey, Nil) ::: applicationDeltas
      connection.store(DeltaKey) = deltas
      FilterResponse.Continue(connection)
    }
  }
}
