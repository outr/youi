package org.hyperscala.web.useragent

import org.powerscala.enum.{EnumEntry, Enumerated}

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class BrowserFamily(val friendlyName: String) extends EnumEntry {
  override def toString() = friendlyName
}

object BrowserFamily extends Enumerated[BrowserFamily] {
  /**
   * Representation of an unknown User-Agent
   *
   * <p>
   * <strong>Attention</strong>: This is not a known User-Agent family, but only a placeholder.
   */
  case object Unknown extends BrowserFamily("unknown")

  /**
   * 192.comAgent
   */
  case object ComAgent192 extends BrowserFamily("192.comAgent")

  /**
   * 2Bone LinkChecker
   */
  case object TwoBoneLinkChecker extends BrowserFamily("2Bone LinkChecker")

  /**
   * 50.nu
   */
  case object Fiftynu extends BrowserFamily("50.nu")

  /**
   * 80legs
   */
  case object Eightylegs extends BrowserFamily("80legs")

  /**
   * A1 Sitemap Generator
   */
  case object A1SitemapGenerator extends BrowserFamily("A1 Sitemap Generator")

  /**
   * AB (Apache Bench)
   */
  case object ApacheBench extends BrowserFamily("AB (Apache Bench)")

  /**
   * abby
   */
  case object Abby extends BrowserFamily("abby")

  /**
   * Abilon
   */
  case object Abilon extends BrowserFamily("Abilon")

  /**
   * Abolimba
   */
  case object Abolimba extends BrowserFamily("Abolimba")

  /**
   * Aboundexbot
   */
  case object Aboundexbot extends BrowserFamily("Aboundexbot")

  /**
   * AboutUsBot
   */
  case object AboutUsBot extends BrowserFamily("AboutUsBot")

  /**
   * Abrave Spider
   */
  case object AbraveSpider extends BrowserFamily("Abrave Spider")

  /**
   * ABrowse
   */
  case object ABrowse extends BrowserFamily("ABrowse")

  /**
   * Accelobot
   */
  case object Accelobot extends BrowserFamily("Accelobot")

  /**
   * Accoona-AI-Agent
   */
  case object AccoonaAIAgent extends BrowserFamily("Accoona-AI-Agent")

  /**
   * Acoo Browser
   */
  case object AcooBrowser extends BrowserFamily("Acoo Browser")

  /**
   * AcoonBot
   */
  case object AcoonBot extends BrowserFamily("AcoonBot")

  /**
   * Acorn
   */
  case object Acorn extends BrowserFamily("Acorn")

  /**
   * ActiveXperts Network Monitor
   */
  case object ActiveXpertsNetworkMonitor extends BrowserFamily("ActiveXperts Network Monitor")

  /**
   * AddThis.com
   */
  case object AddThisCom extends BrowserFamily("AddThis.com")

  /**
   * Adobe AIR runtime
   */
  case object AdobeAIRRuntime extends BrowserFamily("Adobe AIR runtime")

  /**
   * adressendeutschland.de
   */
  case object Adressendeutschlandde extends BrowserFamily("adressendeutschland.de")

  /**
   * AdsBot-Google
   */
  case object AdsBotGoogle extends BrowserFamily("AdsBot-Google")

  /**
   * AhrefsBot
   */
  case object AhrefsBot extends BrowserFamily("AhrefsBot")

  /**
   * aiHitBot
   */
  case object aiHitBot extends BrowserFamily("aiHitBot")

  /**
   * aippie
   */
  case object aippie extends BrowserFamily("aippie")

  /**
   * Akregator
   */
  case object Akregator extends BrowserFamily("Akregator")

  /**
   * akula
   */
  case object akula extends BrowserFamily("akula")

  /**
   * Alienforce
   */
  case object Alienforce extends BrowserFamily("Alienforce")

  /**
   * Almaden
   */
  case object Almaden extends BrowserFamily("Almaden")

  /**
   * Amagit.COM
   */
  case object AmagitCOM extends BrowserFamily("Amagit.COM")

  /**
   * Amaya
   */
  case object Amaya extends BrowserFamily("Amaya")

  /**
   * Amazon Silk
   */
  case object AmazonSilk extends BrowserFamily("Amazon Silk")

  /**
   * Amfibibot
   */
  case object Amfibibot extends BrowserFamily("Amfibibot")

  /**
   * amibot
   */
  case object amibot extends BrowserFamily("amibot")

  /**
   * Amiga Aweb
   */
  case object AmigaAweb extends BrowserFamily("Amiga Aweb")

  /**
   * Amiga Voyager
   */
  case object AmigaVoyager extends BrowserFamily("Amiga Voyager")

  /**
   * Android Webkit
   */
  case object AndroidWebkit extends BrowserFamily("Android Webkit")

  /**
   * Anemone
   */
  case object Anemone extends BrowserFamily("Anemone")

  /**
   * Anonymouse.org
   */
  case object Anonymouse extends BrowserFamily("Anonymouse.org")

  /**
   * AntBot
   */
  case object AntBot extends BrowserFamily("AntBot")

  /**
   * anw HTMLChecker
   */
  case object anwHTMLChecker extends BrowserFamily("anw HTMLChecker")

  /**
   * anw LoadControl
   */
  case object anwLoadControl extends BrowserFamily("anw LoadControl")

  /**
   * AOL Explorer
   */
  case object AOLExplorer extends BrowserFamily("AOL Explorer")

  /**
   * Apache internal dummy connection
   */
  case object ApacheInternalDummyConnection extends BrowserFamily("Apache internal dummy connection")

  /**
   * Apache Synapse
   */
  case object ApacheSynapse extends BrowserFamily("Apache Synapse")

  /**
   * Apercite
   */
  case object Apercite extends BrowserFamily("Apercite")

  /**
   * AportWorm
   */
  case object AportWorm extends BrowserFamily("AportWorm")

  /**
   * Apple-PubSub
   */
  case object AppleMail extends BrowserFamily("Apple Mail")

  /**
   * Apple-PubSub
   */
  case object ApplePubSub extends BrowserFamily("Apple-PubSub")

  /**
   * arachnode.net
   */
  case object Arachnode extends BrowserFamily("arachnode.net")

  /**
   * archive.org_bot
   */
  case object ArchiveOrgBot extends BrowserFamily("archive.org_bot")

  /**
   * Arora
   */
  case object Arora extends BrowserFamily("Arora")

  /**
   * ASAHA Search Engine Turkey
   */
  case object ASAHASearchEngineTurkey extends BrowserFamily("ASAHA Search Engine Turkey")

  /**
   * Ask Jeeves/Teoma
   */
  case object AskJeevesTeoma extends BrowserFamily("Ask Jeeves/Teoma")

  /**
   * Atomic Email Hunter
   */
  case object AtomicEmailHunter extends BrowserFamily("Atomic Email Hunter")

  /**
   * Atomic Web Browser
   */
  case object AtomicWebBrowser extends BrowserFamily("Atomic Web Browser")

  /**
   * Avant Browser
   */
  case object AvantBrowser extends BrowserFamily("Avant Browser")

  /**
   * AvantGo
   */
  case object AvantGo extends BrowserFamily("AvantGo")

  /**
   * Awasu
   */
  case object Awasu extends BrowserFamily("Awasu")

  /**
   * Axel
   */
  case object Axel extends BrowserFamily("Axel")

  /**
   * BabalooSpider
   */
  case object BabalooSpider extends BrowserFamily("BabalooSpider")

  /**
   * BacklinkCrawler
   */
  case object BacklinkCrawler extends BrowserFamily("BacklinkCrawler")

  /**
   * Bad-Neighborhood
   */
  case object BadNeighborhood extends BrowserFamily("Bad-Neighborhood")

  /**
   * Baidu Browser
   */
  case object BaiduBrowser extends BrowserFamily("Baidu Browser")

  /**
   * Baiduspider
   */
  case object Baiduspider extends BrowserFamily("Baiduspider")

  /**
   * Banshee
   */
  case object Banshee extends BrowserFamily("Banshee")

  /**
   * Barca
   */
  case object Barca extends BrowserFamily("Barca")

  /**
   * baypup
   */
  case object baypup extends BrowserFamily("baypup")

  /**
   * BDFetch
   */
  case object BDFetch extends BrowserFamily("BDFetch")

  /**
   * Beamrise
   */
  case object Beamrise extends BrowserFamily("Beamrise")

  /**
   * BecomeBot
   */
  case object BecomeBot extends BrowserFamily("BecomeBot")

  /**
   * Beonex
   */
  case object Beonex extends BrowserFamily("Beonex")

  /**
   * Bigsearch.ca
   */
  case object BigsearchCa extends BrowserFamily("Bigsearch.ca")

  /**
   * bingbot
   */
  case object BingBot extends BrowserFamily("bingbot")

  /**
   * BinGet
   */
  case object BinGet extends BrowserFamily("BinGet")

  /**
   * bitlybot
   */
  case object bitlybot extends BrowserFamily("bitlybot")

  /**
   * biwec
   */
  case object biwec extends BrowserFamily("biwec")

  /**
   * bixo
   */
  case object bixo extends BrowserFamily("bixo")

  /**
   * bixolabs
   */
  case object bixocrawler extends BrowserFamily("bixocrawler")

  /**
   * BlackBerry Browser
   */
  case object BlackBerryBrowser extends BrowserFamily("BlackBerry Browser")

  /**
   * Blackbird
   */
  case object Blackbird extends BrowserFamily("Blackbird")

  /**
   * BlackHawk
   */
  case object BlackHawk extends BrowserFamily("BlackHawk")

  /**
   * Blaiz-Bee
   */
  case object BlaizBee extends BrowserFamily("Blaiz-Bee")

  /**
   * Blazer
   */
  case object Blazer extends BrowserFamily("Blazer")

  /**
   * Blekkobot
   */
  case object Blekkobot extends BrowserFamily("Blekkobot")

  /**
   * BlinkaCrawler
   */
  case object BlinkaCrawler extends BrowserFamily("BlinkaCrawler")

  /**
   * BlogBridge
   */
  case object BlogBridge extends BrowserFamily("BlogBridge")

  /**
   * Bloggsi
   */
  case object Bloggsi extends BrowserFamily("Bloggsi")

  /**
   * Bloglines
   */
  case object Bloglines extends BrowserFamily("Bloglines")

  /**
   * BlogPulse
   */
  case object BlogPulse extends BrowserFamily("BlogPulse")

  /**
   * bnf.fr_bot
   */
  case object bnffrbot extends BrowserFamily("bnf.fr_bot")

  /**
   * boitho.com-dc
   */
  case object boithocomdc extends BrowserFamily("boitho.com-dc")

  /**
   * Bolt
   */
  case object Bolt extends BrowserFamily("Bolt")

  /**
   * Bookdog
   */
  case object Bookdog extends BrowserFamily("Bookdog")

  /**
   * BookmarkTracker
   */
  case object BookmarkTracker extends BrowserFamily("BookmarkTracker")

  /**
   * bot-pge.chlooe.com
   */
  case object botpgechlooecom extends BrowserFamily("bot-pge.chlooe.com")

  /**
   * botmobi
   */
  case object botmobi extends BrowserFamily("botmobi")

  /**
   * BotOnParade
   */
  case object BotOnParade extends BrowserFamily("BotOnParade")

  /**
   * Boxxe
   */
  case object Boxxe extends BrowserFamily("Boxxe")

  /**
   * BrownRecluse
   */
  case object BrownRecluse extends BrowserFamily("BrownRecluse")

  /**
   * Browsershots
   */
  case object Browsershots extends BrowserFamily("Browsershots")

  /**
   * BrowseX
   */
  case object BrowseX extends BrowserFamily("BrowseX")

  /**
   * Browzar
   */
  case object Browzar extends BrowserFamily("Browzar")

  /**
   * btbot
   */
  case object btbot extends BrowserFamily("btbot")

