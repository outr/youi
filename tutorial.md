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

The hyperscala sources contain numerous examples.  Look for examples here:

- [Hyperscala examples directory](https://github.com/darkfrog26/hyperscala/tree/master/examples/src/main/scala/org/hyperscala/examples "Hyperscala examples directory shown in github")
  These examples are hosted online [here](http://hyperscala.com/examples.html "Hyperscala examples directory hosted online")

- [Hello example](#helloexample)

### [Hello example](id:helloexample)

The following is based on the [hello example from the hyperscala sources](https://github.com/darkfrog26/hyperscala/tree/master/hello "A simple hyperscala hello example shown in github").
 

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

##### `web.xml` 
  This example uses a `Jetty` container so create the `web.xml` as needed.  This would go into 
  someplace like `src/main/webapp/WEB-INF/`.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app
xmlns="http://java.sun.com/xml/ns/javaee"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
version="3.0"
>
<servlet>
<servlet-name>outrnet</servlet-name>
<servlet-class>com.outr.net.http.servlet.OUTRNetServlet</servlet-class>
<init-param>
<param-name>application</param-name>
<param-value>hello.HelloSite</param-value>
</init-param>
<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
<servlet-name>outrnet</servlet-name>
<url-pattern>/*</url-pattern>
</servlet-mapping>
<session-config>
<session-timeout>25</session-timeout>
</session-config>
</web-app>
```

##### Create the Site

Create something like the following.

```scala
import org.hyperscala.web.{StaticWebsite, BasicWebsite}
import com.outr.net.http.jetty.JettyApplication
import com.outr.net.http.session.MapSession

object HelloSite extends BasicWebsite with StaticWebsite[MapSession] with JettyApplication {
  def index = new HelloPage
}
```

This creates a `HelloSite` site that will take requests at `index.html` and the root of the site 
as expected.  If you add `def foo = new HelloPage` then a `HelloPage` will answer at `foo.html`...

`BasicWebsite` extends [com.outr.net.http.WebApplication](https://github.com/darkfrog26/outrnet/blob/master/core/src/main/scala/com/outr/net/http/WebApplication.scala).
The `outr.net` library is found [here](https://github.com/darkfrog26/outrnet).

#### Try it

Run with from sbt with the following.

    $ sbt run

## Modules



## Dynamic Content

## Putting it All Together

## Where Do I Go from Here? 


- - -

[1]: http://www.matthicks.com/2013/01/hyperscala-getting-started.html "matthicks.com: Hyperscala Getting Started"
[2]: http://www.matthicks.com/2013/01/hyperscala-introduction.html "matthicks.com: Hyperscala Introduction"
[3]: https://github.com/n8han/giter8 "giter8 template repository on github"
