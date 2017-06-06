# youi

[![Build Status](https://travis-ci.org/outr/youi.svg?branch=master)](https://travis-ci.org/outr/youi)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c0425ea823824cd7ab60659e8b9542dc)](https://www.codacy.com/app/matthicks/youi?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=outr/youi&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/c0425ea823824cd7ab60659e8b9542dc)](https://www.codacy.com/app/matthicks/youi?utm_source=github.com&utm_medium=referral&utm_content=outr/youi&utm_campaign=Badge_Coverage)
[![Stories in Ready](https://badge.waffle.io/outr/youi.png?label=ready&title=Ready)](https://waffle.io/outr/youi)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/outr/youi)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.youi/youi-core_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.youi/youi-core_2.12)
[![Latest version](https://index.scala-lang.org/outr/youi/youi-core/latest.svg)](https://index.scala-lang.org/outr/youi)

Next generation user interface and application development in Scala and Scala.js for web, mobile, and desktop.

## Status

We have completed migration from Hyperscala (http://github.com/outr/hyperscala) and the base functionality of NextUI
(https://github.com/outr/nextui) and we've added a lot of new functionality. We're currently being used in production
environments but are still in heavy development.

## Modules

YouI is divided into modules of functionality to minimize the dependencies required for your specific usage:

* [app](app) - unification of client and server to write complete applications (Scala and Scala.js)
* [client](client) - HTTP client for asynchronous request/response and restful support (Scala)
* [communication](communication) - communication framework to provide type-safe communication between a client / server (Scala and Scala.js)
* [core](core) - core features generally useful for web and HTTP (Scala and Scala.js)
* [dom](dom) - features and functionality related to working with the browser's DOM (Scala.js)
* [optimizer](optimizer) - HTML, JavaScript, and Image optimizations to reduce extra overhead from your application
* [server](server) - base functionality for a web server (Scala)
* [server-undertow](serverUndertow) - implementation of [server](server) using [Undertow](http://undertow.io/) (Scala)
* [stream](stream) - streaming functionality for on-the-fly processing and modification of any XML or HTML content (Scala)
* [template](template) - features for creating and managing templates for use in applications (Scala and Scala.js)
* [ui](ui) - functionality for user-interface creation and management (Scala.js)

## Features for 1.0.0 (Future)

* [ ] Optimization module
    * [ ] CSS merging, cleanup, minification, and optimization support.
    * [ ] HTML minification and optimization support.
    * [ ] Image validator and optimizer for production deployment.
* [ ] HTML caching in Local Storage for offline and faster cached loading
* [ ] Convenience functionality for deploying native applications
    * [ ] Windows
    * [ ] Mac
    * [ ] Linux
    * [ ] iOS
    * [ ] Android
    
## Features for 0.8.0 (Future)

* [ ] Selectable Text in Canvas for copy/paste
* [ ] TextInput in Canvas for editable editable text
    * [ ] Editing features
    * [ ] Caret with customization support
    * [ ] Rich text segments
    * [ ] Multi-line support or explicit single-line support
* [ ] Scrollbar support

## Features for 0.7.0 (Future)

* [ ] Serializable / Deserializable JSON structure for UI
* [ ] PSD import tool
* [ ] SVG import tool
* [ ] AI import tool

## Features for 0.6.0 (Future)

* [ ] Major cleanup and simplification of writing a web application
    * [ ] Provide simple classes to extend for client, server, and shared
    * [ ] SBT plugin to simplify build setup (especially management and sharing of generated JS)
    * [ ] Ability to create an app with only three classes (client, server, and shared)
    * [ ] Create a template project (maybe Giter8)
    
## Features for 0.5.0 (Future)

* [ ] Snap Layout Management (ex. `snap(img1).left.to(img2).right`)
* [ ] Layout managers
    * [ ] Flow layout
    * [ ] Grid layout
    * [ ] Form layout
    * [ ] Stack layout
* [ ] Border support in DrawableComponent
* [ ] VideoComponent
* [ ] VideoPaint
    
## Features for 0.4.0 (In-Progress)

* [ ] UI Features
    * [X] Update Pixi.js facade to use JSDeps
    * [X] On-demand rendering capabilities for exceptional performance
    * [X] Workflow rendering tied to elements to allow pausing when invisible
    * [X] CanvasComponent for specialized drawing using HTML5 Canvas
    * [X] DrawableComponent to simplify and maintain stateful drawing backed by CanvasComponent
        * [X] Text integration using https://github.com/nodebox/opentype.js
        * [X] Complete SVG rendering support (Canvg)
    * [X] Complete Paint functionality
        * [X] Fill integration
        * [X] Stroke wrapper
        * [X] Gradients
        * [X] Texture
        * [X] Color
    * [X] HTMLComponent to render HTML components on top of Canvas with the same transformations
    * [X] RectangularSelection tool
        * [X] Move and Resize Support
        * [X] Minimum and Maximum constraints
        * [X] Aspect ratio constraints
    * [X] Texture clipping support
    * [X] Scale-9 Support
    * [X] AbstractContainer to better support custom components and more explicit containers
    * [ ] Hierarchical routing support for server handlers
    * [ ] ImageEditor
        * [ ] Selection area
        * [ ] Cropping
        * [ ] Scaling
        * [ ] Rotation
        * [ ] Upload local file
        * [ ] Download result
    
## Features for 0.3.0 (Released 2017.04.21)

* [X] Optimization: JavaScript merging, cleanup, minification, obfuscation, and optimization support.
* [X] Simplify connectivity / communication functionality
* [X] Hierarchical `update` calls to allow scoping of animations, graphical layout, and other functionality.
* [X] Complete re-write of JavaScript error logging and support.
* [X] Integration of basic HTML components for UI (Scala.js)
    * [X] Button
    * [X] Container
    * [X] ImageView
    * [X] Label
    * [X] TextInput
    * [X] TextArea
* [X] Integration of Pixi.js for more advanced UI functionality (Scala.js)
    * [X] Canvas / Integration with HTML
    * [X] Events
    * [X] Container
    * [X] Image
    * [X] Text
    * [X] Shapes
* [X] Animation and workflow functionality
    * [X] Task
    * [X] Action
    * [X] Temporal
    * [X] Parallel and Sequential
    * [X] Sleep
    * [X] Easings integration
    * [X] DSL
* [X] Layout Managers
    * [X] Core Support
    * [X] BoxLayout
    * [X] GridLayout
    * [X] FlowLayout

## Features for 0.2.0 (Released 2017.02.27)

* [X] Complete SSL support (binding and proxying)
* [X] Ajax Request / Response framework
* [X] XML Content Modification Streams for any XML / HTML content
* [X] Compile-time HTML injection into Scala.js from existing template
* [X] Shared Var - shared state modifiable on client or server
* [X] History Management convenience functionality (Scala.js)
* [X] YouIApplication to simplify client/server functionality
    * [X] General WebSocket connectivity support
    * [X] Communication support
    * [X] Streaming convenience functionality
    * [X] Pages functionality (JVM)
    * [X] Filter support on HttpHandlers
    * [X] Screens functionality (Scala.js)
    * [X] Receive JavaScript errors on server for logging
    * [X] Client auto reload on server stop / restart
* [X] Template features
    * [X] Includes
    * [X] Server for faster testing
    * [X] Real-time updates in browser
    * [X] LESS and SASS support
* [X] HTTP Client
    * [X] Basic HTTP request / response using abstraction
    * [X] Fully and properly asynchronous and non-blocking
    * [X] Restful support for case class serialization and deserialization

## Features for 0.1.0 (Released 2016.12.22)

* [X] Scala JVM and JS support
* [X] URL implementation offering good parsing and flexibility
    * [X] compile-time interpolation
* [X] Server abstraction with HttpRequest and HttpResponse allowing for multiple implementations
    * [X] Undertow implementation
* [X] HttpHandler prioritization and flow to allow handlers to build upon each other
* [X] IPv4 and IPv6 wrapper classes
* [X] TestServerImplementation for easy unit testing
* [X] Full Cookie support
* [X] Wrapper for standard ContentTypes
* [X] WebSocket support through abstraction
* [X] Proxying support and optional ProxyingSupport trait to be mixed into Server
* [X] Session support
* [X] Communication implementation supporting client and server with JVM and JS support
