package org.hyperscala.event

import org.hyperscala.css.{Style, StyleSheet}
import org.powerscala.property.event.PropertyChangeEvent
import org.powerscala.property.Property

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class StylePropertyChangeEvent(val styleSheet: StyleSheet,
                               val style: Style[_],
                               val oldStyleValue: AnyRef,
                               val newStyleValue: AnyRef,
                               property: Property[StyleSheet],
                               oldValue: StyleSheet,
                               newValue: StyleSheet) extends PropertyChangeEvent[StyleSheet](property, oldValue, newValue)