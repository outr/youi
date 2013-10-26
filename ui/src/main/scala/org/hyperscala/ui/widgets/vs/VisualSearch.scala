package org.hyperscala.ui.widgets.vs

import org.hyperscala.html._
import org.hyperscala.module.Module
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.jquery.jQuery
import org.hyperscala.jquery.ui.jQueryUI
import org.hyperscala.realtime.Realtime
import org.powerscala.property._
import org.powerscala.Version
import org.hyperscala.ResponseMessage
import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import com.outr.net.http.content.StringContent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class VisualSearch extends tag.Div {
  private var _facets = List.empty[VisualSearchFacet]

  Webpage().require(VisualSearch)
  identity

  private val modifying = new ThreadLocal[VisualSearchQuery]

  val query = Property[VisualSearchQuery](default = Some(VisualSearchQuery()))

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

    val instruction = s"createVisualSearch('${Webpage().pageId}', '$identity', '${VisualSearch.Path}');"
    Realtime.sendJavaScript(instruction, selector = s"#${id()}", onlyRealtime = false)

    query.change.on {
      case evt => {
        if (query() != modifying.get()) {   // Only send change to client if not the same information sent from client
        val content = "\"%s\"".format(query().query)
          Realtime.sendJavaScript("setVisualSearchQuery('%s', content);".format(id()), content)
        }
        search(query())
      }
    }
  }

  override def receive(event: String, message: ResponseMessage) = event match {
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

object VisualSearch extends Module with HttpHandler {
  val Path = "/visualsearch/search"

  val name = "visualsearch"
  val version = Version(0, 4, 0)

  override val dependencies = List(jQuery.LatestWithDefault, jQueryUI.LatestWithDefault, Realtime)

  val basePath = "/visualsearch-0.4.0/"

  def init() = {
    Website().addHandler(this, Path)
    Website().addClassPath(basePath, "visualsearch/0.4.0/")
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

  def onReceive(request: HttpRequest, response: HttpResponse) = {
    val pageId = request.url.parameters.first("pageId")
    val fieldId = request.url.parameters.first("fieldId")

    val page = Website().pages.byId[Webpage](pageId).getOrElse(throw new NullPointerException(s"Cannot find page by id: $pageId"))
    Webpage.contextualize(page) {
      val visualSearch = page.getById[VisualSearch](fieldId)
      val results = request.url.parameters.first("requestType") match {
        case "facets" => {
          visualSearch.facets.collect {
            case f if f.allowMultiple || !visualSearch.queryHasFacet(f.name) => if (f.category == null) {
              "\"%s\"".format(f.name)
            } else {
              "{\"label\": \"%s\", \"category\": \"%s\"}".format(f.name, f.category)
            }
          }.mkString("[", ", ", "]")
        }
        case "values" => {
          val facetName = request.url.parameters.first("facet")
          val term = request.url.parameters.first("term")
          visualSearch.facet(facetName) match {
            case Some(facet) => {
              val results = facet.search(term, visualSearch)
              val resultString = results.map(r => "{\"value\": \"%s\", \"label\": \"%s\"}".format(r.value, r.label)).mkString("[", ", ", "]")
              "{\"resultType\": \"%s\", \"exactMatch\": %s, \"results\": %s}".format(facet.resultType.name, facet.exactMatch, resultString)
            }
            case None => "{\"results\": []}"
          }
        }
      }
      response.copy(content = StringContent(results), status = HttpResponseStatus.OK)
    }
  }
}