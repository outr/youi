package org.hyperscala.site

import org.hyperscala.html._
import org.powerscala.reflect._
import org.hyperscala.web.WebpageHandler

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HyperscalaExamples extends HyperscalaPage {
  main.contents += new tag.H1(content = "Hyperscala Examples")
  main.contents += new tag.Ul {
    examples(null, site.examples).sortBy {
      case (n, r) => n
    }.foreach {
      case (n, r) => contents += new tag.Li {
        contents += new tag.A(href = r.link, content = n)
      }
    }
  }

  def examples(pre: String = null, o: AnyRef): List[(String, WebpageHandler)] = {
    o.getClass.methods.collect {
      case m if m.returnType.`type`.hasType(classOf[WebpageHandler]) => List((name(pre, m), m.invoke[WebpageHandler](o)))
      case m if m.name == "svg" => examples("SVG ", m.invoke[AnyRef](o))
    }.flatten
  }

  def name(pre: String, m: EnhancedMethod) = {
    val n = if (pre != null) {
      "%s%s".format(pre, m.name)
    } else {
      m.name
    }
    CaseValue.generateLabel(n)
  }
}
