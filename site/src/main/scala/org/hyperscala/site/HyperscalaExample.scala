package org.hyperscala.site

import org.hyperscala.examples.Example

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class HyperscalaExample(example: Example) extends HyperscalaPage {
  main.contents += example

  override def sourceURL = example.sourceURL
}
