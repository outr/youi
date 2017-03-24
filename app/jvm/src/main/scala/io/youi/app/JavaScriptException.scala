package io.youi.app

import io.youi.{JavaScriptError, MessageException}
import io.youi.net.IP
import net.sf.uadetector.ReadableUserAgent

class JavaScriptException(val error: JavaScriptError,
                          val userAgent: ReadableUserAgent,
                          val ip: IP) extends RuntimeException() with MessageException {
  lazy val message: String =
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
      |$error
      |-------------------------------------------------
    """.stripMargin.trim

  override def getMessage: String = message
}