package org.hyperscala.examples.ui

import org.hyperscala.css.attributes.{Alignment, Display, LineStyle, VerticalAlignment}
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.hyperscala.selector._
import org.hyperscala.ui.{Dropzone, DropzoneFileEvent}
import org.hyperscala.web._
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DropzoneExample extends Webpage with Example {
  val ServerMode = true

  require(Dropzone)
  require(Gritter)

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

  val container = new tag.Div(id = "container")
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
  s.paddingTop := 25.px
  container.contents += "Drop Files Here"
  body.contents += container

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
  body.contents += previews

  val dropzone = Dropzone(container)
  if (ServerMode) {
    dropzone.connectEventsToServer()
    dropzone.addedFileEvent.on {
      case evt => Gritter.add(this, "Event Received", s"Added file: ${evt.name}")
    }
    dropzone.completeEvent.on {
      case evt => Gritter.add(this, "Event Received", "Complete")
    }
    dropzone.errorEvent.on {
      case evt => Gritter.add(this, "Event Received", "Error")
    }
    dropzone.processingEvent.on {
      case evt => Gritter.add(this, "Event Received", "Processing")
    }
    dropzone.removedFileEvent.on {
      case evt => Gritter.add(this, "Event Received", s"Removed File: ${evt.name}")
    }
    dropzone.successEvent.on {
      case evt => Gritter.add(this, "Event Received", "Success")
    }
    dropzone.thumbnailEvent.on {
      case evt => Gritter.add(this, "Event Received", "Thumbnail")
    }
    dropzone.uploadProgressEvent.on {
      case evt => Gritter.add(this, "Event Received", s"Upload Progress: ${evt.progress}")
    }
    dropzone.addedFileEvent.on {
      case evt => previews.contents += new DropzoneEntry(dropzone, Some(evt))
    }
    dropzone.fileReceived.on {
      case (filename, file) => println(s"Filename: $filename, Length: ${file.length()}")
    }
  } else {
    dropzone.previewsContainer := "#previews"
    dropzone.previewTemplate := new DropzoneEntry(dropzone).outputString
  }
  dropzone.thumbnailWidth := 150
  dropzone.thumbnailHeight := 150
  dropzone.ready()
}

class DropzoneEntry(dropzone: Dropzone, evt: Option[DropzoneFileEvent] = None) extends tag.Div {
  val preview = new tag.Img {
    data("dz-thumbnail", "")
  }
  val progress = new tag.Span {
    clazz += "dz-upload"
    data("dz-uploadprogress", "")
  }

  evt match {
    case Some(e) => {
      dropzone.thumbnailEvent.on {
        case pe => if (pe.name == e.name) {
          dropzone.applyThumbnail(preview)
        }
      }
      dropzone.uploadProgressEvent.on {
        case pe => if (pe.name == e.name) {   // Make sure it's this entry
          progress.style.width := pe.progress.pct
        }
      }
      dropzone.successEvent.on {
        case pe => if (pe.name == e.name) {
          progress.style.width := 100.pct
        }
      }
    }
    case None => // Static
  }

  clazz += "dz-preview"
  clazz += "dz-file-preview"
  contents += new tag.Div {
    clazz += "dz-details"
    contents += preview
    contents += new tag.Div {
      clazz += "dz-filename"
      contents += new tag.Span {
        data("dz-name", "")
        evt match {
          case Some(e) => contents += e.name
          case None => // Ignore
        }
      }
    }
    contents += new tag.Div {
      clazz += "dz-size"
      data("dz-size", "")
      evt match {
        case Some(e) => contents += s"${e.size} bytes"
        case None => // Ignore
      }
    }
  }
  contents += new tag.Div {
    clazz += "dz-progress"
    contents += progress
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