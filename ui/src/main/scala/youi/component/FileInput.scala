package youi.component

import youi.component.types.Prop
import youi.dom
import org.scalajs.dom.{Event, File, FileList, html}
import reactify.{Val, Var}
import spice.net.ContentType

import scala.scalajs.js.|

class FileInput(element: html.Input = dom.create.input) extends Component(element) {
  lazy val name: Prop[String] = new Prop[String](element.name, element.name_=)
  lazy val disabled: Prop[Boolean] = new Prop[Boolean](element.disabled, element.disabled_=)
  lazy val accept: Prop[Set[ContentType | String]] = new Prop(
    getter = Set.empty,
    setter = set => set.map(_.toString).mkString(", ")
  )
  lazy val multiple: Prop[Boolean] = new Prop[Boolean](element.multiple, element.multiple_=)
  lazy val files: Val[List[File]] = {
    val v = Var[List[File]](Nil)
    def setFileList(fileList: FileList): Unit = {
      val list = (0 until fileList.length).toList.map(index => fileList.item(index))
      v @= list
    }
    element.addEventListener("change", (_: Event) => {
      setFileList(element.files)
    })
    v
  }

  element.`type` = "file"

  def open(): Unit = element.click()

  def reset(): Unit = {
    element.value = ""
    files.asInstanceOf[Var[List[File]]] @= Nil
  }
}
