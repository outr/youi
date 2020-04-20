package io.youi.server.util

import java.io.File
import java.nio.file.Files
import java.security.KeyStore

import javax.net.ssl.{KeyManagerFactory, SSLContext, TrustManagerFactory}

object SSLUtil {
  def createSSLContext(keyStoreFile: File, password: String): SSLContext = {
    val passwordChars = password.toCharArray

    val keyStore = KeyStore.getInstance("JKS")
    assert(keyStoreFile.exists(), s"No keystore file was found at the location: ${keyStoreFile.getAbsolutePath}")
    val keyStoreInput = Files.newInputStream(keyStoreFile.toPath)
    keyStore.load(keyStoreInput, passwordChars)

    val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm)
    keyManagerFactory.init(keyStore, passwordChars)
    val keyManagers = keyManagerFactory.getKeyManagers

    val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm)
    trustManagerFactory.init(keyStore)
    val trustManagers = trustManagerFactory.getTrustManagers

    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(keyManagers, trustManagers, null)
    sslContext
  }
}
