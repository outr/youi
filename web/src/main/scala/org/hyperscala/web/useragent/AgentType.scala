package org.hyperscala.web.useragent

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class AgentType private(val friendlyName: String) extends EnumEntry {
  override def toString() = friendlyName
}

object AgentType extends Enumerated[AgentType] {
  /**
   * A web browser, especially on a desktop, notebook or workstation
   */
  val Browser = new AgentType("Browser")

  /**
   * An email client, email reader
   */
  val EmailClient = new AgentType("Email client")

  /**
   * A <b>news aggregator</b>, also termed a <b>feed aggregator<b>, <b>feed reader</b>, <b>news reader</b>, <b>RSS
   * reader</b> or simply <b>aggregator</b>
   */
  val FeedReader = new AgentType("Feed Reader")

  /**
   * A library is a collection of resources used to develop software.
   */
  val Library = new AgentType("Library")

  /**
   * Media player, also called multimedia player, is a term typically used to describe computer software for playing
   * back multimedia files.
   */
  val MultimediaPlayer = new AgentType("Multimedia Player")

  /**
   * A mobile browser, also called a microbrowser, minibrowser, or wireless internet browser (WIB), is a web browser
   * designed for use on a mobile device.
   */
  val MobileBrowser = new AgentType("Mobile Browser")

  /**
   * Offline browser which may download completely or partially a website to a hard drive
   */
  val OfflineBrowser = new AgentType("Offline Browser")

  /**
   * A software that doesn't match the other types
   */
  val Other = new AgentType("Other")

  /**
   * Bots, such as Web crawlers
   */
  val Robot = new AgentType("Robot")

  /**
   * An unknown user agent type
   */
  val Unknown = new AgentType("Unknown")

  /**
   * A software to hide the real user agent information
   */
  val UseragentAnonymizer = new AgentType("Useragent Anonymizer")

  /**
   * A software or service that validates parts of a website or webservice, such as CSS, HTML, JSON, XML
   */
  val Validator = new AgentType("Validator")

  /**
   * A WAP browser is a web browser for mobile devices such as mobile phones that uses the Wireless Application
   * Protocol (WAP). WAP is a technical standard for accessing information over a mobile wireless network.
   */
  val WapBrowser = new AgentType("Wap Browser")

  def byFriendlyName(name: String) = values.find(at => at.friendlyName.equalsIgnoreCase(name)).getOrElse(Unknown)
}