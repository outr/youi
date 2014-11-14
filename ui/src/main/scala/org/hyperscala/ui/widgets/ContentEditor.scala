package org.hyperscala.ui.widgets

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ContentEditor extends Module with StorageComponent[ContentEditor, HTMLTag] {
  val name = "contentEditor"
  val version = Version(1)

  override def dependencies = List(jQuery, Realtime, Rangy)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/undo.js", "undo.js")
    website.register("/js/medium.js", "medium.js")
    website.register("/js/content_editor.js", "content_editor.js")
  }

  override def load[S <: Session](page: Webpage[S]) = {
    page.head.contents += new tag.Script(src = "/js/undo.js")
    page.head.contents += new tag.Script(src = "/js/medium.js")
    page.head.contents += new tag.Script(src = "/js/content_editor.js")
  }

  override def apply(t: HTMLTag) = {
    t.require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new ContentEditor(t)
}

class ContentEditor private(val container: HTMLTag) extends Listenable {
  private val clientChanging = new AtomicBoolean
  val content = Property(default = Some(container.outputChildrenString))
  val updateContainerOnChange = Property(default = Some(true))

  init()

  // Handle propagation of content changes to the container
  content.change.on {
    case evt => if (updateContainerOnChange()) {    // Make sure changes should propagate to container
      updateContainer(!clientChanging())            // Only propagate if the client isn't causing this event
    }
  }

  /**
   * Updates the container with the content property.
   *
   * @param propagate defines whether the change should propagate up to the client
   */
  def updateContainer(propagate: Boolean) = {
    if (!propagate) {
      RealtimePage.ignoreStructureChanges {
        HTMLToScala.replaceChildren(container.asInstanceOf[HTMLTag with Container[_ <: HTMLTag]], content())
      }
    } else {
      HTMLToScala.replaceChildren(container.asInstanceOf[HTMLTag with Container[_ <: HTMLTag]], content())
    }
  }

