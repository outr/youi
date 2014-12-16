package org.hyperscala.ui.widgets

import com.outr.net.http.session.{MapSession, Session}
import org.hyperscala.Container
import org.hyperscala.css.Style
import org.hyperscala.html._
import org.hyperscala.io.HTMLToScala
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.javascript.dsl.{document, Command, JSFunction1}
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.Module
import org.hyperscala.realtime.{RealtimePage, Realtime}
import org.hyperscala.selector.Selector
import org.hyperscala.ui.Rangy
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.concurrent.AtomicBoolean
import org.powerscala.event.{Intercept, Listenable}
import org.powerscala.property.Property
import org.powerscala.{Version, StorageComponent}
import org.hyperscala.web._
import org.hyperscala.realtime._
import org.hyperscala.realtime.dsl._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ContentEditor extends Module with StorageComponent[ContentEditor, HTMLTag] {
  /**
   * Provides a conversion strategy to better support numeric values in length styles. Numbers automatically get
   * amended with a 'px'.
   */
  val PixelConversion = JSFunction1[String, String]("""
                                                      |if (!isNaN(p1)) {
                                                      |  return p1 + 'px';
                                                      |}
                                                      |return p1;
                                                    """.stripMargin.trim)

  val name = "contentEditor"
  val version = Version(1)

  override def dependencies = List(jQuery, Realtime, Rangy)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/undo.js", "undo.js")
    website.register("/js/medium.js", "medium.js")
    website.register("/js/content_editor.js", "content_editor.js")
  }

  override def load[S <: Session](page: Webpage[S]) = {
    page.head.contents += new tag.Script(src = "/js/undo.js")
    page.head.contents += new tag.Script(src = "/js/medium.js")
    page.head.contents += new tag.Script(src = "/js/content_editor.js")
  }

  override def apply(t: HTMLTag) = {
    t.require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new ContentEditor(t)
}

class ContentEditor private(val container: HTMLTag) extends Listenable {
  private val clientChanging = new AtomicBoolean
  val content = Property(default = Some(container.outputChildrenString))
  val updateContainerOnChange = Property(default = Some(true))

  init()

  // Handle propagation of content changes to the container
  content.change.on {
    case evt => if (updateContainerOnChange()) {    // Make sure changes should propagate to container
      updateContainer(!clientChanging())            // Only propagate if the client isn't causing this event
    }
  }

  /**
   * Updates the container with the content property.
   *
   * @param propagate defines whether the change should propagate up to the client
   */
  def updateContainer(propagate: Boolean) = {
    if (!propagate) {
      RealtimePage.ignoreStructureChanges {
        HTMLToScala.replaceChildren(container.asInstanceOf[HTMLTag with Container[_ <: HTMLTag]], content())
      }
    } else {
      HTMLToScala.replaceChildren(container.asInstanceOf[HTMLTag with Container[_ <: HTMLTag]], content())
    }
  }

