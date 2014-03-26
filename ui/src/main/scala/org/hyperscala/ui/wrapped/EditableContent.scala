package org.hyperscala.ui.wrapped

import org.powerscala._
import org.hyperscala.html._
import org.hyperscala.web._
import org.hyperscala.realtime.{RealtimePage, Realtime}
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.module.Module
import org.hyperscala.jquery.jQuery
import org.powerscala.event.Intercept
import org.hyperscala.io.HTMLToScala
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.Container
import org.jdom2.Element
import org.powerscala.event.processor.UnitProcessor
import org.hyperscala.selector.Selector
import com.outr.net.http.session.Session

/**
 * EditableContent allows easy wrapping around an HTMLTag container to allow the use of HTML5's contentEditable flag
 * to be utilized. Use EditableContent(tag) to get a reference to the editable content and then invoke the provided
 * methods to easily control the editing of content.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object EditableContent extends Module with StorageComponent[EditableContent, HTMLTag with Container[BodyChild]] {
  def name = "EditableContent"
  def version = Version(1)

  override def dependencies = List(jQuery.LatestWithDefault, Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/editable_content.js", "editable_content.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(src = "/js/editable_content.js")
  }

  override def apply(tag: HTMLTag with Container[BodyChild]) = {
    tag.require(this)
    super.apply(tag)
  }

  protected def create(t: HTMLTag with Container[BodyChild]) = new EditableContent(t)
}

class EditableContent private(t: HTMLTag with Container[BodyChild]) {
  val contentChanged = new UnitProcessor[ContentChanged]("contentChanged")(t, implicitly[Manifest[ContentChanged]])
  val selectionChanged = new UnitProcessor[SelectionChanged]("selectionChanged")(t, implicitly[Manifest[SelectionChanged]])

  private val selection = new MappedStorage[String, Any] {}

  init()

  private def init() = t.connected[Webpage[Session]] {
    case webpage => {
      t.contentEditable := ContentEditable.True

      Realtime.sendJavaScript(webpage, s"initEditableContent('${t.identity}');", selector = Some(Selector.id(t.identity)), onlyRealtime = false)
      t.eventReceived.on {
        case evt if evt.event == "htmlChanged" => {
          val htmlString = evt.json.string("html")
          val xml = HTMLToScala.toXML(htmlString, clean = true)
          val body = xml.getChild("body")
          RealtimePage.ignoreStructureChanges {
            // Keep WebpageConnection from sending the changes to the client
            t.contents.clear() // Remove all contents
            t.read(body) // Read the new data back in
          }
          contentChanged.fire(ContentChanged(xml, htmlString))
          Intercept.Stop
        }
        case evt if evt.event == "selectionChanged" => {
          evt.json.toMap.foreach {
            case (key, value) => {
              val v = if (value.isString) {
                value.stringOrEmpty
              } else if (value.isNull) {
                null
              } else if (value.isBool) {
                value.bool.get
              } else if (value.isNumber) {
                value.numberOrZero
              } else {
                throw new RuntimeException(s"Unsupported conversion: $value")
              }
              selection(key) = v
            }
          }
          selectionChanged.fire(SelectionChanged(
            selectedText.getOrElse(null),
            selectedHTML.getOrElse(null),
            selectedStartOffset.getOrElse(-1),
            selectedEndOffset.getOrElse(-1)
          ))
          Intercept.Stop
          //        throw new RuntimeException(s"Not implemented: ${evt.json.toJson.spaces2}")
          //        evt.message.map.foreach {   // Update all changed values
          //          case (key, value) => selection(key) = value
          //        }
          //        selectionChanged.fire(SelectionChanged(
          //          selectedText.getOrElse(null),
          //          selectedHTML.getOrElse(null),
          //          selectedStartOffset.getOrElse(-1),
          //          selectedEndOffset.getOrElse(-1)
          //        ))
          //        Intercept.Stop
        }
        case _ => Intercept.Continue
      }
    }
  }

  /**
   * Determines whether selection events that occur on the client should propagate back to the server immediately.
   *
   * Defaults to false.
   */
  val realtimeSelection = t.dataWrapper[Boolean]("realtime-selection", false) {
    case b => b.toString
  }

  /**
   * Determines whether content changes that occur on the client should propagate back to the server immediately.
   * If this is set to false, changes propagate back upon blur.
   *
   * Defaults to false.
   */
  val realtimeContent = t.dataWrapper[Boolean]("realtime-content", false) {
    case b => b.toString
  }

  /**
   * The currently selected text without formatting. This only works if realtimeSelection is set to true.
   */
  def selectedText = selection.get[String]("text")

  /**
   * The current selected HTML. This only works if realtimeSelection is set to true.
   */
  def selectedHTML = selection.get[String]("html")

  /**
   * The current selected start offset. This only works if realtimeSelection is set to true.
   */
  def selectedStartOffset = selection.get[Double]("startOffset").map(d => d.toInt)

  /**
   * The current selected end offset. This only works if realtimeSelection is set to true.
   */
  def selectedEndOffset = selection.get[Double]("endOffset").map(d => d.toInt)

  /**
   * Changes the document background color. In styleWithCss mode, it affects the background color of the containing
   * block instead. This requires a color value string to be passed in as a value argument.
   * (Internet Explorer uses this to set text background color.)
   *
   * @param color the background color to use
   */
  def backColor(color: Color) = execCommand("backColor", color)

  /**
   * The current selected backColor. This only works if realtimeSelection is set to true.
   */
  def backColor = selection.get[String]("backColor").map(s => Color(s))

  /**
   * Toggles bold on/off for the selection or at the insertion point. (Internet Explorer uses the STRONG tag instead
   * of B.)
   */
  def bold() = execCommand("bold")

  /**
   * The current selected bold state. This only works if realtimeSelection is set to true.
   */
  def isBold = selection.get[Boolean]("bold")

  /**
   * Makes the content document either read-only or editable. This requires a boolean true/false to be passed in as a
   * value argument. (Not supported by Internet Explorer.)
   *
   * @param flag whether to set the content read-only
   */
  def contentReadOnly(flag: Boolean) = execCommand("contentReadOnly", flag)

  /**
   * Copies the current selection to the clipboard. Clipboard capability must be enabled in the user.js preference file.
   */
  def copy() = execCommand("copy")

  /**
   * Creates an anchor link from the selection, only if there is a selection. This requires the HREF URI string to be
   * passed in as a value argument. The URI must contain at least a single character, which may be a white space.
   * (Internet Explorer will create a link with a null URI value.)
   *
   * @param link to create
   */
  def createLink(link: String) = execCommand("createLink", link)

  /**
   * The current selected link value. This only works if realtimeSelection is set to true.
   */
  def link = selection.get[String]("createLink")

  /**
   * Cuts the current selection and copies it to the clipboard. Clipboard capability must be enabled in the user.js
   * preference file.
   */
  def cut() = execCommand("cut")

  /**
   * Adds a SMALL tag around the selection or at the insertion point. (Not supported by Internet Explorer.)
   */
  def decreaseFontSize() = execCommand("decreaseFontSize")

  /**
   * Deletes the current selection.
   */
  def delete() = execCommand("delete")

  /**
   * Enables or disables the table row and column insertion and deletion controls. (Not supported by Internet
   * Explorer.)
   */
  def enableInlineTableEditing() = execCommand("enableInlineTableEditing")

  /**
   * Enables or disables the resize handles on images and other resizable objects. (Not supported by Internet
   * Explorer.)
   */
  def enableObjectResizing() = execCommand("enableObjectResizing")

  /**
   * Changes the font name for the selection or at the insertion point. This requires a font name string ("Arial"
   * for example) to be passed in as a value argument.
   *
   * @param name of the font
   */
  def fontName(name: String) = execCommand("fontName", name)

  /**
   * The current selected font name. This only works if realtimeSelection is set to true.
   */
  def fontName = selection.get[String]("fontName")

  /**
   * Changes the font size for the selection or at the insertion point. This requires an HTML font size (1-7) to be
   * passed in as a value argument.
   *
   * @param size of the font
   */
  def fontSize(size: Int) = execCommand("fontSize", size)

  /**
   * The current selected font size. This only works if realtimeSelection is set to true.
   */
  def fontSize = selection.get[Int]("fontSize")

  /**
   * Changes a font color for the selection or at the insertion point. This requires a color value string to be passed
   * in as a value argument.
   *
   * @param color to set the foreground to
   */
  def foreColor(color: Color) = execCommand("foreColor", color)

  /**
   * The current selected foreground color. This only works if realtimeSelection is set to true.
   */
  def foreColor = selection.get[String]("foreColor").map(s => Color(s))

  /**
   * Adds an HTML block-style tag around the line containing the current selection, replacing the block element
   * containing the line if one exists (in Firefox, BLOCKQUOTE is the exception - it will wrap any containing block
   * element). Requires a tag-name string to be passed in as a value argument. Virtually all block style tags can be
   * used (eg. "H1", "P", "DL", "BLOCKQUOTE"). (Internet Explorer supports only heading tags H1 - H6, ADDRESS, and
   * PRE, which must also include the tag delimiters < >, such as "<H1>".)
   *
   * @param tagName to format with
   */
  def formatBlock(tagName: String) = execCommand("formatBlock", tagName)

  /**
   * Deletes the character ahead of the cursor's position.  It is the same as hitting the delete key.
   */
  def forwardDelete() = execCommand("forwardDelete")

  /**
   * Adds a heading tag around a selection or insertion point line. Requires the tag-name string to be passed in as a
   * value argument (i.e. "H1", "H6"). (Not supported by Internet Explorer.)
   *
   * @param tagName to format with
   */
  def heading(tagName: String) = execCommand("heading")

  /**
   * Changes the background color for the selection or at the insertion point. Requires a color value string to be
   * passed in as a value argument. UseCSS must be turned on for this to function. (Not supported by Internet
   * Explorer.)
   *
   * @param color to highlight the background upon selection
   */
  def hiliteColor(color: Color) = execCommand("hiliteColor", color)

  /**
   * The current selected selection background color. This only works if realtimeSelection is set to true.
   */
  def hiliteColor = selection.get[String]("hiliteColor").map(s => Color(s))

  /**
   * Adds a BIG tag around the selection or at the insertion point. (Not supported by Internet Explorer.)
   */
  def increaseFontSize() = execCommand("increaseFontSize")

  /**
   * Indents the line containing the selection or insertion point. In Firefox, if the selection spans multiple lines
   * at different levels of indentation, only the least indented lines in the selection will be indented.
   */
  def indent() = execCommand("indent")

  /**
   * Controls whether the Enter key inserts a br tag or splits the current block element into two. (Not supported by
   * Internet Explorer.)
   */
  def insertBrOnReturn() = execCommand("insertBrOnReturn")

  /**
   * Inserts a horizontal rule at the insertion point (deletes selection).
   */
  def insertHorizontalRule() = execCommand("insertHorizontalRule")

  /**
   * Inserts an HTML string at the insertion point (deletes selection). Requires a valid HTML string to be passed in
   * as a value argument. (Not supported by Internet Explorer.)
   *
   * @param html to insert
   */
  def insertHTML(html: String) = execCommand("insertHTML", html)

  /**
   * Inserts an image at the insertion point (deletes selection). Requires the image SRC URI string to be passed in as
   * a value argument. The URI must contain at least a single character, which may be a white space. (Internet
   * Explorer will create a link with a null URI value.)
   *
   * @param uri to represent the src of the image
   */
  def insertImage(uri: String) = execCommand("insertImage", uri)

  /**
   * Creates a numbered ordered list for the selection or at the insertion point.
   */
  def insertOrderedList() = execCommand("insertOrderedList")

  /**
   * Creates a bulleted unordered list for the selection or at the insertion point.
   */
  def insertUnorderedList() = execCommand("insertUnorderedList")

  /**
   * Inserts a paragraph around the selection or the current line. (Internet Explorer inserts a paragraph at the
   * insertion point and deletes the selection.)
   */
  def insertParagraph() = execCommand("insertParagraph")

  /**
   * Inserts the given plain text at the insertion point (deletes selection).
   *
   * @param text to insert
   */
  def insertText(text: String) = execCommand("insertText", text)

  /**
   * Toggles italics on/off for the selection or at the insertion point. (Internet Explorer uses the EM tag instead
   * of I.)
   */
  def italic() = execCommand("italic")

  /**
   * The current selected italic state. This only works if realtimeSelection is set to true.
   */
  def isItalic = selection.get[Boolean]("italic")

  /**
   * Centers the selection or insertion point.
   */
  def justifyCenter() = execCommand("justifyCenter")

  /**
   * The current selected justify center state. This only works if realtimeSelection is set to true.
   */
  def isJustifyCenter = selection.get[Boolean]("justifyCenter")

  /**
   * Justifies the selection or insertion point.
   */
  def justifyFull() = execCommand("justifyFull")

  /**
   * The current selected justify full state. This only works if realtimeSelection is set to true.
   */
  def isJustifyFull = selection.get[Boolean]("justifyFull")

  /**
   * Justifies the selection or insertion point to the left.
   */
  def justifyLeft() = execCommand("justifyLeft")

  /**
   * The current selected justify left state. This only works if realtimeSelection is set to true.
   */
  def isJustifyLeft = selection.get[Boolean]("justifyLeft")

  /**
   * Right-justifies the selection or the insertion point.
   */
  def justifyRight() = execCommand("justifyRight")

  /**
   * The current selected justify right state. This only works if realtimeSelection is set to true.
   */
  def isJustifyRight = selection.get[Boolean]("justifyRight")

  /**
   * Outdents the line containing the selection or insertion point.
   */
  def outdent() = execCommand("outdent")

  /**
   * Pastes the clipboard contents at the insertion point (replaces current selection). Clipboard capability must be
   * enabled in the user.js preference file.
   */
  def paste() = execCommand("paste")

  /**
   * Redoes the previous undo command.
   */
  def redo() = execCommand("redo")

  /**
   * Removes all formatting from the current selection.
   */
  def removeFormat() = execCommand("removeFormat")

  /**
   * Selects all of the content of the editable region.
   */
  def selectAll() = execCommand("selectAll")

  /**
   * Toggles strikethrough on/off for the selection or at the insertion point.
   */
  def strikeThrough() = execCommand("strikeThrough")

  /**
   * The current selected strike-through state. This only works if realtimeSelection is set to true.
   */
  def isStrikeThrough = selection.get[Boolean]("strikeThrough")

  /**
   * Toggles subscript on/off for the selection or at the insertion point.
   */
  def subscript() = execCommand("subscript")

  /**
   * The current selected subscript state. This only works if realtimeSelection is set to true.
   */
  def isSubscript = selection.get[Boolean]("subscript")

  /**
   * Toggles superscript on/off for the selection or at the insertion point.
   */
  def superscript() = execCommand("superscript")

  /**
   * The current selected superscript state. This only works if realtimeSelection is set to true.
   */
  def isSuperscript = selection.get[Boolean]("superscript")

  /**
   * Toggles underline on/off for the selection or at the insertion point.
   */
  def underline() = execCommand("underline")

  /**
   * The current selected underline state. This only works if realtimeSelection is set to true.
   */
  def isUnderline = selection.get[Boolean]("underline")

  /**
   * Undoes the last executed command.
   */
  def undo() = execCommand("undo")

  /**
   * Removes the anchor tag from a selected anchor link.
   */
  def unlink() = execCommand("unlink")

  /**
   * Toggles the use of HTML tags or CSS for the generated markup. Requires a boolean true/false as a value argument.
   *
   * @param flag true modifies/generates style attributes in markup, false generates formatting elements.
   */
  def styleWithCSS(flag: Boolean) = execCommand("styleWithCSS", flag)

  def execCommand(command: String, value: Any = null): Unit = t.connected[Webpage[Session]] {
    case webpage => Realtime.sendJavaScript(webpage, s"editableContentExecCommand('${t.identity}', '$command', ${JavaScriptContent.toJS(value)});")
  }
}

case class ContentChanged(xml: Element, htmlString: String)

case class SelectionChanged(text: String, html: String, startOffset: Int, endOffset: Int)