package specs

import io.youi.Template
import org.scalajs.dom.html
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TemplateSpec extends AnyWordSpec with Matchers {
  "Template" should {
    "load from id" in {
      val div = Template.byId[html.Div]("template.html", "hello", "youi")
      div.innerHTML should be("Hello, World!")
    }
  }
}