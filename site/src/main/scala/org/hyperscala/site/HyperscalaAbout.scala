package org.hyperscala.site

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HyperscalaAbout extends HyperscalaPage {
  main.contents += new tag.H1(content = "About Hyperscala")
  main.contents += new tag.H2(content = "What is Hyperscala?")
  main.contents +=
    """
      |Hyperscala is a statically typed bare-metal HTML, CSS, and JavaScript framework for Scala. What does that mean?
      |It means you can write web pages in Scala entirely without markup. Hyperscala, unlike many frameworks in the web
      |framework arena, is at its core a bare-metal framework. This means that we've done our best to perfectly
      |represent HTML, CSS, and JavaScript so that there is nothing that can be done in a webpage that cannot be
      |replicated in Hyperscala.
    """.stripMargin
  main.contents += new tag.H2(content = "What if I already have HTML?")
  main.contents +=
    """
      |Great question! The goal of Hyperscala is to make web development easier and the honest truth is that sometimes
      |it's just easier to write markup or you already have HTML you'd like to use. That's okay, we understand and we've
      |created a lot of ways to make use of existing HTML. The first option is just to plug it in. You can use the
      |StaticHTML class to inject a snippet of HTML code into your page as-is without any changes. This can be
      |problematic though because rarely do you need to just work with static content, you need to modify it. To that
      |end there are two other ways you can interact with the existing HTML. The first option is to convert it to Scala.
      |We've provided a utility that can take any existing HTML file and convert it to Scala source code utilizing
      |Hyperscala. This is great when you are porting over and the developer will be maintaining layout and design going
      |forward. Unfortunately, this does not take into account the separation of developer and designer. Good luck
      |trying to convince your web designer to learn Scala. This is where the second option comes in. Through the use of
      |DynamicContent you can pull in snippets of HTML (similarly to StaticHTML), but extract elements out by id and
      |modify and introspect them to your hearts content. Though it would seem like this would be incredibly slow, it's
      |actually optimized to the extent that in most cases it performs faster than any other option. It's sort of like
      |a dynamically regenerating JSP file that only deals with the dynamic parts.
    """.stripMargin
  main.contents += new tag.H2(content = "Isn't HTML a pain?")
  main.contents +=
    """
      |Yes, we sure think so. Though at Hyperscala's core it is nothing more than a statically typed Scala framework
      |that lets you write HTML there's so much more to it. We started with the core because we don't want to make
      |assumptions of how people will use the framework. However, we've provided a lot of features on top that allow
      |developers a much easier and abstract way of creating and manipulating web pages. One of the core features is
      |the idea of Modules. In Hyperscala a module can be "required" by a page or component and will be loaded to
      |provide some functionality into the page. For example, there are simple modules that inject JavaScript
      |dependencies like jQuery (while avoiding multiple versions or includes from occurring) and other more advanced
      |features like Realtime that allows two-way realtime communication via WebSockets (failing down to AJAX polling)
      |for your page. Creating your own module is simple and allows libraries to be written and utilized without
      |the idea of using them overly complicated. There's so much more that Hyperscala provides, check out the
      |documentation for more information.
    """.stripMargin

  override def sourceURL = "https://github.com/darkfrog26/hyperscala/blob/master/site/src/main/scala/org/hyperscala/site/HyperscalaAbout.scala"
}