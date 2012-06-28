package org.hyperscala.io

import io.Source
import xml.{Text, Elem, XML}
import org.htmlcleaner.{PrettyXmlSerializer, HtmlCleaner}
import org.hyperscala.{GenericAttribute, WebPage}
import org.hyperscala.tags.{Script, Tag}
import org.hyperscala.style.StyleSheet
import org.hyperscala.js.JavaScript
import org.hyperscala.tags.attributes.InputType

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object WebPageImporter {
  def apply(source: Source, filename: String) = {
    val xml = toXML(source)
    val importer = new WebPageImporter(xml, filename)
    importer.webPage
  }

  def toXML(source: Source) = {
    val html = source.mkString
    val cleaner = new HtmlCleaner()
    val props = cleaner.getProperties
    val cleaned = cleaner.clean(html)
    val content = new PrettyXmlSerializer(props).getAsString(cleaned)
    XML.loadString(content)
  }

  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("Z:\\clients\\ProjectSpeaker\\backup\\2012.06.25 linode server\\html\\profile.html")
    val webPage = WebPageImporter(source, "test.html")
    println(WebPageExporter(webPage, "TestImportExport"))
  }
}

private class WebPageImporter(html: Elem, filename: String) {
  val webPage = new WebPage(filename)

  // Process head
  (html \ "head").head.child.foreach {
    case elem: Elem => {
      convertToTag(elem) match {
        case Some(tag) => webPage.head.contents += tag
        case None => // Ignore
      }
    }
    case text: Text=> text.data.trim match {
      case "" => // Ignore
      case s => webPage.head.contents += org.hyperscala.tags.Text(s)
    }
  }
  // Process body
  (html \ "body").head.child.foreach {
    case elem: Elem => {
      convertToTag(elem) match {
        case Some(tag) => webPage.body.contents += tag
        case None => // Ignore
      }
    }
    case text: Text=> text.data.trim match {
      case "" => // Ignore
      case s => webPage.body.contents += org.hyperscala.tags.Text(s)
    }
  }

  def convertToTag(elem: Elem): Option[Tag] = {
    val tagName = elem.label.toLowerCase
    if (tagName == "title") {
      webPage.head.title := elem.text.trim
      None
    } else {
      val tagClass = Class.forName("org.hyperscala.tags.%s".format(tagName.charAt(0).toUpper + tagName.substring(1)))
      val tag = tagClass.newInstance().asInstanceOf[Tag]
      elem.attributes.foreach {
        case a => {
          val key = a.key
          val value = a.value.toString()
          try {
            tag.attributes.getOrElse(key, null) match {
              case ga: GenericAttribute[_] => ga.manifest.erasure.getSimpleName match {
                case "InputType" => ga.asInstanceOf[GenericAttribute[InputType]] := InputType(value)
                case "String" => ga.asInstanceOf[GenericAttribute[String]] := value
                case _ => throw new RuntimeException("Unable to handle attribute type of %s".format(ga.manifest.erasure))
              }
              case ss: StyleSheet => {
                ss.attribute = value
              }
              case null => tag.custom(key, value)
            }
          } catch {
            case exc => throw new RuntimeException("Unable to process %s=%s for %s".format(key, value, elem.label), exc)
          }
        }
      }
      elem.child.foreach {
        case elem: Elem => convertToTag(elem) match {
          case Some(childTag) => {
            tag.contents += childTag
          }
          case None => // Ignore
        }
        case text: Text=> text.data.trim match {
          case "" => // Ignore
          case s if (tag.isInstanceOf[Script]) => tag.contents += JavaScript(s)
          case s => tag.contents += org.hyperscala.tags.Text(s)
        }
      }
      Some(tag)
    }
  }
}