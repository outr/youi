package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import org.hyperscala.ui.module.DynamicURL
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.jquery.Gritter
import org.hyperscala.web._

import language.reflectiveCalls
import language.implicitConversions
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DynamicURLExample extends Example {
  this.require(DynamicURL)
  this.require(Gritter)

  implicit def i2s(i: Int) = i.toString
  implicit def s2i(s: String) = try {
    s.toInt
  } catch {
    case t: Throwable => 0
  }

  contents += new tag.P {
    contents += "DynamicURL module allows a convenient mechanism to modify and monitor changes to hash values applied to a URL."
  }

  connected[Webpage[Session]] {
    case webpage => {
      val test = DynamicURL(webpage).property("test", "")
      val num = DynamicURL(webpage).property("num", 2)
      test.change.on {
        case evt => writeValues()
      }
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

      def writeValues() = Gritter.add(webpage, "Hash Changed", "Test: %s, Num: %s".format(test(), num()))
    }
  }

}