package org.hyperscala.examples.basic.service

import org.hyperscala.server.{ServiceRequestResponse, Session}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class POSTExampleService extends ServiceRequestResponse[Person, Message, Session] {
  lazy val name = "postexample"

  def process(session: Session, ref: Option[Person]) = ref match {
    case Some(person) => Message("Hello %s".format(person.name))
    case None => Message("Who are you?")
  }
}

case class Person(name: String, phone: String)

case class Message(greeting: String)