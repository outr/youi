package com.outr

import webframework.tags.Text

package object webframework {
  implicit def string2Text(s: String) = Text(s)
}