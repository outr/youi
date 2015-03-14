package org.hyperscala.site

import com.outr.net.http.session.MapSession
import org.hyperscala.bootstrap.component.{Button, ButtonStyle, Glyphicon}
import org.hyperscala.css.attributes.Clear
import org.hyperscala.examples.{Example, ExamplePage}
import org.hyperscala.html._
import org.hyperscala.javascript.dsl.window
import org.powerscala.reflect.CaseValue

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class HyperscalaExample(f: => Example) extends HyperscalaPage with ExamplePage {
  lazy val example: Example = f
  main.contents += new tag.Div {
    contents += new tag.H2(content = CaseValue.generateLabel(example.exampleName))
  }
  main.contents += new tag.Hr
  main.contents += example
  main.contents += new tag.Hr
  main.contents += new tag.P {
    style.marginTop := 15.px
    style.clear := Clear.Both

    contents += new Button(" Back to Examples", ButtonStyle.Info) {
      contents.insert(0, Glyphicon.ArrowLeft.create())
      clazz += "example_back"

      clickEvent := window.open(HyperscalaSite.siteExamples.link)
    }
  }

  override def sourceURL = example.sourceURL
}
