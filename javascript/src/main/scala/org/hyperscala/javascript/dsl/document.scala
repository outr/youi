package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.selector.Selector
import org.powerscala.enum.{EnumEntry, Enumerated}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object document extends DelayedStatement[HTMLTag] with Selector {
  def thisValue = "document"

  def parent = None

  def thisMatches(t: HTMLTag) = false

  override def quoted = false

  def toStatement = ExistingStatement[HTMLTag]("document")

  def getElementById[T <: HTMLTag](id: Statement[String]) = {
    WrappedStatement[T]("document.getElementById(", id, ")", sideEffects = false)
  }

  def writeln(line: Statement[String]) = {
    WrappedStatement[Unit]("document.writeln(", line, ")", sideEffects = true)
  }

  def duplicate(parent: Option[Selector]) = throw new RuntimeException("document cannot be duplicated")

  def execCommand(command: Command, value: String = null) = {
    MultiStatement[Unit](sideEffects = true, "document.execCommand(", command, ", ", value, ")")
  }
}

class Command extends EnumEntry with JavaScriptContent {
  override def content = s"'$name'"
}

object Command extends Enumerated[Command] {
  /**
   * Changes the document background color. In styleWithCss mode, it affects the background color of the containing block instead. This requires a color value string to be passed in as a value argument. (Internet Explorer uses this to set text background color.)
   */
  val backColor = new Command

  /**
   * Toggles bold on/off for the selection or at the insertion point. (Internet Explorer uses the STRONG tag instead of B.)
   */
  val bold = new Command

  /**
   * Makes the content document either read-only or editable. This requires a boolean true/false to be passed in as a value argument. (Not supported by Internet Explorer.)
   */
  val contentReadOnly = new Command

  /**
   * Copies the current selection to the clipboard. Clipboard capability must be enabled in the user.js preference file. See
   */
  val copy = new Command

  /**
   * Creates an anchor link from the selection, only if there is a selection. This requires the HREF URI string to be passed in as a value argument. The URI must contain at least a single character, which may be a white space. (Internet Explorer will create a link with a null URI value.)
   */
  val createLink = new Command

  /**
   * Cuts the current selection and copies it to the clipboard. Clipboard capability must be enabled in the user.js preference file. See
   */
  val cut = new Command

  /**
   * Adds a SMALL tag around the selection or at the insertion point. (Not supported by Internet Explorer.)
   */
  val decreaseFontSize = new Command

  /**
   * Deletes the current selection.
   */
  val delete = new Command

  /**
   * Enables or disables the table row and column insertion and deletion controls. (Not supported by Internet Explorer.)
   */
  val enableInlineTableEditing = new Command

  /**
   * Enables or disables the resize handles on images and other resizable objects. (Not supported by Internet Explorer.)
   */
  val enableObjectResizing = new Command

  /**
   * Changes the font name for the selection or at the insertion point. This requires a font name string ("Arial" for example) to be passed in as a value argument.
   */
  val fontName = new Command

  /**
   * Changes the font size for the selection or at the insertion point. This requires an HTML font size (1-7) to be passed in as a value argument.
   */
  val fontSize = new Command

  /**
   * Changes a font color for the selection or at the insertion point. This requires a color value string to be passed in as a value argument.
   */
  val foreColor = new Command

  /**
   * Adds an HTML block-style tag around the line containing the current selection, replacing the block element containing the line if one exists (in Firefox, BLOCKQUOTE is the exception - it will wrap any containing block element). Requires a tag-name string to be passed in as a value argument. Virtually all block style tags can be used (eg. "H1", "P", "DL", "BLOCKQUOTE"). (Internet Explorer supports only heading tags H1 - H6, ADDRESS, and PRE, which must also include the tag delimiters < >, such as "<H1>".)
   */
  val formatBlock = new Command

  /**
   * Deletes the character ahead of the cursor's position.  It is the same as hitting the delete key.
   */
  val forwardDelete = new Command

  /**
   * Adds a heading tag around a selection or insertion point line. Requires the tag-name string to be passed in as a value argument (i.e. "H1", "H6"). (Not supported by Internet Explorer and Safari.)
   */
  val heading = new Command

