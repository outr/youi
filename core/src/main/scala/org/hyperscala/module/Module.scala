package org.hyperscala.module

import org.powerscala.Version

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait Module extends Interface {
  def name: String
  def version: Version
  def implements: List[Interface] = Nil
  def dependencies: List[Interface] = Nil
  def load(): Unit
}
