package org.hyperscala.site

import org.hyperscala.web.{Scope, PageHandler, Website}
import org.hyperscala.web.session.MapSession
import org.hyperscala.examples.basic.{AdvancedBeanFormExample, BeanFormExample, FormExample}
import org.hyperscala.examples.todomvc.TodoMVC
import org.hyperscala.examples.helloworld.HelloWorldPage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends Website[MapSession] {
  contents += PageHandler("about.html") {
    HyperscalaAbout
  }
  contents += PageHandler("example/hello.html", Scope.Request) {
    HelloWorldPage
  }
  contents += PageHandler("example/form.html", Scope.Session) {
    new FormExample
  }
  contents += PageHandler("example/beanform.html", Scope.Session) {
    new BeanFormExample
  }
  contents += PageHandler("example/advancedbeanform.html", Scope.Session) {
    new AdvancedBeanFormExample
  }
  contents += PageHandler("todomvc.html") {
    TodoMVC
  }

  protected def createSession = new MapSession
}
