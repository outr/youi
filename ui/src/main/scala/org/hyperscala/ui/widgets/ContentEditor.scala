package org.hyperscala.ui.widgets

import com.outr.net.http.session.{MapSession, Session}
import org.hyperscala.Container
import org.hyperscala.css.Style
import org.hyperscala.html._
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.io.HTMLToScala
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.javascript.dsl.{Command, JSFunction1, document}
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.Module
import org.hyperscala.realtime._
import org.hyperscala.realtime.dsl._
import org.hyperscala.selector.Selector
import org.hyperscala.ui.Rangy
import org.hyperscala.web._
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.event.{Intercept, Listenable}
import org.powerscala.property.Property
import org.powerscala.{StorageComponent, Version}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ContentEditor extends Module with StorageComponent[ContentEditor, HTMLTag] {
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
  val editable = Property(default = Some(true))
  val contentChanged = new UnitProcessor[String]("contentChanged")

  init()

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
        val content = evt.json.string("html")
        RealtimePage.ignoreStructureChanges {
          HTMLToScala.replaceChildren(container.asInstanceOf[HTMLTag with Container[_ <: HTMLTag]], content)
        }
        contentChanged.fire(content)
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

  def bindInput[S](style: Style[S], input: tag.Input) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        // Update the input when the selection changes
        val js =
          s"""
            |(function() {
            | var input = window.parent.document.getElementById('${input.identity}');
            |
            | addSelectionStyleChangeListener(document.getElementById('${container.identity}'), '${style.name}', function(oldValue, newValue) {
            |   input.value = newValue;
            | });
            |})();
          """.stripMargin
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)

        // Update the selection style when the input value changes
        input.changeEvent.onRealtime {
          case evt => Realtime.sendJavaScript(webpage, s"setStyle('${container.identity}', '${style.name}', ${JavaScriptContent.toJS(input.value())});")
        }
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
        val js =
          s"""
            |(function() {
            | var tag = $$(window.parent.document.getElementById('${t.identity}'));
            | var styleValues = [${values.map(JavaScriptContent.toJS).mkString(", ")}];
            | var activeClass = ${JavaScriptContent.toJS(activeClass.orNull)};
            | var inactiveClass = ${JavaScriptContent.toJS(inactiveClass.orNull)};
            |
            | addSelectionStyleChangeListener(document.getElementById('${container.identity}'), '${style.name}', function(oldValue, newValue) {
            |  if (${if (block) "selectionBlockHasStyle" else "selectionHasStyle"}('${style.name}', styleValues)) {
            |    if (inactiveClass != null) {
            |      tag.removeClass(inactiveClass);
            |    }
            |    if (activeClass != null) {
            |      tag.addClass(activeClass);
            |    }
            |  } else {
            |    if (activeClass != null) {
            |      tag.removeClass(activeClass);
            |    }
            |    if (inactiveClass != null) {
            |      tag.addClass(inactiveClass);
            |    }
            |  }
            | });
            |})();
          """.stripMargin
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

  updateEditable()

  private def updateEditable() = {
    val value = if (editable()) ContentEditable.True else ContentEditable.False
    container.contentEditable := value
  }
}