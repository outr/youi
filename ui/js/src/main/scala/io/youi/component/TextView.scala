package io.youi.component

import io.youi.drawable.Context
import io.youi.font.Text
import io.youi.paint.Stroke
import io.youi.path.Fill
import io.youi.theme.TextViewTheme
import reactify._

class TextView extends Component with TextViewTheme {
  lazy val text: Var[Text] = Var(Text.empty)
  lazy val value: Var[String] = Var("")

  private val internalText: Val[Text] = Val {
    if (value().nonEmpty && font.file().nonEmpty) {
      font.file().apply(text = value, size = font.size, kerning = font.kerning())
    } else {
      Text.empty
    }
  }
  internalText.attach(text := _)

  override lazy val theme: Var[TextViewTheme] = Var(TextView)

  updateMeasured(text.boundingBox.width, text.boundingBox.height)

  override protected def defaultThemeParent = Some(theme)

  override protected def drawInternal(context: Context): Unit = {
    Fill.draw(context, fill, Some(text.path))
    Stroke.draw(context, stroke.paint, Some(text.path), stroke.lineWidth, stroke.lineDash, stroke.lineDashOffset, stroke.lineCap, stroke.lineJoin)
  }
}

object TextView extends TextViewTheme