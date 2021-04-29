package specs

import testy._
import io.youi.Template
import org.scalajs.dom.html

class TemplateSpec extends Spec {
  "Template" should {
    "load from id" in {
      val div = Template.byId[html.Div]("template.html", "hello", "youi")
      div.innerHTML should be("Hello, World!")
    }
  }
}