package youi.layout

import youi.component.Component
import youi.component.support.ContainerSupport
import youi.component.types.Display
import reactify.Var

trait LayoutSupport extends ContainerSupport {
  val layout: Var[Option[Layout]] = Var(None)

  private var _layoutInProgress = false
  private var _userSetWidth = false
  private var _userSetHeight = false

  layout.changes {
    case (old, current) =>
      old.foreach(_.disconnect(this, children))
      current.foreach { l =>
        _layoutInProgress = true
        try {
          l.connect(this, children)
          updateContentBounds()
        } finally {
          _layoutInProgress = false
        }
      }
  }

  children.changes { case (_, current) =>
    if (!_layoutInProgress) {
      _layoutInProgress = true
      try {
        layout().foreach { l =>
          l.childrenChanged(this, current)
          updateContentBounds()
        }
      } finally {
        _layoutInProgress = false
      }
    }
  }

  size.width.on {
    if (!_layoutInProgress) {
      _userSetWidth = true
      _layoutInProgress = true
      try {
        layout().foreach { l =>
          l.resized(this, children)
          updateContentBounds()
        }
      } finally {
        _layoutInProgress = false
      }
    }
  }

  size.height.on {
    if (!_layoutInProgress) {
      _userSetHeight = true
      _layoutInProgress = true
      try {
        layout().foreach { l =>
          l.resized(this, children)
          updateContentBounds()
        }
      } finally {
        _layoutInProgress = false
      }
    }
  }

  private def updateContentBounds(): Unit = {
    val all = children.get
    if (all.nonEmpty) {
      var maxRight = 0.0
      var maxBottom = 0.0
      all.foreach { child =>
        if (child.display() != Display.None) {
          maxRight = math.max(maxRight, child.position.left() + child.effectiveWidth())
          maxBottom = math.max(maxBottom, child.position.top() + child.effectiveHeight())
        }
      }
      if (!_userSetWidth && maxRight > 0.0) size.width @= maxRight
      if (!_userSetHeight && maxBottom > 0.0) size.height @= maxBottom
    }
  }
}