  /**
   * Bunjalloo
   */
  case object Bunjalloo extends BrowserFamily("Bunjalloo")

  /**
   * Butterfly
   */
  case object Butterfly extends BrowserFamily("Butterfly")

  /**
   * BuzzRankingBot
   */
  case object BuzzRankingBot extends BrowserFamily("BuzzRankingBot")

  /**
   * Camino
   */
  case object Camino extends BrowserFamily("Camino")

  /**
   * CamontSpider
   */
  case object CamontSpider extends BrowserFamily("CamontSpider")

  /**
   * CareerBot
   */
  case object CareerBot extends BrowserFamily("CareerBot")

  /**
   * Nail
   */
  case object Nail extends BrowserFamily("^Nail")

  /**
   * Castabot
   */
  case object Castabot extends BrowserFamily("Castabot")

  /**
   * CatchBot
   */
  case object CatchBot extends BrowserFamily("CatchBot")

  /**
   * CazoodleBot
   */
  case object CazoodleBot extends BrowserFamily("CazoodleBot")

  /**
   * CCBot
   */
  case object CCBot extends BrowserFamily("CCBot")

  /**
   * ccubee
   */
  case object ccubee extends BrowserFamily("ccubee")

  /**
   * ChangeDetection
   */
  case object ChangeDetection extends BrowserFamily("ChangeDetection")

  /**
   * Charlotte
   */
  case object Charlotte extends BrowserFamily("Charlotte")

  /**
   * Charon
   */
  case object Charon extends BrowserFamily("Charon")

  /**
   * Checkbot
   */
  case object Checkbot extends BrowserFamily("Checkbot")

  /**
   * Cheshire
   */
  case object Cheshire extends BrowserFamily("Cheshire")

  /**
   * Chilkat HTTP .NET
   */
  case object ChilkatHTTP extends BrowserFamily("Chilkat HTTP .NET")

  /**
   * Chrome
   */
  case object Chrome extends BrowserFamily("Chrome")

  /**
   * Chrome Mobile
   */
  case object ChromeMobile extends BrowserFamily("Chrome Mobile")

  /**
   * Chromium
   */
  case object Chromium extends BrowserFamily("Chromium")

  /**
   * City4you
   */
  case object City4you extends BrowserFamily("City4you")

  /**
   * cityreview
   */
  case object cityreview extends BrowserFamily("cityreview")

  /**
   * CJB.NET Proxy
   */
  case object CJBNETProxy extends BrowserFamily("CJB.NET Proxy")

  /**
   * Claws Mail GtkHtml2 plugin
   */
  case object ClawsMailGtkHtml2Plugin extends BrowserFamily("Claws Mail GtkHtml2 plugin")

  /**
   * CligooRobot
   */
  case object CligooRobot extends BrowserFamily("CligooRobot")

  /**
   * coccoc
   */
  case object coccoc extends BrowserFamily("coccoc")

  /**
   * Columbus
   */
  case object Columbus extends BrowserFamily("Columbus")

  /**
   * Combine
   */
  case object Combine extends BrowserFamily("Combine")

  /**
   * CometBird
   */
  case object CometBird extends BrowserFamily("CometBird")

  /**
   * Comodo Dragon
   */
  case object ComodoDragon extends BrowserFamily("Comodo Dragon")

  /**
   * CompSpyBot - Competitive Spying and Scraping
   */
  case object CompSpyBot1 extends BrowserFamily("CompSpyBot/1.0")

  /**
   * Conkeror
   */
  case object Conkeror extends BrowserFamily("Conkeror")

  /**
   * ConveraCrawler
   */
  case object ConveraCrawler extends BrowserFamily("ConveraCrawler")

  /**
   * CoolNovo
   */
  case object CoolNovo extends BrowserFamily("CoolNovo")

  /**
   * copyright sheriff
   */
  case object CopyrightSheriff extends BrowserFamily("copyright sheriff")

  /**
   * CorePlayer
   */
  case object CorePlayer extends BrowserFamily("CorePlayer")

  /**
   * CorpusCrawler
   */
  case object CorpusCrawler extends BrowserFamily("CorpusCrawler")

  /**
   * Covario-IDS
   */
  case object CovarioIDS extends BrowserFamily("Covario-IDS")

  /**
   * CPG Dragonfly RSS Module
   */
  case object CPGDragonflyRSSModule extends BrowserFamily("CPG Dragonfly RSS Module")

  /**
   * Crawler4j
   */
  case object Crawler4j extends BrowserFamily("Crawler4j")

  /**
   * Crazy Browser
   */
  case object CrazyBrowser extends BrowserFamily("Crazy Browser")

  /**
   * csci_b659
   */
  case object cscib659 extends BrowserFamily("csci_b659")

  /**
   * CSE HTML Validator
   */
  case object CSEHTMLValidator extends BrowserFamily("CSE HTML Validator")

  /**
   * cURL
   */
  case object cURL extends BrowserFamily("cURL")

  /**
   * Cyberduck
   */
  case object Cyberduck extends BrowserFamily("Cyberduck")

  /**
   * Cynthia
   */
  case object Cynthia extends BrowserFamily("Cynthia")

  /**
   * D+
   */
  case object DPlus extends BrowserFamily("D+")

  /**
   * DataFountains
   */
  case object DataFountains extends BrowserFamily("DataFountains")

  /**
   * DataparkSearch
   */
  case object DataparkSearch extends BrowserFamily("DataparkSearch")

  /**
   * Daumoa
   */
  case object Daumoa extends BrowserFamily("Daumoa")

  /**
   * DBLBot
   */
  case object DBLBot extends BrowserFamily("DBLBot")

  /**
   * DCPbot
   */
  case object DCPbot extends BrowserFamily("DCPbot")

  /**
   * DealGates Bot
   */
  case object DealGatesBot extends BrowserFamily("DealGates Bot")

  /**
   * Deepnet Explorer
   */
  case object DeepnetExplorer extends BrowserFamily("Deepnet Explorer")

  /**
   * del.icio.us-thumbnails
   */
  case object deliciousThumbnails extends BrowserFamily("del.icio.us-thumbnails")

  /**
   * Dell Web Monitor
   */
  case object DellWebMonitor extends BrowserFamily("Dell Web Monitor")

  /**
   * Demeter
   */
  case object Demeter extends BrowserFamily("Demeter")

  /**
   * DepSpid
   */
  case object DepSpid extends BrowserFamily("DepSpid")

  /**
   * DeskBrowse
   */
  case object DeskBrowse extends BrowserFamily("DeskBrowse")

  /**
   * Dillo
   */
  case object Dillo extends BrowserFamily("Dillo")

  /**
   * Discoverybot is Discovery Engine's web crawler. It downloads text/html documents for use in building our full web
   * search engine.
   */
  case object discobot extends BrowserFamily("discobot")

  /**
   * DKIMRepBot
   */
  case object DKIMRepBot extends BrowserFamily("DKIMRepBot")

  /**
   * DNS-Digger-Explorer
   */
  case object DNSDiggerExplorer extends BrowserFamily("DNS-Digger-Explorer")

  /**
   * DocZilla
   */
  case object DocZilla extends BrowserFamily("DocZilla")

  /**
   * Dolphin
   */
  case object Dolphin extends BrowserFamily("Dolphin")

  /**
   * DomainDB
   */
  case object DomainDB extends BrowserFamily("DomainDB")

  /**
   * Dooble
   */
  case object Dooble extends BrowserFamily("Dooble")

  /**
   * Doris
   */
  case object Doris extends BrowserFamily("Doris")

  /**
   * Dot TK - spider
   */
  case object DotTKSpider extends BrowserFamily("Dot TK - spider")

  /**
   * DotBot
   */
  case object DotBot extends BrowserFamily("DotBot")

  /**
   * dotSemantic
   */
  case object dotSemantic extends BrowserFamily("dotSemantic")

  /**
   * DownloadStudio
   */
  case object DownloadStudio extends BrowserFamily("DownloadStudio")

  /**
   * DripfeedBot
   */
  case object DripfeedBot extends BrowserFamily("DripfeedBot")

  /**
   * DuckDuckBot
   */
  case object DuckDuckBot extends BrowserFamily("DuckDuckBot")

  /**
   * DuckDuckPreview
   */
  case object DuckDuckPreview extends BrowserFamily("DuckDuckPreview")

  /**
   * e-SocietyRobot
   */
  case object eSocietyRobot extends BrowserFamily("e-SocietyRobot")

  /**
   * EasyBib AutoCite
   */
  case object EasyBibAutoCite extends BrowserFamily("EasyBib AutoCite")

  /**
   * eCairn-Grabber
   */
  case object eCairnGrabber extends BrowserFamily("eCairn-Grabber")

  /**
   * Edbrowse
   */
  case object Edbrowse extends BrowserFamily("Edbrowse")

  /**
   * EDI
   */
  case object EDI extends BrowserFamily("EDI")

  /**
   * EdisterBot
   */
  case object EdisterBot extends BrowserFamily("EdisterBot")

  /**
   * egothor
   */
  case object egothor extends BrowserFamily("egothor")

  /**
   * ejupiter.com
   */
  case object ejupiter extends BrowserFamily("ejupiter.com")

  /**
   * Element Browser
   */
  case object ElementBrowser extends BrowserFamily("Element Browser")

  /**
   * Elinks
   */
  case object Elinks extends BrowserFamily("Elinks")

  /**
   * EnaBot
   */
  case object EnaBot extends BrowserFamily("EnaBot")

  /**
   * Enigma browser
   */
  case object Enigmabrowser extends BrowserFamily("Enigma browser")

  /**
   * Enterprise_Search
   */
  case object EnterpriseSearch extends BrowserFamily("Enterprise_Search")

  /**
   * envolk
   */
  case object envolk extends BrowserFamily("envolk")

  /**
   * Epic
   */
  case object Epic extends BrowserFamily("Epic")

  /**
   * Epiphany
   */
  case object Epiphany extends BrowserFamily("Epiphany")

  /**
   * Espial TV Browser
   */
  case object EspialTVBrowser extends BrowserFamily("Espial TV Browser")

  /**
   * Eudora
   */
  case object Eudora extends BrowserFamily("Eudora")

  /**
   * EuripBot
   */
  case object EuripBot extends BrowserFamily("EuripBot")

  /**
   * Eurobot
   */
  case object Eurobot extends BrowserFamily("Eurobot")

  /**
   * EventGuruBot
   */
  case object EventGuruBot extends BrowserFamily("EventGuruBot")

  /**
   * EventMachine
   */
  case object EventMachine extends BrowserFamily("EventMachine")

  /**
   * Evolution/Camel.Stream
   */
  case object EvolutionCamelStream extends BrowserFamily("Evolution/Camel.Stream")

  /**
   * EvriNid
   */
  case object EvriNid extends BrowserFamily("EvriNid")

  /**
   * Exabot
   */
  case object Exabot extends BrowserFamily("Exabot")

  /**
   * ExactSEEK
   */
  case object ExactSEEK extends BrowserFamily("ExactSEEK")

  /**
   * Ezooms
   */
  case object Ezooms extends BrowserFamily("Ezooms")

  /**
   * FacebookExternalHit
   */
  case object FacebookExternalHit extends BrowserFamily("FacebookExternalHit")

  /**
   * factbot
   */
  case object factbot extends BrowserFamily("factbot")

  /**
   * FairShare
   */
  case object FairShare extends BrowserFamily("FairShare")

  /**
   * Falconsbot
   */
  case object Falconsbot extends BrowserFamily("Falconsbot")

  /**
   * FAST Enterprise Crawler
   */
  case object FASTEnterpriseCrawler extends BrowserFamily("FAST Enterprise Crawler")

  /**
   * FAST MetaWeb Crawler
   */
  case object FASTMetaWebCrawler extends BrowserFamily("FAST MetaWeb Crawler")

