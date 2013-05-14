package org.hyperscala.html

import attributes._
import org.hyperscala._
import io.HTMLWriter
import org.hyperscala.html.tag._
import org.hyperscala.css.{StyleSheet, StyleSheetProperty}
import scala.collection.{Map => ScalaMap}
import org.powerscala.property.Property
import org.hyperscala.event._
import org.hyperscala.event.processor._

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
trait HTMLTag extends IdentifiableTag {
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

  val afterPrintEvent = new AfterPrintEventProcessor()
  val beforePrintEvent = new BeforePrintEventProcessor()
  val beforeOnLoadEvent = new BeforeOnLoadEventProcessor()
  val hasChangeEvent = new HasChangeEventProcessor()
  val loadEvent = new LoadEventProcessor()
  val messageEvent = new MessageEventProcessor()
  val offlineEvent = new OfflineEventProcessor()
  val onlineEvent = new OnlineEventProcessor()
  val pageHideEvent = new PageHideEventProcessor()
  val pageShowEvent = new PageShowEventProcessor()
  val popStateEvent = new PopStateEventProcessor()
  val redoEvent = new RedoEventProcessor()
  val resizeEvent = new ResizeEventProcessor()
  val storageEvent = new StorageEventProcessor()
  val undoEvent = new UndoEventProcessor()
  val unLoadEvent = new UnLoadEventProcessor()
  val blurEvent = new BlurEventProcessor()
  val changeEvent = new ChangeEventProcessor()
  val contextMenuEvent = new ContextMenuEventProcessor()
  val focusEvent = new FocusEventProcessor()
  val formChangeEvent = new FormChangeEventProcessor()
  val formInputEvent = new FormInputEventProcessor()
  val inputEvent = new InputEventProcessor()
  val invalidEvent = new InvalidEventProcessor()
  val resetEvent = new ResetEventProcessor()
  val selectEvent = new SelectEventProcessor()
  val submitEvent = new SubmitEventProcessor()
  val keyDownEvent = new KeyDownEventProcessor()
  val keyPressEvent = new KeyPressEventProcessor()
  val keyUpEvent = new KeyUpEventProcessor()
  val clickEvent = new ClickEventProcessor()
  val doubleClickEvent = new DoubleClickEventProcessor()
  val dragEvent = new DragEventProcessor()
  val dragEndEvent = new DragEndEventProcessor()
  val dragEnterEvent = new DragEnterEventProcessor()
  val dragLeaveEvent = new DragLeaveEventProcessor()
  val dragOverEvent = new DragOverEventProcessor()
  val dragStartEvent = new DragStartEventProcessor()
  val dropEvent = new DropEventProcessor()
  val mouseDownEvent = new MouseDownEventProcessor()
  val mouseMoveEvent = new MouseMoveEventProcessor()
  val mouseOutEvent = new MouseOutEventProcessor()
  val mouseOverEvent = new MouseOverEventProcessor()
  val mouseUpEvent = new MouseUpEventProcessor()
  val mouseWheelEvent = new MouseWheelEventProcessor()
  val scrollEvent = new ScrollEventProcessor()
  val abortEvent = new AbortEventProcessor()
  val canPlayEvent = new CanPlayEventProcessor()
  val canPlayThroughEvent = new CanPlayThroughEventProcessor()
  val durationChangeEvent = new DurationChangeEventProcessor()
  val emptiedEvent = new EmptiedEventProcessor()
  val endedEvent = new EndedEventProcessor()
  val errorEvent = new ErrorEventProcessor()
  val loadedDataEvent = new LoadedDataEventProcessor()
  val loadedMetaDataEvent = new LoadedMetaDataEventProcessor()
  val loadStartEvent = new LoadStartEventProcessor()
  val pauseEvent = new PauseEventProcessor()
  val playEvent = new PlayEventProcessor()
  val playingEvent = new PlayingEventProcessor()
  val progressEvent = new ProgressEventProcessor()
  val rateChangeEvent = new RateChangeEventProcessor()
  val readyStateChangeEvent = new ReadyStateChangeEventProcessor()
  val seekedEvent = new SeekedEventProcessor()
  val seekingEvent = new SeekingEventProcessor()
  val stalledEvent = new StalledEventProcessor()
  val suspendEvent = new SuspendEventProcessor()
  val timeUpdateEvent = new TimeUpdateEventProcessor()
  val volumeChangeEvent = new VolumeChangeEventProcessor()
  val waitingEvent = new WaitingEventProcessor()

  protected def init(name: String = null,
           accessKey: java.lang.Character = null,
           clazz: List[String] = null,
           contentEditable: ContentEditable = null,
           contextMenu: String = null,
           dir: Direction = null,
           draggable: Draggable = null,
           dropZone: DropZone = null,
           hidden: java.lang.Boolean = null,
           id: String = null,
           lang: String = null,
           role: String = null,
           spellCheck: java.lang.Boolean = null,
           style: StyleSheet = null,
           tabIndex: java.lang.Integer = null,
           titleText: String = null) = {
    up(this.name, name)
    up(this.accessKey, accessKey)
    up(this.clazz, clazz)
    up(this.contentEditable, contentEditable)
    up(this.contextMenu, contextMenu)
    up(this.dir, dir)
    up(this.draggable, draggable)
    up(this.dropZone, dropZone)
    up(this.hidden, hidden)
    up(this.id, id)
    up(this.lang, lang)
    up(this.role, role)
    up(this.spellCheck, spellCheck)
    up(this.style, style)
    up(this.tabIndex, tabIndex)
    up(this.titleText, titleText)
  }

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

