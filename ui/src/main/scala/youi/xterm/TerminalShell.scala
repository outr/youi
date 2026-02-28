package youi.xterm

import reactify.{Channel, Var}
import scribe.ANSI

import scala.collection.mutable.ListBuffer
import scala.scalajs.js

trait TerminalShell extends XTermView {
  val prompt: Var[String] = Var(s"${ANSI.fg.BrightGreen("shell")}${ANSI.fg.BrightYellow(">")} ")
  val streamDelay: Var[Double] = Var(5.0)
  val banner: Var[String] = Var("")

  val onLine: Channel[String] = Channel[String]
  val onInterrupt: Channel[Unit] = Channel[Unit]

  private val commands: ListBuffer[TerminalCommand] = ListBuffer.empty
  private var inputBuffer: String = ""

  def register(cmd: TerminalCommand): Unit = commands += cmd

  def register(cmds: TerminalCommand*): Unit = cmds.foreach(register)

  def writePrompt(): Unit = write(prompt())

  def streamOutput(text: String): Unit = {
    val delay = streamDelay()
    if (delay <= 0.0) {
      write(text)
      write("\r\n")
      writePrompt()
    } else {
      var index = 0
      def streamNext(): Unit = {
        if (index < text.length) {
          if (text(index) == '\u001b') {
            val end = text.indexOf('m', index)
            if (end >= 0) {
              write(text.substring(index, end + 1))
              index = end + 1
            } else {
              write(text(index).toString)
              index += 1
            }
            streamNext()
          } else if (text(index) == '\r' && index + 1 < text.length && text(index + 1) == '\n') {
            write("\r\n")
            index += 2
            js.timers.setTimeout(delay)(streamNext())
          } else {
            write(text(index).toString)
            index += 1
            js.timers.setTimeout(delay)(streamNext())
          }
        } else {
          write("\r\n")
          writePrompt()
        }
      }
      streamNext()
    }
  }

  private def dispatchCommand(line: String): Unit = {
    if (line.isEmpty) {
      writePrompt()
      return
    }

    val parts = line.split("\\s+", 2)
    val cmd = parts(0).toLowerCase
    val args = if (parts.length > 1) parts(1) else ""

    commands.find(_.name == cmd) match {
      case Some(command) =>
        val response = command.execute(args, this)
        if (response.nonEmpty) streamOutput(response)
      case None =>
        streamOutput(s"${ANSI.fg.BrightRed("Unknown command:")} $line\r\nType '${ANSI.fg.BrightGreen("help")}' for available commands.")
    }
  }

  private val helpCommand: TerminalCommand = TerminalCommand("help", "Show available commands") { (_, _) =>
    val header = ANSI.fg.BrightYellow("Available commands:")
    val entries = commands.map { c =>
      val usage = if (c.usage.nonEmpty) s" ${c.usage}" else ""
      s"  ${ANSI.fg.BrightGreen(c.name)}$usage — ${c.description}"
    }
    (header +: entries).mkString("\r\n")
  }

  override def initialize(): Unit = {
    super.initialize()

    register(helpCommand)

    val b = banner()
    if (b.nonEmpty) {
      writeln(b)
      writeln("")
    }

    onData.attach { data =>
      data match {
        case "\r" =>
          write("\r\n")
          val line = inputBuffer.trim
          inputBuffer = ""
          onLine @= line
          dispatchCommand(line)
        case "\u007f" =>
          if (inputBuffer.nonEmpty) {
            inputBuffer = inputBuffer.dropRight(1)
            write("\b \b")
          }
        case "\u0003" =>
          write("^C\r\n")
          inputBuffer = ""
          onInterrupt @= (())
          writePrompt()
        case s =>
          inputBuffer += s
          write(s)
      }
    }

    writePrompt()
  }
}
