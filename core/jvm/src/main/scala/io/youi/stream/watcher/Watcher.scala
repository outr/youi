package io.youi.stream.watcher

import java.nio.file.LinkOption.NOFOLLOW_LINKS
import java.nio.file.StandardWatchEventKinds.{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY}
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file._

import scala.annotation.tailrec
import scala.collection.JavaConverters._
import scala.collection.mutable

abstract class Watcher(directory: Path,
                       ignoreMatchers: Set[String] = Watcher.DefaultIgnores,
                       eventDelay: Long = 1000L) extends Runnable {
  private val enqueued = mutable.Map.empty[String, PathEvent]
  private val watcher = FileSystems.getDefault.newWatchService()
  private var keys = Map.empty[WatchKey, Path]
  private val thread = new Thread(this) {
    setDaemon(true)
  }
  private var keepAlive = true

  private def registerAll(start: Path): Unit = {
    Files.walkFileTree(start, new SimpleFileVisitor[Path] {
      override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult = {
        register(dir)
        FileVisitResult.CONTINUE
      }
    })
  }

  private def register(directory: Path): Unit = {
    val watchKey = directory.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY)
    keys += watchKey -> directory
  }

  def start(): Unit = {
    registerAll(directory)
    thread.start()
  }

  def dispose(): Unit = keepAlive = false

  override def run(): Unit = processEvents()

  @tailrec
  private def processEvents(): Unit = {
    val time = System.currentTimeMillis()

    // Poll for events
    Option(watcher.poll()) match {
      case Some(key) => {
        val directory = keys(key)

        key.pollEvents().asScala.foreach { event =>
          val kind: EventKind = event.kind() match {
            case ENTRY_CREATE => EventKind.Create
            case ENTRY_MODIFY => EventKind.Modify
            case ENTRY_DELETE => EventKind.Delete
          }
          val watchEvent = event.asInstanceOf[WatchEvent[Path]]
          val name = watchEvent.context()
          val child = directory.resolve(name)
          val fileName = name.toString

          if (!ignoreMatchers.exists(fileName.matches)) {
            val pathEvent = enqueued.getOrElse(child.toString, PathEvent(child, directory, Set.empty, 0L))
            enqueued += child.toString -> pathEvent.copy(kinds = pathEvent.kinds + kind, lastModified = time)
          }

          if (kind == EventKind.Create && Files.isDirectory(child, NOFOLLOW_LINKS)) {
            registerAll(child)
          }
        }

        val valid = key.reset()
        if (!valid) {
          keys -= key
        }
      }
      case None => // Nothing to process
    }

    // Check for queued changes
    enqueued.foreach {
      case (key, pathEvent) if time - pathEvent.lastModified > eventDelay => {
        enqueued -= key
        fire(pathEvent)
      }
      case _ => // Not old enough
    }

    if (keys.nonEmpty && keepAlive) {
      Thread.sleep(100)
      processEvents()
    }
  }

  def fire(pathEvent: PathEvent): Unit
}

object Watcher {
  val DefaultIgnores = Set(
    ".*___jb_tmp___",           // JetBrains tmp file
    ".*___jb_old___"            // JetBrains old file
  )
}