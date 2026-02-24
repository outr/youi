package io.youi.example.server

import rapid.{RapidApp, Task}
import spice.http
import spice.http.content.Content
import spice.http.server.StaticHttpServer
import spice.http.server.config.HttpServerListener
import spice.http.server.handler.HttpHandler
import spice.http.server.dsl.*
import spice.http.server.dsl.given

import java.io.File
import scala.language.implicitConversions

object ExampleServer extends RapidApp with StaticHttpServer {
  config.listeners @= List(
    HttpServerListener(host = "0.0.0.0", port = Some(8888))
  )

  private lazy val classLoader = ClassLoaderPath(classPathRoot = "site", pathTransform = (s: String) => {
    // Serve index.html for any HTML request or root — this is a SPA so the
    // client-side router handles all page paths.  Static assets (images, JS,
    // etc.) pass through to the classpath as-is.
    if (s == "/" || s.endsWith(".html")) "/index.html" else s
  })

  override protected val handler: HttpHandler = filters(
    "main.js" / Content.file(new File("../target/scala-3.8.1/youi-example-fastopt/main.js")),
    "main.js.map" / Content.file(new File("../target/scala-3.8.1/youi-example-fastopt/main.js.map")),
    classLoader
  )

  override def run(args: List[String]): Task[Unit] = start().flatMap { _ =>
    whileRunning()
  }
}