new bootstrap.component.Tabs {
  tabs := true
  contents += new bootstrap.component.TabEntry {
    role := "presentation"
    active := true
    contents += new tag.A(href = "#") {
      contents += "Home"
    }
  }
  contents += new bootstrap.component.TabEntry {
    role := "presentation"
    contents += new tag.A(href = "#") {
      contents += "Profile"
    }
  }
  contents += new bootstrap.component.TabEntry {
    role := "presentation"
    contents += new tag.A(href = "#") {
      contents += "Messages"
    }
  }
}