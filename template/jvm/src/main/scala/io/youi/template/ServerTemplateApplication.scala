package io.youi.template

import io.youi.app.ServerApplication
import io.youi.stream._

class ServerTemplateApplication(compiler: TemplateCompiler) extends ServerApplication with TemplateApplication {
  addTemplate(compiler.destinationDirectory, deltas = List(
    Delta.InsertLastChild(ByTag("body"), s"""<input type="hidden" id="template_pages" value="${compiler.pages.mkString(";")}"/>""")
  ))

  override def main(args: Array[String]): Unit = start(args)
}