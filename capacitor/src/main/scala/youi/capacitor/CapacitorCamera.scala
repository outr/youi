package youi.capacitor

import scala.scalajs.js

object CapacitorCamera {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Camera")

  def getPhoto(
    quality: Int = 90,
    source: String = "PROMPT",
    resultType: String = "uri",
    allowEditing: Boolean = false
  ): js.Promise[js.Dynamic] =
    plugin.getPhoto(js.Dynamic.literal(
      quality = quality,
      source = source,
      resultType = resultType,
      allowEditing = allowEditing
    )).asInstanceOf[js.Promise[js.Dynamic]]

  def pickImages(quality: Int = 90, limit: Int = 0): js.Promise[js.Dynamic] =
    plugin.pickImages(js.Dynamic.literal(quality = quality, limit = limit)).asInstanceOf[js.Promise[js.Dynamic]]

  def checkPermissions(): js.Promise[js.Dynamic] = plugin.checkPermissions().asInstanceOf[js.Promise[js.Dynamic]]

  def requestPermissions(): js.Promise[js.Dynamic] = plugin.requestPermissions().asInstanceOf[js.Promise[js.Dynamic]]
}