  private def init() = {
    // Initialize the container in the browser
    container.connected[Webpage[MapSession]] {
      case webpage => {
        Realtime.sendJavaScript(webpage, s"initContentEditor('${container.identity}')", selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }

    // Listen for content change events
    container.eventReceived.on {
      case evt => if (evt.event == "contentChanged") {
        clientChanging.attempt {
          val html = evt.json.string("html")
          content := html
        }
        Intercept.Stop
      } else {
        Intercept.Continue
      }
    }
  }

  def insert(t: HTMLTag) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        Realtime.sendJavaScript(webpage, s"insertIntoSelection('${container.identity}', content);", content = Some(t.outputString.trim))
      }
    }
  }

  def insertAround(t: HTMLTag with Container[_ <: HTMLTag]) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        Realtime.sendJavaScript(webpage, s"insertAroundSelection('${container.identity}', content);", content = Some(t.outputString.trim))
      }
    }
  }

  def insertBlock(t: HTMLTag with Container[_ <: HTMLTag]) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        Realtime.sendJavaScript(webpage, s"insertAroundBlock('${container.identity}', content);", content = Some(t.outputString.trim))
      }
    }
  }

  def removeFromBlock(tagName: String) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        Realtime.sendJavaScript(webpage, s"removeFromBlock('${container.identity}', '$tagName');")
      }
    }
  }

  def manipulateSelection(manipulator: JSFunction1[Array[tag.Span], Unit]) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js = s"manipulateSelection('${container.identity}', ${manipulator.content});"
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def adjustStyle[S](style: Style[S], adjuster: JSFunction1[S, S]) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js = s"adjustStyle('${container.identity}', '${style.name}', ${adjuster.content});"
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def adjustStyleSize[S](style: Style[S], adjustment: Int, min: Int = 0, max: Int = Int.MaxValue)(implicit manifest: Manifest[S]) = {
    val js =
      """
        |var regex = /(-?\d+[.]?\d*)(.*)/;
        |var result = regex.exec(p1);
        |var n = parseInt(result[1]);
        |var type = result[2];
        |var value = n + $adjustment
        |if (value < $min) {
        | value = $min
        |}
        |if (value > $max) {
        | value = $max
        |}
        |return value + type;
      """.stripMargin.replaceAll("[$]adjustment", adjustment.toString).replaceAll("[$]min", min.toString).replaceAll("[$]max", max.toString)
    adjustStyle[S](style, JSFunction1[S, S](js))
  }

  def toggleStyle[S](style: Style[S], values: Any*) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js = s"toggleStyle('${container.identity}', '${style.name}', [${values.map(JavaScriptContent.toJS).mkString(", ")}]);"
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def setStyle[S](style: Style[S], value: Any) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js = s"setStyle('${container.identity}', '${style.name}', ${JavaScriptContent.toJS(value)});"
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def bindInput[S](style: Style[S], input: tag.Input, format: Boolean, converter: JSFunction1[String, String], aliases: VisualAlias*) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        // Update the input when the selection changes
        val content = s"""{${aliases.map(va => s"'${va.visual}': '${va.internal}'").mkString(", ")}}"""
        val js = s"contentEditorBindInput('${input.identity}', '${container.identity}', '${style.name}', $format, content, ${JavaScriptContent.toJS(converter)});"
        Realtime.sendJavaScript(webpage, js, content = Some(content), selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def bindFontStyle(input: tag.Input) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js = s"contentEditorBindFontStyle('${input.identity}', '${container.identity}');"
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def bindToggle[S](style: Style[S], t: HTMLTag, values: Seq[Any], activeClass: Option[String] = None, inactiveClass: Option[String] = None, block: Boolean = false) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        // Toggle the style when the tag is clicked on
        t.clickEvent.onRealtime {
          case evt => if (block) {
            Realtime.sendJavaScript(webpage, s"toggleBlockStyle('${container.identity}', '${style.name}', [${values.map(JavaScriptContent.toJS).mkString(", ")}], null);")
          } else {
            Realtime.sendJavaScript(webpage, s"toggleStyle('${container.identity}', '${style.name}', [${values.map(JavaScriptContent.toJS).mkString(", ")}], null);")
          }
        }

        // Update the class when the style is set
        val js = s"contentEditorBindToggle('${t.identity}', '${container.identity}', '${style.name}', [${values.map(JavaScriptContent.toJS).mkString(", ")}], ${JavaScriptContent.toJS(activeClass.orNull)}, ${JavaScriptContent.toJS(inactiveClass.orNull)}, $block);"
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def clearFormatting() = container.connected[Webpage[MapSession]] {
    case webpage => Realtime.sendJavaScript(webpage, s"removeFormattingFromSelection('${container.identity}');", selector = Some(Selector.id(container)), onlyRealtime = false)
  }

  def exec(command: Command, value: String = null) = container.connected[Webpage[MapSession]] {
    case webpage => document.execCommand(command, value).send(webpage)
  }
}

case class VisualAlias(visual: String, internal: String)