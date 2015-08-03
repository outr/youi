package org.hyperscala.io.bootstrap

import org.powerscala.enum.{Enumerated, EnumEntry}

object Specification {
  sealed trait Component {
    def name: String
    def tag: Option[String]
    def cssMatcher: String => Boolean
  }

  case class DefComponent(name: String,
                          tag: Option[String],
                          cssMatcher: String => Boolean,
                          properties: Property*)
    extends Component

  case class EnumComponent(name: String,
                           tag: Option[String],
                           cssMatcher: String => Boolean,
                           values: Value.Option*)
    extends Component

  case class Property(name: String, values: Value*)

  sealed trait Value
  object Value {
    case class Boolean(css: String) extends Value
    case class Set(name: String, options: Option*) extends Value
    case class Enum[T](values: Seq[T], css: T => String) extends Value
    case class Option(name: String, css: String) extends Value
    case class Instance[T](value: T) extends Value
  }

  val components = Seq(
    DefComponent("Alert",
      Some("div"),
      _ == "alert",
      Property("alertType",
        Value.Set("AlertType",
          Value.Option("Success", "alert-success"),
          Value.Option("Info", "alert-info"),
          Value.Option("Warning", "alert-warning"),
          Value.Option("Danger", "alert-danger")
        )
      )
    ),

    DefComponent("Badge",
      Some("span"),
      _ == "badge",
      Property("pullRight", Value.Boolean("pull-right"))),

    DefComponent("Button",
      Some("button"),
      _ == "btn",
      Property("buttonStyle",
        Value.Set("ButtonStyle",
          Value.Option("Default", "btn-default"),
          Value.Option("Primary", "btn-primary"),
          Value.Option("Success", "btn-success"),
          Value.Option("Info", "btn-info"),
          Value.Option("Warning", "btn-warning"),
          Value.Option("Danger", "btn-danger"),
          Value.Option("Link", "btn-link")
        )
      ),

      Property("buttonSize",
        Value.Set("ButtonSize",
          Value.Option("Large", "btn-lg"),
          Value.Option("Small", "btn-sm"),
          Value.Option("ExtraSmall", "btn-xs")
        )
      ),

      Property("block", Value.Boolean("btn-block")),
      Property("active", Value.Boolean("active"))
    ),

    DefComponent("ButtonGroup",
      Some("div"),
      _ == "btn-group"),

    // TODO Support CollapsiblePanel

    DefComponent("Column",
      None,
      _.startsWith("col-"),
      Property("large", Value.Enum[Int](1 to 12, columns => s"col-lg-$columns")),
      Property("largeOffset", Value.Enum[Int](1 to 12, columns => s"col-lg-offset-$columns")),
      Property("medium", Value.Enum[Int](1 to 12, columns => s"col-md-$columns")),
      Property("mediumOffset", Value.Enum[Int](1 to 12, columns => s"col-md-offset-$columns")),
      Property("small", Value.Enum[Int](1 to 12, columns => s"col-sm-$columns")),
      Property("smallOffset", Value.Enum[Int](1 to 12, columns => s"col-sm-offset-$columns")),
      Property("extraSmall", Value.Enum[Int](1 to 12, columns => s"col-xs-$columns")),
      Property("extraSmallOffset", Value.Enum[Int](1 to 12, columns => s"col-xs-offset-$columns"))
    ),

    DefComponent("Container",
      Some("div"),
      _ == "container"),

    // TODO Call add()
    DefComponent("Description",
      Some("dl"),
      _ => true,
      Property("horizontal", Value.Boolean("dl-horizontal"))),

    DefComponent("Form",
      Some("form"),
      _ == "form",
      Property("inline", Value.Boolean("form-inline")),
      Property("horizontal", Value.Boolean("form-horizontal"))),

    DefComponent("FormGroup",
      Some("div"),
      _ == "form-group"),

    EnumComponent("Glyphicon",
      Some("span"),
      _ == "glyphicon",
      Glyphicon.values.map(g =>
        Value.Option(g.toString, s"glyphicon-${g.className}")): _*),

    // TODO Support Header
    // TODO Support HolderImage

    // TODO Call addOn()
    DefComponent("InputGroup",
      Some("div"),
      _ == "input-group",
      Property("size",
        Value.Set("InputGroupSize",
          Value.Option("Large", "input-group-lg"),
          Value.Option("Small", "input-group-sm")
        )
      )
    ),

    DefComponent("Jumbotron",
      Some("div"),
      _ == "jumbotron"),

    DefComponent("Label",
      Some("span"),
      _ == "label",
      Property("labelStyle",
        Value.Set("LabelStyle",
          Value.Option("Default", "label-default"),
          Value.Option("Primary", "label-primary"),
          Value.Option("Success", "label-success"),
          Value.Option("Info", "label-info"),
          Value.Option("Warning", "label-warning"),
          Value.Option("Danger", "label-danger")
        )
      )
    ),

    // TODO Call addItem() etc.
    DefComponent("ListGroup",
      Some("div"),
      _ == "list-group"),

    DefComponent("ListItem",
      Some("li"),
      _ => true,
      Property("active", Value.Boolean("active"))),

    // TODO Detect children
    DefComponent("NavBar",
      Some("div"),
      _ == "navbar",
      Property("active", Value.Boolean("active"))),

    // TODO Detect heading, panelTitle, outer, body
    DefComponent("Panel",
      Some("div"),
      _ == "panel",
      Property("panelType",
        Value.Set("PanelType",
          Value.Option("Default", "panel-default"),
          Value.Option("Primary", "panel-primary"),
          Value.Option("Success", "panel-success"),
          Value.Option("Info", "panel-info"),
          Value.Option("Warning", "panel-warning"),
          Value.Option("Danger", "panel-danger")
        )
      )
    ),

    // TODO Call addProgress()
    DefComponent("ProgressBar",
      Some("div"),
      _ == "progress",
      Property("striped", Value.Boolean("progress-striped")),
      Property("active", Value.Boolean("progress-active"))
    ),

    DefComponent("Row", Some("div"), _ == "row"),

    // TODO Detect head and body
    DefComponent("Table",
      Some("table"),
      _ == "table",
      Property("striped", Value.Boolean("table-striped")),
      Property("bordered", Value.Boolean("table-bordered")),
      Property("hover", Value.Boolean("table-hover")),
      Property("condensed", Value.Boolean("table-condensed"))
    ),

    DefComponent("TableRow",
      Some("tr"),
      _ => true,  // TODO only within Table
      Property("rowStyle",
        Value.Set("RowStyle",
          Value.Option("Default", "default"),
          Value.Option("Active", "active"),
          Value.Option("Success", "success"),
          Value.Option("Info", "info"),
          Value.Option("Warning", "warning"),
          Value.Option("Danger", "danger")
        )
      )
    ),

    DefComponent("Tabs",
      Some("ul"),
      _ == "nav",
      Property("tabs", Value.Boolean("nav-tabs")),
      Property("pills", Value.Boolean("nav-pills")),
      Property("stacked", Value.Boolean("nav-stacked")),
      Property("justified", Value.Boolean("nav-justified"))
    ),

    DefComponent("TabEntry",
      Some("li"),
      _ => true,  // TODO only within Tabs
      Property("active", Value.Boolean("active")),
      Property("disabled", Value.Boolean("disabled"))
    ),

    DefComponent("Well",
      Some("div"),
      _ == "well",
      Property("wellType",
        Value.Set("WellType",
          Value.Option("Default", "well-default"),
          Value.Option("Small", "well-sm"),
          Value.Option("Larger", "well-lg")
        )
      )
    )
  )

