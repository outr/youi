package org.hyperscala.web.useragent

import org.powerscala.enum.{EnumEntry, Enumerated}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BrowserFamily private(val friendlyName: String) extends EnumEntry {
  override def toString() = friendlyName
}

object BrowserFamily extends Enumerated[BrowserFamily] {
  /**
   * Representation of an unknown User-Agent
   *
   * <p>
   * <strong>Attention</strong>: This is not a known User-Agent family, but only a placeholder.
   */
  val Unknown = new BrowserFamily("unknown")

  /**
   * 192.comAgent
   */
  val ComAgent192 = new BrowserFamily("192.comAgent")

  /**
   * 2Bone LinkChecker
   */
  val TwoBoneLinkChecker = new BrowserFamily("2Bone LinkChecker")

  /**
   * 50.nu
   */
  val Fiftynu = new BrowserFamily("50.nu")

  /**
   * 80legs
   */
  val Eightylegs = new BrowserFamily("80legs")

  /**
   * A1 Sitemap Generator
   */
  val A1SitemapGenerator = new BrowserFamily("A1 Sitemap Generator")

  /**
   * AB (Apache Bench)
   */
  val ApacheBench = new BrowserFamily("AB (Apache Bench)")

  /**
   * abby
   */
  val Abby = new BrowserFamily("abby")

  /**
   * Abilon
   */
  val Abilon = new BrowserFamily("Abilon")

  /**
   * Abolimba
   */
  val Abolimba = new BrowserFamily("Abolimba")

  /**
   * Aboundexbot
   */
  val Aboundexbot = new BrowserFamily("Aboundexbot")

  /**
   * AboutUsBot
   */
  val AboutUsBot = new BrowserFamily("AboutUsBot")

  /**
   * Abrave Spider
   */
  val AbraveSpider = new BrowserFamily("Abrave Spider")

  /**
   * ABrowse
   */
  val ABrowse = new BrowserFamily("ABrowse")

  /**
   * Accelobot
   */
  val Accelobot = new BrowserFamily("Accelobot")

  /**
   * Accoona-AI-Agent
   */
  val AccoonaAIAgent = new BrowserFamily("Accoona-AI-Agent")

  /**
   * Acoo Browser
   */
  val AcooBrowser = new BrowserFamily("Acoo Browser")

  /**
   * AcoonBot
   */
  val AcoonBot = new BrowserFamily("AcoonBot")

  /**
   * Acorn
   */
  val Acorn = new BrowserFamily("Acorn")

  /**
   * ActiveXperts Network Monitor
   */
  val ActiveXpertsNetworkMonitor = new BrowserFamily("ActiveXperts Network Monitor")

  /**
   * AddThis.com
   */
  val AddThisCom = new BrowserFamily("AddThis.com")

  /**
   * Adobe AIR runtime
   */
  val AdobeAIRRuntime = new BrowserFamily("Adobe AIR runtime")

  /**
   * adressendeutschland.de
   */
  val Adressendeutschlandde = new BrowserFamily("adressendeutschland.de")

  /**
   * AdsBot-Google
   */
  val AdsBotGoogle = new BrowserFamily("AdsBot-Google")

  /**
   * AhrefsBot
   */
  val AhrefsBot = new BrowserFamily("AhrefsBot")

  /**
   * aiHitBot
   */
  val aiHitBot = new BrowserFamily("aiHitBot")

  /**
   * aippie
   */
  val aippie = new BrowserFamily("aippie")

  /**
   * Akregator
   */
  val Akregator = new BrowserFamily("Akregator")

  /**
   * akula
   */
  val akula = new BrowserFamily("akula")

  /**
   * Alienforce
   */
  val Alienforce = new BrowserFamily("Alienforce")

  /**
   * Almaden
   */
  val Almaden = new BrowserFamily("Almaden")

  /**
   * Amagit.COM
   */
  val AmagitCOM = new BrowserFamily("Amagit.COM")

  /**
   * Amaya
   */
  val Amaya = new BrowserFamily("Amaya")

  /**
   * Amazon Silk
   */
  val AmazonSilk = new BrowserFamily("Amazon Silk")

  /**
   * Amfibibot
   */
  val Amfibibot = new BrowserFamily("Amfibibot")

  /**
   * amibot
   */
  val amibot = new BrowserFamily("amibot")

  /**
   * Amiga Aweb
   */
  val AmigaAweb = new BrowserFamily("Amiga Aweb")

  /**
   * Amiga Voyager
   */
  val AmigaVoyager = new BrowserFamily("Amiga Voyager")

  /**
   * Android Webkit
   */
  val AndroidWebkit = new BrowserFamily("Android Webkit")

  /**
   * Anemone
   */
  val Anemone = new BrowserFamily("Anemone")

  /**
   * Anonymouse.org
   */
  val Anonymouse = new BrowserFamily("Anonymouse.org")

  /**
   * AntBot
   */
  val AntBot = new BrowserFamily("AntBot")

  /**
   * anw HTMLChecker
   */
  val anwHTMLChecker = new BrowserFamily("anw HTMLChecker")

  /**
   * anw LoadControl
   */
  val anwLoadControl = new BrowserFamily("anw LoadControl")

  /**
   * AOL Explorer
   */
  val AOLExplorer = new BrowserFamily("AOL Explorer")

  /**
   * Apache internal dummy connection
   */
  val ApacheInternalDummyConnection = new BrowserFamily("Apache internal dummy connection")

  /**
   * Apache Synapse
   */
  val ApacheSynapse = new BrowserFamily("Apache Synapse")

  /**
   * Apercite
   */
  val Apercite = new BrowserFamily("Apercite")

  /**
   * AportWorm
   */
  val AportWorm = new BrowserFamily("AportWorm")

  /**
   * Apple-PubSub
   */
  val AppleMail = new BrowserFamily("Apple Mail")

  /**
   * Apple-PubSub
   */
  val ApplePubSub = new BrowserFamily("Apple-PubSub")

  /**
   * arachnode.net
   */
  val Arachnode = new BrowserFamily("arachnode.net")

  /**
   * archive.org_bot
   */
  val ArchiveOrgBot = new BrowserFamily("archive.org_bot")

  /**
   * Arora
   */
  val Arora = new BrowserFamily("Arora")

  /**
   * ASAHA Search Engine Turkey
   */
  val ASAHASearchEngineTurkey = new BrowserFamily("ASAHA Search Engine Turkey")

  /**
   * Ask Jeeves/Teoma
   */
  val AskJeevesTeoma = new BrowserFamily("Ask Jeeves/Teoma")

  /**
   * Atomic Email Hunter
   */
  val AtomicEmailHunter = new BrowserFamily("Atomic Email Hunter")

  /**
   * Atomic Web Browser
   */
  val AtomicWebBrowser = new BrowserFamily("Atomic Web Browser")

  /**
   * Avant Browser
   */
  val AvantBrowser = new BrowserFamily("Avant Browser")

  /**
   * AvantGo
   */
  val AvantGo = new BrowserFamily("AvantGo")

  /**
   * Awasu
   */
  val Awasu = new BrowserFamily("Awasu")

  /**
   * Axel
   */
  val Axel = new BrowserFamily("Axel")

  /**
   * BabalooSpider
   */
  val BabalooSpider = new BrowserFamily("BabalooSpider")

  /**
   * BacklinkCrawler
   */
  val BacklinkCrawler = new BrowserFamily("BacklinkCrawler")

  /**
   * Bad-Neighborhood
   */
  val BadNeighborhood = new BrowserFamily("Bad-Neighborhood")

  /**
   * Baidu Browser
   */
  val BaiduBrowser = new BrowserFamily("Baidu Browser")

  /**
   * Baiduspider
   */
  val Baiduspider = new BrowserFamily("Baiduspider")

  /**
   * Banshee
   */
  val Banshee = new BrowserFamily("Banshee")

  /**
   * Barca
   */
  val Barca = new BrowserFamily("Barca")

  /**
   * baypup
   */
  val baypup = new BrowserFamily("baypup")

  /**
   * BDFetch
   */
  val BDFetch = new BrowserFamily("BDFetch")

  /**
   * Beamrise
   */
  val Beamrise = new BrowserFamily("Beamrise")

  /**
   * BecomeBot
   */
  val BecomeBot = new BrowserFamily("BecomeBot")

  /**
   * Beonex
   */
  val Beonex = new BrowserFamily("Beonex")

  /**
   * Bigsearch.ca
   */
  val BigsearchCa = new BrowserFamily("Bigsearch.ca")

  /**
   * bingbot
   */
  val BingBot = new BrowserFamily("bingbot")

  /**
   * BinGet
   */
  val BinGet = new BrowserFamily("BinGet")

  /**
   * bitlybot
   */
  val bitlybot = new BrowserFamily("bitlybot")

  /**
   * biwec
   */
  val biwec = new BrowserFamily("biwec")

  /**
   * bixo
   */
  val bixo = new BrowserFamily("bixo")

  /**
   * bixolabs
   */
  val bixocrawler = new BrowserFamily("bixocrawler")

  /**
   * BlackBerry Browser
   */
  val BlackBerryBrowser = new BrowserFamily("BlackBerry Browser")

  /**
   * Blackbird
   */
  val Blackbird = new BrowserFamily("Blackbird")

  /**
   * BlackHawk
   */
  val BlackHawk = new BrowserFamily("BlackHawk")

  /**
   * Blaiz-Bee
   */
  val BlaizBee = new BrowserFamily("Blaiz-Bee")

  /**
   * Blazer
   */
  val Blazer = new BrowserFamily("Blazer")

  /**
   * Blekkobot
   */
  val Blekkobot = new BrowserFamily("Blekkobot")

  /**
   * BlinkaCrawler
   */
  val BlinkaCrawler = new BrowserFamily("BlinkaCrawler")

  /**
   * BlogBridge
   */
  val BlogBridge = new BrowserFamily("BlogBridge")

  /**
   * Bloggsi
   */
  val Bloggsi = new BrowserFamily("Bloggsi")

  /**
   * Bloglines
   */
  val Bloglines = new BrowserFamily("Bloglines")

  /**
   * BlogPulse
   */
  val BlogPulse = new BrowserFamily("BlogPulse")

  /**
   * bnf.fr_bot
   */
  val bnffrbot = new BrowserFamily("bnf.fr_bot")

  /**
   * boitho.com-dc
   */
  val boithocomdc = new BrowserFamily("boitho.com-dc")

  /**
   * Bolt
   */
  val Bolt = new BrowserFamily("Bolt")

  /**
   * Bookdog
   */
  val Bookdog = new BrowserFamily("Bookdog")

  /**
   * BookmarkTracker
   */
  val BookmarkTracker = new BrowserFamily("BookmarkTracker")

  /**
   * bot-pge.chlooe.com
   */
  val botpgechlooecom = new BrowserFamily("bot-pge.chlooe.com")

  /**
   * botmobi
   */
  val botmobi = new BrowserFamily("botmobi")

  /**
   * BotOnParade
   */
  val BotOnParade = new BrowserFamily("BotOnParade")

  /**
   * Boxxe
   */
  val Boxxe = new BrowserFamily("Boxxe")

  /**
   * BrownRecluse
   */
  val BrownRecluse = new BrowserFamily("BrownRecluse")

  /**
   * Browsershots
   */
  val Browsershots = new BrowserFamily("Browsershots")

  /**
   * BrowseX
   */
  val BrowseX = new BrowserFamily("BrowseX")

  /**
   * Browzar
   */
  val Browzar = new BrowserFamily("Browzar")

