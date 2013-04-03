package org.hyperscala.site

import org.hyperscala.web.site.{Website, WebpageResource}
import org.hyperscala.web.session.MapSession
import org.hyperscala.examples.helloworld.HelloWorldPage
import org.hyperscala.web.Scope
import org.hyperscala.examples.basic._
import org.hyperscala.examples.ui._
import org.hyperscala.examples.todomvc.TodoMVC
import com.outr.webcommunicator.netty.handler.PathHandler
import org.hyperscala.examples.svg.{DynamicSVGExample, SVGShapesExample, BasicSVGExample}
import org.hyperscala.examples.comparison.PlayHelloWorldPage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends Website[MapSession] {
  override def port = 8889

  val site = new {
    val about = new WebpageResource("/about.html", new HyperscalaAbout, Scope.Request) {
      matchers += matches("/")
    }
    val examples = new WebpageResource("/examples.html", new HyperscalaExamples, Scope.Request)
    val documentation = new WebpageResource("/documentation.html", new HyperscalaDocumentation, Scope.Request)
  }
  val examples = new {
    val hello = WebpageResource("/example/hello.html", new HyperscalaExample(new HelloWorldPage), Scope.Request)
//    val numberGuess = WebpageResource("/example/number_guess.html", new HyperscalaExample(new NumberGuess), Scope.Page)
    val static = WebpageResource("/example/static.html", new HyperscalaExample(new StaticHTMLExample), Scope.Request)
    val form = WebpageResource("/example/form.html", new HyperscalaExample(new FormExample), Scope.Session)
    val realTime = WebpageResource("/example/realtime.html", new HyperscalaExample(new RealtimeWebpageExample), Scope.Session)
    val realTimeForm = WebpageResource("/example/realtime_form.html", new HyperscalaExample(new RealtimeFormExample), Scope.Page)
    val visual = WebpageResource("/example/visual.html", new HyperscalaExample(new VisualExample), Scope.Session)
    val visualize = WebpageResource("/example/visualize.html", new HyperscalaExample(new VisualizeExample), Scope.Session)
    val visualizeAdvanced = WebpageResource("/example/visualize_advanced.html", new HyperscalaExample(new VisualizeAdvancedExample), Scope.Session)
    val autoComplete = WebpageResource("/example/autocomplete.html", new HyperscalaExample(new AutoCompleteExample), Scope.Session)
    val tabs = WebpageResource("/example/tabs.html", new HyperscalaExample(new TabsExample), Scope.Page)
    val richEditor = WebpageResource("/example/richeditor.html", new HyperscalaExample(new RichEditorExample), Scope.Page)
    val nivoSlider = WebpageResource("/example/nivoslider.html", new HyperscalaExample(new NivoSliderExample), Scope.Page)
    val gritter = WebpageResource("/example/gritter.html", new HyperscalaExample(new GritterExample), Scope.Page)
    val visualSearch = WebpageResource("/example/visualsearch.html", new HyperscalaExample(new VisualSearchExample), Scope.Page)
    val dialog = WebpageResource("/example/dialog.html", new HyperscalaExample(new DialogExample), Scope.Page)
    val confirmDialog = WebpageResource("/example/confirm_dialog.html", new HyperscalaExample(new ConfirmDialogExample), Scope.Page)
    val dynamic = WebpageResource("/example/dynamic.html", new HyperscalaExample(new DynamicContentExample), Scope.Page)
    val dynamicPage = WebpageResource("/example/dynamic_page.html", new DynamicPageExample, Scope.Page)
    val chat = WebpageResource("/example/chat.html", new ChatExample, Scope.Session)
    val pageChange = WebpageResource("/example/page_change.html", new HyperscalaExample(new PageChangeWarningExample), Scope.Page)
    val pageLoader = WebpageResource("/example/page_loader.html", new PageLoaderExample, Scope.Page)
    val fileUploader = WebpageResource("/example/file_upload.html", new HyperscalaExample(new FileUploaderExample), Scope.Page)
    val dynamicURL = WebpageResource("/example/dynamic_url.html", new HyperscalaExample(new DynamicURLExample), Scope.Page)
    val multiSelect = WebpageResource("/example/multi_select.html", new HyperscalaExample(new MultiSelectExample), Scope.Page)
    val svg = new {
      val basic = WebpageResource("/example/svg/basic.html", new HyperscalaExample(new BasicSVGExample), Scope.Page)
      val shapes = WebpageResource("/example/svg/shapes.html", new HyperscalaExample(new SVGShapesExample), Scope.Page)
      val dynamic = WebpageResource("/example/svg/dynamic.html", new HyperscalaExample(new DynamicSVGExample), Scope.Page)
    }
    val playComparison = WebpageResource("/example/comparison/play_hello_world.html", new PlayHelloWorldPage, Scope.Page)
  }
  val todoMVC = WebpageResource("/todomvc.html", TodoMVC, Scope.Session)

  register(PathHandler("/images/", "images/"))
  register(PathHandler("/css/", "css/"))
  register(PathHandler("/js/", "js/"))

  protected def createSession() = new MapSession
}
