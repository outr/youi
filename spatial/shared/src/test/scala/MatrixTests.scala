import io.youi.spatial.Matrix3
import io.youi.spatial._
import io.youi.spatial.Point
import org.scalatest.{FlatSpec, FlatSpecLike, Matchers}
import org.scalatest.prop.PropertyChecks
import org.scalatest.prop.Checkers._



class MatrixTests extends MatrixTestHelpers with PropertyChecks with Matchers with FlatSpecLike {

  behavior of "Matrix3"

  it should "Return the same matrix after 360 degree rotation" in {
    forAll{
      m: Matrix3 =>
        val duplicated = m.duplicate()
        m.rotateDeg(180.deg).rotateDeg(180.deg) should equal(duplicated)
        m.rotateDeg(360.deg) should equal(duplicated)
        m.rotate((2 * math.Pi).rad) should equal(duplicated)
    }
  }

  it should "follow proper addition laws" in {
    forAll {
      (m1: Matrix3, m2: Matrix3, m3: Matrix3) =>
        (m1 + m1) should equal(m1 * 2) //Scalar and addition
        ((m1 + m2) + m3) should equal(m1 + (m2 + m3)) //associativity
    }
  }

  it should "follow proper subtraction laws" in {
    forAll {
      (m1: Matrix3) =>
        (m1 - m1) should equal(Matrix3.Empty) //Scalar
        (m1 - (m1 * 2)) should equal(m1 * -1)
    }
  }

  it should "follow proper multiplication laws" in {
    forAll {
      (m1: Matrix3) =>
        whenever(m1.det() != 0){
          val inverse = m1.duplicate().inv()
          (m1 * inverse) should equal(Matrix3.Identity) //Inverse identity
        }
    }
  }

  it should "follow proper scalar division laws" in {
    forAll {
      (m1: Matrix3, scalar: Int) =>
        whenever(scalar != 0){
          (m1 / scalar.toDouble) should equal(m1 * (1.0/scalar.toDouble))
        }
    }
  }

  it should "follow transpose laws" in {
    forAll {
      (m1: Matrix3) =>
        val duplicate = m1.duplicate()
          m1.transpose.transpose should equal(duplicate)
    }
  }

  it should "localize a point properly" in {
    forAll{
      (point: Point, xtrans: Int, ytrans: Int, rotation: Degrees) =>
        val duplicate = point.duplicate()

        val tMatrix = Matrix3.Identity.toTranslation(xtrans, ytrans).rotateDeg(rotation)

        tMatrix.localize(point) should equal(duplicate.rotateDeg(rotation) + Point(xtrans, ytrans))
    }
  }
}
