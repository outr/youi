package io.youi.form

import org.scalajs.dom.html

trait Validation {
  def validate(input: html.Input, value: Option[String]): Option[String]
}

object Validation {
  object NonEmpty extends Validation {
    override def validate(input: html.Input, value: Option[String]): Option[String] = if (value.isEmpty) {
      Some(s"${input.name.capitalize} must not be empty")
    } else {
      None
    }
  }

  case class Length(minimum: Int = 0, maximum: Int = Int.MaxValue) extends Validation {
    override def validate(input: html.Input, value: Option[String]): Option[String] = {
      val v = value.getOrElse("")
      val length = v.length
      if (length < minimum) {
        Some(s"${input.name.capitalize} must be at least $minimum characters in length")
      } else if (length > maximum) {
        Some(s"${input.name.capitalize} must be $maximum or fewer characters in length")
      } else {
        None
      }
    }
  }

  object Email extends Validation {
    private val regex = """^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$"""

    override def validate(input: html.Input, value: Option[String]): Option[String] = {
      if (value.exists(_.matches(regex))) {
        None
      } else {
        Some(s"${input.name.capitalize} must be a valid email address")
      }
    }
  }

  case class EqualTo(that: FormInput) extends Validation {
    override def validate(input: html.Input, value: Option[String]): Option[String] = if (value != that.option) {
      Some(s"${input.name.capitalize} must be equal to ${that.input.name.capitalize}")
    } else {
      None
    }
  }
}