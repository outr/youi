package io.youi.font

import io.youi.net.URL

object GoogleFont {
  private val base: String = "http://fonts.gstatic.com/s"

  object `ABeeZee` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/abeezee/v10/mE5BOuZKGln_Ex0uYKpIaw.ttf")
    def `italic`: URL = URL(s"$base/abeezee/v10/kpplLynmYgP0YtlJA3atRw.ttf")
  }
  object `Abel` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/abel/v7/RpUKfqNxoyNe_ka23bzQ2A.ttf")
  }
  object `Abhaya Libre` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "sinhala", "latin")
    def `regular`: URL = URL(s"$base/abhayalibre/v2/zTLc5Jxv6yvb1nHyqBasVy3USBnSvpkopQaUR-2r7iU.ttf")
    def `500`: URL = URL(s"$base/abhayalibre/v2/wBjdF6T34NCo7wQYXgzrc5MQuUSAwdHsY8ov_6tk1oA.ttf")
    def `600`: URL = URL(s"$base/abhayalibre/v2/wBjdF6T34NCo7wQYXgzrc2v8CylhIUtwUiYO7Z2wXbE.ttf")
    def `700`: URL = URL(s"$base/abhayalibre/v2/wBjdF6T34NCo7wQYXgzrc0D2ttfZwueP-QU272T9-k4.ttf")
    def `800`: URL = URL(s"$base/abhayalibre/v2/wBjdF6T34NCo7wQYXgzrc_qsay_1ZmRGmC8pVRdIfAg.ttf")
  }
  object `Abril Fatface` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/abrilfatface/v8/X1g_KwGeBV3ajZIXQ9VnDojjx0o0jr6fNXxPgYh_a8Q.ttf")
  }
  object `Aclonica` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/aclonica/v7/M6pHZMPwK3DiBSlo3jwAKQ.ttf")
  }
  object `Acme` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/acme/v6/-J6XNtAHPZBEbsifCdBt-g.ttf")
  }
  object `Actor` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/actor/v6/ugMf40CrRK6Jf6Yz_xNSmQ.ttf")
  }
  object `Adamina` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/adamina/v9/RUQfOodOMiVVYqFZcSlT9w.ttf")
  }
  object `Advent Pro` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "greek", "latin")
    def `100`: URL = URL(s"$base/adventpro/v6/87-JOpSUecTG50PBYK4ysi3USBnSvpkopQaUR-2r7iU.ttf")
    def `200`: URL = URL(s"$base/adventpro/v6/URTSSjIp0Wr-GrjxFdFWnGeudeTO44zf-ht3k-KNzwg.ttf")
    def `300`: URL = URL(s"$base/adventpro/v6/sJaBfJYSFgoB80OL1_66m0eOrDcLawS7-ssYqLr2Xp4.ttf")
    def `regular`: URL = URL(s"$base/adventpro/v6/1NxMBeKVcNNH2H46AUR3wfesZW2xOQ-xsNqO47m55DA.ttf")
    def `500`: URL = URL(s"$base/adventpro/v6/7kBth2-rT8tP40RmMMXMLJp-63r6doWhTEbsfBIRJ7A.ttf")
    def `600`: URL = URL(s"$base/adventpro/v6/3Jo-2maCzv2QLzQBzaKHV_pTEJqju4Hz1txDWij77d4.ttf")
    def `700`: URL = URL(s"$base/adventpro/v6/M4I6QiICt-ey_wZTpR2gKwJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Aguafina Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/aguafinascript/v5/65g7cgMtMGnNlNyq_Z6CvMxLhO8OSNnfAp53LK1_iRs.ttf")
  }
  object `Akronim` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/akronim/v6/qA0L2CSArk3tuOWE1AR1DA.ttf")
  }
  object `Aladin` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/aladin/v5/PyuJ5cVHkduO0j5fAMKvAA.ttf")
  }
  object `Aldrich` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/aldrich/v7/kMMW1S56gFx7RP_mW1g-Eg.ttf")
  }
  object `Alef` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("hebrew", "latin")
    def `regular`: URL = URL(s"$base/alef/v8/ENvZ_P0HBDQxNZYCQO0lUA.ttf")
    def `700`: URL = URL(s"$base/alef/v8/VDgZJhEwudtOzOFQpZ8MEA.ttf")
  }
  object `Alegreya` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/alegreya/v8/62J3atXd6bvMU4qO_ca-eA.ttf")
    def `italic`: URL = URL(s"$base/alegreya/v8/cbshnQGxwmlHBjUil7DaIfesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/alegreya/v8/5oZtdI5-wQwgAFrd9erCsaCWcynf_cDxXwCLxiixG1c.ttf")
    def `700italic`: URL = URL(s"$base/alegreya/v8/IWi8e5bpnqhMRsZKTcTUWgJKKGfqHaYFsRG-T3ceEVo.ttf")
    def `900`: URL = URL(s"$base/alegreya/v8/oQeMxX-vxGImzDgX6nxA7KCWcynf_cDxXwCLxiixG1c.ttf")
    def `900italic`: URL = URL(s"$base/alegreya/v8/-L71QLH_XqgYWaI1GbOVhp0EAVxt0G0biEntp43Qt6E.ttf")
  }
  object `Alegreya SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/alegreyasc/v7/3ozeFnTbygMK6PfHh8B-iqCWcynf_cDxXwCLxiixG1c.ttf")
    def `italic`: URL = URL(s"$base/alegreyasc/v7/GOqmv3FLsJ2r6ZALMZVBmkeOrDcLawS7-ssYqLr2Xp4.ttf")
    def `700`: URL = URL(s"$base/alegreyasc/v7/M9OIREoxDkvynwTpBAYUq3e1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `700italic`: URL = URL(s"$base/alegreyasc/v7/5PCoU7IUfCicpKBJtBmP6c_zJjSACmk0BRPxQqhnNLU.ttf")
    def `900`: URL = URL(s"$base/alegreyasc/v7/M9OIREoxDkvynwTpBAYUqyenaqEuufTBk9XMKnKmgDA.ttf")
    def `900italic`: URL = URL(s"$base/alegreyasc/v7/5PCoU7IUfCicpKBJtBmP6U_yTOUGsoC54csJe1b-IRw.ttf")
  }
  object `Alegreya Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `100`: URL = URL(s"$base/alegreyasans/v5/TKyx_-JJ6MdpQruNk-t-PJFGFO4uyVFMfB6LZsii7kI.ttf")
    def `100italic`: URL = URL(s"$base/alegreyasans/v5/gRkSP2lBpqoMTVxg7DmVn2cDnjsrnI9_xJ-5gnBaHsE.ttf")
    def `300`: URL = URL(s"$base/alegreyasans/v5/11EDm-lum6tskJMBbdy9acB1LjARzAvdqa1uQC32v70.ttf")
    def `300italic`: URL = URL(s"$base/alegreyasans/v5/WfiXipsmjqRqsDBQ1bA9CnfqlVoxTUFFx1C8tBqmbcg.ttf")
    def `regular`: URL = URL(s"$base/alegreyasans/v5/KYNzioYhDai7mTMnx_gDgn8f0n03UdmQgF_CLvNR2vg.ttf")
    def `italic`: URL = URL(s"$base/alegreyasans/v5/TKyx_-JJ6MdpQruNk-t-PD4G9C9ttb0Oz5Cvf0qOitE.ttf")
    def `500`: URL = URL(s"$base/alegreyasans/v5/11EDm-lum6tskJMBbdy9aQqQmZ7VjhwksfpNVG0pqGc.ttf")
    def `500italic`: URL = URL(s"$base/alegreyasans/v5/WfiXipsmjqRqsDBQ1bA9Cs7DCVO6wo6i5LKIyZDzK40.ttf")
    def `700`: URL = URL(s"$base/alegreyasans/v5/11EDm-lum6tskJMBbdy9aVCbmAUID8LN-q3pJpOk3Ys.ttf")
    def `700italic`: URL = URL(s"$base/alegreyasans/v5/WfiXipsmjqRqsDBQ1bA9CpF66r9C4AnxxlBlGd7xY4g.ttf")
    def `800`: URL = URL(s"$base/alegreyasans/v5/11EDm-lum6tskJMBbdy9acxnD5BewVtRRHHljCwR2bM.ttf")
    def `800italic`: URL = URL(s"$base/alegreyasans/v5/WfiXipsmjqRqsDBQ1bA9CicOAJ_9MkLPbDmrtXDPbIU.ttf")
    def `900`: URL = URL(s"$base/alegreyasans/v5/11EDm-lum6tskJMBbdy9aW42xlVP-j5dagE7-AU2zwg.ttf")
    def `900italic`: URL = URL(s"$base/alegreyasans/v5/WfiXipsmjqRqsDBQ1bA9ChRaDUI9aE8-k7PrIG2iiuo.ttf")
  }
  object `Alegreya Sans SC` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `100`: URL = URL(s"$base/alegreyasanssc/v5/trwFkDJLOJf6hqM93944kVnzStfdnFU-MXbO84aBs_M.ttf")
    def `100italic`: URL = URL(s"$base/alegreyasanssc/v5/qG3gA9iy5RpXMH4crZboqqakMVR0XlJhO7VdJ8yYvA4.ttf")
    def `300`: URL = URL(s"$base/alegreyasanssc/v5/AjAmkoP1y0Vaad0UPPR46-1IqtfxJspFjzJp0SaQRcI.ttf")
    def `300italic`: URL = URL(s"$base/alegreyasanssc/v5/0VweK-TO3aQgazdxg8fs0CnTKaH808trtzttbEg4yVA.ttf")
    def `regular`: URL = URL(s"$base/alegreyasanssc/v5/6kgb6ZvOagoVIRZyl8XV-EklWX-XdLVn1WTiuGuvKIU.ttf")
    def `italic`: URL = URL(s"$base/alegreyasanssc/v5/trwFkDJLOJf6hqM93944kTfqo69HNOlCNZvbwAmUtiA.ttf")
    def `500`: URL = URL(s"$base/alegreyasanssc/v5/AjAmkoP1y0Vaad0UPPR46_hHTluI57wqxl55RvSYo3s.ttf")
    def `500italic`: URL = URL(s"$base/alegreyasanssc/v5/0VweK-TO3aQgazdxg8fs0NqVvxKdFVwqwzilqfVd39U.ttf")
    def `700`: URL = URL(s"$base/alegreyasanssc/v5/AjAmkoP1y0Vaad0UPPR4600aId5t1FC-xZ8nmpa_XLk.ttf")
    def `700italic`: URL = URL(s"$base/alegreyasanssc/v5/0VweK-TO3aQgazdxg8fs0IBYn3VD6xMEnodOh8pnFw4.ttf")
    def `800`: URL = URL(s"$base/alegreyasanssc/v5/AjAmkoP1y0Vaad0UPPR46wQgSHD3Lo1Mif2Wkk5swWA.ttf")
    def `800italic`: URL = URL(s"$base/alegreyasanssc/v5/0VweK-TO3aQgazdxg8fs0HStmCm6Rs90XeztCALm0H8.ttf")
    def `900`: URL = URL(s"$base/alegreyasanssc/v5/AjAmkoP1y0Vaad0UPPR461Rf9EWUSEX_PR1d_gLKfpM.ttf")
    def `900italic`: URL = URL(s"$base/alegreyasanssc/v5/0VweK-TO3aQgazdxg8fs0IvtwEfTCJoOJugANj-jWDI.ttf")
  }
  object `Alex Brush` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/alexbrush/v7/ooh3KJFbKJSUoIRWfiu8o_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Alfa Slab One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/alfaslabone/v6/Qx6FPcitRwTC_k88tLPc-Yjjx0o0jr6fNXxPgYh_a8Q.ttf")
  }
  object `Alice` {
    val category: String = "serif"
    val subsets: Set[String] = Set("cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/alice/v8/wZTAfivekBqIg-rk63nFvQ.ttf")
  }
  object `Alike` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/alike/v9/Ho8YpRKNk_202fwDiGNIyw.ttf")
  }
  object `Alike Angular` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/alikeangular/v7/OpeCu4xxI3qO1C7CZcJtPT3XH2uEnVI__ynTBvNyki8.ttf")
  }
  object `Allan` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/allan/v8/T3lemhgZmLQkQI2Qc2bQHA.ttf")
    def `700`: URL = URL(s"$base/allan/v8/zSxQiwo7wgnr7KkMXhSiag.ttf")
  }
  object `Allerta` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/allerta/v7/s9FOEuiJFTNbMe06ifzV8g.ttf")
  }
  object `Allerta Stencil` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/allertastencil/v7/CdSZfRtHbQrBohqmzSdDYFf2eT4jUldwg_9fgfY_tHc.ttf")
  }
  object `Allura` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/allura/v5/4hcqgZanyuJ2gMYWffIR6A.ttf")
  }
  object `Almendra` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/almendra/v9/PDpbB-ZF7deXAAEYPkQOeg.ttf")
    def `italic`: URL = URL(s"$base/almendra/v9/CNWLyiDucqVKVgr4EMidi_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/almendra/v9/ZpLdQMj7Q2AFio4nNO6A76CWcynf_cDxXwCLxiixG1c.ttf")
    def `700italic`: URL = URL(s"$base/almendra/v9/-tXHKMcnn6FqrhJV3l1e3QJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Almendra Display` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/almendradisplay/v7/2Zuu97WJ_ez-87yz5Ai8fF6uyC_qD11hrFQ6EGgTJWI.ttf")
  }
  object `Almendra SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/almendrasc/v7/IuiLd8Fm9I6raSalxMoWeaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Amarante` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/amarante/v4/2dQHjIBWSpydit5zkJZnOw.ttf")
  }
  object `Amaranth` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/amaranth/v7/7VcBog22JBHsHXHdnnycTA.ttf")
    def `italic`: URL = URL(s"$base/amaranth/v7/UrJlRY9LcVERJSvggsdBqPesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/amaranth/v7/j5OFHqadfxyLnQRxFeox6qCWcynf_cDxXwCLxiixG1c.ttf")
    def `700italic`: URL = URL(s"$base/amaranth/v7/BHyuYFj9nqLFNvOvGh0xTwJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Amatic SC` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "hebrew", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/amaticsc/v9/MldbRWLFytvqxU1y81xSVg.ttf")
    def `700`: URL = URL(s"$base/amaticsc/v9/IDnkRTPGcrSVo50UyYNK7y3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Amatica SC` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "hebrew", "latin")
    def `regular`: URL = URL(s"$base/amaticasc/v1/f9SWSy9DLsJV2etvm5rwGPesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/amaticasc/v1/nFmSxyAMfCP_5xGDJ4j5TgJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Amethysta` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/amethysta/v5/1jEo9tOFIJDolAUpBnWbnA.ttf")
  }
  object `Amiko` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/amiko/v1/A7bjc3cOLJtGgpPGnxyHsw.ttf")
    def `600`: URL = URL(s"$base/amiko/v1/BaZst4RZ4sDyD3mH-BfVaA.ttf")
    def `700`: URL = URL(s"$base/amiko/v1/6syx43mQ07VvOmpFc0G9Lg.ttf")
  }
  object `Amiri` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "arabic")
    def `regular`: URL = URL(s"$base/amiri/v8/ATARrPmSew75SlpOw2YABQ.ttf")
    def `italic`: URL = URL(s"$base/amiri/v8/3t1yTQlLUXBw8htrqlXBrw.ttf")
    def `700`: URL = URL(s"$base/amiri/v8/WQsR_moz-FNqVwGYgptqiA.ttf")
    def `700italic`: URL = URL(s"$base/amiri/v8/uF8aNEyD0bxMeTBg9bFDSPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Amita` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/amita/v2/RhdhGBXSJqkHo6g7miTEcQ.ttf")
    def `700`: URL = URL(s"$base/amita/v2/cIYA2Lzp7l2pcGsqpUidBg.ttf")
  }
  object `Anaheim` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/anaheim/v4/t-z8aXHMpgI2gjN_rIflKA.ttf")
  }
  object `Andada` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/andada/v8/rSFaDqNNQBRw3y19MB5Y4w.ttf")
  }
  object `Andika` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/andika/v8/oe-ag1G0lcqZ3IXfeEgaGg.ttf")
  }
  object `Angkor` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/angkor/v9/DLpLgIS-8F10ecwKqCm95Q.ttf")
  }
  object `Annie Use Your Telescope` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/annieuseyourtelescope/v7/2cuiO5VmaR09C8SLGEQjGqbp7mtG8sPlcZvOaO8HBak.ttf")
  }
  object `Anonymous Pro` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "greek", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/anonymouspro/v10/Zhfjj_gat3waL4JSju74E-V_5zh5b-_HiooIRUBwn1A.ttf")
    def `italic`: URL = URL(s"$base/anonymouspro/v10/q0u6LFHwttnT_69euiDbWKwIsuKDCXG0NQm7BvAgx-c.ttf")
    def `700`: URL = URL(s"$base/anonymouspro/v10/WDf5lZYgdmmKhO8E1AQud--Cz_5MeePnXDAcLNWyBME.ttf")
    def `700italic`: URL = URL(s"$base/anonymouspro/v10/_fVr_XGln-cetWSUc-JpfA1LL9bfs7wyIp6F8OC9RxA.ttf")
  }
  object `Antic` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/antic/v8/hEa8XCNM7tXGzD0Uk0AipA.ttf")
  }
  object `Antic Didone` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/anticdidone/v5/r3nJcTDuOluOL6LGDV1vRy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Antic Slab` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/anticslab/v5/PSbJCTKkAS7skPdkd7AKEvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Anton` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/anton/v8/XIbCenm-W0IRHWYIh7CGUQ.ttf")
  }
  object `Arapey` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/arapey/v5/dqu823lrSYn8T2gApTdslA.ttf")
    def `italic`: URL = URL(s"$base/arapey/v5/pY-Xi5JNBpaWxy2tZhEm5A.ttf")
  }
  object `Arbutus` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/arbutus/v6/Go_hurxoUsn5MnqNVQgodQ.ttf")
  }
  object `Arbutus Slab` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/arbutusslab/v5/6k3Yp6iS9l4jRIpynA8qMy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Architects Daughter` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/architectsdaughter/v7/RXTgOOQ9AAtaVOHxx0IUBMCy0EhZjHzu-y0e6uLf4Fg.ttf")
  }
  object `Archivo` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/archivo/v2/r-UxY2mA_5pDuZN717veMA.ttf")
    def `italic`: URL = URL(s"$base/archivo/v2/xM6Bws4B8M6CBFj_NjFDmQ.ttf")
    def `500`: URL = URL(s"$base/archivo/v2/kolpDHEnC87zFuFfslSCevesZW2xOQ-xsNqO47m55DA.ttf")
    def `500italic`: URL = URL(s"$base/archivo/v2/MKuleTj-xvH_kzDLSfxAny3USBnSvpkopQaUR-2r7iU.ttf")
    def `600`: URL = URL(s"$base/archivo/v2/ujChrOQvaQhWGqGyAyvouPesZW2xOQ-xsNqO47m55DA.ttf")
    def `600italic`: URL = URL(s"$base/archivo/v2/yabYJWzTLFXwCTAuo02FTC3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/archivo/v2/pOE88CC9eYkEsVEVFu184_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/archivo/v2/KPG24G28nybJri09faZ5fy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Archivo Black` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/archivoblack/v6/WoAoVT7K3k7hHfxKbvB6B51XQG8isOYYJhPIYAyrESQ.ttf")
  }
  object `Archivo Narrow` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/archivonarrow/v7/DsLzC9scoPnrGiwYYMQXppTvAuddT2xDMbdz0mdLyZY.ttf")
    def `italic`: URL = URL(s"$base/archivonarrow/v7/vqsrtPCpTU3tJlKfuXP5zUpmlyBQEFfdE6dERLXdQGQ.ttf")
    def `500`: URL = URL(s"$base/archivonarrow/v7/M__Wu4PAmHf4YZvQM8tWsFZXnRfcj2QuLtpR7YorIko.ttf")
    def `500italic`: URL = URL(s"$base/archivonarrow/v7/wG6O733y5zHl4EKCOh8rSQPEI7VifuA7dF_atQng58I.ttf")
    def `600`: URL = URL(s"$base/archivonarrow/v7/M__Wu4PAmHf4YZvQM8tWsAYHMmBTXW-z0TFb_R_tMpQ.ttf")
    def `600italic`: URL = URL(s"$base/archivonarrow/v7/wG6O733y5zHl4EKCOh8rSQFfhWXBmyfiPDGj4ZvwGNU.ttf")
    def `700`: URL = URL(s"$base/archivonarrow/v7/M__Wu4PAmHf4YZvQM8tWsMLtdzs3iyjn_YuT226ZsLU.ttf")
    def `700italic`: URL = URL(s"$base/archivonarrow/v7/wG6O733y5zHl4EKCOh8rSTg5KB8MNJ4uPAETq9naQO8.ttf")
  }
  object `Aref Ruqaa` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin", "arabic")
    def `regular`: URL = URL(s"$base/arefruqaa/v3/kbqI055uLQz2hkccTTrYPfesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/arefruqaa/v3/RT-Q5DVI9arM6ZKux-UmTAJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Arima Madurai` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "tamil")
    def `100`: URL = URL(s"$base/arimamadurai/v2/Q0tjl46beRRcUe3RlWWNrdyXLlNBCUjoM1yKFfVCFUI.ttf")
    def `200`: URL = URL(s"$base/arimamadurai/v2/EsCGNPwBfkMk17-w_DTJ4rArwWuxcSSKq67BdR6k5Rg.ttf")
    def `300`: URL = URL(s"$base/arimamadurai/v2/EsCGNPwBfkMk17-w_DTJ4joJ52uD-1fmXmi8u0n_zsc.ttf")
    def `regular`: URL = URL(s"$base/arimamadurai/v2/8fNfThKRw_pr7MwgNdcHiW_MnNA9OgK8I1F23mNWOpE.ttf")
    def `500`: URL = URL(s"$base/arimamadurai/v2/EsCGNPwBfkMk17-w_DTJ4v_2zpxNHQ3utWt_82o9dAo.ttf")
    def `700`: URL = URL(s"$base/arimamadurai/v2/EsCGNPwBfkMk17-w_DTJ4qiiXuG_rGcOxkuidirlnJE.ttf")
    def `800`: URL = URL(s"$base/arimamadurai/v2/EsCGNPwBfkMk17-w_DTJ4khKLu0CevfTHM1eXjGnvQo.ttf")
    def `900`: URL = URL(s"$base/arimamadurai/v2/EsCGNPwBfkMk17-w_DTJ4kZ0oshA7r_PlGegwiHddT8.ttf")
  }
  object `Arimo` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "hebrew", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/arimo/v10/Gpeo80g-5ji2CcyXWnzh7g.ttf")
    def `italic`: URL = URL(s"$base/arimo/v10/_OdGbnX2-qQ96C4OjhyuPw.ttf")
    def `700`: URL = URL(s"$base/arimo/v10/ZItXugREyvV9LnbY_gxAmw.ttf")
    def `700italic`: URL = URL(s"$base/arimo/v10/__nOLWqmeXdhfr0g7GaFePesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Arizonia` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/arizonia/v7/yzJqkHZqryZBTM7RKYV9Wg.ttf")
  }
  object `Armata` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/armata/v7/1H8FwGgIRrbYtxSfXhOHlQ.ttf")
  }
  object `Arsenal` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/arsenal/v1/PkcjwJ0AdgwImdsRdyzQQQ.ttf")
    def `italic`: URL = URL(s"$base/arsenal/v1/FvYQ_YMyIFZw-8dXMYPhHg.ttf")
    def `700`: URL = URL(s"$base/arsenal/v1/6R-JWA0Y5N2Lvul2TLOH3_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/arsenal/v1/AnUIg26c0nuMZMpNWtsDFy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Artifika` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/artifika/v7/Ekfp4H4QG7D-WsABDOyj8g.ttf")
  }
  object `Arvo` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/arvo/v9/vvWPwz-PlZEwjOOIKqoZzA.ttf")
    def `italic`: URL = URL(s"$base/arvo/v9/id5a4BCjbenl5Gkqonw_Rw.ttf")
    def `700`: URL = URL(s"$base/arvo/v9/OB3FDST7U38u3OjPK_vvRQ.ttf")
    def `700italic`: URL = URL(s"$base/arvo/v9/Hvl2MuWoXLaCy2v6MD4Yvw.ttf")
  }
  object `Arya` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/arya/v2/xEVqtU3v8QLospHKpDaYEw.ttf")
    def `700`: URL = URL(s"$base/arya/v2/N13tgOvG7VTXawiI-fJiQA.ttf")
  }
  object `Asap` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/asap/v6/2lf-1MDR8tsTpEtvJmr2hA.ttf")
    def `italic`: URL = URL(s"$base/asap/v6/mwxNHf8QS8gNWCAMwkJNIg.ttf")
    def `500`: URL = URL(s"$base/asap/v6/bSf7UzaPFkjzB9TuOPVhgw.ttf")
    def `500italic`: URL = URL(s"$base/asap/v6/RUbFVj3EkB2Yo9QDVzDKLw.ttf")
    def `600`: URL = URL(s"$base/asap/v6/aj9e6BCAPmcrrkHyAtWfSg.ttf")
    def `600italic`: URL = URL(s"$base/asap/v6/lSgrQWENLu3EVBpHYwzirw.ttf")
    def `700`: URL = URL(s"$base/asap/v6/o5RUA7SsJ80M8oDFBnrDbg.ttf")
    def `700italic`: URL = URL(s"$base/asap/v6/_rZz9y2oXc09jT5T6BexLQ.ttf")
  }
  object `Asap Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/asapcondensed/v1/WnB1QP0n-KM9-GXLGChcYSavnWbQ852KImK774Atfew.ttf")
    def `italic`: URL = URL(s"$base/asapcondensed/v1/qnSL07X2cz9966iZSWZCBfYZB3dvQ7xQFxvHcvx7fMA.ttf")
    def `500`: URL = URL(s"$base/asapcondensed/v1/TyBiCbCbffkYs45BrMexjI_Y-6sQdpH-OU-ZdWEi-4E.ttf")
    def `500italic`: URL = URL(s"$base/asapcondensed/v1/9jDg2d4w2asxgWRh6ddxUYiIPHHw_LT0InVaNEq3i9o.ttf")
    def `600`: URL = URL(s"$base/asapcondensed/v1/TyBiCbCbffkYs45BrMexjKfWDuPM568rGzS6rTUUBAI.ttf")
    def `600italic`: URL = URL(s"$base/asapcondensed/v1/9jDg2d4w2asxgWRh6ddxUSWF8ZKt6Ad7F4DSH_awyvE.ttf")
    def `700`: URL = URL(s"$base/asapcondensed/v1/TyBiCbCbffkYs45BrMexjDuwRdwRx6RgmD2V-BAnY3I.ttf")
    def `700italic`: URL = URL(s"$base/asapcondensed/v1/9jDg2d4w2asxgWRh6ddxUWd8_gdoFFngi4b8GzqPlPw.ttf")
  }
  object `Asar` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/asar/v3/mSmn3H5CcMA84CZ586X7WQ.ttf")
  }
  object `Asset` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/asset/v7/hfPmqY-JzuR1lULlQf9iTg.ttf")
  }
  object `Assistant` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("hebrew", "latin")
    def `200`: URL = URL(s"$base/assistant/v1/xXstfiHQzjB9j5ZxYTBoZy3USBnSvpkopQaUR-2r7iU.ttf")
    def `300`: URL = URL(s"$base/assistant/v1/vPC3tCw3LOzCSeGCtVp5Wi3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/assistant/v1/2iDwv6DBtyixlK5YHngp1w.ttf")
    def `600`: URL = URL(s"$base/assistant/v1/Y4UC5nQA69lWpfV0itoWLi3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/assistant/v1/dZywGH4pMxP6OVyrppOJxy3USBnSvpkopQaUR-2r7iU.ttf")
    def `800`: URL = URL(s"$base/assistant/v1/-mTR0sX8a0RsadH4AMDT8C3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Astloch` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/astloch/v7/fmbitVmHYLQP7MGPuFgpag.ttf")
    def `700`: URL = URL(s"$base/astloch/v7/aPkhM2tL-tz1jX6aX2rvo_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Asul` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/asul/v6/9qpsNR_OOwyOYyo2N0IbBw.ttf")
    def `700`: URL = URL(s"$base/asul/v6/uO8uNmxaq87-DdPmkEg5Gg.ttf")
  }
  object `Athiti` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `200`: URL = URL(s"$base/athiti/v1/Ge5skdKwzxRPajVLdOJuIg.ttf")
    def `300`: URL = URL(s"$base/athiti/v1/OoT7lj4AaSp1JpGJLKn3CA.ttf")
    def `regular`: URL = URL(s"$base/athiti/v1/e7eiIKP18Iz9Kg1xat6AYw.ttf")
    def `500`: URL = URL(s"$base/athiti/v1/W3pP-ANXfsMOVOG-cqqMFw.ttf")
    def `600`: URL = URL(s"$base/athiti/v1/kYx3dtUYNEuUlzWczYzsmQ.ttf")
    def `700`: URL = URL(s"$base/athiti/v1/tyXFOxQyZGXfqHhtqSikdw.ttf")
  }
  object `Atma` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "bengali")
    def `300`: URL = URL(s"$base/atma/v2/noxn2r6cT3JgmEDt6Ip5pQ.ttf")
    def `regular`: URL = URL(s"$base/atma/v2/dkXPrLoE_uqcgUFj4JdfRQ.ttf")
    def `500`: URL = URL(s"$base/atma/v2/Htksg3ZXeAEbSvUdTQX-uw.ttf")
    def `600`: URL = URL(s"$base/atma/v2/EGUwD65ZZn9IIHp5Y36b4A.ttf")
    def `700`: URL = URL(s"$base/atma/v2/-fkXl3wADUHjobbwO9d-Wg.ttf")
  }
  object `Atomic Age` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/atomicage/v8/WvBMe4FxANIKpo6Oi0mVJ_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Aubrey` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/aubrey/v9/zo9w8klO8bmOQIMajQ2aTA.ttf")
  }
  object `Audiowide` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/audiowide/v5/yGcwRZB6VmoYhPUYT-mEow.ttf")
  }
  object `Autour One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/autourone/v6/2xmQBcg7FN72jaQRFZPIDvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Average` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/average/v5/aHUibBqdDbVYl5FM48pxyQ.ttf")
  }
  object `Average Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/averagesans/v5/dnU3R-5A_43y5bIyLztPsS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Averia Gruesa Libre` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/averiagruesalibre/v5/10vbZTOoN6T8D-nvDzwRFyXcKHuZXlCN8VkWHpkUzKM.ttf")
  }
  object `Averia Libre` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `300`: URL = URL(s"$base/averialibre/v5/r6hGL8sSLm4dTzOPXgx5XacQoVhARpoaILP7amxE_8g.ttf")
    def `300italic`: URL = URL(s"$base/averialibre/v5/I6wAYuAvOgT7el2ePj2nkina0FLWfcB-J_SAYmcAXaI.ttf")
    def `regular`: URL = URL(s"$base/averialibre/v5/rYVgHZZQICWnhjguGsBspC3USBnSvpkopQaUR-2r7iU.ttf")
    def `italic`: URL = URL(s"$base/averialibre/v5/1etzuoNxVHR8F533EkD1WfMZXuCXbOrAvx5R0IT5Oyo.ttf")
    def `700`: URL = URL(s"$base/averialibre/v5/r6hGL8sSLm4dTzOPXgx5XUD2ttfZwueP-QU272T9-k4.ttf")
    def `700italic`: URL = URL(s"$base/averialibre/v5/I6wAYuAvOgT7el2ePj2nkvAs9-1nE9qOqhChW0m4nDE.ttf")
  }
  object `Averia Sans Libre` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `300`: URL = URL(s"$base/averiasanslibre/v5/_9-jTfQjaBsWAF_yp5z-V4CP_KG_g80s1KXiBtJHoNc.ttf")
    def `300italic`: URL = URL(s"$base/averiasanslibre/v5/o7BEIK-fG3Ykc5Rzteh88YuyGu4JqttndUh4gRKxic0.ttf")
    def `regular`: URL = URL(s"$base/averiasanslibre/v5/yRJpjT39KxACO9F31mj_LqV8_KRn4epKAjTFK1s1fsg.ttf")
    def `italic`: URL = URL(s"$base/averiasanslibre/v5/COEzR_NPBSUOl3pFwPbPoCZU2HnUZT1xVKaIrHDioao.ttf")
    def `700`: URL = URL(s"$base/averiasanslibre/v5/_9-jTfQjaBsWAF_yp5z-V8QwVOrz1y5GihpZmtKLhlI.ttf")
    def `700italic`: URL = URL(s"$base/averiasanslibre/v5/o7BEIK-fG3Ykc5Rzteh88bXy1DXgmJcVtKjM5UWamMs.ttf")
  }
  object `Averia Serif Libre` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `300`: URL = URL(s"$base/averiaseriflibre/v6/yvITAdr5D1nlsdFswJAb8SmC4gFJ2PHmfdVKEd_5S9M.ttf")
    def `300italic`: URL = URL(s"$base/averiaseriflibre/v6/YOLFXyye4sZt6AZk1QybCG2okl0bU63CauowU4iApig.ttf")
    def `regular`: URL = URL(s"$base/averiaseriflibre/v6/fdtF30xa_Erw0zAzOoG4BZqY66i8AUyI16fGqw0iAew.ttf")
    def `italic`: URL = URL(s"$base/averiaseriflibre/v6/o9qhvK9iT5iDWfyhQUe-6Ru_b0bTq5iipbJ9hhgHJ6U.ttf")
    def `700`: URL = URL(s"$base/averiaseriflibre/v6/yvITAdr5D1nlsdFswJAb8Q50KV5TaOVolur4zV2iZsg.ttf")
    def `700italic`: URL = URL(s"$base/averiaseriflibre/v6/YOLFXyye4sZt6AZk1QybCNxohRXP4tNDqG3X4Hqn21k.ttf")
  }
  object `Bad Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin", "cyrillic")
    def `regular`: URL = URL(s"$base/badscript/v5/cRyUs0nJ2eMQFHwBsZNRXfesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Bahiana` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/bahiana/v1/uUnBWf2QkuMyfXPof7lcwQ.ttf")
  }
  object `Baloo` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/baloo/v2/uFkbq9GEAWUcT0XNeptJ1Q.ttf")
  }
  object `Baloo Bhai` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "gujarati", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/baloobhai/v2/FQvpC-04bh2QINuWAdnNW_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Baloo Bhaijaan` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "arabic", "vietnamese")
    def `regular`: URL = URL(s"$base/baloobhaijaan/v1/WADJjVg5Kkv7JQ_7Ty9eDj083UVTX9pxrhfn5xHQ3fY.ttf")
  }
  object `Baloo Bhaina` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "oriya")
    def `regular`: URL = URL(s"$base/baloobhaina/v2/HxxbxOVf9WQem_hKo1MXSi3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Baloo Chettan` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "malayalam")
    def `regular`: URL = URL(s"$base/baloochettan/v2/ODsFofLybGVOJ90e_EwdFbyYXtM25qb63HASTPtoTFA.ttf")
  }
  object `Baloo Da` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "bengali")
    def `regular`: URL = URL(s"$base/balooda/v2/RAJ0l2eJl_HDURCVxRE1iQ.ttf")
  }
  object `Baloo Paaji` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "gurmukhi", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/baloopaaji/v2/KeqAjVRzso6QUEfpMLQ-7KCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Baloo Tamma` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "kannada", "vietnamese")
    def `regular`: URL = URL(s"$base/balootamma/v2/-FKAYy14SAfG8Gc6YAAaMaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Baloo Tammudu` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "telugu", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/balootammudu/v2/_VlYJH4sGzgC_fTDQEKfT6ESp5dI1YWe8pDCvQ6RhbI.ttf")
  }
  object `Baloo Thambi` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "tamil")
    def `regular`: URL = URL(s"$base/baloothambi/v2/qXK3dZIeU-O-HruaN5cK0y3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Balthazar` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/balthazar/v5/WgbaSIs6dJAGXJ0qbz2xlw.ttf")
  }
  object `Bangers` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/bangers/v9/WAffdge5w99Xif-DLeqmcA.ttf")
  }
  object `Barrio` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/barrio/v1/kzvMfZB0agZKzXC5yyRwWA.ttf")
  }
  object `Basic` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/basic/v6/hNII2mS5Dxw5C0u_m3mXgA.ttf")
  }
  object `Battambang` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/battambang/v10/MzrUfQLefYum5vVGM3EZVPesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/battambang/v10/dezbRtMzfzAA99DmrCYRMgJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Baumans` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/baumans/v6/o0bFdPW1H5kd5saqqOcoVg.ttf")
  }
  object `Bayon` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/bayon/v9/yTubusjTnpNRZwA4_50iVw.ttf")
  }
  object `Belgrano` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/belgrano/v7/iq8DUa2s7g6WRCeMiFrmtQ.ttf")
  }
  object `Bellefair` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "hebrew", "latin")
    def `regular`: URL = URL(s"$base/bellefair/v2/V_AInB3Ikm6UgW6_YKlk2g.ttf")
  }
  object `Belleza` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/belleza/v5/wchA3BWJlVqvIcSeNZyXew.ttf")
  }
  object `BenchNine` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/benchnine/v5/ah9xtUy9wLQ3qnWa2p-piS3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/benchnine/v5/h3OAlYqU3aOeNkuXgH2Q2w.ttf")
    def `700`: URL = URL(s"$base/benchnine/v5/qZpi6ZVZg3L2RL_xoBLxWS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Bentham` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/bentham/v7/5-Mo8Fe7yg5tzV0GlQIuzQ.ttf")
  }
  object `Berkshire Swash` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/berkshireswash/v5/4RZJjVRPjYnC2939hKCAimKfbtsIjCZP_edQljX9gR0.ttf")
  }
  object `Bevan` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/bevan/v8/Rtg3zDsCeQiaJ_Qno22OJA.ttf")
  }
  object `Bigelow Rules` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/bigelowrules/v5/FEJCPLwo07FS-6SK6Al50X8f0n03UdmQgF_CLvNR2vg.ttf")
  }
  object `Bigshot One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/bigshotone/v7/wSyZjBNTWDQHnvWE2jt6j6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Bilbo` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/bilbo/v6/-ty-lPs5H7OIucWbnpFrkA.ttf")
  }
  object `Bilbo Swash Caps` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/bilboswashcaps/v8/UB_-crLvhx-PwGKW1oosDmYeFSdnSpRYv5h9gpdlD1g.ttf")
  }
  object `BioRhyme` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `200`: URL = URL(s"$base/biorhyme/v1/bj-6g_1gJHCc9xQZtLWL36CWcynf_cDxXwCLxiixG1c.ttf")
    def `300`: URL = URL(s"$base/biorhyme/v1/jWqHmLFlu30n7xp12uZd8qCWcynf_cDxXwCLxiixG1c.ttf")
    def `regular`: URL = URL(s"$base/biorhyme/v1/n6v5UkVPy_CjbP3fvsu1CA.ttf")
    def `700`: URL = URL(s"$base/biorhyme/v1/36KN76U1iKt5TFDm2lBz0KCWcynf_cDxXwCLxiixG1c.ttf")
    def `800`: URL = URL(s"$base/biorhyme/v1/k6bYbUnESjLYnworWvSTL6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `BioRhyme Expanded` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `200`: URL = URL(s"$base/biorhymeexpanded/v2/FKL4Vyxmq2vsiDrSOzz2sC7oxZzNh3ej55UHm-HviBI.ttf")
    def `300`: URL = URL(s"$base/biorhymeexpanded/v2/FKL4Vyxmq2vsiDrSOzz2sFu4cYPPksG4MRjB5UiYPPw.ttf")
    def `regular`: URL = URL(s"$base/biorhymeexpanded/v2/hgBNpgjTRZzGmZxqN5OuVjndr_hij4ilAk2n1d1AhsE.ttf")
    def `700`: URL = URL(s"$base/biorhymeexpanded/v2/FKL4Vyxmq2vsiDrSOzz2sMVisRVfPEfQ0jijOMQbr0Q.ttf")
    def `800`: URL = URL(s"$base/biorhymeexpanded/v2/FKL4Vyxmq2vsiDrSOzz2sIv1v1eCT6RPbcYZYQ1T1CE.ttf")
  }
  object `Biryani` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `200`: URL = URL(s"$base/biryani/v2/Xx38YzyTFF8n6mRS1Yd88vesZW2xOQ-xsNqO47m55DA.ttf")
    def `300`: URL = URL(s"$base/biryani/v2/u-bneRbizmFMd0VQp5Ze6vesZW2xOQ-xsNqO47m55DA.ttf")
    def `regular`: URL = URL(s"$base/biryani/v2/W7bfR8-IY76Xz0QoB8L2xw.ttf")
    def `600`: URL = URL(s"$base/biryani/v2/1EdcPCVxBR2txgjrza6_YPesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/biryani/v2/qN2MTZ0j1sKSCtfXLB2dR_esZW2xOQ-xsNqO47m55DA.ttf")
    def `800`: URL = URL(s"$base/biryani/v2/DJyziS7FEy441v22InYdevesZW2xOQ-xsNqO47m55DA.ttf")
    def `900`: URL = URL(s"$base/biryani/v2/trcLkrIut0lM_PPSyQfAMPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Bitter` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/bitter/v11/w_BNdJvVZDRmqy5aSfB2kQ.ttf")
    def `italic`: URL = URL(s"$base/bitter/v11/TC0FZEVzXQIGgzmRfKPZbA.ttf")
    def `700`: URL = URL(s"$base/bitter/v11/4dUtr_4BvHuoRU35suyOAg.ttf")
  }
  object `Black Ops One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/blackopsone/v8/2XW-DmDsGbDLE372KrMW1Yjjx0o0jr6fNXxPgYh_a8Q.ttf")
  }
  object `Bokor` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/bokor/v9/uAKdo0A85WW23Gs6mcbw7A.ttf")
  }
  object `Bonbon` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/bonbon/v8/IW3u1yzG1knyW5oz0s9_6Q.ttf")
  }
  object `Boogaloo` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/boogaloo/v7/4Wu1tvFMoB80fSu8qLgQfQ.ttf")
  }
  object `Bowlby One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/bowlbyone/v8/eKpHjHfjoxM2bX36YNucefesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Bowlby One SC` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/bowlbyonesc/v8/8ZkeXftTuzKBtmxOYXoRedDkZCMxWJecxjvKm2f8MJw.ttf")
  }
  object `Brawler` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/brawler/v7/3gfSw6imxQnQxweVITqUrg.ttf")
  }
  object `Bree Serif` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/breeserif/v6/5h9crBVIrvZqgf34FHcnEfesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Bubblegum Sans` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/bubblegumsans/v5/Y9iTUUNz6lbl6TrvV4iwsytnKWgpfO2iSkLzTz-AABg.ttf")
  }
  object `Bubbler One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/bubblerone/v5/e8S0qevkZAFaBybtt_SU4qCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Buda` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `300`: URL = URL(s"$base/buda/v7/hLtAmNUmEMJH2yx7NGUjnA.ttf")
  }
  object `Buenard` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/buenard/v8/NSpMPGKAUgrLrlstYVvIXQ.ttf")
    def `700`: URL = URL(s"$base/buenard/v8/yUlGE115dGr7O9w9FlP3UvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Bungee` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/bungee/v2/0jM4G9s968t1_tpwzM9UDg.ttf")
  }
  object `Bungee Hairline` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/bungeehairline/v2/8Li3dr3whdkxuk7pmLaZaSom6rTIagUDR1YFcrrRZjQ.ttf")
  }
  object `Bungee Inline` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/bungeeinline/v2/Tb-1914q4rFpjT-F66PLCYjjx0o0jr6fNXxPgYh_a8Q.ttf")
  }
  object `Bungee Outline` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/bungeeoutline/v2/PcidvzXIcqS2Qwxm_iG6bLAREgn5xbW23GEXXnhMQ5Y.ttf")
  }
  object `Bungee Shade` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/bungeeshade/v2/HSW7pxPYXBWkq7OSnuXoeC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Butcherman` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/butcherman/v8/bxiJmD567sPBVpJsT0XR0vesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Butterfly Kids` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/butterflykids/v5/J4NTF5M25htqeTffYImtlUZaDk62iwTBnbnvwSjZciA.ttf")
  }
  object `Cabin` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/cabin/v11/XeuAFYo2xAPHxZGBbQtHhA.ttf")
    def `italic`: URL = URL(s"$base/cabin/v11/0tJ9k3DI5xC4GBgs1E_Jxw.ttf")
    def `500`: URL = URL(s"$base/cabin/v11/HgsCQ-k3_Z_uQ86aFolNBg.ttf")
    def `500italic`: URL = URL(s"$base/cabin/v11/50sjhrGE0njyO-7mGDhGP_esZW2xOQ-xsNqO47m55DA.ttf")
    def `600`: URL = URL(s"$base/cabin/v11/eUDAvKhBtmTCkeVBsFk34A.ttf")
    def `600italic`: URL = URL(s"$base/cabin/v11/sFQpQDBd3G2om0Nl5dD2CvesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/cabin/v11/4EKhProuY1hq_WCAomq9Dg.ttf")
    def `700italic`: URL = URL(s"$base/cabin/v11/K83QKi8MOKLEqj6bgZ7LrfesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Cabin Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/cabincondensed/v10/B0txb0blf2N29WdYPJjMSiQPsWWoiv__AzYJ9Zzn9II.ttf")
    def `500`: URL = URL(s"$base/cabincondensed/v10/Ez4zJbsGr2BgXcNUWBVgEARL_-ABKXdjsJSPT0lc2Bk.ttf")
    def `600`: URL = URL(s"$base/cabincondensed/v10/Ez4zJbsGr2BgXcNUWBVgELS5sSASxc8z4EQTQj7DCAI.ttf")
    def `700`: URL = URL(s"$base/cabincondensed/v10/Ez4zJbsGr2BgXcNUWBVgEMAWgzcA047xWLixhLCofl8.ttf")
  }
  object `Cabin Sketch` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/cabinsketch/v10/d9fijO34zQajqQvl3YHRCS3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/cabinsketch/v10/ki3SSN5HMOO0-IOLOj069ED2ttfZwueP-QU272T9-k4.ttf")
  }
  object `Caesar Dressing` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/caesardressing/v5/2T_WzBgE2Xz3FsyJMq34T9gR43u4FvCuJwIfF5Zxl6Y.ttf")
  }
  object `Cagliostro` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/cagliostro/v5/i85oXbtdSatNEzss99bpj_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Cairo` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "arabic")
    def `200`: URL = URL(s"$base/cairo/v1/9BU6Hrio9syG9zwo_CNPXg.ttf")
    def `300`: URL = URL(s"$base/cairo/v1/mpy3SIEJVOIfFnVLujcRDg.ttf")
    def `regular`: URL = URL(s"$base/cairo/v1/-tPnHq7mmAjcjJRSjsuZGA.ttf")
    def `600`: URL = URL(s"$base/cairo/v1/Ct_3a0tcTEyNNSnuZKDd7g.ttf")
    def `700`: URL = URL(s"$base/cairo/v1/ONxTSBYfmg-V5CkIwS_5gQ.ttf")
    def `900`: URL = URL(s"$base/cairo/v1/Fm-hIVCp5OI5mO4Ec71jcw.ttf")
  }
  object `Calligraffitti` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/calligraffitti/v8/vLVN2Y-z65rVu1R7lWdvyDXz_orj3gX0_NzfmYulrko.ttf")
  }
  object `Cambay` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/cambay/v2/etU9Bab4VuhzS-OKsb1VXg.ttf")
    def `italic`: URL = URL(s"$base/cambay/v2/ZEz9yNqpEOgejaw1rBhugQ.ttf")
    def `700`: URL = URL(s"$base/cambay/v2/jw9niBxa04eEhnSwTWCEgw.ttf")
    def `700italic`: URL = URL(s"$base/cambay/v2/j-5v_uUr0NXTumWN0siOiaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Cambo` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/cambo/v5/PnwpRuTdkYCf8qk4ajmNRA.ttf")
  }
  object `Candal` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/candal/v6/x44dDW28zK7GR1gGDBmj9g.ttf")
  }
  object `Cantarell` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/cantarell/v6/p5ydP_uWQ5lsFzcP_XVMEw.ttf")
    def `italic`: URL = URL(s"$base/cantarell/v6/DTCLtOSqP-7dgM-V_xKUjqCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/cantarell/v6/Yir4ZDsCn4g1kWopdg-ehC3USBnSvpkopQaUR-2r7iU.ttf")
    def `700italic`: URL = URL(s"$base/cantarell/v6/weehrwMeZBXb0QyrWnRwFXe1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Cantata One` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/cantataone/v6/-a5FDvnBqaBMDaGgZYnEfqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Cantora One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/cantoraone/v6/oI-DS62RbHI8ZREjp73ehqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Capriola` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/capriola/v4/JxXPlkdzWwF9Cwelbvi9jA.ttf")
  }
  object `Cardo` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "greek", "latin", "greek-ext")
    def `regular`: URL = URL(s"$base/cardo/v8/jbkF2_R0FKUEZTq5dwSknQ.ttf")
    def `italic`: URL = URL(s"$base/cardo/v8/pcv4Np9tUkq0YREYUcEEJQ.ttf")
    def `700`: URL = URL(s"$base/cardo/v8/lQN30weILimrKvp8rZhF1w.ttf")
  }
  object `Carme` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/carme/v7/08E0NP1eRBEyFRUadmMfgA.ttf")
  }
  object `Carrois Gothic` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/carroisgothic/v6/GCgb7bssGpwp7V5ynxmWy2x3d0cwUleGuRTmCYfCUaM.ttf")
  }
  object `Carrois Gothic SC` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/carroisgothicsc/v6/bVp4nhwFIXU-r3LqUR8DSJTdPW1ioadGi2uRiKgJVCY.ttf")
  }
  object `Carter One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/carterone/v8/5X_LFvdbcB7OBG7hBgZ7fPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Catamaran` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "tamil")
    def `100`: URL = URL(s"$base/catamaran/v3/ilWHBiy0krUPdlmYxDuqC6CWcynf_cDxXwCLxiixG1c.ttf")
    def `200`: URL = URL(s"$base/catamaran/v3/hFc-HKSsGk6M-psujei1MC3USBnSvpkopQaUR-2r7iU.ttf")
    def `300`: URL = URL(s"$base/catamaran/v3/Aaag4ccR7Oh_4eai-jbrYC3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/catamaran/v3/MdNkM-DU8f6R-25Nxpr_XA.ttf")
    def `500`: URL = URL(s"$base/catamaran/v3/83WSX3F86qsvj1Z4EI0tQi3USBnSvpkopQaUR-2r7iU.ttf")
    def `600`: URL = URL(s"$base/catamaran/v3/a9PlHHnuBWiGGk0TwuFKTi3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/catamaran/v3/PpgVtUHUdnBZYNpnzGbScy3USBnSvpkopQaUR-2r7iU.ttf")
    def `800`: URL = URL(s"$base/catamaran/v3/6VjB_uSfn3DZ93IQv58CmC3USBnSvpkopQaUR-2r7iU.ttf")
    def `900`: URL = URL(s"$base/catamaran/v3/5ys9TqpQc9Q6gHqbSox6py3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Caudex` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "greek", "latin", "greek-ext")
    def `regular`: URL = URL(s"$base/caudex/v6/PWEexiHLDmQbn2b1OPZWfg.ttf")
    def `italic`: URL = URL(s"$base/caudex/v6/XjMZF6XCisvV3qapD4oJdw.ttf")
    def `700`: URL = URL(s"$base/caudex/v6/PetCI4GyQ5Q3LiOzUu_mMg.ttf")
    def `700italic`: URL = URL(s"$base/caudex/v6/yT8YeHLjaJvQXlUEYOA8gqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Caveat` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/caveat/v2/8I23b6N-6rRVbh-C_Vx3yA.ttf")
    def `700`: URL = URL(s"$base/caveat/v2/LkaFtQENGJry2eUMwGRTeA.ttf")
  }
  object `Caveat Brush` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/caveatbrush/v2/_d7bgsk3hfC4DXnUEeYKsy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Cedarville Cursive` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/cedarvillecursive/v7/cuCe6HrkcqrWTWTUE7dw-41zwq9-z_Lf44CzRAA0d0Y.ttf")
  }
  object `Ceviche One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/cevicheone/v7/WOaXIMBD4VYMy39MsobJhKCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Changa` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "arabic")
    def `200`: URL = URL(s"$base/changa/v2/QNWVD9FzsnhVmHzE7HryDQ.ttf")
    def `300`: URL = URL(s"$base/changa/v2/OKZ0H1bMg3M9EZMVzgQ9fg.ttf")
    def `regular`: URL = URL(s"$base/changa/v2/7_e8qktkj6uKM0DamZJY9Q.ttf")
    def `500`: URL = URL(s"$base/changa/v2/KrXcHYf9ILB8aFWCj0Vfxg.ttf")
    def `600`: URL = URL(s"$base/changa/v2/6uCpqxwcsYkfV0M8Ls6WPA.ttf")
    def `700`: URL = URL(s"$base/changa/v2/vAXzeaPkdpxlejFN7h0ibw.ttf")
    def `800`: URL = URL(s"$base/changa/v2/H3IsiH2Fx0Pc4_OU4HSpng.ttf")
  }
  object `Changa One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/changaone/v9/dr4qjce4W3kxFrZRkVD87fesZW2xOQ-xsNqO47m55DA.ttf")
    def `italic`: URL = URL(s"$base/changaone/v9/wJVQlUs1lAZel-WdTo2U9y3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Chango` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/chango/v5/3W3AeMMtRTH08t5qLOjBmg.ttf")
  }
  object `Chathura` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `100`: URL = URL(s"$base/chathura/v2/7tUse0wFXIOSPewsdeNXPvesZW2xOQ-xsNqO47m55DA.ttf")
    def `300`: URL = URL(s"$base/chathura/v2/Gmhr6ULHnPDt9spOZrHOfKCWcynf_cDxXwCLxiixG1c.ttf")
    def `regular`: URL = URL(s"$base/chathura/v2/7hRNO-_zjRopkcP2n1rr8g.ttf")
    def `700`: URL = URL(s"$base/chathura/v2/BO9LvNAseMQ3n1tKWH-uTKCWcynf_cDxXwCLxiixG1c.ttf")
    def `800`: URL = URL(s"$base/chathura/v2/prh_X_5NSsBQefIdGi5B6KCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Chau Philomene One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/chauphilomeneone/v6/KKc5egCL-a2fFVoOA2x6tBFi5dxgSTdxqnMJgWkBJcg.ttf")
    def `italic`: URL = URL(s"$base/chauphilomeneone/v6/eJj1PY_iN4KiIuyOvtMHJP6uyLkxyiC4WcYA74sfquE.ttf")
  }
  object `Chela One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/chelaone/v5/h5O0dEnpnIq6jQnWxZybrA.ttf")
  }
  object `Chelsea Market` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/chelseamarket/v4/qSdzwh2A4BbNemy78sJLfAAI1i8fIftCBXsBF2v9UMI.ttf")
  }
  object `Chenla` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/chenla/v9/aLNpdAUDq2MZbWz2U1a16g.ttf")
  }
  object `Cherry Cream Soda` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/cherrycreamsoda/v7/OrD-AUnFcZeeKa6F_c0_WxOiHiuAPYA9ry3O1RG2XIU.ttf")
  }
  object `Cherry Swash` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/cherryswash/v4/HqOk7C7J1TZ5i3L-ejF0vi3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/cherryswash/v4/-CfyMyQqfucZPQNB0nvYyED2ttfZwueP-QU272T9-k4.ttf")
  }
  object `Chewy` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/chewy/v8/hcDN5cvQdIu6Bx4mg_TSyw.ttf")
  }
  object `Chicle` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/chicle/v5/xg4q57Ut9ZmyFwLp51JLgg.ttf")
  }
  object `Chivo` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/chivo/v8/NB24D2RW9gYUd3ctGd-AhA.ttf")
    def `300italic`: URL = URL(s"$base/chivo/v8/A0NbKkUXhyt-4OxUzvrNT_esZW2xOQ-xsNqO47m55DA.ttf")
    def `regular`: URL = URL(s"$base/chivo/v8/L88PEuzS9eRfHRZhAPhZyw.ttf")
    def `italic`: URL = URL(s"$base/chivo/v8/Oe3-Q-a2kBzPnhHck_baMg.ttf")
    def `700`: URL = URL(s"$base/chivo/v8/zC8JLnJuu9Lw0_rA3_VYhg.ttf")
    def `700italic`: URL = URL(s"$base/chivo/v8/2M3ifXA84fdnDIxoCi18JvesZW2xOQ-xsNqO47m55DA.ttf")
    def `900`: URL = URL(s"$base/chivo/v8/JAdkiWd46QCW4vOsj3dzTA.ttf")
    def `900italic`: URL = URL(s"$base/chivo/v8/LoszYnE86q2wJEOjCigBQ_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Chonburi` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `regular`: URL = URL(s"$base/chonburi/v1/jd9PfbW0x_8Myt_XeUxvSQ.ttf")
  }
  object `Cinzel` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/cinzel/v6/GF7dy_Nc-a6EaHYSyGd-EA.ttf")
    def `700`: URL = URL(s"$base/cinzel/v6/nYcFQ6_3pf_6YDrOFjBR8Q.ttf")
    def `900`: URL = URL(s"$base/cinzel/v6/FTBj72ozM2cEOSxiVsRb3A.ttf")
  }
  object `Cinzel Decorative` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/cinzeldecorative/v5/fmgK7oaJJIXAkhd9798yQgT5USbJx2F82lQbogPy2bY.ttf")
    def `700`: URL = URL(s"$base/cinzeldecorative/v5/pXhIVnhFtL_B9Vb1wq2F95-YYVDmZkJErg0zgx9XuZI.ttf")
    def `900`: URL = URL(s"$base/cinzeldecorative/v5/pXhIVnhFtL_B9Vb1wq2F97Khqbv0zQZa0g-9HOXAalU.ttf")
  }
  object `Clicker Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/clickerscript/v4/Zupmk8XwADjufGxWB9KThBnpV0hQCek3EmWnCPrvGRM.ttf")
  }
  object `Coda` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/coda/v12/yHDvulhg-P-p2KRgRrnUYw.ttf")
    def `800`: URL = URL(s"$base/coda/v12/6ZIw0sbALY0KTMWllZB3hQ.ttf")
  }
  object `Coda Caption` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `800`: URL = URL(s"$base/codacaption/v10/YDl6urZh-DUFhiMBTgAnz_qsay_1ZmRGmC8pVRdIfAg.ttf")
  }
  object `Codystar` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/codystar/v4/EVaUzfJkcb8Zqx9kzQLXqqCWcynf_cDxXwCLxiixG1c.ttf")
    def `regular`: URL = URL(s"$base/codystar/v4/EN-CPFKYowSI7SuR7-0cZA.ttf")
  }
  object `Coiny` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "tamil")
    def `regular`: URL = URL(s"$base/coiny/v2/B-pC9lRxssd2RDK37Rdekw.ttf")
  }
  object `Combo` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/combo/v5/Nab98KjR3JZSSPGtzLyXNw.ttf")
  }
  object `Comfortaa` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "vietnamese", "cyrillic")
    def `300`: URL = URL(s"$base/comfortaa/v10/r_tUZNl0G8xCoOmp_JkSCi3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/comfortaa/v10/lZx6C1VViPgSOhCBUP7hXA.ttf")
    def `700`: URL = URL(s"$base/comfortaa/v10/fND5XPYKrF2tQDwwfWZJIy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Coming Soon` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/comingsoon/v7/Yz2z3IAe2HSQAOWsSG8COKCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Concert One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/concertone/v7/N5IWCIGhUNdPZn_efTxKN6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Condiment` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/condiment/v4/CstmdiPpgFSV0FUNL5LrJA.ttf")
  }
  object `Content` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/content/v8/l8qaLjygvOkDEU2G6-cjfQ.ttf")
    def `700`: URL = URL(s"$base/content/v8/7PivP8Zvs2qn6F6aNbSQe_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Contrail One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/contrailone/v6/b41KxjgiyqX-hkggANDU6C3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Convergence` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/convergence/v5/eykrGz1NN_YpQmkAZjW-qKCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Cookie` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/cookie/v7/HxeUC62y_YdDbiFlze357A.ttf")
  }
  object `Copse` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/copse/v6/wikLrtPGjZDvZ5w2i5HLWg.ttf")
  }
  object `Corben` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/corben/v10/tTysMZkt-j8Y5yhkgsoajQ.ttf")
    def `700`: URL = URL(s"$base/corben/v10/lirJaFSQWdGQuV--fksg5g.ttf")
  }
  object `Cormorant` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `300`: URL = URL(s"$base/cormorant/v5/diggKPcUerIA8GQWRVxsVS3USBnSvpkopQaUR-2r7iU.ttf")
    def `300italic`: URL = URL(s"$base/cormorant/v5/UydD9tmk-DfLnEFRr_bBZy9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/cormorant/v5/9vWr5LgrNEgvhv1P3z9uuQ.ttf")
    def `italic`: URL = URL(s"$base/cormorant/v5/zzcH3j00ejnIc8jicdcz6KCWcynf_cDxXwCLxiixG1c.ttf")
    def `500`: URL = URL(s"$base/cormorant/v5/lwoiMb1lzDf49h802vpRUy3USBnSvpkopQaUR-2r7iU.ttf")
    def `500italic`: URL = URL(s"$base/cormorant/v5/UydD9tmk-DfLnEFRr_bBZ8CNfqCYlB_eIx7H1TVXe60.ttf")
    def `600`: URL = URL(s"$base/cormorant/v5/LKEtp8XimHLN0gSYqnV9qy3USBnSvpkopQaUR-2r7iU.ttf")
    def `600italic`: URL = URL(s"$base/cormorant/v5/UydD9tmk-DfLnEFRr_bBZ5Z7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `700`: URL = URL(s"$base/cormorant/v5/vOi7JV5F3JmPzXDgUqUwgS3USBnSvpkopQaUR-2r7iU.ttf")
    def `700italic`: URL = URL(s"$base/cormorant/v5/UydD9tmk-DfLnEFRr_bBZ3e1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Cormorant Garamond` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `300`: URL = URL(s"$base/cormorantgaramond/v4/iEjm9hVxcattz37Y8gZwVXDeRRUpi2fYbqcTC9PsYaU.ttf")
    def `300italic`: URL = URL(s"$base/cormorantgaramond/v4/zuqx3k1yUEl3Eavo-ZPEAjZXe39LdglsIzDOvKnCCso.ttf")
    def `regular`: URL = URL(s"$base/cormorantgaramond/v4/EI2hhCO6kSfLAy-Dpd8fd7_BES7rBA-D9Lo3vCx9yHc.ttf")
    def `italic`: URL = URL(s"$base/cormorantgaramond/v4/eGTlzchVxDKKvK6d7drzlkVlEttMzBRhK_wsRQ4MqEE.ttf")
    def `500`: URL = URL(s"$base/cormorantgaramond/v4/iEjm9hVxcattz37Y8gZwVSkwnhSVYGQY4MSUB3uw374.ttf")
    def `500italic`: URL = URL(s"$base/cormorantgaramond/v4/zuqx3k1yUEl3Eavo-ZPEAq8qrY1CcUgPLrA3ytfr3SY.ttf")
    def `600`: URL = URL(s"$base/cormorantgaramond/v4/iEjm9hVxcattz37Y8gZwVVc2xdGA7R8efE0K6NwSoyI.ttf")
    def `600italic`: URL = URL(s"$base/cormorantgaramond/v4/zuqx3k1yUEl3Eavo-ZPEAqms9Rm_p2hhD4xhClOGPEw.ttf")
    def `700`: URL = URL(s"$base/cormorantgaramond/v4/iEjm9hVxcattz37Y8gZwVdNg01MkafbqNYmDx8wt95c.ttf")
    def `700italic`: URL = URL(s"$base/cormorantgaramond/v4/zuqx3k1yUEl3Eavo-ZPEAvEntfLz8TC-DlAIEJQEwCA.ttf")
  }
  object `Cormorant Infant` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `300`: URL = URL(s"$base/cormorantinfant/v4/MYRpw6pQIf0XStsiZXQWA_alucuYFvoGqpCMGloCN2Y.ttf")
    def `300italic`: URL = URL(s"$base/cormorantinfant/v4/PK34LKusK6SSQFR2m5-LZgNCjGMFnYSoo4kW2wZNowE.ttf")
    def `regular`: URL = URL(s"$base/cormorantinfant/v4/q5F0I_a42y_qtMoOtqdjagGlf-pqPDOheSBqZOVpkRo.ttf")
    def `italic`: URL = URL(s"$base/cormorantinfant/v4/U6OamtMgLoVs0zd53Z1pNpbq6_N3pcDBvA-VsecMIAA.ttf")
    def `500`: URL = URL(s"$base/cormorantinfant/v4/MYRpw6pQIf0XStsiZXQWA4PJQ8Vh-2Qw35Pq7cVYzdo.ttf")
    def `500italic`: URL = URL(s"$base/cormorantinfant/v4/PK34LKusK6SSQFR2m5-LZq9x-au7fLBTFpfuT52_G64.ttf")
    def `600`: URL = URL(s"$base/cormorantinfant/v4/MYRpw6pQIf0XStsiZXQWA9G0tNuOpbNMRdNl4S5e-n0.ttf")
    def `600italic`: URL = URL(s"$base/cormorantinfant/v4/PK34LKusK6SSQFR2m5-LZkZbdnTqrL_1WMEFjxg0OwY.ttf")
    def `700`: URL = URL(s"$base/cormorantinfant/v4/MYRpw6pQIf0XStsiZXQWAx-3ZynwDtU_450Ho62jf_I.ttf")
    def `700italic`: URL = URL(s"$base/cormorantinfant/v4/PK34LKusK6SSQFR2m5-LZmKEEmz9BBHY1o7RrRAiUXQ.ttf")
  }
  object `Cormorant SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `300`: URL = URL(s"$base/cormorantsc/v4/CCo4fI9EYzhUJcvojQ9Em6cQoVhARpoaILP7amxE_8g.ttf")
    def `regular`: URL = URL(s"$base/cormorantsc/v4/o2HxNCgvhmwJdltu-68tzC3USBnSvpkopQaUR-2r7iU.ttf")
    def `500`: URL = URL(s"$base/cormorantsc/v4/CCo4fI9EYzhUJcvojQ9Em5MQuUSAwdHsY8ov_6tk1oA.ttf")
    def `600`: URL = URL(s"$base/cormorantsc/v4/CCo4fI9EYzhUJcvojQ9Em2v8CylhIUtwUiYO7Z2wXbE.ttf")
    def `700`: URL = URL(s"$base/cormorantsc/v4/CCo4fI9EYzhUJcvojQ9Em0D2ttfZwueP-QU272T9-k4.ttf")
  }
  object `Cormorant Unicase` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `300`: URL = URL(s"$base/cormorantunicase/v4/-0mwRHhjEGfrz-UDHJ_78TyAYAK5JX1-zBpfFXu9t3Y.ttf")
    def `regular`: URL = URL(s"$base/cormorantunicase/v4/THO7JMNV6qRoZlg7dU5RUz01TLsHlMvD1uPU3gXOh9s.ttf")
    def `500`: URL = URL(s"$base/cormorantunicase/v4/-0mwRHhjEGfrz-UDHJ_78WActzpz5sLElWWJpZBcHK4.ttf")
    def `600`: URL = URL(s"$base/cormorantunicase/v4/-0mwRHhjEGfrz-UDHJ_78U0bQT13XmwBbvkXy6Yb64Y.ttf")
    def `700`: URL = URL(s"$base/cormorantunicase/v4/-0mwRHhjEGfrz-UDHJ_78Z5CFeQBXku3ADXbkP2V7W8.ttf")
  }
  object `Cormorant Upright` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `300`: URL = URL(s"$base/cormorantupright/v3/PwJT_lCdbLUyVq-tARIPhjCfCvaSiUMfec2BKBTMAaw.ttf")
    def `regular`: URL = URL(s"$base/cormorantupright/v3/0n68kajKjTOJn9EPQkf1a-ojtTJJf2MtgkoRSid3NcM.ttf")
    def `500`: URL = URL(s"$base/cormorantupright/v3/PwJT_lCdbLUyVq-tARIPhiWhx5Kr-bzfZXhgF-AnSvk.ttf")
    def `600`: URL = URL(s"$base/cormorantupright/v3/PwJT_lCdbLUyVq-tARIPhuDigFx2V_wQ4SOTZdg5a2s.ttf")
    def `700`: URL = URL(s"$base/cormorantupright/v3/PwJT_lCdbLUyVq-tARIPhuO6SP7lRr11seyd3AkK37Q.ttf")
  }
  object `Courgette` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/courgette/v4/2YO0EYtyE9HUPLZprahpZA.ttf")
  }
  object `Cousine` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "hebrew", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/cousine/v11/GYX4bPXObJNJo63QJEUnLg.ttf")
    def `italic`: URL = URL(s"$base/cousine/v11/1WtIuajLoo8vjVwsrZ3eOg.ttf")
    def `700`: URL = URL(s"$base/cousine/v11/FXEOnNUcCzhdtoBxiq-lovesZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/cousine/v11/y_AZ5Sz-FwL1lux2xLSTZS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Coustard` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/coustard/v7/iO2Rs5PmqAEAXoU3SkMVBg.ttf")
    def `900`: URL = URL(s"$base/coustard/v7/W02OCWO6OfMUHz6aVyegQ6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Covered By Your Grace` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/coveredbyyourgrace/v6/6ozZp4BPlrbDRWPe3EBGA6CVUMdvnk-GcAiZQrX9Gek.ttf")
  }
  object `Crafty Girls` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/craftygirls/v6/0Sv8UWFFdhQmesHL32H8oy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Creepster` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/creepster/v5/0vdr5kWJ6aJlOg5JvxnXzQ.ttf")
  }
  object `Crete Round` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/creteround/v5/B8EwN421qqOCCT8vOH4wJ6CWcynf_cDxXwCLxiixG1c.ttf")
    def `italic`: URL = URL(s"$base/creteround/v5/5xAt7XK2vkUdjhGtt98unUeOrDcLawS7-ssYqLr2Xp4.ttf")
  }
  object `Crimson Text` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/crimsontext/v7/3IFMwfRa07i-auYR-B-zNS3USBnSvpkopQaUR-2r7iU.ttf")
    def `italic`: URL = URL(s"$base/crimsontext/v7/a5QZnvmn5amyNI-t2BMkWPMZXuCXbOrAvx5R0IT5Oyo.ttf")
    def `600`: URL = URL(s"$base/crimsontext/v7/rEy5tGc5HdXy56Xvd4f3I2v8CylhIUtwUiYO7Z2wXbE.ttf")
    def `600italic`: URL = URL(s"$base/crimsontext/v7/4j4TR-EfnvCt43InYpUNDIR-5-urNOGAobhAyctHvW8.ttf")
    def `700`: URL = URL(s"$base/crimsontext/v7/rEy5tGc5HdXy56Xvd4f3I0D2ttfZwueP-QU272T9-k4.ttf")
    def `700italic`: URL = URL(s"$base/crimsontext/v7/4j4TR-EfnvCt43InYpUNDPAs9-1nE9qOqhChW0m4nDE.ttf")
  }
  object `Croissant One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/croissantone/v4/mPjsOObnC77fp1cvZlOfIYjjx0o0jr6fNXxPgYh_a8Q.ttf")
  }
  object `Crushed` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/crushed/v7/aHwSejs3Kt0Lg95u7j32jA.ttf")
  }
  object `Cuprum` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/cuprum/v8/JgXs0F_UiaEdAS74msmFNg.ttf")
    def `italic`: URL = URL(s"$base/cuprum/v8/cLEz0KV6OxInnktSzpk58g.ttf")
    def `700`: URL = URL(s"$base/cuprum/v8/6tl3_FkDeXSD72oEHuJh4w.ttf")
    def `700italic`: URL = URL(s"$base/cuprum/v8/bnkXaBfoYvaJ75axRPSwVKCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Cutive` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/cutive/v8/G2bW-ImyOCwKxBkLyz39YQ.ttf")
  }
  object `Cutive Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/cutivemono/v5/ncWQtFVKcSs8OW798v30k6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Damion` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/damion/v6/13XtECwKxhD_VrOqXL4SiA.ttf")
  }
  object `Dancing Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/dancingscript/v8/DK0eTGXiZjN6yA8zAEyM2RnpV0hQCek3EmWnCPrvGRM.ttf")
    def `700`: URL = URL(s"$base/dancingscript/v8/KGBfwabt0ZRLA5W1ywjowb_dAmXiKjTPGCuO6G2MbfA.ttf")
  }
  object `Dangrek` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/dangrek/v8/LOaFhBT-EHNxZjV8DAW_ew.ttf")
  }
  object `David Libre` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "hebrew", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/davidlibre/v1/Fp_YuX4CP0pzlSUtACdOo6CWcynf_cDxXwCLxiixG1c.ttf")
    def `500`: URL = URL(s"$base/davidlibre/v1/ea-623K8OFNeGhfSzdpmysCNfqCYlB_eIx7H1TVXe60.ttf")
    def `700`: URL = URL(s"$base/davidlibre/v1/ea-623K8OFNeGhfSzdpmyne1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Dawning of a New Day` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/dawningofanewday/v7/JiDsRhiKZt8uz3NJ5xA06gXLnohmOYWQZqo_sW8GLTk.ttf")
  }
  object `Days One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/daysone/v6/kzwZjNhc1iabMsrc_hKBIA.ttf")
  }
  object `Dekko` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/dekko/v3/AKtgABKC1rUxgIgS-bpojw.ttf")
  }
  object `Delius` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/delius/v6/TQA163qafki2-gV-B6F_ag.ttf")
  }
  object `Delius Swash Caps` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/deliusswashcaps/v8/uXyrEUnoWApxIOICunRq7yIrxb5zDVgU2N3VzXm7zq4.ttf")
  }
  object `Delius Unicase` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/deliusunicase/v9/b2sKujV3Q48RV2PQ0k1vqu6rPKfVZo7L2bERcf0BDns.ttf")
    def `700`: URL = URL(s"$base/deliusunicase/v9/7FTMTITcb4dxUp99FAdTqNy5weKXdcrx-wE0cgECMq8.ttf")
  }
  object `Della Respira` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/dellarespira/v4/F4E6Lo_IZ6L9AJCcbqtDVeDcg5akpSnIcsPhLOFv7l8.ttf")
  }
  object `Denk One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/denkone/v4/TdXOeA4eA_hEx4W8Sh9wPw.ttf")
  }
  object `Devonshire` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/devonshire/v5/I3ct_2t12SYizP8ZC-KFi_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Dhurjati` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/dhurjati/v4/uV6jO5e2iFMbGB0z79Cy5g.ttf")
  }
  object `Didact Gothic` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "cyrillic")
    def `regular`: URL = URL(s"$base/didactgothic/v10/v8_72sD3DYMKyM0dn3LtWotBLojGU5Qdl8-5NL4v70w.ttf")
  }
  object `Diplomata` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/diplomata/v8/u-ByBiKgN6rTMA36H3kcKg.ttf")
  }
  object `Diplomata SC` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/diplomatasc/v5/JdVwAwfE1a_pahXjk5qpNi3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Domine` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/domine/v4/wfVIgamVFjMNQAEWurCiHA.ttf")
    def `700`: URL = URL(s"$base/domine/v4/phBcG1ZbQFxUIt18hPVxnw.ttf")
  }
  object `Donegal One` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/donegalone/v4/6kN4-fDxz7T9s5U61HwfF6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Doppio One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/doppioone/v4/WHZ3HJQotpk_4aSMNBo_t_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Dorsa` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/dorsa/v7/wCc3cUe6XrmG2LQE6GlIrw.ttf")
  }
  object `Dosis` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `200`: URL = URL(s"$base/dosis/v6/ztftab0r6hcd7AeurUGrSQ.ttf")
    def `300`: URL = URL(s"$base/dosis/v6/awIB6L0h5mb0plIKorXmuA.ttf")
    def `regular`: URL = URL(s"$base/dosis/v6/rJRlixu-w0JZ1MyhJpao_Q.ttf")
    def `500`: URL = URL(s"$base/dosis/v6/ruEXDOFMxDPGnjCBKRqdAQ.ttf")
    def `600`: URL = URL(s"$base/dosis/v6/KNAswRNwm3tfONddYyidxg.ttf")
    def `700`: URL = URL(s"$base/dosis/v6/AEEAj0ONidK8NQQMBBlSig.ttf")
    def `800`: URL = URL(s"$base/dosis/v6/nlrKd8E69vvUU39XGsvR7Q.ttf")
  }
  object `Dr Sugiyama` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/drsugiyama/v5/S5Yx3MIckgoyHhhS4C9Tv6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Droid Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/droidsans/v7/rS9BT6-asrfjpkcV3DXf__esZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/droidsans/v7/EFpQQyG9GqCrobXxL-KRMQJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Droid Sans Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/droidsansmono/v8/ns-m2xQYezAtqh7ai59hJcwD6PD0c3_abh9zHKQtbGU.ttf")
  }
  object `Droid Serif` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/droidserif/v7/DgAtPy6rIVa2Zx3Xh9KaNaCWcynf_cDxXwCLxiixG1c.ttf")
    def `italic`: URL = URL(s"$base/droidserif/v7/cj2hUnSRBhwmSPr9kS5890eOrDcLawS7-ssYqLr2Xp4.ttf")
    def `700`: URL = URL(s"$base/droidserif/v7/QQt14e8dY39u-eYBZmppwXe1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `700italic`: URL = URL(s"$base/droidserif/v7/c92rD_x0V1LslSFt3-QEps_zJjSACmk0BRPxQqhnNLU.ttf")
  }
  object `Duru Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/durusans/v9/xn7iYH8xwmSyTvEV_HOxTw.ttf")
  }
  object `Dynalight` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/dynalight/v5/-CWsIe8OUDWTIHjSAh41kA.ttf")
  }
  object `EB Garamond` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/ebgaramond/v7/CDR0kuiFK7I1OZ2hSdR7G6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Eagle Lake` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/eaglelake/v4/ZKlYin7caemhx9eSg6RvPfesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Eater` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/eater/v5/gm6f3OmYEdbs3lPQtUfBkA.ttf")
  }
  object `Economica` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/economica/v4/G4rJRujzZbq9Nxngu9l3hg.ttf")
    def `italic`: URL = URL(s"$base/economica/v4/p5O9AVeUqx_n35xQRinNYaCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/economica/v4/UK4l2VEpwjv3gdcwbwXE9C3USBnSvpkopQaUR-2r7iU.ttf")
    def `700italic`: URL = URL(s"$base/economica/v4/ac5dlUsedQ03RqGOeay-3Xe1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Eczar` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/eczar/v5/uKZcAQ5JBBs1UbeXFRbBRg.ttf")
    def `500`: URL = URL(s"$base/eczar/v5/Ooe4KaPp2594tF8TbMfdlQ.ttf")
    def `600`: URL = URL(s"$base/eczar/v5/IjQsWW0bmgkZ6lnN72cnTQ.ttf")
    def `700`: URL = URL(s"$base/eczar/v5/ELC8RVXfBMb3VuuHtMwBOA.ttf")
    def `800`: URL = URL(s"$base/eczar/v5/9Uyt6nTZLx_Qj5_WRah-iQ.ttf")
  }
  object `El Messiri` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin", "arabic", "cyrillic")
    def `regular`: URL = URL(s"$base/elmessiri/v1/dik94vfrFvHFnvdvxaX8N_esZW2xOQ-xsNqO47m55DA.ttf")
    def `500`: URL = URL(s"$base/elmessiri/v1/kQW9PA2krAOzditagrX75pp-63r6doWhTEbsfBIRJ7A.ttf")
    def `600`: URL = URL(s"$base/elmessiri/v1/HYl7TNqFfA1utGLZRWwzLPpTEJqju4Hz1txDWij77d4.ttf")
    def `700`: URL = URL(s"$base/elmessiri/v1/ji73glXFIetaSqMU3cz7rAJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Electrolize` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/electrolize/v5/yFVu5iokC-nt4B1Cyfxb9aCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Elsie` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/elsie/v6/gwspePauE45BJu6Ok1QrfQ.ttf")
    def `900`: URL = URL(s"$base/elsie/v6/1t-9f0N2NFYwAgN7oaISqg.ttf")
  }
  object `Elsie Swash Caps` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/elsieswashcaps/v5/9L3hIJMPCf6sxCltnxd6X2YeFSdnSpRYv5h9gpdlD1g.ttf")
    def `900`: URL = URL(s"$base/elsieswashcaps/v5/iZnus9qif0tR5pGaDv5zdKoKBWBozTtxi30NfZDOXXU.ttf")
  }
  object `Emblema One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/emblemaone/v5/7IlBUjBWPIiw7cr_O2IfSaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Emilys Candy` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/emilyscandy/v4/PofLVm6v1SwZGOzC8s-I3S3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Encode Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/encodesans/v1/TvUFkOGoNYwmv-XugrRC14AWxXGWZ3yJw6KhWS7MxOk.ttf")
    def `200`: URL = URL(s"$base/encodesans/v1/IaOhmWC4W3-qZLH1UUd4vEnzyIngrzGjGh22wPb6cGM.ttf")
    def `300`: URL = URL(s"$base/encodesans/v1/IaOhmWC4W3-qZLH1UUd4vC9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/encodesans/v1/xpYstnmVhPpbvOHKD75EK6CWcynf_cDxXwCLxiixG1c.ttf")
    def `500`: URL = URL(s"$base/encodesans/v1/IaOhmWC4W3-qZLH1UUd4vMCNfqCYlB_eIx7H1TVXe60.ttf")
    def `600`: URL = URL(s"$base/encodesans/v1/IaOhmWC4W3-qZLH1UUd4vJZ7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `700`: URL = URL(s"$base/encodesans/v1/IaOhmWC4W3-qZLH1UUd4vHe1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `800`: URL = URL(s"$base/encodesans/v1/IaOhmWC4W3-qZLH1UUd4vA89PwPrYLaRFJ-HNCU9NbA.ttf")
    def `900`: URL = URL(s"$base/encodesans/v1/IaOhmWC4W3-qZLH1UUd4vCenaqEuufTBk9XMKnKmgDA.ttf")
  }
  object `Encode Sans Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/encodesanscondensed/v1/6LOoEWi9It096ZzMNw6yeii7tdGxScTr3oVgcrTUqWw.ttf")
    def `200`: URL = URL(s"$base/encodesanscondensed/v1/UP_H-DzI6prLPN-PMUyxY61IHoFZjDq9yl49NJ3Y0wY.ttf")
    def `300`: URL = URL(s"$base/encodesanscondensed/v1/UP_H-DzI6prLPN-PMUyxY-ZroXgFx_lT3TTeDaAqrWE.ttf")
    def `regular`: URL = URL(s"$base/encodesanscondensed/v1/CbFzpyBSY4j-AYSd59uzHIelbRYnLTTQA1Z5cVLnsI4.ttf")
    def `500`: URL = URL(s"$base/encodesanscondensed/v1/UP_H-DzI6prLPN-PMUyxY64Ixr3FMLIaz6yY1ILODIU.ttf")
    def `600`: URL = URL(s"$base/encodesanscondensed/v1/UP_H-DzI6prLPN-PMUyxY8MHImBNo4aGUuMCjGiDijI.ttf")
    def `700`: URL = URL(s"$base/encodesanscondensed/v1/UP_H-DzI6prLPN-PMUyxY7GMx7y0UuyPIsLqSMg46Ks.ttf")
    def `800`: URL = URL(s"$base/encodesanscondensed/v1/UP_H-DzI6prLPN-PMUyxY_3VPWKD9LjLpSGgTAgUUIc.ttf")
    def `900`: URL = URL(s"$base/encodesanscondensed/v1/UP_H-DzI6prLPN-PMUyxY73y6LE9HhLx9tlnlwi3OAw.ttf")
  }
  object `Encode Sans Expanded` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/encodesansexpanded/v1/SxJCe-5XtgTwkLeuB6DsDAzYtaUryPdMybTmqF2t-hk.ttf")
    def `200`: URL = URL(s"$base/encodesansexpanded/v1/NZFW_aAjtWMwFwRPQHyMtImyl4eLRAk2hWaf4usQtfw.ttf")
    def `300`: URL = URL(s"$base/encodesansexpanded/v1/NZFW_aAjtWMwFwRPQHyMtE8dNemX_23MZOKO5OoYF5E.ttf")
    def `regular`: URL = URL(s"$base/encodesansexpanded/v1/OdOWbHhxwo9XAUoeS5o4Dg7dxr0N5HY0cZKknTIL6n4.ttf")
    def `500`: URL = URL(s"$base/encodesansexpanded/v1/NZFW_aAjtWMwFwRPQHyMtPqCJK4Zn8SYLcLgnaiBGrc.ttf")
    def `600`: URL = URL(s"$base/encodesansexpanded/v1/NZFW_aAjtWMwFwRPQHyMtFwX9co0a2-oIpf1o8i-1K0.ttf")
    def `700`: URL = URL(s"$base/encodesansexpanded/v1/NZFW_aAjtWMwFwRPQHyMtD3JW4OQm61sg8k8DfLBAwg.ttf")
    def `800`: URL = URL(s"$base/encodesansexpanded/v1/NZFW_aAjtWMwFwRPQHyMtJvi7umicd6qVgIYLFojqyc.ttf")
    def `900`: URL = URL(s"$base/encodesansexpanded/v1/NZFW_aAjtWMwFwRPQHyMtGZrxQvJ_xEKbxayeNEjyrc.ttf")
  }
  object `Encode Sans Semi Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/encodesanssemicondensed/v1/E6kA5T3mzxUj69IdQg70PS1QEJchpDhTUwbwiSjEPbgt1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `200`: URL = URL(s"$base/encodesanssemicondensed/v1/z-mVMDpNLBzCo6eVg95vHVxi1xYyRqMxS_FPu-moW0lnrnXkzuOM3_obd5Pijc8I.ttf")
    def `300`: URL = URL(s"$base/encodesanssemicondensed/v1/z-mVMDpNLBzCo6eVg95vHSLQwj9Lduqb1W3tq4fXf91Hjqw3C2sEu_rLGKi69l6e.ttf")
    def `regular`: URL = URL(s"$base/encodesanssemicondensed/v1/70xnFP2R6L67b4lbb0LqFQ760Nu0ZmWpK1JTCHVCKHz3rGVtsTkPsbDajuO5ueQw.ttf")
    def `500`: URL = URL(s"$base/encodesanssemicondensed/v1/z-mVMDpNLBzCo6eVg95vHWPzD9HBxt0HXJBsJbnj8Taafut6-naFoUxG7HwSESew.ttf")
    def `600`: URL = URL(s"$base/encodesanssemicondensed/v1/z-mVMDpNLBzCo6eVg95vHZTIxrxLvLMtU-yhyAf1TK_6UxCao7uB89bcQ1oo--3e.ttf")
    def `700`: URL = URL(s"$base/encodesanssemicondensed/v1/z-mVMDpNLBzCo6eVg95vHanrccv-0xgQwXIoROQBHDkCSihn6h2mBbERvk93HhFa.ttf")
    def `800`: URL = URL(s"$base/encodesanssemicondensed/v1/z-mVMDpNLBzCo6eVg95vHaUGwPLApwd9av9Pcjv04cOpN24TwUgSdG0iUOmnC_tI.ttf")
    def `900`: URL = URL(s"$base/encodesanssemicondensed/v1/z-mVMDpNLBzCo6eVg95vHf3LPq0EY0JuN61BrMSCA9udBAFcbdBtG4hJ7aeN0Leh.ttf")
  }
  object `Encode Sans Semi Expanded` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/encodesanssemiexpanded/v1/Dt9vBR-jlFaGi37WwOwD_8qIAxu59oivT8gVJSaPAJmglnMp3_3A8V8Ai8YosRtX.ttf")
    def `200`: URL = URL(s"$base/encodesanssemiexpanded/v1/CzlMbAciMXgtU6UUaNDI4iyuBgySKCdxv6GjzoxXXEct1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `300`: URL = URL(s"$base/encodesanssemiexpanded/v1/CzlMbAciMXgtU6UUaNDI4mA0loIJ_cqzG2SO7pmT2v8t1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `regular`: URL = URL(s"$base/encodesanssemiexpanded/v1/L50h_XWfeGcmQgSaLLv8qDl-hG_EEbQLBeCEvsoBv9c.ttf")
    def `500`: URL = URL(s"$base/encodesanssemiexpanded/v1/CzlMbAciMXgtU6UUaNDI4m9ZGOr7ke8-zfCGnYaqVkwt1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `600`: URL = URL(s"$base/encodesanssemiexpanded/v1/CzlMbAciMXgtU6UUaNDI4jZr6ABenySL2MEoV49ZPIEt1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `700`: URL = URL(s"$base/encodesanssemiexpanded/v1/CzlMbAciMXgtU6UUaNDI4vb58e8syHA9EvUqaFcpH8kt1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `800`: URL = URL(s"$base/encodesanssemiexpanded/v1/CzlMbAciMXgtU6UUaNDI4v1ujhhC8jANxa3d-BaQZ3st1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `900`: URL = URL(s"$base/encodesanssemiexpanded/v1/CzlMbAciMXgtU6UUaNDI4sIOIZ6BsfRi1i9aEyUWch4t1EgZ0r6ZKKUGlEftq-4l.ttf")
  }
  object `Engagement` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/engagement/v5/4Uz0Jii7oVPcaFRYmbpU6vesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Englebert` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/englebert/v4/sll38iOvOuarDTYBchlP3Q.ttf")
  }
  object `Enriqueta` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/enriqueta/v5/_p90TrIwR1SC-vDKtmrv6A.ttf")
    def `700`: URL = URL(s"$base/enriqueta/v5/I27Pb-wEGH2ajLYP0QrtSC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Erica One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/ericaone/v7/cIBnH2VAqQMIGYAcE4ufvQ.ttf")
  }
  object `Esteban` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/esteban/v4/ESyhLgqDDyK5JcFPp2svDw.ttf")
  }
  object `Euphoria Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/euphoriascript/v4/c4XB4Iijj_NvSsCF4I0O2MxLhO8OSNnfAp53LK1_iRs.ttf")
  }
  object `Ewert` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/ewert/v4/Em8hrzuzSbfHcTVqMjbAQg.ttf")
  }
  object `Exo` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/exo/v5/RI7A9uwjRmPbVp0n8e-Jvg.ttf")
    def `100italic`: URL = URL(s"$base/exo/v5/qtGyZZlWb2EEvby3ZPosxw.ttf")
    def `200`: URL = URL(s"$base/exo/v5/F8OfC_swrRRxpFt-tlXZQg.ttf")
    def `200italic`: URL = URL(s"$base/exo/v5/fr4HBfXHYiIngW2_bhlgRw.ttf")
    def `300`: URL = URL(s"$base/exo/v5/SBrN7TKUqgGUvfxqHqsnNw.ttf")
    def `300italic`: URL = URL(s"$base/exo/v5/3gmiLjBegIfcDLISjTGA1g.ttf")
    def `regular`: URL = URL(s"$base/exo/v5/eUEzTFueNXRVhbt4PEB8kQ.ttf")
    def `italic`: URL = URL(s"$base/exo/v5/cfgolWisMSURhpQeVHl_NA.ttf")
    def `500`: URL = URL(s"$base/exo/v5/jCg6DmGGXt_OVyp5ofQHPw.ttf")
    def `500italic`: URL = URL(s"$base/exo/v5/lo5eTdCNJZQVN08p8RnzAQ.ttf")
    def `600`: URL = URL(s"$base/exo/v5/q_SG5kXUmOcIvFpgtdZnlw.ttf")
    def `600italic`: URL = URL(s"$base/exo/v5/0cExa8K_pxS2lTuMr68XUA.ttf")
    def `700`: URL = URL(s"$base/exo/v5/3_jwsL4v9uHjl5Q37G57mw.ttf")
    def `700italic`: URL = URL(s"$base/exo/v5/0me55yJIxd5vyQ9bF7SsiA.ttf")
    def `800`: URL = URL(s"$base/exo/v5/yLPuxBuV0lzqibRJyooOJg.ttf")
    def `800italic`: URL = URL(s"$base/exo/v5/n3LejeKVj_8gtZq5fIgNYw.ttf")
    def `900`: URL = URL(s"$base/exo/v5/97d0nd6Yv4-SA_X92xAuZA.ttf")
    def `900italic`: URL = URL(s"$base/exo/v5/JHTkQVhzyLtkY13Ye95TJQ.ttf")
  }
  object `Exo 2` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `100`: URL = URL(s"$base/exo2/v3/oVOtQy53isv97g4UhBUDqg.ttf")
    def `100italic`: URL = URL(s"$base/exo2/v3/LNYVgsJcaCxoKFHmd4AZcg.ttf")
    def `200`: URL = URL(s"$base/exo2/v3/qa-Ci2pBwJdCxciE1ErifQ.ttf")
    def `200italic`: URL = URL(s"$base/exo2/v3/DCrVxDVvS69n50O-5erZVvesZW2xOQ-xsNqO47m55DA.ttf")
    def `300`: URL = URL(s"$base/exo2/v3/nLUBdz_lHHoVIPor05Byhw.ttf")
    def `300italic`: URL = URL(s"$base/exo2/v3/iSy9VTeUTiqiurQg2ywtu_esZW2xOQ-xsNqO47m55DA.ttf")
    def `regular`: URL = URL(s"$base/exo2/v3/Pf_kZuIH5c5WKVkQUaeSWQ.ttf")
    def `italic`: URL = URL(s"$base/exo2/v3/xxA5ZscX9sTU6U0lZJUlYA.ttf")
    def `500`: URL = URL(s"$base/exo2/v3/oM0rzUuPqVJpW-VEIpuW5w.ttf")
    def `500italic`: URL = URL(s"$base/exo2/v3/amzRVCB-gipwdihZZ2LtT_esZW2xOQ-xsNqO47m55DA.ttf")
    def `600`: URL = URL(s"$base/exo2/v3/YnSn3HsyvyI1feGSdRMYqA.ttf")
    def `600italic`: URL = URL(s"$base/exo2/v3/Vmo58BiptGwfVFb0teU5gPesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/exo2/v3/2DiK4XkdTckfTk6we73-bQ.ttf")
    def `700italic`: URL = URL(s"$base/exo2/v3/Sdo-zW-4_--pDkTg6bYrY_esZW2xOQ-xsNqO47m55DA.ttf")
    def `800`: URL = URL(s"$base/exo2/v3/IVYl_7dJruOg8zKRpC8Hrw.ttf")
    def `800italic`: URL = URL(s"$base/exo2/v3/p0TA6KeOz1o4rySEbvUxI_esZW2xOQ-xsNqO47m55DA.ttf")
    def `900`: URL = URL(s"$base/exo2/v3/e8csG8Wnu87AF6uCndkFRQ.ttf")
    def `900italic`: URL = URL(s"$base/exo2/v3/KPhsGCoT2-7Uj6pMlRscH_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Expletus Sans` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/expletussans/v9/gegTSDBDs5le3g6uxU1ZsX8f0n03UdmQgF_CLvNR2vg.ttf")
    def `italic`: URL = URL(s"$base/expletussans/v9/Y-erXmY0b6DU_i2Qu0hTJj4G9C9ttb0Oz5Cvf0qOitE.ttf")
    def `500`: URL = URL(s"$base/expletussans/v9/cl6rhMY77Ilk8lB_uYRRwAqQmZ7VjhwksfpNVG0pqGc.ttf")
    def `500italic`: URL = URL(s"$base/expletussans/v9/sRBNtc46w65uJE451UYmW87DCVO6wo6i5LKIyZDzK40.ttf")
    def `600`: URL = URL(s"$base/expletussans/v9/cl6rhMY77Ilk8lB_uYRRwCvj1tU7IJMS3CS9kCx2B3U.ttf")
    def `600italic`: URL = URL(s"$base/expletussans/v9/sRBNtc46w65uJE451UYmW8yKH23ZS6zCKOFHG0e_4JE.ttf")
    def `700`: URL = URL(s"$base/expletussans/v9/cl6rhMY77Ilk8lB_uYRRwFCbmAUID8LN-q3pJpOk3Ys.ttf")
    def `700italic`: URL = URL(s"$base/expletussans/v9/sRBNtc46w65uJE451UYmW5F66r9C4AnxxlBlGd7xY4g.ttf")
  }
  object `Fanwood Text` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/fanwoodtext/v6/hDNDHUlsSb8bgnEmDp4T_i3USBnSvpkopQaUR-2r7iU.ttf")
    def `italic`: URL = URL(s"$base/fanwoodtext/v6/0J3SBbkMZqBV-3iGxs5E9_MZXuCXbOrAvx5R0IT5Oyo.ttf")
  }
  object `Farsan` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "gujarati", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/farsan/v2/Hdf9Y76SQ6e1X0Nqk3rHtw.ttf")
  }
  object `Fascinate` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/fascinate/v5/ZE0637WWkBPKt1AmFaqD3Q.ttf")
  }
  object `Fascinate Inline` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/fascinateinline/v6/lRguYfMfWArflkm5aOQ5QJmp8DTZ6iHear7UV05iykg.ttf")
  }
  object `Faster One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/fasterone/v7/H4ciBXCHmdfClFb-vWhfyLs.ttf")
  }
  object `Fasthand` {
    val category: String = "serif"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/fasthand/v7/6XAagHH_KmpZL67wTvsETQ.ttf")
  }
  object `Fauna One` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/faunaone/v4/8kL-wpAPofcAMELI_5NRnQ.ttf")
  }
  object `Faustina` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/faustina/v1/VG2SxiuKreAgH5lXZ5wbng.ttf")
    def `italic`: URL = URL(s"$base/faustina/v1/JxwP25AedFpQZdkRJXn_5_esZW2xOQ-xsNqO47m55DA.ttf")
    def `500`: URL = URL(s"$base/faustina/v1/DMeEDU8yYDdzN-7RbPNe8KCWcynf_cDxXwCLxiixG1c.ttf")
    def `500italic`: URL = URL(s"$base/faustina/v1/P6ASjT1goNMRHifKhq6WRZp-63r6doWhTEbsfBIRJ7A.ttf")
    def `600`: URL = URL(s"$base/faustina/v1/YOr4BI3KhIzqwTG7vH0SM6CWcynf_cDxXwCLxiixG1c.ttf")
    def `600italic`: URL = URL(s"$base/faustina/v1/OJMzHMQmadDP2rMiZVbZd_pTEJqju4Hz1txDWij77d4.ttf")
    def `700`: URL = URL(s"$base/faustina/v1/fO-A_KFKgRicxL_4JD_smaCWcynf_cDxXwCLxiixG1c.ttf")
    def `700italic`: URL = URL(s"$base/faustina/v1/XGqbj0LfEd8UkIzdKBNuggJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Federant` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/federant/v8/tddZFSiGvxICNOGra0i5aA.ttf")
  }
  object `Federo` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/federo/v8/JPhe1S2tujeyaR79gXBLeQ.ttf")
  }
  object `Felipa` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/felipa/v4/SeyfyFZY7abAQXGrOIYnYg.ttf")
  }
  object `Fenix` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/fenix/v4/Ak8wR3VSlAN7VN_eMeJj7Q.ttf")
  }
  object `Finger Paint` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/fingerpaint/v6/m_ZRbiY-aPb13R3DWPBGXy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Fira Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "cyrillic")
    def `regular`: URL = URL(s"$base/firamono/v5/WQOm1D4RO-yvA9q9trJc8g.ttf")
    def `500`: URL = URL(s"$base/firamono/v5/PJ4zAY1ucu5ib6LzyvHMkS3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/firamono/v5/l24Wph3FsyKAbJ8dfExTZy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Fira Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `100`: URL = URL(s"$base/firasans/v7/8lKWk2lAb6-y9gc_GLDdPKCWcynf_cDxXwCLxiixG1c.ttf")
    def `100italic`: URL = URL(s"$base/firasans/v7/fmobwZujc_UI4huzQvESm4AWxXGWZ3yJw6KhWS7MxOk.ttf")
    def `200`: URL = URL(s"$base/firasans/v7/H2QtVYRshA1CFy63P7ykZy3USBnSvpkopQaUR-2r7iU.ttf")
    def `200italic`: URL = URL(s"$base/firasans/v7/6s0YCA9oCTF6hM60YM-qTUnzyIngrzGjGh22wPb6cGM.ttf")
    def `300`: URL = URL(s"$base/firasans/v7/VTBnrK42EiOBncVyQXZ7jy3USBnSvpkopQaUR-2r7iU.ttf")
    def `300italic`: URL = URL(s"$base/firasans/v7/6s0YCA9oCTF6hM60YM-qTS9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/firasans/v7/nsT0isDy56OkSX99sFQbXw.ttf")
    def `italic`: URL = URL(s"$base/firasans/v7/cPT_2ddmoxsUuMtQqa8zGqCWcynf_cDxXwCLxiixG1c.ttf")
    def `500`: URL = URL(s"$base/firasans/v7/zM2u8V3CuPVwAAXFQcDi4C3USBnSvpkopQaUR-2r7iU.ttf")
    def `500italic`: URL = URL(s"$base/firasans/v7/6s0YCA9oCTF6hM60YM-qTcCNfqCYlB_eIx7H1TVXe60.ttf")
    def `600`: URL = URL(s"$base/firasans/v7/TPhEsJuyxIEzWtby22btfi3USBnSvpkopQaUR-2r7iU.ttf")
    def `600italic`: URL = URL(s"$base/firasans/v7/6s0YCA9oCTF6hM60YM-qTZZ7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `700`: URL = URL(s"$base/firasans/v7/DugPdSljmOTocZOR2CItOi3USBnSvpkopQaUR-2r7iU.ttf")
    def `700italic`: URL = URL(s"$base/firasans/v7/6s0YCA9oCTF6hM60YM-qTXe1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `800`: URL = URL(s"$base/firasans/v7/htOw9f-chtELyJuFCkCrFi3USBnSvpkopQaUR-2r7iU.ttf")
    def `800italic`: URL = URL(s"$base/firasans/v7/6s0YCA9oCTF6hM60YM-qTQ89PwPrYLaRFJ-HNCU9NbA.ttf")
    def `900`: URL = URL(s"$base/firasans/v7/rowJfijyp23uW9P2J-sluC3USBnSvpkopQaUR-2r7iU.ttf")
    def `900italic`: URL = URL(s"$base/firasans/v7/6s0YCA9oCTF6hM60YM-qTSenaqEuufTBk9XMKnKmgDA.ttf")
  }
  object `Fira Sans Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `100`: URL = URL(s"$base/firasanscondensed/v1/-hkH0zXsjNm-yd0g99LvtmzsEJYDLiwza6ZHrdqhthQ.ttf")
    def `100italic`: URL = URL(s"$base/firasanscondensed/v1/Nqqv1KfmeTlTML-ky7aaRPKr3wa5Ugsm4QGD8HSjBf8.ttf")
    def `200`: URL = URL(s"$base/firasanscondensed/v1/k1srRZ14gKpu4XGd0R993IBfX0yoOQz7y6Fa57EWAgY.ttf")
    def `200italic`: URL = URL(s"$base/firasanscondensed/v1/Z87ZCYzj43dcQd7C-kCjDzTCSvnRzshTGhbaUNxVLsY.ttf")
    def `300`: URL = URL(s"$base/firasanscondensed/v1/k1srRZ14gKpu4XGd0R993EMwSSh38KQVJx4ABtsZTnA.ttf")
    def `300italic`: URL = URL(s"$base/firasanscondensed/v1/Z87ZCYzj43dcQd7C-kCjD4_LkTZ_uhAwfmGJ084hlvM.ttf")
    def `regular`: URL = URL(s"$base/firasanscondensed/v1/HQGj1o4-qj8agzakWWMQw0b2huS6PSilRpwXI3qYZmg.ttf")
    def `italic`: URL = URL(s"$base/firasanscondensed/v1/-hkH0zXsjNm-yd0g99Lvtv745YdnE8ZqDtluSBzScUA.ttf")
    def `500`: URL = URL(s"$base/firasanscondensed/v1/k1srRZ14gKpu4XGd0R993OsjvTPWUq6WFqixIyn02S8.ttf")
    def `500italic`: URL = URL(s"$base/firasanscondensed/v1/Z87ZCYzj43dcQd7C-kCjD4BZvKPjZWiSZqpadd3c-cI.ttf")
    def `600`: URL = URL(s"$base/firasanscondensed/v1/k1srRZ14gKpu4XGd0R993HI2_Em5SxSZLj3SINQVfR0.ttf")
    def `600italic`: URL = URL(s"$base/firasanscondensed/v1/Z87ZCYzj43dcQd7C-kCjD5AgRolq0CFuJyGMzcpUuqI.ttf")
    def `700`: URL = URL(s"$base/firasanscondensed/v1/k1srRZ14gKpu4XGd0R993BEM87DM3yorPOrvA-vB930.ttf")
    def `700italic`: URL = URL(s"$base/firasanscondensed/v1/Z87ZCYzj43dcQd7C-kCjDzkJmEiMQ4xM-o8FMi_9og4.ttf")
    def `800`: URL = URL(s"$base/firasanscondensed/v1/k1srRZ14gKpu4XGd0R993IakE3OFfI2LZ4c6GPO8Mzs.ttf")
    def `800italic`: URL = URL(s"$base/firasanscondensed/v1/Z87ZCYzj43dcQd7C-kCjD07QUKmu2W_Ow4yNN8hZ1i8.ttf")
    def `900`: URL = URL(s"$base/firasanscondensed/v1/k1srRZ14gKpu4XGd0R993BL2AAruu1GYH8xAyPJJAg8.ttf")
    def `900italic`: URL = URL(s"$base/firasanscondensed/v1/Z87ZCYzj43dcQd7C-kCjD8mJu-lqHNyZBDoYLJNH3Ks.ttf")
  }
  object `Fira Sans Extra Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `100`: URL = URL(s"$base/firasansextracondensed/v1/_dPmaUiuUAWmL0ibePdArgFORyOzJNaQMfz6m4ejZbGglnMp3_3A8V8Ai8YosRtX.ttf")
    def `100italic`: URL = URL(s"$base/firasansextracondensed/v1/G8VKhLxlTd0YOlG3i1R8CfHXjqTqiXVW6z8kDssMYPCAFsVxlmd8icOioVkuzMTp.ttf")
    def `200`: URL = URL(s"$base/firasansextracondensed/v1/34whiWDL4CxC1laOcj7OwW_7IC3ILXfeIVwvfWGu4Sgt1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `200italic`: URL = URL(s"$base/firasansextracondensed/v1/iGnuurQ1EqiOs_hlr82MCvHXjqTqiXVW6z8kDssMYPBJ88iJ4K8xoxodtsD2-nBj.ttf")
    def `300`: URL = URL(s"$base/firasansextracondensed/v1/34whiWDL4CxC1laOcj7OwW7O05EUNkkL_mPtCuekiV0t1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `300italic`: URL = URL(s"$base/firasansextracondensed/v1/iGnuurQ1EqiOs_hlr82MCvHXjqTqiXVW6z8kDssMYPAvflpT0sW34iOPRrF6N6tI.ttf")
    def `regular`: URL = URL(s"$base/firasansextracondensed/v1/wg_5XrW_o1_ZfuCbAkBfGRreEc6WSk_gssVJg3w2ARQ.ttf")
    def `italic`: URL = URL(s"$base/firasansextracondensed/v1/_dPmaUiuUAWmL0ibePdArnKUexidEaHsf8DLYXbriUSglnMp3_3A8V8Ai8YosRtX.ttf")
    def `500`: URL = URL(s"$base/firasansextracondensed/v1/34whiWDL4CxC1laOcj7Owdd0GPYAHEVh0EvoffkRAuMt1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `500italic`: URL = URL(s"$base/firasansextracondensed/v1/iGnuurQ1EqiOs_hlr82MCvHXjqTqiXVW6z8kDssMYPDAjX6gmJQf3iMex9U1V3ut.ttf")
    def `600`: URL = URL(s"$base/firasansextracondensed/v1/34whiWDL4CxC1laOcj7OwW8v1dGG_WArVpDmblm5TDot1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `600italic`: URL = URL(s"$base/firasansextracondensed/v1/iGnuurQ1EqiOs_hlr82MCvHXjqTqiXVW6z8kDssMYPCWe8ZvgY99AY9ijXVw80mY.ttf")
    def `700`: URL = URL(s"$base/firasansextracondensed/v1/34whiWDL4CxC1laOcj7OwdEjTMY3GGLBv_AxlS3Ww6ct1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `700italic`: URL = URL(s"$base/firasansextracondensed/v1/iGnuurQ1EqiOs_hlr82MCvHXjqTqiXVW6z8kDssMYPB3tT3e-lZe80aROzSyUO11.ttf")
    def `800`: URL = URL(s"$base/firasansextracondensed/v1/34whiWDL4CxC1laOcj7OwZZWqFq9WyGGQ2ef9bXDKiQt1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `800italic`: URL = URL(s"$base/firasansextracondensed/v1/iGnuurQ1EqiOs_hlr82MCvHXjqTqiXVW6z8kDssMYPAPPT8D62C2kRSfhzQlPTWw.ttf")
    def `900`: URL = URL(s"$base/firasansextracondensed/v1/34whiWDL4CxC1laOcj7OwRPaRBEe7-4iQsBL_zD1FQ8t1EgZ0r6ZKKUGlEftq-4l.ttf")
    def `900italic`: URL = URL(s"$base/firasansextracondensed/v1/iGnuurQ1EqiOs_hlr82MCvHXjqTqiXVW6z8kDssMYPAnp2qhLrn0wZPVzCpypoAw.ttf")
  }
  object `Fjalla One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/fjallaone/v4/3b7vWCfOZsU53vMa8LWsf_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Fjord One` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/fjordone/v5/R_YHK8au2uFPw5tNu5N7zw.ttf")
  }
  object `Flamenco` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `300`: URL = URL(s"$base/flamenco/v7/x9iI5CogvuZVCGoRHwXuo6CWcynf_cDxXwCLxiixG1c.ttf")
    def `regular`: URL = URL(s"$base/flamenco/v7/HC0ugfLLgt26I5_BWD1PZA.ttf")
  }
  object `Flavors` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/flavors/v5/SPJi5QclATvon8ExcKGRvQ.ttf")
  }
  object `Fondamento` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/fondamento/v6/6LWXcjT1B7bnWluAOSNfMPesZW2xOQ-xsNqO47m55DA.ttf")
    def `italic`: URL = URL(s"$base/fondamento/v6/y6TmwhSbZ8rYq7OTFyo7OS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Fontdiner Swanky` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/fontdinerswanky/v7/8_GxIO5ixMtn5P6COsF3TlBjMPLzPAFJwRBn-s1U7kA.ttf")
  }
  object `Forum` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/forum/v7/MZUpsq1VfLrqv8eSDcbrrQ.ttf")
  }
  object `Francois One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/francoisone/v10/bYbkq2nU2TSx4SwFbz5sCC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Frank Ruhl Libre` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "hebrew", "latin")
    def `300`: URL = URL(s"$base/frankruhllibre/v2/y8NWif61iD8Hg8bGAmxFPOo9jvbqtCEVUIntIHarXsc.ttf")
    def `regular`: URL = URL(s"$base/frankruhllibre/v2/yDLloNqBpFmakCImLv4OJkfFI6QBbouvcOFcz81E3Ek.ttf")
    def `500`: URL = URL(s"$base/frankruhllibre/v2/y8NWif61iD8Hg8bGAmxFPC-WNtISbX_UO2d0wZPgXtk.ttf")
    def `700`: URL = URL(s"$base/frankruhllibre/v2/y8NWif61iD8Hg8bGAmxFPDPYiZEMiRRbPdIFMoTwDbo.ttf")
    def `900`: URL = URL(s"$base/frankruhllibre/v2/y8NWif61iD8Hg8bGAmxFPNRZIVFRjDx-6MOpcoWbVhA.ttf")
  }
  object `Freckle Face` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/freckleface/v4/7-B8j9BPJgazdHIGqPNv8y3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Fredericka the Great` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/frederickathegreat/v5/7Es8Lxoku-e5eOZWpxw18nrnet6gXN1McwdQxS1dVrI.ttf")
  }
  object `Fredoka One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/fredokaone/v4/QKfwXi-z-KtJAlnO2ethYqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Freehand` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/freehand/v8/uEBQxvA0lnn_BrD6krlxMw.ttf")
  }
  object `Fresca` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/fresca/v5/2q7Qm9sCo1tWvVgSDVWNIw.ttf")
  }
  object `Frijole` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/frijole/v5/L2MfZse-2gCascuD-nLhWg.ttf")
  }
  object `Fruktur` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/fruktur/v9/PnQvfEi1LssAvhJsCwH__w.ttf")
  }
  object `Fugaz One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/fugazone/v6/5tteVDCwxsr8-5RuSiRWOw.ttf")
  }
  object `GFS Didot` {
    val category: String = "serif"
    val subsets: Set[String] = Set("greek")
    def `regular`: URL = URL(s"$base/gfsdidot/v6/jQKxZy2RU-h9tkPZcRVluA.ttf")
  }
  object `GFS Neohellenic` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("greek")
    def `regular`: URL = URL(s"$base/gfsneohellenic/v7/B4xRqbn-tANVqVgamMsSDiayCZa0z7CpFzlkqoCHztc.ttf")
    def `italic`: URL = URL(s"$base/gfsneohellenic/v7/KnaWrO4awITAqigQIIYXKkCTdomiyJpIzPbEbIES3rU.ttf")
    def `700`: URL = URL(s"$base/gfsneohellenic/v7/7HwjPQa7qNiOsnUce2h4448_BwCLZY3eDSV6kppAwI8.ttf")
    def `700italic`: URL = URL(s"$base/gfsneohellenic/v7/FwWjoX6XqT-szJFyqsu_GYFF0fM4h-krcpQk7emtCpE.ttf")
  }
  object `Gabriela` {
    val category: String = "serif"
    val subsets: Set[String] = Set("cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/gabriela/v5/B-2ZfbAO3HDrxqV6lR5tdA.ttf")
  }
  object `Gafata` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/gafata/v5/aTFqlki_3Dc3geo-FxHTvQ.ttf")
  }
  object `Galada` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin", "bengali")
    def `regular`: URL = URL(s"$base/galada/v2/xGkllHQb8OOCv9VJ6IObSA.ttf")
  }
  object `Galdeano` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/galdeano/v6/ZKFMQI6HxEG1jOT0UGSZUg.ttf")
  }
  object `Galindo` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/galindo/v4/2lafAS_ZEfB33OJryhXDUg.ttf")
  }
  object `Gentium Basic` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/gentiumbasic/v8/KCktj43blvLkhOTolFn-MYtBLojGU5Qdl8-5NL4v70w.ttf")
    def `italic`: URL = URL(s"$base/gentiumbasic/v8/qoFz4NSMaYC2UmsMAG3lyTj3mvXnCeAk09uTtmkJGRc.ttf")
    def `700`: URL = URL(s"$base/gentiumbasic/v8/2qL6yulgGf0wwgOp-UqGyLNuTeOOLg3nUymsEEGmdO0.ttf")
    def `700italic`: URL = URL(s"$base/gentiumbasic/v8/8N9-c_aQDJ8LbI1NGVMrwtswO1vWwP9exiF8s0wqW10.ttf")
  }
  object `Gentium Book Basic` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/gentiumbookbasic/v7/IRFxB2matTxrjZt6a3FUnrWDjKAyldGEr6eEi2MBNeY.ttf")
    def `italic`: URL = URL(s"$base/gentiumbookbasic/v7/qHqW2lwKO8-uTfIkh8FsUfXfjMwrYnmPVsQth2IcAPY.ttf")
    def `700`: URL = URL(s"$base/gentiumbookbasic/v7/T2vUYmWzlqUtgLYdlemGnaWESMHIjnSjm9UUxYtEOko.ttf")
    def `700italic`: URL = URL(s"$base/gentiumbookbasic/v7/632u7TMIoFDWQYUaHFUp5PA2A9KyRZEkn4TZVuhsWRM.ttf")
  }
  object `Geo` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/geo/v8/mJuJYk5Pww84B4uHAQ1XaA.ttf")
    def `italic`: URL = URL(s"$base/geo/v8/8_r1wToF7nPdDuX1qxel6Q.ttf")
  }
  object `Geostar` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/geostar/v6/A8WQbhQbpYx3GWWaShJ9GA.ttf")
  }
  object `Geostar Fill` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/geostarfill/v6/Y5ovXPPOHYTfQzK2aM-hui3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Germania One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/germaniaone/v4/3_6AyUql_-FbDi1e68jHdC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Gidugu` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/gidugu/v3/Ey6Eq3hrT6MM58iFItFcgw.ttf")
  }
  object `Gilda Display` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/gildadisplay/v4/8yAVUZLLZ3wb7dSsjix0CADHmap7fRWINAsw8-RaxNg.ttf")
  }
  object `Give You Glory` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/giveyouglory/v6/DFEWZFgGmfseyIdGRJAxuBwwkpSPZdvjnMtysdqprfI.ttf")
  }
  object `Glass Antiqua` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/glassantiqua/v4/0yLrXKplgdUDIMz5TnCHNODcg5akpSnIcsPhLOFv7l8.ttf")
  }
  object `Glegoo` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/glegoo/v5/2tf-h3n2A_SNYXEO0C8bKw.ttf")
    def `700`: URL = URL(s"$base/glegoo/v5/TlLolbauH0-0Aiz1LUH5og.ttf")
  }
  object `Gloria Hallelujah` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/gloriahallelujah/v8/CA1k7SlXcY5kvI81M_R28Q3RdPdyebSUyJECJouPsvA.ttf")
  }
  object `Goblin One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/goblinone/v6/331XtzoXgpVEvNTVcBJ_C_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Gochi Hand` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/gochihand/v7/KT1-WxgHsittJ34_20IfAPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Gorditas` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/gorditas/v4/uMgZhXUyH6qNGF3QsjQT5Q.ttf")
    def `700`: URL = URL(s"$base/gorditas/v4/6-XCeknmxaon8AUqVkMnHaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Goudy Bookletter 1911` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/goudybookletter1911/v6/l5lwlGTN3pEY5Bf-rQEuIIjNDsyURsIKu4GSfvSE4mA.ttf")
  }
  object `Graduate` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/graduate/v4/JpAmYLHqcIh9_Ff35HHwiA.ttf")
  }
  object `Grand Hotel` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/grandhotel/v4/C_A8HiFZjXPpnMt38XnK7qCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Gravitas One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/gravitasone/v6/nBHdBv6zVNU8MtP6w9FwTS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Great Vibes` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/greatvibes/v4/4Mi5RG_9LjQYrTU55GN_L6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Griffy` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/griffy/v4/vWkyYGBSyE5xjnShNtJtzw.ttf")
  }
  object `Gruppo` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/gruppo/v7/pS_JM0cK_piBZve-lfUq9w.ttf")
  }
  object `Gudea` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/gudea/v4/S-4QqBlkMPiiA3jNeCR5yw.ttf")
    def `italic`: URL = URL(s"$base/gudea/v4/7mNgsGw_vfS-uUgRVXNDSw.ttf")
    def `700`: URL = URL(s"$base/gudea/v4/lsip4aiWhJ9bx172Y9FN_w.ttf")
  }
  object `Gurajada` {
    val category: String = "serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/gurajada/v4/6Adfkl4PCRyq6XTENACEyA.ttf")
  }
  object `Habibi` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/habibi/v5/YYyqXF6pWpL7kmKgS_2iUA.ttf")
  }
  object `Halant` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `300`: URL = URL(s"$base/halant/v3/dM3ItAOWNNod_Cf3MnLlEg.ttf")
    def `regular`: URL = URL(s"$base/halant/v3/rEs7Jk3SVyt3cTx6DoTu1w.ttf")
    def `500`: URL = URL(s"$base/halant/v3/tlsNj3K-hJKtiirTDtUbkQ.ttf")
    def `600`: URL = URL(s"$base/halant/v3/zNR2WvI_V8o652vIZp3X4Q.ttf")
    def `700`: URL = URL(s"$base/halant/v3/D9FN7OH89AuCmZDLHbPQfA.ttf")
  }
  object `Hammersmith One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/hammersmithone/v7/FWNn6ITYqL6or7ZTmBxRhjjVlsJB_M_Q_LtZxsoxvlw.ttf")
  }
  object `Hanalei` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/hanalei/v6/Sx8vVMBnXSQyK6Cn0CBJ3A.ttf")
  }
  object `Hanalei Fill` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/hanaleifill/v5/5uPeWLnaDdtm4UBG26Ds6C3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Handlee` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/handlee/v5/6OfkXkyC0E5NZN80ED8u3A.ttf")
  }
  object `Hanuman` {
    val category: String = "serif"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/hanuman/v10/hRhwOGGmElJSl6KSPvEnOQ.ttf")
    def `700`: URL = URL(s"$base/hanuman/v10/lzzXZ2l84x88giDrbfq76vesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Happy Monkey` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/happymonkey/v5/c2o0ps8nkBmaOYctqBq1rS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Harmattan` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin", "arabic")
    def `regular`: URL = URL(s"$base/harmattan/v1/xNM1nDKzsLfoCLQtMRztGA.ttf")
  }
  object `Headland One` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/headlandone/v4/iGmBeOvQGfq9DSbjJ8jDVy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Heebo` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("hebrew", "latin")
    def `100`: URL = URL(s"$base/heebo/v2/SoQODIucfpkiveZloUR6ag.ttf")
    def `300`: URL = URL(s"$base/heebo/v2/dg5T18yyjkKiU_9mmcbDSQ.ttf")
    def `regular`: URL = URL(s"$base/heebo/v2/nyHCGMPliplPNqpssbDSIA.ttf")
    def `500`: URL = URL(s"$base/heebo/v2/jDb70ZCwdD6JnmQU62ZQZA.ttf")
    def `700`: URL = URL(s"$base/heebo/v2/NsBYEn6oWei8pPqytA07yA.ttf")
    def `800`: URL = URL(s"$base/heebo/v2/h4CV2Qq56LKIinGGOStvsw.ttf")
    def `900`: URL = URL(s"$base/heebo/v2/uDfzHw3R0Bfa6HyIIcj-ow.ttf")
  }
  object `Henny Penny` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/hennypenny/v4/XRgo3ogXyi3tpsFfjImRF6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Herr Von Muellerhoff` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/herrvonmuellerhoff/v6/mmy24EUmk4tjm4gAEjUd7NLGIYrUsBdh-JWHYgiDiMU.ttf")
  }
  object `Hind` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `300`: URL = URL(s"$base/hind/v7/qa346Adgv9kPDXoD1my4kA.ttf")
    def `regular`: URL = URL(s"$base/hind/v7/mktFHh5Z5P9YjGKSslSUtA.ttf")
    def `500`: URL = URL(s"$base/hind/v7/2cs8RCVcYtiv4iNDH1UsQQ.ttf")
    def `600`: URL = URL(s"$base/hind/v7/TUKUmFMXSoxloBP1ni08oA.ttf")
    def `700`: URL = URL(s"$base/hind/v7/cXJJavLdUbCfjxlsA6DqTw.ttf")
  }
  object `Hind Guntur` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "telugu", "latin")
    def `300`: URL = URL(s"$base/hindguntur/v2/Szg33M7ab5MTWe-PWAcNAi9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/hindguntur/v2/MXz-KyAeVZstlFz6v-5SC6CWcynf_cDxXwCLxiixG1c.ttf")
    def `500`: URL = URL(s"$base/hindguntur/v2/Szg33M7ab5MTWe-PWAcNAsCNfqCYlB_eIx7H1TVXe60.ttf")
    def `600`: URL = URL(s"$base/hindguntur/v2/Szg33M7ab5MTWe-PWAcNApZ7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `700`: URL = URL(s"$base/hindguntur/v2/Szg33M7ab5MTWe-PWAcNAne1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Hind Madurai` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "tamil")
    def `300`: URL = URL(s"$base/hindmadurai/v2/sdSJTZLdRXJhVTP92m2S66cQoVhARpoaILP7amxE_8g.ttf")
    def `regular`: URL = URL(s"$base/hindmadurai/v2/pJpl47LatORZNWf8rgdiyS3USBnSvpkopQaUR-2r7iU.ttf")
    def `500`: URL = URL(s"$base/hindmadurai/v2/sdSJTZLdRXJhVTP92m2S65MQuUSAwdHsY8ov_6tk1oA.ttf")
    def `600`: URL = URL(s"$base/hindmadurai/v2/sdSJTZLdRXJhVTP92m2S62v8CylhIUtwUiYO7Z2wXbE.ttf")
    def `700`: URL = URL(s"$base/hindmadurai/v2/sdSJTZLdRXJhVTP92m2S60D2ttfZwueP-QU272T9-k4.ttf")
  }
  object `Hind Siliguri` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "bengali")
    def `300`: URL = URL(s"$base/hindsiliguri/v3/fBpmjMpv5Rh6S25yVfWJnzoJ52uD-1fmXmi8u0n_zsc.ttf")
    def `regular`: URL = URL(s"$base/hindsiliguri/v3/f2eEi2pbIa8eBfNwpUl0Am_MnNA9OgK8I1F23mNWOpE.ttf")
    def `500`: URL = URL(s"$base/hindsiliguri/v3/fBpmjMpv5Rh6S25yVfWJn__2zpxNHQ3utWt_82o9dAo.ttf")
    def `600`: URL = URL(s"$base/hindsiliguri/v3/fBpmjMpv5Rh6S25yVfWJn-x91FDzFvnud68bXrNkpDA.ttf")
    def `700`: URL = URL(s"$base/hindsiliguri/v3/fBpmjMpv5Rh6S25yVfWJn6iiXuG_rGcOxkuidirlnJE.ttf")
  }
  object `Hind Vadodara` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "gujarati", "latin")
    def `300`: URL = URL(s"$base/hindvadodara/v3/KrZ6f_YevRawHvh0qDBkTbDwfZ__Dotj_J8NiWv76DQ.ttf")
    def `regular`: URL = URL(s"$base/hindvadodara/v3/9c6KKeibr6NtFqknnNxZB-Dcg5akpSnIcsPhLOFv7l8.ttf")
    def `500`: URL = URL(s"$base/hindvadodara/v3/KrZ6f_YevRawHvh0qDBkTZzEKvFIU9WyojfbAkhDb6c.ttf")
    def `600`: URL = URL(s"$base/hindvadodara/v3/KrZ6f_YevRawHvh0qDBkTfgXs2VXrZsRiQ1c96pXZKI.ttf")
    def `700`: URL = URL(s"$base/hindvadodara/v3/KrZ6f_YevRawHvh0qDBkTYGjoH95IEFGA7BjhXnx_eg.ttf")
  }
  object `Holtwood One SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/holtwoodonesc/v7/sToOq3cIxbfnhbEkgYNuBbAgSRh1LpJXlLfl8IbsmHg.ttf")
  }
  object `Homemade Apple` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/homemadeapple/v7/yg3UMEsefgZ8IHz_ryz86BiPOmFWYV1WlrJkRafc4c0.ttf")
  }
  object `Homenaje` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/homenaje/v6/v0YBU0iBRrGdVjDNQILxtA.ttf")
  }
  object `IM Fell DW Pica` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/imfelldwpica/v6/W81bfaWiUicLSPbJhW-ATsA5qm663gJGVdtpamafG5A.ttf")
    def `italic`: URL = URL(s"$base/imfelldwpica/v6/alQJ8SK5aSOZVaelYoyT4PL2asmh5DlYQYCosKo6yQs.ttf")
  }
  object `IM Fell DW Pica SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/imfelldwpicasc/v6/xBKKJV4z2KsrtQnmjGO17JZ9RBdEL0H9o5qzT1Rtof4.ttf")
  }
  object `IM Fell Double Pica` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/imfelldoublepica/v6/yN1wY_01BkQnO0LYAhXdUol14jEdVOhEmvtCMCVwYak.ttf")
    def `italic`: URL = URL(s"$base/imfelldoublepica/v6/64odUh2hAw8D9dkFKTlWYq0AWwkgdQfsRHec8TYi4mI.ttf")
  }
  object `IM Fell Double Pica SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/imfelldoublepicasc/v6/jkrUtrLFpMw4ZazhfkKsGwc4LoC4OJUqLw9omnT3VOU.ttf")
  }
  object `IM Fell English` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/imfellenglish/v6/xwIisCqGFi8pff-oa9uSVHGNmx1fDm-u2eBJHQkdrmk.ttf")
    def `italic`: URL = URL(s"$base/imfellenglish/v6/Z3cnIAI_L3XTRfz4JuZKbuewladMPCWTthtMv9cPS-c.ttf")
  }
  object `IM Fell English SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/imfellenglishsc/v6/h3Tn6yWfw4b5qaLD1RWvz5ATixNthKRRR1XVH3rJNiw.ttf")
  }
  object `IM Fell French Canon` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/imfellfrenchcanon/v6/iKB0WL1BagSpNPz3NLMdsJ3V2FNpBrlLSvqUnERhBP8.ttf")
    def `italic`: URL = URL(s"$base/imfellfrenchcanon/v6/owCuNQkLLFW7TBBPJbMnhRa-QL94KdW80H29tcyld2A.ttf")
  }
  object `IM Fell French Canon SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/imfellfrenchcanonsc/v6/kA3bS19-tQbeT_iG32EZmaiyyzHwYrAbmNulTz423iM.ttf")
  }
  object `IM Fell Great Primer` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/imfellgreatprimer/v6/AL8ALGNthei20f9Cu3e93rgeX3ROgtTz44CitKAxzKI.ttf")
    def `italic`: URL = URL(s"$base/imfellgreatprimer/v6/1a-artkXMVg682r7TTxVY1_YG2SFv8Ma7CxRl1S3o7g.ttf")
  }
  object `IM Fell Great Primer SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/imfellgreatprimersc/v6/A313vRj97hMMGFjt6rgSJtRg-ciw1Y27JeXb2Zv4lZQ.ttf")
  }
  object `Iceberg` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/iceberg/v4/p2XVm4M-N0AOEEOymFKC5w.ttf")
  }
  object `Iceland` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/iceland/v5/kq3uTMGgvzWGNi39B_WxGA.ttf")
  }
  object `Imprima` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/imprima/v4/eRjquWLjwLGnTEhLH7u3kA.ttf")
  }
  object `Inconsolata` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/inconsolata/v15/7bMKuoy6Nh0ft0SHnIGMuaCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/inconsolata/v15/AIed271kqQlcIRSOnQH0yXe1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Inder` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/inder/v5/C38TwecLTfKxIHDc_Adcrw.ttf")
  }
  object `Indie Flower` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/indieflower/v8/10JVD_humAd5zP2yrFqw6i3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Inika` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/inika/v4/eZCrULQGaIxkrRoGz_DjhQ.ttf")
    def `700`: URL = URL(s"$base/inika/v4/bl3ZoTyrWsFun2zYbsgJrA.ttf")
  }
  object `Inknut Antiqua` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `300`: URL = URL(s"$base/inknutantiqua/v2/CagoW52rBcslcXzHh6tVIg6hmPNSXwHGnJQCeQHKUMo.ttf")
    def `regular`: URL = URL(s"$base/inknutantiqua/v2/VlmmTfOrxr3HfcnhMueX9arFJ4O13IHVxZbM6yoslpo.ttf")
    def `500`: URL = URL(s"$base/inknutantiqua/v2/CagoW52rBcslcXzHh6tVIiYCDvi1XFzRnTV7qUFsNgk.ttf")
    def `600`: URL = URL(s"$base/inknutantiqua/v2/CagoW52rBcslcXzHh6tVIjLEgY6PI0GrY6L00mykcEQ.ttf")
    def `700`: URL = URL(s"$base/inknutantiqua/v2/CagoW52rBcslcXzHh6tVIlRhfXn9P4_QueZ7VkUHUNc.ttf")
    def `800`: URL = URL(s"$base/inknutantiqua/v2/CagoW52rBcslcXzHh6tVInARjXVu2t2krcNTHiCb1qY.ttf")
    def `900`: URL = URL(s"$base/inknutantiqua/v2/CagoW52rBcslcXzHh6tVIrTsNy1JrFNT1qKy8j7W3CU.ttf")
  }
  object `Irish Grover` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/irishgrover/v7/kUp7uUPooL-KsLGzeVJbBC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Istok Web` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/istokweb/v10/RYLSjEXQ0nNtLLc4n7--dQ.ttf")
    def `italic`: URL = URL(s"$base/istokweb/v10/kvcT2SlTjmGbC3YlZxmrl6CWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/istokweb/v10/2koEo4AKFSvK4B52O_Mwai3USBnSvpkopQaUR-2r7iU.ttf")
    def `700italic`: URL = URL(s"$base/istokweb/v10/ycQ3g52ELrh3o_HYCNNUw3e1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Italiana` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/italiana/v5/dt95fkCSTOF-c6QNjwSycA.ttf")
  }
  object `Italianno` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/italianno/v6/HsyHnLpKf8uP7aMpDQHZmg.ttf")
  }
  object `Itim` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `regular`: URL = URL(s"$base/itim/v1/HHV9WK2x5lUkc5bxMXG8Tw.ttf")
  }
  object `Jacques Francois` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/jacquesfrancois/v4/_-0XWPQIW6tOzTHg4KaJ_M13D_4KM32Q4UmTSjpuNGQ.ttf")
  }
  object `Jacques Francois Shadow` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/jacquesfrancoisshadow/v4/V14y0H3vq56fY9SV4OL_FASt0D_oLVawA8L8b9iKjbs.ttf")
  }
  object `Jaldi` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/jaldi/v2/x1vR-bPW9a1EB-BUVqttCw.ttf")
    def `700`: URL = URL(s"$base/jaldi/v2/OIbtgjjEp3aVWtjF6WY8mA.ttf")
  }
  object `Jim Nightshade` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/jimnightshade/v4/_n43lYHXVWNgXegdYRIK9CF1W_bo0EdycfH0kHciIic.ttf")
  }
  object `Jockey One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/jockeyone/v6/cAucnOZLvFo07w2AbufBCfesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Jolly Lodger` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/jollylodger/v4/RX8HnkBgaEKQSHQyP9itiS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Jomhuria` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "arabic")
    def `regular`: URL = URL(s"$base/jomhuria/v2/hrvsccQpBliIgor15WxE6g.ttf")
  }
  object `Josefin Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/josefinsans/v11/q9w3H4aeBxj0hZ8Osfi3d8SVQ0giZ-l_NELu3lgGyYw.ttf")
    def `100italic`: URL = URL(s"$base/josefinsans/v11/s7-P1gqRNRNn-YWdOYnAOXXcj1rQwlNLIS625o-SrL0.ttf")
    def `300`: URL = URL(s"$base/josefinsans/v11/C6HYlRF50SGJq1XyXj04z6cQoVhARpoaILP7amxE_8g.ttf")
    def `300italic`: URL = URL(s"$base/josefinsans/v11/ppse0J9fKSaoxCIIJb33Gyna0FLWfcB-J_SAYmcAXaI.ttf")
    def `regular`: URL = URL(s"$base/josefinsans/v11/xgzbb53t8j-Mo-vYa23n5i3USBnSvpkopQaUR-2r7iU.ttf")
    def `italic`: URL = URL(s"$base/josefinsans/v11/q9w3H4aeBxj0hZ8Osfi3d_MZXuCXbOrAvx5R0IT5Oyo.ttf")
    def `600`: URL = URL(s"$base/josefinsans/v11/C6HYlRF50SGJq1XyXj04z2v8CylhIUtwUiYO7Z2wXbE.ttf")
    def `600italic`: URL = URL(s"$base/josefinsans/v11/ppse0J9fKSaoxCIIJb33G4R-5-urNOGAobhAyctHvW8.ttf")
    def `700`: URL = URL(s"$base/josefinsans/v11/C6HYlRF50SGJq1XyXj04z0D2ttfZwueP-QU272T9-k4.ttf")
    def `700italic`: URL = URL(s"$base/josefinsans/v11/ppse0J9fKSaoxCIIJb33G_As9-1nE9qOqhChW0m4nDE.ttf")
  }
  object `Josefin Slab` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `100`: URL = URL(s"$base/josefinslab/v7/etsUjZYO8lTLU85lDhZwUsSVQ0giZ-l_NELu3lgGyYw.ttf")
    def `100italic`: URL = URL(s"$base/josefinslab/v7/8BjDChqLgBF3RJKfwHIYh3Xcj1rQwlNLIS625o-SrL0.ttf")
    def `300`: URL = URL(s"$base/josefinslab/v7/NbE6ykYuM2IyEwxQxOIi2KcQoVhARpoaILP7amxE_8g.ttf")
    def `300italic`: URL = URL(s"$base/josefinslab/v7/af9sBoKGPbGO0r21xJulyyna0FLWfcB-J_SAYmcAXaI.ttf")
    def `regular`: URL = URL(s"$base/josefinslab/v7/46aYWdgz-1oFX11flmyEfS3USBnSvpkopQaUR-2r7iU.ttf")
    def `italic`: URL = URL(s"$base/josefinslab/v7/etsUjZYO8lTLU85lDhZwUvMZXuCXbOrAvx5R0IT5Oyo.ttf")
    def `600`: URL = URL(s"$base/josefinslab/v7/NbE6ykYuM2IyEwxQxOIi2Gv8CylhIUtwUiYO7Z2wXbE.ttf")
    def `600italic`: URL = URL(s"$base/josefinslab/v7/af9sBoKGPbGO0r21xJuly4R-5-urNOGAobhAyctHvW8.ttf")
    def `700`: URL = URL(s"$base/josefinslab/v7/NbE6ykYuM2IyEwxQxOIi2ED2ttfZwueP-QU272T9-k4.ttf")
    def `700italic`: URL = URL(s"$base/josefinslab/v7/af9sBoKGPbGO0r21xJuly_As9-1nE9qOqhChW0m4nDE.ttf")
  }
  object `Joti One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/jotione/v4/P3r_Th0ESHJdzunsvWgUfQ.ttf")
  }
  object `Judson` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/judson/v9/znM1AAs0eytUaJzf1CrYZQ.ttf")
    def `italic`: URL = URL(s"$base/judson/v9/GVqQW9P52ygW-ySq-CLwAA.ttf")
    def `700`: URL = URL(s"$base/judson/v9/he4a2LwiPJc7r8x0oKCKiA.ttf")
  }
  object `Julee` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/julee/v6/CAib-jsUsSO8SvVRnE9fHA.ttf")
  }
  object `Julius Sans One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/juliussansone/v5/iU65JP9acQHPDLkdalCF7jjVlsJB_M_Q_LtZxsoxvlw.ttf")
  }
  object `Junge` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/junge/v4/j4IXCXtxrw9qIBheercp3A.ttf")
  }
  object `Jura` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `300`: URL = URL(s"$base/jura/v8/Rqx_xy1UnN0C7wD3FUSyPQ.ttf")
    def `regular`: URL = URL(s"$base/jura/v8/YAWMwF3sN0KCbynMq-Yr_Q.ttf")
    def `500`: URL = URL(s"$base/jura/v8/16xhfjHCiaLj3tsqqgmtGg.ttf")
    def `600`: URL = URL(s"$base/jura/v8/iwseduOwJSdY8wQ1Y6CJdA.ttf")
    def `700`: URL = URL(s"$base/jura/v8/k0wz0WR1Y0M_AuROdfv4xQ.ttf")
  }
  object `Just Another Hand` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/justanotherhand/v8/fKV8XYuRNNagXr38eqbRf99BnJIEGrvoojniP57E51c.ttf")
  }
  object `Just Me Again Down Here` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/justmeagaindownhere/v8/sN06iTc9ITubLTgXoG-kc3M9eVLpVTSK6TqZTIgBrWQ.ttf")
  }
  object `Kadwa` {
    val category: String = "serif"
    val subsets: Set[String] = Set("devanagari", "latin")
    def `regular`: URL = URL(s"$base/kadwa/v1/VwEN8oKGqaa0ug9kRpvSSg.ttf")
    def `700`: URL = URL(s"$base/kadwa/v1/NFPZaBfekj_Io-7vUMz4Ww.ttf")
  }
  object `Kalam` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `300`: URL = URL(s"$base/kalam/v7/MgQQlk1SgPEHdlkWMNh7Jg.ttf")
    def `regular`: URL = URL(s"$base/kalam/v7/hNEJkp2K-aql7e5WQish4Q.ttf")
    def `700`: URL = URL(s"$base/kalam/v7/95nLItUGyWtNLZjSckluLQ.ttf")
  }
  object `Kameron` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/kameron/v7/9r8HYhqDSwcq9WMjupL82A.ttf")
    def `700`: URL = URL(s"$base/kameron/v7/rabVVbzlflqvmXJUFlKnu_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Kanit` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `100`: URL = URL(s"$base/kanit/v2/CYl4qOK-NWwZp3iTKW1eIA.ttf")
    def `100italic`: URL = URL(s"$base/kanit/v2/NLNtc56MpXmHl1yOrop8oQ.ttf")
    def `200`: URL = URL(s"$base/kanit/v2/wfLWkj1C4tYl7MoiFWS3bA.ttf")
    def `200italic`: URL = URL(s"$base/kanit/v2/D8gkrAAM2bvNJ-1i4ot-1_esZW2xOQ-xsNqO47m55DA.ttf")
    def `300`: URL = URL(s"$base/kanit/v2/SM5qHynYGdOmMKEwGUFIPA.ttf")
    def `300italic`: URL = URL(s"$base/kanit/v2/IePislKOKy3Bqfpb9V5VM_esZW2xOQ-xsNqO47m55DA.ttf")
    def `regular`: URL = URL(s"$base/kanit/v2/L6VKvM17ZmevDynOiw7H9w.ttf")
    def `italic`: URL = URL(s"$base/kanit/v2/sHLq5U0-T0oSMTnwTKgv-A.ttf")
    def `500`: URL = URL(s"$base/kanit/v2/GxoU_USIJyIy8WIcYSUO2g.ttf")
    def `500italic`: URL = URL(s"$base/kanit/v2/hrCiWCaNv9AaF0mDY1F2zPesZW2xOQ-xsNqO47m55DA.ttf")
    def `600`: URL = URL(s"$base/kanit/v2/n_qoIVxojeQY0D1pvoNDhA.ttf")
    def `600italic`: URL = URL(s"$base/kanit/v2/9BkP85yRDoVayTWQwdGLqPesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/kanit/v2/kEGmYvO8My36j5ILmbUPRg.ttf")
    def `700italic`: URL = URL(s"$base/kanit/v2/WNo3ZZ9xtOZJknNlvHAFWfesZW2xOQ-xsNqO47m55DA.ttf")
    def `800`: URL = URL(s"$base/kanit/v2/YTp-zAuKXxwnA1YnJIF1rg.ttf")
    def `800italic`: URL = URL(s"$base/kanit/v2/qiTGrW5sCa9UQp841fWjc_esZW2xOQ-xsNqO47m55DA.ttf")
    def `900`: URL = URL(s"$base/kanit/v2/1NIEkusi3bG3GgO9Hor3fQ.ttf")
    def `900italic`: URL = URL(s"$base/kanit/v2/ogN5dFD1r4BfxNV4Nb-TXfesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Kantumruy` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("khmer")
    def `300`: URL = URL(s"$base/kantumruy/v3/ERRwQE0WG5uanaZWmOFXNi3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/kantumruy/v3/kQfXNYElQxr5dS8FyjD39Q.ttf")
    def `700`: URL = URL(s"$base/kantumruy/v3/gie_zErpGf_rNzs920C2Ji3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Karla` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/karla/v5/78UgGRwJFkhqaoFimqoKpQ.ttf")
    def `italic`: URL = URL(s"$base/karla/v5/51UBKly9RQOnOkj95ZwEFw.ttf")
    def `700`: URL = URL(s"$base/karla/v5/JS501sZLxZ4zraLQdncOUA.ttf")
    def `700italic`: URL = URL(s"$base/karla/v5/3YDyi09gQjCRh-5-SVhTTvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Karma` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `300`: URL = URL(s"$base/karma/v6/lH6ijJnguWR2Sz7tEl6MQQ.ttf")
    def `regular`: URL = URL(s"$base/karma/v6/wvqTxAGBUrTqU0urTEoPIw.ttf")
    def `500`: URL = URL(s"$base/karma/v6/9YGjxi6Hcvz2Kh-rzO_cAw.ttf")
    def `600`: URL = URL(s"$base/karma/v6/h_CVzXXtqSxjfS2sIwaejA.ttf")
    def `700`: URL = URL(s"$base/karma/v6/smuSM08oApsQPPVYbHd1CA.ttf")
  }
  object `Katibeh` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "arabic")
    def `regular`: URL = URL(s"$base/katibeh/v2/Q-SA43uWR2uu3wBIvedotA.ttf")
  }
  object `Kaushan Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/kaushanscript/v5/qx1LSqts-NtiKcLw4N03IBnpV0hQCek3EmWnCPrvGRM.ttf")
  }
  object `Kavivanar` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin", "tamil")
    def `regular`: URL = URL(s"$base/kavivanar/v2/VLDrdUtF1irKFc8rFWgDaw.ttf")
  }
  object `Kavoon` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/kavoon/v5/382m-6baKXqJFQjEgobt6Q.ttf")
  }
  object `Kdam Thmor` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/kdamthmor/v3/otCdP6UU-VBIrBfVDWBQJ_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Keania One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/keaniaone/v4/PACrDKZWngXzgo-ucl6buvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Kelly Slab` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/kellyslab/v6/F_2oS1e9XdYx1MAi8XEVefesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Kenia` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/kenia/v8/OLM9-XfITK9PsTLKbGBrwg.ttf")
  }
  object `Khand` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `300`: URL = URL(s"$base/khand/v5/072zRl4OU9Pinjjkg174LA.ttf")
    def `regular`: URL = URL(s"$base/khand/v5/HdLdTNFqNIDGJZl1ZEj84w.ttf")
    def `500`: URL = URL(s"$base/khand/v5/46_p-SqtuMe56nxQdteWxg.ttf")
    def `600`: URL = URL(s"$base/khand/v5/zggGWYIiPJyMTgkfxP_kaA.ttf")
    def `700`: URL = URL(s"$base/khand/v5/0I0UWaN-X5QBmfexpXKhqg.ttf")
  }
  object `Khmer` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/khmer/v9/vWaBJIbaQuBNz02ALIKJ3A.ttf")
  }
  object `Khula` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `300`: URL = URL(s"$base/khula/v2/_1LySU5Upq-sc4OZ1b_GIw.ttf")
    def `regular`: URL = URL(s"$base/khula/v2/izcPIFyCSd16XI1Ak_Wk7Q.ttf")
    def `600`: URL = URL(s"$base/khula/v2/4ZH86Hce-aeFDaedTnbkbg.ttf")
    def `700`: URL = URL(s"$base/khula/v2/UGVExGl-Jjs-YPpGv-MZ6w.ttf")
    def `800`: URL = URL(s"$base/khula/v2/Sccp_oOo8FWgbx5smie7xQ.ttf")
  }
  object `Kite One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/kiteone/v4/8ojWmgUc97m0f_i6sTqLoQ.ttf")
  }
  object `Knewave` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/knewave/v5/KGHM4XWr4iKnBMqzZLkPBg.ttf")
  }
  object `Kotta One` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/kottaone/v4/AB2Q7hVw6niJYDgLvFXu5w.ttf")
  }
  object `Koulen` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/koulen/v10/AAYOK8RSRO7FTskTzFuzNw.ttf")
  }
  object `Kranky` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/kranky/v7/C8dxxTS99-fZ84vWk8SDrg.ttf")
  }
  object `Kreon` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `300`: URL = URL(s"$base/kreon/v10/HKtJRiq5C2zbq5N1IX32sA.ttf")
    def `regular`: URL = URL(s"$base/kreon/v10/zA_IZt0u0S3cvHJu-n1oEg.ttf")
    def `700`: URL = URL(s"$base/kreon/v10/jh0dSmaPodjxISiblIUTkw.ttf")
  }
  object `Kristi` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/kristi/v8/aRsgBQrkQkMlu4UPSnJyOQ.ttf")
  }
  object `Krona One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/kronaone/v4/zcQj4ljqTo166AdourlF9w.ttf")
  }
  object `Kumar One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "gujarati", "latin")
    def `regular`: URL = URL(s"$base/kumarone/v1/YmcJD6Wky1clGYY5OD-BkQ.ttf")
  }
  object `Kumar One Outline` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "gujarati", "latin")
    def `regular`: URL = URL(s"$base/kumaroneoutline/v1/hnQF47H-55qiLAGgq7C3QyxhoCTLJoiJ-y-zew8F8j0.ttf")
  }
  object `Kurale` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "devanagari", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/kurale/v2/rxeyIcvQlT4XAWwNbXFCfw.ttf")
  }
  object `La Belle Aurore` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/labelleaurore/v7/Irdbc4ASuUoWDjd_Wc3md123K2iuuhwZgaKapkyRTY8.ttf")
  }
  object `Laila` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `300`: URL = URL(s"$base/laila/v3/bLbIVEZF3IWSZ-in72GJvA.ttf")
    def `regular`: URL = URL(s"$base/laila/v3/6iYor3edprH7360qtBGoag.ttf")
    def `500`: URL = URL(s"$base/laila/v3/tkf8VtFvW9g3VsxQCA6WOQ.ttf")
    def `600`: URL = URL(s"$base/laila/v3/3EMP2L6JRQ4GaHIxCldCeA.ttf")
    def `700`: URL = URL(s"$base/laila/v3/R7P4z1xjcjecmjZ9GyhqHQ.ttf")
  }
  object `Lakki Reddy` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/lakkireddy/v3/Q5EpFa91FjW37t0FCnedaKCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Lalezar` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "arabic", "vietnamese")
    def `regular`: URL = URL(s"$base/lalezar/v1/k4_MPf09PGmL7oyGdPKwcg.ttf")
  }
  object `Lancelot` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/lancelot/v6/XMT7T_oo_MQUGAnU2v-sdA.ttf")
  }
  object `Lateef` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin", "arabic")
    def `regular`: URL = URL(s"$base/lateef/v10/PAsKCgi1qc7XPwvzo_I-DQ.ttf")
  }
  object `Lato` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `100`: URL = URL(s"$base/lato/v13/Upp-ka9rLQmHYCsFgwL-eg.ttf")
    def `100italic`: URL = URL(s"$base/lato/v13/zLegi10uS_9-fnUDISl0KA.ttf")
    def `300`: URL = URL(s"$base/lato/v13/Ja02qOppOVq9jeRjWekbHg.ttf")
    def `300italic`: URL = URL(s"$base/lato/v13/dVebFcn7EV7wAKwgYestUg.ttf")
    def `regular`: URL = URL(s"$base/lato/v13/h7rISIcQapZBpei-sXwIwg.ttf")
    def `italic`: URL = URL(s"$base/lato/v13/P_dJOFJylV3A870UIOtr0w.ttf")
    def `700`: URL = URL(s"$base/lato/v13/iX_QxBBZLhNj5JHlTzHQzg.ttf")
    def `700italic`: URL = URL(s"$base/lato/v13/WFcZakHrrCKeUJxHA4T_gw.ttf")
    def `900`: URL = URL(s"$base/lato/v13/8TPEV6NbYWZlNsXjbYVv7w.ttf")
    def `900italic`: URL = URL(s"$base/lato/v13/draWperrI7n2xi35Cl08fA.ttf")
  }
  object `League Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/leaguescript/v7/wnRFLvfabWK_DauqppD6vSeUSrabuTpOsMEiRLtKwk0.ttf")
  }
  object `Leckerli One` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/leckerlione/v7/S2Y_iLrItTu8kIJTkS7DrC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Ledger` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/ledger/v4/G432jp-tahOfWHbCYkI0jw.ttf")
  }
  object `Lekton` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/lekton/v7/r483JYmxf5PjIm4jVAm8Yg.ttf")
    def `italic`: URL = URL(s"$base/lekton/v7/_UbDIPBA1wDqSbhp-OED7A.ttf")
    def `700`: URL = URL(s"$base/lekton/v7/WZw-uL8WTkx3SBVfTlevXQ.ttf")
  }
  object `Lemon` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/lemon/v5/wed1nNu4LNSu-3RoRVUhUw.ttf")
  }
  object `Lemonada` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "arabic", "vietnamese")
    def `300`: URL = URL(s"$base/lemonada/v2/uM3MufQOcwGHuruj4TsXiqCWcynf_cDxXwCLxiixG1c.ttf")
    def `regular`: URL = URL(s"$base/lemonada/v2/pkzws3AUXmaaAzOi7aydSQ.ttf")
    def `600`: URL = URL(s"$base/lemonada/v2/9Vd4MNKsOxNyLzlfTXdKLqCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/lemonada/v2/9jKcm4hRI511-Dy7FFfQ3aCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Libre Barcode 128` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/librebarcode128/v3/mJ_rGOyyL62_i4eysdBvxEaNJhdpbyHQuRiGjlHceQo.ttf")
  }
  object `Libre Barcode 128 Text` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/librebarcode128text/v3/T1o66XlW_PeuHiRa8wDOJDfWl2h5aCwBu15s5iWPtdk.ttf")
  }
  object `Libre Barcode 39` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/librebarcode39/v3/tsmYkcVN_FjeCmyWhRNQuDLD7PrtP9qwC5bVQ-6ZBpw.ttf")
  }
  object `Libre Barcode 39 Extended` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/librebarcode39extended/v2/fb2-vuy0PLrmtXyLBPV4KGYAiLTSvZR2kkYPJthhKEg.ttf")
  }
  object `Libre Barcode 39 Extended Text` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/librebarcode39extendedtext/v2/wJsqK3E245PKDhdHYS7MabGP_8dGDh0UJYBW4DYg-cv00s133LT-tR5tU-vU7gLU.ttf")
  }
  object `Libre Barcode 39 Text` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/librebarcode39text/v3/O4inMvtTcDsw_GI-nhT1nhLP3W-fKNeNuxNx_t55A8U.ttf")
  }
  object `Libre Baskerville` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/librebaskerville/v4/pR0sBQVcY0JZc_ciXjFsKyyZRYCSvpCzQKuMWnP5NDY.ttf")
    def `italic`: URL = URL(s"$base/librebaskerville/v4/QHIOz1iKF3bIEzRdDFaf5QnhapNS5Oi8FPrBRDLbsW4.ttf")
    def `700`: URL = URL(s"$base/librebaskerville/v4/kH7K4InNTm7mmOXXjrA5v-xuswJKUVpBRfYFpz0W3Iw.ttf")
  }
  object `Libre Franklin` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `100`: URL = URL(s"$base/librefranklin/v1/zrsyK9EytLQ07oRM9IZIsX6Zf0VB_l-7q6pFtcZSRCs.ttf")
    def `100italic`: URL = URL(s"$base/librefranklin/v1/LHzsuUmxr4UY-IoiG8pRK4gsWNE1DYiT_eIOcNe2Au4.ttf")
    def `200`: URL = URL(s"$base/librefranklin/v1/1_DGDtljMiPWFs5rl_p0yCwKTB4uIbnDXE2hyxZaFPY.ttf")
    def `200italic`: URL = URL(s"$base/librefranklin/v1/7_V210XP3LBEtEwiCTqho0lu1sSkaQaYEjN61aJ3i1I.ttf")
    def `300`: URL = URL(s"$base/librefranklin/v1/1_DGDtljMiPWFs5rl_p0yMhKJW3W9-339CFS_Lie1us.ttf")
    def `300italic`: URL = URL(s"$base/librefranklin/v1/7_V210XP3LBEtEwiCTqho14je5cfhxzx5bEvSaoyQQI.ttf")
    def `regular`: URL = URL(s"$base/librefranklin/v1/PFwjf3aDdAQPvNKUrT3U7_fSnedoLXQQjURyDxluu8g.ttf")
    def `italic`: URL = URL(s"$base/librefranklin/v1/zrsyK9EytLQ07oRM9IZIsX5kKxjpQfTpnFf2SrDLxlg.ttf")
    def `500`: URL = URL(s"$base/librefranklin/v1/1_DGDtljMiPWFs5rl_p0yMBjwrbmxH6gp8HgxjPD8qo.ttf")
    def `500italic`: URL = URL(s"$base/librefranklin/v1/7_V210XP3LBEtEwiCTqho5VcuOW5XbZIr02vW37iuvg.ttf")
    def `600`: URL = URL(s"$base/librefranklin/v1/1_DGDtljMiPWFs5rl_p0yORt4MKdIUjA60qLK3wI2m8.ttf")
    def `600italic`: URL = URL(s"$base/librefranklin/v1/7_V210XP3LBEtEwiCTqhowNPRgU5g4Xymf9hgRWrbNs.ttf")
    def `700`: URL = URL(s"$base/librefranklin/v1/1_DGDtljMiPWFs5rl_p0yEnStGWSv3WdwjmyyI8xc7Q.ttf")
    def `700italic`: URL = URL(s"$base/librefranklin/v1/7_V210XP3LBEtEwiCTqhow7kn3RFjf4gfwsdsBE-Rf4.ttf")
    def `800`: URL = URL(s"$base/librefranklin/v1/1_DGDtljMiPWFs5rl_p0yKltwG0cydF-uC1kFVv1hts.ttf")
    def `800italic`: URL = URL(s"$base/librefranklin/v1/7_V210XP3LBEtEwiCTqho80d7u0uHUbaRkK-cNyim1w.ttf")
    def `900`: URL = URL(s"$base/librefranklin/v1/1_DGDtljMiPWFs5rl_p0yF7duMYIKwoQ5QsTL00fobw.ttf")
    def `900italic`: URL = URL(s"$base/librefranklin/v1/7_V210XP3LBEtEwiCTqho0THpHUXJVnEwH4tSjkF0wg.ttf")
  }
  object `Life Savers` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/lifesavers/v6/g49cUDk4Y1P0G5NMkMAm7qCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/lifesavers/v6/THQKqChyYUm97rNPVFdGGXe1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Lilita One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/lilitaone/v4/vTxJQjbNV6BCBHx8sGDCVvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Lily Script One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/lilyscriptone/v4/uPWsLVW8uiXqIBnE8ZwGPDjVlsJB_M_Q_LtZxsoxvlw.ttf")
  }
  object `Limelight` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/limelight/v7/5dTfN6igsXjLjOy8QQShcg.ttf")
  }
  object `Linden Hill` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/lindenhill/v6/UgsC0txqd-E1yjvjutwm_KCWcynf_cDxXwCLxiixG1c.ttf")
    def `italic`: URL = URL(s"$base/lindenhill/v6/OcS3bZcu8vJvIDH8Zic83keOrDcLawS7-ssYqLr2Xp4.ttf")
  }
  object `Lobster` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/lobster/v18/9LpJGtNuM1D8FAZ2BkJH2Q.ttf")
  }
  object `Lobster Two` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/lobstertwo/v9/xb9aY4w9ceh8JRzobID1naCWcynf_cDxXwCLxiixG1c.ttf")
    def `italic`: URL = URL(s"$base/lobstertwo/v9/Ul_16MSbfayQv1I4QhLEoEeOrDcLawS7-ssYqLr2Xp4.ttf")
    def `700`: URL = URL(s"$base/lobstertwo/v9/bmdxOflBqMqjEC0-kGsIiHe1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `700italic`: URL = URL(s"$base/lobstertwo/v9/LEkN2_no_6kFvRfiBZ8xpM_zJjSACmk0BRPxQqhnNLU.ttf")
  }
  object `Londrina Outline` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/londrinaoutline/v7/lls08GOa1eT74p072l1AWJmp8DTZ6iHear7UV05iykg.ttf")
  }
  object `Londrina Shadow` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/londrinashadow/v5/dNYuzPS_7eYgXFJBzMoKdbw6Z3rVA5KDSi7aQxS92Nk.ttf")
  }
  object `Londrina Sketch` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/londrinasketch/v5/p7Ai06aT1Ycp_D2fyE3z69d6z_uhFGnpCOifUY1fJQo.ttf")
  }
  object `Londrina Solid` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `100`: URL = URL(s"$base/londrinasolid/v5/GNw2ckl4GiWuueFb9dMt4kBPCDJ-ayOoeeQPacAe1lc.ttf")
    def `300`: URL = URL(s"$base/londrinasolid/v5/BDKo9ty0kfh66weuamkY1YGlXQxaR_emZVjFa6K5Gm8.ttf")
    def `regular`: URL = URL(s"$base/londrinasolid/v5/yysorIEiYSBb0ylZjg791MR125CwGqh8XBqkBzea0LA.ttf")
    def `900`: URL = URL(s"$base/londrinasolid/v5/BDKo9ty0kfh66weuamkY1cOBCLEQFAwATxcDa2xYLs8.ttf")
  }
  object `Lora` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/lora/v10/aXJ7KVIGcejEy1abawZazg.ttf")
    def `italic`: URL = URL(s"$base/lora/v10/AN2EZaj2tFRpyveuNn9BOg.ttf")
    def `700`: URL = URL(s"$base/lora/v10/enKND5SfzQKkggBA_VnT1A.ttf")
    def `700italic`: URL = URL(s"$base/lora/v10/ivs9j3kYU65pR9QD9YFdzQ.ttf")
  }
  object `Love Ya Like A Sister` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/loveyalikeasister/v7/LzkxWS-af0Br2Sk_YgSJY-ad1xEP8DQfgfY8MH9aBUg.ttf")
  }
  object `Loved by the King` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/lovedbytheking/v6/wg03xD4cWigj4YDufLBSr8io2AFEwwMpu7y5KyiyAJc.ttf")
  }
  object `Lovers Quarrel` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/loversquarrel/v4/gipdZ8b7pKb89MzQLAtJHLHLxci2ElvNEmOB303HLk0.ttf")
  }
  object `Luckiest Guy` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/luckiestguy/v7/5718gH8nDy3hFVihOpkY5C3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Lusitana` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/lusitana/v4/l1h9VDomkwbdzbPdmLcUIw.ttf")
    def `700`: URL = URL(s"$base/lusitana/v4/GWtZyUsONxgkdl3Mc1P7FKCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Lustria` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/lustria/v4/gXAk0s4ai0X-TAOhYzZd1w.ttf")
  }
  object `Macondo` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/macondo/v5/G6yPNUscRPQ8ufBXs_8yRQ.ttf")
  }
  object `Macondo Swash Caps` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/macondoswashcaps/v4/SsSR706z-MlvEH7_LS6JAPkkgYRHs6GSG949m-K6x2k.ttf")
  }
  object `Mada` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin", "arabic")
    def `200`: URL = URL(s"$base/mada/v3/sN1aPvvd07F1Sq3qcEQg4w.ttf")
    def `300`: URL = URL(s"$base/mada/v3/P46fye2TPh4fVwALgHSXCA.ttf")
    def `regular`: URL = URL(s"$base/mada/v3/io_zUrt5o943T_q45OHLWQ.ttf")
    def `500`: URL = URL(s"$base/mada/v3/PhhDsBi34sP0LptbpS9m6w.ttf")
    def `600`: URL = URL(s"$base/mada/v3/6zYBU-NFokCo3MIlPsWCUw.ttf")
    def `700`: URL = URL(s"$base/mada/v3/VnwndFbEsjy4VcU_Dzedhg.ttf")
    def `900`: URL = URL(s"$base/mada/v3/aCyc9Kc3rOJLL6fV9VfptA.ttf")
  }
  object `Magra` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/magra/v4/hoZ13bwCXBxuGZqAudgc5A.ttf")
    def `700`: URL = URL(s"$base/magra/v4/6fOM5sq5cIn8D0RjX8Lztw.ttf")
  }
  object `Maiden Orange` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/maidenorange/v7/ZhKIA2SPisEwdhW7g0RUWojjx0o0jr6fNXxPgYh_a8Q.ttf")
  }
  object `Maitree` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `200`: URL = URL(s"$base/maitree/v1/JTlrRs3bVPV4i05cUIx_z_esZW2xOQ-xsNqO47m55DA.ttf")
    def `300`: URL = URL(s"$base/maitree/v1/rEGdABAOaqCHggl37mkWjfesZW2xOQ-xsNqO47m55DA.ttf")
    def `regular`: URL = URL(s"$base/maitree/v1/SpKVJkAjDAYOr1VkdSRspA.ttf")
    def `500`: URL = URL(s"$base/maitree/v1/2VHD7TXjRhN4Xu74SEPGdvesZW2xOQ-xsNqO47m55DA.ttf")
    def `600`: URL = URL(s"$base/maitree/v1/uuazDnPwt30gW3cKsG-e0_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/maitree/v1/cnHhc9fphsL3q-pistN3IPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Mako` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/mako/v7/z5zSLmfPlv1uTVAdmJBLXg.ttf")
  }
  object `Mallanna` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/mallanna/v4/krCTa-CfMbtxqF0689CbuQ.ttf")
  }
  object `Mandali` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/mandali/v4/0lF8yJ7fkyjXuqtSi5bWbQ.ttf")
  }
  object `Manuale` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/manuale/v1/OL9lzPXATGiZUB8Qdk3tiQ.ttf")
    def `italic`: URL = URL(s"$base/manuale/v1/oRbwaLnv_NzztbUuhNLiBw.ttf")
    def `500`: URL = URL(s"$base/manuale/v1/xsy0EZlufjk4A6mPfwX5mfesZW2xOQ-xsNqO47m55DA.ttf")
    def `500italic`: URL = URL(s"$base/manuale/v1/r4TYrL7JhyPxpmVA-JAN0S3USBnSvpkopQaUR-2r7iU.ttf")
    def `600`: URL = URL(s"$base/manuale/v1/gDxlyLYdCx7A4S8cf-Z8JvesZW2xOQ-xsNqO47m55DA.ttf")
    def `600italic`: URL = URL(s"$base/manuale/v1/n25GBfdDLxRFJ-OYtzyorS3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/manuale/v1/ut2ZOkBP2LtTYOuh1fI83_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/manuale/v1/Lrka5WC7aKfhIA6uk-QS6y3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Marcellus` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/marcellus/v4/UjiLZzumxWC9whJ86UtaYw.ttf")
  }
  object `Marcellus SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/marcellussc/v4/_jugwxhkkynrvsfrxVx8gS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Marck Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/marckscript/v7/O_D1NAZVOFOobLbVtW3bci3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Margarine` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/margarine/v5/DJnJwIrcO_cGkjSzY3MERw.ttf")
  }
  object `Marko One` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/markoone/v6/hpP7j861sOAco43iDc4n4w.ttf")
  }
  object `Marmelad` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/marmelad/v6/jI0_FBlSOIRLL0ePWOhOwQ.ttf")
  }
  object `Martel` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `200`: URL = URL(s"$base/martel/v1/_wfGdswZbat7P4tupHLA1w.ttf")
    def `300`: URL = URL(s"$base/martel/v1/SghoV2F2VPdVU3P0a4fa9w.ttf")
    def `regular`: URL = URL(s"$base/martel/v1/9ALu5czkaaf5zsYk6GJEnQ.ttf")
    def `600`: URL = URL(s"$base/martel/v1/Kt9uPhH1PvUwuZ5Y6zuAMQ.ttf")
    def `700`: URL = URL(s"$base/martel/v1/4OzIiKB5wE36xXL2U0vzWQ.ttf")
    def `800`: URL = URL(s"$base/martel/v1/RVF8drcQoRkRL7l_ZkpTlQ.ttf")
    def `900`: URL = URL(s"$base/martel/v1/iS0YUpFJoiLRlnyl40rpEA.ttf")
  }
  object `Martel Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `200`: URL = URL(s"$base/martelsans/v3/7ajme85aKKx_SCWF59ImQEnzyIngrzGjGh22wPb6cGM.ttf")
    def `300`: URL = URL(s"$base/martelsans/v3/7ajme85aKKx_SCWF59ImQC9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/martelsans/v3/91c8DPDZncMc0RFfhmc2RqCWcynf_cDxXwCLxiixG1c.ttf")
    def `600`: URL = URL(s"$base/martelsans/v3/7ajme85aKKx_SCWF59ImQJZ7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `700`: URL = URL(s"$base/martelsans/v3/7ajme85aKKx_SCWF59ImQHe1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `800`: URL = URL(s"$base/martelsans/v3/7ajme85aKKx_SCWF59ImQA89PwPrYLaRFJ-HNCU9NbA.ttf")
    def `900`: URL = URL(s"$base/martelsans/v3/7ajme85aKKx_SCWF59ImQCenaqEuufTBk9XMKnKmgDA.ttf")
  }
  object `Marvel` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/marvel/v6/Fg1dO8tWVb-MlyqhsbXEkg.ttf")
    def `italic`: URL = URL(s"$base/marvel/v6/HzyjFB-oR5usrc7Lxz9g8w.ttf")
    def `700`: URL = URL(s"$base/marvel/v6/WrHDBL1RupWGo2UcdgxB3Q.ttf")
    def `700italic`: URL = URL(s"$base/marvel/v6/Gzf5NT09Y6xskdQRj2kz1qCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Mate` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/mate/v5/ooFviPcJ6hZP5bAE71Cawg.ttf")
    def `italic`: URL = URL(s"$base/mate/v5/5XwW6_cbisGvCX5qmNiqfA.ttf")
  }
  object `Mate SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/matesc/v5/-YkIT2TZoPZF6pawKzDpWw.ttf")
  }
  object `Maven Pro` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/mavenpro/v10/sqPJIFG4gqsjl-0q_46Gbw.ttf")
    def `500`: URL = URL(s"$base/mavenpro/v10/SQVfzoJBbj9t3aVcmbspRi3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/mavenpro/v10/uDssvmXgp7Nj3i336k_dSi3USBnSvpkopQaUR-2r7iU.ttf")
    def `900`: URL = URL(s"$base/mavenpro/v10/-91TwiFzqeL1F7Kh91APwS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `McLaren` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/mclaren/v4/OprvTGxaiINBKW_1_U0eoQ.ttf")
  }
  object `Meddon` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/meddon/v9/f8zJO98uu2EtSj9p7ci9RA.ttf")
  }
  object `MedievalSharp` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/medievalsharp/v8/85X_PjV6tftJ0-rX7KYQkOe45sJkivqprK7VkUlzfg0.ttf")
  }
  object `Medula One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/medulaone/v6/AasPgDQak81dsTGQHc5zUPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Meera Inimai` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin", "tamil")
    def `regular`: URL = URL(s"$base/meerainimai/v1/fWbdJc2ZVZnWCi06NRCxDy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Megrim` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/megrim/v7/e-9jVUC9lv1zxaFQARuftw.ttf")
  }
  object `Meie Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/meiescript/v4/oTIWE5MmPye-rCyVp_6KEqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Merienda` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/merienda/v4/MYY6Og1qZlOQtPW2G95Y3A.ttf")
    def `700`: URL = URL(s"$base/merienda/v4/GlwcvRLlgiVE2MBFQ4r0sKCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Merienda One` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/meriendaone/v7/bCA-uDdUx6nTO8SjzCLXvS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Merriweather` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `300`: URL = URL(s"$base/merriweather/v17/ZvcMqxEwPfh2qDWBPxn6nqcQoVhARpoaILP7amxE_8g.ttf")
    def `300italic`: URL = URL(s"$base/merriweather/v17/EYh7Vl4ywhowqULgRdYwICna0FLWfcB-J_SAYmcAXaI.ttf")
    def `regular`: URL = URL(s"$base/merriweather/v17/RFda8w1V0eDZheqfcyQ4EC3USBnSvpkopQaUR-2r7iU.ttf")
    def `italic`: URL = URL(s"$base/merriweather/v17/So5lHxHT37p2SS4-t60SlPMZXuCXbOrAvx5R0IT5Oyo.ttf")
    def `700`: URL = URL(s"$base/merriweather/v17/ZvcMqxEwPfh2qDWBPxn6nkD2ttfZwueP-QU272T9-k4.ttf")
    def `700italic`: URL = URL(s"$base/merriweather/v17/EYh7Vl4ywhowqULgRdYwIPAs9-1nE9qOqhChW0m4nDE.ttf")
    def `900`: URL = URL(s"$base/merriweather/v17/ZvcMqxEwPfh2qDWBPxn6nqObDOjC3UL77puoeHsE3fw.ttf")
    def `900italic`: URL = URL(s"$base/merriweather/v17/EYh7Vl4ywhowqULgRdYwIBd0_s6jQr9r5s5OZYvtzBY.ttf")
  }
  object `Merriweather Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/merriweathersans/v8/6LmGj5dOJopQKEkt88Gowan5N8K-_DP0e9e_v51obXQ.ttf")
    def `300italic`: URL = URL(s"$base/merriweathersans/v8/nAqt4hiqwq3tzCecpgPmVdytE4nGXk2hYD5nJ740tBw.ttf")
    def `regular`: URL = URL(s"$base/merriweathersans/v8/AKu1CjQ4qnV8MUltkAX3sOAj_ty82iuwwDTNEYXGiyQ.ttf")
    def `italic`: URL = URL(s"$base/merriweathersans/v8/3Mz4hOHzs2npRMG3B1ascZ32VBCoA_HLsn85tSWZmdo.ttf")
    def `700`: URL = URL(s"$base/merriweathersans/v8/6LmGj5dOJopQKEkt88GowbqxG25nQNOioCZSK4sU-CA.ttf")
    def `700italic`: URL = URL(s"$base/merriweathersans/v8/nAqt4hiqwq3tzCecpgPmVbuqAJxizi8Dk_SK5et7kMg.ttf")
    def `800`: URL = URL(s"$base/merriweathersans/v8/6LmGj5dOJopQKEkt88GowYufzO2zUYSj5LqoJ3UGkco.ttf")
    def `800italic`: URL = URL(s"$base/merriweathersans/v8/nAqt4hiqwq3tzCecpgPmVdDmPrYMy3aZO4LmnZsxTQw.ttf")
  }
  object `Metal` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/metal/v9/zA3UOP13ooQcxjv04BZX5g.ttf")
  }
  object `Metal Mania` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/metalmania/v6/isriV_rAUgj6bPWPN6l9QKCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Metamorphous` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/metamorphous/v6/wGqUKXRinIYggz-BTRU9ei3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Metrophobic` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/metrophobic/v9/SaglWZWCrrv_D17u1i4v_aCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Michroma` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/michroma/v7/0c2XrW81_QsiKV8T9thumA.ttf")
  }
  object `Milonga` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/milonga/v4/dzNdIUSTGFmy2ahovDRcWg.ttf")
  }
  object `Miltonian` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/miltonian/v10/Z4HrYZyqm0BnNNzcCUfzoQ.ttf")
  }
  object `Miltonian Tattoo` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/miltoniantattoo/v11/1oU_8OGYwW46eh02YHydn2uk0YtI6thZkz1Hmh-odwg.ttf")
  }
  object `Miniver` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/miniver/v5/4yTQohOH_cWKRS5laRFhYg.ttf")
  }
  object `Miriam Libre` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "hebrew", "latin")
    def `regular`: URL = URL(s"$base/miriamlibre/v2/Ljtpu8zR5iJWmlN3Faba5S3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/miriamlibre/v2/FLc0J-Gdn8ynDWUkeeesAED2ttfZwueP-QU272T9-k4.ttf")
  }
  object `Mirza` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "arabic")
    def `regular`: URL = URL(s"$base/mirza/v2/8oe36Xbgj9BMSLJBaZ8VAQ.ttf")
    def `500`: URL = URL(s"$base/mirza/v2/dT3HbZoBCx1xbU7PnFEFyQ.ttf")
    def `600`: URL = URL(s"$base/mirza/v2/6T4uh2Zti9P6Eq_gbAYvVQ.ttf")
    def `700`: URL = URL(s"$base/mirza/v2/b47CZDHoZdhnplmDpZymFw.ttf")
  }
  object `Miss Fajardose` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/missfajardose/v6/WcXjlQPKn6nBfr8LY3ktNu6rPKfVZo7L2bERcf0BDns.ttf")
  }
  object `Mitr` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `200`: URL = URL(s"$base/mitr/v2/GCzZRAhweqJhxrmM0bPztg.ttf")
    def `300`: URL = URL(s"$base/mitr/v2/A61rQ_y9i8Ja__oFN7KxiQ.ttf")
    def `regular`: URL = URL(s"$base/mitr/v2/vKMd72X2iT4iBo5GvdCa_A.ttf")
    def `500`: URL = URL(s"$base/mitr/v2/r_Z6yrJJ0zmkGAqxqjlLRg.ttf")
    def `600`: URL = URL(s"$base/mitr/v2/42l66tb_XMxM97GKatU9Ng.ttf")
    def `700`: URL = URL(s"$base/mitr/v2/V-V7Rul5HOZ651R4Tml2Lw.ttf")
  }
  object `Modak` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/modak/v2/lMsN0QIKid-pCPvL0hH4nw.ttf")
  }
  object `Modern Antiqua` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/modernantiqua/v6/8qX_tr6Xzy4t9fvZDXPkh6rFJ4O13IHVxZbM6yoslpo.ttf")
  }
  object `Mogra` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "gujarati", "latin")
    def `regular`: URL = URL(s"$base/mogra/v3/gIxQBn9PseDaI0D4FnOiBQ.ttf")
  }
  object `Molengo` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/molengo/v7/jcjgeGuzv83I55AzOTpXNQ.ttf")
  }
  object `Molle` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `italic`: URL = URL(s"$base/molle/v5/9XTdCsjPXifLqo5et-YoGA.ttf")
  }
  object `Monda` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/monda/v6/qFMHZ9zvR6B_gnoIgosPrw.ttf")
    def `700`: URL = URL(s"$base/monda/v6/EVOzZUyc_j1w2GuTgTAW1g.ttf")
  }
  object `Monofett` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/monofett/v6/C6K5L799Rgxzg2brgOaqAw.ttf")
  }
  object `Monoton` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/monoton/v6/aCz8ja_bE4dg-7agSvExdw.ttf")
  }
  object `Monsieur La Doulaise` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/monsieurladoulaise/v5/IMAdMj6Eq9jZ46CPctFtMKP61oAqTJXlx5ZVOBmcPdM.ttf")
  }
  object `Montaga` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/montaga/v4/PwTwUboiD-M4-mFjZfJs2A.ttf")
  }
  object `Montez` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/montez/v7/kx58rLOWQQLGFM4pDHv5Ng.ttf")
  }
  object `Montserrat` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/montserrat/v10/CdKWaRAal2Bxq9mORLKRRS3USBnSvpkopQaUR-2r7iU.ttf")
    def `100italic`: URL = URL(s"$base/montserrat/v10/1809Y0aW9bpFOPXsQTFwf8SVQ0giZ-l_NELu3lgGyYw.ttf")
    def `200`: URL = URL(s"$base/montserrat/v10/eWRmKHdPNWGn_iFyeEYja2eudeTO44zf-ht3k-KNzwg.ttf")
    def `200italic`: URL = URL(s"$base/montserrat/v10/zhwB3-BAdyKDf0geWr9FtwQm5IkIgNCodAfQb4ovl18.ttf")
    def `300`: URL = URL(s"$base/montserrat/v10/IVeH6A3MiFyaSEiudUMXE0eOrDcLawS7-ssYqLr2Xp4.ttf")
    def `300italic`: URL = URL(s"$base/montserrat/v10/zhwB3-BAdyKDf0geWr9Ft6cQoVhARpoaILP7amxE_8g.ttf")
    def `regular`: URL = URL(s"$base/montserrat/v10/Kqy6-utIpx_30Xzecmeo8_esZW2xOQ-xsNqO47m55DA.ttf")
    def `italic`: URL = URL(s"$base/montserrat/v10/-iqwlckIhsmvkx0N6rwPmi3USBnSvpkopQaUR-2r7iU.ttf")
    def `500`: URL = URL(s"$base/montserrat/v10/BYPM-GE291ZjIXBWrtCwepp-63r6doWhTEbsfBIRJ7A.ttf")
    def `500italic`: URL = URL(s"$base/montserrat/v10/zhwB3-BAdyKDf0geWr9Ft5MQuUSAwdHsY8ov_6tk1oA.ttf")
    def `600`: URL = URL(s"$base/montserrat/v10/q2OIMsAtXEkOulLQVdSl0_pTEJqju4Hz1txDWij77d4.ttf")
    def `600italic`: URL = URL(s"$base/montserrat/v10/zhwB3-BAdyKDf0geWr9Ft2v8CylhIUtwUiYO7Z2wXbE.ttf")
    def `700`: URL = URL(s"$base/montserrat/v10/IQHow_FEYlDC4Gzy_m8fcgJKKGfqHaYFsRG-T3ceEVo.ttf")
    def `700italic`: URL = URL(s"$base/montserrat/v10/zhwB3-BAdyKDf0geWr9Ft0D2ttfZwueP-QU272T9-k4.ttf")
    def `800`: URL = URL(s"$base/montserrat/v10/H8_7oktkjVeeX06kbAvc0Kk3bhPBSBJ0bSJQ6acL-0g.ttf")
    def `800italic`: URL = URL(s"$base/montserrat/v10/zhwB3-BAdyKDf0geWr9Ft_qsay_1ZmRGmC8pVRdIfAg.ttf")
    def `900`: URL = URL(s"$base/montserrat/v10/aEu-9ATAroJ1iN4zmQ55Bp0EAVxt0G0biEntp43Qt6E.ttf")
    def `900italic`: URL = URL(s"$base/montserrat/v10/zhwB3-BAdyKDf0geWr9Ft6ObDOjC3UL77puoeHsE3fw.ttf")
  }
  object `Montserrat Alternates` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/montserratalternates/v7/oqQkJ7FUCF9bJw9oNhwpltmjtuu7N1WAenNR-bns1HU.ttf")
    def `100italic`: URL = URL(s"$base/montserratalternates/v7/3-rFIqHz_U7TAmWg7RcpLzob9T7De5a9EmE7cInrugI.ttf")
    def `200`: URL = URL(s"$base/montserratalternates/v7/YENqOGAVzwIHjYNjmKuAZrWzJnWnTj1NV2WEtcqW8F0.ttf")
    def `200italic`: URL = URL(s"$base/montserratalternates/v7/AXzeb8s80Wvg1Wkw1cVlATSYqyfLbk4Wyr4DDJHtpar3rGVtsTkPsbDajuO5ueQw.ttf")
    def `300`: URL = URL(s"$base/montserratalternates/v7/YENqOGAVzwIHjYNjmKuAZoE9JAqK0NEjKMCIBssy61I.ttf")
    def `300italic`: URL = URL(s"$base/montserratalternates/v7/AXzeb8s80Wvg1Wkw1cVlAX0Ksah31OxOJpZejHsaXyX3rGVtsTkPsbDajuO5ueQw.ttf")
    def `regular`: URL = URL(s"$base/montserratalternates/v7/z2n1Sjxk9souK3HCtdHuklPuEVRGaG9GCQnmM16YWq0.ttf")
    def `italic`: URL = URL(s"$base/montserratalternates/v7/oqQkJ7FUCF9bJw9oNhwpliKJhVBtn9MynHVBPiS2bkc.ttf")
    def `500`: URL = URL(s"$base/montserratalternates/v7/YENqOGAVzwIHjYNjmKuAZkLT1bEhWimL9YDPt6og4ow.ttf")
    def `500italic`: URL = URL(s"$base/montserratalternates/v7/AXzeb8s80Wvg1Wkw1cVlAbq1yxDcj1rkVNifBkzxbjz3rGVtsTkPsbDajuO5ueQw.ttf")
    def `600`: URL = URL(s"$base/montserratalternates/v7/YENqOGAVzwIHjYNjmKuAZlzJBia8MVcXq42LmpYhWMY.ttf")
    def `600italic`: URL = URL(s"$base/montserratalternates/v7/AXzeb8s80Wvg1Wkw1cVlAdzE96w6fJMDbKTKS-tt8C_3rGVtsTkPsbDajuO5ueQw.ttf")
    def `700`: URL = URL(s"$base/montserratalternates/v7/YENqOGAVzwIHjYNjmKuAZpeqBKvsAhm-s2I4RVSXFfc.ttf")
    def `700italic`: URL = URL(s"$base/montserratalternates/v7/AXzeb8s80Wvg1Wkw1cVlAVeYZ2vsofSkgKvS_YtoH2b3rGVtsTkPsbDajuO5ueQw.ttf")
    def `800`: URL = URL(s"$base/montserratalternates/v7/YENqOGAVzwIHjYNjmKuAZkG2AOFTt9I0BIk1fL0aWvI.ttf")
    def `800italic`: URL = URL(s"$base/montserratalternates/v7/AXzeb8s80Wvg1Wkw1cVlAbM_h-OHjcDf1XWbHqSgRF73rGVtsTkPsbDajuO5ueQw.ttf")
    def `900`: URL = URL(s"$base/montserratalternates/v7/YENqOGAVzwIHjYNjmKuAZqjHT7NF_e7B-hWEBx2SqPI.ttf")
    def `900italic`: URL = URL(s"$base/montserratalternates/v7/AXzeb8s80Wvg1Wkw1cVlAX18ggQg0KDcknRVFWguAv_3rGVtsTkPsbDajuO5ueQw.ttf")
  }
  object `Montserrat Subrayada` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/montserratsubrayada/v4/nzoCWCz0e9c7Mr2Gl8bbgrJymm6ilkk9f0nDA_sC_qk.ttf")
    def `700`: URL = URL(s"$base/montserratsubrayada/v4/wf-IKpsHcfm0C9uaz9IeGJvEcF1LWArDbGWgKZSH9go.ttf")
  }
  object `Moul` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/moul/v8/Kb0ALQnfyXawP1a_P_gpTQ.ttf")
  }
  object `Moulpali` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/moulpali/v9/diD74BprGhmVkJoerKmrKA.ttf")
  }
  object `Mountains of Christmas` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/mountainsofchristmas/v9/dVGBFPwd6G44IWDbQtPew2Auds3jz1Fxb61CgfaGDr4.ttf")
    def `700`: URL = URL(s"$base/mountainsofchristmas/v9/PymufKtHszoLrY0uiAYKNM9cPTbSBTrQyTa5TWAe3vE.ttf")
  }
  object `Mouse Memoirs` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/mousememoirs/v4/NBFaaJFux_j0AQbAsW3QeH8f0n03UdmQgF_CLvNR2vg.ttf")
  }
  object `Mr Bedfort` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/mrbedfort/v5/81bGgHTRikLs_puEGshl7_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Mr Dafoe` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/mrdafoe/v5/s32Q1S6ZkT7EaX53mUirvQ.ttf")
  }
  object `Mr De Haviland` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/mrdehaviland/v5/fD8y4L6PJ4vqDk7z8Y8e27v4lrhng1lzu7-weKO6cw8.ttf")
  }
  object `Mrs Saint Delafield` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/mrssaintdelafield/v4/vuWagfFT7bj9lFtZOFBwmjHMBelqWf3tJeGyts2SmKU.ttf")
  }
  object `Mrs Sheppards` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/mrssheppards/v5/2WFsWMV3VUeCz6UVH7UjCn8f0n03UdmQgF_CLvNR2vg.ttf")
  }
  object `Mukta` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `200`: URL = URL(s"$base/mukta/v3/tDVdzIQ8YtIPQkpeTPxaRw.ttf")
    def `300`: URL = URL(s"$base/mukta/v3/XBYaFkW7WJ8kqXq2Yt41cw.ttf")
    def `regular`: URL = URL(s"$base/mukta/v3/7dmf9fx1PuHBtLhSPnZzrQ.ttf")
    def `500`: URL = URL(s"$base/mukta/v3/lQPvn1FqPa-GCFx-cAuBHg.ttf")
    def `600`: URL = URL(s"$base/mukta/v3/NcubiFyhit9Cmsn9p9y9Xg.ttf")
    def `700`: URL = URL(s"$base/mukta/v3/TZMKZcvgKiI-bWO9PoMI7w.ttf")
    def `800`: URL = URL(s"$base/mukta/v3/QJVapEVpFpMfDYz2xuPBmQ.ttf")
  }
  object `Mukta Mahee` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "gurmukhi", "latin")
    def `200`: URL = URL(s"$base/muktamahee/v1/kolKnxd29wydc4yTvsM4p0nzyIngrzGjGh22wPb6cGM.ttf")
    def `300`: URL = URL(s"$base/muktamahee/v1/kolKnxd29wydc4yTvsM4py9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/muktamahee/v1/aY_0-ayxlrgq21R8UWTI96CWcynf_cDxXwCLxiixG1c.ttf")
    def `500`: URL = URL(s"$base/muktamahee/v1/kolKnxd29wydc4yTvsM4p8CNfqCYlB_eIx7H1TVXe60.ttf")
    def `600`: URL = URL(s"$base/muktamahee/v1/kolKnxd29wydc4yTvsM4p5Z7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `700`: URL = URL(s"$base/muktamahee/v1/kolKnxd29wydc4yTvsM4p3e1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `800`: URL = URL(s"$base/muktamahee/v1/kolKnxd29wydc4yTvsM4pw89PwPrYLaRFJ-HNCU9NbA.ttf")
  }
  object `Mukta Malar` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "tamil")
    def `200`: URL = URL(s"$base/muktamalar/v2/1-N_tlWLJvzngraerf18eUnzyIngrzGjGh22wPb6cGM.ttf")
    def `300`: URL = URL(s"$base/muktamalar/v2/1-N_tlWLJvzngraerf18eS9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/muktamalar/v2/xdx0fv5-ENz5PCzqiKyrqqCWcynf_cDxXwCLxiixG1c.ttf")
    def `500`: URL = URL(s"$base/muktamalar/v2/1-N_tlWLJvzngraerf18ecCNfqCYlB_eIx7H1TVXe60.ttf")
    def `600`: URL = URL(s"$base/muktamalar/v2/1-N_tlWLJvzngraerf18eZZ7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `700`: URL = URL(s"$base/muktamalar/v2/1-N_tlWLJvzngraerf18eXe1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `800`: URL = URL(s"$base/muktamalar/v2/1-N_tlWLJvzngraerf18eQ89PwPrYLaRFJ-HNCU9NbA.ttf")
  }
  object `Mukta Vaani` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "gujarati", "latin")
    def `200`: URL = URL(s"$base/muktavaani/v3/X9qyC4rK_D9w1AvSv0mw_0nzyIngrzGjGh22wPb6cGM.ttf")
    def `300`: URL = URL(s"$base/muktavaani/v3/X9qyC4rK_D9w1AvSv0mw_y9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/muktavaani/v3/knS0wTOFNOwOD4CZrdHIxKCWcynf_cDxXwCLxiixG1c.ttf")
    def `500`: URL = URL(s"$base/muktavaani/v3/X9qyC4rK_D9w1AvSv0mw_8CNfqCYlB_eIx7H1TVXe60.ttf")
    def `600`: URL = URL(s"$base/muktavaani/v3/X9qyC4rK_D9w1AvSv0mw_5Z7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `700`: URL = URL(s"$base/muktavaani/v3/X9qyC4rK_D9w1AvSv0mw_3e1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `800`: URL = URL(s"$base/muktavaani/v3/X9qyC4rK_D9w1AvSv0mw_w89PwPrYLaRFJ-HNCU9NbA.ttf")
  }
  object `Muli` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `200`: URL = URL(s"$base/muli/v10/59Vi0Dm-YSaaKxRiSKrm0w.ttf")
    def `200italic`: URL = URL(s"$base/muli/v10/ZV7FMcmPA9u6IXfXrqyybA.ttf")
    def `300`: URL = URL(s"$base/muli/v10/VJw4F3ZHRAZ7Hmg3nQu5YQ.ttf")
    def `300italic`: URL = URL(s"$base/muli/v10/s-NKMCru8HiyjEt0ZDoBoA.ttf")
    def `regular`: URL = URL(s"$base/muli/v10/KJiP6KznxbALQgfJcDdPAw.ttf")
    def `italic`: URL = URL(s"$base/muli/v10/Cg0K_IWANs9xkNoxV7H1_w.ttf")
    def `600`: URL = URL(s"$base/muli/v10/O4zVJyE-wzb2CQjcHkw-Xg.ttf")
    def `600italic`: URL = URL(s"$base/muli/v10/xasdEbMzFtnmERn70-CN-A.ttf")
    def `700`: URL = URL(s"$base/muli/v10/n0UfHdYd8jlanPB1sJ0WYQ.ttf")
    def `700italic`: URL = URL(s"$base/muli/v10/9vQS_qOVbbe4j6LkPjCG1g.ttf")
    def `800`: URL = URL(s"$base/muli/v10/QdHPibssQgzNly7JkF7wdw.ttf")
    def `800italic`: URL = URL(s"$base/muli/v10/jbD7XyPvLT1oJBLbEcQmmg.ttf")
    def `900`: URL = URL(s"$base/muli/v10/RcGfHFZUYLsFj9c3uAb4Gg.ttf")
    def `900italic`: URL = URL(s"$base/muli/v10/r4hqeWwjqEvTncJsq5KCSg.ttf")
  }
  object `Mystery Quest` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/mysteryquest/v4/467jJvg0c7HgucvBB9PLDyeUSrabuTpOsMEiRLtKwk0.ttf")
  }
  object `NTR` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/ntr/v4/e7H4ZLtGfVOYyOupo6T12g.ttf")
  }
  object `Neucha` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin", "cyrillic")
    def `regular`: URL = URL(s"$base/neucha/v8/bijdhB-TzQdtpl0ykhGh4Q.ttf")
  }
  object `Neuton` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `200`: URL = URL(s"$base/neuton/v9/DA3Mkew3XqSkPpi1f4tJow.ttf")
    def `300`: URL = URL(s"$base/neuton/v9/xrc_aZ2hx-gdeV0mlY8Vww.ttf")
    def `regular`: URL = URL(s"$base/neuton/v9/9R-MGIOQUdjAVeB6nE6PcQ.ttf")
    def `italic`: URL = URL(s"$base/neuton/v9/uVMT3JOB5BNFi3lgPp6kEg.ttf")
    def `700`: URL = URL(s"$base/neuton/v9/gnWpkWY7DirkKiovncYrfg.ttf")
    def `800`: URL = URL(s"$base/neuton/v9/XPzBQV4lY6enLxQG9cF1jw.ttf")
  }
  object `New Rocker` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/newrocker/v5/EFUWzHJedEkpW399zYOHofesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `News Cycle` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/newscycle/v13/xyMAr8VfiUzIOvS1abHJO_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/newscycle/v13/G28Ny31cr5orMqEQy6ljtwJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Niconne` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/niconne/v6/ZA-mFw2QNXodx5y7kfELBg.ttf")
  }
  object `Nixie One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/nixieone/v7/h6kQfmzm0Shdnp3eswRaqQ.ttf")
  }
  object `Nobile` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/nobile/v8/lC_lPi1ddtN38iXTCRh6ow.ttf")
    def `italic`: URL = URL(s"$base/nobile/v8/vGmrpKzWQQSrb-PR6FWBIA.ttf")
    def `500`: URL = URL(s"$base/nobile/v8/el-1JDqzLC5ePMPiB2COqQ.ttf")
    def `500italic`: URL = URL(s"$base/nobile/v8/y2A1jpvs_uHcnmIZDscDC6CWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/nobile/v8/9p6M-Yrg_r_QPmSD1skrOg.ttf")
    def `700italic`: URL = URL(s"$base/nobile/v8/oQ1eYPaXV638N03KvsNvyKCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Nokora` {
    val category: String = "serif"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/nokora/v10/dRyz1JfnyKPNaRcBNX9F9A.ttf")
    def `700`: URL = URL(s"$base/nokora/v10/QMqqa4QEOhQpiig3cAPmbQ.ttf")
  }
  object `Norican` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/norican/v4/SHnSqhYAWG5sZTWcPzEHig.ttf")
  }
  object `Nosifer` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/nosifer/v5/7eJGoIuHRrtcG00j6CptSA.ttf")
  }
  object `Nothing You Could Do` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/nothingyoucoulddo/v6/jpk1K3jbJoyoK0XKaSyQAf-TpkXjXYGWiJZAEtBRjPU.ttf")
  }
  object `Noticia Text` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/noticiatext/v6/wdyV6x3eKpdeUPQ7BJ5uUC3USBnSvpkopQaUR-2r7iU.ttf")
    def `italic`: URL = URL(s"$base/noticiatext/v6/dAuxVpkYE_Q_IwIm6elsKPMZXuCXbOrAvx5R0IT5Oyo.ttf")
    def `700`: URL = URL(s"$base/noticiatext/v6/pEko-RqEtp45bE2P80AAKUD2ttfZwueP-QU272T9-k4.ttf")
    def `700italic`: URL = URL(s"$base/noticiatext/v6/-rQ7V8ARjf28_b7kRa0JuvAs9-1nE9qOqhChW0m4nDE.ttf")
  }
  object `Noto Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "devanagari", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/notosans/v6/0Ue9FiUJwVhi4NGfHJS5uA.ttf")
    def `italic`: URL = URL(s"$base/notosans/v6/dLcNKMgJ1H5RVoZFraDz0qCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/notosans/v6/PIbvSEyHEdL91QLOQRnZ1y3USBnSvpkopQaUR-2r7iU.ttf")
    def `700italic`: URL = URL(s"$base/notosans/v6/9Z3uUWMRR7crzm1TjRicDne1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Noto Serif` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/notoserif/v4/zW6mc7bC1CWw8dH0yxY8JfesZW2xOQ-xsNqO47m55DA.ttf")
    def `italic`: URL = URL(s"$base/notoserif/v4/HQXBIwLHsOJCNEQeX9kNzy3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/notoserif/v4/lJAvZoKA5NttpPc9yc6lPQJKKGfqHaYFsRG-T3ceEVo.ttf")
    def `700italic`: URL = URL(s"$base/notoserif/v4/Wreg0Be4tcFGM2t6VWytvED2ttfZwueP-QU272T9-k4.ttf")
  }
  object `Nova Cut` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/novacut/v8/6q12jWcBvj0KO2cMRP97tA.ttf")
  }
  object `Nova Flat` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/novaflat/v8/pK7a0CoGzI684qe_XSHBqQ.ttf")
  }
  object `Nova Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("greek", "latin")
    def `regular`: URL = URL(s"$base/novamono/v7/6-SChr5ZIaaasJFBkgrLNw.ttf")
  }
  object `Nova Oval` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/novaoval/v8/VuukVpKP8BwUf8o9W5LYQQ.ttf")
  }
  object `Nova Round` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/novaround/v8/7-cK3Ari_8XYYFgVMxVhDvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Nova Script` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/novascript/v9/dEvxQDLgx1M1TKY-NmBWYaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Nova Slim` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/novaslim/v8/rPYXC81_VL2EW-4CzBX65g.ttf")
  }
  object `Nova Square` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/novasquare/v8/BcBzXoaDzYX78rquGXVuSqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Numans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/numans/v6/g5snI2p6OEjjTNmTHyBdiQ.ttf")
  }
  object `Nunito` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `200`: URL = URL(s"$base/nunito/v8/xtWPP_05UbsUNY9Kdgwt_w.ttf")
    def `200italic`: URL = URL(s"$base/nunito/v8/EbyHzRpZ3jx6yC2BjZCsQqCWcynf_cDxXwCLxiixG1c.ttf")
    def `300`: URL = URL(s"$base/nunito/v8/zXQvrWBJqUooM7Xv98MrQw.ttf")
    def `300italic`: URL = URL(s"$base/nunito/v8/4BFBxBQCHZfUELdybShAwKCWcynf_cDxXwCLxiixG1c.ttf")
    def `regular`: URL = URL(s"$base/nunito/v8/ySZTeT3IuzJj0GK6uGpbBg.ttf")
    def `italic`: URL = URL(s"$base/nunito/v8/NZNWFpgsC6hUUE2c03CLoQ.ttf")
    def `600`: URL = URL(s"$base/nunito/v8/B4-BGlpEzQ4WP-D3Zi0PRQ.ttf")
    def `600italic`: URL = URL(s"$base/nunito/v8/7SyYp8NBEeMV4V7MAKJnZ6CWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/nunito/v8/aEdlqgMuYbpe4U3TnqOQMA.ttf")
    def `700italic`: URL = URL(s"$base/nunito/v8/4cHctiCFYmTpv-a6b6vYsKCWcynf_cDxXwCLxiixG1c.ttf")
    def `800`: URL = URL(s"$base/nunito/v8/GtGHSZwowZF8a9-GAsh20A.ttf")
    def `800italic`: URL = URL(s"$base/nunito/v8/2TsLUs-EFIKsriUeVTl6nKCWcynf_cDxXwCLxiixG1c.ttf")
    def `900`: URL = URL(s"$base/nunito/v8/QVvFcvcPoFKH9Q71V4WsjQ.ttf")
    def `900italic`: URL = URL(s"$base/nunito/v8/cIxOb6Vw6BqF9ZoAlenp3qCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Nunito Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `200`: URL = URL(s"$base/nunitosans/v2/XvilrNtBQKRMeiqSPzEFHUnzyIngrzGjGh22wPb6cGM.ttf")
    def `200italic`: URL = URL(s"$base/nunitosans/v2/ORCQQ32ldzJ6bFTh_zXqV02YN_dW5g9CXH6iztHQiR4.ttf")
    def `300`: URL = URL(s"$base/nunitosans/v2/XvilrNtBQKRMeiqSPzEFHS9-WlPSxbfiI49GsXo3q0g.ttf")
    def `300italic`: URL = URL(s"$base/nunitosans/v2/ORCQQ32ldzJ6bFTh_zXqV2o9eWDfYYxG3A176Zl7aIg.ttf")
    def `regular`: URL = URL(s"$base/nunitosans/v2/qDS9UelBO44ppiSawKNcIKCWcynf_cDxXwCLxiixG1c.ttf")
    def `italic`: URL = URL(s"$base/nunitosans/v2/w9sy7IRyDFLWACdltghEwUeOrDcLawS7-ssYqLr2Xp4.ttf")
    def `600`: URL = URL(s"$base/nunitosans/v2/XvilrNtBQKRMeiqSPzEFHZZ7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `600italic`: URL = URL(s"$base/nunitosans/v2/ORCQQ32ldzJ6bFTh_zXqV5e6We3S5L6hKLscKpOkmlo.ttf")
    def `700`: URL = URL(s"$base/nunitosans/v2/XvilrNtBQKRMeiqSPzEFHXe1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `700italic`: URL = URL(s"$base/nunitosans/v2/ORCQQ32ldzJ6bFTh_zXqV8_zJjSACmk0BRPxQqhnNLU.ttf")
    def `800`: URL = URL(s"$base/nunitosans/v2/XvilrNtBQKRMeiqSPzEFHQ89PwPrYLaRFJ-HNCU9NbA.ttf")
    def `800italic`: URL = URL(s"$base/nunitosans/v2/ORCQQ32ldzJ6bFTh_zXqVyad_7rtf4IdDfsLVg-2OV4.ttf")
    def `900`: URL = URL(s"$base/nunitosans/v2/XvilrNtBQKRMeiqSPzEFHSenaqEuufTBk9XMKnKmgDA.ttf")
    def `900italic`: URL = URL(s"$base/nunitosans/v2/ORCQQ32ldzJ6bFTh_zXqV0_yTOUGsoC54csJe1b-IRw.ttf")
  }
  object `Odor Mean Chey` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/odormeanchey/v8/GK3E7EjPoBkeZhYshGFo0eVKG8sq4NyGgdteJLvqLDs.ttf")
  }
  object `Offside` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/offside/v4/v0C913SB8wqQUvcu1faUqw.ttf")
  }
  object `Old Standard TT` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/oldstandardtt/v8/n6RTCDcIPWSE8UNBa4k-DLcB5jyhm1VsHs65c3QNDr0.ttf")
    def `italic`: URL = URL(s"$base/oldstandardtt/v8/QQT_AUSp4AV4dpJfIN7U5PWrQzeMtsHf8QsWQ2cZg3c.ttf")
    def `700`: URL = URL(s"$base/oldstandardtt/v8/5Ywdce7XEbTSbxs__4X1_HJqbZqK7TdZ58X80Q_Lw8Y.ttf")
  }
  object `Oldenburg` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/oldenburg/v4/dqA_M_uoCVXZbCO-oKBTnQ.ttf")
  }
  object `Oleo Script` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/oleoscript/v5/21stZcmPyzbQVXtmGegyqKCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/oleoscript/v5/hudNQFKFl98JdNnlo363fne1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Oleo Script Swash Caps` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/oleoscriptswashcaps/v4/vdWhGqsBUAP-FF3NOYTe4iMF4kXAPemmyaDpMXQ31P0.ttf")
    def `700`: URL = URL(s"$base/oleoscriptswashcaps/v4/HMO3ftxA9AU5floml9c755reFYaXZ4zuJXJ8fr8OO1g.ttf")
  }
  object `Open Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `300`: URL = URL(s"$base/opensans/v14/DXI1ORHCpsQm3Vp6mXoaTS3USBnSvpkopQaUR-2r7iU.ttf")
    def `300italic`: URL = URL(s"$base/opensans/v14/PRmiXeptR36kaC0GEAetxi9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/opensans/v14/IgZJs4-7SA1XX_edsoXWog.ttf")
    def `italic`: URL = URL(s"$base/opensans/v14/O4NhV7_qs9r9seTo7fnsVKCWcynf_cDxXwCLxiixG1c.ttf")
    def `600`: URL = URL(s"$base/opensans/v14/MTP_ySUJH_bn48VBG8sNSi3USBnSvpkopQaUR-2r7iU.ttf")
    def `600italic`: URL = URL(s"$base/opensans/v14/PRmiXeptR36kaC0GEAetxpZ7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `700`: URL = URL(s"$base/opensans/v14/k3k702ZOKiLJc3WVjuplzC3USBnSvpkopQaUR-2r7iU.ttf")
    def `700italic`: URL = URL(s"$base/opensans/v14/PRmiXeptR36kaC0GEAetxne1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `800`: URL = URL(s"$base/opensans/v14/EInbV5DfGHOiMmvb1Xr-hi3USBnSvpkopQaUR-2r7iU.ttf")
    def `800italic`: URL = URL(s"$base/opensans/v14/PRmiXeptR36kaC0GEAetxg89PwPrYLaRFJ-HNCU9NbA.ttf")
  }
  object `Open Sans Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `300`: URL = URL(s"$base/opensanscondensed/v11/gk5FxslNkTTHtojXrkp-xEMwSSh38KQVJx4ABtsZTnA.ttf")
    def `300italic`: URL = URL(s"$base/opensanscondensed/v11/jIXlqT1WKafUSwj6s9AzV4_LkTZ_uhAwfmGJ084hlvM.ttf")
    def `700`: URL = URL(s"$base/opensanscondensed/v11/gk5FxslNkTTHtojXrkp-xBEM87DM3yorPOrvA-vB930.ttf")
  }
  object `Oranienbaum` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/oranienbaum/v5/M98jYwCSn0PaFhXXgviCoaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Orbitron` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/orbitron/v8/DY8swouAZjR3RaUPRf0HDQ.ttf")
    def `500`: URL = URL(s"$base/orbitron/v8/p-y_ffzMdo5JN_7ia0vYEqCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/orbitron/v8/PS9_6SLkY1Y6OgPO3APr6qCWcynf_cDxXwCLxiixG1c.ttf")
    def `900`: URL = URL(s"$base/orbitron/v8/2I3-8i9hT294TE_pyjy9SaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Oregano` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/oregano/v4/UiLhqNixVv2EpjRoBG6axA.ttf")
    def `italic`: URL = URL(s"$base/oregano/v4/_iwqGEht6XsAuEaCbYG64Q.ttf")
  }
  object `Orienta` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/orienta/v4/_NKSk93mMs0xsqtfjCsB3Q.ttf")
  }
  object `Original Surfer` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/originalsurfer/v5/gdHw6HpSIN4D6Xt7pi1-qIkEz33TDwAZczo_6fY7eg0.ttf")
  }
  object `Oswald` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "cyrillic")
    def `200`: URL = URL(s"$base/oswald/v14/NFBt4e1rewQyDPftazXlBw.ttf")
    def `300`: URL = URL(s"$base/oswald/v14/y3tZpCdiRD4oNRRYFcAR5Q.ttf")
    def `regular`: URL = URL(s"$base/oswald/v14/uLEd2g2vJglLPfsBF91DCg.ttf")
    def `500`: URL = URL(s"$base/oswald/v14/wrHWShuZ7ELtrnx0cnkzXw.ttf")
    def `600`: URL = URL(s"$base/oswald/v14/JNlamLn5ALW8eKp46JLlQA.ttf")
    def `700`: URL = URL(s"$base/oswald/v14/7wj8ldV_5Ti37rHa0m1DDw.ttf")
  }
  object `Over the Rainbow` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/overtherainbow/v7/6gp-gkpI2kie2dHQQLM2jQBdxkZd83xOSx-PAQ2QmiI.ttf")
  }
  object `Overlock` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/overlock/v6/Z8oYsGi88-E1cUB8YBFMAg.ttf")
    def `italic`: URL = URL(s"$base/overlock/v6/rq6EacukHROOBrFrK_zF6_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/overlock/v6/Fexr8SqXM8Bm_gEVUA7AKaCWcynf_cDxXwCLxiixG1c.ttf")
    def `700italic`: URL = URL(s"$base/overlock/v6/wFWnYgeXKYBks6gEUwYnfAJKKGfqHaYFsRG-T3ceEVo.ttf")
    def `900`: URL = URL(s"$base/overlock/v6/YPJCVTT8ZbG3899l_-KIGqCWcynf_cDxXwCLxiixG1c.ttf")
    def `900italic`: URL = URL(s"$base/overlock/v6/iOZhxT2zlg7W5ij_lb-oDp0EAVxt0G0biEntp43Qt6E.ttf")
  }
  object `Overlock SC` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/overlocksc/v5/8D7HYDsvS_g1GhBnlHzgzaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Overpass` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `100`: URL = URL(s"$base/overpass/v1/ywiUWFAguOSxQn0FFeOdWPesZW2xOQ-xsNqO47m55DA.ttf")
    def `100italic`: URL = URL(s"$base/overpass/v1/thg-CA5nD5lyYWLwXbqXXi3USBnSvpkopQaUR-2r7iU.ttf")
    def `200`: URL = URL(s"$base/overpass/v1/WrbWRQuVnXt_EslNm2vBt6CWcynf_cDxXwCLxiixG1c.ttf")
    def `200italic`: URL = URL(s"$base/overpass/v1/Eyj9nfhrJ71MmfPNEwqE02eudeTO44zf-ht3k-KNzwg.ttf")
    def `300`: URL = URL(s"$base/overpass/v1/nqDUqkXaOp0r1j0uaM5VUaCWcynf_cDxXwCLxiixG1c.ttf")
    def `300italic`: URL = URL(s"$base/overpass/v1/R77XtXNe7WC4SXZBLWmy80eOrDcLawS7-ssYqLr2Xp4.ttf")
    def `regular`: URL = URL(s"$base/overpass/v1/1fNed5evrqtu4ZjkbTnCRw.ttf")
    def `italic`: URL = URL(s"$base/overpass/v1/lG-Dpm66OH9lPHbYTnITSvesZW2xOQ-xsNqO47m55DA.ttf")
    def `600`: URL = URL(s"$base/overpass/v1/-GUou309ST_HAHIhkHjkz6CWcynf_cDxXwCLxiixG1c.ttf")
    def `600italic`: URL = URL(s"$base/overpass/v1/aPYi-s_WVz-zuU4TsgAEjvpTEJqju4Hz1txDWij77d4.ttf")
    def `700`: URL = URL(s"$base/overpass/v1/sBTg-F6_A1NQLJPfW5I7Q6CWcynf_cDxXwCLxiixG1c.ttf")
    def `700italic`: URL = URL(s"$base/overpass/v1/E5UsN4VY1e_Twk_bY6TpQAJKKGfqHaYFsRG-T3ceEVo.ttf")
    def `800`: URL = URL(s"$base/overpass/v1/YeZIq305iGwGCyZbaiEbVqCWcynf_cDxXwCLxiixG1c.ttf")
    def `800italic`: URL = URL(s"$base/overpass/v1/j6xjlCEDoKw-D0Co-88A9Kk3bhPBSBJ0bSJQ6acL-0g.ttf")
    def `900`: URL = URL(s"$base/overpass/v1/4lJ8BLdIYI_B9rFwoB4zO6CWcynf_cDxXwCLxiixG1c.ttf")
    def `900italic`: URL = URL(s"$base/overpass/v1/SegM1mSQIRZG2pJwM_2Nm50EAVxt0G0biEntp43Qt6E.ttf")
  }
  object `Overpass Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/overpassmono/v2/JEQ6tXkANEo2u0wZ-MTOPEW1P7_iUBn_wmH5B9p-CEw.ttf")
    def `regular`: URL = URL(s"$base/overpassmono/v2/MarHoIqW2hy_po97b_wS9uV_5zh5b-_HiooIRUBwn1A.ttf")
    def `600`: URL = URL(s"$base/overpassmono/v2/JEQ6tXkANEo2u0wZ-MTOPCvU6mrnWf1MVbTZ5LZwmOY.ttf")
    def `700`: URL = URL(s"$base/overpassmono/v2/JEQ6tXkANEo2u0wZ-MTOPO-Cz_5MeePnXDAcLNWyBME.ttf")
  }
  object `Ovo` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/ovo/v7/mFg27dimu3s9t09qjCwB1g.ttf")
  }
  object `Oxygen` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/oxygen/v6/lZ31r0bR1Bzt_DfGZu1S8A.ttf")
    def `regular`: URL = URL(s"$base/oxygen/v6/uhoyAE7XlQL22abzQieHjw.ttf")
    def `700`: URL = URL(s"$base/oxygen/v6/yLqkmDwuNtt5pSqsJmhyrg.ttf")
  }
  object `Oxygen Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/oxygenmono/v4/DigTu7k4b7OmM8ubt1Qza6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `PT Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/ptmono/v4/QUbM8H9yJK5NhpQ0REO6Wg.ttf")
  }
  object `PT Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/ptsans/v8/UFoEz2uiuMypUGZL1NKoeg.ttf")
    def `italic`: URL = URL(s"$base/ptsans/v8/yls9EYWOd496wiu7qzfgNg.ttf")
    def `700`: URL = URL(s"$base/ptsans/v8/F51BEgHuR0tYHxF0bD4vwvesZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/ptsans/v8/lILlYDvubYemzYzN7GbLkC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `PT Sans Caption` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/ptsanscaption/v9/OXYTDOzBcXU8MTNBvBHeSW8by34Z3mUMtM-o4y-SHCY.ttf")
    def `700`: URL = URL(s"$base/ptsanscaption/v9/Q-gJrFokeE7JydPpxASt25tc0eyfI4QDEsobEEpk_hA.ttf")
  }
  object `PT Sans Narrow` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/ptsansnarrow/v7/UyYrYy3ltEffJV9QueSi4ZTvAuddT2xDMbdz0mdLyZY.ttf")
    def `700`: URL = URL(s"$base/ptsansnarrow/v7/Q_pTky3Sc3ubRibGToTAYsLtdzs3iyjn_YuT226ZsLU.ttf")
  }
  object `PT Serif` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/ptserif/v8/sAo427rn3-QL9sWCbMZXhA.ttf")
    def `italic`: URL = URL(s"$base/ptserif/v8/9khWhKzhpkH0OkNnBKS3n_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/ptserif/v8/kyZw18tqQ5if-_wpmxxOeKCWcynf_cDxXwCLxiixG1c.ttf")
    def `700italic`: URL = URL(s"$base/ptserif/v8/Foydq9xJp--nfYIx2TBz9QJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `PT Serif Caption` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/ptserifcaption/v8/7xkFOeTxxO1GMC1suOUYWVsRioCqs5fohhaYel24W3k.ttf")
    def `italic`: URL = URL(s"$base/ptserifcaption/v8/0kfPsmrmTSgiec7u_Wa0DB1mqvzPHelJwRcF_s_EUM0.ttf")
  }
  object `Pacifico` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/pacifico/v9/GIrpeRY1r5CzbfL8r182lw.ttf")
  }
  object `Padauk` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("myanmar", "latin")
    def `regular`: URL = URL(s"$base/padauk/v3/WdTk6igBu-qn4v8naF9hGQ.ttf")
    def `700`: URL = URL(s"$base/padauk/v3/XUBO5k0emPIVnqCcQCcEpg.ttf")
  }
  object `Palanquin` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `100`: URL = URL(s"$base/palanquin/v2/Hu0eGDVGK_g4saUFu6AK3KCWcynf_cDxXwCLxiixG1c.ttf")
    def `200`: URL = URL(s"$base/palanquin/v2/pqXYXD7-VI5ezTjeqQOcyC3USBnSvpkopQaUR-2r7iU.ttf")
    def `300`: URL = URL(s"$base/palanquin/v2/c0-J5OCAagpFCKkKraz-Ey3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/palanquin/v2/xCwBUoAEV0kzCDwerAZ0Aw.ttf")
    def `500`: URL = URL(s"$base/palanquin/v2/wLvvkEcZMKy95afLWh2EfC3USBnSvpkopQaUR-2r7iU.ttf")
    def `600`: URL = URL(s"$base/palanquin/v2/405UIAv95_yZkCECrH6y-i3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/palanquin/v2/-UtkePo3NFvxEN3rGCtTvi3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Palanquin Dark` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/palanquindark/v2/PamTqrrgbBh_M3702w39rOfChn3JSg5yz_Q_xmrKQN0.ttf")
    def `500`: URL = URL(s"$base/palanquindark/v2/iXyBGf5UbFUu6BG8hOY-maMZTo-EwKMRQt3RWHocLi0.ttf")
    def `600`: URL = URL(s"$base/palanquindark/v2/iXyBGf5UbFUu6BG8hOY-mVNxaunw8i4Gywrk2SigRnk.ttf")
    def `700`: URL = URL(s"$base/palanquindark/v2/iXyBGf5UbFUu6BG8hOY-mWToair6W0TEE44XrlfKbiM.ttf")
  }
  object `Pangolin` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/pangolin/v2/i2W796ne6lveehHXs8AFGA.ttf")
  }
  object `Paprika` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/paprika/v4/b-VpyoRSieBdB5BPJVF8HQ.ttf")
  }
  object `Parisienne` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/parisienne/v4/TW74B5QISJNx9moxGlmJfvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Passero One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/passeroone/v8/Yc-7nH5deCCv9Ed0MMnAQqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Passion One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/passionone/v7/1UIK1tg3bKJ4J3o35M4heqCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/passionone/v7/feOcYDy2R-f3Ysy72PYJ2ne1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `900`: URL = URL(s"$base/passionone/v7/feOcYDy2R-f3Ysy72PYJ2ienaqEuufTBk9XMKnKmgDA.ttf")
  }
  object `Pathway Gothic One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/pathwaygothicone/v5/Lqv9ztoTUV8Q0FmQZzPqaA6A6xIYD7vYcYDop1i-K-c.ttf")
  }
  object `Patrick Hand` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/patrickhand/v10/9BG3JJgt_HlF3NpEUehL0C3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Patrick Hand SC` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/patrickhandsc/v4/OYFWCgfCR-7uHIovjUZXsbAgSRh1LpJXlLfl8IbsmHg.ttf")
  }
  object `Pattaya` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "cyrillic", "thai")
    def `regular`: URL = URL(s"$base/pattaya/v1/sJEout1xdD7J8H-1H81pIQ.ttf")
  }
  object `Patua One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/patuaone/v6/njZwotTYjswR4qdhsW-kJw.ttf")
  }
  object `Pavanam` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "tamil")
    def `regular`: URL = URL(s"$base/pavanam/v1/C7yuEhNK5oftNLSL3I0bGw.ttf")
  }
  object `Paytone One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/paytoneone/v9/3WCxC7JAJjQHQVoIE0ZwvqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Peddana` {
    val category: String = "serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/peddana/v4/zaSZuj_GhmC8AOTugOROnA.ttf")
  }
  object `Peralta` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/peralta/v4/cTJX5KEuc0GKRU9NXSm-8Q.ttf")
  }
  object `Permanent Marker` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/permanentmarker/v6/9vYsg5VgPHKK8SXYbf3sMol14xj5tdg9OHF8w4E7StQ.ttf")
  }
  object `Petit Formal Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/petitformalscript/v4/OEZwr2-ovBsq2n3ACCKoEvVPl2Gjtxj0D6F7QLy1VQc.ttf")
  }
  object `Petrona` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/petrona/v5/nnQwxlP6dhrGovYEFtemTg.ttf")
  }
  object `Philosopher` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/philosopher/v8/oZLTrB9jmJsyV0u_T0TKEaCWcynf_cDxXwCLxiixG1c.ttf")
    def `italic`: URL = URL(s"$base/philosopher/v8/_9Hnc_gz9k7Qq6uKaeHKmUeOrDcLawS7-ssYqLr2Xp4.ttf")
    def `700`: URL = URL(s"$base/philosopher/v8/napvkewXG9Gqby5vwGHICHe1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `700italic`: URL = URL(s"$base/philosopher/v8/PuKlryTcvTj7-qZWfLCFIM_zJjSACmk0BRPxQqhnNLU.ttf")
  }
  object `Piedra` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/piedra/v5/owf-AvEEyAj9LJ2tVZ_3Mw.ttf")
  }
  object `Pinyon Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/pinyonscript/v6/TzghnhfCn7TuE73f-CBQ0CeUSrabuTpOsMEiRLtKwk0.ttf")
  }
  object `Pirata One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/pirataone/v4/WnbD86B4vB2ckYcL7oxuhvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Plaster` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/plaster/v8/O4QG9Z5116CXyfJdR9zxLw.ttf")
  }
  object `Play` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/play/v8/GWvfObW8LhtsOX333MCpBg.ttf")
    def `700`: URL = URL(s"$base/play/v8/crPhg6I0alLI-MpB3vW-zw.ttf")
  }
  object `Playball` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/playball/v6/3hOFiQm_EUzycTpcN9uz4w.ttf")
  }
  object `Playfair Display` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/playfairdisplay/v11/2NBgzUtEeyB-Xtpr9bm1CV6uyC_qD11hrFQ6EGgTJWI.ttf")
    def `italic`: URL = URL(s"$base/playfairdisplay/v11/9MkijrV-dEJ0-_NWV7E6NzMsbnvDNEBX25F5HWk9AhI.ttf")
    def `700`: URL = URL(s"$base/playfairdisplay/v11/UC3ZEjagJi85gF9qFaBgICsv6SrURqJprbhH_C1Mw8w.ttf")
    def `700italic`: URL = URL(s"$base/playfairdisplay/v11/n7G4PqJvFP2Kubl0VBLDECsYW3XoOVcYyYdp9NzzS9E.ttf")
    def `900`: URL = URL(s"$base/playfairdisplay/v11/UC3ZEjagJi85gF9qFaBgIKqwMe2wjvZrAR44M0BJZ48.ttf")
    def `900italic`: URL = URL(s"$base/playfairdisplay/v11/n7G4PqJvFP2Kubl0VBLDEC0JfJ4xmm7j1kL6D7mPxrA.ttf")
  }
  object `Playfair Display SC` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/playfairdisplaysc/v5/G0-tvBxd4eQRdwFKB8dRkcpjYTDWIvcAwAccqeW9uNM.ttf")
    def `italic`: URL = URL(s"$base/playfairdisplaysc/v5/myuYiFR-4NTrUT4w6TKls2klJsJYggW8rlNoTOTuau0.ttf")
    def `700`: URL = URL(s"$base/playfairdisplaysc/v5/5ggqGkvWJU_TtW2W8cEubA-Amcyomnuy4WsCiPxGHjw.ttf")
    def `700italic`: URL = URL(s"$base/playfairdisplaysc/v5/6X0OQrQhEEnPo56RalREX4krgPi80XvBcbTwmz-rgmU.ttf")
    def `900`: URL = URL(s"$base/playfairdisplaysc/v5/5ggqGkvWJU_TtW2W8cEubKXL3C32k275YmX_AcBPZ7w.ttf")
    def `900italic`: URL = URL(s"$base/playfairdisplaysc/v5/6X0OQrQhEEnPo56RalREX8Zag2q3ssKz8uH1RU4a9gs.ttf")
  }
  object `Podkova` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/podkova/v10/eylljyGVfB8ZUQjYY3WZRQ.ttf")
    def `500`: URL = URL(s"$base/podkova/v10/8MkhKmKhl0HgqBeKkV0pmvesZW2xOQ-xsNqO47m55DA.ttf")
    def `600`: URL = URL(s"$base/podkova/v10/921xSzgq6uUBjPZXn2IH0PesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/podkova/v10/SqW4aa8m_KVrOgYSydQ33vesZW2xOQ-xsNqO47m55DA.ttf")
    def `800`: URL = URL(s"$base/podkova/v10/ObfRYfRr58NtktZuAa1VhfesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Poiret One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/poiretone/v4/dWcYed048E5gHGDIt8i1CPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Poller One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/pollerone/v6/dkctmDlTPcZ6boC8662RA_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Poly` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/poly/v7/bcMAuiacS2qkd54BcwW6_Q.ttf")
    def `italic`: URL = URL(s"$base/poly/v7/Zkx-eIlZSjKUrPGYhV5PeA.ttf")
  }
  object `Pompiere` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/pompiere/v6/o_va2p9CD5JfmFohAkGZIA.ttf")
  }
  object `Pontano Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/pontanosans/v4/gTHiwyxi6S7iiHpqAoiE3C3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Poppins` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `100`: URL = URL(s"$base/poppins/v4/J_Uo-RBVJYTcfQfJqaBpiA.ttf")
    def `100italic`: URL = URL(s"$base/poppins/v4/AgVJ3FHPsWMHPMmRYdKWQKCWcynf_cDxXwCLxiixG1c.ttf")
    def `200`: URL = URL(s"$base/poppins/v4/iG8N2M28abs14mWAmy9C8vesZW2xOQ-xsNqO47m55DA.ttf")
    def `200italic`: URL = URL(s"$base/poppins/v4/-GlaWpWcSgdVagNuOGuFKS3USBnSvpkopQaUR-2r7iU.ttf")
    def `300`: URL = URL(s"$base/poppins/v4/VIeViZ2fPtYBt3B2fQZplvesZW2xOQ-xsNqO47m55DA.ttf")
    def `300italic`: URL = URL(s"$base/poppins/v4/QmRKoWaGfh304P2oApdMLS3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/poppins/v4/hlvAxH6aIdOjWlLzgm0jqg.ttf")
    def `italic`: URL = URL(s"$base/poppins/v4/3cZiAJEeIIIKVRjGXr9qVg.ttf")
    def `500`: URL = URL(s"$base/poppins/v4/4WGKlFyjcmCFVl8pRsgZ9vesZW2xOQ-xsNqO47m55DA.ttf")
    def `500italic`: URL = URL(s"$base/poppins/v4/ZswPVmYNMYXIwQy7Wnzcyi3USBnSvpkopQaUR-2r7iU.ttf")
    def `600`: URL = URL(s"$base/poppins/v4/-zOABrCWORC3lyDh-ajNnPesZW2xOQ-xsNqO47m55DA.ttf")
    def `600italic`: URL = URL(s"$base/poppins/v4/RbebACOccNN-5ixkDIVLjS3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/poppins/v4/8JitanEsk5aDh7mDYs-fYfesZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/poppins/v4/c4FPK8_hIFKoX59qcGwdCi3USBnSvpkopQaUR-2r7iU.ttf")
    def `800`: URL = URL(s"$base/poppins/v4/vVhctzCFjekFM26ZXVvlAvesZW2xOQ-xsNqO47m55DA.ttf")
    def `800italic`: URL = URL(s"$base/poppins/v4/nhuxdF7XMkIXmkGDadS6EC3USBnSvpkopQaUR-2r7iU.ttf")
    def `900`: URL = URL(s"$base/poppins/v4/7WUVvX7AIKpgWf6w-guTPfesZW2xOQ-xsNqO47m55DA.ttf")
    def `900italic`: URL = URL(s"$base/poppins/v4/Lmn8WRFdDq3MeV9dyKOb_y3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Port Lligat Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/portlligatsans/v5/CUEdhRk7oC7up0p6t0g4P6mASEpx5X0ZpsuJOuvfOGA.ttf")
  }
  object `Port Lligat Slab` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/portlligatslab/v5/CUEdhRk7oC7up0p6t0g4PxLSPACXvawUYCBEnHsOe30.ttf")
  }
  object `Pragati Narrow` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/pragatinarrow/v2/HzG2TfC862qPNsZsV_djPpTvAuddT2xDMbdz0mdLyZY.ttf")
    def `700`: URL = URL(s"$base/pragatinarrow/v2/DnSI1zRkc0CY-hI5SC3q3MLtdzs3iyjn_YuT226ZsLU.ttf")
  }
  object `Prata` {
    val category: String = "serif"
    val subsets: Set[String] = Set("cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/prata/v7/3gmx8r842loRRm9iQkCDGg.ttf")
  }
  object `Preahvihear` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/preahvihear/v8/82tDI-xTc53CxxOzEG4hDaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Press Start 2P` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/pressstart2p/v5/8Lg6LX8-ntOHUQnvQ0E7o1jfl3W46Sz5gOkEVhcFWF4.ttf")
  }
  object `Pridi` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `200`: URL = URL(s"$base/pridi/v2/WvKJ-kflGuELyK4uQzpYIA.ttf")
    def `300`: URL = URL(s"$base/pridi/v2/Ihwk-OGVFS69PINILdqAjQ.ttf")
    def `regular`: URL = URL(s"$base/pridi/v2/Mau018Ghi7LJX7FkGYCZAQ.ttf")
    def `500`: URL = URL(s"$base/pridi/v2/dPNOrMxU-HjLo-fvkFydsQ.ttf")
    def `600`: URL = URL(s"$base/pridi/v2/J0i5OZxX07KC4mby5RjNbg.ttf")
    def `700`: URL = URL(s"$base/pridi/v2/UhCy4jDDJttTB8k8rtWadg.ttf")
  }
  object `Princess Sofia` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/princesssofia/v4/8g5l8r9BM0t1QsXLTajDe-wjmA7ie-lFcByzHGRhCIg.ttf")
  }
  object `Prociono` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/prociono/v6/43ZYDHWogdFeNBWTl6ksmw.ttf")
  }
  object `Prompt` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `100`: URL = URL(s"$base/prompt/v1/ltjX-trOmfS-yKy_awt70g.ttf")
    def `100italic`: URL = URL(s"$base/prompt/v1/KvTeArBpVb-tA2mahV6Jk_esZW2xOQ-xsNqO47m55DA.ttf")
    def `200`: URL = URL(s"$base/prompt/v1/MNB_CVkbfYHFMWX_UbDC2Q.ttf")
    def `200italic`: URL = URL(s"$base/prompt/v1/NR0JuXzzCDKpLNVhfyEAiaCWcynf_cDxXwCLxiixG1c.ttf")
    def `300`: URL = URL(s"$base/prompt/v1/LzifakiWysr3N3OoAdbdpg.ttf")
    def `300italic`: URL = URL(s"$base/prompt/v1/ir8BhbeDHM-qnbo-tnpmt6CWcynf_cDxXwCLxiixG1c.ttf")
    def `regular`: URL = URL(s"$base/prompt/v1/nDo1rQFnTFNua4cp-OnD2A.ttf")
    def `italic`: URL = URL(s"$base/prompt/v1/ZD4khIP924SU2fRYOJkraQ.ttf")
    def `500`: URL = URL(s"$base/prompt/v1/w31OY1otplAgr5iZ21K7Fg.ttf")
    def `500italic`: URL = URL(s"$base/prompt/v1/dfaeaRx00u9arVHsaDjliaCWcynf_cDxXwCLxiixG1c.ttf")
    def `600`: URL = URL(s"$base/prompt/v1/uUrJjg1BGaIb6CAOlUIp9g.ttf")
    def `600italic`: URL = URL(s"$base/prompt/v1/CJUBMsoNNHMMdFRxm-n7p6CWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/prompt/v1/HdM_epiStzshOr-49ubVyg.ttf")
    def `700italic`: URL = URL(s"$base/prompt/v1/GtXRH7QWy3aLCHoJuR5WIKCWcynf_cDxXwCLxiixG1c.ttf")
    def `800`: URL = URL(s"$base/prompt/v1/GF9cOamDd7mYPHNW1nZLKg.ttf")
    def `800italic`: URL = URL(s"$base/prompt/v1/kBLgnnEB-VXkOLFCc0pzwqCWcynf_cDxXwCLxiixG1c.ttf")
    def `900`: URL = URL(s"$base/prompt/v1/KFgmbwHbRBQb28VFhH3c8Q.ttf")
    def `900italic`: URL = URL(s"$base/prompt/v1/qjrOe-lEPwDDeUu5g6q_DaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Prosto One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/prostoone/v5/bsqnAElAqk9kX7eABTRFJPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Proza Libre` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/prozalibre/v1/Hg11OrfE1P_U6mKmrZPknKCWcynf_cDxXwCLxiixG1c.ttf")
    def `italic`: URL = URL(s"$base/prozalibre/v1/ClQTew5IUT7yKo8vyspLxEeOrDcLawS7-ssYqLr2Xp4.ttf")
    def `500`: URL = URL(s"$base/prozalibre/v1/4gjxWDPA6RMWrIls_qgQBsCNfqCYlB_eIx7H1TVXe60.ttf")
    def `500italic`: URL = URL(s"$base/prozalibre/v1/rWq3Qp4ZlPGKduc1qkgLHGnWRcJAYo5PSCx8UfGMHCI.ttf")
    def `600`: URL = URL(s"$base/prozalibre/v1/4gjxWDPA6RMWrIls_qgQBpZ7xm-Bj30Bj2KNdXDzSZg.ttf")
    def `600italic`: URL = URL(s"$base/prozalibre/v1/rWq3Qp4ZlPGKduc1qkgLHJe6We3S5L6hKLscKpOkmlo.ttf")
    def `700`: URL = URL(s"$base/prozalibre/v1/4gjxWDPA6RMWrIls_qgQBne1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `700italic`: URL = URL(s"$base/prozalibre/v1/rWq3Qp4ZlPGKduc1qkgLHM_zJjSACmk0BRPxQqhnNLU.ttf")
    def `800`: URL = URL(s"$base/prozalibre/v1/4gjxWDPA6RMWrIls_qgQBg89PwPrYLaRFJ-HNCU9NbA.ttf")
    def `800italic`: URL = URL(s"$base/prozalibre/v1/rWq3Qp4ZlPGKduc1qkgLHCad_7rtf4IdDfsLVg-2OV4.ttf")
  }
  object `Puritan` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/puritan/v8/wv_RtgVBSCn-or2MC0n4Kg.ttf")
    def `italic`: URL = URL(s"$base/puritan/v8/BqZX8Tp200LeMv1KlzXgLQ.ttf")
    def `700`: URL = URL(s"$base/puritan/v8/pJS2SdwI0SCiVnO0iQSFT_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/puritan/v8/rFG3XkMJL75nUNZwCEIJqC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Purple Purse` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/purplepurse/v5/Q5heFUrdmei9axbMITxxxS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Quando` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/quando/v5/03nDiEZuO2-h3xvtG6UmHg.ttf")
  }
  object `Quantico` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/quantico/v5/pwSnP8Xpaix2rIz99HrSlQ.ttf")
    def `italic`: URL = URL(s"$base/quantico/v5/KQhDd2OsZi6HiITUeFQ2U_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/quantico/v5/OVZZzjcZ3Hkq2ojVcUtDjaCWcynf_cDxXwCLxiixG1c.ttf")
    def `700italic`: URL = URL(s"$base/quantico/v5/HeCYRcZbdRso3ZUu01ELbQJKKGfqHaYFsRG-T3ceEVo.ttf")
  }
  object `Quattrocento` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/quattrocento/v8/WZDISdyil4HsmirlOdBRFC3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/quattrocento/v8/Uvi-cRwyvqFpl9j3oT2mqkD2ttfZwueP-QU272T9-k4.ttf")
  }
  object `Quattrocento Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/quattrocentosans/v9/efd6FGWWGX5Z3ztwLBrG9eAj_ty82iuwwDTNEYXGiyQ.ttf")
    def `italic`: URL = URL(s"$base/quattrocentosans/v9/8PXYbvM__bjl0rBnKiByg532VBCoA_HLsn85tSWZmdo.ttf")
    def `700`: URL = URL(s"$base/quattrocentosans/v9/tXSgPxDl7Lk8Zr_5qX8FIbqxG25nQNOioCZSK4sU-CA.ttf")
    def `700italic`: URL = URL(s"$base/quattrocentosans/v9/8N1PdXpbG6RtFvTjl-5E7buqAJxizi8Dk_SK5et7kMg.ttf")
  }
  object `Questrial` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/questrial/v6/MoHHaw_WwNs_hd9ob1zTVw.ttf")
  }
  object `Quicksand` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `300`: URL = URL(s"$base/quicksand/v6/qhfoJiLu10kFjChCCTvGlC3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/quicksand/v6/Ngv3fIJjKB7sD-bTUGIFCA.ttf")
    def `500`: URL = URL(s"$base/quicksand/v6/FRGja7LlrG1Mypm0hCq0Di3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/quicksand/v6/32nyIRHyCu6iqEka_hbKsi3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Quintessential` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/quintessential/v4/mmk6ioesnTrEky_Zb92E5s02lXbtMOtZWfuxKeMZO8Q.ttf")
  }
  object `Qwigley` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/qwigley/v6/aDqxws-KubFID85TZHFouw.ttf")
  }
  object `Racing Sans One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/racingsansone/v4/1r3DpWaCiT7y3PD4KgkNyDjVlsJB_M_Q_LtZxsoxvlw.ttf")
  }
  object `Radley` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/radley/v11/FgE9di09a-mXGzAIyI6Q9Q.ttf")
    def `italic`: URL = URL(s"$base/radley/v11/Z_JcACuPAOO2f9kzQcGRug.ttf")
  }
  object `Rajdhani` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `300`: URL = URL(s"$base/rajdhani/v6/9pItuEhQZVGdq8spnHTku6CWcynf_cDxXwCLxiixG1c.ttf")
    def `regular`: URL = URL(s"$base/rajdhani/v6/Wfy5zp4PGFAFS7-Wetehzw.ttf")
    def `500`: URL = URL(s"$base/rajdhani/v6/nd_5ZpVwm710HcLual0fBqCWcynf_cDxXwCLxiixG1c.ttf")
    def `600`: URL = URL(s"$base/rajdhani/v6/5fnmZahByDeTtgxIiqbJSaCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/rajdhani/v6/UBK6d2Hg7X7wYLlF92aXW6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Rakkas` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "arabic")
    def `regular`: URL = URL(s"$base/rakkas/v1/XWSZpoSbAR4myQgKbSJM9A.ttf")
  }
  object `Raleway` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `100`: URL = URL(s"$base/raleway/v11/UDfD6oxBaBnmFJwQ7XAFNw.ttf")
    def `100italic`: URL = URL(s"$base/raleway/v11/hUpHtml6IPNuUR-FwVi2UKCWcynf_cDxXwCLxiixG1c.ttf")
    def `200`: URL = URL(s"$base/raleway/v11/LAQwev4hdCtYkOYX4Oc7nPesZW2xOQ-xsNqO47m55DA.ttf")
    def `200italic`: URL = URL(s"$base/raleway/v11/N2DIbZG4399cPGfifZUEQi3USBnSvpkopQaUR-2r7iU.ttf")
    def `300`: URL = URL(s"$base/raleway/v11/2VvSZU2kb4DZwFfRM4fLQPesZW2xOQ-xsNqO47m55DA.ttf")
    def `300italic`: URL = URL(s"$base/raleway/v11/TVSB8ogXDKMcnAAJ5CqrUi3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/raleway/v11/_dCzxpXzIS3sL-gdJWAP8A.ttf")
    def `italic`: URL = URL(s"$base/raleway/v11/utU2m1gdZSfuQpArSy5Dbw.ttf")
    def `500`: URL = URL(s"$base/raleway/v11/348gn6PEmbLDWlHbbV15d_esZW2xOQ-xsNqO47m55DA.ttf")
    def `500italic`: URL = URL(s"$base/raleway/v11/S7vGLZZ40c85SJgiptJGVy3USBnSvpkopQaUR-2r7iU.ttf")
    def `600`: URL = URL(s"$base/raleway/v11/M7no6oPkwKYJkedjB1wqEvesZW2xOQ-xsNqO47m55DA.ttf")
    def `600italic`: URL = URL(s"$base/raleway/v11/OY22yoG8EJ3IN_muVWm29C3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/raleway/v11/VGEV9-DrblisWOWLbK-1XPesZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/raleway/v11/lFxvRPuGFG5ktd7P0WRwKi3USBnSvpkopQaUR-2r7iU.ttf")
    def `800`: URL = URL(s"$base/raleway/v11/mMh0JrsYMXcLO69jgJwpUvesZW2xOQ-xsNqO47m55DA.ttf")
    def `800italic`: URL = URL(s"$base/raleway/v11/us4LjTCmlYgh3W8CKujEJi3USBnSvpkopQaUR-2r7iU.ttf")
    def `900`: URL = URL(s"$base/raleway/v11/ajQQGcDBLcyLpaUfD76UuPesZW2xOQ-xsNqO47m55DA.ttf")
    def `900italic`: URL = URL(s"$base/raleway/v11/oY2RadnkHfshu5f0FLsgVS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Raleway Dots` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/ralewaydots/v4/lhLgmWCRcyz-QXo8LCzTfC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Ramabhadra` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/ramabhadra/v5/JyhxLXRVQChLDGADS_c5MPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Ramaraja` {
    val category: String = "serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/ramaraja/v1/XIqzxFapVczstBedHdQTiw.ttf")
  }
  object `Rambla` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/rambla/v4/YaTmpvm5gFg_ShJKTQmdzg.ttf")
    def `italic`: URL = URL(s"$base/rambla/v4/mhUgsKmp0qw3uATdDDAuwA.ttf")
    def `700`: URL = URL(s"$base/rambla/v4/C5VZH8BxQKmnBuoC00UPpw.ttf")
    def `700italic`: URL = URL(s"$base/rambla/v4/ziMzUZya6QahrKONSI1TzqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Rammetto One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/rammettoone/v5/mh0uQ1tV8QgSx9v_KyEYPC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Ranchers` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/ranchers/v4/9ya8CZYhqT66VERfjQ7eLA.ttf")
  }
  object `Rancho` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/rancho/v7/ekp3-4QykC4--6KaslRgHA.ttf")
  }
  object `Ranga` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/ranga/v2/xpW6zFTNzY1JykoBIqE1Zg.ttf")
    def `700`: URL = URL(s"$base/ranga/v2/h8G_gEUH7vHKH-NkjAs34A.ttf")
  }
  object `Rasa` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "gujarati", "latin")
    def `300`: URL = URL(s"$base/rasa/v2/XQ1gDq2EqBtGcdadPyPbww.ttf")
    def `regular`: URL = URL(s"$base/rasa/v2/A5PoJUwX_PxTsywxlRB79g.ttf")
    def `500`: URL = URL(s"$base/rasa/v2/HfsDi_Ls3NARO_YEODINGg.ttf")
    def `600`: URL = URL(s"$base/rasa/v2/f-fvbq-hWIQCdmT3QHGk3Q.ttf")
    def `700`: URL = URL(s"$base/rasa/v2/TSF3CG-8Cn72jvaVdqtMMQ.ttf")
  }
  object `Rationale` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/rationale/v7/7M2eN-di0NGLQse7HzJRfg.ttf")
  }
  object `Ravi Prakash` {
    val category: String = "display"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/raviprakash/v3/8EzbM7Rymjk25jWeHxbO6C3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Redressed` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/redressed/v7/3aZ5sTBppH3oSm5SabegtA.ttf")
  }
  object `Reem Kufi` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin", "arabic")
    def `regular`: URL = URL(s"$base/reemkufi/v2/xLwMbK_T1g-h9p-rp60A1Q.ttf")
  }
  object `Reenie Beanie` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/reeniebeanie/v7/ljpKc6CdXusL1cnGUSamX4jjx0o0jr6fNXxPgYh_a8Q.ttf")
  }
  object `Revalia` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/revalia/v4/1TKw66fF5_poiL0Ktgo4_A.ttf")
  }
  object `Rhodium Libre` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/rhodiumlibre/v1/Vxr7A4-xE2zsBDDI8BcseIjjx0o0jr6fNXxPgYh_a8Q.ttf")
  }
  object `Ribeye` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/ribeye/v5/e5w3VE8HnWBln4Ll6lUj3Q.ttf")
  }
  object `Ribeye Marrow` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/ribeyemarrow/v6/q7cBSA-4ErAXBCDFPrhlY0cTNmV93fYG7UKgsLQNQWs.ttf")
  }
  object `Righteous` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/righteous/v5/0nRRWM_gCGCt2S-BCfN8WQ.ttf")
  }
  object `Risque` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/risque/v4/92RnElGnl8yHP97-KV3Fyg.ttf")
  }
  object `Roboto` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `100`: URL = URL(s"$base/roboto/v16/7MygqTe2zs9YkP0adA9QQQ.ttf")
    def `100italic`: URL = URL(s"$base/roboto/v16/T1xnudodhcgwXCmZQ490TPesZW2xOQ-xsNqO47m55DA.ttf")
    def `300`: URL = URL(s"$base/roboto/v16/dtpHsbgPEm2lVWciJZ0P-A.ttf")
    def `300italic`: URL = URL(s"$base/roboto/v16/iE8HhaRzdhPxC93dOdA056CWcynf_cDxXwCLxiixG1c.ttf")
    def `regular`: URL = URL(s"$base/roboto/v16/W5F8_SL0XFawnjxHGsZjJA.ttf")
    def `italic`: URL = URL(s"$base/roboto/v16/hcKoSgxdnKlbH5dlTwKbow.ttf")
    def `500`: URL = URL(s"$base/roboto/v16/Uxzkqj-MIMWle-XP2pDNAA.ttf")
    def `500italic`: URL = URL(s"$base/roboto/v16/daIfzbEw-lbjMyv4rMUUTqCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/roboto/v16/bdHGHleUa-ndQCOrdpfxfw.ttf")
    def `700italic`: URL = URL(s"$base/roboto/v16/owYYXKukxFDFjr0ZO8NXh6CWcynf_cDxXwCLxiixG1c.ttf")
    def `900`: URL = URL(s"$base/roboto/v16/H1vB34nOKWXqzKotq25pcg.ttf")
    def `900italic`: URL = URL(s"$base/roboto/v16/b9PWBSMHrT2zM5FgUdtu0aCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Roboto Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `300`: URL = URL(s"$base/robotocondensed/v14/b9QBgL0iMZfDSpmcXcE8nJRhFVcex_hajThhFkHyhYk.ttf")
    def `300italic`: URL = URL(s"$base/robotocondensed/v14/mg0cGfGRUERshzBlvqxeAPYa9bgCHecWXGgisnodcS0.ttf")
    def `regular`: URL = URL(s"$base/robotocondensed/v14/Zd2E9abXLFGSr9G3YK2MsKDbm6fPDOZJsR8PmdG62gY.ttf")
    def `italic`: URL = URL(s"$base/robotocondensed/v14/BP5K8ZAJv9qEbmuFp8RpJY_eiqgTfYGaH0bJiUDZ5GA.ttf")
    def `700`: URL = URL(s"$base/robotocondensed/v14/b9QBgL0iMZfDSpmcXcE8nPOYkGiSOYDq_T7HbIOV1hA.ttf")
    def `700italic`: URL = URL(s"$base/robotocondensed/v14/mg0cGfGRUERshzBlvqxeAE2zk2RGRC3SlyyLLQfjS_8.ttf")
  }
  object `Roboto Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `100`: URL = URL(s"$base/robotomono/v4/aOIeRp72J9_Hp_8KwQ9M-YAWxXGWZ3yJw6KhWS7MxOk.ttf")
    def `100italic`: URL = URL(s"$base/robotomono/v4/rqQ1zSE-ZGCKVZgew-A9dgyDtfpXZi-8rXUZYR4dumU.ttf")
    def `300`: URL = URL(s"$base/robotomono/v4/N4duVc9C58uwPiY8_59Fzy9-WlPSxbfiI49GsXo3q0g.ttf")
    def `300italic`: URL = URL(s"$base/robotomono/v4/1OsMuiiO6FCF2x67vzDKA2o9eWDfYYxG3A176Zl7aIg.ttf")
    def `regular`: URL = URL(s"$base/robotomono/v4/eJ4cxQe85Lo39t-LVoKa26CWcynf_cDxXwCLxiixG1c.ttf")
    def `italic`: URL = URL(s"$base/robotomono/v4/mE0EPT_93c7f86_WQexR3EeOrDcLawS7-ssYqLr2Xp4.ttf")
    def `500`: URL = URL(s"$base/robotomono/v4/N4duVc9C58uwPiY8_59Fz8CNfqCYlB_eIx7H1TVXe60.ttf")
    def `500italic`: URL = URL(s"$base/robotomono/v4/1OsMuiiO6FCF2x67vzDKA2nWRcJAYo5PSCx8UfGMHCI.ttf")
    def `700`: URL = URL(s"$base/robotomono/v4/N4duVc9C58uwPiY8_59Fz3e1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `700italic`: URL = URL(s"$base/robotomono/v4/1OsMuiiO6FCF2x67vzDKA8_zJjSACmk0BRPxQqhnNLU.ttf")
  }
  object `Roboto Slab` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `100`: URL = URL(s"$base/robotoslab/v6/MEz38VLIFL-t46JUtkIEgIAWxXGWZ3yJw6KhWS7MxOk.ttf")
    def `300`: URL = URL(s"$base/robotoslab/v6/dazS1PrQQuCxC3iOAJFEJS9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/robotoslab/v6/3__ulTNA7unv0UtplybPiqCWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/robotoslab/v6/dazS1PrQQuCxC3iOAJFEJXe1Pd76Vl7zRpE7NLJQ7XU.ttf")
  }
  object `Rochester` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/rochester/v7/bnj8tmQBiOkdji_G_yvypg.ttf")
  }
  object `Rock Salt` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/rocksalt/v7/Zy7JF9h9WbhD9V3SFMQ1UQ.ttf")
  }
  object `Rokkitt` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/rokkitt/v11/_3YC6rPA1FdHK3T5HJAiKA.ttf")
    def `200`: URL = URL(s"$base/rokkitt/v11/YawjzRx4kAyF2FdhIXfg1_esZW2xOQ-xsNqO47m55DA.ttf")
    def `300`: URL = URL(s"$base/rokkitt/v11/Cw0HfZi5axnl2GTVcAe4x_esZW2xOQ-xsNqO47m55DA.ttf")
    def `regular`: URL = URL(s"$base/rokkitt/v11/GMA7Z_ToF8uSvpZAgnp_VQ.ttf")
    def `500`: URL = URL(s"$base/rokkitt/v11/jSxUaZL9JCo117IMemf-iPesZW2xOQ-xsNqO47m55DA.ttf")
    def `600`: URL = URL(s"$base/rokkitt/v11/b4_SvUo9hy0bV60RoA1RKPesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/rokkitt/v11/gxlo-sr3rPmvgSixYog_ofesZW2xOQ-xsNqO47m55DA.ttf")
    def `800`: URL = URL(s"$base/rokkitt/v11/mCok2W9ZHFgB-LY6ITuapfesZW2xOQ-xsNqO47m55DA.ttf")
    def `900`: URL = URL(s"$base/rokkitt/v11/riY221k9xwvseUAhNXMjQPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Romanesco` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/romanesco/v5/2udIjUrpK_CPzYSxRVzD4Q.ttf")
  }
  object `Ropa Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/ropasans/v6/Gba7ZzVBuhg6nX_AoSwlkQ.ttf")
    def `italic`: URL = URL(s"$base/ropasans/v6/V1zbhZQscNrh63dy5Jk2nqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Rosario` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/rosario/v11/bL-cEh8dXtDupB2WccA2LA.ttf")
    def `italic`: URL = URL(s"$base/rosario/v11/pkflNy18HEuVVx4EOjeb_Q.ttf")
    def `700`: URL = URL(s"$base/rosario/v11/nrS6PJvDWN42RP4TFWccd_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/rosario/v11/EOgFX2Va5VGrkhn_eDpIRS3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Rosarivo` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/rosarivo/v4/EmPiINK0qyqc7KSsNjJamA.ttf")
    def `italic`: URL = URL(s"$base/rosarivo/v4/u3VuWsWQlX1pDqsbz4paNPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Rouge Script` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/rougescript/v5/AgXDSqZJmy12qS0ixjs6Vy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Rozha One` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/rozhaone/v3/PyrMHQ6lucEIxwKmhqsX8A.ttf")
  }
  object `Rubik` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "hebrew", "latin", "cyrillic")
    def `300`: URL = URL(s"$base/rubik/v6/o1vXYO8YwDpErHEAPAxpOg.ttf")
    def `300italic`: URL = URL(s"$base/rubik/v6/NyXDvUhvZLSWiVfGa5KM-vesZW2xOQ-xsNqO47m55DA.ttf")
    def `regular`: URL = URL(s"$base/rubik/v6/4sMyW_teKWHB3K8Hm-Il6A.ttf")
    def `italic`: URL = URL(s"$base/rubik/v6/elD65ddI0qvNcCh42b1Iqg.ttf")
    def `500`: URL = URL(s"$base/rubik/v6/D4HihERG27s-BJrQ4dvkbw.ttf")
    def `500italic`: URL = URL(s"$base/rubik/v6/0hcxMdoMbXtHiEM1ebdN6PesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/rubik/v6/m1GGHcpLe6Mb0_sAyjXE4g.ttf")
    def `700italic`: URL = URL(s"$base/rubik/v6/R4g_rs714cUXVZcdnRdHw_esZW2xOQ-xsNqO47m55DA.ttf")
    def `900`: URL = URL(s"$base/rubik/v6/mOHfPRl5uP4vw7-5-dbnng.ttf")
    def `900italic`: URL = URL(s"$base/rubik/v6/HH1b7kBbwInqlw8OQxRE5vesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Rubik Mono One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/rubikmonoone/v5/e_cupPtD4BrZzotubJD7UbAREgn5xbW23GEXXnhMQ5Y.ttf")
  }
  object `Ruda` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/ruda/v8/jPEIPB7DM2DNK_uBGv2HGw.ttf")
    def `700`: URL = URL(s"$base/ruda/v8/JABOu1SYOHcGXVejUq4w6g.ttf")
    def `900`: URL = URL(s"$base/ruda/v8/Uzusv-enCjoIrznlJJaBRw.ttf")
  }
  object `Rufina` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/rufina/v4/s9IFr_fIemiohfZS-ZRDbQ.ttf")
    def `700`: URL = URL(s"$base/rufina/v4/D0RUjXFr55y4MVZY2Ww_RA.ttf")
  }
  object `Ruge Boogie` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/rugeboogie/v7/U-TTmltL8aENLVIqYbI5QaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Ruluko` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/ruluko/v4/lv4cMwJtrx_dzmlK5SDc1g.ttf")
  }
  object `Rum Raisin` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/rumraisin/v4/kDiL-ntDOEq26B7kYM7cx_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Ruslan Display` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/ruslandisplay/v7/SREdhlyLNUfU1VssRBfs3rgH88D3l9N4auRNHrNS708.ttf")
  }
  object `Russo One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/russoone/v5/zfwxZ--UhUc7FVfgT21PRQ.ttf")
  }
  object `Ruthie` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/ruthie/v6/vJ2LorukHSbWYoEs5juivg.ttf")
  }
  object `Rye` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/rye/v4/VUrJlpPpSZxspl3w_yNOrQ.ttf")
  }
  object `Sacramento` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sacramento/v4/_kv-qycSHMNdhjiv0Kj7BvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Sahitya` {
    val category: String = "serif"
    val subsets: Set[String] = Set("devanagari", "latin")
    def `regular`: URL = URL(s"$base/sahitya/v1/wQWULcDbZqljdTfjOUtDvw.ttf")
    def `700`: URL = URL(s"$base/sahitya/v1/Zm5hNvMwUyN3tC4GMkH1l_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Sail` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sail/v7/iuEoG6kt-bePGvtdpL0GUQ.ttf")
  }
  object `Saira` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/saira/v1/Ozk8do2fTcpbNH9fymkZGg.ttf")
    def `200`: URL = URL(s"$base/saira/v1/IqoIheMFTgcbZXFWbGwENA.ttf")
    def `300`: URL = URL(s"$base/saira/v1/ANavK9Yw1m9jo7r6xy-MSg.ttf")
    def `regular`: URL = URL(s"$base/saira/v1/Xscf3I_Twe9a3mnmbLi5XQ.ttf")
    def `500`: URL = URL(s"$base/saira/v1/8JTYqpjvzQP3oTjzUn8w7Q.ttf")
    def `600`: URL = URL(s"$base/saira/v1/7TS8zxqrCaFpOEscLh1xXg.ttf")
    def `700`: URL = URL(s"$base/saira/v1/Vmcd_0w8o16ONteEu2UzSw.ttf")
    def `800`: URL = URL(s"$base/saira/v1/R-CIR5SYaB7pZZbF4KBcmg.ttf")
    def `900`: URL = URL(s"$base/saira/v1/NkJ3cJqxlFuVNRn8L9vVsg.ttf")
  }
  object `Saira Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/sairacondensed/v2/g6ZiOTAus3rTCuLbft-lrhQ4ZQgT5IY6T956D4i2DOg.ttf")
    def `200`: URL = URL(s"$base/sairacondensed/v2/iBnVn24meOdNw5Ie3y-w-t_1mjc__NNUUqnuBhyrdnQ.ttf")
    def `300`: URL = URL(s"$base/sairacondensed/v2/iBnVn24meOdNw5Ie3y-w-mOGg88i8doN2x6-0_j_XSs.ttf")
    def `regular`: URL = URL(s"$base/sairacondensed/v2/RzMaXT8ujYB0FpOoZJ_AtSQPsWWoiv__AzYJ9Zzn9II.ttf")
    def `500`: URL = URL(s"$base/sairacondensed/v2/iBnVn24meOdNw5Ie3y-w-gRL_-ABKXdjsJSPT0lc2Bk.ttf")
    def `600`: URL = URL(s"$base/sairacondensed/v2/iBnVn24meOdNw5Ie3y-w-rS5sSASxc8z4EQTQj7DCAI.ttf")
    def `700`: URL = URL(s"$base/sairacondensed/v2/iBnVn24meOdNw5Ie3y-w-sAWgzcA047xWLixhLCofl8.ttf")
    def `800`: URL = URL(s"$base/sairacondensed/v2/iBnVn24meOdNw5Ie3y-w-hVl4JojgVAnfiwswP7KrtY.ttf")
    def `900`: URL = URL(s"$base/sairacondensed/v2/iBnVn24meOdNw5Ie3y-w-mCsDIq3El29Rd5VD3daJ_M.ttf")
  }
  object `Saira Extra Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/sairaextracondensed/v2/fW6xdUWepu0r8HZYLdXhdSi7tdGxScTr3oVgcrTUqWw.ttf")
    def `200`: URL = URL(s"$base/sairaextracondensed/v2/XVu3ZHO65MpX5FDLl4hwfa1IHoFZjDq9yl49NJ3Y0wY.ttf")
    def `300`: URL = URL(s"$base/sairaextracondensed/v2/XVu3ZHO65MpX5FDLl4hwfeZroXgFx_lT3TTeDaAqrWE.ttf")
    def `regular`: URL = URL(s"$base/sairaextracondensed/v2/3XMbuc1UIdE_Bo4eJ-H3G4elbRYnLTTQA1Z5cVLnsI4.ttf")
    def `500`: URL = URL(s"$base/sairaextracondensed/v2/XVu3ZHO65MpX5FDLl4hwfa4Ixr3FMLIaz6yY1ILODIU.ttf")
    def `600`: URL = URL(s"$base/sairaextracondensed/v2/XVu3ZHO65MpX5FDLl4hwfcMHImBNo4aGUuMCjGiDijI.ttf")
    def `700`: URL = URL(s"$base/sairaextracondensed/v2/XVu3ZHO65MpX5FDLl4hwfbGMx7y0UuyPIsLqSMg46Ks.ttf")
    def `800`: URL = URL(s"$base/sairaextracondensed/v2/XVu3ZHO65MpX5FDLl4hwff3VPWKD9LjLpSGgTAgUUIc.ttf")
    def `900`: URL = URL(s"$base/sairaextracondensed/v2/XVu3ZHO65MpX5FDLl4hwfb3y6LE9HhLx9tlnlwi3OAw.ttf")
  }
  object `Saira Semi Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `100`: URL = URL(s"$base/sairasemicondensed/v2/W0qqtuwvTyZEzthCisMvJNpUFoAgdo3N6uMK4qBKl14.ttf")
    def `200`: URL = URL(s"$base/sairasemicondensed/v2/AqP7QX0TdaZHs8pWxeHdZXmwZH8Mj4a8GCt9BVpguoM.ttf")
    def `300`: URL = URL(s"$base/sairasemicondensed/v2/AqP7QX0TdaZHs8pWxeHdZf41r7gBuORyHypyaMk5V7M.ttf")
    def `regular`: URL = URL(s"$base/sairasemicondensed/v2/E1gvqhdADptsO-uwP-KYOplmjOf-f3WTIBZyrvssS_s.ttf")
    def `500`: URL = URL(s"$base/sairasemicondensed/v2/AqP7QX0TdaZHs8pWxeHdZQTR7LyNMQKOmEK2zaPVo7k.ttf")
    def `600`: URL = URL(s"$base/sairasemicondensed/v2/AqP7QX0TdaZHs8pWxeHdZYxOyuVPIqzYlTscMcnFFdw.ttf")
    def `700`: URL = URL(s"$base/sairasemicondensed/v2/AqP7QX0TdaZHs8pWxeHdZVhvgkvS4Vb80oyvTRs3xAw.ttf")
    def `800`: URL = URL(s"$base/sairasemicondensed/v2/AqP7QX0TdaZHs8pWxeHdZbgNSs8Rfv-SK6bauL4DA_k.ttf")
    def `900`: URL = URL(s"$base/sairasemicondensed/v2/AqP7QX0TdaZHs8pWxeHdZftJ9g8-32R6gX5VB508ZS0.ttf")
  }
  object `Salsa` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/salsa/v6/BnpUCBmYdvggScEPs5JbpA.ttf")
  }
  object `Sanchez` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sanchez/v4/BEL8ao-E2LJ5eHPLB2UAiw.ttf")
    def `italic`: URL = URL(s"$base/sanchez/v4/iSrhkWLexUZzDeNxNEHtzA.ttf")
  }
  object `Sancreek` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sancreek/v7/8ZacBMraWMvHly4IJI3esw.ttf")
  }
  object `Sansita` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sansita/v1/ey9oYobmakEwtEciY0G5Mg.ttf")
    def `italic`: URL = URL(s"$base/sansita/v1/UkWzQlyaYvMqX8-kX9fI1A.ttf")
    def `700`: URL = URL(s"$base/sansita/v1/q9hPUXq37zR3BVunMJi2HfesZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/sansita/v1/Izkki8H_L5Nxxk6vpKrxXS3USBnSvpkopQaUR-2r7iU.ttf")
    def `800`: URL = URL(s"$base/sansita/v1/vOIsA3n-LuVE_PeoZ3aSFfesZW2xOQ-xsNqO47m55DA.ttf")
    def `800italic`: URL = URL(s"$base/sansita/v1/4OvihNMj_b3nyu4KlgNNVS3USBnSvpkopQaUR-2r7iU.ttf")
    def `900`: URL = URL(s"$base/sansita/v1/lwgTmJASMyrLsXnTfRSt7fesZW2xOQ-xsNqO47m55DA.ttf")
    def `900italic`: URL = URL(s"$base/sansita/v1/JTPHz0Wyy3AImmVqi8CQTy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Sarala` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/sarala/v1/ohip9lixCHoBab7hTtgLnw.ttf")
    def `700`: URL = URL(s"$base/sarala/v1/hpc9cz8KYsazwq2In_oJYw.ttf")
  }
  object `Sarina` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sarina/v5/XYtRfaSknHIU3NHdfTdXoQ.ttf")
  }
  object `Sarpanch` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/sarpanch/v2/YMBZdT27b6O5a1DADbAGSg.ttf")
    def `500`: URL = URL(s"$base/sarpanch/v2/Ov7BxSrFSZYrfuJxL1LzQaCWcynf_cDxXwCLxiixG1c.ttf")
    def `600`: URL = URL(s"$base/sarpanch/v2/WTnP2wnc0qSbUaaDG-2OQ6CWcynf_cDxXwCLxiixG1c.ttf")
    def `700`: URL = URL(s"$base/sarpanch/v2/57kYsSpovYmFaEt2hsZhv6CWcynf_cDxXwCLxiixG1c.ttf")
    def `800`: URL = URL(s"$base/sarpanch/v2/OKyqPLjdnuVghR-1TV6RzaCWcynf_cDxXwCLxiixG1c.ttf")
    def `900`: URL = URL(s"$base/sarpanch/v2/JhYc2cr6kqWTo_P0vfvJR6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Satisfy` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/satisfy/v7/PRlyepkd-JCGHiN8e9WV2w.ttf")
  }
  object `Scada` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/scada/v5/iZNC3ZEYwe3je6H-28d5Ug.ttf")
    def `italic`: URL = URL(s"$base/scada/v5/PCGyLT1qNawkOUQ3uHFhBw.ttf")
    def `700`: URL = URL(s"$base/scada/v5/t6XNWdMdVWUz93EuRVmifQ.ttf")
    def `700italic`: URL = URL(s"$base/scada/v5/kLrBIf7V4mDMwcd_Yw7-D_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Scheherazade` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin", "arabic")
    def `regular`: URL = URL(s"$base/scheherazade/v12/AuKlqGWzUC-8XqMOmsqXiy3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/scheherazade/v12/C1wtT46acJkQxc6mPHwvHED2ttfZwueP-QU272T9-k4.ttf")
  }
  object `Schoolbell` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/schoolbell/v7/95-3djEuubb3cJx-6E7j4vesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Scope One` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/scopeone/v2/ge7dY8Yht-n7_1cLHtoT3w.ttf")
  }
  object `Seaweed Script` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/seaweedscript/v4/eorWAPpOvvWrPw5IHwE60BnpV0hQCek3EmWnCPrvGRM.ttf")
  }
  object `Secular One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "hebrew", "latin")
    def `regular`: URL = URL(s"$base/secularone/v1/yW9qikjpt_X0fh5oQJcdo6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Sedgwick Ave` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/sedgwickave/v2/pbgmsWX_2A5V-qqzaczoEy3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Sedgwick Ave Display` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/sedgwickavedisplay/v2/_2bQpgd1Hl3UOD3yDrU-cP912kD9slMJGfCNYtCeVl4.ttf")
  }
  object `Sevillana` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sevillana/v4/6m1Nh35oP7YEt00U80Smiw.ttf")
  }
  object `Seymour One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/seymourone/v4/HrdG2AEG_870Xb7xBVv6C6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Shadows Into Light` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/shadowsintolight/v6/clhLqOv7MXn459PTh0gXYAW_5bEze-iLRNvGrRpJsfM.ttf")
  }
  object `Shadows Into Light Two` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/shadowsintolighttwo/v4/gDxHeefcXIo-lOuZFCn2xVQrZk-Pga5KeEE_oZjkQjQ.ttf")
  }
  object `Shanti` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/shanti/v8/lc4nG_JG6Q-2FQSOMMhb_w.ttf")
  }
  object `Share` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/share/v7/1ytD7zSb_-g9I2GG67vmVw.ttf")
    def `italic`: URL = URL(s"$base/share/v7/a9YGdQWFRlNJ0zClJVaY3Q.ttf")
    def `700`: URL = URL(s"$base/share/v7/XrU8e7a1YKurguyY2azk1Q.ttf")
    def `700italic`: URL = URL(s"$base/share/v7/A992-bLVYwAflKu6iaznufesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Share Tech` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/sharetech/v6/Dq3DuZ5_0SW3oEfAWFpen_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Share Tech Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/sharetechmono/v6/RQxK-3RA0Lnf3gnnnNrAscwD6PD0c3_abh9zHKQtbGU.ttf")
  }
  object `Shojumaru` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/shojumaru/v4/WP8cxonzQQVAoI3RJQ2wug.ttf")
  }
  object `Short Stack` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/shortstack/v6/v4dXPI0Rm8XN9gk4SDdqlqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Shrikhand` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "gujarati", "latin")
    def `regular`: URL = URL(s"$base/shrikhand/v2/45jwHiwIDTWCy3Ir85vvKA.ttf")
  }
  object `Siemreap` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/siemreap/v9/JSK-mOIsXwxo-zE9XDDl_g.ttf")
  }
  object `Sigmar One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/sigmarone/v7/oh_5NxD5JBZksdo2EntKefesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Signika` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/signika/v7/0wDPonOzsYeEo-1KO78w4fesZW2xOQ-xsNqO47m55DA.ttf")
    def `regular`: URL = URL(s"$base/signika/v7/WvDswbww0oAtvBg2l1L-9w.ttf")
    def `600`: URL = URL(s"$base/signika/v7/lQMOF6NUN2ooR7WvB7tADvesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/signika/v7/lEcnfPBICWJPv5BbVNnFJPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Signika Negative` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/signikanegative/v6/q5TOjIw4CenPw6C-TW06FjYFXpUPtCmIEFDvjUnLLaI.ttf")
    def `regular`: URL = URL(s"$base/signikanegative/v6/Z-Q1hzbY8uAo3TpTyPFMXVM1lnCWMnren5_v6047e5A.ttf")
    def `600`: URL = URL(s"$base/signikanegative/v6/q5TOjIw4CenPw6C-TW06FrKLaDJM01OezSVA2R_O3qI.ttf")
    def `700`: URL = URL(s"$base/signikanegative/v6/q5TOjIw4CenPw6C-TW06FpYzPxtVvobH1w3hEppR8WI.ttf")
  }
  object `Simonetta` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/simonetta/v6/fN8puNuahBo4EYMQgp12Yg.ttf")
    def `italic`: URL = URL(s"$base/simonetta/v6/ynxQ3FqfF_Nziwy3T9ZwL6CWcynf_cDxXwCLxiixG1c.ttf")
    def `900`: URL = URL(s"$base/simonetta/v6/22EwvvJ2r1VwVCxit5LcVi3USBnSvpkopQaUR-2r7iU.ttf")
    def `900italic`: URL = URL(s"$base/simonetta/v6/WUXOpCgBZaRPrWtMCpeKoienaqEuufTBk9XMKnKmgDA.ttf")
  }
  object `Sintony` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sintony/v4/IDhCijoIMev2L6Lg5QsduQ.ttf")
    def `700`: URL = URL(s"$base/sintony/v4/zVXQB1wqJn6PE4dWXoYpvPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Sirin Stencil` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/sirinstencil/v5/pRpLdo0SawzO7MoBpvowsImg74kgS1F7KeR8rWhYwkU.ttf")
  }
  object `Six Caps` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/sixcaps/v7/_XeDnO0HOV8Er9u97If1tQ.ttf")
  }
  object `Skranji` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/skranji/v4/jnOLPS0iZmDL7dfWnW3nIw.ttf")
    def `700`: URL = URL(s"$base/skranji/v4/Lcrhg-fviVkxiEgoadsI1vesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Slabo 13px` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/slabo13px/v3/jPGWFTjRXfCSzy0qd1nqdvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Slabo 27px` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/slabo27px/v3/gC0o8B9eU21EafNkXlRAfPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Slackey` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/slackey/v7/evRIMNhGVCRJvCPv4kteeA.ttf")
  }
  object `Smokum` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/smokum/v7/8YP4BuAcy97X8WfdKfxVRw.ttf")
  }
  object `Smythe` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/smythe/v7/yACD1gy_MpbB9Ft42fUvYw.ttf")
  }
  object `Sniglet` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sniglet/v8/XWhyQLHH4SpCVsHRPRgu9w.ttf")
    def `800`: URL = URL(s"$base/sniglet/v8/NLF91nBmcEfkBgcEWbHFa_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Snippet` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/snippet/v6/eUcYMLq2GtHZovLlQH_9kA.ttf")
  }
  object `Snowburst One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/snowburstone/v4/zSQzKOPukXRux2oTqfYJjIjjx0o0jr6fNXxPgYh_a8Q.ttf")
  }
  object `Sofadi One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/sofadione/v5/nirf4G12IcJ6KI8Eoj119fesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Sofia` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/sofia/v5/Imnvx0Ag9r6iDBFUY5_RaQ.ttf")
  }
  object `Sonsie One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sonsieone/v5/KSP7xT1OSy0q2ob6RQOTWPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Sorts Mill Goudy` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sortsmillgoudy/v6/JzRrPKdwEnE8F1TDmDLMUlIL2Qjg-Xlsg_fhGbe2P5U.ttf")
    def `italic`: URL = URL(s"$base/sortsmillgoudy/v6/UUu1lKiy4hRmBWk599VL1TYNkCNSzLyoucKmbTguvr0.ttf")
  }
  object `Source Code Pro` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `200`: URL = URL(s"$base/sourcecodepro/v6/leqv3v-yTsJNC7nFznSMqaXvKVW_haheDNrHjziJZVk.ttf")
    def `300`: URL = URL(s"$base/sourcecodepro/v6/leqv3v-yTsJNC7nFznSMqVP7R5lD_au4SZC6Ks_vyWs.ttf")
    def `regular`: URL = URL(s"$base/sourcecodepro/v6/mrl8jkM18OlOQN8JLgasD9Rl0pGnog23EMYRrBmUzJQ.ttf")
    def `500`: URL = URL(s"$base/sourcecodepro/v6/leqv3v-yTsJNC7nFznSMqX63uKwMO11Of4rJWV582wg.ttf")
    def `600`: URL = URL(s"$base/sourcecodepro/v6/leqv3v-yTsJNC7nFznSMqeiMeWyi5E_-XkTgB5psiDg.ttf")
    def `700`: URL = URL(s"$base/sourcecodepro/v6/leqv3v-yTsJNC7nFznSMqfgXsetDviZcdR5OzC1KPcw.ttf")
    def `900`: URL = URL(s"$base/sourcecodepro/v6/leqv3v-yTsJNC7nFznSMqRA_awHl7mXRjE_LQVochcU.ttf")
  }
  object `Source Sans Pro` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `200`: URL = URL(s"$base/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGKXvKVW_haheDNrHjziJZVk.ttf")
    def `200italic`: URL = URL(s"$base/sourcesanspro/v10/fpTVHK8qsXbIeTHTrnQH6OptKU7UIBg2hLM7eMTU8bI.ttf")
    def `300`: URL = URL(s"$base/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFP7R5lD_au4SZC6Ks_vyWs.ttf")
    def `300italic`: URL = URL(s"$base/sourcesanspro/v10/fpTVHK8qsXbIeTHTrnQH6DUpNKoQAsDux-Todp8f29w.ttf")
    def `regular`: URL = URL(s"$base/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlNRl0pGnog23EMYRrBmUzJQ.ttf")
    def `italic`: URL = URL(s"$base/sourcesanspro/v10/M2Jd71oPJhLKp0zdtTvoMwRX4TIfMQQEXLu74GftruE.ttf")
    def `600`: URL = URL(s"$base/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGOiMeWyi5E_-XkTgB5psiDg.ttf")
    def `600italic`: URL = URL(s"$base/sourcesanspro/v10/fpTVHK8qsXbIeTHTrnQH6Pp6lGoTTgjlW0sC4r900Co.ttf")
    def `700`: URL = URL(s"$base/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGPgXsetDviZcdR5OzC1KPcw.ttf")
    def `700italic`: URL = URL(s"$base/sourcesanspro/v10/fpTVHK8qsXbIeTHTrnQH6LVT4locI09aamSzFGQlDMY.ttf")
    def `900`: URL = URL(s"$base/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGBA_awHl7mXRjE_LQVochcU.ttf")
    def `900italic`: URL = URL(s"$base/sourcesanspro/v10/fpTVHK8qsXbIeTHTrnQH6A0NcF6HPGWR298uWIdxWv0.ttf")
  }
  object `Source Serif Pro` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/sourceserifpro/v4/CeUM4np2c42DV49nanp55YGL0S0YDpKs5GpLtZIQ0m4.ttf")
    def `600`: URL = URL(s"$base/sourceserifpro/v4/yd5lDMt8Sva2PE17yiLarGi4cQnvCGV11m1KlXh97aQ.ttf")
    def `700`: URL = URL(s"$base/sourceserifpro/v4/yd5lDMt8Sva2PE17yiLarEkpYHRvxGNSCrR82n_RDNk.ttf")
  }
  object `Space Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/spacemono/v1/B_LOPq3uMVBqC_kmqwURBfesZW2xOQ-xsNqO47m55DA.ttf")
    def `italic`: URL = URL(s"$base/spacemono/v1/7xgIgvUEl9Gvhtf7tXsRzC3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/spacemono/v1/vdpMRWfyjfCvDYTz00NEPAJKKGfqHaYFsRG-T3ceEVo.ttf")
    def `700italic`: URL = URL(s"$base/spacemono/v1/y2NWQDXe2-qPj6a6rWkLc0D2ttfZwueP-QU272T9-k4.ttf")
  }
  object `Special Elite` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/specialelite/v7/9-wW4zu3WNoD5Fjka35Jm4jjx0o0jr6fNXxPgYh_a8Q.ttf")
  }
  object `Spectral` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `200`: URL = URL(s"$base/spectral/v1/RPsjutNSGdCMO0uTaGNKAaCWcynf_cDxXwCLxiixG1c.ttf")
    def `200italic`: URL = URL(s"$base/spectral/v1/iTACFYcWCBGY-0cRjdYs3meudeTO44zf-ht3k-KNzwg.ttf")
    def `300`: URL = URL(s"$base/spectral/v1/EUVu_t3TbuiAmr-6bAqTvaCWcynf_cDxXwCLxiixG1c.ttf")
    def `300italic`: URL = URL(s"$base/spectral/v1/gXmD0bm_WQVxhEdjIN6xlEeOrDcLawS7-ssYqLr2Xp4.ttf")
    def `regular`: URL = URL(s"$base/spectral/v1/iBj67vddkZHOY5CJLE9SnA.ttf")
    def `italic`: URL = URL(s"$base/spectral/v1/lQA62MkEULvXDckLFYyk-vesZW2xOQ-xsNqO47m55DA.ttf")
    def `500`: URL = URL(s"$base/spectral/v1/KuRhuOjLr-dCVlaHBMOF96CWcynf_cDxXwCLxiixG1c.ttf")
    def `500italic`: URL = URL(s"$base/spectral/v1/hUloM7YPsU02LWYFA7w1x5p-63r6doWhTEbsfBIRJ7A.ttf")
    def `600`: URL = URL(s"$base/spectral/v1/OSDAbiOpLs0hkOIFx2oUZKCWcynf_cDxXwCLxiixG1c.ttf")
    def `600italic`: URL = URL(s"$base/spectral/v1/c6okfJABbOc8QqRI28ISV_pTEJqju4Hz1txDWij77d4.ttf")
    def `700`: URL = URL(s"$base/spectral/v1/g1QizOcRY_Apk-QDq3rhOKCWcynf_cDxXwCLxiixG1c.ttf")
    def `700italic`: URL = URL(s"$base/spectral/v1/v9WvdY1ll-vjpGHSRxsAIQJKKGfqHaYFsRG-T3ceEVo.ttf")
    def `800`: URL = URL(s"$base/spectral/v1/qQdpRyS_X5oC54LeW0MlmKCWcynf_cDxXwCLxiixG1c.ttf")
    def `800italic`: URL = URL(s"$base/spectral/v1/wYroR9dlOe2UFhp_3HJ9qqk3bhPBSBJ0bSJQ6acL-0g.ttf")
  }
  object `Spicy Rice` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/spicyrice/v5/WGCtz7cLoggXARPi9OGD6_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Spinnaker` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/spinnaker/v8/MQdIXivKITpjROUdiN6Jgg.ttf")
  }
  object `Spirax` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/spirax/v5/IOKqhk-Ccl7y31yDsePPkw.ttf")
  }
  object `Squada One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/squadaone/v5/3tzGuaJdD65cZVgfQzN8uvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Sree Krushnadevaraya` {
    val category: String = "serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/sreekrushnadevaraya/v4/CdsXmnHyEqVl1ahzOh5qnzjDZVem5Eb4d0dXjXa0F_Q.ttf")
  }
  object `Sriracha` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `regular`: URL = URL(s"$base/sriracha/v1/l-TXHmKwoHm6vtjy4oUz8Q.ttf")
  }
  object `Stalemate` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/stalemate/v4/wQLCnG0qB6mOu2Wit2dt_w.ttf")
  }
  object `Stalinist One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/stalinistone/v9/MQpS-WezM9W4Dd7D3B7I-UT7eZ8.ttf")
  }
  object `Stardos Stencil` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/stardosstencil/v6/ygEOyTW9a6u4fi4OXEZeTFf2eT4jUldwg_9fgfY_tHc.ttf")
    def `700`: URL = URL(s"$base/stardosstencil/v6/h4ExtgvoXhPtv9Ieqd-XC81wDCbBgmIo8UyjIhmkeSM.ttf")
  }
  object `Stint Ultra Condensed` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/stintultracondensed/v5/8DqLK6-YSClFZt3u3EgOUYelbRYnLTTQA1Z5cVLnsI4.ttf")
  }
  object `Stint Ultra Expanded` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/stintultraexpanded/v4/FeigX-wDDgHMCKuhekhedQ7dxr0N5HY0cZKknTIL6n4.ttf")
  }
  object `Stoke` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/stoke/v6/Sell9475FOS8jUqQsfFsUQ.ttf")
    def `regular`: URL = URL(s"$base/stoke/v6/A7qJNoqOm2d6o1E6e0yUFg.ttf")
  }
  object `Strait` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/strait/v4/m4W73ViNmProETY2ybc-Bg.ttf")
  }
  object `Sue Ellen Francisco` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/sueellenfrancisco/v7/TwHX4vSxMUnJUdEz1JIgrhzazJzPVbGl8jnf1tisRz4.ttf")
  }
  object `Suez One` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "hebrew", "latin")
    def `regular`: URL = URL(s"$base/suezone/v1/xulpHtKbz3V8hoSLE2uKDw.ttf")
  }
  object `Sumana` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/sumana/v1/wgdl__wAK7pzliiWs0Nlog.ttf")
    def `700`: URL = URL(s"$base/sumana/v1/8AcM-KAproitONSBBHj3sQ.ttf")
  }
  object `Sunshiney` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/sunshiney/v7/kaWOb4pGbwNijM7CkxK1sQ.ttf")
  }
  object `Supermercado One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/supermercadoone/v6/kMGPVTNFiFEp1U274uBMb4mm5hmSKNFf3C5YoMa-lrM.ttf")
  }
  object `Sura` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/sura/v1/jznKrhTH5NezYxb0-Q5zzA.ttf")
    def `700`: URL = URL(s"$base/sura/v1/Z5bXQaFGmoWicN1WlcncxA.ttf")
  }
  object `Suranna` {
    val category: String = "serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/suranna/v4/PYmfr6TQeTqZ-r8HnPM-kA.ttf")
  }
  object `Suravaram` {
    val category: String = "serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/suravaram/v3/G4dPee4pel_w2HqzavW4MA.ttf")
  }
  object `Suwannaphum` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/suwannaphum/v10/1jIPOyXied3T79GCnSlCN6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Swanky and Moo Moo` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/swankyandmoomoo/v6/orVNZ9kDeE3lWp3U3YELu9DVLKqNC3_XMNHhr8S94FU.ttf")
  }
  object `Syncopate` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/syncopate/v8/RQVwO52fAH6MI764EcaYtw.ttf")
    def `700`: URL = URL(s"$base/syncopate/v8/S5z8ixiOoC4WJ1im6jAlYC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Tangerine` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/tangerine/v8/DTPeM3IROhnkz7aYG2a9sA.ttf")
    def `700`: URL = URL(s"$base/tangerine/v8/UkFsr-RwJB_d2l9fIWsx3i3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Taprom` {
    val category: String = "display"
    val subsets: Set[String] = Set("khmer")
    def `regular`: URL = URL(s"$base/taprom/v8/-KByU3BaUsyIvQs79qFObg.ttf")
  }
  object `Tauri` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/tauri/v4/XIWeYJDXNqiVNej0zEqtGg.ttf")
  }
  object `Taviraj` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `100`: URL = URL(s"$base/taviraj/v2/7iDtujKEc7hwcT6D0zLx-A.ttf")
    def `100italic`: URL = URL(s"$base/taviraj/v2/ai0UdHXB1gi5etfpU0CZ6aCWcynf_cDxXwCLxiixG1c.ttf")
    def `200`: URL = URL(s"$base/taviraj/v2/fn3qCO_sC_zLuf2hqWE37fesZW2xOQ-xsNqO47m55DA.ttf")
    def `200italic`: URL = URL(s"$base/taviraj/v2/eDMMTK5GhTdvvz3R-ZWvay3USBnSvpkopQaUR-2r7iU.ttf")
    def `300`: URL = URL(s"$base/taviraj/v2/1EIpbtG_cs5haG6Ba9wX8vesZW2xOQ-xsNqO47m55DA.ttf")
    def `300italic`: URL = URL(s"$base/taviraj/v2/IEBfc1xGgsBbdCeXKNAtfS3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/taviraj/v2/AH1eoWagKJhbVx4Poc3M1A.ttf")
    def `italic`: URL = URL(s"$base/taviraj/v2/hAS5RxygdSnG4626KdkXuQ.ttf")
    def `500`: URL = URL(s"$base/taviraj/v2/s8BuqYm5ebG2N1R4JkTp_fesZW2xOQ-xsNqO47m55DA.ttf")
    def `500italic`: URL = URL(s"$base/taviraj/v2/319qfe3yzAi9RNFu-dI9zy3USBnSvpkopQaUR-2r7iU.ttf")
    def `600`: URL = URL(s"$base/taviraj/v2/KscmiA6HGz7nCcHhaddQH_esZW2xOQ-xsNqO47m55DA.ttf")
    def `600italic`: URL = URL(s"$base/taviraj/v2/ofRN6EMiboGiM2Ga3cG_yy3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/taviraj/v2/TY91892tTFNYCeCXjQ1AEPesZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/taviraj/v2/4Yzb6i1xtMRZn9oAQ484nS3USBnSvpkopQaUR-2r7iU.ttf")
    def `800`: URL = URL(s"$base/taviraj/v2/oGWJbiDGcxlInLLnrLxTDvesZW2xOQ-xsNqO47m55DA.ttf")
    def `800italic`: URL = URL(s"$base/taviraj/v2/MPtY5Qs3hwV4f0LUH-vVmy3USBnSvpkopQaUR-2r7iU.ttf")
    def `900`: URL = URL(s"$base/taviraj/v2/RfIEodnN0NYWUdZHol5fdPesZW2xOQ-xsNqO47m55DA.ttf")
    def `900italic`: URL = URL(s"$base/taviraj/v2/aDM2JaXSd_qo0nqKiBAq5C3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Teko` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `300`: URL = URL(s"$base/teko/v6/OobFGE9eo24rcBpN6zXDaQ.ttf")
    def `regular`: URL = URL(s"$base/teko/v6/UtekqODEqZXSN2L-njejpA.ttf")
    def `500`: URL = URL(s"$base/teko/v6/FQ0duU7gWM4cSaImOfAjBA.ttf")
    def `600`: URL = URL(s"$base/teko/v6/QDx_i8H-TZ1IK1JEVrqwEQ.ttf")
    def `700`: URL = URL(s"$base/teko/v6/xKfTxe_SWpH4xU75vmvylA.ttf")
  }
  object `Telex` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/telex/v5/24-3xP9ywYeHOcFU3iGk8A.ttf")
  }
  object `Tenali Ramakrishna` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/tenaliramakrishna/v3/M0nTmDqv2M7AGoGh-c946BZak5pSBHqWX6uyVMiMFoA.ttf")
  }
  object `Tenor Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/tenorsans/v7/dUBulmjNJJInvK5vL7O9yfesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Text Me One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/textmeone/v4/9em_3ckd_P5PQkP4aDyDLqCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `The Girl Next Door` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/thegirlnextdoor/v7/cWRA4JVGeEcHGcPl5hmX7kzo0nFFoM60ux_D9BUymX4.ttf")
  }
  object `Tienne` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/tienne/v9/-IIfDl701C0z7-fy2kmGvA.ttf")
    def `700`: URL = URL(s"$base/tienne/v9/JvoCDOlyOSEyYGRwCyfs3g.ttf")
    def `900`: URL = URL(s"$base/tienne/v9/FBano5T521OWexj2iRYLMw.ttf")
  }
  object `Tillana` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/tillana/v2/zN0D-jDPsr1HzU3VRFLY5g.ttf")
    def `500`: URL = URL(s"$base/tillana/v2/gqdUngSIcY9tSla5eCZky_esZW2xOQ-xsNqO47m55DA.ttf")
    def `600`: URL = URL(s"$base/tillana/v2/fqon6-r15hy8M1cyiYfQBvesZW2xOQ-xsNqO47m55DA.ttf")
    def `700`: URL = URL(s"$base/tillana/v2/jGARMTxLrMerzTCpGBpMffesZW2xOQ-xsNqO47m55DA.ttf")
    def `800`: URL = URL(s"$base/tillana/v2/pmTtNH_Ibktj5Cyc1XrP6vesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Timmana` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("telugu", "latin")
    def `regular`: URL = URL(s"$base/timmana/v1/T25SicsJUJkc2s2sbBsDnA.ttf")
  }
  object `Tinos` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "hebrew", "greek", "latin", "greek-ext", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/tinos/v10/EqpUbkVmutfwZ0PjpoGwCg.ttf")
    def `italic`: URL = URL(s"$base/tinos/v10/slfyzlasCr9vTsaP4lUh9A.ttf")
    def `700`: URL = URL(s"$base/tinos/v10/vHXfhX8jZuQruowfon93yQ.ttf")
    def `700italic`: URL = URL(s"$base/tinos/v10/M6kfzvDMM0CdxdraoFpG6vesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Titan One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/titanone/v4/FbvpRvzfV_oipS0De3iAZg.ttf")
  }
  object `Titillium Web` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `200`: URL = URL(s"$base/titilliumweb/v5/anMUvcNT0H1YN4FII8wprzOdCrLccoxq42eaxM802O0.ttf")
    def `200italic`: URL = URL(s"$base/titilliumweb/v5/RZunN20OBmkvrU7sA4GPPj4N98U-66ThNJvtgddRfBE.ttf")
    def `300`: URL = URL(s"$base/titilliumweb/v5/anMUvcNT0H1YN4FII8wpr9ZAkYT8DuUZELiKLwMGWAo.ttf")
    def `300italic`: URL = URL(s"$base/titilliumweb/v5/RZunN20OBmkvrU7sA4GPPrfzCkqg7ORZlRf2cc4mXu8.ttf")
    def `regular`: URL = URL(s"$base/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcTyagQBwYgYywpS70xNq8SQ.ttf")
    def `italic`: URL = URL(s"$base/titilliumweb/v5/r9OmwyQxrgzUAhaLET_KO-ixohbIP6lHkU-1Mgq95cY.ttf")
    def `600`: URL = URL(s"$base/titilliumweb/v5/anMUvcNT0H1YN4FII8wpr28K9dEd5Ue-HTQrlA7E2xQ.ttf")
    def `600italic`: URL = URL(s"$base/titilliumweb/v5/RZunN20OBmkvrU7sA4GPPgOhzTSndyK8UWja2yJjKLc.ttf")
    def `700`: URL = URL(s"$base/titilliumweb/v5/anMUvcNT0H1YN4FII8wpr2-6tpSbB9YhmWtmd1_gi_U.ttf")
    def `700italic`: URL = URL(s"$base/titilliumweb/v5/RZunN20OBmkvrU7sA4GPPio3LEw-4MM8Ao2j9wPOfpw.ttf")
    def `900`: URL = URL(s"$base/titilliumweb/v5/anMUvcNT0H1YN4FII8wpr7L0GmZLri-m-nfoo0Vul4Y.ttf")
  }
  object `Trade Winds` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/tradewinds/v5/sDOCVgAxw6PEUi2xdMsoDaCWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Trirong` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "thai")
    def `100`: URL = URL(s"$base/trirong/v2/A4AP1moxqvtadq5CW3L17A.ttf")
    def `100italic`: URL = URL(s"$base/trirong/v2/ke-m75CXBPHlqwRHmCTBi6CWcynf_cDxXwCLxiixG1c.ttf")
    def `200`: URL = URL(s"$base/trirong/v2/QD8N5qk-agpAEYCSSWullPesZW2xOQ-xsNqO47m55DA.ttf")
    def `200italic`: URL = URL(s"$base/trirong/v2/TLnptEEWKdIVHKJYBO592y3USBnSvpkopQaUR-2r7iU.ttf")
    def `300`: URL = URL(s"$base/trirong/v2/mfCfGz4GqprWJZ47PUMDGfesZW2xOQ-xsNqO47m55DA.ttf")
    def `300italic`: URL = URL(s"$base/trirong/v2/RnkK09k5OfEHFxd_smcYuC3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/trirong/v2/lYu4kez-Enlvh2X-itx6CA.ttf")
    def `italic`: URL = URL(s"$base/trirong/v2/kV0MzmWPKkglEtJf--dQhQ.ttf")
    def `500`: URL = URL(s"$base/trirong/v2/6CsQ6UR1e8rURaEPxqnGBvesZW2xOQ-xsNqO47m55DA.ttf")
    def `500italic`: URL = URL(s"$base/trirong/v2/I7H5Vf-5oH45BHkyxaUodS3USBnSvpkopQaUR-2r7iU.ttf")
    def `600`: URL = URL(s"$base/trirong/v2/1FjmLIhPhB6Yc7RWqO27mfesZW2xOQ-xsNqO47m55DA.ttf")
    def `600italic`: URL = URL(s"$base/trirong/v2/BXLhSV51vCWUiACSqyWe6i3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/trirong/v2/ab8hG5CTSzMAobTnPgcDP_esZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/trirong/v2/CEBv6IoZawJuRHdATx4LQi3USBnSvpkopQaUR-2r7iU.ttf")
    def `800`: URL = URL(s"$base/trirong/v2/UBRQXGJvi5EHcyI5wwZew_esZW2xOQ-xsNqO47m55DA.ttf")
    def `800italic`: URL = URL(s"$base/trirong/v2/lGUgSzOvjUqrsrJfnROivC3USBnSvpkopQaUR-2r7iU.ttf")
    def `900`: URL = URL(s"$base/trirong/v2/Lam1ewMdiP3O-bVYT-W6t_esZW2xOQ-xsNqO47m55DA.ttf")
    def `900italic`: URL = URL(s"$base/trirong/v2/EtuLHyx5DS9oX5NoKhYlkC3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Trocchi` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/trocchi/v5/uldNPaKrUGVeGCVsmacLwA.ttf")
  }
  object `Trochut` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/trochut/v4/6Y65B0x-2JsnYt16OH5omw.ttf")
    def `italic`: URL = URL(s"$base/trochut/v4/pczUwr4ZFvC79TgNO5cZng.ttf")
    def `700`: URL = URL(s"$base/trochut/v4/lWqNOv6ISR8ehNzGLFLnJ_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Trykker` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/trykker/v5/YiVrVJpBFN7I1l_CWk6yYQ.ttf")
  }
  object `Tulpen One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/tulpenone/v6/lwcTfVIEVxpZLZlWzR5baPesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Ubuntu` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "cyrillic")
    def `300`: URL = URL(s"$base/ubuntu/v10/4iCv6KVjbNBYlgoC1CzTtw.ttf")
    def `300italic`: URL = URL(s"$base/ubuntu/v10/4iCp6KVjbNBYlgoKejZftWyI.ttf")
    def `regular`: URL = URL(s"$base/ubuntu/v10/4iCs6KVjbNBYlgo6eA.ttf")
    def `italic`: URL = URL(s"$base/ubuntu/v10/4iCu6KVjbNBYlgoKeg7z.ttf")
    def `500`: URL = URL(s"$base/ubuntu/v10/4iCv6KVjbNBYlgoCjC3Ttw.ttf")
    def `500italic`: URL = URL(s"$base/ubuntu/v10/4iCp6KVjbNBYlgoKejYHtGyI.ttf")
    def `700`: URL = URL(s"$base/ubuntu/v10/4iCv6KVjbNBYlgoCxCvTtw.ttf")
    def `700italic`: URL = URL(s"$base/ubuntu/v10/4iCp6KVjbNBYlgoKejZPsmyI.ttf")
  }
  object `Ubuntu Condensed` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "cyrillic")
    def `regular`: URL = URL(s"$base/ubuntucondensed/v7/DBCt-NXN57MTAFjitYxdrKDbm6fPDOZJsR8PmdG62gY.ttf")
  }
  object `Ubuntu Mono` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "greek-ext", "cyrillic")
    def `regular`: URL = URL(s"$base/ubuntumono/v6/EgeuS9OtEmA0y_JRo03MQaCWcynf_cDxXwCLxiixG1c.ttf")
    def `italic`: URL = URL(s"$base/ubuntumono/v6/KAKuHXAHZOeECOWAHsRKA0eOrDcLawS7-ssYqLr2Xp4.ttf")
    def `700`: URL = URL(s"$base/ubuntumono/v6/ceqTZGKHipo8pJj4molytne1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `700italic`: URL = URL(s"$base/ubuntumono/v6/n_d8tv_JOIiYyMXR4eaV9c_zJjSACmk0BRPxQqhnNLU.ttf")
  }
  object `Ultra` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/ultra/v9/OW8uXkOstRADuhEmGOFQLA.ttf")
  }
  object `Uncial Antiqua` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/uncialantiqua/v4/F-leefDiFwQXsyd6eaSllqrFJ4O13IHVxZbM6yoslpo.ttf")
  }
  object `Underdog` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin", "cyrillic")
    def `regular`: URL = URL(s"$base/underdog/v5/gBv9yjez_-5PnTprHWq0ig.ttf")
  }
  object `Unica One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/unicaone/v4/KbYKlhWMDpatWViqDkNQgA.ttf")
  }
  object `UnifrakturCook` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `700`: URL = URL(s"$base/unifrakturcook/v8/ASwh69ykD8iaoYijVEU6RrWZkcsCTHKV51zmcUsafQ0.ttf")
  }
  object `UnifrakturMaguntia` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/unifrakturmaguntia/v7/7KWy3ymCVR_xfAvvcIXm3-kdNg30GQauG_DE-tMYtWk.ttf")
  }
  object `Unkempt` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/unkempt/v8/NLLBeNSspr0RGs71R5LHWA.ttf")
    def `700`: URL = URL(s"$base/unkempt/v8/V7H-GCl9bgwGwqFqTTgDHvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Unlock` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/unlock/v6/rXEQzK7uIAlhoyoAEiMy1w.ttf")
  }
  object `Unna` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/unna/v9/UAS0AM7AmbdCNY_80xyAZQ.ttf")
    def `italic`: URL = URL(s"$base/unna/v9/CB25jfOme9BL61pT4h1_0A.ttf")
    def `700`: URL = URL(s"$base/unna/v9/V-r3KRrJqBWlu97fCUB8Nw.ttf")
    def `700italic`: URL = URL(s"$base/unna/v9/H7rJH2hD4wVI9bOhx98O8A.ttf")
  }
  object `VT323` {
    val category: String = "monospace"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/vt323/v8/ITU2YQfM073o1iYK3nSOmQ.ttf")
  }
  object `Vampiro One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/vampiroone/v7/OVDs4gY4WpS5u3Qd1gXRW6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Varela` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/varela/v7/ON7qs0cKUUixhhDFXlZUjw.ttf")
  }
  object `Varela Round` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "hebrew", "latin", "vietnamese")
    def `regular`: URL = URL(s"$base/varelaround/v8/APH4jr0uSos5wiut5cpjri3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Vast Shadow` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/vastshadow/v6/io4hqKX3ibiqQQjYfW0-h6CWcynf_cDxXwCLxiixG1c.ttf")
  }
  object `Vesper Libre` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/vesperlibre/v8/Cg-TeZFsqV8BaOcoVwzu2C3USBnSvpkopQaUR-2r7iU.ttf")
    def `500`: URL = URL(s"$base/vesperlibre/v8/0liLgNkygqH6EOtsVjZDsZMQuUSAwdHsY8ov_6tk1oA.ttf")
    def `700`: URL = URL(s"$base/vesperlibre/v8/0liLgNkygqH6EOtsVjZDsUD2ttfZwueP-QU272T9-k4.ttf")
    def `900`: URL = URL(s"$base/vesperlibre/v8/0liLgNkygqH6EOtsVjZDsaObDOjC3UL77puoeHsE3fw.ttf")
  }
  object `Vibur` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/vibur/v7/xB9aKsUbJo68XP0bAg2iLw.ttf")
  }
  object `Vidaloka` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/vidaloka/v8/C6Nul0ogKUWkx356rrt9RA.ttf")
  }
  object `Viga` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/viga/v5/uD87gDbhS7frHLX4uL6agg.ttf")
  }
  object `Voces` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/voces/v6/QoBH6g6yKgNIgvL8A2aE2Q.ttf")
  }
  object `Volkhov` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/volkhov/v8/MDIZAofe1T_J3un5Kgo8zg.ttf")
    def `italic`: URL = URL(s"$base/volkhov/v8/1rTjmztKEpbkKH06JwF8Yw.ttf")
    def `700`: URL = URL(s"$base/volkhov/v8/L8PbKS-kEoLHm7nP--NCzPesZW2xOQ-xsNqO47m55DA.ttf")
    def `700italic`: URL = URL(s"$base/volkhov/v8/W6oG0QDDjCgj0gmsHE520C3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Vollkorn` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "greek", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/vollkorn/v7/IiexqYAeh8uII223thYx3w.ttf")
    def `italic`: URL = URL(s"$base/vollkorn/v7/UuIzosgR1ovBhJFdwVp3fvesZW2xOQ-xsNqO47m55DA.ttf")
    def `600`: URL = URL(s"$base/vollkorn/v7/gWz-6Uqzc1g8XxDn5f2-pKCWcynf_cDxXwCLxiixG1c.ttf")
    def `600italic`: URL = URL(s"$base/vollkorn/v7/dU1kkg9Vvuo527vzySfgDPpTEJqju4Hz1txDWij77d4.ttf")
    def `700`: URL = URL(s"$base/vollkorn/v7/gOwQjJVGXlDOONC12hVoBqCWcynf_cDxXwCLxiixG1c.ttf")
    def `700italic`: URL = URL(s"$base/vollkorn/v7/KNiAlx6phRqXCwnZZG51JAJKKGfqHaYFsRG-T3ceEVo.ttf")
    def `900`: URL = URL(s"$base/vollkorn/v7/IBcUSEL3da6GXw0kfPwtqqCWcynf_cDxXwCLxiixG1c.ttf")
    def `900italic`: URL = URL(s"$base/vollkorn/v7/5fOn_dOVwBIkZpOP3_1I750EAVxt0G0biEntp43Qt6E.ttf")
  }
  object `Voltaire` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/voltaire/v6/WvqBzaGEBbRV-hrahwO2cA.ttf")
  }
  object `Waiting for the Sunrise` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/waitingforthesunrise/v7/eNfH7kLpF1PZWpsetF-ha9TChrNgrDiT3Zy6yGf3FnM.ttf")
  }
  object `Wallpoet` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/wallpoet/v8/hmum4WuBN4A0Z_7367NDIg.ttf")
  }
  object `Walter Turncoat` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/walterturncoat/v7/sG9su5g4GXy1KP73cU3hvQplL2YwNeota48DxFlGDUo.ttf")
  }
  object `Warnes` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/warnes/v6/MXG7_Phj4YpzAXxKGItuBw.ttf")
  }
  object `Wellfleet` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/wellfleet/v4/J5tOx72iFRPgHYpbK9J4XQ.ttf")
  }
  object `Wendy One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/wendyone/v4/R8CJT2oDXdMk_ZtuHTxoxw.ttf")
  }
  object `Wire One` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/wireone/v7/sRLhaQOQpWnvXwIx0CycQw.ttf")
  }
  object `Work Sans` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `100`: URL = URL(s"$base/worksans/v2/ZAhtNqLaAViKjGLajtuwWaCWcynf_cDxXwCLxiixG1c.ttf")
    def `200`: URL = URL(s"$base/worksans/v2/u_mYNr_qYP37m7vgvmIYZy3USBnSvpkopQaUR-2r7iU.ttf")
    def `300`: URL = URL(s"$base/worksans/v2/FD_Udbezj8EHXbdsqLUply3USBnSvpkopQaUR-2r7iU.ttf")
    def `regular`: URL = URL(s"$base/worksans/v2/zVvigUiMvx7JVEnrJgc-5Q.ttf")
    def `500`: URL = URL(s"$base/worksans/v2/Nbre-U_bp6Xktt8cpgwaJC3USBnSvpkopQaUR-2r7iU.ttf")
    def `600`: URL = URL(s"$base/worksans/v2/z9rX03Xuz9ZNHTMg1_ghGS3USBnSvpkopQaUR-2r7iU.ttf")
    def `700`: URL = URL(s"$base/worksans/v2/4udXuXg54JlPEP5iKO5AmS3USBnSvpkopQaUR-2r7iU.ttf")
    def `800`: URL = URL(s"$base/worksans/v2/IQh-ap2Uqs7kl1YINeeEGi3USBnSvpkopQaUR-2r7iU.ttf")
    def `900`: URL = URL(s"$base/worksans/v2/Hjn0acvjHfjY_vAK9Uc6gi3USBnSvpkopQaUR-2r7iU.ttf")
  }
  object `Yanone Kaffeesatz` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "latin", "vietnamese", "cyrillic")
    def `200`: URL = URL(s"$base/yanonekaffeesatz/v8/We_iSDqttE3etzfdfhuPRbq92v6XxU4pSv06GI0NsGc.ttf")
    def `300`: URL = URL(s"$base/yanonekaffeesatz/v8/We_iSDqttE3etzfdfhuPRZlIwXPiNoNT_wxzJ2t3mTE.ttf")
    def `regular`: URL = URL(s"$base/yanonekaffeesatz/v8/YDAoLskQQ5MOAgvHUQCcLdXn3cHbFGWU4T2HrSN6JF4.ttf")
    def `700`: URL = URL(s"$base/yanonekaffeesatz/v8/We_iSDqttE3etzfdfhuPRf2R4S6PlKaGXWPfWpHpcl0.ttf")
  }
  object `Yantramanav` {
    val category: String = "sans-serif"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `100`: URL = URL(s"$base/yantramanav/v2/Rs1I2PF4Z8GAb6qjgvr8wIAWxXGWZ3yJw6KhWS7MxOk.ttf")
    def `300`: URL = URL(s"$base/yantramanav/v2/HSfbC4Z8I8BZ00wiXeA5bC9-WlPSxbfiI49GsXo3q0g.ttf")
    def `regular`: URL = URL(s"$base/yantramanav/v2/FwdziO-qWAO8pZg8e376kaCWcynf_cDxXwCLxiixG1c.ttf")
    def `500`: URL = URL(s"$base/yantramanav/v2/HSfbC4Z8I8BZ00wiXeA5bMCNfqCYlB_eIx7H1TVXe60.ttf")
    def `700`: URL = URL(s"$base/yantramanav/v2/HSfbC4Z8I8BZ00wiXeA5bHe1Pd76Vl7zRpE7NLJQ7XU.ttf")
    def `900`: URL = URL(s"$base/yantramanav/v2/HSfbC4Z8I8BZ00wiXeA5bCenaqEuufTBk9XMKnKmgDA.ttf")
  }
  object `Yatra One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "devanagari", "latin")
    def `regular`: URL = URL(s"$base/yatraone/v3/ApKQzWF7_vG0Lt5TDqgUvw.ttf")
  }
  object `Yellowtail` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/yellowtail/v7/HLrU6lhCTjXfLZ7X60LcB_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Yeseva One` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "cyrillic-ext", "latin", "vietnamese", "cyrillic")
    def `regular`: URL = URL(s"$base/yesevaone/v11/eenQQxvpzSA80JmisGcgX_esZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Yesteryear` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/yesteryear/v5/dv09hP_ZrdjVOfZQXKXuZvesZW2xOQ-xsNqO47m55DA.ttf")
  }
  object `Yrsa` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/yrsa/v2/YI0C1syzAYpkrPx27UnC2w.ttf")
    def `regular`: URL = URL(s"$base/yrsa/v2/JWX_dCK4_Jq-oqF7r9rFHg.ttf")
    def `500`: URL = URL(s"$base/yrsa/v2/rWuZmBLHIeKRbnfSvWCvYg.ttf")
    def `600`: URL = URL(s"$base/yrsa/v2/1413P-oEfrq-tBIdqnslDQ.ttf")
    def `700`: URL = URL(s"$base/yrsa/v2/iV49zaJV5wyo_4LgxE2yng.ttf")
  }
  object `Zeyada` {
    val category: String = "handwriting"
    val subsets: Set[String] = Set("latin")
    def `regular`: URL = URL(s"$base/zeyada/v6/hmonmGYYFwqTZQfG2nRswQ.ttf")
  }
  object `Zilla Slab` {
    val category: String = "serif"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `300`: URL = URL(s"$base/zillaslab/v2/MIkI-zFTb-IKu6GQ4qfBIUeOrDcLawS7-ssYqLr2Xp4.ttf")
    def `300italic`: URL = URL(s"$base/zillaslab/v2/SlbCHfLtf3uBEqmR9ezZMqcQoVhARpoaILP7amxE_8g.ttf")
    def `regular`: URL = URL(s"$base/zillaslab/v2/GQa6C2kQZDjk1E7wBSIhnPesZW2xOQ-xsNqO47m55DA.ttf")
    def `italic`: URL = URL(s"$base/zillaslab/v2/0uwn9tpUNTyjFGXazfTluC3USBnSvpkopQaUR-2r7iU.ttf")
    def `500`: URL = URL(s"$base/zillaslab/v2/M-lMpg6F7WVOVam88MR7yJp-63r6doWhTEbsfBIRJ7A.ttf")
    def `500italic`: URL = URL(s"$base/zillaslab/v2/SlbCHfLtf3uBEqmR9ezZMpMQuUSAwdHsY8ov_6tk1oA.ttf")
    def `600`: URL = URL(s"$base/zillaslab/v2/idTxEJxWLSyMdm2hH0_fO_pTEJqju4Hz1txDWij77d4.ttf")
    def `600italic`: URL = URL(s"$base/zillaslab/v2/SlbCHfLtf3uBEqmR9ezZMmv8CylhIUtwUiYO7Z2wXbE.ttf")
    def `700`: URL = URL(s"$base/zillaslab/v2/5alS-fi1sAYG-KJydQxv8AJKKGfqHaYFsRG-T3ceEVo.ttf")
    def `700italic`: URL = URL(s"$base/zillaslab/v2/SlbCHfLtf3uBEqmR9ezZMkD2ttfZwueP-QU272T9-k4.ttf")
  }
  object `Zilla Slab Highlight` {
    val category: String = "display"
    val subsets: Set[String] = Set("latin-ext", "latin")
    def `regular`: URL = URL(s"$base/zillaslabhighlight/v2/A1oFQmFZMluFeVEQs3f1ZsRj1XVSCnpi3yrU572D-Ys.ttf")
    def `700`: URL = URL(s"$base/zillaslabhighlight/v2/4GC1z5cbR6tbZfervoVHHDJanj6ILIntqP8io1sy9nk.ttf")
  }
}
       