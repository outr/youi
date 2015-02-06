package org.hyperscala.realtime.event.server

/**
 * InvokeJavaScript is sent to the browser to allow the server the ability to ask it to invoke arbitrary JavaScript.
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class InvokeJavaScript(code: String, waitCondition: String)