  /**
   * Fastladder FeedFetcher
   */
  case object FastladderFeedFetcher extends BrowserFamily("Fastladder FeedFetcher")

  /**
   * FauBot
   */
  case object FauBot extends BrowserFamily("FauBot")

  /**
   * favorstarbot
   */
  case object favorstarbot extends BrowserFamily("favorstarbot")

  /**
   * Feed::Find
   */
  case object FeedFind extends BrowserFamily("Feed::Find")

  /**
   * Feed Viewer
   */
  case object FeedViewer extends BrowserFamily("Feed Viewer")

  /**
   * FeedCatBot
   */
  case object FeedCatBot extends BrowserFamily("FeedCatBot")

  /**
   * FeedDemon
   */
  case object FeedDemon extends BrowserFamily("FeedDemon")

  /**
   * Feedfetcher-Google
   */
  case object FeedfetcherGoogle extends BrowserFamily("Feedfetcher-Google")

  /**
   * FeedFinder/bloggz.se
   */
  case object FeedFinderbloggzse extends BrowserFamily("FeedFinder/bloggz.se")

  /**
   * FeedParser
   */
  case object FeedParser extends BrowserFamily("FeedParser")

  /**
   * FeedValidator
   */
  case object FeedValidator extends BrowserFamily("FeedValidator")

  /**
   * Findexa Crawler
   */
  case object FindexaCrawler extends BrowserFamily("Findexa Crawler")

  /**
   * findlinks
   */
  case object findlinks extends BrowserFamily("findlinks")

  /**
   * Firebird (old name for Firefox)
   */
  case object Firebird extends BrowserFamily("Firebird (old name for Firefox)")

  /**
   * Firefox
   */
  case object Firefox extends BrowserFamily("Firefox")

  /**
   * Firefox (BonEcho)
   */
  case object FirefoxBonEcho extends BrowserFamily("Firefox (BonEcho)")

  /**
   * Firefox (GranParadiso)
   */
  case object FirefoxGranParadiso extends BrowserFamily("Firefox (GranParadiso)")

  /**
   * Firefox (Lorentz)
   */
  case object FirefoxLorentz extends BrowserFamily("Firefox (Lorentz)")

  /**
   * Firefox (Minefield)
   */
  case object FirefoxMinefield extends BrowserFamily("Firefox (Minefield)")

  /**
   * Firefox (Namoroka)
   */
  case object FirefoxNamoroka extends BrowserFamily("Firefox (Namoroka)")

  /**
   * Firefox (Shiretoko)
   */
  case object FirefoxShiretoko extends BrowserFamily("Firefox (Shiretoko)")

  /**
   * Fireweb Navigator
   */
  case object FirewebNavigator extends BrowserFamily("Fireweb Navigator")

  /**
   * Flatland Industries Web Spider
   */
  case object FlatlandIndustriesWebSpider extends BrowserFamily("Flatland Industries Web Spider")

  /**
   * flatlandbot
   */
  case object flatlandbot extends BrowserFamily("flatlandbot")

  /**
   * FlightDeckReportsBot
   */
  case object FlightDeckReportsBot extends BrowserFamily("FlightDeckReportsBot")

  /**
   * FlipboardProxy
   */
  case object FlipboardProxy extends BrowserFamily("FlipboardProxy")

  /**
   * Flock
   */
  case object Flock extends BrowserFamily("Flock")

  /**
   * Flocke bot
   */
  case object Flockebot extends BrowserFamily("Flocke bot")

  /**
   * Fluid
   */
  case object Fluid extends BrowserFamily("Fluid")

  /**
   * FlyCast
   */
  case object FlyCast extends BrowserFamily("FlyCast")

  /**
   * FollowSite Bot
   */
  case object FollowSiteBot extends BrowserFamily("FollowSite Bot")

  /**
   * foobar2000
   */
  case object foobar2000 extends BrowserFamily("foobar2000")

  /**
   * Fooooo_Web_Video_Crawl
   */
  case object Fooooo_WebVideoCrawl extends BrowserFamily("Fooooo_Web_Video_Crawl")

  /**
   * Forschungsportal
   */
  case object Forschungsportal extends BrowserFamily("Forschungsportal")

  /**
   * Francis
   */
  case object Francis extends BrowserFamily("Francis")

  /**
   * Funambol Mozilla Sync Client
   */
  case object FunambolMozillaSyncClient extends BrowserFamily("Funambol Mozilla Sync Client")

  /**
   * Funambol Outlook Sync Client
   */
  case object FunambolOutlookSyncClient extends BrowserFamily("Funambol Outlook Sync Client")

  /**
   * FunnelBack
   */
  case object FunnelBack extends BrowserFamily("FunnelBack")

  /**
   * FurlBot
   */
  case object FurlBot extends BrowserFamily("FurlBot")

  /**
   * FyberSpider
   */
  case object FyberSpider extends BrowserFamily("FyberSpider")

  /**
   * g2crawler
   */
  case object g2crawler extends BrowserFamily("g2crawler")

  /**
   * Gaisbot
   */
  case object Gaisbot extends BrowserFamily("Gaisbot")

  /**
   * Galeon
   */
  case object Galeon extends BrowserFamily("Galeon")

  /**
   * Gallent Search Spider
   */
  case object GallentSearchSpider extends BrowserFamily("Gallent Search Spider")

  /**
   * GarlikCrawler
   */
  case object GarlikCrawler extends BrowserFamily("GarlikCrawler")

  /**
   * GcMail
   */
  case object GcMail extends BrowserFamily("GcMail")

  /**
   * genieBot
   */
  case object genieBot extends BrowserFamily("genieBot")

  /**
   * GeonaBot
   */
  case object GeonaBot extends BrowserFamily("GeonaBot")

  /**
   * GetRight
   */
  case object GetRight extends BrowserFamily("GetRight")

  /**
   * Giant/1.0
   */
  case object Giant extends BrowserFamily("Giant")

  /**
   * Gigabot
   */
  case object Gigabot extends BrowserFamily("Gigabot")

  /**
   * GingerCrawler
   */
  case object GingerCrawler extends BrowserFamily("GingerCrawler")

  /**
   * Girafabot
   */
  case object Girafabot extends BrowserFamily("Girafabot")

  /**
   * GlobalMojo
   */
  case object GlobalMojo extends BrowserFamily("GlobalMojo")

  /**
   * GnomeVFS
   */
  case object GnomeVFS extends BrowserFamily("GnomeVFS")

  /**
   * GO Browser
   */
  case object GOBrowser extends BrowserFamily("GO Browser")

  /**
   * GOFORITBOT
   */
  case object GOFORITBOT extends BrowserFamily("GOFORITBOT")

  /**
   * GoldenPod
   */
  case object GoldenPod extends BrowserFamily("GoldenPod")

  /**
   * GOM Player
   */
  case object GOMPlayer extends BrowserFamily("GOM Player")

  /**
   * gonzo
   */
  case object gonzo extends BrowserFamily("gonzo")

  /**
   * Google App Engine
   */
  case object GoogleAppEngine extends BrowserFamily("Google App Engine")

  /**
   * Google Earth
   */
  case object GoogleEarth extends BrowserFamily("Google Earth")

  /**
   * Google Friend Connect
   */
  case object GoogleFriendConnect extends BrowserFamily("Google Friend Connect")

  /**
   * Google Listen
   */
  case object GoogleListen extends BrowserFamily("Google Listen")

  /**
   * Google Rich Snippets Testing Tool
   */
  case object GoogleRichSnippetsTestingTool extends BrowserFamily("Google Rich Snippets Testing Tool")

  /**
   * Google Wireless Transcoder
   */
  case object GoogleWirelessTranscoder extends BrowserFamily("Google Wireless Transcoder")

  /**
   * Googlebot
   */
  case object Googlebot extends BrowserFamily("Googlebot")

  /**
   * Googlebot-Mobile
   */
  case object GooglebotMobile extends BrowserFamily("Googlebot-Mobile")

  /**
   * gPodder
   */
  case object gPodder extends BrowserFamily("gPodder")

  /**
   * GrapeshotCrawler
   */
  case object GrapeshotCrawler extends BrowserFamily("GrapeshotCrawler")

  /**
   * GreatNews
   */
  case object GreatNews extends BrowserFamily("GreatNews")

  /**
   * GreenBrowser
   */
  case object GreenBrowser extends BrowserFamily("GreenBrowser")

  /**
   * Gregarius
   */
  case object Gregarius extends BrowserFamily("Gregarius")

  /**
   * GSiteCrawler
   */
  case object GSiteCrawler extends BrowserFamily("GSiteCrawler")

  /**
   * GStreamer
   */
  case object GStreamer extends BrowserFamily("GStreamer")

  /**
   * GurujiBot
   */
  case object GurujiBot extends BrowserFamily("GurujiBot")

  /**
   * Hailoobot
   */
  case object Hailoobot extends BrowserFamily("Hailoobot")

  /**
   * HatenaScreenshot
   */
  case object HatenaScreenshot extends BrowserFamily("HatenaScreenshot")

  /**
   * HeartRails_Capture
   */
  case object HeartRailsCapture extends BrowserFamily("HeartRails_Capture")

  /**
   * heritrix
   */
  case object heritrix extends BrowserFamily("heritrix")

  /**
   * HiddenMarket
   */
  case object HiddenMarket extends BrowserFamily("HiddenMarket")

  /**
   * Holmes
   */
  case object Holmes extends BrowserFamily("Holmes")

  /**
   * HolmesBot
   */
  case object HolmesBot extends BrowserFamily("HolmesBot")

  /**
   * HomeTags
   */
  case object HomeTags extends BrowserFamily("HomeTags")

  /**
   * HooWWWer
   */
  case object HooWWWer extends BrowserFamily("HooWWWer")

  /**
   * HostTracker.com
   */
  case object HostTrackercom extends BrowserFamily("HostTracker.com")

  /**
   * HotJava
   */
  case object HotJava extends BrowserFamily("HotJava")

  /**
   * ht://Dig
   */
  case object htDig extends BrowserFamily("ht://Dig")

  /**
   * HTML2JPG
   */
  case object HTML2JPG extends BrowserFamily("HTML2JPG")

  /**
   * HTMLayout
   */
  case object HTMLayout extends BrowserFamily("HTMLayout")

  /**
   * HTMLParser
   */
  case object HTMLParser extends BrowserFamily("HTMLParser")

  /**
   * HTTP nagios plugin
   */
  case object HTTPnagiosplugin extends BrowserFamily("HTTP nagios plugin")

  /**
   * HTTP_Request2
   */
  case object HTTPRequest2 extends BrowserFamily("HTTP_Request2")

  /**
   * HTTrack
   */
  case object HTTrack extends BrowserFamily("HTTrack")

  /**
   * HuaweiSymantecSpider
   */
  case object HuaweiSymantecSpider extends BrowserFamily("HuaweiSymantecSpider")

  /**
   * Hv3
   */
  case object Hv3 extends BrowserFamily("Hv3")

  /**
   * Hydra Browser
   */
  case object HydraBrowser extends BrowserFamily("Hydra Browser")

  /**
   * ia_archiver
   */
  case object iaarchiver extends BrowserFamily("ia_archiver")

  /**
   * iaskspider
   */
  case object iaskspider extends BrowserFamily("iaskspider")

  /**
   * IBrowse
   */
  case object IBrowse extends BrowserFamily("IBrowse")

  /**
   * iCab
   */
  case object iCab extends BrowserFamily("iCab")

  /**
   * iCatcher!
   */
  case object iCatcher extends BrowserFamily("iCatcher!")

  /**
   * ICC-Crawler
   */
  case object ICCCrawler extends BrowserFamily("ICC-Crawler")

  /**
   * ICE browser
   */
  case object ICEbrowser extends BrowserFamily("ICE browser")

