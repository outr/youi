package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag
import org.hyperscala.selector.Selector
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object document extends document(prefix = null)

class document(prefix: String = null) extends DelayedStatement[HTMLTag] with Selector {
  def thisValue = if (prefix != null) {
    s"$prefix.document"
  } else {
    "document"
  }

  def parentSelector = None

  def parent = documentParent

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
    MultiStatement[Unit](sideEffects = true, s"document.execCommand('${command.name}'", ", ", value, ")")
  }
}

object documentParent extends ExistingStatement[HTMLTag]("parent", sideEffects = false)

sealed abstract class Command extends EnumEntry

object Command extends Enumerated[Command] {
  /**
   * Changes the document background color. In styleWithCss mode, it affects the background color of the containing block instead. This requires a color value string to be passed in as a value argument. (Internet Explorer uses this to set text background color.)
   */
  case object backColor extends Command

  /**
   * Toggles bold on/off for the selection or at the insertion point. (Internet Explorer uses the STRONG tag instead of B.)
   */
  case object bold extends Command

  /**
   * Makes the content document either read-only or editable. This requires a boolean true/false to be passed in as a value argument. (Not supported by Internet Explorer.)
   */
  case object contentReadOnly extends Command

  /**
   * Copies the current selection to the clipboard. Clipboard capability must be enabled in the user.js preference file. See
   */
  case object copy extends Command

  /**
   * Creates an anchor link from the selection, only if there is a selection. This requires the HREF URI string to be passed in as a value argument. The URI must contain at least a single character, which may be a white space. (Internet Explorer will create a link with a null URI value.)
   */
  case object createLink extends Command

  /**
   * Cuts the current selection and copies it to the clipboard. Clipboard capability must be enabled in the user.js preference file. See
   */
  case object cut extends Command

  /**
   * Adds a SMALL tag around the selection or at the insertion point. (Not supported by Internet Explorer.)
   */
  case object decreaseFontSize extends Command

  /**
   * Deletes the current selection.
   */
  case object delete extends Command

  /**
   * Enables or disables the table row and column insertion and deletion controls. (Not supported by Internet Explorer.)
   */
  case object enableInlineTableEditing extends Command

  /**
   * Enables or disables the resize handles on images and other resizable objects. (Not supported by Internet Explorer.)
   */
  case object enableObjectResizing extends Command

  /**
   * Changes the font name for the selection or at the insertion point. This requires a font name string ("Arial" for example) to be passed in as a value argument.
   */
  case object fontName extends Command

  /**
   * Changes the font size for the selection or at the insertion point. This requires an HTML font size (1-7) to be passed in as a value argument.
   */
  case object fontSize extends Command

  /**
   * Changes a font color for the selection or at the insertion point. This requires a color value string to be passed in as a value argument.
   */
  case object foreColor extends Command

  /**
   * Adds an HTML block-style tag around the line containing the current selection, replacing the block element containing the line if one exists (in Firefox, BLOCKQUOTE is the exception - it will wrap any containing block element). Requires a tag-name string to be passed in as a value argument. Virtually all block style tags can be used (eg. "H1", "P", "DL", "BLOCKQUOTE"). (Internet Explorer supports only heading tags H1 - H6, ADDRESS, and PRE, which must also include the tag delimiters < >, such as "<H1>".)
   */
  case object formatBlock extends Command

  /**
   * Deletes the character ahead of the cursor's position.  It is the same as hitting the delete key.
   */
  case object forwardDelete extends Command

  /**
   * Adds a heading tag around a selection or insertion point line. Requires the tag-name string to be passed in as a value argument (i.e. "H1", "H6"). (Not supported by Internet Explorer and Safari.)
   */
  case object heading extends Command

  /**
   * Changes the background color for the selection or at the insertion point. Requires a color value string to be passed in as a value argument. UseCSS must be turned on for this to function. (Not supported by Internet Explorer.)
   */
  case object hiliteColor extends Command

