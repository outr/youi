package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.site.{Webpage, Website}
import org.hyperscala.html._
import org.hyperscala.realtime.Realtime
import org.powerscala.property.{PropertyParent, Property}
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.property.event.PropertyChangeEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
object DynamicURL extends Module {
  val name = "dynamicURL"
  val version = Version(1)

  val delineationCharacter = '&'
  val splitCharacter = '='

  override val dependencies = List(Realtime)

  def init() = {
    Website().register("/js/dynamic_url.js", "dynamic_url.js")
  }

  def load() = {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/dynamic_url.js")
    apply()    // Make sure the instance has been created
  }

  def apply() = {
    val webpage = Webpage()
    webpage.synchronized {
      webpage.store.getOrSet("dynamicURLInstance", new DynamicURLInstance(webpage))
    }
  }
}

case class DynamicURLInstance(webpage: Webpage) extends PropertyParent {
  val name = () => "DynamicURLInstance"
  def parent = null

  /**
   * Represents the map of key/value pairs in the hash. This should generally not be manipulated directly but rather
   * you should use the property method to create properties that represent the keys you wish to mainpulate and/or read.
   */
  val map = Property[Map[String, String]]("map", Map.empty[String, String])
  private val changing = new AtomicBoolean(false)

  listenForBrowserChanges()
  listenForServerChanges()

  /**
   * Creates a Property instance representing the specified name in the hash. The value defaults to null but will be
   * changed based on what is set in the browser.
   *
   * @param name the name to match in the hash
   * @return new property bound multi-directionally to changes in the hash
   */
  def property(name: String) = {
    val initial = map.getOrElse(name, null)
    val p = Property[String](name, initial)
    map.onChange {    // Update the property when the map changes
      synchronized {
        p := map.getOrElse(name, null)
      }
    }
    p.onChange {      // Update the map when the property changes
      synchronized {
        val updated = map() + (name -> p())
        map := updated
      }
    }
    p
  }

  private def listenForBrowserChanges() = {  // Listen for changes coming from the browser changing the hash
    webpage.body.handle("hashChanged") {
      case message => {
        val hash = message[String]("hash") match {
          case null => ""
          case "" => ""
          case s => s.substring(1)
        }
        val hashMap = if (hash == null || hash.trim.isEmpty) {
          Map.empty[String, String]
        } else {
          import java.net.URLDecoder.decode
          hash.split(DynamicURL.delineationCharacter).collect {
            case block => {
              val split = block.indexOf(DynamicURL.splitCharacter)
              if (split == -1) {
                block -> block
              } else {
                decode(block.substring(0, split), "UTF-8") -> decode(block.substring(split + 1), "UTF-8")
              }
            }
          }.toMap
        }
        changing.set(true)
        try {
          map := hashMap
        } finally {
          changing.set(false)
        }
      }
    }
  }

  private def listenForServerChanges() = {
    import java.net.URLEncoder.encode
    map.listeners.synchronous {
      case evt: PropertyChangeEvent if (!changing.get()) => {
        val hashString = map.map {
          case (key, value) => "%s%s%s".format(encode(key, "UTF-8"), DynamicURL.splitCharacter, encode(value, "UTF-8"))
        }.mkString(DynamicURL.delineationCharacter.toString)
        Realtime.sendJavaScript("setHash(content);", content = hashString, onlyRealtime = false)
      }
    }
  }
}