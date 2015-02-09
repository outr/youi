package org.hyperscala.jquery

import org.hyperscala.event.BrowserEvent
import org.hyperscala.html.HTMLTag
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.hyperscala.javascript.dsl.Statement
import org.hyperscala.selector.Selector
import org.hyperscala.web.WrappedComponent

import org.hyperscala.jquery.dsl._
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.json.TypedSupport

/**
 * jQueryComponent trait works to provide easier access to making calls to jQuery for extensions like autoComplete and
 * other jQuery plugins.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait jQueryComponent extends WrappedComponent[HTMLTag] {
  lazy val tagSelector = $(wrapped)

  protected def functionName: String

  protected def realtimeSelector: Option[Selector] = Some(tagSelector.selector)

  private def send(statement: Statement[_]) = synchronized {
    if (initialized) {
      webpage.eval(statement, realtimeSelector.map(s => JavaScriptString(s"""$$(${s.content}).length > 0""")))
    } else {
      throw new RuntimeException("component not initialized!")
    }
  }

  protected def initializeComponent(values: Map[String, Any]) = {
    send(tagSelector.call(functionName, values))
  }

  protected def modify(key: String, value: Any) = {
    send(tagSelector.option(functionName, key, value))
  }

  def call() = if (initialized) {
    send(tagSelector.call(s"$functionName()"))
  }

  def call(function: String) = {
    send(tagSelector.call(s"$functionName('$function')"))
  }

  def call(function: String, arg: Any) = {
    val argValue = JavaScriptContent.toJS(arg)
    send(tagSelector.call(s"$functionName('$function', $argValue)"))
  }

  def on(eventType: String, function: JavaScriptContent) = {
    if (wrapped.rendered) {
      send(tagSelector.on(eventType, function))
    } else {
      option(eventType, function)
    }
  }

  def event(eventType: String): UnitProcessor[jQueryEvent] = {
    val js =
      s"""function() {
        | realtime.send({
        |   id: '${wrapped.identity}',
        |   type: 'jquery',
        |   eventType: 'jquery.$eventType'
        | });
        |}""".stripMargin
    on(eventType, JavaScriptString(js))
    val processor = new UnitProcessor[jQueryEvent](s"jquery.$eventType")(wrapped, implicitly[Manifest[jQueryEvent]])
    wrapped.handle[jQueryEvent]((evt: jQueryEvent) => processor.fire(evt), (evt: jQueryEvent) => evt.eventType == eventType)
    processor
  }

  def event[T <: BrowserEvent](mapping: MappedEvent[T]) = {
    val js =
      s"""function() {
        | realtime.send({
        |  id: '${wrapped.identity}',
        |  type: '${mapping.eventType}',
        |  ${mapping.mapping.map(t => s"${t._1}: ${t._2.content}").mkString(", ")}
        | });
        |}""".stripMargin
    on(mapping.eventType, JavaScriptString(js))
    val processor = new UnitProcessor[T](mapping.eventType)(wrapped, mapping.manifest)
    wrapped.handle[T]((evt: T) => processor.fire(evt))(mapping.manifest)
    processor
  }
}

case class jQueryEvent(tag: HTMLTag, eventType: String) extends BrowserEvent

class MappedEvent[T <: BrowserEvent](val mapping: Map[String, Statement[_]])(implicit val manifest: Manifest[T]) {
  val eventType = getClass.getName

  TypedSupport.register(eventType, manifest.runtimeClass)
}