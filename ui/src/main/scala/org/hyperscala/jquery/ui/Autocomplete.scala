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

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Autocomplete extends tag.Input {
  val autocomplete = Autocompletified(this)
}

class Autocompletified private(input: tag.Input) {
  input.identity

  private lazy val handler = new AutocompleteSearchHandler(this)

  Webpage().require(jQueryUI, jQueryUI191)

  implicit val thisInput = input

  val search = Property[String => Seq[String]]("search", (s: String) => Nil)
  val autoFocus = Property[Boolean]("autoFocus", false)
  val delay = Property[Int]("delay", 300)
  val disabled = Property[Boolean]("disabled", false)
  val minLength = Property[Int]("minLength", 1)
  val multiple = Property[Boolean]("multiple", false)

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
        |       terms.push('');
        |       this.value = terms.join(', ');
        |       return false;
        |     }
        |    },
        |    autoFocus: %3$s,
        |    delay: %4$s,
        |    disabled: %5$s,
        |    minLength: %6$s,
        |    change: function() { jsFireChange($('#%1$s')); jsFireGenericEvent($('#%1$s'), 'change'); }
        |  });
        |});
      """.stripMargin.format(input.id(), source, autoFocus(), delay(), disabled(), minLength(), multiple()), onlyRealtime = false)
    Listenable.listenTo(autoFocus, delay, disabled, minLength) {
      case evt: PropertyChangeEvent => sendChanges(evt)
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
  def apply(input: tag.Input) = input.getOrSet[Autocompletified]("autocompletified", new Autocompletified(input))
}

class AutocompleteSearchHandler(autocompletified: Autocompletified) extends RequestHandler {
  def apply(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent) = event.getMessage match {
    case request: HttpRequest => {
      val term = request2URL(request).parameters("term").head
      val results = autocompletified.search()(term).map(s => "\"%s\"".format(s)).mkString("[", ", ", "]")
      RequestHandler.streamString(results, context, request, "text/plain")
    }
  }
}