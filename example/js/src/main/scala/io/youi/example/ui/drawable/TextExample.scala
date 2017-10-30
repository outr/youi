package io.youi.example.ui.drawable

import io.youi.Color
import io.youi.app.screen.DrawableScreen
import io.youi.drawable._
import io.youi.drawable.stats.RenderTimer
import io.youi.example.ui.UIExampleScreen
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.paint.Paint

import scala.concurrent.Future

object TextExample extends UIExampleScreen with DrawableScreen {
  override def name: String = "Text Example"

  private lazy val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent aliquet eros quis tellus facilisis elementum. Maecenas eros mi, ultrices sit amet urna non, malesuada lobortis arcu. Ut egestas sapien nec arcu iaculis, ac lobortis orci euismod. Morbi imperdiet in augue id blandit. Integer laoreet sapien nec iaculis suscipit. Integer ac diam viverra ante accumsan dapibus eu eu diam. Donec ex ex, porttitor id sagittis a, aliquam ut nisi. Donec ac eleifend enim, elementum commodo elit. Pellentesque laoreet, urna in auctor accumsan, lectus est semper metus, at vulputate mi sapien vel ligula. Ut nunc augue, egestas id vehicula eu, blandit sit amet sapien.\n\nInteger ipsum massa, efficitur ac nulla nec, vestibulum semper ligula. Vestibulum placerat, lectus quis fringilla vestibulum, tellus velit dapibus odio, et euismod erat sapien ac est. Nam ornare, urna vel vestibulum tincidunt, nisl neque sagittis urna, vitae volutpat nisi urna non neque. Phasellus tortor magna, feugiat vel ipsum eu, fermentum malesuada nibh. Aliquam eget facilisis urna. Donec a condimentum est. Proin quis viverra augue. Fusce scelerisque, nunc nec accumsan imperdiet, ante nulla bibendum dolor, sed posuere eros ex et purus. Integer posuere porta scelerisque. Vestibulum porta facilisis dolor, eget finibus nibh congue tristique. Duis semper urna ipsum, id porttitor urna efficitur et. Quisque ultricies metus nisl, et volutpat sem vulputate quis.\n\nMorbi in lorem magna. Duis sollicitudin, arcu suscipit ultrices luctus, ante mauris faucibus velit, ac fermentum nisi odio a lorem. Integer enim ante, iaculis quis pharetra maximus, pharetra at nisl. Duis a fermentum lectus. Nunc ornare pretium mauris eu pharetra. Ut lobortis elit ipsum, eget laoreet purus elementum ultricies. Praesent efficitur, tellus scelerisque vehicula suscipit, nibh purus tempor enim, vitae mattis arcu tellus bibendum ipsum. Ut risus sem, posuere ut erat vitae, tempor vestibulum turpis. Sed in accumsan nulla. Nunc eu massa lacinia, semper risus sed, fringilla diam. Nullam id velit quis massa imperdiet fermentum. Cras ultricies placerat orci et mattis. Ut elit augue, rhoncus quis magna convallis, ultricies dictum felis.\n\nIn hac habitasse platea dictumst. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus id turpis laoreet, ultrices turpis id, sodales tellus. Morbi nunc nunc, fringilla sed tellus sed, pretium ornare ligula. Etiam lacinia risus neque, sit amet accumsan libero condimentum eu. Praesent nisl libero, iaculis sed vehicula vel, cursus at ante. Donec tempor aliquam elit, nec fermentum turpis. Aenean augue sem, ornare quis porta nec, tincidunt id massa. Etiam volutpat lectus ac libero malesuada, non pretium tortor vehicula.\n\nMauris tellus leo, sagittis at ornare eu, posuere a dolor. Sed eleifend arcu ac ligula pretium ornare. Integer pellentesque luctus nisl id cursus. Vivamus congue nibh sit amet magna ornare rutrum. Nulla dolor quam, malesuada dictum pulvinar at, tempus sit amet ipsum. Mauris pretium vehicula mi. Aenean mattis bibendum dolor eget tincidunt. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Integer at massa augue. Praesent justo lectus, lobortis quis sollicitudin sed, condimentum a lectus. Quisque rutrum lectus leo, eu dictum arcu ultrices nec. Proin fringilla nisl vulputate arcu placerat hendrerit. Sed eu tristique sem. Aenean dapibus tortor vel augue euismod porttitor. Proin pharetra, lorem sit amet gravida lobortis, magna velit tempus ipsum, vitae commodo arcu est ac magna."

  override protected val drawable: Future[RenderTimer] = OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`).map { font =>
    RenderTimer(
      Group(
        Transformation(x = 0.0, y = 0.0)(
          font("Testing", 96.0).toDrawable(Color.Green)
        ),
        Transformation(x = 350.0, y = 0.0)(
          font("Testing", 96.0).toDrawable(Color.Orange)
        ),
        Transformation(x = 700.0, y = 0.0)(
          font("Testing", 96.0).toDrawable(Color.Red),
        ),
        Transformation(x = 1050.0, y = 0.0)(
          font("Testing", 96.0).toDrawable(Color.Blue),
        ),
        Transformation(x = 1400.0, y = 0.0)(
          font("Testing", 96.0).toDrawable(Color.HotPink),
        ),
        Transformation(x = 0.0, y = 125.0)(
          font(loremIpsum, 24.0, 1800.0).toDrawable(Paint.vertical(1200.0).distributeColors(Color.Red, Color.Green, Color.Blue))
        )
      )
    )
  }

  override def path: String = "/examples/drawable/text.html"
}