package org.hyperscala

import org.hyperscala.html._
import org.hyperscala.io.{ScalaTagBuffer, WriterContext}
import org.hyperscala.io.bootstrap.Generation
import org.hyperscala.io.bootstrap.Specification._
import org.hyperscala.ui.dynamic.DynamicTag
import org.scalatest.{Matchers, WordSpec}

/**
 * @author Tim Nieradzik <tim@metastack.pl>
 */
class ScalaBufferSpec extends WordSpec with Matchers {
  val select =
    DynamicTag.from[tag.Select](
      """
        |<select>
        |  <option data-icon="fa fa-bars">a</option>
        |  <option data-icon="fa fa-bars">b</option>
        |</select>
      """.stripMargin).create()

  "Select" should {
    "be converted" in {
      val buffer = new ScalaTagBuffer(None, className = "Test", select)
      buffer.code.endsWith("""class Test extends tag.Select {
                             |  contents += new tag.Option {
                             |    data("icon", "fa fa-bars")
                             |    content := "a"
                             |  }
                             |  contents += new tag.Option {
                             |    data("icon", "fa fa-bars")
                             |    content := "b"
                             |  }
                             |
                             |}""".stripMargin) should be (true)
    }
  }
}