  /**
   * Adds a BIG tag around the selection or at the insertion point. (Not supported by Internet Explorer.)
   */
  case object increaseFontSize extends Command

  /**
   * Indents the line containing the selection or insertion point. In Firefox, if the selection spans multiple lines at different levels of indentation, only the least indented lines in the selection will be indented.
   */
  case object indent extends Command

  /**
   * Controls whether the Enter key inserts a br tag or splits the current block element into two. (Not supported by Internet Explorer.)
   */
  case object insertBrOnReturn extends Command

  /**
   * Inserts a horizontal rule at the insertion point (deletes selection).
   */
  case object insertHorizontalRule extends Command

  /**
   * Inserts an HTML string at the insertion point (deletes selection). Requires a valid HTML string to be passed in as a value argument. (Not supported by Internet Explorer.)
   */
  case object insertHTML extends Command

  /**
   * Inserts an image at the insertion point (deletes selection). Requires the image SRC URI string to be passed in as a value argument. The URI must contain at least a single character, which may be a white space. (Internet Explorer will create a link with a null URI value.)
   */
  case object insertImage extends Command

  /**
   * Creates a numbered ordered list for the selection or at the insertion point.
   */
  case object insertOrderedList extends Command

  /**
   * Creates a bulleted unordered list for the selection or at the insertion point.
   */
  case object insertUnorderedList extends Command

  /**
   * Inserts a paragraph around the selection or the current line. (Internet Explorer inserts a paragraph at the insertion point and deletes the selection.)
   */
  case object insertParagraph extends Command
  /**
   * Inserts the given plain text at the insertion point (deletes selection).
   */
  case object insertText extends Command

  /**
   * Toggles italics on/off for the selection or at the insertion point. (Internet Explorer uses the EM tag instead of I.)
   */
  case object italic extends Command

  /**
   * Centers the selection or insertion point.
   */
  case object justifyCenter extends Command

  /**
   * Justifies the selection or insertion point.
   */
  case object justifyFull extends Command

  /**
   * Justifies the selection or insertion point to the left.
   */
  case object justifyLeft extends Command

  /**
   * Right-justifies the selection or the insertion point.
   */
  case object justifyRight extends Command

  /**
   * Outdents the line containing the selection or insertion point.
   */
  case object outdent extends Command

  /**
   * Pastes the clipboard contents at the insertion point (replaces current selection). Clipboard capability must be enabled in the user.js preference file. See
   */
  case object paste extends Command

  /**
   * Redoes the previous undo command.
   */
  case object redo extends Command

  /**
   * Removes all formatting from the current selection.
   */
  case object removeFormat extends Command

  /**
   * Selects all of the content of the editable region.
   */
  case object selectAll extends Command

  /**
   * Toggles strikethrough on/off for the selection or at the insertion point.
   */
  case object strikeThrough extends Command

  /**
   * Toggles subscript on/off for the selection or at the insertion point.
   */
  case object subscript extends Command

  /**
   * Toggles superscript on/off for the selection or at the insertion point.
   */
  case object superscript extends Command

  /**
   * Toggles underline on/off for the selection or at the insertion point.
   */
  case object underline extends Command

  /**
   * Undoes the last executed command.
   */
  case object undo extends Command

  /**
   * Removes the anchor tag from a selected anchor link.
   */
  case object unlink extends Command

  /**
   * Toggles the use of HTML tags or CSS for the generated markup. Requires a boolean true/false as a value argument. NOTE: This argument is logically backwards (i.e. use false to use CSS, true to use HTML). (Not supported by Internet Explorer.) This has been deprecated; use the styleWithCSS command instead.
   */
  case object useCSS extends Command

  /**
   * Replaces the useCSS command; argument works as expected, i.e. true modifies/generates style attributes in markup, false generates formatting elements.
   */
  case object styleWithCSS extends Command

  val values = findValues.toVector
}