package org.hyperscala.ui.widgets

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed class NivoEffect extends EnumEntry {
  def value = name.charAt(0).toLower + name.substring(1)
}

object NivoEffect extends Enumerated[NivoEffect] {
  val SliceDown = new NivoEffect
  val SlideDownLeft = new NivoEffect
  val SliceUp = new NivoEffect
  val SliceUpLeft = new NivoEffect
  val SliceUpDown = new NivoEffect
  val SliceUpDownLeft = new NivoEffect
  val Fold = new NivoEffect
  val Fade = new NivoEffect
  val Random = new NivoEffect
  val SlideInRight = new NivoEffect
  val SlideInLeft = new NivoEffect
  val BoxRandom = new NivoEffect
  val BoxRain = new NivoEffect
  val BoxRainReverse = new NivoEffect
  val BoxRainGrow = new NivoEffect
  val BoxRainGrowReverse = new NivoEffect
}