package org.hyperscala.web.module

import org.hyperscala.module.Module
import java.net.URL
import scala.collection.mutable.ListBuffer
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.tag
import org.powerscala.enum.{Enumerated, EnumEntry}
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait WebJarModule extends Module {
  private var jarResources = ListBuffer.empty[WebJarResource]
  protected def prePath = "/webjar/"

  protected def addWebJar(name: String, version: String, resourceType: WebJarType, resources: String*) = resources.foreach {
    case resource => {
      val path = s"META-INF/resources/webjars/$name/$version/$resource"
      val url = getClass.getClassLoader.getResource(path)
      if (url == null) {
        throw new NullPointerException(s"Unable to find $resource at $path in classloader!")
      }
      jarResources += WebJarResource(s"$prePath$resource", url, resourceType)
    }
  }

  def init(website: Website) = jarResources.foreach(r => r.init(website))

  def load(webpage: Webpage) = jarResources.foreach(r => r.load(webpage))
}

case class WebJarResource(path: String, resource: URL, resourceType: WebJarType) {
  def init(website: Website) = {
    website.register(path, resource)
  }

  def load(webpage: Webpage) = {
    resourceType.load(this, webpage)
  }
}

sealed abstract class WebJarType(val load: (WebJarResource, Webpage) => Unit) extends EnumEntry

object WebJarType extends Enumerated[WebJarType] {
  /**
   * Resource does absolutely nothing except make the resource available at the path.
   */
  case object Resource extends WebJarType((r, w) => Unit)
  /**
   * Adds a script entry to head referencing the path.
   */
  case object Script extends WebJarType((r, w) => w.head.contents += new tag.Script(src = r.path))
  /**
   * Adds a link to the StyleSheet path in head.
   */
  case object Style extends WebJarType((r, w) => w.head.contents += new tag.Link(href = r.path))

  val values = findValues.toVector
}