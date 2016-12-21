package io.youi.example

import io.youi.communicate._

trait ExampleInterface extends Interface {
  def clientURL: ClientCall[String]
  def reverse: ServerMethod[String, String]
  def time: ServerCall[Long]
}