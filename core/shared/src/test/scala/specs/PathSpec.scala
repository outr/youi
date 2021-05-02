package specs

import testy._
import io.youi.net._

class PathSpec extends Spec {
  "Path" should {
    "validate simple" in {
      Path.parse("/one/two/three").parts should be(List(
        new LiteralPathPart("one"),
        new LiteralPathPart("two"),
        new LiteralPathPart("three")
      ))
    }
    "absolutize properly" in {
      Path.parse("/one/two/../three").parts should be(List(
        new LiteralPathPart("one"),
        new LiteralPathPart("three")
      ))
    }
    "detect argument" in {
      val path = Path.parse("/one/two/:three")
      path.parts should be(List(
        new LiteralPathPart("one"),
        new LiteralPathPart("two"),
        new ArgumentPathPart("three")
      ))
      path.arguments should be(List("three"))
    }
    "detect argument via Macro" in {
      val path = path"/one/two/:three"
      path.parts should be(List(
        new LiteralPathPart("one"),
        new LiteralPathPart("two"),
        new ArgumentPathPart("three")
      ))
      path.arguments should be(List("three"))
    }
    "apply arguments" in {
      val p1 = Path.parse("/one/two/:three")
      val p2 = p1.withArguments(Map("three" -> "Testing"))
      p2.parts should be(List(
        new LiteralPathPart("one"),
        new LiteralPathPart("two"),
        new LiteralPathPart("Testing")
      ))
    }
    "extract arguments" in {
      val p1 = Path.parse("/one/two/:three")
      val p2 = Path.parse("/one/two/Testing")
      p1 should be(p2)
      p1.extractArguments(p2) should be(Map("three" -> "Testing"))
    }
  }
}