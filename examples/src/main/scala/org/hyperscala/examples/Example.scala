package org.hyperscala.examples

import org.hyperscala.html._
import org.hyperscala.module.Interface
import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait Example extends tag.Div {
  def exampleName = getClass.getSimpleName

  def require(interface: Interface) = connected[Webpage] {
    case webpage => webpage.require(interface)
  }

  final def sourceURL = Example.URL.format(getClass.getName.replaceAll("[.]", "/"))
}

object Example {
  def URL = "https://github.com/darkfrog26/hyperscala/blob/master/examples/src/main/scala/%s.scala"
}