package org.hyperscala.web

import org.hyperscala.html.HTMLTag
import scala.annotation.tailrec
import org.powerscala.concurrent.AtomicInt

/**
 * @author Matt Hicks <matt@outr.com>
 */
object IdCreator {
  @tailrec
  final def assignId(webpage: Webpage, t: HTMLTag, pre: String = null, index: Int = 1): String = {
    val name = pre match {
      case null => t.xmlLabel
      case _ => pre
    }
    val stored = webpage.store.getOrSet[AtomicInt](s"idCreator.$name", new AtomicInt(index))
    val n = stored() match {
      case i if i > index => i
      case _ => index
    }
    val id = s"$name$n"
    if (checkAndSet(webpage, t, id, stored.set(n))) {
      id
    } else {
      assignId(webpage, t, pre, index + 1)
    }
  }

  def checkAndSet(webpage: Webpage, t: HTMLTag, id: String, f: => Unit) = webpage.synchronized {
    if (webpage.body.byId[HTMLTag](id).isEmpty) {
      t.id := id
      true
    } else {
      false
    }
  }
}