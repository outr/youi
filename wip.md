# Work in Progress
## Tasks currently being worked on for the next release

* [X] Major cleanup and simplification of Component internals
    * [X] Migration back to `ui` module instead of `canvas`
    * [X] Inherent support for `devicePixelRatio` and `backingStoreRatio` to properly render scaled and HiDPI
* [X] `Drawable` core infrastructure
    * [X] `Font`
        * [X] Trait for multiple implementations
        * [X] OpenTypeFont
          * [X] Update to latest version of OpenType.js
          * [X] Fixes for consistent font height
    * [X] `Text`
        * [X] Pre-defined and sized paths for text from `Font`
        * [X] Extend `Drawable` to allow explicit drawing
    * [X] `Image`
        * [X] Extend `Drawable` to allow explicit drawing
        * [X] `AnimatedImage` to support multi-framed images sequenced with delays between frames.
    * [X] `Video`
        * [X] Extend `Drawable` to allow explicit drawing
        * [X] Controls and information
    * [X] `Drawable` for most simplistic drawing functionality
    * [X] `Group` for simplistic child drawing functionality
    * [X] `Transformable` for mix-in of transformation matrix
    * [X] `Cacheable` for mix-in of pre-rendering to `canvas` instance
    * [X] `Clipped` for clipping the drawing area
* [X] `Component` re-design
    * [X] `ImageView` component
    * [X] `TextView` component
    * [X] `VideoView` component
    * [X] Cleanup of `Context` to better utilize `Path.fix`
    * [X] Update Path to utilize `Context`
    * [X] Improve `Path` rendering performance
        * [X] General optimizations
        * [X] Support `Path2D`
    * [X] Cleanup and re-working on `component` package and build on `Drawable` functionality to reduce complexity
* [X] Move `template` module into its own project
* [X] `Activate` support for checked and unchecked to work with checkboxes easily