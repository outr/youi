package io.youi.server.validation

class ValidationError(val status: Int, val message: String) extends RuntimeException(s"$message (status: $status)")
