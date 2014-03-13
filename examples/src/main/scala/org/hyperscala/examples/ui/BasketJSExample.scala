package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.ui.BasketJS
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BasketJSExample extends Example {
  page.require(BasketJS)

  contents += new tag.P {
    contents += "BasketJS (http://addyosmani.github.io/basket.js/) provides the capability of caching JavaScript files in localstorage in the browser. This module wrapper ties into Hyperscala so all script tags added to Head are removed and referenced via BasketJS and all inline scripting occurs after those scripts have loaded successfully."
  }
}
