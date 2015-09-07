new bootstrap.component.ListGroup {
  contents += new tag.A with bootstrap.component.ListGroupItem {
    href := "#"
    active := true
    contents += new bootstrap.component.ListGroupItemHeading {
      contents += "List group item heading"
    }
    contents += new bootstrap.component.ListGroupItemText {
      contents += "..."
    }
  }
}
