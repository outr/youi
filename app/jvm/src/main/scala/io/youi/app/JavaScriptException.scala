package io.youi.app

import io.youi.JavaScriptError

class JavaScriptException(val error: JavaScriptError) extends RuntimeException(error.toString)