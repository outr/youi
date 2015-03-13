package org.hyperscala.fabricjs.filters

import org.hyperscala.fabricjs.Image

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class Mask(mask: Image, channel: Int = 0) extends BaseFilter