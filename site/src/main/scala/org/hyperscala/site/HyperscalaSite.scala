package org.hyperscala.site

import org.hyperscala.web.site.{Website, WebpageResource}
import org.hyperscala.web.session.MapSession
import org.hyperscala.examples.helloworld.HelloWorldPage
import org.hyperscala.web.Scope
import org.hyperscala.examples.basic._
import org.hyperscala.examples.ui._
import org.hyperscala.examples.todomvc.TodoMVC
import com.outr.webcommunicator.netty.handler.PathHandler
import org.hyperscala.examples.numberguess.NumberGuess
import org.hyperscala.examples.svg.{SVGShapesExample, BasicSVGExample}
import org.hyperscala.examples.comparison.PlayHelloWorldPage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends Website[MapSession] {
  override def port = 8080

  val site = new {
    val about = new WebpageResource("/about.html", HyperscalaAbout, Scope.Request) {
      matchers += matches("/")
    }
  }
  val examples = new {
    val hello = WebpageResource("/example/hello.html", new HelloWorldPage, Scope.Request)
    val numberGuess = WebpageResource("/example/number_guess.html", new HyperscalaExample(new NumberGuess), Scope.Page)
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
    val dynamicPage = WebpageResource("/example/dynamic_page.html", new DynamicPageExample, Scope.Page)
    val chat = WebpageResource("/example/chat.html", new ChatExample, Scope.Session)
    val pageChange = WebpageResource("/example/page_change.html", new PageChangeWarningExample, Scope.Page)
    val fileUploader = WebpageResource("/example/file_upload.html", new HyperscalaExample(new FileUploaderExample), Scope.Page)
    val svg = new {
      val basic = WebpageResource("/example/svg/basic.html", new HyperscalaExample(new BasicSVGExample), Scope.Page)
      val shapes = WebpageResource("/example/svg/shapes.html", new HyperscalaExample(new SVGShapesExample), Scope.Page)
    }
    val playComparison = WebpageResource("/example/comparison/play_hello_world.html", new PlayHelloWorldPage, Scope.Page)
  }
  val todoMVC = WebpageResource("/todomvc.html", TodoMVC, Scope.Session)

  register(PathHandler("/images/", "images/"))
  register(PathHandler("/css/", "css/"))
  register(PathHandler("/js/", "js/"))

  protected def createSession() = new MapSession
}
