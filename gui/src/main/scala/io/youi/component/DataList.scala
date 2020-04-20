package io.youi.component

import io.youi.{Unique, dom}
import org.scalajs.dom.html
import reactify.Var

class DataList[T](val dataList: html.DataList = dom.create.datalist)
                 (implicit stringify: T => String) extends Component(dataList) {
  id @= Unique()
  val list: Var[List[T]] = Var(Nil)

  list.attach { l =>
    element.innerHTML = ""
    l.foreach { t =>
      val s = stringify(t)
      val o = dom.create.option
      o.value = s
      o.innerHTML = s
      element.appendChild(o)
    }
  }
}
