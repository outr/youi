package specs

import io.youi.Template
import org.scalatest.{Matchers, WordSpec}

import org.scalajs.dom.html

class TemplateSpec extends WordSpec with Matchers {
  "Template" should {
    "load from id" in {
      val div = Template.byId[html.Div]("template.html", "hello", "youi")
      div.innerHTML should be("Hello, World!")
    }
  }
}