package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage

import org.powerscala.property._
import org.powerscala.{Country, Language}
import org.hyperscala.ui.widgets.visual.Visualize
import org.powerscala.property.event.PropertyChangeEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class VisualizeAdvancedExample extends Webpage {
  title := "Visualize Advanced Example"

  val messages = new tag.Div {
    style.padding.bottom := 10.px
  }
  body.contents += messages

  val instance = Property[Company]("company", Company("Doeco",
                                                      "contact@doeco.com",
                                                      CompanyAddress("Norman",
                                                                     "Oklahoma",
                                                                     "73069",
                                                                     Country.AG,
                                                                     List(CompanyEmail("John Doe", "john@doeco.com"),
                                                                          CompanyEmail("Jane Doe", "jane@doeco.com")))))

  val visualize = Visualize().clazz[Company](bindProperty = instance).field[List[String]]("Company.aliases") {
    case vb => vb.itemizedType[String]()
  }.field[List[Language]]("Company.languages") {
    case vb => vb.itemizedType[Language]()
  }.field[List[CompanyEmail]]("Company.address.emails") {
    case vb => vb.itemizedType[CompanyEmail]()
  }.renameGroup("address", "Mailing Address")
  body.contents += visualize.build()

  instance.listeners.synchronous {
    case evt: PropertyChangeEvent => {
      messages.contents += new tag.Div(content = "Company changed from %s to %s".format(evt.oldValue, evt.newValue))
    }
  }
}

case class Company(name: String = "", email: String = "", address: CompanyAddress = CompanyAddress(), aliases: List[String] = Nil, languages: List[Language] = Nil, receiveEmails: Boolean = false, age: Int = 10)

case class CompanyAddress(city: String = "", state: String = "", zip: String = "", country: Country = Country.EG, emails: List[CompanyEmail] = Nil)

case class CompanyEmail(emailName: String = "", emailValue: String = "")