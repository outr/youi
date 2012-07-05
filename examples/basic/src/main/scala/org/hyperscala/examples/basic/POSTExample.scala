package org.hyperscala.examples.basic

import org.hyperscala.WebPage
import org.hyperscala.tags._
import org.hyperscala.js.JavaScript
import org.hyperscala.form.CaseForm
import service.Person

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object POSTExample extends WebPage("/examples/post.html") {
  head.title := "Ajax POST Example"
  head.contents += new Script(src = "/js/jquery-1.7.2.js")
  head += JavaScript("function processResult(data) { $('#result').empty().append(data.greeting); }")

  body.contents += new CaseForm[Person]("Test", "/service/postexample", "processResult")
  body.contents += new Div(id = "result")
}