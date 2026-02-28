# youi-app

## Getting Started

App is the primary mechanism to utilize to create web, mobile, and desktop applications. This module builds upon many
other modules in YouI, but uses them as dependencies to simplify project setup.

### Introduction

This tutorial presumes you are using SBT and have a basic understanding of setting up a Scala project. Further, this
tutorial is based on the example project here: https://github.com/outr/youi-example

If you would rather skip the lengthy explanations and instructions and would rather just look at the source code, that's
that place to find it.

### Configuring SBT

To configure your project there are two ways this can be done:

1. Using the youi-plugin for SBT
2. Defining all of the dependencies manually

For the sake of brevity we'll focus on #1 but it's fairly straight-forward to accomplish the same with #2. YouI doesn't
do any magic behind the scenes, the youi-plugin is simply designed to make project setup and utilization less boilerplate
heavy and add some useful features.

#### Adding the plugin

In order to add the plugin you simply need add the following line to your `project/plugins.sbt` file:

```scala
addSbtPlugin("com.outr" % "youi-plugin" % "1.0.2")
```

This will also add Scala.js and other necessary dependencies.

#### Defining YouI settings in SBT

The plugin settings that can be defined are as follows:

* youiVersion: Required to be set to the version of YouI that should be used.
* youiServer: Optionally defined to clarify what server implementation to use. As of this writing it defaults to the one and only implementation "undertow".
* youiInclude: Optionally can be flagged to false to exclude YouI dependencies from being included automatically. This is useful if you want to use this plugin's features but are not using YouI in your project.

For the purposes of this tutorial we need only specify the first setting.

#### Creating a CrossApplication

In the Scala.js SBT plugin it provides a `CrossProject` that allows a `jvm`, `js`, and `shared` source folders. However,
it was not intended to connect these two projects in any way. In YouI the compiled Scala.js must be served by the JVM
server, so the youi-plugin provides a similar `CrossApplication` to do that:

```scala
lazy val example = crossApplication.in(file("."))
  .settings(
    youiVersion := latestVersion    // "latestVersion" should be replaced with the latest released version of YouI
  )

lazy val exampleJS = example.js
lazy val exampleJVM = example.jvm
```

If you are familiar with Scala.js then this should look very similar to `CrossProject` to you.

### Creating Shared Sources

Because of the heavy interaction common in powerful applications we want to define shared source that defines common
interfaces that can be leveraged between client and server.

#### Communication interface

The first thing we want to define is the `Communication` interface:

```scala
package youi.example

import youi.communication.{Communication, client, server}

import scala.concurrent.Future

/**
  * Defines the interface shared between client and server. Methods defined with @client must be implemented in the
  * Scala.js code and @server must be defined on the JVM implementation. Methods are ignored if they don't return
  * `Future`.
  */
trait ExampleCommunication extends Communication {
  /**
    * Allows the server to display an alert in the browser.
    */
  @client def alert(message: String): Future[Unit]

  /**
    * Allows the client to ask the server to reverse a String. A relatively pointless operation for the server to handle,
    * but a simplified example of communication from client -> server -> client.
    */
  @server def reverse(value: String): Future[String]
}
```

The `Communication` interface defines anything shared between client and server relating to communication. There are
lots of things that could be done here, but in the above example we're simply exploring the most use-case: methods.

These methods are defined with an annotation defining where they will be implemented. Also note, all methods must return
a `Future` since the very nature of client / server communication is asynchronous and this allows the interface to
remain perfectly type-safe.

#### Application interface

All YouI applications need an application interface which is the launching point on both client and server:

```scala
package youi.example

import youi.app.{CommunicationManager, YouIApplication}

/**
  * Shared definition of the application. This is used to define the interface between client and server. Primarily
  * useful for defining communication end-points.
  */
trait ExampleApplication extends YouIApplication {
  /**
    * Defines a CommunicationManager for ExampleCommunication. This manages connectivity on both client and server to
    * allow communications from client -> server and server -> client via WebSocket.
    */
  val communication: CommunicationManager[ExampleCommunication] = connectivity.communication[ExampleCommunication]
}
``` 

The only major thing we did here is define a trait that exposes a `CommunicationManager` for `ExampleCommunication` (the
`Communication` trait we defined above). This will give us access to it from the client and server in our application
later.

### Server Implementation

Now that we've defined our shared interfaces we need to define the server implementation. This will handle serving up
resources and communication to and from the server.

#### Communication Implementation

```scala
package youi.example
import scala.concurrent.Future

/**
  * The server implementation of ExampleCommunication. Notice only the methods with @server annotation must be
  * implemented in this trait.
  */
trait ServerExampleCommunication extends ExampleCommunication {
  override def reverse(value: String): Future[String] = Future.successful(value.reverse)
}
```

Notice that we needed only define a new trait that extends `ExampleCommunication` and implements all `@server` annotated
methods. Granted the use-case for a server-driven `reverse` is somewhat pointless, but it gives us a simple example of
how to invoke more complex operations on the server driven by calls from the client.

#### Application Implementation