  /**
   * Changes the background color for the selection or at the insertion point. Requires a color value string to be passed in as a value argument. UseCSS must be turned on for this to function. (Not supported by Internet Explorer.)
   */
  val hiliteColor = new Command

  /**
   * Adds a BIG tag around the selection or at the insertion point. (Not supported by Internet Explorer.)
   */
  val increaseFontSize = new Command

  /**
   * Indents the line containing the selection or insertion point. In Firefox, if the selection spans multiple lines at different levels of indentation, only the least indented lines in the selection will be indented.
   */
  val indent = new Command

  /**
   * Controls whether the Enter key inserts a br tag or splits the current block element into two. (Not supported by Internet Explorer.)
   */
  val insertBrOnReturn = new Command

  /**
   * Inserts a horizontal rule at the insertion point (deletes selection).
   */
  val insertHorizontalRule = new Command

  /**
   * Inserts an HTML string at the insertion point (deletes selection). Requires a valid HTML string to be passed in as a value argument. (Not supported by Internet Explorer.)
   */
  val insertHTML = new Command

  /**
   * Inserts an image at the insertion point (deletes selection). Requires the image SRC URI string to be passed in as a value argument. The URI must contain at least a single character, which may be a white space. (Internet Explorer will create a link with a null URI value.)
   */
  val insertImage = new Command

  /**
   * Creates a numbered ordered list for the selection or at the insertion point.
   */
  val insertOrderedList = new Command

  /**
   * Creates a bulleted unordered list for the selection or at the insertion point.
   */
  val insertUnorderedList = new Command

  /**
   * Inserts a paragraph around the selection or the current line. (Internet Explorer inserts a paragraph at the insertion point and deletes the selection.)
   */
  val insertParagraph = new Command
  /**
   * Inserts the given plain text at the insertion point (deletes selection).
   */
  val insertText = new Command

  /**
   * Toggles italics on/off for the selection or at the insertion point. (Internet Explorer uses the EM tag instead of I.)
   */
  val italic = new Command

  /**
   * Centers the selection or insertion point.
   */
  val justifyCenter = new Command

  /**
   * Justifies the selection or insertion point.
   */
  val justifyFull = new Command

  /**
   * Justifies the selection or insertion point to the left.
   */
  val justifyLeft = new Command

  /**
   * Right-justifies the selection or the insertion point.
   */
  val justifyRight = new Command

  /**
   * Outdents the line containing the selection or insertion point.
   */
  val outdent = new Command

  /**
   * Pastes the clipboard contents at the insertion point (replaces current selection). Clipboard capability must be enabled in the user.js preference file. See
   */
  val paste = new Command

  /**
   * Redoes the previous undo command.
   */
  val redo = new Command

  /**
   * Removes all formatting from the current selection.
   */
  val removeFormat = new Command

  /**
   * Selects all of the content of the editable region.
   */
  val selectAll = new Command

  /**
   * Toggles strikethrough on/off for the selection or at the insertion point.
   */
  val strikeThrough = new Command

  /**
   * Toggles subscript on/off for the selection or at the insertion point.
   */
  val subscript = new Command

  /**
   * Toggles superscript on/off for the selection or at the insertion point.
   */
  val superscript = new Command

  /**
   * Toggles underline on/off for the selection or at the insertion point.
   */
  val underline = new Command

  /**
   * Undoes the last executed command.
   */
  val undo = new Command

  /**
   * Removes the anchor tag from a selected anchor link.
   */
  val unlink = new Command

  /**
   * Toggles the use of HTML tags or CSS for the generated markup. Requires a boolean true/false as a value argument. NOTE: This argument is logically backwards (i.e. use false to use CSS, true to use HTML). (Not supported by Internet Explorer.) This has been deprecated; use the styleWithCSS command instead.
   */
  val useCSS = new Command

  /**
   * Replaces the useCSS command; argument works as expected, i.e. true modifies/generates style attributes in markup, false generates formatting elements.
   */
  val styleWithCSS = new Command
}