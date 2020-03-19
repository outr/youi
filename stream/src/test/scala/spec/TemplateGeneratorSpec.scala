package spec

import io.youi.stream.{HTMLParser, Selector}
import io.youi.stream.delta.Delta
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class TemplateGeneratorSpec extends AnyWordSpec with Matchers {
  "TemplateGenerator" should {
    "generate source from a simple HTML file" in {
      val html = HTMLParser.cache(
        """<html>
          |<head>
          |</head>
          |<body>
          |<h1 data-youi="object" id="heading">Heading</h1>
          |<ul>
          | <li data-youi="class" data-youi-class="listItem" id="entry">Entry</li>
          |</ul>
          |</body>
          |</html>""".stripMargin)
      val result = html.stream(deltas = List(
        Delta.Process(
          selector = Selector.HasAtribute("data-youi"),
          replace = true,
          onlyOpenTag = false,
          processor = (tag, content) => {
            val `type` = tag.attributes("data-youi")
            val id = tag.attributes.getOrElse("id", throw new RuntimeException(s"No id defined for: $content"))
            val className = tag.attributes.getOrElse("data-youi-class", id)
            println(s"Tag: $tag, Content: $content, Type: ${`type`}, Class: $className")
            "REPLACED"
          },
          closeTagProcessor = None
        )
      ))
      println(result)
    }
  }
}