  /**
   * btbot
   */
  val btbot = new BrowserFamily("btbot")

  /**
   * Bunjalloo
   */
  val Bunjalloo = new BrowserFamily("Bunjalloo")

  /**
   * Butterfly
   */
  val Butterfly = new BrowserFamily("Butterfly")

  /**
   * BuzzRankingBot
   */
  val BuzzRankingBot = new BrowserFamily("BuzzRankingBot")

  /**
   * Camino
   */
  val Camino = new BrowserFamily("Camino")

  /**
   * CamontSpider
   */
  val CamontSpider = new BrowserFamily("CamontSpider")

  /**
   * CareerBot
   */
  val CareerBot = new BrowserFamily("CareerBot")

  /**
   * Nail
   */
  val Nail = new BrowserFamily("^Nail")

  /**
   * Castabot
   */
  val Castabot = new BrowserFamily("Castabot")

  /**
   * CatchBot
   */
  val CatchBot = new BrowserFamily("CatchBot")

  /**
   * CazoodleBot
   */
  val CazoodleBot = new BrowserFamily("CazoodleBot")

  /**
   * CCBot
   */
  val CCBot = new BrowserFamily("CCBot")

  /**
   * ccubee
   */
  val ccubee = new BrowserFamily("ccubee")

  /**
   * ChangeDetection
   */
  val ChangeDetection = new BrowserFamily("ChangeDetection")

  /**
   * Charlotte
   */
  val Charlotte = new BrowserFamily("Charlotte")

  /**
   * Charon
   */
  val Charon = new BrowserFamily("Charon")

  /**
   * Checkbot
   */
  val Checkbot = new BrowserFamily("Checkbot")

  /**
   * Cheshire
   */
  val Cheshire = new BrowserFamily("Cheshire")

  /**
   * Chilkat HTTP .NET
   */
  val ChilkatHTTP = new BrowserFamily("Chilkat HTTP .NET")

  /**
   * Chrome
   */
  val Chrome = new BrowserFamily("Chrome")

  /**
   * Chrome Mobile
   */
  val ChromeMobile = new BrowserFamily("Chrome Mobile")

  /**
   * Chromium
   */
  val Chromium = new BrowserFamily("Chromium")

  /**
   * City4you
   */
  val City4you = new BrowserFamily("City4you")

  /**
   * cityreview
   */
  val cityreview = new BrowserFamily("cityreview")

  /**
   * CJB.NET Proxy
   */
  val CJBNETProxy = new BrowserFamily("CJB.NET Proxy")

  /**
   * Claws Mail GtkHtml2 plugin
   */
  val ClawsMailGtkHtml2Plugin = new BrowserFamily("Claws Mail GtkHtml2 plugin")

  /**
   * CligooRobot
   */
  val CligooRobot = new BrowserFamily("CligooRobot")

  /**
   * coccoc
   */
  val coccoc = new BrowserFamily("coccoc")

  /**
   * Columbus
   */
  val Columbus = new BrowserFamily("Columbus")

  /**
   * Combine
   */
  val Combine = new BrowserFamily("Combine")

  /**
   * CometBird
   */
  val CometBird = new BrowserFamily("CometBird")

  /**
   * Comodo Dragon
   */
  val ComodoDragon = new BrowserFamily("Comodo Dragon")

  /**
   * CompSpyBot - Competitive Spying and Scraping
   */
  val CompSpyBot1 = new BrowserFamily("CompSpyBot/1.0")

  /**
   * Conkeror
   */
  val Conkeror = new BrowserFamily("Conkeror")

  /**
   * ConveraCrawler
   */
  val ConveraCrawler = new BrowserFamily("ConveraCrawler")

  /**
   * CoolNovo
   */
  val CoolNovo = new BrowserFamily("CoolNovo")

  /**
   * copyright sheriff
   */
  val CopyrightSheriff = new BrowserFamily("copyright sheriff")

  /**
   * CorePlayer
   */
  val CorePlayer = new BrowserFamily("CorePlayer")

  /**
   * CorpusCrawler
   */
  val CorpusCrawler = new BrowserFamily("CorpusCrawler")

  /**
   * Covario-IDS
   */
  val CovarioIDS = new BrowserFamily("Covario-IDS")

  /**
   * CPG Dragonfly RSS Module
   */
  val CPGDragonflyRSSModule = new BrowserFamily("CPG Dragonfly RSS Module")

  /**
   * Crawler4j
   */
  val Crawler4j = new BrowserFamily("Crawler4j")

  /**
   * Crazy Browser
   */
  val CrazyBrowser = new BrowserFamily("Crazy Browser")

  /**
   * csci_b659
   */
  val cscib659 = new BrowserFamily("csci_b659")

  /**
   * CSE HTML Validator
   */
  val CSEHTMLValidator = new BrowserFamily("CSE HTML Validator")

  /**
   * cURL
   */
  val cURL = new BrowserFamily("cURL")

  /**
   * Cyberduck
   */
  val Cyberduck = new BrowserFamily("Cyberduck")

  /**
   * Cynthia
   */
  val Cynthia = new BrowserFamily("Cynthia")

  /**
   * D+
   */
  val DPlus = new BrowserFamily("D+")

  /**
   * DataFountains
   */
  val DataFountains = new BrowserFamily("DataFountains")

  /**
   * DataparkSearch
   */
  val DataparkSearch = new BrowserFamily("DataparkSearch")

  /**
   * Daumoa
   */
  val Daumoa = new BrowserFamily("Daumoa")

  /**
   * DBLBot
   */
  val DBLBot = new BrowserFamily("DBLBot")

  /**
   * DCPbot
   */
  val DCPbot = new BrowserFamily("DCPbot")

  /**
   * DealGates Bot
   */
  val DealGatesBot = new BrowserFamily("DealGates Bot")

  /**
   * Deepnet Explorer
   */
  val DeepnetExplorer = new BrowserFamily("Deepnet Explorer")

  /**
   * del.icio.us-thumbnails
   */
  val deliciousThumbnails = new BrowserFamily("del.icio.us-thumbnails")

  /**
   * Dell Web Monitor
   */
  val DellWebMonitor = new BrowserFamily("Dell Web Monitor")

  /**
   * Demeter
   */
  val Demeter = new BrowserFamily("Demeter")

  /**
   * DepSpid
   */
  val DepSpid = new BrowserFamily("DepSpid")

  /**
   * DeskBrowse
   */
  val DeskBrowse = new BrowserFamily("DeskBrowse")

  /**
   * Dillo
   */
  val Dillo = new BrowserFamily("Dillo")

  /**
   * Discoverybot is Discovery Engine's web crawler. It downloads text/html documents for use in building our full web
   * search engine.
   */
  val discobot = new BrowserFamily("discobot")

  /**
   * DKIMRepBot
   */
  val DKIMRepBot = new BrowserFamily("DKIMRepBot")

  /**
   * DNS-Digger-Explorer
   */
  val DNSDiggerExplorer = new BrowserFamily("DNS-Digger-Explorer")

  /**
   * DocZilla
   */
  val DocZilla = new BrowserFamily("DocZilla")

  /**
   * Dolphin
   */
  val Dolphin = new BrowserFamily("Dolphin")

  /**
   * DomainDB
   */
  val DomainDB = new BrowserFamily("DomainDB")

  /**
   * Dooble
   */
  val Dooble = new BrowserFamily("Dooble")

  /**
   * Doris
   */
  val Doris = new BrowserFamily("Doris")

  /**
   * Dot TK - spider
   */
  val DotTKSpider = new BrowserFamily("Dot TK - spider")

  /**
   * DotBot
   */
  val DotBot = new BrowserFamily("DotBot")

  /**
   * dotSemantic
   */
  val dotSemantic = new BrowserFamily("dotSemantic")

  /**
   * DownloadStudio
   */
  val DownloadStudio = new BrowserFamily("DownloadStudio")

  /**
   * DripfeedBot
   */
  val DripfeedBot = new BrowserFamily("DripfeedBot")

  /**
   * DuckDuckBot
   */
  val DuckDuckBot = new BrowserFamily("DuckDuckBot")

  /**
   * DuckDuckPreview
   */
  val DuckDuckPreview = new BrowserFamily("DuckDuckPreview")

  /**
   * e-SocietyRobot
   */
  val eSocietyRobot = new BrowserFamily("e-SocietyRobot")

  /**
   * EasyBib AutoCite
   */
  val EasyBibAutoCite = new BrowserFamily("EasyBib AutoCite")

  /**
   * eCairn-Grabber
   */
  val eCairnGrabber = new BrowserFamily("eCairn-Grabber")

  /**
   * Edbrowse
   */
  val Edbrowse = new BrowserFamily("Edbrowse")

  /**
   * EDI
   */
  val EDI = new BrowserFamily("EDI")

  /**
   * EdisterBot
   */
  val EdisterBot = new BrowserFamily("EdisterBot")

  /**
   * egothor
   */
  val egothor = new BrowserFamily("egothor")

  /**
   * ejupiter.com
   */
  val ejupiter = new BrowserFamily("ejupiter.com")

  /**
   * Element Browser
   */
  val ElementBrowser = new BrowserFamily("Element Browser")

  /**
   * Elinks
   */
  val Elinks = new BrowserFamily("Elinks")

  /**
   * EnaBot
   */
  val EnaBot = new BrowserFamily("EnaBot")

  /**
   * Enigma browser
   */
  val Enigmabrowser = new BrowserFamily("Enigma browser")

  /**
   * Enterprise_Search
   */
  val EnterpriseSearch = new BrowserFamily("Enterprise_Search")

  /**
   * envolk
   */
  val envolk = new BrowserFamily("envolk")

  /**
   * Epic
   */
  val Epic = new BrowserFamily("Epic")

  /**
   * Epiphany
   */
  val Epiphany = new BrowserFamily("Epiphany")

  /**
   * Espial TV Browser
   */
  val EspialTVBrowser = new BrowserFamily("Espial TV Browser")

  /**
   * Eudora
   */
  val Eudora = new BrowserFamily("Eudora")

  /**
   * EuripBot
   */
  val EuripBot = new BrowserFamily("EuripBot")

  /**
   * Eurobot
   */
  val Eurobot = new BrowserFamily("Eurobot")

  /**
   * EventGuruBot
   */
  val EventGuruBot = new BrowserFamily("EventGuruBot")

  /**
   * EventMachine
   */
  val EventMachine = new BrowserFamily("EventMachine")

  /**
   * Evolution/Camel.Stream
   */
  val EvolutionCamelStream = new BrowserFamily("Evolution/Camel.Stream")

  /**
   * EvriNid
   */
  val EvriNid = new BrowserFamily("EvriNid")

  /**
   * Exabot
   */
  val Exabot = new BrowserFamily("Exabot")

  /**
   * ExactSEEK
   */
  val ExactSEEK = new BrowserFamily("ExactSEEK")

  /**
   * Ezooms
   */
  val Ezooms = new BrowserFamily("Ezooms")

  /**
   * FacebookExternalHit
   */
  val FacebookExternalHit = new BrowserFamily("FacebookExternalHit")

  /**
   * factbot
   */
  val factbot = new BrowserFamily("factbot")

  /**
   * FairShare
   */
  val FairShare = new BrowserFamily("FairShare")

  /**
   * Falconsbot
   */
  val Falconsbot = new BrowserFamily("Falconsbot")

  /**
   * FAST Enterprise Crawler
   */
  val FASTEnterpriseCrawler = new BrowserFamily("FAST Enterprise Crawler")

  /**
   * FAST MetaWeb Crawler
   */
  val FASTMetaWebCrawler = new BrowserFamily("FAST MetaWeb Crawler")

