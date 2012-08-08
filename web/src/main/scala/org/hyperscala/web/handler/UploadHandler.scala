package org.hyperscala.web.handler

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.apache.commons.fileupload.servlet.ServletFileUpload
import javax.servlet.ServletException
import org.apache.commons.fileupload.disk.DiskFileItemFactory

import scala.collection.JavaConversions._
import org.apache.commons.fileupload.FileItem

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait UploadHandler extends ContentHandler {
  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    if (!ServletFileUpload.isMultipartContent(request)) {
      throw new ServletException("Content-Type must be multipart formdata to accept files!")
    }
    val factory = new DiskFileItemFactory()
    val upload = new ServletFileUpload(factory)
    val items = upload.parseRequest(request).toList.asInstanceOf[List[FileItem]]
    items.foreach(fileItem => processFileItem(fileItem))
    uploadComplete()
  }

  protected def processFileItem(fileItem: FileItem): Unit

  protected def uploadComplete(): Unit
}
