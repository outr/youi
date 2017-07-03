package io.youi.config

import java.util.concurrent.atomic.AtomicBoolean

import io.circe.Json

import scala.collection.JavaConverters._

object Config {
  private val initialized = new AtomicBoolean(false)
  private var json: Json = Json.obj()

  def init(args: Seq[String],
           loadEnvironment: Boolean = true,
           loadProperties: Boolean = true): Unit = if (initialized.compareAndSet(false, true)) {
    val env = if (loadEnvironment) {
      ConfigUtil.map2Json(System.getenv().asScala.toMap)
    } else {
      Json.obj()
    }
    val props = if (loadProperties) {
      ConfigUtil.properties2Json(System.getProperties)
    } else {
      Json.obj()
    }
    val argsJson = ConfigUtil.args2Json(args)
    env.deepMerge(props).deepMerge(argsJson)
  }

//  def as[T](path: String*)(implicit decoder: Decoder[T]): T = {
//
//  }

  def merge(json: Json, path: String*): Unit = synchronized {
    if (path.nonEmpty) {
      val updated = ConfigUtil.createJson(path.mkString("."), json)
      this.json = this.json.deepMerge(updated)
    } else {
      this.json = this.json.deepMerge(json)
    }
  }
}