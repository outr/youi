package org.hyperscala.ui.convert

import java.util.Calendar
import org.powerscala.reflect.EnhancedClass

/**
 * @author Matt Hicks <matt@outr.com>
 */
object DateConverter extends Converter[Long] {
  val DateRegex = """(\d{2})/(\d{2})/(\d{4})""".r

  def value2String(value: Long) = "%1$tm/%1$td/%1$tY".format(value)

  def string2Value(s: String, clazz: EnhancedClass): Option[Long] = s match {
    case DateRegex(m, d, y) => try {
      val c = Calendar.getInstance()
      c.set(Calendar.HOUR_OF_DAY, 0)
      c.set(Calendar.MINUTE, 0)
      c.set(Calendar.SECOND, 0)
      c.set(Calendar.MILLISECOND, 0)
      c.set(Calendar.MONTH, m.toInt - 1)
      c.set(Calendar.DAY_OF_MONTH, d.toInt)
      c.set(Calendar.YEAR, y.toInt)
      Some(c.getTimeInMillis)
    } catch {
      case t: Throwable => None
    }
    case _ => None
  }
}