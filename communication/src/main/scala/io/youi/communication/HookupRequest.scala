package io.youi.communication

import io.circe.Json

import scala.concurrent.Promise

case class HookupRequest(id: Long, json: Json, promise: Promise[Json]) {
  def success(response: Json): Unit = promise.success(response)
  def failure(throwable: Throwable): Unit = promise.failure(throwable)
}