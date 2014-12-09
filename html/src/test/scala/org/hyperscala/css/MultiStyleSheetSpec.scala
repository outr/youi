package org.hyperscala.css

import org.powerscala.Color
import org.scalatest.{Matchers, WordSpec}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class MultiStyleSheetSpec extends WordSpec with Matchers {
  "MultiStyleSheet" should {
    val ss1 = new StyleSheet(null, null)
    val ss2 = new StyleSheet(null, null)
    val ss3 = new StyleSheet(null, null)
    val mss = MultiStyleSheet(ss1, ss2, ss3)

    "create successfully" in {
      mss.styleSheets.length should be(3)
    }
    "properly get values" in {
      ss1.color := Color.Red
      ss2.color := Color.Red
      ss3.color := Color.Red

      ss1.backgroundColor := Color.Red
      ss2.backgroundColor := Color.Green
      ss3.backgroundColor := Color.Blue

      mss(Style.color) should be(Some(Color.Red))
      mss(Style.backgroundColor) should be(None)
      mss(Style.top) should be(None)
    }
    "properly set values" in {
      mss.update(Style.backgroundColor, Color.Green)

      mss(Style.backgroundColor) should be(Some(Color.Green))
    }
  }
}