```scala
package youi.example

import youi.app.ServerApplication
import youi.http._

/**
  * The server application implementation. Notice that it requires two mix-ins:
  *   - ServerApplication: required for "servery things"
  *   - ExampleApplication: defines this application as providing an implementation of ExampleApplication shared trait.
  *
  * The only necessity to actually be done in this code here is to define handlers to set up the content. A main method
  * is already defined to allow the server to start.
  */
object ServerExampleApplication extends ServerApplication with ExampleApplication {
  /**
    * Defines "/" and "/index.html" as "pages" that loads a basic HTML page along with the Scala.js resource loaded.
    */
  handler.matcher(
    combined.any(
      path.exact("/"),
      path.exact("/index.html")
    )
  ).page()
}
```

This is the launching point for our server to start and apart from mixing in `ServerApplication` and `ExampleApplication`
we need only configure paths that will serve resources. In this case we're only going to serve HTML pages from "/" and
"index.html".

### Client Implementation

With our server implementation defined we must similarly define the implementation of client functionality in order to
complete our application.

#### Communication Implementation

```scala
package youi.example

import scala.concurrent.Future
import org.scalajs.dom._

/**
  * The client implementation of ExampleCommunication. Notice only the methods with @client annotation must be
  * implemented in this trait.
  */
trait ClientExampleCommunication extends ExampleCommunication {
  override def alert(message: String): Future[Unit] = Future.successful {
    window.alert(message)
  }
}
```

Similar to `ServerExampleCommunication` we need only define the methods in our trait that are annotated with `@client`.

#### Application Implementation

```scala
package youi.example

import youi._
import youi.app.ClientApplication

import scala.scalajs.js.annotation.JSExportTopLevel

/**
  * The client application implementation. This requires two mix-ins:
  *   - ClientApplication: for client-side related functionality and initializing the browser functionality
  *   - ExampleApplication: defines this application as providing an implementation of ExampleApplication shared trait.
  */
object ClientExampleApplication extends ClientApplication with ExampleApplication {
  /**
    * Makes sure the HelloScreen is initialized so it can be checked for URL matching.
    */
  val hello = HelloScreen

  /**
    * This is the main entry-point into the Scala.js application. We export it as a top-level function in JavaScript to
    * allow it to be executed as `application()`. The only action we need to take in this method is to initialize the UI.
    */
  @JSExportTopLevel("application")
  def main(): Unit = ui.init()
}
```

We'll address the `HelloScreen` in a moment, but for the moment simply notice we define an implementation mixing in both
`ClientApplication` and `ExampleApplication`. Lastly, we need initialize the user-interface and define an entry-point
into our application. The `main` method exports to the `application` function in JavaScript and when invoked simply need
call `ui.init()` to start up our application properly.

#### Hello Screen

Screens provide a mechanism in YouI easily define single-page applications with modularized functionality along with
many more advanced features out of the scope of this tutorial. For this tutorial we will simply define a Screen to show
how it can be done and to show something in the browser with our resulting application:

```scala
package youi.example

import youi._
import youi.app.screen.{UIScreen, URLActivation}
import youi.component.BasicText
import youi.net.{URL, URLMatcher}

/**
  * HelloScreen is an example of a simple screen leveraging the canvas-based UI functionality. Though there are many
  * ways to handle content in YouI, the canvas-based functionality is the most powerful, and ultimately the preferred
  * mechanism if possible. We can mix-in UIScreen to simplify using canvas-based functionality and setting up the
  * default `Renderer`. Additionally, we mix-in URLActivation to match to "/" and "/index.html" for this Screen.
  */
object HelloScreen extends UIScreen with URLActivation {
  /**
    * This defines what URLs will match to this screen. Any matching URL will activate this screen automatically.
    *
    * Note: Screens have single-page application functionality built-in, although they can be used in more classic
    * scenarios as well.
    */
  override lazy val matcher: URLMatcher = http.combined.any(
    http.path.exact("/"),
    http.path.exact("/index.html")
  )

  /**
    * Handles updating the URL if the URL should need to be re-written during activation.
    */
  override def updateURL(current: URL): Option[HistoryStateChange] = None

  /**
    * This method is invoked after the Renderer and configured and the screen loaded in order to do the final
    * setup of the screen.
    */
  override def createUI(): Unit = {
    // Create a basic text element to render on the screen
    val text = new BasicText {
      value := "Hello, World!"
      font.size := 48.0
      fill := Color.DarkBlue

      // Because we are using Reactify, we can define complex functionality in our assignments.
      // However, in this case this will simply keep this text centered in the renderer no matter whether
      // the text changes, the screen resizes, the renderer moves, etc.
      position.center := renderer.position.center
      position.middle := renderer.position.middle
    }
    // We must add it to the container for it to display on this screen
    container.children += text
  }
}
```

This will use canvas to draw to the screen in the browser and simply say "Hello, World!".

### Building and Running

Now that we have a complete application we need to build and run it. To accomplish this we'll be using SBT.

Start up the SBT console in the root of your project ("sbt") and run the following command to build the Scala.js, JVM,
and run the application:

`;exampleJS/fastOptJS;exampleJVM/run`

When you are ready to terminate execution simply hit `CTRL + C` to cancel the running process (the youi-plugin enables
cancelable and forking in run).

### Wrap up

This is a very introductory tutorial on the use of writing applications in YouI. However, YouI is a vast and powerful
framework with much more to see. It is recommended to investigate the example project to see many more uses of YouI:
https://github.com/outr/youi/tree/master/example

Finally, as stated at the beginning of this tutorial, all source code referenced is available in the youi-example
project: https://github.com/outr/youi-example
