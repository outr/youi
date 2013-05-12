package org.hyperscala.web.site

import com.outr.webcommunicator.netty._
import com.outr.webcommunicator.URL
import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}
import com.outr.webcommunicator.netty.handler.RequestHandler
import org.jboss.netty.handler.codec.http.HttpRequest
import java.io.{FileNotFoundException, File}
import java.net.URLClassLoader

import org.powerscala.reflect._
import org.hyperscala.web.session.Session

import language.existentials

/**
 * @author Matt Hicks <matt@outr.com>
 */
class MultiWebsite extends NettyWebapp {
  /**
   * The host to default bind to.
   *
   * Defaults to null (wildcard)
   */
  def host: String = null

  /**
   * The port to default bind to.
   *
   * Defaults to 8080
   */
  def port: Int = 8080

  def register(website: Website[_ <: Session])(f: URL => Boolean): Unit = register(WebsiteResource(website, f))

  def register(jar: File, websiteClassName: String)(f: URL => Boolean): Unit = {
    if (!jar.exists()) {
      throw new FileNotFoundException(s"No file exists: ${jar.getAbsolutePath}.")
    }
    val classLoader = new URLClassLoader(Array(jar.toURI.toURL))
    Thread.currentThread().setContextClassLoader(classLoader)
    val clazz = classLoader.loadClass(websiteClassName + "$")
    println(clazz)
    val website = clazz.instance.getOrElse(throw new NullPointerException(s"No companion object on $clazz")).asInstanceOf[Website[_ <: Session]]
    register(website)(f)
  }

  def main(args: Array[String]): Unit = {
    bind(host, port)
    val hostname = host match {
      case null => "*"
      case _ => host
    }
    info("%s bound to %s:%s".format(getClass.getSimpleName, hostname, port))
  }
}

object TestMultiWebsite extends MultiWebsite {
  register(new File("site/target/hyperscala-0.7.2-SNAPSHOT.jar"), "org.hyperscala.site.HyperscalaSite") {
    case url => {
      println(url)
      true
    }
  }
}

case class WebsiteResource(website: Website[_ <: Session], matcher: URL => Boolean) extends WebResource with RequestHandler {
  def request(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent): Option[RequestHandler] = event.getMessage match {
    case request: HttpRequest if (request != null && matcher(request)) => Some(this)
    case _ => None
  }

  def apply(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent) = {
    website.messageReceived(context, event)
  }
}