  /**
   * IceApe
   */
  case object IceApe extends BrowserFamily("IceApe")

  /**
   * IceCat
   */
  case object IceCat extends BrowserFamily("IceCat")

  /**
   * IceDragon: A faster, more secure version of Firefox
   */
  case object IceDragon extends BrowserFamily("IceDragon")

  /**
   * IceWeasel
   */
  case object IceWeasel extends BrowserFamily("IceWeasel")

  /**
   * ICF_Site_Crawler
   */
  case object ICFSiteCrawler extends BrowserFamily("ICF_Site_Crawler")

  /**
   * ichiro
   */
  case object ichiro extends BrowserFamily("ichiro")

  /**
   * iCjobs
   */
  case object iCjobs extends BrowserFamily("iCjobs")

  /**
   * Internet Explorer
   */
  case object IE extends BrowserFamily("IE")

  /**
   * Internet Explorer Mobile
   */
  case object IEMobile extends BrowserFamily("IE Mobile")

  /**
   * Internet Explorer RSS reader
   */
  case object IERSSreader extends BrowserFamily("IE RSS reader")

  /**
   * iGetter
   */
  case object iGetter extends BrowserFamily("iGetter")

  /**
   * iGooMap
   */
  case object iGooMap extends BrowserFamily("iGooMap")

  /**
   * IlseBot
   */
  case object IlseBot extends BrowserFamily("IlseBot")

  /**
   * IlTrovatore
   */
  case object IlTrovatore extends BrowserFamily("IlTrovatore")

  /**
   * IlTrovatore-Setaccio
   */
  case object IlTrovatoreSetaccio extends BrowserFamily("IlTrovatore-Setaccio")

  /**
   * imbot
   */
  case object imbot extends BrowserFamily("imbot")

  /**
   * Indy Library
   */
  case object IndyLibrary extends BrowserFamily("Indy Library")

  /**
   * Influencebot
   */
  case object Influencebot extends BrowserFamily("Influencebot")

  /**
   * InfociousBot
   */
  case object InfociousBot extends BrowserFamily("InfociousBot")

  /**
   * Infohelfer
   */
  case object Infohelfer extends BrowserFamily("Infohelfer")

  /**
   * InternetSeer
   */
  case object InternetSeer extends BrowserFamily("InternetSeer")

  /**
   * InternetSurfboard
   */
  case object InternetSurfboard extends BrowserFamily("InternetSurfboard")

  /**
   * Ipselonbot
   */
  case object Ipselonbot extends BrowserFamily("Ipselonbot")

  /**
   * iRider
   */
  case object iRider extends BrowserFamily("iRider")

  /**
   * IRLbot
   */
  case object IRLbot extends BrowserFamily("IRLbot")

  /**
   * Iron
   */
  case object Iron extends BrowserFamily("Iron")

  /**
   * iSiloX
   */
  case object iSiloX extends BrowserFamily("iSiloX")

  /**
   * iSiloXC
   */
  case object iSiloXC extends BrowserFamily("iSiloXC")

  /**
   * iTunes
   */
  case object iTunes extends BrowserFamily("iTunes")

  /**
   * iVideo
   */
  case object iVideo extends BrowserFamily("iVideo")

  /**
   * IXR lib
   */
  case object IXRlib extends BrowserFamily("IXR lib")

  /**
   * JadynAve
   */
  case object JadynAve extends BrowserFamily("JadynAve")

  /**
   * JadynAveBot
   */
  case object JadynAveBot extends BrowserFamily("JadynAveBot")

  /**
   * Jakarta Commons-HttpClient
   */
  case object JakartaCommonsHttpClient extends BrowserFamily("Jakarta Commons-HttpClient")

  /**
   * Jambot
   */
  case object Jambot extends BrowserFamily("Jambot")

  /**
   * Jamcast
   */
  case object Jamcast extends BrowserFamily("Jamcast")

  /**
   * Jasmine
   */
  case object Jasmine extends BrowserFamily("Jasmine")

  /**
   * Java
   */
  case object Java extends BrowserFamily("Java")

  /**
   * JikeSpider
   */
  case object JikeSpider extends BrowserFamily("JikeSpider")

  /**
   * Job Roboter Spider
   */
  case object JobRoboterSpider extends BrowserFamily("Job Roboter Spider")

  /**
   * JoBo
   */
  case object JoBo extends BrowserFamily("JoBo")

  /**
   * JS-Kit/Echo
   */
  case object JSKitEcho extends BrowserFamily("JS-Kit/Echo")

  /**
   * JUST-CRAWLER
   */
  case object JUSTCRAWLER extends BrowserFamily("JUST-CRAWLER")

  /**
   * Jyxobot
   */
  case object Jyxobot extends BrowserFamily("Jyxobot")

  /**
   * K-Meleon
   */
  case object KMeleon extends BrowserFamily("K-Meleon")

  /**
   * K-Ninja
   */
  case object KNinja extends BrowserFamily("K-Ninja")

  /**
   * Kakle Bot
   */
  case object KakleBot extends BrowserFamily("Kakle Bot")

  /**
   * Kalooga
   */
  case object Kalooga extends BrowserFamily("Kalooga")

  /**
   * Kapiko
   */
  case object Kapiko extends BrowserFamily("Kapiko")

  /**
   * Karneval-Bot
   */
  case object KarnevalBot extends BrowserFamily("Karneval-Bot")

  /**
   * Kazehakase
   */
  case object Kazehakase extends BrowserFamily("Kazehakase")

  /**
   * KeywenBot
   */
  case object KeywenBot extends BrowserFamily("KeywenBot")

  /**
   * KeywordDensityRobot
   */
  case object KeywordDensityRobot extends BrowserFamily("KeywordDensityRobot")

  /**
   * Kindle Browser
   */
  case object KindleBrowser extends BrowserFamily("Kindle Browser")

  /**
   * Kirix Strata
   */
  case object KirixStrata extends BrowserFamily("Kirix Strata")

  /**
   * KKman
   */
  case object KKman extends BrowserFamily("KKman")

  /**
   * Klondike
   */
  case object Klondike extends BrowserFamily("Klondike")

  /**
   * Kongulo
   */
  case object Kongulo extends BrowserFamily("Kongulo")

  /**
   * Konqueror
   */
  case object Konqueror extends BrowserFamily("Konqueror")

  /**
   * KRetrieve
   */
  case object KRetrieve extends BrowserFamily("KRetrieve")

  /**
   * Krugle
   */
  case object Krugle extends BrowserFamily("Krugle")

  /**
   * ksibot
   */
  case object ksibot extends BrowserFamily("ksibot")

  /**
   * Kylo
   */
  case object Kylo extends BrowserFamily("Kylo")

  /**
   * L.webis
   */
  case object Lwebis extends BrowserFamily("L.webis")

  /**
   * LapozzBot
   */
  case object LapozzBot extends BrowserFamily("LapozzBot")

  /**
   * Larbin
   */
  case object Larbin extends BrowserFamily("Larbin")

  /**
   * LBrowser
   */
  case object LBrowser extends BrowserFamily("LBrowser")

  /**
   * LeechCraft
   */
  case object LeechCraft extends BrowserFamily("LeechCraft")

  /**
   * LemurWebCrawler
   */
  case object LemurWebCrawler extends BrowserFamily("LemurWebCrawler")

  /**
   * LexxeBot
   */
  case object LexxeBot extends BrowserFamily("LexxeBot")

  /**
   * LFTP
   */
  case object LFTP extends BrowserFamily("LFTP")

  /**
   * LibSoup
   */
  case object LibSoup extends BrowserFamily("LibSoup")

  /**
   * libwww-perl
   */
  case object libwwwperl extends BrowserFamily("libwww-perl")

  /**
   * Liferea
   */
  case object Liferea extends BrowserFamily("Liferea")

  /**
   * Lijit
   */
  case object Lijit extends BrowserFamily("Lijit")

  /**
   * LinguaBot
   */
  case object LinguaBot extends BrowserFamily("LinguaBot")

  /**
   * Linguee Bot
   */
  case object LingueeBot extends BrowserFamily("Linguee Bot")

  /**
   * Link Valet Online
   */
  case object LinkValetOnline extends BrowserFamily("Link Valet Online")

  /**
   * LinkAider
   */
  case object LinkAider extends BrowserFamily("LinkAider")

  /**
   * LinkbackPlugin for Laconica
   */
  case object LinkbackPluginforLaconica extends BrowserFamily("LinkbackPlugin for Laconica")

  /**
   * LinkChecker
   */
  case object LinkChecker extends BrowserFamily("LinkChecker")

  /**
   * linkdex.com
   */
  case object linkdexcom extends BrowserFamily("linkdex.com")

  /**
   * LinkExaminer
   */
  case object LinkExaminer extends BrowserFamily("LinkExaminer")

  /**
   * Links
   */
  case object Links extends BrowserFamily("Links")

  /**
   * linksmanager_bot
   */
  case object linksmanagerbot extends BrowserFamily("linksmanager_bot")

  /**
   * LinkWalker
   */
  case object LinkWalker extends BrowserFamily("LinkWalker")

  /**
   * livedoor ScreenShot
   */
  case object livedoorScreenShot extends BrowserFamily("livedoor ScreenShot")

  /**
   * lmspider
   */
  case object lmspider extends BrowserFamily("lmspider")

  /**
   * Lobo
   */
  case object Lobo extends BrowserFamily("Lobo")

  /**
   * lolifox
   */
  case object lolifox extends BrowserFamily("lolifox")

  /**
   * Lotus Notes
   */
  case object LotusNotes extends BrowserFamily("Lotus Notes")

  /**
   * Lunascape
   */
  case object Lunascape extends BrowserFamily("Lunascape")

  /**
   * LWP::Simple
   */
  case object LWPSimple extends BrowserFamily("LWP::Simple")

  /**
   * Lynx
   */
  case object Lynx extends BrowserFamily("Lynx")

  /**
   * Madfox
   */
  case object Madfox extends BrowserFamily("Madfox")

  /**
   * magpie-crawler
   */
  case object magpiecrawler extends BrowserFamily("magpie-crawler")

  /**
   * MagpieRSS
   */
  case object MagpieRSS extends BrowserFamily("MagpieRSS")

  /**
   * Mahiti Crawler
   */
  case object MahitiCrawler extends BrowserFamily("Mahiti Crawler")

  /**
   * Mail.RU
   */
  case object MAILRU extends BrowserFamily("Mail.Ru")

  /**
   * Maple browser
   */
  case object Maplebrowser extends BrowserFamily("Maple browser")

  /**
   * Maxthon
   */
  case object Maxthon extends BrowserFamily("Maxthon")

  /**
   * Mechanize
   */
  case object Mechanize extends BrowserFamily("Mechanize")

  /**
   * Megatext
   */
  case object Megatext extends BrowserFamily("Megatext")

  /**
   * MetaGeneratorCrawler
   */
  case object MetaGeneratorCrawler extends BrowserFamily("MetaGeneratorCrawler")

  /**
   * MetaJobBot
   */
  case object MetaJobBot extends BrowserFamily("MetaJobBot")

  /**
   * MetamojiCrawler
   */
  case object MetamojiCrawler extends BrowserFamily("MetamojiCrawler")

  /**
   * Metaspinner/0.01
   */
  case object Metaspinner extends BrowserFamily("Metaspinner")

  /**
   * MetaTagRobot
   */
  case object MetaTagRobot extends BrowserFamily("MetaTagRobot")

  /**
   * MetaURI
   */
  case object MetaURI extends BrowserFamily("MetaURI")

  /**
   * MIA Bot
   */
  case object MIABot extends BrowserFamily("MIA Bot")

  /**
   * MicroB
   */
  case object MicroB extends BrowserFamily("MicroB")

  /**
   * Microsoft Office Existence Discovery
   */
  case object MicrosoftOfficeExistenceDiscovery extends BrowserFamily("Microsoft Office Existence Discovery")

