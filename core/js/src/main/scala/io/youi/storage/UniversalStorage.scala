package io.youi.storage

import reactify.Var

import scala.concurrent.{Future, Promise}
import scribe.Execution.global

import scala.language.experimental.macros
import scala.util.Try

object UniversalStorage {
  private lazy val native: Option[NativeLocalStorage.type] = Try(Some(NativeLocalStorage)).getOrElse(None)

  def getOrCreate(key: String, default: => String): Future[String] = {
    get(key).flatMap {
      case Some(value) => Future.successful(value)
      case None => set(key, default)
    }
  }

  def get(key: String): Future[Option[String]] = {
    val promise = Promise[Option[String]]

    native match {
      case Some(s) => s.getItem(key, (value: String) => {
        promise.success(Some(value))
      }, (_: NativeStorageError) => {
        promise.success(None)
      })
      case None => LocalStorage.string.get(key) match {
        case Some(value) => promise.success(Some(value))
        case None => promise.success(None)
      }
    }

    promise.future
  }

  def set(key: String, value: String): Future[String] = {
    val promise = Promise[String]

    native match {
      case Some(s) => s.setItem(key, value, (result: String) => {
        promise.success(result)
      }, (error: NativeStorageError) => {
        promise.failure(new RuntimeException(s"Error using native storage: ${error.code}"))
      })
      case None => {
        LocalStorage.string(key) = value
        promise.success(value)
      }
    }

    promise.future
  }

  def prop[T](key: String, v: Var[T]): Future[Unit] = macro Storage.universalProp[T]
}
