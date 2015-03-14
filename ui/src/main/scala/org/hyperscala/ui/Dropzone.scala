package org.hyperscala.ui

import java.io.File

import com.outr.net.http.content.{ContentType, StringContent}
import com.outr.net.http.handler.{MultipartSupport, MultipartHandler}
import com.outr.net.http.mime.MimeType
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.event.BrowserEvent
import org.hyperscala.html._
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.hyperscala.javascript.dsl._
import org.hyperscala.jquery.jQueryComponent
import org.hyperscala.module.Module
import org.powerscala.event.{Intercept, Listenable}
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.json.TypedSupport
import org.powerscala.property.Property
import org.powerscala.{StorageComponent, Unique, Version}
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.jquery.ui.{ProgressBar, jQueryUI, Dialog}
import org.hyperscala.realtime.Realtime
import language.reflectiveCalls
import com.outr.net.http.session.Session

/**
 * Hyperscala wrapper around Dropzone.js (http://www.dropzonejs.com/).
 *
 * @author Matt Hicks <mhicks@outr.com>
 */
object Dropzone extends Module with StorageComponent[Dropzone, HTMLTag] {
  TypedSupport.register("dropzoneFileEvent", classOf[DropzoneFileEvent])
  TypedSupport.register("dropzoneThumbnailEvent", classOf[DropzoneThumbnailEvent])
  TypedSupport.register("dropzoneProgressEvent", classOf[DropzoneProgressEvent])
  TypedSupport.register("dropzoneErrorEvent", classOf[DropzoneErrorEvent])

  private def FileScript(id: String, eventName: String) =
    new JavaScriptString(s"""
      |function(file) {
      | realtime.send({
      |   id: '$id',
      |   type: 'dropzoneFileEvent',
      |   event: '$eventName',
      |   lastModified: file.lastModifiedDate.getTime(),
      |   name: file.name,
      |   size: file.size,
      |   mimeType: file.type
      | });
      |}
    """.stripMargin)
  private def FileThumbnailScript(id: String, eventName: String) =
    new JavaScriptString(s"""
      |function(file, dataUrl) {
      | if (document.dataUrls == null) {
      |   document.dataUrls = {};
      | }
      | document.dataUrls['$id'] = dataUrl;
      | realtime.send({
      |   id: '$id',
      |   type: 'dropzoneThumbnailEvent',
      |   lastModified: file.lastModifiedDate.getTime(),
      |   name: file.name,
      |   size: file.size,
      |   mimeType: file.type
      | });
      |}
    """.stripMargin)
  private def FileProgressScript(id: String, eventName: String) =
    new JavaScriptString(s"""
      |function(file, progress, bytesSent) {
      | realtime.send({
      |   id: '$id',
      |   type: 'dropzoneProgressEvent',
      |   lastModified: file.lastModifiedDate.getTime(),
      |   name: file.name,
      |   size: file.size,
      |   mimeType: file.type,
      |   progress: progress,
      |   bytesSent: bytesSent
      | });
      |}
    """.stripMargin)
  private def FileErrorScript(id: String, eventName: String) =
    new JavaScriptString(s"""
      |function(file, message) {
      | realtime.send({
      |   id: '$id',
      |   type: 'dropzoneErrorEvent',
      |   lastModified: file.lastModifiedDate.getTime(),
      |   name: file.name,
      |   size: file.size,
      |   mimeType: file.type,
      |   message: message
      | });
      |}
    """.stripMargin)
  private def UseThumbnailScript(webpage: Webpage, dropzone: Dropzone, img: tag.Img) = {
    webpage.eval(
      s"""
        |$$('#${img.identity}').attr('src', document.dataUrls['${dropzone.wrapped.identity}']);
        |document.dataUrls['${dropzone.wrapped.identity}'] = null;
      """.stripMargin)
  }

  val path = Property[String](default = Some("/dropzone_upload"))

  def name = "dropzone"

  def version = Version(3, 10, 2)

  override def dependencies = List(Realtime)

  override def init(website: Website) = {
    website.register("/js/dropzone.js", "dropzone.js")
    website.addHandler(new DropzoneHandler(website), path())
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/dropzone.js")
  }

  override protected def create(t: HTMLTag) = new Dropzone(t)
}

class Dropzone(container: HTMLTag) extends jQueryComponent with Listenable {
  val id = Unique()
  val fileReceived = new UnitProcessor[(String, File)]("fileReceived")

