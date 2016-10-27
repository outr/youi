package io.youi.http

case class HttpResponse(status: Status = Status.OK,
`                        headers: Headers = Headers.empty,
                        content: Content = Content.empty)