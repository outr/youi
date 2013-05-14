package org.hyperscala.ui.widgets.vs

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed class ResultType extends EnumEntry

object ResultType extends Enumerated[ResultType] {
  /**
   * Results are queried for every search term.
   */
  val Realtime = new ResultType
  /**
   * Entire result set is returned for the facet and can be cached.
   */
  val Complete = new ResultType
  /**
   * Results are cacheable for the specified search value.
   */
  val Cacheable = new ResultType
}
