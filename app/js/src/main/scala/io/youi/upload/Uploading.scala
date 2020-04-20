package io.youi.upload

import reactify.Val

import scala.concurrent.Future

case class Uploading(progress: Val[Long], percentage: Val[Double], future: Future[String])