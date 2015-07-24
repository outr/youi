package org.hyperscala.examples.ui

import java.io.File

import org.hyperscala.examples.Example
import org.hyperscala.ui.widgets.FileUploader
import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class FileUploaderExample extends Webpage with Example {
  body.contents += new FileUploader {
    def onField(name: String, value: String) = println(s"Field: $name=$value")

    def onFile(filename: String, file: File) = println(s"File: $filename (${file.length()})")
  }
}
