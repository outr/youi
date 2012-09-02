package org.hyperscala.editor

import org.powerscala.property.StandardProperty
import org.hyperscala.data.ListSelect
import org.powerscala.reflect._
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.web.HTMLPage
import org.hyperscala.web.live.LivePage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ListSelectEditor[T](val property: StandardProperty[List[T]])(implicit val manifest: Manifest[T]) extends ListEditor[T] {
  lazy val fieldName = "%sField".format(property.name())
  lazy val valueEditor = new ListSelect[T](new StandardProperty[T](fieldName, manifest.erasure.defaultForType.asInstanceOf[T]))

  if (!HTMLPage().isInstanceOf[LivePage]) {
    button.event.click := JavaScriptString("$('#%1$s').focus(); $(this).closest('form').submit();".format(fieldName))
  }
}