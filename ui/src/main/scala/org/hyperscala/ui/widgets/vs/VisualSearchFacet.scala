package org.hyperscala.ui.widgets.vs

import org.powerscala.{EnumEntry, Enumerated}

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class VisualSearchFacet(name: String, allowMultiple: Boolean, resultType: ResultType, search: String => Seq[VisualSearchResult])

object VisualSearchFacet {
  def enum(e: Enumerated[_ <: EnumEntry[_]], allowMultiple: Boolean = false) = {
    val results = e.values.map(entry => VisualSearchResult(entry.label, entry.label))
    VisualSearchFacet(e.name, allowMultiple, ResultType.Complete, (query: String) => results)
  }
}
