package org.hyperscala.ui

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.hyperscala.javascript.dsl._
import org.hyperscala.jquery.jQuery
import org.hyperscala.module._
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object WindowSized extends Module {
  def name = "windowsized"

  def version = Version(1)

  override def dependencies = List(jQuery)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/window_size.js", "window_size.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/window_size.js")
  }

  def resized[S <: Session](webpage: Webpage[S], script: JavaScriptContent) = {
    webpage.require(this)
    webpage.eval(
      s"""(function() {
        | var f = function(event, windowWidth, windowHeight) {
        |   ${script.content}
        | };
        | var $$window = $$(window);
        | f('windowSized', $$window.width(), $$window.height());
        | $$window.bind('windowSized', f);
        |})();
      """.stripMargin)
  }

  def widthAlgorithm[S <: Session](webpage: Webpage[S], id: String, algorithm: String) = resized(webpage, new JavaScriptString(
    """
      |$('#%s').width(%s);
    """.stripMargin.format(id, algorithm)))

  def heightAlgorithm[S <: Session](webpage: Webpage[S], id: String, algorithm: String) = resized(webpage, new JavaScriptString(
    """
      |$('#%s').height(%s);
    """.stripMargin.format(id, algorithm)))
}
