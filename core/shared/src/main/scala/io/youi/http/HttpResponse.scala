package io.youi.http

case class HttpResponse(status: Status = Status.OK,
                        headers: Map[String, String] = Map.empty,
                        content: Content = Content.empty)