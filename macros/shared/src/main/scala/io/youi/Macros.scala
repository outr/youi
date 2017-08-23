package io.youi

import scala.annotation.compileTimeOnly
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

@compileTimeOnly("Enable compile-time Macros for expansion")
object Macros {
  def named(c: blackbox.Context)(value: c.Tree): c.Tree = {
    import c.universe._

    val fieldName = c.internal.enclosingOwner.name.decodedName.toString.trim
    q"""
       io.youi.NamedValues.register(${c.prefix.tree}, $fieldName, $value)
       $value
     """
  }
}