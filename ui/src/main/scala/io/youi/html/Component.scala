package io.youi.html

import com.outr.props.Var
import io.youi.AbstractComponent
import org.scalajs.dom.html.Element

trait Component extends AbstractComponent {
  protected[youi] val element: Element

  protected def prop[T](get: => T, set: T => Unit): Var[T] = {
    val v = Var[T](get)
    v.attach { value =>
      set(value)
    }
    v
  }
}
