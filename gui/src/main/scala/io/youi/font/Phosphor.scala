package io.youi.font

import spice.net._

case class PhosphorIcon(name: String, weight: String = "ph")

object Phosphor {
  var url: URL = url"https://unpkg.com/@phosphor-icons/web@2.1.2/src/regular/style.css"

  val None: PhosphorIcon = PhosphorIcon("", "")

  // Navigation & Actions
  lazy val House: PhosphorIcon          = PhosphorIcon("house")
  lazy val ArrowLeft: PhosphorIcon      = PhosphorIcon("arrow-left")
  lazy val ArrowRight: PhosphorIcon     = PhosphorIcon("arrow-right")
  lazy val ArrowUp: PhosphorIcon        = PhosphorIcon("arrow-up")
  lazy val ArrowDown: PhosphorIcon      = PhosphorIcon("arrow-down")
  lazy val CaretLeft: PhosphorIcon      = PhosphorIcon("caret-left")
  lazy val CaretRight: PhosphorIcon     = PhosphorIcon("caret-right")
  lazy val CaretUp: PhosphorIcon        = PhosphorIcon("caret-up")
  lazy val CaretDown: PhosphorIcon      = PhosphorIcon("caret-down")
  lazy val List: PhosphorIcon           = PhosphorIcon("list")
  lazy val ListBullets: PhosphorIcon    = PhosphorIcon("list-bullets")
  lazy val DotsThree: PhosphorIcon      = PhosphorIcon("dots-three")
  lazy val DotsThreeVertical: PhosphorIcon = PhosphorIcon("dots-three-vertical")
  lazy val Equals: PhosphorIcon         = PhosphorIcon("equals")

  // User & Identity
  lazy val User: PhosphorIcon           = PhosphorIcon("user")
  lazy val UserCircle: PhosphorIcon     = PhosphorIcon("user-circle")
  lazy val Users: PhosphorIcon          = PhosphorIcon("users")
  lazy val IdentificationCard: PhosphorIcon = PhosphorIcon("identification-card")

  // UI Controls
  lazy val MagnifyingGlass: PhosphorIcon = PhosphorIcon("magnifying-glass")
  lazy val X: PhosphorIcon              = PhosphorIcon("x")
  lazy val XCircle: PhosphorIcon        = PhosphorIcon("x-circle")
  lazy val Check: PhosphorIcon          = PhosphorIcon("check")
  lazy val CheckCircle: PhosphorIcon    = PhosphorIcon("check-circle")
  lazy val Plus: PhosphorIcon           = PhosphorIcon("plus")
  lazy val Minus: PhosphorIcon          = PhosphorIcon("minus")
  lazy val Question: PhosphorIcon       = PhosphorIcon("question")
  lazy val Info: PhosphorIcon           = PhosphorIcon("info")
  lazy val Warning: PhosphorIcon        = PhosphorIcon("warning")
  lazy val WarningCircle: PhosphorIcon  = PhosphorIcon("warning-circle")
  lazy val Funnel: PhosphorIcon         = PhosphorIcon("funnel")
  lazy val SortAscending: PhosphorIcon  = PhosphorIcon("sort-ascending")
  lazy val SortDescending: PhosphorIcon = PhosphorIcon("sort-descending")

  // Communication
  lazy val Bell: PhosphorIcon           = PhosphorIcon("bell")
  lazy val BellRinging: PhosphorIcon    = PhosphorIcon("bell-ringing")
  lazy val Chat: PhosphorIcon           = PhosphorIcon("chat")
  lazy val ChatCircle: PhosphorIcon     = PhosphorIcon("chat-circle")
  lazy val Envelope: PhosphorIcon       = PhosphorIcon("envelope")
  lazy val Phone: PhosphorIcon          = PhosphorIcon("phone")
  lazy val PhoneCall: PhosphorIcon      = PhosphorIcon("phone-call")

  // Files & Folders
  lazy val File: PhosphorIcon           = PhosphorIcon("file")
  lazy val FileText: PhosphorIcon       = PhosphorIcon("file-text")
  lazy val FilePlus: PhosphorIcon       = PhosphorIcon("file-plus")
  lazy val Folder: PhosphorIcon         = PhosphorIcon("folder")
  lazy val FolderOpen: PhosphorIcon     = PhosphorIcon("folder-open")
  lazy val FolderPlus: PhosphorIcon     = PhosphorIcon("folder-plus")
  lazy val Clipboard: PhosphorIcon      = PhosphorIcon("clipboard")
  lazy val Copy: PhosphorIcon           = PhosphorIcon("copy")

  // Editing
  lazy val Pencil: PhosphorIcon         = PhosphorIcon("pencil")
  lazy val PencilSimple: PhosphorIcon   = PhosphorIcon("pencil-simple")
  lazy val Trash: PhosphorIcon          = PhosphorIcon("trash")
  lazy val EraseIcon: PhosphorIcon      = PhosphorIcon("eraser")
  lazy val Scissors: PhosphorIcon       = PhosphorIcon("scissors")
  lazy val Undo: PhosphorIcon           = PhosphorIcon("arrow-counter-clockwise")
  lazy val Redo: PhosphorIcon           = PhosphorIcon("arrow-clockwise")

