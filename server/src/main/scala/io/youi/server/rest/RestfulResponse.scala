package io.youi.server.rest

import io.youi.http.HttpStatus

case class RestfulResponse[Response](response: Response, status: HttpStatus)
