package org.hyperscala.site

import org.hyperscala.html._
import org.hyperscala.examples.{ExamplePage, Example}
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

  override def sourceURL = example.sourceURL
}
