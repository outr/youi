package org.hyperscala.web.resource.handler

import java.net.URL
import org.hyperscala.web.resource.URLWebResource
import org.powerscala.Priority

/**
 * URLWebResourceManager is the base class for streaming of URLs back to the client.
 *
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

  /**
   * Looks up the URL by the supplied uri. This is called when matching as well as final lookup, so null is a valid
   * response here.
   */
  def lookup(uri: String): URL

  /**
   * Modifies the uri supplied for consuming in lookup.
   *
   * Defaults to strip the '/' off the front
   */
  def modifyURI(uri: String) = uri.substring(1)

  def create(uri: String) = createWebResource(lookup(modifyURI(uri)))

  def createWebResource(url: URL) = new URLWebResource(url)

  /**
   * Returns true if this supplied uri should be checked in this manager.
   *
   * Defaults to true
   */
  def accept(uri: String) = true

  /**
   * Overrides the default priority to set a Priority of Low.
   */
  override def priority = Priority.Low
}
