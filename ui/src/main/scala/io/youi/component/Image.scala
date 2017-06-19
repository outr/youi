package io.youi.component
import com.outr.pixijs.PIXI
import io.circe.Json
import io.circe.generic.auto._
import io.circe.syntax._
import io.youi.persist.Persistence
import io.youi.theme.ImageTheme
import reactify.{Val, Var}

class Image extends Component {
  override lazy val theme: Var[_ <: ImageTheme] = Var(Image)

  def this(texture: Texture) = {
    this()
    this.texture := texture
  }

  override protected[component] lazy val instance: PIXI.Sprite = new PIXI.Sprite(Texture.Empty.instance)

  lazy val texture: Var[Texture] = prop(new Texture(instance.texture), (t: Texture) => instance.texture = t.instance, updatesRendering = true)
  lazy val update: Val[Long] = Val(texture.update)

  update.on(invalidate())

  size.measured.width := texture().width()
  size.measured.height := texture().height()

  // Configure auto-scaling to size
  scale.x := (if (size.measured.width() > 0.0) size.width() / size.measured.width() else 1.0)
  scale.y := (if (size.measured.height() > 0.0) size.height() / size.measured.height() else 1.0)
}

object Image extends ImageTheme with Persistence[Image] {
  override protected def identifier: String = "image"

  override protected def create(): Image = new Image

  override protected def parentPersistence: Option[Persistence[_ >: Image]] = Some(Component)

  override protected def saveInfo(t: Image): Json = SerializedImage(
    texture = "TODO"
  ).asJson

  override protected def loadInfo(t: Image, json: Json): Unit = {
    val si = json.as[SerializedImage].getOrElse(throw new RuntimeException(s"Unable to parse $json to SerializedImage."))
    // TODO: set texture
    scribe.info(si)
  }

  case class SerializedImage(texture: String)
}