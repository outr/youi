package org.hyperscala.html.event

import org.powerscala.property.Property
import org.hyperscala._
import javascript.{EventProperty, JavaScriptContent}
import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait EventSupport extends Tag {
  this: HTMLTag =>

  /**
   * Specifies whether style change events coming from the client will be applied back to the property.
   *
   * Defaults to true
   */
  val applyStyleChanges = Property[Boolean]("applyStyleChanges", true)

  val event = new {
    val afterPrint = new PropertyAttribute[JavaScriptContent]("onafterprint", null) with EventProperty
    val beforePrint = new PropertyAttribute[JavaScriptContent]("onbeforeprint", null) with EventProperty
    val beforeOnLoad = new PropertyAttribute[JavaScriptContent]("onbeforeonload", null) with EventProperty
    val hasChange = new PropertyAttribute[JavaScriptContent]("onhaschange", null) with EventProperty
    val load = new PropertyAttribute[JavaScriptContent]("onload", null) with EventProperty
    val message = new PropertyAttribute[JavaScriptContent]("onmessage", null) with EventProperty
    val offline = new PropertyAttribute[JavaScriptContent]("onoffline", null) with EventProperty
    val online = new PropertyAttribute[JavaScriptContent]("ononline", null) with EventProperty
    val pageHide = new PropertyAttribute[JavaScriptContent]("onpagehide", null) with EventProperty
    val pageShow = new PropertyAttribute[JavaScriptContent]("onpageshow", null) with EventProperty
    val popState = new PropertyAttribute[JavaScriptContent]("onpopstate", null) with EventProperty
    val redo = new PropertyAttribute[JavaScriptContent]("onredo", null) with EventProperty
    val resize = new PropertyAttribute[JavaScriptContent]("onresize", null) with EventProperty
    val storage = new PropertyAttribute[JavaScriptContent]("onstorage", null) with EventProperty
    val undo = new PropertyAttribute[JavaScriptContent]("onundo", null) with EventProperty
    val unLoad = new PropertyAttribute[JavaScriptContent]("onunload", null) with EventProperty
    val blur = new PropertyAttribute[JavaScriptContent]("onblur", null) with EventProperty
    val change = new PropertyAttribute[JavaScriptContent]("onchange", null) with EventProperty
    val contextMenu = new PropertyAttribute[JavaScriptContent]("oncontextmenu", null) with EventProperty
    val focus = new PropertyAttribute[JavaScriptContent]("onfocus", null) with EventProperty
    val formChange = new PropertyAttribute[JavaScriptContent]("onformchange", null) with EventProperty
    val formInput = new PropertyAttribute[JavaScriptContent]("onforminput", null) with EventProperty
    val input = new PropertyAttribute[JavaScriptContent]("oninput", null) with EventProperty
    val invalid = new PropertyAttribute[JavaScriptContent]("oninvalid", null) with EventProperty
    val reset = new PropertyAttribute[JavaScriptContent]("onreset", null) with EventProperty
    val select = new PropertyAttribute[JavaScriptContent]("onselect", null) with EventProperty
    val submit = new PropertyAttribute[JavaScriptContent]("onsubmit", null) with EventProperty
    val keyDown = new PropertyAttribute[JavaScriptContent]("onkeydown", null) with EventProperty
    val keyPress = new PropertyAttribute[JavaScriptContent]("onkeypress", null) with EventProperty
    val keyUp = new PropertyAttribute[JavaScriptContent]("onkeyup", null) with EventProperty
    val click = new PropertyAttribute[JavaScriptContent]("onclick", null) with EventProperty
    val doubleClick = new PropertyAttribute[JavaScriptContent]("ondblclick", null) with EventProperty
    val drag = new PropertyAttribute[JavaScriptContent]("ondrag", null) with EventProperty
    val dragEnd = new PropertyAttribute[JavaScriptContent]("ondragend", null) with EventProperty
    val dragEnter = new PropertyAttribute[JavaScriptContent]("ondragenter", null) with EventProperty
    val dragLeave = new PropertyAttribute[JavaScriptContent]("ondragleave", null) with EventProperty
    val dragOver = new PropertyAttribute[JavaScriptContent]("ondragover", null) with EventProperty
    val dragStart = new PropertyAttribute[JavaScriptContent]("ondragstart", null) with EventProperty
    val drop = new PropertyAttribute[JavaScriptContent]("ondrop", null) with EventProperty
    val mouseDown = new PropertyAttribute[JavaScriptContent]("onmousedown", null) with EventProperty
    val mouseMove = new PropertyAttribute[JavaScriptContent]("onmousemove", null) with EventProperty
    val mouseOut = new PropertyAttribute[JavaScriptContent]("onmouseout", null) with EventProperty
    val mouseOver = new PropertyAttribute[JavaScriptContent]("onmouseover", null) with EventProperty
    val mouseUp = new PropertyAttribute[JavaScriptContent]("onmouseup", null) with EventProperty
    val mouseWheel = new PropertyAttribute[JavaScriptContent]("onmousewheel", null) with EventProperty
    val scroll = new PropertyAttribute[JavaScriptContent]("onscroll", null) with EventProperty
    val abort = new PropertyAttribute[JavaScriptContent]("onabort", null) with EventProperty
    val canPlay = new PropertyAttribute[JavaScriptContent]("oncanplay", null) with EventProperty
    val canPlayThrough = new PropertyAttribute[JavaScriptContent]("oncanplaythrough", null) with EventProperty
    val durationChange = new PropertyAttribute[JavaScriptContent]("ondurationchange", null) with EventProperty
    val emptied = new PropertyAttribute[JavaScriptContent]("onemptied", null) with EventProperty
    val ended = new PropertyAttribute[JavaScriptContent]("onended", null) with EventProperty
    val error = new PropertyAttribute[JavaScriptContent]("onerror", null) with EventProperty
    val loadedData = new PropertyAttribute[JavaScriptContent]("onloadeddata", null) with EventProperty
    val loadedMetaData = new PropertyAttribute[JavaScriptContent]("onloadedmetadata", null) with EventProperty
    val loadStart = new PropertyAttribute[JavaScriptContent]("onloadstart", null) with EventProperty
    val pause = new PropertyAttribute[JavaScriptContent]("onpause", null) with EventProperty
    val play = new PropertyAttribute[JavaScriptContent]("onplay", null) with EventProperty
    val playing = new PropertyAttribute[JavaScriptContent]("onplaying", null) with EventProperty
    val progress = new PropertyAttribute[JavaScriptContent]("onprogress", null) with EventProperty
    val rateChange = new PropertyAttribute[JavaScriptContent]("onratechange", null) with EventProperty
    val readyStateChange = new PropertyAttribute[JavaScriptContent]("onreadystatechange", null) with EventProperty
    val seeked = new PropertyAttribute[JavaScriptContent]("onseeked", null) with EventProperty
    val seeking = new PropertyAttribute[JavaScriptContent]("onseeking", null) with EventProperty
    val stalled = new PropertyAttribute[JavaScriptContent]("onstalled", null) with EventProperty
    val suspend = new PropertyAttribute[JavaScriptContent]("onsuspend", null) with EventProperty
    val timeUpdate = new PropertyAttribute[JavaScriptContent]("ontimeupdate", null) with EventProperty
    val volumeChange = new PropertyAttribute[JavaScriptContent]("onvolumechange", null) with EventProperty
    val waiting = new PropertyAttribute[JavaScriptContent]("onwaiting", null) with EventProperty
    val styleChange = new PropertyAttribute[JavaScriptContent]("onstylechange", null) with EventProperty
  }
}