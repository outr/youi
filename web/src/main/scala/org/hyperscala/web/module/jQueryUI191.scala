package org.hyperscala.web.module

import org.hyperscala.web.site.Webpage
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI191 extends Module {
  def name = "jquery-ui"

  def version = "1.9.1"

  def load(page: Webpage) = {
    page.require(jQuery182)

    page.website.register("jquery_ui/jquery-ui-1.9.1.custom.min.js")
    page.website.register("jquery_ui/jquery-ui-1.9.1.custom.min.css")
    page.website.register("jquery_ui/images/ui-bg_flat_0_aaaaaa_40x100.png")
    page.website.register("jquery_ui/images/ui-bg_flat_55_fbec88_40x100.png")
    page.website.register("jquery_ui/images/ui-bg_glass_75_d0e5f5_1x400.png")
    page.website.register("jquery_ui/images/ui-bg_glass_85_dfeffc_1x400.png")
    page.website.register("jquery_ui/images/ui-bg_glass_95_fef1ec_1x400.png")
    page.website.register("jquery_ui/images/ui-bg_gloss-wave_55_5c9ccc_500x100.png")
    page.website.register("jquery_ui/images/ui-bg_inset-hard_100_f5f8f9_1x100.png")
    page.website.register("jquery_ui/images/ui-bg_inset-hard_100_fcfdfd_1x100.png")
    page.website.register("jquery_ui/images/ui-icons_2e83ff_256x240.png")
    page.website.register("jquery_ui/images/ui-icons_6da8d5_256x240.png")
    page.website.register("jquery_ui/images/ui-icons_217bc0_256x240.png")
    page.website.register("jquery_ui/images/ui-icons_469bdd_256x240.png")
    page.website.register("jquery_ui/images/ui-icons_cd0a0a_256x240.png")
    page.website.register("jquery_ui/images/ui-icons_d8e7f3_256x240.png")
    page.website.register("jquery_ui/images/ui-icons_f9bd01_256x240.png")
    page.head.contents += new tag.Link(href = "/jquery_ui/jquery-ui-1.9.1.custom.min.css", rel = "stylesheet")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery_ui/jquery-ui-1.9.1.custom.min.js")
  }
}
