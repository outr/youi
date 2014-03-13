package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import org.hyperscala.ui.module.DynamicURL
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.jquery.Gritter

//import org.powerscala.convert.string._
import language.reflectiveCalls
import language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DynamicURLExample extends Example {
  page.require(DynamicURL)
  page.require(Gritter)

  implicit def i2s(i: Int) = i.toString
  implicit def s2i(s: String) = try {
    s.toInt
  } catch {
    case t: Throwable => 0
  }

  contents += new tag.P {
    contents += "DynamicURL module allows a convenient mechanism to modify and monitor changes to hash values applied to a URL."
  }

  val test = DynamicURL().property("test", "")
  test.change.on {
    case evt => writeValues()
  }
  val num = DynamicURL().property("num", 2)
  num.change.on {
    case evt => writeValues()
  }

  contents += new tag.Button(content = "Set Hash") {
    clickEvent := RealtimeEvent()

    clickEvent.on {
      case evt => {
        test := "hello"
        num := 5
      }
    }
  }

  def writeValues() = {
    Gritter.add("Hash Changed", "Test: %s, Num: %s".format(test(), num()))
  }
}