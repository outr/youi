package org.hyperscala.numberguess

import org.hyperscala.web.BasicWebsite

/**
 * NumberGuessSite is the entry point for several implementations of NumberGuess to show the different ways an
 * application can be written in Hyperscala.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object NumberGuessSite extends BasicWebsite with DynamicWebsite {
  def numberGuessServer = new NumberGuessServerPage
}