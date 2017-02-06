package io.youi

object Priority {
  val Fallback = Priority(0)
  val Low = Priority(100)
  val Normal = Priority(1000)
  val High = Priority(10000)
  val Critical = Priority(10000)

  def byName(name: String): Option[Priority] = name.toLowerCase match {
    case "fallback" => Some(Priority.Fallback)
    case "low" => Some(Priority.Low)
    case "normal" => Some(Priority.Normal)
    case "high" => Some(Priority.High)
    case "critical" => Some(Priority.Critical)
    case _ => None
  }
}

case class Priority(value: Int) extends Ordered[Priority] {
  def lower: Priority = Priority(value - 1)
  def higher: Priority = Priority(value + 1)

  override def compare(that: Priority): Int = that.value.compareTo(value)
}