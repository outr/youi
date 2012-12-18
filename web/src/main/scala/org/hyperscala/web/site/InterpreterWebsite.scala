package org.hyperscala.web.site

import org.hyperscala.web.session.Session
import org.powerscala.concurrent.Executor

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait InterpreterWebsite[S <: Session] extends Website[S] {
  val className = getClass.getSimpleName.replaceAll("\\$", "")
  // Setup the interpreter
  protected lazy val interpreter = new InterpreterWrapper {
    def welcomeMsg: String = "%s started in interactive mode".format(className)

    def helpMsg: String = "Help Message!"

    def prompt: String = "%s> ".format(className)
  }

  override def main(args: Array[String]): Unit = {
    super.main(args)

    Executor.invoke {
      interpreter.startInterpreting()
    }
    interpreter.useInterpreter {
      case intp => intp.bind[AnyRef]("site", this)(Manifest.classType[AnyRef](getClass))
    }
  }

  def configureInterpreter(interpreter: InterpreterWrapper) = {}
}