  /**
   * Has to be specified on elements other than form (or when the form doesn't have an action attribute)
   *
   * Defaults to Dropzone.path()
   */
  val url = property("url", Dropzone.path(), includeDefault = true)
  /**
   * Defaults to "post" and can be changed to "put" if necessary.
   */
  val method = property("method", "post")
  val withCredentials = property("withCredentials", false)
  /**
   * How many file uploads to process in parallel.
   *
   * Defaults to 2.
   */
  val parallelUploads = property("parallelUploads", 2)
  val uploadMultiple = property("uploadMultiple", false)
  val maxFilesize = property("maxFilesize", 256L)
  val paramName = property("paramName", "file")
  val createImageThumbnails = property("createImageThumbnails", true)
  val maxThumbnailSize = property("maxThumbnailSize", 10)
  val thumbnailWidth = property("thumbnailWidth", 100)
  val thumbnailHeight = property("thumbnailHeight", 100)
  val maxFiles = property("maxFiles", -1)
  /**
   * Parameters to be sent via POST to the server in the multipart request.
   *
   * The values pageId and tagId are automatically added upon initialization.
   */
  val params = property[List[(String, String)]]("params", Nil, toJS = (list: List[(String, String)]) => JavaScriptString(list.map(i => s"'${i._1}': '${i._2}'").mkString("{", ", ", "}")))
  /**
   * If true, the dropzone element itself will be clickable, if false nothing will be clickable. Otherwise you can pass
   * an HTML element, a CSS selector (for multiple elements) or an array of those.
   *
   * Defaults to "true"
   */
  val clickable = property("clickable", "true")
  val ignoreHiddenFiles = property("ignoreHiddenFiles", true)
  val acceptedFiles = property[List[String]]("acceptedFiles", null)
  val autoProcessQueue = property("autoProcessQueue", true)
  val autoQueue = property("autoQueue", true)
  val addRemoveLinks = property("addRemoveLinks", false)
  val previewsContainer = property[String]("previewsContainer", null)
  val forceFallback = property("forceFallback", false)
  val previewTemplate = property[String]("previewTemplate", null)

  val dictDefaultMessage = property("dictDefaultMessage", "Drop files here to upload")
  val dictFallbackMessage = property("dictFallbackMessage", "Your browser does not support drag'n'drop file uploads.")
  val dictFallbackText = property("dictFallbackText", "Please use the fallback form below to upload your files like in the olden days.")
  val dictFileTooBig = property("dictFileTooBig", "File is too big ({{filesize}}MiB). Max filesize: {{maxFilesize}}MiB.")
  val dictInvalidFileType = property("dictInvalidFileType", "You can't upload files of this type.")
  val dictResponseError = property("dictResponseError", "Server responded with {{statusCode}} code.")
  val dictCancelUpload = property("dictCancelUpload", "Cancel upload")
  val dictCancelUploadConfirmation = property("dictCancelUploadConfirmation", "Are you sure you want to cancel this upload?")
  val dictRemoveFile = property("dictRemoveFile", "Remove file")
  val dictRemoveFileConfirmation = property[String]("dictRemoveFileConfirmation", null)
  val dictMaxFilesExceeded = property("dictMaxFilesExceeded", "You can not upload any more files.")

  val drop = property[JavaScriptContent]("drop", null)
  val dragStart = property[JavaScriptContent]("dragstart", null)
  val dragEnd = property[JavaScriptContent]("dragend", null)
  val dragEnter = property[JavaScriptContent]("dragenter", null)
  val dragOver = property[JavaScriptContent]("dragover", null)
  val dragLeave = property[JavaScriptContent]("dragleave", null)

  val addedFile = property[JavaScriptContent]("addedfile", null)
  val removedFile = property[JavaScriptContent]("removedfile", null)
  val thumbnail = property[JavaScriptContent]("thumbnail", null)
  val error = property[JavaScriptContent]("error", null)
  val processing = property[JavaScriptContent]("processing", null)
  val uploadProgress = property[JavaScriptContent]("uploadprogress", null)
  val sending = property[JavaScriptContent]("sending", null)
  val success = property[JavaScriptContent]("success", null)
  val complete = property[JavaScriptContent]("complete", null)
  val canceled = property[JavaScriptContent]("canceled", null)
  val maxFilesReached = property[JavaScriptContent]("maxFilesReached", null)
  val maxFilesExceeded = property[JavaScriptContent]("maxFilesExceeded", null)

