package io.youi.app

import io.youi.net.IP

class JavaScriptError(val column: Int,
                      val fileName: String,
                      val line: Int,
                      val message: String,
                      val url: String,
                      val userAgent: String,
                      val appName: String,
                      val appVersion: String,
                      val platform: String,
                      val language: String,
                      val referrer: String,
                      val ip: IP = IP.LocalHost) extends RuntimeException {
  override def getMessage: String = s"$message (fileName: $fileName, line: $line, column: $column, url: $url, userAgent: $userAgent, appName: $appName, appVersion: $appVersion, platform: $platform, language: $language, referrer: $referrer, ip: $ip)"
}