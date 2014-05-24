package org.hyperscala.css

import org.powerscala.log.Logging
import org.powerscala.event.Listenable
import org.hyperscala.html._
import org.hyperscala.AttributeContainer
import org.powerscala.reflect._
import org.powerscala.hierarchy.ChildLike
import org.hyperscala.selector.Selector
import org.hyperscala.css.attributes.Length
import org.hyperscala.css.extra._
import scala.Some
import org.hyperscala.html.tag.Head

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StyleSheet(val hierarchicalParent: Listenable,
                 val selector: Selector) extends Logging with Listenable with AttributeContainer[StyleSheetAttribute[_]] with ChildLike[Listenable] {
  protected def fieldsMap = StyleSheet.fieldsMap

  def selectorString = selector.value

  implicit val thisStyleSheet = this

  lazy val alignmentAdjust = new StyleSheetAttribute(Style.alignmentAdjust)
  lazy val alignmentBaseline = new StyleSheetAttribute(Style.alignmentBaseline)
  lazy val animation = new StyleSheetAttribute(Style.animation)
  lazy val animationDelay = new StyleSheetAttribute(Style.animationDelay)
  lazy val animationDirection = new StyleSheetAttribute(Style.animationDirection)
  lazy val animationDuration = new StyleSheetAttribute(Style.animationDuration)
  lazy val animationIterationCount = new StyleSheetAttribute(Style.animationIterationCount)
  lazy val animationName = new StyleSheetAttribute(Style.animationName)
  lazy val animationPlayState = new StyleSheetAttribute(Style.animationPlayState)
  lazy val animationTimingFunction = new StyleSheetAttribute(Style.animationTimingFunction)
  lazy val appearance = new StyleSheetAttribute(Style.appearance)
  lazy val backfaceVisibility = new StyleSheetAttribute(Style.backfaceVisibility)
  lazy val background = new StyleSheetAttribute(Style.background)
  lazy val backgroundAttachment = new StyleSheetAttribute(Style.backgroundAttachment)
  lazy val backgroundClip = new StyleSheetAttribute(Style.backgroundClip)
  lazy val backgroundColor = new StyleSheetAttribute(Style.backgroundColor)
  lazy val backgroundImage = new StyleSheetAttribute(Style.backgroundImage)
  lazy val backgroundOrigin = new StyleSheetAttribute(Style.backgroundOrigin)
  lazy val backgroundPosition = new StyleSheetAttribute(Style.backgroundPosition)
  lazy val backgroundPositionOffsetX = new BackgroundPositionXOffset(this)
  lazy val backgroundPositionOffsetY = new BackgroundPositionYOffset(this)
  lazy val backgroundPositionHorizontal = new BackgroundPositionHorizontal(this)
  lazy val backgroundPositionVertical = new BackgroundPositionVertical(this)
  lazy val backgroundRepeat = new StyleSheetAttribute(Style.backgroundRepeat)
  lazy val backgroundRepeatHorizontal = new BackgroundRepeatHorizontal(this)
  lazy val backgroundRepeatVertical = new BackgroundRepeatVertical(this)
  lazy val backgroundSize = new StyleSheetAttribute(Style.backgroundSize)
  lazy val backgroundSizeHorizontal = new BackgroundSizeHorizontal(this)
  lazy val backgroundSizeVertical = new BackgroundSizeVertical(this)
  lazy val baselineShift = new StyleSheetAttribute(Style.baselineShift)
  lazy val bookmarkLabel = new StyleSheetAttribute(Style.bookmarkLabel)
  lazy val bookmarkLevel = new StyleSheetAttribute(Style.bookmarkLevel)
  lazy val bookmarkTarget = new StyleSheetAttribute(Style.bookmarkTarget)
  lazy val border = new StyleSheetAttribute(Style.border)
  lazy val borderBottom = new StyleSheetAttribute(Style.borderBottom)
  lazy val borderBottomColor = new StyleSheetAttribute(Style.borderBottomColor)
  lazy val borderBottomLeftRadius = new StyleSheetAttribute(Style.borderBottomLeftRadius)
  lazy val borderBottomRightRadius = new StyleSheetAttribute(Style.borderBottomRightRadius)
  lazy val borderBottomStyle = new StyleSheetAttribute(Style.borderBottomStyle)
  lazy val borderBottomWidth = new StyleSheetAttribute(Style.borderBottomWidth)
  lazy val borderCollapse = new StyleSheetAttribute(Style.borderCollapse)
  lazy val borderColor = new StyleSheetAttribute(Style.borderColor)
  lazy val borderImage = new StyleSheetAttribute(Style.borderImage)
  lazy val borderImageOutset = new StyleSheetAttribute(Style.borderImageOutset)
  lazy val borderImageRepeat = new StyleSheetAttribute(Style.borderImageRepeat)
  lazy val borderImageSlice = new StyleSheetAttribute(Style.borderImageSlice)
  lazy val borderImageSource = new StyleSheetAttribute(Style.borderImageSource)
  lazy val borderImageWidth = new StyleSheetAttribute(Style.borderImageWidth)
  lazy val borderLeft = new StyleSheetAttribute(Style.borderLeft)
  lazy val borderLeftColor = new StyleSheetAttribute(Style.borderLeftColor)
  lazy val borderLeftStyle = new StyleSheetAttribute(Style.borderLeftStyle)
  lazy val borderLeftWidth = new StyleSheetAttribute(Style.borderLeftWidth)
  lazy val borderRadius = new StyleSheetAttribute(Style.borderRadius)
  lazy val borderRight = new StyleSheetAttribute(Style.borderRight)
  lazy val borderRightColor = new StyleSheetAttribute(Style.borderRightColor)
  lazy val borderRightStyle = new StyleSheetAttribute(Style.borderRightStyle)
  lazy val borderRightWidth = new StyleSheetAttribute(Style.borderRightWidth)
  lazy val borderSpacing = new StyleSheetAttribute(Style.borderSpacing)
  lazy val borderStyle = new StyleSheetAttribute(Style.borderStyle)
  lazy val borderTop = new StyleSheetAttribute(Style.borderTop)
  lazy val borderTopColor = new StyleSheetAttribute(Style.borderTopColor)
  lazy val borderTopLeftRadius = new StyleSheetAttribute(Style.borderTopLeftRadius)
  lazy val borderTopRightRadius = new StyleSheetAttribute(Style.borderTopRightRadius)
  lazy val borderTopStyle = new StyleSheetAttribute(Style.borderTopStyle)
  lazy val borderTopWidth = new StyleSheetAttribute(Style.borderTopWidth)
  lazy val borderWidth = new StyleSheetAttribute(Style.borderWidth)
  lazy val bottom = new StyleSheetAttribute(Style.bottom)
  lazy val boxAlign = new StyleSheetAttribute(Style.boxAlign)
  lazy val boxDecorationBreak = new StyleSheetAttribute(Style.boxDecorationBreak)
  lazy val boxDirection = new StyleSheetAttribute(Style.boxDirection)
  lazy val boxFlex = new StyleSheetAttribute(Style.boxFlex)
  lazy val boxFlexGroup = new StyleSheetAttribute(Style.boxFlexGroup)
  lazy val boxLines = new StyleSheetAttribute(Style.boxLines)
  lazy val boxOrdinalGroup = new StyleSheetAttribute(Style.boxOrdinalGroup)
  lazy val boxOrient = new StyleSheetAttribute(Style.boxOrient)
  lazy val boxPack = new StyleSheetAttribute(Style.boxPack)
  lazy val boxShadow = new StyleSheetAttribute(Style.boxShadow)
  lazy val boxSizing = new StyleSheetAttribute(Style.boxSizing)
  lazy val captionSide = new StyleSheetAttribute(Style.captionSide)
  lazy val clear = new StyleSheetAttribute(Style.clear)
  lazy val clip = new StyleSheetAttribute(Style.clip)
  lazy val color = new StyleSheetAttribute(Style.color)
  lazy val colorProfile = new StyleSheetAttribute(Style.colorProfile)
  lazy val columnCount = new StyleSheetAttribute(Style.columnCount)
  lazy val columnFill = new StyleSheetAttribute(Style.columnFill)
  lazy val columnGap = new StyleSheetAttribute(Style.columnGap)
  lazy val columnRule = new StyleSheetAttribute(Style.columnRule)
  lazy val columnRuleColor = new StyleSheetAttribute(Style.columnRuleColor)
  lazy val columnRuleStyle = new StyleSheetAttribute(Style.columnRuleStyle)
  lazy val columnRuleWidth = new StyleSheetAttribute(Style.columnRuleWidth)
  lazy val columnSpan = new StyleSheetAttribute(Style.columnSpan)
  lazy val columnWidth = new StyleSheetAttribute(Style.columnWidth)
  lazy val columns = new StyleSheetAttribute(Style.columns)
  lazy val content = new StyleSheetAttribute(Style.content)
  lazy val counterIncrement = new StyleSheetAttribute(Style.counterIncrement)
  lazy val counterReset = new StyleSheetAttribute(Style.counterReset)
  lazy val crop = new StyleSheetAttribute(Style.crop)
  lazy val cursor = new StyleSheetAttribute(Style.cursor)
  lazy val direction = new StyleSheetAttribute(Style.direction)
  lazy val display = new StyleSheetAttribute(Style.display)
  lazy val dominantBaseline = new StyleSheetAttribute(Style.dominantBaseline)
  lazy val dropInitialAfterAdjust = new StyleSheetAttribute(Style.dropInitialAfterAdjust)
  lazy val dropInitialAfterAlign = new StyleSheetAttribute(Style.dropInitialAfterAlign)
  lazy val dropInitialBeforeAdjust = new StyleSheetAttribute(Style.dropInitialBeforeAdjust)
  lazy val dropInitialBeforeAlign = new StyleSheetAttribute(Style.dropInitialBeforeAlign)
  lazy val dropInitialSize = new StyleSheetAttribute(Style.dropInitialSize)
  lazy val dropInitialValue = new StyleSheetAttribute(Style.dropInitialValue)
  lazy val emptyCells = new StyleSheetAttribute(Style.emptyCells)
  lazy val fit = new StyleSheetAttribute(Style.fit)
  lazy val fitPosition = new StyleSheetAttribute(Style.fitPosition)
  lazy val float = new StyleSheetAttribute(Style.float)
  lazy val floatOffset = new StyleSheetAttribute(Style.floatOffset)
  lazy val font = new StyleSheetAttribute(Style.font)
  lazy val fontFace = new StyleSheetAttribute(Style.fontFace)
  lazy val fontFamily = new StyleSheetAttribute(Style.fontFamily)
  lazy val fontSize = new StyleSheetAttribute(Style.fontSize)
  lazy val fontSizeAdjust = new StyleSheetAttribute(Style.fontSizeAdjust)
  lazy val fontStretch = new StyleSheetAttribute(Style.fontStretch)
  lazy val fontStyle = new StyleSheetAttribute(Style.fontStyle)
  lazy val fontVariant = new StyleSheetAttribute(Style.fontVariant)
  lazy val fontWeight = new StyleSheetAttribute(Style.fontWeight)
  lazy val gridColumns = new StyleSheetAttribute(Style.gridColumns)
  lazy val gridRows = new StyleSheetAttribute(Style.gridRows)
  lazy val hangingPunctuation = new StyleSheetAttribute(Style.hangingPunctuation)
  lazy val height = new StyleSheetAttribute(Style.height)
  lazy val hyphenateAfter = new StyleSheetAttribute(Style.hyphenateAfter)
  lazy val hyphenateBefore = new StyleSheetAttribute(Style.hyphenateBefore)
  lazy val hyphenateCharacter = new StyleSheetAttribute(Style.hyphenateCharacter)
  lazy val hyphenateLines = new StyleSheetAttribute(Style.hyphenateLines)
  lazy val hyphenateResource = new StyleSheetAttribute(Style.hyphenateResource)
  lazy val hyphens = new StyleSheetAttribute(Style.hyphens)
  lazy val icon = new StyleSheetAttribute(Style.icon)
  lazy val imageOrientation = new StyleSheetAttribute(Style.imageOrientation)
  lazy val imageResolution = new StyleSheetAttribute(Style.imageResolution)
  lazy val inlineBoxAlign = new StyleSheetAttribute(Style.inlineBoxAlign)
  lazy val left = new StyleSheetAttribute(Style.left)
  lazy val letterSpacing = new StyleSheetAttribute(Style.letterSpacing)
  lazy val lineHeight = new StyleSheetAttribute(Style.lineHeight)
  lazy val lineStacking = new StyleSheetAttribute(Style.lineStacking)
  lazy val lineStackingRuby = new StyleSheetAttribute(Style.lineStackingRuby)
  lazy val lineStackingShift = new StyleSheetAttribute(Style.lineStackingShift)
  lazy val lineStackingStrategy = new StyleSheetAttribute(Style.lineStackingStrategy)
  lazy val listStyle = new StyleSheetAttribute(Style.listStyle)
  lazy val listStyleImage = new StyleSheetAttribute(Style.listStyleImage)
  lazy val listStylePosition = new StyleSheetAttribute(Style.listStylePosition)
  lazy val listStyleType = new StyleSheetAttribute(Style.listStyleType)
  lazy val margin = new StyleSheetAttribute(Style.margin)
  lazy val marginBottom = new StyleSheetAttribute(Style.marginBottom)
  lazy val marginLeft = new StyleSheetAttribute(Style.marginLeft)
  lazy val marginRight = new StyleSheetAttribute(Style.marginRight)
  lazy val marginTop = new StyleSheetAttribute(Style.marginTop)
  def marginAll(length: Length) = {
    marginTop := length
    marginBottom := length
    marginLeft := length
    marginRight := length
  }
  lazy val mark = new StyleSheetAttribute(Style.mark)
  lazy val markAfter = new StyleSheetAttribute(Style.markAfter)
  lazy val markBefore = new StyleSheetAttribute(Style.markBefore)
  lazy val marks = new StyleSheetAttribute(Style.marks)
  lazy val marqueeDirection = new StyleSheetAttribute(Style.marqueeDirection)
  lazy val marqueePlayCount = new StyleSheetAttribute(Style.marqueePlayCount)
  lazy val marqueeSpeed = new StyleSheetAttribute(Style.marqueeSpeed)
  lazy val marqueeStyle = new StyleSheetAttribute(Style.marqueeStyle)
  lazy val maxHeight = new StyleSheetAttribute(Style.maxHeight)
  lazy val maxWidth = new StyleSheetAttribute(Style.maxWidth)
  lazy val minHeight = new StyleSheetAttribute(Style.minHeight)
  lazy val minWidth = new StyleSheetAttribute(Style.minWidth)
  lazy val moveTo = new StyleSheetAttribute(Style.moveTo)
  lazy val navDown = new StyleSheetAttribute(Style.navDown)
  lazy val navIndex = new StyleSheetAttribute(Style.navIndex)
  lazy val navLeft = new StyleSheetAttribute(Style.navLeft)
  lazy val navRight = new StyleSheetAttribute(Style.navRight)
  lazy val navUp = new StyleSheetAttribute(Style.navUp)
  lazy val opacity = new StyleSheetAttribute(Style.opacity)
  lazy val orphans = new StyleSheetAttribute(Style.orphans)
  lazy val outline = new StyleSheetAttribute(Style.outline)
  lazy val outlineColor = new StyleSheetAttribute(Style.outlineColor)
  lazy val outlineOffset = new StyleSheetAttribute(Style.outlineOffset)
  lazy val outlineStyle = new StyleSheetAttribute(Style.outlineStyle)
  lazy val outlineWidth = new StyleSheetAttribute(Style.outlineWidth)
  lazy val overflow = new StyleSheetAttribute(Style.overflow)
  lazy val overflowStyle = new StyleSheetAttribute(Style.overflowStyle)
  lazy val overflowX = new StyleSheetAttribute(Style.overflowX)
  lazy val overflowY = new StyleSheetAttribute(Style.overflowY)
  lazy val padding = new StyleSheetAttribute(Style.padding)
  lazy val paddingBottom = new StyleSheetAttribute(Style.paddingBottom)
  lazy val paddingLeft = new StyleSheetAttribute(Style.paddingLeft)
  lazy val paddingRight = new StyleSheetAttribute(Style.paddingRight)
  lazy val paddingTop = new StyleSheetAttribute(Style.paddingTop)
  def paddingAll(length: Length) = {
    paddingTop := length
    paddingBottom := length
    paddingLeft := length
    paddingRight := length
  }
  lazy val page = new StyleSheetAttribute(Style.page)
  lazy val pageBreakAfter = new StyleSheetAttribute(Style.pageBreakAfter)
  lazy val pageBreakBefore = new StyleSheetAttribute(Style.pageBreakBefore)
  lazy val pageBreakInside = new StyleSheetAttribute(Style.pageBreakInside)
  lazy val pagePolicy = new StyleSheetAttribute(Style.pagePolicy)
  lazy val perspective = new StyleSheetAttribute(Style.perspective)
  lazy val perspectiveOrigin = new StyleSheetAttribute(Style.perspectiveOrigin)
  lazy val phonemes = new StyleSheetAttribute(Style.phonemes)
  lazy val position = new StyleSheetAttribute(Style.position)
  lazy val punctuationTrim = new StyleSheetAttribute(Style.punctuationTrim)
  lazy val quotes = new StyleSheetAttribute(Style.quotes)
  lazy val renderingIntent = new StyleSheetAttribute(Style.renderingIntent)
  lazy val resize = new StyleSheetAttribute(Style.resize)
  lazy val rest = new StyleSheetAttribute(Style.rest)
  lazy val restAfter = new StyleSheetAttribute(Style.restAfter)
  lazy val restBefore = new StyleSheetAttribute(Style.restBefore)
  lazy val right = new StyleSheetAttribute(Style.right)
  lazy val rotation = new StyleSheetAttribute(Style.rotation)
  lazy val rotationPoint = new StyleSheetAttribute(Style.rotationPoint)
  lazy val rubyAlign = new StyleSheetAttribute(Style.rubyAlign)
  lazy val rubyOverhang = new StyleSheetAttribute(Style.rubyOverhang)
  lazy val rubyPosition = new StyleSheetAttribute(Style.rubyPosition)
  lazy val rubySpan = new StyleSheetAttribute(Style.rubySpan)
  lazy val size = new StyleSheetAttribute(Style.size)
  lazy val stringSet = new StyleSheetAttribute(Style.stringSet)
  lazy val tableLayout = new StyleSheetAttribute(Style.tableLayout)
  lazy val target = new StyleSheetAttribute(Style.target)
  lazy val targetName = new StyleSheetAttribute(Style.targetName)
  lazy val targetNew = new StyleSheetAttribute(Style.targetNew)
  lazy val targetPosition = new StyleSheetAttribute(Style.targetPosition)
  lazy val textAlign = new StyleSheetAttribute(Style.textAlign)
  lazy val textAlignLast = new StyleSheetAttribute(Style.textAlignLast)
  lazy val textDecoration = new StyleSheetAttribute(Style.textDecoration)
  lazy val textHeight = new StyleSheetAttribute(Style.textHeight)
  lazy val textIndent = new StyleSheetAttribute(Style.textIndent)
  lazy val textJustify = new StyleSheetAttribute(Style.textJustify)
  lazy val textOutline = new StyleSheetAttribute(Style.textOutline)
  lazy val textOverflow = new StyleSheetAttribute(Style.textOverflow)
  lazy val textShadow = new StyleSheetAttribute(Style.textShadow)
  lazy val textShadowColor = new TextShadowColor(this)
  lazy val textShadowOffsetX = new TextShadowOffsetX(this)
  lazy val textShadowOffsetY = new TextShadowOffsetY(this)
  lazy val textShadowBlurRadius = new TextShadowBlurRadius(this)
  lazy val textTransform = new StyleSheetAttribute(Style.textTransform)
  lazy val textWrap = new StyleSheetAttribute(Style.textWrap)
  lazy val top = new StyleSheetAttribute(Style.top)
  lazy val transform = new StyleSheetAttribute(Style.transform)
  lazy val transformOrigin = new StyleSheetAttribute(Style.transformOrigin)
  lazy val transformStyle = new StyleSheetAttribute(Style.transformStyle)
  lazy val transition = new StyleSheetAttribute(Style.transition)
  lazy val transitionDelay = new StyleSheetAttribute(Style.transitionDelay)
  lazy val transitionDuration = new StyleSheetAttribute(Style.transitionDuration)
  lazy val transitionProperty = new StyleSheetAttribute(Style.transitionProperty)
  lazy val transitionTimingFunction = new StyleSheetAttribute(Style.transitionTimingFunction)
  lazy val unicodeBidi = new StyleSheetAttribute(Style.unicodeBidi)
  lazy val verticalAlign = new StyleSheetAttribute(Style.verticalAlign)
  lazy val visibility = new StyleSheetAttribute(Style.visibility)
  lazy val voiceBalance = new StyleSheetAttribute(Style.voiceBalance)
  lazy val voiceDuration = new StyleSheetAttribute(Style.voiceDuration)
  lazy val voicePitch = new StyleSheetAttribute(Style.voicePitch)
  lazy val voicePitchRange = new StyleSheetAttribute(Style.voicePitchRange)
  lazy val voiceRate = new StyleSheetAttribute(Style.voiceRate)
  lazy val voiceStress = new StyleSheetAttribute(Style.voiceStress)
  lazy val voiceVolume = new StyleSheetAttribute(Style.voiceVolume)
  lazy val whiteSpace = new StyleSheetAttribute(Style.whiteSpace)
  lazy val widows = new StyleSheetAttribute(Style.widows)
  lazy val width = new StyleSheetAttribute(Style.width)
  lazy val wordBreak = new StyleSheetAttribute(Style.wordBreak)
  lazy val wordSpacing = new StyleSheetAttribute(Style.wordSpacing)
  lazy val wordWrap = new StyleSheetAttribute(Style.wordWrap)
  lazy val zIndex = new StyleSheetAttribute(Style.zIndex)

  /**
   * Gets or creates the attribute based on the Style. This can be used to create explicit CSS properties.
   *
   * @param style the style to lookup the attribute from
   * @tparam T the type of value
   * @return StyleSheetAttribute[T]
   */
  def apply[T](style: Style[T], default: Option[T]) = synchronized {
    attributes.get(style.cssName) match {
      case Some(ssa) => ssa.asInstanceOf[StyleSheetAttribute[T]]
      case None => {
        val ssa = new StyleSheetAttribute[T](style)(this, style.manifest)
        default match {
          case Some(v) => ssa := v
          case None => // No default
        }
        ssa
      }
    }
  }

  def apply(css: String) = try {
    css.split(";").map(s => s.trim()).collect {
      case s if s.nonEmpty => {
        val split = s.indexOf(':')
        val key = s.substring(0, split).trim
        val value = s.substring(split + 1).trim
        key -> value
      }
    }.foreach {
      case (key, value) => Style.byCSSName(key) match {
        case Some(style) => update[AnyRef](style.asInstanceOf[Style[AnyRef]], style.persistence.fromString(value, key, style.manifest.runtimeClass).asInstanceOf[AnyRef])
        case None => warn("Unable to find style by css name '%s' with value '%s'.".format(key, value))
      }
    }
  } catch {
    case t: Throwable => throw new RuntimeException(s"Failed to parse CSS: [$css].", t)
  }

  def apply(ss: StyleSheet, append: Boolean = true) = {
    if (!append) {      // Clear unspecified attributes
      attributes.foreach {
        case (key, ssa) => if (!ss.attributes.contains(key)) {
          ssa.asInstanceOf[StyleSheetAttribute[AnyRef]] := null
        }
      }
    }
    ss.attributes.values.foreach {              // Apply the attributes from ss to this stylesheet
      case ssa => update(ssa.style, ssa())
    }
  }

  def clearStyle() = {
    attributes.foreach {
      case (key, ssa) => ssa.asInstanceOf[StyleSheetAttribute[AnyRef]] := null
    }
  }

  def isEmpty = attributes.values.find(ssa => ssa.shouldRender).isEmpty
  def nonEmpty = !isEmpty

  override def toString = if (selector != null) {
    val b = new StringBuilder
    b.append("\n      ")
    b.append(selectorString)
    b.append(" {\n")
    val css = attributes.values.collect {
      case ssa if ssa.shouldRender => s"        ${ssa.style.cssName}: ${ssa.valueString}"
    }.mkString(";\n")
    b.append(css)
    b.append(";\n      }\n    ")
    b.toString()
  } else {
    attributes.values.collect {
      case ssa if ssa.shouldRender => s"${ssa.style.cssName}: ${ssa.valueString}"
    }.mkString("; ")
  }

  def update[T](style: Style[T], value: T) = {
    val a = getAttribute(style.name) match {
      case Some(ssa) => ssa.asInstanceOf[StyleSheetAttribute[T]]
      case None => {
        val field = fieldsMap(style.name)
        field[StyleSheetAttribute[T]](this, computeIfLazy = true)
      }
    }
    a.value = value
  }
}

object StyleSheet {
  private val StyleRegex = """(?s)([\S ]+) [{](.+?)[}]""".r
  lazy val fieldsMap = classOf[StyleSheet].fields.filter(f => f.returnType.hasType(classOf[StyleSheetAttribute[_]])).map(f => f.name -> f).toMap

  def styles2Selectors(head: Head, root: HTMLTag) = {
    root.byTag[HTMLTag].foreach {
      case e => if (e.isStyleDefined) {
        val styleSheet = head.selector(Selector.id(e.identity))
        styleSheet(e.style, append = true)
        e.style.clearStyle()
      }
    }
  }

  /**
   * Parses the supplied multiline CSS into a list of StyleSheet instances.
   *
   * @param parent the parent to assign to the generated StyleSheets.
   * @param css the css to parse
   * @return List[StyleSheet]
   */
  def parse(parent: Listenable, css: String) = StyleRegex.findAllIn(css).matchData.toList.map {
    case m => {
      val ss = new StyleSheet(parent, Selector(m.group(1).trim))
      ss(m.group(2))
      ss
    }
  }
}