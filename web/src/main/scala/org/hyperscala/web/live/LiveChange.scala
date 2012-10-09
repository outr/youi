package org.hyperscala.web.live

case class LiveChange(id: Int, key: String, instruction: String, content: String) {
  def output = if (content != null) {
    "[%s|%s|%s]%s".format(id, instruction, content.length, content)
  } else {
    "[%s|%s|-1]".format(id, instruction)
  }
}
