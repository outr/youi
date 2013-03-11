package org.hyperscala.ui.widgets.vs

import org.hyperscala.html._
import org.hyperscala.module.Module
import org.hyperscala.web.site.{WebpageResource, Webpage, Website}
import com.outr.webcommunicator.netty.handler.PathHandler
import org.hyperscala.jquery.jQuery
import org.hyperscala.jquery.ui.jQueryUI
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.Scope
import org.powerscala.property._
import org.powerscala.Version
import org.hyperscala.Message

/**
 * @author Matt Hicks <matt@outr.com>
 */
class VisualSearch extends tag.Div {
  private lazy val handler = new VisualSearchHandler(this)
  private var _facets = List.empty[VisualSearchFacet]

  Webpage().require(VisualSearch)
  identity

  private val modifying = new ThreadLocal[VisualSearchQuery]

  val query = Property[VisualSearchQuery]("query", VisualSearchQuery())

  def +=(facet: VisualSearchFacet) = synchronized {
    _facets = (facet :: _facets.reverse).reverse
  }

  def -=(facet: VisualSearchFacet) = synchronized {
    _facets = _facets.filterNot(f => f == facet)
  }

  def add(facet: VisualSearchFacet) = this += facet
  def remove(facet: VisualSearchFacet) = this -= facet

  def facets = _facets

  def facet(facet: String) = _facets.find(f => f.name.equalsIgnoreCase(facet))

  def queryHasFacet(facet: String) = {
    query().entries.find(e => e.facet.map(f => f.name).getOrElse("").equalsIgnoreCase(facet)).nonEmpty
  }

  def search(query: VisualSearchQuery) = {}

  override protected def initialize() {
    super.initialize()

    val source = "/visualsearch/%s".format(id())
    Website().registerSession(WebpageResource(source, handler, Scope.Request))
    val instruction = "createVisualSearch('%s', '%s');".format(id(), source)
    Realtime.sendJavaScript(instruction, forId = id(), onlyRealtime = false)

    query.onChange {
      if (query() != modifying.get()) {   // Only send change to client if not the same information sent from client
        val content = "\"%s\"".format(query().query)
        Realtime.sendJavaScript("setVisualSearchQuery('%s', content);".format(id()), content)
      }

      search(query())
    }
  }

  override def receive(event: String, message: Message) = event match {
    case "search" => {
      val searchFacets = message.map("facets").asInstanceOf[List[Map[String, String]]]
      val q = message.map("query").asInstanceOf[String]
      val f = searchFacets.map(m => VisualSearchEntry(facet(m.head._1), m.head._2))
      val searchQuery = VisualSearchQuery(q, f)
      modifying.set(searchQuery)
      try {
        query := searchQuery
      } finally {
        modifying.remove()
      }
    }
    case _ => super.receive(event, message)
  }
}

object VisualSearch extends Module {
  val name = "visualsearch"
  val version = Version(0, 4, 0)

  override val dependencies = List(jQuery.LatestWithDefault, jQueryUI.LatestWithDefault, Realtime)

  val basePath = "/visualsearch-0.4.0/"

  def init() = {
    Website().register(PathHandler(basePath, "visualsearch/0.4.0/"))
  }

  def load() = {
    val page = Webpage()
    // TODO: extract underscore and backbone as modules
    page.head.contents += new tag.Link(href = "%slib/css/reset.css".format(basePath), rel = "stylesheet")
    page.head.contents += new tag.Link(href = "%slib/css/icons.css".format(basePath), rel = "stylesheet")
    page.head.contents += new tag.Link(href = "%slib/css/workspace.css".format(basePath), rel = "stylesheet")

    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%svendor/underscore-1.4.3.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%svendor/backbone-0.9.10.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/visualsearch.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/views/search_box.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/views/search_facet.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/views/search_input.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/models/search_facets.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/models/search_query.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/utils/backbone_extensions.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/utils/hotkeys.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/utils/jquery_extensions.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/utils/search_parser.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/utils/inflector.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%slib/js/templates/templates.js".format(basePath))
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "%shyperscala-visualsearch.js".format(basePath))
  }
}