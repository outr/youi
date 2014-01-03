package org.hyperscala

import org.powerscala.hierarchy.event.{DescendantProcessor, AncestorProcessor, StandardHierarchyEventProcessor}
import org.powerscala.event.processor.{ModifiableOptionProcessor, InterceptProcessor}
import org.powerscala.event.{Listenable, Intercept}
import org.powerscala.hierarchy.ChildLike

/**
 * Convenience intercepting of actions in a page.
 *
 * @author Matt Hicks <matt@outr.com>
 */
class MarkupIntercepting(parentInterceptor: MarkupIntercepting) extends Listenable with ChildLike[MarkupIntercepting] {
  protected def hierarchicalParent = parentInterceptor

  /**
   * Called during initialization of the Markup instance. This occurs only once per instance and immediately before the
   * first rendering.
   */
  val init = new StandardHierarchyEventProcessor[Markup]("init")
  /**
   * Called before rendering of the Markup instance.
   */
  val beforeRender = new InterceptProcessor[Markup]("beforeRender") with AncestorProcessor[Markup, Intercept, Intercept] with DescendantProcessor[Markup, Intercept, Intercept]
  /**
   * Called immediately after rendering of the Markup instance.
   */
  val afterRender = new StandardHierarchyEventProcessor[Markup]("afterRender")
  /**
   * Called upon initialization of a PropertyAttribute.
   */
  val initAttribute = new StandardHierarchyEventProcessor[PropertyAttribute[_]]("initAttribute")
  /**
   * Called upon rendering of a PropertyAttribute. Returning Routing.Stop will cause the property not to be rendered.
   */
  val renderAttribute = new RenderAttributeProcessor()
  /**
   * Called upon update of Page instance.
   */
  val update = new StandardHierarchyEventProcessor[Page]("update")
}

class RenderAttributeProcessor(implicit listenable: Listenable)
  extends ModifiableOptionProcessor[PropertyAttribute[_]]("renderAttribute")
  with AncestorProcessor[PropertyAttribute[_], Option[PropertyAttribute[_]], Option[PropertyAttribute[_]]]
  with DescendantProcessor[PropertyAttribute[_], Option[PropertyAttribute[_]], Option[PropertyAttribute[_]]]