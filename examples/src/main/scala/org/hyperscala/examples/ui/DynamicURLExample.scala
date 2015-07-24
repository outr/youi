package org.hyperscala.examples.ui

import com.outr.net.communicate.ConnectionHolder
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.ui.module.DynamicURL
import org.hyperscala.web._

import scala.language.{implicitConversions, reflectiveCalls}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DynamicURLExample extends Webpage with Example {
  ConnectionHolder.jsonEvent.on {
    case evt => println(s"ConnectionHolder received: $evt")
  }

  require(DynamicURL)
  require(Gritter)

  implicit def i2s(i: Int): String = i.toString
  implicit def s2i(s: String): Int = try {
    Integer.parseInt(s)
  } catch {
    case t: Throwable => 0
  }

  body.contents += new tag.P {
    contents += "DynamicURL module allows a convenient mechanism to modify and monitor changes to hash values applied to a URL."
  }

  val durl = DynamicURL(this)
  val test = durl.property("test", "")
  val num = durl.property("num", 2)
  test.change.on {
    case evt => {
      writeMessage(s"Test changed from ${evt.oldValue} to ${evt.newValue}")
    }
  }
  num.change.on {
    case evt => {
      writeMessage(s"Num changed from ${evt.oldValue} to ${evt.newValue}")
    }
  }

  body.contents += new tag.Button(content = "Set Hash") {
    clickEvent := RealtimeEvent()

    clickEvent.on {
      case evt => {
        test := "hello"
        num := 5
      }
    }
  }

  def writeMessage(message: String) = Gritter.add(this, "Hash Changed", message)
}