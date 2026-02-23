package io.youi.chat

import io.youi.Color
import io.youi.component.{Container, ImageView, TextInput, TextView}
import io.youi.component.support.{BorderSupport, MaxSizeSupport, OverflowSupport}
import io.youi.component.types.{Cursor, Display, Overflow}
import io.youi.datatransfer.DataTransferManager
import io.youi.event.EventSupport
import io.youi.image.Image
import io.youi.key.Key
import reactify.{Channel, Var}

class ChatInput extends Container with OverflowSupport {
  private val pad = 8.0
  private val inputBarHeight = 50.0

  overflow @= Overflow.Hidden
  backgroundColor @= Color.fromHex("f0f0f0")
  size.height @= inputBarHeight

  val sent: Channel[ChatMessage] = Channel[ChatMessage]

  private val pendingImage: Var[Option[Image]] = Var(None)

  // --- Image preview (shown above input row when image attached) ---

  private val previewImage: ImageView & MaxSizeSupport = new ImageView with MaxSizeSupport {
    maxSize.width @= 80.0
    maxSize.height @= 50.0
  }

  private val removePreview = new TextView with EventSupport with BorderSupport {
    content @= "x"
    color @= Color.White
    backgroundColor @= Color.fromHex("cc0000")
    border.radius @= 10.0
    font.size @= 11.0
    cursor @= Cursor.Pointer
    element.style.padding = "1px 5px"
  }
  removePreview.event.click.on {
    pendingImage @= None
  }

  private val previewRow: Container = new Container {
    display @= Display.None
    position.left @= pad
    position.top @= pad
  }
  previewRow.children ++= Seq(previewImage, removePreview)
  removePreview.position.left := previewImage.size.width() + 4.0

  private val previewHeight = 60.0

  pendingImage.attach {
    case Some(img) =>
      val scale = math.min(80.0 / img.width, 50.0 / img.height).min(1.0)
      previewImage.width @= img.width * scale
      previewImage.height @= img.height * scale
      previewImage.image @= img
      previewRow.display @= Display.Block
      size.height @= inputBarHeight + previewHeight
    case None =>
      previewImage.image @= Image.empty
      previewRow.display @= Display.None
      size.height @= inputBarHeight
  }

  // --- Input row: attach button | text field | send button ---

  private val fileInput = DataTransferManager.addInput(accept = "image/*")

  val attachButton: TextView & EventSupport = new TextView with EventSupport with BorderSupport {
    content @= "Attach"
    font.size @= 14.0
    color @= Color.White
    backgroundColor @= Color.SteelBlue
    border.radius @= 6.0
    cursor @= Cursor.Pointer
    element.style.padding = "8px 12px"
  }
  attachButton.event.click.on {
    fileInput.click()
  }

  val sendButton: TextView & EventSupport = new TextView with EventSupport with BorderSupport {
    content @= "Send"
    font.size @= 14.0
    color @= Color.White
    backgroundColor @= Color.RoyalBlue
    border.radius @= 6.0
    cursor @= Cursor.Pointer
    element.style.padding = "8px 16px"
  }
  sendButton.event.click.on {
    send()
  }

  val textInput: TextInput & EventSupport = new TextInput with EventSupport with BorderSupport {
    placeholder @= "Type a message..."
    font.size @= 14.0
    border.radius @= 6.0
    size.height @= 36.0
    element.style.padding = "0 8px"
    element.style.setProperty("box-sizing", "border-box")
  }
  textInput.event.key.down.attach { evt =>
    if (evt.key == Key.Enter && !evt.shiftPressed) {
      evt.preventDefault()
      send()
    }
  }

  // Position: attach at left, send at right, text input fills middle
  // Y offset accounts for preview row when visible
  private def inputTop: Double = if (pendingImage() != scala.None) previewHeight + pad else pad

  attachButton.position.left @= pad
  attachButton.position.top := inputTop

  sendButton.position.top := inputTop
  sendButton.position.left := math.max(0.0, size.width() - sendButton.effectiveWidth() - pad)

  textInput.position.left := attachButton.effectiveWidth() + pad + pad
  textInput.position.top := inputTop
  textInput.size.width := math.max(50.0,
    size.width() - attachButton.effectiveWidth() - sendButton.effectiveWidth() - pad * 4
  )

  children ++= Seq(previewRow, attachButton, textInput, sendButton)

  // Listen for image files from DataTransferManager
  DataTransferManager.fileReceived.attach { dtf =>
    if (Image.isImage(dtf.file)) {
      Image(dtf.file).map { img =>
        pendingImage @= Some(img)
      }.startUnit()
    }
  }

  private def send(): Unit = {
    val text = textInput.value().trim
    val img = pendingImage()
    if (text.nonEmpty || img.isDefined) {
      sent @= ChatMessage(text, role = "user", image = img)
      textInput.value @= ""
      pendingImage @= None
      textInput.focus()
    }
  }
}
