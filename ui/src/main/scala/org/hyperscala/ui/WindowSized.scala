package org.hyperscala.ui

import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.html._
import org.powerscala.Version
import org.hyperscala.module._
import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.hyperscala.jquery.jQuery
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object WindowSized extends Module {
  def name = "windowsized"

  def version = Version(1)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  override def init[S <: Session](website: Website[S]) = {
    website.register("/window_size.js", "window_size.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/window_size.js")
  }

  def resized[S <: Session](webpage: Webpage[S], script: JavaScriptContent) = {
    webpage.require(this)
    webpage.head.contents += new tag.Script {
      contents += new JavaScriptString(
        """
          |$(window).bind('windowSized', function(event, windowWidth, windowHeight) {
          |   %s
          |});
        """.stripMargin.format(script.content))
    }
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
