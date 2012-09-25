package org.hyperscala.web

import org.hyperscala.html.HTMLTag

package object live {
  implicit def tag2LiveTag(tag: HTMLTag) = new LiveTag(tag)
}