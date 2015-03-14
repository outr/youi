package org.hyperscala.web.useragent

import org.powerscala.Version
import org.hyperscala.web.Webpage
import net.sf.uadetector.service.UADetectorServiceFactory
import net.sf.uadetector.{UserAgentType, VersionNumber, ReadableUserAgent}
import com.outr.net.http.session.Session
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
object UserAgent extends Logging {
  private var cache = Map.empty[String, ReadableUserAgent]

  def get(webpage: Webpage) = try {
    val ua = new UserAgent(webpage.website.request.headers.UserAgent)
    webpage.store.getOrSet("userAgentModule", ua)
    Some(ua)
  } catch {
    case t: Throwable => {
      warn(s"Unable to process UserAgent from: ${webpage.website.request.headers.UserAgent}.", t)
      None
    }
  }
  def apply(webpage: Webpage) = webpage.store.getOrSet("userAgentModule", new UserAgent(webpage.website.request.headers.UserAgent))
  def apply(userAgent: String) = new UserAgent(Some(userAgent))

  private def parse(userAgent: Option[String]) = synchronized {
    val userAgentStr = userAgent.getOrElse("")
    cache.get(userAgentStr) match {
      case Some(agent) => agent
      case None => {
        val agent = UADetectorServiceFactory.getResourceModuleParser.parse(userAgentStr)
        cache += userAgentStr -> agent
        agent
      }
    }
  }
}

class UserAgent private(userAgent: Option[String]) {
  val agent = UserAgent.parse(userAgent)

  val browser = Browser(
    family = BrowserFamily.byFriendlyName(agent.getFamily.getName),
    version = versionConverter(agent.getVersionNumber)
  )
  val os = OperatingSystem(
    name = agent.getOperatingSystem.getName,
    family = OperatingSystemFamily.byFriendlyName(agent.getOperatingSystem.getFamilyName),
    version = versionConverter(agent.getOperatingSystem.getVersionNumber)
  )
  val agentType = if (agent.getType == UserAgentType.ROBOT) AgentType.Robot else AgentType.byFriendlyName(agent.getTypeName)

  private def s2Int(s: String) = try {
    if (s == null || s.trim.isEmpty) {
      0
    } else {
      s.trim.toInt
    }
  } catch {
    case t: Throwable => 0
  }

  private def versionConverter(v: VersionNumber) = {
    Version(
      major = s2Int(v.getMajor),
      minor = s2Int(v.getMinor),
      maintenance = s2Int(v.getBugfix),
      extra = v.getExtension)
  }

  override def toString = s"Browser: ${browser.family.friendlyName} (${browser.version}), OS: ${os.family.friendlyName} (${os.name} / ${os.version}), Type: ${agentType.friendlyName}"

}
