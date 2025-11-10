package io.youi.example.server

import rapid.{RapidApp, Task}
import spice.http
import spice.http.content.Content
import spice.http.server.StaticHttpServer
import spice.http.server.handler.HttpHandler
import spice.http.server.dsl._
import spice.net.{ContentType, URLPath, interpolation}

import java.io.File

object ExampleServer extends RapidApp with StaticHttpServer {
  private lazy val classLoader = ClassLoaderPath(classPathRoot = "site", pathTransform = (s: String) => {
    if (s.startsWith("/examples/") || s == "/" || s == "/ui-examples.html") {
      "/index.html"
    } else {
      s
    }
  })

  override protected val handler: HttpHandler = filters(
    "main.js" / Content.file(new File("../target/scala-2.13/youi-example-fastopt/main.js")),
    "main.js.map" / Content.file(new File("../target/scala-2.13/youi-example-fastopt/main.js.map")),
    classLoader
  )

  override def run(args: List[String]): Task[Unit] = start().flatMap { _ =>
    whileRunning()
  }
}