package org.hyperscala.selector

import org.scalatest.{Matchers, WordSpec}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SelectorSpec extends WordSpec with Matchers {
  "Selector" when {
    "parsing" should {
      "support simple all selector" in {
        Selector("*").getClass.getSimpleName should equal("AllSelector$")
        Selector("*").value should equal("*")
      }
      "support simple class selector" in {
        Selector(".simple").getClass.getSimpleName should equal("ClassSelector")
        Selector(".simple").value should equal(".simple")
      }
      "support simple element selector" in {
        Selector("div").getClass.getSimpleName should equal("ElementSelector")
        Selector("div").value should equal("div")
      }
      "support simple id selector" in {
        Selector("#myButton").getClass.getSimpleName should equal("IdSelector")
        Selector("#myButton").value should equal("#myButton")
      }
      "support simple descendant selector" in {
        val selector = Selector("#myDiv button")
        selector.getClass.getSimpleName should equal("DescendantSelector")
        val ds = selector.asInstanceOf[DescendantSelector]
        ds.parent.getClass.getSimpleName should equal("IdSelector")
        ds.child.getClass.getSimpleName should equal("ElementSelector")

        selector.value should equal("#myDiv button")
        ds.parent.value should equal("#myDiv")
        ds.child.value should equal("button")
      }
      "support simple child selector" in {
        val selector = Selector(".container > .buttons")
        selector.getClass.getSimpleName should equal("ChildSelector")
        val cs = selector.asInstanceOf[ChildSelector]
        cs.parent.getClass.getSimpleName should equal("ClassSelector")
        cs.child.getClass.getSimpleName should equal("ClassSelector")

        selector.value should equal(".container > .buttons")
        cs.parent.value should equal(".container")
        cs.child.value should equal(".buttons")
      }
      "support simple pseudo selector" in {
        val selector = Selector("div:hover")
        selector.getClass.getSimpleName should equal("PseudoClassSelector")
        val cs = selector.asInstanceOf[PseudoClassSelector]
        cs.selector.getClass.getSimpleName should equal("ElementSelector")

        selector.value should equal("div:hover")
        cs.selector.value should equal("div")
        cs.clazz should equal(PseudoClass.Hover)
      }
      "support simple preceding selector" in {
        val selector = Selector("div + button")
        selector.getClass.getSimpleName should equal("PrecedingSelector")
        val cs = selector.asInstanceOf[PrecedingSelector]
        cs.selector.getClass.getSimpleName should equal("ElementSelector")
        cs.sibling.getClass.getSimpleName should equal("ElementSelector")

        selector.value should equal("div + button")
        cs.selector.value should equal("div")
        cs.sibling.value should equal("button")
      }
    }
  }
}
