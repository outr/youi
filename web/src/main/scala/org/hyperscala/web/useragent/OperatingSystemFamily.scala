package org.hyperscala.web.useragent

import org.powerscala.enum.{EnumEntry, Enumerated}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class OperatingSystemFamily(val friendlyName: String) extends EnumEntry {
  import OperatingSystemFamily._

  lazy val apple = this == OSX || this == MacOS || this == iOS
  lazy val windows = this == Windows
  lazy val nix = List(AIX, BSD, HPUX, IRIX, Linux, Solaris).contains(this)

  override def toString() = friendlyName
}

object OperatingSystemFamily extends Enumerated[OperatingSystemFamily] {
  /**
   * AIX (Advanced Interactive eXecutive) is a Unix operating system from IBM.
   */
  val AIX = new OperatingSystemFamily("AIX")

  /**
   * AROS is a free operating system aiming at being compatible with AmigaOS at the API level.
   */
  val AROS = new OperatingSystemFamily("AROS")

  /**
   * AmigaOS is the native operating system for the Commodore Amiga, consisting of the components of Workbench,
   * AmigaDOS with the command line interpreter CLI (later renamed to shell) and for many Amiga models in the ROM
   * included kernel <i>kickstart</i>.
   */
  val AmigaOS = new OperatingSystemFamily("Amiga OS")

  /**
   * Android is both an operating system and a software platform for mobile devices like smart phones, mobile phones,
   * netbooks and tablets, which is developed by the Open Handset Alliance.
   */
  val Android = new OperatingSystemFamily("Android")

  /**
   * The Berkeley Software Distribution (BSD) is a version of the Unix operating system, which was created at the
   * University of California at Berkeley in 1977.
   */
  val BSD = new OperatingSystemFamily("BSD")

  /**
   * Bada is a service-oriented operating system that is developed by Samsung Electronics and is designed for use in
   * smartphones.
   */
  val Bada = new OperatingSystemFamily("Bada")

  /**
   * Be Operating System (BeOS) was an operating system of the company <i>Be Incorporated</i> and was called in later
   * versions Be. Due to its multimedia capabilities it is also commonly called "Media OS".
   */
  val BeOS = new OperatingSystemFamily("BeOS")

  /**
   * Danger OS is a smartphone operating system. It is used on Sidekick devices, which are sold in Germany by
   * T-Mobile.
   */
  val DangerOS = new OperatingSystemFamily("DangerOS")

  /**
   * Firefox OS is an open source operating system for smartphones and tablet computers being developed by Mozilla.
   */
  val FirefoxOS = new OperatingSystemFamily("Firefox OS")

  /**
   * HP-UX (Hewlett Packard UniX) is a commercial Unix operating system from Hewlett-Packard and is based on UNIX
   * System V.
   */
  val HPUX = new OperatingSystemFamily("HP-UX")

  /**
   * Haiku (formerly OpenBeOS) is an open-source project with the aim, to reprogram and expand that in 2001 abandoned
   * operating system BeOS.
   */
  val HaikuOS = new OperatingSystemFamily("Haiku OS")

  /**
   * IRIX is a commercial Unix operating system of the company Silicon Graphics (SGI).
   */
  val IRIX = new OperatingSystemFamily("IRIX")

  /**
   * Inferno is a distributed computer operating system that comes from Bell Laboratories.
   */
  val InfernoOS = new OperatingSystemFamily("Inferno OS")

  /**
   * The Java Virtual Machine (abbreviated Java VM or JVM) is the part of the Java Runtime Environment (JRE) for Java
   * programs, which is responsible for the execution of Java bytecode.<br>
   * <br>
   * This value is not an operating system family.
   */
  val JVM = new OperatingSystemFamily("JVM")

  /**
   * Linux or GNU/Linux are usually called free, unix-like multi-user operating systems running based on the Linux
   * kernel and other GNU software.
   */
  val Linux = new OperatingSystemFamily("Linux")

  /**
   * Mac OS is the name of the classic operating system (1984-2001) by Apple for Macintosh computers.
   */
  val MacOS = new OperatingSystemFamily("Mac OS")

