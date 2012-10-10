package org.hyperscala

import org.powerscala.bus.intercept.Interceptable
import org.powerscala.bus.Bus

/**
 * Convenience intercepting of actions in a page.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class MarkupIntercepting(name: String, bus: Bus, parent: MarkupIntercepting) {
  /**
   * Called during initialization of the Markup instance. This occurs only once per instance and immediately before the
   * first rendering.
   */
  val init: Interceptable[Markup] = Interceptable[Markup]("%s.init".format(name), bus, if (parent != null) parent.init else null)
  /**
   * Called before rendering of the Markup instance.
   */
  val beforeRender: Interceptable[Markup] = Interceptable[Markup]("%s.beforeRender".format(name), bus, if (parent != null) parent.beforeRender else null)
  /**
   * Called immediately after rendering of the Markup instance.
   */
  val afterRender: Interceptable[Markup] = Interceptable[Markup]("%s.afterRender".format(name), bus, if (parent != null) parent.afterRender else null)
  /**
   * Called upon initialization of a PropertyAttribute.
   */
  val initAttribute: Interceptable[PropertyAttribute[_]] = Interceptable[PropertyAttribute[_]]("%s.initAttribute".format(name), bus, if (parent != null) parent.initAttribute else null)
  /**
   * Called upon rendering of a PropertyAttribute. Returning Routing.Stop will cause the property not to be rendered.
   */
  val renderAttribute: Interceptable[PropertyAttribute[_]] = Interceptable[PropertyAttribute[_]]("%s.renderAttribute".format(name), bus, if (parent != null) parent.renderAttribute else null)
}
