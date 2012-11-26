package org.hyperscala.site

import org.hyperscala.web.site.{WebpageResource, Website}
import org.hyperscala.web.session.MapSession
import org.hyperscala.examples.helloworld.HelloWorldPage
import org.hyperscala.web.Scope
import org.hyperscala.examples.basic.{RealtimeWebpageExample, FormExample}
import org.hyperscala.examples.ui.{VisualizeAdvancedExample, VisualizeExample, AutoCompleteExample, VisualExample}
import org.hyperscala.examples.todomvc.TodoMVC

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends Website[MapSession] {
  val about = new WebpageResource {
    matchers += matches("/")
    matchers += matches("/about.html")
    creator {
      HyperscalaAbout
    }
  }
  val hello = WebpageResource("/example/hello.html", new HelloWorldPage, Scope.Page)
  val form = WebpageResource("/example/form.html", new FormExample, Scope.Session)
  val realTime = WebpageResource("/example/realtime.html", new RealtimeWebpageExample, Scope.Session)
  val visual = WebpageResource("/example/visual.html", new VisualExample, Scope.Session)
  val visualize = WebpageResource("/example/visualize.html", new VisualizeExample, Scope.Session)
  val visualizeAdvanced = WebpageResource("/example/visualize_advanced.html", new VisualizeAdvancedExample, Scope.Session)
  val autoComplete = WebpageResource("/example/autocomplete.html", new AutoCompleteExample, Scope.Session)
  val todoMVC = WebpageResource("/todomvc.html", TodoMVC, Scope.Session)

  registerPath("images/", "/images/", enableCaching = true)
  registerPath("css/", "/css/", enableCaching = true)
  registerPath("js/", "/js/", enableCaching = true)

  protected def createSession() = new MapSession(this) {
    override def timeout = 30.0

    override def update(delta: Double) {
      super.update(delta)
    }
  }
}
