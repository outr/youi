//package io.youi.persist
//
//import fabric.rw._
//
//trait Persistence[T] {
//  protected def identifier: String
//  protected def parentPersistence: Option[Persistence[_ >: T]]
//  protected def create(): T
//  protected def saveInfo(t: T): Json
//  protected def loadInfo(t: T, json: Json): Unit
//
//  def save(t: T): Json = {
//    val identifierJson = Json.obj("identifier" -> Json.string(identifier))
//    val parentJson = parentPersistence.map(_.save(t)).getOrElse(Json.obj())
//    val json = saveInfo(t)
//
//    parentJson.merge(identifierJson)
//    parentJson.deepMerge(identifierJson).deepMerge(json)
//  }
//
//  def load(json: Json): T = {
//    val t: T = create()
//    parentPersistence.foreach(_.loadInfo(t, json))
//    loadInfo(t, json)
//    t
//  }
//}