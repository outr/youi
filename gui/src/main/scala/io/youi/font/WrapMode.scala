package io.youi.font

trait WrapMode {
  def processLine(builder: TextBuilder, line: String): Unit

  def createText(builder: TextBuilder): Text = {
    builder.text.split('\n').foreach(processLine(builder, _))
    builder.toText
  }
}

object WrapMode {
  case object None extends WrapMode {
    override def processLine(builder: TextBuilder, line: String): Unit = {
      builder.addLine(line)
    }
  }
  case object Clip extends WrapMode {
    override def processLine(builder: TextBuilder, line: String): Unit = {
      builder.addLine(builder.maximum(line))
    }
  }
  case object Ellipsis extends WrapMode {
    override def processLine(builder: TextBuilder, line: String): Unit = {
      val max = builder.maximum(line)
      val text = if (max != line) {
        if (max.length > 3) {
          s"${max.substring(0, max.length - 3)}..."
        } else {
          max
        }
      } else {
        line
      }
      builder.addLine(text)
    }
  }
  case object Hyphenate extends WrapMode {
    override def processLine(builder: TextBuilder, line: String): Unit = {
      val max = builder.maximum(line)
      if (max != line) {
        val text = s"${max.substring(0, max.length - 1)}-"
        builder.addLine(text)
        processLine(builder, line.substring(max.length - 1))
      } else {
        builder.addLine(line)
      }
    }
  }
  case object Word extends WrapMode {
    override def processLine(builder: TextBuilder, line: String): Unit = {
      val max = builder.maximum(line)
      if (max != line) {
        if (max.contains(" ")) {
          val text = max.substring(0, max.lastIndexOf(' '))
          builder.addLine(text)
          processLine(builder, line.substring(text.length + 1))
        } else if (line.contains(" ")) {
          val text = line.substring(0, line.indexOf(' '))
          builder.addLine(text)
          processLine(builder, line.substring(text.length + 1))
        } else {
          builder.addLine(line)
        }
      } else {
        builder.addLine(line)
      }
    }
  }
}