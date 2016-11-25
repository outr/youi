package io.youi.example

import io.youi.communicate._

trait ExampleInterface extends Interface {
  val url: ClientCall[String] = clientCall[String]
  val reverse: ServerMethod[String, String] = serverMethod[String, String]
  val time: ServerCall[Long] = serverCall[Long]
}