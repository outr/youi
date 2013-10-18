package org.hyperscala.web.useragent

import org.powerscala.Version
import org.hyperscala.web.{Website, Webpage}
import net.sf.uadetector.service.UADetectorServiceFactory
import net.sf.uadetector.{UserAgentType, VersionNumber, ReadableUserAgent}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object UserAgent {
  private var cache = Map.empty[String, ReadableUserAgent]

  def apply() = Webpage().store.getOrSet("userAgentModule", new UserAgent())

  private def parse(userAgent: String) = synchronized {
    cache.get(userAgent) match {
      case Some(agent) => agent
      case None => {
        val agent = UADetectorServiceFactory.getResourceModuleParser.parse(userAgent)
        cache += userAgent -> agent
        agent
      }
    }
  }
}

class UserAgent private() {
  val userAgent = Website().request.headers.UserAgent.getOrElse(throw new NullPointerException(s"User-Agent was not supplied: ${Website().request.headers.values.keySet}"))
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
}