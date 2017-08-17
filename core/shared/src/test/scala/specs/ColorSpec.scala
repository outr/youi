package specs

import io.youi.Color
import org.scalatest.{Matchers, WordSpec}

class ColorSpec extends WordSpec with Matchers {
  "Color" should {
    "properly parse from a Long" in {
      val color = Color.fromLong(0x11223344)
      color.redHex should be("11")
      color.greenHex should be("22")
      color.blueHex should be("33")
      color.alphaHex should be("44")
      color.redInt should be(17)
      color.greenInt should be(34)
      color.blueInt should be(51)
      color.alphaInt should be(68)
      color.red should be(0.06666666666666667)
      color.green should be(0.13333333333333333)
      color.blue should be(0.2)
      color.alpha should be(0.26666666666666666)
    }
    "properly parse from an 8-digit hex String" in {
      val color = Color.fromHex("#11223344")
      color.redHex should be("11")
      color.greenHex should be("22")
      color.blueHex should be("33")
      color.alphaHex should be("44")
      color.redInt should be(17)
      color.greenInt should be(34)
      color.blueInt should be(51)
      color.alphaInt should be(68)
      color.red should be(0.06666666666666667)
      color.green should be(0.13333333333333333)
      color.blue should be(0.2)
      color.alpha should be(0.26666666666666666)
    }
    "properly parse from an RGBA" in {
      val color = Color.fromRGBA(0.06666666666666667, 0.13333333333333333, 0.2, 0.26666666666666666)
      color.redHex should be("11")
      color.greenHex should be("22")
      color.blueHex should be("33")
      color.alphaHex should be("44")
      color.redInt should be(17)
      color.greenInt should be(34)
      color.blueInt should be(51)
      color.alphaInt should be(68)
      color.red should be(0.06666666666666667)
      color.green should be(0.13333333333333333)
      color.blue should be(0.2)
      color.alpha should be(0.26666666666666666)
    }
    "properly modify a Color" in {
      val color = Color.fromLong(0x11223344).withAlpha(1.0)
      color.redHex should be("11")
      color.greenHex should be("22")
      color.blueHex should be("33")
      color.alphaHex should be("ff")
      color.redInt should be(17)
      color.greenInt should be(34)
      color.blueInt should be(51)
      color.alphaInt should be(255)
      color.red should be(0.06666666666666667)
      color.green should be(0.13333333333333333)
      color.blue should be(0.2)
      color.alpha should be(1.0)
    }
    "output to RGBA" in {
      val color = Color.fromLong(0x11223344)
      color.toRGBA should be("rgba(17, 34, 51, 0.26666666666666666)")
    }
  }
}