  /**
   * Fastladder FeedFetcher
   */
  val FastladderFeedFetcher = new BrowserFamily("Fastladder FeedFetcher")

  /**
   * FauBot
   */
  val FauBot = new BrowserFamily("FauBot")

  /**
   * favorstarbot
   */
  val favorstarbot = new BrowserFamily("favorstarbot")

  /**
   * Feed::Find
   */
  val FeedFind = new BrowserFamily("Feed::Find")

  /**
   * Feed Viewer
   */
  val FeedViewer = new BrowserFamily("Feed Viewer")

  /**
   * FeedCatBot
   */
  val FeedCatBot = new BrowserFamily("FeedCatBot")

  /**
   * FeedDemon
   */
  val FeedDemon = new BrowserFamily("FeedDemon")

  /**
   * Feedfetcher-Google
   */
  val FeedfetcherGoogle = new BrowserFamily("Feedfetcher-Google")

  /**
   * FeedFinder/bloggz.se
   */
  val FeedFinderbloggzse = new BrowserFamily("FeedFinder/bloggz.se")

  /**
   * FeedParser
   */
  val FeedParser = new BrowserFamily("FeedParser")

  /**
   * FeedValidator
   */
  val FeedValidator = new BrowserFamily("FeedValidator")

  /**
   * Findexa Crawler
   */
  val FindexaCrawler = new BrowserFamily("Findexa Crawler")

  /**
   * findlinks
   */
  val findlinks = new BrowserFamily("findlinks")

  /**
   * Firebird (old name for Firefox)
   */
  val Firebird = new BrowserFamily("Firebird (old name for Firefox)")

  /**
   * Firefox
   */
  val Firefox = new BrowserFamily("Firefox")

  /**
   * Firefox (BonEcho)
   */
  val FirefoxBonEcho = new BrowserFamily("Firefox (BonEcho)")

  /**
   * Firefox (GranParadiso)
   */
  val FirefoxGranParadiso = new BrowserFamily("Firefox (GranParadiso)")

  /**
   * Firefox (Lorentz)
   */
  val FirefoxLorentz = new BrowserFamily("Firefox (Lorentz)")

  /**
   * Firefox (Minefield)
   */
  val FirefoxMinefield = new BrowserFamily("Firefox (Minefield)")

  /**
   * Firefox (Namoroka)
   */
  val FirefoxNamoroka = new BrowserFamily("Firefox (Namoroka)")

  /**
   * Firefox (Shiretoko)
   */
  val FirefoxShiretoko = new BrowserFamily("Firefox (Shiretoko)")

  /**
   * Fireweb Navigator
   */
  val FirewebNavigator = new BrowserFamily("Fireweb Navigator")

  /**
   * Flatland Industries Web Spider
   */
  val FlatlandIndustriesWebSpider = new BrowserFamily("Flatland Industries Web Spider")

  /**
   * flatlandbot
   */
  val flatlandbot = new BrowserFamily("flatlandbot")

  /**
   * FlightDeckReportsBot
   */
  val FlightDeckReportsBot = new BrowserFamily("FlightDeckReportsBot")

  /**
   * FlipboardProxy
   */
  val FlipboardProxy = new BrowserFamily("FlipboardProxy")

  /**
   * Flock
   */
  val Flock = new BrowserFamily("Flock")

  /**
   * Flocke bot
   */
  val Flockebot = new BrowserFamily("Flocke bot")

  /**
   * Fluid
   */
  val Fluid = new BrowserFamily("Fluid")

  /**
   * FlyCast
   */
  val FlyCast = new BrowserFamily("FlyCast")

  /**
   * FollowSite Bot
   */
  val FollowSiteBot = new BrowserFamily("FollowSite Bot")

  /**
   * foobar2000
   */
  val foobar2000 = new BrowserFamily("foobar2000")

  /**
   * Fooooo_Web_Video_Crawl
   */
  val Fooooo_WebVideoCrawl = new BrowserFamily("Fooooo_Web_Video_Crawl")

  /**
   * Forschungsportal
   */
  val Forschungsportal = new BrowserFamily("Forschungsportal")

  /**
   * Francis
   */
  val Francis = new BrowserFamily("Francis")

  /**
   * Funambol Mozilla Sync Client
   */
  val FunambolMozillaSyncClient = new BrowserFamily("Funambol Mozilla Sync Client")

  /**
   * Funambol Outlook Sync Client
   */
  val FunambolOutlookSyncClient = new BrowserFamily("Funambol Outlook Sync Client")

  /**
   * FunnelBack
   */
  val FunnelBack = new BrowserFamily("FunnelBack")

  /**
   * FurlBot
   */
  val FurlBot = new BrowserFamily("FurlBot")

  /**
   * FyberSpider
   */
  val FyberSpider = new BrowserFamily("FyberSpider")

  /**
   * g2crawler
   */
  val g2crawler = new BrowserFamily("g2crawler")

  /**
   * Gaisbot
   */
  val Gaisbot = new BrowserFamily("Gaisbot")

  /**
   * Galeon
   */
  val Galeon = new BrowserFamily("Galeon")

  /**
   * Gallent Search Spider
   */
  val GallentSearchSpider = new BrowserFamily("Gallent Search Spider")

  /**
   * GarlikCrawler
   */
  val GarlikCrawler = new BrowserFamily("GarlikCrawler")

  /**
   * GcMail
   */
  val GcMail = new BrowserFamily("GcMail")

  /**
   * genieBot
   */
  val genieBot = new BrowserFamily("genieBot")

  /**
   * GeonaBot
   */
  val GeonaBot = new BrowserFamily("GeonaBot")

  /**
   * GetRight
   */
  val GetRight = new BrowserFamily("GetRight")

  /**
   * Giant/1.0
   */
  val Giant = new BrowserFamily("Giant")

  /**
   * Gigabot
   */
  val Gigabot = new BrowserFamily("Gigabot")

  /**
   * GingerCrawler
   */
  val GingerCrawler = new BrowserFamily("GingerCrawler")

  /**
   * Girafabot
   */
  val Girafabot = new BrowserFamily("Girafabot")

  /**
   * GlobalMojo
   */
  val GlobalMojo = new BrowserFamily("GlobalMojo")

  /**
   * GnomeVFS
   */
  val GnomeVFS = new BrowserFamily("GnomeVFS")

  /**
   * GO Browser
   */
  val GOBrowser = new BrowserFamily("GO Browser")

  /**
   * GOFORITBOT
   */
  val GOFORITBOT = new BrowserFamily("GOFORITBOT")

  /**
   * GoldenPod
   */
  val GoldenPod = new BrowserFamily("GoldenPod")

  /**
   * GOM Player
   */
  val GOMPlayer = new BrowserFamily("GOM Player")

  /**
   * gonzo
   */
  val gonzo = new BrowserFamily("gonzo")

  /**
   * Google App Engine
   */
  val GoogleAppEngine = new BrowserFamily("Google App Engine")

  /**
   * Google Earth
   */
  val GoogleEarth = new BrowserFamily("Google Earth")

  /**
   * Google Friend Connect
   */
  val GoogleFriendConnect = new BrowserFamily("Google Friend Connect")

  /**
   * Google Listen
   */
  val GoogleListen = new BrowserFamily("Google Listen")

  /**
   * Google Rich Snippets Testing Tool
   */
  val GoogleRichSnippetsTestingTool = new BrowserFamily("Google Rich Snippets Testing Tool")

  /**
   * Google Wireless Transcoder
   */
  val GoogleWirelessTranscoder = new BrowserFamily("Google Wireless Transcoder")

  /**
   * Googlebot
   */
  val Googlebot = new BrowserFamily("Googlebot")

  /**
   * Googlebot-Mobile
   */
  val GooglebotMobile = new BrowserFamily("Googlebot-Mobile")

  /**
   * gPodder
   */
  val gPodder = new BrowserFamily("gPodder")

  /**
   * GrapeshotCrawler
   */
  val GrapeshotCrawler = new BrowserFamily("GrapeshotCrawler")

  /**
   * GreatNews
   */
  val GreatNews = new BrowserFamily("GreatNews")

  /**
   * GreenBrowser
   */
  val GreenBrowser = new BrowserFamily("GreenBrowser")

  /**
   * Gregarius
   */
  val Gregarius = new BrowserFamily("Gregarius")

  /**
   * GSiteCrawler
   */
  val GSiteCrawler = new BrowserFamily("GSiteCrawler")

  /**
   * GStreamer
   */
  val GStreamer = new BrowserFamily("GStreamer")

  /**
   * GurujiBot
   */
  val GurujiBot = new BrowserFamily("GurujiBot")

  /**
   * Hailoobot
   */
  val Hailoobot = new BrowserFamily("Hailoobot")

  /**
   * HatenaScreenshot
   */
  val HatenaScreenshot = new BrowserFamily("HatenaScreenshot")

  /**
   * HeartRails_Capture
   */
  val HeartRailsCapture = new BrowserFamily("HeartRails_Capture")

  /**
   * heritrix
   */
  val heritrix = new BrowserFamily("heritrix")

  /**
   * HiddenMarket
   */
  val HiddenMarket = new BrowserFamily("HiddenMarket")

  /**
   * Holmes
   */
  val Holmes = new BrowserFamily("Holmes")

  /**
   * HolmesBot
   */
  val HolmesBot = new BrowserFamily("HolmesBot")

  /**
   * HomeTags
   */
  val HomeTags = new BrowserFamily("HomeTags")

  /**
   * HooWWWer
   */
  val HooWWWer = new BrowserFamily("HooWWWer")

  /**
   * HostTracker.com
   */
  val HostTrackercom = new BrowserFamily("HostTracker.com")

  /**
   * HotJava
   */
  val HotJava = new BrowserFamily("HotJava")

  /**
   * ht://Dig
   */
  val htDig = new BrowserFamily("ht://Dig")

  /**
   * HTML2JPG
   */
  val HTML2JPG = new BrowserFamily("HTML2JPG")

  /**
   * HTMLayout
   */
  val HTMLayout = new BrowserFamily("HTMLayout")

  /**
   * HTMLParser
   */
  val HTMLParser = new BrowserFamily("HTMLParser")

  /**
   * HTTP nagios plugin
   */
  val HTTPnagiosplugin = new BrowserFamily("HTTP nagios plugin")

  /**
   * HTTP_Request2
   */
  val HTTPRequest2 = new BrowserFamily("HTTP_Request2")

  /**
   * HTTrack
   */
  val HTTrack = new BrowserFamily("HTTrack")

  /**
   * HuaweiSymantecSpider
   */
  val HuaweiSymantecSpider = new BrowserFamily("HuaweiSymantecSpider")

  /**
   * Hv3
   */
  val Hv3 = new BrowserFamily("Hv3")

  /**
   * Hydra Browser
   */
  val HydraBrowser = new BrowserFamily("Hydra Browser")

  /**
   * ia_archiver
   */
  val iaarchiver = new BrowserFamily("ia_archiver")

  /**
   * iaskspider
   */
  val iaskspider = new BrowserFamily("iaskspider")

  /**
   * IBrowse
   */
  val IBrowse = new BrowserFamily("IBrowse")

  /**
   * iCab
   */
  val iCab = new BrowserFamily("iCab")

  /**
   * iCatcher!
   */
  val iCatcher = new BrowserFamily("iCatcher!")

  /**
   * ICC-Crawler
   */
  val ICCCrawler = new BrowserFamily("ICC-Crawler")

  /**
   * ICE browser
   */
  val ICEbrowser = new BrowserFamily("ICE browser")

