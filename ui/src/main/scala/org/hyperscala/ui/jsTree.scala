package org.hyperscala.ui

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.site.{Webpage, Website}
import org.hyperscala.html._
import org.hyperscala.jquery.jQuery
import com.outr.webcommunicator.netty.handler.PathHandler
import scala.collection.mutable.ListBuffer

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jsTree {

}

object jsTree extends Module {
  var debug = false
  val name = "jstree"
  val version = Version(2, extra = "alpha")

  override val dependencies = List(jQuery.LatestWithDefault)

  def init() = {
    Website().register(PathHandler("/js/jstree/", "jstree/"))
  }

  def load() = Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jstree/jquery.jstree.js")

  def apply(t: HTMLTag, types: List[Type] = Nil) = {
    Webpage().require(jsTree)
    val attributes = ListBuffer.empty[String]
    attributes += "'plugins': ['themes', 'html_data', 'ui', 'types']"
    if (types.nonEmpty) {
      attributes +=
        """
          |'types': {
          |%s
          |}
        """.stripMargin.trim.format(types.map(t => "\t%s".format(t.toJSON)).mkString(",\r\n"))
    }
    val js =
      """
        |jstree({
        |%s
        |})
      """.stripMargin.trim.format(attributes.map(s => "\t%s".format(s)).mkString(",\r\n"))
    jQuery.call(t, js)
  }

  case class Type(name: String,
                  maxChildren: Int = -1,
                  maxDepth: Int = -1,
                  useData: Boolean = false,
                  iconURL: String = null,
                  allowSelect: Boolean = true,
                  typeAttr: String = "rel") {
    def toJSON = {
      val attributes = ListBuffer.empty[String]
      if (maxChildren != -1) {
        attributes += "'max_children': %s".format(maxChildren)
      }
      if (maxDepth != -1) {
        attributes += "'max_depth': %s".format(maxDepth)
      }
      if (useData) {
        attributes += "'useData': %s".format(useData)
      }
      if (iconURL != null) {
        attributes += "'icon': { 'image': '%s' }".format(iconURL)
      }
      if (!allowSelect) {
        attributes += "'select_node': function() { return false; }"
      }
      if (typeAttr != "rel") {
        attributes += "'type_attr': '%s'".format(typeAttr)
      }
      """
        |'%s': {
        |%s
        |}
      """.stripMargin.trim.format(name, attributes.map(s => "\t%s".format(s)).mkString(",\r\n"))
    }
  }
}