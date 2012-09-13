package org.hyperscala.site

import org.hyperscala.web.Website
import org.hyperscala.web.session.MapSession
import org.hyperscala.examples.basic._
import org.hyperscala.examples.todomvc.TodoMVC
import org.hyperscala.examples.helloworld.HelloWorldPage
import org.hyperscala.examples.ui.AutoCompleteExample
import org.hyperscala.web.resource.handler.{ClassLoaderWebResourceManager, SessionWebResourceManager, URIWebResourceManager}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends Website[MapSession] {
  val about = new URIWebResourceManager("/about.html") {
    def create(uri: String) = HyperscalaAbout
  }
  val hello = new URIWebResourceManager("/example/hello.html") {
    def create(uri: String) = HelloWorldPage
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
  val todoMVC = new URIWebResourceManager("/todomvc.html") {
    def create(uri: String) = TodoMVC
  }

  val defaultLoader = new ClassLoaderWebResourceManager()

  protected def createSession = new MapSession
}
