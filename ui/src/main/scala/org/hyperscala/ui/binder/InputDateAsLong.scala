package org.hyperscala.ui.binder

import org.hyperscala.html._
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.web.Webpage
import java.text.SimpleDateFormat
import java.util.Date
import org.hyperscala.jquery.ui.jQueryUI
import org.hyperscala.jquery.jQuery
import org.hyperscala.ui.dynamic.Binder
import language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class InputDateAsLong(format: String = "MM/dd/yyyy") extends Binder[tag.Input, Long] {
  def bind(input: tag.Input) = {
    Webpage().require(jQueryUI, jQueryUI.Latest)

    input.value.change.on {
      case evt => {
        val time = try {
          new SimpleDateFormat(format).parse(input.value()).getTime
        } catch {
          case t: Throwable => 0L
        }
        valueProperty := time
      }
    }
    valueProperty.change.on {
      case evt => {
        val formatted = valueProperty() match {
          case 0L => ""
          case v => new SimpleDateFormat(format).format(new Date(v))
        }
        input.value := formatted
      }
    }

    jQuery.call(input.id(), "datepicker()")

    input.changeEvent := JavaScriptEvent()
//    Webpage().body.contents += new tag.Script {
//      contents += new JavaScriptString(
//        """
//          |$(function() {
//          | $('#%s').datepicker();
//          |});
//        """.stripMargin.format(input.id()))
//    }
  }
}
