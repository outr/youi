package io.youi.component

import io.youi.drawable.Context
import io.youi.font.{Text, WrapMode}
import io.youi.paint.Stroke
import io.youi.path.Fill
import io.youi.theme.TextViewTheme
import reactify._

class TextView extends Component with TextViewTheme {
  lazy val text: Var[Text] = prop(Text.empty, updatesRendering = true)
  lazy val value: Var[String] = prop("", updatesRendering = true)
  lazy val wrap: Var[WrapMode] = prop(WrapMode.Word, updatesRendering = true)

  private val internalText: Val[Text] = Val {
    if (value().nonEmpty && font.file().nonEmpty) {
      val f = font.file()
      val mw = f.measureWidth(value, font.size, font.kerning)
      val mh = f.lineHeight(font.size)
      updateMeasured(mw, mh)
      val text = f(text = value, size = font.size, kerning = font.kerning(), maxWidth = size.width, wrap = wrap)
      updateMeasured(text.boundingBox.width, text.boundingBox.height)
      text
    } else {
      Text.empty
    }
  }

  override def init(): Unit = {
    super.init()

    internalText.attach(text := _)

    updateMeasured(text.boundingBox.width, text.boundingBox.height)
  }

  override lazy val theme: Var[TextViewTheme] = Var(TextView)

  override protected def defaultThemeParent = Some(theme)

  override def `type`: String = "TextView"

  override protected def drawInternal(context: Context): Unit = {
    Fill.draw(context, fill, Some(text.path))
    Stroke.draw(context, stroke.paint, Some(text.path), stroke.lineWidth, stroke.lineDash, stroke.lineDashOffset, stroke.lineCap, stroke.lineJoin)
  }

  init()
}

object TextView extends TextViewTheme