  private def init() = {
    // Initialize the container in the browser
    container.connected[Webpage[MapSession]] {
      case webpage => {
        Realtime.sendJavaScript(webpage, s"initContentEditor('${container.identity}')", selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }

    // Listen for content change events
    container.eventReceived.on {
      case evt => if (evt.event == "contentChanged") {
        clientChanging.attempt {
          val html = evt.json.string("html")
          content := html
        }
        Intercept.Stop
      } else {
        Intercept.Continue
      }
    }
  }

  def insert(t: HTMLTag) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        Realtime.sendJavaScript(webpage, s"insertIntoSelection('${container.identity}', content);", content = Some(t.outputString.trim))
      }
    }
  }

  def insertAround(t: HTMLTag with Container[_ <: HTMLTag]) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        Realtime.sendJavaScript(webpage, s"insertAroundSelection('${container.identity}', content);", content = Some(t.outputString.trim))
      }
    }
  }

  def insertBlock(t: HTMLTag with Container[_ <: HTMLTag]) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        Realtime.sendJavaScript(webpage, s"insertAroundBlock('${container.identity}', content);", content = Some(t.outputString.trim))
      }
    }
  }

  def removeFromBlock(tagName: String) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        Realtime.sendJavaScript(webpage, s"removeFromBlock('${container.identity}', '$tagName');")
      }
    }
  }

  def manipulateSelection(manipulator: JSFunction1[Array[tag.Span], Unit]) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js = s"manipulateSelection('${container.identity}', ${manipulator.content});"
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def adjustStyle[S](style: Style[S], adjuster: JSFunction1[S, S]) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js = s"adjustStyle('${container.identity}', '${style.name}', ${adjuster.content});"
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def adjustStyleSize[S](style: Style[S], adjustment: Int, min: Int = 0, max: Int = Int.MaxValue)(implicit manifest: Manifest[S]) = {
    val js =
      """
        |var regex = /(-?\d+[.]?\d*)(.*)/;
        |var result = regex.exec(p1);
        |var n = parseInt(result[1]);
        |var type = result[2];
        |var value = n + $adjustment
        |if (value < $min) {
        | value = $min
        |}
        |if (value > $max) {
        | value = $max
        |}
        |return value + type;
      """.stripMargin.replaceAll("[$]adjustment", adjustment.toString).replaceAll("[$]min", min.toString).replaceAll("[$]max", max.toString)
    adjustStyle[S](style, JSFunction1[S, S](js))
  }

  def toggleStyle[S](style: Style[S], values: Any*) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js = s"toggleStyle('${container.identity}', '${style.name}', [${values.map(JavaScriptContent.toJS).mkString(", ")}]);"
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def setStyle[S](style: Style[S], value: Any) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js = s"setStyle('${container.identity}', '${style.name}', ${JavaScriptContent.toJS(value)});"
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def bindInput[S](style: Style[S], input: tag.Input, format: Boolean, aliases: VisualAlias*) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        // Update the input when the selection changes
        val js =
          s"""
            |(function() {
            | var input = window.parent.document.getElementById('${input.identity}');
            | var visual2Internal = {${aliases.map(va => s"'${va.visual}': '${va.internal}'").mkString(", ")}};
            | var internal2Visual = {${aliases.map(va => s"'${va.internal}': '${va.visual}'").mkString(", ")}};
            |
            | addSelectionStyleChangeListener(document.getElementById('${container.identity}'), '${style.name}', function(oldValue, newValue) {
            |   var v = newValue;
            |   if (v in internal2Visual) {
            |     v = internal2Visual[v];
            |   }
            |   ${if (format) """v = v.capitalize().replace(/\s*,\s*/g, ', ');""" else ""}
            |   input.value = v;
            | });
            | input.addEventListener('change', function(event) {
            |   var v = input.value;
            |   if (v in visual2Internal) {
            |     v = visual2Internal[v];
            |   }
            |   setStyle('${container.identity}', '${style.name}', v);
            |
            |   return realtimeEvent(event, null, null, true, false, 0);
            | });
            |})();
          """.stripMargin
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)

        // Update the selection style when the input value changes
