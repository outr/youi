package io.youi

import org.scalajs.dom._
import reactify.Var

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object LocalStorage {
  private lazy val storage = window.localStorage

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

  def get[T](key: String): Option[T] = macro macroGet[T]
  def update[T](key: String, value: T): Unit = macro macroUpdate[T]
  def remove(key: String): Unit = string.remove(key)

  def prop[T](key: String, default: => T): Var[T] =  macro macroProp[T]

  def macroGet[T](c: blackbox.Context)(key: c.Expr[String])(t: c.WeakTypeTag[T]): c.Expr[Option[T]] = {
    import c.universe._

    c.Expr[Option[T]](
      q"""
         io.youi.LocalStorage.string.get($key).flatMap { s =>
           profig.JsonUtil.fromJsonString[Option[$t]](s)
         }
       """)
  }

  def macroUpdate[T](c: blackbox.Context)(key: c.Expr[String], value: c.Expr[T])(t: c.WeakTypeTag[T]): c.Expr[Unit] = {
    import c.universe._

    c.Expr[Unit](
      q"""
         val json = profig.JsonUtil.toJsonString[$t]($value)
         io.youi.LocalStorage.string.update($key, json)
       """
    )
  }

  def macroProp[T](c: blackbox.Context)(key: c.Expr[String], default: c.Tree)(t: c.WeakTypeTag[T]): c.Expr[Var[T]] = {
    import c.universe._

    c.Expr[Var[T]](
      q"""
         val initial = io.youi.LocalStorage.get[$t]($key).getOrElse($default)
         val p = reactify.Var[T](initial)
         p.attach { value =>
           io.youi.LocalStorage.update($key, value)
         }
         p
       """
    )
  }
}