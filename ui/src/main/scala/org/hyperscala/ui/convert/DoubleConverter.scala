package org.hyperscala.ui.convert

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DoubleConverter(pre: String = "", post: String = "") extends Converter[Double] {
  def value2String(value: Double) = s"$pre$value$post"

  def string2Value(s: String): Option[Double] = DoubleConverter.string2Value(s)
}

object DoubleConverter extends DoubleConverter(pre = "", post = "") {
  val Regex = """(\d+([.]\d+)?)""".r

  override def string2Value(s: String) = s match {
    case DoubleConverter.Regex(d, extra) => try {
      Some(d.toDouble)
    } catch {
      case t: Throwable => None
    }
    case _ => None
  }
}