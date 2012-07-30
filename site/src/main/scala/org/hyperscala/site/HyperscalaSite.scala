package org.hyperscala.site

import org.hyperscala.web.{Scope, PageHandler, Website}
import org.hyperscala.web.session.MapSession
import org.hyperscala.examples.helloworld.HelloWorldPage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends Website[MapSession] {
  contents += new PageHandler(() => HelloWorldPage, (s: String) => true, Scope.Request)

  protected def createSession = new MapSession
}
