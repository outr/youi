package org.hyperscala.html

import org.hyperscala.Page

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait HTMLPage extends Page {
  def html: tag.HTML
  def head: tag.Head
  def body: tag.Body
}