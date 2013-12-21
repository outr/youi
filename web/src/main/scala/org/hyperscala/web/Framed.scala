package org.hyperscala.web

import org.hyperscala.html._
import com.outr.net.http.HttpParameters
import org.powerscala.property.Property

/**
 * Framed extends tag.IFrame to allow instantiation of a page to be inlined and accessible directly.
 *
 * @author Matt Hicks <matt@outr.com>
 */
class Framed[P <: Webpage](wrapper: => P)(implicit manifest: Manifest[P]) extends tag.IFrame {
  val parentPage = Webpage()

  val wrapped = Property[P]()
  wrapped.change.on {
    case evt => {
      WebpageHandler.cachePage(evt.newValue)      // Make sure the page is cached for retrieval
      Webpage.updateContext(parentPage)           // Set the context back to the parent page
      src := url.toString()                       // Set the iframe's src to load the supplied page
    }
  }
  Website().contextualize(Website().requestContext) {
    wrapped := wrapper
  }

  def url = Website().request.url.copy(parameters = HttpParameters(Map("pageId" -> List(wrapped().pageId))))

  /**
   * Contextualizes the wrapped page. Interactions with the wrapped page should take place within this function to
   * allow proper page context.
   *
   * @param f the function to invoke within
   * @tparam T the return type
   * @return T
   */
  def contextualize[T](f: => T) = Webpage.contextualize(wrapped())(f)
}
