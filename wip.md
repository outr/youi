# Work in Progress
## Tasks currently being worked on for the next release

* [ ] Major cleanup and simplification of Component internals
    * [X] Migration back to `ui` module instead of `canvas`
    * [X] Inherent support for `devicePixelRatio` and `backingStoreRatio` to properly render scaled and HiDPI
    * [ ] `Font`
        * [ ] Trait for multiple implementations
        * [ ] OpenTypeFont
          * [ ] Update to latest version of OpenType.js
          * [ ] Fixes for consistent font height
        * [ ] NativeFont
    * [ ] `Text`
        * [ ] Pre-defined and sized paths for text from `Font`
        * [ ] Extend `Drawable` to allow explicit drawing
    * [ ] `Image`
        * [ ] Extend `Drawable` to allow explicit drawing
    * [X] `Drawable` for most simplistic drawing functionality
    * [X] `Group` for simplistic child drawing functionality
    * [ ] `Transformable` for mix-in of transformation matrix
    * [ ] `Cacheable` for mix-in of pre-rendering to `canvas` instance
    * [ ] `ClippedDrawable` for clipping the drawing area
    * [ ] `AsynchronousDrawable` for asynchronous drawing mix-in (extends `Cacheable`)
    * [ ] `InteractiveDrawable` mix-in for hit testing (extends `Transformable`)
    * [ ] `ImageView` component
    * [ ] `TextView` component
    * [ ] Cleanup of `Context` to better utilize `Path.fix`
    * [ ] Update Path to utilize `Context`
* [ ] Selectable Text in Canvas for copy/paste
* [ ] TextInput in Canvas for editable editable text
    * [ ] Editing features
    * [ ] Caret with customization support
    * [ ] Rich text segments
    * [ ] Multi-line support or explicit single-line support
* [ ] Move `template` module into its own project
* [ ] Stylized Components
    * [ ] Button
    * [ ] TextField
    * [ ] TextArea
    * [ ] Checkbox
    * [ ] RadioButton
    * [ ] ListSelection
* [ ] Update Components to only render when they need to (lazy rendering)
    * [ ] Disable rendering of offscreen or invisible components