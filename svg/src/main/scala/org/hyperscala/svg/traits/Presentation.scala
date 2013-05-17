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

  lazy val alignmentBaseline = PropertyAttribute[AlignmentBaseline]("alignment-baseline", AlignmentBaseline.Auto)
  lazy val baselineShift = PropertyAttribute[String]("baseline-shift", null)
  lazy val clip = PropertyAttribute[String]("clip", null)
  lazy val clipPath = PropertyAttribute[String]("clip-path", null)
  lazy val clipRule = PropertyAttribute[String]("clip-rule", null)
  lazy val color = PropertyAttribute[Color]("color", null)
  lazy val colorInterpolation = PropertyAttribute[String]("color-interpolation", null)
  lazy val colorInterpolationFilters = PropertyAttribute[String]("color-interpolation-filters", null)
  lazy val colorProfile = PropertyAttribute[String]("color-profile", null)
  lazy val colorRendering = PropertyAttribute[String]("color-rendering", null)
  lazy val cursor = PropertyAttribute[String]("cursor", null)
  lazy val direction = PropertyAttribute[String]("direction", null)
  lazy val display = PropertyAttribute[String]("display", null)
  lazy val dominantBaseline = PropertyAttribute[DominantBaseline]("dominant-baseline", DominantBaseline.Auto)
  lazy val enableBackground = PropertyAttribute[String]("enable-background", null)
  lazy val fill = PropertyAttribute[Paint]("fill", null)
  lazy val fillOpacity = PropertyAttribute[Double]("fill-opacity", 1.0)
  lazy val fillRule = PropertyAttribute[String]("fill-rule", null)
  lazy val filter = PropertyAttribute[String]("filter", null)
  lazy val floodColor = PropertyAttribute[Color]("flood-color", null)
  lazy val floodOpacity = PropertyAttribute[String]("flood-opacity", null)
  lazy val fontFamily = PropertyAttribute[String]("font-family", null)
  lazy val fontSize = PropertyAttribute[FontSize]("font-size", null)
  lazy val fontSizeAdjust = PropertyAttribute[String]("font-size-adjust", null)
  lazy val fontStretch = PropertyAttribute[FontStretch]("font-stretch", FontStretch.Normal)
  lazy val fontStyle = PropertyAttribute[FontStyle]("font-style", FontStyle.Normal)
  lazy val fontVariant = PropertyAttribute[FontVariant]("font-variant", FontVariant.Normal)
  lazy val fontWeight = PropertyAttribute[FontWeight]("font-weight", FontWeight.Normal)
  lazy val glyphOrientationHorizontal = PropertyAttribute[String]("glyph-orientation-horizontal", null)
  lazy val glyphOrientationVertical = PropertyAttribute[String]("glyph-orientation-vertical", null)
  lazy val imageRendering = PropertyAttribute[ImageRendering]("image-rendering", ImageRendering.Auto)
  lazy val kerning = PropertyAttribute[String]("kerning", null)
  lazy val letterSpacing = PropertyAttribute[String]("letter-spacing", null)
  lazy val lightingColor = PropertyAttribute[Color]("lighting-color", null)
  lazy val markerEnd = PropertyAttribute[String]("marker-end", null)
  lazy val markerMid = PropertyAttribute[String]("marker-mid", null)
  lazy val markerStart = PropertyAttribute[String]("marker-start", null)
  lazy val mask = PropertyAttribute[String]("mask", null)
  lazy val opacity = PropertyAttribute[Double]("opacity", 1.0)
  lazy val overflow = PropertyAttribute[String]("overflow", null)
  lazy val pointerEvents = PropertyAttribute[String]("pointer-events", null)
  lazy val shapeRendering = PropertyAttribute[String]("shape-rendering", null)
  lazy val stopColor = PropertyAttribute[Color]("stop-color", null)
  lazy val stopOpacity = PropertyAttribute[Double]("stop-opacity", 1.0)
  lazy val stroke = PropertyAttribute[Paint]("stroke", null)
  lazy val strokeDashArray = PropertyAttribute[String]("stroke-dasharray", null)
  lazy val strokeDashOffset = PropertyAttribute[String]("stroke-dashoffset", null)
  lazy val strokeLineCap = PropertyAttribute[String]("stroke-linecap", null)
  lazy val strokeLineJoin = PropertyAttribute[String]("stroke-linejoin", null)
  lazy val strokeMiterLimit = PropertyAttribute[String]("stroke-miterlimit", null)
  lazy val strokeOpacity = PropertyAttribute[Double]("stroke-opacity", 0.0)
  lazy val strokeWidth = PropertyAttribute[Double]("stroke-width", 0.0)
  lazy val textAnchor = PropertyAttribute[TextAnchor]("text-anchor", TextAnchor.Start)
  lazy val textDecoration = PropertyAttribute[TextDecoration]("text-decoration", TextDecoration.None)
  lazy val textRendering = PropertyAttribute[TextRendering]("text-rendering", TextRendering.Auto)
  lazy val unicodeBidi = PropertyAttribute[String]("unicode-bidi", null)
  lazy val visibility = PropertyAttribute[Visibility]("visibility", null)
  lazy val wordSpacing = PropertyAttribute[String]("word-spacing", null)
  lazy val writingMode = PropertyAttribute[String]("writing-mode", null)
}