  /**
   * Microsoft WebDAV client
   */
  case object MicrosoftWebDAVclient extends BrowserFamily("Microsoft WebDAV client")

  /**
   * Midori
   */
  case object Midori extends BrowserFamily("Midori")

  /**
   * Mini Browser
   */
  case object MiniBrowser extends BrowserFamily("Mini Browser")

  /**
   * Minimo
   */
  case object Minimo extends BrowserFamily("Minimo")

  /**
   * miniRank
   */
  case object miniRank extends BrowserFamily("miniRank")

  /**
   * Miro
   */
  case object Miro extends BrowserFamily("Miro")

  /**
   * MJ12bot
   */
  case object MJ12bot extends BrowserFamily("MJ12bot")

  /**
   * MLBot
   */
  case object MLBot extends BrowserFamily("MLBot")

  /**
   * MnoGoSearch
   */
  case object MnoGoSearch extends BrowserFamily("MnoGoSearch")

  /**
   * Moatbot
   */
  case object Moatbot extends BrowserFamily("Moatbot")

  /**
   * moba-crawler
   */
  case object mobacrawler extends BrowserFamily("moba-crawler")

  /**
   * Mobile Firefox
   */
  case object MobileFirefox extends BrowserFamily("Mobile Firefox")

  /**
   * Mobile Safari
   */
  case object MobileSafari extends BrowserFamily("Mobile Safari")

  /**
   * MojeekBot
   */
  case object MojeekBot extends BrowserFamily("MojeekBot")

  /**
   * Motoricerca-Robots.txt-Checker
   */
  case object MotoricercaRobotstxtChecker extends BrowserFamily("Motoricerca-Robots.txt-Checker")

  /**
   * Motorola Internet Browser
   */
  case object MotorolaInternetBrowser extends BrowserFamily("Motorola Internet Browser")

  /**
   * mozDex
   */
  case object mozDex extends BrowserFamily("mozDex")

  /**
   * Mozilla
   */
  case object Mozilla extends BrowserFamily("Mozilla")

  /**
   * Mp3Bot
   */
  case object Mp3Bot extends BrowserFamily("Mp3Bot")

  /**
   * MPlayer
   */
  case object MPlayer extends BrowserFamily("MPlayer")

  /**
   * MPlayer2
   */
  case object MPlayer2 extends BrowserFamily("MPlayer2")

  /**
   * MQbot
   */
  case object MQbot extends BrowserFamily("MQbot")

  /**
   * MSNBot
   */
  case object MSNBot extends BrowserFamily("MSNBot")

  /**
   * MSRBOT
   */
  case object MSRBOT extends BrowserFamily("MSRBOT")

  /**
   * muCommander
   */
  case object muCommander extends BrowserFamily("muCommander")

  /**
   * Multi-Browser XP
   */
  case object MultiBrowserXP extends BrowserFamily("Multi-Browser XP")

  /**
   * MultiCrawler
   */
  case object MultiCrawler extends BrowserFamily("MultiCrawler")

  /**
   * Multipage Validator
   */
  case object MultipageValidator extends BrowserFamily("Multipage Validator")

  /**
   * MultiZilla
   */
  case object MultiZilla extends BrowserFamily("MultiZilla")

  /**
   * My Internet Browser
   */
  case object MyInternetBrowser extends BrowserFamily("My Internet Browser")

  /**
   * MyFamilyBot
   */
  case object MyFamilyBot extends BrowserFamily("MyFamilyBot")

  /**
   * Najdi.si
   */
  case object Najdisi extends BrowserFamily("Najdi.si")

  /**
   * NaverBot
   */
  case object NaverBot extends BrowserFamily("NaverBot")

  /**
   * navissobot
   */
  case object navissobot extends BrowserFamily("navissobot")

  /**
   * NCSA Mosaic
   */
  case object NCSAMosaic extends BrowserFamily("NCSA Mosaic")

  /**
   * NerdByNature.Bot
   */
  case object NerdByNatureBot extends BrowserFamily("NerdByNature.Bot")

  /**
   * nestReader
   */
  case object nestReader extends BrowserFamily("nestReader")

  /**
   * NetBox
   */
  case object NetBox extends BrowserFamily("NetBox")

  /**
   * NetCaptor
   */
  case object NetCaptor extends BrowserFamily("NetCaptor")

  /**
   * NetcraftSurveyAgent
   */
  case object NetcraftSurveyAgent extends BrowserFamily("NetcraftSurveyAgent")

  /**
   * netEstate Crawler
   */
  case object netEstateCrawler extends BrowserFamily("netEstate Crawler")

  /**
   * NetFront
   */
  case object NetFront extends BrowserFamily("NetFront")

  /**
   * NetFront Mobile Content Viewer
   */
  case object NetFrontMobileContentViewer extends BrowserFamily("NetFront Mobile Content Viewer")

  /**
   * Netintelligence LiveAssessment
   */
  case object NetintelligenceLiveAssessment extends BrowserFamily("Netintelligence LiveAssessment")

  /**
   * NetNewsWire
   */
  case object NetNewsWire extends BrowserFamily("NetNewsWire")

  /**
   * NetPositive
   */
  case object NetPositive extends BrowserFamily("NetPositive")

  /**
   * NetResearchServer
   */
  case object NetResearchServer extends BrowserFamily("NetResearchServer")

  /**
   * Netscape Navigator
   */
  case object NetscapeNavigator extends BrowserFamily("Netscape Navigator")

  /**
   * Netseer
   */
  case object Netseer extends BrowserFamily("Netseer")

  /**
   * NetSurf
   */
  case object NetSurf extends BrowserFamily("NetSurf")

  /**
   * Netvibes feed reader
   */
  case object Netvibesfeedreader extends BrowserFamily("Netvibes feed reader")

  /**
   * NetWhatCrawler
   */
  case object NetWhatCrawler extends BrowserFamily("NetWhatCrawler")

  /**
   * Newsbeuter
   */
  case object Newsbeuter extends BrowserFamily("Newsbeuter")

  /**
   * NewsBreak
   */
  case object NewsBreak extends BrowserFamily("NewsBreak")

  /**
   * NewsFox
   */
  case object NewsFox extends BrowserFamily("NewsFox")

  /**
   * NewsGatorOnline
   */
  case object NewsGatorOnline extends BrowserFamily("NewsGatorOnline")

  /**
   * NextGenSearchBot
   */
  case object NextGenSearchBot extends BrowserFamily("NextGenSearchBot")

  /**
   * nextthing.org
   */
  case object nextthingorg extends BrowserFamily("nextthing.org")

  /**
   * NFReader
   */
  case object NFReader extends BrowserFamily("NFReader")

  /**
   * NG
   */
  case object NG extends BrowserFamily("NG")

  /**
   * NG-Search
   */
  case object NGSearch extends BrowserFamily("NG-Search")

  /**
   * Nigma.ru
   */
  case object Nigmaru extends BrowserFamily("Nigma.ru")

  /**
   * NimbleCrawler
   */
  case object NimbleCrawler extends BrowserFamily("NimbleCrawler")

  /**
   * NineSky
   */
  case object NineSky extends BrowserFamily("NineSky")

  /**
   * Nintendo Browser
   */
  case object NintendoBrowser extends BrowserFamily("Nintendo Browser")

  /**
   * nodestackbot
   */
  case object nodestackbot extends BrowserFamily("nodestackbot")

  /**
   * Nokia SyncML Client
   */
  case object NokiaSyncMLClient extends BrowserFamily("Nokia SyncML Client")

  /**
   * Nokia Web Browser
   */
  case object NokiaWebBrowser extends BrowserFamily("Nokia Web Browser")

  /**
   * Novell BorderManager
   */
  case object NovellBorderManager extends BrowserFamily("Novell BorderManager")

  /**
   * noyona
   */
  case object noyona extends BrowserFamily("noyona")

  /**
   * NPBot
   */
  case object NPBot extends BrowserFamily("NPBot")

  /**
   * Nuhk
   */
  case object Nuhk extends BrowserFamily("Nuhk")

  /**
   * NuSearch Spider
   */
  case object NuSearchSpider extends BrowserFamily("NuSearch Spider")

  /**
   * Nutch
   */
  case object Nutch extends BrowserFamily("Nutch")

  /**
   * nworm
   */
  case object nworm extends BrowserFamily("nworm")

  /**
   * Nymesis
   */
  case object Nymesis extends BrowserFamily("Nymesis")

  /**
   * Obigo
   */
  case object Obigo extends BrowserFamily("Obigo")

  /**
   * oBot
   */
  case object oBot extends BrowserFamily("oBot")

  /**
   * Ocelli
   */
  case object Ocelli extends BrowserFamily("Ocelli")

  /**
   * Off By One
   */
  case object OffByOne extends BrowserFamily("Off By One")

  /**
   * Offline Explorer
   */
  case object OfflineExplorer extends BrowserFamily("Offline Explorer")

  /**
   * Omea Reader
   */
  case object OmeaReader extends BrowserFamily("Omea Reader")

  /**
   * OmniExplorer_Bot
   */
  case object OmniExplorerBot extends BrowserFamily("OmniExplorer_Bot")

  /**
   * OmniWeb
   */
  case object OmniWeb extends BrowserFamily("OmniWeb")

  /**
   * OnetSzukaj
   */
  case object OnetSzukaj extends BrowserFamily("OnetSzukaj")

  /**
   * Openbot
   */
  case object Openbot extends BrowserFamily("Openbot")

  /**
   * OpenCalaisSemanticProxy
   */
  case object OpenCalaisSemanticProxy extends BrowserFamily("OpenCalaisSemanticProxy")

  /**
   * OpenindexSpider
   */
  case object OpenindexSpider extends BrowserFamily("OpenindexSpider")

  /**
   * Openwave Mobile Browser
   */
  case object OpenwaveMobileBrowser extends BrowserFamily("Openwave Mobile Browser")

  /**
   * Opera
   */
  case object Opera extends BrowserFamily("Opera")

  /**
   * Opera Mini
   */
  case object OperaMini extends BrowserFamily("Opera Mini")

  /**
   * Opera Mobile
   */
  case object OperaMobile extends BrowserFamily("Opera Mobile")

  /**
   * Orbiter
   */
  case object Orbiter extends BrowserFamily("Orbiter")

  /**
   * Orca
   */
  case object Orca extends BrowserFamily("Orca")

  /**
   * Oregano
   */
  case object Oregano extends BrowserFamily("Oregano")

  /**
   * OrgbyBot
   */
  case object OrgbyBot extends BrowserFamily("OrgbyBot")

  /**
   * OsObot
   */
  case object OsObot extends BrowserFamily("OsObot")

  /**
   * Outlook 2007
   */
  case object Outlook2007 extends BrowserFamily("Outlook 2007")

  /**
   * Outlook 2010
   */
  case object Outlook2010 extends BrowserFamily("Outlook 2010")

  /**
   * Outlook 2013
   */
  case object Outlook2013 extends BrowserFamily("Outlook 2013")

  /**
   * OWB
   */
  case object OWB extends BrowserFamily("OWB")

  /**
   * owsBot
   */
  case object owsBot extends BrowserFamily("owsBot")

  /**
   * P3P Validator
   */
  case object P3PValidator extends BrowserFamily("P3P Validator")

  /**
   * page_verifier
   */
  case object pageverifier extends BrowserFamily("page_verifier")

  /**
   * Page2RSS
   */
  case object Page2RSS extends BrowserFamily("Page2RSS")

  /**
   * PageBitesHyperBot
   */
  case object PageBitesHyperBot extends BrowserFamily("PageBitesHyperBot")

  /**
   * PagePeeker
   */
  case object PagePeeker extends BrowserFamily("PagePeeker")

