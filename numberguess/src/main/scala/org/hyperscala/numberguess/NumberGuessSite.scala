package org.hyperscala.numberguess

import org.hyperscala.web.{StaticWebsite, BasicWebsite}
import com.outr.net.http.jetty.JettyApplication
import com.outr.net.http.session.MapSession

/**
 * NumberGuessSite is the entry point for several implementations of NumberGuess to show the different ways an
 * application can be written in Hyperscala.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object NumberGuessSite extends BasicWebsite with StaticWebsite with JettyApplication {
  def numberGuessServer = new NumberGuessServerPage
}