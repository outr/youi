package io.youi

import java.util.concurrent.ThreadLocalRandom

import scala.language.experimental.macros

trait NamedValues[T] {
  private var name2Value = Map.empty[String, T]
  private var value2Name = Map.empty[T, String]

  protected def named(value: T): T = macro Macros.named

  def getByName(name: String): Option[T] = name2Value.get(NamedValues.fixName(name))
  def byName(name: String): T = getByName(name).getOrElse(throw new RuntimeException(s"Unable to find by name: $name."))

  def name(value: T): Option[String] = value2Name.get(value)

  def allByName: Map[String, T] = name2Value
  lazy val all: Vector[T] = allByName.toVector.sortBy(_._1).map(_._2)
  def random: T = all(ThreadLocalRandom.current().nextInt(all.length - 1))
}

object NamedValues {
  private def fixName(name: String): String = name.toLowerCase.replace(" ", "")

  def register[T](nv: NamedValues[T], name: String, value: T): Unit = nv.synchronized {
    val n = fixName(name)
    nv.name2Value += n -> value
    nv.value2Name += value -> name
  }
}