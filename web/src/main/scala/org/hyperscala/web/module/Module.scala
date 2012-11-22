package org.hyperscala.web.module

import org.hyperscala.web.site.Webpage
import org.powerscala.Version

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Module {
  def name: String
  def version: Version
  def interfaces: List[Interface] = Nil
  def load(page: Webpage): Unit
}