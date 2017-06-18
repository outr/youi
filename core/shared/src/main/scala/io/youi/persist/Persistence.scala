package io.youi.persist

import io.circe.Json

trait Persistence[T] {
  protected def identifier: String
  protected def parent: Option[Persistence[_ >: T]]
  protected def create(): T
  protected def saveInfo(t: T): Json
  protected def loadInfo(t: T, json: Json): Unit

  def save(t: T): Json = {
    val identifierJson = Json.obj("identifier" -> Json.fromString(identifier))
    val parentJson = parent.map(_.save(t)).getOrElse(Json.obj())
    val json = saveInfo(t)
    identifierJson.deepMerge(parentJson).deepMerge(json)
  }

  def load(json: Json): T = {
    val t: T = create()
    parent.foreach(_.loadInfo(t, json))
    loadInfo(t, json)
    t
  }
}