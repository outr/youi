package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import org.hyperscala.javascript.dsl._
import org.hyperscala.realtime._
import org.powerscala.json.TypedSupport
import org.powerscala.property.Property
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.event.Listenable
import com.outr.net.http.session.Session
import org.powerscala.property.event.PropertyChangeEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
object DynamicURL extends Module {
  TypedSupport.register("hashChanged", classOf[HashChanged])

  val name = "dynamicURL"
  val version = Version(1)

  val delineationCharacter = '&'
  val splitCharacter = '='

  override val dependencies = List(Realtime)

  override def init(website: Website) = {
    website.register("/js/dynamic_url.js", "dynamic_url.js")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/dynamic_url.js")
    apply(webpage)    // Make sure the instance has been created
  }

  def apply(webpage: Webpage) = {
    webpage.synchronized {
      webpage.store.getOrSet("dynamicURLInstance", new DynamicURLInstance(webpage))
    }
  }
}

case class DynamicURLInstance(webpage: Webpage) extends Listenable {
  val name = () => "DynamicURLInstance"
  def parent = null

  /**
   * Represents the map of key/value pairs in the hash. This should generally not be manipulated directly but rather
   * you should use the property method to create properties that represent the keys you wish to mainpulate and/or read.
   */
  val map = Property[Map[String, String]](default = Some(Map.empty[String, String]))
  private val changing = new AtomicBoolean(false)

  listenForBrowserChanges()
  listenForServerChanges()

  /**
   * Creates a Property instance representing the specified name in the hash. The value will be
   * changed based on what is set in the browser or by explicitly modifying the property.
   *
   * @param name the name to match in the hash
   * @param default the default value to use if not found in the hash
   * @param allowNull if enabled will set the value of the property to null if there is no value of the property (defaults to false)
   * @return new property bound multi-directionally to changes in the hash
   */
  def property[T](name: String, default: T, allowNull: Boolean = false)(implicit t2s: T => String, s2t: String => T, manifest: Manifest[T]) = {
    val initial = map().get(name) match {
      case Some(value) => s2t(value)
      case None => default
    }
    val p = Property[T](default = Some(initial))
    map.change.on {    // Update the property when the map changes
      case evt => synchronized {
        map().get(name) match {
          case Some(value) => p := s2t(value)
          case None if allowNull => p := s2t(null)
          case None => // Ignore
        }
      }
    }
    p.change.on {      // Update the map when the property changes
      case evt => synchronized {
        val updated = map() + (name -> t2s(p()))
        map := updated
      }
    }
    p
  }

  private def listenForBrowserChanges() = {  // Listen for changes coming from the browser changing the hash
    webpage.jsonEvent.partial(Unit) {
      case evt: HashChanged => {
        val hash = evt.hash
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
          if (map() == hashMap) {     // Make sure a change event is always fired
            map.change.fire(PropertyChangeEvent(map, map(), hashMap))
          } else {                    // New value is not equal
            map := hashMap
          }
        } finally {
          changing.set(false)
        }
      }
    }
  }

  private def listenForServerChanges() = {
    import java.net.URLEncoder.encode
    map.change.on {
      case evt => if (!changing.get()) {
        val hashString = map().collect {
          case (key, value) if value != null => "%s%s%s".format(encode(key, "UTF-8"), DynamicURL.splitCharacter, encode(value, "UTF-8"))
        }.mkString(DynamicURL.delineationCharacter.toString)
        webpage.eval(s"setHash('$hashString');")
      }
    }
  }
}

case class HashChanged(hash: String)