  /**
   * Pale Moon
   */
  case object PaleMoon extends BrowserFamily("Pale Moon")

  /**
   * Palm Pre web browser
   */
  case object PalmPrewebbrowser extends BrowserFamily("Palm Pre web browser")

  /**
   * Panscient web crawler
   */
  case object Panscientwebcrawler extends BrowserFamily("Panscient web crawler")

  /**
   * Paparazzi!
   */
  case object Paparazzi extends BrowserFamily("Paparazzi!")

  /**
   * PaperLiBot
   */
  case object PaperLiBot extends BrowserFamily("PaperLiBot")

  /**
   * ParchBot
   */
  case object ParchBot extends BrowserFamily("ParchBot")

  /**
   * Patriott
   */
  case object Patriott extends BrowserFamily("Patriott")

  /**
   * Pattern is a web mining module for the Python programming language.
   */
  case object Pattern extends BrowserFamily("Pattern")

  /**
   * PEAR HTTP_Request
   */
  case object PEARHTTPRequest extends BrowserFamily("PEAR HTTP_Request")

  /**
   * Peew
   */
  case object Peew extends BrowserFamily("Peew")

  /**
   * percbotspider
   */
  case object percbotspider extends BrowserFamily("percbotspider")

  /**
   * Phaseout
   */
  case object Phaseout extends BrowserFamily("Phaseout")

  /**
   * Phoenix (old name for Firefox)
   */
  case object PhoenixoldnameforFirefox extends BrowserFamily("Phoenix (old name for Firefox)")

  /**
   * PHP
   */
  case object PHP extends BrowserFamily("PHP")

  /**
   * PHP link checker
   */
  case object PHPlinkchecker extends BrowserFamily("PHP link checker")

  /**
   * PHP OpenID library
   */
  case object PHPOpenIDlibrary extends BrowserFamily("PHP OpenID library")

  /**
   * PHPcrawl
   */
  case object PHPcrawl extends BrowserFamily("PHPcrawl")

  /**
   * pingdom.com_bot
   */
  case object pingdomcombot extends BrowserFamily("pingdom.com_bot")

  /**
   * Pixray-Seeker
   */
  case object PixraySeeker extends BrowserFamily("Pixray-Seeker")

  /**
   * Plex Media Center
   */
  case object PlexMediaCenter extends BrowserFamily("Plex Media Center")

  /**
   * Plukkie
   */
  case object Plukkie extends BrowserFamily("Plukkie")

  /**
   * Pocket Tunes
   */
  case object PocketTunes extends BrowserFamily("Pocket Tunes")

  /**
   * PocoMail
   */
  case object PocoMail extends BrowserFamily("PocoMail")

  /**
   * Podkicker
   */
  case object Podkicker extends BrowserFamily("Podkicker")

  /**
   * POE-Component-Client-HTTP
   */
  case object POEComponentClientHTTP extends BrowserFamily("POE-Component-Client-HTTP")

  /**
   * Pogodak.co.yu
   */
  case object Pogodakcoyu extends BrowserFamily("Pogodak.co.yu")

  /**
   * Polaris
   */
  case object Polaris extends BrowserFamily("Polaris")

  /**
   * polixea.de
   */
  case object polixeade extends BrowserFamily("polixea.de")

  /**
   * Pompos
   */
  case object Pompos extends BrowserFamily("Pompos")

  /**
   * Postbox
   */
  case object Postbox extends BrowserFamily("Postbox")

  /**
   * posterus
   */
  case object posterus extends BrowserFamily("posterus")

  /**
   * PostPost
   */
  case object PostPost extends BrowserFamily("PostPost")

  /**
   * Powermarks
   */
  case object Powermarks extends BrowserFamily("Powermarks")

  /**
   * Prism
   */
  case object Prism extends BrowserFamily("Prism")

  /**
   * ProCogBot
   */
  case object ProCogBot extends BrowserFamily("ProCogBot")

  /**
   * proximic
   */
  case object proximic extends BrowserFamily("proximic")

  /**
   * PRTG Network Monitor
   */
  case object PRTGNetworkMonitor extends BrowserFamily("PRTG Network Monitor")

  /**
   * PS Vita browser
   */
  case object PSVitabrowser extends BrowserFamily("PS Vita browser")

  /**
   * psbot
   */
  case object psbot extends BrowserFamily("psbot")

  /**
   * ptd-crawler
   */
  case object ptdcrawler extends BrowserFamily("ptd-crawler")

  /**
   * Public Radio Player
   */
  case object PublicRadioPlayer extends BrowserFamily("Public Radio Player")

  /**
   * PycURL
   */
  case object PycURL extends BrowserFamily("PycURL")

  /**
   * Python-requests
   */
  case object Pythonrequests extends BrowserFamily("Python-requests")

  /**
   * Python-urllib
   */
  case object Pythonurllib extends BrowserFamily("Python-urllib")

  /**
   * Python-webchecker
   */
  case object Pythonwebchecker extends BrowserFamily("Python-webchecker")

  /**
   * Qirina Hurdler
   */
  case object QirinaHurdler extends BrowserFamily("Qirina Hurdler")

  /**
   * QQbrowser
   */
  case object QQbrowser extends BrowserFamily("QQbrowser")

  /**
   * Qseero
   */
  case object Qseero extends BrowserFamily("Qseero")

  /**
   * QtWeb
   */
  case object QtWeb extends BrowserFamily("QtWeb")

  /**
   * Qualidator.com Bot
   */
  case object QualidatorcomBot extends BrowserFamily("Qualidator.com Bot")

  /**
   * Quantcastbot
   */
  case object Quantcastbot extends BrowserFamily("Quantcastbot")

  /**
   * quickobot
   */
  case object quickobot extends BrowserFamily("quickobot")

  /**
   * QuickTime
   */
  case object QuickTime extends BrowserFamily("QuickTime")

  /**
   * QupZilla
   */
  case object QupZilla extends BrowserFamily("QupZilla")

  /**
   * R6 bot
   */
  case object R6bot extends BrowserFamily("R6 bot")

  /**
   * RADaR-Bot
   */
  case object RADaRBot extends BrowserFamily("RADaR-Bot")

  /**
   * Radio Downloader
   */
  case object RadioDownloader extends BrowserFamily("Radio Downloader")

  /**
   * RankurBot
   */
  case object RankurBot extends BrowserFamily("RankurBot")

  /**
   * RedBot
   */
  case object RedBot extends BrowserFamily("RedBot")

  /**
   * Reeder
   */
  case object Reeder extends BrowserFamily("Reeder")

  /**
   * Rekonq
   */
  case object Rekonq extends BrowserFamily("Rekonq")

  /**
   * REL Link Checker Lite
   */
  case object RELLinkCheckerLite extends BrowserFamily("REL Link Checker Lite")

  /**
   * retawq
   */
  case object retawq extends BrowserFamily("retawq")

  /**
   * Robo Crawler
   */
  case object RoboCrawler extends BrowserFamily("Robo Crawler")

  /**
   * Robots_Tester
   */
  case object RobotsTester extends BrowserFamily("Robots_Tester")

  /**
   * Robozilla
   */
  case object Robozilla extends BrowserFamily("Robozilla")

  /**
   * RockMelt
   */
  case object RockMelt extends BrowserFamily("RockMelt")

  /**
   * ROME library
   */
  case object ROMElibrary extends BrowserFamily("ROME library")

  /**
   * Ronzoobot
   */
  case object Ronzoobot extends BrowserFamily("Ronzoobot")

  /**
   * Rss Bandit
   */
  case object RssBandit extends BrowserFamily("Rss Bandit")

  /**
   * RSS Menu
   */
  case object RSSMenu extends BrowserFamily("RSS Menu")

  /**
   * RSS Popper
   */
  case object RSSPopper extends BrowserFamily("RSS Popper")

  /**
   * RSS Radio
   */
  case object RSSRadio extends BrowserFamily("RSS Radio")

  /**
   * RSSMicro.com RSS/Atom Feed Robot
   */
  case object RSSMicrocomRSSAtomFeedRobot extends BrowserFamily("RSSMicro.com RSS/Atom Feed Robot")

  /**
   * RSSOwl
   */
  case object RSSOwl extends BrowserFamily("RSSOwl")

  /**
   * Ruky-Roboter
   */
  case object RukyRoboter extends BrowserFamily("Ruky-Roboter")

  /**
   * Ryouko
   */
  case object Ryouko extends BrowserFamily("Ryouko")

  /**
   * RyzeCrawler
   */
  case object RyzeCrawler extends BrowserFamily("RyzeCrawler")

  /**
   * SaaYaa Explorer
   */
  case object SaaYaaExplorer extends BrowserFamily("SaaYaa Explorer")

  /**
   * Safari
   */
  case object Safari extends BrowserFamily("Safari")

  /**
   * Safari RSS reader
   */
  case object SafariRSSreader extends BrowserFamily("Safari RSS reader")

  /**
   * Sage
   */
  case object Sage extends BrowserFamily("Sage")

  /**
   * SAI Crawler
   */
  case object SAICrawler extends BrowserFamily("SAI Crawler")

  /**
   * SanszBot
   */
  case object SanszBot extends BrowserFamily("SanszBot")

  /**
   * SBIder
   */
  case object SBIder extends BrowserFamily("SBIder")

  /**
   * SBSearch
   */
  case object SBSearch extends BrowserFamily("SBSearch")

  /**
   * Scarlett
   */
  case object Scarlett extends BrowserFamily("Scarlett")

  /**
   * schibstedsokbot
   */
  case object schibstedsokbot extends BrowserFamily("schibstedsokbot")

  /**
   * ScollSpider
   */
  case object ScollSpider extends BrowserFamily("ScollSpider")

  /**
   * Scooter
   */
  case object Scooter extends BrowserFamily("Scooter")

  /**
   * ScoutJet
   */
  case object ScoutJet extends BrowserFamily("ScoutJet")

  /**
   * SeaMonkey
   */
  case object SeaMonkey extends BrowserFamily("SeaMonkey")

  /**
   * Search Engine World Robots.txt Validator
   */
  case object SearchEngineWorldRobotsTextValidator extends BrowserFamily("Search Engine World Robots.txt Validator")

  /**
   * search.KumKie.com
   */
  case object searchKumKiecom extends BrowserFamily("search.KumKie.com")

  /**
   * Search17Bot
   */
  case object Search17Bot extends BrowserFamily("Search17Bot")

  /**
   * Semager
   */
  case object Semager extends BrowserFamily("Semager")

  /**
   * SEMC Browser
   */
  case object SEMCBrowser extends BrowserFamily("SEMC Browser")

  /**
   * SemrushBot
   */
  case object SemrushBot extends BrowserFamily("SemrushBot")

  /**
   * Sensis Web Crawler
   */
  case object SensisWebCrawler extends BrowserFamily("Sensis Web Crawler")

  /**
   * SEODat
   */
  case object SEODat extends BrowserFamily("SEODat")

  /**
   * SEOENGBot
   */
  case object SEOENGBot extends BrowserFamily("SEOENGBot")

  /**
   * SEOkicks-Robot
   */
  case object SEOkicksRobot extends BrowserFamily("SEOkicks-Robot")

  /**
   * Setoozbot
   */
  case object Setoozbot extends BrowserFamily("Setoozbot")

  /**
   * Seznam RSS reader
   */
  case object SeznamRSSreader extends BrowserFamily("Seznam RSS reader")

  /**
   * Seznam WAP Proxy
   */
  case object SeznamWAPProxy extends BrowserFamily("Seznam WAP Proxy")

  /**
   * SeznamBot
   */
  case object SeznamBot extends BrowserFamily("SeznamBot")

  /**
   * SharpReader
   */
  case object SharpReader extends BrowserFamily("SharpReader")

  /**
   * Shelob
   */
  case object Shelob extends BrowserFamily("Shelob")

