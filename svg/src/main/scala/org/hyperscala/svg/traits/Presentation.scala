package org.hyperscala.svg.traits

import org.hyperscala.svg.SVGTag
import org.hyperscala.PropertyAttribute
import org.powerscala.Color
import org.hyperscala.svg.attributes._
import org.hyperscala.css.attributes.FontSize

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait Presentation {
  this: SVGTag =>

  val alignmentBaseline = PropertyAttribute[AlignmentBaseline]("alignment-baseline", AlignmentBaseline.Auto)
  val baselineShift = PropertyAttribute[String]("baseline-shift", null)
  val clip = PropertyAttribute[String]("clip", null)
  val clipPath = PropertyAttribute[String]("clip-path", null)
  val clipRule = PropertyAttribute[String]("clip-rule", null)
  val color = PropertyAttribute[Color]("color", null)
  val colorInterpolation = PropertyAttribute[String]("color-interpolation", null)
  val colorInterpolationFilters = PropertyAttribute[String]("color-interpolation-filters", null)
  val colorProfile = PropertyAttribute[String]("color-profile", null)
  val colorRendering = PropertyAttribute[String]("color-rendering", null)
  val cursor = PropertyAttribute[String]("cursor", null)
  val direction = PropertyAttribute[String]("direction", null)
  val display = PropertyAttribute[String]("display", null)
  val dominantBaseline = PropertyAttribute[DominantBaseline]("dominant-baseline", DominantBaseline.Auto)
  val enableBackground = PropertyAttribute[String]("enable-background", null)
  val fill = PropertyAttribute[Paint]("fill", null)
  val fillOpacity = PropertyAttribute[Double]("fill-opacity", 1.0)
  val fillRule = PropertyAttribute[String]("fill-rule", null)
  val filter = PropertyAttribute[String]("filter", null)
  val floodColor = PropertyAttribute[Color]("flood-color", null)
  val floodOpacity = PropertyAttribute[String]("flood-opacity", null)
  val fontFamily = PropertyAttribute[String]("font-family", null)
  val fontSize = PropertyAttribute[FontSize]("font-size", null)
  val fontSizeAdjust = PropertyAttribute[String]("font-size-adjust", null)
  val fontStretch = PropertyAttribute[FontStretch]("font-stretch", FontStretch.Normal)
  val fontStyle = PropertyAttribute[FontStyle]("font-style", FontStyle.Normal)
  val fontVariant = PropertyAttribute[FontVariant]("font-variant", FontVariant.Normal)
  val fontWeight = PropertyAttribute[FontWeight]("font-weight", FontWeight.Normal)
  val glyphOrientationHorizontal = PropertyAttribute[String]("glyph-orientation-horizontal", null)
  val glyphOrientationVertical = PropertyAttribute[String]("glyph-orientation-vertical", null)
  val imageRendering = PropertyAttribute[ImageRendering]("image-rendering", ImageRendering.Auto)
  val kerning = PropertyAttribute[String]("kerning", null)
  val letterSpacing = PropertyAttribute[String]("letter-spacing", null)
  val lightingColor = PropertyAttribute[Color]("lighting-color", null)
  val markerEnd = PropertyAttribute[String]("marker-end", null)
  val markerMid = PropertyAttribute[String]("marker-mid", null)
  val markerStart = PropertyAttribute[String]("marker-start", null)
  val mask = PropertyAttribute[String]("mask", null)
  val opacity = PropertyAttribute[Double]("opacity", 1.0)
  val overflow = PropertyAttribute[String]("overflow", null)
  val pointerEvents = PropertyAttribute[String]("pointer-events", null)
  val shapeRendering = PropertyAttribute[String]("shape-rendering", null)
  val stopColor = PropertyAttribute[Color]("stop-color", null)
  val stopOpacity = PropertyAttribute[Double]("stop-opacity", 1.0)
  val stroke = PropertyAttribute[Paint]("stroke", null)
  val strokeDashArray = PropertyAttribute[String]("stroke-dasharray", null)
  val strokeDashOffset = PropertyAttribute[String]("stroke-dashoffset", null)
  val strokeLineCap = PropertyAttribute[String]("stroke-linecap", null)
  val strokeLineJoin = PropertyAttribute[String]("stroke-linejoin", null)
  val strokeMiterLimit = PropertyAttribute[String]("stroke-miterlimit", null)
  val strokeOpacity = PropertyAttribute[Double]("stroke-opacity", 0.0)
  val strokeWidth = PropertyAttribute[Double]("stroke-width", 0.0)
  val textAnchor = PropertyAttribute[TextAnchor]("text-anchor", TextAnchor.Start)
  val textDecoration = PropertyAttribute[TextDecoration]("text-decoration", TextDecoration.None)
  val textRendering = PropertyAttribute[TextRendering]("text-rendering", TextRendering.Auto)
  val unicodeBidi = PropertyAttribute[String]("unicode-bidi", null)
  val visibility = PropertyAttribute[String]("visibility", null)
  val wordSpacing = PropertyAttribute[String]("word-spacing", null)
  val writingMode = PropertyAttribute[String]("writing-mode", null)
}