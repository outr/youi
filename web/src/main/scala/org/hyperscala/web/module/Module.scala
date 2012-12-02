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
  // TODO: add def requires: List[Module] but allow for requiring Interfaces with defaults...
  def load(page: Webpage): Unit
}