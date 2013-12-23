package org.hyperscala.html

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CopyableHTMLTag[T <: HTMLTag](t: T) {
  /**
   * Creates a copy of this tag and returns it.
   *
   * @param idHandler how to handle ids in the copy (defaults to setting all ids to null).
   * @return copy of tag
   */
  def copy(idHandler: String => String = HTMLCloner.NullifyIdHandler) = HTMLCloner.clone(t, idHandler).asInstanceOf[T]
}