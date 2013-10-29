package org.hyperscala.javascript.dsl

import org.hyperscala.javascript.JavaScriptContent

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Statement extends JavaScriptContent {
  JavaScript.statementCreated(this)     // Notify JavaScript in case this is contextualized
}