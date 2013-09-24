package org.hyperscala.ui.widgets

import org.hyperscala.html._
import org.hyperscala.web.site.{WebpageConnection, Website, Webpage}
import org.hyperscala.realtime.Realtime
import org.hyperscala.module.Module
import org.powerscala.{Version, StorageComponent}
import org.hyperscala.jquery.jQuery
import org.hyperscala.web.WrappedComponent
import org.powerscala.event.Intercept
import org.powerscala.property.Property
import org.hyperscala.javascript.{JSFunction1, JavaScriptContent}
import org.hyperscala.{Container, IdentifiableTag}
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.io.HTMLToScala
import org.hyperscala.css.Style
import org.hyperscala.css.attributes.FontSize
import org.hyperscala.ui.clipboard.{ClipType, Clipboard}
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object RichEditor extends Module with StorageComponent[RichEditor, HTMLTag] {
  val BoldStyle = RichEditorStyle("strong", overrides = List(Override("b")))
  val ItalicStyle = RichEditorStyle("em", overrides = List(Override("i")))
  val UnderlineStyle = RichEditorStyle("u")
  val StrikeStyle = RichEditorStyle("s", overrides = List(Override("strike")))
  val SubscriptStyle = RichEditorStyle("sub")
  val SuperscriptStyle = RichEditorStyle("sup")
  def FontSizeStyle(size: FontSize) = RichEditorStyle("span", styles = Map("font-size" -> size), overrides = List(Override("font", Map("size" -> null))))

  def name = "RichEditor"
  def version = Version(2)

  override def dependencies = List(jQuery.LatestWithDefault, Realtime, CKEditor)

  def init() = {
    Website().register("/js/rich_editor.js", "rich_editor.js")
  }

  def load() = {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/rich_editor.js")
  }

  override def apply(t: HTMLTag) = {
    Webpage().require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new RichEditor(t)
}

class RichEditor private(val wrapped: HTMLTag) extends WrappedComponent[HTMLTag] {
  import RichEditor._

  /**
   * Configures the Clipboard module to integrate with this editor.
   */
  def enableClipboard() = {
    Webpage().require(Clipboard)

    Clipboard.connect(wrapped)
    Clipboard().configureDefaultHandling()

    Clipboard().clientEvent.on {
      case evt => if (evt.element.getOrElse(null) == wrapped) {
        evt.clipType match {
          case ClipType.Cut => delete()
          case ClipType.Copy => // Default handling will take care of this
          case ClipType.Paste => Clipboard().headOption match {
            case Some(entry) => insert(entry.value.toString, InsertMode.HTML)
            case None => // Nothing in the clipboard to paste
          }
        }
      }
    }
  }

  /**
   * Whether this editor should be inlined (use contenteditable) or the standard ckeditor. If using the standard
   * ckeditor an iframe will be used for the content and the toolbar will appear above the editor. When inlined the
   * content will be edited directly and the toolbar will float when editing.
   *
   * This property will not do anything after the editor has been instantiated.
   *
   * Defaults to true.
   */
  val inline = Property[Boolean](default = Some(true))

  /**
   * The frequency at which the content is validated for changes in milliseconds. If this is set to 0 the content
   * will only be validated upon blur.
   *
   * Defaults to 1000.
   */
  val validateFrequency = property[Long]("validateFrequency", 1000)

  /**
   * Whether this editor is read-only.
   *
   * Defaults to false.
   */
  val readOnly = property[Boolean]("readOnly", false)

  /**
   * Reflects the HTML content of the editor.
   *
   * Defaults to the innerHTML value of wrapped.
   */
  val html = property[String]("html", IdentifiableTag.ignoreIds(wrapped.innerHTML))

  /**
   * Updates the wrapped tag's content when the content is modified if this is set to true.
   *
   * Defaults to false.
   */
  val updateWrapped = Property[Boolean](default = Some(false))

  /**
   * Defines whether the native toolbar should be displayed.
   *
   * Defaults to false.
   */
  val showToolbar = property[Boolean]("showToolbar", false)

  /**
   * Defines whether the path navigation in the bottom bar should be displayed. This is only applicable when not
   * inline.
   *
   * Defaults to true.
   */
  val showPath = property[Boolean]("showPath", true)

  /**
   * Defines whether the resizer in the bottom bar should be displayed. This is only applicable when not inline.
   *
   * Defaults to true.
   */
  val showResizer = property[Boolean]("showResizer", true)

  val showSaveButton = property[Boolean]("showToolbarButtonsave", true)
  val showNewPageButton = property[Boolean]("showToolbarButtonnewpage", true)
  val showPreviewButton = property[Boolean]("showToolbarButtonpreview", true)
  val showPrintButton = property[Boolean]("showToolbarButtonprint", true)
  val showTemplatesButton = property[Boolean]("showToolbarButtontemplates", true)
  val showCutButton = property[Boolean]("showToolbarButtoncut", true)
  val showCopyButton = property[Boolean]("showToolbarButtoncopy", true)
  val showPasteButton = property[Boolean]("showToolbarButtonpaste", true)
  val showPasteTextButton = property[Boolean]("showToolbarButtonpastetext", true)
  val showPasteFromWordButton = property[Boolean]("showToolbarButtonpastefromword", true)
  val showUndoButton = property[Boolean]("showToolbarButtonundo", true)
  val showRedoButton = property[Boolean]("showToolbarButtonredo", true)
  val showFindButton = property[Boolean]("showToolbarButtonfind", true)
  val showReplaceButton = property[Boolean]("showToolbarButtonreplace", true)
  val showSelectAllButton = property[Boolean]("showToolbarButtonselectall", true)
  val showSpellCheckButton = property[Boolean]("showToolbarButtonscayt", true)
  val showFormButton = property[Boolean]("showToolbarButtonform", true)
  val showCheckBoxButton = property[Boolean]("showToolbarButtoncheckbox", true)
  val showRadioButton = property[Boolean]("showToolbarButtonradio", true)
  val showTextFieldButton = property[Boolean]("showToolbarButtontextfield", true)
  val showTextAreaButton = property[Boolean]("showToolbarButtontextarea", true)
  val showSelectButton = property[Boolean]("showToolbarButtonselect", true)
  val showButtonButton = property[Boolean]("showToolbarButtonbutton", true)
  val showImageButtonButton = property[Boolean]("showToolbarButtonimagebutton", true)
  val showHiddenFieldButton = property[Boolean]("showToolbarButtonhiddenfield", true)
  val showAboutButton = property[Boolean]("showToolbarButtonabout", true)

  def showFormButtons(visible: Boolean) = {
    showFormButton := false
    showCheckBoxButton := false
    showRadioButton := false
    showTextFieldButton := false
    showTextAreaButton := false
    showSelectButton := false
    showButtonButton := false
    showImageButtonButton := false
    showHiddenFieldButton := false
  }

  /**
   * Toggles the visibility of the context menu.
   */
  def contextMenu() = execCommand("contextMenu")

  /**
   * Displays CKEDITOR's About dialog.
   */
  def about() = execCommand("about")

  /**
   * Displays accessibility instructions.
   */
  def a11yHelp() = execCommand("a11yHelp")

  /**
   * Applies the specified font size to the selected content.
   *
   * @param size represents the size to apply to the selection.
   */
  def fontSize(size: FontSize) = execApplyStyle(FontSizeStyle(size))

  /**
   * Invokes the supplied action when the value of the font-size changes in the selectoin.
   *
   * @param action the action function that is called when font-size changes with the size as a String or null.
   */
  def onFontSize(action: JSFunction1[String, Unit]) = onStyleValueChange(action, Style.fontSize)

  /**
   * Toggles bold state on the selected content.
   */
  def bold() = execCommand("bold")

  /**
   * Invokes the supplied action when the state of bold in the selection changes.
   *
   * @param action the action to invoke taking a Boolean of the current status.
   */
  def onBold(action: JSFunction1[Boolean, Unit]) = onStyleChange(action, BoldStyle)

  /**
   * Toggles italic state on the selected content.
   */
  def italic() = execCommand("italic")

  /**
   * Invokes the supplied action when the state of italic in the selection changes.
   *
   * @param action the action to invoke taking a Boolean of the current status.
   */
  def onItalic(action: JSFunction1[Boolean, Unit]) = onStyleChange(action, ItalicStyle)

  /**
   * Toggles the underline state on the selected content.
   */
  def underline() = execCommand("underline")

  /**
   * Invokes the supplied action when the state of underline in the selection changes.
   *
   * @param action the action to invoke taking a Boolean of the current status.
   */
  def onUnderline(action: JSFunction1[Boolean, Unit]) = onStyleChange(action, UnderlineStyle)

  /**
   * Toggles the strike-through state on the selected content.
   */
  def strike() = execCommand("strike")

  /**
   * Invokes the supplied action when the state of strike-through in the selection changes.
   *
   * @param action the action to invoke taking a Boolean of the current status.
   */
  def onStrike(action: JSFunction1[Boolean, Unit]) = onStyleChange(action, StrikeStyle)

  /**
   * Toggles the subscript state on the selected content.
   */
  def subscript() = execCommand("subscript")

  /**
   * Invokes the supplied action when the state of subscript in the selection changes.
   *
   * @param action the action to invoke taking a Boolean of the current status.
   */
  def onSubscript(action: JSFunction1[Boolean, Unit]) = onStyleChange(action, SubscriptStyle)

  /**
   * Toggles the superscript state on the selected content.
   */
  def superscript() = execCommand("superscript")

  /**
   * Invokes the supplied action when the state of superscript in the selection changes.
   *
   * @param action the action to invoke taking a Boolean of the current status.
   */
  def onSuperscript(action: JSFunction1[Boolean, Unit]) = onStyleChange(action, SuperscriptStyle)

  /**
   * Specifies the content within the selected range should represent language left-to-right.
   */
  def bidiltr() = execCommand("bidiltr")

  /**
   * Specifies the content within the selected range should represent language right-to-left.
   */
  def bidirtl() = execCommand("bidirtl")

  /**
   * Toggle blockquote status.
   */
  def blockquote() = execCommand("blockquote")

  /**
   * Cuts the selected range and stores in the clipboard. This requires browser security permissions set to allow
   * access to the clipboard from JavaScript.
   */
  def cut() = execCommand("cut")

  /**
   * Copies the selected range and stores in the clipboard. This requires browser security permissions set to allow
   * access to the clipboard from JavaScript.
   */
  def copy() = execCommand("copy")

  /**
   * Pastes the selected range from the clipboard. This requires browser security permissions set to allow
   * access to the clipboard from JavaScript.
   */
  def paste() = execCommand("paste")

  /**
   * Displays the color dialog.
   *
   * TODO: figure out how to leverage the resulting color.
   */
  def colorDialog() = execCommand("colordialog")

  /**
   * Display templates dialog. This allows changing the layout of the content area to reflect a predefined template.
   */
  def templates() = execCommand("templates")

  /**
   * Displays the create div dialog.
   *
   * TODO: figure out if this actually inserts the div?
   */
  def createDiv() = execCommand("creatediv")

  /**
   * TODO: figure out how this works
   */
  def editDiv() = execCommand("editdiv")

  /**
   * TODO: figure out how this works
   */
  def removeDiv() = execCommand("removediv")

  /**
   * Gives focus to the toolbar.
   */
  def toolbarFocus() = execCommand("toolbarFocus")

  /**
   * Mimics pressing the enter key on the keyboard in the editor.
   */
  def enter() = execCommand("enter")

  /**
   * Mimics pressing the enter key on the keyboard in the editor while holding down the shift key.
   */
  def shiftEnter() = execCommand("shiftEnter")

  /**
   * Displays the find dialog to search the editor for specific content.
   */
  def find() = execCommand("find")

  /**
   * Displays the replace dialog to search the editor for specific content and replace it.
   */
  def replace() = execCommand("replace")

  def flash() = execCommand("flash")

  /**
   * Display form editor popup for the current selection. If a form doesn't already exist a new one will be created.
   */
  def form() = execCommand("form")

  /**
   * Displays the create/edit checkbox dialog.
   */
  def checkbox() = execCommand("checkbox")

  /**
   * Displays the create/edit radio dialog.
   */
  def radio() = execCommand("radio")

  /**
   * Displays the create/edit text field dialog.
   */
  def textField() = execCommand("textfield")

  /**
   * Displays the create/edit text area dialog.
   */
  def textArea() = execCommand("textarea")

  /**
   * Displays the create/edit select dialog.
   */
  def select() = execCommand("select")

  /**
   * Displays the create/edit button dialog.
   */
  def button() = execCommand("button")

  /**
   * Displays the create/edit image button dialog.
   */
  def imageButton() = execCommand("imagebutton")

  /**
   * Displays the create/edit hidden field dialog.
   */
  def hiddenField() = execCommand("hiddenfield")

  /**
   * Inserts a horizontal rule at the selected area.
   */
  def horizontalRule() = execCommand("horizontalrule")

  /**
   * Displays the create/edit iframe dialog.
   */
  def iframe() = execCommand("iframe")

  /**
   * Displays the create/edit image dialog.
   */
  def image() = execCommand("image")

  /**
   * Increases the indentation of the selection.
   */
  def indent() = execCommand("indent")

  /**
   * Decreases the indentation of the selection.
   */
  def outdent() = execCommand("outdent")

  /**
   * Displays the insert smiley dialog.
   */
  def smiley() = execCommand("smiley")

  /**
   * Sets the justification to left for the selection.
   */
  def justifyLeft() = execCommand("justifyleft")

  /**
   * Sets the justification to center for the selection.
   */
  def justifyCenter() = execCommand("justifycenter")

  /**
   * Sets the justification to right for the selection.
   */
  def justifyRight() = execCommand("justifyright")

  /**
   * Sets the justification to block forthe selection.
   */
  def justifyBlock() = execCommand("justifyblock")

  /**
   * Displays the link editor dialog.
   */
  def link() = execCommand("link")

  /**
   * Unlinks the selection.
   */
  def unlink() = execCommand("unlink")

  /**
   * Displays the anchor editor dialog (creates a named anchor on the page that can be linked to).
   */
  def anchor() = execCommand("anchor")

  /**
   * Removes the anchor from the selection.
   */
  def removeAnchor() = execCommand("removeAnchor")

  /**
   * Toggles the selected content within a numbered list.
   */
  def numberedList() = execCommand("numberedlist")

  /**
   * Displays a dialog for styling the current numbered list (will not create one).
   */
  def numberedListStyle() = execCommand("numberedListStyle")

  /**
   * Toggles the selected content within a bulleted list.
   */
  def bulletedList() = execCommand("bulletedlist")

  /**
   * Displays a dialog for styling the current bulleted list (will not create one).
   */
  def bulletedListStyle() = execCommand("bulletedListStyle")

  /**
   * Toggles the maximized state of the editor.
   */
  def maximize() = execCommand("maximize")

  /**
   * Clears the editor of all content and resets.
   */
  def newPage() = execCommand("newpage")

  /**
   * Inserts a page break.
   */
  def pageBreak() = execCommand("pagebreak")

  /**
   * Displays the paste as plain text dialog.
   */
  def pasteText() = execCommand("pastetext")

  /**
   * Displays the paste from Word dialog.
   */
  def pastFromWord() = execCommand("pastefromword")

  /**
   * Previews the content.
   */
  def preview() = execCommand("preview")

  /**
   * Displays the print dialog.
   */
  def print() = execCommand("print")

  /**
   * Removes formatting from the selected content.
   */
  def removeFormat() = execCommand("removeFormat")

  def save() = execCommand("save")

  def selectAll() = execCommand("selectAll")

  /**
   * Toggles showing of container blocks.
   */
  def showBlocks() = execCommand("showblocks")

  /**
   * Toggles showing of borders on all elements.
   */
  def showBorders() = execCommand("showborders")

  /**
   * Toggles display of source in editor instead of rich content.
   */
  def source() = execCommand("source")

  /**
   * Displays the insert special character dialog.
   */
  def specialChar() = execCommand("specialchar")

  /**
   * Enables spell checking addon.
   */
  def scaytCheck() = execCommand("scaytcheck")

  def blur() = execCommand("blur")

  def blurBack() = execCommand("blurBack")

  def selectNextCell() = execCommand("selectNextCell")

  def selectPreviousCell() = execCommand("selectPreviousCell")

  def table() = execCommand("table")

  def tableProperties() = execCommand("tableProperties")

  def tableDelete() = execCommand("tableDelete")

  def cellProperties() = execCommand("cellProperties")

  def rowDelete() = execCommand("rowDelete")

  def rowInsertBefore() = execCommand("rowInsertBefore")

  def rowInsertAfter() = execCommand("rowInsertAfter")

  def columnDelete() = execCommand("columnDelete")

  def columnInsertBefore() = execCommand("columnInsertBefore")

  def columnInsertAfter() = execCommand("columnInsertAfter")

  def cellDelete() = execCommand("cellDelete")

  def cellMerge() = execCommand("cellMerge")

  def cellMergeRight() = execCommand("cellMergeRight")

  def cellMergeDown() = execCommand("cellMergeDown")

  def cellVerticalSplit() = execCommand("cellVerticalSplit")

  def cellHorizontalSplit() = execCommand("cellHorizontalSplit")

  def cellInsertBefore() = execCommand("cellInsertBefore")

  def cellInsertAfter() = execCommand("cellInsertAfter")

  def undo() = execCommand("undo")

  def redo() = execCommand("redo")

  def checkSpell() = execCommand("checkspell")

  def elementsPathFocus() = execCommand("elementsPathFocus")

  def accessPreviousSpace() = execCommand("accessPreviousSpace")

  def accessNextSpace() = execCommand("accessNextSpace")

  /**
   * Deletes the current selection
   */
  def delete() = execCommand("delete")

  // TODO: getCommand('bold').state - 0 = disabled, 1 = true, 2 = false

  init()

  private def init() = {
    if (inline()) {
      wrapped.contentEditable := ContentEditable.True
    }
    wrapped.eventReceived.on {
      case evt => if (evt.event == "editorChanged") {
        val content = evt.message[String]("value")
        changeValue(content)

        Intercept.Stop
      } else {
        Intercept.Continue
      }
    }
    html.change.on {
      case evt => if (updateWrapped()) {
        update(fireChanges = false)
      }
    }
  }

  /**
   * Updates the HTML content of the wrapped component with the contents of the editor.
   */
  def update(fireChanges: Boolean) = {
    val f = () => HTMLToScala.replaceChildren(wrapped.asInstanceOf[HTMLTag with Container[HTMLTag]], html())
    if (fireChanges) {
      f()
    } else {
      WebpageConnection.ignoreStructureChanges {
        f()
      }
    }
  }

  protected def initializeComponent(values: Map[String, Any]) = {
    Realtime.sendJavaScript(s"createRichEditor('${wrapped.identity}', ${inline()});", onlyRealtime = false)
    values.foreach {
      case (key, value) => modify(key, value)
    }
  }

  protected def modify(key: String, value: Any) = {
    Realtime.sendJavaScript(s"richEditorOption('${wrapped.identity}', '$key', ${JavaScriptContent.toJS(value)});", onlyRealtime = false)
  }

  /**
   * Inserts the supplied content at the current selection. The selected text will be replaced with this content.
   *
   * @param content the HTML content to insert.
   * @param mode the insertion mode to use.
   */
  def insert(content: String, mode: InsertMode) = {
    Realtime.sendJavaScript(s"richEditorInsert('${wrapped.identity}', '${mode.value}', content);", onlyRealtime = false, content = content)
  }

  /**
   * Changes 'value' without triggering an event back to the client.
   *
   * @param content to set value to
   */
  private def changeValue(content: String) = html.applyChange(content)

  def execCommand(command: String, value: Any = null): Unit = {
    Realtime.sendJavaScript(s"richEditorExecCommand('${wrapped.identity}', '$command', ${JavaScriptContent.toJS(value)});")
  }

  def execApplyStyle(style: RichEditorStyle) = {
    val js = s"richEditorApplyStyle('${wrapped.identity}', ${style.toJSString});"
    Realtime.sendJavaScript(js)
  }

  def execRemoveStyle(style: RichEditorStyle) = {
    val js = s"richEditorRemoveStyle('${wrapped.identity}', ${style.toJSString});"
    Realtime.sendJavaScript(js)
  }

  def execToggleStyle(style: RichEditorStyle) = {
    val js = s"richEditorToggleStyle('${wrapped.identity}', ${style.toJSString});"
    Realtime.sendJavaScript(js)
  }

  /**
   * Invokes the supplied action when the state of the supplied style changes on the current selection.
   *
   * For example, passing "b" as the element invokes action when the selection status of being wrapped in a bold tag
   * changes.
   *
   * @param action the JavaScript action to take when the state changes
   * @param style the style to listen to
   */
  def onStyleChange(action: JSFunction1[Boolean, Unit], style: RichEditorStyle): Unit = {
    Realtime.sendJavaScript(s"richEditorAttachStyleStateChange('${wrapped.identity}', ${style.toJSString}, ${action.content});",
      onlyRealtime = false)
  }

  /**
   * Invokes the supplied action when the state of the supplied css style changes on the current selection.
   *
   * @param action the JavaScript action to take when the state changes
   * @param cssStyle the Style to listen to
   */
  def onStyleValueChange(action: JSFunction1[String, Unit], cssStyle: Style[_]): Unit = {
    Realtime.sendJavaScript(s"richEditorAttachStyleValueChange('${wrapped.identity}', '${cssStyle.cssName}', ${action.content});",
      onlyRealtime = false)
  }

  /**
   * Invokes the supplied JavaScript after this editor has completed initialization.
   *
   * @param f the content to execute
   * @param onlyRealtime true if this should only be executed after page rendering is complete
   */
  def afterInit(f: JavaScriptContent, onlyRealtime: Boolean = false): Unit = {
    val s =
      s"""
        |var f = function() { ${f.content} };
        |invokeAfterRichEditorInit('${wrapped.identity}', f);
      """.stripMargin
    println(s"AfterInit[$s]")
    Realtime.sendJavaScript(s, onlyRealtime = onlyRealtime)
  }
}

case class RichEditorStyle(element: String,
                           styles: Map[String, Any] = Map.empty,
                           attributes: Map[String, Any] = Map.empty,
                           overrides: List[Override] = Nil) {
  def toJSString = {
    val styleString = styles.map {
      case (key, value) => s"'$key': ${JavaScriptContent.toJS(value)}"
    }.mkString(", ")
    val attributesString = attributes.map {
      case (key, value) => s"'$key': ${JavaScriptContent.toJS(value)}"
    }.mkString(", ")
    val overridesString = overrides.map {
      case o => {
        val attributes = o.attributes.map {
          case (key, value) => s"'$key': ${JavaScriptContent.toJS(value)}"
        }.mkString(", ")
        s"{ element: '${o.element}', attributes: {$attributes} }"
      }
    }.mkString(", ")
    s"new CKEDITOR.style({element: '$element', styles: {$styleString}, attributes: {$attributesString}, overrides: [$overridesString]})"
  }
}

case class Override(element: String, attributes: Map[String, String] = Map.empty)

class InsertMode private(val value: String) extends EnumEntry

object InsertMode extends Enumerated[InsertMode] {
  val HTML = new InsertMode("html")
  val UnfilteredHTML = new InsertMode("unfiltered_html")
  val Text = new InsertMode("text")
}