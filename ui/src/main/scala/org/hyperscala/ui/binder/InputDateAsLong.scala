package org.hyperscala.ui.binder

import org.hyperscala.html._
import org.hyperscala.web._
import java.text.SimpleDateFormat
import java.util.Date
import org.hyperscala.jquery.ui.jQueryUI
import org.hyperscala.ui.dynamic.Binder
import language.reflectiveCalls
import org.hyperscala.realtime.{Realtime, RealtimeEvent}
import org.hyperscala.jquery.dsl._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class InputDateAsLong(format: String = "MM/dd/yyyy") extends Binder[tag.Input, Long] {
  def bind(input: tag.Input) = {
    input.require(jQueryUI, jQueryUI.Latest)

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

    input.connected[Webpage[_ <: Session]] {
      case webpage => Realtime.send(webpage, $(input).call("datepicker()"))
    }


    input.changeEvent := RealtimeEvent()
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