  // Media
  lazy val Image: PhosphorIcon          = PhosphorIcon("image")
  lazy val Images: PhosphorIcon         = PhosphorIcon("images")
  lazy val Camera: PhosphorIcon         = PhosphorIcon("camera")
  lazy val Video: PhosphorIcon          = PhosphorIcon("video")
  lazy val VideoCamera: PhosphorIcon    = PhosphorIcon("video-camera")
  lazy val Microphone: PhosphorIcon     = PhosphorIcon("microphone")
  lazy val MicrophoneSlash: PhosphorIcon = PhosphorIcon("microphone-slash")
  lazy val SpeakerHigh: PhosphorIcon    = PhosphorIcon("speaker-high")
  lazy val SpeakerSlash: PhosphorIcon   = PhosphorIcon("speaker-slash")
  lazy val Play: PhosphorIcon           = PhosphorIcon("play")
  lazy val Pause: PhosphorIcon          = PhosphorIcon("pause")
  lazy val Stop: PhosphorIcon           = PhosphorIcon("stop")
  lazy val SkipForward: PhosphorIcon    = PhosphorIcon("skip-forward")
  lazy val SkipBack: PhosphorIcon       = PhosphorIcon("skip-back")
  lazy val Repeat: PhosphorIcon         = PhosphorIcon("repeat")
  lazy val Shuffle: PhosphorIcon        = PhosphorIcon("shuffle")

  // Data & Storage
  lazy val Star: PhosphorIcon           = PhosphorIcon("star")
  lazy val Heart: PhosphorIcon          = PhosphorIcon("heart")
  lazy val Bookmark: PhosphorIcon       = PhosphorIcon("bookmark")
  lazy val Tag: PhosphorIcon            = PhosphorIcon("tag")
  lazy val Database: PhosphorIcon       = PhosphorIcon("database")
  lazy val HardDrive: PhosphorIcon      = PhosphorIcon("hard-drive")
  lazy val Cloud: PhosphorIcon          = PhosphorIcon("cloud")
  lazy val CloudArrowUp: PhosphorIcon   = PhosphorIcon("cloud-arrow-up")
  lazy val CloudArrowDown: PhosphorIcon = PhosphorIcon("cloud-arrow-down")
  lazy val Download: PhosphorIcon       = PhosphorIcon("download")
  lazy val Upload: PhosphorIcon         = PhosphorIcon("upload")

  // Security
  lazy val Shield: PhosphorIcon         = PhosphorIcon("shield")
  lazy val ShieldCheck: PhosphorIcon    = PhosphorIcon("shield-check")
  lazy val Lock: PhosphorIcon           = PhosphorIcon("lock")
  lazy val LockOpen: PhosphorIcon       = PhosphorIcon("lock-open")
  lazy val Key: PhosphorIcon            = PhosphorIcon("key")
  lazy val Eye: PhosphorIcon            = PhosphorIcon("eye")
  lazy val EyeSlash: PhosphorIcon       = PhosphorIcon("eye-slash")

  // Settings & System
  lazy val Gear: PhosphorIcon           = PhosphorIcon("gear")
  lazy val GearSix: PhosphorIcon        = PhosphorIcon("gear-six")
  lazy val Sliders: PhosphorIcon        = PhosphorIcon("sliders")
  lazy val Wrench: PhosphorIcon         = PhosphorIcon("wrench")
  lazy val Power: PhosphorIcon          = PhosphorIcon("power")
  lazy val SignIn: PhosphorIcon         = PhosphorIcon("sign-in")
  lazy val SignOut: PhosphorIcon        = PhosphorIcon("sign-out")

  // Time & Date
  lazy val Calendar: PhosphorIcon       = PhosphorIcon("calendar")
  lazy val CalendarBlank: PhosphorIcon  = PhosphorIcon("calendar-blank")
  lazy val Clock: PhosphorIcon          = PhosphorIcon("clock")
  lazy val Timer: PhosphorIcon          = PhosphorIcon("timer")

  // Location & Maps
  lazy val MapPin: PhosphorIcon         = PhosphorIcon("map-pin")
  lazy val Map: PhosphorIcon            = PhosphorIcon("map")
  lazy val Globe: PhosphorIcon          = PhosphorIcon("globe")
  lazy val Compass: PhosphorIcon        = PhosphorIcon("compass")
  lazy val Navigation: PhosphorIcon     = PhosphorIcon("navigation")