  /**
   * IceApe
   */
  val IceApe = new BrowserFamily("IceApe")

  /**
   * IceCat
   */
  val IceCat = new BrowserFamily("IceCat")

  /**
   * IceDragon: A faster, more secure version of Firefox
   */
  val IceDragon = new BrowserFamily("IceDragon")

  /**
   * IceWeasel
   */
  val IceWeasel = new BrowserFamily("IceWeasel")

  /**
   * ICF_Site_Crawler
   */
  val ICFSiteCrawler = new BrowserFamily("ICF_Site_Crawler")

  /**
   * ichiro
   */
  val ichiro = new BrowserFamily("ichiro")

  /**
   * iCjobs
   */
  val iCjobs = new BrowserFamily("iCjobs")

  /**
   * Internet Explorer
   */
  val IE = new BrowserFamily("IE")

  /**
   * Internet Explorer Mobile
   */
  val IEMobile = new BrowserFamily("IE Mobile")

  /**
   * Internet Explorer RSS reader
   */
  val IERSSreader = new BrowserFamily("IE RSS reader")

  /**
   * iGetter
   */
  val iGetter = new BrowserFamily("iGetter")

  /**
   * iGooMap
   */
  val iGooMap = new BrowserFamily("iGooMap")

  /**
   * IlseBot
   */
  val IlseBot = new BrowserFamily("IlseBot")

  /**
   * IlTrovatore
   */
  val IlTrovatore = new BrowserFamily("IlTrovatore")

  /**
   * IlTrovatore-Setaccio
   */
  val IlTrovatoreSetaccio = new BrowserFamily("IlTrovatore-Setaccio")

  /**
   * imbot
   */
  val imbot = new BrowserFamily("imbot")

  /**
   * Indy Library
   */
  val IndyLibrary = new BrowserFamily("Indy Library")

  /**
   * Influencebot
   */
  val Influencebot = new BrowserFamily("Influencebot")

  /**
   * InfociousBot
   */
  val InfociousBot = new BrowserFamily("InfociousBot")

  /**
   * Infohelfer
   */
  val Infohelfer = new BrowserFamily("Infohelfer")

  /**
   * InternetSeer
   */
  val InternetSeer = new BrowserFamily("InternetSeer")

  /**
   * InternetSurfboard
   */
  val InternetSurfboard = new BrowserFamily("InternetSurfboard")

  /**
   * Ipselonbot
   */
  val Ipselonbot = new BrowserFamily("Ipselonbot")

  /**
   * iRider
   */
  val iRider = new BrowserFamily("iRider")

  /**
   * IRLbot
   */
  val IRLbot = new BrowserFamily("IRLbot")

  /**
   * Iron
   */
  val Iron = new BrowserFamily("Iron")

  /**
   * iSiloX
   */
  val iSiloX = new BrowserFamily("iSiloX")

  /**
   * iSiloXC
   */
  val iSiloXC = new BrowserFamily("iSiloXC")

  /**
   * iTunes
   */
  val iTunes = new BrowserFamily("iTunes")

  /**
   * iVideo
   */
  val iVideo = new BrowserFamily("iVideo")

  /**
   * IXR lib
   */
  val IXRlib = new BrowserFamily("IXR lib")

  /**
   * JadynAve
   */
  val JadynAve = new BrowserFamily("JadynAve")

  /**
   * JadynAveBot
   */
  val JadynAveBot = new BrowserFamily("JadynAveBot")

  /**
   * Jakarta Commons-HttpClient
   */
  val JakartaCommonsHttpClient = new BrowserFamily("Jakarta Commons-HttpClient")

  /**
   * Jambot
   */
  val Jambot = new BrowserFamily("Jambot")

  /**
   * Jamcast
   */
  val Jamcast = new BrowserFamily("Jamcast")

  /**
   * Jasmine
   */
  val Jasmine = new BrowserFamily("Jasmine")

  /**
   * Java
   */
  val Java = new BrowserFamily("Java")

  /**
   * JikeSpider
   */
  val JikeSpider = new BrowserFamily("JikeSpider")

  /**
   * Job Roboter Spider
   */
  val JobRoboterSpider = new BrowserFamily("Job Roboter Spider")

  /**
   * JoBo
   */
  val JoBo = new BrowserFamily("JoBo")

  /**
   * JS-Kit/Echo
   */
  val JSKitEcho = new BrowserFamily("JS-Kit/Echo")

  /**
   * JUST-CRAWLER
   */
  val JUSTCRAWLER = new BrowserFamily("JUST-CRAWLER")

  /**
   * Jyxobot
   */
  val Jyxobot = new BrowserFamily("Jyxobot")

  /**
   * K-Meleon
   */
  val KMeleon = new BrowserFamily("K-Meleon")

  /**
   * K-Ninja
   */
  val KNinja = new BrowserFamily("K-Ninja")

  /**
   * Kakle Bot
   */
  val KakleBot = new BrowserFamily("Kakle Bot")

  /**
   * Kalooga
   */
  val Kalooga = new BrowserFamily("Kalooga")

  /**
   * Kapiko
   */
  val Kapiko = new BrowserFamily("Kapiko")

  /**
   * Karneval-Bot
   */
  val KarnevalBot = new BrowserFamily("Karneval-Bot")

  /**
   * Kazehakase
   */
  val Kazehakase = new BrowserFamily("Kazehakase")

  /**
   * KeywenBot
   */
  val KeywenBot = new BrowserFamily("KeywenBot")

  /**
   * KeywordDensityRobot
   */
  val KeywordDensityRobot = new BrowserFamily("KeywordDensityRobot")

  /**
   * Kindle Browser
   */
  val KindleBrowser = new BrowserFamily("Kindle Browser")

  /**
   * Kirix Strata
   */
  val KirixStrata = new BrowserFamily("Kirix Strata")

  /**
   * KKman
   */
  val KKman = new BrowserFamily("KKman")

  /**
   * Klondike
   */
  val Klondike = new BrowserFamily("Klondike")

  /**
   * Kongulo
   */
  val Kongulo = new BrowserFamily("Kongulo")

  /**
   * Konqueror
   */
  val Konqueror = new BrowserFamily("Konqueror")

  /**
   * KRetrieve
   */
  val KRetrieve = new BrowserFamily("KRetrieve")

  /**
   * Krugle
   */
  val Krugle = new BrowserFamily("Krugle")

  /**
   * ksibot
   */
  val ksibot = new BrowserFamily("ksibot")

  /**
   * Kylo
   */
  val Kylo = new BrowserFamily("Kylo")

  /**
   * L.webis
   */
  val Lwebis = new BrowserFamily("L.webis")

  /**
   * LapozzBot
   */
  val LapozzBot = new BrowserFamily("LapozzBot")

  /**
   * Larbin
   */
  val Larbin = new BrowserFamily("Larbin")

  /**
   * LBrowser
   */
  val LBrowser = new BrowserFamily("LBrowser")

  /**
   * LeechCraft
   */
  val LeechCraft = new BrowserFamily("LeechCraft")

  /**
   * LemurWebCrawler
   */
  val LemurWebCrawler = new BrowserFamily("LemurWebCrawler")

  /**
   * LexxeBot
   */
  val LexxeBot = new BrowserFamily("LexxeBot")

  /**
   * LFTP
   */
  val LFTP = new BrowserFamily("LFTP")

  /**
   * LibSoup
   */
  val LibSoup = new BrowserFamily("LibSoup")

  /**
   * libwww-perl
   */
  val libwwwperl = new BrowserFamily("libwww-perl")

  /**
   * Liferea
   */
  val Liferea = new BrowserFamily("Liferea")

  /**
   * Lijit
   */
  val Lijit = new BrowserFamily("Lijit")

  /**
   * LinguaBot
   */
  val LinguaBot = new BrowserFamily("LinguaBot")

  /**
   * Linguee Bot
   */
  val LingueeBot = new BrowserFamily("Linguee Bot")

  /**
   * Link Valet Online
   */
  val LinkValetOnline = new BrowserFamily("Link Valet Online")

  /**
   * LinkAider
   */
  val LinkAider = new BrowserFamily("LinkAider")

  /**
   * LinkbackPlugin for Laconica
   */
  val LinkbackPluginforLaconica = new BrowserFamily("LinkbackPlugin for Laconica")

  /**
   * LinkChecker
   */
  val LinkChecker = new BrowserFamily("LinkChecker")

  /**
   * linkdex.com
   */
  val linkdexcom = new BrowserFamily("linkdex.com")

  /**
   * LinkExaminer
   */
  val LinkExaminer = new BrowserFamily("LinkExaminer")

  /**
   * Links
   */
  val Links = new BrowserFamily("Links")

  /**
   * linksmanager_bot
   */
  val linksmanagerbot = new BrowserFamily("linksmanager_bot")

  /**
   * LinkWalker
   */
  val LinkWalker = new BrowserFamily("LinkWalker")

  /**
   * livedoor ScreenShot
   */
  val livedoorScreenShot = new BrowserFamily("livedoor ScreenShot")

  /**
   * lmspider
   */
  val lmspider = new BrowserFamily("lmspider")

  /**
   * Lobo
   */
  val Lobo = new BrowserFamily("Lobo")

  /**
   * lolifox
   */
  val lolifox = new BrowserFamily("lolifox")

  /**
   * Lotus Notes
   */
  val LotusNotes = new BrowserFamily("Lotus Notes")

  /**
   * Lunascape
   */
  val Lunascape = new BrowserFamily("Lunascape")

  /**
   * LWP::Simple
   */
  val LWPSimple = new BrowserFamily("LWP::Simple")

  /**
   * Lynx
   */
  val Lynx = new BrowserFamily("Lynx")

  /**
   * Madfox
   */
  val Madfox = new BrowserFamily("Madfox")

  /**
   * magpie-crawler
   */
  val magpiecrawler = new BrowserFamily("magpie-crawler")

  /**
   * MagpieRSS
   */
  val MagpieRSS = new BrowserFamily("MagpieRSS")

  /**
   * Mahiti Crawler
   */
  val MahitiCrawler = new BrowserFamily("Mahiti Crawler")

  /**
   * Mail.RU
   */
  val MAILRU = new BrowserFamily("Mail.Ru")

  /**
   * Maple browser
   */
  val Maplebrowser = new BrowserFamily("Maple browser")

  /**
   * Maxthon
   */
  val Maxthon = new BrowserFamily("Maxthon")

  /**
   * Mechanize
   */
  val Mechanize = new BrowserFamily("Mechanize")

  /**
   * Megatext
   */
  val Megatext = new BrowserFamily("Megatext")

  /**
   * MetaGeneratorCrawler
   */
  val MetaGeneratorCrawler = new BrowserFamily("MetaGeneratorCrawler")

  /**
   * MetaJobBot
   */
  val MetaJobBot = new BrowserFamily("MetaJobBot")

  /**
   * MetamojiCrawler
   */
  val MetamojiCrawler = new BrowserFamily("MetamojiCrawler")

  /**
   * Metaspinner/0.01
   */
  val Metaspinner = new BrowserFamily("Metaspinner")

  /**
   * MetaTagRobot
   */
  val MetaTagRobot = new BrowserFamily("MetaTagRobot")

  /**
   * MetaURI
   */
  val MetaURI = new BrowserFamily("MetaURI")

  /**
   * MIA Bot
   */
  val MIABot = new BrowserFamily("MIA Bot")

  /**
   * MicroB
   */
  val MicroB = new BrowserFamily("MicroB")

