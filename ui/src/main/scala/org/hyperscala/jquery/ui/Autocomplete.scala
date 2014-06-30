package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.realtime.Realtime
import org.hyperscala.web._
import org.powerscala.property.Property
import org.powerscala.event.Intercept
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.event.EventReceived
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.{Version, Storage}
import org.hyperscala.module.Module
import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import com.outr.net.http.content.StringContent
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Autocomplete extends tag.Input {
  val autocomplete = Autocompletified(this)
}

class Autocompletified private(val input: FormField) {
  input.identity
  input.require(Autocomplete)

  private val changing = new AtomicBoolean(false)

  private implicit val thisInput = input

  val search = Property[String => Seq[AutocompleteResult]](default = Some((s: String) => Nil))
  val autoFocus = Property[Boolean](default = Some(false))
  val delay = Property[Int](default = Some(300))
  val appendTo = Property[HTMLTag]()
  val disabled = Property[Boolean](default = Some(false))
  val minLength = Property[Int](default = Some(1))
  val multiple = Property[Boolean](default = Some(false))
  val highlight = Property[Boolean](default = Some(true))
  val caseSensitive = Property[Boolean](default = Some(false))

  val selected = Property[List[String]](default = Some(Nil))
  selected.change.on {
    case evt => {
      changing.set(true)
      try {
        val v = if (multiple()) {
          selected().map(e => "%s, ".format(e)).mkString
        } else if (selected().nonEmpty) {
          selected().head
        } else {
          ""
        }
        input.value := v
      } finally {
        changing.set(false)
      }
    }
  }
  def firstSelected = selected().headOption

  def submit(query: String) = {
    val term = caseSensitive() match {
      case true => query
      case false => query.toLowerCase
    }
    // TODO: make multiple a server-side operation
    val results = search()(term)
    if (highlight()) {
      results.map {
        case AutocompleteResult(label, value, category) => {
          val index = label.toLowerCase.indexOf(term)
          if (index != -1) {
            val b = new StringBuilder
            if (index > 0) {
              b.append(label.substring(0, index))
            }
            b.append("<strong>")
            b.append(label.substring(index, index + term.length))
            b.append("</strong>")
            if (index != label.length - 1) {
              b.append(label.substring(index + term.length))
            }
            AutocompleteResult(b.toString(), value, category)
          } else {
            AutocompleteResult(label, value, category)
          }
        }
      }
    } else {
      results
    }
  }

  input.connected[Webpage[_ <: Session]] {
    case webpage => autoCompletify(webpage)
  }

  private def autoCompletify[S <: Session](webpage: Webpage[S]) = {
    val appendId = appendTo() match {
      case null => null
      case t => s"'t.id()'"
    }
    Realtime.sendJavaScript(webpage, s"autocompletify('${webpage.pageId}', '${input.id()}', ${multiple()}, ${autoFocus()}, ${delay()}, $appendId, ${disabled()}, ${minLength()})", onlyRealtime = false)
    autoFocus.change.on {
      case evt => sendChanges(webpage, evt)
    }
    delay.change.on {
      case evt => sendChanges(webpage, evt)
    }
    disabled.change.on {
      case evt => sendChanges(webpage, evt)
    }
    minLength.change.on {
      case evt => sendChanges(webpage, evt)
    }
    input.eventReceived.on {
      case EventReceived(event, json) if event == "autocompleteSelect" => {
        val value = json.string("value")
        selected := List(value)
        Intercept.Stop
      }
      case EventReceived(event, json) if event == "autocompleteMultiSelect" => {
        val values = json.strings("values")
        selected := values
        Intercept.Stop
      }
    }
    input.value.change.on {
      case evt => if (!changing.get()) {
        Realtime.sendJavaScript(webpage, "$('#%s')[0].value = \"%s\";".format(input.id(), input.value()))
        selected := (input.value() match {
          case null | "" => Nil
          case s => s.split(",").map(s => s.trim).toList
        })
      }
    }
  }

  private def sendChanges[S <: Session](webpage: Webpage[S], evt: PropertyChangeEvent[_]) = evt.property match {
    case p if p == autoFocus => Realtime.sendJavaScript(webpage, "$('#%s').autocomplete('option', 'autoFocus', %s);".format(input.id(), autoFocus()))
    case p if p == delay => Realtime.sendJavaScript(webpage, "$('#%s').autocomplete('option', 'delay', %s);".format(input.id(), delay()))
    case p if p == disabled => Realtime.sendJavaScript(webpage, "$('#%s').autocomplete('option', 'disabled', %s);".format(input.id(), disabled()))
    case p if p == minLength => Realtime.sendJavaScript(webpage, "$('#%s').autocomplete('option', 'minLength', %s);".format(input.id(), minLength()))
  }
}

object Autocompletified {
  def apply(input: FormField) = input.synchronized {
    Storage.get[String, Autocompletified](input, "autocompletified") match {
      case Some(a) => a
      case None => {
        val a = new Autocompletified(input)
        Storage.set(input, "autocompletified", a)
        a
      }
    }
  }

  val Result2JSON = (r: AutocompleteResult) => {
    """
      |{
      | "label": "%s",
      | "value": "%s",
      | "category": "%s"
      |}
    """.stripMargin.format(r.label, r.value, r.category)
  }
}

object Autocomplete extends Module {
  val name = "autocomplete"
  val version = Version(1)

  override def dependencies = List(jQueryUI.LatestWithDefault, Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.addHandler(new AutocompleteHandler(website), "/autocomplete/request")
    website.register("/js/autocomplete.js", "autocomplete.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(src = "/js/autocomplete.js")
  }
}

class AutocompleteHandler[S <: Session](website: Website[S]) extends HttpHandler {
  def onReceive(request: HttpRequest, response: HttpResponse) = {
    val pageId = request.url.parameters.first("pageId")
    val fieldId = request.url.parameters.first("fieldId")
    val term = request.url.parameters.first("term")

    val page = website.pages.byId[Webpage[S]](pageId).getOrElse(throw new NullPointerException(s"Cannot find page by id: $pageId"))
    val input = page.getById[FormField](fieldId)
    val autocompletified = Autocompletified(input)
    val results = autocompletified.submit(term).map(Autocompletified.Result2JSON).mkString("[", ", ", "]")
    response.copy(content = StringContent(results), status = HttpResponseStatus.OK)
  }
}

case class AutocompleteResult(label: String, value: String, category: String = "")