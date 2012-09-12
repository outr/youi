package org.hyperscala.web.resource.handler

import java.net.URL
import org.hyperscala.web.resource.URLWebResource
import org.powerscala.Priority

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait URLWebResourceManager extends WebResourceManager {
  def isMatch(uri: String) = {
    val modified = modifyURI(uri)
    if (accept(modified)) {
      lookup(modified) != null
    } else {
      false
    }
  }

  def lookup(uri: String): URL

  def modifyURI(uri: String) = uri.substring(1)

  def create(uri: String) = createWebResource(lookup(modifyURI(uri)))

  def createWebResource(url: URL) = new URLWebResource(url)

  def accept(uri: String) = true

  override def priority = Priority.Low
}