  protected def processComment(text: String): Unit = {
    if (text != null && text.trim.length > 0) {
      this.asInstanceOf[Container[HTMLTag]].contents += new Comment(text)
    }
  }

  def byName[T <: HTMLTag](name: String)(implicit manifest: Manifest[T]) = {
    byTag[T].collect {
      case t if (t.name() == name) => t
    }
  }

  def outputString = {
    val b = new StringBuilder
    val writer: String => Unit = (s: String) => b.append(s)
    val htmlWriter = HTMLWriter(writer)
    write(htmlWriter)
    b.toString()
  }

  def formValue: Property[String] = {
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
  
  protected def fire(event: JavaScriptEvent) = event match {
    case evt: AfterPrintEvent => afterPrintEvent.fire(evt)
    case evt: BeforePrintEvent => beforePrintEvent.fire(evt)
    case evt: BeforeOnLoadEvent => beforeOnLoadEvent.fire(evt)
    case evt: HasChangeEvent => hasChangeEvent.fire(evt)
    case evt: LoadEvent => loadEvent.fire(evt)
    case evt: MessageEvent => messageEvent.fire(evt)
    case evt: OfflineEvent => offlineEvent.fire(evt)
    case evt: OnlineEvent => onlineEvent.fire(evt)
    case evt: PageHideEvent => pageHideEvent.fire(evt)
    case evt: PageShowEvent => pageShowEvent.fire(evt)
    case evt: PopStateEvent => popStateEvent.fire(evt)
    case evt: RedoEvent => redoEvent.fire(evt)
    case evt: ResizeEvent => resizeEvent.fire(evt)
    case evt: StorageEvent => storageEvent.fire(evt)
    case evt: UndoEvent => undoEvent.fire(evt)
    case evt: UnLoadEvent => unLoadEvent.fire(evt)
    case evt: BlurEvent => blurEvent.fire(evt)
    case evt: ChangeEvent => changeEvent.fire(evt)
    case evt: ContextMenuEvent => contextMenuEvent.fire(evt)
    case evt: FocusEvent => focusEvent.fire(evt)
    case evt: FormChangeEvent => formChangeEvent.fire(evt)
    case evt: FormInputEvent => formInputEvent.fire(evt)
    case evt: InputEvent => inputEvent.fire(evt)
    case evt: InvalidEvent => invalidEvent.fire(evt)
    case evt: ResetEvent => resetEvent.fire(evt)
    case evt: SelectEvent => selectEvent.fire(evt)
    case evt: SubmitEvent => submitEvent.fire(evt)
    case evt: KeyDownEvent => keyDownEvent.fire(evt)
    case evt: KeyPressEvent => keyPressEvent.fire(evt)
    case evt: KeyUpEvent => keyUpEvent.fire(evt)
    case evt: ClickEvent => clickEvent.fire(evt)
    case evt: DoubleClickEvent => doubleClickEvent.fire(evt)
    case evt: DragEvent => dragEvent.fire(evt)
    case evt: DragEndEvent => dragEndEvent.fire(evt)
    case evt: DragEnterEvent => dragEnterEvent.fire(evt)
    case evt: DragLeaveEvent => dragLeaveEvent.fire(evt)
    case evt: DragOverEvent => dragOverEvent.fire(evt)
    case evt: DragStartEvent => dragStartEvent.fire(evt)
    case evt: DropEvent => dropEvent.fire(evt)
    case evt: MouseDownEvent => mouseDownEvent.fire(evt)
    case evt: MouseMoveEvent => mouseMoveEvent.fire(evt)
    case evt: MouseOutEvent => mouseOutEvent.fire(evt)
    case evt: MouseOverEvent => mouseOverEvent.fire(evt)
    case evt: MouseUpEvent => mouseUpEvent.fire(evt)
    case evt: MouseWheelEvent => mouseWheelEvent.fire(evt)
    case evt: ScrollEvent => scrollEvent.fire(evt)
    case evt: AbortEvent => abortEvent.fire(evt)
    case evt: CanPlayEvent => canPlayEvent.fire(evt)
    case evt: CanPlayThroughEvent => canPlayThroughEvent.fire(evt)
    case evt: DurationChangeEvent => durationChangeEvent.fire(evt)
    case evt: EmptiedEvent => emptiedEvent.fire(evt)
    case evt: EndedEvent => endedEvent.fire(evt)
    case evt: ErrorEvent => errorEvent.fire(evt)
    case evt: LoadedDataEvent => loadedDataEvent.fire(evt)
    case evt: LoadedMetaDataEvent => loadedMetaDataEvent.fire(evt)
    case evt: LoadStartEvent => loadStartEvent.fire(evt)
    case evt: PauseEvent => pauseEvent.fire(evt)
    case evt: PlayEvent => playEvent.fire(evt)
    case evt: PlayingEvent => playingEvent.fire(evt)
    case evt: ProgressEvent => progressEvent.fire(evt)
    case evt: RateChangeEvent => rateChangeEvent.fire(evt)
    case evt: ReadyStateChangeEvent => readyStateChangeEvent.fire(evt)
    case evt: SeekedEvent => seekedEvent.fire(evt)
    case evt: SeekingEvent => seekingEvent.fire(evt)
    case evt: StalledEvent => stalledEvent.fire(evt)
    case evt: SuspendEvent => suspendEvent.fire(evt)
    case evt: TimeUpdateEvent => timeUpdateEvent.fire(evt)
    case evt: VolumeChangeEvent => volumeChangeEvent.fire(evt)
    case evt: WaitingEvent => waitingEvent.fire(evt)
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