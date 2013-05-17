package org.hyperscala.svg.traits

import org.hyperscala.svg.SVGTag
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait ConditionalProcessing {
  this: SVGTag =>

  lazy val requiredFeatures = PropertyAttribute[List[String]]("requiredFeatures", Nil)
  lazy val requiredExtensions = PropertyAttribute[List[String]]("requiredExtensions", Nil)
  lazy val systemLanguage = PropertyAttribute[List[String]]("systemLanguage", Nil)
}
