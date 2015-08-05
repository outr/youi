package org.hyperscala.site

import org.hyperscala.bootstrap.component.{Button, ButtonStyle, Glyphicon}
import org.hyperscala.css.attributes.Clear
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.javascript.dsl.window
import org.hyperscala.module.Module
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version
import org.powerscala.reflect.CaseValue

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object HyperscalaExample extends Module {
  override val name = "HyperscalaExample"
  override val version = Version(2)

  override def init(website: Website) = {}

  override def load(webpage: Webpage) = {
    val items = webpage.body.contents.map { child =>
      child.removeFromParent()
      child
    }

    val (sourceURL, exampleName) = webpage match {
      case e: Example => (e.sourceURL, e.exampleName)
      case _ => (null, webpage.getClass.getSimpleName)
    }
    val (_, main) = HyperscalaPage.configure(webpage, sourceURL)

    main.contents += new tag.Div {
      contents += new tag.H2(content = CaseValue.generateLabel(exampleName))
    }
    main.contents += new tag.Hr
    items.foreach(main.contents += _)
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
  }
}
