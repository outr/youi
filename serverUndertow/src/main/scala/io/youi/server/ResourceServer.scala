package io.youi.server

import java.io.File
import java.util.Date

import io.undertow.io.IoCallback
import io.undertow.server.handlers.cache.ResponseCache
import io.undertow.server.{HttpHandler, HttpServerExchange}
import io.undertow.server.handlers.resource._
import io.undertow.util._
import io.youi.http.content.FileContent

object ResourceServer {
  private val defaultResourceManager = new FileResourceManager(new File("."))

  def serve(exchange: HttpServerExchange, content: FileContent): Unit = {
    if (DirectoryUtils.sendRequestedBlobs(exchange)) return
    val cache: ResponseCache = exchange.getAttachment(ResponseCache.ATTACHMENT_KEY)
    val cachable: Boolean = true
    //we set caching headers before we try and serve from the cache
    if (cache != null && cachable) if (cache.tryServeResponse) return
    //we now dispatch to a worker thread
    //as resource manager methods are potentially blocking
    val dispatchTask: HttpHandler = new HttpHandler() {
      @throws[Exception]
      def handleRequest(exchange: HttpServerExchange): Unit = {
        val resource: Resource = new FileResource(content.file, defaultResourceManager, content.file.getAbsolutePath)
        val etag: ETag = resource.getETag
        val lastModified: Date = resource.getLastModified
        if (!ETagUtils.handleIfMatch(exchange, etag, false) || !DateUtils.handleIfUnmodifiedSince(exchange, lastModified)) {
          exchange.setStatusCode(StatusCodes.PRECONDITION_FAILED)
          exchange.endExchange
          return
        }
        if (!ETagUtils.handleIfNoneMatch(exchange, etag, true) || !DateUtils.handleIfModifiedSince(exchange, lastModified)) {
          exchange.setStatusCode(StatusCodes.NOT_MODIFIED)
          exchange.endExchange
          return
        }
        val contentLength: Long = resource.getContentLength
        if (!exchange.getResponseHeaders.contains(Headers.TRANSFER_ENCODING)) exchange.setResponseContentLength(contentLength)
        var rangeResponse: ByteRange.RangeResponseResult = null
        var start: Long = -1
        var end: Long = -1
        resource match {
          case rar: RangeAwareResource if rar.isRangeSupported => {
            exchange.getResponseHeaders.put(Headers.ACCEPT_RANGES, "bytes")
            val range: ByteRange = ByteRange.parse(exchange.getRequestHeaders.getFirst(Headers.RANGE))
            if (range != null && range.getRanges == 1 && resource.getContentLength != null) {
              rangeResponse = range.getResponseResult(resource.getContentLength, exchange.getRequestHeaders.getFirst(Headers.IF_RANGE), resource.getLastModified, if (resource.getETag == null) null
              else resource.getETag.getTag)
              if (rangeResponse != null) {
                start = rangeResponse.getStart
                end = rangeResponse.getEnd
                exchange.setStatusCode(rangeResponse.getStatusCode)
                exchange.getResponseHeaders.put(Headers.CONTENT_RANGE, rangeResponse.getContentRange)
                val length: Long = rangeResponse.getContentLength
                exchange.setResponseContentLength(length)
                if (rangeResponse.getStatusCode == StatusCodes.REQUEST_RANGE_NOT_SATISFIABLE) return
              }
            }
          }
          case _ =>
        }
        //we are going to proceed. Set the appropriate headers
        if (!exchange.getResponseHeaders.contains(Headers.CONTENT_TYPE)) {
          val contentType: String = content.contentType.mimeType
          if (contentType != null) exchange.getResponseHeaders.put(Headers.CONTENT_TYPE, contentType)
          else exchange.getResponseHeaders.put(Headers.CONTENT_TYPE, "application/octet-stream")
        }
        if (lastModified != null) exchange.getResponseHeaders.put(Headers.LAST_MODIFIED, resource.getLastModifiedString)
        if (etag != null) exchange.getResponseHeaders.put(Headers.ETAG, etag.toString)
        else if (rangeResponse != null) resource.asInstanceOf[RangeAwareResource].serveRange(exchange.getResponseSender, exchange, start, end, IoCallback.END_EXCHANGE)
        else resource.serve(exchange.getResponseSender, exchange, IoCallback.END_EXCHANGE)
      }
    }
    if (exchange.isInIoThread) exchange.dispatch(dispatchTask)
    else dispatchTask.handleRequest(exchange)
  }
}
