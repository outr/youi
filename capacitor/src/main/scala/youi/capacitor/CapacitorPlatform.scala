package youi.capacitor

object CapacitorPlatform {
  def isNative: Boolean = try { Capacitor.isNativePlatform() } catch { case _: Throwable => false }
  def platform: String = try { Capacitor.getPlatform() } catch { case _: Throwable => "web" }
  def isIOS: Boolean = platform == "ios"
  def isAndroid: Boolean = platform == "android"
  def isWeb: Boolean = platform == "web"

  def isPluginAvailable(name: String): Boolean =
    try { Capacitor.isPluginAvailable(name) } catch { case _: Throwable => false }
}
