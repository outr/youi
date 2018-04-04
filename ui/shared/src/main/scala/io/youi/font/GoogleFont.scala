package io.youi.font

import io.youi.net.URL

trait GoogleFont {
  def family: String
  def category: String
  def subsets: GoogleFontSubsets
  def weights: List[GoogleFontWeight]
}

object GoogleFont {
  private val base: String = s"https://fonts.gstatic.com/s"

  object `ABeeZee` extends GoogleFont {
    override lazy val family: String = "ABeeZee"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/abeezee/v11/esDR31xSG-6AGleN6tI.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/abeezee/v11/esDT31xSG-6AGleN2tCklQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Abel` extends GoogleFont {
    override lazy val family: String = "Abel"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/abel/v8/MwQ5bhbm2POE6Vg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Abhaya Libre` extends GoogleFont {
    override lazy val family: String = "Abhaya Libre"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `sinhala`: GoogleFontSubset = new GoogleFontSubset("sinhala")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `sinhala`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/abhayalibre/v3/e3tmeuGtX-Co5MNzeAOqinEgew.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/abhayalibre/v3/e3t5euGtX-Co5MNzeAOqinEYj2ryqg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/abhayalibre/v3/e3t5euGtX-Co5MNzeAOqinEYo23yqg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/abhayalibre/v3/e3t5euGtX-Co5MNzeAOqinEYx2zyqg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/abhayalibre/v3/e3t5euGtX-Co5MNzeAOqinEY22_yqg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `800`)
  }
  object `Abril Fatface` extends GoogleFont {
    override lazy val family: String = "Abril Fatface"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/abrilfatface/v9/zOL64pLDlL1D99S8g8PtiKchm-A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Aclonica` extends GoogleFont {
    override lazy val family: String = "Aclonica"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aclonica/v8/K2FyfZJVlfNNSEBXGb7T.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Acme` extends GoogleFont {
    override lazy val family: String = "Acme"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/acme/v7/RrQfboBx-C5_bx0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Actor` extends GoogleFont {
    override lazy val family: String = "Actor"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/actor/v7/wEOzEBbCkc5cO3ek.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Adamina` extends GoogleFont {
    override lazy val family: String = "Adamina"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/adamina/v10/j8_r6-DH1bjoc-dwu-o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Advent Pro` extends GoogleFont {
    override lazy val family: String = "Advent Pro"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `greek`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/adventpro/v7/V8mCoQfxVT4Dvddr_yOwjVmtLQ.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/adventpro/v7/V8mDoQfxVT4Dvddr_yOwjfWMDbY.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/adventpro/v7/V8mDoQfxVT4Dvddr_yOwjZGPDbY.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/adventpro/v7/V8mAoQfxVT4Dvddr_yOwtT0.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/adventpro/v7/V8mDoQfxVT4Dvddr_yOwjcmODbY.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/adventpro/v7/V8mDoQfxVT4Dvddr_yOwjeWJDbY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/adventpro/v7/V8mDoQfxVT4Dvddr_yOwjYGIDbY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Aguafina Script` extends GoogleFont {
    override lazy val family: String = "Aguafina Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aguafinascript/v6/If2QXTv_ZzSxGIO30LemWEOmt1bHqg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Akronim` extends GoogleFont {
    override lazy val family: String = "Akronim"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/akronim/v7/fdN-9sqWtWZZlHRp-gA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Aladin` extends GoogleFont {
    override lazy val family: String = "Aladin"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aladin/v6/ZgNSjPJFPrvJV5f16Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Aldrich` extends GoogleFont {
    override lazy val family: String = "Aldrich"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aldrich/v8/MCoTzAn-1s3IGyJMZaA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Alef` extends GoogleFont {
    override lazy val family: String = "Alef"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `hebrew`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alef/v9/FeVfS0NQpLYgrjI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/alef/v9/FeVQS0NQpLYglo50L5k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Alegreya` extends GoogleFont {
    override lazy val family: String = "Alegreya"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alegreya/v10/4UaBrEBBsBhlBjvfkRLm.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/alegreya/v10/4UaHrEBBsBhlBjvfkSLkx60.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/alegreya/v10/4UaGrEBBsBhlBjvfkSoS5I3J.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/alegreya/v10/4UaErEBBsBhlBjvfkSLk_1nKwps.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/alegreya/v10/4UaGrEBBsBhlBjvfkSpa4o3J.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/alegreya/v10/4UaErEBBsBhlBjvfkSLk_xHMwps.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/alegreya/v10/4UaGrEBBsBhlBjvfkSpG4Y3J.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/alegreya/v10/4UaErEBBsBhlBjvfkSLk_w3Pwps.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/alegreya/v10/4UaGrEBBsBhlBjvfkSpi4I3J.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/alegreya/v10/4UaErEBBsBhlBjvfkSLk_ynOwps.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Alegreya SC` extends GoogleFont {
    override lazy val family: String = "Alegreya SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alegreyasc/v9/taiOGmRtCJ62-O0HhNEa-a6o.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/alegreyasc/v9/taiMGmRtCJ62-O0HhNEa-Z6q2ZU.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/alegreyasc/v9/taiTGmRtCJ62-O0HhNEa-ZZc-rUx.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/alegreyasc/v9/taiRGmRtCJ62-O0HhNEa-Z6q4WEySK8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/alegreyasc/v9/taiTGmRtCJ62-O0HhNEa-ZYU_LUx.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/alegreyasc/v9/taiRGmRtCJ62-O0HhNEa-Z6q4Sk0SK8.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/alegreyasc/v9/taiTGmRtCJ62-O0HhNEa-ZYI_7Ux.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/alegreyasc/v9/taiRGmRtCJ62-O0HhNEa-Z6q4TU3SK8.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/alegreyasc/v9/taiTGmRtCJ62-O0HhNEa-ZYs_rUx.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/alegreyasc/v9/taiRGmRtCJ62-O0HhNEa-Z6q4RE2SK8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Alegreya Sans` extends GoogleFont {
    override lazy val family: String = "Alegreya Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/alegreyasans/v8/5aUt9_-1phKLFgshYDvh6Vwt5TltuA.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/alegreyasans/v8/5aUv9_-1phKLFgshYDvh6Vwt7V9V3G1W.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/alegreyasans/v8/5aUu9_-1phKLFgshYDvh6Vwt5fFPmE0.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/alegreyasans/v8/5aUo9_-1phKLFgshYDvh6Vwt7V9VFE92jg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alegreyasans/v8/5aUz9_-1phKLFgshYDvh6Vwt3V0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/alegreyasans/v8/5aUt9_-1phKLFgshYDvh6Vwt7V9tuA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/alegreyasans/v8/5aUu9_-1phKLFgshYDvh6Vwt5alOmE0.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/alegreyasans/v8/5aUo9_-1phKLFgshYDvh6Vwt7V9VTE52jg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/alegreyasans/v8/5aUu9_-1phKLFgshYDvh6Vwt5eFImE0.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/alegreyasans/v8/5aUo9_-1phKLFgshYDvh6Vwt7V9VBEh2jg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/alegreyasans/v8/5aUu9_-1phKLFgshYDvh6Vwt5f1LmE0.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/alegreyasans/v8/5aUo9_-1phKLFgshYDvh6Vwt7V9VGEt2jg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/alegreyasans/v8/5aUu9_-1phKLFgshYDvh6Vwt5dlKmE0.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/alegreyasans/v8/5aUo9_-1phKLFgshYDvh6Vwt7V9VPEp2jg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Alegreya Sans SC` extends GoogleFont {
    override lazy val family: String = "Alegreya Sans SC"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/alegreyasanssc/v7/mtGn4-RGJqfMvt7P8FUr0Q1j-Hf1Dipl8g.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/alegreyasanssc/v7/mtGl4-RGJqfMvt7P8FUr0Q1j-Hf1BkxdlgRB.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/alegreyasanssc/v7/mtGm4-RGJqfMvt7P8FUr0Q1j-Hf1DuJH0iQ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/alegreyasanssc/v7/mtGk4-RGJqfMvt7P8FUr0Q1j-Hf1BkxdXiZhNQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alegreyasanssc/v7/mtGh4-RGJqfMvt7P8FUr0Q1j-Hf1Nk4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/alegreyasanssc/v7/mtGn4-RGJqfMvt7P8FUr0Q1j-Hf1Bkxl8g.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/alegreyasanssc/v7/mtGm4-RGJqfMvt7P8FUr0Q1j-Hf1DrpG0iQ.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/alegreyasanssc/v7/mtGk4-RGJqfMvt7P8FUr0Q1j-Hf1BkxdBidhNQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/alegreyasanssc/v7/mtGm4-RGJqfMvt7P8FUr0Q1j-Hf1DvJA0iQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/alegreyasanssc/v7/mtGk4-RGJqfMvt7P8FUr0Q1j-Hf1BkxdTiFhNQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/alegreyasanssc/v7/mtGm4-RGJqfMvt7P8FUr0Q1j-Hf1Du5D0iQ.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/alegreyasanssc/v7/mtGk4-RGJqfMvt7P8FUr0Q1j-Hf1BkxdUiJhNQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/alegreyasanssc/v7/mtGm4-RGJqfMvt7P8FUr0Q1j-Hf1DspC0iQ.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/alegreyasanssc/v7/mtGk4-RGJqfMvt7P8FUr0Q1j-Hf1BkxddiNhNQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Alex Brush` extends GoogleFont {
    override lazy val family: String = "Alex Brush"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alexbrush/v8/SZc83FzrJKuqFbwMKk6EtUI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Alfa Slab One` extends GoogleFont {
    override lazy val family: String = "Alfa Slab One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alfaslabone/v7/6NUQ8FmMKwSEKjnm5-4v-4Jh6dU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Alice` extends GoogleFont {
    override lazy val family: String = "Alice"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alice/v9/OpNCnoEEmtHa6FcJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Alike` extends GoogleFont {
    override lazy val family: String = "Alike"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alike/v10/HI_EiYEYI6BIoEjB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Alike Angular` extends GoogleFont {
    override lazy val family: String = "Alike Angular"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alikeangular/v8/3qTrojWunjGQtEBlIcwMbSoI3kM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Allan` extends GoogleFont {
    override lazy val family: String = "Allan"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/allan/v9/ea8XadU7WuTxEtb2.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/allan/v9/ea8aadU7WuTxEu5KEPCN.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Allerta` extends GoogleFont {
    override lazy val family: String = "Allerta"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/allerta/v8/TwMO-IAHRlkbx940UnE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Allerta Stencil` extends GoogleFont {
    override lazy val family: String = "Allerta Stencil"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/allertastencil/v8/HTx0L209KT-LmIE9N7OR6eiycOeF-w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Allura` extends GoogleFont {
    override lazy val family: String = "Allura"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/allura/v6/9oRPNYsQpS4zjuAPjA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Almendra` extends GoogleFont {
    override lazy val family: String = "Almendra"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/almendra/v10/H4ckBXKAlMnTn0CskyY6.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/almendra/v10/H4ciBXKAlMnTn0CskxY4yLs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/almendra/v10/H4cjBXKAlMnTn0Cskx6G7Zu4.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/almendra/v10/H4chBXKAlMnTn0CskxY48Ae9oqY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Almendra Display` extends GoogleFont {
    override lazy val family: String = "Almendra Display"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/almendradisplay/v8/0FlPVOGWl1Sb4O3tETtADHRRlZhzXS8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Almendra SC` extends GoogleFont {
    override lazy val family: String = "Almendra SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/almendrasc/v8/Iure6Yx284eebowr7hbyTZZJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Amarante` extends GoogleFont {
    override lazy val family: String = "Amarante"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amarante/v5/xMQXuF1KTa6EvGx9bq-3.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Amaranth` extends GoogleFont {
    override lazy val family: String = "Amaranth"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amaranth/v8/KtkuALODe433f0j1zPnC.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/amaranth/v8/KtkoALODe433f0j1zMnAHdU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/amaranth/v8/KtkpALODe433f0j1zMF-OPWi.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/amaranth/v8/KtkrALODe433f0j1zMnAJWmn42Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Amatic SC` extends GoogleFont {
    override lazy val family: String = "Amatic SC"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `hebrew`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amaticsc/v11/TUZyzwprpvBS1izr_vO0DQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/amaticsc/v11/TUZ3zwprpvBS1izr_vOMscG6eQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Amethysta` extends GoogleFont {
    override lazy val family: String = "Amethysta"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amethysta/v6/rP2Fp2K15kgb_F3ibfWIGA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Amiko` extends GoogleFont {
    override lazy val family: String = "Amiko"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amiko/v2/WwkQxPq1DFK04tql.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/amiko/v2/WwkdxPq1DFK04uJ9XXrE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/amiko/v2/WwkdxPq1DFK04uIZXHrE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `600`, `700`)
  }
  object `Amiri` extends GoogleFont {
    override lazy val family: String = "Amiri"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amiri/v10/J7aRnpd8CGxBHqUp.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/amiri/v10/J7afnpd8CGxBHpUrtLY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/amiri/v10/J7acnpd8CGxBHp2VkZY4.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/amiri/v10/J7aanpd8CGxBHpUrjAo9zps.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Amita` extends GoogleFont {
    override lazy val family: String = "Amita"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amita/v3/HhyaU5si9Om7PQlv.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/amita/v3/HhyXU5si9Om7PTHTLtCC.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Anaheim` extends GoogleFont {
    override lazy val family: String = "Anaheim"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/anaheim/v5/8vII7w042Wp87g4G0UQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Andada` extends GoogleFont {
    override lazy val family: String = "Andada"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/andada/v9/uK_y4riWaego3w9RCg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Andika` extends GoogleFont {
    override lazy val family: String = "Andika"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/andika/v9/mem_Ya6iyW-LwqgAbQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Angkor` extends GoogleFont {
    override lazy val family: String = "Angkor"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/angkor/v10/H4cmBXyAlsPdnlb-8g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Annie Use Your Telescope` extends GoogleFont {
    override lazy val family: String = "Annie Use Your Telescope"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/annieuseyourtelescope/v8/daaLSS4tI2qYYl3Jq9s_Hu74xwktnlKxH6osGVGjlA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Anonymous Pro` extends GoogleFont {
    override lazy val family: String = "Anonymous Pro"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/anonymouspro/v11/rP2Bp2a15UIB7Un-bOeISG3pLlw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/anonymouspro/v11/rP2fp2a15UIB7Un-bOeISG3pHl428A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/anonymouspro/v11/rP2cp2a15UIB7Un-bOeISG3pFuAT0Ck.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/anonymouspro/v11/rP2ap2a15UIB7Un-bOeISG3pHl4OTCzc6A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Antic` extends GoogleFont {
    override lazy val family: String = "Antic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/antic/v9/TuGfUVB8XY5DRaZL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Antic Didone` extends GoogleFont {
    override lazy val family: String = "Antic Didone"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/anticdidone/v6/RWmPoKKX6u8sp8fIWdnDKqDiqQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Antic Slab` extends GoogleFont {
    override lazy val family: String = "Antic Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/anticslab/v6/bWt97fPFfRzkCa9Jlp6IWcI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Anton` extends GoogleFont {
    override lazy val family: String = "Anton"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/anton/v9/1Ptgg87LROyAm0K0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Arapey` extends GoogleFont {
    override lazy val family: String = "Arapey"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arapey/v6/-W__XJn-UDDA2RC6Zw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/arapey/v6/-W_9XJn-UDDA2RCKZdoY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Arbutus` extends GoogleFont {
    override lazy val family: String = "Arbutus"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arbutus/v7/NaPYcZ7dG_5J3poob9I.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Arbutus Slab` extends GoogleFont {
    override lazy val family: String = "Arbutus Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arbutusslab/v6/oY1Z8e7OuLXkJGbXtr5ba7ZVaw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Architects Daughter` extends GoogleFont {
    override lazy val family: String = "Architects Daughter"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/architectsdaughter/v8/KtkxAKiDZI_td1Lkx62xHZHDtgO_Y-bvfY4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Archivo` extends GoogleFont {
    override lazy val family: String = "Archivo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/archivo/v3/k3kQo8UDI-1M0wlSTd4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/archivo/v3/k3kSo8UDI-1M0wlSfdzoKw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/archivo/v3/k3kVo8UDI-1M0wlSdSrLC0E.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/archivo/v3/k3kXo8UDI-1M0wlSfdzQ30LhKQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/archivo/v3/k3kVo8UDI-1M0wlSdQbMC0E.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/archivo/v3/k3kXo8UDI-1M0wlSfdzQ80XhKQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/archivo/v3/k3kVo8UDI-1M0wlSdWLNC0E.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/archivo/v3/k3kXo8UDI-1M0wlSfdzQl0ThKQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Archivo Black` extends GoogleFont {
    override lazy val family: String = "Archivo Black"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/archivoblack/v7/HTxqL289NzCGg4MzN6KJ7eW6OYs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Archivo Narrow` extends GoogleFont {
    override lazy val family: String = "Archivo Narrow"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/archivonarrow/v8/tss0ApVBdCYD5Q7hcxTE1ArZ0Yb3.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/archivonarrow/v8/tss2ApVBdCYD5Q7hcxTE1ArZ0bb1iXk.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/archivonarrow/v8/tss3ApVBdCYD5Q7hcxTE1ArZ0b4Dqlla.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/archivonarrow/v8/tssxApVBdCYD5Q7hcxTE1ArZ0bb1sY1Z-9c.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/archivonarrow/v8/tss3ApVBdCYD5Q7hcxTE1ArZ0b4vrVla.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/archivonarrow/v8/tssxApVBdCYD5Q7hcxTE1ArZ0bb1saFe-9c.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/archivonarrow/v8/tss3ApVBdCYD5Q7hcxTE1ArZ0b5LrFla.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/archivonarrow/v8/tssxApVBdCYD5Q7hcxTE1ArZ0bb1scVf-9c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Aref Ruqaa` extends GoogleFont {
    override lazy val family: String = "Aref Ruqaa"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arefruqaa/v5/WwkbxPW1E165rajQKDulEIA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arefruqaa/v5/WwkYxPW1E165rajQKDulKDwNcNI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Arima Madurai` extends GoogleFont {
    override lazy val family: String = "Arima Madurai"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`tamil`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/arimamadurai/v3/t5t4IRoeKYORG0WNMgnC3seB1V3Pqg.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/arimamadurai/v3/t5t7IRoeKYORG0WNMgnC3seB1fHuips.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/arimamadurai/v3/t5t7IRoeKYORG0WNMgnC3seB1ZXtips.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arimamadurai/v3/t5tmIRoeKYORG0WNMgnC3seB7Tk.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/arimamadurai/v3/t5t7IRoeKYORG0WNMgnC3seB1c3sips.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arimamadurai/v3/t5t7IRoeKYORG0WNMgnC3seB1YXqips.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/arimamadurai/v3/t5t7IRoeKYORG0WNMgnC3seB1Znpips.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/arimamadurai/v3/t5t7IRoeKYORG0WNMgnC3seB1b3oips.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `700`, `800`, `900`)
  }
  object `Arimo` extends GoogleFont {
    override lazy val family: String = "Arimo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `hebrew`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arimo/v11/P5sMzZCDf9_T_20e.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/arimo/v11/P5sCzZCDf9_T_10cxCQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arimo/v11/P5sBzZCDf9_T_1Wi4QRE.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/arimo/v11/P5sHzZCDf9_T_10c_JhBrZc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Arizonia` extends GoogleFont {
    override lazy val family: String = "Arizonia"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arizonia/v8/neIIzCemt4A5qa7mv6WG.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Armata` extends GoogleFont {
    override lazy val family: String = "Armata"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/armata/v8/gokvH63_HV5jQ-E9lA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Arsenal` extends GoogleFont {
    override lazy val family: String = "Arsenal"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arsenal/v2/wXKrE3kQtZQ4pF3D118.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/arsenal/v2/wXKpE3kQtZQ4pF3D513cBQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arsenal/v2/wXKuE3kQtZQ4pF3D7-P5JeQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/arsenal/v2/wXKsE3kQtZQ4pF3D513kueEKnQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Artifika` extends GoogleFont {
    override lazy val family: String = "Artifika"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/artifika/v8/VEMyRoxzronptCuxu6Wt.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Arvo` extends GoogleFont {
    override lazy val family: String = "Arvo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arvo/v10/tDbD2oWUg0MKmSA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/arvo/v10/tDbN2oWUg0MKqSIQ6A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arvo/v10/tDbM2oWUg0MKoZw1yLQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/arvo/v10/tDbO2oWUg0MKqSIoVLHK9g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Arya` extends GoogleFont {
    override lazy val family: String = "Arya"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arya/v3/ga6CawNG-HJd9UY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arya/v3/ga6NawNG-HJdzfra3b8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Asap` extends GoogleFont {
    override lazy val family: String = "Asap"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/asap/v7/KFOoCniXp96a-zw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/asap/v7/KFOmCniXp96ayz4e5Q.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/asap/v7/KFOnCniXp96aw8g9xUw.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/asap/v7/KFOlCniXp96ayz4mEU9vAw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/asap/v7/KFOnCniXp96aw-Q6xUw.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/asap/v7/KFOlCniXp96ayz4mPUhvAw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/asap/v7/KFOnCniXp96aw4A7xUw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/asap/v7/KFOlCniXp96ayz4mWUlvAw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Asap Condensed` extends GoogleFont {
    override lazy val family: String = "Asap Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/asapcondensed/v2/pxidypY1o9NHyXh3WvSbGSggdNeL.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/asapcondensed/v2/pxifypY1o9NHyXh3WvSbGSggdOeJaEk.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/asapcondensed/v2/pxieypY1o9NHyXh3WvSbGSggdO9_S2lE.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/asapcondensed/v2/pxiYypY1o9NHyXh3WvSbGSggdOeJUL1Him4.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/asapcondensed/v2/pxieypY1o9NHyXh3WvSbGSggdO9TTGlE.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/asapcondensed/v2/pxiYypY1o9NHyXh3WvSbGSggdOeJUJFAim4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/asapcondensed/v2/pxieypY1o9NHyXh3WvSbGSggdO83TWlE.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/asapcondensed/v2/pxiYypY1o9NHyXh3WvSbGSggdOeJUPVBim4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Asar` extends GoogleFont {
    override lazy val family: String = "Asar"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/asar/v4/sZlLdRyI6TBIXkY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Asset` extends GoogleFont {
    override lazy val family: String = "Asset"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/asset/v8/SLXGc1na-mM4cWIm.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Assistant` extends GoogleFont {
    override lazy val family: String = "Assistant"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `hebrew`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/assistant/v2/2sDZZGJYnIjSi6H75xk7p0ScAw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/assistant/v2/2sDZZGJYnIjSi6H75xk7w0ecAw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/assistant/v2/2sDcZGJYnIjSi6H75xkDbw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/assistant/v2/2sDZZGJYnIjSi6H75xk7t0GcAw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/assistant/v2/2sDZZGJYnIjSi6H75xk700CcAw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/assistant/v2/2sDZZGJYnIjSi6H75xk7z0OcAw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `600`, `700`, `800`)
  }
  object `Astloch` extends GoogleFont {
    override lazy val family: String = "Astloch"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/astloch/v8/TuGRUVJ8QI5GSeUjq9w.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/astloch/v8/TuGUUVJ8QI5GSeUjk2A-6MM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Asul` extends GoogleFont {
    override lazy val family: String = "Asul"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/asul/v7/VuJ-dNjKxYr46fM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/asul/v7/VuJxdNjKxYr40U8qeKY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Athiti` extends GoogleFont {
    override lazy val family: String = "Athiti"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/athiti/v2/pe0sMISdLIZIv1wAxDNyAg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/athiti/v2/pe0sMISdLIZIv1wAoDByAg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/athiti/v2/pe0vMISdLIZIv1w4DA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/athiti/v2/pe0sMISdLIZIv1wA-DFyAg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/athiti/v2/pe0sMISdLIZIv1wA1DZyAg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/athiti/v2/pe0sMISdLIZIv1wAsDdyAg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Atma` extends GoogleFont {
    override lazy val family: String = "Atma"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `bengali`: GoogleFontSubset = new GoogleFontSubset("bengali")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `bengali`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/atma/v3/uK_z4rqWc-Eoo8JzKjc.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/atma/v3/uK_84rqWc-Eom24.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/atma/v3/uK_z4rqWc-Eoo5pyKjc.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/atma/v3/uK_z4rqWc-Eoo7Z1Kjc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/atma/v3/uK_z4rqWc-Eoo9J0Kjc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Atomic Age` extends GoogleFont {
    override lazy val family: String = "Atomic Age"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/atomicage/v9/f0Xz0eug6sdmRFkYZZGL58E.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Aubrey` extends GoogleFont {
    override lazy val family: String = "Aubrey"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aubrey/v10/q5uGsou7NPBw-p7vug.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Audiowide` extends GoogleFont {
    override lazy val family: String = "Audiowide"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/audiowide/v6/l7gdbjpo0cum0ckerWCtkQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Autour One` extends GoogleFont {
    override lazy val family: String = "Autour One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/autourone/v7/UqyVK80cP25l3fJgbdfbk5k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Average` extends GoogleFont {
    override lazy val family: String = "Average"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/average/v6/fC1hPYBHe23MxA7rIeI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Average Sans` extends GoogleFont {
    override lazy val family: String = "Average Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/averagesans/v6/1Ptpg8fLXP2dlAXR-HlJJNJPBQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Averia Gruesa Libre` extends GoogleFont {
    override lazy val family: String = "Averia Gruesa Libre"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/averiagruesalibre/v6/NGSov4nEGEktOaDRKsY-1dhh8eEtIx3ZUmk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Averia Libre` extends GoogleFont {
    override lazy val family: String = "Averia Libre"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/averialibre/v6/2V0FKIcMGZEnV6xygz7eNjEarovtbw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/averialibre/v6/2V0HKIcMGZEnV6xygz7eNjESAJFhbUTp.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/averialibre/v6/2V0aKIcMGZEnV6xygz7eNjEiAg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/averialibre/v6/2V0EKIcMGZEnV6xygz7eNjESAKnN.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/averialibre/v6/2V0FKIcMGZEnV6xygz7eNjEavoztbw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/averialibre/v6/2V0HKIcMGZEnV6xygz7eNjESAJFxakTp.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`)
  }
  object `Averia Sans Libre` extends GoogleFont {
    override lazy val family: String = "Averia Sans Libre"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/averiasanslibre/v6/ga6SaxZG_G5OvCf_rt7FH3B6BHLMEd3lMKcQ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/averiasanslibre/v6/ga6caxZG_G5OvCf_rt7FH3B6BHLMEdVLKisSL5c.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/averiasanslibre/v6/ga6XaxZG_G5OvCf_rt7FH3B6BHLMEeVJ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/averiasanslibre/v6/ga6RaxZG_G5OvCf_rt7FH3B6BHLMEdVLEoc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/averiasanslibre/v6/ga6SaxZG_G5OvCf_rt7FH3B6BHLMEd31N6cQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/averiasanslibre/v6/ga6caxZG_G5OvCf_rt7FH3B6BHLMEdVLKjsVL5c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`)
  }
  object `Averia Serif Libre` extends GoogleFont {
    override lazy val family: String = "Averia Serif Libre"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/averiaseriflibre/v7/neIVzD2ms4wxr6GvjeD0X88SHPyX2xYGCSmqwQ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/averiaseriflibre/v7/neIbzD2ms4wxr6GvjeD0X88SHPyX2xYOpzMmw60u.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/averiaseriflibre/v7/neIWzD2ms4wxr6GvjeD0X88SHPyX2xY-pQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/averiaseriflibre/v7/neIUzD2ms4wxr6GvjeD0X88SHPyX2xYOpwuK.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/averiaseriflibre/v7/neIVzD2ms4wxr6GvjeD0X88SHPyX2xYGGS6qwQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/averiaseriflibre/v7/neIbzD2ms4wxr6GvjeD0X88SHPyX2xYOpzM2xK0u.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`)
  }
  object `Bad Script` extends GoogleFont {
    override lazy val family: String = "Bad Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/badscript/v6/6NUT8F6PJgbFWQn47_x7lOw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bahiana` extends GoogleFont {
    override lazy val family: String = "Bahiana"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bahiana/v2/uU9PCBUV4YenPWJU7xM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo` extends GoogleFont {
    override lazy val family: String = "Baloo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloo/v3/6xKhdSpJJ92I9PWI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Bhai` extends GoogleFont {
    override lazy val family: String = "Baloo Bhai"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `gujarati`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloobhai/v3/ZgNWjP5GM7bCUdmXgWyVjGU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Bhaijaan` extends GoogleFont {
    override lazy val family: String = "Baloo Bhaijaan"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloobhaijaan/v3/RWmRoKCU5fcqq8fOWNzFLqSjx4EC.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Bhaina` extends GoogleFont {
    override lazy val family: String = "Baloo Bhaina"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `oriya`: GoogleFontSubset = new GoogleFontSubset("oriya")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`oriya`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloobhaina/v3/Noa16Uzzzp2FIkfhq5vm9thxPA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Chettan` extends GoogleFont {
    override lazy val family: String = "Baloo Chettan"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `malayalam`: GoogleFontSubset = new GoogleFontSubset("malayalam")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `malayalam`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloochettan/v3/0QImMXRN8o2gTC2YTr4665DA07w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Da` extends GoogleFont {
    override lazy val family: String = "Baloo Da"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `bengali`: GoogleFontSubset = new GoogleFontSubset("bengali")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`, `bengali`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/balooda/v3/LhWmMVnXOfIZO795FXkf.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Paaji` extends GoogleFont {
    override lazy val family: String = "Baloo Paaji"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `gurmukhi`: GoogleFontSubset = new GoogleFontSubset("gurmukhi")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`gurmukhi`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloopaaji/v3/8AttGsyxM5KQQU-Y4MTwVZnT.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Tamma` extends GoogleFont {
    override lazy val family: String = "Baloo Tamma"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `kannada`: GoogleFontSubset = new GoogleFontSubset("kannada")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`kannada`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/balootamma/v3/JTUTjIk68Cy27gWhOWIghE5B.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Tammudu` extends GoogleFont {
    override lazy val family: String = "Baloo Tammudu"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/balootammudu/v3/mFT3Wb8Qza7c_Z5HTsC_5nxW8Eo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Thambi` extends GoogleFont {
    override lazy val family: String = "Baloo Thambi"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`tamil`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloothambi/v3/va9B4kXJzNhTFoA7CYcS8sHuQQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Balthazar` extends GoogleFont {
    override lazy val family: String = "Balthazar"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/balthazar/v6/d6lKkaajS8Gm4CVQjFEvyQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bangers` extends GoogleFont {
    override lazy val family: String = "Bangers"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bangers/v10/FeVQS0BTqb0h60ACL5k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Barlow` extends GoogleFont {
    override lazy val family: String = "Barlow"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/barlow/v1/7cHrv4kjgoGqM7E3b8s8.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/barlow/v1/7cHtv4kjgoGqM7E_CfNYwHo.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/barlow/v1/7cHqv4kjgoGqM7E3w-oc4A.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/barlow/v1/7cHsv4kjgoGqM7E_CfP04Vop.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/barlow/v1/7cHqv4kjgoGqM7E3p-kc4A.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/barlow/v1/7cHsv4kjgoGqM7E_CfOQ4lop.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/barlow/v1/7cHpv4kjgoGqM7EPCw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/barlow/v1/7cHrv4kjgoGqM7E_Ccs8.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/barlow/v1/7cHqv4kjgoGqM7E3_-gc4A.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/barlow/v1/7cHsv4kjgoGqM7E_CfPI41op.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/barlow/v1/7cHqv4kjgoGqM7E30-8c4A.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/barlow/v1/7cHsv4kjgoGqM7E_CfPk5Fop.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/barlow/v1/7cHqv4kjgoGqM7E3t-4c4A.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/barlow/v1/7cHsv4kjgoGqM7E_CfOA5Vop.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/barlow/v1/7cHqv4kjgoGqM7E3q-0c4A.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/barlow/v1/7cHsv4kjgoGqM7E_CfOc5lop.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/barlow/v1/7cHqv4kjgoGqM7E3j-wc4A.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/barlow/v1/7cHsv4kjgoGqM7E_CfO451op.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Barlow Condensed` extends GoogleFont {
    override lazy val family: String = "Barlow Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/barlowcondensed/v1/HTxxL3I-JCGChYJ8VI-L6OO_au7B43LT3w.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/barlowcondensed/v1/HTxzL3I-JCGChYJ8VI-L6OO_au7B6xTru1H2.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/barlowcondensed/v1/HTxwL3I-JCGChYJ8VI-L6OO_au7B497y_3E.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/barlowcondensed/v1/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrF3DWvA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/barlowcondensed/v1/HTxwL3I-JCGChYJ8VI-L6OO_au7B47rx_3E.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/barlowcondensed/v1/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrc3PWvA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/barlowcondensed/v1/HTx3L3I-JCGChYJ8VI-L6OO_au7B2xY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/barlowcondensed/v1/HTxxL3I-JCGChYJ8VI-L6OO_au7B6xTT3w.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/barlowcondensed/v1/HTxwL3I-JCGChYJ8VI-L6OO_au7B4-Lw_3E.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/barlowcondensed/v1/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrK3LWvA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/barlowcondensed/v1/HTxwL3I-JCGChYJ8VI-L6OO_au7B4873_3E.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/barlowcondensed/v1/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrB3XWvA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/barlowcondensed/v1/HTxwL3I-JCGChYJ8VI-L6OO_au7B46r2_3E.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/barlowcondensed/v1/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrY3TWvA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/barlowcondensed/v1/HTxwL3I-JCGChYJ8VI-L6OO_au7B47b1_3E.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/barlowcondensed/v1/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrf3fWvA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/barlowcondensed/v1/HTxwL3I-JCGChYJ8VI-L6OO_au7B45L0_3E.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/barlowcondensed/v1/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrW3bWvA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Barlow Semi Condensed` extends GoogleFont {
    override lazy val family: String = "Barlow Semi Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/barlowsemicondensed/v1/wlphgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRfG4qv.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/barlowsemicondensed/v1/wlpjgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbLLIEs.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/barlowsemicondensed/v1/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRft6uPAA.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/barlowsemicondensed/v1/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbJnAWsg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/barlowsemicondensed/v1/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRf06iPAA.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/barlowsemicondensed/v1/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbIDAmsg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/barlowsemicondensed/v1/wlpvgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRnfw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/barlowsemicondensed/v1/wlphgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfYqv.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/barlowsemicondensed/v1/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRfi6mPAA.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/barlowsemicondensed/v1/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbJbA2sg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/barlowsemicondensed/v1/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRfp66PAA.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/barlowsemicondensed/v1/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbJ3BGsg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/barlowsemicondensed/v1/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRfw6-PAA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/barlowsemicondensed/v1/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbITBWsg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/barlowsemicondensed/v1/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRf36yPAA.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/barlowsemicondensed/v1/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbIPBmsg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/barlowsemicondensed/v1/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRf-62PAA.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/barlowsemicondensed/v1/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbIrB2sg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Barrio` extends GoogleFont {
    override lazy val family: String = "Barrio"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/barrio/v2/wEO8EBXBk8hBIDiEdQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Basic` extends GoogleFont {
    override lazy val family: String = "Basic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/basic/v7/xfu_0WLxV2_XKQN3.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Battambang` extends GoogleFont {
    override lazy val family: String = "Battambang"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/battambang/v11/uk-mEGe7raEw-HjkzZabDnU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/battambang/v11/uk-lEGe7raEw-HjkzZabNsmMxyQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Baumans` extends GoogleFont {
    override lazy val family: String = "Baumans"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baumans/v7/-W_-XJj9QyTd3QfpR_o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bayon` extends GoogleFont {
    override lazy val family: String = "Bayon"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bayon/v10/9XUrlJNmn0LPFl-p.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Belgrano` extends GoogleFont {
    override lazy val family: String = "Belgrano"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/belgrano/v8/55xvey5tM9rwKWrJZcMF.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bellefair` extends GoogleFont {
    override lazy val family: String = "Bellefair"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `hebrew`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bellefair/v3/kJExBuYY6AAuhiXUxG19_w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Belleza` extends GoogleFont {
    override lazy val family: String = "Belleza"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/belleza/v6/0nkoC9_pNeMfhX4BtcY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `BenchNine` extends GoogleFont {
    override lazy val family: String = "BenchNine"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/benchnine/v6/ahcev8612zF4jxrwMosT--tRhQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/benchnine/v6/ahcbv8612zF4jxrwMosrVw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/benchnine/v6/ahcev8612zF4jxrwMosT6-xRhQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Bentham` extends GoogleFont {
    override lazy val family: String = "Bentham"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bentham/v8/VdGeAZQPEpYfmHglKWw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Berkshire Swash` extends GoogleFont {
    override lazy val family: String = "Berkshire Swash"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/berkshireswash/v6/ptRRTi-cavZOGqCvnNJDl5m5XmNPrQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bevan` extends GoogleFont {
    override lazy val family: String = "Bevan"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bevan/v9/4iCj6KZ0a9NXjF8a.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bigelow Rules` extends GoogleFont {
    override lazy val family: String = "Bigelow Rules"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bigelowrules/v6/RrQWboly8iR_I3KWSzeRuN0zT4c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bigshot One` extends GoogleFont {
    override lazy val family: String = "Bigshot One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bigshotone/v8/u-470qukhRkkO6BD_7cM_gxu.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bilbo` extends GoogleFont {
    override lazy val family: String = "Bilbo"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bilbo/v7/o-0EIpgpwWwZ210h.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bilbo Swash Caps` extends GoogleFont {
    override lazy val family: String = "Bilbo Swash Caps"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bilboswashcaps/v9/zrf-0GXbz-H3Wb4XBsGrTgq2PVmdqAM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `BioRhyme` extends GoogleFont {
    override lazy val family: String = "BioRhyme"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/biorhyme/v2/1cX3aULHBpDMsHYW_ESOjnGA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/biorhyme/v2/1cX3aULHBpDMsHYW_ETqjXGA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/biorhyme/v2/1cXwaULHBpDMsHYW_HxG.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/biorhyme/v2/1cX3aULHBpDMsHYW_ET6inGA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/biorhyme/v2/1cX3aULHBpDMsHYW_ETmiXGA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `700`, `800`)
  }
  object `BioRhyme Expanded` extends GoogleFont {
    override lazy val family: String = "BioRhyme Expanded"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/biorhymeexpanded/v3/i7dVIE1zZzytGswgU577CDY9LjbffxxcblSH.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/biorhymeexpanded/v3/i7dVIE1zZzytGswgU577CDY9Ljbffxw4bVSH.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/biorhymeexpanded/v3/i7dQIE1zZzytGswgU577CDY9LjbffySU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/biorhymeexpanded/v3/i7dVIE1zZzytGswgU577CDY9LjbffxwoalSH.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/biorhymeexpanded/v3/i7dVIE1zZzytGswgU577CDY9Ljbffxw0aVSH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `700`, `800`)
  }
  object `Biryani` extends GoogleFont {
    override lazy val family: String = "Biryani"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/biryani/v3/hv-TlzNxIFoO84YddYQyGTA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/biryani/v3/hv-TlzNxIFoO84YddeAxGTA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/biryani/v3/hv-WlzNxIFoO84YdTUw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/biryani/v3/hv-TlzNxIFoO84YddZQ3GTA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/biryani/v3/hv-TlzNxIFoO84YddfA2GTA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/biryani/v3/hv-TlzNxIFoO84Yddew1GTA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/biryani/v3/hv-TlzNxIFoO84Yddcg0GTA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `600`, `700`, `800`, `900`)
  }
  object `Bitter` extends GoogleFont {
    override lazy val family: String = "Bitter"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bitter/v12/rax8HiqOu8IVPmnLeA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/bitter/v12/rax-HiqOu8IVPmn7eoxs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/bitter/v12/rax_HiqOu8IVPmnzxKlMBA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Black And White Picture` extends GoogleFont {
    override lazy val family: String = "Black And White Picture"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/blackandwhitepicture/v2/TwMe-JAERlQd3ooUHBUXGmrmioKjjnRSFO-NqI5H.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Black Han Sans` extends GoogleFont {
    override lazy val family: String = "Black Han Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/blackhansans/v2/ea8Aad44WunzF9a-dL6toA8r8nqV.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Black Ops One` extends GoogleFont {
    override lazy val family: String = "Black Ops One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/blackopsone/v9/qWcsB6-ypo7xBdr6Xshe96H3WDw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bokor` extends GoogleFont {
    override lazy val family: String = "Bokor"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bokor/v10/m8JcjfpeeaqTiR2W.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bonbon` extends GoogleFont {
    override lazy val family: String = "Bonbon"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bonbon/v9/0FlVVPeVlFec4ee_cA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Boogaloo` extends GoogleFont {
    override lazy val family: String = "Boogaloo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/boogaloo/v8/kmK-Zq45GAvOdnaW6x1F.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bowlby One` extends GoogleFont {
    override lazy val family: String = "Bowlby One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bowlbyone/v9/taiPGmVuC4y96PFeqp8smo4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bowlby One SC` extends GoogleFont {
    override lazy val family: String = "Bowlby One SC"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bowlbyonesc/v9/DtVlJxerQqQm37tzN3wMug9Pzgg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Brawler` extends GoogleFont {
    override lazy val family: String = "Brawler"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/brawler/v8/xn7gYHE3xXewAscGsgA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bree Serif` extends GoogleFont {
    override lazy val family: String = "Bree Serif"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/breeserif/v7/4UaHrEJCrhhnVA3DgluAx60.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bubblegum Sans` extends GoogleFont {
    override lazy val family: String = "Bubblegum Sans"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bubblegumsans/v6/AYCSpXb_Z9EORv1M5QTjEzMEtdaH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bubbler One` extends GoogleFont {
    override lazy val family: String = "Bubbler One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bubblerone/v6/f0Xy0eqj68ppQV9KBLmAouHH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Buda` extends GoogleFont {
    override lazy val family: String = "Buda"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/buda/v8/GFDqWAN8mnyIJSSrG7U.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`)
  }
  object `Buenard` extends GoogleFont {
    override lazy val family: String = "Buenard"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/buenard/v9/OD5DuM6Cyma8FnnsPzc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/buenard/v9/OD5GuM6Cyma8FnnsB4vSjGA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Bungee` extends GoogleFont {
    override lazy val family: String = "Bungee"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bungee/v3/N0bU2SZBIuF2PU_ECg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bungee Hairline` extends GoogleFont {
    override lazy val family: String = "Bungee Hairline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bungeehairline/v3/snfys0G548t04270a_ljTLUVrv-7YA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bungee Inline` extends GoogleFont {
    override lazy val family: String = "Bungee Inline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bungeeinline/v3/Gg8zN58UcgnlCweMrih332VuDGI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bungee Outline` extends GoogleFont {
    override lazy val family: String = "Bungee Outline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bungeeoutline/v3/_6_mEDvmVP24UvU2MyiGDslL3Qg3.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bungee Shade` extends GoogleFont {
    override lazy val family: String = "Bungee Shade"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bungeeshade/v3/DtVkJxarWL0t2KdzK3oI_jks7g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Butcherman` extends GoogleFont {
    override lazy val family: String = "Butcherman"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/butcherman/v9/2EbiL-thF0loflXUBOdb1zU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Butterfly Kids` extends GoogleFont {
    override lazy val family: String = "Butterfly Kids"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/butterflykids/v6/ll8lK2CWTjuqAsXDqlnIbMNs5S4a.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cabin` extends GoogleFont {
    override lazy val family: String = "Cabin"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cabin/v12/u-4x0qWljRw-Pe83.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cabin/v12/u-4_0qWljRw-Pd81__g.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cabin/v12/u-480qWljRw-PdfD3Nhi.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/cabin/v12/u-460qWljRw-Pd81xwxhuyw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cabin/v12/u-480qWljRw-Pdfv29hi.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/cabin/v12/u-460qWljRw-Pd81xyBmuyw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cabin/v12/u-480qWljRw-PdeL2thi.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cabin/v12/u-460qWljRw-Pd81x0Rnuyw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Cabin Condensed` extends GoogleFont {
    override lazy val family: String = "Cabin Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cabincondensed/v11/nwpMtK6mNhBK2err_hqkYhHRqmwaYA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cabincondensed/v11/nwpJtK6mNhBK2err_hqkYhHRqmwilMH97A.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cabincondensed/v11/nwpJtK6mNhBK2err_hqkYhHRqmwiuMb97A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cabincondensed/v11/nwpJtK6mNhBK2err_hqkYhHRqmwi3Mf97A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`)
  }
  object `Cabin Sketch` extends GoogleFont {
    override lazy val family: String = "Cabin Sketch"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cabinsketch/v11/QGYpz_kZZAGCONcK2A4bGOjMnw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cabinsketch/v11/QGY2z_kZZAGCONcK2A4bGOj0I_1o4Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Caesar Dressing` extends GoogleFont {
    override lazy val family: String = "Caesar Dressing"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/caesardressing/v6/yYLx0hLa3vawqtwdswbotmK4vrR3cQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cagliostro` extends GoogleFont {
    override lazy val family: String = "Cagliostro"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cagliostro/v6/ZgNWjP5HM73BV5amnX-TjGU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cairo` extends GoogleFont {
    override lazy val family: String = "Cairo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `arabic`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/cairo/v3/SLXLc1nY6Hkvalrub76M.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cairo/v3/SLXLc1nY6HkvalqKbL6M.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cairo/v3/SLXGc1nY6HkvamIm.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cairo/v3/SLXLc1nY6Hkvalr-ar6M.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cairo/v3/SLXLc1nY6Hkvalqaa76M.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/cairo/v3/SLXLc1nY6Hkvalqiab6M.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `600`, `700`, `900`)
  }
  object `Calligraffitti` extends GoogleFont {
    override lazy val family: String = "Calligraffitti"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/calligraffitti/v9/46k2lbT3XjDVqJw3DCmCFjE0vnFZ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cambay` extends GoogleFont {
    override lazy val family: String = "Cambay"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cambay/v3/SLXJc1rY6H0_ZDsGbg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cambay/v3/SLXLc1rY6H0_ZDs2bL6M.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cambay/v3/SLXKc1rY6H0_ZDs-0pusxw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cambay/v3/SLXMc1rY6H0_ZDs2bIYwwvN0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Cambo` extends GoogleFont {
    override lazy val family: String = "Cambo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cambo/v6/IFSqHeNEk8FJk416.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Candal` extends GoogleFont {
    override lazy val family: String = "Candal"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/candal/v7/XoHn2YH6T7-t_8cNAQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cantarell` extends GoogleFont {
    override lazy val family: String = "Cantarell"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cantarell/v7/B50NF7ZDq37KMUvlO01Jiw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cantarell/v7/B50LF7ZDq37KMUvlO015iaJu.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cantarell/v7/B50IF7ZDq37KMUvlO01xN4dOFA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cantarell/v7/B50WF7ZDq37KMUvlO015iZrSEY6a.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Cantata One` extends GoogleFont {
    override lazy val family: String = "Cantata One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cantataone/v7/PlI5Fl60Nb5obNzNe2jslVxE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cantora One` extends GoogleFont {
    override lazy val family: String = "Cantora One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cantoraone/v7/gyB4hws1JdgnKy56GB_JX6zd.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Capriola` extends GoogleFont {
    override lazy val family: String = "Capriola"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/capriola/v5/wXKoE3YSppcvo1PDln_8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cardo` extends GoogleFont {
    override lazy val family: String = "Cardo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `greek-ext`, `greek`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cardo/v9/wlp_gwjKBV1pqiv_.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cardo/v9/wlpxgwjKBV1pqhv93IQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cardo/v9/wlpygwjKBV1pqhND-aQR.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Carme` extends GoogleFont {
    override lazy val family: String = "Carme"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/carme/v8/ptRHTiWdbvZIDOjG.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Carrois Gothic` extends GoogleFont {
    override lazy val family: String = "Carrois Gothic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/carroisgothic/v7/Z9XPDmFATg-N1PLtLOOxvIHl9ZmD.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Carrois Gothic SC` extends GoogleFont {
    override lazy val family: String = "Carrois Gothic SC"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/carroisgothicsc/v7/ZgNJjOVHM6jfUZCmyUqT2A2HVKjc-28n.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Carter One` extends GoogleFont {
    override lazy val family: String = "Carter One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/carterone/v9/q5uCsoe5IOB2-pXv9UcNIxQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Catamaran` extends GoogleFont {
    override lazy val family: String = "Catamaran"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`tamil`, `latin`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/catamaran/v4/o-0OIpQoyXQa2RxT7-5jhjRF.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/catamaran/v4/o-0NIpQoyXQa2RxT7-5jKhVlYw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/catamaran/v4/o-0NIpQoyXQa2RxT7-5jThZlYw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/catamaran/v4/o-0IIpQoyXQa2RxT7-5b4g.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/catamaran/v4/o-0NIpQoyXQa2RxT7-5jFhdlYw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/catamaran/v4/o-0NIpQoyXQa2RxT7-5jOhBlYw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/catamaran/v4/o-0NIpQoyXQa2RxT7-5jXhFlYw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/catamaran/v4/o-0NIpQoyXQa2RxT7-5jQhJlYw.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/catamaran/v4/o-0NIpQoyXQa2RxT7-5jZhNlYw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Caudex` extends GoogleFont {
    override lazy val family: String = "Caudex"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `greek-ext`, `greek`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/caudex/v7/esDQ311QOP6BJUrIyg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/caudex/v7/esDS311QOP6BJUr4yPKE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/caudex/v7/esDT311QOP6BJUrwdteklQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/caudex/v7/esDV311QOP6BJUr4yMo4kJ8G.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Caveat` extends GoogleFont {
    override lazy val family: String = "Caveat"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/caveat/v4/Wnz6HAc5bAfYB2QLYQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/caveat/v4/Wnz5HAc5bAfYB2Qz3RM9og.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Caveat Brush` extends GoogleFont {
    override lazy val family: String = "Caveat Brush"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/caveatbrush/v3/EYq0maZfwr9S9-ETZc3fKXtMWw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cedarville Cursive` extends GoogleFont {
    override lazy val family: String = "Cedarville Cursive"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cedarvillecursive/v8/yYL00g_a2veiudhUmxjo5VKkoqA-B_neJQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ceviche One` extends GoogleFont {
    override lazy val family: String = "Ceviche One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cevicheone/v8/gyB4hws1IcA6JzR-GB_JX6zd.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Changa` extends GoogleFont {
    override lazy val family: String = "Changa"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `arabic`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/changa/v4/2-cl9JNi2YuVOUcsqb2bUg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/changa/v4/2-cl9JNi2YuVOUcszb6bUg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/changa/v4/2-cm9JNi2YuVOUcUYQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/changa/v4/2-cl9JNi2YuVOUcslb-bUg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/changa/v4/2-cl9JNi2YuVOUcsubibUg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/changa/v4/2-cl9JNi2YuVOUcs3bmbUg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/changa/v4/2-cl9JNi2YuVOUcswbqbUg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Changa One` extends GoogleFont {
    override lazy val family: String = "Changa One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/changaone/v10/xfu00W3wXn3QLUJXhzq46AY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/changaone/v10/xfu20W3wXn3QLUJXhzq42ATivA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Chango` extends GoogleFont {
    override lazy val family: String = "Chango"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chango/v6/2V0cKI0OB5U7WaJyzw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Chathura` extends GoogleFont {
    override lazy val family: String = "Chathura"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/chathura/v3/_gP91R7-rzUuVjim42dEq0Q.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/chathura/v3/_gP81R7-rzUuVjim42eMiWSx.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chathura/v3/_gP71R7-rzUuVjim418g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/chathura/v3/_gP81R7-rzUuVjim42ecjmSx.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/chathura/v3/_gP81R7-rzUuVjim42eAjWSx.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `700`, `800`)
  }
  object `Chau Philomene One` extends GoogleFont {
    override lazy val family: String = "Chau Philomene One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chauphilomeneone/v7/55xxezRsPtfie1vPY49qzdgSlJiHRQFsnA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/chauphilomeneone/v7/55xzezRsPtfie1vPY49qzdgSlJiHRQFcnoZ_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Chela One` extends GoogleFont {
    override lazy val family: String = "Chela One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chelaone/v6/6ae-4KC7Uqgdz_JZdPIy3w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Chelsea Market` extends GoogleFont {
    override lazy val family: String = "Chelsea Market"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chelseamarket/v5/BCawqZsHqfr89WNP_IApC8tzKBhl.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Chenla` extends GoogleFont {
    override lazy val family: String = "Chenla"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chenla/v10/SZc43FDpIKu8WZ9eXw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cherry Cream Soda` extends GoogleFont {
    override lazy val family: String = "Cherry Cream Soda"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cherrycreamsoda/v8/UMBIrOxBrW6w2FFyi9paG0fdVdRciTd6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cherry Swash` extends GoogleFont {
    override lazy val family: String = "Cherry Swash"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cherryswash/v5/i7dNIFByZjaNAMxtZcnfAy58QA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cherryswash/v5/i7dSIFByZjaNAMxtZcnfAy5E_FeaGw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Chewy` extends GoogleFont {
    override lazy val family: String = "Chewy"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chewy/v9/uK_94ruUb-k-wk5x.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Chicle` extends GoogleFont {
    override lazy val family: String = "Chicle"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chicle/v6/lJwG-pw9i2dqU-BDyQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Chivo` extends GoogleFont {
    override lazy val family: String = "Chivo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/chivo/v9/va9F4kzIxd1KFrjDY8Z_.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/chivo/v9/va9D4kzIxd1KFrBteUp9sKg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chivo/v9/va9I4kzIxd1KFoBv.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/chivo/v9/va9G4kzIxd1KFrBtQeY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/chivo/v9/va9F4kzIxd1KFrjTZMZ_.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/chivo/v9/va9D4kzIxd1KFrBteVp6sKg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/chivo/v9/va9F4kzIxd1KFrjrZsZ_.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/chivo/v9/va9D4kzIxd1KFrBteWJ4sKg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Chonburi` extends GoogleFont {
    override lazy val family: String = "Chonburi"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chonburi/v2/8AtqGs-wOpGRTBq66IWa.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cinzel` extends GoogleFont {
    override lazy val family: String = "Cinzel"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cinzel/v7/8vIJ7ww63mVu7gtL8Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cinzel/v7/8vIK7ww63mVu7gtzTUHeFA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/cinzel/v7/8vIK7ww63mVu7gtzdUPeFA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `900`)
  }
  object `Cinzel Decorative` extends GoogleFont {
    override lazy val family: String = "Cinzel Decorative"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cinzeldecorative/v6/daaCSScvJGqLYhG8nNt8KPPswUAPnh7U.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cinzeldecorative/v6/daaHSScvJGqLYhG8nNt8KPPswUAPniZoaelD.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/cinzeldecorative/v6/daaHSScvJGqLYhG8nNt8KPPswUAPniZQa-lD.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `900`)
  }
  object `Clicker Script` extends GoogleFont {
    override lazy val family: String = "Clicker Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/clickerscript/v5/raxkHiKPvt8CMH6ZWP8PdlEq72rY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Coda` extends GoogleFont {
    override lazy val family: String = "Coda"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/coda/v13/SLXHc1jY5nQ8JUI.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/coda/v13/SLXIc1jY5nQ8HeIgTp4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `800`)
  }
  object `Coda Caption` extends GoogleFont {
    override lazy val family: String = "Coda Caption"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/codacaption/v11/ieVm2YRII2GMY7SyXSoDRiQGqcx6xw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`800`)
  }
  object `Codystar` extends GoogleFont {
    override lazy val family: String = "Codystar"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/codystar/v5/FwZf7-Q1xVk-40qxOuYsyuyr.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/codystar/v5/FwZY7-Q1xVk-40qxOt6A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`)
  }
  object `Coiny` extends GoogleFont {
    override lazy val family: String = "Coiny"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`tamil`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/coiny/v3/gyByhwU1K989PXwb.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Combo` extends GoogleFont {
    override lazy val family: String = "Combo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/combo/v6/BXRlvF3Jh_fIhg0i.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Comfortaa` extends GoogleFont {
    override lazy val family: String = "Comfortaa"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/comfortaa/v12/1Ptpg8LJRfWJmhDAuUsw5qNPBQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/comfortaa/v12/1Ptsg8LJRfWJmhDAuUsISg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/comfortaa/v12/1Ptpg8LJRfWJmhDAuUsw9qRPBQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Coming Soon` extends GoogleFont {
    override lazy val family: String = "Coming Soon"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/comingsoon/v8/qWcuB6mzpYL7AJ2VfdQR1u-S.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Concert One` extends GoogleFont {
    override lazy val family: String = "Concert One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/concertone/v8/VEM1Ro9xs5PjtzCu-srDqRTl.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Condiment` extends GoogleFont {
    override lazy val family: String = "Condiment"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/condiment/v5/pONk1hggFNmwvXALyH6Sqw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Content` extends GoogleFont {
    override lazy val family: String = "Content"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/content/v9/zrfl0HLayePhU_AwUaA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/content/v9/zrfg0HLayePhU_AwaRzdBio.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Contrail One` extends GoogleFont {
    override lazy val family: String = "Contrail One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/contrailone/v7/eLGbP-j_JA-kG0_Zo51noafdZQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Convergence` extends GoogleFont {
    override lazy val family: String = "Convergence"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/convergence/v6/rax5HiePvdgXPmmMHcIPYRha.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cookie` extends GoogleFont {
    override lazy val family: String = "Cookie"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cookie/v8/syky-y18lb0tSbfNlQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Copse` extends GoogleFont {
    override lazy val family: String = "Copse"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/copse/v7/11hPGpDKz1rGb0dj.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Corben` extends GoogleFont {
    override lazy val family: String = "Corben"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/corben/v11/LYjDdGzzklQtCMp9oA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/corben/v11/LYjAdGzzklQtCMpFHCZgrQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Cormorant` extends GoogleFont {
    override lazy val family: String = "Cormorant"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorant/v6/H4cgBXOCl9bbnla_nHIiRLmYgg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/cormorant/v6/H4c-BXOCl9bbnla_nHIq6qMUgIa2.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorant/v6/H4clBXOCl9bbnla_nHIa6A.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cormorant/v6/H4cjBXOCl9bbnla_nHIq6pu4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorant/v6/H4cgBXOCl9bbnla_nHIiHLiYgg.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/cormorant/v6/H4c-BXOCl9bbnla_nHIq6qNMgYa2.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorant/v6/H4cgBXOCl9bbnla_nHIiML-Ygg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/cormorant/v6/H4c-BXOCl9bbnla_nHIq6qNghoa2.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorant/v6/H4cgBXOCl9bbnla_nHIiVL6Ygg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cormorant/v6/H4c-BXOCl9bbnla_nHIq6qMEh4a2.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Cormorant Garamond` extends GoogleFont {
    override lazy val family: String = "Cormorant Garamond"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorantgaramond/v5/co3YmX5slCNuHLi8bLeY9MK7whWMhyjQAllvuQ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/cormorantgaramond/v5/co3WmX5slCNuHLi8bLeY9MK7whWMhyjYrEPjuw-N.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorantgaramond/v5/co3bmX5slCNuHLi8bLeY9MK7whWMhyjorg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cormorantgaramond/v5/co3ZmX5slCNuHLi8bLeY9MK7whWMhyjYrHtP.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorantgaramond/v5/co3YmX5slCNuHLi8bLeY9MK7whWMhyjQWlhvuQ.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/cormorantgaramond/v5/co3WmX5slCNuHLi8bLeY9MK7whWMhyjYrEO7ug-N.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorantgaramond/v5/co3YmX5slCNuHLi8bLeY9MK7whWMhyjQdl9vuQ.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/cormorantgaramond/v5/co3WmX5slCNuHLi8bLeY9MK7whWMhyjYrEOXvQ-N.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorantgaramond/v5/co3YmX5slCNuHLi8bLeY9MK7whWMhyjQEl5vuQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cormorantgaramond/v5/co3WmX5slCNuHLi8bLeY9MK7whWMhyjYrEPzvA-N.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Cormorant Infant` extends GoogleFont {
    override lazy val family: String = "Cormorant Infant"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorantinfant/v5/HhyIU44g9vKiM1sORYSiWeAsLN9951w3_DM.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/cormorantinfant/v5/HhyKU44g9vKiM1sORYSiWeAsLN997_ItcDEhRg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorantinfant/v5/HhyPU44g9vKiM1sORYSiWeAsLN993_A.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cormorantinfant/v5/HhyJU44g9vKiM1sORYSiWeAsLN997_IV3A.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorantinfant/v5/HhyIU44g9vKiM1sORYSiWeAsLN995wQ2_DM.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/cormorantinfant/v5/HhyKU44g9vKiM1sORYSiWeAsLN997_ItKDAhRg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorantinfant/v5/HhyIU44g9vKiM1sORYSiWeAsLN995ygx_DM.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/cormorantinfant/v5/HhyKU44g9vKiM1sORYSiWeAsLN997_ItBDchRg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorantinfant/v5/HhyIU44g9vKiM1sORYSiWeAsLN9950ww_DM.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cormorantinfant/v5/HhyKU44g9vKiM1sORYSiWeAsLN997_ItYDYhRg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Cormorant SC` extends GoogleFont {
    override lazy val family: String = "Cormorant SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorantsc/v5/0ybmGD4kxqXBmOVLG30OGwsmABIU_Q.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorantsc/v5/0yb5GD4kxqXBmOVLG30OGwserA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorantsc/v5/0ybmGD4kxqXBmOVLG30OGwsmWBMU_Q.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorantsc/v5/0ybmGD4kxqXBmOVLG30OGwsmdBQU_Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorantsc/v5/0ybmGD4kxqXBmOVLG30OGwsmEBUU_Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Cormorant Unicase` extends GoogleFont {
    override lazy val family: String = "Cormorant Unicase"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorantunicase/v5/HI_ViZUaILtOqhqgDeXoF_n1_fTGX9N_tucv.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorantunicase/v5/HI_QiZUaILtOqhqgDeXoF_n1_fTGX-vT.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorantunicase/v5/HI_ViZUaILtOqhqgDeXoF_n1_fTGX9Mnt-cv.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorantunicase/v5/HI_ViZUaILtOqhqgDeXoF_n1_fTGX9MLsOcv.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorantunicase/v5/HI_ViZUaILtOqhqgDeXoF_n1_fTGX9Nvsecv.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Cormorant Upright` extends GoogleFont {
    override lazy val family: String = "Cormorant Upright"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorantupright/v4/VuJudM3I2Y35poFONtLdafkUCHw1y1N5phDs.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorantupright/v4/VuJrdM3I2Y35poFONtLdafkUCHw1y2vV.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorantupright/v4/VuJudM3I2Y35poFONtLdafkUCHw1y1MhpxDs.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorantupright/v4/VuJudM3I2Y35poFONtLdafkUCHw1y1MNoBDs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorantupright/v4/VuJudM3I2Y35poFONtLdafkUCHw1y1NpoRDs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Courgette` extends GoogleFont {
    override lazy val family: String = "Courgette"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/courgette/v5/wEO_EBrAnc9BLjLQAUkFUQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cousine` extends GoogleFont {
    override lazy val family: String = "Cousine"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `hebrew`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cousine/v12/d6lIkaiiRdih4SpPzSM.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cousine/v12/d6lKkaiiRdih4SpP_SEvyQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cousine/v12/d6lNkaiiRdih4SpP9Z8K6T4.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cousine/v12/d6lPkaiiRdih4SpP_SEXdTvM1w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Coustard` extends GoogleFont {
    override lazy val family: String = "Coustard"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/coustard/v8/3XFpErgg3YsZ5fqUU9UP.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/coustard/v8/3XFuErgg3YsZ5fqUU-2LkEHm.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `900`)
  }
  object `Covered By Your Grace` extends GoogleFont {
    override lazy val family: String = "Covered By Your Grace"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/coveredbyyourgrace/v7/QGYwz-AZahWOJJI9kykWW9mD6opopoqXSOS0Fg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Crafty Girls` extends GoogleFont {
    override lazy val family: String = "Crafty Girls"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/craftygirls/v7/va9B4kXI39VaDdlPJo8N_NvuQQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Creepster` extends GoogleFont {
    override lazy val family: String = "Creepster"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/creepster/v6/AlZy_zVUqJz4yMrniH4hdQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Crete Round` extends GoogleFont {
    override lazy val family: String = "Crete Round"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/creteround/v6/55xoey1sJNPjPiv1ZZZrxJ18.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/creteround/v6/55xqey1sJNPjPiv1ZZZrxK1-0bg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Crimson Text` extends GoogleFont {
    override lazy val family: String = "Crimson Text"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/crimsontext/v8/wlp2gwHKFkZgtmSR3NB0oRJvaA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/crimsontext/v8/wlpogwHKFkZgtmSR3NB0oRJfaghW.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/crimsontext/v8/wlppgwHKFkZgtmSR3NB0oRJXsCx2Cw.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/crimsontext/v8/wlprgwHKFkZgtmSR3NB0oRJfajCOD9NV.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/crimsontext/v8/wlppgwHKFkZgtmSR3NB0oRJX1C12Cw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/crimsontext/v8/wlprgwHKFkZgtmSR3NB0oRJfajDqDtNV.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Croissant One` extends GoogleFont {
    override lazy val family: String = "Croissant One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/croissantone/v5/3y9n6bU9bTPg4m8NDy3Kq24UM3o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Crushed` extends GoogleFont {
    override lazy val family: String = "Crushed"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/crushed/v8/U9Mc6dym6WXImTlFT1k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cuprum` extends GoogleFont {
    override lazy val family: String = "Cuprum"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cuprum/v9/dg4k_pLmvrkcOkB9IQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cuprum/v9/dg4m_pLmvrkcOkBNI-tH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cuprum/v9/dg4n_pLmvrkcOkBFnc5njw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cuprum/v9/dg4h_pLmvrkcOkBNI9P7ipwt.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Cute Font` extends GoogleFont {
    override lazy val family: String = "Cute Font"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cutefont/v2/Noaw6Uny2oWPbSHMrY6vmA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cutive` extends GoogleFont {
    override lazy val family: String = "Cutive"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cutive/v9/NaPZcZ_fHOhV3Ip7Tw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cutive Mono` extends GoogleFont {
    override lazy val family: String = "Cutive Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cutivemono/v6/m8JWjfRfY7WVjVi2E-K9H5RF.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Damion` extends GoogleFont {
    override lazy val family: String = "Damion"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/damion/v7/hv-XlzJ3KEUe_YZUbQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dancing Script` extends GoogleFont {
    override lazy val family: String = "Dancing Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dancingscript/v9/If2RXTr6YS-zF4S-kcSWSVi_swLn.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/dancingscript/v9/If2SXTr6YS-zF4S-kcSWSVi_szpbr8Qt.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Dangrek` extends GoogleFont {
    override lazy val family: String = "Dangrek"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dangrek/v9/LYjCdG30nEgoH8E2gCM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `David Libre` extends GoogleFont {
    override lazy val family: String = "David Libre"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `hebrew`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/davidlibre/v2/snfus0W_99N64iuYSvp4W_l8.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/davidlibre/v2/snfzs0W_99N64iuYSvp4W8GIw7qb.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/davidlibre/v2/snfzs0W_99N64iuYSvp4W8HAxbqb.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `700`)
  }
  object `Dawning of a New Day` extends GoogleFont {
    override lazy val family: String = "Dawning of a New Day"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dawningofanewday/v8/t5t_IQMbOp2SEwuncwLRjMfIg1yYit_nAz8b.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Days One` extends GoogleFont {
    override lazy val family: String = "Days One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/daysone/v7/mem9YaCnxnKRiYZOCLYV.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dekko` extends GoogleFont {
    override lazy val family: String = "Dekko"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dekko/v4/46khlb_wWjfSrttF.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Delius` extends GoogleFont {
    override lazy val family: String = "Delius"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/delius/v7/PN_xRfK0pW_9e1rtYQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Delius Swash Caps` extends GoogleFont {
    override lazy val family: String = "Delius Swash Caps"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/deliusswashcaps/v9/oY1E8fPLr7v4JWCExZpWebxVKORpXXed.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Delius Unicase` extends GoogleFont {
    override lazy val family: String = "Delius Unicase"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/deliusunicase/v10/845BNMEwEIOVT8BmgfSzIr_6mmLH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/deliusunicase/v10/845CNMEwEIOVT8BmgfSzIr_6mlp7WMr_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Della Respira` extends GoogleFont {
    override lazy val family: String = "Della Respira"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dellarespira/v5/RLp5K5v44KaueWI6iEJQBiGPRfk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Denk One` extends GoogleFont {
    override lazy val family: String = "Denk One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/denkone/v5/dg4m_pzhrqcFb2IzROtH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Devonshire` extends GoogleFont {
    override lazy val family: String = "Devonshire"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/devonshire/v6/46kqlbDwWirWr4gtBD2BX0U.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dhurjati` extends GoogleFont {
    override lazy val family: String = "Dhurjati"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dhurjati/v5/_6_8ED3gSeatXfFiFX3y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Didact Gothic` extends GoogleFont {
    override lazy val family: String = "Didact Gothic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/didactgothic/v11/ahcfv8qz1zt6hCC5G4F_P4ASpUw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Diplomata` extends GoogleFont {
    override lazy val family: String = "Diplomata"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/diplomata/v9/Cn-0JtiMXwhNwp-wKxyfYA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Diplomata SC` extends GoogleFont {
    override lazy val family: String = "Diplomata SC"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/diplomatasc/v6/buExpoi3ecvs3kidKgBJo2kf-A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Do Hyeon` extends GoogleFont {
    override lazy val family: String = "Do Hyeon"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dohyeon/v2/TwMN-I8CRRU2zM86HFE3.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dokdo` extends GoogleFont {
    override lazy val family: String = "Dokdo"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dokdo/v2/esDf315XNuCBLxLo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Domine` extends GoogleFont {
    override lazy val family: String = "Domine"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/domine/v5/L0x8DFMnlVwD4h3RvA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/domine/v5/L0x_DFMnlVwD4h3pAN-CTQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Donegal One` extends GoogleFont {
    override lazy val family: String = "Donegal One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/donegalone/v5/m8JWjfRYea-ZnFz6fsK9FZRF.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Doppio One` extends GoogleFont {
    override lazy val family: String = "Doppio One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/doppioone/v5/Gg8wN5gSaBfyBw2MqCh-lgs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dorsa` extends GoogleFont {
    override lazy val family: String = "Dorsa"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dorsa/v8/yYLn0hjd0OGwqo49.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dosis` extends GoogleFont {
    override lazy val family: String = "Dosis"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/dosis/v7/HhyXU5sn9vOmLzGnKtCC.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/dosis/v7/HhyXU5sn9vOmLzHDKdCC.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dosis/v7/HhyaU5sn9vOmLwlv.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/dosis/v7/HhyXU5sn9vOmLzGbKNCC.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/dosis/v7/HhyXU5sn9vOmLzG3L9CC.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/dosis/v7/HhyXU5sn9vOmLzHTLtCC.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/dosis/v7/HhyXU5sn9vOmLzHPLdCC.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Dr Sugiyama` extends GoogleFont {
    override lazy val family: String = "Dr Sugiyama"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/drsugiyama/v6/HTxoL2k4N3O9n5I1boGI7abR.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Duru Sans` extends GoogleFont {
    override lazy val family: String = "Duru Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/durusans/v10/xn7iYH8xwmSyTvEV_HOxTw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dynalight` extends GoogleFont {
    override lazy val family: String = "Dynalight"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dynalight/v6/1Ptsg8LOU_aOmQvTsF4ISg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `EB Garamond` extends GoogleFont {
    override lazy val family: String = "EB Garamond"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ebgaramond/v9/SlGUmQSNjdsmc35JDF1K5FRy.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ebgaramond/v9/SlGWmQSNjdsmc35JDF1K5GRwSDw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ebgaramond/v9/SlGJmQSNjdsmc35JDF1K5GyGaxwV.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ebgaramond/v9/SlGLmQSNjdsmc35JDF1K5GRwcMgWQgs.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/ebgaramond/v9/SlGJmQSNjdsmc35JDF1K5GyqbBwV.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/ebgaramond/v9/SlGLmQSNjdsmc35JDF1K5GRwcOQRQgs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ebgaramond/v9/SlGJmQSNjdsmc35JDF1K5GzObRwV.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ebgaramond/v9/SlGLmQSNjdsmc35JDF1K5GRwcIAQQgs.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/ebgaramond/v9/SlGJmQSNjdsmc35JDF1K5GzSbhwV.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/ebgaramond/v9/SlGLmQSNjdsmc35JDF1K5GRwcJwTQgs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Eagle Lake` extends GoogleFont {
    override lazy val family: String = "Eagle Lake"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/eaglelake/v5/ptRMTiqbbuNJDOiKj9wG5O4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `East Sea Dokdo` extends GoogleFont {
    override lazy val family: String = "East Sea Dokdo"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/eastseadokdo/v2/xfuo0Wn2V2_KanASqXSZp22m05_a.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Eater` extends GoogleFont {
    override lazy val family: String = "Eater"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/eater/v6/mtG04_FCK7bOvpu2.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Economica` extends GoogleFont {
    override lazy val family: String = "Economica"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/economica/v5/Qw3fZQZaHCLgIWa29ZBrMQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/economica/v5/Qw3ZZQZaHCLgIWa29ZBbM8IE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/economica/v5/Qw3aZQZaHCLgIWa29ZBTjeckCg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/economica/v5/Qw3EZQZaHCLgIWa29ZBbM_q4D3x9.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Eczar` extends GoogleFont {
    override lazy val family: String = "Eczar"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/eczar/v6/BXRlvF3Pi-DLmw0i.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/eczar/v6/BXRovF3Pi-DLmzXWL8t6.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/eczar/v6/BXRovF3Pi-DLmzX6KMt6.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/eczar/v6/BXRovF3Pi-DLmzWeKct6.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/eczar/v6/BXRovF3Pi-DLmzWCKst6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `800`)
  }
  object `El Messiri` extends GoogleFont {
    override lazy val family: String = "El Messiri"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/elmessiri/v3/K2F0fZBRmr9vQ1pHEey6Aoo.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/elmessiri/v3/K2F3fZBRmr9vQ1pHEey6On6jJyo.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/elmessiri/v3/K2F3fZBRmr9vQ1pHEey6OlKkJyo.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/elmessiri/v3/K2F3fZBRmr9vQ1pHEey6OjalJyo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`)
  }
  object `Electrolize` extends GoogleFont {
    override lazy val family: String = "Electrolize"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/electrolize/v6/cIf5Ma1dtE0zSiGSiED7AUEG.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Elsie` extends GoogleFont {
    override lazy val family: String = "Elsie"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/elsie/v7/BCanqZABrez54yYu.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/elsie/v7/BCaqqZABrez54x6q2-1I.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `900`)
  }
  object `Elsie Swash Caps` extends GoogleFont {
    override lazy val family: String = "Elsie Swash Caps"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/elsieswashcaps/v6/845DNN8xGZyVX5MVo_upKf7KnjK0feo.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/elsieswashcaps/v6/845ENN8xGZyVX5MVo_upKf7KnjK0RW74DG0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `900`)
  }
  object `Emblema One` extends GoogleFont {
    override lazy val family: String = "Emblema One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/emblemaone/v6/nKKT-GQ0F5dSY8vzG0rOEIRB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Emilys Candy` extends GoogleFont {
    override lazy val family: String = "Emilys Candy"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/emilyscandy/v5/2EbgL-1mD1Rnb0OGKudbk0y5rw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Encode Sans` extends GoogleFont {
    override lazy val family: String = "Encode Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/encodesans/v2/LDI0apOFNxEwR-Bd1O9uYPvIeeI.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/encodesans/v2/LDIrapOFNxEwR-Bd1O9uYPtkWMLO.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/encodesans/v2/LDIrapOFNxEwR-Bd1O9uYPsAW8LO.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/encodesans/v2/LDI2apOFNxEwR-Bd1O9uYMOs.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/encodesans/v2/LDIrapOFNxEwR-Bd1O9uYPtYWsLO.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/encodesans/v2/LDIrapOFNxEwR-Bd1O9uYPt0XcLO.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/encodesans/v2/LDIrapOFNxEwR-Bd1O9uYPsQXMLO.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/encodesans/v2/LDIrapOFNxEwR-Bd1O9uYPsMX8LO.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/encodesans/v2/LDIrapOFNxEwR-Bd1O9uYPsoXsLO.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Encode Sans Condensed` extends GoogleFont {
    override lazy val family: String = "Encode Sans Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/encodesanscondensed/v2/j8_76_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-5a-J.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/encodesanscondensed/v2/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-SY6pBw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/encodesanscondensed/v2/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-LY2pBw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/encodesanscondensed/v2/j8_16_LD37rqfuwxyIuaZhE6cRXOLtm2gfTGgQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/encodesanscondensed/v2/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-dYypBw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/encodesanscondensed/v2/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-WYupBw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/encodesanscondensed/v2/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-PYqpBw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/encodesanscondensed/v2/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-IYmpBw.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/encodesanscondensed/v2/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-BYipBw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Encode Sans Expanded` extends GoogleFont {
    override lazy val family: String = "Encode Sans Expanded"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/encodesansexpanded/v2/c4mx1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpJGKQM.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/encodesansexpanded/v2/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpLqCCNI.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/encodesansexpanded/v2/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpKOCyNI.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/encodesansexpanded/v2/c4m_1mF4GcnstG_Jh1QH6ac4hNLeNyeYUqoi.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/encodesansexpanded/v2/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpLWCiNI.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/encodesansexpanded/v2/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpL6DSNI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/encodesansexpanded/v2/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpKeDCNI.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/encodesansexpanded/v2/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpKCDyNI.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/encodesansexpanded/v2/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpKmDiNI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Encode Sans Semi Condensed` extends GoogleFont {
    override lazy val family: String = "Encode Sans Semi Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/encodesanssemicondensed/v2/3qT6oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1T19ME.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/encodesanssemicondensed/v2/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1RZ1eFH.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/encodesanssemicondensed/v2/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1Q91uFH.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/encodesanssemicondensed/v2/3qT4oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG2yR.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/encodesanssemicondensed/v2/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1Rl1-FH.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/encodesanssemicondensed/v2/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1RJ0OFH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/encodesanssemicondensed/v2/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1Qt0eFH.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/encodesanssemicondensed/v2/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1Qx0uFH.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/encodesanssemicondensed/v2/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1QV0-FH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Encode Sans Semi Expanded` extends GoogleFont {
    override lazy val family: String = "Encode Sans Semi Expanded"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/encodesanssemiexpanded/v2/ke8xOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TM-41Kw.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/encodesanssemiexpanded/v2/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TM0IUCyA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/encodesanssemiexpanded/v2/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TMyYXCyA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/encodesanssemiexpanded/v2/ke83OhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TC4o.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/encodesanssemiexpanded/v2/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TM34WCyA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/encodesanssemiexpanded/v2/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TM1IRCyA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/encodesanssemiexpanded/v2/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TMzYQCyA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/encodesanssemiexpanded/v2/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TMyoTCyA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/encodesanssemiexpanded/v2/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TMw4SCyA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Engagement` extends GoogleFont {
    override lazy val family: String = "Engagement"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/engagement/v6/x3dlckLDZbqa7RUs9MFVXNo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Englebert` extends GoogleFont {
    override lazy val family: String = "Englebert"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/englebert/v5/xn7iYH8w2XGrC8AR4HSxTw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Enriqueta` extends GoogleFont {
    override lazy val family: String = "Enriqueta"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/enriqueta/v6/goksH6L7AUFrRvV44HVTSw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/enriqueta/v6/gokpH6L7AUFrRvV44HVr92-HmA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Erica One` extends GoogleFont {
    override lazy val family: String = "Erica One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ericaone/v8/WBLnrEXccV9VGrOKmGD1Ww.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Esteban` extends GoogleFont {
    override lazy val family: String = "Esteban"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/esteban/v5/r05bGLZE-bdGdN-GdOs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Euphoria Script` extends GoogleFont {
    override lazy val family: String = "Euphoria Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/euphoriascript/v5/mFTpWb0X2bLb_cx6To2B8GpKoD5akw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ewert` extends GoogleFont {
    override lazy val family: String = "Ewert"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ewert/v5/va9I4kzO2tFODYBv.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Exo` extends GoogleFont {
    override lazy val family: String = "Exo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/exo/v6/4UaMrEtFpBIaEH6m.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/exo/v6/4UaCrEtFpBISdkbC0DI.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/exo/v6/4UaDrEtFpBIavF-G8A.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/exo/v6/4UaBrEtFpBISdkZu8RLm.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/exo/v6/4UaDrEtFpBIa2FyG8A.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/exo/v6/4UaBrEtFpBISdkYK8hLm.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/exo/v6/4UaOrEtFpBIidA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/exo/v6/4UaMrEtFpBISdn6m.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/exo/v6/4UaDrEtFpBIagF2G8A.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/exo/v6/4UaBrEtFpBISdkZS8xLm.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/exo/v6/4UaDrEtFpBIarFqG8A.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/exo/v6/4UaBrEtFpBISdkZ-9BLm.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/exo/v6/4UaDrEtFpBIayFuG8A.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/exo/v6/4UaBrEtFpBISdkYa9RLm.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/exo/v6/4UaDrEtFpBIa1FiG8A.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/exo/v6/4UaBrEtFpBISdkYG9hLm.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/exo/v6/4UaDrEtFpBIa8FmG8A.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/exo/v6/4UaBrEtFpBISdkYi9xLm.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Exo 2` extends GoogleFont {
    override lazy val family: String = "Exo 2"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/exo2/v4/7cHov4okm5zmbt5LK-s.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/exo2/v4/7cHqv4okm5zmbtYtE48c4A.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/exo2/v4/7cHrv4okm5zmbt7nCss8.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/exo2/v4/7cHtv4okm5zmbtYtEyM9wHo.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/exo2/v4/7cHrv4okm5zmbt6DCcs8.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/exo2/v4/7cHtv4okm5zmbtYtE0c-wHo.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/exo2/v4/7cHmv4okm5zmbuYv.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/exo2/v4/7cHov4okm5zmbtYtK-s.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/exo2/v4/7cHrv4okm5zmbt7bCMs8.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/exo2/v4/7cHtv4okm5zmbtYtEx8_wHo.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/exo2/v4/7cHrv4okm5zmbt73D8s8.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/exo2/v4/7cHtv4okm5zmbtYtEzM4wHo.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/exo2/v4/7cHrv4okm5zmbt6TDss8.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/exo2/v4/7cHtv4okm5zmbtYtE1c5wHo.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/exo2/v4/7cHrv4okm5zmbt6PDcs8.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/exo2/v4/7cHtv4okm5zmbtYtE0s6wHo.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/exo2/v4/7cHrv4okm5zmbt6rDMs8.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/exo2/v4/7cHtv4okm5zmbtYtE287wHo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Expletus Sans` extends GoogleFont {
    override lazy val family: String = "Expletus Sans"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/expletussans/v10/RLp5K5v5_bqufTYdnhFzDj2dRfk.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/expletussans/v10/RLpnK5v5_bqufTYdnhFzDj2ddfsYvw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/expletussans/v10/RLpkK5v5_bqufTYdnhFzDj2dfQ07n6k.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/expletussans/v10/RLpiK5v5_bqufTYdnhFzDj2ddfsgS6oPVA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/expletussans/v10/RLpkK5v5_bqufTYdnhFzDj2dfSE8n6k.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/expletussans/v10/RLpiK5v5_bqufTYdnhFzDj2ddfsgZ60PVA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/expletussans/v10/RLpkK5v5_bqufTYdnhFzDj2dfUU9n6k.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/expletussans/v10/RLpiK5v5_bqufTYdnhFzDj2ddfsgA6wPVA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Fanwood Text` extends GoogleFont {
    override lazy val family: String = "Fanwood Text"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fanwoodtext/v7/3XFtErwl05Ad_vSCF6Fq7xXGRQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/fanwoodtext/v7/3XFzErwl05Ad_vSCF6Fq7xX2R9zc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Farsan` extends GoogleFont {
    override lazy val family: String = "Farsan"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `gujarati`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/farsan/v3/VEMwRoJ0vY_zsyz62g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fascinate` extends GoogleFont {
    override lazy val family: String = "Fascinate"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fascinate/v6/z7NWdRrufC8XJK0IIEli1A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fascinate Inline` extends GoogleFont {
    override lazy val family: String = "Fascinate Inline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fascinateinline/v7/jVyR7mzzB3zc-jp6QCAu60poNqIy1g0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Faster One` extends GoogleFont {
    override lazy val family: String = "Faster One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fasterone/v8/H4ciBXCHmdfClFb-vWhfyLs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fasthand` extends GoogleFont {
    override lazy val family: String = "Fasthand"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fasthand/v8/0yb9GDohyKTYn_ZEESku.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fauna One` extends GoogleFont {
    override lazy val family: String = "Fauna One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/faunaone/v5/wlpzgwTPBVpjpCuwkuEx2Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Faustina` extends GoogleFont {
    override lazy val family: String = "Faustina"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/faustina/v2/XLYlIZPxYpJfTbZAFW-4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/faustina/v2/XLYjIZPxYpJfTbZAFV-6Hck.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/faustina/v2/XLYiIZPxYpJfTbZAFVdMPulC.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/faustina/v2/XLYgIZPxYpJfTbZAFV-6JT1Bhnc.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/faustina/v2/XLYiIZPxYpJfTbZAFVdgOelC.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/faustina/v2/XLYgIZPxYpJfTbZAFV-6JRFGhnc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/faustina/v2/XLYiIZPxYpJfTbZAFVcEOOlC.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/faustina/v2/XLYgIZPxYpJfTbZAFV-6JXVHhnc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Federant` extends GoogleFont {
    override lazy val family: String = "Federant"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/federant/v9/2sDdZGNfip_eirT0_U0j.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Federo` extends GoogleFont {
    override lazy val family: String = "Federo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/federo/v9/iJWFBX-cbD_ETsbmjQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Felipa` extends GoogleFont {
    override lazy val family: String = "Felipa"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/felipa/v5/FwZa7-owz1Eu4F_wSA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fenix` extends GoogleFont {
    override lazy val family: String = "Fenix"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fenix/v5/XoHo2YL_S7-g5ost.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Finger Paint` extends GoogleFont {
    override lazy val family: String = "Finger Paint"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fingerpaint/v7/0QInMXVJ-o-oRn_7dron8YWO8w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fira Mono` extends GoogleFont {
    override lazy val family: String = "Fira Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/firamono/v6/N0bX2SlFPv1weGeLZDtQIQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/firamono/v6/N0bS2SlFPv1weGeLZDto1d33mQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/firamono/v6/N0bS2SlFPv1weGeLZDtondv3mQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `700`)
  }
  object `Fira Sans` extends GoogleFont {
    override lazy val family: String = "Fira Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/firasans/v8/va9C4kDNxMZdWfMOD5Vn9IjO.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/firasans/v8/va9A4kDNxMZdWfMOD5VvkrCqYTc.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/firasans/v8/va9B4kDNxMZdWfMOD5VnWKnuQQ.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/firasans/v8/va9f4kDNxMZdWfMOD5VvkrAGQBf_.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/firasans/v8/va9B4kDNxMZdWfMOD5VnPKruQQ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/firasans/v8/va9f4kDNxMZdWfMOD5VvkrBiQxf_.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/firasans/v8/va9E4kDNxMZdWfMOD5VfkA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/firasans/v8/va9C4kDNxMZdWfMOD5VvkojO.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/firasans/v8/va9B4kDNxMZdWfMOD5VnZKvuQQ.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/firasans/v8/va9f4kDNxMZdWfMOD5VvkrA6Qhf_.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/firasans/v8/va9B4kDNxMZdWfMOD5VnSKzuQQ.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/firasans/v8/va9f4kDNxMZdWfMOD5VvkrAWRRf_.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/firasans/v8/va9B4kDNxMZdWfMOD5VnLK3uQQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/firasans/v8/va9f4kDNxMZdWfMOD5VvkrByRBf_.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/firasans/v8/va9B4kDNxMZdWfMOD5VnMK7uQQ.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/firasans/v8/va9f4kDNxMZdWfMOD5VvkrBuRxf_.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/firasans/v8/va9B4kDNxMZdWfMOD5VnFK_uQQ.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/firasans/v8/va9f4kDNxMZdWfMOD5VvkrBKRhf_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Fira Sans Condensed` extends GoogleFont {
    override lazy val family: String = "Fira Sans Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/firasanscondensed/v2/wEOjEADFm8hSaQTFG18FErVhsC9x-tarWZXtqA.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/firasanscondensed/v2/wEOtEADFm8hSaQTFG18FErVhsC9x-tarUfPVzONU.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/firasanscondensed/v2/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWTnMiMM.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/firasanscondensed/v2/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVYMJ0dw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/firasanscondensed/v2/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWV3PiMM.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/firasanscondensed/v2/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVBMF0dw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/firasanscondensed/v2/wEOhEADFm8hSaQTFG18FErVhsC9x-tarYfE.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/firasanscondensed/v2/wEOjEADFm8hSaQTFG18FErVhsC9x-tarUfPtqA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/firasanscondensed/v2/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWQXOiMM.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/firasanscondensed/v2/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVXMB0dw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/firasanscondensed/v2/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWSnJiMM.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/firasanscondensed/v2/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVcMd0dw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/firasanscondensed/v2/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWU3IiMM.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/firasanscondensed/v2/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVFMZ0dw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/firasanscondensed/v2/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWVHLiMM.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/firasanscondensed/v2/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVCMV0dw.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/firasanscondensed/v2/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWXXKiMM.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/firasanscondensed/v2/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVLMR0dw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Fira Sans Extra Condensed` extends GoogleFont {
    override lazy val family: String = "Fira Sans Extra Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/firasansextracondensed/v2/NaPMcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3Zyuvw.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/firasansextracondensed/v2/NaPOcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqW21-e.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/firasansextracondensed/v2/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3TCPn38.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/firasansextracondensed/v2/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWd36-pA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/firasansextracondensed/v2/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3VSMn38.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/firasansextracondensed/v2/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWE32-pA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/firasansextracondensed/v2/NaPKcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda5fg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/firasansextracondensed/v2/NaPMcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fquvw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/firasansextracondensed/v2/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3QyNn38.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/firasansextracondensed/v2/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWS3y-pA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/firasansextracondensed/v2/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3SCKn38.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/firasansextracondensed/v2/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWZ3u-pA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/firasansextracondensed/v2/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3USLn38.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/firasansextracondensed/v2/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWA3q-pA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/firasansextracondensed/v2/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3ViIn38.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/firasansextracondensed/v2/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWH3m-pA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/firasansextracondensed/v2/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3XyJn38.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/firasansextracondensed/v2/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWO3i-pA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Fjalla One` extends GoogleFont {
    override lazy val family: String = "Fjalla One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fjallaone/v5/Yq6R-LCAWCX3-6Ky7FAFnOY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fjord One` extends GoogleFont {
    override lazy val family: String = "Fjord One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fjordone/v6/zOL-4pbEnKBY_9S1jNKr6Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Flamenco` extends GoogleFont {
    override lazy val family: String = "Flamenco"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/flamenco/v8/neIPzCehqYguo67ssZ0qNIky.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/flamenco/v8/neIIzCehqYguo67ssaWG.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`)
  }
  object `Flavors` extends GoogleFont {
    override lazy val family: String = "Flavors"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/flavors/v6/FBV2dDrhxqmveJTpbkw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fondamento` extends GoogleFont {
    override lazy val family: String = "Fondamento"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fondamento/v7/4UaHrEJGsxNmFTPDnkaJx60.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/fondamento/v7/4UaFrEJGsxNmFTPDnkaJ96_p4g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Fontdiner Swanky` extends GoogleFont {
    override lazy val family: String = "Fontdiner Swanky"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fontdinerswanky/v8/ijwOs4XgRNsiaI5-hcVb4hQgMvCD4uE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Forum` extends GoogleFont {
    override lazy val family: String = "Forum"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/forum/v8/6aey4Ky-Vb8Ew_IW.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Francois One` extends GoogleFont {
    override lazy val family: String = "Francois One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/francoisone/v11/_Xmr-H4zszafZw3A-KPSZutNxg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Frank Ruhl Libre` extends GoogleFont {
    override lazy val family: String = "Frank Ruhl Libre"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `hebrew`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/frankruhllibre/v3/j8_36_fAw7jrcalD7oKYNX0QfAnPUxvHxJA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/frankruhllibre/v3/j8_w6_fAw7jrcalD7oKYNX0QfAnPa7c.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/frankruhllibre/v3/j8_36_fAw7jrcalD7oKYNX0QfAnPU0PGxJA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/frankruhllibre/v3/j8_36_fAw7jrcalD7oKYNX0QfAnPUwvAxJA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/frankruhllibre/v3/j8_36_fAw7jrcalD7oKYNX0QfAnPUzPCxJA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `700`, `900`)
  }
  object `Freckle Face` extends GoogleFont {
    override lazy val family: String = "Freckle Face"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/freckleface/v5/AMOWz4SXrmKHCvXTohxY-YI0Uw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fredericka the Great` extends GoogleFont {
    override lazy val family: String = "Fredericka the Great"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/frederickathegreat/v6/9Bt33CxNwt7aOctW2xjbCstzwVKsIBVV-9Sk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fredoka One` extends GoogleFont {
    override lazy val family: String = "Fredoka One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fredokaone/v5/k3kUo8kEI-tA1RRcTZGmTmHB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Freehand` extends GoogleFont {
    override lazy val family: String = "Freehand"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/freehand/v9/cIf-Ma5eqk01VjKTgAmB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fresca` extends GoogleFont {
    override lazy val family: String = "Fresca"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fresca/v6/6ae94K--SKgCzbM2Gg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Frijole` extends GoogleFont {
    override lazy val family: String = "Frijole"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/frijole/v6/uU9PCBUR8oakM2BQ7xM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fruktur` extends GoogleFont {
    override lazy val family: String = "Fruktur"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fruktur/v10/SZc53FHsOru5QYsMfz0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fugaz One` extends GoogleFont {
    override lazy val family: String = "Fugaz One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fugazone/v7/rax_HiWKp9EAITukFslMBA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `GFS Didot` extends GoogleFont {
    override lazy val family: String = "GFS Didot"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gfsdidot/v7/Jqzh5TybZ9vZMWFssvwiFw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `GFS Neohellenic` extends GoogleFont {
    override lazy val family: String = "GFS Neohellenic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gfsneohellenic/v8/8QIRdiDOrfiq0b7R8O1Iw9WLcY5TLQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/gfsneohellenic/v8/8QITdiDOrfiq0b7R8O1Iw9WLcY5jL6JL.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gfsneohellenic/v8/8QIUdiDOrfiq0b7R8O1Iw9WLcY5rkYdr6w.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/gfsneohellenic/v8/8QIWdiDOrfiq0b7R8O1Iw9WLcY5jL5r37oQb.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Gabriela` extends GoogleFont {
    override lazy val family: String = "Gabriela"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gabriela/v6/qkBWXvsO6sreR8E-b_m-.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gaegu` extends GoogleFont {
    override lazy val family: String = "Gaegu"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/gaegu/v2/TuGSUVB6Up9NU57nifw7.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gaegu/v2/TuGfUVB6Up9NU6ZL.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gaegu/v2/TuGSUVB6Up9NU573jvw7.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Gafata` extends GoogleFont {
    override lazy val family: String = "Gafata"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gafata/v6/XRXV3I6Cn0VJKon4Mg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Galada` extends GoogleFont {
    override lazy val family: String = "Galada"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `bengali`: GoogleFontSubset = new GoogleFontSubset("bengali")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `bengali`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/galada/v3/H4cmBXyGmcjXlUX-8g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Galdeano` extends GoogleFont {
    override lazy val family: String = "Galdeano"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/galdeano/v7/uU9MCBoQ4YOqOW1boDPx.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Galindo` extends GoogleFont {
    override lazy val family: String = "Galindo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/galindo/v5/HI_KiYMeLqVKqwyuQ5E.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gamja Flower` extends GoogleFont {
    override lazy val family: String = "Gamja Flower"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gamjaflower/v2/6NUR8FiKJg-Pa0rM6uN40Z4kyQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gentium Basic` extends GoogleFont {
    override lazy val family: String = "Gentium Basic"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gentiumbasic/v9/Wnz9HAw9aB_JD2VGQVR80We3HAo.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/gentiumbasic/v9/WnzjHAw9aB_JD2VGQVR80We3LAiJjQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gentiumbasic/v9/WnzgHAw9aB_JD2VGQVR80We3JLasrTo.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/gentiumbasic/v9/WnzmHAw9aB_JD2VGQVR80We3LAixMT8eaA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Gentium Book Basic` extends GoogleFont {
    override lazy val family: String = "Gentium Book Basic"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gentiumbookbasic/v8/pe0zMJCbPYBVokB1LHA9bbyaQb8ZGjcIVw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/gentiumbookbasic/v8/pe0xMJCbPYBVokB1LHA9bbyaQb8ZGjc4VbF_.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gentiumbookbasic/v8/pe0wMJCbPYBVokB1LHA9bbyaQb8ZGjcw65Rfyw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/gentiumbookbasic/v8/pe0-MJCbPYBVokB1LHA9bbyaQb8ZGjc4VYnDzofc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Geo` extends GoogleFont {
    override lazy val family: String = "Geo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/geo/v9/CSRz4zRZlufVLw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/geo/v9/CSRx4zRZluflLXpi.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Geostar` extends GoogleFont {
    override lazy val family: String = "Geostar"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/geostar/v7/sykz-yx4n701VLOftSo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Geostar Fill` extends GoogleFont {
    override lazy val family: String = "Geostar Fill"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/geostarfill/v7/AMOWz4SWuWiXFfjEohxQ9os0Uw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Germania One` extends GoogleFont {
    override lazy val family: String = "Germania One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/germaniaone/v5/Fh4yPjrqIyv2ucM2qzBjeS3ezA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gidugu` extends GoogleFont {
    override lazy val family: String = "Gidugu"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gidugu/v4/L0x8DFMkk1Uf6w3RvA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gilda Display` extends GoogleFont {
    override lazy val family: String = "Gilda Display"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gildadisplay/v5/t5tmIRoYMoaYG0WEOh7HwMeR7Tk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Give You Glory` extends GoogleFont {
    override lazy val family: String = "Give You Glory"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/giveyouglory/v7/8QIQdiHOgt3vv4LR7ahjw9-XYc1z.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Glass Antiqua` extends GoogleFont {
    override lazy val family: String = "Glass Antiqua"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/glassantiqua/v5/xfu30Wr0Wn3NOQM2piC0uXOjnL8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Glegoo` extends GoogleFont {
    override lazy val family: String = "Glegoo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/glegoo/v6/_Xmt-HQyrTKWaw2Jiw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/glegoo/v6/_Xmu-HQyrTKWaw2xN4a9CA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Gloria Hallelujah` extends GoogleFont {
    override lazy val family: String = "Gloria Hallelujah"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gloriahallelujah/v9/LYjYdHv3kUk9BMV96EIswT9DIbW-MLSy.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Goblin One` extends GoogleFont {
    override lazy val family: String = "Goblin One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/goblinone/v7/CSR64z1ZnOqZRjRCBVY_TOc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gochi Hand` extends GoogleFont {
    override lazy val family: String = "Gochi Hand"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gochihand/v8/hES06XlsOjtJsgCkx1PkTo4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gorditas` extends GoogleFont {
    override lazy val family: String = "Gorditas"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gorditas/v5/ll8_K2aTVD26DsPEtQDo.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gorditas/v5/ll84K2aTVD26DsPEtThUIooI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Gothic A1` extends GoogleFont {
    override lazy val family: String = "Gothic A1"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/gothica1/v2/CSR74z5ZnPydRjlCCwlCCMcq.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/gothica1/v2/CSR44z5ZnPydRjlCCwlCpOYKSA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/gothica1/v2/CSR44z5ZnPydRjlCCwlCwOUKSA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gothica1/v2/CSR94z5ZnPydRjlCCwl6bA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/gothica1/v2/CSR44z5ZnPydRjlCCwlCmOQKSA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/gothica1/v2/CSR44z5ZnPydRjlCCwlCtOMKSA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gothica1/v2/CSR44z5ZnPydRjlCCwlC0OIKSA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/gothica1/v2/CSR44z5ZnPydRjlCCwlCzOEKSA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/gothica1/v2/CSR44z5ZnPydRjlCCwlC6OAKSA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Goudy Bookletter 1911` extends GoogleFont {
    override lazy val family: String = "Goudy Bookletter 1911"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/goudybookletter1911/v7/sykt-z54laciWfKv-kX8krex0jDiD2HbY6I5tQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Graduate` extends GoogleFont {
    override lazy val family: String = "Graduate"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/graduate/v5/C8cg4cs3o2n15t_2YxgR.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Grand Hotel` extends GoogleFont {
    override lazy val family: String = "Grand Hotel"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/grandhotel/v5/7Au7p_IgjDKdCRWuR1azpmQN.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gravitas One` extends GoogleFont {
    override lazy val family: String = "Gravitas One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gravitasone/v7/5h1diZ4hJ3cblKy3LWakKQmaDQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Great Vibes` extends GoogleFont {
    override lazy val family: String = "Great Vibes"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/greatvibes/v5/RWmMoKWR9v4ksMfaWd_JN-XC.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Griffy` extends GoogleFont {
    override lazy val family: String = "Griffy"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/griffy/v5/FwZa7-ox2FQh9kfwSA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gruppo` extends GoogleFont {
    override lazy val family: String = "Gruppo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gruppo/v8/WwkfxPmzE06v_ZWFWQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gudea` extends GoogleFont {
    override lazy val family: String = "Gudea"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gudea/v5/neIFzCqgsI0mp-CP.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/gudea/v5/neILzCqgsI0mp9CN_oU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gudea/v5/neIIzCqgsI0mp9gz26WG.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Gugi` extends GoogleFont {
    override lazy val family: String = "Gugi"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gugi/v2/A2BVn5dXywshVA4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gurajada` extends GoogleFont {
    override lazy val family: String = "Gurajada"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gurajada/v5/FwZY7-Qx308m-l-0Kd6A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Habibi` extends GoogleFont {
    override lazy val family: String = "Habibi"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/habibi/v6/CSR-4zFWkuqcTTNCSg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Halant` extends GoogleFont {
    override lazy val family: String = "Halant"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/halant/v4/u-490qaujRI2Pbsvc_pCmw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/halant/v4/u-4-0qaujRI2PbsX3w.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/halant/v4/u-490qaujRI2PbsvK_tCmw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/halant/v4/u-490qaujRI2PbsvB_xCmw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/halant/v4/u-490qaujRI2PbsvY_1Cmw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Hammersmith One` extends GoogleFont {
    override lazy val family: String = "Hammersmith One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hammersmithone/v8/qWcyB624q4L_C4jGQ9IK0O_dFlnbsg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Hanalei` extends GoogleFont {
    override lazy val family: String = "Hanalei"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hanalei/v7/E21n_dD8iufIjBRHXzg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Hanalei Fill` extends GoogleFont {
    override lazy val family: String = "Hanalei Fill"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hanaleifill/v6/fC1mPYtObGbfyQznIaQzPQiMVw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Handlee` extends GoogleFont {
    override lazy val family: String = "Handlee"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/handlee/v6/-F6xfjBsISg9aMakDmo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Hanuman` extends GoogleFont {
    override lazy val family: String = "Hanuman"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hanuman/v11/VuJxdNvD15HhpJJBeKY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hanuman/v11/VuJ0dNvD15HhpJJBQBr4HIk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Happy Monkey` extends GoogleFont {
    override lazy val family: String = "Happy Monkey"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/happymonkey/v6/K2F2fZZcl-9SXwl5F_C4R_OABw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Harmattan` extends GoogleFont {
    override lazy val family: String = "Harmattan"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/harmattan/v3/goksH6L2DkFvVvRp9XpTSw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Headland One` extends GoogleFont {
    override lazy val family: String = "Headland One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/headlandone/v5/yYLu0hHR2vKnp89Tk1TCq3Tx0A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Heebo` extends GoogleFont {
    override lazy val family: String = "Heebo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `hebrew`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/heebo/v3/NGS0v5_NC0k9P9mVTbQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/heebo/v3/NGS3v5_NC0k9P9ldb5RL.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/heebo/v3/NGS6v5_NC0k9P-Hx.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/heebo/v3/NGS3v5_NC0k9P9kFbpRL.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/heebo/v3/NGS3v5_NC0k9P9lNaJRL.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/heebo/v3/NGS3v5_NC0k9P9lRa5RL.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/heebo/v3/NGS3v5_NC0k9P9l1apRL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `800`, `900`)
  }
  object `Henny Penny` extends GoogleFont {
    override lazy val family: String = "Henny Penny"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hennypenny/v5/wXKvE3UZookzsxz_kjGSfMQq.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Herr Von Muellerhoff` extends GoogleFont {
    override lazy val family: String = "Herr Von Muellerhoff"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/herrvonmuellerhoff/v7/WBL6rFjRZkREW8WqmCWYLgCkQKXb4CAft3c6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Hi Melody` extends GoogleFont {
    override lazy val family: String = "Hi Melody"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/himelody/v2/46ktlbP8Vnz0pJcqCTbEfw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Hind` extends GoogleFont {
    override lazy val family: String = "Hind"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/hind/v8/5aU19_a8oxmIfMJaIRs.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hind/v8/5aU69_a8oxmIRG4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/hind/v8/5aU19_a8oxmIfJpbIRs.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/hind/v8/5aU19_a8oxmIfLZcIRs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hind/v8/5aU19_a8oxmIfNJdIRs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Hind Guntur` extends GoogleFont {
    override lazy val family: String = "Hind Guntur"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/hindguntur/v3/wXKyE3UZrok56nvamSuJd_yGn1cz.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hindguntur/v3/wXKvE3UZrok56nvamSuJd8Qq.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/hindguntur/v3/wXKyE3UZrok56nvamSuJd_zenlcz.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/hindguntur/v3/wXKyE3UZrok56nvamSuJd_zymVcz.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hindguntur/v3/wXKyE3UZrok56nvamSuJd_yWmFcz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Hind Madurai` extends GoogleFont {
    override lazy val family: String = "Hind Madurai"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`tamil`, `latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/hindmadurai/v3/f0Xu0e2p98ZvDXdZQIOcpqjfXaUneQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hindmadurai/v3/f0Xx0e2p98ZvDXdZQIOcpqjn8Q.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/hindmadurai/v3/f0Xu0e2p98ZvDXdZQIOcpqjfBaQneQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/hindmadurai/v3/f0Xu0e2p98ZvDXdZQIOcpqjfKaMneQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hindmadurai/v3/f0Xu0e2p98ZvDXdZQIOcpqjfTaIneQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Hind Siliguri` extends GoogleFont {
    override lazy val family: String = "Hind Siliguri"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `bengali`: GoogleFontSubset = new GoogleFontSubset("bengali")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `bengali`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/hindsiliguri/v4/ijwOs5juQtsyLLR5jN4cxBEoRDf44uE.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hindsiliguri/v4/ijwTs5juQtsyLLR5jN4cxBEofJs.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/hindsiliguri/v4/ijwOs5juQtsyLLR5jN4cxBEoRG_54uE.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/hindsiliguri/v4/ijwOs5juQtsyLLR5jN4cxBEoREP-4uE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hindsiliguri/v4/ijwOs5juQtsyLLR5jN4cxBEoRCf_4uE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Hind Vadodara` extends GoogleFont {
    override lazy val family: String = "Hind Vadodara"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `gujarati`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/hindvadodara/v4/neIQzCKvrIcn5pbuuuriV9tTSDn3iXM.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hindvadodara/v4/neINzCKvrIcn5pbuuuriV9tTcJU.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/hindvadodara/v4/neIQzCKvrIcn5pbuuuriV9tTSGH2iXM.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/hindvadodara/v4/neIQzCKvrIcn5pbuuuriV9tTSE3xiXM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hindvadodara/v4/neIQzCKvrIcn5pbuuuriV9tTSCnwiXM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Holtwood One SC` extends GoogleFont {
    override lazy val family: String = "Holtwood One SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/holtwoodonesc/v8/yYLx0hLR0P-3vMFSk1TCq3Txg5B3cQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Homemade Apple` extends GoogleFont {
    override lazy val family: String = "Homemade Apple"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/homemadeapple/v8/Qw3EZQFXECDrI2q789EKQZJob3x9.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Homenaje` extends GoogleFont {
    override lazy val family: String = "Homenaje"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/homenaje/v7/FwZY7-Q-xVAi_l-6Ld6A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `IBM Plex Mono` extends GoogleFont {
    override lazy val family: String = "IBM Plex Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/ibmplexmono/v2/-F6pfjptAgt5VM-kVkqdyU8n3kwq0g.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/ibmplexmono/v2/-F6rfjptAgt5VM-kVkqdyU8n1ioStndl.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/ibmplexmono/v2/-F6qfjptAgt5VM-kVkqdyU8n3uAL8lc.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/ibmplexmono/v2/-F6sfjptAgt5VM-kVkqdyU8n1ioSGlZFhw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/ibmplexmono/v2/-F6qfjptAgt5VM-kVkqdyU8n3oQI8lc.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/ibmplexmono/v2/-F6sfjptAgt5VM-kVkqdyU8n1ioSflVFhw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ibmplexmono/v2/-F63fjptAgt5VM-kVkqdyU8n5ig.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ibmplexmono/v2/-F6pfjptAgt5VM-kVkqdyU8n1ioq0g.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ibmplexmono/v2/-F6qfjptAgt5VM-kVkqdyU8n3twJ8lc.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ibmplexmono/v2/-F6sfjptAgt5VM-kVkqdyU8n1ioSJlRFhw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/ibmplexmono/v2/-F6qfjptAgt5VM-kVkqdyU8n3vAO8lc.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/ibmplexmono/v2/-F6sfjptAgt5VM-kVkqdyU8n1ioSClNFhw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ibmplexmono/v2/-F6qfjptAgt5VM-kVkqdyU8n3pQP8lc.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ibmplexmono/v2/-F6sfjptAgt5VM-kVkqdyU8n1ioSblJFhw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `IBM Plex Sans` extends GoogleFont {
    override lazy val family: String = "IBM Plex Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/ibmplexsans/v2/zYX-KVElMYYaJe8bpLHnCwDKjbLeEA.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/ibmplexsans/v2/zYX8KVElMYYaJe8bpLHnCwDKhdTmdKZM.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/ibmplexsans/v2/zYX9KVElMYYaJe8bpLHnCwDKjR7_MIY.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/ibmplexsans/v2/zYX7KVElMYYaJe8bpLHnCwDKhdTm2IdscQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/ibmplexsans/v2/zYX9KVElMYYaJe8bpLHnCwDKjXr8MIY.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/ibmplexsans/v2/zYX7KVElMYYaJe8bpLHnCwDKhdTmvIRscQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ibmplexsans/v2/zYXgKVElMYYaJe8bpLHnCwDKtdY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ibmplexsans/v2/zYX-KVElMYYaJe8bpLHnCwDKhdTeEA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ibmplexsans/v2/zYX9KVElMYYaJe8bpLHnCwDKjSL9MIY.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ibmplexsans/v2/zYX7KVElMYYaJe8bpLHnCwDKhdTm5IVscQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/ibmplexsans/v2/zYX9KVElMYYaJe8bpLHnCwDKjQ76MIY.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/ibmplexsans/v2/zYX7KVElMYYaJe8bpLHnCwDKhdTmyIJscQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ibmplexsans/v2/zYX9KVElMYYaJe8bpLHnCwDKjWr7MIY.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ibmplexsans/v2/zYX7KVElMYYaJe8bpLHnCwDKhdTmrINscQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `IBM Plex Sans Condensed` extends GoogleFont {
    override lazy val family: String = "IBM Plex Sans Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/ibmplexsanscondensed/v2/Gg8nN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY7KyKs.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/ibmplexsanscondensed/v2/Gg8hN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8M_LhQ.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/ibmplexsanscondensed/v2/Gg8gN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY5m6Yvr.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/ibmplexsanscondensed/v2/Gg8iN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8GPqpYM.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/ibmplexsanscondensed/v2/Gg8gN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY4C6ovr.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/ibmplexsanscondensed/v2/Gg8iN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8AfppYM.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ibmplexsanscondensed/v2/Gg8lN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHbau.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ibmplexsanscondensed/v2/Gg8nN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYasyKs.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ibmplexsanscondensed/v2/Gg8gN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY5a64vr.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ibmplexsanscondensed/v2/Gg8iN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8F_opYM.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/ibmplexsanscondensed/v2/Gg8gN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY527Ivr.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/ibmplexsanscondensed/v2/Gg8iN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8HPvpYM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ibmplexsanscondensed/v2/Gg8gN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY4S7Yvr.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ibmplexsanscondensed/v2/Gg8iN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8BfupYM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `IBM Plex Serif` extends GoogleFont {
    override lazy val family: String = "IBM Plex Serif"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/ibmplexserif/v2/jizBREVNn1dOx-zrZ2X3pZvkTi182zI.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/ibmplexserif/v2/jizHREVNn1dOx-zrZ2X3pZvkTiUa41YTiw.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/ibmplexserif/v2/jizAREVNn1dOx-zrZ2X3pZvkTi3Q-hIz.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/ibmplexserif/v2/jizGREVNn1dOx-zrZ2X3pZvkTiUa4_oyq14.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/ibmplexserif/v2/jizAREVNn1dOx-zrZ2X3pZvkTi20-RIz.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/ibmplexserif/v2/jizGREVNn1dOx-zrZ2X3pZvkTiUa454xq14.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ibmplexserif/v2/jizDREVNn1dOx-zrZ2X3pZvkThUY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ibmplexserif/v2/jizBREVNn1dOx-zrZ2X3pZvkTiUa2zI.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ibmplexserif/v2/jizAREVNn1dOx-zrZ2X3pZvkTi3s-BIz.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ibmplexserif/v2/jizGREVNn1dOx-zrZ2X3pZvkTiUa48Ywq14.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/ibmplexserif/v2/jizAREVNn1dOx-zrZ2X3pZvkTi3A_xIz.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/ibmplexserif/v2/jizGREVNn1dOx-zrZ2X3pZvkTiUa4-o3q14.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ibmplexserif/v2/jizGREVNn1dOx-zrZ2X3pZvkTiUa4442q14.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700italic`)
  }
  object `IM Fell DW Pica` extends GoogleFont {
    override lazy val family: String = "IM Fell DW Pica"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfelldwpica/v7/2sDGZGRQotv9nbn2qSl0TxXVYNw9ZA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/imfelldwpica/v7/2sDEZGRQotv9nbn2qSl0TxXVYNwNZgnQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `IM Fell DW Pica SC` extends GoogleFont {
    override lazy val family: String = "IM Fell DW Pica SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfelldwpicasc/v7/0ybjGCAu5PfqkvtGVU15aBhXz3EUrnTW-A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `IM Fell Double Pica` extends GoogleFont {
    override lazy val family: String = "IM Fell Double Pica"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfelldoublepica/v7/3XF2EqMq_94s9PeKF7Fg4gOKINyMtZ8rT0Q.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/imfelldoublepica/v7/3XF0EqMq_94s9PeKF7Fg4gOKINyMtZ8rf0a_VA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `IM Fell Double Pica SC` extends GoogleFont {
    override lazy val family: String = "IM Fell Double Pica SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfelldoublepicasc/v7/neIazDmuiMkFo6zj_sHpQ8teNbWlwBB_hXjJ4Y0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `IM Fell English` extends GoogleFont {
    override lazy val family: String = "IM Fell English"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellenglish/v7/Ktk1ALSLW8zDe0rthJysWrnLsAz3Fw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/imfellenglish/v7/Ktk3ALSLW8zDe0rthJysWrnLsAzHFaOd.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `IM Fell English SC` extends GoogleFont {
    override lazy val family: String = "IM Fell English SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellenglishsc/v7/a8IENpD3CDX-4zrWfr1VY879qFF05pZLOw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `IM Fell French Canon` extends GoogleFont {
    override lazy val family: String = "IM Fell French Canon"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellfrenchcanon/v7/-F6ufiNtDWYfYc-tDiyiw08rrghJszkK6coV.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/imfellfrenchcanon/v7/-F6gfiNtDWYfYc-tDiyiw08rrghJszkK6foXNNk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `IM Fell French Canon SC` extends GoogleFont {
    override lazy val family: String = "IM Fell French Canon SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellfrenchcanonsc/v7/FBVmdCru5-ifcor2bgq9V89khWcmQghEURY7H3c0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `IM Fell Great Primer` extends GoogleFont {
    override lazy val family: String = "IM Fell Great Primer"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellgreatprimer/v7/bx6aNwSJtayYxOkbYFsT6hMsLzX7u85rJorX.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/imfellgreatprimer/v7/bx6UNwSJtayYxOkbYFsT6hMsLzX7u85rJrrVtj4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `IM Fell Great Primer SC` extends GoogleFont {
    override lazy val family: String = "IM Fell Great Primer SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellgreatprimersc/v7/ga6daxBOxyt6sCqz3fjZCTFCTUDMHagsQKdDTLf9.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Iceberg` extends GoogleFont {
    override lazy val family: String = "Iceberg"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/iceberg/v5/8QIJdijAiM7o-qnZuIg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Iceland` extends GoogleFont {
    override lazy val family: String = "Iceland"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/iceland/v6/rax9HiuFsdMNOnWPWKw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Imprima` extends GoogleFont {
    override lazy val family: String = "Imprima"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imprima/v5/VEMxRoN7sY3yuy-7-oU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Inconsolata` extends GoogleFont {
    override lazy val family: String = "Inconsolata"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/inconsolata/v16/QldKNThLqRwH-OJ1UHjlKFle.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/inconsolata/v16/QldXNThLqRwH-OJ1UHjlKGHiw41u.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Inder` extends GoogleFont {
    override lazy val family: String = "Inder"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/inder/v6/w8gUH2YoQe8_4vq6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Indie Flower` extends GoogleFont {
    override lazy val family: String = "Indie Flower"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/indieflower/v9/m8JVjfNVeKWVnh3QMuKkFcZlbg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Inika` extends GoogleFont {
    override lazy val family: String = "Inika"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/inika/v5/rnCm-x5X3QP-phTH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/inika/v5/rnCr-x5X3QP-pix7auM-.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Inknut Antiqua` extends GoogleFont {
    override lazy val family: String = "Inknut Antiqua"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/inknutantiqua/v3/Y4GRYax7VC4ot_qNB4nYpBdaKU2vwrj5.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/inknutantiqua/v3/Y4GSYax7VC4ot_qNB4nYpBdaKXUD.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/inknutantiqua/v3/Y4GRYax7VC4ot_qNB4nYpBdaKU33w7j5.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/inknutantiqua/v3/Y4GRYax7VC4ot_qNB4nYpBdaKU3bxLj5.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/inknutantiqua/v3/Y4GRYax7VC4ot_qNB4nYpBdaKU2_xbj5.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/inknutantiqua/v3/Y4GRYax7VC4ot_qNB4nYpBdaKU2jxrj5.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/inknutantiqua/v3/Y4GRYax7VC4ot_qNB4nYpBdaKU2Hx7j5.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Irish Grover` extends GoogleFont {
    override lazy val family: String = "Irish Grover"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/irishgrover/v8/buExpoi6YtLz2QW7LA4flVgf-A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Istok Web` extends GoogleFont {
    override lazy val family: String = "Istok Web"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/istokweb/v11/3qTvojGmgSyUukBzKslZAQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/istokweb/v11/3qTpojGmgSyUukBzKslpA2t6.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/istokweb/v11/3qTqojGmgSyUukBzKslhvU5a_g.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/istokweb/v11/3qT0ojGmgSyUukBzKslpA1PG-2MQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Italiana` extends GoogleFont {
    override lazy val family: String = "Italiana"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/italiana/v6/QldNNTtLsx4E__B0XTmR.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Italianno` extends GoogleFont {
    override lazy val family: String = "Italianno"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/italianno/v7/dg4n_p3sv6gCJkwzT6Rnjw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Itim` extends GoogleFont {
    override lazy val family: String = "Itim"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/itim/v2/0nknC9ziJOYewAQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jacques Francois` extends GoogleFont {
    override lazy val family: String = "Jacques Francois"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jacquesfrancois/v5/ZXu9e04ZvKeOOHIe1TMahbcIU2cgmcM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jacques Francois Shadow` extends GoogleFont {
    override lazy val family: String = "Jacques Francois Shadow"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jacquesfrancoisshadow/v5/KR1FBtOz8PKTMk-kqdkLVrvR0ECFrB6Pin-2_q8V.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jaldi` extends GoogleFont {
    override lazy val family: String = "Jaldi"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jaldi/v3/or3sQ67z0_CI30NU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/jaldi/v3/or3hQ67z0_CI33voSbT3.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Jim Nightshade` extends GoogleFont {
    override lazy val family: String = "Jim Nightshade"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jimnightshade/v5/PlIkFlu9Pb08Q8HLM1PxmB0g-OS4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jockey One` extends GoogleFont {
    override lazy val family: String = "Jockey One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jockeyone/v7/HTxpL2g2KjCFj4x8WI6ArIY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jolly Lodger` extends GoogleFont {
    override lazy val family: String = "Jolly Lodger"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jollylodger/v5/BXRsvFTAh_bGkA1uQ48dlB3VWQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jomhuria` extends GoogleFont {
    override lazy val family: String = "Jomhuria"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jomhuria/v4/Dxxp8j-TMXf-llKur2b1.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Josefin Sans` extends GoogleFont {
    override lazy val family: String = "Josefin Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/josefinsans/v12/Qw3EZQNVED7rKGKxtqIqX5Ecbnx9.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/josefinsans/v12/Qw3GZQNVED7rKGKxtqIqX5EUCEQZXH0.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/josefinsans/v12/Qw3FZQNVED7rKGKxtqIqX5Ecpl5dfA.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/josefinsans/v12/Qw3HZQNVED7rKGKxtqIqX5EUCETRfl0k.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/josefinsans/v12/Qw3aZQNVED7rKGKxtqIqX5EkCg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/josefinsans/v12/Qw3EZQNVED7rKGKxtqIqX5EUCHx9.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/josefinsans/v12/Qw3FZQNVED7rKGKxtqIqX5Ec0lhdfA.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/josefinsans/v12/Qw3HZQNVED7rKGKxtqIqX5EUCESleF0k.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/josefinsans/v12/Qw3FZQNVED7rKGKxtqIqX5EctlldfA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/josefinsans/v12/Qw3HZQNVED7rKGKxtqIqX5EUCETBeV0k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Josefin Slab` extends GoogleFont {
    override lazy val family: String = "Josefin Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/josefinslab/v8/lW-nwjwOK3Ps5GSJlNNkMalvyQ6q.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/josefinslab/v8/lW-lwjwOK3Ps5GSJlNNkMalnrzbODso.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/josefinslab/v8/lW-mwjwOK3Ps5GSJlNNkMalvASyKLg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/josefinslab/v8/lW-kwjwOK3Ps5GSJlNNkMalnrzYGLOrg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/josefinslab/v8/lW-5wjwOK3Ps5GSJlNNkMalXrQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/josefinslab/v8/lW-nwjwOK3Ps5GSJlNNkMalnrw6q.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/josefinslab/v8/lW-mwjwOK3Ps5GSJlNNkMalvdSqKLg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/josefinslab/v8/lW-kwjwOK3Ps5GSJlNNkMalnrzZyKurg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/josefinslab/v8/lW-mwjwOK3Ps5GSJlNNkMalvESuKLg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/josefinslab/v8/lW-kwjwOK3Ps5GSJlNNkMalnrzYWK-rg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Joti One` extends GoogleFont {
    override lazy val family: String = "Joti One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jotione/v5/Z9XVDmdJQAmWm9TwaYTe.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jua` extends GoogleFont {
    override lazy val family: String = "Jua"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jua/v2/co3KmW9ljjAjcw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Judson` extends GoogleFont {
    override lazy val family: String = "Judson"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/judson/v10/FeVRS0Fbvbc14VxRDw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/judson/v10/FeVTS0Fbvbc14VxhDblw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/judson/v10/FeVSS0Fbvbc14Vxps5xQ3Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Julee` extends GoogleFont {
    override lazy val family: String = "Julee"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/julee/v7/TuGfUVB3RpZPQ6ZL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Julius Sans One` extends GoogleFont {
    override lazy val family: String = "Julius Sans One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/juliussansone/v6/1Pt2g8TAX_SGgBGUi0tGOYEga5W-xQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Junge` extends GoogleFont {
    override lazy val family: String = "Junge"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/junge/v5/gokgH670Gl1lUqAd.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jura` extends GoogleFont {
    override lazy val family: String = "Jura"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/jura/v9/z7NUdRfiaC4VVW9rdCw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jura/v9/z7NbdRfiaC4VbcM.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/jura/v9/z7NUdRfiaC4VVTdqdCw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/jura/v9/z7NUdRfiaC4VVRttdCw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/jura/v9/z7NUdRfiaC4VVX9sdCw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Just Another Hand` extends GoogleFont {
    override lazy val family: String = "Just Another Hand"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/justanotherhand/v9/845CNN4-AJyIGvIou-6yJKyptyOpOcr_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Just Me Again Down Here` extends GoogleFont {
    override lazy val family: String = "Just Me Again Down Here"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/justmeagaindownhere/v9/MwQmbgXtz-Wc6RUEGNMc0QpRrfUh2hSdBBMoAuwH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kadwa` extends GoogleFont {
    override lazy val family: String = "Kadwa"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kadwa/v2/rnCm-x5V0g7iphTH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kadwa/v2/rnCr-x5V0g7ipix7auM-.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Kalam` extends GoogleFont {
    override lazy val family: String = "Kalam"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/kalam/v8/YA9Qr0Wd4kDdMtD6GgLL.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kalam/v8/YA9dr0Wd4kDdMuhW.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kalam/v8/YA9Qr0Wd4kDdMtDqHQLL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Kameron` extends GoogleFont {
    override lazy val family: String = "Kameron"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kameron/v8/vm82dR7vXErQxuznsL4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kameron/v8/vm8zdR7vXErQxuzniAIfC-0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Kanit` extends GoogleFont {
    override lazy val family: String = "Kanit"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/kanit/v3/nKKX-Go6G5tXcr72GwU.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/kanit/v3/nKKV-Go6G5tXcraQI2GAdQ.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/kanit/v3/nKKU-Go6G5tXcr5aOiWg.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/kanit/v3/nKKS-Go6G5tXcraQI82hVaQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/kanit/v3/nKKU-Go6G5tXcr4-OSWg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/kanit/v3/nKKS-Go6G5tXcraQI6miVaQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kanit/v3/nKKZ-Go6G5tXcoaS.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/kanit/v3/nKKX-Go6G5tXcraQGwU.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/kanit/v3/nKKU-Go6G5tXcr5mOCWg.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/kanit/v3/nKKS-Go6G5tXcraQI_GjVaQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/kanit/v3/nKKU-Go6G5tXcr5KPyWg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/kanit/v3/nKKS-Go6G5tXcraQI92kVaQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kanit/v3/nKKU-Go6G5tXcr4uPiWg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/kanit/v3/nKKS-Go6G5tXcraQI7mlVaQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/kanit/v3/nKKU-Go6G5tXcr4yPSWg.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/kanit/v3/nKKS-Go6G5tXcraQI6WmVaQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/kanit/v3/nKKU-Go6G5tXcr4WPCWg.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/kanit/v3/nKKS-Go6G5tXcraQI4GnVaQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Kantumruy` extends GoogleFont {
    override lazy val family: String = "Kantumruy"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/kantumruy/v4/syk0-yJ0m7wyVb-f4FOPUtDlpg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kantumruy/v4/sykx-yJ0m7wyVb-f4FO3_g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kantumruy/v4/syk0-yJ0m7wyVb-f4FOPQtflpg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Karla` extends GoogleFont {
    override lazy val family: String = "Karla"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/karla/v6/qkBbXvYC6trAT4RS.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/karla/v6/qkBVXvYC6trAT7RQLtk.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/karla/v6/qkBWXvYC6trAT7zuC_m-.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/karla/v6/qkBQXvYC6trAT7RQFmW7xL4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Karma` extends GoogleFont {
    override lazy val family: String = "Karma"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/karma/v7/va9F4kzAzMZRGLjDY8Z_.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/karma/v7/va9I4kzAzMZRGIBv.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/karma/v7/va9F4kzAzMZRGLibYsZ_.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/karma/v7/va9F4kzAzMZRGLi3ZcZ_.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/karma/v7/va9F4kzAzMZRGLjTZMZ_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Katibeh` extends GoogleFont {
    override lazy val family: String = "Katibeh"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/katibeh/v4/ZGjXol5MQJog4bxDaC0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kaushan Script` extends GoogleFont {
    override lazy val family: String = "Kaushan Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kaushanscript/v6/vm8vdRfvXFLG3OLnsO15WYS5DF7_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kavivanar` extends GoogleFont {
    override lazy val family: String = "Kavivanar"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`tamil`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kavivanar/v3/o-0IIpQgyXYSwhxP7_Jb4g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kavoon` extends GoogleFont {
    override lazy val family: String = "Kavoon"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kavoon/v6/pxiFyp4_scRYhlU4NA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kdam Thmor` extends GoogleFont {
    override lazy val family: String = "Kdam Thmor"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kdamthmor/v4/MwQzbhjs3veF6QwJVf0JkGM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Keania One` extends GoogleFont {
    override lazy val family: String = "Keania One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/keaniaone/v5/zOL54pXJk65E8pXardnuycQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kelly Slab` extends GoogleFont {
    override lazy val family: String = "Kelly Slab"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kellyslab/v7/-W_7XJX0Rz3cxUnJC5t6TkM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kenia` extends GoogleFont {
    override lazy val family: String = "Kenia"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kenia/v9/jizURE5PuHQH9qCO.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Khand` extends GoogleFont {
    override lazy val family: String = "Khand"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/khand/v6/TwMN-IINQlQQ0bL5cFE3.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/khand/v6/TwMA-IINQlQQ0YpV.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/khand/v6/TwMN-IINQlQQ0bKhcVE3.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/khand/v6/TwMN-IINQlQQ0bKNdlE3.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/khand/v6/TwMN-IINQlQQ0bLpd1E3.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Khmer` extends GoogleFont {
    override lazy val family: String = "Khmer"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/khmer/v10/MjQImit_vPPwpF-B.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Khula` extends GoogleFont {
    override lazy val family: String = "Khula"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/khula/v3/OpNPnoEOns3V7G-ljCvU.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/khula/v3/OpNCnoEOns3V7FcJ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/khula/v3/OpNPnoEOns3V7G_RiivU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/khula/v3/OpNPnoEOns3V7G-1iyvU.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/khula/v3/OpNPnoEOns3V7G-piCvU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `600`, `700`, `800`)
  }
  object `Kirang Haerang` extends GoogleFont {
    override lazy val family: String = "Kirang Haerang"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kiranghaerang/v2/E21-_dn_gvvIjhYON1lpIU4-bcqv.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kite One` extends GoogleFont {
    override lazy val family: String = "Kite One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kiteone/v5/70lQu7shLnA_E02vyq1b.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Knewave` extends GoogleFont {
    override lazy val family: String = "Knewave"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/knewave/v6/sykz-yx0lLcxQaSItSo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kotta One` extends GoogleFont {
    override lazy val family: String = "Kotta One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kottaone/v5/S6u_w41LXzPc_jlfNWqPHA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Koulen` extends GoogleFont {
    override lazy val family: String = "Koulen"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/koulen/v11/AMOQz46as3KIBPeWgg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kranky` extends GoogleFont {
    override lazy val family: String = "Kranky"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kranky/v8/hESw6XVgJzlPsFnMpg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kreon` extends GoogleFont {
    override lazy val family: String = "Kreon"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/kreon/v11/t5tjIRIUKY-TFH1sUU23.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kreon/v11/t5tuIRIUKY-TFEXA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kreon/v11/t5tjIRIUKY-TFH18Vk23.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Kristi` extends GoogleFont {
    override lazy val family: String = "Kristi"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kristi/v9/uK_y4ricdeU6zwdRCg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Krona One` extends GoogleFont {
    override lazy val family: String = "Krona One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kronaone/v5/jAnEgHdjHcjgfIb1ZcUCMQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kumar One` extends GoogleFont {
    override lazy val family: String = "Kumar One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `gujarati`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kumarone/v2/bMr1mS-P958wYi6YaGeGNA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kumar One Outline` extends GoogleFont {
    override lazy val family: String = "Kumar One Outline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `gujarati`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kumaroneoutline/v2/Noao6VH62pyLP0fsrZ-v18wlUEcX9zDw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kurale` extends GoogleFont {
    override lazy val family: String = "Kurale"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `devanagari`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kurale/v3/4iCs6KV9e9dXjho6eA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `La Belle Aurore` extends GoogleFont {
    override lazy val family: String = "La Belle Aurore"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/labelleaurore/v8/RrQIbot8-mNYKnGNDkWlocovHeIIGw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Laila` extends GoogleFont {
    override lazy val family: String = "Laila"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/laila/v4/LYjBdG_8nE8jDLzxogNA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/laila/v4/LYjMdG_8nE8jDIRd.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/laila/v4/LYjBdG_8nE8jDLypowNA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/laila/v4/LYjBdG_8nE8jDLyFpANA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/laila/v4/LYjBdG_8nE8jDLzhpQNA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Lakki Reddy` extends GoogleFont {
    override lazy val family: String = "Lakki Reddy"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lakkireddy/v4/S6u5w49MUSzD9jlCPmvLZQfo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lalezar` extends GoogleFont {
    override lazy val family: String = "Lalezar"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lalezar/v3/zrfl0HLVx-HwTP82UaA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lancelot` extends GoogleFont {
    override lazy val family: String = "Lancelot"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lancelot/v7/J7acnppxBGtQEulG4JY4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lateef` extends GoogleFont {
    override lazy val family: String = "Lateef"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lateef/v12/hESw6XVnNCxEvkbMpg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lato` extends GoogleFont {
    override lazy val family: String = "Lato"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/lato/v14/S6u8w4BMUTPHh30wWw.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/lato/v14/S6u-w4BMUTPHjxsIPy-v.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/lato/v14/S6u9w4BMUTPHh7USew8.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/lato/v14/S6u_w4BMUTPHjxsI9w2PHA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lato/v14/S6uyw4BMUTPHvxk.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/lato/v14/S6u8w4BMUTPHjxswWw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lato/v14/S6u9w4BMUTPHh6UVew8.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/lato/v14/S6u_w4BMUTPHjxsI5wqPHA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/lato/v14/S6u9w4BMUTPHh50Xew8.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/lato/v14/S6u_w4BMUTPHjxsI3wiPHA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `League Script` extends GoogleFont {
    override lazy val family: String = "League Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/leaguescript/v8/CSR54zpSlumSWj9CGVsoBZdeaNM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Leckerli One` extends GoogleFont {
    override lazy val family: String = "Leckerli One"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/leckerlione/v8/V8mCoQH8VCsNttEnxnGQ-1itLQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ledger` extends GoogleFont {
    override lazy val family: String = "Ledger"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ledger/v5/j8_q6-HK1L3if_sxmw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lekton` extends GoogleFont {
    override lazy val family: String = "Lekton"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lekton/v8/SZc43FDmLaWmWpBeXw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/lekton/v8/SZc63FDmLaWmWpBuXR3s.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lekton/v8/SZc73FDmLaWmWpBm4zjMlQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Lemon` extends GoogleFont {
    override lazy val family: String = "Lemon"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lemon/v6/HI_EiYEVKqRMq0jB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lemonada` extends GoogleFont {
    override lazy val family: String = "Lemonada"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`, `arabic`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/lemonada/v4/0QIkMXFD9oygTWy_R8PindGu.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lemonada/v4/0QIjMXFD9oygTWy_R_tO.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/lemonada/v4/0QIkMXFD9oygTWy_R8OWm9Gu.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lemonada/v4/0QIkMXFD9oygTWy_R8PymtGu.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `600`, `700`)
  }
  object `Libre Barcode 128` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 128"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode128/v5/cIfnMbdUsUoiW3O_hVviCwVjuLtXeJ_A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Barcode 128 Text` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 128 Text"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode128text/v5/fdNv9tubt3ZEnz1Gu3I4-zppwZ9CWZ16Z0w5cV0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Barcode 39` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 39"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode39/v5/-nFnOHM08vwC6h8Li1eQnP_AHzI2K_c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Barcode 39 Extended` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 39 Extended"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode39extended/v4/8At7Gt6_O5yNS0-K4Nf5U922qSzhJ3dUdfJpwNUgfNQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Barcode 39 Extended Text` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 39 Extended Text"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode39extendedtext/v4/eLG1P_rwIgOiDA7yrs9LoKaYRVLQ1YldrrOnnL7xPO4jNP68fA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Barcode 39 Text` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 39 Text"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode39text/v5/sJoa3KhViNKANw_E3LwoDXvs5Un0HQ1vT-031Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Baskerville` extends GoogleFont {
    override lazy val family: String = "Libre Baskerville"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebaskerville/v5/kmKnZrc3Hgbbcjq75U4uslyuy4kn0pNe.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/librebaskerville/v5/kmKhZrc3Hgbbcjq75U4uslyuy4kn0qNcaxY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/librebaskerville/v5/kmKiZrc3Hgbbcjq75U4uslyuy4kn0qviTjYw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Libre Franklin` extends GoogleFont {
    override lazy val family: String = "Libre Franklin"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/librefranklin/v2/jizBREVItHgc8qDIbSTKq4XkRi182zI.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/librefranklin/v2/jizHREVItHgc8qDIbSTKq4XkRiUa41YTiw.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/librefranklin/v2/jizAREVItHgc8qDIbSTKq4XkRi3Q-hIz.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/librefranklin/v2/jizGREVItHgc8qDIbSTKq4XkRiUa4_oyq14.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/librefranklin/v2/jizAREVItHgc8qDIbSTKq4XkRi20-RIz.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/librefranklin/v2/jizGREVItHgc8qDIbSTKq4XkRiUa454xq14.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librefranklin/v2/jizDREVItHgc8qDIbSTKq4XkRhUY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/librefranklin/v2/jizBREVItHgc8qDIbSTKq4XkRiUa2zI.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/librefranklin/v2/jizAREVItHgc8qDIbSTKq4XkRi3s-BIz.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/librefranklin/v2/jizGREVItHgc8qDIbSTKq4XkRiUa48Ywq14.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/librefranklin/v2/jizAREVItHgc8qDIbSTKq4XkRi3A_xIz.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/librefranklin/v2/jizGREVItHgc8qDIbSTKq4XkRiUa4-o3q14.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/librefranklin/v2/jizAREVItHgc8qDIbSTKq4XkRi2k_hIz.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/librefranklin/v2/jizGREVItHgc8qDIbSTKq4XkRiUa4442q14.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/librefranklin/v2/jizAREVItHgc8qDIbSTKq4XkRi24_RIz.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/librefranklin/v2/jizGREVItHgc8qDIbSTKq4XkRiUa45I1q14.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/librefranklin/v2/jizAREVItHgc8qDIbSTKq4XkRi2c_BIz.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/librefranklin/v2/jizGREVItHgc8qDIbSTKq4XkRiUa47Y0q14.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Life Savers` extends GoogleFont {
    override lazy val family: String = "Life Savers"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lifesavers/v7/ZXuie1UftKKabUQMgxAal_lr.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lifesavers/v7/ZXu_e1UftKKabUQMgxAal8HXOS5T.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Lilita One` extends GoogleFont {
    override lazy val family: String = "Lilita One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lilitaone/v5/i7dPIFZ9Zz-WBtRtedDbUEY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lily Script One` extends GoogleFont {
    override lazy val family: String = "Lily Script One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lilyscriptone/v5/LhW9MV7ZMfIPdMxeBjBvFN8SXLS4gg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Limelight` extends GoogleFont {
    override lazy val family: String = "Limelight"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/limelight/v8/XLYkIZL7aopJVbZJHDuYPQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Linden Hill` extends GoogleFont {
    override lazy val family: String = "Linden Hill"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lindenhill/v7/-F61fjxoKSg9Yc3hZgO8ygFI.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/lindenhill/v7/-F63fjxoKSg9Yc3hZgO8yjFK5ig.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Lobster` extends GoogleFont {
    override lazy val family: String = "Lobster"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lobster/v20/neILzCirqoswsqX9_oU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lobster Two` extends GoogleFont {
    override lazy val family: String = "Lobster Two"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lobstertwo/v10/BngMUXZGTXPUvIoyV6yN59fK.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/lobstertwo/v10/BngOUXZGTXPUvIoyV6yN5-fI5qA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lobstertwo/v10/BngRUXZGTXPUvIoyV6yN5-92w4CB.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/lobstertwo/v10/BngTUXZGTXPUvIoyV6yN5-fI3hyEwRg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Londrina Outline` extends GoogleFont {
    override lazy val family: String = "Londrina Outline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/londrinaoutline/v8/C8c44dM8vmb14dfsZxhetg3pDH-Sfuo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Londrina Shadow` extends GoogleFont {
    override lazy val family: String = "Londrina Shadow"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/londrinashadow/v6/oPWX_kB4kOQoWNJmjxLV5JuoCUlXRg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Londrina Sketch` extends GoogleFont {
    override lazy val family: String = "Londrina Sketch"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/londrinasketch/v6/c4m41npxGMTnomOHtRU68eIJn8qfWQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Londrina Solid` extends GoogleFont {
    override lazy val family: String = "Londrina Solid"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/londrinasolid/v6/flUjRq6sw40kQEJxWNgkLuudGfs9KBY.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/londrinasolid/v6/flUiRq6sw40kQEJxWNgkLuudGfv1CjY0.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/londrinasolid/v6/flUhRq6sw40kQEJxWNgkLuudGcNZ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/londrinasolid/v6/flUiRq6sw40kQEJxWNgkLuudGfvdDzY0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `900`)
  }
  object `Lora` extends GoogleFont {
    override lazy val family: String = "Lora"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lora/v12/0QIvMX1D_JOuAw0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/lora/v12/0QIhMX1D_JOuMw_7Jg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lora/v12/0QIgMX1D_JOuO7HeBts.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/lora/v12/0QIiMX1D_JOuMw_Dmt5unw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Love Ya Like A Sister` extends GoogleFont {
    override lazy val family: String = "Love Ya Like A Sister"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/loveyalikeasister/v8/R70EjzUBlOqPeouhFDfR80-0FhOqJubN-Be78g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Loved by the King` extends GoogleFont {
    override lazy val family: String = "Loved by the King"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lovedbytheking/v7/Gw6gwdP76VDVJNXerebZxUMeRXUF2PiN.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lovers Quarrel` extends GoogleFont {
    override lazy val family: String = "Lovers Quarrel"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/loversquarrel/v5/Yq6N-LSKXTL-5bCy8ksBzpQ_-zAs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Luckiest Guy` extends GoogleFont {
    override lazy val family: String = "Luckiest Guy"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/luckiestguy/v8/_gP_1RrxsjcxVyin9l9n_j2RSg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lusitana` extends GoogleFont {
    override lazy val family: String = "Lusitana"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lusitana/v5/CSR84z9ShvucWzsMKxha.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lusitana/v5/CSR74z9ShvucWzsMKyDmaccq.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Lustria` extends GoogleFont {
    override lazy val family: String = "Lustria"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lustria/v5/9oRONYodvDEyjuhOrCg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Macondo` extends GoogleFont {
    override lazy val family: String = "Macondo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/macondo/v6/RrQQboN9-iB1IXmOS2U.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Macondo Swash Caps` extends GoogleFont {
    override lazy val family: String = "Macondo Swash Caps"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/macondoswashcaps/v5/6NUL8EaAJgGKZA7lpt941Z9s6ZYgDq6Oeg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mada` extends GoogleFont {
    override lazy val family: String = "Mada"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `arabic`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/mada/v5/7Au_p_0qnzeSdf3nCCI.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/mada/v5/7Au_p_0qnzeSdZnkCCI.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mada/v5/7Auwp_0qnzeSTTU.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mada/v5/7Au_p_0qnzeSdcHlCCI.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/mada/v5/7Au_p_0qnzeSde3iCCI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mada/v5/7Au_p_0qnzeSdYnjCCI.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/mada/v5/7Au_p_0qnzeSdbHhCCI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `900`)
  }
  object `Magra` extends GoogleFont {
    override lazy val family: String = "Magra"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/magra/v5/uK_94ruaZus72k5x.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/magra/v5/uK_w4ruaZus72nbNDxcX.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Maiden Orange` extends GoogleFont {
    override lazy val family: String = "Maiden Orange"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/maidenorange/v8/kJE1BuIX7AUmhi2V4m08kb1XjOY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Maitree` extends GoogleFont {
    override lazy val family: String = "Maitree"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/maitree/v2/MjQDmil5tffhpBrklhGNWJE.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/maitree/v2/MjQDmil5tffhpBrklnWOWJE.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/maitree/v2/MjQGmil5tffhpBrkrtk.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/maitree/v2/MjQDmil5tffhpBrkli2PWJE.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/maitree/v2/MjQDmil5tffhpBrklgGIWJE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/maitree/v2/MjQDmil5tffhpBrklmWJWJE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Mako` extends GoogleFont {
    override lazy val family: String = "Mako"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mako/v8/H4coBX6Mmc_Z0SQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mallanna` extends GoogleFont {
    override lazy val family: String = "Mallanna"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mallanna/v5/hv-Vlzx-KEQb84YaDGwz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mandali` extends GoogleFont {
    override lazy val family: String = "Mandali"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mandali/v5/LhWlMVbYOfASNfNUVFk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Manuale` extends GoogleFont {
    override lazy val family: String = "Manuale"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/manuale/v2/f0X20eas_8Z-TFZdBPY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/manuale/v2/f0X00eas_8Z-TFZdNPTOxw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/manuale/v2/f0Xz0eas_8Z-TFZdPALt58E.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/manuale/v2/f0Xx0eas_8Z-TFZdNPT2M8Ln8Q.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/manuale/v2/f0Xz0eas_8Z-TFZdPC7q58E.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/manuale/v2/f0Xx0eas_8Z-TFZdNPT2H8Xn8Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/manuale/v2/f0Xz0eas_8Z-TFZdPErr58E.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/manuale/v2/f0Xx0eas_8Z-TFZdNPT2e8Tn8Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Marcellus` extends GoogleFont {
    override lazy val family: String = "Marcellus"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/marcellus/v5/wEO_EBrOk8hQLDvIAF8FUQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Marcellus SC` extends GoogleFont {
    override lazy val family: String = "Marcellus SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/marcellussc/v5/ke8iOgUHP1dg-Rmi6RWjbLEPgQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Marck Script` extends GoogleFont {
    override lazy val family: String = "Marck Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/marckscript/v8/nwpTtK2oNgBA3Or78gapdwuCzw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Margarine` extends GoogleFont {
    override lazy val family: String = "Margarine"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/margarine/v6/qkBXXvoE6trLT9Y7YLye5A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Marko One` extends GoogleFont {
    override lazy val family: String = "Marko One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/markoone/v7/9Btq3DFG0cnVM5lw1haaKg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Marmelad` extends GoogleFont {
    override lazy val family: String = "Marmelad"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/marmelad/v7/Qw3eZQdSHj_jK2e-8tFL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Martel` extends GoogleFont {
    override lazy val family: String = "Martel"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/martel/v2/PN_yRfK9oXHga0XVqekahQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/martel/v2/PN_yRfK9oXHga0XVzeoahQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/martel/v2/PN_xRfK9oXHga0XtYQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/martel/v2/PN_yRfK9oXHga0XVuewahQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/martel/v2/PN_yRfK9oXHga0XV3e0ahQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/martel/v2/PN_yRfK9oXHga0XVwe4ahQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/martel/v2/PN_yRfK9oXHga0XV5e8ahQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `600`, `700`, `800`, `900`)
  }
  object `Martel Sans` extends GoogleFont {
    override lazy val family: String = "Martel Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/martelsans/v4/h0GxssGi7VdzDgKjM-4d8hAX5suH.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/martelsans/v4/h0GxssGi7VdzDgKjM-4d8hBz5cuH.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/martelsans/v4/h0GsssGi7VdzDgKjM-4d8ijf.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/martelsans/v4/h0GxssGi7VdzDgKjM-4d8hAH48uH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/martelsans/v4/h0GxssGi7VdzDgKjM-4d8hBj4suH.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/martelsans/v4/h0GxssGi7VdzDgKjM-4d8hB_4cuH.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/martelsans/v4/h0GxssGi7VdzDgKjM-4d8hBb4MuH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `600`, `700`, `800`, `900`)
  }
  object `Marvel` extends GoogleFont {
    override lazy val family: String = "Marvel"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/marvel/v7/nwpVtKeoNgBV0qaIkQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/marvel/v7/nwpXtKeoNgBV0qa4k1TA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/marvel/v7/nwpWtKeoNgBV0qawLXHgBw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/marvel/v7/nwpQtKeoNgBV0qa4k2x8Al-i.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Mate` extends GoogleFont {
    override lazy val family: String = "Mate"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mate/v6/m8JdjftRd7WZ2z0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/mate/v6/m8JTjftRd7WZ6z-2Xg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Mate SC` extends GoogleFont {
    override lazy val family: String = "Mate SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/matesc/v6/-nF8OGQ1-uoVr2wKyiU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Maven Pro` extends GoogleFont {
    override lazy val family: String = "Maven Pro"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mavenpro/v11/7Au9p_AqnyWWAxW2Wk32yg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mavenpro/v11/7Au4p_AqnyWWAxW2Wk3OPkctOA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mavenpro/v11/7Au4p_AqnyWWAxW2Wk3OdkEtOA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/mavenpro/v11/7Au4p_AqnyWWAxW2Wk3OTkMtOA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `700`, `900`)
  }
  object `McLaren` extends GoogleFont {
    override lazy val family: String = "McLaren"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mclaren/v5/2EbnL-ZuAXFqZFXISYY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Meddon` extends GoogleFont {
    override lazy val family: String = "Meddon"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/meddon/v10/kmK8ZqA2EgDNeHTZhA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `MedievalSharp` extends GoogleFont {
    override lazy val family: String = "MedievalSharp"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/medievalsharp/v9/EvOJzAlL3oU5AQl2mP5KdgptAq8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Medula One` extends GoogleFont {
    override lazy val family: String = "Medula One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/medulaone/v7/YA9Wr0qb5kjJM6l2V0yukiE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Meera Inimai` extends GoogleFont {
    override lazy val family: String = "Meera Inimai"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`tamil`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/meerainimai/v2/845fNMM5EIqOW5MPuvO3ILep_w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Megrim` extends GoogleFont {
    override lazy val family: String = "Megrim"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/megrim/v8/46kulbz5WjvLqJZlbQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Meie Script` extends GoogleFont {
    override lazy val family: String = "Meie Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/meiescript/v5/_LOImzDK7erRjhunIspaMjxn.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Merienda` extends GoogleFont {
    override lazy val family: String = "Merienda"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/merienda/v5/gNMHW3x8Qoy5_mf8uVMC.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/merienda/v5/gNMAW3x8Qoy5_mf8uWu-Fa-y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Merienda One` extends GoogleFont {
    override lazy val family: String = "Merienda One"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/meriendaone/v8/H4cgBXaMndbflEq6kyZ1ht6Ygg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Merriweather` extends GoogleFont {
    override lazy val family: String = "Merriweather"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/merriweather/v19/u-4n0qyriQwlOrhSvowK_l521wRpXw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/merriweather/v19/u-4l0qyriQwlOrhSvowK_l5-eR7lXcf_.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/merriweather/v19/u-440qyriQwlOrhSvowK_l5Oew.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/merriweather/v19/u-4m0qyriQwlOrhSvowK_l5-eSZJ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/merriweather/v19/u-4n0qyriQwlOrhSvowK_l52xwNpXw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/merriweather/v19/u-4l0qyriQwlOrhSvowK_l5-eR71Wsf_.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/merriweather/v19/u-4n0qyriQwlOrhSvowK_l52_wFpXw.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/merriweather/v19/u-4l0qyriQwlOrhSvowK_l5-eR7NWMf_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Merriweather Sans` extends GoogleFont {
    override lazy val family: String = "Merriweather Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/merriweathersans/v9/2-c49IRs1JiJN1FRAMjTN5zd9vgsFH1eYBDD.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/merriweathersans/v9/2-c29IRs1JiJN1FRAMjTN5zd9vgsFHXwepzB0hM.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/merriweathersans/v9/2-c99IRs1JiJN1FRAMjTN5zd9vgsFEXy.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/merriweathersans/v9/2-c79IRs1JiJN1FRAMjTN5zd9vgsFHXwQjA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/merriweathersans/v9/2-c49IRs1JiJN1FRAMjTN5zd9vgsFH1OZxDD.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/merriweathersans/v9/2-c29IRs1JiJN1FRAMjTN5zd9vgsFHXweozG0hM.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/merriweathersans/v9/2-c49IRs1JiJN1FRAMjTN5zd9vgsFH1SZBDD.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/merriweathersans/v9/2-c29IRs1JiJN1FRAMjTN5zd9vgsFHXwepDF0hM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Metal` extends GoogleFont {
    override lazy val family: String = "Metal"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/metal/v10/lW-wwjUJIXTo7i3n.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Metal Mania` extends GoogleFont {
    override lazy val family: String = "Metal Mania"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/metalmania/v7/RWmMoKWb4e8kqMfBUdPFJeXC.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Metamorphous` extends GoogleFont {
    override lazy val family: String = "Metamorphous"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/metamorphous/v7/Wnz8HA03aAXcC39ZEX5y1330PA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Metrophobic` extends GoogleFont {
    override lazy val family: String = "Metrophobic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/metrophobic/v10/sJoA3LZUhMSAPV_u0qwiAT-J.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Michroma` extends GoogleFont {
    override lazy val family: String = "Michroma"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/michroma/v8/PN_zRfy9qWD8fEagAMg6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Milonga` extends GoogleFont {
    override lazy val family: String = "Milonga"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/milonga/v5/SZc53FHnIaK9W5kffz0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Miltonian` extends GoogleFont {
    override lazy val family: String = "Miltonian"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/miltonian/v11/zOL-4pbPn6Ne9JqTg9mr6Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Miltonian Tattoo` extends GoogleFont {
    override lazy val family: String = "Miltonian Tattoo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/miltoniantattoo/v12/EvOUzBRL0o0kCxF-lcMCQxlpVsA_FwM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mina` extends GoogleFont {
    override lazy val family: String = "Mina"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `bengali`: GoogleFontSubset = new GoogleFontSubset("bengali")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `bengali`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mina/v1/-nFzOGc18vARrz8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mina/v1/-nF8OGc18vARl4NMyiU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Miniver` extends GoogleFont {
    override lazy val family: String = "Miniver"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/miniver/v6/eLGcP-PxIg-5H0vC770.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Miriam Libre` extends GoogleFont {
    override lazy val family: String = "Miriam Libre"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `hebrew`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/miriamlibre/v3/DdTh798HsHwubBAqfkcBTL_vYA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/miriamlibre/v3/DdT-798HsHwubBAqfkcBTL_X3LbbRQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Mirza` extends GoogleFont {
    override lazy val family: String = "Mirza"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mirza/v4/co3ImWlikiN5Eurd.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mirza/v4/co3FmWlikiN5EtIpAeO4.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/mirza/v4/co3FmWlikiN5EtIFBuO4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mirza/v4/co3FmWlikiN5EtJhB-O4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`)
  }
  object `Miss Fajardose` extends GoogleFont {
    override lazy val family: String = "Miss Fajardose"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/missfajardose/v7/E21-_dn5gvrawDdPFVl-N0Ajb8qv.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mitr` extends GoogleFont {
    override lazy val family: String = "Mitr"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/mitr/v3/pxiEypw5ucZF8fMZFJA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/mitr/v3/pxiEypw5ucZF8ZcaFJA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mitr/v3/pxiLypw5ucZFyTs.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mitr/v3/pxiEypw5ucZF8c8bFJA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/mitr/v3/pxiEypw5ucZF8eMcFJA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mitr/v3/pxiEypw5ucZF8YcdFJA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Modak` extends GoogleFont {
    override lazy val family: String = "Modak"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/modak/v3/EJRYQgs1XtIEsnMH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Modern Antiqua` extends GoogleFont {
    override lazy val family: String = "Modern Antiqua"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/modernantiqua/v7/NGStv5TIAUg6Iq_RLNo_2dp1sI1E.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mogra` extends GoogleFont {
    override lazy val family: String = "Mogra"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `gujarati`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mogra/v4/f0X40eSs8c95TBo4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Molengo` extends GoogleFont {
    override lazy val family: String = "Molengo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/molengo/v8/I_uuMpWeuBzZNBtQbbQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Molle` extends GoogleFont {
    override lazy val family: String = "Molle"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/molle/v6/E21n_dL5hOXFhWEsXzg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`italic`)
  }
  object `Monda` extends GoogleFont {
    override lazy val family: String = "Monda"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/monda/v7/TK3tWkYFABsmjvpm.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/monda/v7/TK3gWkYFABsmjsLaGz8D.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Monofett` extends GoogleFont {
    override lazy val family: String = "Monofett"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/monofett/v7/mFTyWbofw6zc9NtnW43S.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Monoton` extends GoogleFont {
    override lazy val family: String = "Monoton"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/monoton/v7/5h1aiZUrOngCibe4fkY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Monsieur La Doulaise` extends GoogleFont {
    override lazy val family: String = "Monsieur La Doulaise"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/monsieurladoulaise/v6/_Xmz-GY4rjmCbQfc-aPRaa4pqV340p7EZl5e.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Montaga` extends GoogleFont {
    override lazy val family: String = "Montaga"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/montaga/v5/H4cnBX2Ml8rCkEO_0gY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Montez` extends GoogleFont {
    override lazy val family: String = "Montez"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/montez/v8/845ZNMk5GoGIX8lm1A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Montserrat` extends GoogleFont {
    override lazy val family: String = "Montserrat"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/montserrat/v12/JTUQjIg1_i6t8kCHKm45_Qphzg.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/montserrat/v12/JTUOjIg1_i6t8kCHKm459WxZqi7j.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/montserrat/v12/JTURjIg1_i6t8kCHKm45_aZA7g4.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/montserrat/v12/JTUPjIg1_i6t8kCHKm459WxZBg_D-w.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/montserrat/v12/JTURjIg1_i6t8kCHKm45_cJD7g4.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/montserrat/v12/JTUPjIg1_i6t8kCHKm459WxZYgzD-w.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/montserrat/v12/JTUSjIg1_i6t8kCHKm45xW4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/montserrat/v12/JTUQjIg1_i6t8kCHKm459Wxhzg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/montserrat/v12/JTURjIg1_i6t8kCHKm45_ZpC7g4.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/montserrat/v12/JTUPjIg1_i6t8kCHKm459WxZOg3D-w.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/montserrat/v12/JTURjIg1_i6t8kCHKm45_bZF7g4.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/montserrat/v12/JTUPjIg1_i6t8kCHKm459WxZFgrD-w.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/montserrat/v12/JTURjIg1_i6t8kCHKm45_dJE7g4.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/montserrat/v12/JTUPjIg1_i6t8kCHKm459WxZcgvD-w.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/montserrat/v12/JTURjIg1_i6t8kCHKm45_c5H7g4.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/montserrat/v12/JTUPjIg1_i6t8kCHKm459WxZbgjD-w.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/montserrat/v12/JTURjIg1_i6t8kCHKm45_epG7g4.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/montserrat/v12/JTUPjIg1_i6t8kCHKm459WxZSgnD-w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Montserrat Alternates` extends GoogleFont {
    override lazy val family: String = "Montserrat Alternates"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/montserratalternates/v9/mFThWacfw6zH4dthXcyms1lPpC8I_b0juU0xiKfV.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/montserratalternates/v9/mFTjWacfw6zH4dthXcyms1lPpC8I_b0juU057p-xIJw.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/montserratalternates/v9/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xJIb1AA.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/montserratalternates/v9/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p8dAbxD.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/montserratalternates/v9/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xQIX1AA.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/montserratalternates/v9/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p95ArxD.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/montserratalternates/v9/mFTvWacfw6zH4dthXcyms1lPpC8I_b0juU0J7A.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/montserratalternates/v9/mFThWacfw6zH4dthXcyms1lPpC8I_b0juU057qfV.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/montserratalternates/v9/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xGIT1AA.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/montserratalternates/v9/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p8hA7xD.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/montserratalternates/v9/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xNIP1AA.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/montserratalternates/v9/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p8NBLxD.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/montserratalternates/v9/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xUIL1AA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/montserratalternates/v9/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p9pBbxD.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/montserratalternates/v9/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xTIH1AA.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/montserratalternates/v9/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p91BrxD.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/montserratalternates/v9/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xaID1AA.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/montserratalternates/v9/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p9RB7xD.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Montserrat Subrayada` extends GoogleFont {
    override lazy val family: String = "Montserrat Subrayada"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/montserratsubrayada/v5/U9MD6c-o9H7PgjlTHThBnNHGVUORwteQQE8L.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/montserratsubrayada/v5/U9MM6c-o9H7PgjlTHThBnNHGVUORwteQQHe3TcMW.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Moul` extends GoogleFont {
    override lazy val family: String = "Moul"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/moul/v9/nuF2D__FSo_3E-Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Moulpali` extends GoogleFont {
    override lazy val family: String = "Moulpali"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/moulpali/v10/H4ckBXKMl9HagUWymyY6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mountains of Christmas` extends GoogleFont {
    override lazy val family: String = "Mountains of Christmas"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mountainsofchristmas/v10/3y9w6a4zcCnn5X0FDyrKi2ZRUBIy8uxoUo7ePNY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mountainsofchristmas/v10/3y9z6a4zcCnn5X0FDyrKi2ZRUBIy8uxoUo7eBGqJFPs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Mouse Memoirs` extends GoogleFont {
    override lazy val family: String = "Mouse Memoirs"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mousememoirs/v5/t5tmIRoSNJ-PH0WNNgDYxdSb7Tk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mr Bedfort` extends GoogleFont {
    override lazy val family: String = "Mr Bedfort"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mrbedfort/v6/MQpR-WCtNZSWAdTMwBiclio.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mr Dafoe` extends GoogleFont {
    override lazy val family: String = "Mr Dafoe"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mrdafoe/v6/lJwE-pIzkS5NXuMMrGiq.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mr De Haviland` extends GoogleFont {
    override lazy val family: String = "Mr De Haviland"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mrdehaviland/v6/OpNVnooIhJj96FdB73296ksbOj3C.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mrs Saint Delafield` extends GoogleFont {
    override lazy val family: String = "Mrs Saint Delafield"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mrssaintdelafield/v5/v6-IGZDIOVXH9xtmTZfRagunqBw5WC62cK4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mrs Sheppards` extends GoogleFont {
    override lazy val family: String = "Mrs Sheppards"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mrssheppards/v6/PN_2Rfm9snC0XUGoEZhb91ig3vg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mukta` extends GoogleFont {
    override lazy val family: String = "Mukta"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/mukta/v5/iJWHBXyXfDDVXbEOjFma.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/mukta/v5/iJWHBXyXfDDVXbFqj1ma.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mukta/v5/iJWKBXyXfDDVXYnG.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mukta/v5/iJWHBXyXfDDVXbEyjlma.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/mukta/v5/iJWHBXyXfDDVXbEeiVma.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mukta/v5/iJWHBXyXfDDVXbF6iFma.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/mukta/v5/iJWHBXyXfDDVXbFmi1ma.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Mukta Mahee` extends GoogleFont {
    override lazy val family: String = "Mukta Mahee"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `gurmukhi`: GoogleFontSubset = new GoogleFontSubset("gurmukhi")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`gurmukhi`, `latin`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/muktamahee/v2/XRXN3IOIi0hcP8iVU67hA9MFcBoH.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/muktamahee/v2/XRXN3IOIi0hcP8iVU67hA9NhcxoH.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/muktamahee/v2/XRXQ3IOIi0hcP8iVU67hA-vN.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/muktamahee/v2/XRXN3IOIi0hcP8iVU67hA9M5choH.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/muktamahee/v2/XRXN3IOIi0hcP8iVU67hA9MVdRoH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/muktamahee/v2/XRXN3IOIi0hcP8iVU67hA9NxdBoH.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/muktamahee/v2/XRXN3IOIi0hcP8iVU67hA9NtdxoH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Mukta Malar` extends GoogleFont {
    override lazy val family: String = "Mukta Malar"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`tamil`, `latin`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/muktamalar/v3/MCoKzAXyz8LOE2FpJMxZqIMwBtAB.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/muktamalar/v3/MCoKzAXyz8LOE2FpJMxZqINUBdAB.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/muktamalar/v3/MCoXzAXyz8LOE2FpJMxZqLv4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/muktamalar/v3/MCoKzAXyz8LOE2FpJMxZqIMMBNAB.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/muktamalar/v3/MCoKzAXyz8LOE2FpJMxZqIMgA9AB.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/muktamalar/v3/MCoKzAXyz8LOE2FpJMxZqINEAtAB.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/muktamalar/v3/MCoKzAXyz8LOE2FpJMxZqINYAdAB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Mukta Vaani` extends GoogleFont {
    override lazy val family: String = "Mukta Vaani"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `gujarati`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/muktavaani/v4/3JnkSD_-ynaxmxnEfVHPIGXNV8BD.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/muktavaani/v4/3JnkSD_-ynaxmxnEfVHPIGWpVMBD.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/muktavaani/v4/3Jn5SD_-ynaxmxnEfVHPIF0F.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/muktavaani/v4/3JnkSD_-ynaxmxnEfVHPIGXxVcBD.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/muktavaani/v4/3JnkSD_-ynaxmxnEfVHPIGXdUsBD.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/muktavaani/v4/3JnkSD_-ynaxmxnEfVHPIGW5U8BD.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/muktavaani/v4/3JnkSD_-ynaxmxnEfVHPIGWlUMBD.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Muli` extends GoogleFont {
    override lazy val family: String = "Muli"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/muli/v11/7Au_p_0qiz-adf3nCCI.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/muli/v11/7Au9p_0qiz-afTf-4CP2yg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/muli/v11/7Au_p_0qiz-adZnkCCI.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/muli/v11/7Au9p_0qiz-afTf-hCD2yg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/muli/v11/7Auwp_0qiz-aTTU.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/muli/v11/7Au-p_0qiz-afTfGKA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/muli/v11/7Au_p_0qiz-ade3iCCI.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/muli/v11/7Au9p_0qiz-afTf-8Cb2yg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/muli/v11/7Au_p_0qiz-adYnjCCI.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/muli/v11/7Au9p_0qiz-afTf-lCf2yg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/muli/v11/7Au_p_0qiz-adZXgCCI.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/muli/v11/7Au9p_0qiz-afTf-iCT2yg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/muli/v11/7Au_p_0qiz-adbHhCCI.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/muli/v11/7Au9p_0qiz-afTf-rCX2yg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Mystery Quest` extends GoogleFont {
    override lazy val family: String = "Mystery Quest"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mysteryquest/v5/-nF6OG414u0E6k0wynSGlujRHwE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `NTR` extends GoogleFont {
    override lazy val family: String = "NTR"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ntr/v5/RLpzK5Xy0ZjiGA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nanum Brush Script` extends GoogleFont {
    override lazy val family: String = "Nanum Brush Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nanumbrushscript/v8/wXK2E2wfpokopxzthSqPbcR5_gVaxazyjg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nanum Gothic` extends GoogleFont {
    override lazy val family: String = "Nanum Gothic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nanumgothic/v7/PN_3Rfi-oW3hYwmKDpxS7F_z_g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nanumgothic/v7/PN_oRfi-oW3hYwmKDpxS7F_LQv37zg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/nanumgothic/v7/PN_oRfi-oW3hYwmKDpxS7F_LXv77zg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `800`)
  }
  object `Nanum Gothic Coding` extends GoogleFont {
    override lazy val family: String = "Nanum Gothic Coding"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nanumgothiccoding/v6/8QIVdjzHisX_8vv59_xMxtPFW4IXROwsy6Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nanumgothiccoding/v6/8QIYdjzHisX_8vv59_xMxtPFW4IXROws8xgecsU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Nanum Myeongjo` extends GoogleFont {
    override lazy val family: String = "Nanum Myeongjo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nanummyeongjo/v6/9Btx3DZF0dXLMZlywRbVRNhxy1Lr.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nanummyeongjo/v6/9Bty3DZF0dXLMZlywRbVRNhxy2pXV1A0.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/nanummyeongjo/v6/9Bty3DZF0dXLMZlywRbVRNhxy2pLVFA0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `800`)
  }
  object `Nanum Pen Script` extends GoogleFont {
    override lazy val family: String = "Nanum Pen Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nanumpenscript/v6/daaDSSYiLGqEal3MvdA_FOL_3FkN2z4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Neucha` extends GoogleFont {
    override lazy val family: String = "Neucha"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/neucha/v9/q5uGsou0JOdh94bvug.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Neuton` extends GoogleFont {
    override lazy val family: String = "Neuton"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/neuton/v10/UMBQrPtMoH62xUZKAKkfeg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/neuton/v10/UMBQrPtMoH62xUZKZKofeg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/neuton/v10/UMBTrPtMoH62xUZyyA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/neuton/v10/UMBRrPtMoH62xUZCyog_.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/neuton/v10/UMBQrPtMoH62xUZKdK0feg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/neuton/v10/UMBQrPtMoH62xUZKaK4feg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `italic`, `700`, `800`)
  }
  object `New Rocker` extends GoogleFont {
    override lazy val family: String = "New Rocker"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/newrocker/v6/MwQzbhjp3-HImzcCU_cJkGM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `News Cycle` extends GoogleFont {
    override lazy val family: String = "News Cycle"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/newscycle/v14/CSR64z1Qlv-GDxkbKVQ_TOc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/newscycle/v14/CSR54z1Qlv-GDxkbKVQ_dFsvaNM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Niconne` extends GoogleFont {
    override lazy val family: String = "Niconne"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/niconne/v7/w8gaH2QvRug1_rTfrQs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nixie One` extends GoogleFont {
    override lazy val family: String = "Nixie One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nixieone/v8/lW-8wjkKLXjg5y2o2uUoUA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nobile` extends GoogleFont {
    override lazy val family: String = "Nobile"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nobile/v9/m8JTjflSeaOVl1i2Xg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/nobile/v9/m8JRjflSeaOVl1iGXK3T.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/nobile/v9/m8JQjflSeaOVl1iOqo7zcA.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/nobile/v9/m8JWjflSeaOVl1iGXJUnc5RF.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nobile/v9/m8JQjflSeaOVl1iO4ojzcA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/nobile/v9/m8JWjflSeaOVl1iGXJVvdZRF.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `700`, `700italic`)
  }
  object `Nokora` extends GoogleFont {
    override lazy val family: String = "Nokora"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nokora/v11/hYkIPuwgTubzaWxQOw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nokora/v11/hYkLPuwgTubzaWxohxUrqg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Norican` extends GoogleFont {
    override lazy val family: String = "Norican"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/norican/v5/MwQ2bhXp1eSBqjkPGJI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nosifer` extends GoogleFont {
    override lazy val family: String = "Nosifer"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nosifer/v6/ZGjXol5JTp0g5bxZaC0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nothing You Could Do` extends GoogleFont {
    override lazy val family: String = "Nothing You Could Do"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nothingyoucoulddo/v7/oY1B8fbBpaP5OX3DtrRYf_Q2BPB1SnfZb0OJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Noticia Text` extends GoogleFont {
    override lazy val family: String = "Noticia Text"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/noticiatext/v7/VuJ2dNDF2Yv9qppOePKYRP1GYQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/noticiatext/v7/VuJodNDF2Yv9qppOePKYRP12Yztd.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/noticiatext/v7/VuJpdNDF2Yv9qppOePKYRP1-3R59vw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/noticiatext/v7/VuJrdNDF2Yv9qppOePKYRP12YwPhumvV.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Noto Sans` extends GoogleFont {
    override lazy val family: String = "Noto Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `devanagari`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notosans/v7/o-0IIpQlx3QUlC5A4PNb4g.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/notosans/v7/o-0OIpQlx3QUlC5A4PNr4DRF.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notosans/v7/o-0NIpQlx3QUlC5A4PNjXhFlYw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/notosans/v7/o-0TIpQlx3QUlC5A4PNr4Az5ZtyE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Noto Serif` extends GoogleFont {
    override lazy val family: String = "Noto Serif"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notoserif/v6/ga6Iaw1J5X9T9RW6j9bNTFA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/notoserif/v6/ga6Kaw1J5X9T9RW6j9bNfFIWbQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notoserif/v6/ga6Law1J5X9T9RW6j9bNdOwzTRA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/notoserif/v6/ga6Vaw1J5X9T9RW6j9bNfFIu0RWedA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Nova Cut` extends GoogleFont {
    override lazy val family: String = "Nova Cut"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novacut/v9/KFOkCnSYu8mL-39LkWxP.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Flat` extends GoogleFont {
    override lazy val family: String = "Nova Flat"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novaflat/v9/QdVUSTc-JgqpytEbVebEuQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Mono` extends GoogleFont {
    override lazy val family: String = "Nova Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `greek`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novamono/v8/Cn-0JtiGWQ5Ajb--MRKfYA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Oval` extends GoogleFont {
    override lazy val family: String = "Nova Oval"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novaoval/v9/jAnEgHdmANHvPenMaswCMQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Round` extends GoogleFont {
    override lazy val family: String = "Nova Round"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novaround/v9/flU9Rqquw5UhEnlwTJYTYYc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Script` extends GoogleFont {
    override lazy val family: String = "Nova Script"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novascript/v10/7Au7p_IpkSWSTWaFWkumvmQN.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Slim` extends GoogleFont {
    override lazy val family: String = "Nova Slim"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novaslim/v9/Z9XUDmZNQAuem8jyZcn-yA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Square` extends GoogleFont {
    override lazy val family: String = "Nova Square"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novasquare/v9/RrQUbo9-9DV7b06QHgSWsZhA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Numans` extends GoogleFont {
    override lazy val family: String = "Numans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/numans/v7/SlGRmQmGupYAfH8IYQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nunito` extends GoogleFont {
    override lazy val family: String = "Nunito"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/nunito/v9/XRXW3I6Li01BKofA-sekZg.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/nunito/v9/XRXQ3I6Li01BKofIMN5MZ-vN.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/nunito/v9/XRXW3I6Li01BKofAnsSkZg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/nunito/v9/XRXQ3I6Li01BKofIMN4oZOvN.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nunito/v9/XRXV3I6Li01BKof4Mg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/nunito/v9/XRXX3I6Li01BKofIMOaE.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/nunito/v9/XRXW3I6Li01BKofA6sKkZg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/nunito/v9/XRXQ3I6Li01BKofIMN5cYuvN.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nunito/v9/XRXW3I6Li01BKofAjsOkZg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/nunito/v9/XRXQ3I6Li01BKofIMN44Y-vN.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/nunito/v9/XRXW3I6Li01BKofAksCkZg.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/nunito/v9/XRXQ3I6Li01BKofIMN4kYOvN.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/nunito/v9/XRXW3I6Li01BKofAtsGkZg.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/nunito/v9/XRXQ3I6Li01BKofIMN4AYevN.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Nunito Sans` extends GoogleFont {
    override lazy val family: String = "Nunito Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/nunitosans/v3/pe03MImSLYBIv1o4X1M8cc9yAv5q.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/nunitosans/v3/pe01MImSLYBIv1o4X1M8cce4GxZrU1Q.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/nunitosans/v3/pe03MImSLYBIv1o4X1M8cc8WAf5q.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/nunitosans/v3/pe01MImSLYBIv1o4X1M8cce4G3JoU1Q.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nunitosans/v3/pe0qMImSLYBIv1o4X1M8cfe6.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/nunitosans/v3/pe0oMImSLYBIv1o4X1M8cce4I94.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/nunitosans/v3/pe03MImSLYBIv1o4X1M8cc9iB_5q.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/nunitosans/v3/pe01MImSLYBIv1o4X1M8cce4GwZuU1Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nunitosans/v3/pe03MImSLYBIv1o4X1M8cc8GBv5q.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/nunitosans/v3/pe01MImSLYBIv1o4X1M8cce4G2JvU1Q.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/nunitosans/v3/pe03MImSLYBIv1o4X1M8cc8aBf5q.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/nunitosans/v3/pe01MImSLYBIv1o4X1M8cce4G35sU1Q.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/nunitosans/v3/pe03MImSLYBIv1o4X1M8cc8-BP5q.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/nunitosans/v3/pe01MImSLYBIv1o4X1M8cce4G1ptU1Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Odor Mean Chey` extends GoogleFont {
    override lazy val family: String = "Odor Mean Chey"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/odormeanchey/v9/raxkHiKDttkTe1aOGcJMR1A_4mrY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Offside` extends GoogleFont {
    override lazy val family: String = "Offside"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/offside/v5/HI_KiYMWKa9QrAykQ5E.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Old Standard TT` extends GoogleFont {
    override lazy val family: String = "Old Standard TT"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oldstandardtt/v9/MwQubh3o1vLImiwAVvYawgcf2eVurQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/oldstandardtt/v9/MwQsbh3o1vLImiwAVvYawgcf2eVer1q9.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/oldstandardtt/v9/MwQrbh3o1vLImiwAVvYawgcf2eVWEX-dTA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Oldenburg` extends GoogleFont {
    override lazy val family: String = "Oldenburg"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oldenburg/v5/fC1jPY5JYWzbywv7c4V6UQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Oleo Script` extends GoogleFont {
    override lazy val family: String = "Oleo Script"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oleoscript/v6/rax5HieDvtMOe0iICsUccBha.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/oleoscript/v6/raxkHieDvtMOe0iICsUccCDmnmrY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Oleo Script Swash Caps` extends GoogleFont {
    override lazy val family: String = "Oleo Script Swash Caps"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oleoscriptswashcaps/v5/Noaj6Vb-w5SFbTTAsZP_7JkCS08K-jCzDn_HMXo.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/oleoscriptswashcaps/v5/Noag6Vb-w5SFbTTAsZP_7JkCS08K-jCzDn_HCcaBbYU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Open Sans` extends GoogleFont {
    override lazy val family: String = "Open Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/opensans/v15/mem5YaGs126MiZpBA-UN_r8-VQ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/opensans/v15/memnYaGs126MiZpBA-UFUKWyV-hs.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/opensans/v15/mem8YaGs126MiZpBA-U1Ug.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/opensans/v15/mem6YaGs126MiZpBA-UFUJ0e.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/opensans/v15/mem5YaGs126MiZpBA-UNirk-VQ.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/opensans/v15/memnYaGs126MiZpBA-UFUKXGUehs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/opensans/v15/mem5YaGs126MiZpBA-UN7rg-VQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/opensans/v15/memnYaGs126MiZpBA-UFUKWiUOhs.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/opensans/v15/mem5YaGs126MiZpBA-UN8rs-VQ.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/opensans/v15/memnYaGs126MiZpBA-UFUKW-U-hs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Open Sans Condensed` extends GoogleFont {
    override lazy val family: String = "Open Sans Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/opensanscondensed/v12/z7NFdQDnbTkabZAIOl9il_O6KJj73e7Ff1GhPuI.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/opensanscondensed/v12/z7NHdQDnbTkabZAIOl9il_O6KJj73e7Fd_-7suDMQg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/opensanscondensed/v12/z7NFdQDnbTkabZAIOl9il_O6KJj73e7Ff0GmPuI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `700`)
  }
  object `Oranienbaum` extends GoogleFont {
    override lazy val family: String = "Oranienbaum"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oranienbaum/v6/OZpHg_txtzZKMuXLIVrx-3zn.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Orbitron` extends GoogleFont {
    override lazy val family: String = "Orbitron"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/orbitron/v9/yMJRMIlzdpvBhQQL_Tq8.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/orbitron/v9/yMJWMIlzdpvBhQQL_QJIVAhx.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/orbitron/v9/yMJWMIlzdpvBhQQL_QIAUghx.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/orbitron/v9/yMJWMIlzdpvBhQQL_QI4UAhx.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `700`, `900`)
  }
  object `Oregano` extends GoogleFont {
    override lazy val family: String = "Oregano"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oregano/v5/If2IXTPxciS3H4S2kZc.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/oregano/v5/If2KXTPxciS3H4S2oZXVOw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Orienta` extends GoogleFont {
    override lazy val family: String = "Orienta"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/orienta/v5/PlI9FlK4Jrl5Y9zNeyc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Original Surfer` extends GoogleFont {
    override lazy val family: String = "Original Surfer"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/originalsurfer/v6/RWmQoKGZ9vIirYntXJ3_MbekzNMiDA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Oswald` extends GoogleFont {
    override lazy val family: String = "Oswald"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/oswald/v16/TK3hWkUHHAIjg75-1h4jvQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/oswald/v16/TK3hWkUHHAIjg75-sh0jvQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oswald/v16/TK3iWkUHHAIjg75GHg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/oswald/v16/TK3hWkUHHAIjg75-6hwjvQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/oswald/v16/TK3hWkUHHAIjg75-xhsjvQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/oswald/v16/TK3hWkUHHAIjg75-ohojvQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Over the Rainbow` extends GoogleFont {
    override lazy val family: String = "Over the Rainbow"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/overtherainbow/v8/11haGoXG1k_HKhMLUWz7Mc7vvW5upvM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Overlock` extends GoogleFont {
    override lazy val family: String = "Overlock"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/overlock/v7/Z9XVDmdMWRiN1_T9Z4Te.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/overlock/v7/Z9XTDmdMWRiN1_T9Z7Tc6Ok.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/overlock/v7/Z9XSDmdMWRiN1_T9Z7xizcmM.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/overlock/v7/Z9XQDmdMWRiN1_T9Z7Tc0FWJtrk.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/overlock/v7/Z9XSDmdMWRiN1_T9Z7xaz8mM.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/overlock/v7/Z9XQDmdMWRiN1_T9Z7Tc0G2Ltrk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Overlock SC` extends GoogleFont {
    override lazy val family: String = "Overlock SC"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/overlocksc/v6/1cX3aUHKGZrstGAY8nwVzHGA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Overpass` extends GoogleFont {
    override lazy val family: String = "Overpass"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/overpass/v2/qFdB35WCmI96Ajtm81nGU94.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/overpass/v2/qFdD35WCmI96Ajtm81Gga7rqwg.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/overpass/v2/qFdA35WCmI96Ajtm81lqcv7K.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/overpass/v2/qFdC35WCmI96Ajtm81GgaxbL4h8.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/overpass/v2/qFdA35WCmI96Ajtm81kOcf7K.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/overpass/v2/qFdC35WCmI96Ajtm81Gga3LI4h8.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/overpass/v2/qFdH35WCmI96Ajtm82Gi.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/overpass/v2/qFdB35WCmI96Ajtm81GgU94.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/overpass/v2/qFdA35WCmI96Ajtm81l6d_7K.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/overpass/v2/qFdC35WCmI96Ajtm81GgawbO4h8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/overpass/v2/qFdA35WCmI96Ajtm81kedv7K.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/overpass/v2/qFdC35WCmI96Ajtm81Gga2LP4h8.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/overpass/v2/qFdA35WCmI96Ajtm81kCdf7K.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/overpass/v2/qFdC35WCmI96Ajtm81Gga37M4h8.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/overpass/v2/qFdA35WCmI96Ajtm81kmdP7K.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/overpass/v2/qFdC35WCmI96Ajtm81Gga1rN4h8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Overpass Mono` extends GoogleFont {
    override lazy val family: String = "Overpass Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/overpassmono/v3/_Xm3-H86tzKDdAPa-KPQZ-AC3oSWk_c.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/overpassmono/v3/_Xmq-H86tzKDdAPa-KPQZ-AC5ig.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/overpassmono/v3/_Xm3-H86tzKDdAPa-KPQZ-AC3vCQk_c.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/overpassmono/v3/_Xm3-H86tzKDdAPa-KPQZ-AC3pSRk_c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `600`, `700`)
  }
  object `Ovo` extends GoogleFont {
    override lazy val family: String = "Ovo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ovo/v8/yYLl0h7Wyfzjyw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Oxygen` extends GoogleFont {
    override lazy val family: String = "Oxygen"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/oxygen/v7/2sDcZG1Wl4LcnbuCJW8Dbw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oxygen/v7/2sDfZG1Wl4Lcnbu6iQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/oxygen/v7/2sDcZG1Wl4LcnbuCNWgDbw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Oxygen Mono` extends GoogleFont {
    override lazy val family: String = "Oxygen Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oxygenmono/v5/h0GsssGg9FxgDgCjLeAd7ijf.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `PT Mono` extends GoogleFont {
    override lazy val family: String = "PT Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptmono/v5/9oRONYoBnWILk-9ArCg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `PT Sans` extends GoogleFont {
    override lazy val family: String = "PT Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptsans/v9/jizaRExUiTo99u79P0U.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ptsans/v9/jizYRExUiTo99u79D0eEwA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ptsans/v9/jizfRExUiTo99u79B_mh4Ok.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ptsans/v9/jizdRExUiTo99u79D0e8fOytKA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `PT Sans Caption` extends GoogleFont {
    override lazy val family: String = "PT Sans Caption"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptsanscaption/v10/0FlMVP6Hrxmt7-fsUFhlFXNIlpcqfQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ptsanscaption/v10/0FlJVP6Hrxmt7-fsUFhlFXNIlpcSwSrUSw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `PT Sans Narrow` extends GoogleFont {
    override lazy val family: String = "PT Sans Narrow"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptsansnarrow/v8/BngRUXNadjH0qYEzV7ab-oWlsYCB.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ptsansnarrow/v8/BngSUXNadjH0qYEzV7ab-oWlsbg95DiC.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `PT Serif` extends GoogleFont {
    override lazy val family: String = "PT Serif"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptserif/v9/EJRVQgYoZZY2vCFuvDFR.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ptserif/v9/EJRTQgYoZZY2vCFuvAFTzro.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ptserif/v9/EJRSQgYoZZY2vCFuvAnt65qV.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ptserif/v9/EJRQQgYoZZY2vCFuvAFT9gaQVy4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `PT Serif Caption` extends GoogleFont {
    override lazy val family: String = "PT Serif Caption"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptserifcaption/v9/ieVl2ZhbGCW-JoW6S34pSDpqYKU059U.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ptserifcaption/v9/ieVj2ZhbGCW-JoW6S34pSDpqYKU019e7CA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Pacifico` extends GoogleFont {
    override lazy val family: String = "Pacifico"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pacifico/v12/FwZY7-Qmy14u9lezJ96A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Padauk` extends GoogleFont {
    override lazy val family: String = "Padauk"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `myanmar`: GoogleFontSubset = new GoogleFontSubset("myanmar")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`myanmar`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/padauk/v4/RrQRboJg-id7OnbBaw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/padauk/v4/RrQSboJg-id7Onb512DE1A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Palanquin` extends GoogleFont {
    override lazy val family: String = "Palanquin"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/palanquin/v3/9XUhlJ90n1fBFg7ceXwUEltI.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/palanquin/v3/9XUilJ90n1fBFg7ceXwUvnpoxA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/palanquin/v3/9XUilJ90n1fBFg7ceXwU2nloxA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/palanquin/v3/9XUnlJ90n1fBFg7ceXwsdg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/palanquin/v3/9XUilJ90n1fBFg7ceXwUgnhoxA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/palanquin/v3/9XUilJ90n1fBFg7ceXwUrn9oxA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/palanquin/v3/9XUilJ90n1fBFg7ceXwUyn5oxA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Palanquin Dark` extends GoogleFont {
    override lazy val family: String = "Palanquin Dark"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/palanquindark/v3/xn75YHgl1nqmANMB-26xC7yuF_6O.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/palanquindark/v3/xn76YHgl1nqmANMB-26xC7yuF8Z6ZW41.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/palanquindark/v3/xn76YHgl1nqmANMB-26xC7yuF8ZWYm41.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/palanquindark/v3/xn76YHgl1nqmANMB-26xC7yuF8YyY241.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`)
  }
  object `Pangolin` extends GoogleFont {
    override lazy val family: String = "Pangolin"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pangolin/v3/cY9GfjGcW0FPpi-tWPfK.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Paprika` extends GoogleFont {
    override lazy val family: String = "Paprika"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/paprika/v5/8QIJdijZitv49rDfuIg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Parisienne` extends GoogleFont {
    override lazy val family: String = "Parisienne"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/parisienne/v5/E21i_d3kivvAkxhLEVZpcy8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Passero One` extends GoogleFont {
    override lazy val family: String = "Passero One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/passeroone/v9/JTUTjIko8DOq5FeaeEAjgE5B.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Passion One` extends GoogleFont {
    override lazy val family: String = "Passion One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/passionone/v8/PbynFmL8HhTPqbjUzux3JHuW.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/passionone/v8/Pby6FmL8HhTPqbjUzux3JEMq037o.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/passionone/v8/Pby6FmL8HhTPqbjUzux3JEMS0X7o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `900`)
  }
  object `Pathway Gothic One` extends GoogleFont {
    override lazy val family: String = "Pathway Gothic One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pathwaygothicone/v6/MwQrbgD32-KAvjkYGNUUxAtW7pEBwx-dTA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Patrick Hand` extends GoogleFont {
    override lazy val family: String = "Patrick Hand"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/patrickhand/v11/LDI1apSQOAYtSuYWp8ZhfYeMWQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Patrick Hand SC` extends GoogleFont {
    override lazy val family: String = "Patrick Hand SC"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/patrickhandsc/v5/0nkwC9f7MfsBiWcLtY65AWDK873ViQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pattaya` extends GoogleFont {
    override lazy val family: String = "Pattaya"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pattaya/v2/ea8ZadcqV_zkHY-XNdA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Patua One` extends GoogleFont {
    override lazy val family: String = "Patua One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/patuaone/v7/ZXuke1cDvLCKLDcimxBI5A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pavanam` extends GoogleFont {
    override lazy val family: String = "Pavanam"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`tamil`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pavanam/v2/BXRrvF_aiezLh0xPDOs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Paytone One` extends GoogleFont {
    override lazy val family: String = "Paytone One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/paytoneone/v10/0nksC9P7MfYHj2oFtYm2CiTq.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Peddana` extends GoogleFont {
    override lazy val family: String = "Peddana"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/peddana/v5/aFTU7PBhaX89UcKWhh0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Peralta` extends GoogleFont {
    override lazy val family: String = "Peralta"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/peralta/v5/hYkJPu0-RP_9d3kRGxA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Permanent Marker` extends GoogleFont {
    override lazy val family: String = "Permanent Marker"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/permanentmarker/v7/Fh4uPib9Iyv2ucM6pGQMWimMp004Hao.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Petit Formal Script` extends GoogleFont {
    override lazy val family: String = "Petit Formal Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/petitformalscript/v5/B50TF6xQr2TXJBnGOFME6u5OR83oRP5qoHk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Petrona` extends GoogleFont {
    override lazy val family: String = "Petrona"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/petrona/v6/mtG64_NXL7bZo9XXsXU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Philosopher` extends GoogleFont {
    override lazy val family: String = "Philosopher"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/philosopher/v9/vEFV2_5QCwIS4_Dhez5jcVBp.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/philosopher/v9/vEFX2_5QCwIS4_Dhez5jcWBrT0g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/philosopher/v9/vEFI2_5QCwIS4_Dhez5jcWjVamgc.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/philosopher/v9/vEFK2_5QCwIS4_Dhez5jcWBrd_QZ8tI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Piedra` extends GoogleFont {
    override lazy val family: String = "Piedra"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/piedra/v6/ke8kOg8aN0Bn7hTunA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pinyon Script` extends GoogleFont {
    override lazy val family: String = "Pinyon Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pinyonscript/v7/6xKpdSJbL9-e9LuoeQiDRQR8aOI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pirata One` extends GoogleFont {
    override lazy val family: String = "Pirata One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pirataone/v5/I_urMpiDvgLdLh0fAtoftig.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Plaster` extends GoogleFont {
    override lazy val family: String = "Plaster"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/plaster/v9/DdTm79QatW80eRh4Ei4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Play` extends GoogleFont {
    override lazy val family: String = "Play"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/play/v9/6aez4K2oVqwIjtI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/play/v9/6ae84K2oVqwItm4TOpc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Playball` extends GoogleFont {
    override lazy val family: String = "Playball"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/playball/v7/TK3gWksYAxQ7jbsKcj8D.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Playfair Display` extends GoogleFont {
    override lazy val family: String = "Playfair Display"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/playfairdisplay/v13/nuFiD-vYSZviVYUb_rj3ij__anPXPTs.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/playfairdisplay/v13/nuFkD-vYSZviVYUb_rj3ij__anPXDTnYhQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/playfairdisplay/v13/nuFlD-vYSZviVYUb_rj3ij__anPXBYf9pWk.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/playfairdisplay/v13/nuFnD-vYSZviVYUb_rj3ij__anPXDTngOWwe4w.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/playfairdisplay/v13/nuFlD-vYSZviVYUb_rj3ij__anPXBb__pWk.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/playfairdisplay/v13/nuFnD-vYSZviVYUb_rj3ij__anPXDTngAW4e4w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Playfair Display SC` extends GoogleFont {
    override lazy val family: String = "Playfair Display SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/playfairdisplaysc/v7/ke85OhoaMkR6-hSn7kbHVoFf7ZfgMPr_pb4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/playfairdisplaysc/v7/ke87OhoaMkR6-hSn7kbHVoFf7ZfgMPr_lbwMFQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/playfairdisplaysc/v7/ke80OhoaMkR6-hSn7kbHVoFf7ZfgMPr_nQIpNcs.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/playfairdisplaysc/v7/ke82OhoaMkR6-hSn7kbHVoFf7ZfgMPr_lbw0qc4XKw.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/playfairdisplaysc/v7/ke80OhoaMkR6-hSn7kbHVoFf7ZfgMPr_nTorNcs.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/playfairdisplaysc/v7/ke82OhoaMkR6-hSn7kbHVoFf7ZfgMPr_lbw0kcwXKw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Podkova` extends GoogleFont {
    override lazy val family: String = "Podkova"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/podkova/v11/K2FxfZ1EmftJSV9VWJ4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/podkova/v11/K2F0fZ1EmftJSV9VYGrQAoo.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/podkova/v11/K2F0fZ1EmftJSV9VYEbXAoo.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/podkova/v11/K2F0fZ1EmftJSV9VYCLWAoo.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/podkova/v11/K2F0fZ1EmftJSV9VYD7VAoo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `800`)
  }
  object `Poiret One` extends GoogleFont {
    override lazy val family: String = "Poiret One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/poiretone/v5/UqyVK80NJXN4zfRgbdfbk5k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Poller One` extends GoogleFont {
    override lazy val family: String = "Poller One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pollerone/v7/ahccv82n0TN3gia5E4Bud-k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Poly` extends GoogleFont {
    override lazy val family: String = "Poly"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/poly/v8/MQpb-W6wKNitRLA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/poly/v8/MQpV-W6wKNitdLKKrw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Pompiere` extends GoogleFont {
    override lazy val family: String = "Pompiere"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pompiere/v7/VEMyRoxis5Dwuyeov6Wt.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pontano Sans` extends GoogleFont {
    override lazy val family: String = "Pontano Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pontanosans/v5/qFdD35GdgYR8EzR6oBLDHa3qwg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Poor Story` extends GoogleFont {
    override lazy val family: String = "Poor Story"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/poorstory/v2/jizfREFUsnUct9P6cDfd4Ok.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Poppins` extends GoogleFont {
    override lazy val family: String = "Poppins"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/poppins/v5/pxiGyp8kv8JHgFVrLPTedw.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/poppins/v5/pxiAyp8kv8JHgFVrJJLmE3tF.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/poppins/v5/pxiByp8kv8JHgFVrLFj_V1s.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/poppins/v5/pxiDyp8kv8JHgFVrJJLmv1plEA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/poppins/v5/pxiByp8kv8JHgFVrLDz8V1s.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/poppins/v5/pxiDyp8kv8JHgFVrJJLm21llEA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/poppins/v5/pxiEyp8kv8JHgFVrFJA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/poppins/v5/pxiGyp8kv8JHgFVrJJLedw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/poppins/v5/pxiByp8kv8JHgFVrLGT9V1s.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/poppins/v5/pxiDyp8kv8JHgFVrJJLmg1hlEA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/poppins/v5/pxiByp8kv8JHgFVrLEj6V1s.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/poppins/v5/pxiDyp8kv8JHgFVrJJLmr19lEA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/poppins/v5/pxiByp8kv8JHgFVrLCz7V1s.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/poppins/v5/pxiDyp8kv8JHgFVrJJLmy15lEA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/poppins/v5/pxiByp8kv8JHgFVrLDD4V1s.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/poppins/v5/pxiDyp8kv8JHgFVrJJLm111lEA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/poppins/v5/pxiByp8kv8JHgFVrLBT5V1s.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/poppins/v5/pxiDyp8kv8JHgFVrJJLm81xlEA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Port Lligat Sans` extends GoogleFont {
    override lazy val family: String = "Port Lligat Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/portlligatsans/v6/kmKmZrYrGBbdN1aV7Vokow6Lw4s4l7M.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Port Lligat Slab` extends GoogleFont {
    override lazy val family: String = "Port Lligat Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/portlligatslab/v6/LDIpaoiQNgArA8kR7ulhZ8P_NYOss7o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pragati Narrow` extends GoogleFont {
    override lazy val family: String = "Pragati Narrow"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pragatinarrow/v3/vm8vdRf0T0bS1ffgsPB7WZ-mD17_.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/pragatinarrow/v3/vm8sdRf0T0bS1ffgsPB7WZ-mD2ZD5fd_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Prata` extends GoogleFont {
    override lazy val family: String = "Prata"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/prata/v8/6xKhdSpbNNCT-vWI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Preahvihear` extends GoogleFont {
    override lazy val family: String = "Preahvihear"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/preahvihear/v9/6NUS8F-dNQeEYhzj7uluxswE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Press Start 2P` extends GoogleFont {
    override lazy val family: String = "Press Start 2P"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pressstart2p/v6/e3t4euO8T-267oIAQAu6jDQyK0nS.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pridi` extends GoogleFont {
    override lazy val family: String = "Pridi"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/pridi/v3/2sDdZG5JnZLfkc1SiE0j.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/pridi/v3/2sDdZG5JnZLfkc02i00j.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pridi/v3/2sDQZG5JnZLfkfWa.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/pridi/v3/2sDdZG5JnZLfkc1uik0j.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/pridi/v3/2sDdZG5JnZLfkc1CjU0j.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/pridi/v3/2sDdZG5JnZLfkc0mjE0j.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Princess Sofia` extends GoogleFont {
    override lazy val family: String = "Princess Sofia"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/princesssofia/v5/qWczB6yguIb8DZ_GXZst16n7GRz7.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Prociono` extends GoogleFont {
    override lazy val family: String = "Prociono"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/prociono/v7/r05YGLlR-KxAf9GGO8up.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Prompt` extends GoogleFont {
    override lazy val family: String = "Prompt"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/prompt/v2/-W_9XJnvUD7dzB2CA9oY.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/prompt/v2/-W_7XJnvUD7dzB2KZeJ8TkM.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/prompt/v2/-W_8XJnvUD7dzB2Cr_s4bg.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/prompt/v2/-W_6XJnvUD7dzB2KZeLQb2Mr.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/prompt/v2/-W_8XJnvUD7dzB2Cy_g4bg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/prompt/v2/-W_6XJnvUD7dzB2KZeK0bGMr.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/prompt/v2/-W__XJnvUD7dzB26Zw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/prompt/v2/-W_9XJnvUD7dzB2KZdoY.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/prompt/v2/-W_8XJnvUD7dzB2Ck_k4bg.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/prompt/v2/-W_6XJnvUD7dzB2KZeLsbWMr.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/prompt/v2/-W_8XJnvUD7dzB2Cv_44bg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/prompt/v2/-W_6XJnvUD7dzB2KZeLAamMr.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/prompt/v2/-W_8XJnvUD7dzB2C2_84bg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/prompt/v2/-W_6XJnvUD7dzB2KZeKka2Mr.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/prompt/v2/-W_8XJnvUD7dzB2Cx_w4bg.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/prompt/v2/-W_6XJnvUD7dzB2KZeK4aGMr.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/prompt/v2/-W_8XJnvUD7dzB2C4_04bg.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/prompt/v2/-W_6XJnvUD7dzB2KZeKcaWMr.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Prosto One` extends GoogleFont {
    override lazy val family: String = "Prosto One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/prostoone/v6/OpNJno4VhNfK-RgpwWWxpio.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Proza Libre` extends GoogleFont {
    override lazy val family: String = "Proza Libre"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/prozalibre/v2/LYjGdGHgj0k1DIQRyUEyyHov.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/prozalibre/v2/LYjEdGHgj0k1DIQRyUEyyEotdN8.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/prozalibre/v2/LYjbdGHgj0k1DIQRyUEyyELbV__f.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/prozalibre/v2/LYjZdGHgj0k1DIQRyUEyyEotTCvceJQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/prozalibre/v2/LYjbdGHgj0k1DIQRyUEyyEL3UP_f.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/prozalibre/v2/LYjZdGHgj0k1DIQRyUEyyEotTAfbeJQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/prozalibre/v2/LYjbdGHgj0k1DIQRyUEyyEKTUf_f.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/prozalibre/v2/LYjZdGHgj0k1DIQRyUEyyEotTGPaeJQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/prozalibre/v2/LYjbdGHgj0k1DIQRyUEyyEKPUv_f.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/prozalibre/v2/LYjZdGHgj0k1DIQRyUEyyEotTH_ZeJQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Puritan` extends GoogleFont {
    override lazy val family: String = "Puritan"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/puritan/v9/845YNMgkAJ2VTtIo9Jo.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/puritan/v9/845aNMgkAJ2VTtIoxJj6QQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/puritan/v9/845dNMgkAJ2VTtIozCbfYd4.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/puritan/v9/845fNMgkAJ2VTtIoxJjC_dup_w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Purple Purse` extends GoogleFont {
    override lazy val family: String = "Purple Purse"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/purplepurse/v6/qWctB66gv53iAp-Vfs4My6qyeA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Quando` extends GoogleFont {
    override lazy val family: String = "Quando"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quando/v6/xMQVuFNaVa6YuW0pCw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Quantico` extends GoogleFont {
    override lazy val family: String = "Quantico"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quantico/v6/rax-HiSdp9cPL3KIF4xs.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/quantico/v6/rax4HiSdp9cPL3KIF7xuJDg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/quantico/v6/rax5HiSdp9cPL3KIF7TQARha.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/quantico/v6/rax7HiSdp9cPL3KIF7xuHIRfu0o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Quattrocento` extends GoogleFont {
    override lazy val family: String = "Quattrocento"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quattrocento/v9/OZpEg_xvsDZQL_LKIF7q4jPHxA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/quattrocento/v9/OZpbg_xvsDZQL_LKIF7q4jP_eE3fdw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Quattrocento Sans` extends GoogleFont {
    override lazy val family: String = "Quattrocento Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quattrocentosans/v10/va9c4lja2NVIDdIAAoMR5MfuElaRB3zO.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/quattrocentosans/v10/va9a4lja2NVIDdIAAoMR5MfuElaRB0zMt0o.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/quattrocentosans/v10/va9Z4lja2NVIDdIAAoMR5MfuElaRB0RykmrW.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/quattrocentosans/v10/va9X4lja2NVIDdIAAoMR5MfuElaRB0zMj_bTPXk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Questrial` extends GoogleFont {
    override lazy val family: String = "Questrial"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/questrial/v7/QdVUSTchPBm7nuUeVf7EuQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Quicksand` extends GoogleFont {
    override lazy val family: String = "Quicksand"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/quicksand/v7/6xKodSZaM9iE8KbpRA_pgHYoSA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quicksand/v7/6xKtdSZaM9iE8KbpRA_RLA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/quicksand/v7/6xKodSZaM9iE8KbpRA_p2HcoSA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/quicksand/v7/6xKodSZaM9iE8KbpRA_pkHEoSA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `700`)
  }
  object `Quintessential` extends GoogleFont {
    override lazy val family: String = "Quintessential"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quintessential/v5/fdNn9sOGq31Yjnh3qWU14DdtjY5w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Qwigley` extends GoogleFont {
    override lazy val family: String = "Qwigley"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/qwigley/v7/1cXzaU3UGJb5tGoCuVw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Racing Sans One` extends GoogleFont {
    override lazy val family: String = "Racing Sans One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/racingsansone/v5/sykr-yRtm7EvTrXNxkv5jfKKyDCwLw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Radley` extends GoogleFont {
    override lazy val family: String = "Radley"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/radley/v12/LYjDdGzinEIjCN19oA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/radley/v12/LYjBdGzinEIjCN1NogNA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Rajdhani` extends GoogleFont {
    override lazy val family: String = "Rajdhani"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/rajdhani/v7/LDI2apCSOBg7S-QT7pasEcOs.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rajdhani/v7/LDIxapCSOBg7S-QT7q4A.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/rajdhani/v7/LDI2apCSOBg7S-QT7pb0EMOs.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/rajdhani/v7/LDI2apCSOBg7S-QT7pbYF8Os.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rajdhani/v7/LDI2apCSOBg7S-QT7pa8FsOs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Rakkas` extends GoogleFont {
    override lazy val family: String = "Rakkas"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rakkas/v4/Qw3cZQlNHiblL3j_lg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Raleway` extends GoogleFont {
    override lazy val family: String = "Raleway"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/raleway/v12/1Ptsg8zYS_SKggPNwE4ISg.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/raleway/v12/1Ptqg8zYS_SKggPNyCgwLoFv.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/raleway/v12/1Ptrg8zYS_SKggPNwOIpaqE.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/raleway/v12/1Ptpg8zYS_SKggPNyCgwgqBPBQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/raleway/v12/1Ptrg8zYS_SKggPNwIYqaqE.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/raleway/v12/1Ptpg8zYS_SKggPNyCgw5qNPBQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/raleway/v12/1Ptug8zYS_SKggPN-Co.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/raleway/v12/1Ptsg8zYS_SKggPNyCgISg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/raleway/v12/1Ptrg8zYS_SKggPNwN4raqE.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/raleway/v12/1Ptpg8zYS_SKggPNyCgwvqJPBQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/raleway/v12/1Ptrg8zYS_SKggPNwPIsaqE.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/raleway/v12/1Ptpg8zYS_SKggPNyCgwkqVPBQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/raleway/v12/1Ptrg8zYS_SKggPNwJYtaqE.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/raleway/v12/1Ptpg8zYS_SKggPNyCgw9qRPBQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/raleway/v12/1Ptrg8zYS_SKggPNwIouaqE.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/raleway/v12/1Ptpg8zYS_SKggPNyCgw6qdPBQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/raleway/v12/1Ptrg8zYS_SKggPNwK4vaqE.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/raleway/v12/1Ptpg8zYS_SKggPNyCgwzqZPBQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Raleway Dots` extends GoogleFont {
    override lazy val family: String = "Raleway Dots"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ralewaydots/v5/6NUR8FifJg6AfQvzpshgwJ8kyQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ramabhadra` extends GoogleFont {
    override lazy val family: String = "Ramabhadra"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ramabhadra/v6/EYq2maBOwqRW9P1SQ83LehM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ramaraja` extends GoogleFont {
    override lazy val family: String = "Ramaraja"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ramaraja/v2/SlGTmQearpYAYG1CABIk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rambla` extends GoogleFont {
    override lazy val family: String = "Rambla"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rambla/v5/snfrs0ip98hx6mr0Iw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/rambla/v5/snfps0ip98hx6mrEIbgK.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rambla/v5/snfos0ip98hx6mrMn50qPg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/rambla/v5/snfus0ip98hx6mrEIYC2O_l8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Rammetto One` extends GoogleFont {
    override lazy val family: String = "Rammetto One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rammettoone/v6/LhWiMV3HOfMbMetJG3lQDpp9Mg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ranchers` extends GoogleFont {
    override lazy val family: String = "Ranchers"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ranchers/v5/zrfm0H3Lx-P2Xvs2AoDY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rancho` extends GoogleFont {
    override lazy val family: String = "Rancho"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rancho/v8/46kulbzmXjLaqZRlbQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ranga` extends GoogleFont {
    override lazy val family: String = "Ranga"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ranga/v3/C8ct4cYisGb28p6C.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ranga/v3/C8cg4cYisGb28qY-AxgR.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Rasa` extends GoogleFont {
    override lazy val family: String = "Rasa"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `gujarati`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/rasa/v3/xn7gYHIn1mWmdg52sgA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rasa/v3/xn7vYHIn1mWmTqI.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/rasa/v3/xn7gYHIn1mWmdlZ3sgA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/rasa/v3/xn7gYHIn1mWmdnpwsgA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rasa/v3/xn7gYHIn1mWmdh5xsgA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Rationale` extends GoogleFont {
    override lazy val family: String = "Rationale"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rationale/v8/9XUnlJ92n0_JFxHIfHcsdg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ravi Prakash` extends GoogleFont {
    override lazy val family: String = "Ravi Prakash"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/raviprakash/v4/gokpH6fsDkVrF9Bv9X8SOAKHmA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Redressed` extends GoogleFont {
    override lazy val family: String = "Redressed"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/redressed/v8/x3dickHUbrmJ7wMy9MsBfA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Reem Kufi` extends GoogleFont {
    override lazy val family: String = "Reem Kufi"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/reemkufi/v4/2sDcZGJLip7W2J7v7wQDbw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Reenie Beanie` extends GoogleFont {
    override lazy val family: String = "Reenie Beanie"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/reeniebeanie/v8/z7NSdR76eDkaJKZJFkkjuvWxbP0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Revalia` extends GoogleFont {
    override lazy val family: String = "Revalia"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/revalia/v5/WwkexPimBE2-4ZPEeVo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rhodium Libre` extends GoogleFont {
    override lazy val family: String = "Rhodium Libre"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rhodiumlibre/v2/1q2AY5adA0tn_ukeHcQHqpx6pEQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ribeye` extends GoogleFont {
    override lazy val family: String = "Ribeye"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ribeye/v6/L0x8DFMxk1MP9R3RvA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ribeye Marrow` extends GoogleFont {
    override lazy val family: String = "Ribeye Marrow"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ribeyemarrow/v7/GFDsWApshnqMRO2JdtRZ2d0vEAw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Righteous` extends GoogleFont {
    override lazy val family: String = "Righteous"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/righteous/v6/1cXxaUPXBpj2rGoU7C9mjw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Risque` extends GoogleFont {
    override lazy val family: String = "Risque"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/risque/v5/VdGfAZUfHosahXxoCQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Roboto` extends GoogleFont {
    override lazy val family: String = "Roboto"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/roboto/v18/KFOkCnqEu92Fr1MmgWxP.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/roboto/v18/KFOiCnqEu92Fr1Mu51QrIzc.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/roboto/v18/KFOlCnqEu92Fr1MmSU5vAw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/roboto/v18/KFOjCnqEu92Fr1Mu51TjARc9.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/roboto/v18/KFOmCnqEu92Fr1Me5Q.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/roboto/v18/KFOkCnqEu92Fr1Mu52xP.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/roboto/v18/KFOlCnqEu92Fr1MmEU9vAw.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/roboto/v18/KFOjCnqEu92Fr1Mu51S7ABc9.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/roboto/v18/KFOlCnqEu92Fr1MmWUlvAw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/roboto/v18/KFOjCnqEu92Fr1Mu51TzBhc9.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/roboto/v18/KFOlCnqEu92Fr1MmYUtvAw.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/roboto/v18/KFOjCnqEu92Fr1Mu51TLBBc9.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Roboto Condensed` extends GoogleFont {
    override lazy val family: String = "Roboto Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/robotocondensed/v16/ieVi2ZhZI2eCN5jzbjEETS9weq8-33mZKCM.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/robotocondensed/v16/ieVg2ZhZI2eCN5jzbjEETS9weq8-19eDpCEYag.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/robotocondensed/v16/ieVl2ZhZI2eCN5jzbjEETS9weq8-59U.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/robotocondensed/v16/ieVj2ZhZI2eCN5jzbjEETS9weq8-19e7CA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/robotocondensed/v16/ieVi2ZhZI2eCN5jzbjEETS9weq8-32meKCM.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/robotocondensed/v16/ieVg2ZhZI2eCN5jzbjEETS9weq8-19eDtCYYag.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`)
  }
  object `Roboto Mono` extends GoogleFont {
    override lazy val family: String = "Roboto Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/robotomono/v5/L0x7DF4xlVMF-BfR8bXMIjAoq3o.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/robotomono/v5/L0xlDF4xlVMF-BfR8bXMIjhOkx6WXw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/robotomono/v5/L0xkDF4xlVMF-BfR8bXMIjDgiVq2.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/robotomono/v5/L0xmDF4xlVMF-BfR8bXMIjhOk9a0f7o.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/robotomono/v5/L0x5DF4xlVMF-BfR8bXMIghM.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/robotomono/v5/L0x7DF4xlVMF-BfR8bXMIjhOq3o.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/robotomono/v5/L0xkDF4xlVMF-BfR8bXMIjC4iFq2.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/robotomono/v5/L0xmDF4xlVMF-BfR8bXMIjhOk461f7o.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/robotomono/v5/L0xkDF4xlVMF-BfR8bXMIjDwjlq2.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/robotomono/v5/L0xmDF4xlVMF-BfR8bXMIjhOk8azf7o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`)
  }
  object `Roboto Slab` extends GoogleFont {
    override lazy val family: String = "Roboto Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/robotoslab/v7/BngOUXZYTXPIvIBgJJSb6u-u5qA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/robotoslab/v7/BngRUXZYTXPIvIBgJJSb6u9mxICB.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/robotoslab/v7/BngMUXZYTXPIvIBgJJSb6tfK.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/robotoslab/v7/BngRUXZYTXPIvIBgJJSb6u92w4CB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `700`)
  }
  object `Rochester` extends GoogleFont {
    override lazy val family: String = "Rochester"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rochester/v8/6ae-4KCqVa4Zy6Fif-Uy3w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rock Salt` extends GoogleFont {
    override lazy val family: String = "Rock Salt"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rocksalt/v8/MwQ0bhv11fWD6QsAVOZbsA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rokkitt` extends GoogleFont {
    override lazy val family: String = "Rokkitt"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/rokkitt/v12/qFdG35qfgYFjGy5hmCWCcw.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/rokkitt/v12/qFdB35qfgYFjGy5hmImjU94.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/rokkitt/v12/qFdB35qfgYFjGy5hmO2gU94.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rokkitt/v12/qFdE35qfgYFjGy5hoEE.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/rokkitt/v12/qFdB35qfgYFjGy5hmLWhU94.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/rokkitt/v12/qFdB35qfgYFjGy5hmJmmU94.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rokkitt/v12/qFdB35qfgYFjGy5hmP2nU94.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/rokkitt/v12/qFdB35qfgYFjGy5hmOGkU94.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/rokkitt/v12/qFdB35qfgYFjGy5hmMWlU94.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Romanesco` extends GoogleFont {
    override lazy val family: String = "Romanesco"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/romanesco/v6/w8gYH2ozQOY7_r_J7mSn3A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ropa Sans` extends GoogleFont {
    override lazy val family: String = "Ropa Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ropasans/v7/EYqxmaNOzLlWtsZSScyKWg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ropasans/v7/EYq3maNOzLlWtsZSScy6WDNs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Rosario` extends GoogleFont {
    override lazy val family: String = "Rosario"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rosario/v12/xfux0WDhWW_fOEoY6FQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/rosario/v12/xfuz0WDhWW_fOEoY2Fb9yA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rosario/v12/xfu00WDhWW_fOEoY0OjY6AY.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/rosario/v12/xfu20WDhWW_fOEoY2FbFdAPivA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Rosarivo` extends GoogleFont {
    override lazy val family: String = "Rosarivo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rosarivo/v5/PlI-Fl2lO6N9f8HaNAeC.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/rosarivo/v5/PlI4Fl2lO6N9f8HaNDeA0Hw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Rouge Script` extends GoogleFont {
    override lazy val family: String = "Rouge Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rougescript/v6/LYjFdGbiklMoCIQOw1Ep3S4PVA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rozha One` extends GoogleFont {
    override lazy val family: String = "Rozha One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rozhaone/v4/AlZy_zVFtYP12Zncg2khdQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rubik` extends GoogleFont {
    override lazy val family: String = "Rubik"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `hebrew`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/rubik/v7/iJWHBXyIfDnIV7Fqj1ma.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/rubik/v7/iJWBBXyIfDnIV7nEldWY8WU.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rubik/v7/iJWKBXyIfDnIV4nG.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/rubik/v7/iJWEBXyIfDnIV7nErXk.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/rubik/v7/iJWHBXyIfDnIV7Eyjlma.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/rubik/v7/iJWBBXyIfDnIV7nElY2Z8WU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rubik/v7/iJWHBXyIfDnIV7F6iFma.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/rubik/v7/iJWBBXyIfDnIV7nElcWf8WU.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/rubik/v7/iJWHBXyIfDnIV7FCilma.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/rubik/v7/iJWBBXyIfDnIV7nElf2d8WU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Rubik Mono One` extends GoogleFont {
    override lazy val family: String = "Rubik Mono One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rubikmonoone/v6/UqyJK8kPP3hjw6ANTdfRk9YSN-8w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ruda` extends GoogleFont {
    override lazy val family: String = "Ruda"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ruda/v9/k3kfo8YQJOpFmn8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ruda/v9/k3kQo8YQJOpFosM4Td4.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/ruda/v9/k3kQo8YQJOpFovs6Td4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `900`)
  }
  object `Rufina` extends GoogleFont {
    override lazy val family: String = "Rufina"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rufina/v5/Yq6V-LyURyLy-aKyow.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rufina/v5/Yq6W-LyURyLy-aKKHztAvA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Ruge Boogie` extends GoogleFont {
    override lazy val family: String = "Ruge Boogie"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rugeboogie/v8/JIA3UVFwbHRF_GIWSMhKNROi.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ruluko` extends GoogleFont {
    override lazy val family: String = "Ruluko"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ruluko/v5/xMQVuFNZVaODtm0pCw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rum Raisin` extends GoogleFont {
    override lazy val family: String = "Rum Raisin"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rumraisin/v5/nwpRtKu3Ih8D5avB4h2uJ38.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ruslan Display` extends GoogleFont {
    override lazy val family: String = "Ruslan Display"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ruslandisplay/v8/Gw6jwczl81XcIZuckK_e3Upfdzxr.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Russo One` extends GoogleFont {
    override lazy val family: String = "Russo One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/russoone/v6/Z9XUDmZRWg6M1LvRYsH-yA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ruthie` extends GoogleFont {
    override lazy val family: String = "Ruthie"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ruthie/v7/gokvH63sGkdqXuU9lA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rye` extends GoogleFont {
    override lazy val family: String = "Rye"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rye/v5/r05XGLJT86YDFg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sacramento` extends GoogleFont {
    override lazy val family: String = "Sacramento"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sacramento/v5/buEzpo6gcdjy0EiZMBUG0Co.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sahitya` extends GoogleFont {
    override lazy val family: String = "Sahitya"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sahitya/v2/6qLAKZkOuhnuqlJAaSc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sahitya/v2/6qLFKZkOuhnuqlJAUZsqGyQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Sail` extends GoogleFont {
    override lazy val family: String = "Sail"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sail/v8/DPEjYwiBxwYJFBQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Saira` extends GoogleFont {
    override lazy val family: String = "Saira"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/saira/v2/mem-Ya2wxmKQyNFETZY.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/saira/v2/mem9Ya2wxmKQyNHobLYV.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/saira/v2/mem9Ya2wxmKQyNGMb7YV.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/saira/v2/memwYa2wxmKQyOkg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/saira/v2/mem9Ya2wxmKQyNHUbrYV.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/saira/v2/mem9Ya2wxmKQyNH4abYV.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/saira/v2/mem9Ya2wxmKQyNGcaLYV.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/saira/v2/mem9Ya2wxmKQyNGAa7YV.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/saira/v2/mem9Ya2wxmKQyNGkarYV.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Saira Condensed` extends GoogleFont {
    override lazy val family: String = "Saira Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/sairacondensed/v3/EJRMQgErUN8XuHNEtX81i9TmEkrnwetA.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sairacondensed/v3/EJRLQgErUN8XuHNEtX81i9TmEkrnbcpg8A.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sairacondensed/v3/EJRLQgErUN8XuHNEtX81i9TmEkrnCclg8A.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sairacondensed/v3/EJROQgErUN8XuHNEtX81i9TmEkrfpQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sairacondensed/v3/EJRLQgErUN8XuHNEtX81i9TmEkrnUchg8A.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sairacondensed/v3/EJRLQgErUN8XuHNEtX81i9TmEkrnfc9g8A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sairacondensed/v3/EJRLQgErUN8XuHNEtX81i9TmEkrnGc5g8A.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sairacondensed/v3/EJRLQgErUN8XuHNEtX81i9TmEkrnBc1g8A.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sairacondensed/v3/EJRLQgErUN8XuHNEtX81i9TmEkrnIcxg8A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Saira Extra Condensed` extends GoogleFont {
    override lazy val family: String = "Saira Extra Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/sairaextracondensed/v3/-nFsOHYr-vcC7h8MklGBkrvmUG9rbpkisrTri0jx.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sairaextracondensed/v3/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrJ2nR3A.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sairaextracondensed/v3/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrQ2rR3A.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sairaextracondensed/v3/-nFiOHYr-vcC7h8MklGBkrvmUG9rbpkisrTT7w.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sairaextracondensed/v3/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrG2vR3A.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sairaextracondensed/v3/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrN2zR3A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sairaextracondensed/v3/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrU23R3A.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sairaextracondensed/v3/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrT27R3A.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sairaextracondensed/v3/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTra2_R3A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Saira Semi Condensed` extends GoogleFont {
    override lazy val family: String = "Saira Semi Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/sairasemicondensed/v3/U9MN6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXdvaOM.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sairasemicondensed/v3/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXfDScMW.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sairasemicondensed/v3/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXenSsMW.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sairasemicondensed/v3/U9MD6c-2-nnJkHxyCjRcnMHcWVWV1cWRRU8L.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sairasemicondensed/v3/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXf_S8MW.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sairasemicondensed/v3/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXfTTMMW.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sairasemicondensed/v3/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXe3TcMW.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sairasemicondensed/v3/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXerTsMW.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sairasemicondensed/v3/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXePT8MW.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Salsa` extends GoogleFont {
    override lazy val family: String = "Salsa"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/salsa/v7/gNMKW3FiRpKj-imY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sanchez` extends GoogleFont {
    override lazy val family: String = "Sanchez"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sanchez/v5/Ycm2sZJORluHnXbITm4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/sanchez/v5/Ycm0sZJORluHnXbIfmxR-A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Sancreek` extends GoogleFont {
    override lazy val family: String = "Sancreek"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sancreek/v8/pxiHypAnsdxUm159X7D-.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sansita` extends GoogleFont {
    override lazy val family: String = "Sansita"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sansita/v2/QldONTRRphEb_-V7HBk.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/sansita/v2/QldMNTRRphEb_-V7LBuxSQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sansita/v2/QldLNTRRphEb_-V7JKWUaXk.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/sansita/v2/QldJNTRRphEb_-V7LBuJ9Xx-xg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sansita/v2/QldLNTRRphEb_-V7JLmXaXk.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/sansita/v2/QldJNTRRphEb_-V7LBuJ6X9-xg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sansita/v2/QldLNTRRphEb_-V7JJ2WaXk.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/sansita/v2/QldJNTRRphEb_-V7LBuJzX5-xg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Sarala` extends GoogleFont {
    override lazy val family: String = "Sarala"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sarala/v2/uK_y4riEZv4o1w9RCg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sarala/v2/uK_x4riEZv4o1w9ptjI3Og.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Sarina` extends GoogleFont {
    override lazy val family: String = "Sarina"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sarina/v6/-F6wfjF3ITQwasLhLg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sarpanch` extends GoogleFont {
    override lazy val family: String = "Sarpanch"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sarpanch/v3/hESy6Xt4NCpRuk6Pzh2A.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sarpanch/v3/hES16Xt4NCpRuk6PziV0ba7f.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sarpanch/v3/hES16Xt4NCpRuk6PziVYaq7f.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sarpanch/v3/hES16Xt4NCpRuk6PziU8a67f.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sarpanch/v3/hES16Xt4NCpRuk6PziUgaK7f.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sarpanch/v3/hES16Xt4NCpRuk6PziUEaa7f.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Satisfy` extends GoogleFont {
    override lazy val family: String = "Satisfy"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/satisfy/v8/rP2Hp2yn6lkG50LoOZQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Scada` extends GoogleFont {
    override lazy val family: String = "Scada"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/scada/v6/RLpxK5Pv5qumeWJo.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/scada/v6/RLp_K5Pv5qumeVJqzTE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/scada/v6/RLp8K5Pv5qumeVrU6BEg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/scada/v6/RLp6K5Pv5qumeVJq9Y0lT1M.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Scheherazade` extends GoogleFont {
    override lazy val family: String = "Scheherazade"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `arabic`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/scheherazade/v14/YA9Ur0yF4ETZN60keViq1kQgtw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/scheherazade/v14/YA9Lr0yF4ETZN60keViq1kQYC7yMjg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Schoolbell` extends GoogleFont {
    override lazy val family: String = "Schoolbell"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/schoolbell/v8/92zQtBZWOrcgoe-fgnJIVxI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Scope One` extends GoogleFont {
    override lazy val family: String = "Scope One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/scopeone/v3/WBLnrEXKYFlGHrOKmGD1Ww.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Seaweed Script` extends GoogleFont {
    override lazy val family: String = "Seaweed Script"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/seaweedscript/v5/bx6cNx6Tne2pxOATYE8C_Rsoe0WJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Secular One` extends GoogleFont {
    override lazy val family: String = "Secular One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `hebrew`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/secularone/v2/8QINdiTajsj_87rMuMdKypDl.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sedgwick Ave` extends GoogleFont {
    override lazy val family: String = "Sedgwick Ave"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sedgwickave/v3/uK_04rKEYuguzAcSYRdWTJq8Xg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sedgwick Ave Display` extends GoogleFont {
    override lazy val family: String = "Sedgwick Ave Display"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sedgwickavedisplay/v3/xfuu0XPgU3jZPUoUo3ScvmPi-NapQ8OxM2cz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sevillana` extends GoogleFont {
    override lazy val family: String = "Sevillana"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sevillana/v5/KFOlCnWFscmDt1Bfiy1vAw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Seymour One` extends GoogleFont {
    override lazy val family: String = "Seymour One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/seymourone/v5/4iCp6Khla9xbjQpoWGGd0myI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Shadows Into Light` extends GoogleFont {
    override lazy val family: String = "Shadows Into Light"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shadowsintolight/v7/UqyNK9UOIntux_czAvDQx_ZcHqZXBNQDcg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Shadows Into Light Two` extends GoogleFont {
    override lazy val family: String = "Shadows Into Light Two"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shadowsintolighttwo/v5/4iC86LVlZsRSjQhpWGedwyOoW-0A6_kpsyNmlAs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Shanti` extends GoogleFont {
    override lazy val family: String = "Shanti"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shanti/v9/t5thIREMM4uSDgzgUw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Share` extends GoogleFont {
    override lazy val family: String = "Share"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/share/v8/i7dEIFliZjKNF5VN.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/share/v8/i7dKIFliZjKNF6VPFr4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/share/v8/i7dJIFliZjKNF63xM56-.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/share/v8/i7dPIFliZjKNF6VPLgK7UEY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Share Tech` extends GoogleFont {
    override lazy val family: String = "Share Tech"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sharetech/v7/7cHtv4Uyi5K0OeZ7bohUwHo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Share Tech Mono` extends GoogleFont {
    override lazy val family: String = "Share Tech Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sharetechmono/v7/J7aHnp1uDWRBEqV98dVQztYldFc7pA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Shojumaru` extends GoogleFont {
    override lazy val family: String = "Shojumaru"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shojumaru/v5/rax_HiWfutkLLnaKCtlMBA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Short Stack` extends GoogleFont {
    override lazy val family: String = "Short Stack"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shortstack/v7/bMrzmS2X6p0jZC6EcmPFX-SS.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Shrikhand` extends GoogleFont {
    override lazy val family: String = "Shrikhand"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `gujarati`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shrikhand/v3/a8IbNovtLWfR7T7bMJwbBA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Siemreap` extends GoogleFont {
    override lazy val family: String = "Siemreap"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/siemreap/v10/Gg82N5oFbgLvHAfNl2Yb.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sigmar One` extends GoogleFont {
    override lazy val family: String = "Sigmar One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sigmarone/v8/co3DmWZ8kjZuErj9Ta3dk6M.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Signika` extends GoogleFont {
    override lazy val family: String = "Signika"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/signika/v8/vEFU2_JTCgwQ5ejvE_oEI3A.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/signika/v8/vEFR2_JTCgwQ5ejvK1Y.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/signika/v8/vEFU2_JTCgwQ5ejvE44CI3A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/signika/v8/vEFU2_JTCgwQ5ejvE-oDI3A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `600`, `700`)
  }
  object `Signika Negative` extends GoogleFont {
    override lazy val family: String = "Signika Negative"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/signikanegative/v7/E217_cfngu7HiRpPX3ZpNE4kY5zKal6DipE.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/signikanegative/v7/E218_cfngu7HiRpPX3ZpNE4kY5zKUvI.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/signikanegative/v7/E217_cfngu7HiRpPX3ZpNE4kY5zKaiqFipE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/signikanegative/v7/E217_cfngu7HiRpPX3ZpNE4kY5zKak6EipE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `600`, `700`)
  }
  object `Simonetta` extends GoogleFont {
    override lazy val family: String = "Simonetta"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/simonetta/v7/x3dickHVYrCU5BU15c4BfA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/simonetta/v7/x3dkckHVYrCU5BU15c4xfvoG.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/simonetta/v7/x3dnckHVYrCU5BU15c45-N0mtw.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/simonetta/v7/x3d5ckHVYrCU5BU15c4xfsKCsA7t.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `900`, `900italic`)
  }
  object `Sintony` extends GoogleFont {
    override lazy val family: String = "Sintony"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sintony/v5/XoHm2YDqR7-98cVUITQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sintony/v5/XoHj2YDqR7-98cVUGYgIn9c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Sirin Stencil` extends GoogleFont {
    override lazy val family: String = "Sirin Stencil"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sirinstencil/v6/mem4YaWwznmLx-lzGfN7MdRydcg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Six Caps` extends GoogleFont {
    override lazy val family: String = "Six Caps"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sixcaps/v8/6ae_4KGrU7VR7bNmabcS.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Skranji` extends GoogleFont {
    override lazy val family: String = "Skranji"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/skranji/v5/OZpDg_dtriVFNerMYzs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/skranji/v5/OZpGg_dtriVFNerMW4eBtlw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Slabo 13px` extends GoogleFont {
    override lazy val family: String = "Slabo 13px"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/slabo13px/v4/11hEGp_azEvXZUdSBzzRcKc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Slabo 27px` extends GoogleFont {
    override lazy val family: String = "Slabo 27px"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/slabo27px/v4/mFT0WbgBwKPR_Z4hGN2qsxg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Slackey` extends GoogleFont {
    override lazy val family: String = "Slackey"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/slackey/v8/N0bV2SdQO-5yM0-dKlQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Smokum` extends GoogleFont {
    override lazy val family: String = "Smokum"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/smokum/v8/TK3iWkUbAhopmrdGHg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Smythe` extends GoogleFont {
    override lazy val family: String = "Smythe"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/smythe/v8/MwQ3bhT01--coT1BOA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sniglet` extends GoogleFont {
    override lazy val family: String = "Sniglet"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sniglet/v9/cIf9MaFLtkE3UjaJxCk.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sniglet/v9/cIf4MaFLtkE3UjaJ_ImHRGE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `800`)
  }
  object `Snippet` extends GoogleFont {
    override lazy val family: String = "Snippet"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/snippet/v7/bWt47f7XfQH9Gupu2v8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Snowburst One` extends GoogleFont {
    override lazy val family: String = "Snowburst One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/snowburstone/v5/MQpS-WezKdujBsXY3B7I-UT7eZ8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sofadi One` extends GoogleFont {
    override lazy val family: String = "Sofadi One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sofadione/v6/JIA2UVBxdnVBuElZaMFGcDM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sofia` extends GoogleFont {
    override lazy val family: String = "Sofia"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sofia/v6/8QIHdirahM3j_vu-.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Song Myung` extends GoogleFont {
    override lazy val family: String = "Song Myung"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/songmyung/v2/1cX2aUDWAJH5-EIC7DIhr1E.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sonsie One` extends GoogleFont {
    override lazy val family: String = "Sonsie One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sonsieone/v6/PbymFmP_EAnPqbKaoc18YVs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sorts Mill Goudy` extends GoogleFont {
    override lazy val family: String = "Sorts Mill Goudy"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sortsmillgoudy/v7/Qw3GZR9MED_6PSuS_50nEaVrfzgEXH0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/sortsmillgoudy/v7/Qw3AZR9MED_6PSuS_50nEaVrfzgEbH8Eig.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Source Code Pro` extends GoogleFont {
    override lazy val family: String = "Source Code Pro"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sourcecodepro/v7/HI_XiYsKILxRpg3hIP6sJ7fM7Pqt8srztA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sourcecodepro/v7/HI_XiYsKILxRpg3hIP6sJ7fM7PqtlsnztA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sourcecodepro/v7/HI_SiYsKILxRpg3hIP6sJ7fM7PqVOg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sourcecodepro/v7/HI_XiYsKILxRpg3hIP6sJ7fM7PqtzsjztA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sourcecodepro/v7/HI_XiYsKILxRpg3hIP6sJ7fM7Pqt4s_ztA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sourcecodepro/v7/HI_XiYsKILxRpg3hIP6sJ7fM7Pqths7ztA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sourcecodepro/v7/HI_XiYsKILxRpg3hIP6sJ7fM7PqtvszztA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `900`)
  }
  object `Source Sans Pro` extends GoogleFont {
    override lazy val family: String = "Source Sans Pro"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sourcesanspro/v11/6xKydSBYKcSV-LCoeQqfX1RYOo3i94_AkA.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/sourcesanspro/v11/6xKwdSBYKcSV-LCoeQqfX1RYOo3qPZYokRdr.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sourcesanspro/v11/6xKydSBYKcSV-LCoeQqfX1RYOo3ik4zAkA.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/sourcesanspro/v11/6xKwdSBYKcSV-LCoeQqfX1RYOo3qPZZMkhdr.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sourcesanspro/v11/6xK3dSBYKcSV-LCoeQqfX1RYOo3aPw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/sourcesanspro/v11/6xK1dSBYKcSV-LCoeQqfX1RYOo3qPa7g.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sourcesanspro/v11/6xKydSBYKcSV-LCoeQqfX1RYOo3i54rAkA.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/sourcesanspro/v11/6xKwdSBYKcSV-LCoeQqfX1RYOo3qPZY4lBdr.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sourcesanspro/v11/6xKydSBYKcSV-LCoeQqfX1RYOo3ig4vAkA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/sourcesanspro/v11/6xKwdSBYKcSV-LCoeQqfX1RYOo3qPZZclRdr.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sourcesanspro/v11/6xKydSBYKcSV-LCoeQqfX1RYOo3iu4nAkA.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/sourcesanspro/v11/6xKwdSBYKcSV-LCoeQqfX1RYOo3qPZZklxdr.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Source Serif Pro` extends GoogleFont {
    override lazy val family: String = "Source Serif Pro"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sourceserifpro/v5/neIQzD-0qpwxpaWvjeD0X88SAOeaiXM.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sourceserifpro/v5/neIXzD-0qpwxpaWvjeD0X88SAOeasasahSs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sourceserifpro/v5/neIXzD-0qpwxpaWvjeD0X88SAOeasc8bhSs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `600`, `700`)
  }
  object `Space Mono` extends GoogleFont {
    override lazy val family: String = "Space Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spacemono/v2/i7dPIFZifjKcF5UAWdDRUEY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/spacemono/v2/i7dNIFZifjKcF5UAWdDRYER8QA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/spacemono/v2/i7dMIFZifjKcF5UAWdDRaPpZYFI.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/spacemono/v2/i7dSIFZifjKcF5UAWdDRYERE_FeaGw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Special Elite` extends GoogleFont {
    override lazy val family: String = "Special Elite"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/specialelite/v8/XLYgIZbkc4JPUL5CVArUVL0nhnc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Spectral` extends GoogleFont {
    override lazy val family: String = "Spectral"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/spectral/v4/rnCs-xNNww_2s0amA9v2s13G.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/spectral/v4/rnCu-xNNww_2s0amA9M8qrXHafM.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/spectral/v4/rnCs-xNNww_2s0amA9uSsF3G.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/spectral/v4/rnCu-xNNww_2s0amA9M8qtHEafM.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spectral/v4/rnCr-xNNww_2s0amA-M-.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/spectral/v4/rnCt-xNNww_2s0amA9M8kn0.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/spectral/v4/rnCs-xNNww_2s0amA9vKsV3G.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/spectral/v4/rnCu-xNNww_2s0amA9M8qonFafM.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/spectral/v4/rnCs-xNNww_2s0amA9vmtl3G.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/spectral/v4/rnCu-xNNww_2s0amA9M8qqXCafM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/spectral/v4/rnCs-xNNww_2s0amA9uCt13G.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/spectral/v4/rnCu-xNNww_2s0amA9M8qsHDafM.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/spectral/v4/rnCs-xNNww_2s0amA9uetF3G.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/spectral/v4/rnCu-xNNww_2s0amA9M8qt3AafM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Spectral SC` extends GoogleFont {
    override lazy val family: String = "Spectral SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/spectralsc/v2/Ktk0ALCRZonmalTgyPmRfs1qwkTX.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/spectralsc/v2/Ktk2ALCRZonmalTgyPmRfsWg26zWN4M.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/spectralsc/v2/Ktk0ALCRZonmalTgyPmRfs0OwUTX.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/spectralsc/v2/Ktk2ALCRZonmalTgyPmRfsWg28jVN4M.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spectralsc/v2/KtkpALCRZonmalTgyPmRfvWi.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/spectralsc/v2/KtkrALCRZonmalTgyPmRfsWg42Q.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/spectralsc/v2/Ktk0ALCRZonmalTgyPmRfs1WwETX.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/spectralsc/v2/Ktk2ALCRZonmalTgyPmRfsWg25DUN4M.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/spectralsc/v2/Ktk0ALCRZonmalTgyPmRfs16x0TX.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/spectralsc/v2/Ktk2ALCRZonmalTgyPmRfsWg27zTN4M.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/spectralsc/v2/Ktk0ALCRZonmalTgyPmRfs0exkTX.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/spectralsc/v2/Ktk2ALCRZonmalTgyPmRfsWg29jSN4M.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/spectralsc/v2/Ktk0ALCRZonmalTgyPmRfs0CxUTX.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/spectralsc/v2/Ktk2ALCRZonmalTgyPmRfsWg28TRN4M.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Spicy Rice` extends GoogleFont {
    override lazy val family: String = "Spicy Rice"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spicyrice/v6/uK_24rSEd-Uqwk4jY1RyGv8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Spinnaker` extends GoogleFont {
    override lazy val family: String = "Spinnaker"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spinnaker/v9/w8gYH2oyX-I0_rvR6Hmn3A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Spirax` extends GoogleFont {
    override lazy val family: String = "Spirax"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spirax/v6/buE3poKgYNLy0F3cXg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Squada One` extends GoogleFont {
    override lazy val family: String = "Squada One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/squadaone/v6/BCasqZ8XsOrx4mcOk6MtWaA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sree Krushnadevaraya` extends GoogleFont {
    override lazy val family: String = "Sree Krushnadevaraya"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sreekrushnadevaraya/v5/R70FjzQeifmPepmyQQjQ9kvwMkWYPfTA_EWb.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sriracha` extends GoogleFont {
    override lazy val family: String = "Sriracha"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sriracha/v2/0nkrC9D4IuYBgWcI9ObY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stalemate` extends GoogleFont {
    override lazy val family: String = "Stalemate"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stalemate/v5/taiIGmZ_EJq97-UfkZRpug.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stalinist One` extends GoogleFont {
    override lazy val family: String = "Stalinist One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stalinistone/v10/MQpS-WezM9W4Dd7D3B7I-UT7eZ8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stardos Stencil` extends GoogleFont {
    override lazy val family: String = "Stardos Stencil"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stardosstencil/v7/X7n94bcuGPC8hrvEOHXOgaKCc2TR7w.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/stardosstencil/v7/X7n44bcuGPC8hrvEOHXOgaKCc2TpU3tTvg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Stint Ultra Condensed` extends GoogleFont {
    override lazy val family: String = "Stint Ultra Condensed"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stintultracondensed/v6/-W_gXIrsVjjeyEnPC45qD2NoFPtBE0xCh2A-qg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stint Ultra Expanded` extends GoogleFont {
    override lazy val family: String = "Stint Ultra Expanded"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stintultraexpanded/v5/CSRg4yNNh-GbW3o3JkwoDcdvMKMf0oBAd0qo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stoke` extends GoogleFont {
    override lazy val family: String = "Stoke"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/stoke/v7/z7NXdRb7aTMfKNvFVgxC.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stoke/v7/z7NadRb7aTMfKONp.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`)
  }
  object `Strait` extends GoogleFont {
    override lazy val family: String = "Strait"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/strait/v5/DtViJxy6WaEr1LZzeA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stylish` extends GoogleFont {
    override lazy val family: String = "Stylish"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stylish/v2/m8JSjfhPYriQkk7-fo0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sue Ellen Francisco` extends GoogleFont {
    override lazy val family: String = "Sue Ellen Francisco"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sueellenfrancisco/v8/wXK3E20CsoJ9j1DDkjHcQ5ZL8xRaxru9roo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Suez One` extends GoogleFont {
    override lazy val family: String = "Suez One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `hebrew`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/suezone/v2/taiJGmd_EZ6rqscQgNFJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sumana` extends GoogleFont {
    override lazy val family: String = "Sumana"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sumana/v2/4UaDrE5TqRBjGj-G8A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sumana/v2/4UaArE5TqRBjGj--TDfG5w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Sunflower` extends GoogleFont {
    override lazy val family: String = "Sunflower"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sunflower/v3/RWmPoKeF8fUjqIj7Vc-06MfiqQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sunflower/v3/RWmPoKeF8fUjqIj7Vc-0sMbiqQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sunflower/v3/RWmPoKeF8fUjqIj7Vc-0-MDiqQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `500`, `700`)
  }
  object `Sunshiney` extends GoogleFont {
    override lazy val family: String = "Sunshiney"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sunshiney/v8/LDIwapGTLBwsS-wT4vcgEw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Supermercado One` extends GoogleFont {
    override lazy val family: String = "Supermercado One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/supermercadoone/v7/OpNXnpQWg8jc_xps_Gi14kVVEXOn60Y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sura` extends GoogleFont {
    override lazy val family: String = "Sura"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sura/v2/SZc23FL5PbyzFf4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sura/v2/SZc53FL5PbyzLUJ7fz0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Suranna` extends GoogleFont {
    override lazy val family: String = "Suranna"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/suranna/v5/gokuH6ztGkFjWe58tBQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Suravaram` extends GoogleFont {
    override lazy val family: String = "Suravaram"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/suravaram/v4/_gP61R_usiY7SCym4xIAiw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Suwannaphum` extends GoogleFont {
    override lazy val family: String = "Suwannaphum"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/suwannaphum/v11/jAnCgHV7GtDvc8jbe8hXXIWl.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Swanky and Moo Moo` extends GoogleFont {
    override lazy val family: String = "Swanky and Moo Moo"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/swankyandmoomoo/v7/flUlRrKz24IuWVI_WJYTYcqbEsMUZ3kUtQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Syncopate` extends GoogleFont {
    override lazy val family: String = "Syncopate"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/syncopate/v9/pe0sMIuPIYBCpEV5eFdyAg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/syncopate/v9/pe0pMIuPIYBCpEV5eFdKvtKaAw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Tangerine` extends GoogleFont {
    override lazy val family: String = "Tangerine"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tangerine/v9/IurY6Y5j_oScZZow4VOBDg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/tangerine/v9/Iurd6Y5j_oScZZow4VO5srNpjA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Taprom` extends GoogleFont {
    override lazy val family: String = "Taprom"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/taprom/v9/UcCn3F82JHycULbFQw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tauri` extends GoogleFont {
    override lazy val family: String = "Tauri"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tauri/v5/TwMA-IISS0AM3IpV.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Taviraj` extends GoogleFont {
    override lazy val family: String = "Taviraj"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/taviraj/v3/ahcbv8Cj3ylylTXzRIorVw.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/taviraj/v3/ahcdv8Cj3ylylTXzTOwTM8lx.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/taviraj/v3/ahccv8Cj3ylylTXzRCYKd-k.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/taviraj/v3/ahcev8Cj3ylylTXzTOwTn-hRhQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/taviraj/v3/ahccv8Cj3ylylTXzREIJd-k.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/taviraj/v3/ahcev8Cj3ylylTXzTOwT--tRhQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/taviraj/v3/ahcZv8Cj3ylylTXzfO4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/taviraj/v3/ahcbv8Cj3ylylTXzTOwrVw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/taviraj/v3/ahccv8Cj3ylylTXzRBoId-k.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/taviraj/v3/ahcev8Cj3ylylTXzTOwTo-pRhQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/taviraj/v3/ahccv8Cj3ylylTXzRDYPd-k.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/taviraj/v3/ahcev8Cj3ylylTXzTOwTj-1RhQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/taviraj/v3/ahccv8Cj3ylylTXzRFIOd-k.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/taviraj/v3/ahcev8Cj3ylylTXzTOwT6-xRhQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/taviraj/v3/ahccv8Cj3ylylTXzRE4Nd-k.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/taviraj/v3/ahcev8Cj3ylylTXzTOwT9-9RhQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/taviraj/v3/ahccv8Cj3ylylTXzRGoMd-k.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/taviraj/v3/ahcev8Cj3ylylTXzTOwT0-5RhQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Teko` extends GoogleFont {
    override lazy val family: String = "Teko"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/teko/v7/LYjCdG7kmE0gdQhfgCM.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/teko/v7/LYjNdG7kmE0gTaQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/teko/v7/LYjCdG7kmE0gdVBegCM.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/teko/v7/LYjCdG7kmE0gdXxZgCM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/teko/v7/LYjCdG7kmE0gdRhYgCM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Telex` extends GoogleFont {
    override lazy val family: String = "Telex"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/telex/v6/ieVw2Y1fKWmIO9fT.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tenali Ramakrishna` extends GoogleFont {
    override lazy val family: String = "Tenali Ramakrishna"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tenaliramakrishna/v4/raxgHj6Yt9gAN3LLKs0BZVMo8jmwn1-8KA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tenor Sans` extends GoogleFont {
    override lazy val family: String = "Tenor Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tenorsans/v8/bx6ANxqUneKx06UkIXISr3I.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Text Me One` extends GoogleFont {
    override lazy val family: String = "Text Me One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/textmeone/v5/i7dOIFdlayuLUvgoFvHQFWZc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `The Girl Next Door` extends GoogleFont {
    override lazy val family: String = "The Girl Next Door"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/thegirlnextdoor/v8/pe0zMJCIMIsBjFxqYBIcZ6_OI5oFHCYIVw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tienne` extends GoogleFont {
    override lazy val family: String = "Tienne"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tienne/v10/AYCKpX7pe9YCRP0LkA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/tienne/v10/AYCJpX7pe9YCRP0zLGzjQA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/tienne/v10/AYCJpX7pe9YCRP0zFG7jQA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `900`)
  }
  object `Tillana` extends GoogleFont {
    override lazy val family: String = "Tillana"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tillana/v3/VuJxdNvf35P4qJ1OeKY.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/tillana/v3/VuJ0dNvf35P4qJ1OQFL-HIk.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/tillana/v3/VuJ0dNvf35P4qJ1OQH75HIk.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/tillana/v3/VuJ0dNvf35P4qJ1OQBr4HIk.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/tillana/v3/VuJ0dNvf35P4qJ1OQAb7HIk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `800`)
  }
  object `Timmana` extends GoogleFont {
    override lazy val family: String = "Timmana"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `telugu`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/timmana/v2/6xKvdShfL9yK-rvpCms.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tinos` extends GoogleFont {
    override lazy val family: String = "Tinos"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `hebrew`, `latin-ext`, `greek-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tinos/v11/buE4poGnedXvwgX8.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/tinos/v11/buE2poGnedXvwjX-fmE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/tinos/v11/buE1poGnedXvwj1AW0Fp.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/tinos/v11/buEzpoGnedXvwjX-Rt1s0Co.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Titan One` extends GoogleFont {
    override lazy val family: String = "Titan One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/titanone/v5/mFTzWbsGxbbS_J5cQcjykw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Titillium Web` extends GoogleFont {
    override lazy val family: String = "Titillium Web"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/titilliumweb/v6/NaPDcZTIAOhVxoMyOr9n_E7ffAzHKIw.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/titilliumweb/v6/NaPFcZTIAOhVxoMyOr9n_E7fdMbewI1zZg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/titilliumweb/v6/NaPDcZTIAOhVxoMyOr9n_E7ffGjEKIw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/titilliumweb/v6/NaPFcZTIAOhVxoMyOr9n_E7fdMbepI5zZg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/titilliumweb/v6/NaPecZTIAOhVxoMyOr9n_E7fRMQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/titilliumweb/v6/NaPAcZTIAOhVxoMyOr9n_E7fdMbmCA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/titilliumweb/v6/NaPDcZTIAOhVxoMyOr9n_E7ffBzCKIw.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/titilliumweb/v6/NaPFcZTIAOhVxoMyOr9n_E7fdMbe0IhzZg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/titilliumweb/v6/NaPDcZTIAOhVxoMyOr9n_E7ffHjDKIw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/titilliumweb/v6/NaPFcZTIAOhVxoMyOr9n_E7fdMbetIlzZg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/titilliumweb/v6/NaPDcZTIAOhVxoMyOr9n_E7ffEDBKIw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `900`)
  }
  object `Trade Winds` extends GoogleFont {
    override lazy val family: String = "Trade Winds"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tradewinds/v6/AYCPpXPpYNIIT7h8-QenM3Jq.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Trirong` extends GoogleFont {
    override lazy val family: String = "Trirong"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `thai`, `vietnamese`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/trirong/v3/7r3EqXNgp8wxdOdOl-go3Q.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/trirong/v3/7r3CqXNgp8wxdOdOn44QuY5h.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/trirong/v3/7r3DqXNgp8wxdOdOl0QJ_a4.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/trirong/v3/7r3BqXNgp8wxdOdOn44QFa9B4g.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/trirong/v3/7r3DqXNgp8wxdOdOlyAK_a4.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/trirong/v3/7r3BqXNgp8wxdOdOn44QcaxB4g.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/trirong/v3/7r3GqXNgp8wxdOdOr4w.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/trirong/v3/7r3EqXNgp8wxdOdOn44o3Q.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/trirong/v3/7r3DqXNgp8wxdOdOl3gL_a4.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/trirong/v3/7r3BqXNgp8wxdOdOn44QKa1B4g.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/trirong/v3/7r3DqXNgp8wxdOdOl1QM_a4.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/trirong/v3/7r3BqXNgp8wxdOdOn44QBapB4g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/trirong/v3/7r3DqXNgp8wxdOdOlzAN_a4.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/trirong/v3/7r3BqXNgp8wxdOdOn44QYatB4g.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/trirong/v3/7r3DqXNgp8wxdOdOlywO_a4.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/trirong/v3/7r3BqXNgp8wxdOdOn44QfahB4g.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/trirong/v3/7r3DqXNgp8wxdOdOlwgP_a4.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/trirong/v3/7r3BqXNgp8wxdOdOn44QWalB4g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Trocchi` extends GoogleFont {
    override lazy val family: String = "Trocchi"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/trocchi/v6/qWcqB6WkuIDxDZLcDrs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Trochut` extends GoogleFont {
    override lazy val family: String = "Trochut"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/trochut/v5/CHyjV-fDDlP9bDIw5nQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/trochut/v5/CHyhV-fDDlP9bDIw1naCeQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/trochut/v5/CHymV-fDDlP9bDIw3sinWVo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Trykker` extends GoogleFont {
    override lazy val family: String = "Trykker"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/trykker/v6/KtktALyWZJXudUPzhNk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tulpen One` extends GoogleFont {
    override lazy val family: String = "Tulpen One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tulpenone/v7/dFa6ZfeC474skLgesc0CWj0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ubuntu` extends GoogleFont {
    override lazy val family: String = "Ubuntu"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `greek`, `cyrillic-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/ubuntu/v11/4iCv6KVjbNBYlgoC1CzTtw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/ubuntu/v11/4iCp6KVjbNBYlgoKejZftWyI.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ubuntu/v11/4iCs6KVjbNBYlgo6eA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ubuntu/v11/4iCu6KVjbNBYlgoKeg7z.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ubuntu/v11/4iCv6KVjbNBYlgoCjC3Ttw.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ubuntu/v11/4iCp6KVjbNBYlgoKejYHtGyI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ubuntu/v11/4iCv6KVjbNBYlgoCxCvTtw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ubuntu/v11/4iCp6KVjbNBYlgoKejZPsmyI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`)
  }
  object `Ubuntu Condensed` extends GoogleFont {
    override lazy val family: String = "Ubuntu Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ubuntucondensed/v8/u-4k0rCzjgs5J7oXnJcM_0kACGMtf-c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ubuntu Mono` extends GoogleFont {
    override lazy val family: String = "Ubuntu Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `greek-ext`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ubuntumono/v7/KFOjCneDtsqEr0keqCMhbBc9.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ubuntumono/v7/KFOhCneDtsqEr0keqCMhbCc_CsE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ubuntumono/v7/KFO-CneDtsqEr0keqCMhbC-BL-Hy.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ubuntumono/v7/KFO8CneDtsqEr0keqCMhbCc_Mn33tYg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Ultra` extends GoogleFont {
    override lazy val family: String = "Ultra"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ultra/v10/zOLy4prXmrtY-tT6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Uncial Antiqua` extends GoogleFont {
    override lazy val family: String = "Uncial Antiqua"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/uncialantiqua/v5/N0bM2S5WOex4OUbESzoESK-i-PfR.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Underdog` extends GoogleFont {
    override lazy val family: String = "Underdog"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/underdog/v6/CHygV-jCElj7diMroVSi.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Unica One` extends GoogleFont {
    override lazy val family: String = "Unica One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/unicaone/v5/DPEuYwWHyAYGVTSmalshdg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `UnifrakturCook` extends GoogleFont {
    override lazy val family: String = "UnifrakturCook"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/unifrakturcook/v9/IurA6Yli8YOdcoky-0PTTdkm56n05Uw1.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`700`)
  }
  object `UnifrakturMaguntia` extends GoogleFont {
    override lazy val family: String = "UnifrakturMaguntia"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/unifrakturmaguntia/v8/WWXPlieVYwiGNomYU-ciRLRvEmK7oaVunw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Unkempt` extends GoogleFont {
    override lazy val family: String = "Unkempt"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/unkempt/v9/2EbnL-Z2DFZue0DSSYY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/unkempt/v9/2EbiL-Z2DFZue0DScTow1zU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Unlock` extends GoogleFont {
    override lazy val family: String = "Unlock"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/unlock/v7/7Au-p_8ykD-cDl7GKA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Unna` extends GoogleFont {
    override lazy val family: String = "Unna"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/unna/v10/AYCEpXzofN0NCpg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/unna/v10/AYCKpXzofN0NOpoLkA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/unna/v10/AYCLpXzofN0NMiQusGk.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/unna/v10/AYCJpXzofN0NOpozLGzjQA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `VT323` extends GoogleFont {
    override lazy val family: String = "VT323"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vt323/v9/pxiKyp0ihIEF2hsY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Vampiro One` extends GoogleFont {
    override lazy val family: String = "Vampiro One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vampiroone/v8/gokqH6DoDl5yXvJytFsdLkqn.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Varela` extends GoogleFont {
    override lazy val family: String = "Varela"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/varela/v8/DPEtYwqExx0AWHXJBA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Varela Round` extends GoogleFont {
    override lazy val family: String = "Varela Round"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `hebrew`, `latin-ext`, `vietnamese`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/varelaround/v9/w8gdH283Tvk__Lua32TysjIvoA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Vast Shadow` extends GoogleFont {
    override lazy val family: String = "Vast Shadow"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vastshadow/v7/pe0qMImKOZ1V62ZwbVY9dfe6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Vesper Libre` extends GoogleFont {
    override lazy val family: String = "Vesper Libre"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vesperlibre/v9/bx6CNxyWnf-uxPdXDHUD_Rd4Dw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/vesperlibre/v9/bx6dNxyWnf-uxPdXDHUD_RdA-2ap0g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/vesperlibre/v9/bx6dNxyWnf-uxPdXDHUD_RdAs2Cp0g.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/vesperlibre/v9/bx6dNxyWnf-uxPdXDHUD_RdAi2Kp0g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `700`, `900`)
  }
  object `Vibur` extends GoogleFont {
    override lazy val family: String = "Vibur"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vibur/v8/DPEiYwmEzw0QRjTp.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Vidaloka` extends GoogleFont {
    override lazy val family: String = "Vidaloka"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vidaloka/v9/7cHrv4c3ipenMKlEass8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Viga` extends GoogleFont {
    override lazy val family: String = "Viga"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/viga/v6/xMQbuFFdSaiX_QI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Voces` extends GoogleFont {
    override lazy val family: String = "Voces"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/voces/v7/-F6_fjJyLyU8d4PB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Volkhov` extends GoogleFont {
    override lazy val family: String = "Volkhov"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/volkhov/v9/SlGQmQieoJcKemNeQTI.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/volkhov/v9/SlGSmQieoJcKemNecTAEgA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/volkhov/v9/SlGVmQieoJcKemNeeY4hoHQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/volkhov/v9/SlGXmQieoJcKemNecTA8PHFSaA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Vollkorn` extends GoogleFont {
    override lazy val family: String = "Vollkorn"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `greek`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vollkorn/v8/0yb9GDoxxrvAnPhYGyku.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/vollkorn/v8/0yb7GDoxxrvAnPhYGxksaEg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/vollkorn/v8/0yb6GDoxxrvAnPhYGxH2TGg-.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/vollkorn/v8/0yb4GDoxxrvAnPhYGxksUJA6jBA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/vollkorn/v8/0yb6GDoxxrvAnPhYGxGSTWg-.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/vollkorn/v8/0yb4GDoxxrvAnPhYGxksUPQ7jBA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/vollkorn/v8/0yb6GDoxxrvAnPhYGxGqT2g-.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/vollkorn/v8/0yb4GDoxxrvAnPhYGxksUMw5jBA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Vollkorn SC` extends GoogleFont {
    override lazy val family: String = "Vollkorn SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vollkornsc/v1/j8_v6-zQ3rXpceZj9cqnVhF5.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/vollkornsc/v1/j8_y6-zQ3rXpceZj9cqnVimhGluq.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/vollkornsc/v1/j8_y6-zQ3rXpceZj9cqnVinFG1uq.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/vollkornsc/v1/j8_y6-zQ3rXpceZj9cqnVin9GVuq.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `600`, `700`, `900`)
  }
  object `Voltaire` extends GoogleFont {
    override lazy val family: String = "Voltaire"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/voltaire/v7/1Pttg8PcRfSblAvGvQoo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Waiting for the Sunrise` extends GoogleFont {
    override lazy val family: String = "Waiting for the Sunrise"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/waitingforthesunrise/v8/WBL1rFvOYl9CEv2i1mO6KUW8RKWJ2zoXoz5JsYZQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Wallpoet` extends GoogleFont {
    override lazy val family: String = "Wallpoet"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/wallpoet/v9/f0X10em2_8RnXVVdUNbu.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Walter Turncoat` extends GoogleFont {
    override lazy val family: String = "Walter Turncoat"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/walterturncoat/v8/snfys0Gs98ln43n0d-14ULoToe67YA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Warnes` extends GoogleFont {
    override lazy val family: String = "Warnes"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/warnes/v7/pONn1hc0GsW6sW5Opg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Wellfleet` extends GoogleFont {
    override lazy val family: String = "Wellfleet"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/wellfleet/v5/nuF7D_LfQJb3VYgX6eyT4w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Wendy One` extends GoogleFont {
    override lazy val family: String = "Wendy One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/wendyone/v5/2sDcZGJOipXfgfXV5wgDbw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Wire One` extends GoogleFont {
    override lazy val family: String = "Wire One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/wireone/v8/qFdH35Wah5htUhV75WGi.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Work Sans` extends GoogleFont {
    override lazy val family: String = "Work Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/worksans/v3/QGYqz_wNahGAdqQ43Rh3H6Ds.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/worksans/v3/QGYpz_wNahGAdqQ43Rh3s4HMnw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/worksans/v3/QGYpz_wNahGAdqQ43Rh314LMnw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/worksans/v3/QGYsz_wNahGAdqQ43RhPew.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/worksans/v3/QGYpz_wNahGAdqQ43Rh3j4PMnw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/worksans/v3/QGYpz_wNahGAdqQ43Rh3o4TMnw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/worksans/v3/QGYpz_wNahGAdqQ43Rh3x4XMnw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/worksans/v3/QGYpz_wNahGAdqQ43Rh324bMnw.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/worksans/v3/QGYpz_wNahGAdqQ43Rh3_4fMnw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Yanone Kaffeesatz` extends GoogleFont {
    override lazy val family: String = "Yanone Kaffeesatz"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/yanonekaffeesatz/v9/3y9-6aknfjLm_3lMKjiMgmUUYBs04YfUPs-t.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/yanonekaffeesatz/v9/3y9-6aknfjLm_3lMKjiMgmUUYBs04YewPc-t.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yanonekaffeesatz/v9/3y976aknfjLm_3lMKjiMgmUUYBs04b8c.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/yanonekaffeesatz/v9/3y9-6aknfjLm_3lMKjiMgmUUYBs04YegOs-t.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `700`)
  }
  object `Yantramanav` extends GoogleFont {
    override lazy val family: String = "Yantramanav"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/yantramanav/v3/flU-Rqu5zY00QEpyWJYWN5-QXeM.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/yantramanav/v3/flUhRqu5zY00QEpyWJYWN59Yf8NZ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yantramanav/v3/flU8Rqu5zY00QEpyWJYWN6f0.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/yantramanav/v3/flUhRqu5zY00QEpyWJYWN58AfsNZ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/yantramanav/v3/flUhRqu5zY00QEpyWJYWN59IeMNZ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/yantramanav/v3/flUhRqu5zY00QEpyWJYWN59wesNZ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `900`)
  }
  object `Yatra One` extends GoogleFont {
    override lazy val family: String = "Yatra One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `devanagari`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yatraone/v4/C8ch4copsHzj8p7NaF0xww.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Yellowtail` extends GoogleFont {
    override lazy val family: String = "Yellowtail"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yellowtail/v8/OZpGg_pnoDtINPfRIlLotlw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Yeon Sung` extends GoogleFont {
    override lazy val family: String = "Yeon Sung"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `korean`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yeonsung/v2/QldMNTpbohAGtsJvUn6xSQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Yeseva One` extends GoogleFont {
    override lazy val family: String = "Yeseva One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`, `latin-ext`, `vietnamese`, `cyrillic-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yesevaone/v12/OpNJno4ck8vc-xYpwWWxpio.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Yesteryear` extends GoogleFont {
    override lazy val family: String = "Yesteryear"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yesteryear/v6/dg4g_p78rroaKl8kRKo1r7w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Yrsa` extends GoogleFont {
    override lazy val family: String = "Yrsa"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/yrsa/v3/wlpxgwnQFlxs3af93IQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yrsa/v3/wlp-gwnQFlxs5Qs.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/yrsa/v3/wlpxgwnQFlxs3f_83IQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/yrsa/v3/wlpxgwnQFlxs3dP73IQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/yrsa/v3/wlpxgwnQFlxs3bf63IQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Zeyada` extends GoogleFont {
    override lazy val family: String = "Zeyada"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/zeyada/v7/11hAGpPTxVPUbgZDNA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Zilla Slab` extends GoogleFont {
    override lazy val family: String = "Zilla Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/zillaslab/v3/dFa5ZfeM_74wlPZtksIFYpEY2HQ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/zillaslab/v3/dFanZfeM_74wlPZtksIFaj8CVHapXg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/zillaslab/v3/dFa6ZfeM_74wlPZtksIFWj0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/zillaslab/v3/dFa4ZfeM_74wlPZtksIFaj86-A.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/zillaslab/v3/dFa5ZfeM_74wlPZtksIFYskZ2HQ.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/zillaslab/v3/dFanZfeM_74wlPZtksIFaj8CDHepXg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/zillaslab/v3/dFa5ZfeM_74wlPZtksIFYuUe2HQ.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/zillaslab/v3/dFanZfeM_74wlPZtksIFaj8CIHCpXg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/zillaslab/v3/dFa5ZfeM_74wlPZtksIFYoEf2HQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/zillaslab/v3/dFanZfeM_74wlPZtksIFaj8CRHGpXg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Zilla Slab Highlight` extends GoogleFont {
    override lazy val family: String = "Zilla Slab Highlight"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `latin-ext`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/zillaslabhighlight/v3/gNMbW2BrTpK8-inLtBJgMMfbm6uNVDvRxhtI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/zillaslabhighlight/v3/gNMUW2BrTpK8-inLtBJgMMfbm6uNVDvRxiP0TET4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
}
       