package io.youi.net

case class EmailAddress(local: String, domain: String) {
  def normalize(): EmailAddress = EmailAddress(local.toLowerCase, domain.toLowerCase)
  def canonical(excludePlus: Boolean = false): EmailAddress = {
    var l = local
      .replaceAll("[(].*?[)]", "")
      .replaceAll("[{].*?[}]", "")
    if (excludePlus) {
      val index = l.indexOf('+')
      if (index != -1) {
        l = l.substring(0, index)
      }
    }
    val d = domain
      .replaceAll("[(].*?[)]", "")
      .replaceAll("[{].*?[}]", "")
    EmailAddress(l, d).normalize()
  }
}

object EmailAddress {
  private val EmailRegex = """(.+)@(.+)[.](.+)""".r

  def parse(email: String): Option[EmailAddress] = email.trim match {
    case null | "" => None
    case EmailRegex(local, domain, tld) => Some(EmailAddress(local, s"$domain.$tld"))
    case _ => {
      scribe.warn(s"Unrecognized email address: [$email]")
      None
    }
  }
}