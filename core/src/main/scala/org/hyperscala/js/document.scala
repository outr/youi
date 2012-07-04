package org.hyperscala.js

import org.hyperscala.WebContent

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object document {
  def getElementById[T <: WebContent](id: String)(implicit manifest: Manifest[T]) = {
    val ref = manifest.erasure.newInstance().asInstanceOf[T]
    ref.reference = Some("document.getElementById('%s')".format(id))
    ref
  }
}
