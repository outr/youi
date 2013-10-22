package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import org.hyperscala.web.Webpage
import org.hyperscala.ui.module.DynamicURL

//import org.powerscala.convert.string._
import language.reflectiveCalls
import language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DynamicURLExample extends Example {
  Webpage().require(DynamicURL)

  implicit def i2s(i: Int) = i.toString
  implicit def s2i(s: String) = try {
    s.toInt
  } catch {
    case t: Throwable => 0
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
    println("Test: %s, Num: %s".format(test(), num()))
  }
}