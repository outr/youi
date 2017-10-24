package io.youi.component

import io.youi.drawable.Context
import io.youi.font.Text
import io.youi.paint.Stroke
import io.youi.path.Fill
import io.youi.theme.TextViewTheme
import reactify._

class TextView extends Component with TextViewTheme {
  lazy val text: Var[Text] = prop(Text.empty, updatesRendering = true)
  lazy val value: Var[String] = prop("", updatesRendering = true)

  private val internalText: Val[Text] = Val {
    if (value().nonEmpty && font.file().nonEmpty) {
      font.file().apply(text = value, size = font.size, kerning = font.kerning())
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

  init()

  override protected def defaultThemeParent = Some(theme)

  override def `type`: String = "TextView"

  override protected def drawInternal(context: Context): Unit = {
    Fill.draw(context, fill, Some(text.path))
    Stroke.draw(context, stroke.paint, Some(text.path), stroke.lineWidth, stroke.lineDash, stroke.lineDashOffset, stroke.lineCap, stroke.lineJoin)
  }
}

object TextView extends TextViewTheme