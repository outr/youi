package org.hyperscala.helloworld

import org.hyperscala.WebSite
import org.hyperscala.io.WebPageExporter

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object HelloWorldSite extends WebSite {
  val default = HelloWorldPage

  def main(args: Array[String]): Unit = {
    println(WebPageExporter(HelloWorldPage, "HelloWorldPage"))
  }
}
