package io.youi.theme

import io.youi.{CSSManager, Unique}
import io.youi.component.feature.{BorderFeature, FeatureParent, FontFeature, MarginFeature, MaxSizeFeature, MinSizeFeature, OverflowFeature, PaddingFeature, PositionFeature, SizeFeature, TextDecorationFeature}
import org.scalajs.dom.raw.CSSStyleDeclaration

import scala.annotation.tailrec

class Theme(val selector: String) extends FeatureParent {
  override lazy val css: CSSStyleDeclaration = CSSManager.insertRule(selector)

  def apply(state: ElementState): Theme = {
    val colonIndex = selector.indexOf(':')
    val baseSelector = if (colonIndex != -1) {
      selector.substring(0, colonIndex)
    } else {
      selector
    }
    val stateString = state match {
      case ElementState.Default => ""
      case _ => s":${state.value}"
    }
    Theme.bySelector(s"$baseSelector$stateString")
  }

  def border: BorderFeature = FeatureParent[BorderFeature](this, new BorderFeature(this))
  def font: FontFeature = FeatureParent[FontFeature](this, new FontFeature(this))
  def margin: MarginFeature = FeatureParent[MarginFeature](this, new MarginFeature(this))
  def maxSize: MaxSizeFeature = FeatureParent[MaxSizeFeature](this, new MaxSizeFeature(this))
  def minSize: MinSizeFeature = FeatureParent[MinSizeFeature](this, new MinSizeFeature(this))
  def overflow: OverflowFeature = FeatureParent[OverflowFeature](this, new OverflowFeature(this))
  def padding: PaddingFeature = FeatureParent[PaddingFeature](this, new PaddingFeature(this))
  def position: PositionFeature = FeatureParent[PositionFeature](this, new PositionFeature(this))
  def size: SizeFeature = FeatureParent[SizeFeature](this, new SizeFeature(this))
  def textDecoration: TextDecorationFeature = FeatureParent[TextDecorationFeature](this, new TextDecorationFeature(this))
}

object Theme {
  private var classNames = Set.empty[String]
  private var cache = Map.empty[String, Theme]

  def bySelector(selector: String): Theme = cache.get(selector) match {
    case Some(theme) => theme
    case None => {
      val theme = new Theme(selector)
      classNameFromSelector(selector).foreach { className =>
        classNames += className
      }
      cache += selector -> theme
      theme
    }
  }

  def byClassName(className: String, state: ElementState = ElementState.Default): Theme = {
    val selector: String = if (state == ElementState.Default) {
      s".$className"
    } else {
      s".$className:${state.value}"
    }
    bySelector(selector)
  }

  @tailrec
  def createClassName(state: ElementState = ElementState.Default, length: Int = 6): Theme = {
    val className = Unique(length, Unique.LettersLower)
    if (!classNames.contains(className)) {
      byClassName(className, state)
    } else {
      createClassName(state, length)
    }
  }

  def classNameFromSelector(selector: String): Option[String] = {
    var className = selector
    if (!className.startsWith(".")) {
      None
    } else {
      className = className.substring(1)
      val colonIndex = className.indexOf(':')
      if (colonIndex != -1) {
        className = className.substring(0, colonIndex)
      }
      Some(className)
    }
  }
}