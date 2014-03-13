package org.hyperscala.examples

import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait ExamplePage extends Webpage {
  def example: Example
}
