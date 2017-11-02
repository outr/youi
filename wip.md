# Work in Progress
## Tasks currently being worked on for the next release

* [X] Better debugging support
    * [X] On-going stats gathering `RenderStats` used by `Renderer`
    * [X] FPS-style display support with information about averages, counts, timings, etc.
* [ ] `NativeFont` to represent browser-rendered fonts
* [X] Better text rendering performance with `CachedFont`
* [X] Support for "reload on ratio change"
* [ ] Selectable Text in Canvas for copy/paste
* [ ] TextInput in Canvas for editable editable text
    * [ ] Editing features
    * [ ] Caret with customization support
    * [ ] Rich text segments
    * [ ] Multi-line support or explicit single-line support
* [ ] Stylized Components
    * [ ] Button
    * [ ] TextField
    * [ ] TextArea
    * [ ] Checkbox
    * [ ] RadioButton
    * [ ] ListSelection
* [ ] Update Components to only render when they need to (lazy rendering)
    * [ ] Better handling of `Renderer` to avoid unnecessary re-rendering
    * [ ] Disable rendering of offscreen or invisible components
    * [ ] Better reliability of `Video` playing
* [ ] Update `UIExamples` to render using canvas and look a lot nicer
* [ ] `RichText` to render stylized text
    * [ ] DSL for cleanly representing
    * [ ] JSON format for inline, loading, and exporting
* [ ] Better Server error logging (URL, request info, etc.)