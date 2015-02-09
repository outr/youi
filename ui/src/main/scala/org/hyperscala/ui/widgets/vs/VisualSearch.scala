package org.hyperscala.ui.widgets.vs

import com.outr.net.http.HttpHandler
import com.outr.net.http.content.StringContent
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponse, HttpResponseStatus}
import com.outr.net.http.session.Session
import org.hyperscala.event.BrowserEvent
import org.hyperscala.html._
import org.hyperscala.jquery.jQuery
import org.hyperscala.jquery.ui.jQueryUI
import org.hyperscala.module.Module
import org.hyperscala.realtime.Realtime
import org.hyperscala.selector.Selector
import org.hyperscala.web._
import org.powerscala.Version
import org.powerscala.json.TypedSupport
import org.powerscala.property._
import org.hyperscala.javascript.dsl._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class VisualSearch extends tag.Div {
  private var _facets = List.empty[VisualSearchFacet]

  this.require(VisualSearch)
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

    connected[Webpage[Session]] {
      case webpage => {
        val instruction = s"createVisualSearch('${webpage.pageId}', '$identity', '${VisualSearch.Path}');"
        webpage.eval(instruction, Some(Selector.id(id()).toCondition))
      }
    }

    query.change.on {
      case evt => {
        if (query() != modifying.get()) {   // Only send change to client if not the same information sent from client
          val content = "\"%s\"".format(query().query)
          connected[Webpage[Session]] {
            case webpage => webpage.eval(s"setVisualSearchQuery('%s', $content);".format(id()))
          }
        }
        search(query())
      }
    }
  }

  handle[SearchRequest] {
    case evt => {
      val q = evt.query
      val f = evt.facets.map(m => VisualSearchEntry(facet(m.head._1), m.head._2))
      val searchQuery = VisualSearchQuery(q, f)
      modifying.set(searchQuery)
      try {
        query := searchQuery
      } finally {
        modifying.remove()
      }
    }
  }
}

case class SearchRequest(tag: HTMLTag, query: String, facets: List[Map[String, String]]) extends BrowserEvent

object VisualSearch extends Module {
  TypedSupport.register("searchRequest", classOf[SearchRequest])

  val Path = "/visualsearch/search"

  val name = "visualsearch"
  val version = Version(0, 4, 0)

  override val dependencies = List(jQuery, jQueryUI, Realtime)

  val basePath = "/visualsearch-0.4.0/"

  override def init[S <: Session](website: Website[S]) = {
    website.addHandler(new VisualSearchHandler(website), Path)
    website.addClassPath(basePath, "visualsearch/0.4.0/")
  }

  override def load[S <: Session](page: Webpage[S]) = {
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

class VisualSearchHandler[S <: Session](website: Website[S]) extends HttpHandler {
  def onReceive(request: HttpRequest, response: HttpResponse) = {
    val pageId = request.url.parameters.first("pageId")
    val fieldId = request.url.parameters.first("fieldId")

    val page = website.pages.byId[Webpage[S]](pageId).getOrElse(throw new NullPointerException(s"Cannot find page by id: $pageId"))
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