package io.youi.example.server

import cats.effect.{ExitCode, IO, IOApp}
import spice.http
import spice.http.content.Content
import spice.http.server.StaticHttpServer
import spice.http.server.handler.HttpHandler
import spice.http.server.dsl._
import spice.net.{ContentType, URLPath, interpolation}

import java.io.File

object ExampleServer extends IOApp with StaticHttpServer {
  private lazy val classLoader = ClassLoaderPath(classPathRoot = "site", pathTransform = (s: String) => {
    if (s.startsWith("/examples/") || s == "/") {
      "/index.html"
    } else {
      scribe.info(s"Path: $s")
      s
    }
  })

  override protected val handler: HttpHandler = filters(
    "main.js" / Content.file(new File("../target/scala-2.13/youi-example-fastopt/main.js")),
    "main.js.map" / Content.file(new File("../target/scala-2.13/youi-example-fastopt/main.js.map")),
    classLoader
  )

  override def run(args: List[String]): IO[ExitCode] = start().flatMap { _ =>
    whileRunning().map(_ => ExitCode.Success)
  }
}