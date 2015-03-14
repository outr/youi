package org.hyperscala.realtime

import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.javascript.dsl.Statement
import org.hyperscala.web.Webpage
import org.powerscala.Unique
import org.powerscala.json._

/**
 * RealtimeRequest allows JavaScript to be sent from the server to the client to be executed and the result is sent back
 * to the server.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object RealtimeRequest {
  TypedSupport.register("realtimeResponse", classOf[RealtimeResponse])

  /**
   * Does a one-time call to the browser and sends the result back to be handled by the supplied function.
   *
   * @param webpage the webpage to apply to
   * @param request the JavaScript request that results in the value that is sent to the server
   * @param condition optional condition under which to execute the first time
   * @param f the function to execute with the result
   * @param manifest result Manifest
   * @tparam R result type
   */
  def apply[R](webpage: Webpage,
               request: Statement[R],
               condition: Option[Statement[Boolean]] = None)
              (f: R => Unit)(implicit manifest: Manifest[R]) = {
    val id = Unique()
    webpage.jsonEvent.onceConditional(Unit) {
      case rr: RealtimeResponse if rr.requestId == id => {
        val result = typedJSON[R](rr.result)
        f(result)
        Some(Unit)
      }
      case _ => None
    }
    val js =
      s"""var result = ${request.content};
         |realtime.send({
         |  type: 'realtimeResponse',
         |  requestId: '$id',
         |  result: JSON.stringify(result)
         |});""".stripMargin
    webpage.eval(JavaScriptString(js), condition)
  }

  /**
   * Works like apply except continues to monitor for changes in the value at the supplied frequency and when a change
   * is detected the function is invoked again.
   *
   * @param webpage the webpage to apply to
   * @param request the JavaScript request that results in the value that is sent to the server
   * @param condition optional condition under which to execute the first time
   * @param frequency how frequently to check for changes in milliseconds (defaults to every 1 second)
   * @param f the function to execute with the result
   * @param manifest result manifest
   * @tparam R result type
   */
  def monitor[R](webpage: Webpage,
               request: Statement[R],
               condition: Option[Statement[Boolean]] = None,
               frequency: Long = 1000L)
              (f: R => Unit)(implicit manifest: Manifest[R]) = {
    val id = Unique()
    webpage.jsonEvent.partial(Unit) {
      case rr: RealtimeResponse if rr.requestId == id => {
        val result = typedJSON[R](rr.result)
        f(result)
        Some(Unit)
      }
    }
    val js =
      s"""(function() {
         |  var current = '--first check--';
         |  var update = function() {
         |    var result = ${request.content};
         |    if (result != current) {
         |      current = result;
         |      realtime.send({
         |        type: 'realtimeResponse',
         |        requestId: '$id',
         |        result: JSON.stringify(result)
         |      });
         |    }
         |  };
         |  update();
         |  setInterval(update, $frequency);
         |})();""".stripMargin
    webpage.eval(JavaScriptString(js), condition)
  }
}

case class RealtimeResponse(requestId: String, result: String)