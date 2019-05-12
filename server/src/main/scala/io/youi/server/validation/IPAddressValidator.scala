package io.youi.server.validation

import io.youi.http.{HttpConnection, HttpStatus}
import io.youi.net.IP

import scala.concurrent.Future

class IPAddressValidator(allow: Set[IP], reject: Set[IP], defaultAllow: Boolean) extends Validator {
  override def validate(connection: HttpConnection): Future[ValidationResult] = Future.successful {
    val ip = connection.request.source
    if ((allow.contains(ip) || defaultAllow) && !reject.contains(ip)) {
      ValidationResult.Continue(connection)
    } else {
      scribe.warn(s"Unauthorized attempt to access: ${connection.request.url} from IP: $ip. Allowed: ${allow.mkString(", ")}, Reject: ${reject.mkString(", ")}, Default Allow? $defaultAllow")
      ValidationResult.Error(connection, HttpStatus.Forbidden.code, s"Unauthorized IP address: $ip")
    }
  }
}