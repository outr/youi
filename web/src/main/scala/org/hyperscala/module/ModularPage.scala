package org.hyperscala.module

import org.hyperscala.html._
import org.hyperscala.Page
import org.hyperscala.web.Website
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.event.Intercept
import org.powerscala.event.processor.{EventToken, TokenProcessor}

/**
 * ModularPage represents all the functionality within a Webpage for dealing with Modules and Interfaces.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait ModularPage {
  this: Page =>

  private val modularPageLoaded = new AtomicBoolean(false)
  private var interfaces = List.empty[Interface]

  val modulesLoading = new TokenProcessor("modulesLoading")
  val modulesLoaded = new TokenProcessor("modulesLoaded")

  def module(name: String) = interfaces.find(i => i.name == name)

  def require(interface: Interface): Unit = synchronized {
    val existing = module(interface.name)
    interface match {
      case module: Module => requireModule(module, existing)
      case iwd: InterfaceWithDefault => requireInterfaceWithDefault(iwd, existing)
      case _ => if (existing.isEmpty) addInterface(interface)
    }
  }

  def require(interface: Interface, default: Module): Unit = interface match {
    case module: Module => throw new RuntimeException(s"Requiring with default must not be a Module ($interface / $module).")
    case iwd: InterfaceWithDefault => throw new RuntimeException("Requiring with default must not be a InterfaceWithDefault.")
    case _ => require(InterfaceWithDefault(interface, default))
  }

  private def requireModule(module: Module, existing: Option[Interface]) = existing match {
    case Some(i) => i match {
      case e: Module => e.version.compare(module.version) match {
        case 1 => // Nothing changes, the current is the newer version
        case 0 => // Nothing changes, they are both the same
        case -1 => replaceInterface(e.name, module)
      }
      case _ => replaceInterface(i.name, module)
    }
    case None => addInterface(module)
  }

  private def requireInterfaceWithDefault(iwd: InterfaceWithDefault, existing: Option[Interface]) = existing match {
    case Some(i) => i match {
      case e: Module => // Nothing changes, module supersedes default
      case e: InterfaceWithDefault => e.default.version.compare(iwd.default.version) match {
        case 1 => // Nothing changes, the current default is the newer version
        case 0 => // Nothing changes, they are both the same
        case -1 => replaceInterface(e.name, iwd)
      }
    }
    case None => addInterface(iwd)
  }

  private def replaceInterface(name: String, replacement: Interface, checkPageLoaded: Boolean = true) = {
    if (checkPageLoaded && modularPageLoaded.get()) {
      throw new RuntimeException("Module with name '%s' is already loaded: %s. Cannot replace after page load with: %s!".format(name, module(name).get, replacement))
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
    if (modularPageLoaded.get()) {
      loadInterface(interface)
    }
  }

  private def loadInterface(interface: Interface) = interface match {
    case module: Module => loadModule(module)
    case iwd: InterfaceWithDefault => {
      replaceInterface(iwd.name, iwd.default, checkPageLoaded = false)    // Replace the interface with the default at load-time
      loadModule(iwd.default)
    }
    case _ => throw new RuntimeException("No implementation defined for interface: %s".format(interface.name))
  }

  /**
   * Makes sure the module is initialized the first use in the website and then loads it.
   */
  private def loadModule(module: Module) = {
    val initialized = Website().application.getOrElse("initializedModules", Set.empty[Module])
    if (!initialized.contains(module)) {
      module.init()
      Website().application("initializedModules") = initialized + module
    }
    debug("Loading module: %s".format(module.getClass.getSimpleName))
    module.load()
  }

  intercept.beforeRender.on {
    case html: tag.HTML => {
      if (modularPageLoaded.compareAndSet(false, true)) {
        modulesLoading.fire(EventToken)
        val headItems = html.head.contents.collect {
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
          case h => html.head.contents += h
        }
        modulesLoaded.fire(EventToken)
      }
      Intercept.Continue
    }
    case _ => Intercept.Continue
  }
}