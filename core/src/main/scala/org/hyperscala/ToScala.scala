package org.hyperscala

/**
 * @author Tim Nieradzik <tim@kognit.io>
 */
trait ToScala {
  import scala.reflect.runtime.universe._
  def toScala: Tree
}
