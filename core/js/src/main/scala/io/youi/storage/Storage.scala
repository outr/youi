package io.youi.storage

import reactify.Var

import scala.concurrent.Future
import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import scribe.Execution.global

trait Storage {
  def implementation: StorageImplementation

  object string {
    def get(key: String): Future[Option[String]] = implementation.get(key)
    def getOrElse(key: String, default: => String): Future[String] = get(key).map(_.getOrElse(default))
    def update(key: String, value: String): Future[Unit] = implementation.set(key, value)
    def remove(key: String): Future[Unit] = implementation.remove(key)
    def prop(key: String, default: => String): Var[String] = {
      val p = Var(default)
      get(key).foreach(_.foreach(p.static))
      p.attach { v =>
        update(key, v)
      }
      p
    }
  }

  def get[T](key: String): Future[Option[T]] = macro Storage.macroGet[T]
  def getOrElse[T](key: String, default: => T): Future[T] = macro Storage.macroGetOrElse[T]
  def getOrCreate[T](key: String, default: => T): Future[T] = macro Storage.macroGetOrCreate[T]
  def update[T](key: String, value: T): Future[Unit] = macro Storage.macroUpdate[T]
  def remove(key: String): Future[Unit] = string.remove(key)

  def prop[T](key: String, default: => T): Var[T] = macro Storage.macroProp[T]
  def connect[T](key: String, prop: Var[T]): Future[Option[T]] = macro Storage.macroConnect[T]
}



object Storage {
  def macroGet[T](c: blackbox.Context)(key: c.Expr[String])(t: c.WeakTypeTag[T]): c.Expr[Future[Option[T]]] = {
    import c.universe._

    val prefix = c.prefix.tree

    c.Expr[Future[Option[T]]](
      q"""
          import fabric.rw._

          $prefix.string.get($key).map { o =>
            o.flatMap(s => JsonUtil.fromJsonString[Option[$t]](s))
          }.recover { t =>
            scribe.info("Error retrieving " + $key + " from Storage", t)
            None
          }
       """)
  }

  def macroGetOrElse[T](c: blackbox.Context)(key: c.Expr[String], default: c.Tree)(t: c.WeakTypeTag[T]): c.Expr[Future[T]] = {
    import c.universe._

    val prefix = c.prefix.tree

    c.Expr[Future[T]](
      q"""
          import fabric.rw._

          $prefix.string.getOrElse($key, JsonUtil.toJsonString[$t]($default)).map { s =>
            JsonUtil.fromJsonString[Option[$t]](s).getOrElse($default)
          }
       """)
  }

  def macroGetOrCreate[T](c: blackbox.Context)(key: c.Expr[String], default: c.Tree)(t: c.WeakTypeTag[T]): c.Expr[Future[T]] = {
    import c.universe._

    val prefix = c.prefix.tree

    c.Expr[Future[T]](
      q"""
          import fabric.rw._

          def create(): Future[$t] = {
            val d = $default
            val s = JsonUtil.toJsonString[$t](d)
            $prefix.string.update($key, s).map { _ =>
              d
            }
          }

          $prefix.string.get($key).flatMap {
            case Some(s) => try {
              Future.successful(JsonUtil.fromJsonString[$t](s))
            } catch {
              case t: Throwable => {
                scribe.warn("Error loading " + $key + ": " + t.getMessage)
                create()
              }
            }
            case None => create()
          }
       """)
  }

  def macroUpdate[T](c: blackbox.Context)(key: c.Expr[String], value: c.Expr[T])(t: c.WeakTypeTag[T]): c.Expr[Future[Unit]] = {
    import c.universe._

    val prefix = c.prefix.tree

    c.Expr[Future[Unit]](
      q"""
          import fabric.rw._

          val json = JsonUtil.toJsonString[$t]($value)
          $prefix.string.update($key, json)
       """
    )
  }

  def macroProp[T](c: blackbox.Context)(key: c.Expr[String],
                                        default: c.Tree)(t: c.WeakTypeTag[T]): c.Expr[Var[T]] = {
    import c.universe._

    val prefix = c.prefix.tree

    c.Expr[Var[T]](
      q"""
          import _root_.reactify._

          val v = Var[$t]($default)
          val future = $prefix.getOrElse[$t]($key, $default)
          future.foreach(v.static)
          future.failed.foreach { t =>
            _root_.scribe.warn(t.getMessage + " Failure to load value for properly. Resetting to default value.")
            $prefix.remove($key)
          }
          v.attach { value =>
            $prefix.update($key, value)
          }
          v
       """
    )
  }

  def macroConnect[T](c: blackbox.Context)
                     (key: c.Expr[String], prop: c.Expr[Var[T]])
                     (t: c.WeakTypeTag[T]): c.Expr[Future[Option[T]]] = {
    import c.universe._

    val prefix = c.prefix.tree

    val value = macroGet[T](c)(key)(t)
    c.Expr[Future[Option[T]]](
      q"""
            import fabric.rw._

            $value.map { o =>
              o.foreach { t =>
                $prop @= t
              }
              $prop.attach { t =>
                val json = JsonUtil.toJsonString[$t](t)
                $prefix.string.update($key, json)
              }
              o
            }
         """
    )
  }
}