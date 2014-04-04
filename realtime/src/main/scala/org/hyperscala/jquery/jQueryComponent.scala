package org.hyperscala.jquery

import org.hyperscala.html.HTMLTag
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.event.Intercept
import org.hyperscala.event.EventReceived
import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime.Realtime
import org.hyperscala.javascript.dsl.Statement
import org.hyperscala.web._
import com.outr.net.http.session.Session

/**
 * jQueryComponent trait works to provide easier access to making calls to jQuery for extensions like autocomplete and
 * other jQuery plugins.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait jQueryComponent extends WrappedComponent[HTMLTag] {
  lazy val selector = $(wrapped)

  protected def functionName: String

  private var backlog = List.empty[Statement[_]]
  private var webpage: Webpage[Session] = _
  wrapped.connected[Webpage[Session]] {
    case w => synchronized {
      backlog.reverse.foreach {
        case s => Realtime.send(w, s, Some(selector.selector))
      }
      webpage = w
      backlog = Nil
    }
  }

  private def send(statement: Statement[_]) = synchronized {
    if (webpage != null) {
      Realtime.send(webpage, statement, Some(selector.selector))
    } else {
      backlog = statement :: backlog
    }
  }

  protected def initializeComponent(values: Map[String, Any]) = {
    send(selector.call(functionName, values))
  }

  protected def modify(key: String, value: Any) = {
    send(selector.option(functionName, key, value))
  }

  def call(function: String) = {
    send(selector.call(s"$functionName('$function')"))
  }

  def call(function: String, arg: Any) = {
    val argValue = JavaScriptContent.toJS(arg)
    send(selector.call(s"$functionName('$function', $argValue)"))
  }

  def on(eventType: String, function: JavaScriptContent) = {
    if (wrapped.rendered) {
      send(selector.on(eventType, function))
    } else {
      option(eventType, function)
    }
  }

  def event(eventType: String): UnitProcessor[jQueryEvent] = event(eventType, jQueryEvent.EmptyMapper)

  def event[Event](eventType: String, mapper: JSMapper[Event])(implicit manifest: Manifest[Event]) = {
    val localizedEventType = s"$eventType.${getClass.getSimpleName}"
    val b = new StringBuilder
    b.append("function(")
    b.append(mapper.variables.mkString(", "))
    b.append(") {\r\n")
    b.append("\tvar id = $(this).attr('id');\r\n")
    b.append(s"\trealtimeSend(id, '$localizedEventType', ${mapper.variables2JSON.content});\r\n")
    b.append("}")
    on(eventType, JavaScriptString(b.toString()))
    val processor = new UnitProcessor[Event](localizedEventType)(wrapped, manifest)
    wrapped.eventReceived.on {
      case evt if evt.event == localizedEventType => {
        processor.fire(mapper.converter(evt))
        Intercept.Stop
      }
      case _ => Intercept.Continue
    }
    processor
  }
}

class jQueryEvent

object jQueryEvent extends jQueryEvent {
  val EmptyMapper = JSMapper[jQueryEvent](Nil, JavaScriptString("{}"), (evt: EventReceived) => jQueryEvent)
}

case class JSMapper[Event](variables: List[String], variables2JSON: JavaScriptContent, converter: EventReceived => Event)