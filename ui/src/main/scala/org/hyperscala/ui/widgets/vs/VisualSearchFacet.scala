package org.hyperscala.ui.widgets.vs

import org.powerscala.enum.{EnumEntry, Enumerated}

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait VisualSearchFacet {
  /**
   * The name of this facet that appears in VisualSearch.
   */
  def name: String

  /**
   * The category this facet is represented by.
   */
  def category: String

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
  def search(query: String, search: VisualSearch): Seq[VisualSearchResult]
}

case class BasicVisualSearchFacet(name: String,
                                  category: String,
                                  allowMultiple: Boolean,
                                  exactMatch: Boolean,
                                  resultType: ResultType,
                                  searchHandler: String => Seq[VisualSearchResult]) extends VisualSearchFacet {
  def search(query: String, search: VisualSearch) = searchHandler(query)
}

object VisualSearchFacet {
  def enum(e: Enumerated[_ <: EnumEntry], allowMultiple: Boolean = false, exactMatch: Boolean = true, category: String = null) = {
    val results = e.values.map(entry => VisualSearchResult(entry.label, entry.label))
    BasicVisualSearchFacet(e.name, category, allowMultiple, exactMatch, ResultType.Complete, (query: String) => results)
  }

  def basic(name: String, allowMultiple: Boolean = false, exactMatch: Boolean = false, entries: List[String], category: String = null) = {
    val results = entries.map(s => VisualSearchResult(s, s))
    BasicVisualSearchFacet(name, category, allowMultiple, exactMatch, ResultType.Complete, (query: String) => results)
  }
}
