package org.hyperscala.ui.widgets.vs

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class VisualSearchQuery(query: String = "", entries: List[VisualSearchEntry] = Nil) {
  def values(facet: String) = entries.collect {
    case vse if (vse.facet.map(f => f.name).getOrElse(null) == facet) => vse.value
  }

  lazy val textValue = values(null).headOption
}