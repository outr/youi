package org.hyperscala.examples

import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait Example extends Webpage {
  def exampleName = getClass.getSimpleName

  final def sourceURL = Example.URL.format(getClass.getName.replaceAll("[.]", "/"))
}

object Example {
  def URL = "https://github.com/darkfrog26/hyperscala/blob/master/examples/src/main/scala/%s.scala"
}