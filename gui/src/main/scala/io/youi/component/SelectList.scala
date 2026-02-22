package io.youi.component

import io.youi.component.types.Prop
import io.youi.dom
import org.scalajs.dom.{Event, html}
import reactify.Var

class SelectList[T](val select: html.Select = dom.create.select)
                   (implicit stringify: T => String) extends Component(select) {
  val list: Var[List[T]] = Var(Nil)
  val rows: Prop[Int] = new Prop[Int](select.size, select.size_=, measure.trigger)
  val selected: Var[Option[T]] = Var(None)
  lazy val disabled: Prop[Boolean] = new Prop[Boolean](select.disabled, select.disabled_=)

  list.attach { l =>
    element.innerHTML = ""
    var found = false
    l.foreach { t =>
      val s = stringify(t)
      val o = dom.create.option
      o.value = s
      o.innerHTML = s
      if (selected().contains(t)) {
        o.selected = true
        found = true
      }
      element.appendChild(o)
    }
    if (!found) selected @= None
  }

  selected.attach {
    case Some(t) => select.value = stringify(t)
    case None => select.value = ""
  }

  element.addEventListener("change", (_: Event) => {
    val i = select.selectedIndex
    if (i == -1) {
      selected @= None
    } else {
      selected @= Some(list()(i))
    }
  })
}