package org.hyperscala.realtime.event.server

/**
 * Sent to the client to insert HTML into the page after the specified id.
 *
 * @param html the HTML to insert
 * @param after the id of the element to insert the HTML after (null if it should be the first element)
 * @param parent the id of the parent element to insert the HTML into (only specified if after is null)
 * @param append true if this should be appended to the end of the list
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class InsertHTMLContent(html: String, after: String, parent: String, append: Boolean)