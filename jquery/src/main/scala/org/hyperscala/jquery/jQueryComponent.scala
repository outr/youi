package org.hyperscala.jquery

import org.hyperscala.html.HTMLTag
import org.powerscala.property.Property
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.event.{Listenable, ListenMode, Intercept}

/**
 * jQueryComponent trait works to provide easier access to making calls to jQuery for extensions like autocomplete and
 * other jQuery plugins.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait jQueryComponent {
  protected def html: HTMLTag
  protected def functionName: String
  private var values = Map.empty[String, () => Any]

  html.onBeforeRender {
    jQuery.call(html, functionName, values.map(t => t._1 -> t._2()))
  }

  protected def property[T](name: String,
                            default: T,
                            includeDefault: Boolean = false,
                            toJS: T => JavaScriptContent = (t: T) => JavaScriptString(JavaScriptContent.toJS(t)))
                           (implicit manifest: Manifest[T]) = synchronized {
    val p = new jQueryProperty[T](toJS, default = Option(default))(html, manifest)
    p.change.on {
      case evt => propertyChanged[T](name, p)
    }
    if (includeDefault) {
      values += name -> p.js
    }
    p
  }

  protected def propertyChanged[T](name: String, property: jQueryProperty[T]) = {
    if (html.rendered) {
      jQuery.option(html, functionName, name, property.js())
    } else {
      values += name -> property.js
    }
  }

  def option(key: String, value: Any) = {
    if (html.rendered) {
      jQuery.option(html, functionName, key, value)
    } else {
      values += key -> (() => value)
    }
  }

  def call(function: String) = {
    jQuery.call(html, s"$functionName('$function')")
  }

  def on(eventType: String, function: JavaScriptContent) = {
    if (html.rendered) {
      jQuery.on(html, eventType, function)
    } else {
      values += eventType -> (() => function)
    }
  }

  def event(eventType: String) = {
    on(eventType, JavaScriptString(
      s"""function() {
        | var id = $$(this).attr('id');
        | communicator.send('$eventType', id, {});
        |}
      """.stripMargin))
    val processor = new UnitProcessor[jQueryEvent](eventType)(html, implicitly[Manifest[jQueryEvent]])
    html.eventReceived.on {
      case evt if evt.event == eventType => {
        processor.fire(jQueryEvent, ListenMode.Standard)

        Intercept.Stop
      }
      case _ => Intercept.Continue
    }
    processor
  }
}

class jQueryEvent

object jQueryEvent extends jQueryEvent

class jQueryProperty[T](toJS: T => JavaScriptContent, default: Option[T])
                       (implicit parent: Listenable, manifest: Manifest[T]) extends Property[T](default = default)(parent, manifest) {
  val js = () => toJS(value)
}