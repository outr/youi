package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.site.{WebpageResource, Website, Webpage}
import org.powerscala.property.Property
import org.powerscala.event.Listenable
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.web.Scope
import com.outr.webcommunicator.netty.handler.RequestHandler
import com.outr.webcommunicator.netty._
import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}
import org.jboss.netty.handler.codec.http.HttpRequest
import org.hyperscala.event.EventReceived
import org.powerscala.bus.Routing
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Autocomplete extends tag.Input {
  val autocomplete = Autocompletified(this)
}

// TODO: re-test now that input.value change is fixed on realtime
class Autocompletified private(input: FormField) {
  input.identity

  private lazy val handler = new AutocompleteSearchHandler(this)
  private val changing = new AtomicBoolean(false)

  Webpage().require(jQueryUI, jQueryUI191)

  private implicit val thisInput = input

  val search = Property[String => Seq[AutocompleteResult]]("search", (s: String) => Nil)
  val autoFocus = Property[Boolean]("autoFocus", false)
  val delay = Property[Int]("delay", 300)
  val disabled = Property[Boolean]("disabled", false)
  val minLength = Property[Int]("minLength", 1)
  val multiple = Property[Boolean]("multiple", false)
  val highlight = Property[Boolean]("highlight", true)
  val caseSensitive = Property[Boolean]("caseSensitive", false)

  val selected = Property[List[String]]("values", Nil)
  selected.onChange {
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

  if (input.initialized) {
    autoCompletify()
  } else {
    input.onInit {
      autoCompletify()
    }
  }

  private def autoCompletify() = {
    val source = "/autocomplete/%s".format(input.identity)
    Website().registerSession(WebpageResource(source, handler, Scope.Request))
    // TODO: extract this into its own Module + .js file
    Realtime.sendJavaScript(
      """
        |function split(v) {
        | return v.split(/,\s*/);
        |}
        |
        |function extractLast(term) {
        | return split(term).pop();
        |}
        |
        |var %1$sAutocompleteMultiple = %7$s;
        |
        |$(function() {
        |  $('#%1$s').autocomplete({
        |    source: function(request, response) {
        |     var searchTerm = request.term;
        |     if (%1$sAutocompleteMultiple) {
        |       searchTerm = extractLast(searchTerm);
        |     }
        |     $.getJSON('%2$s', {
        |       term: searchTerm
        |     }, response);
        |    },
        |    search: function() {
        |     if (%1$sAutocompleteMultiple) {
        |       var term = extractLast(this.value);
        |       if (term.length < $(this).autocomplete('option', 'minLength')) {
        |         return false;
        |       }
        |     }
        |    },
        |    focus: function() {
        |     if (%1$sAutocompleteMultiple) {
        |       return false;
        |     }
        |    },
        |    select: function(event, ui) {
        |     if (%1$sAutocompleteMultiple) {
        |       var terms = split(this.value);
        |       terms.pop();
        |       terms.push(ui.item.value);
        |       jsFire($(this), 'autocompleteMultiSelect', {
        |         values: terms
        |       });
        |       terms.push('');
        |       this.value = terms.join(', ');
        |       return false;
        |     }
        |     jsFire($(this), 'autocompleteSelect', {
        |       value: ui.item.value
        |     });
        |    },
        |    autoFocus: %3$s,
        |    delay: %4$s,
        |    disabled: %5$s,
        |    minLength: %6$s,
        |    change: function() { jsFireChange($('#%1$s')); jsFireGenericEvent($('#%1$s'), 'change'); }
        |  }).data('autocomplete')._renderItem = function(ul, item) {
        |    return $('<li></li>').data('item.autocomplete', item).append($('<a></a>').html(item.label)).appendTo(ul);
        |  };
        |});
      """.stripMargin.format(input.id(), source, autoFocus(), delay(), disabled(), minLength(), multiple()), onlyRealtime = false)
    Listenable.listenTo(autoFocus, delay, disabled, minLength) {
      case evt: PropertyChangeEvent => sendChanges(evt)
    }
    input.listeners.synchronous {
      case EventReceived(event, message) if (event == "autocompleteSelect") => {
        val value = message.map("value").asInstanceOf[String]
        selected := List(value)
        Routing.Stop
      }
      case EventReceived(event, message) if (event == "autocompleteMultiSelect") => {
        val values = message.map("values").asInstanceOf[List[String]]
        selected := values
        Routing.Stop
      }
    }
    input.value.listeners.synchronous {
      case evt: PropertyChangeEvent if (!changing.get()) => {
        Realtime.sendJavaScript("$('#%s')[0].value = \"%s\";".format(input.id(), input.value()))
        selected := input.value().split(",").map(s => s.trim).toList
      }
    }
  }

  private def sendChanges(evt: PropertyChangeEvent) = evt.property.name() match {
    case "autoFocus" => Realtime.sendJavaScript("$('#%s').autocomplete('option', 'autoFocus', %s);".format(input.id(), autoFocus()))
    case "delay" => Realtime.sendJavaScript("$('#%s').autocomplete('option', 'delay', %s);".format(input.id(), delay()))
    case "disabled" => Realtime.sendJavaScript("$('#%s').autocomplete('option', 'disabled', %s);".format(input.id(), disabled()))
    case "minLength" => Realtime.sendJavaScript("$('#%s').autocomplete('option', 'minLength', %s);".format(input.id(), minLength()))
  }
}

object Autocompletified {
  def apply(input: FormField) = input.synchronized {
    input.get[Autocompletified]("autocompletified") match {
      case Some(a) => a
      case None => {
        val a = new Autocompletified(input)
        input("autocompletified") = a
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

class AutocompleteSearchHandler(autocompletified: Autocompletified) extends RequestHandler {
  def apply(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent) = event.getMessage match {
    case request: HttpRequest => {
      val term = request2URL(request).parameters("term").head
      val results = autocompletified.submit(term).map(Autocompletified.Result2JSON).mkString("[", ", ", "]")
      RequestHandler.streamString(results, context, request, "text/plain")
    }
  }
}

case class AutocompleteResult(label: String, value: String, category: String = "")