  /**
   * Minix is a free unixoides operating system that was developed by Andrew S. Tanenbaum at the Free University of
   * Amsterdam as a teaching tool.
   */
  val MINIX = new OperatingSystemFamily("MINIX")

  /**
   * OS X, formerly Mac OS X, is a Unix-based operating systems developed by Apple. It is a proprietary distribution
   * of the free Darwin operating system from Apple.
   */
  val OSX = new OperatingSystemFamily("OS X")

  /**
   * MorphOS is an Amiga-compatible computer operating system. It is a mixed proprietary and open source OS produced
   * for the Pegasos PowerPC processor based computer.
   */
  val MorphOS = new OperatingSystemFamily("MorphOS")

  /**
   * This value indicates the operating systems from Nintendo, which they developed for their devices.<br>
   * <br>
   * This value is not an operating system family.
   */
  val Nintendo = new OperatingSystemFamily("Nintendo")

  /**
   * OS/2 is a multitasking operating system for PCs. It was originally developed by IBM and Microsoft together with
   * the aim to replace DOS.
   */
  val OS2 = new OperatingSystemFamily("OS/2")

  /**
   * Palm OS was the operating system for organizer of the Palm series (known as PDAs) and smartphones.
   */
  val PalmOS = new OperatingSystemFamily("Palm OS")

  /**
   * The PlayStation Vita system software is the official, updatable firmware and operating system for the PlayStation
   * Vita.
   */
  val LiveArea = new OperatingSystemFamily("LiveArea")

  /**
   * QNX is a POSIX-compatible proprietary Unix-like real-time operating system that focused primarily at the embedded
   * market.
   */
  val QNX = new OperatingSystemFamily("QNX")

  /**
   * The BlackBerry OS (up to the fifth edition known as the <i>BlackBerry Device Software</i>, also known as
   * <i>Research In Motion OS</i>) is a proprietary, free usable (freeware) multi-tasking operating system for
   * smartphones.
   */
  val RIMOS = new OperatingSystemFamily("RIM OS")

  /**
   * Solaris is the name of an operating system distribution based on SunOS and is a Unix operating system. Since the
   * takeover of Sun Microsystems in 2010 Solaris is part of Oracle.
   */
  val Solaris = new OperatingSystemFamily("Solaris")

  /**
   * Syllable is a slim and fast desktop Unix-like operating system for x86 processors.
   */
  val Syllable = new OperatingSystemFamily("Syllable")

  /**
   * The Symbian platform, simply called Symbian, is an operating system for smartphones and PDAs. The Symbian
   * platform is the successor to Symbian OS
   */
  val SymbianOS = new OperatingSystemFamily("Symbian OS")

  /**
   * Tizen is a free operating system based on Linux respectively Debian and was launched by the Linux Foundation and
   * LiMo Foundation.
   */
  val Tizen = new OperatingSystemFamily("Tizen")

  /**
   * Microsoft Windows is a trademark for operating systems of the Microsoft Corporation. Microsoft Windows was
   * originally a graphical extension of the operating system MS-DOS.
   */
  val Windows = new OperatingSystemFamily("Windows")

  /**
   * XrossMediaBar (XMB) is the name of the graphical user interface, which are used on PlayStation 3, PlayStation
   * Portable, Sony Blu-Ray players and Sony Bravia TVs. Also some special versions of the PlayStation 2, PSX, already
   * using the XMB.
   */
  val XrossMediaBar = new OperatingSystemFamily("XrossMediaBar (XMB)")

  /**
   * iOS (until June 2010 iPhone OS) is the standard operating system of Apple products like iPhone, iPod touch, iPad,
   * and the second generation of Apple TV. iOS is based on Mac OS X.
   */
  val iOS = new OperatingSystemFamily("iOS")

  /**
   * webOS is a smartphone and tablet operating system from Hewlett-Packard (formerly HP Palm). It represents the
   * follower of Palm OS.
   */
  val webOS = new OperatingSystemFamily("webOS")

  /**
   * Unknown operating system family<br>
   * <br>
   * This value will be returned if the operating system family cannot be determined.
   */
  val Unknown = new OperatingSystemFamily("Unknown")

  def byFriendlyName(name: String) = values.find(osf => osf.friendlyName == name).getOrElse(Unknown)
}