  // Network & Connectivity
  lazy val Wifi: PhosphorIcon           = PhosphorIcon("wifi")
  lazy val WifiSlash: PhosphorIcon      = PhosphorIcon("wifi-slash")
  lazy val Bluetooth: PhosphorIcon      = PhosphorIcon("bluetooth")
  lazy val Link: PhosphorIcon           = PhosphorIcon("link")
  lazy val LinkBreak: PhosphorIcon      = PhosphorIcon("link-break")
  lazy val Share: PhosphorIcon          = PhosphorIcon("share")
  lazy val Export: PhosphorIcon         = PhosphorIcon("export")

  // Commerce
  lazy val ShoppingCart: PhosphorIcon   = PhosphorIcon("shopping-cart")
  lazy val ShoppingBag: PhosphorIcon    = PhosphorIcon("shopping-bag")
  lazy val CreditCard: PhosphorIcon     = PhosphorIcon("credit-card")
  lazy val Money: PhosphorIcon          = PhosphorIcon("money")
  lazy val Coins: PhosphorIcon          = PhosphorIcon("coins")
  lazy val Receipt: PhosphorIcon        = PhosphorIcon("receipt")

  // Nature & Environment
  lazy val Sun: PhosphorIcon            = PhosphorIcon("sun")
  lazy val Moon: PhosphorIcon           = PhosphorIcon("moon")
  lazy val Lightning: PhosphorIcon      = PhosphorIcon("lightning")
  lazy val Fire: PhosphorIcon           = PhosphorIcon("fire")
  lazy val Drop: PhosphorIcon           = PhosphorIcon("drop")
  lazy val Leaf: PhosphorIcon           = PhosphorIcon("leaf")
  lazy val Tree: PhosphorIcon           = PhosphorIcon("tree")
  lazy val Planet: PhosphorIcon         = PhosphorIcon("planet")

  // Development
  lazy val Code: PhosphorIcon           = PhosphorIcon("code")
  lazy val CodeBlock: PhosphorIcon      = PhosphorIcon("code-block")
  lazy val Terminal: PhosphorIcon       = PhosphorIcon("terminal")
  lazy val Bug: PhosphorIcon            = PhosphorIcon("bug")
  lazy val GitBranch: PhosphorIcon      = PhosphorIcon("git-branch")
  lazy val Robot: PhosphorIcon          = PhosphorIcon("robot")

  // Logos
  lazy val AndroidLogo: PhosphorIcon    = PhosphorIcon("android-logo")
  lazy val AppleLogo: PhosphorIcon      = PhosphorIcon("apple-logo")
  lazy val WindowsLogo: PhosphorIcon    = PhosphorIcon("windows-logo")
  lazy val LinuxLogo: PhosphorIcon      = PhosphorIcon("linux-logo")
  lazy val GithubLogo: PhosphorIcon     = PhosphorIcon("github-logo")
  lazy val TwitterLogo: PhosphorIcon    = PhosphorIcon("twitter-logo")
  lazy val FacebookLogo: PhosphorIcon   = PhosphorIcon("facebook-logo")
  lazy val InstagramLogo: PhosphorIcon  = PhosphorIcon("instagram-logo")
  lazy val YoutubeLogo: PhosphorIcon    = PhosphorIcon("youtube-logo")
  lazy val SpotifyLogo: PhosphorIcon    = PhosphorIcon("spotify-logo")
  lazy val SlackLogo: PhosphorIcon      = PhosphorIcon("slack-logo")
  lazy val DiscordLogo: PhosphorIcon    = PhosphorIcon("discord-logo")

  // Misc
  lazy val Gift: PhosphorIcon           = PhosphorIcon("gift")
  lazy val Smiley: PhosphorIcon         = PhosphorIcon("smiley")
  lazy val Buildings: PhosphorIcon      = PhosphorIcon("buildings")
  lazy val Bank: PhosphorIcon           = PhosphorIcon("bank")
  lazy val Airplane: PhosphorIcon       = PhosphorIcon("airplane")
  lazy val Car: PhosphorIcon            = PhosphorIcon("car")
  lazy val Bicycle: PhosphorIcon        = PhosphorIcon("bicycle")

  /** Load bold-weight icons (in addition to regular). */
  lazy val boldUrl: URL = url"https://unpkg.com/@phosphor-icons/web@2.1.2/src/bold/style.css"
  /** Load fill-weight icons (in addition to regular). */
  lazy val fillUrl: URL = url"https://unpkg.com/@phosphor-icons/web@2.1.2/src/fill/style.css"

  object Bold {
    def apply(icon: PhosphorIcon): PhosphorIcon = icon.copy(weight = "ph-bold")
  }
  object Fill {
    def apply(icon: PhosphorIcon): PhosphorIcon = icon.copy(weight = "ph-fill")
  }
  object Thin {
    def apply(icon: PhosphorIcon): PhosphorIcon = icon.copy(weight = "ph-thin")
  }
  object Light {
    def apply(icon: PhosphorIcon): PhosphorIcon = icon.copy(weight = "ph-light")
  }
  object Duotone {
    def apply(icon: PhosphorIcon): PhosphorIcon = icon.copy(weight = "ph-duotone")
  }
}
