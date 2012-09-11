package org.hyperscala.editor

import org.hyperscala.data.ListSelect
import org.powerscala.property.StandardProperty

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class BooleanEditor(property: StandardProperty[Boolean],
                    identifier: Boolean => String = (b: Boolean) => b.toString,
                    visualizer: Boolean => String = (b: Boolean) => if (b) "Yes" else "No")
    extends ListSelect[Boolean](property, identifier, visualizer) {
  name := property.name()
  values := List(true, false)
}