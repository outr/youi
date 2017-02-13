package io.youi.hypertext
import com.outr.reactify.Var
import io.youi.dom
import io.youi.hypertext.style.Image
import org.scalajs.dom.Event
import org.scalajs.dom.html.{Image => HTMLImage}

class ImageView extends Component {
  override protected[youi] val element: HTMLImage = dom.create[HTMLImage]("img")

  val image: Var[Image] = prop(Image.empty, i => element.src = i.src, mayCauseResize = true)
  val preserveAspectRatio: Var[Boolean] = Var(true)
  val loaded: Var[Boolean] = Var(false)

  object clip {
    val x1: Var[Option[Double]] = Var(None)
    val y1: Var[Option[Double]] = Var(None)
    val x2: Var[Option[Double]] = Var(None)
    val y2: Var[Option[Double]] = Var(None)
  }

  init()

  override protected def init(): Unit = {
    super.init()

    image.attach { i =>
      loaded := false
    }
    element.addEventListener("load", (evt: Event) => {
      updateSize()
      loaded := true
    })
  }
}