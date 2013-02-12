package org.hyperscala.html

import attributes._
import event.EventSupport
import org.hyperscala._
import io.HTMLWriter
import org.hyperscala.html.tag._
import css.StyleSheetProperty
import scala.collection.{Map => ScalaMap}
import org.powerscala.property.StandardProperty
import org.hyperscala.event.JavaScriptEvent

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
trait HTMLTag extends IdentifiableTag with EventSupport {
  val accessKey = PropertyAttribute[Char]("accesskey", -1.toChar)
  val clazz = PropertyAttribute[List[String]]("class", Nil)
  val contentEditable = PropertyAttribute[ContentEditable]("contenteditable", null)
  val contextMenu = PropertyAttribute[String]("contextmenu", null)
  val dir = PropertyAttribute[Direction]("dir", null)
  val draggable = PropertyAttribute[Draggable]("draggable", null)
  val dropZone = PropertyAttribute[DropZone]("dropzone", null)
  val hidden = PropertyAttribute[Boolean]("hidden", false)
  val lang = PropertyAttribute[String]("lang", null)
  val role = PropertyAttribute[String]("role", null)
  val spellCheck = PropertyAttribute[Boolean]("spellcheck", false)
  val tabIndex = PropertyAttribute[Int]("tabindex", -1)
  val titleText = PropertyAttribute[String]("title", null)

  // TODO: add back selectors
  val style = new StyleSheetProperty("style", InclusionMode.NotEmpty)(this) {
//    val link = new StyleSheetProperty("link", InclusionMode.Exclude)(HTMLTag.this)
//    val visited = new StyleSheetProperty("visited", InclusionMode.Exclude)(HTMLTag.this)
//    val active = new StyleSheetProperty("active", InclusionMode.Exclude)(HTMLTag.this)
//    val hover = new StyleSheetProperty("hover", InclusionMode.Exclude)(HTMLTag.this)
//    val focus = new StyleSheetProperty("focus", InclusionMode.Exclude)(HTMLTag.this)
//    val empty = new StyleSheetProperty("empty", InclusionMode.Exclude)(HTMLTag.this)
//    val target = new StyleSheetProperty("target", InclusionMode.Exclude)(HTMLTag.this)
//    val checked = new StyleSheetProperty("checked", InclusionMode.Exclude)(HTMLTag.this)
//    val enabled = new StyleSheetProperty("enabled", InclusionMode.Exclude)(HTMLTag.this)
//    val disabled = new StyleSheetProperty("disabled", InclusionMode.Exclude)(HTMLTag.this)

    // TODO: add support for custom selectors
//    val selectors: List[StyleProperty] = List(this, link, visited, active, hover, focus, empty, target, checked, enabled, disabled)
  }

  protected def generateChildFromTagName(name: String): XMLContent = {
    HTMLTag.create(name)
  }

  protected def processText(text: String): Unit = {
    if (text != null && text.trim.length > 0) {
      this.asInstanceOf[Container[HTMLTag]].contents += new Text(text)
    }
  }

  def byName[T <: HTMLTag](name: String)(implicit manifest: Manifest[T]) = hierarchy.findAll[T](t => t.name() == name)(manifest)

  def byTag[T <: HTMLTag](implicit manifest: Manifest[T]) = hierarchy.findAll[T](t => true)(manifest)

  def outputString = {
    val b = new StringBuilder
    val writer: String => Unit = (s: String) => b.append(s)
    val htmlWriter = HTMLWriter(writer)
    write(htmlWriter)
    b.toString()
  }

  def formValue: StandardProperty[String] = {
    throw new UnsupportedOperationException("%s doesn't support updating value!".format(xmlLabel))
  }

  def rendered() = {}

  override def receive(event: String, message: Message) = event match {
    case "event" => {     // Applies general JavaScript event from client
      val evt = JavaScriptEvent.create(this, message[String]("event"))
      fire(evt)
    }
    case "keyEvent" => {  // Fires key events on this tag
      val eventType = message[String]("event")
      val altKey = message[Boolean]("altKey")
      val char = message[Int]("char")
      val ctrlKey = message[Boolean]("ctrlKey")
      val key = message[Int]("key")
      val locale = message.getOrElse[String]("locale", null)
      val location = message.getOrElse[Long]("location", 0L)
      val metaKey = message[Boolean]("metaKey")
      val repeat = message.getOrElse[Boolean]("repeat", false)
      val shiftKey = message[Boolean]("shiftKey")
      val evt = JavaScriptEvent.createKeyEvent(this, eventType, altKey, char, ctrlKey, key, locale, location, metaKey, repeat, shiftKey)
      fire(evt)
    }
    case _ => super.receive(event, message)
  }
}

