package org.hyperscala.site.extra

import java.util.Collections

import org.eclipse.egit.github.core.service.GistService
import org.eclipse.egit.github.core.{Gist, GistFile}
import org.hyperscala.Tag
import org.hyperscala.site.HyperscalaPage
import org.hyperscala.html._
import org.hyperscala.realtime._
import org.hyperscala.io.HTMLToScala
import org.hyperscala.html.attributes.InputType
import com.outr.net.http.client.HttpClient
import com.outr.net.http.request.HttpRequest
import com.outr.net.{Method, URL}
import com.outr.net.http.content.{InputStreamContent, ContentType, StringContent}
import org.hyperscala.ui.BusyDialog
import org.powerscala.IO
import org.hyperscala.jquery.Gritter
import org.jdom2.input.JDOMParseException
import org.hyperscala.html.tag.Comment

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HyperscalaGenerator extends HyperscalaPage {
  require(Realtime)
  require(Gritter)
  require(BusyDialog)

  Tag.AutoCreate = true               // Auto-create attributes that don't already exist
  Realtime.connectStandard(this)

  val packageInput = new tag.Input(clazz = List("code_generator"))
  val classNameInput = new tag.Input(clazz = List("code_generator"))
  val text = new tag.TextArea(clazz = List("code_generator"))
  val createGist = new tag.Input(inputType = InputType.CheckBox, checked = true)
  val cleanHTML = new tag.Input(inputType = InputType.CheckBox, checked = false)
  val removeComments = new tag.Input(inputType = InputType.CheckBox, checked = true)
  val rootIdInput = new tag.Input(clazz = List("code_generator"))

  main.contents += new tag.H1(content = "Hyperscala Code Generator")
  main.contents += new tag.P {
    contents += "Generate Hyperscala code from a block of HTML."
  }

  main.contents += new tag.Label(clazz = List("code_generator")) {
    contents += "Package:"
    contents += packageInput
    contents += new tag.Em(content = "(Optional)")
  }
  main.contents += new tag.Label(clazz = List("code_generator")) {
    contents += "Class Name:"
    contents += classNameInput
    contents += new tag.Em(content = "(Optional)")
  }
  main.contents += text
  main.contents += new tag.Label(clazz = List("code_generator")) {
    contents += "Create Gist:"
    contents += createGist
  }
  main.contents += new tag.Label(clazz = List("code_generator")) {
    contents += "Clean HTML:"
    contents += cleanHTML
  }
  main.contents += new tag.Label(clazz = List("code_generator")) {
    contents += "Remove Comments:"
    contents += removeComments
  }
  main.contents += new tag.Label(clazz = List("code_generator")) {
    contents += "Extract ID as root:"
    contents += rootIdInput
    contents += new tag.Em(content = "(Optional)")
  }
  main.contents += new tag.Button(content = "Generate") {
    clickEvent.on {
      case evt => generate()
    }
  }

  def generate() = BusyDialog(this, "Generating...") {
    val source = text.value()
    if (source != null && source.nonEmpty) {
      val rootId = rootIdInput.value() match {
        case null | "" => null
        case id => id
      }
      val packageName = packageInput.value() match {
        case null | "" => null
        case s => s
      }
      val className = classNameInput.value() match {
        case null | "" => null
        case s => s
      }
      try {
        val html = HTMLToScala.toHTML(source, clean = cleanHTML.checked(), rootId = rootId)
        if (removeComments.checked()) {
          html.byTag[Comment].foreach(_.removeFromParent())
        }
        val scala = if (packageName != null && className != null) {
          HTMLToScala.toScala(html, packageName, className)
        } else {
          HTMLToScala.toScala(html)
        }
        if (createGist.checked()) {
          val filename = if (className != null) {
            className
          } else {
            "hyperscala_generated"
          }
          val url = createGist(filename, scala)
          Realtime.sendRedirect(this, url)
        } else {
          text.value := scala
        }
      } catch {
        case exc: JDOMParseException => Gritter.add(this, "Parse Failure", "The content did not resolve to valid XHTML. The HTML content must be able to be loaded as XML. Try enabling 'Clean HTML' or make sure your HTML tags are properly terminating.")
        case t: Throwable => {
          Gritter.add(this, "Parse Failure", "Unable to parse the supplied content!")
          error("Unable to parse the supplied content!", t)
        }
      }
    }
  }

  def createGist(filename: String, content: String) = {
    val file = new GistFile
    file.setContent(content)
    val gist = new Gist
    gist.setPublic(true)
    gist.setDescription("Generated Hyperscala source code from HTML file posted to http://hyperscala.org/generator.html")
    gist.setFiles(Collections.singletonMap(s"$filename.scala", file))
    val service = new GistService
    service.createGist(gist).getHtmlUrl
  }

  override def sourceURL = "https://github.com/darkfrog26/hyperscala/blob/master/site/src/main/scala/org/hyperscala/site/extra/HyperscalaGenerator.scala"
}
