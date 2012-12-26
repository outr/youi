package org.hyperscala.javascript

import org.hyperscala.XMLContent
import org.jdom2.{Text, Content}
import org.hyperscala.io.HTMLWriter

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait JavaScriptContent extends XMLContent {
  def content: String

  def write(writer: HTMLWriter) = writer.write(content)

  def read(content: Content): Unit = throw new UnsupportedOperationException("JavaScriptContent does not support loading. Use JavaScriptString instead.")

  def +(js: JavaScriptContent) = JavaScriptString("%s\n\n%s".format(content, js.content))
}

case class JavaScriptString(var content: String) extends JavaScriptContent {
  override def read(content: Content): Unit = this.content = content.asInstanceOf[Text].getText
}

trait JSFunction0[R] extends JavaScriptContent

trait JSFunction1[P1, R] extends JavaScriptContent

trait JSFunction2[P1, P2, R] extends JavaScriptContent

trait JSFunction3[P1, P2, P3, R] extends JavaScriptContent

trait JSFunction4[P1, P2, P3, P4, R] extends JavaScriptContent

trait JSFunction5[P1, P2, P3, P4, P5, R] extends JavaScriptContent

trait JSFunction6[P1, P2, P3, P4, P5, P6, R] extends JavaScriptContent

trait JSFunction7[P1, P2, P3, P4, P5, P6, P7, R] extends JavaScriptContent

trait JSFunction8[P1, P2, P3, P4, P5, P6, P7, P8, R] extends JavaScriptContent

trait JSFunction9[P1, P2, P3, P4, P5, P6, P7, P8, P9, R] extends JavaScriptContent