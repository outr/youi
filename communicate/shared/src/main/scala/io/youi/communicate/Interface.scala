package io.youi.communicate

import scala.language.experimental.macros

trait Interface {
  def clientCall[Response]: ClientCall[Response] = macro Macros.clientCall[Response]
  def serverCall[Response]: ServerCall[Response] = macro Macros.serverCall[Response]
  def clientMethod[Request, Response]: ClientMethod[Request, Response] = macro Macros.clientMethod[Request, Response]
  def serverMethod[Request, Response]: ServerMethod[Request, Response] = macro Macros.serverMethod[Request, Response]
}
