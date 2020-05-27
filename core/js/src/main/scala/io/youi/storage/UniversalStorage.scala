package io.youi.storage

import reactify.Var
import scribe.Execution.global

import scala.concurrent.{Future, Promise}
import scala.language.experimental.macros
import scala.util.Try

object UniversalStorage {
  private lazy val native: Option[NativeLocalStorage.type] = Try {
    val n = Some(NativeLocalStorage)
    scribe.info("Found NativeLocalStorage")
    n
  }.getOrElse(None)
  private lazy val forage: Option[LocalForage.type] = Try {
    val f = Some(LocalForage)
    scribe.info("Found LocalForage")
    f
  }.getOrElse(None)

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
      case None => forage match {
        case Some(s) => s.getItem(key).toFuture.map { s =>
          promise.success(Option(s))
        }
        case None => LocalStorage.string.get(key) match {
          case Some(value) => promise.success(Some(value))
          case None => promise.success(None)
        }
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
      case None => forage match {
        case Some(s) => s.setItem(key, value).toFuture.map(_ => value)
        case None => {
          LocalStorage.string(key) = value
          promise.success(value)
        }
      }
    }

    promise.future
  }

  def prop[T](key: String, v: Var[T]): Future[Unit] = macro Storage.universalProp[T]
}
