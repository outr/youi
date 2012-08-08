package org.hyperscala.examples.basic

import org.hyperscala.web.{ActionForm, AJAXForm, HTMLPage}
import org.hyperscala.html._
import org.hyperscala.bean.BeanForm
import org.powerscala.property._
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.editor.{ListBeanEditor, ListSelectEditor, ListStringEditor, ValueEditor}
import org.powerscala.{Language, Country}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class AdvancedBeanFormExample extends HTMLPage {
  title := "Advanced BeanForm Example"

  head.contents += new Script(src = "/js/jquery-1.7.2.js")

  val messages = new Div {
    style.padding.bottom := 10.px
  }
  contents += messages

  val form = new BeanForm[Company](Company()) with AJAXForm with ActionForm {
    property.listeners.synchronous {
      case evt: PropertyChangeEvent => {
        messages.contents += "Company changed from %s to %s".format(evt.oldValue, evt.newValue)
        messages.contents += new Br
      }
    }

    hideFields("email", "address.state")

    override def createEditor[C](property: StandardProperty[C])(implicit manifest: Manifest[C]) = {
      if (property.name() == "aliases") {
        // TODO: fix the stupid casting problem
        new ListStringEditor(property.asInstanceOf[StandardProperty[List[String]]]).asInstanceOf[ValueEditor[C]]
      } else if (property.name() == "languages") {
        val editor = new ListSelectEditor[Language](property.asInstanceOf[StandardProperty[List[Language]]])
        editor.valueEditor.values := Language.values.toList
        editor.asInstanceOf[ValueEditor[C]]
      } else if (property.name() == "emails") {
        val editor = new ListBeanEditor[CompanyEmail](this, property.asInstanceOf[StandardProperty[List[CompanyEmail]]], CompanyEmail())
        editor.asInstanceOf[ValueEditor[C]]
      } else {
        super.createEditor(property)(manifest)
      }
    }
  }
  contents += form
}

case class Company(name: String = "", email: String = "", address: CompanyAddress = CompanyAddress(), aliases: List[String] = Nil, languages: List[Language] = Nil, receiveEmails: Boolean = false, age: Int = 10)

case class CompanyAddress(city: String = "", state: String = "", zip: String = "", country: Country = Country.EG, emails: List[CompanyEmail] = Nil)

case class CompanyEmail(emailName: String = "", emailValue: String = "")