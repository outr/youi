package org.hyperscala.persistence

import org.powerscala.Language

/**
 * @author Matt Hicks <matt@outr.com>
 */
// TODO: use ISO codes instead
object LanguagePersistence extends ValuePersistence[Language] {
  def fromString(s: String, name: String, clazz: Class[_]) = Language(s)

  def toString(t: Language, name: String, clazz: Class[_]) = t.name
}
