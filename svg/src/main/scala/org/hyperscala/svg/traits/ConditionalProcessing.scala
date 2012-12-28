package org.hyperscala.svg.traits

import org.hyperscala.svg.SVGTag
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait ConditionalProcessing {
  this: SVGTag =>

  val requiredFeatures = PropertyAttribute[List[String]]("requiredFeatures", Nil)
  val requiredExtensions = PropertyAttribute[List[String]]("requiredExtensions", Nil)
  val systemLanguage = PropertyAttribute[List[String]]("systemLanguage", Nil)
}
