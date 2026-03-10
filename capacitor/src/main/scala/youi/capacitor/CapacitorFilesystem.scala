package youi.capacitor

import scala.scalajs.js

object CapacitorFilesystem {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Filesystem")

  def readFile(path: String, directory: js.UndefOr[String] = js.undefined, encoding: String = "utf8"): js.Promise[js.Dynamic] = {
    val opts = js.Dynamic.literal(path = path, encoding = encoding)
    directory.foreach(v => opts.updateDynamic("directory")(v))
    plugin.readFile(opts).asInstanceOf[js.Promise[js.Dynamic]]
  }

  def writeFile(path: String, data: String, directory: js.UndefOr[String] = js.undefined, encoding: String = "utf8", recursive: Boolean = false): js.Promise[js.Dynamic] = {
    val opts = js.Dynamic.literal(path = path, data = data, encoding = encoding, recursive = recursive)
    directory.foreach(v => opts.updateDynamic("directory")(v))
    plugin.writeFile(opts).asInstanceOf[js.Promise[js.Dynamic]]
  }

  def deleteFile(path: String, directory: js.UndefOr[String] = js.undefined): js.Promise[Unit] = {
    val opts = js.Dynamic.literal(path = path)
    directory.foreach(v => opts.updateDynamic("directory")(v))
    plugin.deleteFile(opts).asInstanceOf[js.Promise[Unit]]
  }

  def mkdir(path: String, directory: js.UndefOr[String] = js.undefined, recursive: Boolean = false): js.Promise[Unit] = {
    val opts = js.Dynamic.literal(path = path, recursive = recursive)
    directory.foreach(v => opts.updateDynamic("directory")(v))
    plugin.mkdir(opts).asInstanceOf[js.Promise[Unit]]
  }

  def readdir(path: String, directory: js.UndefOr[String] = js.undefined): js.Promise[js.Dynamic] = {
    val opts = js.Dynamic.literal(path = path)
    directory.foreach(v => opts.updateDynamic("directory")(v))
    plugin.readdir(opts).asInstanceOf[js.Promise[js.Dynamic]]
  }

  def stat(path: String, directory: js.UndefOr[String] = js.undefined): js.Promise[js.Dynamic] = {
    val opts = js.Dynamic.literal(path = path)
    directory.foreach(v => opts.updateDynamic("directory")(v))
    plugin.stat(opts).asInstanceOf[js.Promise[js.Dynamic]]
  }

  def copy(from: String, to: String, directory: js.UndefOr[String] = js.undefined, toDirectory: js.UndefOr[String] = js.undefined): js.Promise[Unit] = {
    val opts = js.Dynamic.literal(from = from, to = to)
    directory.foreach(v => opts.updateDynamic("directory")(v))
    toDirectory.foreach(v => opts.updateDynamic("toDirectory")(v))
    plugin.copy(opts).asInstanceOf[js.Promise[Unit]]
  }

  def rename(from: String, to: String, directory: js.UndefOr[String] = js.undefined, toDirectory: js.UndefOr[String] = js.undefined): js.Promise[Unit] = {
    val opts = js.Dynamic.literal(from = from, to = to)
    directory.foreach(v => opts.updateDynamic("directory")(v))
    toDirectory.foreach(v => opts.updateDynamic("toDirectory")(v))
    plugin.rename(opts).asInstanceOf[js.Promise[Unit]]
  }

  def getUri(path: String, directory: js.UndefOr[String] = js.undefined): js.Promise[js.Dynamic] = {
    val opts = js.Dynamic.literal(path = path)
    directory.foreach(v => opts.updateDynamic("directory")(v))
    plugin.getUri(opts).asInstanceOf[js.Promise[js.Dynamic]]
  }
}
