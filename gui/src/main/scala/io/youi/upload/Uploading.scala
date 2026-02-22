package io.youi.upload

import rapid.Task
import org.scalajs.dom.File
import reactify.Val

case class Uploading(file: File, progress: Val[Long], percentage: Val[Double], task: Task[String])