  /**
   * Microsoft Office Existence Discovery
   */
  val MicrosoftOfficeExistenceDiscovery = new BrowserFamily("Microsoft Office Existence Discovery")

  /**
   * Microsoft WebDAV client
   */
  val MicrosoftWebDAVclient = new BrowserFamily("Microsoft WebDAV client")

  /**
   * Midori
   */
  val Midori = new BrowserFamily("Midori")

  /**
   * Mini Browser
   */
  val MiniBrowser = new BrowserFamily("Mini Browser")

  /**
   * Minimo
   */
  val Minimo = new BrowserFamily("Minimo")

  /**
   * miniRank
   */
  val miniRank = new BrowserFamily("miniRank")

  /**
   * Miro
   */
  val Miro = new BrowserFamily("Miro")

  /**
   * MJ12bot
   */
  val MJ12bot = new BrowserFamily("MJ12bot")

  /**
   * MLBot
   */
  val MLBot = new BrowserFamily("MLBot")

  /**
   * MnoGoSearch
   */
  val MnoGoSearch = new BrowserFamily("MnoGoSearch")

  /**
   * Moatbot
   */
  val Moatbot = new BrowserFamily("Moatbot")

  /**
   * moba-crawler
   */
  val mobacrawler = new BrowserFamily("moba-crawler")

  /**
   * Mobile Firefox
   */
  val MobileFirefox = new BrowserFamily("Mobile Firefox")

  /**
   * Mobile Safari
   */
  val MobileSafari = new BrowserFamily("Mobile Safari")

  /**
   * MojeekBot
   */
  val MojeekBot = new BrowserFamily("MojeekBot")

  /**
   * Motoricerca-Robots.txt-Checker
   */
  val MotoricercaRobotstxtChecker = new BrowserFamily("Motoricerca-Robots.txt-Checker")

  /**
   * Motorola Internet Browser
   */
  val MotorolaInternetBrowser = new BrowserFamily("Motorola Internet Browser")

  /**
   * mozDex
   */
  val mozDex = new BrowserFamily("mozDex")

  /**
   * Mozilla
   */
  val Mozilla = new BrowserFamily("Mozilla")

  /**
   * Mp3Bot
   */
  val Mp3Bot = new BrowserFamily("Mp3Bot")

  /**
   * MPlayer
   */
  val MPlayer = new BrowserFamily("MPlayer")

  /**
   * MPlayer2
   */
  val MPlayer2 = new BrowserFamily("MPlayer2")

  /**
   * MQbot
   */
  val MQbot = new BrowserFamily("MQbot")

  /**
   * MSNBot
   */
  val MSNBot = new BrowserFamily("MSNBot")

  /**
   * MSRBOT
   */
  val MSRBOT = new BrowserFamily("MSRBOT")

  /**
   * muCommander
   */
  val muCommander = new BrowserFamily("muCommander")

  /**
   * Multi-Browser XP
   */
  val MultiBrowserXP = new BrowserFamily("Multi-Browser XP")

  /**
   * MultiCrawler
   */
  val MultiCrawler = new BrowserFamily("MultiCrawler")

  /**
   * Multipage Validator
   */
  val MultipageValidator = new BrowserFamily("Multipage Validator")

  /**
   * MultiZilla
   */
  val MultiZilla = new BrowserFamily("MultiZilla")

  /**
   * My Internet Browser
   */
  val MyInternetBrowser = new BrowserFamily("My Internet Browser")

  /**
   * MyFamilyBot
   */
  val MyFamilyBot = new BrowserFamily("MyFamilyBot")

  /**
   * Najdi.si
   */
  val Najdisi = new BrowserFamily("Najdi.si")

  /**
   * NaverBot
   */
  val NaverBot = new BrowserFamily("NaverBot")

  /**
   * navissobot
   */
  val navissobot = new BrowserFamily("navissobot")

  /**
   * NCSA Mosaic
   */
  val NCSAMosaic = new BrowserFamily("NCSA Mosaic")

  /**
   * NerdByNature.Bot
   */
  val NerdByNatureBot = new BrowserFamily("NerdByNature.Bot")

  /**
   * nestReader
   */
  val nestReader = new BrowserFamily("nestReader")

  /**
   * NetBox
   */
  val NetBox = new BrowserFamily("NetBox")

  /**
   * NetCaptor
   */
  val NetCaptor = new BrowserFamily("NetCaptor")

  /**
   * NetcraftSurveyAgent
   */
  val NetcraftSurveyAgent = new BrowserFamily("NetcraftSurveyAgent")

  /**
   * netEstate Crawler
   */
  val netEstateCrawler = new BrowserFamily("netEstate Crawler")

  /**
   * NetFront
   */
  val NetFront = new BrowserFamily("NetFront")

  /**
   * NetFront Mobile Content Viewer
   */
  val NetFrontMobileContentViewer = new BrowserFamily("NetFront Mobile Content Viewer")

  /**
   * Netintelligence LiveAssessment
   */
  val NetintelligenceLiveAssessment = new BrowserFamily("Netintelligence LiveAssessment")

  /**
   * NetNewsWire
   */
  val NetNewsWire = new BrowserFamily("NetNewsWire")

  /**
   * NetPositive
   */
  val NetPositive = new BrowserFamily("NetPositive")

  /**
   * NetResearchServer
   */
  val NetResearchServer = new BrowserFamily("NetResearchServer")

  /**
   * Netscape Navigator
   */
  val NetscapeNavigator = new BrowserFamily("Netscape Navigator")

  /**
   * Netseer
   */
  val Netseer = new BrowserFamily("Netseer")

  /**
   * NetSurf
   */
  val NetSurf = new BrowserFamily("NetSurf")

  /**
   * Netvibes feed reader
   */
  val Netvibesfeedreader = new BrowserFamily("Netvibes feed reader")

  /**
   * NetWhatCrawler
   */
  val NetWhatCrawler = new BrowserFamily("NetWhatCrawler")

  /**
   * Newsbeuter
   */
  val Newsbeuter = new BrowserFamily("Newsbeuter")

  /**
   * NewsBreak
   */
  val NewsBreak = new BrowserFamily("NewsBreak")

  /**
   * NewsFox
   */
  val NewsFox = new BrowserFamily("NewsFox")

  /**
   * NewsGatorOnline
   */
  val NewsGatorOnline = new BrowserFamily("NewsGatorOnline")

  /**
   * NextGenSearchBot
   */
  val NextGenSearchBot = new BrowserFamily("NextGenSearchBot")

  /**
   * nextthing.org
   */
  val nextthingorg = new BrowserFamily("nextthing.org")

  /**
   * NFReader
   */
  val NFReader = new BrowserFamily("NFReader")

  /**
   * NG
   */
  val NG = new BrowserFamily("NG")

  /**
   * NG-Search
   */
  val NGSearch = new BrowserFamily("NG-Search")

  /**
   * Nigma.ru
   */
  val Nigmaru = new BrowserFamily("Nigma.ru")

  /**
   * NimbleCrawler
   */
  val NimbleCrawler = new BrowserFamily("NimbleCrawler")

  /**
   * NineSky
   */
  val NineSky = new BrowserFamily("NineSky")

  /**
   * Nintendo Browser
   */
  val NintendoBrowser = new BrowserFamily("Nintendo Browser")

  /**
   * nodestackbot
   */
  val nodestackbot = new BrowserFamily("nodestackbot")

  /**
   * Nokia SyncML Client
   */
  val NokiaSyncMLClient = new BrowserFamily("Nokia SyncML Client")

  /**
   * Nokia Web Browser
   */
  val NokiaWebBrowser = new BrowserFamily("Nokia Web Browser")

  /**
   * Novell BorderManager
   */
  val NovellBorderManager = new BrowserFamily("Novell BorderManager")

  /**
   * noyona
   */
  val noyona = new BrowserFamily("noyona")

  /**
   * NPBot
   */
  val NPBot = new BrowserFamily("NPBot")

  /**
   * Nuhk
   */
  val Nuhk = new BrowserFamily("Nuhk")

  /**
   * NuSearch Spider
   */
  val NuSearchSpider = new BrowserFamily("NuSearch Spider")

  /**
   * Nutch
   */
  val Nutch = new BrowserFamily("Nutch")

  /**
   * nworm
   */
  val nworm = new BrowserFamily("nworm")

  /**
   * Nymesis
   */
  val Nymesis = new BrowserFamily("Nymesis")

  /**
   * Obigo
   */
  val Obigo = new BrowserFamily("Obigo")

  /**
   * oBot
   */
  val oBot = new BrowserFamily("oBot")

  /**
   * Ocelli
   */
  val Ocelli = new BrowserFamily("Ocelli")

  /**
   * Off By One
   */
  val OffByOne = new BrowserFamily("Off By One")

  /**
   * Offline Explorer
   */
  val OfflineExplorer = new BrowserFamily("Offline Explorer")

  /**
   * Omea Reader
   */
  val OmeaReader = new BrowserFamily("Omea Reader")

  /**
   * OmniExplorer_Bot
   */
  val OmniExplorerBot = new BrowserFamily("OmniExplorer_Bot")

  /**
   * OmniWeb
   */
  val OmniWeb = new BrowserFamily("OmniWeb")

  /**
   * OnetSzukaj
   */
  val OnetSzukaj = new BrowserFamily("OnetSzukaj")

  /**
   * Openbot
   */
  val Openbot = new BrowserFamily("Openbot")

  /**
   * OpenCalaisSemanticProxy
   */
  val OpenCalaisSemanticProxy = new BrowserFamily("OpenCalaisSemanticProxy")

  /**
   * OpenindexSpider
   */
  val OpenindexSpider = new BrowserFamily("OpenindexSpider")

  /**
   * Openwave Mobile Browser
   */
  val OpenwaveMobileBrowser = new BrowserFamily("Openwave Mobile Browser")

  /**
   * Opera
   */
  val Opera = new BrowserFamily("Opera")

  /**
   * Opera Mini
   */
  val OperaMini = new BrowserFamily("Opera Mini")

  /**
   * Opera Mobile
   */
  val OperaMobile = new BrowserFamily("Opera Mobile")

  /**
   * Orbiter
   */
  val Orbiter = new BrowserFamily("Orbiter")

  /**
   * Orca
   */
  val Orca = new BrowserFamily("Orca")

  /**
   * Oregano
   */
  val Oregano = new BrowserFamily("Oregano")

  /**
   * OrgbyBot
   */
  val OrgbyBot = new BrowserFamily("OrgbyBot")

  /**
   * OsObot
   */
  val OsObot = new BrowserFamily("OsObot")

  /**
   * Outlook 2007
   */
  val Outlook2007 = new BrowserFamily("Outlook 2007")

  /**
   * Outlook 2010
   */
  val Outlook2010 = new BrowserFamily("Outlook 2010")

  /**
   * Outlook 2013
   */
  val Outlook2013 = new BrowserFamily("Outlook 2013")

  /**
   * OWB
   */
  val OWB = new BrowserFamily("OWB")

  /**
   * owsBot
   */
  val owsBot = new BrowserFamily("owsBot")

  /**
   * P3P Validator
   */
  val P3PValidator = new BrowserFamily("P3P Validator")

  /**
   * page_verifier
   */
  val pageverifier = new BrowserFamily("page_verifier")

  /**
   * Page2RSS
   */
  val Page2RSS = new BrowserFamily("Page2RSS")

  /**
   * PageBitesHyperBot
   */
  val PageBitesHyperBot = new BrowserFamily("PageBitesHyperBot")

  /**
   * PagePeeker
   */
  val PagePeeker = new BrowserFamily("PagePeeker")

