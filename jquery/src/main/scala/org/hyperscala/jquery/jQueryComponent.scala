package org.hyperscala.jquery

import org.hyperscala.html.HTMLTag
import org.powerscala.property.Property
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.event.{Listenable, ListenMode, Intercept}
import org.hyperscala.web.WrappedComponent

/**
 * jQueryComponent trait works to provide easier access to making calls to jQuery for extensions like autocomplete and
 * other jQuery plugins.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait jQueryComponent extends WrappedComponent[HTMLTag] {
  protected def functionName: String

  protected def initializeComponent(values: Map[String, Any]) = {
    jQuery.call(wrapped, functionName, values)
  }

  protected def modify(key: String, value: Any) = {
    jQuery.option(wrapped, functionName, key, value)
  }

  def call(function: String) = {
    jQuery.call(wrapped, s"$functionName('$function')")
  }

  def on(eventType: String, function: JavaScriptContent) = {
    if (wrapped.rendered) {
      jQuery.on(wrapped, eventType, function)
    } else {
      option(eventType, function)
    }
  }

  def event(eventType: String) = {
    val localizedEventType = s"$eventType.${getClass.getSimpleName}"
    on(eventType, JavaScriptString(
      s"""function() {
        | var id = $$(this).attr('id');
        | communicator.send('$localizedEventType', id, {});
        |}
      """.stripMargin))
    val processor = new UnitProcessor[jQueryEvent](localizedEventType)(wrapped, implicitly[Manifest[jQueryEvent]])
    wrapped.eventReceived.on {
      case evt if evt.event == localizedEventType => {
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