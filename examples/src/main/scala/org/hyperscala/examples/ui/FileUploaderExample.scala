package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.ui.widgets.FileUploader
import java.io.File

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class FileUploaderExample extends Example {
  contents += new FileUploader {
    def onField(name: String, value: String) = println(s"Field: $name=$value")

    def onFile(filename: String, file: File) = println(s"File: $filename (${file.length()})")
  }
}
