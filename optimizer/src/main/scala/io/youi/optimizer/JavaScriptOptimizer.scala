package io.youi.optimizer

import java.io.File

import com.google.common.base
import com.google.javascript.jscomp._

import scala.collection.mutable.ListBuffer

object JavaScriptOptimizer {
  def optimize(files: List[ScriptFile], jsOutput: File): Unit = {
    val options = ListBuffer.empty[String]
    def add(key: String, value: String): Unit = {
      options += key
      options += value
    }

    // Add JavaScript files
    files.foreach { f =>
      add("--js", f.js.getCanonicalPath)
    }

    // Define output options
    add("--js_output_file", jsOutput.getCanonicalPath)
    add("--create_source_map", new File(jsOutput.getParentFile, s"${jsOutput.getName}.map").getCanonicalPath)

    val runner = new CommandLineRunner(options.toArray) {}
    runner.setExitCodeReceiver(new base.Function[java.lang.Integer, java.lang.Void] {
      override def apply(input: Integer): Void = null
    })
    if (runner.shouldRunCompiler()) {
      runner.run()
    }
    if (runner.hasErrors) {
      throw new RuntimeException(s"Errors Optimizing JavaScript Files")
    }
  }
}