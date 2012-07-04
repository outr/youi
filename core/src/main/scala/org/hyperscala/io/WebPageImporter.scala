package org.hyperscala.io

import io.Source
import xml.{Text, Elem, XML}
import org.htmlcleaner.{PrettyXmlSerializer, HtmlCleaner}
import org.hyperscala.{GenericAttribute, WebPage}
import org.hyperscala.tags.{Script, Tag}
import org.hyperscala.style.StyleSheet
import org.hyperscala.js.JavaScript
import org.hyperscala.tags.attributes.InputType
import java.net.URLDecoder
import java.util.jar.JarFile
import java.io.File

import scala.collection.JavaConversions._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object WebPageImporter {
  lazy val tagClasses = classes("org.hyperscala.tags")

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

  def classes(packageName: String) = {
    val classLoader = Thread.currentThread().getContextClassLoader
    val pn = packageName.replace('.', '/')
    val url = classLoader.getResource(pn)
    if (url.getProtocol.equals("jar")) {
      val decoded = URLDecoder.decode(url.getFile, "UTF-8")
      val filename = decoded.substring(5, decoded.indexOf('!'))
      val jar = new JarFile(filename)
      jar.entries().collect {
        case entry if (entry.getName.startsWith(pn) && entry.getName.endsWith(".class")) => {
          val name = entry.getName
          name.substring(pn.length + 1, name.lastIndexOf('.')).replace('/', '.')
        }
      }.toList
    } else {
      val directory = new File(url.getFile)
      directory.listFiles().collect {
        case file if (file.getName.endsWith(".class")) => file.getName.substring(0, file.getName.lastIndexOf("."))
        // TODO: add support for subdirectories
      }.toList
    }
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
      val tagClassName = WebPageImporter.tagClasses.find(cn => tagName.equalsIgnoreCase(cn)).getOrElse(throw new RuntimeException("Unable to find %s".format(tagName)))
      val tagClass = Class.forName("org.hyperscala.tags.%s".format(tagClassName))
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
                case "int" => ga.asInstanceOf[GenericAttribute[Int]] := value.toInt
                case "boolean" => ga.asInstanceOf[GenericAttribute[Boolean]] := true
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