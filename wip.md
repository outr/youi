# Work in Progress
## Tasks currently being worked on for the next release

* [X] Migration of `ui` module to be a cross-project instead of Scala.js only
* [X] Added RoundedRectangle to drawable functionality
* [X] Create `utilities` module for internal code generation and other external functionality
    * [X] Create `KeyBuilder` to support MDN standards generation of `Key` class from https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/key/Key_Values
* [X] Update `Key` to use `KeyboardEvent.key` as mapping for better compliance to latest spec
* [X] Replace direct use of `KeyboardEvent` in hypertext and component with `KeyEvent`
* [X] Layout support in AbstractContainer
    * [X] VerticalLayout
    * [X] GridLayout
* [X] Update dom Template to utilize Profig instead of limiting to environment variable for load path
* [X] New `spatial` package to do matrix and other spatial math
* [X] Remove uPickle and rely exclusively on Circe for pickling
* [X] Add better functionality to build FormDataContent
* [X] Support multipart form posting in youi-client
* [X] Update URL interpolator to only accept literals for proper compile-time validation
* [X] DataTransferManager to provide better and more consistent drag-and-drop and file chooser functionality with directory support
* [X] Update Color to be an AnyVal wrapper to use the memory footprint of a `Long`
* [X] `Selection` HTML feature for rectangular and individual selection
* [X] Upgrade to direct canvas functionality instead of using Pixi.js
* [X] Border support in Component
* [X] Padding support in Component
* [X] Scrolling Container support
    * [X] Mouse support
    * [X] Touch support
        * [ ] Utilize acceleration
* [X] AbstractContainer only draws children within viewable area for better performance
* [ ] ScrollBar
* [X] Support Client to Server basic logging for better information control from the server.
* [X] Upgrade to SBT 1.0
* [ ] Complete paint support
    * [X] Texture, Gradient, and Video support
    * [ ] ComponentPaint for better Image and Video integration