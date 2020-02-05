package io.youi.stream

import java.nio.channels.SeekableByteChannel

class HTMLStream(val streamable: StreamableHTML) {
  import StreamAction._

  private var actions = Set.empty[StreamAction]
  private var group: Option[Group] = None

  def add(action: StreamAction): Unit = group match {
    case Some(g) => group = Some(g.copy(g.actions ::: List(action)))
    case None => actions += action
  }

  def insert(position: Int, content: String, priority: Int = 0): Unit = {
    add(Insert(position, content, priority))
  }

  def skip(start: Int, end: Int, priority: Int = 0): Unit = {
    add(Skip(start, end, priority))
  }

  def replace(start: Int, end: Int, content: String, priority: Int = 0): Unit = {
    add(Insert(start, content, priority))
    add(Skip(start, end, priority))
  }

  def process(start: Int, end: Int, processor: String => String, priority: Int = 0, replace: Boolean = true): Unit = {
    add(Process(start, end, processor, priority))
    if (replace) add(Skip(start, end, priority))
  }

  def reposition(position: Int, priority: Int = 0): Unit = add(Reposition(position, priority))

  def group(actions: StreamAction*): Unit = {
    add(Group(actions.toList, 0))
  }

  def grouped(priority: Int = 0)(f: => Unit): Unit = {
    group = Some(Group(Nil, priority))
    try {
      f
    } finally {
      actions += group.get
      group = None
    }
  }

  def stream(channel: SeekableByteChannel, end: Int, start: Option[Int] = None): String = {
    val sorted = actions.toList.sortBy(_.priority).sortBy(_.position)
    val reader = new ByteReader
    val output = new StringBuilder
    var position = start.getOrElse(0)

    def processAction(action: StreamAction): Unit = {
      if (action.position > position) {
        val length = action.position - position
        val pre = reader.readString(position, length, channel)
        output.append(pre)
        position = action.position
      }
      action match {
        case i: Insert => output.append(i.content)
        case s: Skip => position = s.end
        case r: Reposition => position = r.position
        case g: Group => g.actions.foreach(processAction)
        case p: Process => {
          val block = reader.readString(p.position, p.end - p.position, channel)
          val content = p.processor(block)
          output.append(content)
        }
      }
    }

    sorted.foreach(processAction)
    if (position < end) {
      val post = reader.readString(position, end - position, channel)
      output.append(post)
    }
    output.append('\n')
    output.toString()
  }
}