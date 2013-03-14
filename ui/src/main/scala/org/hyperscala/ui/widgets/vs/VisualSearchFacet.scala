package org.hyperscala.ui.widgets.vs

import org.powerscala.{EnumEntry, Enumerated}

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait VisualSearchFacet {
  def name: String
  def allowMultiple: Boolean
  def resultType: ResultType
  def search(query: String): Seq[VisualSearchResult]
}

case class BasicVisualSearchFacet(name: String,
                                  allowMultiple: Boolean,
                                  resultType: ResultType,
                                  searchHandler: String => Seq[VisualSearchResult]) extends VisualSearchFacet {
  def search(query: String) = searchHandler(query)
}

object VisualSearchFacet {
  def enum(e: Enumerated[_ <: EnumEntry[_]], allowMultiple: Boolean = false) = {
    val results = e.values.map(entry => VisualSearchResult(entry.label, entry.label))
    BasicVisualSearchFacet(e.name, allowMultiple, ResultType.Complete, (query: String) => results)
  }

  def basic(name: String, allowMultiple: Boolean = false, entries: List[String]) = {
    val results = entries.map(s => VisualSearchResult(s, s))
    BasicVisualSearchFacet(name, allowMultiple, ResultType.Complete, (query: String) => results)
  }
}
