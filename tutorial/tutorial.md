# Hyperscala Tutorial

This tutorial is based on two blog articles and the [Hyperscala](http://www.hyperscala.org) 
sources.

- [Getting Started][1]
- [An Introduction to Hyperscala][2]

## What is it?

Hyperscala is a Scala web framework providing type-safe HTML, CSS, SVG, and JavaScript.  It is 
designed to eliminate the need for and confusion created by special UI templates.  Presentation 
layer content can be rendered completely from Scala sources or read dynamically from files.  

It provides a mechanism to deliver re-usable content in a simple, rich, and modular way. Hyperscala does 
these things without requiring proprietary configuration files and without making decisions for you.

## Quick Start

The hyperscala sources contain numerous examples, such as:  

- [Hyperscala examples directory](https://github.com/darkfrog26/hyperscala/tree/master/examples/src/main/scala/org/hyperscala/examples "Hyperscala examples directory shown in github")
  These examples are hosted online [here](http://hyperscala.com/examples.html "Hyperscala examples directory hosted online")

- [Hello example](#helloexample)

### [Hello example](id:helloexample)

The following is based on the [hello example from the hyperscala sources](https://github.com/darkfrog26/hyperscala/tree/master/hello "A simple hyperscala hello example shown in github") using 
hyperscala version `0.9.3-SNAPSHOT`.  Please see [snapshots](snapshots.md) for more detail about using snapshot versions. 
 

#### Create a simple scala project manually or use a [giter8][3] template.

Manually create a Scala, `sbt` project, or if you use [giter8][3] you could type the following at the command line and answer the prompts. 

    $ g8 darkfrog26/hyperscala.g8

Here are a couple of other templates. 

- [mmynsted/hyperscala.g8](https://github.com/mmynsted/hyperscala.g8)

- [sebnozzi/hyperscala.g8](https://github.com/sebnozzi/hyperscala.g8)

#### Create a page to host.

```scala
import org.hyperscala.web.Webpage
import org.hyperscala.html._

class HelloPage extends Webpage(HelloSite) {
  title := "Hello world page"
  body.contents += new tag.H1(content = "Hello, World!")
}
```

This sets the title, and adds a greeting in an `H1` tag to a page in the `HelloSite`, 
hyperscala site.

#### Create the site

This example is hosted, in-place using a `Jetty` container, thus a `webapps/WEB-INF/web.xml`, deployment descriptor, is not needed.  Create something like the following.

```scala
import org.hyperscala.web.{StaticWebsite, BasicWebsite}
import com.outr.net.http.jetty.JettyApplication
import com.outr.net.http.session.MapSession

object HelloSite extends BasicWebsite with StaticWebsite[MapSession] with JettyApplication {
  def index = new HelloPage
}
```

This creates a site (`HelloSite`) that will take requests at `index.html`, and the root of the site 
as expected, and reply with the page with created above.  If you add `def foo = new HelloPage` then a `HelloPage` will answer at `foo.html`...

`BasicWebsite` extends [com.outr.net.http.WebApplication](https://github.com/darkfrog26/outrnet/blob/master/core/src/main/scala/com/outr/net/http/WebApplication.scala).
The `outr.net` library is found [here](https://github.com/darkfrog26/outrnet).

#### Try it

Run with from sbt with the following.

    $ sbt run

## Dynamic Content

The following example is based on 
[DynamicContentExample](https://github.com/darkfrog26/hyperscala/blob/master/examples/src/main/scala/org/hyperscala/examples/basic/DynamicContentExample.scala 
"DynamicContentExample at github.com").

First will we create a file called `dynamic.html` in our `resources` directory.  It will look like this.

    <div>
        <b>Name:</b> <input name="name" id="i1"/><br/>
        <b>Age:</b> <input name="age" id="i2"/><br/>
        <button id="b1">Submit</button>
    </div>

We are only going to take parts of this file so we do not care if the file is valid HTML or that fields exist inside a `form`, etc.  Next we 
will create a page to demonstrate how content can be read from a resource file, manipulated, and delivered.  Create something like 
the following.  The comments explain what each part is doing. 

```scala
import org.hyperscala.html._
import org.hyperscala.web.Webpage
import org.powerscala.property.Property

import org.hyperscala.ui.binder._
import org.hyperscala.ui.dynamic.{DynamicContent, DynamicString}
import org.hyperscala.realtime.RealtimeEvent

class DynamicContentExample extends Webpage(HelloSite) {
  title := "Dynamic Content Example"
  body.contents += new tag.P { contents +=
    "Content can be extracted and manipulated before hyperscala delivers a response.  "
  }
  body.contents += new SimpleDynamicForm

  class SimpleDynamicForm extends DynamicContent("DynamicContentExample") {

    /* Load dynamic.html to dynamicString
     * Implementing the dynamicString method from DynamicContent trait
     * with a lazy val because we need not load this more than once.
     */
    lazy val dynamicString = DynamicString.url("dynamic.html", getClass.getClassLoader.getResource("dynamic.html"))

    //Person
    case class Person(name: String, age: Int)

    //Provide a default value for the Person property
    val person = Property[Person](default = Some(Person("John Doe", 123)))

    //Listen for changes
    person.change.on {
      //Lets write to the console when we see the person property change.
      case evt => println("Person changed from %s to %s".format(evt.oldValue, evt.newValue))
    }

    //bind form inputs by id to fields in person
    val nameInput = bind[tag.Input, String]("i1", person, "name")
    val ageInput  = bind[tag.Input, Int]("i2", person, "age")

    //Listen for button clicks on button with id "b1"
    val button = load[tag.Button]("b1")
    button.clickEvent := RealtimeEvent()
    button.clickEvent.on {
      //Replace person with a one with new values on button click.
      case evt => person := Person("Test User", 987)
    }

    /* Manipulate the button so it reads "Do Something"
     * It originally looked like this <button id="b1">Submit</button>
     */
    button.contents.replaceWith("Do Something")
  }
}
```

Next we include this in our site by adding the following to the `HelloSite.scala` file.
```scala
def dynamicContentExample = new DynamicContentExample
```

Now `sbt run` your project and test this by browsing to `http://localhost:8080/dynamicContentExample.html`.   

## Modules

The following is a simple module to provide a particular version of `jQuery` to a page.  First put a jQuery file named 
`jquery-1.8.2.min.js` in a `resources` directory.

Example:  
`src/main/resources/jquery-1.8.2.min.js`

Then create a new module, such as `jquery182.scala`.  It could look like the following.


```scala
import org.hyperscala.html.tag
import org.hyperscala.web.{Website,Webpage}
import org.powerscala.Version
import org.hyperscala.module._

object jQuery182 extends Module {

  import com.outr.net.http.session.Session

  def name = "jQuery182"
  def version = Version(1,8,2)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/jquery-1.8.2.js", "jquery-1.8.2.min.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jquery-1.8.2.js")
  }

}
```

The net of the code above is to supply the module with a name and version, register the `jQuery` script resource when the 
site initializes the module, then add the given `Script` tag with the page is loaded. 

To actually use the module, you would alter your page to include `require(jQuery182)`.  It might look something like this.  

```scala
import org.hyperscala.web.Webpage
import org.hyperscala.html._

class HelloPage extends Webpage(HelloSite) {
  require(jQuery182)
  title := "Hello world page"
  body.contents += new tag.H1(content = "Hello, ")
  body.contents += new tag.H2(content = "World!")
}
```

When you try the page again, you can view source and see that your `jQuery182` resource was included in the `HEAD`. 

    <head>
      <title>Hello world page</title>
      <meta name="generator" content="Hyperscala"/>
      <meta charset="UTF-8"/>
      <script type="text/javascript" src="/js/jquery-1.8.2.js"></script>
    </head>

Modules are designed such that even if they are `require`ed more than once in a page, they will still be injected exactly once.  

- If a particular version of a module is not designated, and more than one version of a module is `require`ed, then the module with 
the greater version number will be injected.

- If a particular version of a module _is_ designated in only one of multiple `require` actions for a module, then the module with 
the designated version will be injected.

- If more than one `require` designates a particular version of a module and the versions conflict, then an error will be thrown
during the page build. 

This encapsulation permits one to create components that can be independently packaged and delivered to pages.  Dependency conflict 
between modules is minimized to situations where two modules can function only with specific, conflicting versions of a particular 
resource.  This kind of modularity is a powerful feature of hyperscala and is one characteristic that distinguishes it from many other frameworks.

## Where Do I Go from Here?

Try creating your own examples from the [Hyperscala examples directory](https://github.com/darkfrog26/hyperscala/tree/master/examples/src/main/scala/org/hyperscala/examples 
"Hyperscala examples directory shown in github") read the links provided above, or hop in `#hyperscala` irc on `freenode`. 


- - -

[1]: http://www.matthicks.com/2013/01/hyperscala-getting-started.html "matthicks.com: Hyperscala Getting Started"
[2]: http://www.matthicks.com/2013/01/hyperscala-introduction.html "matthicks.com: Hyperscala Introduction"
[3]: https://github.com/n8han/giter8 "giter8 template repository on github"
