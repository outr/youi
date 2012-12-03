package org.hyperscala.web.module

/**
 * Interfaces exist as named modularity that are implemented by Modules and required by Webpages.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait Interface {
  def name: String
}