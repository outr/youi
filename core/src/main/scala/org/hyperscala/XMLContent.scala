package org.hyperscala

import io.HTMLOutputter
import org.jdom2._
import java.io.OutputStream
import output.XMLOutputter

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait XMLContent extends org.powerscala.hierarchy.Element {
  def toXML: Content
  def fromXML(content: Content): Unit
  def render = true

  def stream(out: OutputStream, pretty: Boolean = true) = {
    val outputter = new HTMLOutputter()
    val writer = HTMLOutputter.OutputStreamWriter(out)
    outputter(toXML, writer)
    writer.close()
//    val format = pretty match {
//      case true => Format.getPrettyFormat
//      case false => Format.getCompactFormat
//    }
//    format.setExpandEmptyElements(true)
//    val outputter = new XMLOutputter(format)
//    // TODO: write a custom XMLOutputter that better supports HTML
////    toXML match {
////      case cdata: CDATA => outputter.output(cdata, out)
////      case comment: Comment => outputter.output(comment, out)
////      case docType: DocType => outputter.output(docType, out)
////      case document: Document => outputter.output(document, out)
////      case element: Element => outputter.output(element, out)
////      case entityRef: EntityRef => outputter.output(entityRef, out)
////    }
//    val bytes = toString(outputter)
//      .replaceAll("></img>", "/>")
//      .replaceAll("></link>", "/>")
//      .replaceAll("></meta>", "/>")
//      .replaceAll("></br>", "/>")
//      .replaceAll("></input>", "/>")
//      .getBytes
//    out.write(bytes)
//    out.flush()
  }

  def outputString = toString(new XMLOutputter())

  protected def toString(outputter: XMLOutputter) = {
    toXML match {
      case cdata: CDATA => outputter.outputString(cdata)
      case comment: Comment => outputter.outputString(comment)
      case docType: DocType => outputter.outputString(docType)
      case document: Document => outputter.outputString(document)
      case element: Element => outputter.outputString(element)
      case entityRef: EntityRef => outputter.outputString(entityRef)
    }
  }

//  override def toString: String = outputString
}