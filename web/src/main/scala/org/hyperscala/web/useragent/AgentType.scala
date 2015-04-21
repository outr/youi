package org.hyperscala.web.useragent

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class AgentType(val friendlyName: String) extends EnumEntry {
  override def toString = friendlyName
}

object AgentType extends Enumerated[AgentType] {
  /**
   * A web browser, especially on a desktop, notebook or workstation
   */
  case object Browser extends AgentType("Browser")

  /**
   * An email client, email reader
   */
  case object EmailClient extends AgentType("Email client")

  /**
   * A <b>news aggregator</b>, also termed a <b>feed aggregator<b>, <b>feed reader</b>, <b>news reader</b>, <b>RSS
   * reader</b> or simply <b>aggregator</b>
   */
  case object FeedReader extends AgentType("Feed Reader")

  /**
   * A library is a collection of resources used to develop software.
   */
  case object Library extends AgentType("Library")

  /**
   * Media player, also called multimedia player, is a term typically used to describe computer software for playing
   * back multimedia files.
   */
  case object MultimediaPlayer extends AgentType("Multimedia Player")

  /**
   * A mobile browser, also called a microbrowser, minibrowser, or wireless internet browser (WIB), is a web browser
   * designed for use on a mobile device.
   */
  case object MobileBrowser extends AgentType("Mobile Browser")

  /**
   * Offline browser which may download completely or partially a website to a hard drive
   */
  case object OfflineBrowser extends AgentType("Offline Browser")

  /**
   * A software that doesn't match the other types
   */
  case object Other extends AgentType("Other")

  /**
   * Bots, such as Web crawlers
   */
  case object Robot extends AgentType("Robot")

  /**
   * An unknown user agent type
   */
  case object Unknown extends AgentType("Unknown")

  /**
   * A software to hide the real user agent information
   */
  case object UseragentAnonymizer extends AgentType("Useragent Anonymizer")

  /**
   * A software or service that validates parts of a website or webservice, such as CSS, HTML, JSON, XML
   */
  case object Validator extends AgentType("Validator")

  /**
   * A WAP browser is a web browser for mobile devices such as mobile phones that uses the Wireless Application
   * Protocol (WAP). WAP is a technical standard for accessing information over a mobile wireless network.
   */
  case object WapBrowser extends AgentType("Wap Browser")

  val values = findValues.toVector

  def byFriendlyName(name: String) = values.find(at => at.friendlyName.equalsIgnoreCase(name)).getOrElse(Unknown)
}