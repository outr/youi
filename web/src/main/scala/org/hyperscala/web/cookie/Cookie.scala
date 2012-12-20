package org.hyperscala.web.cookie

import org.jboss.netty.handler.codec.http.DefaultCookie

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class Cookie(name: String,
                  value: String,
                  comment: String = null,
                  commentUrl: String = null,
                  discard: Boolean = false,
                  domain: String = null,
                  httpOnly: Boolean = false,
                  maxAge: Int = Int.MinValue,
                  path: String = "/",
                  ports: List[Int] = Nil,
                  secure: Boolean = false,
                  version: Int = 0) {
  def toNettyCookie = {
    val c = new DefaultCookie(name, value)
    c.setComment(comment)
    c.setCommentUrl(commentUrl)
    c.setDiscard(discard)
    c.setDomain(domain)
    c.setHttpOnly(httpOnly)
    c.setMaxAge(maxAge)
    c.setPath(path)
    c.setPorts(ports: _*)
    c.setSecure(secure)
    c.setVersion(version)
    c
  }
}