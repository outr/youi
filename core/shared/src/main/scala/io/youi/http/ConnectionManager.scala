package io.youi.http

trait ConnectionManager {
  def withConnection[R](connection: Connection)(f: => R): R = f
}

object ConnectionManager extends ConnectionManager