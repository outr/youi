package io.youi.template

import java.io.File

import io.youi.app.SinglePageApplication
import io.youi.http._
import io.youi.server.UndertowServer
import io.youi.server.handler.CachingManager

class ServerTemplateApplication(compiler: TemplateCompiler) extends UndertowServer with TemplateApplication with SinglePageApplication {
  config.clearListeners().addHttpListener("0.0.0.0")
  handler.matcher(path.startsWith("/app")).caching(CachingManager.LastModified()).classLoader()

  override protected def templateDirectory: File = compiler.destinationDirectory
  override protected def appJSPath: String = "/app/youi-template-fastopt.js"
  override protected def appJSMethod: String = "application"
  override protected def responseMap(httpConnection: HttpConnection): Map[String, String] = Map(
    "template_pages" -> compiler.pages.mkString(";")
  )
}