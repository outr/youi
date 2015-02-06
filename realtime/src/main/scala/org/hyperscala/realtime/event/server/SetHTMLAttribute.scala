package org.hyperscala.realtime.event.server

/**
 * SetHTMLAttribute is sent to the browser to modify an attribute on an HTML element.
 *
 * @param id the id of the element to modify
 * @param key the attribute name
 * @param value the new attribute value
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class SetHTMLAttribute(id: String, key: String, value: Any)