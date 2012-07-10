package org.hyperscala.examples.basic.service

import org.hyperscala.server.{Session, Service}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class POSTExampleService extends Service[Person, Message, Session] {
  def matches(uri: String) = uri == "/service/postexample"

  def process(session: Session, ref: Option[Person]) = ref match {
    case Some(person) => Message("Hello %s".format(person.name))
    case None => Message("Who are you?")
  }
}

case class Person(name: String, phone: String)

case class Message(greeting: String)