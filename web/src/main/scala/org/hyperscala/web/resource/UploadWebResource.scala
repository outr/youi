package org.hyperscala.web.resource

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.apache.commons.fileupload.servlet.ServletFileUpload
import javax.servlet.ServletException
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.FileItem

import scala.collection.JavaConversions._

/**
 * UploadWebResource handles HTTP POST multi-part file uploads.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait UploadWebResource extends WebResource {
  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) {
    if (!ServletFileUpload.isMultipartContent(request)) {
      throw new ServletException("Content-Type must be multipart formdata to accept files!")
    }
    val factory = new DiskFileItemFactory()
    val upload = new ServletFileUpload(factory)
    val items = upload.parseRequest(request).toList.asInstanceOf[List[FileItem]]
    items.foreach(fileItem => processFileItem(fileItem))
    uploadComplete()
  }

  /**
   * Called for each file included in the multi-part upload.
   *
   * @param fileItem the file item currently being processed
   */
  protected def processFileItem(fileItem: FileItem): Unit

  /**
   * Called when the upload has completed.
   */
  protected def uploadComplete(): Unit
}
