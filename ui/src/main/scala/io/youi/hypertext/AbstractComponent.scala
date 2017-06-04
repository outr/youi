package io.youi.hypertext

import io.youi.hypertext.style.{ColorProperties, Position}
import io.youi.task.TaskSupport
import io.youi.{Unique, Updates}
import reactify._

trait AbstractComponent extends TaskSupport {
  lazy val id: Var[String] = Var(Unique(length = 8, characters = Unique.LettersLower))
  lazy val parent: State[Option[AbstractComponent]] = Var(None)

  object position {
    lazy val `type`: Var[Position] = Var(Position.Absolute)
    lazy val x: Var[Double] = Var(0.0)
    lazy val y: Var[Double] = Var(0.0)

    lazy val left: Var[Double] = x
    lazy val center: Dep[Double, Double] = Dep(left, size.actual.width / 2.0)
    lazy val right: Dep[Double, Double] = Dep(left, size.actual.width)

    lazy val top: Var[Double] = y
    lazy val middle: Dep[Double, Double] = Dep(top, size.actual.height / 2.0)
    lazy val bottom: Dep[Double, Double] = Dep(top, size.actual.height)
  }

  object padding {
    lazy val left: Var[Double] = Var(0.0)
    lazy val right: Var[Double] = Var(0.0)
    lazy val top: Var[Double] = Var(0.0)
    lazy val bottom: Var[Double] = Var(0.0)

    def :=(value: => Double): Unit = set(value)

    def set(value: => Double): Unit = {
      left := value
      right := value
      top := value
      bottom := value
    }
  }

  object margin {
    lazy val left: Var[Double] = Var(0.0)
    lazy val right: Var[Double] = Var(0.0)
    lazy val top: Var[Double] = Var(0.0)
    lazy val bottom: Var[Double] = Var(0.0)

    def :=(value: => Double): Unit = set(value)

    def set(value: => Double): Unit = {
      left := value
      right := value
      top := value
      bottom := value
    }
  }

  object scrollbar {
    object horizontal {
      /**
        * Size in pixels of the vertical space used by a horizontal scrollbar. 0 if not showing.
        */
      lazy val size: State[Double] = Var(0.0)
      lazy val visible: Val[Boolean] = Val(size > 0.0)
      lazy val position: Var[Double] = Var(0.0)
      lazy val percentage: Var[Double] = Var(0.0)
    }
    object vertical {
      /**
        * Size in pixels of the horizontal space used by a vertical scrollbar. 0 if not showing.
        */
      lazy val size: State[Double] = Var(0.0)
      lazy val visible: Val[Boolean] = Val(size > 0.0)
      lazy val position: Var[Double] = Var(0.0)
      lazy val percentage: Var[Double] = Var(0.0)
    }
  }

  protected val actualWidth: Var[Double] = Var(0.0)
  protected val actualHeight: Var[Double] = Var(0.0)

  protected val innerWidth: Var[Double] = Var(0.0)
  protected val innerHeight: Var[Double] = Var(0.0)

  object size {
    lazy val width: Var[Double] = Var(0.0)
    lazy val height: Var[Double] = Var(0.0)

    lazy val center: Val[Double] = Val(width / 2.0)
    lazy val middle: Val[Double] = Val(height / 2.0)

    object min {
      lazy val width: Var[Double] = Var(0.0)
      lazy val height: Var[Double] = Var(0.0)
    }
    object max {
      lazy val width: Var[Double] = Var(Double.MaxValue)
      lazy val height: Var[Double] = Var(Double.MaxValue)
    }
    object preferred {
      lazy val width: Val[Double] = Val(math.min(math.max(size.width, min.width), max.width))
      lazy val height: Val[Double] = Val(math.min(math.max(size.height, min.height), max.height))
    }
    object actual {
      val width: Val[Double] = Val(actualWidth)
      val height: Val[Double] = Val(actualHeight)
    }
    object inner {
      val width: Val[Double] = Val(innerWidth)
      val height: Val[Double] = Val(innerHeight)
    }
  }

  object scale {
    lazy val x: Var[Double] = Var(1.0)
    lazy val y: Var[Double] = Var(1.0)
  }

  lazy val color: ColorProperties = new ColorProperties(0.0, 0.0, 0.0, 1.0)
  lazy val backgroundColor: ColorProperties = new ColorProperties(1.0, 1.0, 1.0, 0.0)
  lazy val rotation: Var[Double] = Var(0.0)
  lazy val opacity: Var[Double] = Var(1.0)
  lazy val visible: Var[Boolean] = Var(true)

  protected def init(): Unit = {}

  def removeFromParent(): Boolean = parent().exists { p =>
    p.asInstanceOf[AbstractContainer[AbstractComponent]].children -= this
    true
  }

  override def toString: String = s"${getClass.getSimpleName}:${id()}"
}