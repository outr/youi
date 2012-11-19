package org.hyperscala.html

import attributes._
import event.EventSupport
import org.hyperscala._
import io.HTMLWriter
import org.hyperscala.html.tag._
import css.StyleSheet
import persistence.StyleSheetPersistence
import scala.collection.{Map => ScalaMap}
import org.powerscala.property.{StandardProperty, PropertyParent}

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
trait HTMLTag extends Tag with EventSupport {
  val accessKey = PropertyAttribute[Char]("accesskey", -1.toChar)
  val clazz = PropertyAttribute[List[String]]("class", Nil)
  val contentEditable = PropertyAttribute[ContentEditable]("contenteditable", null)
  val contextMenu = PropertyAttribute[String]("contextmenu", null)
  val dir = PropertyAttribute[Direction]("dir", null)
  val draggable = PropertyAttribute[Draggable]("draggable", null)
  val dropZone = PropertyAttribute[DropZone]("dropzone", null)
  val hidden = PropertyAttribute[Boolean]("hidden", false)
  val id = PropertyAttribute[String]("id", null)
  val lang = PropertyAttribute[String]("lang", null)
  val spellCheck = PropertyAttribute[Boolean]("spellcheck", false)
  val tabIndex = PropertyAttribute[Int]("tabindex", -1)
  val titleText = PropertyAttribute[String]("title", null)

  /**
   * Gets the id value and sets it to a unique value if it's null.
   */
  def identity = id() match {
    case null => {
      val uuid = Unique()
      id := uuid
      uuid
    }
    case value => value
  }

  val style = new StyleProperty("style", InclusionMode.NotEmpty) {
    val link = new StyleProperty("link", InclusionMode.Exclude)(HTMLTag.this)
    val visited = new StyleProperty("visited", InclusionMode.Exclude)(HTMLTag.this)
    val active = new StyleProperty("active", InclusionMode.Exclude)(HTMLTag.this)
    val hover = new StyleProperty("hover", InclusionMode.Exclude)(HTMLTag.this)
    val focus = new StyleProperty("focus", InclusionMode.Exclude)(HTMLTag.this)
    val empty = new StyleProperty("empty", InclusionMode.Exclude)(HTMLTag.this)
    val target = new StyleProperty("target", InclusionMode.Exclude)(HTMLTag.this)
    val checked = new StyleProperty("checked", InclusionMode.Exclude)(HTMLTag.this)
    val enabled = new StyleProperty("enabled", InclusionMode.Exclude)(HTMLTag.this)
    val disabled = new StyleProperty("disabled", InclusionMode.Exclude)(HTMLTag.this)

    // TODO: add support for custom selectors
    val selectors: List[StyleProperty] = List(this, link, visited, active, hover, focus, empty, target, checked, enabled, disabled)
  }

  protected def generateChildFromTagName(name: String): XMLContent = {
    HTMLTag.create(name)
  }

  protected def processText(text: String): Unit = {
    this.asInstanceOf[Container[HTMLTag]].contents += new Text(text)
  }

  def byId[T <: HTMLTag](id: String)(implicit manifest: Manifest[T]) = hierarchy.findFirst[T](t => t.id() == id)(manifest)

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
}

class StyleProperty(_name: String, inclusion: InclusionMode)(implicit parent: PropertyParent) extends PropertyAttribute[StyleSheet](_name, null, inclusion = inclusion)(StyleSheetPersistence, parent, Manifest.classType[StyleSheet](classOf[StyleSheet])) with LazyProperty[StyleSheet] {
  protected def lazyValue = new StyleSheet

  // Avoid overwriting previously set values
  override def attributeValue_=(value: String) = StyleSheetPersistence(this.value, value)

  override def lazyLoaded() = {
    super.lazyLoaded()

    Page().intercept.initStyle.fire(parent.asInstanceOf[Markup])
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