package org.hyperscala.site

import org.hyperscala.web.site.{WebpageResource, Website}
import org.hyperscala.web.session.MapSession
import org.hyperscala.examples.helloworld.HelloWorldPage
import org.hyperscala.web.Scope
import org.hyperscala.examples.basic._
import org.hyperscala.examples.ui._
import org.hyperscala.examples.todomvc.TodoMVC
import com.outr.webcommunicator.netty.handler.PathHandler

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends Website[MapSession] {
  override def port = 8888

  val site = new {
    val about = new WebpageResource {
      matchers += matches("/")
      matchers += matches("/about.html")
      creator {
        HyperscalaAbout
      }
    }
  }
  val examples = new {
    val hello = WebpageResource("/example/hello.html", new HelloWorldPage, Scope.Request)
    val static = WebpageResource("/example/static.html", new StaticHTMLExample, Scope.Request)
    val form = WebpageResource("/example/form.html", new FormExample, Scope.Session)
    val realTime = WebpageResource("/example/realtime.html", new RealtimeWebpageExample, Scope.Session)
    val visual = WebpageResource("/example/visual.html", new VisualExample, Scope.Session)
    val visualize = WebpageResource("/example/visualize.html", new VisualizeExample, Scope.Session)
    val visualizeAdvanced = WebpageResource("/example/visualize_advanced.html", new VisualizeAdvancedExample, Scope.Session)
    val autoComplete = WebpageResource("/example/autocomplete.html", new AutoCompleteExample, Scope.Session)
    val richEditor = WebpageResource("/example/richeditor.html", new RichEditorExample, Scope.Page)
    val nivoSlider = WebpageResource("/example/nivoslider.html", new NivoSliderExample, Scope.Page)
    val dialog = WebpageResource("/example/dialog.html", new DialogExample, Scope.Page)
    val dynamic = WebpageResource("/example/dynamic.html", new DynamicContentExample, Scope.Page)
  }
  val todoMVC = WebpageResource("/todomvc.html", TodoMVC, Scope.Session)

  register("images/slide1.jpg")
  register("images/slide2.jpg")
  register("images/slide3.jpg")
  register("images/slide4.jpg")

  register(PathHandler("images/", "/images/"))
  register(PathHandler("css/", "/css/"))
  register(PathHandler("js/", "/js/"))

  protected def createSession() = new MapSession
}
