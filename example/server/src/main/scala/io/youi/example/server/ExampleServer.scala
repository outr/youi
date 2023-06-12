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
  private lazy val classLoader = ClassLoaderPath(
    classPathRoot = "site",
    pathTransform = (s: String) => {
      scribe.info(s"Path Transform: $s")
      "/index.html"
    }
  )

  override protected val handler: HttpHandler = filters(
    // TODO: Why isn't this working?
    filters(path"/", path"index.html", http.paths.startsWith("examples/")) / classLoader,
    // TODO: this works...
    URLPath.empty / classLoader,
    "index.html" / classLoader,
    "main.js" / Content.file(new File("../target/scala-2.13/youi-example-fastopt/main.js")),
    "main.js.map" / Content.file(new File("../target/scala-2.13/youi-example-fastopt/main.js.map"))
  )

  override def run(args: List[String]): IO[ExitCode] = start().flatMap { _ =>
    whileRunning().map(_ => ExitCode.Success)
  }
}