  /**
   * Shiira
   */
  case object Shiira extends BrowserFamily("Shiira")

  /**
   * Shim-Crawler
   */
  case object ShimCrawler extends BrowserFamily("Shim-Crawler")

  /**
   * ShopWiki
   */
  case object ShopWiki extends BrowserFamily("ShopWiki")

  /**
   * ShowyouBot
   */
  case object ShowyouBot extends BrowserFamily("ShowyouBot")

  /**
   * Shredder
   */
  case object Shredder extends BrowserFamily("Shredder")

  /**
   * Siege
   */
  case object Siege extends BrowserFamily("Siege")

  /**
   * silk
   */
  case object silk extends BrowserFamily("silk")

  /**
   * SimplePie
   */
  case object SimplePie extends BrowserFamily("SimplePie")

  /**
   * Sirketce/Busiverse
   */
  case object SirketceBusiverse extends BrowserFamily("Sirketce/Busiverse")

  /**
   * sistrix
   */
  case object sistrix extends BrowserFamily("sistrix")

  /**
   * Sitedomain-Bot
   */
  case object SitedomainBot extends BrowserFamily("Sitedomain-Bot")

  /**
   * SiteKiosk
   */
  case object SiteKiosk extends BrowserFamily("SiteKiosk")

  /**
   * SiteSucker
   */
  case object SiteSucker extends BrowserFamily("SiteSucker")

  /**
   * SkipStone
   */
  case object SkipStone extends BrowserFamily("SkipStone")

  /**
   * SkreemRBot
   */
  case object SkreemRBot extends BrowserFamily("SkreemRBot")

  /**
   * Skyfire
   */
  case object Skyfire extends BrowserFamily("Skyfire")

  /**
   * Sleipnir
   */
  case object Sleipnir extends BrowserFamily("Sleipnir")

  /**
   * SlimBoat
   */
  case object SlimBoat extends BrowserFamily("SlimBoat")

  /**
   * SlimBrowser
   */
  case object SlimBrowser extends BrowserFamily("SlimBrowser")

  /**
   * smart.apnoti.com Robot
   */
  case object smartapnoticomRobot extends BrowserFamily("smart.apnoti.com Robot")

  /**
   * snap.com
   */
  case object snapcom extends BrowserFamily("snap.com")

  /**
   * SnapBot
   */
  case object SnapBot extends BrowserFamily("SnapBot")

  /**
   * Snappy
   */
  case object Snappy extends BrowserFamily("Snappy")

  /**
   * SniffRSS
   */
  case object SniffRSS extends BrowserFamily("SniffRSS")

  /**
   * Snoopy
   */
  case object Snoopy extends BrowserFamily("Snoopy")

  /**
   * Sogou
   */
  case object Sogou extends BrowserFamily("Sogou")

  /**
   * Sogou Explorer
   */
  case object SogouExplorer extends BrowserFamily("Sogou Explorer")

  /**
   * sogou spider
   */
  case object sogouspider extends BrowserFamily("sogou spider")

  /**
   * Songbird
   */
  case object Songbird extends BrowserFamily("Songbird")

  /**
   * Sosospider
   */
  case object Sosospider extends BrowserFamily("Sosospider")

  /**
   * Sparrow
   */
  case object Sparrow extends BrowserFamily("Sparrow")

  /**
   * spbot
   */
  case object spbot extends BrowserFamily("spbot")

  /**
   * Speedy
   */
  case object Speedy extends BrowserFamily("Speedy")

  /**
   * Spicebird
   */
  case object Spicebird extends BrowserFamily("Spicebird")

  /**
   * SpiderLing
   */
  case object SpiderLing extends BrowserFamily("SpiderLing")

  /**
   * Spinn3r
   */
  case object Spinn3r extends BrowserFamily("Spinn3r")

  /**
   * Spock Crawler
   */
  case object SpockCrawler extends BrowserFamily("Spock Crawler")

  /**
   * SpokeSpider
   */
  case object SpokeSpider extends BrowserFamily("SpokeSpider")

  /**
   * Sproose
   */
  case object Sproose extends BrowserFamily("Sproose")

  /**
   * SrevBot
   */
  case object SrevBot extends BrowserFamily("SrevBot")

  /**
   * SSLBot
   */
  case object SSLBot extends BrowserFamily("SSLBot")

  /**
   * StackRambler
   */
  case object StackRambler extends BrowserFamily("StackRambler")

  /**
   * Stainless
   */
  case object Stainless extends BrowserFamily("Stainless")

  /**
   * StatoolsBot
   */
  case object StatoolsBot extends BrowserFamily("StatoolsBot")

  /**
   * Steeler
   */
  case object Steeler extends BrowserFamily("Steeler")

  /**
   * Strokebot
   */
  case object Strokebot extends BrowserFamily("Strokebot")

  /**
   * SubStream
   */
  case object SubStream extends BrowserFamily("SubStream")

  /**
   * suggybot
   */
  case object suggybot extends BrowserFamily("suggybot")

  /**
   * Summer
   */
  case object Summer extends BrowserFamily("Summer")

  /**
   * Sundance
   */
  case object Sundance extends BrowserFamily("Sundance")

  /**
   * Sundial
   */
  case object Sundial extends BrowserFamily("Sundial")

  /**
   * Sunrise
   */
  case object Sunrise extends BrowserFamily("Sunrise")

  /**
   * SuperBot
   */
  case object SuperBot extends BrowserFamily("SuperBot")

  /**
   * Surf
   */
  case object Surf extends BrowserFamily("Surf")

  /**
   * Surphace Scout
   */
  case object SurphaceScout extends BrowserFamily("Surphace Scout")

  /**
   * SurveyBot
   */
  case object SurveyBot extends BrowserFamily("SurveyBot")

  /**
   * SWEBot
   */
  case object SWEBot extends BrowserFamily("SWEBot")

  /**
   * Swiftfox
   */
  case object Swiftfox extends BrowserFamily("Swiftfox")

  /**
   * Swiftweasel
   */
  case object Swiftweasel extends BrowserFamily("Swiftweasel")

  /**
   * SygolBot
   */
  case object SygolBot extends BrowserFamily("SygolBot")

  /**
   * SynooBot
   */
  case object SynooBot extends BrowserFamily("SynooBot")

  /**
   * Szukacz
   */
  case object Szukacz extends BrowserFamily("Szukacz")

  /**
   * Szukankobot
   */
  case object Szukankobot extends BrowserFamily("Szukankobot")

  /**
   * Tagoobot
   */
  case object Tagoobot extends BrowserFamily("Tagoobot")

  /**
   * taptubot
   */
  case object taptubot extends BrowserFamily("taptubot")

  /**
   * Tear
   */
  case object Tear extends BrowserFamily("Tear")

  /**
   * TeaShark
   */
  case object TeaShark extends BrowserFamily("TeaShark")

  /**
   * Technoratibot
   */
  case object Technoratibot extends BrowserFamily("Technoratibot")

  /**
   * Teleport Pro
   */
  case object TeleportPro extends BrowserFamily("Teleport Pro")

  /**
   * TenFourFox
   */
  case object TenFourFox extends BrowserFamily("TenFourFox")

  /**
   * TeragramCrawler
   */
  case object TeragramCrawler extends BrowserFamily("TeragramCrawler")

  /**
   * textractor
   */
  case object textractor extends BrowserFamily("textractor")

  /**
   * The Bat!
   */
  case object TheBat extends BrowserFamily("The Bat!")

  /**
   * Theophrastus
   */
  case object Theophrastus extends BrowserFamily("Theophrastus")

  /**
   * TheWorld Browser
   */
  case object TheWorldBrowser extends BrowserFamily("TheWorld Browser")

  /**
   * Thumbnail.CZ robot
   */
  case object ThumbnailCZrobot extends BrowserFamily("Thumbnail.CZ robot")

  /**
   * ThumbShots-Bot
   */
  case object ThumbShotsBot extends BrowserFamily("ThumbShots-Bot")

  /**
   * thumbshots-de-Bot
   */
  case object thumbshotsdeBot extends BrowserFamily("thumbshots-de-Bot")

  /**
   * Thumbshots.ru
   */
  case object Thumbshotsru extends BrowserFamily("Thumbshots.ru")

  /**
   * Thunderbird
   */
  case object Thunderbird extends BrowserFamily("Thunderbird")

  /**
   * TinEye
   */
  case object TinEye extends BrowserFamily("TinEye")

  /**
   * Tizen Browser
   */
  case object TizenBrowser extends BrowserFamily("Tizen Browser")

  /**
   * Tjusig
   */
  case object Tjusig extends BrowserFamily("Tjusig")

  /**
   * Topicbot
   */
  case object Topicbot extends BrowserFamily("Topicbot")

  /**
   * Toread-Crawler
   */
  case object ToreadCrawler extends BrowserFamily("Toread-Crawler")

  /**
   * Touche
   */
  case object Touche extends BrowserFamily("Touche")

  /**
   * trendictionbot
   */
  case object trendictionbot extends BrowserFamily("trendictionbot")

  /**
   * Trileet NewsRoom
   */
  case object TrileetNewsRoom extends BrowserFamily("Trileet NewsRoom")

  /**
   * TT Explorer
   */
  case object TTExplorer extends BrowserFamily("TT Explorer")

  /**
   * Tulip Chain
   */
  case object TulipChain extends BrowserFamily("Tulip Chain")

  /**
   * TurnitinBot
   */
  case object TurnitinBot extends BrowserFamily("TurnitinBot")

  /**
   * TutorGigBot
   */
  case object TutorGigBot extends BrowserFamily("TutorGigBot")

  /**
   * TwengaBot
   */
  case object TwengaBot extends BrowserFamily("TwengaBot")

  /**
   * Twiceler
   */
  case object Twiceler extends BrowserFamily("Twiceler")

  /**
   * Twikle
   */
  case object Twikle extends BrowserFamily("Twikle")

  /**
   * Typhoeus
   */
  case object Typhoeus extends BrowserFamily("Typhoeus")

  /**
   * UASlinkChecker
   */
  case object UASlinkChecker extends BrowserFamily("UASlinkChecker")

  /**
   * UC Browser
   */
  case object UCBrowser extends BrowserFamily("UC Browser")

  /**
   * UltraBrowser
   */
  case object UltraBrowser extends BrowserFamily("UltraBrowser ")

  /**
   * UnisterBot
   */
  case object UnisterBot extends BrowserFamily("UnisterBot")

  /**
   * UnwindFetchor
   */
  case object UnwindFetchor extends BrowserFamily("UnwindFetchor")

  /**
   * updated
   */
  case object updated extends BrowserFamily("updated")

  /**
   * Updownerbot
   */
  case object Updownerbot extends BrowserFamily("Updownerbot")

  /**
   * UptimeDog
   */
  case object UptimeDog extends BrowserFamily("UptimeDog")

  /**
   * UptimeRobot
   */
  case object UptimeRobot extends BrowserFamily("UptimeRobot")

  /**
   * urlfan-bot
   */
  case object urlfanbot extends BrowserFamily("urlfan-bot")

  /**
   * Urlfilebot (Urlbot)
   */
  case object UrlfilebotUrlbot extends BrowserFamily("Urlfilebot (Urlbot)")

  /**
   * urlgrabber
   */
  case object urlgrabber extends BrowserFamily("urlgrabber")

  /**
   * Usejump
   */
  case object Usejump extends BrowserFamily("Usejump")

  /**
   * uZard Web
   */
  case object uZardWeb extends BrowserFamily("uZard Web")

  /**
   * Uzbl
   */
  case object Uzbl extends BrowserFamily("Uzbl")

  /**
   * Vagabondo
   */
  case object Vagabondo extends BrowserFamily("Vagabondo")

  /**
   * Validator.nu
   */
  case object Validatornu extends BrowserFamily("Validator.nu")

