package org.hyperscala.html

/**
 * Get a [[scala.collection.Seq]] with a value if in a matching location, as would be useful
 * for site navigation.
 *
 * ==Example==
 *
 * If we want `"active"` in the `clazz` [[scala.collection.Seq]] when in the matching location,
 * one could do the following.
 *
 * {{{
 * class BasePage extends Webpage(MySite) with Position[String, String] {
 *
 *   //Create partially applied function to simplify use
 *   val inPos = inPosition(List.empty, _:String, "active")
 *
 *   ...  //implement some common navigation here in Parent class
 *
 *         contents += new tag.Li(clazz = inPos("index")) {
 *           contents += new tag.A(href = "index.html") {
 *             contents += "Home"
 *           }
 *         }
 *
 * }
 * }}}
 *
 * Then in an `IndexPage` page that extends `BasePage`, set the `posId` to `"index"`
 *
 * {{{
 * object IndexPage extends BasePage {
 *   override def posId: Option[String] = Some("index")
 * }}}
 *
 *  The result will then be that the navigation built for the `IndexPage` will have `"active"` applied to the
 *  list item tested with `inPos("index")`.
 *
 * `inPos("index")` would return `List("active")`
 *
 *
 * @tparam A The type for the position identifier
 * @tparam B The type for the elements of the Seq
 */
trait Position[A,B] {

  def posId: Option[A] = None

  def inPosition(in: Seq[B], pos: A, flag: B): Seq[B] =
    if (posId.contains(pos)) flag +: in else in
}