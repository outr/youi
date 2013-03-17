package org.hyperscala.examples.ui

import org.hyperscala.web.site.Webpage
import org.hyperscala.ui.widgets._
import org.powerscala.{Country, Language}
import org.hyperscala.jquery.Gritter
import vs._
import org.hyperscala.examples.Example

/**
 * @author Matt Hicks <matt@outr.com>
 */
class VisualSearchExample extends Example {
  Webpage().require(Gritter)

  val search = new VisualSearch {
    add(VisualSearchFacet.enum(Language))
    add(VisualSearchFacet.enum(Country))

    override def search(query: VisualSearchQuery) {
      super.search(query)

      Gritter.add("Search", "Searched for: %s".format(query.query))
    }
  }
  contents += search
}