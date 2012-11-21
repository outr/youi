package org.hyperscala.web.module

import org.hyperscala.web.site.Webpage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Module {
  def name: String
  def version: String
  def load(page: Webpage): Unit
}