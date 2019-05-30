package io.youi.util

import io.circe.parser._
import java.io.File

import org.powerscala.io.IO

object SwaggerClientBuilder {
  def main(args: Array[String]): Unit = {
    val file = new File("swagger.json")
    val json = parse(IO.stream(file, new StringBuilder).toString).getOrElse(throw new RuntimeException("Failed to parse!"))
    val definitions = (json \\ "definitions").head
    val paths = (json \\ "paths").head
    val path = (paths \\ "/_api/collection").head
    val post = (path \\ "post").head
    val parameters = (post \\ "parameters").head
    val postAPICollection = (definitions \\ "post_api_collection").head
    scribe.info(s"Post: $postAPICollection")
  }
}