  lazy val addedFileEvent = new UnitProcessor[DropzoneFileEvent]("addedFileEvent")
  lazy val removedFileEvent = new UnitProcessor[DropzoneFileEvent]("removedFileEvent")
  lazy val thumbnailEvent = new UnitProcessor[DropzoneThumbnailEvent]("thumbnailEvent")
  lazy val processingEvent = new UnitProcessor[DropzoneFileEvent]("processingEvent")
  lazy val uploadProgressEvent = new UnitProcessor[DropzoneProgressEvent]("uploadProgressEvent")
  lazy val successEvent = new UnitProcessor[DropzoneFileEvent]("successEvent")
  lazy val errorEvent = new UnitProcessor[DropzoneErrorEvent]("errorEvent")
  lazy val completeEvent = new UnitProcessor[DropzoneFileEvent]("completeEvent")

  val processingMultiple = property[JavaScriptContent]("processingmultiple", null)
  val sendingMultiple = property[JavaScriptContent]("sendingmultiple", null)
  val successMultiple = property[JavaScriptContent]("successmultiple", null)
  val completeMultiple = property[JavaScriptContent]("completemultiple", null)
  val cancelMultiple = property[JavaScriptContent]("cancelmultiple", null)

  container.connected[Webpage] {
    case webpage => {
      params := params() ::: List("pageId" -> webpage.pageId, "tagId" -> container.identity)
      init()
    }
  }

  def connectEventsToServer() = {
    // Override default JavaScript to send to server instead of executing in the browser
    addedFile := Dropzone.FileScript(container.identity, "addedFile")
    removedFile := Dropzone.FileScript(container.identity, "removedFile")
    thumbnail := Dropzone.FileThumbnailScript(container.identity, "thumbnail")
    processing := Dropzone.FileScript(container.identity, "processing")
    uploadProgress := Dropzone.FileProgressScript(container.identity, "uploadProgress")
    success := Dropzone.FileScript(container.identity, "success")
    complete := Dropzone.FileScript(container.identity, "complete")
    error := Dropzone.FileErrorScript(container.identity, "error")

    // Add handler for new events
    container.handle[DropzoneFileEvent] {
      case evt => evt.event match {
        case "addedFile" => addedFileEvent.fire(evt)
        case "removedFile" => removedFileEvent.fire(evt)
        case "processing" => processingEvent.fire(evt)
        case "success" => successEvent.fire(evt)
        case "complete" => completeEvent.fire(evt)
      }
    }
    container.handle[DropzoneThumbnailEvent] {
      case evt => thumbnailEvent.fire(evt)
    }
    container.handle[DropzoneProgressEvent] {
      case evt => uploadProgressEvent.fire(evt)
    }
    container.handle[DropzoneErrorEvent] {
      case evt => errorEvent.fire(evt)
    }
  }

  def applyThumbnail(img: tag.Img) = {
    Dropzone.UseThumbnailScript(webpage, this, img)
  }

  override protected def functionName = "dropzone"

  override protected def autoInit = false

  override protected def wrapped = container
}

case class DropzoneFileEvent(tag: HTMLTag, event: String, lastModified: Long, name: String, size: Long, mimeType: String) extends BrowserEvent
case class DropzoneThumbnailEvent(tag: HTMLTag, lastModified: Long, name: String, size: Long, mimeType: String) extends BrowserEvent
case class DropzoneProgressEvent(tag: HTMLTag, lastModified: Long, name: String, size: Long, mimeType: String, progress: Double, bytesSent: Long) extends BrowserEvent
case class DropzoneErrorEvent(tag: HTMLTag, lastModified: Long, name: String, size: Long, mimeType: String, message: String) extends BrowserEvent

class DropzoneHandler(website: Website) extends MultipartHandler {
  override def create(request: HttpRequest, response: HttpResponse) = {
    new DropzoneSupport(website)
  }
}

class DropzoneSupport(website: Website) extends MultipartSupport {
  private var page: Webpage = _
  private var t: HTMLTag = _
  private var dropzone: Dropzone = _

  override def begin(request: HttpRequest, response: HttpResponse) = {}

  override def onField(name: String, value: String) = name match {
    case "pageId" => page = website.pages.byPageId[Webpage](value).getOrElse(throw new NullPointerException(s"Cannot find page by id: $value."))
    case "tagId" => {
      t = page.getById[HTMLTag](value)
      dropzone = Dropzone(t)
    }
  }

  override def finish(request: HttpRequest, response: HttpResponse) = {
    response.copy(content = StringContent("Success", ContentType.Plain), status = HttpResponseStatus.OK)
  }

  override def onFile(filename: String, file: File) = dropzone.fileReceived.fire(filename -> file)
}