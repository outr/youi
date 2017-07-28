import io.youi.math.{ImmutableMatrix3, Matrix3, MutableMatrix3}
import org.scalacheck.{Arbitrary, Gen}
import org.scalactic.{Equality, TolerantNumerics}

trait MatrixTestHelpers {

//  implicit val doubleEqualityTolerance: Equality[Double] = TolerantNumerics.tolerantDoubleEquality(0.001)

  implicit val matrixEquality: Equality[Matrix3] = (a: Matrix3, b: Any) => b match {
    case b: Matrix3 => a == b
    case _ => false
  }

  implicit val mutableGen: Gen[MutableMatrix3] = for {
    d <- Gen.listOfN(9,Gen.chooseNum[Double](-1e10,1e10))
  } yield MutableMatrix3(d.toArray)

  implicit val mutableArbitrary = Arbitrary(mutableGen)

  implicit val immutableGen: Gen[ImmutableMatrix3] = for {
    d <- Gen.listOfN(9,Gen.chooseNum[Double](-1e10,1e10))
  } yield ImmutableMatrix3(d.toArray)

  implicit val immutableArbitrary = Arbitrary(immutableGen)

  implicit val matrixGen: Gen[Matrix3] = Gen.oneOf(mutableGen, immutableGen)

  implicit val matrixArbitrary: Arbitrary[Matrix3] = Arbitrary(matrixGen)
}
