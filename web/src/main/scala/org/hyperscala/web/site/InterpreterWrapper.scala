package org.hyperscala.web.site

//Copyright (c) 2009, Joshua Suereth
//All rights reserved.
//
//Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
//
//    * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
//    * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
//    * Neither the name of the Joshua Suereth nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
//
//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

import scala.tools.nsc._
import interpreter._
import java.io._
import scala.reflect._
import org.powerscala.concurrent.Time

/**
 * A trait to ease the embedding of the scala interpreter
 */
trait InterpreterWrapper {
  def helpMsg: String
  def welcomeMsg: String
  def prompt: String

  private var bindings : Map[String, (java.lang.Class[_], AnyRef)] = Map()
  private var packageImports : List[String] = List()
  private var files = List[String]()

  /**
   * Binds a given value into the interpreter when it starts with its most specific class
   */
  def bind[A <: AnyRef](name : String, value : A)(implicit m : Manifest[A]): Unit =
    bindings += (( name,  (m.erasure, value)))
  /**
   * Binds a given value itnot he interpreter with a given interface/higher-level class.
   */
  def bindAs[A <: AnyRef, B <: A](name : String, interface : java.lang.Class[A], value : B): Unit =
    bindings += ((name,  (interface, value)))

  /**
   * adds an auto-import for the interpreter.
   */
  def autoImport(importString : String): Unit =
    packageImports = importString :: packageImports

  /**
   * Adds a file that will be interpreter at the start of the interpreter
   */
  def addScriptFile(fileName : String): Unit = files = fileName :: files

  private var interpreterInitialized = false

  /**
   * This class actually runs the interpreter loop.
   */
  class MyInterpreterLoop(out : PrintWriter) extends ILoop(None, out) {
    override val prompt = InterpreterWrapper.this.prompt

    // In Scala 2.8, InterpreterLoop.bindSettings() has been removed.
    // So, I need to override repl() and bind my settings by myself.
    override def loop() {
      if(isAsync) awaitInitialized()
      bindSettings()
      interpreterInitialized = true
      super.loop()
    }

    /** Bind the settings so that evaluated code can modify them */
    def bindSettings() {
      intp beQuietDuring {
        for( (name, (clazz, value)) <- bindings) {
          intp.bind(name, clazz.getCanonicalName, value)
        }
        for( importString <- packageImports) {
          intp.interpret("import " + importString)
        }
      }
    }
    override def helpCommand(line: String): Result = {
      if (line == "") echo(helpMsg)

      super.helpCommand(line)
    }

    override def printWelcome(): Unit = {
      out.println(welcomeMsg)
      out.flush()
    }
  }

  private var interpreter: MyInterpreterLoop = _

  def startInterpreting(): Unit = {
    val out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))
    val settings = new GenericRunnerSettings(out.println)
    files foreach settings.loadfiles.appendToValue
    settings.usejavacp.value = true
    interpreter = new MyInterpreterLoop(out)
    interpreter process settings
    ()
  }

  def interpret(code: String) = {
    Time.waitFor(30.0) {
      interpreterInitialized
    }
    interpreter.intp.interpret(code)
  }

  def useInterpreter(f: IMain => Unit, beQuiet: Boolean = true) = {
    Time.waitFor(30.0) {
      interpreterInitialized
    }
    interpreter.intp.beQuietDuring {
      f(interpreter.intp)
    }
  }
}