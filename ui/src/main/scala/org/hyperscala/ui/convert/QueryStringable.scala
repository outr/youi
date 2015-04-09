package org.hyperscala.ui.convert

/**
 * Created by mmynsted on 4/10/15.
 * code@growingliberty.com
 */

trait QueryStringable[A] {
  def toQueryString(a: A): String
}

object QueryStringable {
  def toQueryString[A: QueryStringable](a: A) = implicitly [QueryStringable[A]].toQueryString(a)
}


