package io.youi.component

import com.outr.pixijs.PIXI
import io.youi.theme.ContainerTheme
import reactify.{ChangeListener, Var}

class Container extends Component {
  override protected[component] lazy val instance: PIXI.Container = new PIXI.Container()

  override lazy val theme: Var[ContainerTheme] = Var(Container)

  val children: Var[Vector[Component]] = prop(Vector.empty, updatesTransform = true)

  children.changes(new ChangeListener[Vector[Component]] {
    override def change(oldValue: Vector[Component], newValue: Vector[Component]): Unit = {
      var modifiedOld = oldValue

      // Remove values that existed previously that are not available in the new version
      oldValue.foreach { c =>
        if (!newValue.contains(c)) {
          modifiedOld = modifiedOld.filterNot(_ == c)
          remove(c)
          c.parent.asInstanceOf[Var[Option[Container]]] := None
        }
      }
      // Iterate over new and deal with changes
      var previous: Option[Component] = None
      newValue.zipWithIndex.foreach {
        case (c, index) => {
          val shouldAdd = if (modifiedOld.contains(c)) {
            if (modifiedOld.indexOf(c) == index) {
              false
            } else {
              remove(c)
              true
            }
          } else {
            true
          }
          if (shouldAdd) {
            addAfter(c, previous)
            c.parent.asInstanceOf[Var[Option[Container]]] := Some(Container.this)
          }
          previous = Some(c)
        }
      }
    }
  })

  protected def addAfter(component: Component, previous: Option[Component]): Unit = previous match {
    case Some(p) => {
      val index = instance.getChildIndex(p.instance)
      instance.addChildAt(component.instance, index + 1)
    }
    case None => instance.addChild(component.instance)
  }

  protected def remove(component: Component): Unit = instance.removeChild(component.instance)

  override def update(delta: Double): Unit = {
    super.update(delta)

    children().foreach(_.update(delta))
  }
}

object Container extends ContainerTheme