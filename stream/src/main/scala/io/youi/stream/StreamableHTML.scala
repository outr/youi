package io.youi.stream

import java.io.File
import java.nio.channels.FileChannel
import java.nio.file.StandardOpenOption

import com.outr.scribe._

class StreamableHTML(file: File, cacheBuilder: CacheBuilder) {
  private var cache = cacheBuilder.buildCache()

  def byId: Map[String, OpenTag] = cache.byId
  def byClass: Map[String, Set[OpenTag]] = cache.byClass
  def byTag: Map[String, Set[OpenTag]] = cache.byTag

  def stream(deltas: List[Delta], selector: Option[Selector] = None, includeTag: Boolean = true): String = {
    synchronized {
      if (cacheBuilder.isStale) {
        cache = cacheBuilder.buildCache()
      }
    }
    val channel = FileChannel.open(file.toPath, StandardOpenOption.READ)
    try {
      val streamer = new HTMLStream(this)
      val tag = selector.flatMap(_.lookup(this).headOption)
      val start = tag.map { t =>
        if (includeTag) {
          t.start
        } else {
          t.end
        }
      }
      val end = tag.map { t =>
        if (includeTag) {
          t.close.get.end
        } else {
          t.close.get.start
        }
      }.getOrElse(file.length().toInt)
      deltas.foreach { delta =>
        val tags = delta.selector.lookup(this)
        tags.foreach { tag =>
          if (tag.start >= start.getOrElse(0) && tag.close.map(_.end).getOrElse(tag.end) <= end) {
            try {
              delta(streamer, tag)
            } catch {
              case t: Throwable => throw new RuntimeException(s"Error processing Delta: $delta for tag: $tag.", t)
            }
          } else {
            logger.debug(s"Excluding $tag")
          }
        }
      }
      streamer.stream(channel, end, start)
    } finally {
      channel.close()
    }
  }
}

trait CacheBuilder {
  def isStale: Boolean
  def buildCache(): CachedInformation
}

case class CachedInformation(byId: Map[String, OpenTag],
                             byClass: Map[String, Set[OpenTag]],
                             byTag: Map[String, Set[OpenTag]])