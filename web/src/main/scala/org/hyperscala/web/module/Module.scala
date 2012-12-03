package org.hyperscala.web.module

import org.hyperscala.web.site.Webpage
import org.powerscala.Version

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Module extends Interface {
  def name: String
  def version: Version
  def implements: List[Interface] = Nil
  def dependencies: List[Interface] = Nil
  def load(page: Webpage): Unit
}