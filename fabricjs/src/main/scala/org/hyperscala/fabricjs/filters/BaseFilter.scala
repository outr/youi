package org.hyperscala.fabricjs.filters

import org.hyperscala.fabricjs.Image
import org.hyperscala.javascript.JavaScriptContent
import org.powerscala.Unique
import org.powerscala.reflect._

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class BaseFilter {
  val id = Unique
  private lazy val options = getClass.caseValues.map(cv => cv.name -> JavaScriptContent.toJS(cv[Any](this)))

  protected[fabricjs] def addFilterJS(image: Image) = s"FabricJS.addFilter(image, '$id', $toJS);"
  def toJS: String = s"new fabric.Image.filters.${getClass.getSimpleName.replaceAll("[$]", "")}($optionsJS)"
  protected def optionsJS = if (options.nonEmpty) {
    options.map(t => s"${t._1}: ${t._2}").mkString("{ ", ", ", " }")
  } else {
    ""
  }
}