  // TODO Read from Bootstrap CSS file
  sealed abstract class Glyphicon(val className: String) extends EnumEntry
  object Glyphicon extends Enumerated[Glyphicon] {
    case object Asterisk extends Glyphicon("asterisk")
    case object Plus extends Glyphicon("plus")
    case object Euro extends Glyphicon("euro")
    case object Minus extends Glyphicon("minus")
    case object Cloud extends Glyphicon("cloud")
    case object Envelope extends Glyphicon("envelope")
    case object Pencil extends Glyphicon("pencil")
    case object Glass extends Glyphicon("glass")
    case object Music extends Glyphicon("music")
    case object Search extends Glyphicon("search")
    case object Heart extends Glyphicon("heart")
    case object Star extends Glyphicon("star")
    case object StarEmpty extends Glyphicon("star-empty")
    case object User extends Glyphicon("user")
    case object Film extends Glyphicon("film")
    case object ThLarge extends Glyphicon("th-large")
    case object Th extends Glyphicon("th")
    case object ThList extends Glyphicon("th-list")
    case object Ok extends Glyphicon("ok")
    case object Remove extends Glyphicon("remove")
    case object ZoomIn extends Glyphicon("zoom-in")
    case object ZoomOut extends Glyphicon("zoom-out")
    case object Off extends Glyphicon("off")
    case object Signal extends Glyphicon("signal")
    case object Cog extends Glyphicon("cog")
    case object Trash extends Glyphicon("trash")
    case object Home extends Glyphicon("home")
    case object File extends Glyphicon("file")
    case object Time extends Glyphicon("time")
    case object Road extends Glyphicon("road")
    case object DownloadAlt extends Glyphicon("download-alt")
    case object Download extends Glyphicon("download")
    case object Upload extends Glyphicon("upload")
    case object Inbox extends Glyphicon("inbox")
    case object PlayCircle extends Glyphicon("play-circle")
    case object Repeat extends Glyphicon("repeat")
    case object Refresh extends Glyphicon("refresh")
    case object ListAlt extends Glyphicon("list-alt")
    case object Lock extends Glyphicon("lock")
    case object Flag extends Glyphicon("flag")
    case object Headphones extends Glyphicon("headphones")
    case object VolumeOff extends Glyphicon("volume-off")
    case object VolumeDown extends Glyphicon("volume-down")
    case object VolumeUp extends Glyphicon("volume-up")
    case object Qrcode extends Glyphicon("qrcode")
    case object Barcode extends Glyphicon("barcode")
    case object Tag extends Glyphicon("tag")
    case object Tags extends Glyphicon("tags")
    case object Book extends Glyphicon("book")
    case object Bookmark extends Glyphicon("bookmark")
    case object Print extends Glyphicon("print")
    case object Camera extends Glyphicon("camera")
    case object Font extends Glyphicon("font")
    case object Bold extends Glyphicon("bold")
    case object Italic extends Glyphicon("italic")
    case object TextHeight extends Glyphicon("text-height")
    case object TextWidth extends Glyphicon("text-width")
    case object AlignLeft extends Glyphicon("align-left")
    case object AlignCenter extends Glyphicon("align-center")
    case object AlignRight extends Glyphicon("align-right")
    case object AlignJustify extends Glyphicon("align-justify")
    case object List extends Glyphicon("list")
    case object IndentLeft extends Glyphicon("indent-left")
    case object IndentRight extends Glyphicon("indent-right")
    case object FacetimeVideo extends Glyphicon("facetime-video")
    case object Picture extends Glyphicon("picture")
    case object MapMarker extends Glyphicon("map-marker")
    case object Adjust extends Glyphicon("adjust")
    case object Tint extends Glyphicon("tint")
    case object Edit extends Glyphicon("edit")
    case object Share extends Glyphicon("share")
    case object Check extends Glyphicon("check")
    case object Move extends Glyphicon("move")
    case object StepBackward extends Glyphicon("step-backward")
    case object FastBackward extends Glyphicon("fast-backward")
    case object Backward extends Glyphicon("backward")
    case object Play extends Glyphicon("play")
    case object Pause extends Glyphicon("pause")
    case object Stop extends Glyphicon("stop")
    case object Forward extends Glyphicon("forward")
    case object FastForward extends Glyphicon("fast-forward")
    case object StepForward extends Glyphicon("step-forward")
    case object Eject extends Glyphicon("eject")
    case object ChevronLeft extends Glyphicon("chevron-left")
    case object ChevronRight extends Glyphicon("chevron-right")
    case object PlusSign extends Glyphicon("plus-sign")
    case object MinusSign extends Glyphicon("minus-sign")
    case object RemoveSign extends Glyphicon("remove-sign")
    case object OkSign extends Glyphicon("ok-sign")
    case object QuestionSign extends Glyphicon("question-sign")
    case object InfoSign extends Glyphicon("info-sign")
    case object Screenshot extends Glyphicon("screenshot")
    case object RemoveCircle extends Glyphicon("remove-circle")
    case object OkCircle extends Glyphicon("ok-circle")
    case object BanCircle extends Glyphicon("ban-circle")
    case object ArrowLeft extends Glyphicon("arrow-left")
    case object ArrowRight extends Glyphicon("arrow-right")
    case object ArrowUp extends Glyphicon("arrow-up")
    case object ArrowDown extends Glyphicon("arrow-down")
    case object ShareAlt extends Glyphicon("share-alt")
    case object ResizeFull extends Glyphicon("resize-full")
    case object ResizeSmall extends Glyphicon("resize-small")
    case object ExclamationSign extends Glyphicon("exclamation-sign")
    case object Gift extends Glyphicon("gift")
    case object Leaf extends Glyphicon("leaf")
    case object Fire extends Glyphicon("fire")
    case object EyeOpen extends Glyphicon("eye-open")
    case object EyeClose extends Glyphicon("eye-close")
    case object WarningSign extends Glyphicon("warning-sign")
    case object Plane extends Glyphicon("plane")
    case object Calendar extends Glyphicon("calendar")
    case object Random extends Glyphicon("random")
    case object Comment extends Glyphicon("comment")
    case object Magnet extends Glyphicon("magnet")
    case object ChevronUp extends Glyphicon("chevron-up")
    case object ChevronDown extends Glyphicon("chevron-down")
    case object Retweet extends Glyphicon("retweet")
    case object ShoppingCart extends Glyphicon("shopping-cart")
    case object FolderClose extends Glyphicon("folder-close")
    case object FolderOpen extends Glyphicon("folder-open")
    case object ResizeVertical extends Glyphicon("resize-vertical")
    case object ResizeHorizontal extends Glyphicon("resize-horizontal")
    case object Hdd extends Glyphicon("hdd")
    case object Bullhorn extends Glyphicon("bullhorn")
    case object Bell extends Glyphicon("bell")
    case object Certificate extends Glyphicon("certificate")
    case object ThumbsUp extends Glyphicon("thumbs-up")
    case object ThumbsDown extends Glyphicon("thumbs-down")
    case object HandRight extends Glyphicon("hand-right")
    case object HandLeft extends Glyphicon("hand-left")
    case object HandUp extends Glyphicon("hand-up")
    case object HandDown extends Glyphicon("hand-down")
    case object CircleArrowRight extends Glyphicon("circle-arrow-right")
    case object CircleArrowLeft extends Glyphicon("circle-arrow-left")
    case object CircleArrowUp extends Glyphicon("circle-arrow-up")
    case object CircleArrowDown extends Glyphicon("circle-arrow-down")
    case object Globe extends Glyphicon("globe")
    case object Wrench extends Glyphicon("wrench")
    case object Tasks extends Glyphicon("tasks")
    case object Filter extends Glyphicon("filter")
    case object Briefcase extends Glyphicon("briefcase")
    case object Fullscreen extends Glyphicon("fullscreen")
    case object Dashboard extends Glyphicon("dashboard")
    case object Paperclip extends Glyphicon("paperclip")
    case object HeartEmpty extends Glyphicon("heart-empty")
    case object Link extends Glyphicon("link")
    case object Phone extends Glyphicon("phone")
    case object Pushpin extends Glyphicon("pushpin")
    case object Usd extends Glyphicon("usd")
    case object Gbp extends Glyphicon("gbp")
    case object Sort extends Glyphicon("sort")
    case object SortByAlphabet extends Glyphicon("sort-by-alphabet")
    case object SortByAlphabetAlt extends Glyphicon("sort-by-alphabet-alt")
    case object SortByOrder extends Glyphicon("sort-by-order")
    case object SortByOrderAlt extends Glyphicon("sort-by-order-alt")
    case object SortByAttributes extends Glyphicon("sort-by-attributes")
    case object SortByAttributesAlt extends Glyphicon("sort-by-attributes-alt")
    case object Unchecked extends Glyphicon("unchecked")
    case object Expand extends Glyphicon("expand")
    case object CollapseDown extends Glyphicon("collapse-down")
    case object CollapseUp extends Glyphicon("collapse-up")
    case object LogIn extends Glyphicon("log-in")
    case object Flash extends Glyphicon("flash")
    case object LogOut extends Glyphicon("log-out")
    case object NewWindow extends Glyphicon("new-window")
    case object Record extends Glyphicon("record")
    case object Save extends Glyphicon("save")
    case object Open extends Glyphicon("open")
    case object Saved extends Glyphicon("saved")
    case object Import extends Glyphicon("import")
    case object Export extends Glyphicon("export")
    case object Send extends Glyphicon("send")
    case object FloppyDisk extends Glyphicon("floppy-disk")
    case object FloppySaved extends Glyphicon("floppy-saved")
    case object FloppyRemove extends Glyphicon("floppy-remove")
    case object FloppySave extends Glyphicon("floppy-save")
    case object FloppyOpen extends Glyphicon("floppy-open")
    case object CreditCard extends Glyphicon("credit-card")
    case object Transfer extends Glyphicon("transfer")
    case object Cutlery extends Glyphicon("cutlery")
    case object Header extends Glyphicon("header")
    case object Compressed extends Glyphicon("compressed")
    case object Earphone extends Glyphicon("earphone")
    case object PhoneAlt extends Glyphicon("phone-alt")
    case object Tower extends Glyphicon("tower")
    case object Stats extends Glyphicon("stats")
    case object SdVideo extends Glyphicon("sd-video")
    case object HdVideo extends Glyphicon("hd-video")
    case object Subtitles extends Glyphicon("subtitles")
    case object SoundStereo extends Glyphicon("sound-stereo")
    case object SoundDolby extends Glyphicon("sound-dolby")
    case object Sound51 extends Glyphicon("sound-5-1")
    case object Sound61 extends Glyphicon("sound-6-1")
    case object Sound71 extends Glyphicon("sound-7-1")
    case object CopyrightMark extends Glyphicon("copyright-mark")
    case object RegistrationMark extends Glyphicon("registration-mark")
    case object CloudDownload extends Glyphicon("cloud-download")
    case object CloudUpload extends Glyphicon("cloud-upload")
    case object TreeConifer extends Glyphicon("tree-conifer")
    case object TreeDeciduous extends Glyphicon("tree-deciduous")

    val values = findValues.toVector
  }
}