//        input.changeEvent.onRealtime {
//          case evt => Realtime.sendJavaScript(webpage, s"setStyle('${container.identity}', '${style.name}', ${JavaScriptContent.toJS(input.value())});")
//        }
      }
    }
  }

  def bindFontStyle(input: tag.Input) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        val js =
          s"""
            |(function() {
            | var container = document.getElementById('${container.identity}');
            | var input = window.parent.document.getElementById('${input.identity}');
            | var weight2Internal = {'Thin': '100', 'Light': '300', 'Medium': '500', 'Extra-Bold': '800', 'Ultra-Bold': '900'};
            | var weight2Visual = {'100': 'Thin', '300': 'Light', '400': 'Normal', '500': 'Medium', '700': 'Bold', '800': 'Extra-Bold', '900': 'Ultra-Bold'};
            |
            | var weight = null;
            | var style = null;
            |
            | var updateInput = function() {
            |   var s = '';
            |   if (weight != null && (weight.toLowerCase() != 'normal' || style == null || style.toLowerCase() == 'normal')) {
            |     s = weight;
            |   }
            |   if (style != null && style.toLowerCase() != 'normal') {
            |     if (s != '') {
            |       s += ' ';
            |     }
            |     s += style;
            |   }
            |   input.value = s.capitalize();
            | };
            |
            | addSelectionStyleChangeListener(container, 'fontWeight', function(oldValue, newValue) {
            |   var w = newValue;
            |   if (w in weight2Visual) {
            |     w = weight2Visual[w];
            |   }
            |   weight = w;
            |   updateInput();
            | });
            | addSelectionStyleChangeListener(container, 'fontStyle', function(oldValue, newValue) {
            |   var s = newValue;
            |   if (s == null) {
            |     s = 'normal';
            |   }
            |   style = s.capitalize();
            |   updateInput();
            | });
            | input.addEventListener('change', function(event) {
            |   var index = input.value.lastIndexOf(' ');
            |   var parser = [input.value];
            |   if (index > 0) {
            |     parser = [input.value.substring(0, index), input.value.substring(index + 1)];
            |   }
            |   for (var i = 0; i < parser.length; i++) {
            |     parser[i] = parser[i].capitalize();
            |   }
            |   var w = 'normal';
            |   var s = 'normal';
            |   if (parser.length == 1) {
            |     if (parser[0].toLowerCase() == 'italic' || parser[0].toLowerCase() == 'oblique') {
            |       s = parser[0];
            |     } else {
            |       w = parser[0];
            |       if (w in weight2Internal) {
            |         w = weight2Internal[w];
            |       }
            |     }
            |   } else {
            |     w = parser[0];
            |     if (w in weight2Internal) {
            |       w = weight2Internal[w];
            |     }
            |     s = parser[1];
            |   }
            |   w = w.toLowerCase();
            |   s = s.toLowerCase();
            |   console.log('value: ' + input.value + ' - weight: ' + w + ', style: ' + s);
            |   setStyle('${container.identity}', 'fontWeight', w);
            |   setStyle('${container.identity}', 'fontStyle', s);
            | });
            |})();
          """.stripMargin
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def bindToggle[S](style: Style[S], t: HTMLTag, values: Seq[Any], activeClass: Option[String] = None, inactiveClass: Option[String] = None, block: Boolean = false) = {
    container.connected[Webpage[MapSession]] {
      case webpage => {
        // Toggle the style when the tag is clicked on
        t.clickEvent.onRealtime {
          case evt => if (block) {
            Realtime.sendJavaScript(webpage, s"toggleBlockStyle('${container.identity}', '${style.name}', [${values.map(JavaScriptContent.toJS).mkString(", ")}], null);")
          } else {
            Realtime.sendJavaScript(webpage, s"toggleStyle('${container.identity}', '${style.name}', [${values.map(JavaScriptContent.toJS).mkString(", ")}], null);")
          }
        }

        // Update the class when the style is set
        val js =
          s"""
            |(function() {
            | var tag = $$(window.parent.document.getElementById('${t.identity}'));
            | var styleValues = [${values.map(JavaScriptContent.toJS).mkString(", ")}];
            | var activeClass = ${JavaScriptContent.toJS(activeClass.orNull)};
            | var inactiveClass = ${JavaScriptContent.toJS(inactiveClass.orNull)};
            |
            | addSelectionStyleChangeListener(document.getElementById('${container.identity}'), '${style.name}', function(oldValue, newValue) {
            |  if (${if (block) "selectionBlockHasStyle" else "selectionHasStyle"}('${style.name}', styleValues)) {
            |    if (inactiveClass != null) {
            |      tag.removeClass(inactiveClass);
            |    }
            |    if (activeClass != null) {
            |      tag.addClass(activeClass);
            |    }
            |  } else {
            |    if (activeClass != null) {
            |      tag.removeClass(activeClass);
            |    }
            |    if (inactiveClass != null) {
            |      tag.addClass(inactiveClass);
            |    }
            |  }
            | });
            |})();
          """.stripMargin
        Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(container)), onlyRealtime = false)
      }
    }
  }

  def clearFormatting() = container.connected[Webpage[MapSession]] {
    case webpage => Realtime.sendJavaScript(webpage, s"removeFormattingFromSelection('${container.identity}');", selector = Some(Selector.id(container)), onlyRealtime = false)
  }

  def exec(command: Command, value: String = null) = container.connected[Webpage[MapSession]] {
    case webpage => document.execCommand(command, value).send(webpage)
  }
}

case class VisualAlias(visual: String, internal: String)