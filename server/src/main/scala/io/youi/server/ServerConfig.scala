package io.youi.server

import java.io.File

import reactify._

class ServerConfig(server: Server) {
  object session {
    val name: Var[String] = Var("JSESSIONID")
    val maxAge: Var[Option[Long]] = Var(None)
    val domain: Var[Option[String]] = Var(None)
    val secure: Var[Boolean] = Var(false)
  }

  /**
    * If set to true the server will automatically restart when any configuration option changes. If set to false
    * the server must be manually restarted before changes will take effect.
    *
    * Default is true.
    */
  val autoRestart: Var[Boolean] = Var(true)

  /**
    * Listeners for the server. Support HTTP and HTTPS listeners. Use addHttpListener and addHttpsListener for easier
    * usage.
    *
    * Defaults to one HTTP listener on 127.0.0.1:8080
    */
  val listeners: Var[List[ServerSocketListener]] = prop(List(HttpServerListener()))

  def clearListeners(): ServerConfig = {
    listeners := Nil
    this
  }

  def addHttpListener(host: String = "127.0.0.1", port: Int = 8080): ServerConfig = {
    listeners := HttpServerListener(host, port) :: listeners()
    this
  }

  def addHttpsListener(host: String = "127.0.0.1",
                       port: Int = 8443,
                       keyStorePassword: String = "password",
                       keyStoreLocation: File = new File("keystore.jks")): ServerConfig = {
    listeners := HttpsServerListener(host, port, KeyStore(keyStoreLocation, keyStorePassword)) :: listeners()
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

sealed trait ServerSocketListener

case class HttpServerListener(host: String = "127.0.0.1", port: Int = 8080) extends ServerSocketListener {
  override def toString: String = if (host == "0.0.0.0") {
    s"HTTP ${ServerUtil.localIPs().map(ip => s"$ip:$port").mkString(", ")}"
  } else {
    s"HTTP $host:$port"
  }
}

case class HttpsServerListener(host: String = "127.0.0.1",
                               port: Int = 8443,
                               keyStore: KeyStore = KeyStore()) extends ServerSocketListener {
  override def toString: String = if (host == "0.0.0.0") {
    s"HTTPS ${ServerUtil.localIPs().map(ip => s"$ip:$port").mkString(", ")}"
  } else {
    s"HTTPS $host:$port"
  }
}

case class KeyStore(location: File = new File("keystore.jks"), password: String = "password")