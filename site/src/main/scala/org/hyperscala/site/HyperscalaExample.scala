package org.hyperscala.site

import org.hyperscala.bootstrap.component.{Glyphicon, ButtonStyle, Button}
import org.hyperscala.html._
import org.hyperscala.examples.{ExamplePage, Example}
import org.hyperscala.javascript.dsl.window
import org.powerscala.reflect.CaseValue
import com.outr.net.http.session.MapSession

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class HyperscalaExample(f: => Example) extends HyperscalaPage with ExamplePage[MapSession] {
  lazy val example: Example = f
  main.contents += new tag.Div {
    contents += new tag.H2(content = CaseValue.generateLabel(example.exampleName))
  }
  main.contents += example
  main.contents += new tag.P {
    style.marginTop := 15.px

    contents += new Button(" Back to Examples", ButtonStyle.Default) {
      contents.insert(0, Glyphicon.ArrowLeft.create())

      clickEvent := window.open(HyperscalaSite.siteExamples.link)
    }
  }

  override def sourceURL = example.sourceURL
}
