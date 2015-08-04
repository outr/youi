new Tabs {
  tabs := true
  contents += new TabEntry {
    role := "presentation"
    active := true
    contents += new tag.A(href = "#") {
      contents += "Home"
    }
  }
  contents += new TabEntry {
    role := "presentation"
    contents += new tag.A(href = "#") {
      contents += "Profile"
    }
  }
  contents += new TabEntry {
    role := "presentation"
    contents += new tag.A(href = "#") {
      contents += "Messages"
    }
  }
}