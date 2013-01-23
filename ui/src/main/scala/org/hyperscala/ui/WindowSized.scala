package org.hyperscala.ui

import org.hyperscala.web.site.{Website, Webpage}
import org.hyperscala.html._
import org.powerscala.Version
import org.hyperscala.module._
import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.hyperscala.jquery.jQuery

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object WindowSized extends Module {
  def name = "windowsized"

  def version = Version(1)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  def init() = {
    Website().register("/window_size.js", "window_size.js")
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/window_size.js")
  }

  def resized(script: JavaScriptContent) = {
    Webpage().require(this)
    Webpage().head.contents += new tag.Script {
      contents += new JavaScriptString(
        """
          |$(window).bind('windowSized', function(event, windowWidth, windowHeight) {
          |   %s
          |});
        """.stripMargin.format(script.content))
    }
  }

  def widthAlgorithm(id: String, algorithm: String) = resized(new JavaScriptString(
    """
      |$('#%s').width(%s);
    """.stripMargin.format(id, algorithm)))

  def heightAlgorithm(id: String, algorithm: String) = resized(new JavaScriptString(
    """
      |$('#%s').height(%s);
    """.stripMargin.format(id, algorithm)))
}
