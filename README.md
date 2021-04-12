# youi

[![Build Status](https://travis-ci.com/outr/youi.svg?branch=master)](https://travis-ci.com/outr/youi)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c0425ea823824cd7ab60659e8b9542dc)](https://www.codacy.com/app/matthicks/youi?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=outr/youi&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/c0425ea823824cd7ab60659e8b9542dc)](https://www.codacy.com/app/matthicks/youi?utm_source=github.com&utm_medium=referral&utm_content=outr/youi&utm_campaign=Badge_Coverage)
[![Stories in Ready](https://badge.waffle.io/outr/youi.png?label=ready&title=Ready)](https://waffle.io/outr/youi)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/outr/youi)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.youi/youi-core_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.youi/youi-core_2.12)
[![Latest version](https://index.scala-lang.org/outr/youi/youi-core/latest.svg)](https://index.scala-lang.org/outr/youi)
[![Javadocs](https://javadoc.io/badge/io.youi/youi-core_2.12.svg)](https://javadoc.io/doc/io.youi/youi-core_2.12)

Next generation user interface and application development in Scala and Scala.js for web, mobile, and desktop.

## Status

There is heavy development going on toward 1.0, but YouI releases are stable and used in several production systems.

## Modules

YouI is divided into modules of functionality to minimize the dependencies required for your specific usage:

* [app](app) - unification of client and server to write complete applications (Scala and Scala.js)
* [canvas](canvas) - User Interface implementation on HTML Canvas for greater power and flexibility than HTML provides
* [client](client) - HTTP client for asynchronous request/response and restful support (Scala)
* [communication](communication) - communication framework to provide type-safe communication between a client / server (Scala and Scala.js)
* [core](core) - core features generally useful for web and HTTP (Scala and Scala.js)
* [dom](dom) - features and functionality related to working with the browser's DOM (Scala.js)
* [example](example) - example and test functionality for applications using youi
* [hypertext](hypertext) - extension functionality for working with HTML in a more powerful way
* [macros](macros) - internal macros for various internal uses
* [optimizer](optimizer) - HTML, JavaScript, and Image optimizations to reduce extra overhead from your application
* [server](server) - base functionality for a web server (Scala)
* [server-undertow](serverUndertow) - implementation of [server](server) using [Undertow](http://undertow.io/) (Scala)
* [spatial](spatial) - Spatial and math related functionality for Matrix and other operations
* [stream](stream) - streaming functionality for on-the-fly processing and modification of any XML or HTML content (Scala)
* [utilities](utilities) - internal utilities to support the infrastructure of youi

## External Projects

Though this project has several sub-modules, where possible, external projects exist to add optional functionality.

* [youi-plugin](https://github.com/outr/youi-plugin) - An SBT plugin to simplify setting up your youi project.
* [youi-template](https://github.com/outr/youi-template) - Stand-alone server instance to help designers work with HTML templates locally and support integration for developers.
* [youi-designer](https://github.com/outr/youi-designer) - User interface designer tool to create, edit, import, export, and generate user interfaces for youi.
* [youi-example](https://github.com/outr/youi-example) - An example project showing the basic usage of youi.

## Tutorials

Though YouI provides many modules to accomplish many things, the primary goal of YouI is application development for
web, mobile, and desktop. Take a look at the [app](app) module for a great getting started tutorial.

## Examples

More examples are located in the `example` directory. Run them with `sbt` 

    exampleJS/fastOptJS 
    exampleJVM/reStart 

then load http://localhost:8080/ui-examples.html or search with `def path:` for URLs.

## Roadmap

https://github.com/outr/youi/wiki/Roadmap