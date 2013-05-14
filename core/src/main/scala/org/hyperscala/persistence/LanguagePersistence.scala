package org.hyperscala.persistence

import org.powerscala.Language

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
// TODO: use ISO codes instead
object LanguagePersistence extends ValuePersistence[Language] {
  def fromString(s: String, clazz: Class[_]) = Language(s)

  def toString(t: Language, clazz: Class[_]) = t.name
}
