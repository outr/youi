package org.hyperscala.tweenjs

import com.outr.net.http.session.Session
import org.hyperscala.easeljs.{Stage, ShapePropertyValue, Shape, EaselJS}
import org.hyperscala.html._
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.hyperscala.module.Module
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.{Unique, Version}

/**
 * Matt Hicks <matt@outr.com>
 */
object TweenJS extends Module {
  val name = "tweenJS"
  val version = Version(0, 6, 0)

  override def dependencies = List(EaselJS)

  override def init(website: Website) = {
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(src = s"https://code.createjs.com/tweenjs-0.6.0.min.js")
  }
}

object Ticker {
  def setFPS(stage: Stage, fps: Int) = stage.canvas.connected[Webpage] {
    case webpage => webpage.eval(JavaScriptString(s"createjs.Ticker.setFPS($fps);"))
  }

  def addEventListener(eventType: String, stage: Stage) = stage.canvas.connected[Webpage] {
    case webpage => webpage.eval(JavaScriptString(s"createjs.Ticker.addEventListener('$eventType', EaselJS.stage('${stage.id}'));"))
  }
}

case class Tween(shape: Shape,
                 loop: Boolean = false,
                 useTicks: Boolean = false,
                 ignoreGlobalPause: Boolean = false,
                 overrideCurrent: Boolean = false,
                 paused: Boolean = false) {
  val id = Unique()

  private val options = Map("loop" -> loop, "useTicks" -> useTicks, "ignoreGlobalPause" -> ignoreGlobalPause, "override" -> overrideCurrent, "paused" -> paused).filter(_._2)
  shape.stage.send(JavaScriptString(s"""EaselJS.items['$id'] = createjs.Tween.get(EaselJS.shape('${shape.id}', '${shape.stage.id}'), ${options.map(t => s"${t._1}: ${t._2}").mkString("{", ", ", "}")})"""))

  def to(values: List[ShapePropertyValue[_]], duration: Double = 0.0, ease: EaseInstance = Ease.Linear()) = {
    val vals = values.map(v => s"${v.property.name.toLowerCase}: ${JavaScriptContent.toJS(v.value)}").mkString("{", ", ", "}")
    shape.stage.send(JavaScriptString(s"""EaselJS.items['$id'].to($vals, $duration, ${ease.toJS})"""))
    this
  }
}