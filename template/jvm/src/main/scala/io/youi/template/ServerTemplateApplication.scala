package io.youi.template

import java.io.File

import io.youi.app.SinglePageApplication
import io.youi.http._
import io.youi.server.Server
import io.youi.server.handler.CachingManager

class ServerTemplateApplication(compiler: TemplateCompiler) extends Server with TemplateApplication with SinglePageApplication {
  handler.matcher(path.startsWith("/app")).caching(CachingManager.LastModified()).classLoader()

  override protected def templateDirectory: File = compiler.destinationDirectory
  override protected def appJSContent: Content = Content.classPath("app/youi-template-fastopt.js")
  override protected def appJSMethod: String = "application"
  override protected def responseMap(httpConnection: HttpConnection): Map[String, String] = Map(
    "template_pages" -> compiler.pages.mkString(";")
  )
}