new ListGroup {
  contents += new tag.A with ListGroupItem {
    href := "#"
    active := true
    contents += new ListGroupItemHeading {
      contents += "List group item heading"
    }
    contents += new ListGroupItemText {
      contents += "..."
    }
  }
}