  /**
   * Pale Moon
   */
  val PaleMoon = new BrowserFamily("Pale Moon")

  /**
   * Palm Pre web browser
   */
  val PalmPrewebbrowser = new BrowserFamily("Palm Pre web browser")

  /**
   * Panscient web crawler
   */
  val Panscientwebcrawler = new BrowserFamily("Panscient web crawler")

  /**
   * Paparazzi!
   */
  val Paparazzi = new BrowserFamily("Paparazzi!")

  /**
   * PaperLiBot
   */
  val PaperLiBot = new BrowserFamily("PaperLiBot")

  /**
   * ParchBot
   */
  val ParchBot = new BrowserFamily("ParchBot")

  /**
   * Patriott
   */
  val Patriott = new BrowserFamily("Patriott")

  /**
   * Pattern is a web mining module for the Python programming language.
   */
  val Pattern = new BrowserFamily("Pattern")

  /**
   * PEAR HTTP_Request
   */
  val PEARHTTPRequest = new BrowserFamily("PEAR HTTP_Request")

  /**
   * Peew
   */
  val Peew = new BrowserFamily("Peew")

  /**
   * percbotspider
   */
  val percbotspider = new BrowserFamily("percbotspider")

  /**
   * Phaseout
   */
  val Phaseout = new BrowserFamily("Phaseout")

  /**
   * Phoenix (old name for Firefox)
   */
  val PhoenixoldnameforFirefox = new BrowserFamily("Phoenix (old name for Firefox)")

  /**
   * PHP
   */
  val PHP = new BrowserFamily("PHP")

  /**
   * PHP link checker
   */
  val PHPlinkchecker = new BrowserFamily("PHP link checker")

  /**
   * PHP OpenID library
   */
  val PHPOpenIDlibrary = new BrowserFamily("PHP OpenID library")

  /**
   * PHPcrawl
   */
  val PHPcrawl = new BrowserFamily("PHPcrawl")

  /**
   * pingdom.com_bot
   */
  val pingdomcombot = new BrowserFamily("pingdom.com_bot")

  /**
   * Pixray-Seeker
   */
  val PixraySeeker = new BrowserFamily("Pixray-Seeker")

  /**
   * Plex Media Center
   */
  val PlexMediaCenter = new BrowserFamily("Plex Media Center")

  /**
   * Plukkie
   */
  val Plukkie = new BrowserFamily("Plukkie")

  /**
   * Pocket Tunes
   */
  val PocketTunes = new BrowserFamily("Pocket Tunes")

  /**
   * PocoMail
   */
  val PocoMail = new BrowserFamily("PocoMail")

  /**
   * Podkicker
   */
  val Podkicker = new BrowserFamily("Podkicker")

  /**
   * POE-Component-Client-HTTP
   */
  val POEComponentClientHTTP = new BrowserFamily("POE-Component-Client-HTTP")

  /**
   * Pogodak.co.yu
   */
  val Pogodakcoyu = new BrowserFamily("Pogodak.co.yu")

  /**
   * Polaris
   */
  val Polaris = new BrowserFamily("Polaris")

  /**
   * polixea.de
   */
  val polixeade = new BrowserFamily("polixea.de")

  /**
   * Pompos
   */
  val Pompos = new BrowserFamily("Pompos")

  /**
   * Postbox
   */
  val Postbox = new BrowserFamily("Postbox")

  /**
   * posterus
   */
  val posterus = new BrowserFamily("posterus")

  /**
   * PostPost
   */
  val PostPost = new BrowserFamily("PostPost")

  /**
   * Powermarks
   */
  val Powermarks = new BrowserFamily("Powermarks")

  /**
   * Prism
   */
  val Prism = new BrowserFamily("Prism")

  /**
   * ProCogBot
   */
  val ProCogBot = new BrowserFamily("ProCogBot")

  /**
   * proximic
   */
  val proximic = new BrowserFamily("proximic")

  /**
   * PRTG Network Monitor
   */
  val PRTGNetworkMonitor = new BrowserFamily("PRTG Network Monitor")

  /**
   * PS Vita browser
   */
  val PSVitabrowser = new BrowserFamily("PS Vita browser")

  /**
   * psbot
   */
  val psbot = new BrowserFamily("psbot")

  /**
   * ptd-crawler
   */
  val ptdcrawler = new BrowserFamily("ptd-crawler")

  /**
   * Public Radio Player
   */
  val PublicRadioPlayer = new BrowserFamily("Public Radio Player")

  /**
   * PycURL
   */
  val PycURL = new BrowserFamily("PycURL")

  /**
   * Python-requests
   */
  val Pythonrequests = new BrowserFamily("Python-requests")

  /**
   * Python-urllib
   */
  val Pythonurllib = new BrowserFamily("Python-urllib")

  /**
   * Python-webchecker
   */
  val Pythonwebchecker = new BrowserFamily("Python-webchecker")

  /**
   * Qirina Hurdler
   */
  val QirinaHurdler = new BrowserFamily("Qirina Hurdler")

  /**
   * QQbrowser
   */
  val QQbrowser = new BrowserFamily("QQbrowser")

  /**
   * Qseero
   */
  val Qseero = new BrowserFamily("Qseero")

  /**
   * QtWeb
   */
  val QtWeb = new BrowserFamily("QtWeb")

  /**
   * Qualidator.com Bot
   */
  val QualidatorcomBot = new BrowserFamily("Qualidator.com Bot")

  /**
   * Quantcastbot
   */
  val Quantcastbot = new BrowserFamily("Quantcastbot")

  /**
   * quickobot
   */
  val quickobot = new BrowserFamily("quickobot")

  /**
   * QuickTime
   */
  val QuickTime = new BrowserFamily("QuickTime")

  /**
   * QupZilla
   */
  val QupZilla = new BrowserFamily("QupZilla")

  /**
   * R6 bot
   */
  val R6bot = new BrowserFamily("R6 bot")

  /**
   * RADaR-Bot
   */
  val RADaRBot = new BrowserFamily("RADaR-Bot")

  /**
   * Radio Downloader
   */
  val RadioDownloader = new BrowserFamily("Radio Downloader")

  /**
   * RankurBot
   */
  val RankurBot = new BrowserFamily("RankurBot")

  /**
   * RedBot
   */
  val RedBot = new BrowserFamily("RedBot")

  /**
   * Reeder
   */
  val Reeder = new BrowserFamily("Reeder")

  /**
   * Rekonq
   */
  val Rekonq = new BrowserFamily("Rekonq")

  /**
   * REL Link Checker Lite
   */
  val RELLinkCheckerLite = new BrowserFamily("REL Link Checker Lite")

  /**
   * retawq
   */
  val retawq = new BrowserFamily("retawq")

  /**
   * Robo Crawler
   */
  val RoboCrawler = new BrowserFamily("Robo Crawler")

  /**
   * Robots_Tester
   */
  val RobotsTester = new BrowserFamily("Robots_Tester")

  /**
   * Robozilla
   */
  val Robozilla = new BrowserFamily("Robozilla")

  /**
   * RockMelt
   */
  val RockMelt = new BrowserFamily("RockMelt")

  /**
   * ROME library
   */
  val ROMElibrary = new BrowserFamily("ROME library")

  /**
   * Ronzoobot
   */
  val Ronzoobot = new BrowserFamily("Ronzoobot")

  /**
   * Rss Bandit
   */
  val RssBandit = new BrowserFamily("Rss Bandit")

  /**
   * RSS Menu
   */
  val RSSMenu = new BrowserFamily("RSS Menu")

  /**
   * RSS Popper
   */
  val RSSPopper = new BrowserFamily("RSS Popper")

  /**
   * RSS Radio
   */
  val RSSRadio = new BrowserFamily("RSS Radio")

  /**
   * RSSMicro.com RSS/Atom Feed Robot
   */
  val RSSMicrocomRSSAtomFeedRobot = new BrowserFamily("RSSMicro.com RSS/Atom Feed Robot")

  /**
   * RSSOwl
   */
  val RSSOwl = new BrowserFamily("RSSOwl")

  /**
   * Ruky-Roboter
   */
  val RukyRoboter = new BrowserFamily("Ruky-Roboter")

  /**
   * Ryouko
   */
  val Ryouko = new BrowserFamily("Ryouko")

  /**
   * RyzeCrawler
   */
  val RyzeCrawler = new BrowserFamily("RyzeCrawler")

  /**
   * SaaYaa Explorer
   */
  val SaaYaaExplorer = new BrowserFamily("SaaYaa Explorer")

  /**
   * Safari
   */
  val Safari = new BrowserFamily("Safari")

  /**
   * Safari RSS reader
   */
  val SafariRSSreader = new BrowserFamily("Safari RSS reader")

  /**
   * Sage
   */
  val Sage = new BrowserFamily("Sage")

  /**
   * SAI Crawler
   */
  val SAICrawler = new BrowserFamily("SAI Crawler")

  /**
   * SanszBot
   */
  val SanszBot = new BrowserFamily("SanszBot")

  /**
   * SBIder
   */
  val SBIder = new BrowserFamily("SBIder")

  /**
   * SBSearch
   */
  val SBSearch = new BrowserFamily("SBSearch")

  /**
   * Scarlett
   */
  val Scarlett = new BrowserFamily("Scarlett")

  /**
   * schibstedsokbot
   */
  val schibstedsokbot = new BrowserFamily("schibstedsokbot")

  /**
   * ScollSpider
   */
  val ScollSpider = new BrowserFamily("ScollSpider")

  /**
   * Scooter
   */
  val Scooter = new BrowserFamily("Scooter")

  /**
   * ScoutJet
   */
  val ScoutJet = new BrowserFamily("ScoutJet")

  /**
   * SeaMonkey
   */
  val SeaMonkey = new BrowserFamily("SeaMonkey")

  /**
   * Search Engine World Robots.txt Validator
   */
  val SearchEngineWorldRobotsTextValidator = new BrowserFamily("Search Engine World Robots.txt Validator")

  /**
   * search.KumKie.com
   */
  val searchKumKiecom = new BrowserFamily("search.KumKie.com")

  /**
   * Search17Bot
   */
  val Search17Bot = new BrowserFamily("Search17Bot")

  /**
   * Semager
   */
  val Semager = new BrowserFamily("Semager")

  /**
   * SEMC Browser
   */
  val SEMCBrowser = new BrowserFamily("SEMC Browser")

  /**
   * SemrushBot
   */
  val SemrushBot = new BrowserFamily("SemrushBot")

  /**
   * Sensis Web Crawler
   */
  val SensisWebCrawler = new BrowserFamily("Sensis Web Crawler")

  /**
   * SEODat
   */
  val SEODat = new BrowserFamily("SEODat")

  /**
   * SEOENGBot
   */
  val SEOENGBot = new BrowserFamily("SEOENGBot")

  /**
   * SEOkicks-Robot
   */
  val SEOkicksRobot = new BrowserFamily("SEOkicks-Robot")

  /**
   * Setoozbot
   */
  val Setoozbot = new BrowserFamily("Setoozbot")

  /**
   * Seznam RSS reader
   */
  val SeznamRSSreader = new BrowserFamily("Seznam RSS reader")

  /**
   * Seznam WAP Proxy
   */
  val SeznamWAPProxy = new BrowserFamily("Seznam WAP Proxy")

  /**
   * SeznamBot
   */
  val SeznamBot = new BrowserFamily("SeznamBot")

  /**
   * SharpReader
   */
  val SharpReader = new BrowserFamily("SharpReader")

  /**
   * Shelob
   */
  val Shelob = new BrowserFamily("Shelob")

