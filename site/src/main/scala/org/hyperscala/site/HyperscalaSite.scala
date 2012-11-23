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
/*object HyperscalaSite extends Website[MapSession] {
  val about = new URIWebResourceManager("/about.html") {
    def create(uri: String) = HyperscalaAbout
  }
  val hello = new URIWebResourceManager("/example/hello.html") {
    def create(uri: String) = new HelloWorldPage
  }
  val form = new URIWebResourceManager("/example/form.html") with SessionWebResourceManager {
    def create(uri: String) = new FormExample
  }
  val beanForm = new URIWebResourceManager("/example/beanform.html") with SessionWebResourceManager {
    def create(uri: String) = new BeanFormExample
  }
  val advancedBeanForm = new URIWebResourceManager("/example/advancedbeanform.html") with SessionWebResourceManager {
    def create(uri: String) = new AdvancedBeanFormExample
  }
  val livePage = new URIWebResourceManager("/example/livepage.html") with SessionWebResourceManager {
    def create(uri: String) = new LivePageExample
  }
  val autoComplete = new URIWebResourceManager("/example/autocomplete.html") with SessionWebResourceManager {
    def create(uri: String) = new AutoCompleteExample
  }
  val editable = new URIWebResourceManager("/example/editable.html") with SessionWebResourceManager {
    def create(uri: String) = new EditableExample
  }
  val visual = new URIWebResourceManager("/example/visual.html") with SessionWebResourceManager {
    def create(uri: String) = new VisualExample
  }
  val todoMVC = new URIWebResourceManager("/todomvc.html") {
    def create(uri: String) = TodoMVC
  }

  val defaultLoader = new ClassLoaderWebResourceManager()

  protected def createSession = new MapSession
}*/

object HyperscalaSite extends Website[MapSession] {
  val about = WebpageResource.regex("/about.html") {
    HyperscalaAbout
  }
  val hello = WebpageResource.regex("/example/hello.html", scope = Scope.Page) {
    new HelloWorldPage
  }
  val form = WebpageResource.regex("/example/form.html", scope = Scope.Session) {
    new FormExample
  }
  val realtime = WebpageResource.regex("/example/realtime.html", scope = Scope.Session) {
    new RealtimeWebpageExample
  }
  val visual = WebpageResource.regex("/example/visual.html", scope = Scope.Session) {
    new VisualExample
  }
  val visualize = WebpageResource.regex("/example/visualize.html", scope = Scope.Session) {
    new VisualizeExample
  }
  val visualizeAdvanced = WebpageResource.regex("/example/visualize_advanced.html", scope = Scope.Session) {
    new VisualizeAdvancedExample
  }
  val autoComplete = WebpageResource.regex("/example/autocomplete.html", scope = Scope.Session) {
    new AutoCompleteExample
  }
  val todoMVC = WebpageResource.regex("/todomvc.html", scope = Scope.Session) {
    TodoMVC
  }

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
