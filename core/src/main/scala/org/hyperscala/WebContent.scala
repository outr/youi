package org.hyperscala

import js.Var
import org.powerscala.hierarchy.Element
import xml.{PrettyPrinter, Node}
import java.io.{FileWriter, File, PrintWriter}
import java.util

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait WebContent extends Element with Var {
  implicit val thisWebContent: WebContent = this

  val uuid = util.UUID.randomUUID()

  private var _reference: Option[String] = None
  override def reference = _reference
  def reference_=(_reference: Option[String]) = this._reference = _reference

  def xml: Node

  protected def children: Seq[Node] = Nil

  def render = {
    beforeRender()
    val printer = new PrettyPrinter(1024, 2)
    WebContent.render = printer.format(xml)
      .replaceAll("></img>", "/>")
      .replaceAll("></link>", "/>")
      .replaceAll("></meta>", "/>")
      .replaceAll("></br>", "/>")
      .replaceAll("></input>", "/>")
      .replaceAll("&amp;", "&")
    afterRender()
    val result = WebContent.render
    WebContent.render = null
    result
  }

  def renderToFile(file: File) = {
    val writer = new FileWriter(file)
    try {
      writer.write(render)
    } finally {
      writer.flush()
      writer.close()
    }
  }

  def write(writer: PrintWriter) = {
    writer.println("<!DOCTYPE html>")
    writer.println(render)
    writer.flush()
  }

  /**
   * Gets invoked before anything is rendered.
   */
  def beforeRender() = {
  }

  /**
   * Gets invoked after everything is rendered.
   */
  def afterRender() = {
  }

  def webPage = hierarchy.backward[WebPage]()
}

object WebContent {
  private val threadLocal = new ThreadLocal[String]

  def render = threadLocal.get
  def render_=(_render: String) = threadLocal.set(_render)
}