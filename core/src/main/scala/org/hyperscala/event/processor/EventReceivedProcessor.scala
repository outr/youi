package org.hyperscala.event.processor

import org.json4s.JObject
import org.json4s.JsonAST.JString
import org.powerscala.event.processor.InterceptProcessor
import org.powerscala.event.Listenable
import org.hyperscala.event.ClientEvent
import org.powerscala.json.JSONClassMap
import org.powerscala.json.convert.{CaseClassSupport, JSONConverter}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class EventReceivedProcessor(implicit listenable: Listenable) extends InterceptProcessor[ClientEvent]("eventReceived") {
  EventReceivedProcessor      // Make sure it is initialized
}

object EventReceivedProcessor extends JSONConverter[ClientEvent, JObject] {
  private var classMapping = Map.empty[String, Class[_]]
  private var nameMapping = Map.empty[Class[_], String]

  JSONClassMap.register(this)

  override def toJSON(v: ClientEvent) = {
    val o = CaseClassSupport.toJSON(v)
    o.copy("eventType" -> JString(nameMapping(v.getClass)) :: o.obj)
  }

  override def fromJSON(v: JObject) = {
    val eventType = v.values("eventType").asInstanceOf[String]
    val clazz = classMapping(eventType)
    CaseClassSupport.converter(clazz).fromJSON(v).asInstanceOf[ClientEvent]
  }

  def register[E <: ClientEvent](implicit manifest: Manifest[E]): Unit = register(manifest.runtimeClass.getSimpleName)(manifest)

  def register[E <: ClientEvent](name: String)(implicit manifest: Manifest[E]): Unit = synchronized {
    classMapping += name -> manifest.runtimeClass
    nameMapping += manifest.runtimeClass -> name
    JSONClassMap.alias(this, manifest.runtimeClass)
  }
}