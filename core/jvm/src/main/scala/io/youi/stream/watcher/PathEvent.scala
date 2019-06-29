package io.youi.stream.watcher

import java.nio.file.Path

case class PathEvent(path: Path, directory: Path, kinds: Set[EventKind], lastModified: Long) {
  override def toString: String = s"PathEvent(path: $path, directory: $directory, kinds: $kinds, lastModified: $lastModified)"
}