object HTMLTag {
  private val registry = ScalaMap("html" -> classOf[HTML],
                                  "head" -> classOf[Head],
                                  "title" -> classOf[Title],
                                  "body" -> classOf[Body],
                                  "a" -> classOf[A],
                                  "abbr" -> classOf[Abbr],
                                  "address" -> classOf[Address],
                                  "area" -> classOf[Area],
                                  "article" -> classOf[Article],
                                  "aside" -> classOf[Aside],
                                  "audio" -> classOf[Audio],
                                  "b" -> classOf[B],
                                  "base" -> classOf[Base],
                                  "bdi" -> classOf[Bdi],
                                  "bdo" -> classOf[Bdo],
                                  "blockquote" -> classOf[BlockQuote],
                                  "br" -> classOf[Br],
                                  "button" -> classOf[Button],
                                  "canvas" -> classOf[Canvas],
                                  "caption" -> classOf[Caption],
                                  "cite" -> classOf[Cite],
                                  "code" -> classOf[Code],
                                  "col" -> classOf[Col],
                                  "colgroup" -> classOf[ColGroup],
                                  "command" -> classOf[Command],
                                  "datalist" -> classOf[DataList],
                                  "dd" -> classOf[Dd],
                                  "del" -> classOf[Del],
                                  "details" -> classOf[Details],
                                  "dfn" -> classOf[Dfn],
                                  "div" -> classOf[Div],
                                  "dl" -> classOf[Dl],
                                  "dt" -> classOf[Dt],
                                  "em" -> classOf[Em],
                                  "embed" -> classOf[Embed],
                                  "fieldset" -> classOf[FieldSet],
                                  "figcaption" -> classOf[FigCaption],
                                  "figure" -> classOf[Figure],
                                  "footer" -> classOf[Footer],
                                  "form" -> classOf[Form],
                                  "h1" -> classOf[H1],
                                  "h2" -> classOf[H2],
                                  "h3" -> classOf[H3],
                                  "h4" -> classOf[H4],
                                  "h5" -> classOf[H5],
                                  "h6" -> classOf[H6],
                                  "header" -> classOf[Header],
                                  "hgroup" -> classOf[HGroup],
                                  "hr" -> classOf[Hr],
                                  "i" -> classOf[I],
                                  "iframe" -> classOf[IFrame],
                                  "img" -> classOf[Img],
                                  "input" -> classOf[Input],
                                  "ins" -> classOf[Ins],
                                  "kbd" -> classOf[Kbd],
                                  "keygen" -> classOf[KeyGen],
                                  "label" -> classOf[Label],
                                  "legend" -> classOf[Legend],
                                  "li" -> classOf[Li],
                                  "link" -> classOf[Link],
                                  "map" -> classOf[tag.Map],
                                  "mark" -> classOf[Mark],
                                  "menu" -> classOf[Menu],
                                  "meta" -> classOf[Meta],
                                  "meter" -> classOf[Meter],
                                  "nav" -> classOf[Nav],
                                  "noscript" -> classOf[NoScript],
                                  "object" -> classOf[tag.Object],
                                  "ol" -> classOf[Ol],
                                  "optgroup" -> classOf[OptGroup],
                                  "option" -> classOf[tag.Option],
                                  "output" -> classOf[Output],
                                  "p" -> classOf[P],
                                  "param" -> classOf[Param],
                                  "pre" -> classOf[Pre],
                                  "progress" -> classOf[Progress],
                                  "q" -> classOf[Q],
                                  "rp" -> classOf[Rp],
                                  "rt" -> classOf[Rt],
                                  "ruby" -> classOf[Ruby],
                                  "s" -> classOf[S],
                                  "samp" -> classOf[Samp],
                                  "script" -> classOf[Script],
                                  "section" -> classOf[Section],
                                  "select" -> classOf[Select],
                                  "small" -> classOf[Small],
                                  "source" -> classOf[Source],
                                  "span" -> classOf[Span],
                                  "strong" -> classOf[Strong],
                                  "style" -> classOf[Style],
                                  "sub" -> classOf[Sub],
                                  "sup" -> classOf[Sup],
                                  "table" -> classOf[Table],
                                  "tbody" -> classOf[TBody],
                                  "td" -> classOf[Td],
                                  "textarea" -> classOf[TextArea],
                                  "tfoot" -> classOf[TFoot],
                                  "th" -> classOf[Th],
                                  "thead" -> classOf[THead],
                                  "time" -> classOf[Time],
                                  "tr" -> classOf[Tr],
                                  "track" -> classOf[Track],
                                  "u" -> classOf[U],
                                  "ul" -> classOf[Ul],
                                  "var" -> classOf[Var],
                                  "video" -> classOf[Video],
                                  "wbr" -> classOf[Wbr])

  def create(tagName: String) = {
    registry(tagName).newInstance().asInstanceOf[HTMLTag]
  }
}