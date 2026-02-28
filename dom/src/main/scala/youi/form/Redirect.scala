package youi.form

import spice.net.{URLPath, URL}

import scala.concurrent.duration._

case class Redirect(url: URL => URL, _push: Boolean = true, delay: Option[FiniteDuration] = None) {
  def push: Redirect = copy(_push = true)
  def set: Redirect = copy(_push = false)
  def delay(delay: FiniteDuration): Redirect = copy(delay = Some(delay))
}

object Redirect {
  def url(url: URL): Redirect = Redirect(url = _ => url)
  def path(path: URLPath): Redirect = Redirect(url = url => url.withPath(path))
}