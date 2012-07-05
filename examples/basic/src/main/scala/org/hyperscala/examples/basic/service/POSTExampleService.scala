package org.hyperscala.examples.basic.service

import org.hyperscala.server.Service

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class POSTExampleService extends Service[Person, Message] {
  def process(person: Person) = Message("Hello %s".format(person.name))
}

case class Person(name: String, phone: String)

case class Message(greeting: String)