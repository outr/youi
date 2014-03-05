package org.hyperscala.html

import attributes._
import org.hyperscala._
import org.hyperscala.html.tag._
import org.hyperscala.css.StyleSheet
import scala.collection.{Map => ScalaMap}
import org.powerscala.property.{ListSetProperty, Property}
import org.hyperscala.event._
import org.hyperscala.event.processor._
import org.jdom2.Attribute
import org.hyperscala.selector.{PseudoClass, Selector, TagIdSelector}
import scala.collection.immutable.ListSet
import argonaut.JsonObject
import org.hyperscala.io.HTMLWriter

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
trait HTMLTag extends IdentifiableTag {
  lazy val accessKey = PropertyAttribute[Char]("accesskey", -1.toChar)
  lazy val clazz = new PropertyAttribute[ListSet[String]]("class", ListSet.empty) with ListSetProperty[String]
  lazy val contentEditable = PropertyAttribute[ContentEditable]("contenteditable", null)
  lazy val contextMenu = PropertyAttribute[String]("contextmenu", null)
  lazy val dir = PropertyAttribute[Direction]("dir", null)
  lazy val draggable = PropertyAttribute[Draggable]("draggable", null)
  lazy val dropZone = PropertyAttribute[DropZone]("dropzone", null)
  lazy val hidden = PropertyAttribute[Boolean]("hidden", false)
  lazy val lang = PropertyAttribute[String]("lang", null)
  lazy val role = PropertyAttribute[String]("role", null)
  lazy val spellCheck = PropertyAttribute[Boolean]("spellcheck", false)
  lazy val tabIndex = PropertyAttribute[Int]("tabindex", -1)
  lazy val titleText = PropertyAttribute[String]("title", null)

  lazy val afterPrintEvent = new AfterPrintEventProcessor()
  lazy val beforePrintEvent = new BeforePrintEventProcessor()
  lazy val beforeOnLoadEvent = new BeforeOnLoadEventProcessor()
  lazy val hasChangeEvent = new HasChangeEventProcessor()
  lazy val loadEvent = new LoadEventProcessor()
  lazy val messageEvent = new MessageEventProcessor()
  lazy val offlineEvent = new OfflineEventProcessor()
  lazy val onlineEvent = new OnlineEventProcessor()
  lazy val pageHideEvent = new PageHideEventProcessor()
  lazy val pageShowEvent = new PageShowEventProcessor()
  lazy val popStateEvent = new PopStateEventProcessor()
  lazy val redoEvent = new RedoEventProcessor()
  lazy val resizeEvent = new ResizeEventProcessor()
  lazy val storageEvent = new StorageEventProcessor()
  lazy val undoEvent = new UndoEventProcessor()
  lazy val unLoadEvent = new UnLoadEventProcessor()
  lazy val blurEvent = new BlurEventProcessor()
  lazy val changeEvent = new ChangeEventProcessor()
  lazy val contextMenuEvent = new ContextMenuEventProcessor()
  lazy val focusEvent = new FocusEventProcessor()
  lazy val formChangeEvent = new FormChangeEventProcessor()
  lazy val formInputEvent = new FormInputEventProcessor()
  lazy val inputEvent = new InputEventProcessor()
  lazy val invalidEvent = new InvalidEventProcessor()
  lazy val resetEvent = new ResetEventProcessor()
  lazy val selectEvent = new SelectEventProcessor()
  lazy val submitEvent = new SubmitEventProcessor()
  lazy val keyDownEvent = new KeyDownEventProcessor()
  lazy val keyPressEvent = new KeyPressEventProcessor()
  lazy val keyUpEvent = new KeyUpEventProcessor()
  lazy val clickEvent = new ClickEventProcessor()
  lazy val doubleClickEvent = new DoubleClickEventProcessor()
  lazy val dragEvent = new DragEventProcessor()
  lazy val dragEndEvent = new DragEndEventProcessor()
  lazy val dragEnterEvent = new DragEnterEventProcessor()
  lazy val dragLeaveEvent = new DragLeaveEventProcessor()
  lazy val dragOverEvent = new DragOverEventProcessor()
  lazy val dragStartEvent = new DragStartEventProcessor()
  lazy val dropEvent = new DropEventProcessor()
  lazy val mouseDownEvent = new MouseDownEventProcessor()
  lazy val mouseMoveEvent = new MouseMoveEventProcessor()
  lazy val mouseOutEvent = new MouseOutEventProcessor()
  lazy val mouseOverEvent = new MouseOverEventProcessor()
  lazy val mouseUpEvent = new MouseUpEventProcessor()
  lazy val mouseWheelEvent = new MouseWheelEventProcessor()
  lazy val scrollEvent = new ScrollEventProcessor()
  lazy val abortEvent = new AbortEventProcessor()
  lazy val canPlayEvent = new CanPlayEventProcessor()
  lazy val canPlayThroughEvent = new CanPlayThroughEventProcessor()
  lazy val durationChangeEvent = new DurationChangeEventProcessor()
  lazy val emptiedEvent = new EmptiedEventProcessor()
  lazy val endedEvent = new EndedEventProcessor()
  lazy val errorEvent = new ErrorEventProcessor()
  lazy val loadedDataEvent = new LoadedDataEventProcessor()
  lazy val loadedMetaDataEvent = new LoadedMetaDataEventProcessor()
  lazy val loadStartEvent = new LoadStartEventProcessor()
  lazy val pauseEvent = new PauseEventProcessor()
  lazy val playEvent = new PlayEventProcessor()
  lazy val playingEvent = new PlayingEventProcessor()
  lazy val progressEvent = new ProgressEventProcessor()
  lazy val rateChangeEvent = new RateChangeEventProcessor()
  lazy val readyStateChangeEvent = new ReadyStateChangeEventProcessor()
  lazy val seekedEvent = new SeekedEventProcessor()
  lazy val seekingEvent = new SeekingEventProcessor()
  lazy val stalledEvent = new StalledEventProcessor()
  lazy val suspendEvent = new SuspendEventProcessor()
  lazy val timeUpdateEvent = new TimeUpdateEventProcessor()
  lazy val volumeChangeEvent = new VolumeChangeEventProcessor()
  lazy val waitingEvent = new WaitingEventProcessor()

