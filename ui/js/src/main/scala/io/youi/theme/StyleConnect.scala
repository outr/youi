package io.youi.theme

trait StyleConnect[T] {
  def init(theme: Theme, v: StyleProp[T], name: String): Unit
}

object StyleConnect {
  def withElement(theme: Theme)(f: html.Element => Unit): Unit = theme match {
    case c: HTMLComponent[_] => {
      val e = HTMLComponent.element(c)
      f(e)
    }
    case _ => // Not an HTMLComponent
  }

  def field[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: StyleProp[T], name: String): Unit = withElement(theme) { e =>
      val value = v.option.map(valueOption => stringify.toString(valueOption.getOrElse(v.value())))
      value.attachAndFire {
        case Some(s) => e.setAttribute(name, s)
        case None => e.removeAttribute(name)
      }
    }
  })

  def style[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = style[T]((t: T) => t)(stringify)

  def style[T](modifier: T => T)
              (implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: StyleProp[T], name: String): Unit = withElement(theme) { e =>
      val value = v.option.map(valueOption => stringify.toString(modifier(valueOption.getOrElse(v.value()))))
      value.attach {
        case Some(s) => e.style.setProperty(name, s)
        case None => e.style.removeProperty(name)
      }
    }
  })

  def content[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: StyleProp[T], name: String): Unit = withElement(theme) { e =>
      val value = v.option.map(valueOption => stringify.toString(valueOption.getOrElse(v.value())))
      value.attachAndFire {
        case Some(s) => e.innerHTML = s
        case None => e.innerHTML = ""
      }
    }
  })

  def classify[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: StyleProp[T], name: String): Unit = withElement(theme) { e =>
      val value = v.option.map(valueOption => stringify.toString(valueOption.getOrElse(v.value())))
      var previous: Option[String] = None
      value.attachAndFire { current =>
        previous.foreach(e.classList.remove)
        current.foreach(e.classList.add)
        previous = current
      }
    }
  })

  def flag(on: Option[String] = None, off: Option[String] = None): Option[StyleConnect[Boolean]] = Some(new StyleConnect[Boolean] {
    override def init(theme: Theme, v: StyleProp[Boolean], name: String): Unit = withElement(theme) { e =>
      val classes = e.classList.toSet
      val isOn = on.exists(classes.contains)
      val isOff = off.exists(classes.contains)
      if (isOn) {
        v @= true
      } else if (isOff) {
        v @= false
      }
      v.attachAndFire {
        case true => {
          off.foreach(e.classList.remove)
          on.foreach(e.classList.add)
        }
        case false => {
          on.foreach(e.classList.remove)
          off.foreach(e.classList.add)
        }
      }
    }
  })
}