package org.hyperscala

import org.jdom2._
import java.io.OutputStream
import output.{Format, XMLOutputter}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait XMLContent extends org.powerscala.hierarchy.Element {
  def toXML: Content
  def fromXML(content: Content): Unit

  def stream(out: OutputStream, pretty: Boolean = true) = {
    val format = pretty match {
      case true => Format.getPrettyFormat
      case false => Format.getCompactFormat
    }
    val outputter = new XMLOutputter(format)
    toXML match {
      case cdata: CDATA => outputter.output(cdata, out)
      case comment: Comment => outputter.output(comment, out)
      case docType: DocType => outputter.output(docType, out)
      case document: Document => outputter.output(document, out)
      case element: Element => outputter.output(element, out)
      case entityRef: EntityRef => outputter.output(entityRef, out)
    }
  }

  def outputString = {
    val outputter = new XMLOutputter()
    toXML match {
      case cdata: CDATA => outputter.outputString(cdata)
      case comment: Comment => outputter.outputString(comment)
      case docType: DocType => outputter.outputString(docType)
      case document: Document => outputter.outputString(document)
      case element: Element => outputter.outputString(element)
      case entityRef: EntityRef => outputter.outputString(entityRef)
    }
  }

  override def toString = outputString
}