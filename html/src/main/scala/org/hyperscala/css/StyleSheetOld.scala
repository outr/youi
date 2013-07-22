package org.hyperscala.css

import attributes._
import org.powerscala.Color
import org.hyperscala.persistence.ValuePersistence
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class StyleSheetOld private(val map: Map[Style[_], AnyRef] = Map.empty) {
  def alignmentAdjust = get[String](Style.alignmentAdjust)
  def alignmentAdjust(v: String) = set(Style.alignmentAdjust, v)

  def alignmentBaseline = get[String](Style.alignmentBaseline)
  def alignmentBaseline(v: String) = set(Style.alignmentBaseline, v)

  def animation = get[String](Style.animation)
  def animation(v: String) = set(Style.animation, v)

  def animationDelay = get[String](Style.animationDelay)
  def animationDelay(v: String) = set(Style.animationDelay, v)

  def animationDirection = get[String](Style.animationDirection)
  def animationDirection(v: String) = set(Style.animationDirection, v)

  def animationDuration = get[String](Style.animationDuration)
  def animationDuration(v: String) = set(Style.animationDuration, v)

  def animationIterationCount = get[String](Style.animationIterationCount)
  def animationIterationCount(v: String) = set(Style.animationIterationCount, v)

  def animationName = get[String](Style.animationName)
  def animationName(v: String) = set(Style.animationName, v)

  def animationPlayState = get[String](Style.animationPlayState)
  def animationPlayState(v: String) = set(Style.animationPlayState, v)

  def animationTimingFunction = get[String](Style.animationTimingFunction)
  def animationTimingFunction(v: String) = set(Style.animationTimingFunction, v)

  def appearance = get[String](Style.appearance)
  def appearance(v: String) = set(Style.appearance, v)

  def backfaceVisibility = get[String](Style.backfaceVisibility)
  def backfaceVisibility(v: String) = set(Style.backfaceVisibility, v)

  def background = get[String](Style.background)
  def background(v: String) = set(Style.background, v)

  def backgroundAttachment = get[String](Style.backgroundAttachment)
  def backgroundAttachment(v: String) = set(Style.backgroundAttachment, v)

  def backgroundClip = get[String](Style.backgroundClip)
  def backgroundClip(v: String) = set(Style.backgroundClip, v)

  def backgroundColor = get[Color](Style.backgroundColor)
  def backgroundColor(v: Color) = set(Style.backgroundColor, v)

  def backgroundImage = get[Resource](Style.backgroundImage)
  def backgroundImage(v: Resource) = set(Style.backgroundImage, v)

  def backgroundOrigin = get[String](Style.backgroundOrigin)
  def backgroundOrigin(v: String) = set(Style.backgroundOrigin, v)

  def backgroundPosition = get[String](Style.backgroundPosition)
  def backgroundPosition(v: String) = set(Style.backgroundPosition, v)

  def backgroundRepeat = get[String](Style.backgroundRepeat)
  def backgroundRepeat(v: String) = set(Style.backgroundRepeat, v)

  def backgroundSize = get[String](Style.backgroundSize)
  def backgroundSize(v: String) = set(Style.backgroundSize, v)

  def baselineShift = get[String](Style.baselineShift)
  def baselineShift(v: String) = set(Style.baselineShift, v)

  def bookmarkLabel = get[String](Style.bookmarkLabel)
  def bookmarkLabel(v: String) = set(Style.bookmarkLabel, v)

  def bookmarkLevel = get[String](Style.bookmarkLevel)
  def bookmarkLevel(v: String) = set(Style.bookmarkLevel, v)

  def bookmarkTarget = get[String](Style.bookmarkTarget)
  def bookmarkTarget(v: String) = set(Style.bookmarkTarget, v)

  def border = get[String](Style.border)
  def border(v: String) = set(Style.border, v)

  def borderBottom = get[String](Style.borderBottom)
  def borderBottom(v: String) = set(Style.borderBottom, v)

  def borderBottomColor = get[Color](Style.borderBottomColor)
  def borderBottomColor(v: Color) = set(Style.borderBottomColor, v)

  def borderBottomLeftRadius = get[Length](Style.borderBottomLeftRadius)
  def borderBottomLeftRadius(v: Length) = set(Style.borderBottomLeftRadius, v)

  def borderBottomRightRadius = get[Length](Style.borderBottomRightRadius)
  def borderBottomRightRadius(v: Length) = set(Style.borderBottomRightRadius, v)

  def borderBottomStyle = get[String](Style.borderBottomStyle)
  def borderBottomStyle(v: String) = set(Style.borderBottomStyle, v)

  def borderBottomWidth = get[Length](Style.borderBottomWidth)
  def borderBottomWidth(v: Length) = set(Style.borderBottomWidth, v)

  def borderCollapse = get[String](Style.borderCollapse)
  def borderCollapse(v: String) = set(Style.borderCollapse, v)

  def borderColor = get[Color](Style.borderColor)
  def borderColor(v: Color) = set(Style.borderColor, v)

  def borderImage = get[String](Style.borderImage)
  def borderImage(v: String) = set(Style.borderImage, v)

  def borderImageOutset = get[String](Style.borderImageOutset)
  def borderImageOutset(v: String) = set(Style.borderImageOutset, v)

  def borderImageRepeat = get[String](Style.borderImageRepeat)
  def borderImageRepeat(v: String) = set(Style.borderImageRepeat, v)

  def borderImageSlice = get[String](Style.borderImageSlice)
  def borderImageSlice(v: String) = set(Style.borderImageSlice, v)

  def borderImageSource = get[String](Style.borderImageSource)
  def borderImageSource(v: String) = set(Style.borderImageSource, v)

  def borderImageWidth = get[Length](Style.borderImageWidth)
  def borderImageWidth(v: Length) = set(Style.borderImageWidth, v)

  def borderLeft = get[String](Style.borderLeft)
  def borderLeft(v: String) = set(Style.borderLeft, v)

  def borderLeftColor = get[Color](Style.borderLeftColor)
  def borderLeftColor(v: Color) = set(Style.borderLeftColor, v)

  def borderLeftStyle = get[String](Style.borderLeftStyle)
  def borderLeftStyle(v: String) = set(Style.borderLeftStyle, v)

  def borderLeftWidth = get[Length](Style.borderLeftWidth)
  def borderLeftWidth(v: Length) = set(Style.borderLeftWidth, v)

  def borderRadius = get[Length](Style.borderRadius)
  def borderRadius(v: Length) = set(Style.borderRadius, v)

  def borderRight = get[String](Style.borderRight)
  def borderRight(v: String) = set(Style.borderRight, v)

  def borderRightColor = get[Color](Style.borderRightColor)
  def borderRightColor(v: Color) = set(Style.borderRightColor, v)

  def borderRightStyle = get[String](Style.borderRightStyle)
  def borderRightStyle(v: String) = set(Style.borderRightStyle, v)

  def borderRightWidth = get[Length](Style.borderRightWidth)
  def borderRightWidth(v: Length) = set(Style.borderRightWidth, v)

  def borderSpacing = get[String](Style.borderSpacing)
  def borderSpacing(v: String) = set(Style.borderSpacing, v)

  def borderStyle = get[String](Style.borderStyle)
  def borderStyle(v: String) = set(Style.borderStyle, v)

  def borderTop = get[String](Style.borderTop)
  def borderTop(v: String) = set(Style.borderTop, v)

  def borderTopColor = get[Color](Style.borderTopColor)
  def borderTopColor(v: Color) = set(Style.borderTopColor, v)

  def borderTopLeftRadius = get[Length](Style.borderTopLeftRadius)
  def borderTopLeftRadius(v: Length) = set(Style.borderTopLeftRadius, v)

  def borderTopRightRadius = get[Length](Style.borderTopRightRadius)
  def borderTopRightRadius(v: Length) = set(Style.borderTopRightRadius, v)

  def borderTopStyle = get[String](Style.borderTopStyle)
  def borderTopStyle(v: String) = set(Style.borderTopStyle, v)

  def borderTopWidth = get[Length](Style.borderTopWidth)
  def borderTopWidth(v: Length) = set(Style.borderTopWidth, v)

  def borderWidth = get[Length](Style.borderWidth)
  def borderWidth(v: Length) = set(Style.borderWidth, v)

  def bottom = get[String](Style.bottom)
  def bottom(v: String) = set(Style.bottom, v)

  def boxAlign = get[String](Style.boxAlign)
  def boxAlign(v: String) = set(Style.boxAlign, v)

  def boxDecorationBreak = get[String](Style.boxDecorationBreak)
  def boxDecorationBreak(v: String) = set(Style.boxDecorationBreak, v)

  def boxDirection = get[String](Style.boxDirection)
  def boxDirection(v: String) = set(Style.boxDirection, v)

  def boxFlex = get[String](Style.boxFlex)
  def boxFlex(v: String) = set(Style.boxFlex, v)

  def boxFlexGroup = get[String](Style.boxFlexGroup)
  def boxFlexGroup(v: String) = set(Style.boxFlexGroup, v)

  def boxLines = get[String](Style.boxLines)
  def boxLines(v: String) = set(Style.boxLines, v)

  def boxOrdinalGroup = get[String](Style.boxOrdinalGroup)
  def boxOrdinalGroup(v: String) = set(Style.boxOrdinalGroup, v)

  def boxOrient = get[String](Style.boxOrient)
  def boxOrient(v: String) = set(Style.boxOrient, v)

  def boxPack = get[String](Style.boxPack)
  def boxPack(v: String) = set(Style.boxPack, v)

  def boxShadow = get[String](Style.boxShadow)
  def boxShadow(v: String) = set(Style.boxShadow, v)

  def boxSizing = get[String](Style.boxSizing)
  def boxSizing(v: String) = set(Style.boxSizing, v)

  def captionSide = get[String](Style.captionSide)
  def captionSide(v: String) = set(Style.captionSide, v)

  def clear = get[Clear](Style.clear)
  def clear(v: Clear) = set(Style.clear, v)

  def clip = get[String](Style.clip)
  def clip(v: String) = set(Style.clip, v)

  def color = get[Color](Style.color)
  def color(v: Color) = set(Style.color, v)

  def colorProfile = get[String](Style.colorProfile)
  def colorProfile(v: String) = set(Style.colorProfile, v)

  def columnCount = get[String](Style.columnCount)
  def columnCount(v: String) = set(Style.columnCount, v)

  def columnFill = get[String](Style.columnFill)
  def columnFill(v: String) = set(Style.columnFill, v)

  def columnGap = get[String](Style.columnGap)
  def columnGap(v: String) = set(Style.columnGap, v)

  def columnRule = get[String](Style.columnRule)
  def columnRule(v: String) = set(Style.columnRule, v)

  def columnRuleColor = get[Color](Style.columnRuleColor)
  def columnRuleColor(v: Color) = set(Style.columnRuleColor, v)

  def columnRuleStyle = get[String](Style.columnRuleStyle)
  def columnRuleStyle(v: String) = set(Style.columnRuleStyle, v)

  def columnRuleWidth = get[Length](Style.columnRuleWidth)
  def columnRuleWidth(v: Length) = set(Style.columnRuleWidth, v)

  def columnSpan = get[String](Style.columnSpan)
  def columnSpan(v: String) = set(Style.columnSpan, v)

  def columnWidth = get[Length](Style.columnWidth)
  def columnWidth(v: Length) = set(Style.columnWidth, v)

  def columns = get[String](Style.columns)
  def columns(v: String) = set(Style.columns, v)

  def content = get[String](Style.content)
  def content(v: String) = set(Style.content, v)

  def counterIncrement = get[String](Style.counterIncrement)
  def counterIncrement(v: String) = set(Style.counterIncrement, v)

  def counterReset = get[String](Style.counterReset)
  def counterReset(v: String) = set(Style.counterReset, v)

  def crop = get[String](Style.crop)
  def crop(v: String) = set(Style.crop, v)

  def cursor = get[String](Style.cursor)
  def cursor(v: String) = set(Style.cursor, v)

  def direction = get[String](Style.direction)
  def direction(v: String) = set(Style.direction, v)

  def display = get[Display](Style.display)
  def display(v: Display) = set(Style.display, v)

  def dominantBaseline = get[String](Style.dominantBaseline)
  def dominantBaseline(v: String) = set(Style.dominantBaseline, v)

  def dropInitialAfterAdjust = get[String](Style.dropInitialAfterAdjust)
  def dropInitialAfterAdjust(v: String) = set(Style.dropInitialAfterAdjust, v)

  def dropInitialAfterAlign = get[String](Style.dropInitialAfterAlign)
  def dropInitialAfterAlign(v: String) = set(Style.dropInitialAfterAlign, v)

  def dropInitialBeforeAdjust = get[String](Style.dropInitialBeforeAdjust)
  def dropInitialBeforeAdjust(v: String) = set(Style.dropInitialBeforeAdjust, v)

  def dropInitialBeforeAlign = get[String](Style.dropInitialBeforeAlign)
  def dropInitialBeforeAlign(v: String) = set(Style.dropInitialBeforeAlign, v)

  def dropInitialSize = get[String](Style.dropInitialSize)
  def dropInitialSize(v: String) = set(Style.dropInitialSize, v)

  def dropInitialValue = get[String](Style.dropInitialValue)
  def dropInitialValue(v: String) = set(Style.dropInitialValue, v)

  def emptyCells = get[String](Style.emptyCells)
  def emptyCells(v: String) = set(Style.emptyCells, v)

  def fit = get[String](Style.fit)
  def fit(v: String) = set(Style.fit, v)

  def fitPosition = get[String](Style.fitPosition)
  def fitPosition(v: String) = set(Style.fitPosition, v)

  def float = get[Float](Style.float)
  def float(v: Float) = set(Style.float, v)

  def floatOffset = get[String](Style.floatOffset)
  def floatOffset(v: String) = set(Style.floatOffset, v)

  def font = get[String](Style.font)
  def font(v: String) = set(Style.font, v)

  def fontFace = get[String](Style.fontFace)
  def fontFace(v: String) = set(Style.fontFace, v)

  def fontFamily = get[String](Style.fontFamily)
  def fontFamily(v: String) = set(Style.fontFamily, v)

  def fontSize = get[FontSize](Style.fontSize)
  def fontSize(v: FontSize) = set(Style.fontSize, v)

  def fontSizeAdjust = get[String](Style.fontSizeAdjust)
  def fontSizeAdjust(v: String) = set(Style.fontSizeAdjust, v)

  def fontStretch = get[String](Style.fontStretch)
  def fontStretch(v: String) = set(Style.fontStretch, v)

  def fontStyle = get[String](Style.fontStyle)
  def fontStyle(v: String) = set(Style.fontStyle, v)

  def fontVariant = get[String](Style.fontVariant)
  def fontVariant(v: String) = set(Style.fontVariant, v)

  def fontWeight = get[String](Style.fontWeight)
  def fontWeight(v: String) = set(Style.fontWeight, v)

  def gridColumns = get[String](Style.gridColumns)
  def gridColumns(v: String) = set(Style.gridColumns, v)

  def gridRows = get[String](Style.gridRows)
  def gridRows(v: String) = set(Style.gridRows, v)

  def hangingPunctuation = get[String](Style.hangingPunctuation)
  def hangingPunctuation(v: String) = set(Style.hangingPunctuation, v)

  def height = get[Length](Style.height)
  def height(v: Length) = set(Style.height, v)

  def hyphenateAfter = get[String](Style.hyphenateAfter)
  def hyphenateAfter(v: String) = set(Style.hyphenateAfter, v)

  def hyphenateBefore = get[String](Style.hyphenateBefore)
  def hyphenateBefore(v: String) = set(Style.hyphenateBefore, v)

  def hyphenateCharacter = get[String](Style.hyphenateCharacter)
  def hyphenateCharacter(v: String) = set(Style.hyphenateCharacter, v)

  def hyphenateLines = get[String](Style.hyphenateLines)
  def hyphenateLines(v: String) = set(Style.hyphenateLines, v)

  def hyphenateResource = get[String](Style.hyphenateResource)
  def hyphenateResource(v: String) = set(Style.hyphenateResource, v)

  def hyphens = get[String](Style.hyphens)
  def hyphens(v: String) = set(Style.hyphens, v)

  def icon = get[String](Style.icon)
  def icon(v: String) = set(Style.icon, v)

  def imageOrientation = get[String](Style.imageOrientation)
  def imageOrientation(v: String) = set(Style.imageOrientation, v)

  def imageResolution = get[String](Style.imageResolution)
  def imageResolution(v: String) = set(Style.imageResolution, v)

  def inlineBoxAlign = get[String](Style.inlineBoxAlign)
  def inlineBoxAlign(v: String) = set(Style.inlineBoxAlign, v)

  def left = get[Length](Style.left)
  def left(v: Length) = set(Style.left, v)

  def letterSpacing = get[String](Style.letterSpacing)
  def letterSpacing(v: String) = set(Style.letterSpacing, v)

  def lineHeight = get[String](Style.lineHeight)
  def lineHeight(v: String) = set(Style.lineHeight, v)

  def lineStacking = get[String](Style.lineStacking)
  def lineStacking(v: String) = set(Style.lineStacking, v)

  def lineStackingRuby = get[String](Style.lineStackingRuby)
  def lineStackingRuby(v: String) = set(Style.lineStackingRuby, v)

  def lineStackingShift = get[String](Style.lineStackingShift)
  def lineStackingShift(v: String) = set(Style.lineStackingShift, v)

  def lineStackingStrategy = get[String](Style.lineStackingStrategy)
  def lineStackingStrategy(v: String) = set(Style.lineStackingStrategy, v)

  def listStyle = get[String](Style.listStyle)
  def listStyle(v: String) = set(Style.listStyle, v)

  def listStyleImage = get[String](Style.listStyleImage)
  def listStyleImage(v: String) = set(Style.listStyleImage, v)

  def listStylePosition = get[String](Style.listStylePosition)
  def listStylePosition(v: String) = set(Style.listStylePosition, v)

  def listStyleType = get[String](Style.listStyleType)
  def listStyleType(v: String) = set(Style.listStyleType, v)

  def margin = get[String](Style.margin)
  def margin(v: String) = set(Style.margin, v)

  def marginBottom = get[Length](Style.marginBottom)
  def marginBottom(v: Length) = set(Style.marginBottom, v)

  def marginLeft = get[Length](Style.marginLeft)
  def marginLeft(v: Length) = set(Style.marginLeft, v)

  def marginRight = get[Length](Style.marginRight)
  def marginRight(v: Length) = set(Style.marginRight, v)

  def marginTop = get[Length](Style.marginTop)
  def marginTop(v: Length) = set(Style.marginTop, v)

  def mark = get[String](Style.mark)
  def mark(v: String) = set(Style.mark, v)

  def markAfter = get[String](Style.markAfter)
  def markAfter(v: String) = set(Style.markAfter, v)

  def markBefore = get[String](Style.markBefore)
  def markBefore(v: String) = set(Style.markBefore, v)

  def marks = get[String](Style.marks)
  def marks(v: String) = set(Style.marks, v)

  def marqueeDirection = get[String](Style.marqueeDirection)
  def marqueeDirection(v: String) = set(Style.marqueeDirection, v)

  def marqueePlayCount = get[String](Style.marqueePlayCount)
  def marqueePlayCount(v: String) = set(Style.marqueePlayCount, v)

  def marqueeSpeed = get[String](Style.marqueeSpeed)
  def marqueeSpeed(v: String) = set(Style.marqueeSpeed, v)

  def marqueeStyle = get[String](Style.marqueeStyle)
  def marqueeStyle(v: String) = set(Style.marqueeStyle, v)

  def maxHeight = get[Length](Style.maxHeight)
  def maxHeight(v: Length) = set(Style.maxHeight, v)

  def maxWidth = get[Length](Style.maxWidth)
  def maxWidth(v: Length) = set(Style.maxWidth, v)

  def minHeight = get[Length](Style.minHeight)
  def minHeight(v: Length) = set(Style.minHeight, v)

  def minWidth = get[Length](Style.minWidth)
  def minWidth(v: Length) = set(Style.minWidth, v)

  def moveTo = get[String](Style.moveTo)
  def moveTo(v: String) = set(Style.moveTo, v)

  def navDown = get[String](Style.navDown)
  def navDown(v: String) = set(Style.navDown, v)

  def navIndex = get[String](Style.navIndex)
  def navIndex(v: String) = set(Style.navIndex, v)

  def navLeft = get[String](Style.navLeft)
  def navLeft(v: String) = set(Style.navLeft, v)

  def navRight = get[String](Style.navRight)
  def navRight(v: String) = set(Style.navRight, v)

  def navUp = get[String](Style.navUp)
  def navUp(v: String) = set(Style.navUp, v)

  def opacity = get[Opacity](Style.opacity)
  def opacity(v: Opacity) = set(Style.opacity, v)

  def orphans = get[String](Style.orphans)
  def orphans(v: String) = set(Style.orphans, v)

  def outline = get[String](Style.outline)
  def outline(v: String) = set(Style.outline, v)

  def outlineColor = get[Color](Style.outlineColor)
  def outlineColor(v: Color) = set(Style.outlineColor, v)

  def outlineOffset = get[String](Style.outlineOffset)
  def outlineOffset(v: String) = set(Style.outlineOffset, v)

  def outlineStyle = get[String](Style.outlineStyle)
  def outlineStyle(v: String) = set(Style.outlineStyle, v)

  def outlineWidth = get[Length](Style.outlineWidth)
  def outlineWidth(v: Length) = set(Style.outlineWidth, v)

  def overflow = get[Overflow](Style.overflow)
  def overflow(v: Overflow) = set(Style.overflow, v)

  def overflowStyle = get[String](Style.overflowStyle)
  def overflowStyle(v: String) = set(Style.overflowStyle, v)

  def overflowX = get[Overflow](Style.overflowX)
  def overflowX(v: Overflow) = set(Style.overflowX, v)

  def overflowY = get[Overflow](Style.overflowY)
  def overflowY(v: Overflow) = set(Style.overflowY, v)

  def padding = get[String](Style.padding)
  def padding(v: String) = set(Style.padding, v)

  def paddingBottom = get[Length](Style.paddingBottom)
  def paddingBottom(v: Length) = set(Style.paddingBottom, v)

  def paddingLeft = get[Length](Style.paddingLeft)
  def paddingLeft(v: Length) = set(Style.paddingLeft, v)

  def paddingRight = get[Length](Style.paddingRight)
  def paddingRight(v: Length) = set(Style.paddingRight, v)

  def paddingTop = get[Length](Style.paddingTop)
  def paddingTop(v: Length) = set(Style.paddingTop, v)

  def page = get[String](Style.page)
  def page(v: String) = set(Style.page, v)

  def pageBreakAfter = get[String](Style.pageBreakAfter)
  def pageBreakAfter(v: String) = set(Style.pageBreakAfter, v)

  def pageBreakBefore = get[String](Style.pageBreakBefore)
  def pageBreakBefore(v: String) = set(Style.pageBreakBefore, v)

  def pageBreakInside = get[String](Style.pageBreakInside)
  def pageBreakInside(v: String) = set(Style.pageBreakInside, v)

  def pagePolicy = get[String](Style.pagePolicy)
  def pagePolicy(v: String) = set(Style.pagePolicy, v)

  def perspective = get[String](Style.perspective)
  def perspective(v: String) = set(Style.perspective, v)

  def perspectiveOrigin = get[String](Style.perspectiveOrigin)
  def perspectiveOrigin(v: String) = set(Style.perspectiveOrigin, v)

  def phonemes = get[String](Style.phonemes)
  def phonemes(v: String) = set(Style.phonemes, v)

  def position = get[Position](Style.position)
  def position(v: Position) = set(Style.position, v)

  def punctuationTrim = get[String](Style.punctuationTrim)
  def punctuationTrim(v: String) = set(Style.punctuationTrim, v)

  def quotes = get[String](Style.quotes)
  def quotes(v: String) = set(Style.quotes, v)

  def renderingIntent = get[String](Style.renderingIntent)
  def renderingIntent(v: String) = set(Style.renderingIntent, v)

  def resize = get[String](Style.resize)
  def resize(v: String) = set(Style.resize, v)

  def rest = get[String](Style.rest)
  def rest(v: String) = set(Style.rest, v)

  def restAfter = get[String](Style.restAfter)
  def restAfter(v: String) = set(Style.restAfter, v)

  def restBefore = get[String](Style.restBefore)
  def restBefore(v: String) = set(Style.restBefore, v)

  def right = get[String](Style.right)
  def right(v: String) = set(Style.right, v)

  def rotation = get[String](Style.rotation)
  def rotation(v: String) = set(Style.rotation, v)

  def rotationPoint = get[String](Style.rotationPoint)
  def rotationPoint(v: String) = set(Style.rotationPoint, v)

  def rubyAlign = get[String](Style.rubyAlign)
  def rubyAlign(v: String) = set(Style.rubyAlign, v)

  def rubyOverhang = get[String](Style.rubyOverhang)
  def rubyOverhang(v: String) = set(Style.rubyOverhang, v)

  def rubyPosition = get[String](Style.rubyPosition)
  def rubyPosition(v: String) = set(Style.rubyPosition, v)

  def rubySpan = get[String](Style.rubySpan)
  def rubySpan(v: String) = set(Style.rubySpan, v)

  def size = get[String](Style.size)
  def size(v: String) = set(Style.size, v)

  def stringSet = get[String](Style.stringSet)
  def stringset(v: String) = set(Style.stringSet, v)

  def tableLayout = get[String](Style.tableLayout)
  def tableLayout(v: String) = set(Style.tableLayout, v)

  def target = get[String](Style.target)
  def target(v: String) = set(Style.target, v)

  def targetName = get[String](Style.targetName)
  def targetName(v: String) = set(Style.targetName, v)

  def targetPosition = get[String](Style.targetPosition)
  def targetPosition(v: String) = set(Style.targetPosition, v)

  def targetNew = get[String](Style.targetNew)
  def targetNew(v: String) = set(Style.targetNew, v)

  def textAlign = get[Alignment](Style.textAlign)
  def textAlign(v: Alignment) = set(Style.textAlign, v)

  def textAlignLast = get[Alignment](Style.textAlignLast)
  def textAlignLast(v: Alignment) = set(Style.textAlignLast, v)

  def textDecoration = get[String](Style.textDecoration)
  def textDecoration(v: String) = set(Style.textDecoration, v)

  def textHeight = get[String](Style.textHeight)
  def textHeight(v: String) = set(Style.textHeight, v)

  def textIndent = get[String](Style.textIndent)
  def textIndent(v: String) = set(Style.textIndent, v)

  def textJustify = get[String](Style.textJustify)
  def textJustify(v: String) = set(Style.textJustify, v)

  def textOutline = get[String](Style.textOutline)
  def textOutline(v: String) = set(Style.textOutline, v)

  def textOverflow = get[String](Style.textOverflow)
  def textOverflow(v: String) = set(Style.textOverflow, v)

  def textShadow = get[String](Style.textShadow)
  def textShadow(v: String) = set(Style.textShadow, v)

  def textTransform = get[String](Style.textTransform)
  def textTransform(v: String) = set(Style.textTransform, v)

  def textWrap = get[String](Style.textWrap)
  def textWrap(v: String) = set(Style.textWrap, v)

  def top = get[Length](Style.top)
  def top(v: Length) = set(Style.top, v)

  def transform = get[String](Style.transform)
  def transform(v: String) = set(Style.transform, v)

  def transformOrigin = get[String](Style.transformOrigin)
  def transformOrigin(v: String) = set(Style.transformOrigin, v)

  def transformStyle = get[String](Style.transformStyle)
  def transformStyle(v: String) = set(Style.transformStyle, v)

  def transition = get[String](Style.transition)
  def transition(v: String) = set(Style.transition, v)

  def transitionDelay = get[String](Style.transitionDelay)
  def transitionDelay(v: String) = set(Style.transitionDelay, v)

  def transitionDuration = get[String](Style.transitionDuration)
  def transitionDuration(v: String) = set(Style.transitionDuration, v)

  def transitionProperty = get[String](Style.transitionProperty)
  def transitionProperty(v: String) = set(Style.transitionProperty, v)

  def transitionTimingFunction = get[String](Style.transitionTimingFunction)
  def transitionTimingFunction(v: String) = set(Style.transitionTimingFunction, v)

  def unicodeBidi = get[String](Style.unicodeBidi)
  def unicodeBidi(v: String) = set(Style.unicodeBidi, v)

  def verticalAlign = get[Length](Style.verticalAlign)
  def verticalAlign(v: Length) = set(Style.verticalAlign, v)

  def visibility = get[String](Style.visibility)
  def visibility(v: String) = set(Style.visibility, v)

  def voiceBalance = get[String](Style.voiceBalance)
  def voiceBalance(v: String) = set(Style.voiceBalance, v)

  def voiceDuration = get[String](Style.voiceDuration)
  def voiceDuration(v: String) = set(Style.voiceDuration, v)

  def voicePitch = get[String](Style.voicePitch)
  def voicePitch(v: String) = set(Style.voicePitch, v)

  def voicePitchRange = get[String](Style.voicePitchRange)
  def voicePitchRange(v: String) = set(Style.voicePitchRange, v)

  def voiceRate = get[String](Style.voiceRate)
  def voiceRate(v: String) = set(Style.voiceRate, v)

  def voiceStress = get[String](Style.voiceStress)
  def voiceStress(v: String) = set(Style.voiceStress, v)

  def voiceVolume = get[String](Style.voiceVolume)
  def voiceVolume(v: String) = set(Style.voiceVolume, v)

  def whiteSpace = get[WhiteSpace](Style.whiteSpace)
  def whiteSpace(v: WhiteSpace) = set(Style.whiteSpace, v)

  def widows = get[String](Style.widows)
  def widows(v: String) = set(Style.widows, v)

  def width = get[Length](Style.width)
  def width(v: Length) = set(Style.width, v)

  def wordBreak = get[String](Style.wordBreak)
  def wordBreak(v: String) = set(Style.wordBreak, v)

  def wordSpacing = get[String](Style.wordSpacing)
  def wordSpacing(v: String) = set(Style.wordSpacing, v)

  def wordWrap = get[String](Style.wordWrap)
  def wordWrap(v: String) = set(Style.wordWrap, v)

  def zIndex = get[String](Style.zIndex)
  def zIndex(v: String) = set(Style.zIndex, v)

  def ++(ss: StyleSheetOld) = new StyleSheetOld(map ++ ss.map)

  def apply[T](style: Style[T]) = map(style).asInstanceOf[T]
  def getOrElse[T](style: Style[T], default: => T) = map.getOrElse(style, default).asInstanceOf[T]
  def get[T](style: Style[T]) = map.get(style).map(s => s.asInstanceOf[T])
  def set[T](style: Style[T], value: T) = if (value == null) {
    new StyleSheetOld(map - style)
  } else {
    new StyleSheetOld(map + (style -> value.asInstanceOf[AnyRef]))
  }

  override def toString = {
    map.collect {
      case (style, value) => "%s: %s".format(style.cssName, style.persistence.asInstanceOf[ValuePersistence[AnyRef]].toString(value, style.cssName, value.getClass))
    }.mkString("; ")
  }
}

object StyleSheetOld extends ValuePersistence[StyleSheetOld] with Logging {
  def apply() = new StyleSheetOld()

  def apply(ss: StyleSheetOld) = new StyleSheetOld(ss.map)

  def apply(css: String): StyleSheetOld = {
    var ss = apply()
    css.split(";").map(s => s.split(":")).map(a => a(0).trim -> a(1).trim).foreach {
      case (key, value) => Style.byCSSName(key) match {
        case Some(style) => ss = ss.set[AnyRef](style.asInstanceOf[Style[AnyRef]], style.persistence.fromString(value, key, style.manifest.runtimeClass).asInstanceOf[AnyRef])
        case None => warn("Unable to find style by css name '%s' with value '%s'.".format(key, value))
      }
    }
    ss
  }

  def fromString(css: String, name: String, clazz: Class[_]) = apply(css)

  def toString(ss: StyleSheetOld, name: String, clazz: Class[_]) = ss.toString
}