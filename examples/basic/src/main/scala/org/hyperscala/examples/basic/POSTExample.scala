package org.hyperscala.examples.basic

import org.hyperscala.WebPage
import org.hyperscala.tags._
import org.hyperscala.tags.attributes.InputType
import org.hyperscala.js.JavaScript

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object POSTExample extends WebPage("/examples/post.html") {
  head.title := "Ajax POST Example"
  head.contents += new Script(src = "/js/jquery-1.7.2.js")

  body.contents += new Form(action = "/", id = "postForm") {
    contents += new Label(content = "Name")
    contents += new Input(name = "name")
    contents += new Label(content = "Phone")
    contents += new Input(name = "phone")
    contents += new Input(inputType = InputType.Submit)
  }
  body.contents += new Div(id = "result")
  body.contents += new Script {
    contents += JavaScript(
      """
        |$("#postForm").submit(function(event) {
        | event.preventDefault();
        | $.post("/service/postexample", $("#postForm").serialize(), function(data) {
        |   $("#result").empty().append(data.message);
        | });
        |});
      """.stripMargin)
  }
  val s = $("testing")
}

case class $(name: String)