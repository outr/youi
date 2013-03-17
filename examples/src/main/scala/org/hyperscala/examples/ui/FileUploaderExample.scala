package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.ui.widgets.FileUploader
import org.jboss.netty.handler.codec.http.multipart.FileUpload

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class FileUploaderExample extends Example {
  contents += new FileUploader {
    def uploaded(upload: FileUpload) = {
      println("File Uploaded: %s / %s".format(upload.getFilename, upload.getFile.getAbsolutePath))
    }
  }
}
