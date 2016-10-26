package io.youi.http

import io.youi.net.URL

case class HttpRequest(method: Method = Method.Get,
                       url: URL = URL(),
                       headers: Map[String, String] = Map.empty,
                       content: Content = Content.empty)