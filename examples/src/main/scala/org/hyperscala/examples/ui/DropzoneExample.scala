package org.hyperscala.examples.ui

import org.hyperscala.css.attributes.{Alignment, VerticalAlignment, Display, LineStyle}
import org.hyperscala.examples.Example
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.web._
import org.hyperscala.ui.Dropzone
import org.hyperscala.html._
import org.powerscala.Color
import org.hyperscala.selector._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DropzoneExample extends Example {
  this.require(Dropzone)

  connected[tag.HTML] {
    case html => {
      html.head.useStyle(".dz-preview") {
        case style => {
          style.paddingAll(5.px)
          style.marginAll(5.px)
          style.borderWidth := 1.px
          style.borderStyle := LineStyle.Ridge
          style.borderColor := Color.Black
          style.borderRadius := 5.px
          style.width := 150.px
          style.height := 350.px
          style.display := Display.InlineBlock
          style.verticalAlign := VerticalAlignment.Top
        }
      }
      html.head.useStyle(".dz-preview .dz-progress") {
        case style => {
          style.borderWidth := 1.px
          style.borderStyle := LineStyle.Solid
          style.borderColor := Color.Black
          style.borderRadius := 5.px
          style.width := 140.px
          style.height := 25.px
        }
      }
      html.head.useStyle(".dz-preview .dz-progress .dz-upload") {
        case style => {
          style.borderRadius := 5.px
          style.height := 25.px
          style.backgroundColor := Color.Blue
          style.display := Display.Block
        }
      }
      html.head.useStyle(".dz-preview .dz-success-mark, .dz-preview .dz-error-mark") {
        case style => style.display := Display.None
      }
      html.head.useStyle(".dz-preview.dz-success .dz-success-mark") {
        case style => style.display := Display.Block
      }
      html.head.useStyle(".dz-preview.dz-error .dz-error-mark") {
        case style => style.display := Display.Block
      }
    }
  }

  val container = new tag.Div
  val s = container.style
  s.cursor := "pointer"
  s.width := 200.px
  s.height := 150.px
  s.borderWidth := 2.px
  s.borderStyle := LineStyle.Dashed
  s.borderColor := Color.Black
  s.borderRadius := 5.px
  s.fontSize := 36.px
  s.textAlign := Alignment.Center
  s.paddingTop := 50.px
  container.contents += "Drop Files Here"
  contents += container

  val previews = new tag.Div {
    id := "previews"
    clazz += "dropzone-previews"
    style.marginTop := 10.px
    style.minHeight := 100.px
    style.borderWidth := 1.px
    style.borderStyle := LineStyle.Solid
    style.borderColor := Color.Blue
    style.borderRadius := 5.px
  }
  contents += previews

  val template = new tag.Div {
    clazz += "dz-preview"
    clazz += "dz-file-preview"
    contents += new tag.Div {
      clazz += "dz-details"
      contents += new tag.Img {
        data("dz-thumbnail", "")
      }
      contents += new tag.Div {
        clazz += "dz-filename"
        contents += new tag.Span {
          data("dz-name", "")
        }
      }
      contents += new tag.Div {
        clazz += "dz-size"
        data("dz-size", "")
      }
    }
    contents += new tag.Div {
      clazz += "dz-progress"
      contents += new tag.Span {
        clazz += "dz-upload"
        data("dz-uploadprogress", "")
      }
    }
    contents += new tag.Div {
      clazz += "dz-success-mark"
      contents += "Success!"
    }
    contents += new tag.Div {
      clazz += "dz-error-mark"
      contents += "Failure!"
    }
    contents += new tag.Div {
      clazz += "dz-error-message"
      contents += new tag.Span {
        data("dz-errormessage", "")
      }
    }
  }

  val dropzone = Dropzone(container)
  dropzone.previewsContainer := "#previews"
  dropzone.previewTemplate := template.outputString
  dropzone.thumbnailWidth := 150
  dropzone.thumbnailHeight := 150
  dropzone.addedFile := JavaScriptString(
    """
      |function(file) {
      | console.log('addedfile!: ' + file);
      |}
    """.stripMargin)
  dropzone.removedFile := JavaScriptString(
    """
      |function(file) {
      | console.log('removedfile!: ' + file);
      |}
    """.stripMargin)
  dropzone.thumbnail := JavaScriptString(
    """
      |function(file, dataUrl) {
      | console.log('thumbnail!: ' + file + ', ' + dataUrl);
      |}
    """.stripMargin)
  dropzone.processing := JavaScriptString(
    """
      |function(file) {
      | console.log('processing!: ' + file);
      |}
    """.stripMargin)
  dropzone.uploadProgress := JavaScriptString(
    """
      |function(file, progress, bytesSent) {
      | console.log('uploadProgress!: ' + file + ', ' + progress + ', ' + bytesSent);
      |}
    """.stripMargin)
  dropzone.success := JavaScriptString(
    """
      |function(file) {
      | console.log('success!: ' + file);
      |}
    """.stripMargin)
  dropzone.complete := JavaScriptString(
    """
      |function(file) {
      | console.log('complete!: ' + file);
      |}
    """.stripMargin)
  dropzone.fileReceived.on {
    case (filename, file) => println(s"Filename: $filename, Length: ${file.length()}")
  }
}