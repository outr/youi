package io.youi.net

case class Protocol private(scheme: String, description: String, rfc: String, defaultPort: Option[Int] = None) {
  Protocol.schemeMap += scheme -> this

  override def toString: String = scheme
}

object Protocol {
  private var schemeMap = Map.empty[String, Protocol]

  val Aaa: Protocol = Protocol("aaa", "Diameter Protocol", "RFC6733")
  val Aaas: Protocol = Protocol("aaas", "Diameter Protocol with Secure Transport", "RFC6733")
  val About: Protocol = Protocol("about", "about", "RFC6694")
  val Acap: Protocol = Protocol("acap", "application configuration access protocol", "RFC2244")
  val Acct: Protocol = Protocol("acct", "acct", "RFC-ietf-appsawg-acct-uri-06")
  val Cap: Protocol = Protocol("cap", "Calendar Access Protocol", "RFC4324")
  val Cid: Protocol = Protocol("cid", "content identifier", "RFC2392")
  val Coap: Protocol = Protocol("coap", "coap", "RFC-ietf-core-coap-18")
  val Coaps: Protocol = Protocol("coaps", "coaps", "RFC-ietf-core-coap-18")
  val Crid: Protocol = Protocol("crid", "TV-Anytime Content Reference Identifier", "RFC4078")
  val Data: Protocol = Protocol("data", "data", "RFC2397")
  val Dav: Protocol = Protocol("dav", "dav", "RFC4918")
  val Dict: Protocol = Protocol("dict", "dictionary service protocol", "RFC2229")
  val Dns: Protocol = Protocol("dns", "Domain Name System", "RFC4501")
  val File: Protocol = Protocol("file", "Host-specific file names", "RFC1738")
  val Ftp: Protocol = Protocol("ftp", "File Transfer Protocol", "RFC1738")
  val Geo: Protocol = Protocol("geo", "Geographic Locations", "RFC5870")
  val Go: Protocol = Protocol("go", "go", "RFC3368")
  val Gopher: Protocol = Protocol("gopher", "The Gopher Protocol", "RFC4266")
  val H323: Protocol = Protocol("h323", "H.323", "RFC3508")
  val Http: Protocol = Protocol("http", "Hypertext Transfer Protocol", "RFC2616", Some(80))
  val Https: Protocol = Protocol("https", "Hypertext Transfer Protocol Secure", "RFC2818", Some(443))
  val Iax: Protocol = Protocol("iax", "Inter-Asterisk eXchange Version 2", "RFC5456")
  val Icap: Protocol = Protocol("icap", "Internet Content Adaptation Protocol", "RFC3507")
  val Im: Protocol = Protocol("im", "Instant Messaging", "RFC3860")
  val Imap: Protocol = Protocol("imap", "internet message access protocol", "RFC5092")
  val Info: Protocol = Protocol("info", "Information Assets with Identifiers in Public Namespaces", "RFC4452")
  val Ionic: Protocol = Protocol("ionic", "Ionic", "IONIC")
  val Ipp: Protocol = Protocol("ipp", "Internet Printing Protocol", "RFC3510")
  val Iris: Protocol = Protocol("iris", "Internet Registry Information Service", "RFC3981")
  val IrisBeep: Protocol = Protocol("iris.beep", "iris.beep", "RFC3983")
  val IrisXpc: Protocol = Protocol("iris.xpc", "iris.xpc", "RFC4992")
  val IrisXpcs: Protocol = Protocol("iris.xpcs", "iris.xpcs", "RFC4992")
  val IrisLwz: Protocol = Protocol("iris.lwz", "iris.lwz", "RFC4993")
  val Jabber: Protocol = Protocol("jabber", "jabber","Saint-Andre")
  val JarFile: Protocol = Protocol("jar:file", "JAR File","JAR")
  val Ldap: Protocol = Protocol("ldap", "Lightweight Directory Access Protocol","RFC4516")
  val Mailto: Protocol = Protocol("mailto", "Electronic mail address", "RFC6068")
  val Mid: Protocol = Protocol("mid", "message identifier", "RFC2392")
  val Msrp: Protocol = Protocol("msrp", "Message Session Relay Protocol", "RFC4975")
  val Msrps: Protocol = Protocol("msrps", "Message Session Relay Protocol Secure", "RFC4975")
  val Mtqp: Protocol = Protocol("mtqp", "Message Tracking Query Protocol", "RFC3887")
  val Mupdate: Protocol = Protocol("mupdate", "Mailbox Update (MUPDATE) Protocol", "RFC3656")
  val News: Protocol = Protocol("news", "USENET news", "RFC5538")
  val Nfs: Protocol = Protocol("nfs", "network file system protocol", "RFC2224")
  val Ni: Protocol = Protocol("ni", "ni", "RFC6920")
  val Nih: Protocol = Protocol("nih", "nih", "RFC6920")
  val Nntp: Protocol = Protocol("nntp", "USENET news using NNTP access", "RFC5538")
  val Opaquelocktoken: Protocol = Protocol("opaquelocktoken", "opaquelocktokent", "RFC4918")
  val Pop: Protocol = Protocol("pop", "Post Office Protocol v3", "RFC2384")
  val Pres: Protocol = Protocol("pres", "Presence", "RFC3859")
  val Reload: Protocol = Protocol("reload", "reload","draft-ietf-p2psip-base-26")
  val Rtsp: Protocol = Protocol("rtsp", "real time streaming protocol", "RFC2326")
  val Service: Protocol = Protocol("service", "service location", "RFC2609")
  val Session: Protocol = Protocol("session", "session", "RFC6787")
  val Shttp: Protocol = Protocol("shttp", "Secure Hypertext Transfer Protocol", "RFC2660")
  val Sieve: Protocol = Protocol("sieve", "ManageSieve Protocol", "RFC5804")
  val Sip: Protocol = Protocol("sip", "session initiation protocol", "RFC3261")
  val Sips: Protocol = Protocol("sips", "secure session initiation protocol", "RFC3261")
  val Sms: Protocol = Protocol("sms", "Short Message Service", "RFC5724")
  val Snmp: Protocol = Protocol("snmp", "Simple Network Management Protocol", "RFC4088")
  val SoapBeep: Protocol = Protocol("soap.beep", "soap.beep", "RFC4227")
  val SoapBeeps: Protocol = Protocol("soap.beeps", "soap.beeps", "RFC4227")
  val Tag: Protocol = Protocol("tag", "tag", "RFC4151")
  val Tel: Protocol = Protocol("tel", "telephone", "RFC3966")
  val Telnet: Protocol = Protocol("telnet", "Reference to interactive sessions", "RFC4248")
  val Tftp: Protocol = Protocol("tftp", "Trivial File Transfer Protocol", "RFC3617")
  val Thismessage: Protocol = Protocol("thismessage", "perm/thismessage	multipart/related relative reference resolution", "RFC2557")
  val Tn3270: Protocol = Protocol("tn3270", "Interactive 3270 emulation sessions", "RFC6270")
  val Tip: Protocol = Protocol("tip", "Transaction Internet Protocol", "RFC2371")
  val Tv: Protocol = Protocol("tv", "TV Broadcasts", "RFC2838")
  val Urn: Protocol = Protocol("urn", "Uniform Resource Names", "RFC2141][IANA registry urn-namespaces")
  val Vemmi: Protocol = Protocol("vemmi", "versatile multimedia interface", "RFC2122")
  val Ws: Protocol = Protocol("ws", "WebSocket connections", "RFC6455", Some(80))
  val Wss: Protocol = Protocol("wss", "Encrypted WebSocket connections", "RFC6455", Some(443))
  val Xcon: Protocol = Protocol("xcon", "xcon", "RFC6501")
  val XconUserid: Protocol = Protocol("xcon-userid", "xcon-userid", "RFC6501")
  val XmlrpcBeep: Protocol = Protocol("xmlrpc.beep", "xmlrpc.beep", "RFC3529")
  val XmlrpcBeeps: Protocol = Protocol("xmlrpc.beeps", "xmlrpc.beeps", "RFC3529")
  val Xmpp: Protocol = Protocol("xmpp", "Extensible Messaging and Presence Protocol", "RFC5122")
  val Z3950r: Protocol = Protocol("z39.50r", "Z39.50 Retrieval", "RFC2056")
  val Z3950s: Protocol = Protocol("z39.50s", "Z39.50 Session", "RFC2056")

  def apply(scheme: String): Protocol = apply(scheme, allowUnsupported = true)

  def apply(scheme: String, allowUnsupported: Boolean): Protocol = get(scheme, allowUnsupported).getOrElse(throw new RuntimeException(s"Unable to find $scheme in Protocol."))

  def get(scheme: String, allowUnsupported: Boolean): Option[Protocol] = {
    val option = schemeMap.get(scheme.toLowerCase)
    if (option.isEmpty && allowUnsupported) {
      Some(Protocol(scheme, "Adhoc", "Unknown"))
    } else {
      option
    }
  }
}