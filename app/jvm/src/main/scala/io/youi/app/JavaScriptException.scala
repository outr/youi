package io.youi.app

import io.youi.http.HttpRequest
import io.youi.{JavaScriptError, MessageException}
import io.youi.net.IP
import net.sf.uadetector.ReadableUserAgent

class JavaScriptException(val error: JavaScriptError,
                          val userAgent: ReadableUserAgent,
                          val ip: IP,
                          val request: HttpRequest,
                          val info: Map[String, String]) extends RuntimeException() with MessageException {
  lazy val message: String = {
    val headers = request.headers.map.map {
      case (key, values) => s"   $key: ${values.mkString(", ")}"
    }.mkString("\n")
    val infoString = info.map {
      case (key, value) => s"   $key: $value"
    }.mkString("\n") match {
      case s if s.isEmpty => ""
      case s => s"\nInfo {\n$s\n}"
    }

    s"""
      |-------------------------------------------------
      |IP: $ip
      |Agent {
      |   Name: ${userAgent.getName} (Version: ${userAgent.getVersionNumber.toVersionString})
      |   Type: ${userAgent.getTypeName}
      |   Category: ${userAgent.getDeviceCategory.getName}
      |   Family: ${userAgent.getFamily.getName}
      |   OS: ${userAgent.getOperatingSystem.getName} (Family: ${userAgent.getOperatingSystem.getFamilyName}, Version: ${userAgent.getOperatingSystem.getVersionNumber.toVersionString})
      |}
      |Headers {
      |$headers
      |}$infoString
      |$error
      |-------------------------------------------------
    """.stripMargin.trim
  }

  override def getMessage: String = message
}