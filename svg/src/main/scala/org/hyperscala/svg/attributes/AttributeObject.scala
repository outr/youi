package org.hyperscala.svg.attributes

import org.powerscala.{EnumEntry, Enumerated}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait AttributeObject[E <: EnumEntry[E] with AttributeValue] extends Enumerated[E] with EnumEntryPersistence[E]