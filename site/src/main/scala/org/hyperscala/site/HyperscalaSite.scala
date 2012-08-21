package org.hyperscala.site

import org.hyperscala.web.handler.PageHandler
import org.hyperscala.web.{Scope, Website}
import org.hyperscala.web.session.MapSession
import org.hyperscala.examples.basic.{LivePageExample, AdvancedBeanFormExample, BeanFormExample, FormExample}
import org.hyperscala.examples.todomvc.TodoMVC
import org.hyperscala.examples.helloworld.HelloWorldPage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends Website[MapSession] {
  val about = PageHandler("/about.html") {
    HyperscalaAbout
  }
  val hello = PageHandler("/example/hello.html", Scope.Request) {
    HelloWorldPage
  }
  val form = PageHandler("/example/form.html", Scope.Session) {
    new FormExample
  }
  val beanForm = PageHandler("/example/beanform.html", Scope.Session) {
    new BeanFormExample
  }
  val advancedBeanForm = PageHandler("/example/advancedbeanform.html", Scope.Session) {
    new AdvancedBeanFormExample
  }
  val livePage = PageHandler("/example/livepage.html", Scope.Session) {
    new LivePageExample
  }
  val todoMVC = PageHandler("/todomvc.html") {
    TodoMVC
  }

  protected def createSession = new MapSession
}