  protected def init(name: String = null,
           accessKey: java.lang.Character = null,
           clazz: Seq[String] = null,
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
    if (clazz != null) {
      this.clazz ++= clazz
    }
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
    up(this.tabIndex, tabIndex)
    up(this.titleText, titleText)

    if (style != null) {
      styleProperty := style
    }
  }

  private[html] var _styleDefined = false
  def isStyleDefined = _styleDefined
  lazy val styleProperty = Property[StyleSheet](default = scala.Option(HTMLTag.styleCreator()(this)))
  def style = styleProperty()
  def style(sub: PseudoClass) = HTMLPage().head.selector(Selector.pseudo(Selector.id(this), sub))

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

  /**
   * Returns a reference to the HTMLPage associated with this element.
   */
  def page = HTMLPage()

  /**
   * Returns all defined styles for this tag.
   */
  def allStyles = {
    val selectorStyles = page.head.styleSpaces.values.collect {
      case styleSheet if styleSheet.selector.matches(this) => styleSheet
    }.toList
    if (isStyleDefined) {
      style :: selectorStyles
    } else {
      selectorStyles
    }
  }

  def byName[T <: HTMLTag](name: String)(implicit manifest: Manifest[T]) = {
    byTag[T].collect {
      case t if t.name() == name => t
    }
  }

  def byClass[T <: HTMLTag](className: String)(implicit manifest: Manifest[T]) = {
    byTag[T].collect {
      case t if t.clazz().contains(className) => t
    }
  }

  def bySelector[T <: HTMLTag](selector: Selector)(implicit manifest: Manifest[T]) = {
    byTag[T].collect {
      case t if selector.matches(t) => t
    }
  }

  def outputString = {
    val b = new StringBuilder
    val writer: String => Unit = (s: String) => b.append(s)
    val htmlWriter = HTMLWriter(writer)
    write(htmlWriter)
    b.toString()
  }

  def outputChildrenString = {
    val b = new StringBuilder
    val writer: String => Unit = (s: String) => b.append(s)
    val htmlWriter = HTMLWriter(writer)
    writeChildren(htmlWriter, xmlChildren)
    b.toString()
  }

  def innerHTML = {
    val b = new StringBuilder
    val writer: String => Unit = (s: String) => b.append(s)
    val htmlWriter = HTMLWriter(writer)
    val children = xmlChildren
    writeChildren(htmlWriter, children)
    b.toString()
  }

  override protected def writeExtra(writer: HTMLWriter) = {
    super.writeExtra(writer)

    if (isStyleDefined && style.selector == null) {
      writer.write(" style=\"")
      writer.write(style.toString)
      writer.write("\"")
    }
  }

  override protected def applyAttribute(a: Attribute) = a.getName match {
    case "style" => style(a.getValue)
    case _ => super.applyAttribute(a)
  }

  def formValue: Property[String] = {
    throw new UnsupportedOperationException("%s doesn't support updating value!".format(xmlLabel))
  }

  override def receive(event: String, json: JsonObject) = event match {
    case JavaScriptEvent(creator) => {
      val evt = creator(this)
      fire(evt)
    }
    case "keydown" | "keyup" | "keypress" => {  // Fires key events on this tag
      val eventType = event
      val altKey = json.boolean("altKey")
      val char = json.int("char")
      val ctrlKey = json.boolean("ctrlKey")
      val key = json.int("key")
      val locale = json.string("locale")
      val location = json.long("location", 0L)
      val metaKey = json.boolean("metaKey")
      val repeat = json.boolean("repeat")
      val shiftKey = json.boolean("shiftKey")
      val evt = JavaScriptEvent.createKeyEvent(this, eventType, altKey, char, ctrlKey, key, locale, location, metaKey, repeat, shiftKey)
      fire(evt)
    }
    case _ => super.receive(event, json)
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
  /**
   * Creates inline CSS in the tag.
   */
  val InlineStyleCreator = (t: HTMLTag) => {
    t._styleDefined = true
    new StyleSheet(t, null)
  }
  /**
   * Creates a selector in Head that contains the style data.
   */
  val SelectorStyleCreator = (t: HTMLTag) => {
    t.page.head.selector(TagIdSelector(t))
  }

  /**
   * The style creator function to use when creating default styles for HTMLTags.
   *
   * Defaults to InlineStyleCreator.
   */
  val styleCreator = Property[HTMLTag => StyleSheet](default = scala.Option(InlineStyleCreator))

  def create(tagName: String) = {
    HTMLTagType.get(tagName).getOrElse(throw new UnsupportedOperationException(s"Unknown tag: $tagName")).create()
  }
}