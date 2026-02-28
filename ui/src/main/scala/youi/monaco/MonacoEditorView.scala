package youi.monaco

import youi.component.Component
import youi.dom
import monaco.{EditorOptions, IStandaloneCodeEditor, MinimapOptions, Monaco}
import reactify.{Channel, Var}

import scala.scalajs.js

class MonacoEditorView extends Component(dom.create.div) {
  val language: Var[String] = Var("scala")
  val theme: Var[String] = Var("vs-dark")
  val readOnly: Var[Boolean] = Var(false)
  val value: Var[String] = Var("")
  val fontSize: Var[Int] = Var(14)
  val minimap: Var[Boolean] = Var(true)
  val wordWrap: Var[String] = Var("off")

  val contentChanged: Channel[String] = Channel[String]

  private var editor: Option[IStandaloneCodeEditor] = None
  private var updating: Boolean = false

  def initialize(): Unit = {
    val opts = new EditorOptions {}
    opts.value = value()
    opts.language = language()
    opts.theme = theme()
    opts.readOnly = readOnly()
    opts.fontSize = fontSize()
    opts.wordWrap = wordWrap()
    opts.automaticLayout = true
    opts.scrollBeyondLastLine = false
    opts.tabSize = 2
    opts.minimap = {
      val m = new MinimapOptions {}
      m.enabled = minimap()
      m
    }

    val ed = Monaco.editor.create(element, opts)
    editor = Some(ed)

    ed.onDidChangeModelContent { (_: js.Any) =>
      if (!updating) {
        updating = true
        try {
          val v = ed.getValue()
          value @= v
          contentChanged @= v
        } finally {
          updating = false
        }
      }
    }

    value.attach { v =>
      if (!updating) {
        editor.foreach { ed =>
          updating = true
          try {
            ed.setValue(v)
          } finally {
            updating = false
          }
        }
      }
    }

    theme.attach { t =>
      Monaco.editor.setTheme(t)
    }

    readOnly.attach { ro =>
      editor.foreach { ed =>
        val opts = new EditorOptions {}
        opts.readOnly = ro
        ed.updateOptions(opts)
      }
    }

    fontSize.attach { fs =>
      editor.foreach { ed =>
        val opts = new EditorOptions {}
        opts.fontSize = fs
        ed.updateOptions(opts)
      }
    }

    minimap.attach { enabled =>
      editor.foreach { ed =>
        val opts = new EditorOptions {}
        opts.minimap = {
          val m = new MinimapOptions {}
          m.enabled = enabled
          m
        }
        ed.updateOptions(opts)
      }
    }

    wordWrap.attach { ww =>
      editor.foreach { ed =>
        val opts = new EditorOptions {}
        opts.wordWrap = ww
        ed.updateOptions(opts)
      }
    }
  }

  def focus(): Unit = editor.foreach(_.focus())

  def dispose(): Unit = editor.foreach { ed =>
    ed.dispose()
    editor = None
  }
}
