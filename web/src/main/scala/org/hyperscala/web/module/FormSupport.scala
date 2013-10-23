package org.hyperscala.web.module

import org.hyperscala.module.Module
import org.powerscala.{Priority, Version}
import org.hyperscala.web.Website
import org.hyperscala.html.attributes.Method

/**
 * @author Matt Hicks <matt@outr.com>
 */
object FormSupport extends Module {
  val name = "formSupport"
  val version = Version(1)

  def init() = {
    Website().handlers.on({
      case (request, response) => {
        if (request.method == Method.Post) {
          // TODO: implement
        }

        response
      }
    }, Priority.High)
  }

  def load() = {}
}
