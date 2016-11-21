//package spec
//
//import io.youi.communicate.Communication
//import org.scalatest.{Matchers, WordSpec}
//
//class CommunicationSpec extends WordSpec with Matchers {
//  "Communication" should {
//    "have the proper ids" in {
//      SimpleCommunication.first.id should be(1)
//      SimpleCommunication.second.id should be(2)
//    }
//  }
//
//  object SimpleCommunication extends Communication {
//    val first = register[First]
//    val second = register[Second]
//  }
//
//  case class First(value: String)
//  case class Second(value: Int)
//}