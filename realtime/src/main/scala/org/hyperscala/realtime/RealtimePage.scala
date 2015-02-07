package org.hyperscala.realtime

import com.outr.net.communicate.{ConnectionHolder, Connection}
import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.javascript.dsl.{Statement, JSFunction0}
import org.hyperscala.realtime.event.server._
import org.hyperscala.svg.{Svg, SVGTag}
import org.hyperscala.web.event.server.InvokeJavaScript
import org.hyperscala.{Textual, PropertyAttribute, Container, IdentifiableTag}
import org.hyperscala.css.{Style, StyleSheetAttribute, StyleSheet}
import org.hyperscala.event.BrowserEvent
import org.hyperscala.web.Webpage
import org.powerscala.hierarchy.ChildLike
import org.powerscala.{LocalStack, Priority}
import org.powerscala.hierarchy.event.{ChildRemovedEvent, ChildAddedEvent, Descendants}
import org.powerscala.json.TypedSupport
import org.powerscala.log.Logging
import org.powerscala.property.Property
import org.powerscala.property.event.PropertyChangeEvent

/**
 * RealtimePage is the management layer for Realtime specific to a webpage instance.
 *
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimePage[S <: Session] private(val webpage: Webpage[S]) extends Logging {
  private val _initialized = Property[Boolean](default = Some(false))
  def initialized = _initialized.readOnlyView

  // Fire all BrowserEvents on the specified tag
  webpage.json.partial(Unit) {
    case evt: BrowserEvent => evt.tag.eventReceived.fire(evt)
  }

  def init() = synchronized {
    if (!initialized()) {
      webpage.head.styleSpaces.removed.on {
        case evt => styleSheetRemoved(evt.value)
      }
      webpage.childAdded.listen(Priority.Normal, Descendants)(childAdded)
      webpage.childRemoved.listen(Priority.Normal, Descendants)(childRemoved)
      webpage.listen[PropertyChangeEvent[_], Unit, Unit]("change", Priority.Normal, Descendants)(propertyChanged)

      _initialized := true
    }
  }

  private def childAdded(evt: ChildAddedEvent) = {
    // TODO: provide exclusion to avoid synchronizing data coming from client back to server (one Connection - should send to all other connections)
    val parent = evt.parent.asInstanceOf[IdentifiableTag with Container[IdentifiableTag]]
    evt.child match {
      case child: SVGTag if !child.isInstanceOf[Svg] => throw new RuntimeException(s"SVG not yet supported!")
      case child: HTMLTag => {
        val index = parent.contents.indexOf(child)
        val afterId = if (index == 0) null else parent.contents(index - 1).identity
        val parentId = if (index == 0) parent.identity else null
        val html = child.outputString
        send(InsertHTMLContent(html, afterId, parentId))
      }
      case js: JavaScriptContent => throw new RuntimeException(s"JS not yet supported! ${js.content}")
    }
  }

  private def childRemoved(evt: ChildRemovedEvent) = {
    info(s"childRemoved: $evt")
    evt.child match {
      case t: IdentifiableTag => send(RemoveHTMLContent(t.identity))
    }
  }

  private def styleSheetRemoved(styleSheet: StyleSheet) = {
    info(s"stylesheetRemoved: $styleSheet")
    send(SetSelectorStyle(styleSheet.selectorString, null, null, important = false, styleSheet = true))
  }

  private def propertyChanged(evt: PropertyChangeEvent[_]) = evt.property match {
    case property: StyleSheetAttribute[_] => ChildLike.parentOf(property) match {
      case styleSheet: StyleSheet => ChildLike.parentOf(styleSheet) match {
        case styleSpaces: StyleSpaces => styleChanged(styleSheet.selectorString, property.style, evt.newValue.asInstanceOf[AnyRef], property.isImportant, styleSheet = true)
        case t: IdentifiableTag => styleChanged(s"#${t.identity}", property.style, evt.newValue.asInstanceOf[AnyRef], property.isImportant, styleSheet = false)
      }
    }
    case property: PropertyAttribute[_] => ChildLike.parentOf(property) match {
      case t: IdentifiableTag => tagPropertyChanged(t, property, evt.oldValue, evt.newValue)
    }
    case _ => // Ignore other properties
  }

  private def styleChanged(selector: String, style: Style[_], value: AnyRef, important: Boolean, styleSheet: Boolean) = {
    info(s"styleChanged: $selector, $style, $value, $important")
    val anyStyle = style.asInstanceOf[Style[AnyRef]]
    val cssName = style.cssName
    val cssValue = if (value != null) anyStyle.persistence.toString(value, cssName, value.getClass) else null
    send(SetSelectorStyle(selector, cssName, cssValue, important, styleSheet))
  }

  private def tagPropertyChanged(t: IdentifiableTag, property: PropertyAttribute[_], oldValue: Any, newValue: Any) = {
    if (property == t.id && oldValue == null) {
      // Ignore initial id change as it happens right before send
    } else {
      val excludeCurrentConnection = FormField.changingProperty == property && FormField.changingValue == newValue
      info(s"tagPropertyChanged: ${t.xmlLabel} - $oldValue changed to $newValue, Exclude current connection? $excludeCurrentConnection")
      webpage.intercept.renderAttribute.fire(property) match {
        case Some(attribute) => {
          if (t.isInstanceOf[Textual] && property.name == "content") {
            val parent = t.parent.asInstanceOf[HTMLTag with Container[IdentifiableTag]]
            send(SetHTMLAttribute(parent.identity, "content", parent.outputChildrenString), excludeCurrentConnection)
          } else if (t.id == property) {
            send(SetHTMLAttribute(oldValue.asInstanceOf[String], attribute.name, attribute.attributeValue), excludeCurrentConnection)
          } else {
            send(SetHTMLAttribute(t.identity, attribute.name, attribute.attributeValue), excludeCurrentConnection)
          }
        }
        case None => // Attribute change was rejected for render, so we don't send the change
      }
    }
  }

  def send(message: AnyRef, excludeCurrentConnection: Boolean = false) = if (!RealtimePage.dontSend) {     // Make sure we're not ignoring all changes in this thread
    if (excludeCurrentConnection) {
      webpage.broadcastJSON(message, ConnectionHolder.connection)
    } else {
      webpage.broadcastJSON(message)
    }
  }
}

object RealtimePage {
  private val _dontSend = new LocalStack[Boolean]

  TypedSupport.register("insertHTML", classOf[InsertHTMLContent])
  TypedSupport.register("removeHTML", classOf[RemoveHTMLContent])
  TypedSupport.register("attributeHTML", classOf[SetHTMLAttribute])
  TypedSupport.register("setStyle", classOf[SetSelectorStyle])
  TypedSupport.register("eval", classOf[InvokeJavaScript])

  def apply[S <: Session](webpage: Webpage[S]) = webpage.store.getOrSet[RealtimePage[S]]("realtime", new RealtimePage(webpage))

  def dontSend = _dontSend.get().getOrElse(false)

  def dontSend[R](f: => R): R = {
    _dontSend.push(true)
    try {
      f
    } finally {
      _dontSend.pop(true)
    }
  }
}