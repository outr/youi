package org.hyperscala.html

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.html.tag._

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class HTMLTagType[T <: HTMLTag] protected(htmlName: String, clazz: Class[T]) extends EnumEntry {
  def create() = clazz.newInstance()
}

object HTMLTagType extends Enumerated[HTMLTagType[_ <: HTMLTag]] {
    val html = new HTMLTagType("html", classOf[HTML])
    val head = new HTMLTagType("head", classOf[Head])
    val title = new HTMLTagType("title", classOf[Title])
    val body = new HTMLTagType("body", classOf[Body])
    val a = new HTMLTagType("a", classOf[A])
    val abbr = new HTMLTagType("abbr", classOf[Abbr])
    val address = new HTMLTagType("address", classOf[Address])
    val area = new HTMLTagType("area", classOf[Area])
    val article = new HTMLTagType("article", classOf[Article])
    val aside = new HTMLTagType("aside", classOf[Aside])
    val audio = new HTMLTagType("audio", classOf[Audio])
    val b = new HTMLTagType("b", classOf[B])
    val base = new HTMLTagType("base", classOf[Base])
    val bdi = new HTMLTagType("bdi", classOf[Bdi])
    val bdo = new HTMLTagType("bdo", classOf[Bdo])
    val blockquote = new HTMLTagType("blockquote", classOf[BlockQuote])
    val br = new HTMLTagType("br", classOf[Br])
    val button = new HTMLTagType("button", classOf[Button])
    val canvas = new HTMLTagType("canvas", classOf[Canvas])
    val caption = new HTMLTagType("caption", classOf[Caption])
    val cite = new HTMLTagType("cite", classOf[Cite])
    val code = new HTMLTagType("code", classOf[Code])
    val col = new HTMLTagType("col", classOf[Col])
    val colgroup = new HTMLTagType("colgroup", classOf[ColGroup])
    val command = new HTMLTagType("command", classOf[Command])
    val datalist = new HTMLTagType("datalist", classOf[DataList])
    val dd = new HTMLTagType("dd", classOf[Dd])
    val del = new HTMLTagType("del", classOf[Del])
    val details = new HTMLTagType("details", classOf[Details])
    val dfn = new HTMLTagType("dfn", classOf[Dfn])
    val div = new HTMLTagType("div", classOf[Div])
    val dl = new HTMLTagType("dl", classOf[Dl])
    val dt = new HTMLTagType("dt", classOf[Dt])
    val em = new HTMLTagType("em", classOf[Em])
    val embed = new HTMLTagType("embed", classOf[Embed])
    val fieldset = new HTMLTagType("fieldset", classOf[FieldSet])
    val figcaption = new HTMLTagType("figcaption", classOf[FigCaption])
    val figure = new HTMLTagType("figure", classOf[Figure])
    val footer = new HTMLTagType("footer", classOf[Footer])
    val form = new HTMLTagType("form", classOf[Form])
    val h1 = new HTMLTagType("h1", classOf[H1])
    val h2 = new HTMLTagType("h2", classOf[H2])
    val h3 = new HTMLTagType("h3", classOf[H3])
    val h4 = new HTMLTagType("h4", classOf[H4])
    val h5 = new HTMLTagType("h5", classOf[H5])
    val h6 = new HTMLTagType("h6", classOf[H6])
    val header = new HTMLTagType("header", classOf[Header])
    val hgroup = new HTMLTagType("hgroup", classOf[HGroup])
    val hr = new HTMLTagType("hr", classOf[Hr])
    val i = new HTMLTagType("i", classOf[I])
    val iframe = new HTMLTagType("iframe", classOf[IFrame])
    val img = new HTMLTagType("img", classOf[Img])
    val input = new HTMLTagType("input", classOf[Input])
    val ins = new HTMLTagType("ins", classOf[Ins])
    val kbd = new HTMLTagType("kbd", classOf[Kbd])
    val keygen = new HTMLTagType("keygen", classOf[KeyGen])
    val label = new HTMLTagType("label", classOf[Label])
    val legend = new HTMLTagType("legend", classOf[Legend])
    val li = new HTMLTagType("li", classOf[Li])
    val link = new HTMLTagType("link", classOf[Link])
    val main = new HTMLTagType("main", classOf[tag.Main])
    val map = new HTMLTagType("map", classOf[tag.Map])
    val mark = new HTMLTagType("mark", classOf[Mark])
    val menu = new HTMLTagType("menu", classOf[Menu])
    val menuItem = new HTMLTagType("menuitem", classOf[MenuItem])
    val meta = new HTMLTagType("meta", classOf[Meta])
    val meter = new HTMLTagType("meter", classOf[Meter])
    val nav = new HTMLTagType("nav", classOf[Nav])
    val noscript = new HTMLTagType("noscript", classOf[NoScript])
    val obj = new HTMLTagType("object", classOf[tag.Object])
    val ol = new HTMLTagType("ol", classOf[Ol])
    val optgroup = new HTMLTagType("optgroup", classOf[OptGroup])
    val option = new HTMLTagType("option", classOf[tag.Option])
    val output = new HTMLTagType("output", classOf[Output])
    val p = new HTMLTagType("p", classOf[P])
    val param = new HTMLTagType("param", classOf[Param])
    val pre = new HTMLTagType("pre", classOf[Pre])
    val progress = new HTMLTagType("progress", classOf[Progress])
    val q = new HTMLTagType("q", classOf[Q])
    val rp = new HTMLTagType("rp", classOf[Rp])
    val rt = new HTMLTagType("rt", classOf[Rt])
    val ruby = new HTMLTagType("ruby", classOf[Ruby])
    val s = new HTMLTagType("s", classOf[S])
    val samp = new HTMLTagType("samp", classOf[Samp])
    val script = new HTMLTagType("script", classOf[Script])
    val section = new HTMLTagType("section", classOf[Section])
    val select = new HTMLTagType("select", classOf[Select])
    val small = new HTMLTagType("small", classOf[Small])
    val source = new HTMLTagType("source", classOf[Source])
    val span = new HTMLTagType("span", classOf[Span])
    val strong = new HTMLTagType("strong", classOf[Strong])
    val style = new HTMLTagType("style", classOf[Style])
    val sub = new HTMLTagType("sub", classOf[Sub])
    val sup = new HTMLTagType("sup", classOf[Sup])
    val table = new HTMLTagType("table", classOf[Table])
    val tbody = new HTMLTagType("tbody", classOf[TBody])
    val td = new HTMLTagType("td", classOf[Td])
    val textarea = new HTMLTagType("textarea", classOf[TextArea])
    val tfoot = new HTMLTagType("tfoot", classOf[TFoot])
    val th = new HTMLTagType("th", classOf[Th])
    val thead = new HTMLTagType("thead", classOf[THead])
    val time = new HTMLTagType("time", classOf[Time])
    val tr = new HTMLTagType("tr", classOf[Tr])
    val track = new HTMLTagType("track", classOf[Track])
    val u = new HTMLTagType("u", classOf[U])
    val ul = new HTMLTagType("ul", classOf[Ul])
    val variable = new HTMLTagType("var", classOf[Var])
    val video = new HTMLTagType("video", classOf[Video])
    val wbr = new HTMLTagType("wbr", classOf[Wbr])

  def byClass[T <: HTMLTag](clazz: Class[T]) = values.find(htt => htt.clazz == clazz).asInstanceOf[scala.Option[HTMLTagType[T]]]
}