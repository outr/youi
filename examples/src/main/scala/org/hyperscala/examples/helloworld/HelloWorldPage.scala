package org.hyperscala.examples.helloworld

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HelloWorldPage extends Webpage with Example {
  body.contents += "Hello World!"
}