  /**
   * VERASYS 2k
   */
  case object VERASYS2k extends BrowserFamily("VERASYS 2k")

  /**
   * Vermut
   */
  case object Vermut extends BrowserFamily("Vermut")

  /**
   * Vespa Crawler
   */
  case object VespaCrawler extends BrowserFamily("Vespa Crawler")

  /**
   * VideoSurf_bot
   */
  case object VideoSurfbot extends BrowserFamily("VideoSurf_bot")

  /**
   * virus_detector
   */
  case object virusdetector extends BrowserFamily("virus_detector")

  /**
   * Visbot
   */
  case object Visbot extends BrowserFamily("Visbot")

  /**
   * VLC media player
   */
  case object VLCmediaplayer extends BrowserFamily("VLC media player")

  /**
   * VMBot
   */
  case object VMBot extends BrowserFamily("VMBot")

  /**
   * void-bot
   */
  case object voidbot extends BrowserFamily("void-bot")

  /**
   * VoilaBot
   */
  case object VoilaBot extends BrowserFamily("VoilaBot")

  /**
   * Vonkeror
   */
  case object Vonkeror extends BrowserFamily("Vonkeror")

  /**
   * VORTEX
   */
  case object VORTEX extends BrowserFamily("VORTEX")

  /**
   * voyager
   */
  case object voyager extends BrowserFamily("voyager")

  /**
   * Vuze
   */
  case object Vuze extends BrowserFamily("Vuze")

  /**
   * VWBot
   */
  case object VWBot extends BrowserFamily("VWBot")

  /**
   * W3C Checklink
   */
  case object W3CChecklink extends BrowserFamily("W3C Checklink")

  /**
   * W3C CSS Validator
   */
  case object W3CCSSValidator extends BrowserFamily("W3C CSS Validator")

  /**
   * W3C mobileOK Checker
   */
  case object W3CmobileOKChecker extends BrowserFamily("W3C mobileOK Checker")

  /**
   * W3C Validator
   */
  case object W3CValidator extends BrowserFamily("W3C Validator")

  /**
   * w3m
   */
  case object w3m extends BrowserFamily("w3m")

  /**
   * WapTiger
   */
  case object WapTiger extends BrowserFamily("WapTiger")

  /**
   * WASALive-Bot
   */
  case object WASALiveBot extends BrowserFamily("WASALive-Bot")

  /**
   * WatchMouse
   */
  case object WatchMouse extends BrowserFamily("WatchMouse")

  /**
   * WBSearchBot
   */
  case object WBSearchBot extends BrowserFamily("WBSearchBot")

  /**
   * WDG CSSCheck
   */
  case object WDGCSSCheck extends BrowserFamily("WDG CSSCheck")

  /**
   * WDG Page Valet
   */
  case object WDGPageValet extends BrowserFamily("WDG Page Valet")

  /**
   * WDG Validator
   */
  case object WDGValidator extends BrowserFamily("WDG Validator")

  /**
   * Web-sniffer
   */
  case object Websniffer extends BrowserFamily("Web-sniffer")

  /**
   * WebAlta Crawler
   */
  case object WebAltaCrawler extends BrowserFamily("WebAlta Crawler")

  /**
   * WebarooBot
   */
  case object WebarooBot extends BrowserFamily("WebarooBot")

  /**
   * WebCollage
   */
  case object WebCollage extends BrowserFamily("WebCollage")

  /**
   * WebCopier
   */
  case object WebCopier extends BrowserFamily("WebCopier")

  /**
   * webfetch
   */
  case object webfetch extends BrowserFamily("webfetch")

  /**
   * webfs
   */
  case object webfs extends BrowserFamily("webfs")

  /**
   * Webian Shell
   */
  case object WebianShell extends BrowserFamily("Webian Shell")

  /**
   * WebImages
   */
  case object WebImages extends BrowserFamily("WebImages")

  /**
   * webinatorbot
   */
  case object webinatorbot extends BrowserFamily("webinatorbot")

  /**
   * webmastercoffee
   */
  case object webmastercoffee extends BrowserFamily("webmastercoffee")

  /**
   * WebNL
   */
  case object WebNL extends BrowserFamily("WebNL")

  /**
   * WebRankSpider
   */
  case object WebRankSpider extends BrowserFamily("WebRankSpider")

  /**
   * WebRender
   */
  case object WebRender extends BrowserFamily("WebRender")

  /**
   * Webscope Crawler
   */
  case object WebscopeCrawler extends BrowserFamily("Webscope Crawler")

  /**
   * WebStripper
   */
  case object WebStripper extends BrowserFamily("WebStripper")

  /**
   * WebWatch/Robot_txtChecker
   */
  case object WebWatchRobottxtChecker extends BrowserFamily("WebWatch/Robot_txtChecker")

  /**
   * WebZIP
   */
  case object WebZIP extends BrowserFamily("WebZIP")

  /**
   * wectar
   */
  case object wectar extends BrowserFamily("wectar")

  /**
   * Weltweitimnetz Browser
   */
  case object WeltweitimnetzBrowser extends BrowserFamily("Weltweitimnetz Browser")

  /**
   * WeSEE:Search
   */
  case object WeSEESearch extends BrowserFamily("WeSEE:Search")

  /**
   * Wget
   */
  case object Wget extends BrowserFamily("Wget")

  /**
   * Whoismindbot
   */
  case object Whoismindbot extends BrowserFamily("Whoismindbot")

  /**
   * WikioFeedBot
   */
  case object WikioFeedBot extends BrowserFamily("WikioFeedBot")

  /**
   * wikiwix-bot
   */
  case object wikiwixbot extends BrowserFamily("wikiwix-bot")

  /**
   * Willow Internet Crawler
   */
  case object WillowInternetCrawler extends BrowserFamily("Willow Internet Crawler")

  /**
   * Winamp for Android
   */
  case object WinampforAndroid extends BrowserFamily("Winamp for Android")

  /**
   * Windows Live Mail
   */
  case object WindowsLiveMail extends BrowserFamily("Windows Live Mail")

  /**
   * Windows Media Player
   */
  case object WindowsMediaPlayer extends BrowserFamily("Windows Media Player")

  /**
   * WinHTTP
   */
  case object WinHTTP extends BrowserFamily("WinHTTP")

  /**
   * WinkBot
   */
  case object WinkBot extends BrowserFamily("WinkBot")

  /**
   * WinPodder
   */
  case object WinPodder extends BrowserFamily("WinPodder")

  /**
   * WinWap
   */
  case object WinWap extends BrowserFamily("WinWap")

  /**
   * WinWebBot
   */
  case object WinWebBot extends BrowserFamily("WinWebBot")

  /**
   * WIRE
   */
  case object WIRE extends BrowserFamily("WIRE")

  /**
   * wKiosk
   */
  case object wKiosk extends BrowserFamily("wKiosk")

  /**
   * WMCAI_robot
   */
  case object WMCAIrobot extends BrowserFamily("WMCAI_robot")

  /**
   * Woko
   */
  case object Woko extends BrowserFamily("Woko")

  /**
   * WordPress pingback
   */
  case object WordPresspingback extends BrowserFamily("WordPress pingback")

  /**
   * woriobot
   */
  case object woriobot extends BrowserFamily("woriobot")

  /**
   * WorldWideWeb
   */
  case object WorldWideWeb extends BrowserFamily("WorldWideWeb")

  /**
   * wOSBrowser
   */
  case object wOSBrowser extends BrowserFamily("wOSBrowser")

  /**
   * Wotbox
   */
  case object Wotbox extends BrowserFamily("Wotbox")

  /**
   * wsAnalyzer
   */
  case object wsAnalyzer extends BrowserFamily("wsAnalyzer")

  /**
   * www.fi crawler
   */
  case object wwwficrawler extends BrowserFamily("www.fi crawler")

  /**
   * WWW::Mechanize
   */
  case object WWWMechanize extends BrowserFamily("WWW::Mechanize")

  /**
   * wwwster
   */
  case object wwwster extends BrowserFamily("wwwster")

  /**
   * Wyzo
   */
  case object Wyzo extends BrowserFamily("Wyzo")

  /**
   * X-Smiles
   */
  case object XSmiles extends BrowserFamily("X-Smiles")

  /**
   * Xaldon WebSpider
   */
  case object XaldonWebSpider extends BrowserFamily("Xaldon WebSpider")

  /**
   * XBMC
   */
  case object XBMC extends BrowserFamily("XBMC")

  /**
   * Xenu
   */
  case object Xenu extends BrowserFamily("Xenu")

  /**
   * xine
   */
  case object xine extends BrowserFamily("xine")

  /**
   * XmarksFetch
   */
  case object XmarksFetch extends BrowserFamily("XmarksFetch")

  /**
   * XML-RPC for PHP
   */
  case object XMLRPCforPHP extends BrowserFamily("XML-RPC for PHP")

  /**
   * XML-RPC for Ruby
   */
  case object XMLRPCforRuby extends BrowserFamily("XML-RPC for Ruby")

  /**
   * XML Sitemaps Generator
   */
  case object XMLSitemapsGenerator extends BrowserFamily("XML Sitemaps Generator")

  /**
   * XMPlay
   */
  case object XMPlay extends BrowserFamily("XMPlay")

  /**
   * Yaanb
   */
  case object Yaanb extends BrowserFamily("Yaanb")

  /**
   * yacybot
   */
  case object yacybot extends BrowserFamily("yacybot")

  /**
   * Yahoo!
   */
  case object Yahoo extends BrowserFamily("Yahoo!")

  /**
   * Yahoo! JAPAN
   */
  case object YahooJAPAN extends BrowserFamily("Yahoo! JAPAN")

  /**
   * YahooFeedSeeker
   */
  case object YahooFeedSeeker extends BrowserFamily("YahooFeedSeeker")

  /**
   * Yandex.Browser
   */
  case object YandexBrowser extends BrowserFamily("Yandex.Browser")

  /**
   * YandexBot
   */
  case object YandexBot extends BrowserFamily("YandexBot")

  /**
   * Yanga
   */
  case object Yanga extends BrowserFamily("Yanga")

  /**
   * YeahReader
   */
  case object YeahReader extends BrowserFamily("YeahReader")

  /**
   * YioopBot
   */
  case object YioopBot extends BrowserFamily("YioopBot")

  /**
   * YodaoBot
   */
  case object YodaoBot extends BrowserFamily("YodaoBot")

  /**
   * Yoono Bot
   */
  case object YoonoBot extends BrowserFamily("Yoono Bot")

  /**
   * YoudaoBot
   */
  case object YoudaoBot extends BrowserFamily("YoudaoBot")

  /**
   * YowedoBot
   */
  case object YowedoBot extends BrowserFamily("YowedoBot")

  /**
   * YRSpider
   */
  case object YRSpider extends BrowserFamily("YRSpider")

  /**
   * ZACATEK_CZ
   */
  case object ZACATEKCZ extends BrowserFamily("ZACATEK_CZ")

  /**
   * zBrowser
   */
  case object zBrowser extends BrowserFamily("zBrowser")

  /**
   * Zend_Http_Client
   */
  case object ZendHttpClient extends BrowserFamily("Zend_Http_Client")

  /**
   * Zeusbot
   */
  case object Zeusbot extends BrowserFamily("Zeusbot")

  /**
   * ZipZap
   */
  case object ZipZap extends BrowserFamily("ZipZap")

  /**
   * ZookaBot
   */
  case object ZookaBot extends BrowserFamily("ZookaBot")

  /**
   * ZoomSpider (ZSEBOT)
   */
  case object ZoomSpiderZSEBOT extends BrowserFamily("ZoomSpider (ZSEBOT)")

  /**
   * ZyBorg
   */
  case object Zyborg extends BrowserFamily("ZyBorg")

  val values = findValues.toVector

  def byFriendlyName(name: String) = values.find(b => b.friendlyName == name).getOrElse(Unknown)
}