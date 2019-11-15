package io.youi.storage

import reactify.Var

import scala.concurrent.Future
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

trait Storage {
  protected def storage: org.scalajs.dom.raw.Storage

  object string {
    def get(key: String): Option[String] = Option(storage.getItem(key))
    def update(key: String, value: String): Unit = storage.setItem(key, value)
    def remove(key: String): Unit = storage.removeItem(key)
    def prop(key: String, default: => String): Var[String] = {
      val p = Var[String](get(key).getOrElse(default))
      p.attach { value =>
        update(key, value)
      }
      p
    }
  }

  def get[T](key: String): Option[T] = macro Storage.macroGet[T]
  def update[T](key: String, value: T): Unit = macro Storage.macroUpdate[T]
  def remove(key: String): Unit = string.remove(key)

  def prop[T](key: String, default: => T): Var[T] =  macro Storage.macroProp[T]
}

object Storage {
  def macroGet[T](c: blackbox.Context)(key: c.Expr[String])(t: c.WeakTypeTag[T]): c.Expr[Option[T]] = {
    import c.universe._

    c.Expr[Option[T]](
      q"""
         io.youi.storage.LocalStorage.string.get($key).flatMap { s =>
           profig.JsonUtil.fromJsonString[Option[$t]](s)
         }
       """)
  }

  def macroUpdate[T](c: blackbox.Context)(key: c.Expr[String], value: c.Expr[T])(t: c.WeakTypeTag[T]): c.Expr[Unit] = {
    import c.universe._

    c.Expr[Unit](
      q"""
         val json = profig.JsonUtil.toJsonString[$t]($value)
         io.youi.storage.LocalStorage.string.update($key, json)
       """
    )
  }

  def macroProp[T](c: blackbox.Context)(key: c.Expr[String],
                                        default: c.Tree)(t: c.WeakTypeTag[T]): c.Expr[Var[T]] = {
    import c.universe._

    c.Expr[Var[T]](
      q"""
         val initial = try {
           _root_.io.youi.storage.LocalStorage.get[$t]($key).getOrElse($default)
         } catch {
           case t: Throwable => {
             _root_.scribe.warn(t.getMessage + " Deleting stored data and resetting to default value.")
             _root_.io.youi.storage.LocalStorage.remove($key)
             $default
           }
         }
         val p = reactify.Var[$t](initial)
         p.attach { value =>
           io.youi.storage.LocalStorage.update($key, value)
         }
         p
       """
    )
  }

  def universalProp[T](c: blackbox.Context)
                      (key: c.Expr[String], v: c.Expr[Var[T]])
                      (t: c.WeakTypeTag[T]): c.Expr[Future[Unit]] = {
    import c.universe._

    c.Expr[Future[Unit]](
      q"""
        import _root_.io.youi.storage._
        import _root_.profig._

        UniversalStorage.get($key).map { result =>
          result match {
            case Some(value) => try {
              $v @= JsonUtil.fromJsonString[$t](value)
            } catch {
              case t: Throwable => scribe.warn("JSON incompatibility while attempting to load " + $key, t)
            }
            case None => // No current value
          }
          $v.attach { value =>
            UniversalStorage.set($key, JsonUtil.toJsonString[$t](value))
          }
        }
       """)
  }
}