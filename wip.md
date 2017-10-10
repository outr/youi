# Work in Progress
## Tasks currently being worked on for the next release

* [ ] Major cleanup and simplification of Component internals
    * [X] Migration back to `ui` module instead of `canvas`
    * [X] Inherent support for `devicePixelRatio` and `backingStoreRatio` to properly render scaled and HiDPI
    * [X] `Font`
        * [X] Trait for multiple implementations
        * [X] OpenTypeFont
          * [X] Update to latest version of OpenType.js
          * [X] Fixes for consistent font height
        * [ ] NativeFont
    * [X] `Text`
        * [X] Pre-defined and sized paths for text from `Font`
        * [X] Extend `Drawable` to allow explicit drawing
        * [ ] Support `WrapMode` for `None`, `Clip`, `Ellipsis`, `Hyphenate`, and `Word`.
    * [ ] `Image`
        * [X] Extend `Drawable` to allow explicit drawing
        * [ ] Separate asynchronous and synchronous versions (utilize `Cacheable`)
    * [X] `Video`
        * [X] Extend `Drawable` to allow explicit drawing
        * [X] Controls and information
    * [X] `Drawable` for most simplistic drawing functionality
    * [X] `Group` for simplistic child drawing functionality
    * [X] `Transformable` for mix-in of transformation matrix
    * [X] `Cacheable` for mix-in of pre-rendering to `canvas` instance
    * [ ] `ClippedDrawable` for clipping the drawing area
    * [ ] `AsynchronousDrawable` for asynchronous drawing mix-in (extends `Cacheable`)
    * [ ] `InteractiveDrawable` mix-in for hit testing (extends `Transformable`)
    * [ ] `ImageView` component
    * [ ] `TextView` component
    * [ ] `VideoView` component
    * [X] Cleanup of `Context` to better utilize `Path.fix`
    * [X] Update Path to utilize `Context`
    * [X] Improve `Path` rendering performance
        * [X] General optimizations
        * [X] Support `Path2D`
* [ ] Move `template` module into its own project