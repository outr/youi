package org.hyperscala.easeljs

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.hyperscala.web.Webpage
import org.powerscala.Storage

/**
 * Matt Hicks <matt@outr.com>
 */
class Stage private(val canvas: tag.Canvas) {
  def createShape() = new Shape(this)

  def update() = send(JavaScriptString(s"EaselJS.stage('${canvas.identity}').update();"))

  private[easeljs] def send(js: JavaScriptContent) = canvas.connected[Webpage[Session]] {
    case webpage => webpage.eval(js)
  }
}

object Stage {
  def apply(canvas: tag.Canvas) = Storage.getOrSet(canvas, "easeljs.Stage", new Stage(canvas))
}