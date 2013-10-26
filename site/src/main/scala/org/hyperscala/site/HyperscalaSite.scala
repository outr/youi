package org.hyperscala.site

import org.hyperscala.web.Website
import org.hyperscala.examples.helloworld.HelloWorldPage
import org.hyperscala.web.Scope
import org.hyperscala.examples.basic._
import org.hyperscala.examples.ui._
import org.hyperscala.examples.todomvc.TodoMVC
import org.hyperscala.examples.svg.{DynamicSVGExample, SVGShapesExample, BasicSVGExample}
import org.hyperscala.examples.comparison.PlayHelloWorldPage
import com.outr.net.http.session.MapSession
import com.outr.net.http.jetty.JettyApplication
import com.outr.net.http.request.HttpRequest
import org.powerscala.log.{Level, Logger}
import java.io.File
import org.powerscala.log.writer.FileWriter
import org.powerscala.log.formatter.Formatter

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends Website[MapSession] with JettyApplication {
  // Setup file logging
  Logger.configure("root") {
    case l => {
      val directory = new File("logs")
      val writer = new FileWriter(directory, FileWriter.Daily("hyp"), append = true)
      l.withHandler(Formatter.Default, Level.Info, writer)
    }
  }

  override def port = 8889

  val site = new {
    val about = page(new HyperscalaAbout, Scope.Request, "/about.html", "/")
    val examples = page(new HyperscalaExamples, Scope.Request, "/examples.html")
    val documentation = page(new HyperscalaDocumentation, Scope.Request, "/documentation.html")
  }
  val examples = new {
    val hello = page(new HyperscalaExample(new HelloWorldPage), Scope.Request, "/example/hello.html")
//    val numberGuess = page(new HyperscalaExample(new NumberGuess), Scope.Page, "/example/number_guess.html")
    val style = page(new StyleSheetExample, Scope.Request, "/example/style.html")
    val userAgent = page(new HyperscalaExample(new UserAgentExample), Scope.Request, "/example/user_agent.html")
    val large = page(new LargePageExample, Scope.Request, "/example/large.html")
    val static = page(new HyperscalaExample(new StaticHTMLExample), Scope.Request, "/example/static.html")
    val form = page(new HyperscalaExample(new FormExample), Scope.Session, "/example/form.html")
    val realTime = page(new HyperscalaExample(new RealtimeWebpageExample), Scope.Session, "/example/realtime.html")
    val realTimeForm = page(new HyperscalaExample(new RealtimeFormExample), Scope.Page, "/example/realtime_form.html")
    val visual = page(new HyperscalaExample(new VisualExample), Scope.Session, "/example/visual.html")
    val visualize = page(new HyperscalaExample(new VisualizeExample), Scope.Session, "/example/visualize.html")
    val visualizeAdvanced = page(new HyperscalaExample(new VisualizeAdvancedExample), Scope.Session, "/example/visualize_advanced.html")
    val autoComplete = page(new HyperscalaExample(new AutoCompleteExample), Scope.Session, "/example/autocomplete.html")
    val tabs = page(new HyperscalaExample(new TabsExample), Scope.Page, "/example/tabs.html")
    val tree = page(new HyperscalaExample(new TreeExample), Scope.Page, "/example/tree.html")
    val richEditor = page(new HyperscalaExample(new RichEditorExample), Scope.Page, "/example/richeditor.html")
    val editableContent = page(new HyperscalaExample(new EditableContentExample), Scope.Page, "/example/editablecontent.html")
    val nivoSlider = page(new HyperscalaExample(new NivoSliderExample), Scope.Page, "/example/nivoslider.html")
    val gritter = page(new HyperscalaExample(new GritterExample), Scope.Page, "/example/gritter.html")
    val spectrum = page(new HyperscalaExample(new SpectrumExample), Scope.Page, "/example/spectrum.html")
    val colorPicker = page(new HyperscalaExample(new ColorPickerExample), Scope.Page, "/example/colorpicker.html")
    val visualSearch = page(new HyperscalaExample(new VisualSearchExample), Scope.Page, "/example/visualsearch.html")
    val dialog = page(new HyperscalaExample(new DialogExample), Scope.Page, "/example/dialog.html")
    val draggable = page(new HyperscalaExample(new DraggableExample), Scope.Page, "/example/draggable.html")
    val droppable = page(new HyperscalaExample(new DroppableExample), Scope.Page, "/example/droppable.html")
    val dropReceiver = page(new HyperscalaExample(new DropReceiverExample), Scope.Page, "/example/drop_receiver.html")
    val clipboard = page(new HyperscalaExample(new ClipboardExample), Scope.Page, "/example/clipboard.html")
    val confirmDialog = page(new HyperscalaExample(new ConfirmDialogExample), Scope.Page, "/example/confirm_dialog.html")
    val dynamic = page(new HyperscalaExample(new DynamicContentExample), Scope.Page, "/example/dynamic.html")
    val dynamicPage = page(new DynamicPageExample, Scope.Page, "/example/dynamic_page.html")
    val chat = page(new ChatExample, Scope.Session, "/example/chat.html")
    val pageChange = page(new HyperscalaExample(new PageChangeWarningExample), Scope.Page, "/example/page_change.html")
    val pageLoader = page(new PageLoaderExample, Scope.Page, "/example/page_loader.html")
    val fileUploader = page(new HyperscalaExample(new FileUploaderExample), Scope.Page, "/example/file_upload.html")
    val dynamicURL = page(new HyperscalaExample(new DynamicURLExample), Scope.Page, "/example/dynamic_url.html")
    val multiSelect = page(new HyperscalaExample(new MultiSelectExample), Scope.Page, "/example/multi_select.html")
    val select2 = page(new HyperscalaExample(new Select2Example), Scope.Page, "/example/select2.html")
    val caseForm = page(new HyperscalaExample(new CaseFormExample), Scope.Page, "/example/case_form.html")
    val jsCombiner = page(new HyperscalaExample(new JavaScriptCombinerExample), Scope.Request, "/example/js_combiner.html")
    val jQueryEvents = page(new HyperscalaExample(new jQueryEventsExample), Scope.Request, "/example/jquery_events.html")
    val selectWrapper = page(new HyperscalaExample(new SelectWrapperExample), Scope.Page, "/example/select_wrapper.html")
    val dsl = page(new HyperscalaExample(new RealtimeDSLExample), Scope.Page, "/example/dsl.html")
    val history = page(new HyperscalaExample(new HistoryExample), Scope.Page, "/example/history.html")
    val monitor = page(new HyperscalaExample(new MonitorExample), Scope.Page, "/example/monitor.html")
    val changeable = page(new HyperscalaExample(new ChangeableExample), Scope.Page, "/example/changeable.html")
    val jsrequest = page(new HyperscalaExample(new JSRequestExample), Scope.Page, "/example/jsrequest.html")
    val svg = new {
      val basic = page(new HyperscalaExample(new BasicSVGExample), Scope.Page, "/example/svg/basic.html")
      val shapes = page(new HyperscalaExample(new SVGShapesExample), Scope.Page, "/example/svg/shapes.html")
      val dynamic = page(new HyperscalaExample(new DynamicSVGExample), Scope.Page, "/example/svg/dynamic.html")
    }
    val playComparison = page(new PlayHelloWorldPage, Scope.Page, "/example/comparison/play_hello_world.html")
  }
  val todoMVC = page(TodoMVC, Scope.Session, "/todomvc.html")

  addClassPath("/images/", "images/")
  addClassPath("/css/", "css/")
  addClassPath("/js/", "js/")

  protected def createSession(request: HttpRequest, id: String) = new MapSession(id)
}