  /**
   * Shiira
   */
  val Shiira = new BrowserFamily("Shiira")

  /**
   * Shim-Crawler
   */
  val ShimCrawler = new BrowserFamily("Shim-Crawler")

  /**
   * ShopWiki
   */
  val ShopWiki = new BrowserFamily("ShopWiki")

  /**
   * ShowyouBot
   */
  val ShowyouBot = new BrowserFamily("ShowyouBot")

  /**
   * Shredder
   */
  val Shredder = new BrowserFamily("Shredder")

  /**
   * Siege
   */
  val Siege = new BrowserFamily("Siege")

  /**
   * silk
   */
  val silk = new BrowserFamily("silk")

  /**
   * SimplePie
   */
  val SimplePie = new BrowserFamily("SimplePie")

  /**
   * Sirketce/Busiverse
   */
  val SirketceBusiverse = new BrowserFamily("Sirketce/Busiverse")

  /**
   * sistrix
   */
  val sistrix = new BrowserFamily("sistrix")

  /**
   * Sitedomain-Bot
   */
  val SitedomainBot = new BrowserFamily("Sitedomain-Bot")

  /**
   * SiteKiosk
   */
  val SiteKiosk = new BrowserFamily("SiteKiosk")

  /**
   * SiteSucker
   */
  val SiteSucker = new BrowserFamily("SiteSucker")

  /**
   * SkipStone
   */
  val SkipStone = new BrowserFamily("SkipStone")

  /**
   * SkreemRBot
   */
  val SkreemRBot = new BrowserFamily("SkreemRBot")

  /**
   * Skyfire
   */
  val Skyfire = new BrowserFamily("Skyfire")

  /**
   * Sleipnir
   */
  val Sleipnir = new BrowserFamily("Sleipnir")

  /**
   * SlimBoat
   */
  val SlimBoat = new BrowserFamily("SlimBoat")

  /**
   * SlimBrowser
   */
  val SlimBrowser = new BrowserFamily("SlimBrowser")

  /**
   * smart.apnoti.com Robot
   */
  val smartapnoticomRobot = new BrowserFamily("smart.apnoti.com Robot")

  /**
   * snap.com
   */
  val snapcom = new BrowserFamily("snap.com")

  /**
   * SnapBot
   */
  val SnapBot = new BrowserFamily("SnapBot")

  /**
   * Snappy
   */
  val Snappy = new BrowserFamily("Snappy")

  /**
   * SniffRSS
   */
  val SniffRSS = new BrowserFamily("SniffRSS")

  /**
   * Snoopy
   */
  val Snoopy = new BrowserFamily("Snoopy")

  /**
   * Sogou
   */
  val Sogou = new BrowserFamily("Sogou")

  /**
   * Sogou Explorer
   */
  val SogouExplorer = new BrowserFamily("Sogou Explorer")

  /**
   * sogou spider
   */
  val sogouspider = new BrowserFamily("sogou spider")

  /**
   * Songbird
   */
  val Songbird = new BrowserFamily("Songbird")

  /**
   * Sosospider
   */
  val Sosospider = new BrowserFamily("Sosospider")

  /**
   * Sparrow
   */
  val Sparrow = new BrowserFamily("Sparrow")

  /**
   * spbot
   */
  val spbot = new BrowserFamily("spbot")

  /**
   * Speedy
   */
  val Speedy = new BrowserFamily("Speedy")

  /**
   * Spicebird
   */
  val Spicebird = new BrowserFamily("Spicebird")

  /**
   * SpiderLing
   */
  val SpiderLing = new BrowserFamily("SpiderLing")

  /**
   * Spinn3r
   */
  val Spinn3r = new BrowserFamily("Spinn3r")

  /**
   * Spock Crawler
   */
  val SpockCrawler = new BrowserFamily("Spock Crawler")

  /**
   * SpokeSpider
   */
  val SpokeSpider = new BrowserFamily("SpokeSpider")

  /**
   * Sproose
   */
  val Sproose = new BrowserFamily("Sproose")

  /**
   * SrevBot
   */
  val SrevBot = new BrowserFamily("SrevBot")

  /**
   * SSLBot
   */
  val SSLBot = new BrowserFamily("SSLBot")

  /**
   * StackRambler
   */
  val StackRambler = new BrowserFamily("StackRambler")

  /**
   * Stainless
   */
  val Stainless = new BrowserFamily("Stainless")

  /**
   * StatoolsBot
   */
  val StatoolsBot = new BrowserFamily("StatoolsBot")

  /**
   * Steeler
   */
  val Steeler = new BrowserFamily("Steeler")

  /**
   * Strokebot
   */
  val Strokebot = new BrowserFamily("Strokebot")

  /**
   * SubStream
   */
  val SubStream = new BrowserFamily("SubStream")

  /**
   * suggybot
   */
  val suggybot = new BrowserFamily("suggybot")

  /**
   * Summer
   */
  val Summer = new BrowserFamily("Summer")

  /**
   * Sundance
   */
  val Sundance = new BrowserFamily("Sundance")

  /**
   * Sundial
   */
  val Sundial = new BrowserFamily("Sundial")

  /**
   * Sunrise
   */
  val Sunrise = new BrowserFamily("Sunrise")

  /**
   * SuperBot
   */
  val SuperBot = new BrowserFamily("SuperBot")

  /**
   * Surf
   */
  val Surf = new BrowserFamily("Surf")

  /**
   * Surphace Scout
   */
  val SurphaceScout = new BrowserFamily("Surphace Scout")

  /**
   * SurveyBot
   */
  val SurveyBot = new BrowserFamily("SurveyBot")

  /**
   * SWEBot
   */
  val SWEBot = new BrowserFamily("SWEBot")

  /**
   * Swiftfox
   */
  val Swiftfox = new BrowserFamily("Swiftfox")

  /**
   * Swiftweasel
   */
  val Swiftweasel = new BrowserFamily("Swiftweasel")

  /**
   * SygolBot
   */
  val SygolBot = new BrowserFamily("SygolBot")

  /**
   * SynooBot
   */
  val SynooBot = new BrowserFamily("SynooBot")

  /**
   * Szukacz
   */
  val Szukacz = new BrowserFamily("Szukacz")

  /**
   * Szukankobot
   */
  val Szukankobot = new BrowserFamily("Szukankobot")

  /**
   * Tagoobot
   */
  val Tagoobot = new BrowserFamily("Tagoobot")

  /**
   * taptubot
   */
  val taptubot = new BrowserFamily("taptubot")

  /**
   * Tear
   */
  val Tear = new BrowserFamily("Tear")

  /**
   * TeaShark
   */
  val TeaShark = new BrowserFamily("TeaShark")

  /**
   * Technoratibot
   */
  val Technoratibot = new BrowserFamily("Technoratibot")

  /**
   * Teleport Pro
   */
  val TeleportPro = new BrowserFamily("Teleport Pro")

  /**
   * TenFourFox
   */
  val TenFourFox = new BrowserFamily("TenFourFox")

  /**
   * TeragramCrawler
   */
  val TeragramCrawler = new BrowserFamily("TeragramCrawler")

  /**
   * textractor
   */
  val textractor = new BrowserFamily("textractor")

  /**
   * The Bat!
   */
  val TheBat = new BrowserFamily("The Bat!")

  /**
   * Theophrastus
   */
  val Theophrastus = new BrowserFamily("Theophrastus")

  /**
   * TheWorld Browser
   */
  val TheWorldBrowser = new BrowserFamily("TheWorld Browser")

  /**
   * Thumbnail.CZ robot
   */
  val ThumbnailCZrobot = new BrowserFamily("Thumbnail.CZ robot")

  /**
   * ThumbShots-Bot
   */
  val ThumbShotsBot = new BrowserFamily("ThumbShots-Bot")

  /**
   * thumbshots-de-Bot
   */
  val thumbshotsdeBot = new BrowserFamily("thumbshots-de-Bot")

  /**
   * Thumbshots.ru
   */
  val Thumbshotsru = new BrowserFamily("Thumbshots.ru")

  /**
   * Thunderbird
   */
  val Thunderbird = new BrowserFamily("Thunderbird")

  /**
   * TinEye
   */
  val TinEye = new BrowserFamily("TinEye")

  /**
   * Tizen Browser
   */
  val TizenBrowser = new BrowserFamily("Tizen Browser")

  /**
   * Tjusig
   */
  val Tjusig = new BrowserFamily("Tjusig")

  /**
   * Topicbot
   */
  val Topicbot = new BrowserFamily("Topicbot")

  /**
   * Toread-Crawler
   */
  val ToreadCrawler = new BrowserFamily("Toread-Crawler")

  /**
   * Touche
   */
  val Touche = new BrowserFamily("Touche")

  /**
   * trendictionbot
   */
  val trendictionbot = new BrowserFamily("trendictionbot")

  /**
   * Trileet NewsRoom
   */
  val TrileetNewsRoom = new BrowserFamily("Trileet NewsRoom")

  /**
   * TT Explorer
   */
  val TTExplorer = new BrowserFamily("TT Explorer")

  /**
   * Tulip Chain
   */
  val TulipChain = new BrowserFamily("Tulip Chain")

  /**
   * TurnitinBot
   */
  val TurnitinBot = new BrowserFamily("TurnitinBot")

  /**
   * TutorGigBot
   */
  val TutorGigBot = new BrowserFamily("TutorGigBot")

  /**
   * TwengaBot
   */
  val TwengaBot = new BrowserFamily("TwengaBot")

  /**
   * Twiceler
   */
  val Twiceler = new BrowserFamily("Twiceler")

  /**
   * Twikle
   */
  val Twikle = new BrowserFamily("Twikle")

  /**
   * Typhoeus
   */
  val Typhoeus = new BrowserFamily("Typhoeus")

  /**
   * UASlinkChecker
   */
  val UASlinkChecker = new BrowserFamily("UASlinkChecker")

  /**
   * UC Browser
   */
  val UCBrowser = new BrowserFamily("UC Browser")

  /**
   * UltraBrowser
   */
  val UltraBrowser = new BrowserFamily("UltraBrowser ")

  /**
   * UnisterBot
   */
  val UnisterBot = new BrowserFamily("UnisterBot")

  /**
   * UnwindFetchor
   */
  val UnwindFetchor = new BrowserFamily("UnwindFetchor")

  /**
   * updated
   */
  val updated = new BrowserFamily("updated")

  /**
   * Updownerbot
   */
  val Updownerbot = new BrowserFamily("Updownerbot")

  /**
   * UptimeDog
   */
  val UptimeDog = new BrowserFamily("UptimeDog")

  /**
   * UptimeRobot
   */
  val UptimeRobot = new BrowserFamily("UptimeRobot")

  /**
   * urlfan-bot
   */
  val urlfanbot = new BrowserFamily("urlfan-bot")

  /**
   * Urlfilebot (Urlbot)
   */
  val UrlfilebotUrlbot = new BrowserFamily("Urlfilebot (Urlbot)")

  /**
   * urlgrabber
   */
  val urlgrabber = new BrowserFamily("urlgrabber")

  /**
   * Usejump
   */
  val Usejump = new BrowserFamily("Usejump")

  /**
   * uZard Web
   */
  val uZardWeb = new BrowserFamily("uZard Web")

  /**
   * Uzbl
   */
  val Uzbl = new BrowserFamily("Uzbl")

  /**
   * Vagabondo
   */
  val Vagabondo = new BrowserFamily("Vagabondo")

  /**
   * Validator.nu
   */
  val Validatornu = new BrowserFamily("Validator.nu")

