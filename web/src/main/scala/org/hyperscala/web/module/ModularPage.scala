package org.hyperscala.web.module

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage

/**
 * ModularPage represents all the functionality within a Webpage for dealing with Modules and Interfaces.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait ModularPage {
  this: Webpage =>

  private var modularPageLoaded = false
  private var interfaces = List.empty[Interface]

  def require(interface: Interface): Unit = synchronized {
    val existing = interfaces.find(i => i.name == interface.name)
    interface match {
      case module: Module => requireModule(module, existing)
      case iwd: InterfaceWithDefault => requireInterfaceWithDefault(iwd, existing)
      case _ => if (existing.isEmpty) addInterface(interface)
    }
  }

  def require(interface: Interface, default: Module): Unit = interface match {
    case module: Module => throw new RuntimeException("Requiring with default must not be a Module.")
    case iwd: InterfaceWithDefault => throw new RuntimeException("Requiring with default must not be a InterfaceWithDefault.")
    case _ => require(InterfaceWithDefault(interface, default))
  }

  private def requireModule(module: Module, existing: Option[Interface]) = existing match {
    case Some(i) => i match {
      case e: Module => e.version.compare(module.version) match {
        case -1 => // Nothing changes, the current is the newer version
        case 0 => // Nothing changes, they are both the same
        case 1 => replaceInterface(e.name, module)
      }
      case _ => replaceInterface(i.name, module)
    }
    case None => addInterface(module)
  }

  private def requireInterfaceWithDefault(iwd: InterfaceWithDefault, existing: Option[Interface]) = existing match {
    case Some(i) => i match {
      case e: Module => // Nothing changes, module supersedes default
      case e: InterfaceWithDefault => e.default.version.compare(iwd.default.version) match {
        case -1 => // Nothing changes, the current default is the newer version
        case 0 => // Nothing changes, they are both the same
        case 1 => replaceInterface(e.name, iwd)
      }
    }
    case None => addInterface(iwd)
  }

  private def replaceInterface(name: String, replacement: Interface, checkPageLoaded: Boolean = true) = {
    if (checkPageLoaded && modularPageLoaded) {
      throw new RuntimeException("Module with name '%s' is already loaded. Cannot replace the module after page load!".format(name))
    }
    replacement match {
      case module: Module => module.dependencies.foreach(require)
      case _ =>
    }
    interfaces = interfaces.map(i => if (i.name == name) replacement else i)
  }

  private def addInterface(interface: Interface) = {
    interface match {
      case module: Module => module.dependencies.foreach(require)
      case _ =>
    }
    interfaces = (interface :: interfaces.reverse).reverse
    if (modularPageLoaded) {
      loadInterface(interface)
    }
  }

  private def loadInterface(interface: Interface) = interface match {
    case module: Module => module.load(this)
    case iwd: InterfaceWithDefault => {
      replaceInterface(iwd.name, iwd.default, checkPageLoaded = false)    // Replace the interface with the default at load-time
      iwd.default.load(this)
    }
    case _ => throw new RuntimeException("No implementation defined for interface: %s".format(interface.name))
  }

  intercept.beforeRender {
    case html: tag.HTML => synchronized {
      if (!modularPageLoaded) {
        modularPageLoaded = true

        val headItems = head.contents.collect {
          case script: tag.Script => script
          case link: tag.Link => link
          case style: tag.Style => style
        }.toList
        interfaces.foreach(loadInterface)

        // Make sure module head functionality appears before everything else
        headItems.foreach {
          case h => h.removeFromParent()
        }
        headItems.foreach {
          case h => head.contents += h
        }
      }
    }
  }
}
