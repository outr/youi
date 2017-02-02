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

At the moment we are currently migrating hyperscala (https://github.com/outr/hyperscala) and NextUI (https://github.com/outr/nextui)
into this new framework. If you need a production-ready framework please look at those for now.

## Modules

* [core](core) - core features generally useful for web and HTTP (Scala and Scala.js)
* [communicate](communicate) - communication framework to provide type-safe communication between a client / server (Scala and Scala.js)
* [dom](dom) - features and functionality related to working with the browser's DOM (Scala.js)
* [server](server) - base functionality for a web server (Scala)
* [server-undertow](serverUndertow) - implementation of [server](server) using [Undertow](http://undertow.io/) (Scala)
* [ui](ui) - functionality for user-interface creation and management (Scala.js)

## Features for 1.0.0 (Future)

* [ ] Integration of basic HTML components for UI (Scala.js)
* [ ] Integration of Pixi.js for more advanced UI functionality (Scala.js)
* [ ] Existing HTML, CSS, and JavaScript optimization, compression, and obfuscation for production use
* [ ] Image optimizer for production use supporting compile-time and run-time optimization
* [ ] HTML caching in Local Storage for offline and faster cached loading
* [ ] Convenience functionality for deploying native applications
    * [ ] Windows
    * [ ] Mac
    * [ ] Linux
    * [ ] iOS
    * [ ] Android

## Features for 0.2.0 (In-Progress)

* [X] Complete SSL support (binding and proxying)
* [X] Ajax Request / Response framework
* [X] XML Content Modification Streams for any XML / HTML content
* [X] Compile-time HTML injection into Scala.js from existing template
* [X] Shared Var - shared state modifiable on client or server
* [X] History Management convenience functionality (Scala.js)
* [ ] YouIApplication to simplify client/server functionality
    * [X] General WebSocket connectivity support
    * [X] Communication support
    * [X] Streaming convenience functionality
    * [X] Pages functionality (JVM)
    * [ ] Filter support on HttpHandlers
        * [ ] Security convenience functionality
    * [X] Screens functionality (Scala.js)

## Features for 0.1.0 (Released 2016.12.22)

* [X] Scala JVM and JS support
* [X] URL implementation offering good parsing and flexibility
* [X] URL interpolation at compile-time
* [X] Server abstraction with HttpRequest and HttpResponse allowing for multiple implementations
* [X] HttpHandler prioritization and flow to allow handlers to build upon each other
* [X] IPv4 and IPv6 wrapper classes
* [X] TestServerImplementation for easy unit testing
* [X] Full Cookie support
* [X] Undertow implementation for Server
* [X] Wrapper for standard ContentTypes
* [X] WebSocket support through abstraction
* [X] Proxying support and optional ProxyingSupport trait to be mixed into Server
* [X] Session support
* [X] Communication implementation supporting client and server with JVM and JS support
