package io.youi.util

import java.io.File

import io.swagger.v3.parser.OpenAPIV3Parser
import scala.collection.JavaConverters._

object SwaggerClientBuilder {
  def main(args: Array[String]): Unit = {
    val file = new File("swagger.json")
    assert(file.exists(), s"File not found: ${file.getAbsolutePath}")
    val api = new OpenAPIV3Parser().read(file.getCanonicalPath)
    api.getPaths.asScala.foreach {
      case (key, path) => scribe.info(s"Key: $key")
    }
  }
}