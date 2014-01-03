package org.hyperscala.web

import java.net.URL
import org.powerscala.concurrent.{Time, AtomicInt, Executor}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object StressTester {
  def connect() = {
    val url = new URL("http://localhost:8080/privacy.html")
    val connection = url.openConnection()
    if (connection.getContentType != "text/html") {
      println("Unexpected content type: %s".format(connection.getContentType))
    }
  }

  def main(args: Array[String]): Unit = {
    val increment = new AtomicInt(0)
    (0 until 500).foreach {
      case index => Executor.invoke {
        println("connecting %s".format(index))
        connect()
        increment.addAndGet(1)
      }
    }
    Time.waitFor(Double.MaxValue) {
      increment.get() == 500
    }
  }
}
