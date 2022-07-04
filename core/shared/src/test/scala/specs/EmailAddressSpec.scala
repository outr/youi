package specs

import io.youi.net.EmailAddress
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class EmailAddressSpec extends AnyWordSpec with Matchers {
  "EmailAddress" when {
    "validating" should {
      "parse simple@example.com" in {
        val email = EmailAddress.parse("simple@example.com").get
        email.local should be("simple")
        email.domain should be("example.com")
      }
      "parse SIMPLE@ExAmPlE.com" in {
        val email = EmailAddress.parse("SIMPLE@ExAmPlE.com").get
        email.local should be("SIMPLE")
        email.domain should be("ExAmPlE.com")
        val normalized = email.normalize()
        normalized.local should be("simple")
        normalized.domain should be("example.com")
        val canonicalized = email.canonical()
        canonicalized.local should be("simple")
        canonicalized.domain should be("example.com")
      }
      "parse SI(a comment)MPLE+test@ExAm{another comment}PlE.com" in {
        val email = EmailAddress.parse("SI(a comment)MPLE+test@ExAm{another comment}PlE.com").get
        email.local should be("SI(a comment)MPLE+test")
        email.domain should be("ExAm{another comment}PlE.com")
        val normalized = email.normalize()
        normalized.local should be("si(a comment)mple+test")
        normalized.domain should be("exam{another comment}ple.com")
        val canonicalized = email.canonical(excludePlus = true)
        canonicalized.local should be("simple")
        canonicalized.domain should be("example.com")
      }
    }
  }
}