package io.youi.server

import java.io.File
import java.util.concurrent.TimeUnit

import io.youi.http.cookie.SameSite
import fabric.rw._
import reactify._
import profig._

class ServerConfig(server: Server) {
  /**
    * The Server name set in the HTTP header
    */
  val name: Var[String] = Var(Profig("server.name").asOr[String]("youi"))

  object session {
    private val config = Profig("session").as[SessionConfig]

    val name: Var[String] = Var(config.name)
    val maxAge: Var[Long] = Var(config.maxAge)
    val domain: Var[Option[String]] = Var(config.domain)
    val path: Var[Option[String]] = Var(config.path)
    val secure: Var[Boolean] = Var(config.secure)
    /**
      * If true, will send secure even over insecure connections. Useful when a higher-level proxy is providing SSL.
      */
    val forceSecure: Var[Boolean] = Var(config.forceSecure)
    val httpOnly: Var[Boolean] = Var(config.httpOnly)
    val sameSite: Var[SameSite] = Var(config.sameSite.toLowerCase match {
      case "normal" => SameSite.Normal
      case "lax" => SameSite.Lax
      case "strict" => SameSite.Strict
    })

    case class SessionConfig(name: String = server.getClass.getSimpleName.replace("$", ""),
                             maxAge: Long = TimeUnit.DAYS.toSeconds(365L),
                             domain: Option[String] = None,
                             path: Option[String] = Some("/"),
                             secure: Boolean = false,
                             forceSecure: Boolean = false,
                             httpOnly: Boolean = true,
                             sameSite: String = "strict")

    object SessionConfig {
      implicit val rw: ReaderWriter[SessionConfig] = ccRW
    }
  }

  /**
    * If set to true the server will automatically restart when any configuration option changes. If set to false
    * the server must be manually restarted before changes will take effect.
    *
    * Default is true.
    */
  lazy val autoRestart: Var[Boolean] = Var(if (Profig("autoRestart").exists()) Profig("autoRestart").as[Boolean] else true)

  /**
    * Listeners for the server. Support HTTP and HTTPS listeners. Use addHttpListener and addHttpsListener for easier
    * usage.
    *
    * Defaults to one HTTP listener on 127.0.0.1:8080. This can be managed in code or via configuration. Storing an
    * HttpServerListener instance to "listeners.http" or HttpsServerListener to "listeners.https" will override the
    * defaults if done before this property is accessed for the first time. This can also be overridden via command-line
    * using specifics like "-listeners.http.host=0.0.0.0". HTTPS is configured by default, but enabled is set to false.
    * To easily enable HTTPS just pass "-listeners.https.enabled=true".
    */
  lazy val listeners: Var[List[ServerSocketListener]] = prop(List(
    Profig("listeners.http").asOr[HttpServerListener](HttpServerListener()),
    Profig("listeners.https").asOr[HttpsServerListener](HttpsServerListener())
  ))

  def enabledListeners: List[ServerSocketListener] = listeners().filter(_.enabled)

  def clearListeners(): ServerConfig = {
    listeners @= Nil
    this
  }

  def addHttpListener(host: String = "127.0.0.1", port: Int = 8080): ServerConfig = {
    listeners @= HttpServerListener(host, port) :: listeners()
    this
  }

  def addHttpsListener(host: String = "127.0.0.1",
                       port: Int = 8443,
                       keyStorePassword: String = "password",
                       keyStoreLocation: File = new File("keystore.jks")): ServerConfig = {
    listeners @= HttpsServerListener(host, port, KeyStore(keyStoreLocation.getAbsolutePath, keyStorePassword), enabled = true) :: listeners()
    this
  }

  protected def prop[T](value: T): Var[T] = {
    val v = Var[T](value)
    v.attach { value =>
      if (autoRestart.get && server.isRunning) {
        server.restart()
      }
    }
    v
  }
}

trait ServerSocketListener {
  def host: String
  def port: Int
  def enabled: Boolean
}

case class HttpServerListener(host: String = "127.0.0.1",
                              port: Int = 8080,
                              enabled: Boolean = true) extends ServerSocketListener {
  override def toString: String = if (host == "0.0.0.0") {
    s"HTTP ${ServerUtil.localIPs().map(ip => s"$ip:$port").mkString(", ")}"
  } else {
    s"HTTP $host:$port"
  }
}

object HttpServerListener {
  implicit val rw: ReaderWriter[HttpServerListener] = ccRW
}

case class HttpsServerListener(host: String = "127.0.0.1",
                               port: Int = 8443,
                               keyStore: KeyStore = KeyStore(),
                               enabled: Boolean = false) extends ServerSocketListener {
  override def toString: String = if (host == "0.0.0.0") {
    s"HTTPS ${ServerUtil.localIPs().map(ip => s"$ip:$port").mkString(", ")}"
  } else {
    s"HTTPS $host:$port"
  }
}

object HttpsServerListener {
  implicit val rw: ReaderWriter[HttpsServerListener] = ccRW
}

case class KeyStore(path: String = "keystore.jks", password: String = "password") {
  lazy val location: File = new File(path)
}

object KeyStore {
  implicit val rw: ReaderWriter[KeyStore] = ccRW
}