package youi.layout

import youi.component.support.ContainerSupport
import youi.component.types.Display
import reactify.{Val, Var}

trait LayoutSupport extends ContainerSupport {
  val layout: Var[Option[Layout]] = Var(None)

  private var _layoutInProgress = false
  private var _userSetWidth = false
  private var _userSetHeight = false

  private val totalWidth: Val[Double] = Val(children().map(_.size.width()).sum)
  private val totalHeight : Val[Double] = Val(children().map(_.size.height()).sum)

  val contentWidth: Val[Double] = Val {
    val all = children.get
    if (all.nonEmpty) {
      all.filter(_.display() != Display.None).map(c => c.position.right()).maxOption.getOrElse(0.0)
    } else 0.0
  }

  val contentHeight: Val[Double] = Val {
    val all = children.get
    if (all.nonEmpty) {
      all.filter(_.display() != Display.None).map(c => c.position.bottom()).maxOption.getOrElse(0.0)
    } else 0.0
  }

  size.width := contentWidth()
  size.height := contentHeight()

  layout.changes {
    case (old, current) =>
      old.foreach(_.disconnect(this, children))
      current.foreach { l =>
        _layoutInProgress = true
        try {
          l.connect(this, children)
        } finally {
          _layoutInProgress = false
        }
      }
  }

  children.changes { case (_, current) =>
    withLayout() { l =>
      l.childrenChanged(this, current)
    }
  }
  size.width.on(withLayout(widthSet = true) { l =>
    l.resized(this, children)
  })
  size.height.on(withLayout(heightSet = true) { l =>
    l.resized(this, children)
  })
  totalWidth.on(withLayout() { l =>
    l.resized(this, children)
  })
  totalHeight.on(withLayout() { l =>
    l.resized(this, children)
  })

  private def withLayout(widthSet: Boolean = false,
                         heightSet: Boolean = false)(f: Layout => Unit): Unit = {
    if (!_layoutInProgress) {
      if (widthSet) _userSetWidth = true
      if (heightSet) _userSetHeight = true
      _layoutInProgress = true
      try {
        layout().foreach { l =>
          f(l)
        }
      } finally {
        _layoutInProgress = false
      }
    }
  }
}
