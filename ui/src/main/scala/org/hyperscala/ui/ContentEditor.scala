package org.hyperscala.ui

import com.outr.net.http.session.{MapSession, Session}
import org.hyperscala.Container
import org.hyperscala.css.Style
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.html.{HTMLTag, tag}
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.javascript.dsl.JSFunction1
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.Module
import org.hyperscala.realtime._
import org.hyperscala.selector.Selector
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ContentEditor extends Module {
  val name = "contentEditor"
  val version = Version(1)

  override def dependencies = List(jQuery.LatestWithDefault, Realtime, Rangy)

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
}

class ContentEditor[T <: HTMLTag](val container: T) {
  val editable = Property(default = Some(true))

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

  def adjustStyle[S](style: Style[S], adjuster: JSFunction1[S, S]) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js =
          s"""
            |(function() {
            | adjustStyle('${container.identity}', '${style.name}', ${adjuster.content});
            |})();
          """.stripMargin
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def adjustStyleSize[S](style: Style[S], adjustment: Int)(implicit manifest: Manifest[S]) = {
    val js =
      """
        |var regex = /(\d+)(.*)/;
        |var result = regex.exec(p1);
        |var n = parseInt(result[1]);
        |var type = result[2];
        |return (n + $adjustment) + type;
      """.stripMargin.replaceAll("[$]adjustment", adjustment.toString)
    adjustStyle[S](style, JSFunction1[S, S](js))
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

  def bindToggle[S](style: Style[S], t: HTMLTag, values: Seq[Any], activeClass: Option[String] = None, inactiveClass: Option[String] = None) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        // Toggle the style when the tag is clicked on
        t.clickEvent.onRealtime {
          case evt => Realtime.sendJavaScript(webpage, s"toggleStyle('${container.identity}', '${style.name}', [${values.map(JavaScriptContent.toJS).mkString(", ")}], null);")
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
            |  if (selectionHasStyle('${style.name}', styleValues)) {
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

  updateEditable()

  private def updateEditable() = {
    val value = if (editable()) ContentEditable.True else ContentEditable.False
    container.contentEditable := value
  }
}