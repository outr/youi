package org.hyperscala.ui.widgets

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class NivoEffect extends EnumEntry {
  def value = name.charAt(0).toLower + name.substring(1)
}

object NivoEffect extends Enumerated[NivoEffect] {
  case object SliceDown extends NivoEffect
  case object SlideDownLeft extends NivoEffect
  case object SliceUp extends NivoEffect
  case object SliceUpLeft extends NivoEffect
  case object SliceUpDown extends NivoEffect
  case object SliceUpDownLeft extends NivoEffect
  case object Fold extends NivoEffect
  case object Fade extends NivoEffect
  case object Random extends NivoEffect
  case object SlideInRight extends NivoEffect
  case object SlideInLeft extends NivoEffect
  case object BoxRandom extends NivoEffect
  case object BoxRain extends NivoEffect
  case object BoxRainReverse extends NivoEffect
  case object BoxRainGrow extends NivoEffect
  case object BoxRainGrowReverse extends NivoEffect

  val values = findValues.toVector
}