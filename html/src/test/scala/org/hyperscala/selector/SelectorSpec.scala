package org.hyperscala.selector

import org.scalatest.{Matchers, WordSpec}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SelectorSpec extends WordSpec with Matchers {
  "Selector" when {
    "parsing" should {
      "support simple all selector" in {
        Selector("*").getClass.getSimpleName should equal("AllSelector")
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

        val entries = selector.toList
        entries.length should be(3)
        val e1 = entries.head
        e1.thisValue should be("#myDiv")
        e1.getClass should be(classOf[IdSelector])
        val e2 = entries.tail.head
        e2.thisValue should be(" ")
        e2.getClass should be(classOf[DescendantSelector])
        val e3 = entries.tail.tail.head
        e3.thisValue should be("button")
        e3.getClass should be(classOf[ElementSelector[_]])

        selector.value should be("#myDiv button")
      }
      "support simple child selector" in {
        val selector = Selector(".container > .buttons")

        val entries = selector.toList
        entries.length should be(3)
        val e1 = entries.head
        e1.thisValue should be(".container")
        e1.getClass should be(classOf[ClassSelector])
        val e2 = entries.tail.head
        e2.thisValue should be(" > ")
        e2.getClass should be(classOf[ChildSelector])
        val e3 = entries.tail.tail.head
        e3.thisValue should be(".buttons")
        e3.getClass should be(classOf[ClassSelector])

        selector.value should be(".container > .buttons")
      }
      "support simple pseudo selector" in {
        val selector = Selector("div:hover")

        val entries = selector.toList
        entries.length should be(2)
        val e1 = entries.head
        e1.thisValue should be("div")
        e1.getClass should be(classOf[ElementSelector[_]])
        val e2 = entries.tail.head
        e2.thisValue should be(":hover")
        e2.getClass should be(classOf[PseudoClassSelector])

        selector.value should be("div:hover")
      }
      "support simple preceding selector" in {
        val selector = Selector("div + button")

        val entries = selector.toList
        entries.length should be(3)
        val e1 = entries.head
        e1.thisValue should be("div")
        e1.getClass should be(classOf[ElementSelector[_]])
        val e2 = entries.tail.head
        e2.thisValue should be(" + ")
        e2.getClass should be(classOf[PrecedingSelector])
        val e3 = entries.tail.tail.head
        e3.thisValue should be("button")
        e3.getClass should be(classOf[ElementSelector[_]])

        selector.value should be("div + button")
      }
      "support simple Selector.multiple" in {
        val selector = Selector.multiple(Selector.id("first"), Selector.id("second"))

        val entries = selector.toList
        entries.length should be(3)
        entries.head.thisValue should be("#first")
        entries.tail.head.thisValue should be(", ")
        entries.tail.tail.head.thisValue should be("#second")
      }
      "support complex Selector.multiple" in {
        val selector = Selector.multiple(Selector("div.test:hover"), Selector("span#other:active"), Selector("#awesome:focus"))

        val entries = selector.toList.toVector
        entries.length should be(10)
        entries(0).thisValue should be("div")
        entries(1).thisValue should be(".test")
        entries(2).thisValue should be(":hover")
        entries(3).thisValue should be(", ")
        entries(4).thisValue should be("span")
        entries(5).thisValue should be("#other")
        entries(6).thisValue should be(":active")
        entries(7).thisValue should be(", ")
        entries(8).thisValue should be("#awesome")
        entries(9).thisValue should be(":focus")

        selector.value should be("div.test:hover, span#other:active, #awesome:focus")
      }
    }
  }
}
