package youi.component.support

import youi.component.Component

/**
  * ThemeComponent is a convenience trait presuming that its companion object is a Theme without a unique selector
  */
trait ThemedComponent {
  this: Component =>

  classes += getClass.getSimpleName
}