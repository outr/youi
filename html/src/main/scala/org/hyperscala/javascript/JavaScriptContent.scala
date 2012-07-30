package org.hyperscala.javascript

import org.hyperscala.XMLContent
import org.jdom2.{Text, Content}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait JavaScriptContent extends XMLContent {
  def content: String
  protected def content_=(content: String): Unit

  def toXML = new Text(content)

  def fromXML(content: Content) = this.content = content.asInstanceOf[Text].getText
}

case class JavaScriptString(var content: String) extends JavaScriptContent

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