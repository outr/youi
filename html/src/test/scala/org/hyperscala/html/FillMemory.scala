package org.hyperscala.html

import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
object FillMemory extends Logging {
  def main(args: Array[String]): Unit = {
    val start = System.currentTimeMillis()
    var counter = 0
    val buffer = new java.util.ArrayList[tag.A](200000)
    try {
      while (true) {
        buffer.add(new tag.A(name = "Tag%s".format(counter)))
        counter += 1
      }
    } catch {
      case exc: OutOfMemoryError => info("Ran out of memory!")
    }
    info("Finished in %s seconds with %s entries.".format((System.currentTimeMillis() - start) / 1000, counter))
  }
}
