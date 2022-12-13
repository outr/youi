import scala.scalajs.js

package object opentype {
  def load(url: String, callback: js.Function2[String, Font, Unit]): Unit = TopLevel.load(url, callback)
}