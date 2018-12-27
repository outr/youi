package io.youi.client

import io.youi.http.cookie.ResponseCookie

case class Session(cookies: List[ResponseCookie] = Nil)