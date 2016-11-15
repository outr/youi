# youi
============

[![Build Status](https://travis-ci.org/outr/youi.svg?branch=master)](https://travis-ci.org/outr/youi)
[![Stories in Ready](https://badge.waffle.io/outr/youi.png?label=ready&title=Ready)](https://waffle.io/outr/youi)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/outr/youi)
[![Maven Central](https://img.shields.io/maven-central/v/io.youi/youi-core_2.11.svg)](https://maven-badges.herokuapp.com/maven-central/io.youi/youi-core_2.11)
[![Latest version](https://index.scala-lang.org/io.youi/youi/youi-core/latest.svg)](https://index.scala-lang.org/io.youi/youi/youi-core)

Next generation user interface and application development in Scala and Scala.js for web, mobile, and desktop.

### Status

At the moment we are currently migrating hyperscala (https://github.com/outr/hyperscala) and NextUI (https://github.com/outr/nextui)
into this new framework. If you need a production-ready framework please look at those for now.

### Features for 1.0

* [X] Scala JVM and JS support
* [X] URL implementation offering good parsing and flexibility
* [X] Server abstraction with HttpRequest and HttpResponse allowing for multiple implementations
* [X] HttpHandler prioritization and flow to allow handlers to build upon each other
* [X] IPv4 and IPv6 wrapper classes
* [X] TestServerImplementation for easy unit testing
* [ ] Full Cookie support
* [ ] Session support
* [ ] Proxying support and optional ProxyingSupport trait to be mixed into Server
* [ ] Communication implementation supporting client and server with JVM and JS support (see Hyperscala)
* [ ] Undertow implementation for Server (see Hyperscala)
* [ ] User Interface framework for Scala.js (see NextUI)
* [ ] Client / Server Page and Screen support (see Hyperscala)
* [ ] Ajax Request / Response framework for Server (JVM) and Client (JS) (see Hyperscala)
* [ ] Scala JVM and JS Content Modification Streams for any XML / HTML content (see Hyperscala)
