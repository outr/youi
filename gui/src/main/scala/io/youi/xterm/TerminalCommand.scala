package io.youi.xterm

class TerminalCommand(val name: String, val description: String, val usage: String, val execute: (String, TerminalShell) => String)

object TerminalCommand {
  def apply(name: String, description: String, usage: String = "")(execute: (String, TerminalShell) => String): TerminalCommand =
    new TerminalCommand(name, description, usage, execute)
}
