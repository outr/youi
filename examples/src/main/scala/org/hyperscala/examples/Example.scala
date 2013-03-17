package org.hyperscala.examples

import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait Example extends tag.Div {
  def exampleName = getClass.getSimpleName

  final def sourceURL = Example.URL.format(getClass.getName.replaceAll("[.]", "/"))
}

object Example {
  def URL = "https://github.com/darkfrog26/hyperscala/blob/master/examples/src/main/scala/%s.scala"
}