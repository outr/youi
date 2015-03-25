package org.hyperscala.screen

import com.outr.net.URL

import scala.util.matching.Regex

/**
 * ScreenValidator provides the ability to match a specific URL and manage how the screen will be initialized.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait ScreenValidator[S <: Screen] {
  def matches(url: URL): Boolean
  def validate(url: URL, existing: Option[S]): ValidationResponse[S]
  def load(): S

  protected def use(screen: S) = UseScreen[S](screen)
  protected def redirect(url: URL, replace: Boolean = false) = RedirectURL[S](url, replace)
  protected[screen] val noMatch = new NoMatch[S]
}

trait ValidationResponse[S <: Screen]

case class UseScreen[S <: Screen](screen: S) extends ValidationResponse[S]

case class RedirectURL[S <: Screen](url: URL, replace: Boolean) extends ValidationResponse[S]

class NoMatch[S <: Screen]() extends ValidationResponse[S]

object ScreenValidator {
  def builder[S <: Screen] = ScreenValidatorBuilder[S]()

  def apply[S <: Screen](uri: String, loader: => S) = builder[S].load(loader).uri(uri)
}

case class ScreenValidatorBuilder[S <: Screen](loader: () => S = null, matchers: List[URL => Boolean] = Nil, redirects: List[URL => Option[(URL, Boolean)]] = Nil) extends ScreenValidator[S] {
  def load(f: => S) = copy[S](loader = () => f)
  def uri(s: String) = copy[S](matchers = ((url: URL) => url.path.equalsIgnoreCase(s)) :: matchers)
  def uri(r: Regex) = copy[S](matchers = ((url: URL) => r.pattern.matcher(url.path).matches()) :: matchers)
  def url(matching: URL) = copy[S](matchers = ((url: URL) => url.toString().toLowerCase == matching.toString().toLowerCase) :: matchers)
  def url(r: Regex) = copy[S](matchers = ((url: URL) => r.pattern.matcher(url.toString()).matches()) :: matchers)
  def redirect(uri: String, redirect: URL) = copy[S](redirects = ((url: URL) => if (url.path.equalsIgnoreCase(uri)) Some((redirect, false)) else None) :: redirects)
  def replace(uri: String, redirect: URL) = copy[S](redirects = ((url: URL) => if (url.path.equalsIgnoreCase(uri)) Some((redirect, true)) else None) :: redirects)
  def redirect(f: URL => Option[URL], replace: Boolean) = copy[S](redirects = ((url: URL) => f(url).map(u => u -> replace)) :: redirects)

  override def matches(url: URL) = matchers.find(m => m(url)).nonEmpty || redirects.find(r => r(url).nonEmpty).nonEmpty

  override def validate(url: URL, existing: Option[S]) = if (matchers.find(m => m(url)).nonEmpty) {
    redirects.toStream.map(f => f(url)).collectFirst {
      case Some((uri, replace)) => redirect(uri, replace)
    } match {
      case Some(redirect) => redirect
      case None => existing match {
        case Some(screen) => use(screen)
        case None => use(loader())
      }
    }
  } else {
    noMatch
  }

  override def load() = loader()
}