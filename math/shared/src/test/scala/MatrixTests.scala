import io.youi.math.Matrix3
import io.youi.math._
import org.scalacheck.Prop._
import org.scalatest.{FlatSpec, FlatSpecLike, Matchers}
import org.scalatest.prop.PropertyChecks


class MatrixTests extends MatrixTestHelpers with PropertyChecks with Matchers with FlatSpecLike {

  behavior of "Matrix3"

  it should "Return the same matrix after 360 degree rotation" in {
    forAll{
      m: Matrix3 =>
        m.rotateDeg(180.deg).rotateDeg(180.deg) should equal(m)
        m.rotateDeg(360.deg) should equal(m)
        m.rotate((2 * math.Pi).rad) should equal(m)
    }
  }

  it should "follow proper addition laws" in {
    forAll {
      (m1: Matrix3) =>
        (m1 + m1) should equal(m1 * 2)
    }

  }
}
