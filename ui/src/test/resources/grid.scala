new bootstrap.component.Row {
  contents += new bootstrap.component.Column {
    medium := Some(8)
    extraSmall := Some(12)
    contents += "a"
  }
  contents += new bootstrap.component.Column {
    medium := Some(4)
    extraSmall := Some(6)
    contents += "b"
  }
}