package org

import hyperscala.tags.Text

package object hyperscala {
  implicit def string2Text(s: String) = Text(s)
}