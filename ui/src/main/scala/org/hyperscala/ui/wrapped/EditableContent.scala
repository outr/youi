package org.hyperscala.ui.wrapped

import org.powerscala.{Color, Version, StorageComponent}
import org.hyperscala.html.{tag, HTMLTag}
import org.hyperscala.web.site.{WebpageConnection, Website, Webpage}
import org.hyperscala.realtime.Realtime
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.module.{Interface, Module}
import org.hyperscala.jquery.jQuery
import org.powerscala.event.Intercept
import org.hyperscala.io.HTMLToScala
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.Container
import org.jdom2.Element
import org.powerscala.event.processor.UnitProcessor

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

  def init() {
    Website().register("/js/editable_content.js", "editable_content.js")
  }

  def load() {
    Webpage().head.contents += new tag.Script(src = "/js/editable_content.js")
  }

  override def apply(tag: HTMLTag with Container[BodyChild]) = {
    Webpage().require(this)
    super.apply(tag)
  }

  protected def create(t: HTMLTag with Container[BodyChild]) = new EditableContent(t)
}

class EditableContent private(t: HTMLTag with Container[BodyChild]) {
  val contentChanged = new UnitProcessor[ContentChanged]("contentChanged")(t, implicitly[Manifest[ContentChanged]])

  init()

  private def init() = {
    t.contentEditable := ContentEditable.True

    Realtime.sendJavaScript(s"initEditableContent('${t.identity}');", forId = t.identity, onlyRealtime = false)
    t.eventReceived.on {
      case evt if evt.event == "htmlChanged" => {
        val htmlString = evt.message[String]("html")
        val xml = HTMLToScala.toXML(htmlString, clean = true)
        val body = xml.getChild("body")
        WebpageConnection.ignoreStructureChanges {      // Keep WebpageConnection from sending the changes to the client
          t.contents.clear()                            // Remove all contents
          t.read(body)                                  // Read the new data back in
        }
        contentChanged.fire(ContentChanged(xml, htmlString))
        Intercept.Stop
      }
      case _ => Intercept.Continue
    }
  }

  /**
   * Changes the document background color. In styleWithCss mode, it affects the background color of the containing
   * block instead. This requires a color value string to be passed in as a value argument.
   * (Internet Explorer uses this to set text background color.)
   *
   * @param color the background color to use
   */
  def backColor(color: Color) = execCommand("backColor", color)

  /**
   * Toggles bold on/off for the selection or at the insertion point. (Internet Explorer uses the STRONG tag instead
   * of B.)
   */
  def bold() = execCommand("bold")

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
   * Changes the font size for the selection or at the insertion point. This requires an HTML font size (1-7) to be
   * passed in as a value argument.
   *
   * @param size of the font
   */
  def fontSize(size: Int) = execCommand("fontSize", size)

  /**
   * Changes a font color for the selection or at the insertion point. This requires a color value string to be passed
   * in as a value argument.
   *
   * @param color to set the foreground to
   */
  def foreColor(color: Color) = execCommand("foreColor", color)

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
   * Centers the selection or insertion point.
   */
  def justifyCenter() = execCommand("justifyCenter")

  /**
   * Justifies the selection or insertion point.
   */
  def justifyFull() = execCommand("justifyFull")

  /**
   * Justifies the selection or insertion point to the left.
   */
  def justifyLeft() = execCommand("justifyLeft")

  /**
   * Right-justifies the selection or the insertion point.
   */
  def justifyRight() = execCommand("justifyRight")

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
   * Toggles subscript on/off for the selection or at the insertion point.
   */
  def subscript() = execCommand("subscript")

  /**
   * Toggles superscript on/off for the selection or at the insertion point.
   */
  def superscript() = execCommand("superscript")

  /**
   * Toggles underline on/off for the selection or at the insertion point.
   */
  def underline() = execCommand("underline")

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

  def execCommand(command: String, value: Any = null): Unit = {
    Realtime.sendJavaScript(s"editableContentExecCommand('${t.identity}', '$command', ${JavaScriptContent.toJS(value)});")
  }
}

case class ContentChanged(xml: Element, htmlString: String)