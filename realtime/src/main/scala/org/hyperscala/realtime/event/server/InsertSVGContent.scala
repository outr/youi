package org.hyperscala.realtime.event.server

/**
 * Sent to the client to insert SVG into the page after the specified id.
 *
 * @param svg the SVG to insert
 * @param after the id of the element to insert the SVG after (null if it should be the first element)
 * @param parent the id of the parent element to insert the HTML into (only specified if after is null)
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class InsertSVGContent(svg: String, after: String, parent: String)