  /**
   * VERASYS 2k
   */
  val VERASYS2k = new BrowserFamily("VERASYS 2k")

  /**
   * Vermut
   */
  val Vermut = new BrowserFamily("Vermut")

  /**
   * Vespa Crawler
   */
  val VespaCrawler = new BrowserFamily("Vespa Crawler")

  /**
   * VideoSurf_bot
   */
  val VideoSurfbot = new BrowserFamily("VideoSurf_bot")

  /**
   * virus_detector
   */
  val virusdetector = new BrowserFamily("virus_detector")

  /**
   * Visbot
   */
  val Visbot = new BrowserFamily("Visbot")

  /**
   * VLC media player
   */
  val VLCmediaplayer = new BrowserFamily("VLC media player")

  /**
   * VMBot
   */
  val VMBot = new BrowserFamily("VMBot")

  /**
   * void-bot
   */
  val voidbot = new BrowserFamily("void-bot")

  /**
   * VoilaBot
   */
  val VoilaBot = new BrowserFamily("VoilaBot")

  /**
   * Vonkeror
   */
  val Vonkeror = new BrowserFamily("Vonkeror")

  /**
   * VORTEX
   */
  val VORTEX = new BrowserFamily("VORTEX")

  /**
   * voyager
   */
  val voyager = new BrowserFamily("voyager")

  /**
   * Vuze
   */
  val Vuze = new BrowserFamily("Vuze")

  /**
   * VWBot
   */
  val VWBot = new BrowserFamily("VWBot")

  /**
   * W3C Checklink
   */
  val W3CChecklink = new BrowserFamily("W3C Checklink")

  /**
   * W3C CSS Validator
   */
  val W3CCSSValidator = new BrowserFamily("W3C CSS Validator")

  /**
   * W3C mobileOK Checker
   */
  val W3CmobileOKChecker = new BrowserFamily("W3C mobileOK Checker")

  /**
   * W3C Validator
   */
  val W3CValidator = new BrowserFamily("W3C Validator")

  /**
   * w3m
   */
  val w3m = new BrowserFamily("w3m")

  /**
   * WapTiger
   */
  val WapTiger = new BrowserFamily("WapTiger")

  /**
   * WASALive-Bot
   */
  val WASALiveBot = new BrowserFamily("WASALive-Bot")

  /**
   * WatchMouse
   */
  val WatchMouse = new BrowserFamily("WatchMouse")

  /**
   * WBSearchBot
   */
  val WBSearchBot = new BrowserFamily("WBSearchBot")

  /**
   * WDG CSSCheck
   */
  val WDGCSSCheck = new BrowserFamily("WDG CSSCheck")

  /**
   * WDG Page Valet
   */
  val WDGPageValet = new BrowserFamily("WDG Page Valet")

  /**
   * WDG Validator
   */
  val WDGValidator = new BrowserFamily("WDG Validator")

  /**
   * Web-sniffer
   */
  val Websniffer = new BrowserFamily("Web-sniffer")

  /**
   * WebAlta Crawler
   */
  val WebAltaCrawler = new BrowserFamily("WebAlta Crawler")

  /**
   * WebarooBot
   */
  val WebarooBot = new BrowserFamily("WebarooBot")

  /**
   * WebCollage
   */
  val WebCollage = new BrowserFamily("WebCollage")

  /**
   * WebCopier
   */
  val WebCopier = new BrowserFamily("WebCopier")

  /**
   * webfetch
   */
  val webfetch = new BrowserFamily("webfetch")

  /**
   * webfs
   */
  val webfs = new BrowserFamily("webfs")

  /**
   * Webian Shell
   */
  val WebianShell = new BrowserFamily("Webian Shell")

  /**
   * WebImages
   */
  val WebImages = new BrowserFamily("WebImages")

  /**
   * webinatorbot
   */
  val webinatorbot = new BrowserFamily("webinatorbot")

  /**
   * webmastercoffee
   */
  val webmastercoffee = new BrowserFamily("webmastercoffee")

  /**
   * WebNL
   */
  val WebNL = new BrowserFamily("WebNL")

  /**
   * WebRankSpider
   */
  val WebRankSpider = new BrowserFamily("WebRankSpider")

  /**
   * WebRender
   */
  val WebRender = new BrowserFamily("WebRender")

  /**
   * Webscope Crawler
   */
  val WebscopeCrawler = new BrowserFamily("Webscope Crawler")

  /**
   * WebStripper
   */
  val WebStripper = new BrowserFamily("WebStripper")

  /**
   * WebWatch/Robot_txtChecker
   */
  val WebWatchRobottxtChecker = new BrowserFamily("WebWatch/Robot_txtChecker")

  /**
   * WebZIP
   */
  val WebZIP = new BrowserFamily("WebZIP")

  /**
   * wectar
   */
  val wectar = new BrowserFamily("wectar")

  /**
   * Weltweitimnetz Browser
   */
  val WeltweitimnetzBrowser = new BrowserFamily("Weltweitimnetz Browser")

  /**
   * WeSEE:Search
   */
  val WeSEESearch = new BrowserFamily("WeSEE:Search")

  /**
   * Wget
   */
  val Wget = new BrowserFamily("Wget")

  /**
   * Whoismindbot
   */
  val Whoismindbot = new BrowserFamily("Whoismindbot")

  /**
   * WikioFeedBot
   */
  val WikioFeedBot = new BrowserFamily("WikioFeedBot")

  /**
   * wikiwix-bot
   */
  val wikiwixbot = new BrowserFamily("wikiwix-bot")

  /**
   * Willow Internet Crawler
   */
  val WillowInternetCrawler = new BrowserFamily("Willow Internet Crawler")

  /**
   * Winamp for Android
   */
  val WinampforAndroid = new BrowserFamily("Winamp for Android")

  /**
   * Windows Live Mail
   */
  val WindowsLiveMail = new BrowserFamily("Windows Live Mail")

  /**
   * Windows Media Player
   */
  val WindowsMediaPlayer = new BrowserFamily("Windows Media Player")

  /**
   * WinHTTP
   */
  val WinHTTP = new BrowserFamily("WinHTTP")

  /**
   * WinkBot
   */
  val WinkBot = new BrowserFamily("WinkBot")

  /**
   * WinPodder
   */
  val WinPodder = new BrowserFamily("WinPodder")

  /**
   * WinWap
   */
  val WinWap = new BrowserFamily("WinWap")

  /**
   * WinWebBot
   */
  val WinWebBot = new BrowserFamily("WinWebBot")

  /**
   * WIRE
   */
  val WIRE = new BrowserFamily("WIRE")

  /**
   * wKiosk
   */
  val wKiosk = new BrowserFamily("wKiosk")

  /**
   * WMCAI_robot
   */
  val WMCAIrobot = new BrowserFamily("WMCAI_robot")

  /**
   * Woko
   */
  val Woko = new BrowserFamily("Woko")

  /**
   * WordPress pingback
   */
  val WordPresspingback = new BrowserFamily("WordPress pingback")

  /**
   * woriobot
   */
  val woriobot = new BrowserFamily("woriobot")

  /**
   * WorldWideWeb
   */
  val WorldWideWeb = new BrowserFamily("WorldWideWeb")

  /**
   * wOSBrowser
   */
  val wOSBrowser = new BrowserFamily("wOSBrowser")

  /**
   * Wotbox
   */
  val Wotbox = new BrowserFamily("Wotbox")

  /**
   * wsAnalyzer
   */
  val wsAnalyzer = new BrowserFamily("wsAnalyzer")

  /**
   * www.fi crawler
   */
  val wwwficrawler = new BrowserFamily("www.fi crawler")

  /**
   * WWW::Mechanize
   */
  val WWWMechanize = new BrowserFamily("WWW::Mechanize")

  /**
   * wwwster
   */
  val wwwster = new BrowserFamily("wwwster")

  /**
   * Wyzo
   */
  val Wyzo = new BrowserFamily("Wyzo")

  /**
   * X-Smiles
   */
  val XSmiles = new BrowserFamily("X-Smiles")

  /**
   * Xaldon WebSpider
   */
  val XaldonWebSpider = new BrowserFamily("Xaldon WebSpider")

  /**
   * XBMC
   */
  val XBMC = new BrowserFamily("XBMC")

  /**
   * Xenu
   */
  val Xenu = new BrowserFamily("Xenu")

  /**
   * xine
   */
  val xine = new BrowserFamily("xine")

  /**
   * XmarksFetch
   */
  val XmarksFetch = new BrowserFamily("XmarksFetch")

  /**
   * XML-RPC for PHP
   */
  val XMLRPCforPHP = new BrowserFamily("XML-RPC for PHP")

  /**
   * XML-RPC for Ruby
   */
  val XMLRPCforRuby = new BrowserFamily("XML-RPC for Ruby")

  /**
   * XML Sitemaps Generator
   */
  val XMLSitemapsGenerator = new BrowserFamily("XML Sitemaps Generator")

  /**
   * XMPlay
   */
  val XMPlay = new BrowserFamily("XMPlay")

  /**
   * Yaanb
   */
  val Yaanb = new BrowserFamily("Yaanb")

  /**
   * yacybot
   */
  val yacybot = new BrowserFamily("yacybot")

  /**
   * Yahoo!
   */
  val Yahoo = new BrowserFamily("Yahoo!")

  /**
   * Yahoo! JAPAN
   */
  val YahooJAPAN = new BrowserFamily("Yahoo! JAPAN")

  /**
   * YahooFeedSeeker
   */
  val YahooFeedSeeker = new BrowserFamily("YahooFeedSeeker")

  /**
   * Yandex.Browser
   */
  val YandexBrowser = new BrowserFamily("Yandex.Browser")

  /**
   * YandexBot
   */
  val YandexBot = new BrowserFamily("YandexBot")

  /**
   * Yanga
   */
  val Yanga = new BrowserFamily("Yanga")

  /**
   * YeahReader
   */
  val YeahReader = new BrowserFamily("YeahReader")

  /**
   * YioopBot
   */
  val YioopBot = new BrowserFamily("YioopBot")

  /**
   * YodaoBot
   */
  val YodaoBot = new BrowserFamily("YodaoBot")

  /**
   * Yoono Bot
   */
  val YoonoBot = new BrowserFamily("Yoono Bot")

  /**
   * YoudaoBot
   */
  val YoudaoBot = new BrowserFamily("YoudaoBot")

  /**
   * YowedoBot
   */
  val YowedoBot = new BrowserFamily("YowedoBot")

  /**
   * YRSpider
   */
  val YRSpider = new BrowserFamily("YRSpider")

  /**
   * ZACATEK_CZ
   */
  val ZACATEKCZ = new BrowserFamily("ZACATEK_CZ")

  /**
   * zBrowser
   */
  val zBrowser = new BrowserFamily("zBrowser")

  /**
   * Zend_Http_Client
   */
  val ZendHttpClient = new BrowserFamily("Zend_Http_Client")

  /**
   * Zeusbot
   */
  val Zeusbot = new BrowserFamily("Zeusbot")

  /**
   * ZipZap
   */
  val ZipZap = new BrowserFamily("ZipZap")

  /**
   * ZookaBot
   */
  val ZookaBot = new BrowserFamily("ZookaBot")

  /**
   * ZoomSpider (ZSEBOT)
   */
  val ZoomSpiderZSEBOT = new BrowserFamily("ZoomSpider (ZSEBOT)")

  /**
   * ZyBorg
   */
  val Zyborg = new BrowserFamily("ZyBorg")

  def byFriendlyName(name: String) = values.find(b => b.friendlyName == name).getOrElse(Unknown)
}