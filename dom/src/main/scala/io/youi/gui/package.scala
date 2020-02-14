package io.youi

import org.scalajs.dom.html

import scala.language.implicitConversions

package object gui {
  private var componentsMap = Map.empty[html.Element, Component]

  implicit def element2Component(element: html.Element): Component = componentsMap.get(element) match {
    case Some(c) => c
    case None => {
      val c = Component.createFor(element)
      componentsMap += element -> c
      c
    }
  }
}