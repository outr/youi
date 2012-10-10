package org.hyperscala.web.module

import org.hyperscala.web.HTMLPage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Module {
  def name: String
  def version: String
  protected[web] def load(page: HTMLPage): Unit
}