package org.hyperscala.ui.widgets.vs

import org.powerscala.{EnumEntry, Enumerated}

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait VisualSearchFacet {
  /**
   * The name of this facet that appears in VisualSearch.
   */
  def name: String

  /**
   * Only allows one entry of this facet in the VisualSearch.
   */
  def allowMultiple: Boolean

  /**
   * Requires an exact match. If set to false the first result displayed will be the query typed if there is more than
   * one result matching. If set to true the first result will be the first entry.
   */
  def exactMatch: Boolean

  /**
   * The type of result being returned.
   */
  def resultType: ResultType

  /**
   * Search for entries for this facet.
   */
  def search(query: String): Seq[VisualSearchResult]
}

case class BasicVisualSearchFacet(name: String,
                                  allowMultiple: Boolean,
                                  exactMatch: Boolean,
                                  resultType: ResultType,
                                  searchHandler: String => Seq[VisualSearchResult]) extends VisualSearchFacet {
  def search(query: String) = searchHandler(query)
}

object VisualSearchFacet {
  def enum(e: Enumerated[_ <: EnumEntry[_]], allowMultiple: Boolean = false, exactMatch: Boolean = true) = {
    val results = e.values.map(entry => VisualSearchResult(entry.label, entry.label))
    BasicVisualSearchFacet(e.name, allowMultiple, exactMatch, ResultType.Complete, (query: String) => results)
  }

  def basic(name: String, allowMultiple: Boolean = false, exactMatch: Boolean = false, entries: List[String]) = {
    val results = entries.map(s => VisualSearchResult(s, s))
    BasicVisualSearchFacet(name, allowMultiple, exactMatch, ResultType.Complete, (query: String) => results)
  }
}
