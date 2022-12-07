package io.youi.font

import spice.net.URL

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
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/abeezee/v12/esDR31xSG-6AGleN6tKukbcHCpE.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/abeezee/v12/esDT31xSG-6AGleN2tCklZUCGpG-GQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Abel` extends GoogleFont {
    override lazy val family: String = "Abel"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/abel/v9/MwQ5bhbm2POE6VhLPJp6qGI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Abhaya Libre` extends GoogleFont {
    override lazy val family: String = "Abhaya Libre"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `sinhala`: GoogleFontSubset = new GoogleFontSubset("sinhala")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`sinhala`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/abhayalibre/v4/e3tmeuGtX-Co5MNzeAOqinEge0PWovdU4w.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/abhayalibre/v4/e3t5euGtX-Co5MNzeAOqinEYj2ryqtxI6oYtBA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/abhayalibre/v4/e3t5euGtX-Co5MNzeAOqinEYo23yqtxI6oYtBA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/abhayalibre/v4/e3t5euGtX-Co5MNzeAOqinEYx2zyqtxI6oYtBA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/abhayalibre/v4/e3t5euGtX-Co5MNzeAOqinEY22_yqtxI6oYtBA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `800`)
  }
  object `Abril Fatface` extends GoogleFont {
    override lazy val family: String = "Abril Fatface"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/abrilfatface/v10/zOL64pLDlL1D99S8g8PtiKchm-BsjOLhZBY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Aclonica` extends GoogleFont {
    override lazy val family: String = "Aclonica"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aclonica/v9/K2FyfZJVlfNNSEBXGb7TCI6oBjLz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Acme` extends GoogleFont {
    override lazy val family: String = "Acme"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/acme/v8/RrQfboBx-C5_bx3Lb23lzLk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Actor` extends GoogleFont {
    override lazy val family: String = "Actor"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/actor/v8/wEOzEBbCkc5cO3ekXygtUMIO.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Adamina` extends GoogleFont {
    override lazy val family: String = "Adamina"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/adamina/v12/j8_r6-DH1bjoc-dwu-reETl4Bno.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Advent Pro` extends GoogleFont {
    override lazy val family: String = "Advent Pro"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `latin-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/adventpro/v9/V8mCoQfxVT4Dvddr_yOwjVmtLZxcBtItFw.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/adventpro/v9/V8mDoQfxVT4Dvddr_yOwjfWMDbZyCts0DqQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/adventpro/v9/V8mDoQfxVT4Dvddr_yOwjZGPDbZyCts0DqQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/adventpro/v9/V8mAoQfxVT4Dvddr_yOwtT2nKb5ZFtI.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/adventpro/v9/V8mDoQfxVT4Dvddr_yOwjcmODbZyCts0DqQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/adventpro/v9/V8mDoQfxVT4Dvddr_yOwjeWJDbZyCts0DqQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/adventpro/v9/V8mDoQfxVT4Dvddr_yOwjYGIDbZyCts0DqQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Aguafina Script` extends GoogleFont {
    override lazy val family: String = "Aguafina Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aguafinascript/v7/If2QXTv_ZzSxGIO30LemWEOmt1bHqs4pgicOrg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Akronim` extends GoogleFont {
    override lazy val family: String = "Akronim"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/akronim/v8/fdN-9sqWtWZZlHRp-gBxkFYN-a8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Aladin` extends GoogleFont {
    override lazy val family: String = "Aladin"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aladin/v7/ZgNSjPJFPrvJV5f16Sf4pGT2Ng.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Aldrich` extends GoogleFont {
    override lazy val family: String = "Aldrich"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aldrich/v9/MCoTzAn-1s3IGyJMZaAS3pP5H_E.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Alef` extends GoogleFont {
    override lazy val family: String = "Alef"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alef/v10/FeVfS0NQpLYgrjJbC5FxxbU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/alef/v10/FeVQS0NQpLYglo50L5la2bxii28.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Alegreya` extends GoogleFont {
    override lazy val family: String = "Alegreya"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alegreya/v12/4UaBrEBBsBhlBjvfkRLmzanB44N1.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/alegreya/v12/4UaHrEBBsBhlBjvfkSLkx63j5pN1MwI.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/alegreya/v12/4UaGrEBBsBhlBjvfkSoS5I3JyJ98KhtH.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/alegreya/v12/4UaErEBBsBhlBjvfkSLk_1nKwpteLwtHJlc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/alegreya/v12/4UaGrEBBsBhlBjvfkSpa4o3JyJ98KhtH.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/alegreya/v12/4UaErEBBsBhlBjvfkSLk_xHMwpteLwtHJlc.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/alegreya/v12/4UaGrEBBsBhlBjvfkSpG4Y3JyJ98KhtH.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/alegreya/v12/4UaErEBBsBhlBjvfkSLk_w3PwpteLwtHJlc.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/alegreya/v12/4UaGrEBBsBhlBjvfkSpi4I3JyJ98KhtH.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/alegreya/v12/4UaErEBBsBhlBjvfkSLk_ynOwpteLwtHJlc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Alegreya SC` extends GoogleFont {
    override lazy val family: String = "Alegreya SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alegreyasc/v10/taiOGmRtCJ62-O0HhNEa-a6o05E5abe_.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/alegreyasc/v10/taiMGmRtCJ62-O0HhNEa-Z6q2ZUbbKe_DGs.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/alegreyasc/v10/taiTGmRtCJ62-O0HhNEa-ZZc-rUxQqu2FXKD.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/alegreyasc/v10/taiRGmRtCJ62-O0HhNEa-Z6q4WEySK-UEGKDBz4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/alegreyasc/v10/taiTGmRtCJ62-O0HhNEa-ZYU_LUxQqu2FXKD.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/alegreyasc/v10/taiRGmRtCJ62-O0HhNEa-Z6q4Sk0SK-UEGKDBz4.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/alegreyasc/v10/taiTGmRtCJ62-O0HhNEa-ZYI_7UxQqu2FXKD.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/alegreyasc/v10/taiRGmRtCJ62-O0HhNEa-Z6q4TU3SK-UEGKDBz4.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/alegreyasc/v10/taiTGmRtCJ62-O0HhNEa-ZYs_rUxQqu2FXKD.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/alegreyasc/v10/taiRGmRtCJ62-O0HhNEa-Z6q4RE2SK-UEGKDBz4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Alegreya Sans` extends GoogleFont {
    override lazy val family: String = "Alegreya Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/alegreyasans/v9/5aUt9_-1phKLFgshYDvh6Vwt5TltuGdShm5bsg.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/alegreyasans/v9/5aUv9_-1phKLFgshYDvh6Vwt7V9V3G1WpGtLsgu7.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/alegreyasans/v9/5aUu9_-1phKLFgshYDvh6Vwt5fFPmE18imdCqxI.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/alegreyasans/v9/5aUo9_-1phKLFgshYDvh6Vwt7V9VFE92jkVHuxKiBA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alegreyasans/v9/5aUz9_-1phKLFgshYDvh6Vwt3V1nvEVXlm4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/alegreyasans/v9/5aUt9_-1phKLFgshYDvh6Vwt7V9tuGdShm5bsg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/alegreyasans/v9/5aUu9_-1phKLFgshYDvh6Vwt5alOmE18imdCqxI.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/alegreyasans/v9/5aUo9_-1phKLFgshYDvh6Vwt7V9VTE52jkVHuxKiBA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/alegreyasans/v9/5aUu9_-1phKLFgshYDvh6Vwt5eFImE18imdCqxI.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/alegreyasans/v9/5aUo9_-1phKLFgshYDvh6Vwt7V9VBEh2jkVHuxKiBA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/alegreyasans/v9/5aUu9_-1phKLFgshYDvh6Vwt5f1LmE18imdCqxI.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/alegreyasans/v9/5aUo9_-1phKLFgshYDvh6Vwt7V9VGEt2jkVHuxKiBA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/alegreyasans/v9/5aUu9_-1phKLFgshYDvh6Vwt5dlKmE18imdCqxI.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/alegreyasans/v9/5aUo9_-1phKLFgshYDvh6Vwt7V9VPEp2jkVHuxKiBA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Alegreya Sans SC` extends GoogleFont {
    override lazy val family: String = "Alegreya Sans SC"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/alegreyasanssc/v8/mtGn4-RGJqfMvt7P8FUr0Q1j-Hf1Dipl8g5FPYtmMg.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/alegreyasanssc/v8/mtGl4-RGJqfMvt7P8FUr0Q1j-Hf1BkxdlgRBH452Mvds.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/alegreyasanssc/v8/mtGm4-RGJqfMvt7P8FUr0Q1j-Hf1DuJH0iRrMYJ_K-4.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/alegreyasanssc/v8/mtGk4-RGJqfMvt7P8FUr0Q1j-Hf1BkxdXiZhNaB6O-51OA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alegreyasanssc/v8/mtGh4-RGJqfMvt7P8FUr0Q1j-Hf1Nk5v9ixALYs.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/alegreyasanssc/v8/mtGn4-RGJqfMvt7P8FUr0Q1j-Hf1Bkxl8g5FPYtmMg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/alegreyasanssc/v8/mtGm4-RGJqfMvt7P8FUr0Q1j-Hf1DrpG0iRrMYJ_K-4.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/alegreyasanssc/v8/mtGk4-RGJqfMvt7P8FUr0Q1j-Hf1BkxdBidhNaB6O-51OA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/alegreyasanssc/v8/mtGm4-RGJqfMvt7P8FUr0Q1j-Hf1DvJA0iRrMYJ_K-4.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/alegreyasanssc/v8/mtGk4-RGJqfMvt7P8FUr0Q1j-Hf1BkxdTiFhNaB6O-51OA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/alegreyasanssc/v8/mtGm4-RGJqfMvt7P8FUr0Q1j-Hf1Du5D0iRrMYJ_K-4.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/alegreyasanssc/v8/mtGk4-RGJqfMvt7P8FUr0Q1j-Hf1BkxdUiJhNaB6O-51OA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/alegreyasanssc/v8/mtGm4-RGJqfMvt7P8FUr0Q1j-Hf1DspC0iRrMYJ_K-4.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/alegreyasanssc/v8/mtGk4-RGJqfMvt7P8FUr0Q1j-Hf1BkxddiNhNaB6O-51OA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Aleo` extends GoogleFont {
    override lazy val family: String = "Aleo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/aleo/v2/c4mg1nF8G8_syKbr9DVDno985KM.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/aleo/v2/c4mi1nF8G8_swAjxeDdJmq159KOnWA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aleo/v2/c4mv1nF8G8_s8ArD0D1ogoY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/aleo/v2/c4mh1nF8G8_swAjJ1B9tkoZl_Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/aleo/v2/c4mg1nF8G8_syLbs9DVDno985KM.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/aleo/v2/c4mi1nF8G8_swAjxaDBJmq159KOnWA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`)
  }
  object `Alex Brush` extends GoogleFont {
    override lazy val family: String = "Alex Brush"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alexbrush/v10/SZc83FzrJKuqFbwMKk6EtUL57DtOmCc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Alfa Slab One` extends GoogleFont {
    override lazy val family: String = "Alfa Slab One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alfaslabone/v8/6NUQ8FmMKwSEKjnm5-4v-4Jh6dVretWvYmE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Alice` extends GoogleFont {
    override lazy val family: String = "Alice"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alice/v10/OpNCnoEEmtHa6FcJpA_chzJ0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Alike` extends GoogleFont {
    override lazy val family: String = "Alike"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alike/v11/HI_EiYEYI6BIoEjBSZXAQ4-d.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Alike Angular` extends GoogleFont {
    override lazy val family: String = "Alike Angular"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/alikeangular/v9/3qTrojWunjGQtEBlIcwMbSoI3kM6bB7FKjE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Allan` extends GoogleFont {
    override lazy val family: String = "Allan"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/allan/v11/ea8XadU7WuTxEtb2P9SF8nZE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/allan/v11/ea8aadU7WuTxEu5KEPCN2WpNgEKU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Allerta` extends GoogleFont {
    override lazy val family: String = "Allerta"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/allerta/v9/TwMO-IAHRlkbx940UnEdSQqO5uY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Allerta Stencil` extends GoogleFont {
    override lazy val family: String = "Allerta Stencil"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/allertastencil/v9/HTx0L209KT-LmIE9N7OR6eiycOeF-zz313DuvQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Allura` extends GoogleFont {
    override lazy val family: String = "Allura"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/allura/v7/9oRPNYsQpS4zjuAPjAIXPtrrGA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Almendra` extends GoogleFont {
    override lazy val family: String = "Almendra"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/almendra/v11/H4ckBXKAlMnTn0CskyY6wr-wg763.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/almendra/v11/H4ciBXKAlMnTn0CskxY4yLuShq63czE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/almendra/v11/H4cjBXKAlMnTn0Cskx6G7Zu4qKK-aihq.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/almendra/v11/H4chBXKAlMnTn0CskxY48Ae9oqacbzhqDtg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Almendra Display` extends GoogleFont {
    override lazy val family: String = "Almendra Display"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/almendradisplay/v9/0FlPVOGWl1Sb4O3tETtADHRRlZhzXS_eTyer338.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Almendra SC` extends GoogleFont {
    override lazy val family: String = "Almendra SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/almendrasc/v9/Iure6Yx284eebowr7hbyTZZJprVA4XQ0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Amarante` extends GoogleFont {
    override lazy val family: String = "Amarante"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amarante/v6/xMQXuF1KTa6EvGx9bq-3C3rAmD-b.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Amaranth` extends GoogleFont {
    override lazy val family: String = "Amaranth"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amaranth/v9/KtkuALODe433f0j1zPnCF9GqwnzW.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/amaranth/v9/KtkoALODe433f0j1zMnAHdWIx2zWD4I.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/amaranth/v9/KtkpALODe433f0j1zMF-OPWi6WDfFpuc.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/amaranth/v9/KtkrALODe433f0j1zMnAJWmn42T9E4ucRY8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Amatic SC` extends GoogleFont {
    override lazy val family: String = "Amatic SC"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin-ext`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amaticsc/v12/TUZyzwprpvBS1izr_vO0De6ecZQf1A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/amaticsc/v12/TUZ3zwprpvBS1izr_vOMscG6eb8D3WTy-A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Amethysta` extends GoogleFont {
    override lazy val family: String = "Amethysta"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amethysta/v7/rP2Fp2K15kgb_F3ibfWIGDWCBl0O8Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Amiko` extends GoogleFont {
    override lazy val family: String = "Amiko"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amiko/v3/WwkQxPq1DFK04tqlc17MMZgJ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/amiko/v3/WwkdxPq1DFK04uJ9XXrEGoQAUco5.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/amiko/v3/WwkdxPq1DFK04uIZXHrEGoQAUco5.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `600`, `700`)
  }
  object `Amiri` extends GoogleFont {
    override lazy val family: String = "Amiri"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amiri/v12/J7aRnpd8CGxBHqUpvrIw74NL.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/amiri/v12/J7afnpd8CGxBHpUrtLYS6pNLAjk.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/amiri/v12/J7acnpd8CGxBHp2VkZY4xJ9CGyAa.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/amiri/v12/J7aanpd8CGxBHpUrjAo9zptgHjAavCA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Amita` extends GoogleFont {
    override lazy val family: String = "Amita"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/amita/v4/HhyaU5si9Om7PQlvAfSKEZZL.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/amita/v4/HhyXU5si9Om7PTHTLtCCOopCTKkI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Anaheim` extends GoogleFont {
    override lazy val family: String = "Anaheim"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/anaheim/v6/8vII7w042Wp87g4G0UTUEE5eK_w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Andada` extends GoogleFont {
    override lazy val family: String = "Andada"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/andada/v10/uK_y4riWaego3w9RCh0TMv6EXw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Andika` extends GoogleFont {
    override lazy val family: String = "Andika"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/andika/v10/mem_Ya6iyW-LwqgAbbwRWrwGVA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Angkor` extends GoogleFont {
    override lazy val family: String = "Angkor"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/angkor/v11/H4cmBXyAlsPdnlb-8iw-4Lqggw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Annie Use Your Telescope` extends GoogleFont {
    override lazy val family: String = "Annie Use Your Telescope"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/annieuseyourtelescope/v9/daaLSS4tI2qYYl3Jq9s_Hu74xwktnlKxH6osGVGjlDfB3UUVZA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Anonymous Pro` extends GoogleFont {
    override lazy val family: String = "Anonymous Pro"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/anonymouspro/v12/rP2Bp2a15UIB7Un-bOeISG3pLlw89CH98Ko.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/anonymouspro/v12/rP2fp2a15UIB7Un-bOeISG3pHl428AP44Kqr2Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/anonymouspro/v12/rP2cp2a15UIB7Un-bOeISG3pFuAT0CnW7KOywKo.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/anonymouspro/v12/rP2ap2a15UIB7Un-bOeISG3pHl4OTCzc6IG30KqB9Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Antic` extends GoogleFont {
    override lazy val family: String = "Antic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/antic/v10/TuGfUVB8XY5DRaZLodgzydtk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Antic Didone` extends GoogleFont {
    override lazy val family: String = "Antic Didone"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/anticdidone/v7/RWmPoKKX6u8sp8fIWdnDKqDiqYsGBGBzCw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Antic Slab` extends GoogleFont {
    override lazy val family: String = "Antic Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/anticslab/v7/bWt97fPFfRzkCa9Jlp6IWcJWXW5p5Qo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Anton` extends GoogleFont {
    override lazy val family: String = "Anton"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/anton/v10/1Ptgg87LROyAm0K08i4gS7lu.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Arapey` extends GoogleFont {
    override lazy val family: String = "Arapey"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arapey/v7/-W__XJn-UDDA2RC6Z9AcZkIzeg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/arapey/v7/-W_9XJn-UDDA2RCKZdoYREcjeo0k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Arbutus` extends GoogleFont {
    override lazy val family: String = "Arbutus"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arbutus/v8/NaPYcZ7dG_5J3poob9JtryO8fMU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Arbutus Slab` extends GoogleFont {
    override lazy val family: String = "Arbutus Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arbutusslab/v7/oY1Z8e7OuLXkJGbXtr5ba7ZVa68dJlaFAQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Architects Daughter` extends GoogleFont {
    override lazy val family: String = "Architects Daughter"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/architectsdaughter/v9/KtkxAKiDZI_td1Lkx62xHZHDtgO_Y-bvfY5q4szgE-Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Archivo` extends GoogleFont {
    override lazy val family: String = "Archivo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/archivo/v4/k3kQo8UDI-1M0wlSTd7iL0nAMaM.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/archivo/v4/k3kSo8UDI-1M0wlSfdzoK2vFIaOV8A.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/archivo/v4/k3kVo8UDI-1M0wlSdSrLC0HrLaqM6Q4.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/archivo/v4/k3kXo8UDI-1M0wlSfdzQ30LhKYiJ-Q7m8w.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/archivo/v4/k3kVo8UDI-1M0wlSdQbMC0HrLaqM6Q4.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/archivo/v4/k3kXo8UDI-1M0wlSfdzQ80XhKYiJ-Q7m8w.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/archivo/v4/k3kVo8UDI-1M0wlSdWLNC0HrLaqM6Q4.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/archivo/v4/k3kXo8UDI-1M0wlSfdzQl0ThKYiJ-Q7m8w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Archivo Black` extends GoogleFont {
    override lazy val family: String = "Archivo Black"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/archivoblack/v8/HTxqL289NzCGg4MzN6KJ7eW6OYuP_x7yx3A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Archivo Narrow` extends GoogleFont {
    override lazy val family: String = "Archivo Narrow"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/archivonarrow/v9/tss0ApVBdCYD5Q7hcxTE1ArZ0Yb3g31S2s8p.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/archivonarrow/v9/tss2ApVBdCYD5Q7hcxTE1ArZ0bb1iXlw398pJxk.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/archivonarrow/v9/tss3ApVBdCYD5Q7hcxTE1ArZ0b4Dqlla8dMgPgBu.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/archivonarrow/v9/tssxApVBdCYD5Q7hcxTE1ArZ0bb1sY1Z-9cCOxBu_BM.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/archivonarrow/v9/tss3ApVBdCYD5Q7hcxTE1ArZ0b4vrVla8dMgPgBu.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/archivonarrow/v9/tssxApVBdCYD5Q7hcxTE1ArZ0bb1saFe-9cCOxBu_BM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/archivonarrow/v9/tss3ApVBdCYD5Q7hcxTE1ArZ0b5LrFla8dMgPgBu.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/archivonarrow/v9/tssxApVBdCYD5Q7hcxTE1ArZ0bb1scVf-9cCOxBu_BM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Aref Ruqaa` extends GoogleFont {
    override lazy val family: String = "Aref Ruqaa"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arefruqaa/v7/WwkbxPW1E165rajQKDulEIAiVNo5xNY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arefruqaa/v7/WwkYxPW1E165rajQKDulKDwNcNIS2N_7Bdk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Arima Madurai` extends GoogleFont {
    override lazy val family: String = "Arima Madurai"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`, `tamil`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/arimamadurai/v4/t5t4IRoeKYORG0WNMgnC3seB1V3PqrGCch4Drg.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/arimamadurai/v4/t5t7IRoeKYORG0WNMgnC3seB1fHuipusfhcat2c.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/arimamadurai/v4/t5t7IRoeKYORG0WNMgnC3seB1ZXtipusfhcat2c.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arimamadurai/v4/t5tmIRoeKYORG0WNMgnC3seB7TnFrpOHYh4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/arimamadurai/v4/t5t7IRoeKYORG0WNMgnC3seB1c3sipusfhcat2c.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arimamadurai/v4/t5t7IRoeKYORG0WNMgnC3seB1YXqipusfhcat2c.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/arimamadurai/v4/t5t7IRoeKYORG0WNMgnC3seB1Znpipusfhcat2c.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/arimamadurai/v4/t5t7IRoeKYORG0WNMgnC3seB1b3oipusfhcat2c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `700`, `800`, `900`)
  }
  object `Arimo` extends GoogleFont {
    override lazy val family: String = "Arimo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `hebrew`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arimo/v12/P5sMzZCDf9_T_20eziBMjI-u.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/arimo/v12/P5sCzZCDf9_T_10cxCRuiZ-uydg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arimo/v12/P5sBzZCDf9_T_1Wi4QREp5On0ME2.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/arimo/v12/P5sHzZCDf9_T_10c_JhBrZeF1dE2PY4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Arizonia` extends GoogleFont {
    override lazy val family: String = "Arizonia"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arizonia/v9/neIIzCemt4A5qa7mv6WGHK06UY30.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Armata` extends GoogleFont {
    override lazy val family: String = "Armata"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/armata/v10/gokvH63_HV5jQ-E9lD53Q2u_mQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Arsenal` extends GoogleFont {
    override lazy val family: String = "Arsenal"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arsenal/v3/wXKrE3kQtZQ4pF3D11_WAewrhXY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/arsenal/v3/wXKpE3kQtZQ4pF3D513cBc4ulXYrtA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arsenal/v3/wXKuE3kQtZQ4pF3D7-P5JeQAmX8yrdk.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/arsenal/v3/wXKsE3kQtZQ4pF3D513kueEKnV03vdnKjw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Artifika` extends GoogleFont {
    override lazy val family: String = "Artifika"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/artifika/v9/VEMyRoxzronptCuxu6Wt5jDtreOL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Arvo` extends GoogleFont {
    override lazy val family: String = "Arvo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arvo/v11/tDbD2oWUg0MKmSAa7Lzr7vs.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/arvo/v11/tDbN2oWUg0MKqSIQ6J7u_vvijQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arvo/v11/tDbM2oWUg0MKoZw1yLTA8vL7lAE.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/arvo/v11/tDbO2oWUg0MKqSIoVLHK9tD-hAHkGg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Arya` extends GoogleFont {
    override lazy val family: String = "Arya"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/arya/v4/ga6CawNG-HJd9Ub1-beqdFE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/arya/v4/ga6NawNG-HJdzfra3b-BaFg3dRE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Asap` extends GoogleFont {
    override lazy val family: String = "Asap"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/asap/v9/KFOoCniXp96a-zwU4UROGzY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/asap/v9/KFOmCniXp96ayz4e5WZLCzYlKw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/asap/v9/KFOnCniXp96aw8g9xUxlBz88MsA.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/asap/v9/KFOlCniXp96ayz4mEU9vAx05IsDqlA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/asap/v9/KFOnCniXp96aw-Q6xUxlBz88MsA.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/asap/v9/KFOlCniXp96ayz4mPUhvAx05IsDqlA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/asap/v9/KFOnCniXp96aw4A7xUxlBz88MsA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/asap/v9/KFOlCniXp96ayz4mWUlvAx05IsDqlA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Asap Condensed` extends GoogleFont {
    override lazy val family: String = "Asap Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/asapcondensed/v3/pxidypY1o9NHyXh3WvSbGSggdNeLYk1Mq3ap.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/asapcondensed/v3/pxifypY1o9NHyXh3WvSbGSggdOeJaElurmapvvM.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/asapcondensed/v3/pxieypY1o9NHyXh3WvSbGSggdO9_S2lEgGqgp-pO.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/asapcondensed/v3/pxiYypY1o9NHyXh3WvSbGSggdOeJUL1Him6CovpOkXA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/asapcondensed/v3/pxieypY1o9NHyXh3WvSbGSggdO9TTGlEgGqgp-pO.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/asapcondensed/v3/pxiYypY1o9NHyXh3WvSbGSggdOeJUJFAim6CovpOkXA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/asapcondensed/v3/pxieypY1o9NHyXh3WvSbGSggdO83TWlEgGqgp-pO.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/asapcondensed/v3/pxiYypY1o9NHyXh3WvSbGSggdOeJUPVBim6CovpOkXA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Asar` extends GoogleFont {
    override lazy val family: String = "Asar"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/asar/v6/sZlLdRyI6TBIXkYQDLlTW6E.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Asset` extends GoogleFont {
    override lazy val family: String = "Asset"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/asset/v9/SLXGc1na-mM4cWImRJqExst1.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Assistant` extends GoogleFont {
    override lazy val family: String = "Assistant"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/assistant/v3/2sDZZGJYnIjSi6H75xk7p0ScA5cZbCjItw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/assistant/v3/2sDZZGJYnIjSi6H75xk7w0ecA5cZbCjItw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/assistant/v3/2sDcZGJYnIjSi6H75xkDb2-4C7wFZQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/assistant/v3/2sDZZGJYnIjSi6H75xk7t0GcA5cZbCjItw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/assistant/v3/2sDZZGJYnIjSi6H75xk700CcA5cZbCjItw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/assistant/v3/2sDZZGJYnIjSi6H75xk7z0OcA5cZbCjItw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `600`, `700`, `800`)
  }
  object `Astloch` extends GoogleFont {
    override lazy val family: String = "Astloch"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/astloch/v9/TuGRUVJ8QI5GSeUjq9wRzMtkH1Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/astloch/v9/TuGUUVJ8QI5GSeUjk2A-6MNPA10xLMQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Asul` extends GoogleFont {
    override lazy val family: String = "Asul"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/asul/v8/VuJ-dNjKxYr46fMFXK78JIg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/asul/v8/VuJxdNjKxYr40U8qeKbXOIFneRo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Athiti` extends GoogleFont {
    override lazy val family: String = "Athiti"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/athiti/v3/pe0sMISdLIZIv1wAxDNyAv2-C99ycg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/athiti/v3/pe0sMISdLIZIv1wAoDByAv2-C99ycg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/athiti/v3/pe0vMISdLIZIv1w4DBhWCtaiAg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/athiti/v3/pe0sMISdLIZIv1wA-DFyAv2-C99ycg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/athiti/v3/pe0sMISdLIZIv1wA1DZyAv2-C99ycg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/athiti/v3/pe0sMISdLIZIv1wAsDdyAv2-C99ycg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Atma` extends GoogleFont {
    override lazy val family: String = "Atma"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `bengali`: GoogleFontSubset = new GoogleFontSubset("bengali")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`bengali`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/atma/v4/uK_z4rqWc-Eoo8JzKjc9PvedRkM.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/atma/v4/uK_84rqWc-Eom25bDj8WIv4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/atma/v4/uK_z4rqWc-Eoo5pyKjc9PvedRkM.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/atma/v4/uK_z4rqWc-Eoo7Z1Kjc9PvedRkM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/atma/v4/uK_z4rqWc-Eoo9J0Kjc9PvedRkM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Atomic Age` extends GoogleFont {
    override lazy val family: String = "Atomic Age"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/atomicage/v11/f0Xz0eug6sdmRFkYZZGL58Ht9a8GYeA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Aubrey` extends GoogleFont {
    override lazy val family: String = "Aubrey"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/aubrey/v11/q5uGsou7NPBw-p7vugNsCxVEgA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Audiowide` extends GoogleFont {
    override lazy val family: String = "Audiowide"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/audiowide/v7/l7gdbjpo0cum0ckerWCtkQXPExpQBw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Autour One` extends GoogleFont {
    override lazy val family: String = "Autour One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/autourone/v8/UqyVK80cP25l3fJgbdfbk5lWVscxdKE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Average` extends GoogleFont {
    override lazy val family: String = "Average"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/average/v7/fC1hPYBHe23MxA7rIeJwVWytTyk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Average Sans` extends GoogleFont {
    override lazy val family: String = "Average Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/averagesans/v7/1Ptpg8fLXP2dlAXR-HlJJNJPBdqazVoK4A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Averia Gruesa Libre` extends GoogleFont {
    override lazy val family: String = "Averia Gruesa Libre"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/averiagruesalibre/v7/NGSov4nEGEktOaDRKsY-1dhh8eEtIx3ZUmmJw0SLRA8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Averia Libre` extends GoogleFont {
    override lazy val family: String = "Averia Libre"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/averialibre/v7/2V0FKIcMGZEnV6xygz7eNjEarovtb07t-pQgTw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/averialibre/v7/2V0HKIcMGZEnV6xygz7eNjESAJFhbUTp2JEwT4Sk.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/averialibre/v7/2V0aKIcMGZEnV6xygz7eNjEiAqPJZ2Xx8w.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/averialibre/v7/2V0EKIcMGZEnV6xygz7eNjESAKnNRWDh8405.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/averialibre/v7/2V0FKIcMGZEnV6xygz7eNjEavoztb07t-pQgTw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/averialibre/v7/2V0HKIcMGZEnV6xygz7eNjESAJFxakTp2JEwT4Sk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`)
  }
  object `Averia Sans Libre` extends GoogleFont {
    override lazy val family: String = "Averia Sans Libre"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/averiasanslibre/v7/ga6SaxZG_G5OvCf_rt7FH3B6BHLMEd3lMKcQJZP1LmD9.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/averiasanslibre/v7/ga6caxZG_G5OvCf_rt7FH3B6BHLMEdVLKisSL5fXK3D9qtg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/averiasanslibre/v7/ga6XaxZG_G5OvCf_rt7FH3B6BHLMEeVJGIMYDo_8.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/averiasanslibre/v7/ga6RaxZG_G5OvCf_rt7FH3B6BHLMEdVLEoc6C5_8N3k.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/averiasanslibre/v7/ga6SaxZG_G5OvCf_rt7FH3B6BHLMEd31N6cQJZP1LmD9.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/averiasanslibre/v7/ga6caxZG_G5OvCf_rt7FH3B6BHLMEdVLKjsVL5fXK3D9qtg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`)
  }
  object `Averia Serif Libre` extends GoogleFont {
    override lazy val family: String = "Averia Serif Libre"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/averiaseriflibre/v8/neIVzD2ms4wxr6GvjeD0X88SHPyX2xYGCSmqwacqdrKvbQ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/averiaseriflibre/v8/neIbzD2ms4wxr6GvjeD0X88SHPyX2xYOpzMmw60uVLe_bXHq.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/averiaseriflibre/v8/neIWzD2ms4wxr6GvjeD0X88SHPyX2xY-pQGOyYw2fw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/averiaseriflibre/v8/neIUzD2ms4wxr6GvjeD0X88SHPyX2xYOpwuK64kmf6u2.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/averiaseriflibre/v8/neIVzD2ms4wxr6GvjeD0X88SHPyX2xYGGS6qwacqdrKvbQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/averiaseriflibre/v8/neIbzD2ms4wxr6GvjeD0X88SHPyX2xYOpzM2xK0uVLe_bXHq.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`)
  }
  object `B612` extends GoogleFont {
    override lazy val family: String = "B612"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/b612/v3/3JnySDDxiSz32jm4GDigUXw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/b612/v3/3Jn8SDDxiSz36juyHBqlQXwdVw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/b612/v3/3Jn9SDDxiSz34oWXPDCLTXUETuE.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/b612/v3/3Jn_SDDxiSz36juKoDWBSVcBXuFb0Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `B612 Mono` extends GoogleFont {
    override lazy val family: String = "B612 Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/b612mono/v3/kmK_Zq85QVWbN1eW6lJl1wTcquRTtg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/b612mono/v3/kmK5Zq85QVWbN1eW6lJV1Q7YiOFDtqtf.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/b612mono/v3/kmK6Zq85QVWbN1eW6lJdayv4os9Pv7JGSg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/b612mono/v3/kmKkZq85QVWbN1eW6lJV1TZkp8VLnbdWSg4x.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Bad Script` extends GoogleFont {
    override lazy val family: String = "Bad Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/badscript/v7/6NUT8F6PJgbFWQn47_x7lOwuzd1AZtw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bahiana` extends GoogleFont {
    override lazy val family: String = "Bahiana"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bahiana/v3/uU9PCBUV4YenPWJU7xPb3vyHmlI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bahianita` extends GoogleFont {
    override lazy val family: String = "Bahianita"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bahianita/v1/yYLr0hTb3vuqqsBUgxWtxTvV2NJPcA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bai Jamjuree` extends GoogleFont {
    override lazy val family: String = "Bai Jamjuree"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/baijamjuree/v2/LDIqapSCOBt_aeQQ7ftydoa0kePuk5A1-yiSgA.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/baijamjuree/v2/LDIoapSCOBt_aeQQ7ftydoa8W_oGkpox2S2CgOva.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/baijamjuree/v2/LDIqapSCOBt_aeQQ7ftydoa09eDuk5A1-yiSgA.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/baijamjuree/v2/LDIoapSCOBt_aeQQ7ftydoa8W_pikZox2S2CgOva.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baijamjuree/v2/LDI1apSCOBt_aeQQ7ftydoaMWcjKm7sp8g.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/baijamjuree/v2/LDIrapSCOBt_aeQQ7ftydoa8W8LOub458jGL.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/baijamjuree/v2/LDIqapSCOBt_aeQQ7ftydoa0reHuk5A1-yiSgA.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/baijamjuree/v2/LDIoapSCOBt_aeQQ7ftydoa8W_o6kJox2S2CgOva.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/baijamjuree/v2/LDIqapSCOBt_aeQQ7ftydoa0gebuk5A1-yiSgA.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/baijamjuree/v2/LDIoapSCOBt_aeQQ7ftydoa8W_oWl5ox2S2CgOva.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/baijamjuree/v2/LDIqapSCOBt_aeQQ7ftydoa05efuk5A1-yiSgA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/baijamjuree/v2/LDIoapSCOBt_aeQQ7ftydoa8W_pylpox2S2CgOva.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Baloo` extends GoogleFont {
    override lazy val family: String = "Baloo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloo/v4/6xKhdSpJJ92I9PWIAG_5LWwJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Bhai` extends GoogleFont {
    override lazy val family: String = "Baloo Bhai"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`, `gujarati`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloobhai/v4/ZgNWjP5GM7bCUdmXgWyVjGXEM4COoE4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Bhaijaan` extends GoogleFont {
    override lazy val family: String = "Baloo Bhaijaan"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloobhaijaan/v5/RWmRoKCU5fcqq8fOWNzFLqSjx4ECJmVjC0-V.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Bhaina` extends GoogleFont {
    override lazy val family: String = "Baloo Bhaina"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `oriya`: GoogleFontSubset = new GoogleFontSubset("oriya")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`oriya`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloobhaina/v4/Noa16Uzzzp2FIkfhq5vm9thxPAR9mhHobg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Chettan` extends GoogleFont {
    override lazy val family: String = "Baloo Chettan"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `malayalam`: GoogleFontSubset = new GoogleFontSubset("malayalam")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`, `malayalam`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloochettan/v4/0QImMXRN8o2gTC2YTr4665DA07z8_ApHqqk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Da` extends GoogleFont {
    override lazy val family: String = "Baloo Da"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `bengali`: GoogleFontSubset = new GoogleFontSubset("bengali")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`bengali`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/balooda/v4/LhWmMVnXOfIZO795FXkfSvtVM8mZ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Paaji` extends GoogleFont {
    override lazy val family: String = "Baloo Paaji"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `gurmukhi`: GoogleFontSubset = new GoogleFontSubset("gurmukhi")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`gurmukhi`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloopaaji/v5/8AttGsyxM5KQQU-Y4MTwVZnToxvQBiot.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Tamma` extends GoogleFont {
    override lazy val family: String = "Baloo Tamma"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `kannada`: GoogleFontSubset = new GoogleFontSubset("kannada")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`kannada`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/balootamma/v5/JTUTjIk68Cy27gWhOWIghE5B5Arr-s50.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Tammudu` extends GoogleFont {
    override lazy val family: String = "Baloo Tammudu"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `telugu`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/balootammudu/v5/mFT3Wb8Qza7c_Z5HTsC_5nxW8EpQl9RWxD8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Baloo Thambi` extends GoogleFont {
    override lazy val family: String = "Baloo Thambi"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`, `tamil`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baloothambi/v4/va9B4kXJzNhTFoA7CYcS8sHuQR37fF3Wlg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Balthazar` extends GoogleFont {
    override lazy val family: String = "Balthazar"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/balthazar/v8/d6lKkaajS8Gm4CVQjFEvyRTo39l8hw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bangers` extends GoogleFont {
    override lazy val family: String = "Bangers"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bangers/v11/FeVQS0BTqb0h60ACL5la2bxii28.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Barlow` extends GoogleFont {
    override lazy val family: String = "Barlow"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/barlow/v3/7cHrv4kjgoGqM7E3b8s8yn4hnCci.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/barlow/v3/7cHtv4kjgoGqM7E_CfNYwHoDmTcibrA.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/barlow/v3/7cHqv4kjgoGqM7E3w-oc4FAtlT47dw.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/barlow/v3/7cHsv4kjgoGqM7E_CfP04Voptzsrd6m9.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/barlow/v3/7cHqv4kjgoGqM7E3p-kc4FAtlT47dw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/barlow/v3/7cHsv4kjgoGqM7E_CfOQ4loptzsrd6m9.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/barlow/v3/7cHpv4kjgoGqM7EPC8E46HsxnA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/barlow/v3/7cHrv4kjgoGqM7E_Ccs8yn4hnCci.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/barlow/v3/7cHqv4kjgoGqM7E3_-gc4FAtlT47dw.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/barlow/v3/7cHsv4kjgoGqM7E_CfPI41optzsrd6m9.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/barlow/v3/7cHqv4kjgoGqM7E30-8c4FAtlT47dw.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/barlow/v3/7cHsv4kjgoGqM7E_CfPk5Foptzsrd6m9.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/barlow/v3/7cHqv4kjgoGqM7E3t-4c4FAtlT47dw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/barlow/v3/7cHsv4kjgoGqM7E_CfOA5Voptzsrd6m9.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/barlow/v3/7cHqv4kjgoGqM7E3q-0c4FAtlT47dw.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/barlow/v3/7cHsv4kjgoGqM7E_CfOc5loptzsrd6m9.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/barlow/v3/7cHqv4kjgoGqM7E3j-wc4FAtlT47dw.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/barlow/v3/7cHsv4kjgoGqM7E_CfO451optzsrd6m9.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Barlow Condensed` extends GoogleFont {
    override lazy val family: String = "Barlow Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/barlowcondensed/v3/HTxxL3I-JCGChYJ8VI-L6OO_au7B43LT31vytKgbaw.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/barlowcondensed/v3/HTxzL3I-JCGChYJ8VI-L6OO_au7B6xTru1H2lq0La6JN.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/barlowcondensed/v3/HTxwL3I-JCGChYJ8VI-L6OO_au7B497y_3HcuKECcrs.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/barlowcondensed/v3/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrF3DWvIMHYrtUxg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/barlowcondensed/v3/HTxwL3I-JCGChYJ8VI-L6OO_au7B47rx_3HcuKECcrs.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/barlowcondensed/v3/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrc3PWvIMHYrtUxg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/barlowcondensed/v3/HTx3L3I-JCGChYJ8VI-L6OO_au7B2xbZ23n3pKg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/barlowcondensed/v3/HTxxL3I-JCGChYJ8VI-L6OO_au7B6xTT31vytKgbaw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/barlowcondensed/v3/HTxwL3I-JCGChYJ8VI-L6OO_au7B4-Lw_3HcuKECcrs.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/barlowcondensed/v3/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrK3LWvIMHYrtUxg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/barlowcondensed/v3/HTxwL3I-JCGChYJ8VI-L6OO_au7B4873_3HcuKECcrs.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/barlowcondensed/v3/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrB3XWvIMHYrtUxg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/barlowcondensed/v3/HTxwL3I-JCGChYJ8VI-L6OO_au7B46r2_3HcuKECcrs.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/barlowcondensed/v3/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrY3TWvIMHYrtUxg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/barlowcondensed/v3/HTxwL3I-JCGChYJ8VI-L6OO_au7B47b1_3HcuKECcrs.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/barlowcondensed/v3/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrf3fWvIMHYrtUxg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/barlowcondensed/v3/HTxwL3I-JCGChYJ8VI-L6OO_au7B45L0_3HcuKECcrs.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/barlowcondensed/v3/HTxyL3I-JCGChYJ8VI-L6OO_au7B6xTrW3bWvIMHYrtUxg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Barlow Semi Condensed` extends GoogleFont {
    override lazy val family: String = "Barlow Semi Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/barlowsemicondensed/v4/wlphgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRfG4qvKk8ogoSP.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/barlowsemicondensed/v4/wlpjgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbLLIEsKh5SPZWs.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/barlowsemicondensed/v4/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRft6uPAGEki52WfA.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/barlowsemicondensed/v4/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbJnAWsgqZiGfHK5.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/barlowsemicondensed/v4/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRf06iPAGEki52WfA.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/barlowsemicondensed/v4/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbIDAmsgqZiGfHK5.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/barlowsemicondensed/v4/wlpvgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRnf4CrCEo4gg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/barlowsemicondensed/v4/wlphgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfYqvKk8ogoSP.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/barlowsemicondensed/v4/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRfi6mPAGEki52WfA.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/barlowsemicondensed/v4/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbJbA2sgqZiGfHK5.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/barlowsemicondensed/v4/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRfp66PAGEki52WfA.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/barlowsemicondensed/v4/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbJ3BGsgqZiGfHK5.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/barlowsemicondensed/v4/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRfw6-PAGEki52WfA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/barlowsemicondensed/v4/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbITBWsgqZiGfHK5.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/barlowsemicondensed/v4/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRf36yPAGEki52WfA.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/barlowsemicondensed/v4/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbIPBmsgqZiGfHK5.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/barlowsemicondensed/v4/wlpigxjLBV1hqnzfr-F8sEYMB0Yybp0mudRf-62PAGEki52WfA.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/barlowsemicondensed/v4/wlpkgxjLBV1hqnzfr-F8sEYMB0Yybp0mudRXfbIrB2sgqZiGfHK5.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Barriecito` extends GoogleFont {
    override lazy val family: String = "Barriecito"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/barriecito/v1/WWXXlj-CbBOSLY2QTuY_KdUiYwTO0MU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Barrio` extends GoogleFont {
    override lazy val family: String = "Barrio"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/barrio/v3/wEO8EBXBk8hBIDiEdQYhWdsX1Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Basic` extends GoogleFont {
    override lazy val family: String = "Basic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/basic/v8/xfu_0WLxV2_XKQN34lDVyR7D.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Battambang` extends GoogleFont {
    override lazy val family: String = "Battambang"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/battambang/v12/uk-mEGe7raEw-HjkzZabDnWj4yxx7o8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/battambang/v12/uk-lEGe7raEw-HjkzZabNsmMxyRa8oZK9I0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Baumans` extends GoogleFont {
    override lazy val family: String = "Baumans"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/baumans/v8/-W_-XJj9QyTd3QfpR_oyaksqY5Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bayon` extends GoogleFont {
    override lazy val family: String = "Bayon"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bayon/v12/9XUrlJNmn0LPFl-pOhYEd2NJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Belgrano` extends GoogleFont {
    override lazy val family: String = "Belgrano"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/belgrano/v9/55xvey5tM9rwKWrJZcMFirl08KDJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bellefair` extends GoogleFont {
    override lazy val family: String = "Bellefair"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bellefair/v4/kJExBuYY6AAuhiXUxG19__A2pOdvDA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Belleza` extends GoogleFont {
    override lazy val family: String = "Belleza"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/belleza/v7/0nkoC9_pNeMfhX4BtcbyawzruP8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `BenchNine` extends GoogleFont {
    override lazy val family: String = "BenchNine"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/benchnine/v7/ahcev8612zF4jxrwMosT--tRhWa8q0v8ag.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/benchnine/v7/ahcbv8612zF4jxrwMosrV8N1jU2gog.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/benchnine/v7/ahcev8612zF4jxrwMosT6-xRhWa8q0v8ag.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Bentham` extends GoogleFont {
    override lazy val family: String = "Bentham"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bentham/v9/VdGeAZQPEpYfmHglKWw7CJaK_y4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Berkshire Swash` extends GoogleFont {
    override lazy val family: String = "Berkshire Swash"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/berkshireswash/v7/ptRRTi-cavZOGqCvnNJDl5m5XmNPrcQybX4pQA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bevan` extends GoogleFont {
    override lazy val family: String = "Bevan"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bevan/v10/4iCj6KZ0a9NXjF8aUir7tlSJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bigelow Rules` extends GoogleFont {
    override lazy val family: String = "Bigelow Rules"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bigelowrules/v7/RrQWboly8iR_I3KWSzeRuN0zT4cCH8WAJVk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bigshot One` extends GoogleFont {
    override lazy val family: String = "Bigshot One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bigshotone/v9/u-470qukhRkkO6BD_7cM_gxuUQJBXv_-.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bilbo` extends GoogleFont {
    override lazy val family: String = "Bilbo"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bilbo/v8/o-0EIpgpwWwZ210hpIRz4wxE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bilbo Swash Caps` extends GoogleFont {
    override lazy val family: String = "Bilbo Swash Caps"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bilboswashcaps/v11/zrf-0GXbz-H3Wb4XBsGrTgq2PVmdqAPopiRfKp8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `BioRhyme` extends GoogleFont {
    override lazy val family: String = "BioRhyme"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/biorhyme/v3/1cX3aULHBpDMsHYW_ESOjnGAq8Sk1PoH.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/biorhyme/v3/1cX3aULHBpDMsHYW_ETqjXGAq8Sk1PoH.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/biorhyme/v3/1cXwaULHBpDMsHYW_HxGpVWIgNit.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/biorhyme/v3/1cX3aULHBpDMsHYW_ET6inGAq8Sk1PoH.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/biorhyme/v3/1cX3aULHBpDMsHYW_ETmiXGAq8Sk1PoH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `700`, `800`)
  }
  object `BioRhyme Expanded` extends GoogleFont {
    override lazy val family: String = "BioRhyme Expanded"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/biorhymeexpanded/v4/i7dVIE1zZzytGswgU577CDY9LjbffxxcblSHSdTXrb_z.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/biorhymeexpanded/v4/i7dVIE1zZzytGswgU577CDY9Ljbffxw4bVSHSdTXrb_z.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/biorhymeexpanded/v4/i7dQIE1zZzytGswgU577CDY9LjbffySURXCPYsje.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/biorhymeexpanded/v4/i7dVIE1zZzytGswgU577CDY9LjbffxwoalSHSdTXrb_z.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/biorhymeexpanded/v4/i7dVIE1zZzytGswgU577CDY9Ljbffxw0aVSHSdTXrb_z.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `700`, `800`)
  }
  object `Biryani` extends GoogleFont {
    override lazy val family: String = "Biryani"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/biryani/v4/hv-TlzNxIFoO84YddYQyGTBSU-J-RxQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/biryani/v4/hv-TlzNxIFoO84YddeAxGTBSU-J-RxQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/biryani/v4/hv-WlzNxIFoO84YdTUwZPTh5T-s.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/biryani/v4/hv-TlzNxIFoO84YddZQ3GTBSU-J-RxQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/biryani/v4/hv-TlzNxIFoO84YddfA2GTBSU-J-RxQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/biryani/v4/hv-TlzNxIFoO84Yddew1GTBSU-J-RxQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/biryani/v4/hv-TlzNxIFoO84Yddcg0GTBSU-J-RxQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `600`, `700`, `800`, `900`)
  }
  object `Bitter` extends GoogleFont {
    override lazy val family: String = "Bitter"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bitter/v14/rax8HiqOu8IVPmnLeIZoDDlCmg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/bitter/v14/rax-HiqOu8IVPmn7eoxsLjxSmlLZ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/bitter/v14/rax_HiqOu8IVPmnzxKlMBBJek0vA8A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Black And White Picture` extends GoogleFont {
    override lazy val family: String = "Black And White Picture"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/blackandwhitepicture/v7/TwMe-JAERlQd3ooUHBUXGmrmioKjjnRSFO-NqI5HbcMi-yWY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Black Han Sans` extends GoogleFont {
    override lazy val family: String = "Black Han Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/blackhansans/v7/ea8Aad44WunzF9a-dL6toA8r8nqVIXSkH-Hc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Black Ops One` extends GoogleFont {
    override lazy val family: String = "Black Ops One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/blackopsone/v10/qWcsB6-ypo7xBdr6Xshe96H3WDzRtjkho4M.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bokor` extends GoogleFont {
    override lazy val family: String = "Bokor"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bokor/v11/m8JcjfpeeaqTiR2WdInbcaxE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bonbon` extends GoogleFont {
    override lazy val family: String = "Bonbon"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bonbon/v10/0FlVVPeVlFec4ee_cDEAbQY5-A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Boogaloo` extends GoogleFont {
    override lazy val family: String = "Boogaloo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/boogaloo/v10/kmK-Zq45GAvOdnaW6x1F_SrQo_1K.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bowlby One` extends GoogleFont {
    override lazy val family: String = "Bowlby One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bowlbyone/v10/taiPGmVuC4y96PFeqp8smo6C_Z0wcK4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bowlby One SC` extends GoogleFont {
    override lazy val family: String = "Bowlby One SC"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bowlbyonesc/v10/DtVlJxerQqQm37tzN3wMug9Pzgj8owhNjuE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Brawler` extends GoogleFont {
    override lazy val family: String = "Brawler"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/brawler/v9/xn7gYHE3xXewAscGsgC7S9XdZN8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bree Serif` extends GoogleFont {
    override lazy val family: String = "Bree Serif"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/breeserif/v8/4UaHrEJCrhhnVA3DgluAx63j5pN1MwI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bubblegum Sans` extends GoogleFont {
    override lazy val family: String = "Bubblegum Sans"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bubblegumsans/v7/AYCSpXb_Z9EORv1M5QTjEzMEtdaHzoPPb7R4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bubbler One` extends GoogleFont {
    override lazy val family: String = "Bubbler One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bubblerone/v7/f0Xy0eqj68ppQV9KBLmAouHH26MPePkt.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Buda` extends GoogleFont {
    override lazy val family: String = "Buda"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/buda/v9/GFDqWAN8mnyIJSSrG7UBr7pZKA0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`)
  }
  object `Buenard` extends GoogleFont {
    override lazy val family: String = "Buenard"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/buenard/v10/OD5DuM6Cyma8FnnsPzf9qGi9HL4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/buenard/v10/OD5GuM6Cyma8FnnsB4vSjGCWALepwss.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Bungee` extends GoogleFont {
    override lazy val family: String = "Bungee"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bungee/v4/N0bU2SZBIuF2PU_ECn50Kd_PmA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bungee Hairline` extends GoogleFont {
    override lazy val family: String = "Bungee Hairline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bungeehairline/v4/snfys0G548t04270a_ljTLUVrv-7YB2dQ5ZPqQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bungee Inline` extends GoogleFont {
    override lazy val family: String = "Bungee Inline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bungeeinline/v4/Gg8zN58UcgnlCweMrih332VuDGJ1-FEglsc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bungee Outline` extends GoogleFont {
    override lazy val family: String = "Bungee Outline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bungeeoutline/v4/_6_mEDvmVP24UvU2MyiGDslL3Qg3YhJqPXxo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Bungee Shade` extends GoogleFont {
    override lazy val family: String = "Bungee Shade"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/bungeeshade/v4/DtVkJxarWL0t2KdzK3oI_jks7iLSrwFUlw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Butcherman` extends GoogleFont {
    override lazy val family: String = "Butcherman"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/butcherman/v10/2EbiL-thF0loflXUBOdb1zWzq_5uT84.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Butterfly Kids` extends GoogleFont {
    override lazy val family: String = "Butterfly Kids"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/butterflykids/v7/ll8lK2CWTjuqAsXDqlnIbMNs5S4arxFrAX1D.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cabin` extends GoogleFont {
    override lazy val family: String = "Cabin"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cabin/v13/u-4x0qWljRw-Pe839fxqmjRv.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cabin/v13/u-4_0qWljRw-Pd81__hInyRvYwc.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cabin/v13/u-480qWljRw-PdfD3NhisShmeh5I.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/cabin/v13/u-460qWljRw-Pd81xwxhuyxEfw5IR-Y.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cabin/v13/u-480qWljRw-Pdfv29hisShmeh5I.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/cabin/v13/u-460qWljRw-Pd81xyBmuyxEfw5IR-Y.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cabin/v13/u-480qWljRw-PdeL2thisShmeh5I.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cabin/v13/u-460qWljRw-Pd81x0RnuyxEfw5IR-Y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Cabin Condensed` extends GoogleFont {
    override lazy val family: String = "Cabin Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cabincondensed/v12/nwpMtK6mNhBK2err_hqkYhHRqmwaYOjZ5HZl8Q.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cabincondensed/v12/nwpJtK6mNhBK2err_hqkYhHRqmwilMH97F15-K1oqQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cabincondensed/v12/nwpJtK6mNhBK2err_hqkYhHRqmwiuMb97F15-K1oqQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cabincondensed/v12/nwpJtK6mNhBK2err_hqkYhHRqmwi3Mf97F15-K1oqQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`)
  }
  object `Cabin Sketch` extends GoogleFont {
    override lazy val family: String = "Cabin Sketch"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cabinsketch/v12/QGYpz_kZZAGCONcK2A4bGOjMn9JM6fnuKg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cabinsketch/v12/QGY2z_kZZAGCONcK2A4bGOj0I_1o4dLyI4CMFw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Caesar Dressing` extends GoogleFont {
    override lazy val family: String = "Caesar Dressing"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/caesardressing/v7/yYLx0hLa3vawqtwdswbotmK4vrR3cbb6LZttyg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cagliostro` extends GoogleFont {
    override lazy val family: String = "Cagliostro"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cagliostro/v7/ZgNWjP5HM73BV5amnX-TjGXEM4COoE4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cairo` extends GoogleFont {
    override lazy val family: String = "Cairo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/cairo/v5/SLXLc1nY6Hkvalrub76M7dd8aGZk.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cairo/v5/SLXLc1nY6HkvalqKbL6M7dd8aGZk.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cairo/v5/SLXGc1nY6HkvamImRJqExst1.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cairo/v5/SLXLc1nY6Hkvalr-ar6M7dd8aGZk.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cairo/v5/SLXLc1nY6Hkvalqaa76M7dd8aGZk.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/cairo/v5/SLXLc1nY6Hkvalqiab6M7dd8aGZk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `600`, `700`, `900`)
  }
  object `Calligraffitti` extends GoogleFont {
    override lazy val family: String = "Calligraffitti"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/calligraffitti/v10/46k2lbT3XjDVqJw3DCmCFjE0vnFZM5ZBpYN-.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cambay` extends GoogleFont {
    override lazy val family: String = "Cambay"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cambay/v5/SLXJc1rY6H0_ZDsGbrSIz9JsaA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cambay/v5/SLXLc1rY6H0_ZDs2bL6M7dd8aGZk.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cambay/v5/SLXKc1rY6H0_ZDs-0pusx_lwYX99kA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cambay/v5/SLXMc1rY6H0_ZDs2bIYwwvN0Q3ptkDMN.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Cambo` extends GoogleFont {
    override lazy val family: String = "Cambo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cambo/v7/IFSqHeNEk8FJk416ok7xkPm8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Candal` extends GoogleFont {
    override lazy val family: String = "Candal"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/candal/v8/XoHn2YH6T7-t_8cNAR4Jt9Yxlw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cantarell` extends GoogleFont {
    override lazy val family: String = "Cantarell"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cantarell/v8/B50NF7ZDq37KMUvlO01Ji6hqHK-CLA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cantarell/v8/B50LF7ZDq37KMUvlO015iaJuPqqSLJYf.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cantarell/v8/B50IF7ZDq37KMUvlO01xN4dOFISeJY8GgQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cantarell/v8/B50WF7ZDq37KMUvlO015iZrSEY6aB4oWgWHB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Cantata One` extends GoogleFont {
    override lazy val family: String = "Cantata One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cantataone/v8/PlI5Fl60Nb5obNzNe2jslVxEt8CwfGaD.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cantora One` extends GoogleFont {
    override lazy val family: String = "Cantora One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cantoraone/v8/gyB4hws1JdgnKy56GB_JX6zdZ4vZVbgZ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Capriola` extends GoogleFont {
    override lazy val family: String = "Capriola"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/capriola/v6/wXKoE3YSppcvo1PDln_8L-AinG8y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cardo` extends GoogleFont {
    override lazy val family: String = "Cardo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cardo/v10/wlp_gwjKBV1pqiv_1oAZ2H5O.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cardo/v10/wlpxgwjKBV1pqhv93IQ73W5OcCk.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cardo/v10/wlpygwjKBV1pqhND-aQR82JHaTBX.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Carme` extends GoogleFont {
    override lazy val family: String = "Carme"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/carme/v9/ptRHTiWdbvZIDOjGxLNrxfbZ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Carrois Gothic` extends GoogleFont {
    override lazy val family: String = "Carrois Gothic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/carroisgothic/v9/Z9XPDmFATg-N1PLtLOOxvIHl9ZmD3i7ajcJ-.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Carrois Gothic SC` extends GoogleFont {
    override lazy val family: String = "Carrois Gothic SC"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/carroisgothicsc/v8/ZgNJjOVHM6jfUZCmyUqT2A2HVKjc-28nNHabY4dN.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Carter One` extends GoogleFont {
    override lazy val family: String = "Carter One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/carterone/v10/q5uCsoe5IOB2-pXv9UcNIxR2hYxREMs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Catamaran` extends GoogleFont {
    override lazy val family: String = "Catamaran"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `tamil`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/catamaran/v5/o-0OIpQoyXQa2RxT7-5jhjRFSfiM7HBj.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/catamaran/v5/o-0NIpQoyXQa2RxT7-5jKhVlY9aA5Wl6PQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/catamaran/v5/o-0NIpQoyXQa2RxT7-5jThZlY9aA5Wl6PQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/catamaran/v5/o-0IIpQoyXQa2RxT7-5b4j5Ba_2c7A.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/catamaran/v5/o-0NIpQoyXQa2RxT7-5jFhdlY9aA5Wl6PQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/catamaran/v5/o-0NIpQoyXQa2RxT7-5jOhBlY9aA5Wl6PQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/catamaran/v5/o-0NIpQoyXQa2RxT7-5jXhFlY9aA5Wl6PQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/catamaran/v5/o-0NIpQoyXQa2RxT7-5jQhJlY9aA5Wl6PQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/catamaran/v5/o-0NIpQoyXQa2RxT7-5jZhNlY9aA5Wl6PQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Caudex` extends GoogleFont {
    override lazy val family: String = "Caudex"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/caudex/v8/esDQ311QOP6BJUrIyviAnb4eEw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/caudex/v8/esDS311QOP6BJUr4yPKEv7sOE4in.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/caudex/v8/esDT311QOP6BJUrwdteklZUCGpG-GQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/caudex/v8/esDV311QOP6BJUr4yMo4kJ8GOJSuGdLB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Caveat` extends GoogleFont {
    override lazy val family: String = "Caveat"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/caveat/v6/Wnz6HAc5bAfYB2QLYTwZqg_MPQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/caveat/v6/Wnz5HAc5bAfYB2Qz3RM9oiTQNAuxjA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Caveat Brush` extends GoogleFont {
    override lazy val family: String = "Caveat Brush"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/caveatbrush/v4/EYq0maZfwr9S9-ETZc3fKXtMW7mT03pdQw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cedarville Cursive` extends GoogleFont {
    override lazy val family: String = "Cedarville Cursive"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cedarvillecursive/v10/yYL00g_a2veiudhUmxjo5VKkoqA-B_neJbBxw8BeTg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ceviche One` extends GoogleFont {
    override lazy val family: String = "Ceviche One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cevicheone/v9/gyB4hws1IcA6JzR-GB_JX6zdZ4vZVbgZ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Chakra Petch` extends GoogleFont {
    override lazy val family: String = "Chakra Petch"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/chakrapetch/v2/cIflMapbsEk7TDLdtEz1BwkeNIhFQJXE3AY00g.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/chakrapetch/v2/cIfnMapbsEk7TDLdtEz1BwkWmpLJQp_A_gMk0izH.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chakrapetch/v2/cIf6MapbsEk7TDLdtEz1BwkmmKBhSL7Y1Q.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/chakrapetch/v2/cIfkMapbsEk7TDLdtEz1BwkWmqplarvI1R8t.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/chakrapetch/v2/cIflMapbsEk7TDLdtEz1BwkebIlFQJXE3AY00g.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/chakrapetch/v2/cIfnMapbsEk7TDLdtEz1BwkWmpKRQ5_A_gMk0izH.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/chakrapetch/v2/cIflMapbsEk7TDLdtEz1BwkeQI5FQJXE3AY00g.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/chakrapetch/v2/cIfnMapbsEk7TDLdtEz1BwkWmpK9RJ_A_gMk0izH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/chakrapetch/v2/cIflMapbsEk7TDLdtEz1BwkeJI9FQJXE3AY00g.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/chakrapetch/v2/cIfnMapbsEk7TDLdtEz1BwkWmpLZRZ_A_gMk0izH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Changa` extends GoogleFont {
    override lazy val family: String = "Changa"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/changa/v6/2-cl9JNi2YuVOUcsqb2bUsT5rZhaZg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/changa/v6/2-cl9JNi2YuVOUcszb6bUsT5rZhaZg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/changa/v6/2-cm9JNi2YuVOUcUYZa_Wu_lpA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/changa/v6/2-cl9JNi2YuVOUcslb-bUsT5rZhaZg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/changa/v6/2-cl9JNi2YuVOUcsubibUsT5rZhaZg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/changa/v6/2-cl9JNi2YuVOUcs3bmbUsT5rZhaZg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/changa/v6/2-cl9JNi2YuVOUcswbqbUsT5rZhaZg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Changa One` extends GoogleFont {
    override lazy val family: String = "Changa One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/changaone/v11/xfu00W3wXn3QLUJXhzq46AbouLfbK64.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/changaone/v11/xfu20W3wXn3QLUJXhzq42ATivJXeO67ISw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Chango` extends GoogleFont {
    override lazy val family: String = "Chango"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chango/v7/2V0cKI0OB5U7WaJyz324TFUaAw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Charm` extends GoogleFont {
    override lazy val family: String = "Charm"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/charm/v3/7cHmv4oii5K0MeYvIe804WIo.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/charm/v3/7cHrv4oii5K0Md6TDss8yn4hnCci.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Charmonman` extends GoogleFont {
    override lazy val family: String = "Charmonman"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/charmonman/v2/MjQDmiR3vP_nuxDv47jiWJGovLdh6OE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/charmonman/v2/MjQAmiR3vP_nuxDv47jiYC2HmL9K9OhmGnY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Chathura` extends GoogleFont {
    override lazy val family: String = "Chathura"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/chathura/v4/_gP91R7-rzUuVjim42dEq0SbTvZyuDo.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/chathura/v4/_gP81R7-rzUuVjim42eMiWSxYPp7oSNy.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chathura/v4/_gP71R7-rzUuVjim418goUC5S-Zy.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/chathura/v4/_gP81R7-rzUuVjim42ecjmSxYPp7oSNy.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/chathura/v4/_gP81R7-rzUuVjim42eAjWSxYPp7oSNy.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `700`, `800`)
  }
  object `Chau Philomene One` extends GoogleFont {
    override lazy val family: String = "Chau Philomene One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chauphilomeneone/v8/55xxezRsPtfie1vPY49qzdgSlJiHRQFsnIx7QMISdQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/chauphilomeneone/v8/55xzezRsPtfie1vPY49qzdgSlJiHRQFcnoZ_YscCdXQB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Chela One` extends GoogleFont {
    override lazy val family: String = "Chela One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chelaone/v7/6ae-4KC7Uqgdz_JZdPIy31vWNTMwoQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Chelsea Market` extends GoogleFont {
    override lazy val family: String = "Chelsea Market"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chelseamarket/v6/BCawqZsHqfr89WNP_IApC8tzKBhlLA4uKkWk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Chenla` extends GoogleFont {
    override lazy val family: String = "Chenla"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chenla/v11/SZc43FDpIKu8WZ9eXxfonUPL6Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cherry Cream Soda` extends GoogleFont {
    override lazy val family: String = "Cherry Cream Soda"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cherrycreamsoda/v9/UMBIrOxBrW6w2FFyi9paG0fdVdRciTd6Cd47DJ7G.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cherry Swash` extends GoogleFont {
    override lazy val family: String = "Cherry Swash"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cherryswash/v7/i7dNIFByZjaNAMxtZcnfAy58QHi-EwWMbg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cherryswash/v7/i7dSIFByZjaNAMxtZcnfAy5E_FeaGy6QZ3WfYg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Chewy` extends GoogleFont {
    override lazy val family: String = "Chewy"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chewy/v10/uK_94ruUb-k-wk5xIDMfO-ed.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Chicle` extends GoogleFont {
    override lazy val family: String = "Chicle"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chicle/v7/lJwG-pw9i2dqU-BDyWKuobYSxw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Chivo` extends GoogleFont {
    override lazy val family: String = "Chivo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/chivo/v10/va9F4kzIxd1KFrjDY8Z_uqzGQC_-.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/chivo/v10/va9D4kzIxd1KFrBteUp9sKjkRT_-bF0.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chivo/v10/va9I4kzIxd1KFoBvS-J3kbDP.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/chivo/v10/va9G4kzIxd1KFrBtQeZVlKDPWTY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/chivo/v10/va9F4kzIxd1KFrjTZMZ_uqzGQC_-.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/chivo/v10/va9D4kzIxd1KFrBteVp6sKjkRT_-bF0.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/chivo/v10/va9F4kzIxd1KFrjrZsZ_uqzGQC_-.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/chivo/v10/va9D4kzIxd1KFrBteWJ4sKjkRT_-bF0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Chonburi` extends GoogleFont {
    override lazy val family: String = "Chonburi"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/chonburi/v3/8AtqGs-wOpGRTBq66IWaFr3biAfZ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cinzel` extends GoogleFont {
    override lazy val family: String = "Cinzel"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cinzel/v8/8vIJ7ww63mVu7gtL8W76HEdHMg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cinzel/v8/8vIK7ww63mVu7gtzTUHeFGxbO_zo-w.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/cinzel/v8/8vIK7ww63mVu7gtzdUPeFGxbO_zo-w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `900`)
  }
  object `Cinzel Decorative` extends GoogleFont {
    override lazy val family: String = "Cinzel Decorative"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cinzeldecorative/v7/daaCSScvJGqLYhG8nNt8KPPswUAPnh7URs1LaCyC.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cinzeldecorative/v7/daaHSScvJGqLYhG8nNt8KPPswUAPniZoaelDQzCLlQXE.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/cinzeldecorative/v7/daaHSScvJGqLYhG8nNt8KPPswUAPniZQa-lDQzCLlQXE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `900`)
  }
  object `Clicker Script` extends GoogleFont {
    override lazy val family: String = "Clicker Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/clickerscript/v6/raxkHiKPvt8CMH6ZWP8PdlEq72rY2zqUKafv.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Coda` extends GoogleFont {
    override lazy val family: String = "Coda"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/coda/v14/SLXHc1jY5nQ8JUIMapaN39I.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/coda/v14/SLXIc1jY5nQ8HeIgTp6mw9t1cX8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `800`)
  }
  object `Coda Caption` extends GoogleFont {
    override lazy val family: String = "Coda Caption"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/codacaption/v12/ieVm2YRII2GMY7SyXSoDRiQGqcx6x_-fACIgaw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`800`)
  }
  object `Codystar` extends GoogleFont {
    override lazy val family: String = "Codystar"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/codystar/v6/FwZf7-Q1xVk-40qxOuYsyuyrj0e29bfC.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/codystar/v6/FwZY7-Q1xVk-40qxOt6A4sijpFu_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`)
  }
  object `Coiny` extends GoogleFont {
    override lazy val family: String = "Coiny"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`, `tamil`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/coiny/v4/gyByhwU1K989PXwbElSvO5Tc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Combo` extends GoogleFont {
    override lazy val family: String = "Combo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/combo/v7/BXRlvF3Jh_fIhg0iBu9y8Hf0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Comfortaa` extends GoogleFont {
    override lazy val family: String = "Comfortaa"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/comfortaa/v22/1Pt_g8LJRfWJmhDAuUsSQamb1W0lwk4S4TbMPrQVIT9c2c8.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/comfortaa/v22/1Pt_g8LJRfWJmhDAuUsSQamb1W0lwk4S4WjMPrQVIT9c2c8.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/comfortaa/v22/1Pt_g8LJRfWJmhDAuUsSQamb1W0lwk4S4VrMPrQVIT9c2c8.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/comfortaa/v22/1Pt_g8LJRfWJmhDAuUsSQamb1W0lwk4S4bbLPrQVIT9c2c8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/comfortaa/v22/1Pt_g8LJRfWJmhDAuUsSQamb1W0lwk4S4Y_LPrQVIT9c2c8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Coming Soon` extends GoogleFont {
    override lazy val family: String = "Coming Soon"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/comingsoon/v9/qWcuB6mzpYL7AJ2VfdQR1u-SUjjzsykh.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Concert One` extends GoogleFont {
    override lazy val family: String = "Concert One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/concertone/v9/VEM1Ro9xs5PjtzCu-srDqRTlhv-CuVAQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Condiment` extends GoogleFont {
    override lazy val family: String = "Condiment"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/condiment/v6/pONk1hggFNmwvXALyH6Sq4n4o1vyCQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Content` extends GoogleFont {
    override lazy val family: String = "Content"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/content/v11/zrfl0HLayePhU_AwUaDyIiL0RCg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/content/v11/zrfg0HLayePhU_AwaRzdBirfWCHvkAI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Contrail One` extends GoogleFont {
    override lazy val family: String = "Contrail One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/contrailone/v8/eLGbP-j_JA-kG0_Zo51noafdZUvt_c092w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Convergence` extends GoogleFont {
    override lazy val family: String = "Convergence"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/convergence/v7/rax5HiePvdgXPmmMHcIPYRhasU7Q8Cad.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cookie` extends GoogleFont {
    override lazy val family: String = "Cookie"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cookie/v10/syky-y18lb0tSbfNlQCT9tPdpw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Copse` extends GoogleFont {
    override lazy val family: String = "Copse"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/copse/v8/11hPGpDKz1rGb0djHkihUb-A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Corben` extends GoogleFont {
    override lazy val family: String = "Corben"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/corben/v12/LYjDdGzzklQtCMp9oAlEpVs3VQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/corben/v12/LYjAdGzzklQtCMpFHCZgrXArXN7HWQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Cormorant` extends GoogleFont {
    override lazy val family: String = "Cormorant"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorant/v7/H4cgBXOCl9bbnla_nHIiRLmYgoyyYzFzFw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/cormorant/v7/H4c-BXOCl9bbnla_nHIq6qMUgIa2QTRjF8ER.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorant/v7/H4clBXOCl9bbnla_nHIa6JG8iqeuag.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cormorant/v7/H4cjBXOCl9bbnla_nHIq6pu4qKK-aihq.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorant/v7/H4cgBXOCl9bbnla_nHIiHLiYgoyyYzFzFw.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/cormorant/v7/H4c-BXOCl9bbnla_nHIq6qNMgYa2QTRjF8ER.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorant/v7/H4cgBXOCl9bbnla_nHIiML-YgoyyYzFzFw.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/cormorant/v7/H4c-BXOCl9bbnla_nHIq6qNghoa2QTRjF8ER.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorant/v7/H4cgBXOCl9bbnla_nHIiVL6YgoyyYzFzFw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cormorant/v7/H4c-BXOCl9bbnla_nHIq6qMEh4a2QTRjF8ER.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Cormorant Garamond` extends GoogleFont {
    override lazy val family: String = "Cormorant Garamond"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorantgaramond/v6/co3YmX5slCNuHLi8bLeY9MK7whWMhyjQAllvuQWJ5heb_w.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/cormorantgaramond/v6/co3WmX5slCNuHLi8bLeY9MK7whWMhyjYrEPjuw-NxBKL_y94.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorantgaramond/v6/co3bmX5slCNuHLi8bLeY9MK7whWMhyjornFLsS6V7w.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cormorantgaramond/v6/co3ZmX5slCNuHLi8bLeY9MK7whWMhyjYrHtPkyuF7w6C.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorantgaramond/v6/co3YmX5slCNuHLi8bLeY9MK7whWMhyjQWlhvuQWJ5heb_w.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/cormorantgaramond/v6/co3WmX5slCNuHLi8bLeY9MK7whWMhyjYrEO7ug-NxBKL_y94.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorantgaramond/v6/co3YmX5slCNuHLi8bLeY9MK7whWMhyjQdl9vuQWJ5heb_w.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/cormorantgaramond/v6/co3WmX5slCNuHLi8bLeY9MK7whWMhyjYrEOXvQ-NxBKL_y94.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorantgaramond/v6/co3YmX5slCNuHLi8bLeY9MK7whWMhyjQEl5vuQWJ5heb_w.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cormorantgaramond/v6/co3WmX5slCNuHLi8bLeY9MK7whWMhyjYrEPzvA-NxBKL_y94.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Cormorant Infant` extends GoogleFont {
    override lazy val family: String = "Cormorant Infant"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorantinfant/v7/HhyIU44g9vKiM1sORYSiWeAsLN9951w3_DMrQqcdJrk.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/cormorantinfant/v7/HhyKU44g9vKiM1sORYSiWeAsLN997_ItcDEhRoUYNrn_Ig.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorantinfant/v7/HhyPU44g9vKiM1sORYSiWeAsLN993_Af2DsAXq4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cormorantinfant/v7/HhyJU44g9vKiM1sORYSiWeAsLN997_IV3BkFTq4EPw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorantinfant/v7/HhyIU44g9vKiM1sORYSiWeAsLN995wQ2_DMrQqcdJrk.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/cormorantinfant/v7/HhyKU44g9vKiM1sORYSiWeAsLN997_ItKDAhRoUYNrn_Ig.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorantinfant/v7/HhyIU44g9vKiM1sORYSiWeAsLN995ygx_DMrQqcdJrk.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/cormorantinfant/v7/HhyKU44g9vKiM1sORYSiWeAsLN997_ItBDchRoUYNrn_Ig.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorantinfant/v7/HhyIU44g9vKiM1sORYSiWeAsLN9950ww_DMrQqcdJrk.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cormorantinfant/v7/HhyKU44g9vKiM1sORYSiWeAsLN997_ItYDYhRoUYNrn_Ig.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Cormorant SC` extends GoogleFont {
    override lazy val family: String = "Cormorant SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorantsc/v7/0ybmGD4kxqXBmOVLG30OGwsmABIU_R3y8DOWGA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorantsc/v7/0yb5GD4kxqXBmOVLG30OGwserDow9Tbu-Q.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorantsc/v7/0ybmGD4kxqXBmOVLG30OGwsmWBMU_R3y8DOWGA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorantsc/v7/0ybmGD4kxqXBmOVLG30OGwsmdBQU_R3y8DOWGA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorantsc/v7/0ybmGD4kxqXBmOVLG30OGwsmEBUU_R3y8DOWGA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Cormorant Unicase` extends GoogleFont {
    override lazy val family: String = "Cormorant Unicase"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorantunicase/v7/HI_ViZUaILtOqhqgDeXoF_n1_fTGX9N_tucv7Gy0DRzS.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorantunicase/v7/HI_QiZUaILtOqhqgDeXoF_n1_fTGX-vTnsMnx3C9.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorantunicase/v7/HI_ViZUaILtOqhqgDeXoF_n1_fTGX9Mnt-cv7Gy0DRzS.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorantunicase/v7/HI_ViZUaILtOqhqgDeXoF_n1_fTGX9MLsOcv7Gy0DRzS.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorantunicase/v7/HI_ViZUaILtOqhqgDeXoF_n1_fTGX9Nvsecv7Gy0DRzS.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Cormorant Upright` extends GoogleFont {
    override lazy val family: String = "Cormorant Upright"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/cormorantupright/v5/VuJudM3I2Y35poFONtLdafkUCHw1y1N5phDsU9X6RPzQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cormorantupright/v5/VuJrdM3I2Y35poFONtLdafkUCHw1y2vVjjTkeMnz.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/cormorantupright/v5/VuJudM3I2Y35poFONtLdafkUCHw1y1MhpxDsU9X6RPzQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/cormorantupright/v5/VuJudM3I2Y35poFONtLdafkUCHw1y1MNoBDsU9X6RPzQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cormorantupright/v5/VuJudM3I2Y35poFONtLdafkUCHw1y1NpoRDsU9X6RPzQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Courgette` extends GoogleFont {
    override lazy val family: String = "Courgette"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/courgette/v6/wEO_EBrAnc9BLjLQAUkFUfAL3EsHiA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cousine` extends GoogleFont {
    override lazy val family: String = "Cousine"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `hebrew`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cousine/v13/d6lIkaiiRdih4SpPzSMlzTbtz9k.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cousine/v13/d6lKkaiiRdih4SpP_SEvyRTo39l8hw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cousine/v13/d6lNkaiiRdih4SpP9Z8K6T7G09BlnmQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cousine/v13/d6lPkaiiRdih4SpP_SEXdTvM1_JgjmRpOA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Coustard` extends GoogleFont {
    override lazy val family: String = "Coustard"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/coustard/v9/3XFpErgg3YsZ5fqUU9UPvWXuROTd.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/coustard/v9/3XFuErgg3YsZ5fqUU-2LkEHmb_jU3eRL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `900`)
  }
  object `Covered By Your Grace` extends GoogleFont {
    override lazy val family: String = "Covered By Your Grace"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/coveredbyyourgrace/v8/QGYwz-AZahWOJJI9kykWW9mD6opopoqXSOS0FgItq6bFIg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Crafty Girls` extends GoogleFont {
    override lazy val family: String = "Crafty Girls"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/craftygirls/v8/va9B4kXI39VaDdlPJo8N_NvuQR37fF3Wlg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Creepster` extends GoogleFont {
    override lazy val family: String = "Creepster"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/creepster/v7/AlZy_zVUqJz4yMrniH4hdXf4XB0Tow.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Crete Round` extends GoogleFont {
    override lazy val family: String = "Crete Round"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/creteround/v7/55xoey1sJNPjPiv1ZZZrxJ1827zAKnxN.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/creteround/v7/55xqey1sJNPjPiv1ZZZrxK1-0bjiL2xNhKc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Crimson Text` extends GoogleFont {
    override lazy val family: String = "Crimson Text"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/crimsontext/v9/wlp2gwHKFkZgtmSR3NB0oRJvaAJSA_JN3Q.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/crimsontext/v9/wlpogwHKFkZgtmSR3NB0oRJfaghWIfdd3ahG.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/crimsontext/v9/wlppgwHKFkZgtmSR3NB0oRJXsCx2C9lR1LFffg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/crimsontext/v9/wlprgwHKFkZgtmSR3NB0oRJfajCOD9NV9rRPfrKu.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/crimsontext/v9/wlppgwHKFkZgtmSR3NB0oRJX1C12C9lR1LFffg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/crimsontext/v9/wlprgwHKFkZgtmSR3NB0oRJfajDqDtNV9rRPfrKu.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Croissant One` extends GoogleFont {
    override lazy val family: String = "Croissant One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/croissantone/v6/3y9n6bU9bTPg4m8NDy3Kq24UM3pqn5cdJ-4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Crushed` extends GoogleFont {
    override lazy val family: String = "Crushed"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/crushed/v9/U9Mc6dym6WXImTlFT1kfuIqyLzA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cuprum` extends GoogleFont {
    override lazy val family: String = "Cuprum"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cuprum/v10/dg4k_pLmvrkcOkB9IeFDh701Sg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/cuprum/v10/dg4m_pLmvrkcOkBNI-tHpbglShon.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/cuprum/v10/dg4n_pLmvrkcOkBFnc5nj5YpQwM-gg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/cuprum/v10/dg4h_pLmvrkcOkBNI9P7ipwtYQYugjW4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Cute Font` extends GoogleFont {
    override lazy val family: String = "Cute Font"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cutefont/v7/Noaw6Uny2oWPbSHMrY6vmJNVNC9hkw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cutive` extends GoogleFont {
    override lazy val family: String = "Cutive"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cutive/v10/NaPZcZ_fHOhV3Ip7T_hDoyqlZQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Cutive Mono` extends GoogleFont {
    override lazy val family: String = "Cutive Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/cutivemono/v7/m8JWjfRfY7WVjVi2E-K9H5RFRG-K3Mud.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `DM Sans` extends GoogleFont {
    override lazy val family: String = "DM Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dmsans/v1/rP2Hp2ywxg089UriOZSCHBeHFl0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/dmsans/v1/rP2Fp2ywxg089UriCZaIGDWCBl0O8Q.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/dmsans/v1/rP2Cp2ywxg089UriAWCrOB-sClQX6Cg.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/dmsans/v1/rP2Ap2ywxg089UriCZaw7BymDnYS-Cjk6Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/dmsans/v1/rP2Cp2ywxg089UriASitOB-sClQX6Cg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/dmsans/v1/rP2Ap2ywxg089UriCZawpBqmDnYS-Cjk6Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `700`, `700italic`)
  }
  object `DM Serif Display` extends GoogleFont {
    override lazy val family: String = "DM Serif Display"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dmserifdisplay/v1/-nFnOHM81r4j6k0gjAW3mujVU2B2K_d709jy92k.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/dmserifdisplay/v1/-nFhOHM81r4j6k0gjAW3mujVU2B2G_Vx1_r352np3Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `DM Serif Text` extends GoogleFont {
    override lazy val family: String = "DM Serif Text"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dmseriftext/v1/rnCu-xZa_krGokauCeNq1wWyafOPXHIJErY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/dmseriftext/v1/rnCw-xZa_krGokauCeNq1wWyWfGFWFAMArZKqQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Damion` extends GoogleFont {
    override lazy val family: String = "Damion"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/damion/v8/hv-XlzJ3KEUe_YZUbWY3MTFgVg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dancing Script` extends GoogleFont {
    override lazy val family: String = "Dancing Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dancingscript/v10/If2RXTr6YS-zF4S-kcSWSVi_swLngOAliz4X.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/dancingscript/v10/If2SXTr6YS-zF4S-kcSWSVi_szpbr8QtoCIervbA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Dangrek` extends GoogleFont {
    override lazy val family: String = "Dangrek"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dangrek/v10/LYjCdG30nEgoH8E2gCNqqVIuTN4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `David Libre` extends GoogleFont {
    override lazy val family: String = "David Libre"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/davidlibre/v3/snfus0W_99N64iuYSvp4W_l86p6TYS-Y.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/davidlibre/v3/snfzs0W_99N64iuYSvp4W8GIw7qbSjORSo9W.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/davidlibre/v3/snfzs0W_99N64iuYSvp4W8HAxbqbSjORSo9W.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `700`)
  }
  object `Dawning of a New Day` extends GoogleFont {
    override lazy val family: String = "Dawning of a New Day"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dawningofanewday/v9/t5t_IQMbOp2SEwuncwLRjMfIg1yYit_nAz8bhWJGNoBE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Days One` extends GoogleFont {
    override lazy val family: String = "Days One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/daysone/v8/mem9YaCnxnKRiYZOCLYVeLkWVNBt.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dekko` extends GoogleFont {
    override lazy val family: String = "Dekko"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dekko/v5/46khlb_wWjfSrttFR0vsfl1B.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Delius` extends GoogleFont {
    override lazy val family: String = "Delius"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/delius/v8/PN_xRfK0pW_9e1rtYcI-jT3L_w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Delius Swash Caps` extends GoogleFont {
    override lazy val family: String = "Delius Swash Caps"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/deliusswashcaps/v10/oY1E8fPLr7v4JWCExZpWebxVKORpXXedKmeBvEYs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Delius Unicase` extends GoogleFont {
    override lazy val family: String = "Delius Unicase"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/deliusunicase/v12/845BNMEwEIOVT8BmgfSzIr_6mmLHd-73LXWs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/deliusunicase/v12/845CNMEwEIOVT8BmgfSzIr_6mlp7WMr_BmmlS5aw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Della Respira` extends GoogleFont {
    override lazy val family: String = "Della Respira"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dellarespira/v6/RLp5K5v44KaueWI6iEJQBiGPRfkSu6EuTHo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Denk One` extends GoogleFont {
    override lazy val family: String = "Denk One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/denkone/v6/dg4m_pzhrqcFb2IzROtHpbglShon.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Devonshire` extends GoogleFont {
    override lazy val family: String = "Devonshire"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/devonshire/v7/46kqlbDwWirWr4gtBD2BX0Vq01lYAZM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dhurjati` extends GoogleFont {
    override lazy val family: String = "Dhurjati"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dhurjati/v6/_6_8ED3gSeatXfFiFX3ySKQtuTA2.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Didact Gothic` extends GoogleFont {
    override lazy val family: String = "Didact Gothic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/didactgothic/v12/ahcfv8qz1zt6hCC5G4F_P4ASpUySp0LlcyQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Diplomata` extends GoogleFont {
    override lazy val family: String = "Diplomata"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/diplomata/v10/Cn-0JtiMXwhNwp-wKxyfYGxYrdM9Sg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Diplomata SC` extends GoogleFont {
    override lazy val family: String = "Diplomata SC"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/diplomatasc/v7/buExpoi3ecvs3kidKgBJo2kf-P5Oaiw4cw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Do Hyeon` extends GoogleFont {
    override lazy val family: String = "Do Hyeon"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dohyeon/v10/TwMN-I8CRRU2zM86HFE3ZwaH__-C.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dokdo` extends GoogleFont {
    override lazy val family: String = "Dokdo"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dokdo/v7/esDf315XNuCBLxLo4NaMlKcH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Domine` extends GoogleFont {
    override lazy val family: String = "Domine"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/domine/v6/L0x8DFMnlVwD4h3RvPCmRSlUig.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/domine/v6/L0x_DFMnlVwD4h3pAN-CTQJIg3uuXg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Donegal One` extends GoogleFont {
    override lazy val family: String = "Donegal One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/donegalone/v6/m8JWjfRYea-ZnFz6fsK9FZRFRG-K3Mud.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Doppio One` extends GoogleFont {
    override lazy val family: String = "Doppio One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/doppioone/v6/Gg8wN5gSaBfyBw2MqCh-lgshKGpe5Fg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dorsa` extends GoogleFont {
    override lazy val family: String = "Dorsa"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dorsa/v9/yYLn0hjd0OGwqo493XCFxAnQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dosis` extends GoogleFont {
    override lazy val family: String = "Dosis"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/dosis/v8/HhyXU5sn9vOmLzGnKtCCOopCTKkI.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/dosis/v8/HhyXU5sn9vOmLzHDKdCCOopCTKkI.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dosis/v8/HhyaU5sn9vOmLwlvAfSKEZZL.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/dosis/v8/HhyXU5sn9vOmLzGbKNCCOopCTKkI.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/dosis/v8/HhyXU5sn9vOmLzG3L9CCOopCTKkI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/dosis/v8/HhyXU5sn9vOmLzHTLtCCOopCTKkI.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/dosis/v8/HhyXU5sn9vOmLzHPLdCCOopCTKkI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Dr Sugiyama` extends GoogleFont {
    override lazy val family: String = "Dr Sugiyama"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/drsugiyama/v8/HTxoL2k4N3O9n5I1boGI7abRM4-t-g7y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Duru Sans` extends GoogleFont {
    override lazy val family: String = "Duru Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/durusans/v12/xn7iYH8xwmSyTvEV_HOxT_fYdN-WZw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Dynalight` extends GoogleFont {
    override lazy val family: String = "Dynalight"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/dynalight/v7/1Ptsg8LOU_aOmQvTsF4ISotrDfGGxA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `EB Garamond` extends GoogleFont {
    override lazy val family: String = "EB Garamond"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ebgaramond/v10/SlGUmQSNjdsmc35JDF1K5FRyQjgdYxPJ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ebgaramond/v10/SlGWmQSNjdsmc35JDF1K5GRwSDw_ZgPJtWk.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ebgaramond/v10/SlGJmQSNjdsmc35JDF1K5GyGaxwVSA_ArHC_.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ebgaramond/v10/SlGLmQSNjdsmc35JDF1K5GRwcMgWQgviqWC_O7Y.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/ebgaramond/v10/SlGJmQSNjdsmc35JDF1K5GyqbBwVSA_ArHC_.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/ebgaramond/v10/SlGLmQSNjdsmc35JDF1K5GRwcOQRQgviqWC_O7Y.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ebgaramond/v10/SlGJmQSNjdsmc35JDF1K5GzObRwVSA_ArHC_.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ebgaramond/v10/SlGLmQSNjdsmc35JDF1K5GRwcIAQQgviqWC_O7Y.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/ebgaramond/v10/SlGJmQSNjdsmc35JDF1K5GzSbhwVSA_ArHC_.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/ebgaramond/v10/SlGLmQSNjdsmc35JDF1K5GRwcJwTQgviqWC_O7Y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Eagle Lake` extends GoogleFont {
    override lazy val family: String = "Eagle Lake"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/eaglelake/v6/ptRMTiqbbuNJDOiKj9wG5O7yKQNute8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `East Sea Dokdo` extends GoogleFont {
    override lazy val family: String = "East Sea Dokdo"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/eastseadokdo/v7/xfuo0Wn2V2_KanASqXSZp22m05_aGavYS18y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Eater` extends GoogleFont {
    override lazy val family: String = "Eater"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/eater/v7/mtG04_FCK7bOvpu2u3FwsXsR.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Economica` extends GoogleFont {
    override lazy val family: String = "Economica"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/economica/v6/Qw3fZQZaHCLgIWa29ZBrMcgAAl1lfQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/economica/v6/Qw3ZZQZaHCLgIWa29ZBbM8IEIFh1fWUl.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/economica/v6/Qw3aZQZaHCLgIWa29ZBTjeckCnZ5dHw8iw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/economica/v6/Qw3EZQZaHCLgIWa29ZBbM_q4D3x9Vnksi4M7.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Eczar` extends GoogleFont {
    override lazy val family: String = "Eczar"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/eczar/v7/BXRlvF3Pi-DLmw0iBu9y8Hf0.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/eczar/v7/BXRovF3Pi-DLmzXWL8t622v9WNjW.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/eczar/v7/BXRovF3Pi-DLmzX6KMt622v9WNjW.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/eczar/v7/BXRovF3Pi-DLmzWeKct622v9WNjW.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/eczar/v7/BXRovF3Pi-DLmzWCKst622v9WNjW.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `800`)
  }
  object `El Messiri` extends GoogleFont {
    override lazy val family: String = "El Messiri"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/elmessiri/v5/K2F0fZBRmr9vQ1pHEey6AoqKAyLzfWo.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/elmessiri/v5/K2F3fZBRmr9vQ1pHEey6On6jJyrYYWOMluQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/elmessiri/v5/K2F3fZBRmr9vQ1pHEey6OlKkJyrYYWOMluQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/elmessiri/v5/K2F3fZBRmr9vQ1pHEey6OjalJyrYYWOMluQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`)
  }
  object `Electrolize` extends GoogleFont {
    override lazy val family: String = "Electrolize"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/electrolize/v7/cIf5Ma1dtE0zSiGSiED7AUEGso5tQafB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Elsie` extends GoogleFont {
    override lazy val family: String = "Elsie"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/elsie/v8/BCanqZABrez54yYu9slAeLgX.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/elsie/v8/BCaqqZABrez54x6q2-1IU6QeXSBk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `900`)
  }
  object `Elsie Swash Caps` extends GoogleFont {
    override lazy val family: String = "Elsie Swash Caps"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/elsieswashcaps/v7/845DNN8xGZyVX5MVo_upKf7KnjK0ferVKGWsUo8.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/elsieswashcaps/v7/845ENN8xGZyVX5MVo_upKf7KnjK0RW74DG2HToawrdU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `900`)
  }
  object `Emblema One` extends GoogleFont {
    override lazy val family: String = "Emblema One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/emblemaone/v7/nKKT-GQ0F5dSY8vzG0rOEIRBHl57G_f_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Emilys Candy` extends GoogleFont {
    override lazy val family: String = "Emilys Candy"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/emilyscandy/v6/2EbgL-1mD1Rnb0OGKudbk0y5r9xrX84JjA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Encode Sans` extends GoogleFont {
    override lazy val family: String = "Encode Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/encodesans/v3/LDI0apOFNxEwR-Bd1O9uYPvIeeLkl7Iw6yg.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/encodesans/v3/LDIrapOFNxEwR-Bd1O9uYPtkWMLOub458jGL.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/encodesans/v3/LDIrapOFNxEwR-Bd1O9uYPsAW8LOub458jGL.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/encodesans/v3/LDI2apOFNxEwR-Bd1O9uYMOsc-bGkqIw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/encodesans/v3/LDIrapOFNxEwR-Bd1O9uYPtYWsLOub458jGL.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/encodesans/v3/LDIrapOFNxEwR-Bd1O9uYPt0XcLOub458jGL.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/encodesans/v3/LDIrapOFNxEwR-Bd1O9uYPsQXMLOub458jGL.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/encodesans/v3/LDIrapOFNxEwR-Bd1O9uYPsMX8LOub458jGL.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/encodesans/v3/LDIrapOFNxEwR-Bd1O9uYPsoXsLOub458jGL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Encode Sans Condensed` extends GoogleFont {
    override lazy val family: String = "Encode Sans Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/encodesanscondensed/v3/j8_76_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-5a-JLQoFI2KR.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/encodesanscondensed/v3/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-SY6pByQJKnuIFA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/encodesanscondensed/v3/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-LY2pByQJKnuIFA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/encodesanscondensed/v3/j8_16_LD37rqfuwxyIuaZhE6cRXOLtm2gfTGgaWNDw8VIw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/encodesanscondensed/v3/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-dYypByQJKnuIFA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/encodesanscondensed/v3/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-WYupByQJKnuIFA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/encodesanscondensed/v3/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-PYqpByQJKnuIFA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/encodesanscondensed/v3/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-IYmpByQJKnuIFA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/encodesanscondensed/v3/j8_46_LD37rqfuwxyIuaZhE6cRXOLtm2gfT-BYipByQJKnuIFA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Encode Sans Expanded` extends GoogleFont {
    override lazy val family: String = "Encode Sans Expanded"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/encodesansexpanded/v3/c4mx1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpJGKQNicoAbJlw.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/encodesansexpanded/v3/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpLqCCNIXIwSP0XD.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/encodesansexpanded/v3/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpKOCyNIXIwSP0XD.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/encodesansexpanded/v3/c4m_1mF4GcnstG_Jh1QH6ac4hNLeNyeYUqoiIwdAd5Ab.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/encodesansexpanded/v3/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpLWCiNIXIwSP0XD.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/encodesansexpanded/v3/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpL6DSNIXIwSP0XD.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/encodesansexpanded/v3/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpKeDCNIXIwSP0XD.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/encodesansexpanded/v3/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpKCDyNIXIwSP0XD.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/encodesansexpanded/v3/c4mw1mF4GcnstG_Jh1QH6ac4hNLeNyeYUpKmDiNIXIwSP0XD.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Encode Sans Semi Condensed` extends GoogleFont {
    override lazy val family: String = "Encode Sans Semi Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/encodesanssemicondensed/v3/3qT6oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1T19MFtQ9jpVUA.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/encodesanssemicondensed/v3/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1RZ1eFHbdTgTFmr.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/encodesanssemicondensed/v3/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1Q91uFHbdTgTFmr.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/encodesanssemicondensed/v3/3qT4oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG2yR_sVPRsjp.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/encodesanssemicondensed/v3/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1Rl1-FHbdTgTFmr.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/encodesanssemicondensed/v3/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1RJ0OFHbdTgTFmr.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/encodesanssemicondensed/v3/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1Qt0eFHbdTgTFmr.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/encodesanssemicondensed/v3/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1Qx0uFHbdTgTFmr.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/encodesanssemicondensed/v3/3qT7oiKqnDuUtQUEHMoXcmspmy55SFWrXFRp9FTOG1QV0-FHbdTgTFmr.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Encode Sans Semi Expanded` extends GoogleFont {
    override lazy val family: String = "Encode Sans Semi Expanded"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/encodesanssemiexpanded/v4/ke8xOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TM-41KwrlKXeOEA.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/encodesanssemiexpanded/v4/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TM0IUCyDLJX6XCWU.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/encodesanssemiexpanded/v4/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TMyYXCyDLJX6XCWU.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/encodesanssemiexpanded/v4/ke83OhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TC4o_LyjgOXc.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/encodesanssemiexpanded/v4/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TM34WCyDLJX6XCWU.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/encodesanssemiexpanded/v4/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TM1IRCyDLJX6XCWU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/encodesanssemiexpanded/v4/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TMzYQCyDLJX6XCWU.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/encodesanssemiexpanded/v4/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TMyoTCyDLJX6XCWU.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/encodesanssemiexpanded/v4/ke8yOhAPMEZs-BDuzwftTNJ85JvwMOzE9d9Cca5TMw4SCyDLJX6XCWU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Engagement` extends GoogleFont {
    override lazy val family: String = "Engagement"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/engagement/v8/x3dlckLDZbqa7RUs9MFVXNossybsHQI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Englebert` extends GoogleFont {
    override lazy val family: String = "Englebert"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/englebert/v6/xn7iYH8w2XGrC8AR4HSxT_fYdN-WZw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Enriqueta` extends GoogleFont {
    override lazy val family: String = "Enriqueta"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/enriqueta/v7/goksH6L7AUFrRvV44HVTS0CjkP1Yog.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/enriqueta/v7/gokpH6L7AUFrRvV44HVr92-HmNZEq6TTFw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Erica One` extends GoogleFont {
    override lazy val family: String = "Erica One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ericaone/v9/WBLnrEXccV9VGrOKmGD1W0_MJMGxiQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Esteban` extends GoogleFont {
    override lazy val family: String = "Esteban"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/esteban/v7/r05bGLZE-bdGdN-GdOuD5jokU8E.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Euphoria Script` extends GoogleFont {
    override lazy val family: String = "Euphoria Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/euphoriascript/v7/mFTpWb0X2bLb_cx6To2B8GpKoD5ak_ZT1D8x7Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ewert` extends GoogleFont {
    override lazy val family: String = "Ewert"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ewert/v6/va9I4kzO2tFODYBvS-J3kbDP.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Exo` extends GoogleFont {
    override lazy val family: String = "Exo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/exo/v8/4UaMrEtFpBIaEH6m2jbu5rXI.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/exo/v8/4UaCrEtFpBISdkbC0DLM46XI-po.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/exo/v8/4UaDrEtFpBIavF-G8Bji76zR4w.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/exo/v8/4UaBrEtFpBISdkZu8RLmzanB44N1.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/exo/v8/4UaDrEtFpBIa2FyG8Bji76zR4w.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/exo/v8/4UaBrEtFpBISdkYK8hLmzanB44N1.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/exo/v8/4UaOrEtFpBIidHSi-DP-5g.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/exo/v8/4UaMrEtFpBISdn6m2jbu5rXI.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/exo/v8/4UaDrEtFpBIagF2G8Bji76zR4w.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/exo/v8/4UaBrEtFpBISdkZS8xLmzanB44N1.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/exo/v8/4UaDrEtFpBIarFqG8Bji76zR4w.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/exo/v8/4UaBrEtFpBISdkZ-9BLmzanB44N1.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/exo/v8/4UaDrEtFpBIayFuG8Bji76zR4w.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/exo/v8/4UaBrEtFpBISdkYa9RLmzanB44N1.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/exo/v8/4UaDrEtFpBIa1FiG8Bji76zR4w.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/exo/v8/4UaBrEtFpBISdkYG9hLmzanB44N1.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/exo/v8/4UaDrEtFpBIa8FmG8Bji76zR4w.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/exo/v8/4UaBrEtFpBISdkYi9xLmzanB44N1.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Exo 2` extends GoogleFont {
    override lazy val family: String = "Exo 2"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/exo2/v5/7cHov4okm5zmbt5LK-sW5HIohT4.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/exo2/v5/7cHqv4okm5zmbtYtE48c4FAtlT47dw.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/exo2/v5/7cHrv4okm5zmbt7nCss8yn4hnCci.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/exo2/v5/7cHtv4okm5zmbtYtEyM9wHoDmTcibrA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/exo2/v5/7cHrv4okm5zmbt6DCcs8yn4hnCci.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/exo2/v5/7cHtv4okm5zmbtYtE0c-wHoDmTcibrA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/exo2/v5/7cHmv4okm5zmbuYvIe804WIo.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/exo2/v5/7cHov4okm5zmbtYtK-sW5HIohT4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/exo2/v5/7cHrv4okm5zmbt7bCMs8yn4hnCci.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/exo2/v5/7cHtv4okm5zmbtYtEx8_wHoDmTcibrA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/exo2/v5/7cHrv4okm5zmbt73D8s8yn4hnCci.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/exo2/v5/7cHtv4okm5zmbtYtEzM4wHoDmTcibrA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/exo2/v5/7cHrv4okm5zmbt6TDss8yn4hnCci.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/exo2/v5/7cHtv4okm5zmbtYtE1c5wHoDmTcibrA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/exo2/v5/7cHrv4okm5zmbt6PDcs8yn4hnCci.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/exo2/v5/7cHtv4okm5zmbtYtE0s6wHoDmTcibrA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/exo2/v5/7cHrv4okm5zmbt6rDMs8yn4hnCci.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/exo2/v5/7cHtv4okm5zmbtYtE287wHoDmTcibrA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Expletus Sans` extends GoogleFont {
    override lazy val family: String = "Expletus Sans"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/expletussans/v12/RLp5K5v5_bqufTYdnhFzDj2dRfkSu6EuTHo.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/expletussans/v12/RLpnK5v5_bqufTYdnhFzDj2ddfsYv4MrXHrRDA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/expletussans/v12/RLpkK5v5_bqufTYdnhFzDj2dfQ07n6kFUHPIFaU.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/expletussans/v12/RLpiK5v5_bqufTYdnhFzDj2ddfsgS6oPVFHNBaVImA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/expletussans/v12/RLpkK5v5_bqufTYdnhFzDj2dfSE8n6kFUHPIFaU.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/expletussans/v12/RLpiK5v5_bqufTYdnhFzDj2ddfsgZ60PVFHNBaVImA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/expletussans/v12/RLpkK5v5_bqufTYdnhFzDj2dfUU9n6kFUHPIFaU.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/expletussans/v12/RLpiK5v5_bqufTYdnhFzDj2ddfsgA6wPVFHNBaVImA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Fahkwang` extends GoogleFont {
    override lazy val family: String = "Fahkwang"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/fahkwang/v2/Noa26Uj3zpmBOgbNpOJHmZlRFipxkwjx.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/fahkwang/v2/Noa06Uj3zpmBOgbNpOqNgHFQHC5Tlhjxdw4.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/fahkwang/v2/Noa26Uj3zpmBOgbNpOIjmplRFipxkwjx.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/fahkwang/v2/Noa06Uj3zpmBOgbNpOqNgBVTHC5Tlhjxdw4.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fahkwang/v2/Noax6Uj3zpmBOgbNpNqPsr1ZPTZ4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/fahkwang/v2/Noa36Uj3zpmBOgbNpOqNuLl7OCZ4ihE.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/fahkwang/v2/Noa26Uj3zpmBOgbNpOJ7m5lRFipxkwjx.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/fahkwang/v2/Noa06Uj3zpmBOgbNpOqNgE1SHC5Tlhjxdw4.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/fahkwang/v2/Noa26Uj3zpmBOgbNpOJXnJlRFipxkwjx.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/fahkwang/v2/Noa06Uj3zpmBOgbNpOqNgGFVHC5Tlhjxdw4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/fahkwang/v2/Noa26Uj3zpmBOgbNpOIznZlRFipxkwjx.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/fahkwang/v2/Noa06Uj3zpmBOgbNpOqNgAVUHC5Tlhjxdw4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Fanwood Text` extends GoogleFont {
    override lazy val family: String = "Fanwood Text"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fanwoodtext/v8/3XFtErwl05Ad_vSCF6Fq7xXGRdbY1P1Sbg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/fanwoodtext/v8/3XFzErwl05Ad_vSCF6Fq7xX2R9zc9vhCblye.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Farsan` extends GoogleFont {
    override lazy val family: String = "Farsan"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`, `gujarati`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/farsan/v4/VEMwRoJ0vY_zsyz62q-pxDX9rQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fascinate` extends GoogleFont {
    override lazy val family: String = "Fascinate"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fascinate/v7/z7NWdRrufC8XJK0IIEli1LbQRPyNrw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fascinate Inline` extends GoogleFont {
    override lazy val family: String = "Fascinate Inline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fascinateinline/v8/jVyR7mzzB3zc-jp6QCAu60poNqIy1g3CfRXxWZQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Faster One` extends GoogleFont {
    override lazy val family: String = "Faster One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fasterone/v10/H4ciBXCHmdfClFb-vWhfyLuShq63czE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fasthand` extends GoogleFont {
    override lazy val family: String = "Fasthand"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fasthand/v9/0yb9GDohyKTYn_ZEESkuYkw2rQg1.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fauna One` extends GoogleFont {
    override lazy val family: String = "Fauna One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/faunaone/v6/wlpzgwTPBVpjpCuwkuEx2UxLYClOCg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Faustina` extends GoogleFont {
    override lazy val family: String = "Faustina"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/faustina/v3/XLYlIZPxYpJfTbZAFW-4F81Kp28v.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/faustina/v3/XLYjIZPxYpJfTbZAFV-6Hcloon8vVXg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/faustina/v3/XLYiIZPxYpJfTbZAFVdMPulCjHMmTGFt.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/faustina/v3/XLYgIZPxYpJfTbZAFV-6JT1BhncESXFtUsM.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/faustina/v3/XLYiIZPxYpJfTbZAFVdgOelCjHMmTGFt.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/faustina/v3/XLYgIZPxYpJfTbZAFV-6JRFGhncESXFtUsM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/faustina/v3/XLYiIZPxYpJfTbZAFVcEOOlCjHMmTGFt.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/faustina/v3/XLYgIZPxYpJfTbZAFV-6JXVHhncESXFtUsM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Federant` extends GoogleFont {
    override lazy val family: String = "Federant"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/federant/v10/2sDdZGNfip_eirT0_U0jRUG0AqUc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Federo` extends GoogleFont {
    override lazy val family: String = "Federo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/federo/v10/iJWFBX-cbD_ETsbmjVOe2WTG7Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Felipa` extends GoogleFont {
    override lazy val family: String = "Felipa"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/felipa/v6/FwZa7-owz1Eu4F_wSNSEwM2zpA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fenix` extends GoogleFont {
    override lazy val family: String = "Fenix"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fenix/v6/XoHo2YL_S7-g5ostKzAFvs8o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Finger Paint` extends GoogleFont {
    override lazy val family: String = "Finger Paint"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fingerpaint/v8/0QInMXVJ-o-oRn_7dron8YWO85bS8ANesw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fira Mono` extends GoogleFont {
    override lazy val family: String = "Fira Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/firamono/v7/N0bX2SlFPv1weGeLZDtQIfTTkdbJYA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/firamono/v7/N0bS2SlFPv1weGeLZDto1d33mf3VaZBRBQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/firamono/v7/N0bS2SlFPv1weGeLZDtondv3mf3VaZBRBQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `700`)
  }
  object `Fira Sans` extends GoogleFont {
    override lazy val family: String = "Fira Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/firasans/v9/va9C4kDNxMZdWfMOD5Vn9IjOazP3dUTP.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/firasans/v9/va9A4kDNxMZdWfMOD5VvkrCqYTfVcFTPj0s.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/firasans/v9/va9B4kDNxMZdWfMOD5VnWKnuQR37fF3Wlg.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/firasans/v9/va9f4kDNxMZdWfMOD5VvkrAGQBf_XljGllLX.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/firasans/v9/va9B4kDNxMZdWfMOD5VnPKruQR37fF3Wlg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/firasans/v9/va9f4kDNxMZdWfMOD5VvkrBiQxf_XljGllLX.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/firasans/v9/va9E4kDNxMZdWfMOD5VfkILKSTbndQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/firasans/v9/va9C4kDNxMZdWfMOD5VvkojOazP3dUTP.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/firasans/v9/va9B4kDNxMZdWfMOD5VnZKvuQR37fF3Wlg.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/firasans/v9/va9f4kDNxMZdWfMOD5VvkrA6Qhf_XljGllLX.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/firasans/v9/va9B4kDNxMZdWfMOD5VnSKzuQR37fF3Wlg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/firasans/v9/va9f4kDNxMZdWfMOD5VvkrAWRRf_XljGllLX.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/firasans/v9/va9B4kDNxMZdWfMOD5VnLK3uQR37fF3Wlg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/firasans/v9/va9f4kDNxMZdWfMOD5VvkrByRBf_XljGllLX.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/firasans/v9/va9B4kDNxMZdWfMOD5VnMK7uQR37fF3Wlg.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/firasans/v9/va9f4kDNxMZdWfMOD5VvkrBuRxf_XljGllLX.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/firasans/v9/va9B4kDNxMZdWfMOD5VnFK_uQR37fF3Wlg.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/firasans/v9/va9f4kDNxMZdWfMOD5VvkrBKRhf_XljGllLX.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Fira Sans Condensed` extends GoogleFont {
    override lazy val family: String = "Fira Sans Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/firasanscondensed/v3/wEOjEADFm8hSaQTFG18FErVhsC9x-tarWZXtqOlQfx9CjA.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/firasanscondensed/v3/wEOtEADFm8hSaQTFG18FErVhsC9x-tarUfPVzONUXRpSjJcu.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/firasanscondensed/v3/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWTnMiMN-cxZblY4.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/firasanscondensed/v3/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVYMJ0dzRehY43EA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/firasanscondensed/v3/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWV3PiMN-cxZblY4.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/firasanscondensed/v3/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVBMF0dzRehY43EA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/firasanscondensed/v3/wEOhEADFm8hSaQTFG18FErVhsC9x-tarYfHnrMtVbx8.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/firasanscondensed/v3/wEOjEADFm8hSaQTFG18FErVhsC9x-tarUfPtqOlQfx9CjA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/firasanscondensed/v3/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWQXOiMN-cxZblY4.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/firasanscondensed/v3/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVXMB0dzRehY43EA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/firasanscondensed/v3/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWSnJiMN-cxZblY4.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/firasanscondensed/v3/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVcMd0dzRehY43EA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/firasanscondensed/v3/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWU3IiMN-cxZblY4.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/firasanscondensed/v3/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVFMZ0dzRehY43EA.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/firasanscondensed/v3/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWVHLiMN-cxZblY4.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/firasanscondensed/v3/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVCMV0dzRehY43EA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/firasanscondensed/v3/wEOsEADFm8hSaQTFG18FErVhsC9x-tarWXXKiMN-cxZblY4.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/firasanscondensed/v3/wEOuEADFm8hSaQTFG18FErVhsC9x-tarUfPVLMR0dzRehY43EA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Fira Sans Extra Condensed` extends GoogleFont {
    override lazy val family: String = "Fira Sans Extra Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/firasansextracondensed/v3/NaPMcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3Zyuv1WarE9ncg.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/firasansextracondensed/v3/NaPOcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqW21-ejkp3cn22.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/firasansextracondensed/v3/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3TCPn3-0oEZ-a2Q.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/firasansextracondensed/v3/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWd36-pGR7e2SvJQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/firasansextracondensed/v3/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3VSMn3-0oEZ-a2Q.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/firasansextracondensed/v3/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWE32-pGR7e2SvJQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/firasansextracondensed/v3/NaPKcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda5fiku3efvE8.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/firasansextracondensed/v3/NaPMcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fquv1WarE9ncg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/firasansextracondensed/v3/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3QyNn3-0oEZ-a2Q.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/firasansextracondensed/v3/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWS3y-pGR7e2SvJQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/firasansextracondensed/v3/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3SCKn3-0oEZ-a2Q.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/firasansextracondensed/v3/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWZ3u-pGR7e2SvJQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/firasansextracondensed/v3/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3USLn3-0oEZ-a2Q.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/firasansextracondensed/v3/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWA3q-pGR7e2SvJQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/firasansextracondensed/v3/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3ViIn3-0oEZ-a2Q.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/firasansextracondensed/v3/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWH3m-pGR7e2SvJQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/firasansextracondensed/v3/NaPPcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda3XyJn3-0oEZ-a2Q.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/firasansextracondensed/v3/NaPxcYDaAO5dirw6IaFn7lPJFqXmS-M9Atn3wgda1fqWO3i-pGR7e2SvJQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Fjalla One` extends GoogleFont {
    override lazy val family: String = "Fjalla One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fjallaone/v6/Yq6R-LCAWCX3-6Ky7FAFnOZwkxgtUb8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fjord One` extends GoogleFont {
    override lazy val family: String = "Fjord One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fjordone/v7/zOL-4pbEnKBY_9S1jNKr6e5As-FeiQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Flamenco` extends GoogleFont {
    override lazy val family: String = "Flamenco"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/flamenco/v9/neIPzCehqYguo67ssZ0qNIkyepH9qGsf.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/flamenco/v9/neIIzCehqYguo67ssaWGHK06UY30.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`)
  }
  object `Flavors` extends GoogleFont {
    override lazy val family: String = "Flavors"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/flavors/v7/FBV2dDrhxqmveJTpbkzlNqkG9UY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fondamento` extends GoogleFont {
    override lazy val family: String = "Fondamento"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fondamento/v9/4UaHrEJGsxNmFTPDnkaJx63j5pN1MwI.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/fondamento/v9/4UaFrEJGsxNmFTPDnkaJ96_p4rFwIwJePw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Fontdiner Swanky` extends GoogleFont {
    override lazy val family: String = "Fontdiner Swanky"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fontdinerswanky/v9/ijwOs4XgRNsiaI5-hcVb4hQgMvCD4uEfKiGvxts.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Forum` extends GoogleFont {
    override lazy val family: String = "Forum"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/forum/v9/6aey4Ky-Vb8Ew_IWMJMa3mnT.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Francois One` extends GoogleFont {
    override lazy val family: String = "Francois One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/francoisone/v13/_Xmr-H4zszafZw3A-KPSZutNxgKQu_avAg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Frank Ruhl Libre` extends GoogleFont {
    override lazy val family: String = "Frank Ruhl Libre"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/frankruhllibre/v4/j8_36_fAw7jrcalD7oKYNX0QfAnPUxvHxJDMhYeIHw8.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/frankruhllibre/v4/j8_w6_fAw7jrcalD7oKYNX0QfAnPa7fv4JjnmY4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/frankruhllibre/v4/j8_36_fAw7jrcalD7oKYNX0QfAnPU0PGxJDMhYeIHw8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/frankruhllibre/v4/j8_36_fAw7jrcalD7oKYNX0QfAnPUwvAxJDMhYeIHw8.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/frankruhllibre/v4/j8_36_fAw7jrcalD7oKYNX0QfAnPUzPCxJDMhYeIHw8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `700`, `900`)
  }
  object `Freckle Face` extends GoogleFont {
    override lazy val family: String = "Freckle Face"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/freckleface/v7/AMOWz4SXrmKHCvXTohxY-YI0U1K2w9lb4g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fredericka the Great` extends GoogleFont {
    override lazy val family: String = "Fredericka the Great"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/frederickathegreat/v7/9Bt33CxNwt7aOctW2xjbCstzwVKsIBVV-9Skz7Ylch2L.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fredoka One` extends GoogleFont {
    override lazy val family: String = "Fredoka One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fredokaone/v6/k3kUo8kEI-tA1RRcTZGmTmHBA6aF8Bf_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Freehand` extends GoogleFont {
    override lazy val family: String = "Freehand"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/freehand/v10/cIf-Ma5eqk01VjKTgAmBTmUOmZJk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fresca` extends GoogleFont {
    override lazy val family: String = "Fresca"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fresca/v7/6ae94K--SKgCzbM2Gr0W13DKPA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Frijole` extends GoogleFont {
    override lazy val family: String = "Frijole"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/frijole/v7/uU9PCBUR8oakM2BQ7xPb3vyHmlI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fruktur` extends GoogleFont {
    override lazy val family: String = "Fruktur"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fruktur/v11/SZc53FHsOru5QYsMfz3GkUrS8DI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Fugaz One` extends GoogleFont {
    override lazy val family: String = "Fugaz One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/fugazone/v8/rax_HiWKp9EAITukFslMBBJek0vA8A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `GFS Didot` extends GoogleFont {
    override lazy val family: String = "GFS Didot"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gfsdidot/v8/Jqzh5TybZ9vZMWFssvwiF-fGFSCGAA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `GFS Neohellenic` extends GoogleFont {
    override lazy val family: String = "GFS Neohellenic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gfsneohellenic/v11/8QIRdiDOrfiq0b7R8O1Iw9WLcY5TLahP46UDUw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/gfsneohellenic/v11/8QITdiDOrfiq0b7R8O1Iw9WLcY5jL6JLwaATU91X.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gfsneohellenic/v11/8QIUdiDOrfiq0b7R8O1Iw9WLcY5rkYdr644fWsRO9w.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/gfsneohellenic/v11/8QIWdiDOrfiq0b7R8O1Iw9WLcY5jL5r37oQbeMFe985V.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Gabriela` extends GoogleFont {
    override lazy val family: String = "Gabriela"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gabriela/v7/qkBWXvsO6sreR8E-b_m-zrpHmRzC.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gaegu` extends GoogleFont {
    override lazy val family: String = "Gaegu"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/gaegu/v7/TuGSUVB6Up9NU57nifw74sdtBk0x.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gaegu/v7/TuGfUVB6Up9NU6ZLodgzydtk.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gaegu/v7/TuGSUVB6Up9NU573jvw74sdtBk0x.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Gafata` extends GoogleFont {
    override lazy val family: String = "Gafata"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gafata/v7/XRXV3I6Cn0VJKon4MuyAbsrVcA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Galada` extends GoogleFont {
    override lazy val family: String = "Galada"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `bengali`: GoogleFontSubset = new GoogleFontSubset("bengali")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`bengali`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/galada/v4/H4cmBXyGmcjXlUX-8iw-4Lqggw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Galdeano` extends GoogleFont {
    override lazy val family: String = "Galdeano"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/galdeano/v8/uU9MCBoQ4YOqOW1boDPx8PCOg0uX.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Galindo` extends GoogleFont {
    override lazy val family: String = "Galindo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/galindo/v6/HI_KiYMeLqVKqwyuQ5HiRp-dhpQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gamja Flower` extends GoogleFont {
    override lazy val family: String = "Gamja Flower"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gamjaflower/v7/6NUR8FiKJg-Pa0rM6uN40Z4kyf9Fdty2ew.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gentium Basic` extends GoogleFont {
    override lazy val family: String = "Gentium Basic"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gentiumbasic/v10/Wnz9HAw9aB_JD2VGQVR80We3HAqDiTI_cIM.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/gentiumbasic/v10/WnzjHAw9aB_JD2VGQVR80We3LAiJjRA6YIORZQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gentiumbasic/v10/WnzgHAw9aB_JD2VGQVR80We3JLasrToUbIqIfBU.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/gentiumbasic/v10/WnzmHAw9aB_JD2VGQVR80We3LAixMT8eaKiNbBVWkw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Gentium Book Basic` extends GoogleFont {
    override lazy val family: String = "Gentium Book Basic"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gentiumbookbasic/v9/pe0zMJCbPYBVokB1LHA9bbyaQb8ZGjcIV7t7w6bE2A.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/gentiumbookbasic/v9/pe0xMJCbPYBVokB1LHA9bbyaQb8ZGjc4VbF_4aPU2Ec9.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gentiumbookbasic/v9/pe0wMJCbPYBVokB1LHA9bbyaQb8ZGjcw65Rfy43Y0V4kvg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/gentiumbookbasic/v9/pe0-MJCbPYBVokB1LHA9bbyaQb8ZGjc4VYnDzofc81s0voO3.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Geo` extends GoogleFont {
    override lazy val family: String = "Geo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/geo/v10/CSRz4zRZlufVL3BmQjlCbQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/geo/v10/CSRx4zRZluflLXpiYDxSbf8r.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Geostar` extends GoogleFont {
    override lazy val family: String = "Geostar"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/geostar/v9/sykz-yx4n701VLOftSq9-trEvlQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Geostar Fill` extends GoogleFont {
    override lazy val family: String = "Geostar Fill"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/geostarfill/v9/AMOWz4SWuWiXFfjEohxQ9os0U1K2w9lb4g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Germania One` extends GoogleFont {
    override lazy val family: String = "Germania One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/germaniaone/v6/Fh4yPjrqIyv2ucM2qzBjeS3ezAJONau6ew.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gidugu` extends GoogleFont {
    override lazy val family: String = "Gidugu"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gidugu/v5/L0x8DFMkk1Uf6w3RvPCmRSlUig.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gilda Display` extends GoogleFont {
    override lazy val family: String = "Gilda Display"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gildadisplay/v6/t5tmIRoYMoaYG0WEOh7HwMeR7TnFrpOHYh4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Give You Glory` extends GoogleFont {
    override lazy val family: String = "Give You Glory"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/giveyouglory/v8/8QIQdiHOgt3vv4LR7ahjw9-XYc1zB4ZD6rwa.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Glass Antiqua` extends GoogleFont {
    override lazy val family: String = "Glass Antiqua"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/glassantiqua/v6/xfu30Wr0Wn3NOQM2piC0uXOjnL_wN6fRUkY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Glegoo` extends GoogleFont {
    override lazy val family: String = "Glegoo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/glegoo/v8/_Xmt-HQyrTKWaw2Ji6mZAI91xw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/glegoo/v8/_Xmu-HQyrTKWaw2xN4a9CKRpzimMsg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Gloria Hallelujah` extends GoogleFont {
    override lazy val family: String = "Gloria Hallelujah"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gloriahallelujah/v10/LYjYdHv3kUk9BMV96EIswT9DIbW-MLSy3TKEvkCF.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Goblin One` extends GoogleFont {
    override lazy val family: String = "Goblin One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/goblinone/v8/CSR64z1ZnOqZRjRCBVY_TOcATNt_pOU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gochi Hand` extends GoogleFont {
    override lazy val family: String = "Gochi Hand"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gochihand/v9/hES06XlsOjtJsgCkx1PkTo71-n0nXWA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gorditas` extends GoogleFont {
    override lazy val family: String = "Gorditas"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gorditas/v6/ll8_K2aTVD26DsPEtQDoDa4AlxYb.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gorditas/v6/ll84K2aTVD26DsPEtThUIooIvAoShA1i.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Gothic A1` extends GoogleFont {
    override lazy val family: String = "Gothic A1"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/gothica1/v7/CSR74z5ZnPydRjlCCwlCCMcqYtd2vfwk.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/gothica1/v7/CSR44z5ZnPydRjlCCwlCpOYKSPl6tOU9Eg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/gothica1/v7/CSR44z5ZnPydRjlCCwlCwOUKSPl6tOU9Eg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gothica1/v7/CSR94z5ZnPydRjlCCwl6bM0uQNJmvQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/gothica1/v7/CSR44z5ZnPydRjlCCwlCmOQKSPl6tOU9Eg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/gothica1/v7/CSR44z5ZnPydRjlCCwlCtOMKSPl6tOU9Eg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gothica1/v7/CSR44z5ZnPydRjlCCwlC0OIKSPl6tOU9Eg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/gothica1/v7/CSR44z5ZnPydRjlCCwlCzOEKSPl6tOU9Eg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/gothica1/v7/CSR44z5ZnPydRjlCCwlC6OAKSPl6tOU9Eg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Goudy Bookletter 1911` extends GoogleFont {
    override lazy val family: String = "Goudy Bookletter 1911"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/goudybookletter1911/v8/sykt-z54laciWfKv-kX8krex0jDiD2HbY6I5tRbXZ4IXAA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Graduate` extends GoogleFont {
    override lazy val family: String = "Graduate"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/graduate/v6/C8cg4cs3o2n15t_2YxgR6X2NZAn2.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Grand Hotel` extends GoogleFont {
    override lazy val family: String = "Grand Hotel"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/grandhotel/v6/7Au7p_IgjDKdCRWuR1azpmQNEl0O0kEx.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gravitas One` extends GoogleFont {
    override lazy val family: String = "Gravitas One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gravitasone/v8/5h1diZ4hJ3cblKy3LWakKQmaDWRNr3DzbQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Great Vibes` extends GoogleFont {
    override lazy val family: String = "Great Vibes"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/greatvibes/v6/RWmMoKWR9v4ksMfaWd_JN-XCg6UKDXlq.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Griffy` extends GoogleFont {
    override lazy val family: String = "Griffy"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/griffy/v7/FwZa7-ox2FQh9kfwSNSEwM2zpA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gruppo` extends GoogleFont {
    override lazy val family: String = "Gruppo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gruppo/v9/WwkfxPmzE06v_ZWFWXDAOIEQUQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gudea` extends GoogleFont {
    override lazy val family: String = "Gudea"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gudea/v8/neIFzCqgsI0mp-CP9IGON7Ez.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/gudea/v8/neILzCqgsI0mp9CN_oWsMqEzSJQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/gudea/v8/neIIzCqgsI0mp9gz26WGHK06UY30.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Gugi` extends GoogleFont {
    override lazy val family: String = "Gugi"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gugi/v7/A2BVn5dXywshVA6A9DEfgqM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Gurajada` extends GoogleFont {
    override lazy val family: String = "Gurajada"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/gurajada/v6/FwZY7-Qx308m-l-0Kd6A4sijpFu_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Habibi` extends GoogleFont {
    override lazy val family: String = "Habibi"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/habibi/v7/CSR-4zFWkuqcTTNCShJeZOYySQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Halant` extends GoogleFont {
    override lazy val family: String = "Halant"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/halant/v6/u-490qaujRI2Pbsvc_pCmwZqcwdRXg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/halant/v6/u-4-0qaujRI2PbsX39Jmky12eg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/halant/v6/u-490qaujRI2PbsvK_tCmwZqcwdRXg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/halant/v6/u-490qaujRI2PbsvB_xCmwZqcwdRXg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/halant/v6/u-490qaujRI2PbsvY_1CmwZqcwdRXg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Hammersmith One` extends GoogleFont {
    override lazy val family: String = "Hammersmith One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hammersmithone/v9/qWcyB624q4L_C4jGQ9IK0O_dFlnbshsks4MRXw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Hanalei` extends GoogleFont {
    override lazy val family: String = "Hanalei"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hanalei/v9/E21n_dD8iufIjBRHXzgmVydREus.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Hanalei Fill` extends GoogleFont {
    override lazy val family: String = "Hanalei Fill"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hanaleifill/v7/fC1mPYtObGbfyQznIaQzPQiMVwLBplm9aw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Handlee` extends GoogleFont {
    override lazy val family: String = "Handlee"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/handlee/v7/-F6xfjBsISg9aMakDmr6oilJ3ik.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Hanuman` extends GoogleFont {
    override lazy val family: String = "Hanuman"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hanuman/v12/VuJxdNvD15HhpJJBeKbXOIFneRo.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hanuman/v12/VuJ0dNvD15HhpJJBQBr4HIlMZRNcp0o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Happy Monkey` extends GoogleFont {
    override lazy val family: String = "Happy Monkey"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/happymonkey/v7/K2F2fZZcl-9SXwl5F_C4R_OABwD2bWqVjw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Harmattan` extends GoogleFont {
    override lazy val family: String = "Harmattan"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/harmattan/v5/goksH6L2DkFvVvRp9XpTS0CjkP1Yog.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Headland One` extends GoogleFont {
    override lazy val family: String = "Headland One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/headlandone/v6/yYLu0hHR2vKnp89Tk1TCq3Tx0PlTeZ3mJA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Heebo` extends GoogleFont {
    override lazy val family: String = "Heebo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/heebo/v4/NGS0v5_NC0k9P9mVTbRhtKMByaw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/heebo/v4/NGS3v5_NC0k9P9ldb5RLmq8I0LVF.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/heebo/v4/NGS6v5_NC0k9P-HxR7BDsbMB.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/heebo/v4/NGS3v5_NC0k9P9kFbpRLmq8I0LVF.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/heebo/v4/NGS3v5_NC0k9P9lNaJRLmq8I0LVF.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/heebo/v4/NGS3v5_NC0k9P9lRa5RLmq8I0LVF.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/heebo/v4/NGS3v5_NC0k9P9l1apRLmq8I0LVF.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `800`, `900`)
  }
  object `Henny Penny` extends GoogleFont {
    override lazy val family: String = "Henny Penny"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hennypenny/v6/wXKvE3UZookzsxz_kjGSfMQqt3M7tMDT.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Herr Von Muellerhoff` extends GoogleFont {
    override lazy val family: String = "Herr Von Muellerhoff"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/herrvonmuellerhoff/v8/WBL6rFjRZkREW8WqmCWYLgCkQKXb4CAft3c6_qJY3QPQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Hi Melody` extends GoogleFont {
    override lazy val family: String = "Hi Melody"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/himelody/v7/46ktlbP8Vnz0pJcqCTbEf29E31BBGA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Hind` extends GoogleFont {
    override lazy val family: String = "Hind"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/hind/v9/5aU19_a8oxmIfMJaIRuYjDpf5Vw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hind/v9/5aU69_a8oxmIRG5yBROzkDM.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/hind/v9/5aU19_a8oxmIfJpbIRuYjDpf5Vw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/hind/v9/5aU19_a8oxmIfLZcIRuYjDpf5Vw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hind/v9/5aU19_a8oxmIfNJdIRuYjDpf5Vw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Hind Guntur` extends GoogleFont {
    override lazy val family: String = "Hind Guntur"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `telugu`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/hindguntur/v4/wXKyE3UZrok56nvamSuJd_yGn1czn9zaj5Ju.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hindguntur/v4/wXKvE3UZrok56nvamSuJd8Qqt3M7tMDT.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/hindguntur/v4/wXKyE3UZrok56nvamSuJd_zenlczn9zaj5Ju.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/hindguntur/v4/wXKyE3UZrok56nvamSuJd_zymVczn9zaj5Ju.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hindguntur/v4/wXKyE3UZrok56nvamSuJd_yWmFczn9zaj5Ju.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Hind Madurai` extends GoogleFont {
    override lazy val family: String = "Hind Madurai"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `tamil`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/hindmadurai/v4/f0Xu0e2p98ZvDXdZQIOcpqjfXaUnecsoMJ0b_g.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hindmadurai/v4/f0Xx0e2p98ZvDXdZQIOcpqjn8Y0DceA0OQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/hindmadurai/v4/f0Xu0e2p98ZvDXdZQIOcpqjfBaQnecsoMJ0b_g.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/hindmadurai/v4/f0Xu0e2p98ZvDXdZQIOcpqjfKaMnecsoMJ0b_g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hindmadurai/v4/f0Xu0e2p98ZvDXdZQIOcpqjfTaInecsoMJ0b_g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Hind Siliguri` extends GoogleFont {
    override lazy val family: String = "Hind Siliguri"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `bengali`: GoogleFontSubset = new GoogleFontSubset("bengali")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`bengali`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/hindsiliguri/v5/ijwOs5juQtsyLLR5jN4cxBEoRDf44uEfKiGvxts.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hindsiliguri/v5/ijwTs5juQtsyLLR5jN4cxBEofJvQxuk0Nig.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/hindsiliguri/v5/ijwOs5juQtsyLLR5jN4cxBEoRG_54uEfKiGvxts.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/hindsiliguri/v5/ijwOs5juQtsyLLR5jN4cxBEoREP-4uEfKiGvxts.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hindsiliguri/v5/ijwOs5juQtsyLLR5jN4cxBEoRCf_4uEfKiGvxts.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Hind Vadodara` extends GoogleFont {
    override lazy val family: String = "Hind Vadodara"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `gujarati`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/hindvadodara/v5/neIQzCKvrIcn5pbuuuriV9tTSDn3iXM0oSOL2Yw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/hindvadodara/v5/neINzCKvrIcn5pbuuuriV9tTcJXfrXsfvSo.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/hindvadodara/v5/neIQzCKvrIcn5pbuuuriV9tTSGH2iXM0oSOL2Yw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/hindvadodara/v5/neIQzCKvrIcn5pbuuuriV9tTSE3xiXM0oSOL2Yw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/hindvadodara/v5/neIQzCKvrIcn5pbuuuriV9tTSCnwiXM0oSOL2Yw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Holtwood One SC` extends GoogleFont {
    override lazy val family: String = "Holtwood One SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/holtwoodonesc/v9/yYLx0hLR0P-3vMFSk1TCq3Txg5B3cbb6LZttyg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Homemade Apple` extends GoogleFont {
    override lazy val family: String = "Homemade Apple"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/homemadeapple/v9/Qw3EZQFXECDrI2q789EKQZJob3x9Vnksi4M7.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Homenaje` extends GoogleFont {
    override lazy val family: String = "Homenaje"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/homenaje/v8/FwZY7-Q-xVAi_l-6Ld6A4sijpFu_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `IBM Plex Mono` extends GoogleFont {
    override lazy val family: String = "IBM Plex Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/ibmplexmono/v4/-F6pfjptAgt5VM-kVkqdyU8n3kwq0n1hj-sNFQ.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/ibmplexmono/v4/-F6rfjptAgt5VM-kVkqdyU8n1ioStndlre4dFcFh.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/ibmplexmono/v4/-F6qfjptAgt5VM-kVkqdyU8n3uAL8ldPg-IUDNg.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/ibmplexmono/v4/-F6sfjptAgt5VM-kVkqdyU8n1ioSGlZFh8ARHNh4zg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/ibmplexmono/v4/-F6qfjptAgt5VM-kVkqdyU8n3oQI8ldPg-IUDNg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/ibmplexmono/v4/-F6sfjptAgt5VM-kVkqdyU8n1ioSflVFh8ARHNh4zg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ibmplexmono/v4/-F63fjptAgt5VM-kVkqdyU8n5igg1l9kn-s.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ibmplexmono/v4/-F6pfjptAgt5VM-kVkqdyU8n1ioq0n1hj-sNFQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ibmplexmono/v4/-F6qfjptAgt5VM-kVkqdyU8n3twJ8ldPg-IUDNg.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ibmplexmono/v4/-F6sfjptAgt5VM-kVkqdyU8n1ioSJlRFh8ARHNh4zg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/ibmplexmono/v4/-F6qfjptAgt5VM-kVkqdyU8n3vAO8ldPg-IUDNg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/ibmplexmono/v4/-F6sfjptAgt5VM-kVkqdyU8n1ioSClNFh8ARHNh4zg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ibmplexmono/v4/-F6qfjptAgt5VM-kVkqdyU8n3pQP8ldPg-IUDNg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ibmplexmono/v4/-F6sfjptAgt5VM-kVkqdyU8n1ioSblJFh8ARHNh4zg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `IBM Plex Sans` extends GoogleFont {
    override lazy val family: String = "IBM Plex Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/ibmplexsans/v6/zYX-KVElMYYaJe8bpLHnCwDKjbLeEKxIedbzDw.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/ibmplexsans/v6/zYX8KVElMYYaJe8bpLHnCwDKhdTmdKZMW9PjD3N8.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/ibmplexsans/v6/zYX9KVElMYYaJe8bpLHnCwDKjR7_MIZmdd_qFmo.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/ibmplexsans/v6/zYX7KVElMYYaJe8bpLHnCwDKhdTm2Idscf3vBmpl8A.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/ibmplexsans/v6/zYX9KVElMYYaJe8bpLHnCwDKjXr8MIZmdd_qFmo.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/ibmplexsans/v6/zYX7KVElMYYaJe8bpLHnCwDKhdTmvIRscf3vBmpl8A.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ibmplexsans/v6/zYXgKVElMYYaJe8bpLHnCwDKtdbUFI5NadY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ibmplexsans/v6/zYX-KVElMYYaJe8bpLHnCwDKhdTeEKxIedbzDw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ibmplexsans/v6/zYX9KVElMYYaJe8bpLHnCwDKjSL9MIZmdd_qFmo.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ibmplexsans/v6/zYX7KVElMYYaJe8bpLHnCwDKhdTm5IVscf3vBmpl8A.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/ibmplexsans/v6/zYX9KVElMYYaJe8bpLHnCwDKjQ76MIZmdd_qFmo.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/ibmplexsans/v6/zYX7KVElMYYaJe8bpLHnCwDKhdTmyIJscf3vBmpl8A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ibmplexsans/v6/zYX9KVElMYYaJe8bpLHnCwDKjWr7MIZmdd_qFmo.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ibmplexsans/v6/zYX7KVElMYYaJe8bpLHnCwDKhdTmrINscf3vBmpl8A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `IBM Plex Sans Condensed` extends GoogleFont {
    override lazy val family: String = "IBM Plex Sans Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/ibmplexsanscondensed/v5/Gg8nN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY7KyKvBgYsMDhM.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/ibmplexsanscondensed/v5/Gg8hN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8M_LhakJHhOgBg.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/ibmplexsanscondensed/v5/Gg8gN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY5m6Yvrr4cFFwq5.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/ibmplexsanscondensed/v5/Gg8iN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8GPqpYMnEhq5H1w.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/ibmplexsanscondensed/v5/Gg8gN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY4C6ovrr4cFFwq5.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/ibmplexsanscondensed/v5/Gg8iN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8AfppYMnEhq5H1w.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ibmplexsanscondensed/v5/Gg8lN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHbauwq_jhJsM.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ibmplexsanscondensed/v5/Gg8nN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYasyKvBgYsMDhM.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ibmplexsanscondensed/v5/Gg8gN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY5a64vrr4cFFwq5.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ibmplexsanscondensed/v5/Gg8iN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8F_opYMnEhq5H1w.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/ibmplexsanscondensed/v5/Gg8gN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY527Ivrr4cFFwq5.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/ibmplexsanscondensed/v5/Gg8iN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8HPvpYMnEhq5H1w.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ibmplexsanscondensed/v5/Gg8gN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHY4S7Yvrr4cFFwq5.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ibmplexsanscondensed/v5/Gg8iN4UfRSqiPg7Jn2ZI12V4DCEwkj1E4LVeHYas8BfupYMnEhq5H1w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `IBM Plex Serif` extends GoogleFont {
    override lazy val family: String = "IBM Plex Serif"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/ibmplexserif/v7/jizBREVNn1dOx-zrZ2X3pZvkTi182zIZj1bIkNo.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/ibmplexserif/v7/jizHREVNn1dOx-zrZ2X3pZvkTiUa41YTi3TNgNq55w.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/ibmplexserif/v7/jizAREVNn1dOx-zrZ2X3pZvkTi3Q-hIzoVrBicOg.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/ibmplexserif/v7/jizGREVNn1dOx-zrZ2X3pZvkTiUa4_oyq17jjNOg_oc.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/ibmplexserif/v7/jizAREVNn1dOx-zrZ2X3pZvkTi20-RIzoVrBicOg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/ibmplexserif/v7/jizGREVNn1dOx-zrZ2X3pZvkTiUa454xq17jjNOg_oc.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ibmplexserif/v7/jizDREVNn1dOx-zrZ2X3pZvkThUY0TY7ikbI.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ibmplexserif/v7/jizBREVNn1dOx-zrZ2X3pZvkTiUa2zIZj1bIkNo.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ibmplexserif/v7/jizAREVNn1dOx-zrZ2X3pZvkTi3s-BIzoVrBicOg.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ibmplexserif/v7/jizGREVNn1dOx-zrZ2X3pZvkTiUa48Ywq17jjNOg_oc.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/ibmplexserif/v7/jizAREVNn1dOx-zrZ2X3pZvkTi3A_xIzoVrBicOg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/ibmplexserif/v7/jizGREVNn1dOx-zrZ2X3pZvkTiUa4-o3q17jjNOg_oc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ibmplexserif/v7/jizAREVNn1dOx-zrZ2X3pZvkTi2k_hIzoVrBicOg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ibmplexserif/v7/jizGREVNn1dOx-zrZ2X3pZvkTiUa4442q17jjNOg_oc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `IM Fell DW Pica` extends GoogleFont {
    override lazy val family: String = "IM Fell DW Pica"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfelldwpica/v8/2sDGZGRQotv9nbn2qSl0TxXVYNw9ZAPUvi88MQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/imfelldwpica/v8/2sDEZGRQotv9nbn2qSl0TxXVYNwNZgnQnCosMXm0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `IM Fell DW Pica SC` extends GoogleFont {
    override lazy val family: String = "IM Fell DW Pica SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfelldwpicasc/v8/0ybjGCAu5PfqkvtGVU15aBhXz3EUrnTW-BiKEUiBGA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `IM Fell Double Pica` extends GoogleFont {
    override lazy val family: String = "IM Fell Double Pica"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfelldoublepica/v8/3XF2EqMq_94s9PeKF7Fg4gOKINyMtZ8rT0S1UL5Ayp0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/imfelldoublepica/v8/3XF0EqMq_94s9PeKF7Fg4gOKINyMtZ8rf0a_VJxF2p2G8g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `IM Fell Double Pica SC` extends GoogleFont {
    override lazy val family: String = "IM Fell Double Pica SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfelldoublepicasc/v8/neIazDmuiMkFo6zj_sHpQ8teNbWlwBB_hXjJ4Y0Eeru2dGg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `IM Fell English` extends GoogleFont {
    override lazy val family: String = "IM Fell English"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellenglish/v8/Ktk1ALSLW8zDe0rthJysWrnLsAz3F6mZVY9Y5w.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/imfellenglish/v8/Ktk3ALSLW8zDe0rthJysWrnLsAzHFaOdd4pI59zg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `IM Fell English SC` extends GoogleFont {
    override lazy val family: String = "IM Fell English SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellenglishsc/v8/a8IENpD3CDX-4zrWfr1VY879qFF05pZLO4gOg0shzA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `IM Fell French Canon` extends GoogleFont {
    override lazy val family: String = "IM Fell French Canon"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellfrenchcanon/v8/-F6ufiNtDWYfYc-tDiyiw08rrghJszkK6coVPt1ozoPz.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/imfellfrenchcanon/v8/-F6gfiNtDWYfYc-tDiyiw08rrghJszkK6foXNNlKy5PzzrU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `IM Fell French Canon SC` extends GoogleFont {
    override lazy val family: String = "IM Fell French Canon SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellfrenchcanonsc/v8/FBVmdCru5-ifcor2bgq9V89khWcmQghEURY7H3c0UBCVIVqH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `IM Fell Great Primer` extends GoogleFont {
    override lazy val family: String = "IM Fell Great Primer"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellgreatprimer/v8/bx6aNwSJtayYxOkbYFsT6hMsLzX7u85rJorXvDo3SQY1.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/imfellgreatprimer/v8/bx6UNwSJtayYxOkbYFsT6hMsLzX7u85rJrrVtj4VTBY1N6U.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `IM Fell Great Primer SC` extends GoogleFont {
    override lazy val family: String = "IM Fell Great Primer SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imfellgreatprimersc/v8/ga6daxBOxyt6sCqz3fjZCTFCTUDMHagsQKdDTLf9BXz0s8FG.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Iceberg` extends GoogleFont {
    override lazy val family: String = "Iceberg"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/iceberg/v6/8QIJdijAiM7o-qnZuIgOq7jkAOw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Iceland` extends GoogleFont {
    override lazy val family: String = "Iceland"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/iceland/v7/rax9HiuFsdMNOnWPWKxGADBbg0s.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Imprima` extends GoogleFont {
    override lazy val family: String = "Imprima"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/imprima/v7/VEMxRoN7sY3yuy-7-oWHyDzktPo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Inconsolata` extends GoogleFont {
    override lazy val family: String = "Inconsolata"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/inconsolata/v17/QldKNThLqRwH-OJ1UHjlKFle7KlmxuHx.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/inconsolata/v17/QldXNThLqRwH-OJ1UHjlKGHiw41u7f34DYwn.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Inder` extends GoogleFont {
    override lazy val family: String = "Inder"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/inder/v7/w8gUH2YoQe8_4vq6pw-P3U4O.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Indie Flower` extends GoogleFont {
    override lazy val family: String = "Indie Flower"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/indieflower/v10/m8JVjfNVeKWVnh3QMuKkFcZlbkGG1dKEDw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Inika` extends GoogleFont {
    override lazy val family: String = "Inika"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/inika/v7/rnCm-x5X3QP-phTHRcc2s2XH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/inika/v7/rnCr-x5X3QP-pix7auM-mHnOSOuk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Inknut Antiqua` extends GoogleFont {
    override lazy val family: String = "Inknut Antiqua"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/inknutantiqua/v4/Y4GRYax7VC4ot_qNB4nYpBdaKU2vwrj5bBoIYJNf.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/inknutantiqua/v4/Y4GSYax7VC4ot_qNB4nYpBdaKXUD6pzxRwYB.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/inknutantiqua/v4/Y4GRYax7VC4ot_qNB4nYpBdaKU33w7j5bBoIYJNf.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/inknutantiqua/v4/Y4GRYax7VC4ot_qNB4nYpBdaKU3bxLj5bBoIYJNf.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/inknutantiqua/v4/Y4GRYax7VC4ot_qNB4nYpBdaKU2_xbj5bBoIYJNf.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/inknutantiqua/v4/Y4GRYax7VC4ot_qNB4nYpBdaKU2jxrj5bBoIYJNf.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/inknutantiqua/v4/Y4GRYax7VC4ot_qNB4nYpBdaKU2Hx7j5bBoIYJNf.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Irish Grover` extends GoogleFont {
    override lazy val family: String = "Irish Grover"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/irishgrover/v9/buExpoi6YtLz2QW7LA4flVgf-P5Oaiw4cw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Istok Web` extends GoogleFont {
    override lazy val family: String = "Istok Web"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/istokweb/v13/3qTvojGmgSyUukBzKslZAWF-9kIIaQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/istokweb/v13/3qTpojGmgSyUukBzKslpA2t61EcYaQ7F.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/istokweb/v13/3qTqojGmgSyUukBzKslhvU5a_mkUYBfcMw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/istokweb/v13/3qT0ojGmgSyUukBzKslpA1PG-2MQQhLMMygN.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Italiana` extends GoogleFont {
    override lazy val family: String = "Italiana"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/italiana/v7/QldNNTtLsx4E__B0XTmRY31Wx7Vv.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Italianno` extends GoogleFont {
    override lazy val family: String = "Italianno"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/italianno/v8/dg4n_p3sv6gCJkwzT6Rnj5YpQwM-gg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Itim` extends GoogleFont {
    override lazy val family: String = "Itim"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/itim/v3/0nknC9ziJOYewARKkc7ZdwU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jacques Francois` extends GoogleFont {
    override lazy val family: String = "Jacques Francois"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jacquesfrancois/v6/ZXu9e04ZvKeOOHIe1TMahbcIU2cgmcPqoeRWfbs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jacques Francois Shadow` extends GoogleFont {
    override lazy val family: String = "Jacques Francois Shadow"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jacquesfrancoisshadow/v7/KR1FBtOz8PKTMk-kqdkLVrvR0ECFrB6Pin-2_q8VsHuV5ULS.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jaldi` extends GoogleFont {
    override lazy val family: String = "Jaldi"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jaldi/v5/or3sQ67z0_CI30NUZpD_B6g8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/jaldi/v5/or3hQ67z0_CI33voSbT3LLQ1niPn.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Jim Nightshade` extends GoogleFont {
    override lazy val family: String = "Jim Nightshade"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jimnightshade/v6/PlIkFlu9Pb08Q8HLM1PxmB0g-OS4V3qKaMxD.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jockey One` extends GoogleFont {
    override lazy val family: String = "Jockey One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jockeyone/v8/HTxpL2g2KjCFj4x8WI6ArIb7HYOk4xc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jolly Lodger` extends GoogleFont {
    override lazy val family: String = "Jolly Lodger"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jollylodger/v6/BXRsvFTAh_bGkA1uQ48dlB3VWerT3ZyuqA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jomhuria` extends GoogleFont {
    override lazy val family: String = "Jomhuria"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jomhuria/v6/Dxxp8j-TMXf-llKur2b1MOGbC3Dh.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Josefin Sans` extends GoogleFont {
    override lazy val family: String = "Josefin Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/josefinsans/v13/Qw3EZQNVED7rKGKxtqIqX5Ecbnx9Vnksi4M7.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/josefinsans/v13/Qw3GZQNVED7rKGKxtqIqX5EUCEQZXH0OjpM75PE.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/josefinsans/v13/Qw3FZQNVED7rKGKxtqIqX5Ecpl5dfFcggpoi_Q.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/josefinsans/v13/Qw3HZQNVED7rKGKxtqIqX5EUCETRfl0koJ8y_eiS.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/josefinsans/v13/Qw3aZQNVED7rKGKxtqIqX5EkCnZ5dHw8iw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/josefinsans/v13/Qw3EZQNVED7rKGKxtqIqX5EUCHx9Vnksi4M7.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/josefinsans/v13/Qw3FZQNVED7rKGKxtqIqX5Ec0lhdfFcggpoi_Q.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/josefinsans/v13/Qw3HZQNVED7rKGKxtqIqX5EUCESleF0koJ8y_eiS.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/josefinsans/v13/Qw3FZQNVED7rKGKxtqIqX5EctlldfFcggpoi_Q.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/josefinsans/v13/Qw3HZQNVED7rKGKxtqIqX5EUCETBeV0koJ8y_eiS.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Josefin Slab` extends GoogleFont {
    override lazy val family: String = "Josefin Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/josefinslab/v9/lW-nwjwOK3Ps5GSJlNNkMalvyQ6qBM7oPxMX.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/josefinslab/v9/lW-lwjwOK3Ps5GSJlNNkMalnrzbODsrKOgMX95A.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/josefinslab/v9/lW-mwjwOK3Ps5GSJlNNkMalvASyKLuDkNgoO7g.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/josefinslab/v9/lW-kwjwOK3Ps5GSJlNNkMalnrzYGLOrgFA8e7onu.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/josefinslab/v9/lW-5wjwOK3Ps5GSJlNNkMalXrQSuJsv4Pw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/josefinslab/v9/lW-nwjwOK3Ps5GSJlNNkMalnrw6qBM7oPxMX.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/josefinslab/v9/lW-mwjwOK3Ps5GSJlNNkMalvdSqKLuDkNgoO7g.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/josefinslab/v9/lW-kwjwOK3Ps5GSJlNNkMalnrzZyKurgFA8e7onu.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/josefinslab/v9/lW-mwjwOK3Ps5GSJlNNkMalvESuKLuDkNgoO7g.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/josefinslab/v9/lW-kwjwOK3Ps5GSJlNNkMalnrzYWK-rgFA8e7onu.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Joti One` extends GoogleFont {
    override lazy val family: String = "Joti One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jotione/v7/Z9XVDmdJQAmWm9TwaYTe4u2El6GC.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jua` extends GoogleFont {
    override lazy val family: String = "Jua"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jua/v7/co3KmW9ljjAjc-DZCsKgsg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Judson` extends GoogleFont {
    override lazy val family: String = "Judson"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/judson/v11/FeVRS0Fbvbc14VxRD7N01bV7kg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/judson/v11/FeVTS0Fbvbc14VxhDblw97BrknZf.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/judson/v11/FeVSS0Fbvbc14Vxps5xQ3Z5nm29Gww.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Julee` extends GoogleFont {
    override lazy val family: String = "Julee"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/julee/v8/TuGfUVB3RpZPQ6ZLodgzydtk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Julius Sans One` extends GoogleFont {
    override lazy val family: String = "Julius Sans One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/juliussansone/v7/1Pt2g8TAX_SGgBGUi0tGOYEga5W-xXEW6aGXHw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Junge` extends GoogleFont {
    override lazy val family: String = "Junge"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/junge/v6/gokgH670Gl1lUqAdvhB7SnKm.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Jura` extends GoogleFont {
    override lazy val family: String = "Jura"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/jura/v10/z7NUdRfiaC4VVW9rdCxo0JTVVPw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/jura/v10/z7NbdRfiaC4VbcNDUCRDzJ0.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/jura/v10/z7NUdRfiaC4VVTdqdCxo0JTVVPw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/jura/v10/z7NUdRfiaC4VVRttdCxo0JTVVPw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/jura/v10/z7NUdRfiaC4VVX9sdCxo0JTVVPw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Just Another Hand` extends GoogleFont {
    override lazy val family: String = "Just Another Hand"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/justanotherhand/v10/845CNN4-AJyIGvIou-6yJKyptyOpOcr_BmmlS5aw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Just Me Again Down Here` extends GoogleFont {
    override lazy val family: String = "Just Me Again Down Here"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/justmeagaindownhere/v10/MwQmbgXtz-Wc6RUEGNMc0QpRrfUh2hSdBBMoAuwHvqDwc_fg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `K2D` extends GoogleFont {
    override lazy val family: String = "K2D"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/k2d/v2/J7aRnpF2V0ErE6UpvrIw74NL.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/k2d/v2/J7afnpF2V0EjdZ1NtLYS6pNLAjk.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/k2d/v2/J7aenpF2V0Erv4QJlJw85ppSGw.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/k2d/v2/J7acnpF2V0EjdZ3hlZY4xJ9CGyAa.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/k2d/v2/J7aenpF2V0Er24cJlJw85ppSGw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/k2d/v2/J7acnpF2V0EjdZ2FlpY4xJ9CGyAa.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/k2d/v2/J7aTnpF2V0ETd68tnLcg7w.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/k2d/v2/J7aRnpF2V0EjdaUpvrIw74NL.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/k2d/v2/J7aenpF2V0Erg4YJlJw85ppSGw.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/k2d/v2/J7acnpF2V0EjdZ3dl5Y4xJ9CGyAa.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/k2d/v2/J7aenpF2V0Err4EJlJw85ppSGw.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/k2d/v2/J7acnpF2V0EjdZ3xkJY4xJ9CGyAa.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/k2d/v2/J7aenpF2V0Ery4AJlJw85ppSGw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/k2d/v2/J7acnpF2V0EjdZ2VkZY4xJ9CGyAa.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/k2d/v2/J7aenpF2V0Er14MJlJw85ppSGw.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/k2d/v2/J7acnpF2V0EjdZ2JkpY4xJ9CGyAa.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Kadwa` extends GoogleFont {
    override lazy val family: String = "Kadwa"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kadwa/v3/rnCm-x5V0g7iphTHRcc2s2XH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kadwa/v3/rnCr-x5V0g7ipix7auM-mHnOSOuk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Kalam` extends GoogleFont {
    override lazy val family: String = "Kalam"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/kalam/v9/YA9Qr0Wd4kDdMtD6GgLLmCUItqGt.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kalam/v9/YA9dr0Wd4kDdMuhWMibDszkB.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kalam/v9/YA9Qr0Wd4kDdMtDqHQLLmCUItqGt.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Kameron` extends GoogleFont {
    override lazy val family: String = "Kameron"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kameron/v9/vm82dR7vXErQxuznsL4wL-XIYH8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kameron/v9/vm8zdR7vXErQxuzniAIfC-3jfHb--NY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Kanit` extends GoogleFont {
    override lazy val family: String = "Kanit"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/kanit/v4/nKKX-Go6G5tXcr72GwWKcaxALFs.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/kanit/v4/nKKV-Go6G5tXcraQI2GAdY5FPFtrGw.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/kanit/v4/nKKU-Go6G5tXcr5aOiWgX6BJNUJy.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/kanit/v4/nKKS-Go6G5tXcraQI82hVaRrMFJyAu4.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/kanit/v4/nKKU-Go6G5tXcr4-OSWgX6BJNUJy.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/kanit/v4/nKKS-Go6G5tXcraQI6miVaRrMFJyAu4.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kanit/v4/nKKZ-Go6G5tXcoaSEQGodLxA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/kanit/v4/nKKX-Go6G5tXcraQGwWKcaxALFs.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/kanit/v4/nKKU-Go6G5tXcr5mOCWgX6BJNUJy.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/kanit/v4/nKKS-Go6G5tXcraQI_GjVaRrMFJyAu4.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/kanit/v4/nKKU-Go6G5tXcr5KPyWgX6BJNUJy.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/kanit/v4/nKKS-Go6G5tXcraQI92kVaRrMFJyAu4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kanit/v4/nKKU-Go6G5tXcr4uPiWgX6BJNUJy.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/kanit/v4/nKKS-Go6G5tXcraQI7mlVaRrMFJyAu4.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/kanit/v4/nKKU-Go6G5tXcr4yPSWgX6BJNUJy.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/kanit/v4/nKKS-Go6G5tXcraQI6WmVaRrMFJyAu4.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/kanit/v4/nKKU-Go6G5tXcr4WPCWgX6BJNUJy.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/kanit/v4/nKKS-Go6G5tXcraQI4GnVaRrMFJyAu4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Kantumruy` extends GoogleFont {
    override lazy val family: String = "Kantumruy"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/kantumruy/v5/syk0-yJ0m7wyVb-f4FOPUtDlpn-UJ1H6Uw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kantumruy/v5/sykx-yJ0m7wyVb-f4FO3_vjBrlSILg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kantumruy/v5/syk0-yJ0m7wyVb-f4FOPQtflpn-UJ1H6Uw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Karla` extends GoogleFont {
    override lazy val family: String = "Karla"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/karla/v7/qkBbXvYC6trAT4RSJN225aZO.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/karla/v7/qkBVXvYC6trAT7RQLtmU4LZOgAU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/karla/v7/qkBWXvYC6trAT7zuC_m-zrpHmRzC.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/karla/v7/qkBQXvYC6trAT7RQFmW7xL5lnAzCKNg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Karma` extends GoogleFont {
    override lazy val family: String = "Karma"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/karma/v9/va9F4kzAzMZRGLjDY8Z_uqzGQC_-.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/karma/v9/va9I4kzAzMZRGIBvS-J3kbDP.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/karma/v9/va9F4kzAzMZRGLibYsZ_uqzGQC_-.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/karma/v9/va9F4kzAzMZRGLi3ZcZ_uqzGQC_-.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/karma/v9/va9F4kzAzMZRGLjTZMZ_uqzGQC_-.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Katibeh` extends GoogleFont {
    override lazy val family: String = "Katibeh"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/katibeh/v6/ZGjXol5MQJog4bxDaC1RVDNdGDs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kaushan Script` extends GoogleFont {
    override lazy val family: String = "Kaushan Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kaushanscript/v7/vm8vdRfvXFLG3OLnsO15WYS5DF7_ytN3M48a.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kavivanar` extends GoogleFont {
    override lazy val family: String = "Kavivanar"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `tamil`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kavivanar/v4/o-0IIpQgyXYSwhxP7_Jb4j5Ba_2c7A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kavoon` extends GoogleFont {
    override lazy val family: String = "Kavoon"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kavoon/v7/pxiFyp4_scRYhlU4NLr6f1pdEQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kdam Thmor` extends GoogleFont {
    override lazy val family: String = "Kdam Thmor"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kdamthmor/v6/MwQzbhjs3veF6QwJVf0JkGMViblPtXs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Keania One` extends GoogleFont {
    override lazy val family: String = "Keania One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/keaniaone/v6/zOL54pXJk65E8pXardnuycRuv-hHkOs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kelly Slab` extends GoogleFont {
    override lazy val family: String = "Kelly Slab"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kellyslab/v9/-W_7XJX0Rz3cxUnJC5t6TkMBf50kbiM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kenia` extends GoogleFont {
    override lazy val family: String = "Kenia"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kenia/v10/jizURE5PuHQH9qCONUGswfGM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Khand` extends GoogleFont {
    override lazy val family: String = "Khand"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/khand/v7/TwMN-IINQlQQ0bL5cFE3ZwaH__-C.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/khand/v7/TwMA-IINQlQQ0YpVWHU_TBqO.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/khand/v7/TwMN-IINQlQQ0bKhcVE3ZwaH__-C.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/khand/v7/TwMN-IINQlQQ0bKNdlE3ZwaH__-C.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/khand/v7/TwMN-IINQlQQ0bLpd1E3ZwaH__-C.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Khmer` extends GoogleFont {
    override lazy val family: String = "Khmer"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/khmer/v11/MjQImit_vPPwpF-BpN2EeYmD.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Khula` extends GoogleFont {
    override lazy val family: String = "Khula"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/khula/v4/OpNPnoEOns3V7G-ljCvUrC59XwXD.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/khula/v4/OpNCnoEOns3V7FcJpA_chzJ0.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/khula/v4/OpNPnoEOns3V7G_RiivUrC59XwXD.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/khula/v4/OpNPnoEOns3V7G-1iyvUrC59XwXD.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/khula/v4/OpNPnoEOns3V7G-piCvUrC59XwXD.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `600`, `700`, `800`)
  }
  object `Kirang Haerang` extends GoogleFont {
    override lazy val family: String = "Kirang Haerang"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kiranghaerang/v7/E21-_dn_gvvIjhYON1lpIU4-bcqvWPaJq4no.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kite One` extends GoogleFont {
    override lazy val family: String = "Kite One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kiteone/v6/70lQu7shLnA_E02vyq1b6HnGO4uA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Knewave` extends GoogleFont {
    override lazy val family: String = "Knewave"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/knewave/v7/sykz-yx0lLcxQaSItSq9-trEvlQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `KoHo` extends GoogleFont {
    override lazy val family: String = "KoHo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/koho/v2/K2FxfZ5fmddNPuE1WJ75JoKhHys.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/koho/v2/K2FzfZ5fmddNNisssJ_zIqCkDyvqZA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/koho/v2/K2FxfZ5fmddNPoU2WJ75JoKhHys.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/koho/v2/K2FzfZ5fmddNNiss1JzzIqCkDyvqZA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/koho/v2/K2F-fZ5fmddNBikefJbSOos.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/koho/v2/K2FwfZ5fmddNNisUeLTXKou4Bg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/koho/v2/K2FxfZ5fmddNPt03WJ75JoKhHys.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/koho/v2/K2FzfZ5fmddNNissjJ3zIqCkDyvqZA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/koho/v2/K2FxfZ5fmddNPvEwWJ75JoKhHys.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/koho/v2/K2FzfZ5fmddNNissoJrzIqCkDyvqZA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/koho/v2/K2FxfZ5fmddNPpUxWJ75JoKhHys.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/koho/v2/K2FzfZ5fmddNNissxJvzIqCkDyvqZA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Kodchasan` extends GoogleFont {
    override lazy val family: String = "Kodchasan"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/kodchasan/v2/1cX0aUPOAJv9sG4I-DJeR1Cggeqo3eMeoA.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/kodchasan/v2/1cXqaUPOAJv9sG4I-DJWjUlIgOCs_-YOoIgN.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/kodchasan/v2/1cX0aUPOAJv9sG4I-DJeI1Oggeqo3eMeoA.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/kodchasan/v2/1cXqaUPOAJv9sG4I-DJWjUksg-Cs_-YOoIgN.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kodchasan/v2/1cXxaUPOAJv9sG4I-DJmj3uEicG01A.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/kodchasan/v2/1cX3aUPOAJv9sG4I-DJWjXGAq8Sk1PoH.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/kodchasan/v2/1cX0aUPOAJv9sG4I-DJee1Kggeqo3eMeoA.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/kodchasan/v2/1cXqaUPOAJv9sG4I-DJWjUl0guCs_-YOoIgN.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/kodchasan/v2/1cX0aUPOAJv9sG4I-DJeV1Wggeqo3eMeoA.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/kodchasan/v2/1cXqaUPOAJv9sG4I-DJWjUlYheCs_-YOoIgN.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kodchasan/v2/1cX0aUPOAJv9sG4I-DJeM1Sggeqo3eMeoA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/kodchasan/v2/1cXqaUPOAJv9sG4I-DJWjUk8hOCs_-YOoIgN.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Kosugi` extends GoogleFont {
    override lazy val family: String = "Kosugi"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `japanese`: GoogleFontSubset = new GoogleFontSubset("japanese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`japanese`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kosugi/v5/pxiFyp4_v8FCjlI4NLr6f1pdEQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kosugi Maru` extends GoogleFont {
    override lazy val family: String = "Kosugi Maru"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `japanese`: GoogleFontSubset = new GoogleFontSubset("japanese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`japanese`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kosugimaru/v5/0nksC9PgP_wGh21A2KeqGiTqivr9iBq_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kotta One` extends GoogleFont {
    override lazy val family: String = "Kotta One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kottaone/v6/S6u_w41LXzPc_jlfNWqPHA3s5dwt7w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Koulen` extends GoogleFont {
    override lazy val family: String = "Koulen"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/koulen/v12/AMOQz46as3KIBPeWgnA9kuYMUg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kranky` extends GoogleFont {
    override lazy val family: String = "Kranky"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kranky/v9/hESw6XVgJzlPsFnMpheEZo_H_w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kreon` extends GoogleFont {
    override lazy val family: String = "Kreon"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/kreon/v13/t5tjIRIUKY-TFH1sUU23hqLgzCHu.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kreon/v13/t5tuIRIUKY-TFEXAeWm_rb7p.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/kreon/v13/t5tjIRIUKY-TFH18Vk23hqLgzCHu.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Kristi` extends GoogleFont {
    override lazy val family: String = "Kristi"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kristi/v10/uK_y4ricdeU6zwdRCh0TMv6EXw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Krona One` extends GoogleFont {
    override lazy val family: String = "Krona One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kronaone/v7/jAnEgHdjHcjgfIb1ZcUCMY-h3cWkWg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Krub` extends GoogleFont {
    override lazy val family: String = "Krub"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/krub/v2/sZlEdRyC6CRYZo47KLF4R6gWaf8.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/krub/v2/sZlGdRyC6CRYbkQiwLByQ4oTef_6gQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/krub/v2/sZlEdRyC6CRYZuo4KLF4R6gWaf8.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/krub/v2/sZlGdRyC6CRYbkQipLNyQ4oTef_6gQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/krub/v2/sZlLdRyC6CRYXkYQDLlTW6E.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/krub/v2/sZlFdRyC6CRYbkQaCJtWS6EPcA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/krub/v2/sZlEdRyC6CRYZrI5KLF4R6gWaf8.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/krub/v2/sZlGdRyC6CRYbkQi_LJyQ4oTef_6gQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/krub/v2/sZlEdRyC6CRYZp4-KLF4R6gWaf8.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/krub/v2/sZlGdRyC6CRYbkQi0LVyQ4oTef_6gQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/krub/v2/sZlEdRyC6CRYZvo_KLF4R6gWaf8.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/krub/v2/sZlGdRyC6CRYbkQitLRyQ4oTef_6gQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Kumar One` extends GoogleFont {
    override lazy val family: String = "Kumar One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `gujarati`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kumarone/v3/bMr1mS-P958wYi6YaGeGNO6WU3oT0g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kumar One Outline` extends GoogleFont {
    override lazy val family: String = "Kumar One Outline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `gujarati`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kumaroneoutline/v4/Noao6VH62pyLP0fsrZ-v18wlUEcX9zDwRQu8EGKF.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Kurale` extends GoogleFont {
    override lazy val family: String = "Kurale"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/kurale/v4/4iCs6KV9e9dXjho6eAT3v02QFg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `La Belle Aurore` extends GoogleFont {
    override lazy val family: String = "La Belle Aurore"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/labelleaurore/v9/RrQIbot8-mNYKnGNDkWlocovHeIIG-eFNVmULg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Laila` extends GoogleFont {
    override lazy val family: String = "Laila"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/laila/v5/LYjBdG_8nE8jDLzxogNAh14nVcfe.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/laila/v5/LYjMdG_8nE8jDIRdiidIrEIu.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/laila/v5/LYjBdG_8nE8jDLypowNAh14nVcfe.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/laila/v5/LYjBdG_8nE8jDLyFpANAh14nVcfe.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/laila/v5/LYjBdG_8nE8jDLzhpQNAh14nVcfe.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Lakki Reddy` extends GoogleFont {
    override lazy val family: String = "Lakki Reddy"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lakkireddy/v5/S6u5w49MUSzD9jlCPmvLZQfox9k97-xZ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lalezar` extends GoogleFont {
    override lazy val family: String = "Lalezar"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lalezar/v5/zrfl0HLVx-HwTP82UaDyIiL0RCg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lancelot` extends GoogleFont {
    override lazy val family: String = "Lancelot"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lancelot/v8/J7acnppxBGtQEulG4JY4xJ9CGyAa.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lateef` extends GoogleFont {
    override lazy val family: String = "Lateef"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lateef/v14/hESw6XVnNCxEvkbMpheEZo_H_w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lato` extends GoogleFont {
    override lazy val family: String = "Lato"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/lato/v15/S6u8w4BMUTPHh30wWyWrFCbw7A.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/lato/v15/S6u-w4BMUTPHjxsIPy-vNiPg7MU0.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/lato/v15/S6u9w4BMUTPHh7USew-FGC_p9dw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/lato/v15/S6u_w4BMUTPHjxsI9w2PHA3s5dwt7w.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lato/v15/S6uyw4BMUTPHvxk6XweuBCY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/lato/v15/S6u8w4BMUTPHjxswWyWrFCbw7A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lato/v15/S6u9w4BMUTPHh6UVew-FGC_p9dw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/lato/v15/S6u_w4BMUTPHjxsI5wqPHA3s5dwt7w.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/lato/v15/S6u9w4BMUTPHh50Xew-FGC_p9dw.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/lato/v15/S6u_w4BMUTPHjxsI3wiPHA3s5dwt7w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `League Script` extends GoogleFont {
    override lazy val family: String = "League Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/leaguescript/v9/CSR54zpSlumSWj9CGVsoBZdeaNNUuOwkC2s.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Leckerli One` extends GoogleFont {
    override lazy val family: String = "Leckerli One"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/leckerlione/v9/V8mCoQH8VCsNttEnxnGQ-1itLZxcBtItFw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ledger` extends GoogleFont {
    override lazy val family: String = "Ledger"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ledger/v6/j8_q6-HK1L3if_sxm8DwHTBhHw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lekton` extends GoogleFont {
    override lazy val family: String = "Lekton"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lekton/v9/SZc43FDmLaWmWpBeXxfonUPL6Q.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/lekton/v9/SZc63FDmLaWmWpBuXR3sv0bb6StO.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lekton/v9/SZc73FDmLaWmWpBm4zjMlWjX4DJXgQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Lemon` extends GoogleFont {
    override lazy val family: String = "Lemon"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lemon/v7/HI_EiYEVKqRMq0jBSZXAQ4-d.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lemonada` extends GoogleFont {
    override lazy val family: String = "Lemonada"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/lemonada/v6/0QIkMXFD9oygTWy_R8PindGu2bje-RpH.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lemonada/v6/0QIjMXFD9oygTWy_R_tOtfWm8qTX.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/lemonada/v6/0QIkMXFD9oygTWy_R8OWm9Gu2bje-RpH.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lemonada/v6/0QIkMXFD9oygTWy_R8PymtGu2bje-RpH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `600`, `700`)
  }
  object `Libre Barcode 128` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 128"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode128/v8/cIfnMbdUsUoiW3O_hVviCwVjuLtXeJ_A_gMk0izH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Barcode 128 Text` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 128 Text"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode128text/v8/fdNv9tubt3ZEnz1Gu3I4-zppwZ9CWZ16Z0w5cV3Y6M90w4k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Barcode 39` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 39"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode39/v8/-nFnOHM08vwC6h8Li1eQnP_AHzI2K_d709jy92k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Barcode 39 Extended` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 39 Extended"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode39extended/v7/8At7Gt6_O5yNS0-K4Nf5U922qSzhJ3dUdfJpwNUgfNRCOZ1GOBw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Barcode 39 Extended Text` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 39 Extended Text"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode39extendedtext/v7/eLG1P_rwIgOiDA7yrs9LoKaYRVLQ1YldrrOnnL7xPO4jNP68fLIiPopNNA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Barcode 39 Text` extends GoogleFont {
    override lazy val family: String = "Libre Barcode 39 Text"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebarcode39text/v8/sJoa3KhViNKANw_E3LwoDXvs5Un0HQ1vT-031RRL-9rYaw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Libre Baskerville` extends GoogleFont {
    override lazy val family: String = "Libre Baskerville"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librebaskerville/v6/kmKnZrc3Hgbbcjq75U4uslyuy4kn0pNeYRI4CN2V.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/librebaskerville/v6/kmKhZrc3Hgbbcjq75U4uslyuy4kn0qNcaxYaDc2V2ro.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/librebaskerville/v6/kmKiZrc3Hgbbcjq75U4uslyuy4kn0qviTjYwI8Gcw6Oi.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Libre Franklin` extends GoogleFont {
    override lazy val family: String = "Libre Franklin"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/librefranklin/v3/jizBREVItHgc8qDIbSTKq4XkRi182zIZj1bIkNo.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/librefranklin/v3/jizHREVItHgc8qDIbSTKq4XkRiUa41YTi3TNgNq55w.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/librefranklin/v3/jizAREVItHgc8qDIbSTKq4XkRi3Q-hIzoVrBicOg.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/librefranklin/v3/jizGREVItHgc8qDIbSTKq4XkRiUa4_oyq17jjNOg_oc.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/librefranklin/v3/jizAREVItHgc8qDIbSTKq4XkRi20-RIzoVrBicOg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/librefranklin/v3/jizGREVItHgc8qDIbSTKq4XkRiUa454xq17jjNOg_oc.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/librefranklin/v3/jizDREVItHgc8qDIbSTKq4XkRhUY0TY7ikbI.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/librefranklin/v3/jizBREVItHgc8qDIbSTKq4XkRiUa2zIZj1bIkNo.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/librefranklin/v3/jizAREVItHgc8qDIbSTKq4XkRi3s-BIzoVrBicOg.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/librefranklin/v3/jizGREVItHgc8qDIbSTKq4XkRiUa48Ywq17jjNOg_oc.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/librefranklin/v3/jizAREVItHgc8qDIbSTKq4XkRi3A_xIzoVrBicOg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/librefranklin/v3/jizGREVItHgc8qDIbSTKq4XkRiUa4-o3q17jjNOg_oc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/librefranklin/v3/jizAREVItHgc8qDIbSTKq4XkRi2k_hIzoVrBicOg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/librefranklin/v3/jizGREVItHgc8qDIbSTKq4XkRiUa4442q17jjNOg_oc.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/librefranklin/v3/jizAREVItHgc8qDIbSTKq4XkRi24_RIzoVrBicOg.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/librefranklin/v3/jizGREVItHgc8qDIbSTKq4XkRiUa45I1q17jjNOg_oc.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/librefranklin/v3/jizAREVItHgc8qDIbSTKq4XkRi2c_BIzoVrBicOg.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/librefranklin/v3/jizGREVItHgc8qDIbSTKq4XkRiUa47Y0q17jjNOg_oc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Life Savers` extends GoogleFont {
    override lazy val family: String = "Life Savers"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lifesavers/v9/ZXuie1UftKKabUQMgxAal_lrFgpbuNvB.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lifesavers/v9/ZXu_e1UftKKabUQMgxAal8HXOS5Tk8fIpPRW.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/lifesavers/v9/ZXu_e1UftKKabUQMgxAal8HLOi5Tk8fIpPRW.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `800`)
  }
  object `Lilita One` extends GoogleFont {
    override lazy val family: String = "Lilita One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lilitaone/v6/i7dPIFZ9Zz-WBtRtedDbUEZ2RFq7AwU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lily Script One` extends GoogleFont {
    override lazy val family: String = "Lily Script One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lilyscriptone/v6/LhW9MV7ZMfIPdMxeBjBvFN8SXLS4gsSjQNsRMg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Limelight` extends GoogleFont {
    override lazy val family: String = "Limelight"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/limelight/v9/XLYkIZL7aopJVbZJHDuYPeNGrnY2TA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Linden Hill` extends GoogleFont {
    override lazy val family: String = "Linden Hill"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lindenhill/v8/-F61fjxoKSg9Yc3hZgO8ygFI7CwC009k.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/lindenhill/v8/-F63fjxoKSg9Yc3hZgO8yjFK5igg1l9kn-s.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Literata` extends GoogleFont {
    override lazy val family: String = "Literata"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/literata/v3/or38Q6P12-iJxAIgLa78DkTtAoDhk0oVpaLVa5RXzC1KOw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/literata/v3/or38Q6P12-iJxAIgLa78DkTtAoDhk0oVl6LVa5RXzC1KOw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/literata/v3/or38Q6P12-iJxAIgLa78DkTtAoDhk0oVe6XVa5RXzC1KOw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/literata/v3/or38Q6P12-iJxAIgLa78DkTtAoDhk0oVQqXVa5RXzC1KOw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/literata/v3/or3yQ6P12-iJxAIgLYT1PLs1a-t7PU0AbeE9KJ5T7ihaO_CS.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/literata/v3/or3yQ6P12-iJxAIgLYT1PLs1a-t7PU0AbeEPKJ5T7ihaO_CS.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/literata/v3/or3yQ6P12-iJxAIgLYT1PLs1a-t7PU0AbeHjL55T7ihaO_CS.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/literata/v3/or3yQ6P12-iJxAIgLYT1PLs1a-t7PU0AbeHaL55T7ihaO_CS.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `italic`, `500italic`, `600italic`, `700italic`)
  }
  object `Lobster` extends GoogleFont {
    override lazy val family: String = "Lobster"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lobster/v21/neILzCirqoswsqX9_oWsMqEzSJQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lobster Two` extends GoogleFont {
    override lazy val family: String = "Lobster Two"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lobstertwo/v11/BngMUXZGTXPUvIoyV6yN59fK7KSJ4ACD.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/lobstertwo/v11/BngOUXZGTXPUvIoyV6yN5-fI5qCr5RCDY_k.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lobstertwo/v11/BngRUXZGTXPUvIoyV6yN5-92w4CByxyKeuDp.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/lobstertwo/v11/BngTUXZGTXPUvIoyV6yN5-fI3hyEwRiof_DpXMY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Londrina Outline` extends GoogleFont {
    override lazy val family: String = "Londrina Outline"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/londrinaoutline/v9/C8c44dM8vmb14dfsZxhetg3pDH-SfuoxrSKMDvI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Londrina Shadow` extends GoogleFont {
    override lazy val family: String = "Londrina Shadow"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/londrinashadow/v8/oPWX_kB4kOQoWNJmjxLV5JuoCUlXRlaSxkrMCQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Londrina Sketch` extends GoogleFont {
    override lazy val family: String = "Londrina Sketch"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/londrinasketch/v7/c4m41npxGMTnomOHtRU68eIJn8qfWWn5Pos6CA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Londrina Solid` extends GoogleFont {
    override lazy val family: String = "Londrina Solid"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/londrinasolid/v8/flUjRq6sw40kQEJxWNgkLuudGfs9KBYesZHhV64.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/londrinasolid/v8/flUiRq6sw40kQEJxWNgkLuudGfv1CjY0n53oTrcL.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/londrinasolid/v8/flUhRq6sw40kQEJxWNgkLuudGcNZIhI8tIHh.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/londrinasolid/v8/flUiRq6sw40kQEJxWNgkLuudGfvdDzY0n53oTrcL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `900`)
  }
  object `Lora` extends GoogleFont {
    override lazy val family: String = "Lora"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lora/v13/0QIvMX1D_JOuAw3xItNPh_A.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/lora/v13/0QIhMX1D_JOuMw_7JvFKl_C28g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lora/v13/0QIgMX1D_JOuO7HeBttkm_mv670.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/lora/v13/0QIiMX1D_JOuMw_Dmt5un9uq-73O-Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Love Ya Like A Sister` extends GoogleFont {
    override lazy val family: String = "Love Ya Like A Sister"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/loveyalikeasister/v9/R70EjzUBlOqPeouhFDfR80-0FhOqJubN-Be78nZcsGGycA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Loved by the King` extends GoogleFont {
    override lazy val family: String = "Loved by the King"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lovedbytheking/v8/Gw6gwdP76VDVJNXerebZxUMeRXUF2PiNlXFu2R64.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lovers Quarrel` extends GoogleFont {
    override lazy val family: String = "Lovers Quarrel"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/loversquarrel/v6/Yq6N-LSKXTL-5bCy8ksBzpQ_-zAsY7pO6siz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Luckiest Guy` extends GoogleFont {
    override lazy val family: String = "Luckiest Guy"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/luckiestguy/v9/_gP_1RrxsjcxVyin9l9n_j2RStR3qDpraA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Lusitana` extends GoogleFont {
    override lazy val family: String = "Lusitana"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lusitana/v6/CSR84z9ShvucWzsMKxhaRuMiSct_.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/lusitana/v6/CSR74z9ShvucWzsMKyDmaccqYtd2vfwk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Lustria` extends GoogleFont {
    override lazy val family: String = "Lustria"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/lustria/v6/9oRONYodvDEyjuhOrCg5MtPyAcg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `M PLUS 1p` extends GoogleFont {
    override lazy val family: String = "M PLUS 1p"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `japanese`: GoogleFontSubset = new GoogleFontSubset("japanese")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `hebrew`, `japanese`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/mplus1p/v17/e3tleuShHdiFyPFzBRrQnDQAUW3aq-5N.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/mplus1p/v17/e3tmeuShHdiFyPFzBRrQVBYge0PWovdU4w.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mplus1p/v17/e3tjeuShHdiFyPFzBRro-D4Ec2jKqw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mplus1p/v17/e3tmeuShHdiFyPFzBRrQDBcge0PWovdU4w.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mplus1p/v17/e3tmeuShHdiFyPFzBRrQRBEge0PWovdU4w.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/mplus1p/v17/e3tmeuShHdiFyPFzBRrQWBIge0PWovdU4w.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/mplus1p/v17/e3tmeuShHdiFyPFzBRrQfBMge0PWovdU4w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `800`, `900`)
  }
  object `M PLUS Rounded 1c` extends GoogleFont {
    override lazy val family: String = "M PLUS Rounded 1c"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `japanese`: GoogleFontSubset = new GoogleFontSubset("japanese")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `hebrew`, `japanese`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/mplusrounded1c/v9/VdGCAYIAV6gnpUpoWwNkYvrugw9RuM3ixLsg6-av1x0.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/mplusrounded1c/v9/VdGBAYIAV6gnpUpoWwNkYvrugw9RuM0q5psKxeqmzgRK.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mplusrounded1c/v9/VdGEAYIAV6gnpUpoWwNkYvrugw9RuPWGzr8C7vav.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mplusrounded1c/v9/VdGBAYIAV6gnpUpoWwNkYvrugw9RuM1y55sKxeqmzgRK.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mplusrounded1c/v9/VdGBAYIAV6gnpUpoWwNkYvrugw9RuM064ZsKxeqmzgRK.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/mplusrounded1c/v9/VdGBAYIAV6gnpUpoWwNkYvrugw9RuM0m4psKxeqmzgRK.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/mplusrounded1c/v9/VdGBAYIAV6gnpUpoWwNkYvrugw9RuM0C45sKxeqmzgRK.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `800`, `900`)
  }
  object `Macondo` extends GoogleFont {
    override lazy val family: String = "Macondo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/macondo/v7/RrQQboN9-iB1IXmOS2XO0LBBd4Y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Macondo Swash Caps` extends GoogleFont {
    override lazy val family: String = "Macondo Swash Caps"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/macondoswashcaps/v6/6NUL8EaAJgGKZA7lpt941Z9s6ZYgDq6Oekoa_mm5bA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mada` extends GoogleFont {
    override lazy val family: String = "Mada"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/mada/v7/7Au_p_0qnzeSdf3nCCL8zkwMIFg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/mada/v7/7Au_p_0qnzeSdZnkCCL8zkwMIFg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mada/v7/7Auwp_0qnzeSTTXMLCrX0kU.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mada/v7/7Au_p_0qnzeSdcHlCCL8zkwMIFg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/mada/v7/7Au_p_0qnzeSde3iCCL8zkwMIFg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mada/v7/7Au_p_0qnzeSdYnjCCL8zkwMIFg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/mada/v7/7Au_p_0qnzeSdbHhCCL8zkwMIFg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `900`)
  }
  object `Magra` extends GoogleFont {
    override lazy val family: String = "Magra"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/magra/v7/uK_94ruaZus72k5xIDMfO-ed.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/magra/v7/uK_w4ruaZus72nbNDxcXEPuUX1ow.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Maiden Orange` extends GoogleFont {
    override lazy val family: String = "Maiden Orange"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/maidenorange/v9/kJE1BuIX7AUmhi2V4m08kb1XjOZdCZS8FY8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Maitree` extends GoogleFont {
    override lazy val family: String = "Maitree"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/maitree/v3/MjQDmil5tffhpBrklhGNWJGovLdh6OE.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/maitree/v3/MjQDmil5tffhpBrklnWOWJGovLdh6OE.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/maitree/v3/MjQGmil5tffhpBrkrtmmfJmDoL4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/maitree/v3/MjQDmil5tffhpBrkli2PWJGovLdh6OE.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/maitree/v3/MjQDmil5tffhpBrklgGIWJGovLdh6OE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/maitree/v3/MjQDmil5tffhpBrklmWJWJGovLdh6OE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Major Mono Display` extends GoogleFont {
    override lazy val family: String = "Major Mono Display"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/majormonodisplay/v2/RWmVoLyb5fEqtsfBX9PDZIGr2tFubRhLCn2QIndPww.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mako` extends GoogleFont {
    override lazy val family: String = "Mako"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mako/v10/H4coBX6Mmc_Z0ST09g478Lo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mali` extends GoogleFont {
    override lazy val family: String = "Mali"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/mali/v2/N0bV2SRONuN4QOLlKlRaJdbWgdY.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/mali/v2/N0bX2SRONuN4SCj8wlVQIfTTkdbJYA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/mali/v2/N0bV2SRONuN4QIbmKlRaJdbWgdY.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/mali/v2/N0bX2SRONuN4SCj8plZQIfTTkdbJYA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mali/v2/N0ba2SRONuN4eCrODlxxOd8.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/mali/v2/N0bU2SRONuN4SCjECn50Kd_PmA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mali/v2/N0bV2SRONuN4QN7nKlRaJdbWgdY.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/mali/v2/N0bX2SRONuN4SCj8_ldQIfTTkdbJYA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/mali/v2/N0bV2SRONuN4QPLgKlRaJdbWgdY.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/mali/v2/N0bX2SRONuN4SCj80lBQIfTTkdbJYA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mali/v2/N0bV2SRONuN4QJbhKlRaJdbWgdY.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/mali/v2/N0bX2SRONuN4SCj8tlFQIfTTkdbJYA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Mallanna` extends GoogleFont {
    override lazy val family: String = "Mallanna"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mallanna/v6/hv-Vlzx-KEQb84YaDGwzEzRwVvJ-.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mandali` extends GoogleFont {
    override lazy val family: String = "Mandali"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mandali/v7/LhWlMVbYOfASNfNUVFk1ZPdcKtA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Manuale` extends GoogleFont {
    override lazy val family: String = "Manuale"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/manuale/v3/f0X20eas_8Z-TFZdBPbEw8nG6aY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/manuale/v3/f0X00eas_8Z-TFZdNPTOx-vD-aYfeA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/manuale/v3/f0Xz0eas_8Z-TFZdPALt58Ht9a8GYeA.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/manuale/v3/f0Xx0eas_8Z-TFZdNPT2M8Ln8Y0DceA0OQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/manuale/v3/f0Xz0eas_8Z-TFZdPC7q58Ht9a8GYeA.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/manuale/v3/f0Xx0eas_8Z-TFZdNPT2H8Xn8Y0DceA0OQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/manuale/v3/f0Xz0eas_8Z-TFZdPErr58Ht9a8GYeA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/manuale/v3/f0Xx0eas_8Z-TFZdNPT2e8Tn8Y0DceA0OQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Marcellus` extends GoogleFont {
    override lazy val family: String = "Marcellus"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/marcellus/v6/wEO_EBrOk8hQLDvIAF8FUfAL3EsHiA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Marcellus SC` extends GoogleFont {
    override lazy val family: String = "Marcellus SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/marcellussc/v6/ke8iOgUHP1dg-Rmi6RWjbLEPgdydGKikhA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Marck Script` extends GoogleFont {
    override lazy val family: String = "Marck Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/marckscript/v9/nwpTtK2oNgBA3Or78gapdwuCzyI-aMPF7Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Margarine` extends GoogleFont {
    override lazy val family: String = "Margarine"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/margarine/v7/qkBXXvoE6trLT9Y7YLye5JRLkAXbMQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Markazi Text` extends GoogleFont {
    override lazy val family: String = "Markazi Text"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/markazitext/v5/sykh-ydym6AtQaiEtX7yhqb_rV1k_81ZVYYZtfSQT4MlBekmJLo.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/markazitext/v5/sykh-ydym6AtQaiEtX7yhqb_rV1k_81ZVYYZtcaQT4MlBekmJLo.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/markazitext/v5/sykh-ydym6AtQaiEtX7yhqb_rV1k_81ZVYYZtSqXT4MlBekmJLo.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/markazitext/v5/sykh-ydym6AtQaiEtX7yhqb_rV1k_81ZVYYZtROXT4MlBekmJLo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`)
  }
  object `Marko One` extends GoogleFont {
    override lazy val family: String = "Marko One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/markoone/v8/9Btq3DFG0cnVM5lw1haaKpUfrHPzUw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Marmelad` extends GoogleFont {
    override lazy val family: String = "Marmelad"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/marmelad/v8/Qw3eZQdSHj_jK2e-8tFLG-YMC0R8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Martel` extends GoogleFont {
    override lazy val family: String = "Martel"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/martel/v3/PN_yRfK9oXHga0XVqekahRbX9vnDzw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/martel/v3/PN_yRfK9oXHga0XVzeoahRbX9vnDzw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/martel/v3/PN_xRfK9oXHga0XtYcI-jT3L_w.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/martel/v3/PN_yRfK9oXHga0XVuewahRbX9vnDzw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/martel/v3/PN_yRfK9oXHga0XV3e0ahRbX9vnDzw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/martel/v3/PN_yRfK9oXHga0XVwe4ahRbX9vnDzw.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/martel/v3/PN_yRfK9oXHga0XV5e8ahRbX9vnDzw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `600`, `700`, `800`, `900`)
  }
  object `Martel Sans` extends GoogleFont {
    override lazy val family: String = "Martel Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/martelsans/v5/h0GxssGi7VdzDgKjM-4d8hAX5suHFUknqMxQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/martelsans/v5/h0GxssGi7VdzDgKjM-4d8hBz5cuHFUknqMxQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/martelsans/v5/h0GsssGi7VdzDgKjM-4d8ijfze-PPlUu.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/martelsans/v5/h0GxssGi7VdzDgKjM-4d8hAH48uHFUknqMxQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/martelsans/v5/h0GxssGi7VdzDgKjM-4d8hBj4suHFUknqMxQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/martelsans/v5/h0GxssGi7VdzDgKjM-4d8hB_4cuHFUknqMxQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/martelsans/v5/h0GxssGi7VdzDgKjM-4d8hBb4MuHFUknqMxQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `600`, `700`, `800`, `900`)
  }
  object `Marvel` extends GoogleFont {
    override lazy val family: String = "Marvel"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/marvel/v8/nwpVtKeoNgBV0qaIkV7ED366zg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/marvel/v8/nwpXtKeoNgBV0qa4k1TALXuqzhA7.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/marvel/v8/nwpWtKeoNgBV0qawLXHgB1WmxwkiYQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/marvel/v8/nwpQtKeoNgBV0qa4k2x8Al-i5QwyYdrc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Mate` extends GoogleFont {
    override lazy val family: String = "Mate"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mate/v7/m8JdjftRd7WZ2z28WoXSaLU.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/mate/v7/m8JTjftRd7WZ6z-2XqfXeLVdbw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Mate SC` extends GoogleFont {
    override lazy val family: String = "Mate SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/matesc/v7/-nF8OGQ1-uoVr2wKyiXZ95OkJwA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Maven Pro` extends GoogleFont {
    override lazy val family: String = "Maven Pro"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mavenpro/v12/7Au9p_AqnyWWAxW2Wk32ym4JMFge0g.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mavenpro/v12/7Au4p_AqnyWWAxW2Wk3OPkctOHMC21go8A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mavenpro/v12/7Au4p_AqnyWWAxW2Wk3OdkEtOHMC21go8A.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/mavenpro/v12/7Au4p_AqnyWWAxW2Wk3OTkMtOHMC21go8A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `700`, `900`)
  }
  object `McLaren` extends GoogleFont {
    override lazy val family: String = "McLaren"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mclaren/v6/2EbnL-ZuAXFqZFXISYYf8z2Yt_c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Meddon` extends GoogleFont {
    override lazy val family: String = "Meddon"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/meddon/v11/kmK8ZqA2EgDNeHTZhBdB3y_Aow.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `MedievalSharp` extends GoogleFont {
    override lazy val family: String = "MedievalSharp"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/medievalsharp/v11/EvOJzAlL3oU5AQl2mP5KdgptAq96MwvXLDk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Medula One` extends GoogleFont {
    override lazy val family: String = "Medula One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/medulaone/v8/YA9Wr0qb5kjJM6l2V0yukiEqs7GtlvY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Meera Inimai` extends GoogleFont {
    override lazy val family: String = "Meera Inimai"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `tamil`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/meerainimai/v3/845fNMM5EIqOW5MPuvO3ILep_2jDVevnLQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Megrim` extends GoogleFont {
    override lazy val family: String = "Megrim"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/megrim/v9/46kulbz5WjvLqJZlbWXgd0RY1g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Meie Script` extends GoogleFont {
    override lazy val family: String = "Meie Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/meiescript/v6/_LOImzDK7erRjhunIspaMjxn5IXg0WDz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Merienda` extends GoogleFont {
    override lazy val family: String = "Merienda"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/merienda/v7/gNMHW3x8Qoy5_mf8uVMCOou6_dvg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/merienda/v7/gNMAW3x8Qoy5_mf8uWu-Fa-y1sfpPES4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Merienda One` extends GoogleFont {
    override lazy val family: String = "Merienda One"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/meriendaone/v9/H4cgBXaMndbflEq6kyZ1ht6YgoyyYzFzFw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Merriweather` extends GoogleFont {
    override lazy val family: String = "Merriweather"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/merriweather/v20/u-4n0qyriQwlOrhSvowK_l521wRpX837pvjxPA.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/merriweather/v20/u-4l0qyriQwlOrhSvowK_l5-eR7lXcf_hP3hPGWH.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/merriweather/v20/u-440qyriQwlOrhSvowK_l5OeyxNV-bnrw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/merriweather/v20/u-4m0qyriQwlOrhSvowK_l5-eSZJdeP3r-Ho.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/merriweather/v20/u-4n0qyriQwlOrhSvowK_l52xwNpX837pvjxPA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/merriweather/v20/u-4l0qyriQwlOrhSvowK_l5-eR71Wsf_hP3hPGWH.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/merriweather/v20/u-4n0qyriQwlOrhSvowK_l52_wFpX837pvjxPA.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/merriweather/v20/u-4l0qyriQwlOrhSvowK_l5-eR7NWMf_hP3hPGWH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Merriweather Sans` extends GoogleFont {
    override lazy val family: String = "Merriweather Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/merriweathersans/v10/2-c49IRs1JiJN1FRAMjTN5zd9vgsFH1eYBDD2BdWzIqY.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/merriweathersans/v10/2-c29IRs1JiJN1FRAMjTN5zd9vgsFHXwepzB0hN0yZqYcqw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/merriweathersans/v10/2-c99IRs1JiJN1FRAMjTN5zd9vgsFEXySDTL8wtf.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/merriweathersans/v10/2-c79IRs1JiJN1FRAMjTN5zd9vgsFHXwQjDp9htf1ZM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/merriweathersans/v10/2-c49IRs1JiJN1FRAMjTN5zd9vgsFH1OZxDD2BdWzIqY.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/merriweathersans/v10/2-c29IRs1JiJN1FRAMjTN5zd9vgsFHXweozG0hN0yZqYcqw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/merriweathersans/v10/2-c49IRs1JiJN1FRAMjTN5zd9vgsFH1SZBDD2BdWzIqY.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/merriweathersans/v10/2-c29IRs1JiJN1FRAMjTN5zd9vgsFHXwepDF0hN0yZqYcqw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Metal` extends GoogleFont {
    override lazy val family: String = "Metal"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/metal/v11/lW-wwjUJIXTo7i3nnoQAUdN2.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Metal Mania` extends GoogleFont {
    override lazy val family: String = "Metal Mania"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/metalmania/v8/RWmMoKWb4e8kqMfBUdPFJeXCg6UKDXlq.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Metamorphous` extends GoogleFont {
    override lazy val family: String = "Metamorphous"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/metamorphous/v9/Wnz8HA03aAXcC39ZEX5y1330PCCthTsmaQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Metrophobic` extends GoogleFont {
    override lazy val family: String = "Metrophobic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/metrophobic/v12/sJoA3LZUhMSAPV_u0qwiAT-J737FPEEL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Michroma` extends GoogleFont {
    override lazy val family: String = "Michroma"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/michroma/v9/PN_zRfy9qWD8fEagAMg6rzjb_-Da.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Milonga` extends GoogleFont {
    override lazy val family: String = "Milonga"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/milonga/v6/SZc53FHnIaK9W5kffz3GkUrS8DI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Miltonian` extends GoogleFont {
    override lazy val family: String = "Miltonian"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/miltonian/v12/zOL-4pbPn6Ne9JqTg9mr6e5As-FeiQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Miltonian Tattoo` extends GoogleFont {
    override lazy val family: String = "Miltonian Tattoo"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/miltoniantattoo/v14/EvOUzBRL0o0kCxF-lcMCQxlpVsA_FwP8MDBku-s.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mina` extends GoogleFont {
    override lazy val family: String = "Mina"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `bengali`: GoogleFontSubset = new GoogleFontSubset("bengali")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`bengali`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mina/v2/-nFzOGc18vARrz9j7i3y65o.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mina/v2/-nF8OGc18vARl4NMyiXZ95OkJwA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Miniver` extends GoogleFont {
    override lazy val family: String = "Miniver"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/miniver/v7/eLGcP-PxIg-5H0vC770Cy8r8fWA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Miriam Libre` extends GoogleFont {
    override lazy val family: String = "Miriam Libre"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/miriamlibre/v5/DdTh798HsHwubBAqfkcBTL_vYJn_Teun9g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/miriamlibre/v5/DdT-798HsHwubBAqfkcBTL_X3LbbRcC7_-Z7Hg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Mirza` extends GoogleFont {
    override lazy val family: String = "Mirza"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mirza/v6/co3ImWlikiN5EurdKMewsrvI.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mirza/v6/co3FmWlikiN5EtIpAeO4mafBomDi.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/mirza/v6/co3FmWlikiN5EtIFBuO4mafBomDi.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mirza/v6/co3FmWlikiN5EtJhB-O4mafBomDi.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`)
  }
  object `Miss Fajardose` extends GoogleFont {
    override lazy val family: String = "Miss Fajardose"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/missfajardose/v8/E21-_dn5gvrawDdPFVl-N0Ajb8qvWPaJq4no.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mitr` extends GoogleFont {
    override lazy val family: String = "Mitr"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/mitr/v4/pxiEypw5ucZF8fMZFJDUc1NECPY.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/mitr/v4/pxiEypw5ucZF8ZcaFJDUc1NECPY.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mitr/v4/pxiLypw5ucZFyTsyMJj_b1o.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mitr/v4/pxiEypw5ucZF8c8bFJDUc1NECPY.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/mitr/v4/pxiEypw5ucZF8eMcFJDUc1NECPY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mitr/v4/pxiEypw5ucZF8YcdFJDUc1NECPY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Modak` extends GoogleFont {
    override lazy val family: String = "Modak"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/modak/v4/EJRYQgs1XtIEsnMH8BVZ76KU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Modern Antiqua` extends GoogleFont {
    override lazy val family: String = "Modern Antiqua"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/modernantiqua/v8/NGStv5TIAUg6Iq_RLNo_2dp1sI1Ea2u0c3Gi.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mogra` extends GoogleFont {
    override lazy val family: String = "Mogra"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `gujarati`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mogra/v5/f0X40eSs8c95TBo4DvLmxtnG.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Molengo` extends GoogleFont {
    override lazy val family: String = "Molengo"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/molengo/v9/I_uuMpWeuBzZNBtQbbRQkiCvs5Y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Molle` extends GoogleFont {
    override lazy val family: String = "Molle"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/molle/v7/E21n_dL5hOXFhWEsXzgmVydREus.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`italic`)
  }
  object `Monda` extends GoogleFont {
    override lazy val family: String = "Monda"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/monda/v8/TK3tWkYFABsmjvpmNBsLvPdG.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/monda/v8/TK3gWkYFABsmjsLaGz8Dl-tPKo2t.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Monofett` extends GoogleFont {
    override lazy val family: String = "Monofett"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/monofett/v8/mFTyWbofw6zc9NtnW43SuRwr0VJ7.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Monoton` extends GoogleFont {
    override lazy val family: String = "Monoton"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/monoton/v8/5h1aiZUrOngCibe4fkbBQ2S7FU8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Monsieur La Doulaise` extends GoogleFont {
    override lazy val family: String = "Monsieur La Doulaise"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/monsieurladoulaise/v7/_Xmz-GY4rjmCbQfc-aPRaa4pqV340p7EZl5ewkEU4HTy.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Montaga` extends GoogleFont {
    override lazy val family: String = "Montaga"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/montaga/v6/H4cnBX2Ml8rCkEO_0gYQ7LO5mqc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Montez` extends GoogleFont {
    override lazy val family: String = "Montez"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/montez/v9/845ZNMk5GoGIX8lm1LDeSd-R_g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Montserrat` extends GoogleFont {
    override lazy val family: String = "Montserrat"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/montserrat/v13/JTUQjIg1_i6t8kCHKm45_QphziTn89dtpQ.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/montserrat/v13/JTUOjIg1_i6t8kCHKm459WxZqi7j0dJ9pTOi.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/montserrat/v13/JTURjIg1_i6t8kCHKm45_aZA7g7J_950vCo.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/montserrat/v13/JTUPjIg1_i6t8kCHKm459WxZBg_D-_xxrCq7qg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/montserrat/v13/JTURjIg1_i6t8kCHKm45_cJD7g7J_950vCo.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/montserrat/v13/JTUPjIg1_i6t8kCHKm459WxZYgzD-_xxrCq7qg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/montserrat/v13/JTUSjIg1_i6t8kCHKm45xW5rygbi49c.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/montserrat/v13/JTUQjIg1_i6t8kCHKm459WxhziTn89dtpQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/montserrat/v13/JTURjIg1_i6t8kCHKm45_ZpC7g7J_950vCo.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/montserrat/v13/JTUPjIg1_i6t8kCHKm459WxZOg3D-_xxrCq7qg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/montserrat/v13/JTURjIg1_i6t8kCHKm45_bZF7g7J_950vCo.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/montserrat/v13/JTUPjIg1_i6t8kCHKm459WxZFgrD-_xxrCq7qg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/montserrat/v13/JTURjIg1_i6t8kCHKm45_dJE7g7J_950vCo.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/montserrat/v13/JTUPjIg1_i6t8kCHKm459WxZcgvD-_xxrCq7qg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/montserrat/v13/JTURjIg1_i6t8kCHKm45_c5H7g7J_950vCo.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/montserrat/v13/JTUPjIg1_i6t8kCHKm459WxZbgjD-_xxrCq7qg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/montserrat/v13/JTURjIg1_i6t8kCHKm45_epG7g7J_950vCo.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/montserrat/v13/JTUPjIg1_i6t8kCHKm459WxZSgnD-_xxrCq7qg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Montserrat Alternates` extends GoogleFont {
    override lazy val family: String = "Montserrat Alternates"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/montserratalternates/v10/mFThWacfw6zH4dthXcyms1lPpC8I_b0juU0xiKfVKphL03l4.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/montserratalternates/v10/mFTjWacfw6zH4dthXcyms1lPpC8I_b0juU057p-xIJxp1ml4imo.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/montserratalternates/v10/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xJIb1ALZH2mBhkw.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/montserratalternates/v10/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p8dAbxD-GVxk3Nd.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/montserratalternates/v10/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xQIX1ALZH2mBhkw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/montserratalternates/v10/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p95ArxD-GVxk3Nd.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/montserratalternates/v10/mFTvWacfw6zH4dthXcyms1lPpC8I_b0juU0J7K3RCJ1b0w.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/montserratalternates/v10/mFThWacfw6zH4dthXcyms1lPpC8I_b0juU057qfVKphL03l4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/montserratalternates/v10/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xGIT1ALZH2mBhkw.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/montserratalternates/v10/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p8hA7xD-GVxk3Nd.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/montserratalternates/v10/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xNIP1ALZH2mBhkw.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/montserratalternates/v10/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p8NBLxD-GVxk3Nd.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/montserratalternates/v10/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xUIL1ALZH2mBhkw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/montserratalternates/v10/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p9pBbxD-GVxk3Nd.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/montserratalternates/v10/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xTIH1ALZH2mBhkw.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/montserratalternates/v10/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p91BrxD-GVxk3Nd.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/montserratalternates/v10/mFTiWacfw6zH4dthXcyms1lPpC8I_b0juU0xaID1ALZH2mBhkw.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/montserratalternates/v10/mFTkWacfw6zH4dthXcyms1lPpC8I_b0juU057p9RB7xD-GVxk3Nd.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Montserrat Subrayada` extends GoogleFont {
    override lazy val family: String = "Montserrat Subrayada"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/montserratsubrayada/v8/U9MD6c-o9H7PgjlTHThBnNHGVUORwteQQE8LYuceqGT-.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/montserratsubrayada/v8/U9MM6c-o9H7PgjlTHThBnNHGVUORwteQQHe3TcMWg3j36Ebz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Moul` extends GoogleFont {
    override lazy val family: String = "Moul"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/moul/v10/nuF2D__FSo_3E-RYiJCy-00.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Moulpali` extends GoogleFont {
    override lazy val family: String = "Moulpali"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/moulpali/v11/H4ckBXKMl9HagUWymyY6wr-wg763.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mountains of Christmas` extends GoogleFont {
    override lazy val family: String = "Mountains of Christmas"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mountainsofchristmas/v11/3y9w6a4zcCnn5X0FDyrKi2ZRUBIy8uxoUo7ePNamMPNpJpc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mountainsofchristmas/v11/3y9z6a4zcCnn5X0FDyrKi2ZRUBIy8uxoUo7eBGqJFPtCOp6IaEA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Mouse Memoirs` extends GoogleFont {
    override lazy val family: String = "Mouse Memoirs"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mousememoirs/v6/t5tmIRoSNJ-PH0WNNgDYxdSb7TnFrpOHYh4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mr Bedfort` extends GoogleFont {
    override lazy val family: String = "Mr Bedfort"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mrbedfort/v7/MQpR-WCtNZSWAdTMwBicliq0XZe_Iy8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mr Dafoe` extends GoogleFont {
    override lazy val family: String = "Mr Dafoe"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mrdafoe/v7/lJwE-pIzkS5NXuMMrGiqg7MCxz_C.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mr De Haviland` extends GoogleFont {
    override lazy val family: String = "Mr De Haviland"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mrdehaviland/v7/OpNVnooIhJj96FdB73296ksbOj3C4ULVNTlB.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mrs Saint Delafield` extends GoogleFont {
    override lazy val family: String = "Mrs Saint Delafield"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mrssaintdelafield/v6/v6-IGZDIOVXH9xtmTZfRagunqBw5WC62cK4tLsubB2w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mrs Sheppards` extends GoogleFont {
    override lazy val family: String = "Mrs Sheppards"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mrssheppards/v7/PN_2Rfm9snC0XUGoEZhb91ig3vjxynMix4Y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Mukta` extends GoogleFont {
    override lazy val family: String = "Mukta"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/mukta/v6/iJWHBXyXfDDVXbEOjFma-2HW7ZB_.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/mukta/v6/iJWHBXyXfDDVXbFqj1ma-2HW7ZB_.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mukta/v6/iJWKBXyXfDDVXYnGp32S0H3f.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/mukta/v6/iJWHBXyXfDDVXbEyjlma-2HW7ZB_.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/mukta/v6/iJWHBXyXfDDVXbEeiVma-2HW7ZB_.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/mukta/v6/iJWHBXyXfDDVXbF6iFma-2HW7ZB_.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/mukta/v6/iJWHBXyXfDDVXbFmi1ma-2HW7ZB_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Mukta Mahee` extends GoogleFont {
    override lazy val family: String = "Mukta Mahee"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `gurmukhi`: GoogleFontSubset = new GoogleFontSubset("gurmukhi")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`gurmukhi`, `latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/muktamahee/v4/XRXN3IOIi0hcP8iVU67hA9MFcBoHJndqZCsW.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/muktamahee/v4/XRXN3IOIi0hcP8iVU67hA9NhcxoHJndqZCsW.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/muktamahee/v4/XRXQ3IOIi0hcP8iVU67hA-vNWz4PDWtj.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/muktamahee/v4/XRXN3IOIi0hcP8iVU67hA9M5choHJndqZCsW.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/muktamahee/v4/XRXN3IOIi0hcP8iVU67hA9MVdRoHJndqZCsW.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/muktamahee/v4/XRXN3IOIi0hcP8iVU67hA9NxdBoHJndqZCsW.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/muktamahee/v4/XRXN3IOIi0hcP8iVU67hA9NtdxoHJndqZCsW.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Mukta Malar` extends GoogleFont {
    override lazy val family: String = "Mukta Malar"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `tamil`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/muktamalar/v5/MCoKzAXyz8LOE2FpJMxZqIMwBtAB62ruoAZW.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/muktamalar/v5/MCoKzAXyz8LOE2FpJMxZqINUBdAB62ruoAZW.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/muktamalar/v5/MCoXzAXyz8LOE2FpJMxZqLv4LfQJwHbn.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/muktamalar/v5/MCoKzAXyz8LOE2FpJMxZqIMMBNAB62ruoAZW.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/muktamalar/v5/MCoKzAXyz8LOE2FpJMxZqIMgA9AB62ruoAZW.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/muktamalar/v5/MCoKzAXyz8LOE2FpJMxZqINEAtAB62ruoAZW.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/muktamalar/v5/MCoKzAXyz8LOE2FpJMxZqINYAdAB62ruoAZW.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Mukta Vaani` extends GoogleFont {
    override lazy val family: String = "Mukta Vaani"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `gujarati`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/muktavaani/v6/3JnkSD_-ynaxmxnEfVHPIGXNV8BD-u97MW1a.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/muktavaani/v6/3JnkSD_-ynaxmxnEfVHPIGWpVMBD-u97MW1a.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/muktavaani/v6/3Jn5SD_-ynaxmxnEfVHPIF0FfORL0fNy.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/muktavaani/v6/3JnkSD_-ynaxmxnEfVHPIGXxVcBD-u97MW1a.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/muktavaani/v6/3JnkSD_-ynaxmxnEfVHPIGXdUsBD-u97MW1a.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/muktavaani/v6/3JnkSD_-ynaxmxnEfVHPIGW5U8BD-u97MW1a.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/muktavaani/v6/3JnkSD_-ynaxmxnEfVHPIGWlUMBD-u97MW1a.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `800`)
  }
  object `Muli` extends GoogleFont {
    override lazy val family: String = "Muli"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/muli/v13/7Au_p_0qiz-adf3nCCL8zkwMIFg.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/muli/v13/7Au9p_0qiz-afTf-4CP2ym4JMFge0g.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/muli/v13/7Au_p_0qiz-adZnkCCL8zkwMIFg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/muli/v13/7Au9p_0qiz-afTf-hCD2ym4JMFge0g.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/muli/v13/7Auwp_0qiz-aTTXMLCrX0kU.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/muli/v13/7Au-p_0qiz-afTfGKAjSwkUVOQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/muli/v13/7Au_p_0qiz-ade3iCCL8zkwMIFg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/muli/v13/7Au9p_0qiz-afTf-8Cb2ym4JMFge0g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/muli/v13/7Au_p_0qiz-adYnjCCL8zkwMIFg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/muli/v13/7Au9p_0qiz-afTf-lCf2ym4JMFge0g.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/muli/v13/7Au_p_0qiz-adZXgCCL8zkwMIFg.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/muli/v13/7Au9p_0qiz-afTf-iCT2ym4JMFge0g.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/muli/v13/7Au_p_0qiz-adbHhCCL8zkwMIFg.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/muli/v13/7Au9p_0qiz-afTf-rCX2ym4JMFge0g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Mystery Quest` extends GoogleFont {
    override lazy val family: String = "Mystery Quest"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/mysteryquest/v6/-nF6OG414u0E6k0wynSGlujRHwElD_9Qz9E.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `NTR` extends GoogleFont {
    override lazy val family: String = "NTR"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ntr/v6/RLpzK5Xy0ZjiGGhs5TA4bg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nanum Brush Script` extends GoogleFont {
    override lazy val family: String = "Nanum Brush Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nanumbrushscript/v16/wXK2E2wfpokopxzthSqPbcR5_gVaxazyjqBr1lO97Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nanum Gothic` extends GoogleFont {
    override lazy val family: String = "Nanum Gothic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nanumgothic/v16/PN_3Rfi-oW3hYwmKDpxS7F_z_tLfxno73g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nanumgothic/v16/PN_oRfi-oW3hYwmKDpxS7F_LQv37zlEn14YEUQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/nanumgothic/v16/PN_oRfi-oW3hYwmKDpxS7F_LXv77zlEn14YEUQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `800`)
  }
  object `Nanum Gothic Coding` extends GoogleFont {
    override lazy val family: String = "Nanum Gothic Coding"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nanumgothiccoding/v13/8QIVdjzHisX_8vv59_xMxtPFW4IXROwsy6QxVs1X7tc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nanumgothiccoding/v13/8QIYdjzHisX_8vv59_xMxtPFW4IXROws8xgecsV88t5V9r4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Nanum Myeongjo` extends GoogleFont {
    override lazy val family: String = "Nanum Myeongjo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nanummyeongjo/v14/9Btx3DZF0dXLMZlywRbVRNhxy1LreHQ8juyl.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nanummyeongjo/v14/9Bty3DZF0dXLMZlywRbVRNhxy2pXV1A0pfCs5Kos.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/nanummyeongjo/v14/9Bty3DZF0dXLMZlywRbVRNhxy2pLVFA0pfCs5Kos.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `800`)
  }
  object `Nanum Pen Script` extends GoogleFont {
    override lazy val family: String = "Nanum Pen Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nanumpenscript/v14/daaDSSYiLGqEal3MvdA_FOL_3FkN2z7-aMFCcTU.ttf"))

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
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/neucha/v10/q5uGsou0JOdh94bvugNsCxVEgA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Neuton` extends GoogleFont {
    override lazy val family: String = "Neuton"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/neuton/v11/UMBQrPtMoH62xUZKAKkfegD5Drog6Q.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/neuton/v11/UMBQrPtMoH62xUZKZKofegD5Drog6Q.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/neuton/v11/UMBTrPtMoH62xUZyyII7civlBw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/neuton/v11/UMBRrPtMoH62xUZCyog_UC71B6M5.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/neuton/v11/UMBQrPtMoH62xUZKdK0fegD5Drog6Q.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/neuton/v11/UMBQrPtMoH62xUZKaK4fegD5Drog6Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `italic`, `700`, `800`)
  }
  object `New Rocker` extends GoogleFont {
    override lazy val family: String = "New Rocker"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/newrocker/v7/MwQzbhjp3-HImzcCU_cJkGMViblPtXs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `News Cycle` extends GoogleFont {
    override lazy val family: String = "News Cycle"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/newscycle/v15/CSR64z1Qlv-GDxkbKVQ_TOcATNt_pOU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/newscycle/v15/CSR54z1Qlv-GDxkbKVQ_dFsvaNNUuOwkC2s.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Niconne` extends GoogleFont {
    override lazy val family: String = "Niconne"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/niconne/v8/w8gaH2QvRug1_rTfrQut2F4OuOo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Niramit` extends GoogleFont {
    override lazy val family: String = "Niramit"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/niramit/v3/I_urMpWdvgLdNxVLVXx7tiiEr5_BdZ8.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/niramit/v3/I_upMpWdvgLdNxVLXbZiXimOq73EZZ_f6w.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/niramit/v3/I_urMpWdvgLdNxVLVRh4tiiEr5_BdZ8.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/niramit/v3/I_upMpWdvgLdNxVLXbZiOiqOq73EZZ_f6w.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/niramit/v3/I_uuMpWdvgLdNxVLbbRQkiCvs5Y.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/niramit/v3/I_usMpWdvgLdNxVLXbZalgKqo5bYbA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/niramit/v3/I_urMpWdvgLdNxVLVUB5tiiEr5_BdZ8.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/niramit/v3/I_upMpWdvgLdNxVLXbZiYiuOq73EZZ_f6w.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/niramit/v3/I_urMpWdvgLdNxVLVWx-tiiEr5_BdZ8.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/niramit/v3/I_upMpWdvgLdNxVLXbZiTiyOq73EZZ_f6w.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/niramit/v3/I_urMpWdvgLdNxVLVQh_tiiEr5_BdZ8.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/niramit/v3/I_upMpWdvgLdNxVLXbZiKi2Oq73EZZ_f6w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Nixie One` extends GoogleFont {
    override lazy val family: String = "Nixie One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nixieone/v9/lW-8wjkKLXjg5y2o2uUoUOFzpS-yLw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nobile` extends GoogleFont {
    override lazy val family: String = "Nobile"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nobile/v10/m8JTjflSeaOVl1i2XqfXeLVdbw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/nobile/v10/m8JRjflSeaOVl1iGXK3TWrBNb3OD.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/nobile/v10/m8JQjflSeaOVl1iOqo7zcJ5BZmqa3A.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/nobile/v10/m8JWjflSeaOVl1iGXJUnc5RFRG-K3Mud.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nobile/v10/m8JQjflSeaOVl1iO4ojzcJ5BZmqa3A.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/nobile/v10/m8JWjflSeaOVl1iGXJVvdZRFRG-K3Mud.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `700`, `700italic`)
  }
  object `Nokora` extends GoogleFont {
    override lazy val family: String = "Nokora"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nokora/v12/hYkIPuwgTubzaWxQOzoPovZg8Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nokora/v12/hYkLPuwgTubzaWxohxUrqt18-B9Uuw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Norican` extends GoogleFont {
    override lazy val family: String = "Norican"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/norican/v7/MwQ2bhXp1eSBqjkPGJJRtGs-lbA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nosifer` extends GoogleFont {
    override lazy val family: String = "Nosifer"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nosifer/v7/ZGjXol5JTp0g5bxZaC1RVDNdGDs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Notable` extends GoogleFont {
    override lazy val family: String = "Notable"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notable/v3/gNMEW3N_SIqx-WX9-HMoFIez5MI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nothing You Could Do` extends GoogleFont {
    override lazy val family: String = "Nothing You Could Do"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nothingyoucoulddo/v8/oY1B8fbBpaP5OX3DtrRYf_Q2BPB1SnfZb0OJl1ol2Ymo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Noticia Text` extends GoogleFont {
    override lazy val family: String = "Noticia Text"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/noticiatext/v8/VuJ2dNDF2Yv9qppOePKYRP1GYTFZt0rNpQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/noticiatext/v8/VuJodNDF2Yv9qppOePKYRP12YztdlU_dpSjt.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/noticiatext/v8/VuJpdNDF2Yv9qppOePKYRP1-3R59v2HRrDH0eA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/noticiatext/v8/VuJrdNDF2Yv9qppOePKYRP12YwPhumvVjjTkeMnz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Noto Sans` extends GoogleFont {
    override lazy val family: String = "Noto Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `devanagari`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notosans/v8/o-0IIpQlx3QUlC5A4PNb4j5Ba_2c7A.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/notosans/v8/o-0OIpQlx3QUlC5A4PNr4DRFSfiM7HBj.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notosans/v8/o-0NIpQlx3QUlC5A4PNjXhFlY9aA5Wl6PQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/notosans/v8/o-0TIpQlx3QUlC5A4PNr4Az5ZtyEx2xqPaif.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Noto Sans HK` extends GoogleFont {
    override lazy val family: String = "Noto Sans HK"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `chinese-hongkong`: GoogleFontSubset = new GoogleFontSubset("chinese-hongkong")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`, `chinese-hongkong`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/notosanshk/v4/nKKO-GM_FYFRJvXzVXaAPe9ZUHp1MOv2ObB7.otf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/notosanshk/v4/nKKP-GM_FYFRJvXzVXaAPe9ZmFhTHMX6MKliqQ.otf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notosanshk/v4/nKKQ-GM_FYFRJvXzVXaAPe9hMnB3Eu7mOQ.otf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/notosanshk/v4/nKKP-GM_FYFRJvXzVXaAPe9ZwFlTHMX6MKliqQ.otf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notosanshk/v4/nKKP-GM_FYFRJvXzVXaAPe9ZiF9THMX6MKliqQ.otf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/notosanshk/v4/nKKP-GM_FYFRJvXzVXaAPe9ZsF1THMX6MKliqQ.otf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `900`)
  }
  object `Noto Sans JP` extends GoogleFont {
    override lazy val family: String = "Noto Sans JP"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `japanese`: GoogleFontSubset = new GoogleFontSubset("japanese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`japanese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/notosansjp/v23/-F6ofjtqLzI2JPCgQBnw7HFQoggM-FNthvIU.otf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/notosansjp/v23/-F6pfjtqLzI2JPCgQBnw7HFQaioq1H1hj-sNFQ.otf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notosansjp/v23/-F62fjtqLzI2JPCgQBnw7HFowAIO2lZ9hg.otf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/notosansjp/v23/-F6pfjtqLzI2JPCgQBnw7HFQMisq1H1hj-sNFQ.otf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notosansjp/v23/-F6pfjtqLzI2JPCgQBnw7HFQei0q1H1hj-sNFQ.otf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/notosansjp/v23/-F6pfjtqLzI2JPCgQBnw7HFQQi8q1H1hj-sNFQ.otf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `900`)
  }
  object `Noto Sans KR` extends GoogleFont {
    override lazy val family: String = "Noto Sans KR"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/notosanskr/v11/Pby6FmXiEBPT4ITbgNA5CgmOsn7uwpYcuH8y.otf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/notosanskr/v11/Pby7FmXiEBPT4ITbgNA5CgmOelzI7rgQsWYrzw.otf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notosanskr/v11/PbykFmXiEBPT4ITbgNA5Cgm20HTs4JMMuA.otf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/notosanskr/v11/Pby7FmXiEBPT4ITbgNA5CgmOIl3I7rgQsWYrzw.otf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notosanskr/v11/Pby7FmXiEBPT4ITbgNA5CgmOalvI7rgQsWYrzw.otf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/notosanskr/v11/Pby7FmXiEBPT4ITbgNA5CgmOUlnI7rgQsWYrzw.otf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `900`)
  }
  object `Noto Sans SC` extends GoogleFont {
    override lazy val family: String = "Noto Sans SC"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `chinese-simplified`: GoogleFontSubset = new GoogleFontSubset("chinese-simplified")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`chinese-simplified`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/notosanssc/v8/k3kJo84MPvpLmixcA63oeALZTYKL2wv287Sb.otf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/notosanssc/v8/k3kIo84MPvpLmixcA63oeALZhaCt9yX6-q2CGg.otf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notosanssc/v8/k3kXo84MPvpLmixcA63oeALhL4iJ-Q7m8w.otf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/notosanssc/v8/k3kIo84MPvpLmixcA63oeALZ3aGt9yX6-q2CGg.otf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notosanssc/v8/k3kIo84MPvpLmixcA63oeALZlaet9yX6-q2CGg.otf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/notosanssc/v8/k3kIo84MPvpLmixcA63oeALZraWt9yX6-q2CGg.otf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `900`)
  }
  object `Noto Sans TC` extends GoogleFont {
    override lazy val family: String = "Noto Sans TC"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `chinese-traditional`: GoogleFontSubset = new GoogleFontSubset("chinese-traditional")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`chinese-traditional`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/notosanstc/v8/-nFlOG829Oofr2wohFbTp9i9WyEJIfNZ1sjy.otf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/notosanstc/v8/-nFkOG829Oofr2wohFbTp9i9kwMvDd1V39Hr7g.otf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notosanstc/v8/-nF7OG829Oofr2wohFbTp9iFOSsLA_ZJ1g.otf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/notosanstc/v8/-nFkOG829Oofr2wohFbTp9i9ywIvDd1V39Hr7g.otf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notosanstc/v8/-nFkOG829Oofr2wohFbTp9i9gwQvDd1V39Hr7g.otf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/notosanstc/v8/-nFkOG829Oofr2wohFbTp9i9uwYvDd1V39Hr7g.otf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `900`)
  }
  object `Noto Serif` extends GoogleFont {
    override lazy val family: String = "Noto Serif"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notoserif/v7/ga6Iaw1J5X9T9RW6j9bNTFAcaRi_bMQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/notoserif/v7/ga6Kaw1J5X9T9RW6j9bNfFIWbTq6fMRRMw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notoserif/v7/ga6Law1J5X9T9RW6j9bNdOwzTRCUcM1IKoY.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/notoserif/v7/ga6Vaw1J5X9T9RW6j9bNfFIu0RWedO9NOoYIDg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Noto Serif JP` extends GoogleFont {
    override lazy val family: String = "Noto Serif JP"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `japanese`: GoogleFontSubset = new GoogleFontSubset("japanese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`japanese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/notoserifjp/v6/xn77YHs72GKoTvER4Gn3b5eMZBaPRkgfU8fEwb0.otf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/notoserifjp/v6/xn77YHs72GKoTvER4Gn3b5eMZHKMRkgfU8fEwb0.otf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notoserifjp/v6/xn7mYHs72GKoTvER4Gn3b5eMXNikYkY0T84.otf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/notoserifjp/v6/xn77YHs72GKoTvER4Gn3b5eMZCqNRkgfU8fEwb0.otf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/notoserifjp/v6/xn77YHs72GKoTvER4Gn3b5eMZAaKRkgfU8fEwb0.otf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notoserifjp/v6/xn77YHs72GKoTvER4Gn3b5eMZGKLRkgfU8fEwb0.otf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/notoserifjp/v6/xn77YHs72GKoTvER4Gn3b5eMZFqJRkgfU8fEwb0.otf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `900`)
  }
  object `Noto Serif KR` extends GoogleFont {
    override lazy val family: String = "Noto Serif KR"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/notoserifkr/v5/3JnmSDn90Gmq2mr3blnHaTZXTihC8O1ZNH1ahck.otf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/notoserifkr/v5/3JnmSDn90Gmq2mr3blnHaTZXTkxB8O1ZNH1ahck.otf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notoserifkr/v5/3Jn7SDn90Gmq2mr3blnHaTZXduZp1ONyKHQ.otf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/notoserifkr/v5/3JnmSDn90Gmq2mr3blnHaTZXThRA8O1ZNH1ahck.otf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/notoserifkr/v5/3JnmSDn90Gmq2mr3blnHaTZXTjhH8O1ZNH1ahck.otf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notoserifkr/v5/3JnmSDn90Gmq2mr3blnHaTZXTlxG8O1ZNH1ahck.otf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/notoserifkr/v5/3JnmSDn90Gmq2mr3blnHaTZXTmRE8O1ZNH1ahck.otf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `900`)
  }
  object `Noto Serif SC` extends GoogleFont {
    override lazy val family: String = "Noto Serif SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `chinese-simplified`: GoogleFontSubset = new GoogleFontSubset("chinese-simplified")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`chinese-simplified`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/notoserifsc/v5/H4c8BXePl9DZ0Xe7gG9cyOj7mm63SzZBEtERe7U.otf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/notoserifsc/v5/H4c8BXePl9DZ0Xe7gG9cyOj7mgq0SzZBEtERe7U.otf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notoserifsc/v5/H4chBXePl9DZ0Xe7gG9cyOj7oqCcbzhqDtg.otf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/notoserifsc/v5/H4c8BXePl9DZ0Xe7gG9cyOj7mlK1SzZBEtERe7U.otf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/notoserifsc/v5/H4c8BXePl9DZ0Xe7gG9cyOj7mn6ySzZBEtERe7U.otf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notoserifsc/v5/H4c8BXePl9DZ0Xe7gG9cyOj7mhqzSzZBEtERe7U.otf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/notoserifsc/v5/H4c8BXePl9DZ0Xe7gG9cyOj7miKxSzZBEtERe7U.otf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `900`)
  }
  object `Noto Serif TC` extends GoogleFont {
    override lazy val family: String = "Noto Serif TC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `chinese-traditional`: GoogleFontSubset = new GoogleFontSubset("chinese-traditional")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`chinese-traditional`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/notoseriftc/v5/XLY9IZb5bJNDGYxLBibeHZ0Bvr8vbX9GTsoOAX4.otf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/notoseriftc/v5/XLY9IZb5bJNDGYxLBibeHZ0BvtssbX9GTsoOAX4.otf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/notoseriftc/v5/XLYgIZb5bJNDGYxLBibeHZ0BhnEESXFtUsM.otf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/notoseriftc/v5/XLY9IZb5bJNDGYxLBibeHZ0BvoMtbX9GTsoOAX4.otf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/notoseriftc/v5/XLY9IZb5bJNDGYxLBibeHZ0Bvq8qbX9GTsoOAX4.otf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/notoseriftc/v5/XLY9IZb5bJNDGYxLBibeHZ0BvssrbX9GTsoOAX4.otf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/notoseriftc/v5/XLY9IZb5bJNDGYxLBibeHZ0BvvMpbX9GTsoOAX4.otf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `900`)
  }
  object `Nova Cut` extends GoogleFont {
    override lazy val family: String = "Nova Cut"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novacut/v10/KFOkCnSYu8mL-39LkWxPKTM1K9nz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Flat` extends GoogleFont {
    override lazy val family: String = "Nova Flat"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novaflat/v10/QdVUSTc-JgqpytEbVebEuStkm20oJA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Mono` extends GoogleFont {
    override lazy val family: String = "Nova Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novamono/v9/Cn-0JtiGWQ5Ajb--MRKfYGxYrdM9Sg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Oval` extends GoogleFont {
    override lazy val family: String = "Nova Oval"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novaoval/v10/jAnEgHdmANHvPenMaswCMY-h3cWkWg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Round` extends GoogleFont {
    override lazy val family: String = "Nova Round"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novaround/v10/flU9Rqquw5UhEnlwTJYTYYfeeetYEBc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Script` extends GoogleFont {
    override lazy val family: String = "Nova Script"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novascript/v11/7Au7p_IpkSWSTWaFWkumvmQNEl0O0kEx.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Slim` extends GoogleFont {
    override lazy val family: String = "Nova Slim"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novaslim/v10/Z9XUDmZNQAuem8jyZcn-yMOInrib9Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nova Square` extends GoogleFont {
    override lazy val family: String = "Nova Square"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/novasquare/v11/RrQUbo9-9DV7b06QHgSWsZhARYMgGtWA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Numans` extends GoogleFont {
    override lazy val family: String = "Numans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/numans/v8/SlGRmQmGupYAfH8IYRggiHVqaQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Nunito` extends GoogleFont {
    override lazy val family: String = "Nunito"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/nunito/v10/XRXW3I6Li01BKofA-sekZuHJeTsfDQ.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/nunito/v10/XRXQ3I6Li01BKofIMN5MZ-vNWz4PDWtj.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/nunito/v10/XRXW3I6Li01BKofAnsSkZuHJeTsfDQ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/nunito/v10/XRXQ3I6Li01BKofIMN4oZOvNWz4PDWtj.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nunito/v10/XRXV3I6Li01BKof4MuyAbsrVcA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/nunito/v10/XRXX3I6Li01BKofIMOaETM_FcCIG.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/nunito/v10/XRXW3I6Li01BKofA6sKkZuHJeTsfDQ.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/nunito/v10/XRXQ3I6Li01BKofIMN5cYuvNWz4PDWtj.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nunito/v10/XRXW3I6Li01BKofAjsOkZuHJeTsfDQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/nunito/v10/XRXQ3I6Li01BKofIMN44Y-vNWz4PDWtj.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/nunito/v10/XRXW3I6Li01BKofAksCkZuHJeTsfDQ.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/nunito/v10/XRXQ3I6Li01BKofIMN4kYOvNWz4PDWtj.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/nunito/v10/XRXW3I6Li01BKofAtsGkZuHJeTsfDQ.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/nunito/v10/XRXQ3I6Li01BKofIMN4AYevNWz4PDWtj.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Nunito Sans` extends GoogleFont {
    override lazy val family: String = "Nunito Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/nunitosans/v4/pe03MImSLYBIv1o4X1M8cc9yAv5qWVAgVol-.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/nunitosans/v4/pe01MImSLYBIv1o4X1M8cce4GxZrU1QCU5l-06Y.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/nunitosans/v4/pe03MImSLYBIv1o4X1M8cc8WAf5qWVAgVol-.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/nunitosans/v4/pe01MImSLYBIv1o4X1M8cce4G3JoU1QCU5l-06Y.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/nunitosans/v4/pe0qMImSLYBIv1o4X1M8cfe6Kdpickwp.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/nunitosans/v4/pe0oMImSLYBIv1o4X1M8cce4I95Ad1wpT5A.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/nunitosans/v4/pe03MImSLYBIv1o4X1M8cc9iB_5qWVAgVol-.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/nunitosans/v4/pe01MImSLYBIv1o4X1M8cce4GwZuU1QCU5l-06Y.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/nunitosans/v4/pe03MImSLYBIv1o4X1M8cc8GBv5qWVAgVol-.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/nunitosans/v4/pe01MImSLYBIv1o4X1M8cce4G2JvU1QCU5l-06Y.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/nunitosans/v4/pe03MImSLYBIv1o4X1M8cc8aBf5qWVAgVol-.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/nunitosans/v4/pe01MImSLYBIv1o4X1M8cce4G35sU1QCU5l-06Y.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/nunitosans/v4/pe03MImSLYBIv1o4X1M8cc8-BP5qWVAgVol-.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/nunitosans/v4/pe01MImSLYBIv1o4X1M8cce4G1ptU1QCU5l-06Y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Odor Mean Chey` extends GoogleFont {
    override lazy val family: String = "Odor Mean Chey"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/odormeanchey/v10/raxkHiKDttkTe1aOGcJMR1A_4mrY2zqUKafv.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Offside` extends GoogleFont {
    override lazy val family: String = "Offside"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/offside/v6/HI_KiYMWKa9QrAykQ5HiRp-dhpQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Old Standard TT` extends GoogleFont {
    override lazy val family: String = "Old Standard TT"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oldstandardtt/v11/MwQubh3o1vLImiwAVvYawgcf2eVurVC5RHdCZg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/oldstandardtt/v11/MwQsbh3o1vLImiwAVvYawgcf2eVer1q9ZnJSZtQG.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/oldstandardtt/v11/MwQrbh3o1vLImiwAVvYawgcf2eVWEX-dTFxeb80flQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Oldenburg` extends GoogleFont {
    override lazy val family: String = "Oldenburg"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oldenburg/v6/fC1jPY5JYWzbywv7c4V6UU6oXyndrw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Oleo Script` extends GoogleFont {
    override lazy val family: String = "Oleo Script"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oleoscript/v7/rax5HieDvtMOe0iICsUccBhasU7Q8Cad.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/oleoscript/v7/raxkHieDvtMOe0iICsUccCDmnmrY2zqUKafv.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Oleo Script Swash Caps` extends GoogleFont {
    override lazy val family: String = "Oleo Script Swash Caps"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oleoscriptswashcaps/v6/Noaj6Vb-w5SFbTTAsZP_7JkCS08K-jCzDn_HMXquSY0Hg90.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/oleoscriptswashcaps/v6/Noag6Vb-w5SFbTTAsZP_7JkCS08K-jCzDn_HCcaBbYUsn9T5dt0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Open Sans` extends GoogleFont {
    override lazy val family: String = "Open Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/opensans/v16/mem5YaGs126MiZpBA-UN_r8-VeJoCqeDjg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/opensans/v16/memnYaGs126MiZpBA-UFUKWyV-hsKKKTjrPW.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/opensans/v16/mem8YaGs126MiZpBA-U1UpcaXcl0Aw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/opensans/v16/mem6YaGs126MiZpBA-UFUJ0ef8xkA76a.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/opensans/v16/mem5YaGs126MiZpBA-UNirk-VeJoCqeDjg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/opensans/v16/memnYaGs126MiZpBA-UFUKXGUehsKKKTjrPW.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/opensans/v16/mem5YaGs126MiZpBA-UN7rg-VeJoCqeDjg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/opensans/v16/memnYaGs126MiZpBA-UFUKWiUOhsKKKTjrPW.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/opensans/v16/mem5YaGs126MiZpBA-UN8rs-VeJoCqeDjg.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/opensans/v16/memnYaGs126MiZpBA-UFUKW-U-hsKKKTjrPW.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Open Sans Condensed` extends GoogleFont {
    override lazy val family: String = "Open Sans Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/opensanscondensed/v13/z7NFdQDnbTkabZAIOl9il_O6KJj73e7Ff1GhPuLGRpWRyAs.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/opensanscondensed/v13/z7NHdQDnbTkabZAIOl9il_O6KJj73e7Fd_-7suDMQreU2AsJSg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/opensanscondensed/v13/z7NFdQDnbTkabZAIOl9il_O6KJj73e7Ff0GmPuLGRpWRyAs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `700`)
  }
  object `Oranienbaum` extends GoogleFont {
    override lazy val family: String = "Oranienbaum"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oranienbaum/v7/OZpHg_txtzZKMuXLIVrx-3zn7kz3dpHc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Orbitron` extends GoogleFont {
    override lazy val family: String = "Orbitron"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/orbitron/v10/yMJRMIlzdpvBhQQL_Tq8fSx5i814.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/orbitron/v10/yMJWMIlzdpvBhQQL_QJIVAhxoNFxW0Hz.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/orbitron/v10/yMJWMIlzdpvBhQQL_QIAUghxoNFxW0Hz.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/orbitron/v10/yMJWMIlzdpvBhQQL_QI4UAhxoNFxW0Hz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `700`, `900`)
  }
  object `Oregano` extends GoogleFont {
    override lazy val family: String = "Oregano"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oregano/v6/If2IXTPxciS3H4S2kZffPznO3yM.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/oregano/v6/If2KXTPxciS3H4S2oZXVOxvLzyP_qw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Orienta` extends GoogleFont {
    override lazy val family: String = "Orienta"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/orienta/v6/PlI9FlK4Jrl5Y9zNeyeo9HRFhcU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Original Surfer` extends GoogleFont {
    override lazy val family: String = "Original Surfer"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/originalsurfer/v7/RWmQoKGZ9vIirYntXJ3_MbekzNMiDEtvAlaMKw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Oswald` extends GoogleFont {
    override lazy val family: String = "Oswald"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/oswald/v23/TK3_WkUHHAIjg75cFRf3bXL8LICs13FvgUFoZAaRliE.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/oswald/v23/TK3_WkUHHAIjg75cFRf3bXL8LICs169vgUFoZAaRliE.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oswald/v23/TK3_WkUHHAIjg75cFRf3bXL8LICs1_FvgUFoZAaRliE.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/oswald/v23/TK3_WkUHHAIjg75cFRf3bXL8LICs18NvgUFoZAaRliE.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/oswald/v23/TK3_WkUHHAIjg75cFRf3bXL8LICs1y9ogUFoZAaRliE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/oswald/v23/TK3_WkUHHAIjg75cFRf3bXL8LICs1xZogUFoZAaRliE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Over the Rainbow` extends GoogleFont {
    override lazy val family: String = "Over the Rainbow"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/overtherainbow/v9/11haGoXG1k_HKhMLUWz7Mc7vvW5upvOm9NA2XG0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Overlock` extends GoogleFont {
    override lazy val family: String = "Overlock"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/overlock/v8/Z9XVDmdMWRiN1_T9Z4Te4u2El6GC.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/overlock/v8/Z9XTDmdMWRiN1_T9Z7Tc6OmmkrGC7Cs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/overlock/v8/Z9XSDmdMWRiN1_T9Z7xizcmMvL2L9TLT.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/overlock/v8/Z9XQDmdMWRiN1_T9Z7Tc0FWJtrmp8CLTlNs.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/overlock/v8/Z9XSDmdMWRiN1_T9Z7xaz8mMvL2L9TLT.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/overlock/v8/Z9XQDmdMWRiN1_T9Z7Tc0G2Ltrmp8CLTlNs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Overlock SC` extends GoogleFont {
    override lazy val family: String = "Overlock SC"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/overlocksc/v7/1cX3aUHKGZrstGAY8nwVzHGAq8Sk1PoH.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Overpass` extends GoogleFont {
    override lazy val family: String = "Overpass"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/overpass/v3/qFdB35WCmI96Ajtm81nGU97gxhcJk1s.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/overpass/v3/qFdD35WCmI96Ajtm81Gga7rqwjUMg1siNQ.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/overpass/v3/qFdA35WCmI96Ajtm81lqcv7K6BsAikI7.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/overpass/v3/qFdC35WCmI96Ajtm81GgaxbL4h8ij1I7LLE.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/overpass/v3/qFdA35WCmI96Ajtm81kOcf7K6BsAikI7.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/overpass/v3/qFdC35WCmI96Ajtm81Gga3LI4h8ij1I7LLE.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/overpass/v3/qFdH35WCmI96Ajtm82GiWdrCwwcJ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/overpass/v3/qFdB35WCmI96Ajtm81GgU97gxhcJk1s.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/overpass/v3/qFdA35WCmI96Ajtm81l6d_7K6BsAikI7.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/overpass/v3/qFdC35WCmI96Ajtm81GgawbO4h8ij1I7LLE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/overpass/v3/qFdA35WCmI96Ajtm81kedv7K6BsAikI7.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/overpass/v3/qFdC35WCmI96Ajtm81Gga2LP4h8ij1I7LLE.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/overpass/v3/qFdA35WCmI96Ajtm81kCdf7K6BsAikI7.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/overpass/v3/qFdC35WCmI96Ajtm81Gga37M4h8ij1I7LLE.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/overpass/v3/qFdA35WCmI96Ajtm81kmdP7K6BsAikI7.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/overpass/v3/qFdC35WCmI96Ajtm81Gga1rN4h8ij1I7LLE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Overpass Mono` extends GoogleFont {
    override lazy val family: String = "Overpass Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/overpassmono/v4/_Xm3-H86tzKDdAPa-KPQZ-AC3oSWk_edB3Zf8EQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/overpassmono/v4/_Xmq-H86tzKDdAPa-KPQZ-AC5ii-t_-2G38.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/overpassmono/v4/_Xm3-H86tzKDdAPa-KPQZ-AC3vCQk_edB3Zf8EQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/overpassmono/v4/_Xm3-H86tzKDdAPa-KPQZ-AC3pSRk_edB3Zf8EQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `600`, `700`)
  }
  object `Ovo` extends GoogleFont {
    override lazy val family: String = "Ovo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ovo/v10/yYLl0h7Wyfzjy4Q5_3WVxA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Oxygen` extends GoogleFont {
    override lazy val family: String = "Oxygen"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/oxygen/v8/2sDcZG1Wl4LcnbuCJW8Db2-4C7wFZQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oxygen/v8/2sDfZG1Wl4Lcnbu6iUcnZ0SkAg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/oxygen/v8/2sDcZG1Wl4LcnbuCNWgDb2-4C7wFZQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `700`)
  }
  object `Oxygen Mono` extends GoogleFont {
    override lazy val family: String = "Oxygen Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/oxygenmono/v6/h0GsssGg9FxgDgCjLeAd7ijfze-PPlUu.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `PT Mono` extends GoogleFont {
    override lazy val family: String = "PT Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptmono/v6/9oRONYoBnWILk-9ArCg5MtPyAcg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `PT Sans` extends GoogleFont {
    override lazy val family: String = "PT Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptsans/v10/jizaRExUiTo99u79P0WOxOGMMDQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ptsans/v10/jizYRExUiTo99u79D0eEwMOJIDQA-g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ptsans/v10/jizfRExUiTo99u79B_mh4OmnLD0Z4zM.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ptsans/v10/jizdRExUiTo99u79D0e8fOytKB8c8zMrig.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `PT Sans Caption` extends GoogleFont {
    override lazy val family: String = "PT Sans Caption"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptsanscaption/v11/0FlMVP6Hrxmt7-fsUFhlFXNIlpcqfQXwQy6yxg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ptsanscaption/v11/0FlJVP6Hrxmt7-fsUFhlFXNIlpcSwSrUSwWuz38Tgg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `PT Sans Narrow` extends GoogleFont {
    override lazy val family: String = "PT Sans Narrow"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptsansnarrow/v10/BngRUXNadjH0qYEzV7ab-oWlsYCByxyKeuDp.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ptsansnarrow/v10/BngSUXNadjH0qYEzV7ab-oWlsbg95DiCUfzgRd-3.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `PT Serif` extends GoogleFont {
    override lazy val family: String = "PT Serif"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptserif/v10/EJRVQgYoZZY2vCFuvDFRxL6ddjb-.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ptserif/v10/EJRTQgYoZZY2vCFuvAFTzrq_cyb-vco.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ptserif/v10/EJRSQgYoZZY2vCFuvAnt65qVXSr3pNNB.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ptserif/v10/EJRQQgYoZZY2vCFuvAFT9gaQVy7VocNB6Iw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `PT Serif Caption` extends GoogleFont {
    override lazy val family: String = "PT Serif Caption"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ptserifcaption/v10/ieVl2ZhbGCW-JoW6S34pSDpqYKU059WxDCs5cvI.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ptserifcaption/v10/ieVj2ZhbGCW-JoW6S34pSDpqYKU019e7CAk8YvJEeg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Pacifico` extends GoogleFont {
    override lazy val family: String = "Pacifico"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pacifico/v14/FwZY7-Qmy14u9lezJ96A4sijpFu_.ttf"))

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
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/padauk/v5/RrQRboJg-id7OnbBa0_g3LlYbg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/padauk/v5/RrQSboJg-id7Onb512DE1JJEZ4YwGg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Palanquin` extends GoogleFont {
    override lazy val family: String = "Palanquin"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/palanquin/v4/9XUhlJ90n1fBFg7ceXwUEltI7rWmZzTH.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/palanquin/v4/9XUilJ90n1fBFg7ceXwUvnpoxJuqbi3ezg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/palanquin/v4/9XUilJ90n1fBFg7ceXwU2nloxJuqbi3ezg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/palanquin/v4/9XUnlJ90n1fBFg7ceXwsdlFMzLC2Zw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/palanquin/v4/9XUilJ90n1fBFg7ceXwUgnhoxJuqbi3ezg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/palanquin/v4/9XUilJ90n1fBFg7ceXwUrn9oxJuqbi3ezg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/palanquin/v4/9XUilJ90n1fBFg7ceXwUyn5oxJuqbi3ezg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Palanquin Dark` extends GoogleFont {
    override lazy val family: String = "Palanquin Dark"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/palanquindark/v5/xn75YHgl1nqmANMB-26xC7yuF_6OTEo9VtfE.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/palanquindark/v5/xn76YHgl1nqmANMB-26xC7yuF8Z6ZW41fcvN2KT4.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/palanquindark/v5/xn76YHgl1nqmANMB-26xC7yuF8ZWYm41fcvN2KT4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/palanquindark/v5/xn76YHgl1nqmANMB-26xC7yuF8YyY241fcvN2KT4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`)
  }
  object `Pangolin` extends GoogleFont {
    override lazy val family: String = "Pangolin"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pangolin/v4/cY9GfjGcW0FPpi-tWPfK5d3aiLBG.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Paprika` extends GoogleFont {
    override lazy val family: String = "Paprika"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/paprika/v6/8QIJdijZitv49rDfuIgOq7jkAOw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Parisienne` extends GoogleFont {
    override lazy val family: String = "Parisienne"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/parisienne/v6/E21i_d3kivvAkxhLEVZpcy96DuKuavM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Passero One` extends GoogleFont {
    override lazy val family: String = "Passero One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/passeroone/v10/JTUTjIko8DOq5FeaeEAjgE5B5Arr-s50.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Passion One` extends GoogleFont {
    override lazy val family: String = "Passion One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/passionone/v9/PbynFmL8HhTPqbjUzux3JHuW_Frg6YoV.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/passionone/v9/Pby6FmL8HhTPqbjUzux3JEMq037owpYcuH8y.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/passionone/v9/Pby6FmL8HhTPqbjUzux3JEMS0X7owpYcuH8y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `900`)
  }
  object `Pathway Gothic One` extends GoogleFont {
    override lazy val family: String = "Pathway Gothic One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pathwaygothicone/v7/MwQrbgD32-KAvjkYGNUUxAtW7pEBwx-dTFxeb80flQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Patrick Hand` extends GoogleFont {
    override lazy val family: String = "Patrick Hand"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/patrickhand/v12/LDI1apSQOAYtSuYWp8ZhfYeMWcjKm7sp8g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Patrick Hand SC` extends GoogleFont {
    override lazy val family: String = "Patrick Hand SC"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/patrickhandsc/v6/0nkwC9f7MfsBiWcLtY65AWDK873ViSi6JQc7Vg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pattaya` extends GoogleFont {
    override lazy val family: String = "Pattaya"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pattaya/v4/ea8ZadcqV_zkHY-XNdCn92ZEmVs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Patua One` extends GoogleFont {
    override lazy val family: String = "Patua One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/patuaone/v9/ZXuke1cDvLCKLDcimxBI5PNvNA9LuA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pavanam` extends GoogleFont {
    override lazy val family: String = "Pavanam"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `tamil`: GoogleFontSubset = new GoogleFontSubset("tamil")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `tamil`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pavanam/v3/BXRrvF_aiezLh0xPDOtQ9Wf0QcE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Paytone One` extends GoogleFont {
    override lazy val family: String = "Paytone One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/paytoneone/v11/0nksC9P7MfYHj2oFtYm2CiTqivr9iBq_.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Peddana` extends GoogleFont {
    override lazy val family: String = "Peddana"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/peddana/v6/aFTU7PBhaX89UcKWhh2aBYyMcKw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Peralta` extends GoogleFont {
    override lazy val family: String = "Peralta"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/peralta/v6/hYkJPu0-RP_9d3kRGxAhrv956B8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Permanent Marker` extends GoogleFont {
    override lazy val family: String = "Permanent Marker"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/permanentmarker/v8/Fh4uPib9Iyv2ucM6pGQMWimMp004HaqIfrT5nlk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Petit Formal Script` extends GoogleFont {
    override lazy val family: String = "Petit Formal Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/petitformalscript/v6/B50TF6xQr2TXJBnGOFME6u5OR83oRP5qoHnqP4gZSiE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Petrona` extends GoogleFont {
    override lazy val family: String = "Petrona"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/petrona/v7/mtG64_NXL7bZo9XXsXVStGsRwCU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Philosopher` extends GoogleFont {
    override lazy val family: String = "Philosopher"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/philosopher/v10/vEFV2_5QCwIS4_Dhez5jcVBpRUwU08qe.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/philosopher/v10/vEFX2_5QCwIS4_Dhez5jcWBrT0g21tqeR7c.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/philosopher/v10/vEFI2_5QCwIS4_Dhez5jcWjVamgc-NaXXq7H.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/philosopher/v10/vEFK2_5QCwIS4_Dhez5jcWBrd_QZ8tK1W77HtMo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Piedra` extends GoogleFont {
    override lazy val family: String = "Piedra"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/piedra/v7/ke8kOg8aN0Bn7hTunEyHN_M3gA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pinyon Script` extends GoogleFont {
    override lazy val family: String = "Pinyon Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pinyonscript/v8/6xKpdSJbL9-e9LuoeQiDRQR8aOLQO4bhiDY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pirata One` extends GoogleFont {
    override lazy val family: String = "Pirata One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pirataone/v7/I_urMpiDvgLdLh0fAtoftiiEr5_BdZ8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Plaster` extends GoogleFont {
    override lazy val family: String = "Plaster"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/plaster/v10/DdTm79QatW80eRh4Ei5JOtLOeLI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Play` extends GoogleFont {
    override lazy val family: String = "Play"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/play/v10/6aez4K2oVqwIjtI8Hp8Tx3A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/play/v10/6ae84K2oVqwItm4TOpc423nTJTM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Playball` extends GoogleFont {
    override lazy val family: String = "Playball"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/playball/v8/TK3gWksYAxQ7jbsKcj8Dl-tPKo2t.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Playfair Display` extends GoogleFont {
    override lazy val family: String = "Playfair Display"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/playfairdisplay/v14/nuFiD-vYSZviVYUb_rj3ij__anPXPTvSgWE_-xU.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/playfairdisplay/v14/nuFkD-vYSZviVYUb_rj3ij__anPXDTnYhUM66xV7PQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/playfairdisplay/v14/nuFlD-vYSZviVYUb_rj3ij__anPXBYf9pWkU5xxiJKY.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/playfairdisplay/v14/nuFnD-vYSZviVYUb_rj3ij__anPXDTngOWwe4z5nNKaV_w.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/playfairdisplay/v14/nuFlD-vYSZviVYUb_rj3ij__anPXBb__pWkU5xxiJKY.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/playfairdisplay/v14/nuFnD-vYSZviVYUb_rj3ij__anPXDTngAW4e4z5nNKaV_w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Playfair Display SC` extends GoogleFont {
    override lazy val family: String = "Playfair Display SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/playfairdisplaysc/v8/ke85OhoaMkR6-hSn7kbHVoFf7ZfgMPr_pb4GEcM2M4s.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/playfairdisplaysc/v8/ke87OhoaMkR6-hSn7kbHVoFf7ZfgMPr_lbwMFeEzI4sNKg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/playfairdisplaysc/v8/ke80OhoaMkR6-hSn7kbHVoFf7ZfgMPr_nQIpNcsdL4IUMyE.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/playfairdisplaysc/v8/ke82OhoaMkR6-hSn7kbHVoFf7ZfgMPr_lbw0qc4XK6ARIyH5IA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/playfairdisplaysc/v8/ke80OhoaMkR6-hSn7kbHVoFf7ZfgMPr_nTorNcsdL4IUMyE.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/playfairdisplaysc/v8/ke82OhoaMkR6-hSn7kbHVoFf7ZfgMPr_lbw0kcwXK6ARIyH5IA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Podkova` extends GoogleFont {
    override lazy val family: String = "Podkova"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/podkova/v12/K2FxfZ1EmftJSV9VWJ75JoKhHys.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/podkova/v12/K2F0fZ1EmftJSV9VYGrQAoqKAyLzfWo.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/podkova/v12/K2F0fZ1EmftJSV9VYEbXAoqKAyLzfWo.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/podkova/v12/K2F0fZ1EmftJSV9VYCLWAoqKAyLzfWo.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/podkova/v12/K2F0fZ1EmftJSV9VYD7VAoqKAyLzfWo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `800`)
  }
  object `Poiret One` extends GoogleFont {
    override lazy val family: String = "Poiret One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/poiretone/v7/UqyVK80NJXN4zfRgbdfbk5lWVscxdKE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Poller One` extends GoogleFont {
    override lazy val family: String = "Poller One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pollerone/v8/ahccv82n0TN3gia5E4Bud-lbgUS5u0s.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Poly` extends GoogleFont {
    override lazy val family: String = "Poly"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/poly/v9/MQpb-W6wKNitRLCAq2Lpris.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/poly/v9/MQpV-W6wKNitdLKKr0DsviuGWA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Pompiere` extends GoogleFont {
    override lazy val family: String = "Pompiere"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pompiere/v8/VEMyRoxis5Dwuyeov6Wt5jDtreOL.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pontano Sans` extends GoogleFont {
    override lazy val family: String = "Pontano Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pontanosans/v6/qFdD35GdgYR8EzR6oBLDHa3qwjUMg1siNQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Poor Story` extends GoogleFont {
    override lazy val family: String = "Poor Story"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/poorstory/v7/jizfREFUsnUct9P6cDfd4OmnLD0Z4zM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Poppins` extends GoogleFont {
    override lazy val family: String = "Poppins"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/poppins/v6/pxiGyp8kv8JHgFVrLPTed3FBGPaTSQ.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/poppins/v6/pxiAyp8kv8JHgFVrJJLmE3tFOvODSVFF.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/poppins/v6/pxiByp8kv8JHgFVrLFj_V1tvFP-KUEg.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/poppins/v6/pxiDyp8kv8JHgFVrJJLmv1plEN2PQEhcqw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/poppins/v6/pxiByp8kv8JHgFVrLDz8V1tvFP-KUEg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/poppins/v6/pxiDyp8kv8JHgFVrJJLm21llEN2PQEhcqw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/poppins/v6/pxiEyp8kv8JHgFVrFJDUc1NECPY.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/poppins/v6/pxiGyp8kv8JHgFVrJJLed3FBGPaTSQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/poppins/v6/pxiByp8kv8JHgFVrLGT9V1tvFP-KUEg.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/poppins/v6/pxiDyp8kv8JHgFVrJJLmg1hlEN2PQEhcqw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/poppins/v6/pxiByp8kv8JHgFVrLEj6V1tvFP-KUEg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/poppins/v6/pxiDyp8kv8JHgFVrJJLmr19lEN2PQEhcqw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/poppins/v6/pxiByp8kv8JHgFVrLCz7V1tvFP-KUEg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/poppins/v6/pxiDyp8kv8JHgFVrJJLmy15lEN2PQEhcqw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/poppins/v6/pxiByp8kv8JHgFVrLDD4V1tvFP-KUEg.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/poppins/v6/pxiDyp8kv8JHgFVrJJLm111lEN2PQEhcqw.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/poppins/v6/pxiByp8kv8JHgFVrLBT5V1tvFP-KUEg.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/poppins/v6/pxiDyp8kv8JHgFVrJJLm81xlEN2PQEhcqw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Port Lligat Sans` extends GoogleFont {
    override lazy val family: String = "Port Lligat Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/portlligatsans/v7/kmKmZrYrGBbdN1aV7Vokow6Lw4s4l7N0Tx4xEcQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Port Lligat Slab` extends GoogleFont {
    override lazy val family: String = "Port Lligat Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/portlligatslab/v7/LDIpaoiQNgArA8kR7ulhZ8P_NYOss7ob9yGLmfI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pragati Narrow` extends GoogleFont {
    override lazy val family: String = "Pragati Narrow"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pragatinarrow/v4/vm8vdRf0T0bS1ffgsPB7WZ-mD17_ytN3M48a.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/pragatinarrow/v4/vm8sdRf0T0bS1ffgsPB7WZ-mD2ZD5fd_GJMTlo_4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Prata` extends GoogleFont {
    override lazy val family: String = "Prata"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/prata/v9/6xKhdSpbNNCT-vWIAG_5LWwJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Preahvihear` extends GoogleFont {
    override lazy val family: String = "Preahvihear"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/preahvihear/v10/6NUS8F-dNQeEYhzj7uluxswE49FJf8Wv.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Press Start 2P` extends GoogleFont {
    override lazy val family: String = "Press Start 2P"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pressstart2p/v7/e3t4euO8T-267oIAQAu6jDQyK0nSgPJE4580.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Pridi` extends GoogleFont {
    override lazy val family: String = "Pridi"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/pridi/v4/2sDdZG5JnZLfkc1SiE0jRUG0AqUc.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/pridi/v4/2sDdZG5JnZLfkc02i00jRUG0AqUc.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/pridi/v4/2sDQZG5JnZLfkfWao2krbl29.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/pridi/v4/2sDdZG5JnZLfkc1uik0jRUG0AqUc.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/pridi/v4/2sDdZG5JnZLfkc1CjU0jRUG0AqUc.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/pridi/v4/2sDdZG5JnZLfkc0mjE0jRUG0AqUc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`)
  }
  object `Princess Sofia` extends GoogleFont {
    override lazy val family: String = "Princess Sofia"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/princesssofia/v7/qWczB6yguIb8DZ_GXZst16n7GRz7mDUoupoI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Prociono` extends GoogleFont {
    override lazy val family: String = "Prociono"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/prociono/v8/r05YGLlR-KxAf9GGO8upyDYtStiJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Prompt` extends GoogleFont {
    override lazy val family: String = "Prompt"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/prompt/v3/-W_9XJnvUD7dzB2CA9oYREcjeo0k.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/prompt/v3/-W_7XJnvUD7dzB2KZeJ8TkMBf50kbiM.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/prompt/v3/-W_8XJnvUD7dzB2Cr_s4bmkvc5Q9dw.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/prompt/v3/-W_6XJnvUD7dzB2KZeLQb2MrUZEtdzow.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/prompt/v3/-W_8XJnvUD7dzB2Cy_g4bmkvc5Q9dw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/prompt/v3/-W_6XJnvUD7dzB2KZeK0bGMrUZEtdzow.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/prompt/v3/-W__XJnvUD7dzB26Z9AcZkIzeg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/prompt/v3/-W_9XJnvUD7dzB2KZdoYREcjeo0k.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/prompt/v3/-W_8XJnvUD7dzB2Ck_k4bmkvc5Q9dw.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/prompt/v3/-W_6XJnvUD7dzB2KZeLsbWMrUZEtdzow.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/prompt/v3/-W_8XJnvUD7dzB2Cv_44bmkvc5Q9dw.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/prompt/v3/-W_6XJnvUD7dzB2KZeLAamMrUZEtdzow.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/prompt/v3/-W_8XJnvUD7dzB2C2_84bmkvc5Q9dw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/prompt/v3/-W_6XJnvUD7dzB2KZeKka2MrUZEtdzow.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/prompt/v3/-W_8XJnvUD7dzB2Cx_w4bmkvc5Q9dw.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/prompt/v3/-W_6XJnvUD7dzB2KZeK4aGMrUZEtdzow.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/prompt/v3/-W_8XJnvUD7dzB2C4_04bmkvc5Q9dw.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/prompt/v3/-W_6XJnvUD7dzB2KZeKcaWMrUZEtdzow.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Prosto One` extends GoogleFont {
    override lazy val family: String = "Prosto One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/prostoone/v7/OpNJno4VhNfK-RgpwWWxpipfWhXD00c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Proza Libre` extends GoogleFont {
    override lazy val family: String = "Proza Libre"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/prozalibre/v3/LYjGdGHgj0k1DIQRyUEyyHovftvXWYyz.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/prozalibre/v3/LYjEdGHgj0k1DIQRyUEyyEotdN_1XJyz7zc.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/prozalibre/v3/LYjbdGHgj0k1DIQRyUEyyELbV__fcpC69i6N.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/prozalibre/v3/LYjZdGHgj0k1DIQRyUEyyEotTCvceJSY8z6Np1k.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/prozalibre/v3/LYjbdGHgj0k1DIQRyUEyyEL3UP_fcpC69i6N.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/prozalibre/v3/LYjZdGHgj0k1DIQRyUEyyEotTAfbeJSY8z6Np1k.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/prozalibre/v3/LYjbdGHgj0k1DIQRyUEyyEKTUf_fcpC69i6N.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/prozalibre/v3/LYjZdGHgj0k1DIQRyUEyyEotTGPaeJSY8z6Np1k.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/prozalibre/v3/LYjbdGHgj0k1DIQRyUEyyEKPUv_fcpC69i6N.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/prozalibre/v3/LYjZdGHgj0k1DIQRyUEyyEotTH_ZeJSY8z6Np1k.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Puritan` extends GoogleFont {
    override lazy val family: String = "Puritan"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/puritan/v10/845YNMgkAJ2VTtIo9JrwRdaI50M.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/puritan/v10/845aNMgkAJ2VTtIoxJj6QfSN90PfXA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/puritan/v10/845dNMgkAJ2VTtIozCbfYd6j-0rGRes.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/puritan/v10/845fNMgkAJ2VTtIoxJjC_dup_2jDVevnLQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Purple Purse` extends GoogleFont {
    override lazy val family: String = "Purple Purse"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/purplepurse/v7/qWctB66gv53iAp-Vfs4My6qyeBb_ujA4ug.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Quando` extends GoogleFont {
    override lazy val family: String = "Quando"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quando/v7/xMQVuFNaVa6YuW0pC6WzKX_QmA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Quantico` extends GoogleFont {
    override lazy val family: String = "Quantico"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quantico/v8/rax-HiSdp9cPL3KIF4xsLjxSmlLZ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/quantico/v8/rax4HiSdp9cPL3KIF7xuJDhwn0LZ6T8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/quantico/v8/rax5HiSdp9cPL3KIF7TQARhasU7Q8Cad.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/quantico/v8/rax7HiSdp9cPL3KIF7xuHIRfu0ry9TadML4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Quattrocento` extends GoogleFont {
    override lazy val family: String = "Quattrocento"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quattrocento/v10/OZpEg_xvsDZQL_LKIF7q4jPHxGL7f4jFuA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/quattrocento/v10/OZpbg_xvsDZQL_LKIF7q4jP_eE3fd6PZsXcM9w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Quattrocento Sans` extends GoogleFont {
    override lazy val family: String = "Quattrocento Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quattrocentosans/v11/va9c4lja2NVIDdIAAoMR5MfuElaRB3zOvU7eHGHJ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/quattrocentosans/v11/va9a4lja2NVIDdIAAoMR5MfuElaRB0zMt0r8GXHJkLI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/quattrocentosans/v11/va9Z4lja2NVIDdIAAoMR5MfuElaRB0RykmrWN33AiasJ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/quattrocentosans/v11/va9X4lja2NVIDdIAAoMR5MfuElaRB0zMj_bTPXnijLsJV7E.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Questrial` extends GoogleFont {
    override lazy val family: String = "Questrial"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/questrial/v8/QdVUSTchPBm7nuUeVf7EuStkm20oJA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Quicksand` extends GoogleFont {
    override lazy val family: String = "Quicksand"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/quicksand/v9/6xKodSZaM9iE8KbpRA_pgHYoSMj-N4_4kQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quicksand/v9/6xKtdSZaM9iE8KbpRA_RLF4MQOPiPg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/quicksand/v9/6xKodSZaM9iE8KbpRA_p2HcoSMj-N4_4kQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/quicksand/v9/6xKodSZaM9iE8KbpRA_pkHEoSMj-N4_4kQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `700`)
  }
  object `Quintessential` extends GoogleFont {
    override lazy val family: String = "Quintessential"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/quintessential/v6/fdNn9sOGq31Yjnh3qWU14DdtjY5wS7kmAyxM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Qwigley` extends GoogleFont {
    override lazy val family: String = "Qwigley"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/qwigley/v8/1cXzaU3UGJb5tGoCuVxsi1mBmcE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Racing Sans One` extends GoogleFont {
    override lazy val family: String = "Racing Sans One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/racingsansone/v6/sykr-yRtm7EvTrXNxkv5jfKKyDCwL3rmWpIBtA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Radley` extends GoogleFont {
    override lazy val family: String = "Radley"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/radley/v13/LYjDdGzinEIjCN19oAlEpVs3VQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/radley/v13/LYjBdGzinEIjCN1NogNAh14nVcfe.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Rajdhani` extends GoogleFont {
    override lazy val family: String = "Rajdhani"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/rajdhani/v8/LDI2apCSOBg7S-QT7pasEcOsc-bGkqIw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rajdhani/v8/LDIxapCSOBg7S-QT7q4AOeekWPrP.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/rajdhani/v8/LDI2apCSOBg7S-QT7pb0EMOsc-bGkqIw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/rajdhani/v8/LDI2apCSOBg7S-QT7pbYF8Osc-bGkqIw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rajdhani/v8/LDI2apCSOBg7S-QT7pa8FsOsc-bGkqIw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Rakkas` extends GoogleFont {
    override lazy val family: String = "Rakkas"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rakkas/v6/Qw3cZQlNHiblL3j_lttPOeMcCw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Raleway` extends GoogleFont {
    override lazy val family: String = "Raleway"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/raleway/v13/1Ptsg8zYS_SKggPNwE4ISotrDfGGxA.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/raleway/v13/1Ptqg8zYS_SKggPNyCgwLoFvL_SWxEMT.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/raleway/v13/1Ptrg8zYS_SKggPNwOIpaqFFAfif3Vo.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/raleway/v13/1Ptpg8zYS_SKggPNyCgwgqBPBdqazVoK4A.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/raleway/v13/1Ptrg8zYS_SKggPNwIYqaqFFAfif3Vo.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/raleway/v13/1Ptpg8zYS_SKggPNyCgw5qNPBdqazVoK4A.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/raleway/v13/1Ptug8zYS_SKggPN-CoCTqluHfE.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/raleway/v13/1Ptsg8zYS_SKggPNyCgISotrDfGGxA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/raleway/v13/1Ptrg8zYS_SKggPNwN4raqFFAfif3Vo.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/raleway/v13/1Ptpg8zYS_SKggPNyCgwvqJPBdqazVoK4A.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/raleway/v13/1Ptrg8zYS_SKggPNwPIsaqFFAfif3Vo.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/raleway/v13/1Ptpg8zYS_SKggPNyCgwkqVPBdqazVoK4A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/raleway/v13/1Ptrg8zYS_SKggPNwJYtaqFFAfif3Vo.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/raleway/v13/1Ptpg8zYS_SKggPNyCgw9qRPBdqazVoK4A.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/raleway/v13/1Ptrg8zYS_SKggPNwIouaqFFAfif3Vo.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/raleway/v13/1Ptpg8zYS_SKggPNyCgw6qdPBdqazVoK4A.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/raleway/v13/1Ptrg8zYS_SKggPNwK4vaqFFAfif3Vo.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/raleway/v13/1Ptpg8zYS_SKggPNyCgwzqZPBdqazVoK4A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Raleway Dots` extends GoogleFont {
    override lazy val family: String = "Raleway Dots"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ralewaydots/v6/6NUR8FifJg6AfQvzpshgwJ8kyf9Fdty2ew.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ramabhadra` extends GoogleFont {
    override lazy val family: String = "Ramabhadra"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ramabhadra/v8/EYq2maBOwqRW9P1SQ83LehNGX5uWw3o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ramaraja` extends GoogleFont {
    override lazy val family: String = "Ramaraja"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ramaraja/v3/SlGTmQearpYAYG1CABIkqnB6aSQU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rambla` extends GoogleFont {
    override lazy val family: String = "Rambla"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rambla/v6/snfrs0ip98hx6mr0I7IONthkwQ.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/rambla/v6/snfps0ip98hx6mrEIbgKFN10wYKa.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rambla/v6/snfos0ip98hx6mrMn50qPvN4yJuDYQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/rambla/v6/snfus0ip98hx6mrEIYC2O_l86p6TYS-Y.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Rammetto One` extends GoogleFont {
    override lazy val family: String = "Rammetto One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rammettoone/v7/LhWiMV3HOfMbMetJG3lQDpp9Mvuciu-_SQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ranchers` extends GoogleFont {
    override lazy val family: String = "Ranchers"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ranchers/v6/zrfm0H3Lx-P2Xvs2AoDYDC79XTHv.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rancho` extends GoogleFont {
    override lazy val family: String = "Rancho"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rancho/v9/46kulbzmXjLaqZRlbWXgd0RY1g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ranga` extends GoogleFont {
    override lazy val family: String = "Ranga"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ranga/v4/C8ct4cYisGb28p6CLDwZwmGE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ranga/v4/C8cg4cYisGb28qY-AxgR6X2NZAn2.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Rasa` extends GoogleFont {
    override lazy val family: String = "Rasa"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `gujarati`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/rasa/v4/xn7gYHIn1mWmdg52sgC7S9XdZN8.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rasa/v4/xn7vYHIn1mWmTqJelgiQV9w.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/rasa/v4/xn7gYHIn1mWmdlZ3sgC7S9XdZN8.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/rasa/v4/xn7gYHIn1mWmdnpwsgC7S9XdZN8.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rasa/v4/xn7gYHIn1mWmdh5xsgC7S9XdZN8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Rationale` extends GoogleFont {
    override lazy val family: String = "Rationale"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rationale/v10/9XUnlJ92n0_JFxHIfHcsdlFMzLC2Zw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ravi Prakash` extends GoogleFont {
    override lazy val family: String = "Ravi Prakash"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/raviprakash/v5/gokpH6fsDkVrF9Bv9X8SOAKHmNZEq6TTFw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Redressed` extends GoogleFont {
    override lazy val family: String = "Redressed"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/redressed/v9/x3dickHUbrmJ7wMy9MsBfPACvy_1BA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Reem Kufi` extends GoogleFont {
    override lazy val family: String = "Reem Kufi"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/reemkufi/v6/2sDcZGJLip7W2J7v7wQDb2-4C7wFZQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Reenie Beanie` extends GoogleFont {
    override lazy val family: String = "Reenie Beanie"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/reeniebeanie/v9/z7NSdR76eDkaJKZJFkkjuvWxbP2_qoOgf_w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Revalia` extends GoogleFont {
    override lazy val family: String = "Revalia"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/revalia/v6/WwkexPimBE2-4ZPEeVruNIgJSNM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rhodium Libre` extends GoogleFont {
    override lazy val family: String = "Rhodium Libre"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rhodiumlibre/v3/1q2AY5adA0tn_ukeHcQHqpx6pETLeo2gm2U.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ribeye` extends GoogleFont {
    override lazy val family: String = "Ribeye"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ribeye/v7/L0x8DFMxk1MP9R3RvPCmRSlUig.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ribeye Marrow` extends GoogleFont {
    override lazy val family: String = "Ribeye Marrow"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ribeyemarrow/v8/GFDsWApshnqMRO2JdtRZ2d0vEAwTVWgKdtw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Righteous` extends GoogleFont {
    override lazy val family: String = "Righteous"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/righteous/v7/1cXxaUPXBpj2rGoU7C9mj3uEicG01A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Risque` extends GoogleFont {
    override lazy val family: String = "Risque"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/risque/v6/VdGfAZUfHosahXxoCUYVBJ-T5g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Roboto` extends GoogleFont {
    override lazy val family: String = "Roboto"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/roboto/v19/KFOkCnqEu92Fr1MmgWxPKTM1K9nz.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/roboto/v19/KFOiCnqEu92Fr1Mu51QrIzcXLsnzjYk.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/roboto/v19/KFOlCnqEu92Fr1MmSU5vAx05IsDqlA.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/roboto/v19/KFOjCnqEu92Fr1Mu51TjARc9AMX6lJBP.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/roboto/v19/KFOmCnqEu92Fr1Me5WZLCzYlKw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/roboto/v19/KFOkCnqEu92Fr1Mu52xPKTM1K9nz.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/roboto/v19/KFOlCnqEu92Fr1MmEU9vAx05IsDqlA.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/roboto/v19/KFOjCnqEu92Fr1Mu51S7ABc9AMX6lJBP.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/roboto/v19/KFOlCnqEu92Fr1MmWUlvAx05IsDqlA.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/roboto/v19/KFOjCnqEu92Fr1Mu51TzBhc9AMX6lJBP.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/roboto/v19/KFOlCnqEu92Fr1MmYUtvAx05IsDqlA.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/roboto/v19/KFOjCnqEu92Fr1Mu51TLBBc9AMX6lJBP.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Roboto Condensed` extends GoogleFont {
    override lazy val family: String = "Roboto Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/robotocondensed/v17/ieVi2ZhZI2eCN5jzbjEETS9weq8-33mZKCMSbvtdYyQ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/robotocondensed/v17/ieVg2ZhZI2eCN5jzbjEETS9weq8-19eDpCEYatlYcyRi4A.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/robotocondensed/v17/ieVl2ZhZI2eCN5jzbjEETS9weq8-59WxDCs5cvI.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/robotocondensed/v17/ieVj2ZhZI2eCN5jzbjEETS9weq8-19e7CAk8YvJEeg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/robotocondensed/v17/ieVi2ZhZI2eCN5jzbjEETS9weq8-32meKCMSbvtdYyQ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/robotocondensed/v17/ieVg2ZhZI2eCN5jzbjEETS9weq8-19eDtCYYatlYcyRi4A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `700`, `700italic`)
  }
  object `Roboto Mono` extends GoogleFont {
    override lazy val family: String = "Roboto Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/robotomono/v6/L0x7DF4xlVMF-BfR8bXMIjAoq3qcW7KCG1w.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/robotomono/v6/L0xlDF4xlVMF-BfR8bXMIjhOkx6WX5CHC1wnFw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/robotomono/v6/L0xkDF4xlVMF-BfR8bXMIjDgiVq2db6LAkU-.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/robotomono/v6/L0xmDF4xlVMF-BfR8bXMIjhOk9a0f7qpB1U-Drg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/robotomono/v6/L0x5DF4xlVMF-BfR8bXMIghMoX6-XqKC.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/robotomono/v6/L0x7DF4xlVMF-BfR8bXMIjhOq3qcW7KCG1w.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/robotomono/v6/L0xkDF4xlVMF-BfR8bXMIjC4iFq2db6LAkU-.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/robotomono/v6/L0xmDF4xlVMF-BfR8bXMIjhOk461f7qpB1U-Drg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/robotomono/v6/L0xkDF4xlVMF-BfR8bXMIjDwjlq2db6LAkU-.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/robotomono/v6/L0xmDF4xlVMF-BfR8bXMIjhOk8azf7qpB1U-Drg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`)
  }
  object `Roboto Slab` extends GoogleFont {
    override lazy val family: String = "Roboto Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/robotoslab/v8/BngOUXZYTXPIvIBgJJSb6u-u5qCr5RCDY_k.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/robotoslab/v8/BngRUXZYTXPIvIBgJJSb6u9mxICByxyKeuDp.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/robotoslab/v8/BngMUXZYTXPIvIBgJJSb6tfK7KSJ4ACD.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/robotoslab/v8/BngRUXZYTXPIvIBgJJSb6u92w4CByxyKeuDp.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `700`)
  }
  object `Rochester` extends GoogleFont {
    override lazy val family: String = "Rochester"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rochester/v9/6ae-4KCqVa4Zy6Fif-Uy31vWNTMwoQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rock Salt` extends GoogleFont {
    override lazy val family: String = "Rock Salt"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rocksalt/v9/MwQ0bhv11fWD6QsAVOZbsEk7hbBWrA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rokkitt` extends GoogleFont {
    override lazy val family: String = "Rokkitt"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/rokkitt/v13/qFdG35qfgYFjGy5hmCWCc_TOyh4Qig.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/rokkitt/v13/qFdB35qfgYFjGy5hmImjU97gxhcJk1s.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/rokkitt/v13/qFdB35qfgYFjGy5hmO2gU97gxhcJk1s.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rokkitt/v13/qFdE35qfgYFjGy5hoEGId9bL2h4.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/rokkitt/v13/qFdB35qfgYFjGy5hmLWhU97gxhcJk1s.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/rokkitt/v13/qFdB35qfgYFjGy5hmJmmU97gxhcJk1s.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rokkitt/v13/qFdB35qfgYFjGy5hmP2nU97gxhcJk1s.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/rokkitt/v13/qFdB35qfgYFjGy5hmOGkU97gxhcJk1s.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/rokkitt/v13/qFdB35qfgYFjGy5hmMWlU97gxhcJk1s.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Romanesco` extends GoogleFont {
    override lazy val family: String = "Romanesco"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/romanesco/v7/w8gYH2ozQOY7_r_J7mSn3HwLqOqSBg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ropa Sans` extends GoogleFont {
    override lazy val family: String = "Ropa Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ropasans/v8/EYqxmaNOzLlWtsZSScyKWjloU5KP2g.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ropasans/v8/EYq3maNOzLlWtsZSScy6WDNscZef2mNE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Rosario` extends GoogleFont {
    override lazy val family: String = "Rosario"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rosario/v13/xfux0WDhWW_fOEoY6FT3zA7DpL4.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/rosario/v13/xfuz0WDhWW_fOEoY2Fb9yCzGtL7CMg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rosario/v13/xfu00WDhWW_fOEoY0OjY6AbouLfbK64.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/rosario/v13/xfu20WDhWW_fOEoY2FbFdAPivJXeO67ISw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Rosarivo` extends GoogleFont {
    override lazy val family: String = "Rosarivo"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rosarivo/v6/PlI-Fl2lO6N9f8HaNAeC2nhMnNy5.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/rosarivo/v6/PlI4Fl2lO6N9f8HaNDeA0Hxumcy5ZX8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Rouge Script` extends GoogleFont {
    override lazy val family: String = "Rouge Script"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rougescript/v7/LYjFdGbiklMoCIQOw1Ep3S4PVPXbUJWq9g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rozha One` extends GoogleFont {
    override lazy val family: String = "Rozha One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rozhaone/v6/AlZy_zVFtYP12Zncg2khdXf4XB0Tow.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rubik` extends GoogleFont {
    override lazy val family: String = "Rubik"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/rubik/v8/iJWHBXyIfDnIV7Fqj1ma-2HW7ZB_.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/rubik/v8/iJWBBXyIfDnIV7nEldWY8WX06IB_18o.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rubik/v8/iJWKBXyIfDnIV4nGp32S0H3f.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/rubik/v8/iJWEBXyIfDnIV7nErXmw1W3f9Ik.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/rubik/v8/iJWHBXyIfDnIV7Eyjlma-2HW7ZB_.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/rubik/v8/iJWBBXyIfDnIV7nElY2Z8WX06IB_18o.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rubik/v8/iJWHBXyIfDnIV7F6iFma-2HW7ZB_.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/rubik/v8/iJWBBXyIfDnIV7nElcWf8WX06IB_18o.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/rubik/v8/iJWHBXyIfDnIV7FCilma-2HW7ZB_.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/rubik/v8/iJWBBXyIfDnIV7nElf2d8WX06IB_18o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Rubik Mono One` extends GoogleFont {
    override lazy val family: String = "Rubik Mono One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rubikmonoone/v7/UqyJK8kPP3hjw6ANTdfRk9YSN-8wRqQrc_j9.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ruda` extends GoogleFont {
    override lazy val family: String = "Ruda"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ruda/v10/k3kfo8YQJOpFmn8XadbJM0A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ruda/v10/k3kQo8YQJOpFosM4Td7iL0nAMaM.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/ruda/v10/k3kQo8YQJOpFovs6Td7iL0nAMaM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `900`)
  }
  object `Rufina` extends GoogleFont {
    override lazy val family: String = "Rufina"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rufina/v6/Yq6V-LyURyLy-aKyoxRktOdClg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/rufina/v6/Yq6W-LyURyLy-aKKHztAvMxenxE0SA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Ruge Boogie` extends GoogleFont {
    override lazy val family: String = "Ruge Boogie"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rugeboogie/v9/JIA3UVFwbHRF_GIWSMhKNROiPzUveSxy.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ruluko` extends GoogleFont {
    override lazy val family: String = "Ruluko"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ruluko/v6/xMQVuFNZVaODtm0pC6WzKX_QmA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rum Raisin` extends GoogleFont {
    override lazy val family: String = "Rum Raisin"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rumraisin/v6/nwpRtKu3Ih8D5avB4h2uJ3-IywA7eMM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ruslan Display` extends GoogleFont {
    override lazy val family: String = "Ruslan Display"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ruslandisplay/v9/Gw6jwczl81XcIZuckK_e3UpfdzxrldyFvm1n.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Russo One` extends GoogleFont {
    override lazy val family: String = "Russo One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/russoone/v7/Z9XUDmZRWg6M1LvRYsH-yMOInrib9Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ruthie` extends GoogleFont {
    override lazy val family: String = "Ruthie"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ruthie/v9/gokvH63sGkdqXuU9lD53Q2u_mQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Rye` extends GoogleFont {
    override lazy val family: String = "Rye"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/rye/v6/r05XGLJT86YDFpTsXOqx4w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sacramento` extends GoogleFont {
    override lazy val family: String = "Sacramento"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sacramento/v6/buEzpo6gcdjy0EiZMBUG0CoV_NxLeiw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sahitya` extends GoogleFont {
    override lazy val family: String = "Sahitya"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sahitya/v3/6qLAKZkOuhnuqlJAaScFPywEDnI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sahitya/v3/6qLFKZkOuhnuqlJAUZsqGyQvEnvSexI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Sail` extends GoogleFont {
    override lazy val family: String = "Sail"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sail/v9/DPEjYwiBxwYJFBTDADYAbvw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Saira` extends GoogleFont {
    override lazy val family: String = "Saira"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/saira/v3/mem-Ya2wxmKQyNFETZY_VrUfTck.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/saira/v3/mem9Ya2wxmKQyNHobLYVeLkWVNBt.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/saira/v3/mem9Ya2wxmKQyNGMb7YVeLkWVNBt.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/saira/v3/memwYa2wxmKQyOkgR5IdU6Uf.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/saira/v3/mem9Ya2wxmKQyNHUbrYVeLkWVNBt.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/saira/v3/mem9Ya2wxmKQyNH4abYVeLkWVNBt.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/saira/v3/mem9Ya2wxmKQyNGcaLYVeLkWVNBt.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/saira/v3/mem9Ya2wxmKQyNGAa7YVeLkWVNBt.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/saira/v3/mem9Ya2wxmKQyNGkarYVeLkWVNBt.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Saira Condensed` extends GoogleFont {
    override lazy val family: String = "Saira Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/sairacondensed/v4/EJRMQgErUN8XuHNEtX81i9TmEkrnwetA2omSrzS8.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sairacondensed/v4/EJRLQgErUN8XuHNEtX81i9TmEkrnbcpg8Keepi2lHw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sairacondensed/v4/EJRLQgErUN8XuHNEtX81i9TmEkrnCclg8Keepi2lHw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sairacondensed/v4/EJROQgErUN8XuHNEtX81i9TmEkrfpeFE-IyCrw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sairacondensed/v4/EJRLQgErUN8XuHNEtX81i9TmEkrnUchg8Keepi2lHw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sairacondensed/v4/EJRLQgErUN8XuHNEtX81i9TmEkrnfc9g8Keepi2lHw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sairacondensed/v4/EJRLQgErUN8XuHNEtX81i9TmEkrnGc5g8Keepi2lHw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sairacondensed/v4/EJRLQgErUN8XuHNEtX81i9TmEkrnBc1g8Keepi2lHw.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sairacondensed/v4/EJRLQgErUN8XuHNEtX81i9TmEkrnIcxg8Keepi2lHw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Saira Extra Condensed` extends GoogleFont {
    override lazy val family: String = "Saira Extra Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/sairaextracondensed/v4/-nFsOHYr-vcC7h8MklGBkrvmUG9rbpkisrTri0jx9i5ss3a3.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sairaextracondensed/v4/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrJ2nR3ABgum-uoQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sairaextracondensed/v4/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrQ2rR3ABgum-uoQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sairaextracondensed/v4/-nFiOHYr-vcC7h8MklGBkrvmUG9rbpkisrTT70L11Ct8sw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sairaextracondensed/v4/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrG2vR3ABgum-uoQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sairaextracondensed/v4/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrN2zR3ABgum-uoQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sairaextracondensed/v4/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrU23R3ABgum-uoQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sairaextracondensed/v4/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTrT27R3ABgum-uoQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sairaextracondensed/v4/-nFvOHYr-vcC7h8MklGBkrvmUG9rbpkisrTra2_R3ABgum-uoQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Saira Semi Condensed` extends GoogleFont {
    override lazy val family: String = "Saira Semi Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/sairasemicondensed/v4/U9MN6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXdvaOM8rXT-8V8.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sairasemicondensed/v4/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXfDScMWg3j36Ebz.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sairasemicondensed/v4/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXenSsMWg3j36Ebz.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sairasemicondensed/v4/U9MD6c-2-nnJkHxyCjRcnMHcWVWV1cWRRU8LYuceqGT-.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sairasemicondensed/v4/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXf_S8MWg3j36Ebz.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sairasemicondensed/v4/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXfTTMMWg3j36Ebz.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sairasemicondensed/v4/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXe3TcMWg3j36Ebz.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sairasemicondensed/v4/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXerTsMWg3j36Ebz.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sairasemicondensed/v4/U9MM6c-2-nnJkHxyCjRcnMHcWVWV1cWRRXePT8MWg3j36Ebz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Salsa` extends GoogleFont {
    override lazy val family: String = "Salsa"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/salsa/v8/gNMKW3FiRpKj-imY8ncKEZez.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sanchez` extends GoogleFont {
    override lazy val family: String = "Sanchez"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sanchez/v6/Ycm2sZJORluHnXbITm5b_BwE1l0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/sanchez/v6/Ycm0sZJORluHnXbIfmxR-D4Bxl3gkw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Sancreek` extends GoogleFont {
    override lazy val family: String = "Sancreek"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sancreek/v9/pxiHypAnsdxUm159X7D-XV9NEe-K.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sansita` extends GoogleFont {
    override lazy val family: String = "Sansita"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sansita/v3/QldONTRRphEb_-V7HBm7TXFf3qw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/sansita/v3/QldMNTRRphEb_-V7LBuxSVNazqx2xg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sansita/v3/QldLNTRRphEb_-V7JKWUaXl0wqVv3_g.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/sansita/v3/QldJNTRRphEb_-V7LBuJ9Xx-xodqz_joDQ.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sansita/v3/QldLNTRRphEb_-V7JLmXaXl0wqVv3_g.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/sansita/v3/QldJNTRRphEb_-V7LBuJ6X9-xodqz_joDQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sansita/v3/QldLNTRRphEb_-V7JJ2WaXl0wqVv3_g.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/sansita/v3/QldJNTRRphEb_-V7LBuJzX5-xodqz_joDQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Sarabun` extends GoogleFont {
    override lazy val family: String = "Sarabun"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/sarabun/v6/DtVhJx26TKEr37c9YHZJmnYI5gnOpg.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/sarabun/v6/DtVnJx26TKEr37c9aBBx_nwMxAzephhN.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sarabun/v6/DtVmJx26TKEr37c9YNpoulwm6gDXvwE.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/sarabun/v6/DtVkJx26TKEr37c9aBBxUl0s7iLSrwFUlw.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sarabun/v6/DtVmJx26TKEr37c9YL5rulwm6gDXvwE.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/sarabun/v6/DtVkJx26TKEr37c9aBBxNl4s7iLSrwFUlw.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sarabun/v6/DtVjJx26TKEr37c9WBJDnlQN9gk.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/sarabun/v6/DtVhJx26TKEr37c9aBBJmnYI5gnOpg.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sarabun/v6/DtVmJx26TKEr37c9YOZqulwm6gDXvwE.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/sarabun/v6/DtVkJx26TKEr37c9aBBxbl8s7iLSrwFUlw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sarabun/v6/DtVmJx26TKEr37c9YMptulwm6gDXvwE.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/sarabun/v6/DtVkJx26TKEr37c9aBBxQlgs7iLSrwFUlw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sarabun/v6/DtVmJx26TKEr37c9YK5sulwm6gDXvwE.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/sarabun/v6/DtVkJx26TKEr37c9aBBxJlks7iLSrwFUlw.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sarabun/v6/DtVmJx26TKEr37c9YLJvulwm6gDXvwE.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/sarabun/v6/DtVkJx26TKEr37c9aBBxOlos7iLSrwFUlw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Sarala` extends GoogleFont {
    override lazy val family: String = "Sarala"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sarala/v3/uK_y4riEZv4o1w9RCh0TMv6EXw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sarala/v3/uK_x4riEZv4o1w9ptjI3OtWYVkMpXA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Sarina` extends GoogleFont {
    override lazy val family: String = "Sarina"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sarina/v7/-F6wfjF3ITQwasLhLkDUriBQxw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sarpanch` extends GoogleFont {
    override lazy val family: String = "Sarpanch"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sarpanch/v4/hESy6Xt4NCpRuk6Pzh2ARIrX_20n.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sarpanch/v4/hES16Xt4NCpRuk6PziV0ba7f1HEuRHkM.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sarpanch/v4/hES16Xt4NCpRuk6PziVYaq7f1HEuRHkM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sarpanch/v4/hES16Xt4NCpRuk6PziU8a67f1HEuRHkM.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sarpanch/v4/hES16Xt4NCpRuk6PziUgaK7f1HEuRHkM.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sarpanch/v4/hES16Xt4NCpRuk6PziUEaa7f1HEuRHkM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Satisfy` extends GoogleFont {
    override lazy val family: String = "Satisfy"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/satisfy/v9/rP2Hp2yn6lkG50LoOZSCHBeHFl0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sawarabi Gothic` extends GoogleFont {
    override lazy val family: String = "Sawarabi Gothic"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `japanese`: GoogleFontSubset = new GoogleFontSubset("japanese")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`japanese`, `latin-ext`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sawarabigothic/v7/x3d4ckfVaqqa-BEj-I9mE65u3k3NBSk3E2YljQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sawarabi Mincho` extends GoogleFont {
    override lazy val family: String = "Sawarabi Mincho"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `japanese`: GoogleFontSubset = new GoogleFontSubset("japanese")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`japanese`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sawarabimincho/v9/8QIRdiDaitzr7brc8ahpxt6GcIJTLahP46UDUw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Scada` extends GoogleFont {
    override lazy val family: String = "Scada"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/scada/v7/RLpxK5Pv5qumeWJoxzUobkvv.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/scada/v7/RLp_K5Pv5qumeVJqzTEKa1vvffg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/scada/v7/RLp8K5Pv5qumeVrU6BEgRVfmZOE5.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/scada/v7/RLp6K5Pv5qumeVJq9Y0lT1PEYfE5p6g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Scheherazade` extends GoogleFont {
    override lazy val family: String = "Scheherazade"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/scheherazade/v16/YA9Ur0yF4ETZN60keViq1kQgt5OohvbJ9A.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/scheherazade/v16/YA9Lr0yF4ETZN60keViq1kQYC7yMjt3V_dB0Yw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Schoolbell` extends GoogleFont {
    override lazy val family: String = "Schoolbell"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/schoolbell/v9/92zQtBZWOrcgoe-fgnJIVxIQ6mRqfiQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Scope One` extends GoogleFont {
    override lazy val family: String = "Scope One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/scopeone/v5/WBLnrEXKYFlGHrOKmGD1W0_MJMGxiQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Seaweed Script` extends GoogleFont {
    override lazy val family: String = "Seaweed Script"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/seaweedscript/v6/bx6cNx6Tne2pxOATYE8C_Rsoe0WJ-KcGVbLW.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Secular One` extends GoogleFont {
    override lazy val family: String = "Secular One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/secularone/v3/8QINdiTajsj_87rMuMdKypDlMul7LJpK.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sedgwick Ave` extends GoogleFont {
    override lazy val family: String = "Sedgwick Ave"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sedgwickave/v4/uK_04rKEYuguzAcSYRdWTJq8Xmg1Vcf5JA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sedgwick Ave Display` extends GoogleFont {
    override lazy val family: String = "Sedgwick Ave Display"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sedgwickavedisplay/v4/xfuu0XPgU3jZPUoUo3ScvmPi-NapQ8OxM2czd-YnOzUD.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sevillana` extends GoogleFont {
    override lazy val family: String = "Sevillana"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sevillana/v7/KFOlCnWFscmDt1Bfiy1vAx05IsDqlA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Seymour One` extends GoogleFont {
    override lazy val family: String = "Seymour One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/seymourone/v6/4iCp6Khla9xbjQpoWGGd0myIPYBvgpUI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Shadows Into Light` extends GoogleFont {
    override lazy val family: String = "Shadows Into Light"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shadowsintolight/v8/UqyNK9UOIntux_czAvDQx_ZcHqZXBNQDcsr4xzSMYA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Shadows Into Light Two` extends GoogleFont {
    override lazy val family: String = "Shadows Into Light Two"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shadowsintolighttwo/v6/4iC86LVlZsRSjQhpWGedwyOoW-0A6_kpsyNmlAvNGLNnIF0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Shanti` extends GoogleFont {
    override lazy val family: String = "Shanti"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shanti/v10/t5thIREMM4uSDgzgU0ezpKfwzA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Share` extends GoogleFont {
    override lazy val family: String = "Share"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/share/v9/i7dEIFliZjKNF5VNHLq2cV5d.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/share/v9/i7dKIFliZjKNF6VPFr6UdE5dWFM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/share/v9/i7dJIFliZjKNF63xM56-WkJUQUq7.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/share/v9/i7dPIFliZjKNF6VPLgK7UEZ2RFq7AwU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Share Tech` extends GoogleFont {
    override lazy val family: String = "Share Tech"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sharetech/v8/7cHtv4Uyi5K0OeZ7bohUwHoDmTcibrA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Share Tech Mono` extends GoogleFont {
    override lazy val family: String = "Share Tech Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sharetechmono/v8/J7aHnp1uDWRBEqV98dVQztYldFc7pAsEIc3Xew.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Shojumaru` extends GoogleFont {
    override lazy val family: String = "Shojumaru"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shojumaru/v6/rax_HiWfutkLLnaKCtlMBBJek0vA8A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Short Stack` extends GoogleFont {
    override lazy val family: String = "Short Stack"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shortstack/v8/bMrzmS2X6p0jZC6EcmPFX-SScX8D0nq6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Shrikhand` extends GoogleFont {
    override lazy val family: String = "Shrikhand"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")
      lazy val `gujarati`: GoogleFontSubset = new GoogleFontSubset("gujarati")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`, `gujarati`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/shrikhand/v4/a8IbNovtLWfR7T7bMJwbBIiQ0zhMtA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Siemreap` extends GoogleFont {
    override lazy val family: String = "Siemreap"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/siemreap/v11/Gg82N5oFbgLvHAfNl2YbnA8DLXpe.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sigmar One` extends GoogleFont {
    override lazy val family: String = "Sigmar One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sigmarone/v9/co3DmWZ8kjZuErj9Ta3dk6Pjp3Di8U0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Signika` extends GoogleFont {
    override lazy val family: String = "Signika"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/signika/v9/vEFU2_JTCgwQ5ejvE_oEI3BDa0AdytM.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/signika/v9/vEFR2_JTCgwQ5ejvK1YsB3hod0k.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/signika/v9/vEFU2_JTCgwQ5ejvE44CI3BDa0AdytM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/signika/v9/vEFU2_JTCgwQ5ejvE-oDI3BDa0AdytM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `600`, `700`)
  }
  object `Signika Negative` extends GoogleFont {
    override lazy val family: String = "Signika Negative"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/signikanegative/v9/E217_cfngu7HiRpPX3ZpNE4kY5zKal6DipHD6z_iXAs.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/signikanegative/v9/E218_cfngu7HiRpPX3ZpNE4kY5zKUvKrrpno9zY.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/signikanegative/v9/E217_cfngu7HiRpPX3ZpNE4kY5zKaiqFipHD6z_iXAs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/signikanegative/v9/E217_cfngu7HiRpPX3ZpNE4kY5zKak6EipHD6z_iXAs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `600`, `700`)
  }
  object `Simonetta` extends GoogleFont {
    override lazy val family: String = "Simonetta"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/simonetta/v8/x3dickHVYrCU5BU15c4BfPACvy_1BA.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/simonetta/v8/x3dkckHVYrCU5BU15c4xfvoGnSrlBBsy.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/simonetta/v8/x3dnckHVYrCU5BU15c45-N0mtwTpDQIrGg.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/simonetta/v8/x3d5ckHVYrCU5BU15c4xfsKCsA7tLwc7Gn88.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `900`, `900italic`)
  }
  object `Sintony` extends GoogleFont {
    override lazy val family: String = "Sintony"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sintony/v6/XoHm2YDqR7-98cVUITQnu98ojjs.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sintony/v6/XoHj2YDqR7-98cVUGYgIn9cDkjLp6C8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Sirin Stencil` extends GoogleFont {
    override lazy val family: String = "Sirin Stencil"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sirinstencil/v7/mem4YaWwznmLx-lzGfN7MdRydchGBq6al6o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Six Caps` extends GoogleFont {
    override lazy val family: String = "Six Caps"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sixcaps/v9/6ae_4KGrU7VR7bNmabcS9XXaPCop.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Skranji` extends GoogleFont {
    override lazy val family: String = "Skranji"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/skranji/v6/OZpDg_dtriVFNerMYzuuklTm3Ek.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/skranji/v6/OZpGg_dtriVFNerMW4eBtlzNwED-b4g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Slabo 13px` extends GoogleFont {
    override lazy val family: String = "Slabo 13px"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/slabo13px/v6/11hEGp_azEvXZUdSBzzRcKer2wkYnvI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Slabo 27px` extends GoogleFont {
    override lazy val family: String = "Slabo 27px"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/slabo27px/v5/mFT0WbgBwKPR_Z4hGN2qsxgJ1EJ7i90.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Slackey` extends GoogleFont {
    override lazy val family: String = "Slackey"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/slackey/v9/N0bV2SdQO-5yM0-dKlRaJdbWgdY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Smokum` extends GoogleFont {
    override lazy val family: String = "Smokum"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/smokum/v9/TK3iWkUbAhopmrdGHjUHte5fKg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Smythe` extends GoogleFont {
    override lazy val family: String = "Smythe"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/smythe/v9/MwQ3bhT01--coT1BOLh_uGInjA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sniglet` extends GoogleFont {
    override lazy val family: String = "Sniglet"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sniglet/v10/cIf9MaFLtkE3UjaJxCmrYGkHgIs.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/sniglet/v10/cIf4MaFLtkE3UjaJ_ImHRGEsnIJkWL4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `800`)
  }
  object `Snippet` extends GoogleFont {
    override lazy val family: String = "Snippet"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/snippet/v8/bWt47f7XfQH9Gupu2v_Afcp9QWc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Snowburst One` extends GoogleFont {
    override lazy val family: String = "Snowburst One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/snowburstone/v6/MQpS-WezKdujBsXY3B7I-UT7eZ-UPyacPbo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sofadi One` extends GoogleFont {
    override lazy val family: String = "Sofadi One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sofadione/v7/JIA2UVBxdnVBuElZaMFGcDOIETkmYDU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sofia` extends GoogleFont {
    override lazy val family: String = "Sofia"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sofia/v7/8QIHdirahM3j_vu-sowsrqjk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Song Myung` extends GoogleFont {
    override lazy val family: String = "Song Myung"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/songmyung/v7/1cX2aUDWAJH5-EIC7DIhr1GqhcitzeM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sonsie One` extends GoogleFont {
    override lazy val family: String = "Sonsie One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sonsieone/v7/PbymFmP_EAnPqbKaoc18YVu80lbp8JM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sorts Mill Goudy` extends GoogleFont {
    override lazy val family: String = "Sorts Mill Goudy"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sortsmillgoudy/v8/Qw3GZR9MED_6PSuS_50nEaVrfzgEXH0OjpM75PE.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/sortsmillgoudy/v8/Qw3AZR9MED_6PSuS_50nEaVrfzgEbH8EirE-9PGLfQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`)
  }
  object `Source Code Pro` extends GoogleFont {
    override lazy val family: String = "Source Code Pro"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sourcecodepro/v9/HI_XiYsKILxRpg3hIP6sJ7fM7Pqt8srztO0rzmmkDQ.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sourcecodepro/v9/HI_XiYsKILxRpg3hIP6sJ7fM7PqtlsnztO0rzmmkDQ.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sourcecodepro/v9/HI_SiYsKILxRpg3hIP6sJ7fM7PqVOuHXvMY3xw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sourcecodepro/v9/HI_XiYsKILxRpg3hIP6sJ7fM7PqtzsjztO0rzmmkDQ.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sourcecodepro/v9/HI_XiYsKILxRpg3hIP6sJ7fM7Pqt4s_ztO0rzmmkDQ.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sourcecodepro/v9/HI_XiYsKILxRpg3hIP6sJ7fM7Pqths7ztO0rzmmkDQ.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sourcecodepro/v9/HI_XiYsKILxRpg3hIP6sJ7fM7PqtvszztO0rzmmkDQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `600`, `700`, `900`)
  }
  object `Source Sans Pro` extends GoogleFont {
    override lazy val family: String = "Source Sans Pro"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/sourcesanspro/v12/6xKydSBYKcSV-LCoeQqfX1RYOo3i94_AkB1v_8CGxg.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/sourcesanspro/v12/6xKwdSBYKcSV-LCoeQqfX1RYOo3qPZYokRdr3cWWxg40.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sourcesanspro/v12/6xKydSBYKcSV-LCoeQqfX1RYOo3ik4zAkB1v_8CGxg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/sourcesanspro/v12/6xKwdSBYKcSV-LCoeQqfX1RYOo3qPZZMkhdr3cWWxg40.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sourcesanspro/v12/6xK3dSBYKcSV-LCoeQqfX1RYOo3aP6TkmDZz9g.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/sourcesanspro/v12/6xK1dSBYKcSV-LCoeQqfX1RYOo3qPa7gujNj9tmf.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sourcesanspro/v12/6xKydSBYKcSV-LCoeQqfX1RYOo3i54rAkB1v_8CGxg.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/sourcesanspro/v12/6xKwdSBYKcSV-LCoeQqfX1RYOo3qPZY4lBdr3cWWxg40.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sourcesanspro/v12/6xKydSBYKcSV-LCoeQqfX1RYOo3ig4vAkB1v_8CGxg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/sourcesanspro/v12/6xKwdSBYKcSV-LCoeQqfX1RYOo3qPZZclRdr3cWWxg40.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/sourcesanspro/v12/6xKydSBYKcSV-LCoeQqfX1RYOo3iu4nAkB1v_8CGxg.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/sourcesanspro/v12/6xKwdSBYKcSV-LCoeQqfX1RYOo3qPZZklxdr3cWWxg40.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Source Serif Pro` extends GoogleFont {
    override lazy val family: String = "Source Serif Pro"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sourceserifpro/v6/neIQzD-0qpwxpaWvjeD0X88SAOeaiXM0oSOL2Yw.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/sourceserifpro/v6/neIXzD-0qpwxpaWvjeD0X88SAOeasasahSugxYUvZrI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sourceserifpro/v6/neIXzD-0qpwxpaWvjeD0X88SAOeasc8bhSugxYUvZrI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `600`, `700`)
  }
  object `Space Mono` extends GoogleFont {
    override lazy val family: String = "Space Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spacemono/v4/i7dPIFZifjKcF5UAWdDRUEZ2RFq7AwU.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/spacemono/v4/i7dNIFZifjKcF5UAWdDRYER8QHi-EwWMbg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/spacemono/v4/i7dMIFZifjKcF5UAWdDRaPpZYFKQHwyVd3U.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/spacemono/v4/i7dSIFZifjKcF5UAWdDRYERE_FeaGy6QZ3WfYg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Special Elite` extends GoogleFont {
    override lazy val family: String = "Special Elite"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/specialelite/v9/XLYgIZbkc4JPUL5CVArUVL0nhncESXFtUsM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Spectral` extends GoogleFont {
    override lazy val family: String = "Spectral"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/spectral/v5/rnCs-xNNww_2s0amA9v2s13GY_etWWIJ.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/spectral/v5/rnCu-xNNww_2s0amA9M8qrXHafOPXHIJErY.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/spectral/v5/rnCs-xNNww_2s0amA9uSsF3GY_etWWIJ.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/spectral/v5/rnCu-xNNww_2s0amA9M8qtHEafOPXHIJErY.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spectral/v5/rnCr-xNNww_2s0amA-M-mHnOSOuk.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/spectral/v5/rnCt-xNNww_2s0amA9M8kn3sTfukQHs.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/spectral/v5/rnCs-xNNww_2s0amA9vKsV3GY_etWWIJ.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/spectral/v5/rnCu-xNNww_2s0amA9M8qonFafOPXHIJErY.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/spectral/v5/rnCs-xNNww_2s0amA9vmtl3GY_etWWIJ.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/spectral/v5/rnCu-xNNww_2s0amA9M8qqXCafOPXHIJErY.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/spectral/v5/rnCs-xNNww_2s0amA9uCt13GY_etWWIJ.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/spectral/v5/rnCu-xNNww_2s0amA9M8qsHDafOPXHIJErY.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/spectral/v5/rnCs-xNNww_2s0amA9uetF3GY_etWWIJ.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/spectral/v5/rnCu-xNNww_2s0amA9M8qt3AafOPXHIJErY.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Spectral SC` extends GoogleFont {
    override lazy val family: String = "Spectral SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/spectralsc/v4/Ktk0ALCRZonmalTgyPmRfs1qwkTXPYeVXJZB.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/spectralsc/v4/Ktk2ALCRZonmalTgyPmRfsWg26zWN4O3WYZB_sU.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/spectralsc/v4/Ktk0ALCRZonmalTgyPmRfs0OwUTXPYeVXJZB.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/spectralsc/v4/Ktk2ALCRZonmalTgyPmRfsWg28jVN4O3WYZB_sU.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spectralsc/v4/KtkpALCRZonmalTgyPmRfvWi6WDfFpuc.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/spectralsc/v4/KtkrALCRZonmalTgyPmRfsWg42T9E4ucRY8.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/spectralsc/v4/Ktk0ALCRZonmalTgyPmRfs1WwETXPYeVXJZB.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/spectralsc/v4/Ktk2ALCRZonmalTgyPmRfsWg25DUN4O3WYZB_sU.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/spectralsc/v4/Ktk0ALCRZonmalTgyPmRfs16x0TXPYeVXJZB.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/spectralsc/v4/Ktk2ALCRZonmalTgyPmRfsWg27zTN4O3WYZB_sU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/spectralsc/v4/Ktk0ALCRZonmalTgyPmRfs0exkTXPYeVXJZB.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/spectralsc/v4/Ktk2ALCRZonmalTgyPmRfsWg29jSN4O3WYZB_sU.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/spectralsc/v4/Ktk0ALCRZonmalTgyPmRfs0CxUTXPYeVXJZB.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/spectralsc/v4/Ktk2ALCRZonmalTgyPmRfsWg28TRN4O3WYZB_sU.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`)
  }
  object `Spicy Rice` extends GoogleFont {
    override lazy val family: String = "Spicy Rice"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spicyrice/v7/uK_24rSEd-Uqwk4jY1RyGv-2WkowRcc.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Spinnaker` extends GoogleFont {
    override lazy val family: String = "Spinnaker"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spinnaker/v10/w8gYH2oyX-I0_rvR6Hmn3HwLqOqSBg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Spirax` extends GoogleFont {
    override lazy val family: String = "Spirax"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/spirax/v7/buE3poKgYNLy0F3cXktt-Csn-Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Squada One` extends GoogleFont {
    override lazy val family: String = "Squada One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/squadaone/v7/BCasqZ8XsOrx4mcOk6MtWaA8WDBkHgs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sree Krushnadevaraya` extends GoogleFont {
    override lazy val family: String = "Sree Krushnadevaraya"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sreekrushnadevaraya/v6/R70FjzQeifmPepmyQQjQ9kvwMkWYPfTA_EWb2FhQuXir.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sriracha` extends GoogleFont {
    override lazy val family: String = "Sriracha"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sriracha/v3/0nkrC9D4IuYBgWcI9ObYRQDioeb0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Srisakdi` extends GoogleFont {
    override lazy val family: String = "Srisakdi"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/srisakdi/v2/yMJRMIlvdpDbkB0A-jq8fSx5i814.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/srisakdi/v2/yMJWMIlvdpDbkB0A-gIAUghxoNFxW0Hz.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Staatliches` extends GoogleFont {
    override lazy val family: String = "Staatliches"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/staatliches/v2/HI_OiY8KO6hCsQSoAPmtMbectJG9O9PS.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stalemate` extends GoogleFont {
    override lazy val family: String = "Stalemate"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stalemate/v6/taiIGmZ_EJq97-UfkZRpuqSs8ZQpaQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stalinist One` extends GoogleFont {
    override lazy val family: String = "Stalinist One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stalinistone/v20/MQpS-WezM9W4Dd7D3B7I-UT7eZ-UPyacPbo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stardos Stencil` extends GoogleFont {
    override lazy val family: String = "Stardos Stencil"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stardosstencil/v8/X7n94bcuGPC8hrvEOHXOgaKCc2TR71R3tiSx0g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/stardosstencil/v8/X7n44bcuGPC8hrvEOHXOgaKCc2TpU3tTvg-t29HSHw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Stint Ultra Condensed` extends GoogleFont {
    override lazy val family: String = "Stint Ultra Condensed"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stintultracondensed/v7/-W_gXIrsVjjeyEnPC45qD2NoFPtBE0xCh2A-qhUO2cNvdg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stint Ultra Expanded` extends GoogleFont {
    override lazy val family: String = "Stint Ultra Expanded"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stintultraexpanded/v6/CSRg4yNNh-GbW3o3JkwoDcdvMKMf0oBAd0qoATQkWwam.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stoke` extends GoogleFont {
    override lazy val family: String = "Stoke"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/stoke/v8/z7NXdRb7aTMfKNvFVgxC_pjcTeWU.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stoke/v8/z7NadRb7aTMfKONpfihK1YTV.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`)
  }
  object `Strait` extends GoogleFont {
    override lazy val family: String = "Strait"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/strait/v6/DtViJxy6WaEr1LZzeDhtkl0U7w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Stylish` extends GoogleFont {
    override lazy val family: String = "Stylish"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/stylish/v7/m8JSjfhPYriQkk7-fo35dLxEdmo.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sue Ellen Francisco` extends GoogleFont {
    override lazy val family: String = "Sue Ellen Francisco"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sueellenfrancisco/v9/wXK3E20CsoJ9j1DDkjHcQ5ZL8xRaxru9ropF2lqk9H4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Suez One` extends GoogleFont {
    override lazy val family: String = "Suez One"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/suezone/v3/taiJGmd_EZ6rqscQgNFJkIqg-I0w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sumana` extends GoogleFont {
    override lazy val family: String = "Sumana"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sumana/v3/4UaDrE5TqRBjGj-G8Bji76zR4w.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sumana/v3/4UaArE5TqRBjGj--TDfG54fN6ppsKg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Sunflower` extends GoogleFont {
    override lazy val family: String = "Sunflower"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/sunflower/v8/RWmPoKeF8fUjqIj7Vc-06MfiqYsGBGBzCw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/sunflower/v8/RWmPoKeF8fUjqIj7Vc-0sMbiqYsGBGBzCw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sunflower/v8/RWmPoKeF8fUjqIj7Vc-0-MDiqYsGBGBzCw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `500`, `700`)
  }
  object `Sunshiney` extends GoogleFont {
    override lazy val family: String = "Sunshiney"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sunshiney/v9/LDIwapGTLBwsS-wT4vcgE8moUePWkg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Supermercado One` extends GoogleFont {
    override lazy val family: String = "Supermercado One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/supermercadoone/v8/OpNXnpQWg8jc_xps_Gi14kVVEXOn60b3MClBRTs.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Sura` extends GoogleFont {
    override lazy val family: String = "Sura"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/sura/v3/SZc23FL5PbyzFf5UWzXtjUM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/sura/v3/SZc53FL5PbyzLUJ7fz3GkUrS8DI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Suranna` extends GoogleFont {
    override lazy val family: String = "Suranna"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/suranna/v6/gokuH6ztGkFjWe58tBRZT2KmgP0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Suravaram` extends GoogleFont {
    override lazy val family: String = "Suravaram"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/suravaram/v5/_gP61R_usiY7SCym4xIAi261Qv9roQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Suwannaphum` extends GoogleFont {
    override lazy val family: String = "Suwannaphum"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/suwannaphum/v12/jAnCgHV7GtDvc8jbe8hXXIWl_8C0Wg2V.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Swanky and Moo Moo` extends GoogleFont {
    override lazy val family: String = "Swanky and Moo Moo"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/swankyandmoomoo/v8/flUlRrKz24IuWVI_WJYTYcqbEsMUZ3kUtbPkR64SYQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Syncopate` extends GoogleFont {
    override lazy val family: String = "Syncopate"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/syncopate/v10/pe0sMIuPIYBCpEV5eFdyAv2-C99ycg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/syncopate/v10/pe0pMIuPIYBCpEV5eFdKvtKaA_Rue1UwVg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Tajawal` extends GoogleFont {
    override lazy val family: String = "Tajawal"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `arabic`: GoogleFontSubset = new GoogleFontSubset("arabic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`arabic`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/tajawal/v2/Iurf6YBj_oCad4k1l_6gLrZjiLlJ-G0.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/tajawal/v2/Iurf6YBj_oCad4k1l5qjLrZjiLlJ-G0.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tajawal/v2/Iura6YBj_oCad4k1rzaLCr5IlLA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/tajawal/v2/Iurf6YBj_oCad4k1l8KiLrZjiLlJ-G0.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/tajawal/v2/Iurf6YBj_oCad4k1l4qkLrZjiLlJ-G0.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/tajawal/v2/Iurf6YBj_oCad4k1l5anLrZjiLlJ-G0.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/tajawal/v2/Iurf6YBj_oCad4k1l7KmLrZjiLlJ-G0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `500`, `700`, `800`, `900`)
  }
  object `Tangerine` extends GoogleFont {
    override lazy val family: String = "Tangerine"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tangerine/v10/IurY6Y5j_oScZZow4VOBDpxNhLBQ4Q.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/tangerine/v10/Iurd6Y5j_oScZZow4VO5srNpjJtM6G0t9w.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Taprom` extends GoogleFont {
    override lazy val family: String = "Taprom"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `khmer`: GoogleFontSubset = new GoogleFontSubset("khmer")

      override lazy val all: Set[GoogleFontSubset] = Set(`khmer`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/taprom/v10/UcCn3F82JHycULbFQyk3-0kvHg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tauri` extends GoogleFont {
    override lazy val family: String = "Tauri"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tauri/v7/TwMA-IISS0AM3IpVWHU_TBqO.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Taviraj` extends GoogleFont {
    override lazy val family: String = "Taviraj"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/taviraj/v4/ahcbv8Cj3ylylTXzRIorV8N1jU2gog.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/taviraj/v4/ahcdv8Cj3ylylTXzTOwTM8lxr0iwolLl.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/taviraj/v4/ahccv8Cj3ylylTXzRCYKd-lbgUS5u0s.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/taviraj/v4/ahcev8Cj3ylylTXzTOwTn-hRhWa8q0v8ag.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/taviraj/v4/ahccv8Cj3ylylTXzREIJd-lbgUS5u0s.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/taviraj/v4/ahcev8Cj3ylylTXzTOwT--tRhWa8q0v8ag.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/taviraj/v4/ahcZv8Cj3ylylTXzfO4hU-FwnU0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/taviraj/v4/ahcbv8Cj3ylylTXzTOwrV8N1jU2gog.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/taviraj/v4/ahccv8Cj3ylylTXzRBoId-lbgUS5u0s.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/taviraj/v4/ahcev8Cj3ylylTXzTOwTo-pRhWa8q0v8ag.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/taviraj/v4/ahccv8Cj3ylylTXzRDYPd-lbgUS5u0s.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/taviraj/v4/ahcev8Cj3ylylTXzTOwTj-1RhWa8q0v8ag.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/taviraj/v4/ahccv8Cj3ylylTXzRFIOd-lbgUS5u0s.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/taviraj/v4/ahcev8Cj3ylylTXzTOwT6-xRhWa8q0v8ag.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/taviraj/v4/ahccv8Cj3ylylTXzRE4Nd-lbgUS5u0s.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/taviraj/v4/ahcev8Cj3ylylTXzTOwT9-9RhWa8q0v8ag.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/taviraj/v4/ahccv8Cj3ylylTXzRGoMd-lbgUS5u0s.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/taviraj/v4/ahcev8Cj3ylylTXzTOwT0-5RhWa8q0v8ag.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Teko` extends GoogleFont {
    override lazy val family: String = "Teko"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/teko/v8/LYjCdG7kmE0gdQhfgCNqqVIuTN4.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/teko/v8/LYjNdG7kmE0gTaR3pCtBtVs.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/teko/v8/LYjCdG7kmE0gdVBegCNqqVIuTN4.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/teko/v8/LYjCdG7kmE0gdXxZgCNqqVIuTN4.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/teko/v8/LYjCdG7kmE0gdRhYgCNqqVIuTN4.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `Telex` extends GoogleFont {
    override lazy val family: String = "Telex"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/telex/v7/ieVw2Y1fKWmIO9fTB1piKFIf.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tenali Ramakrishna` extends GoogleFont {
    override lazy val family: String = "Tenali Ramakrishna"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tenaliramakrishna/v5/raxgHj6Yt9gAN3LLKs0BZVMo8jmwn1-8KJXqUFFvtA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tenor Sans` extends GoogleFont {
    override lazy val family: String = "Tenor Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tenorsans/v10/bx6ANxqUneKx06UkIXISr3JyC22IyqI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Text Me One` extends GoogleFont {
    override lazy val family: String = "Text Me One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/textmeone/v6/i7dOIFdlayuLUvgoFvHQFWZcalayGhyV.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Thasadith` extends GoogleFont {
    override lazy val family: String = "Thasadith"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/thasadith/v2/mtG44_1TIqPYrd_f5R1YsEkU0CWuFw.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/thasadith/v2/mtG-4_1TIqPYrd_f5R1oskMQ8iC-F1ZE.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/thasadith/v2/mtG94_1TIqPYrd_f5R1gDGYw2A6yHk9d8w.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/thasadith/v2/mtGj4_1TIqPYrd_f5R1osnus3QS2PEpN8zxA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `The Girl Next Door` extends GoogleFont {
    override lazy val family: String = "The Girl Next Door"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/thegirlnextdoor/v9/pe0zMJCIMIsBjFxqYBIcZ6_OI5oFHCYIV7t7w6bE2A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tienne` extends GoogleFont {
    override lazy val family: String = "Tienne"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tienne/v11/AYCKpX7pe9YCRP0LkEPHSFNyxw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/tienne/v11/AYCJpX7pe9YCRP0zLGzjQHhuzvef5Q.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/tienne/v11/AYCJpX7pe9YCRP0zFG7jQHhuzvef5Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`, `900`)
  }
  object `Tillana` extends GoogleFont {
    override lazy val family: String = "Tillana"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tillana/v4/VuJxdNvf35P4qJ1OeKbXOIFneRo.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/tillana/v4/VuJ0dNvf35P4qJ1OQFL-HIlMZRNcp0o.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/tillana/v4/VuJ0dNvf35P4qJ1OQH75HIlMZRNcp0o.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/tillana/v4/VuJ0dNvf35P4qJ1OQBr4HIlMZRNcp0o.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/tillana/v4/VuJ0dNvf35P4qJ1OQAb7HIlMZRNcp0o.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `600`, `700`, `800`)
  }
  object `Timmana` extends GoogleFont {
    override lazy val family: String = "Timmana"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `telugu`: GoogleFontSubset = new GoogleFontSubset("telugu")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`telugu`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/timmana/v3/6xKvdShfL9yK-rvpCmvbKHwJUOM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tinos` extends GoogleFont {
    override lazy val family: String = "Tinos"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `hebrew`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tinos/v12/buE4poGnedXvwgX8dGVh8TI-.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/tinos/v12/buE2poGnedXvwjX-fmFD9CI-4NU.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/tinos/v12/buE1poGnedXvwj1AW0Fp2i43-cxL.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/tinos/v12/buEzpoGnedXvwjX-Rt1s0CoV_NxLeiw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Titan One` extends GoogleFont {
    override lazy val family: String = "Titan One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/titanone/v6/mFTzWbsGxbbS_J5cQcjykzIn2Etikg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Titillium Web` extends GoogleFont {
    override lazy val family: String = "Titillium Web"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/titilliumweb/v7/NaPDcZTIAOhVxoMyOr9n_E7ffAzHKIx5YrSYqWM.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/titilliumweb/v7/NaPFcZTIAOhVxoMyOr9n_E7fdMbewI1zZpaduWMmxA.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/titilliumweb/v7/NaPDcZTIAOhVxoMyOr9n_E7ffGjEKIx5YrSYqWM.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/titilliumweb/v7/NaPFcZTIAOhVxoMyOr9n_E7fdMbepI5zZpaduWMmxA.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/titilliumweb/v7/NaPecZTIAOhVxoMyOr9n_E7fRMTsDIRSfr0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/titilliumweb/v7/NaPAcZTIAOhVxoMyOr9n_E7fdMbmCKZXbr2BsA.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/titilliumweb/v7/NaPDcZTIAOhVxoMyOr9n_E7ffBzCKIx5YrSYqWM.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/titilliumweb/v7/NaPFcZTIAOhVxoMyOr9n_E7fdMbe0IhzZpaduWMmxA.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/titilliumweb/v7/NaPDcZTIAOhVxoMyOr9n_E7ffHjDKIx5YrSYqWM.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/titilliumweb/v7/NaPFcZTIAOhVxoMyOr9n_E7fdMbetIlzZpaduWMmxA.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/titilliumweb/v7/NaPDcZTIAOhVxoMyOr9n_E7ffEDBKIx5YrSYqWM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `900`)
  }
  object `Trade Winds` extends GoogleFont {
    override lazy val family: String = "Trade Winds"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tradewinds/v7/AYCPpXPpYNIIT7h8-QenM3Jq7PKP5Z_G.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Trirong` extends GoogleFont {
    override lazy val family: String = "Trirong"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `thai`: GoogleFontSubset = new GoogleFontSubset("thai")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`thai`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/trirong/v4/7r3EqXNgp8wxdOdOl-go3YRl6ujngw.ttf"))
    lazy val `100italic`: GoogleFontWeight = GoogleFontWeight(this, "100italic", URL(s"$base/trirong/v4/7r3CqXNgp8wxdOdOn44QuY5hyO33g8IY.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/trirong/v4/7r3DqXNgp8wxdOdOl0QJ_a5L5uH-mts.ttf"))
    lazy val `200italic`: GoogleFontWeight = GoogleFontWeight(this, "200italic", URL(s"$base/trirong/v4/7r3BqXNgp8wxdOdOn44QFa9B4sP7itsB5g.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/trirong/v4/7r3DqXNgp8wxdOdOlyAK_a5L5uH-mts.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/trirong/v4/7r3BqXNgp8wxdOdOn44QcaxB4sP7itsB5g.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/trirong/v4/7r3GqXNgp8wxdOdOr4wi2aZg-ug.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/trirong/v4/7r3EqXNgp8wxdOdOn44o3YRl6ujngw.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/trirong/v4/7r3DqXNgp8wxdOdOl3gL_a5L5uH-mts.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/trirong/v4/7r3BqXNgp8wxdOdOn44QKa1B4sP7itsB5g.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/trirong/v4/7r3DqXNgp8wxdOdOl1QM_a5L5uH-mts.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/trirong/v4/7r3BqXNgp8wxdOdOn44QBapB4sP7itsB5g.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/trirong/v4/7r3DqXNgp8wxdOdOlzAN_a5L5uH-mts.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/trirong/v4/7r3BqXNgp8wxdOdOn44QYatB4sP7itsB5g.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/trirong/v4/7r3DqXNgp8wxdOdOlywO_a5L5uH-mts.ttf"))
    lazy val `800italic`: GoogleFontWeight = GoogleFontWeight(this, "800italic", URL(s"$base/trirong/v4/7r3BqXNgp8wxdOdOn44QfahB4sP7itsB5g.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/trirong/v4/7r3DqXNgp8wxdOdOlwgP_a5L5uH-mts.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/trirong/v4/7r3BqXNgp8wxdOdOn44QWalB4sP7itsB5g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `100italic`, `200`, `200italic`, `300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`, `800`, `800italic`, `900`, `900italic`)
  }
  object `Trocchi` extends GoogleFont {
    override lazy val family: String = "Trocchi"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/trocchi/v7/qWcqB6WkuIDxDZLcDrtUvMeTYD0.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Trochut` extends GoogleFont {
    override lazy val family: String = "Trochut"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/trochut/v6/CHyjV-fDDlP9bDIw5nSIfVIPLns.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/trochut/v6/CHyhV-fDDlP9bDIw1naCeXAKPns8jw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/trochut/v6/CHymV-fDDlP9bDIw3sinWVokMnIllmA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`)
  }
  object `Trykker` extends GoogleFont {
    override lazy val family: String = "Trykker"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/trykker/v7/KtktALyWZJXudUPzhNnoOd2j22U.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Tulpen One` extends GoogleFont {
    override lazy val family: String = "Tulpen One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/tulpenone/v8/dFa6ZfeC474skLgesc0CWj0w_HyIRlE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ubuntu` extends GoogleFont {
    override lazy val family: String = "Ubuntu"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/ubuntu/v13/4iCv6KVjbNBYlgoC1CzTt2aMH4V_gg.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/ubuntu/v13/4iCp6KVjbNBYlgoKejZftWyIPYBvgpUI.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ubuntu/v13/4iCs6KVjbNBYlgo6eAT3v02QFg.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ubuntu/v13/4iCu6KVjbNBYlgoKeg7znUiAFpxm.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/ubuntu/v13/4iCv6KVjbNBYlgoCjC3Tt2aMH4V_gg.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/ubuntu/v13/4iCp6KVjbNBYlgoKejYHtGyIPYBvgpUI.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ubuntu/v13/4iCv6KVjbNBYlgoCxCvTt2aMH4V_gg.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ubuntu/v13/4iCp6KVjbNBYlgoKejZPsmyIPYBvgpUI.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `700`, `700italic`)
  }
  object `Ubuntu Condensed` extends GoogleFont {
    override lazy val family: String = "Ubuntu Condensed"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ubuntucondensed/v9/u-4k0rCzjgs5J7oXnJcM_0kACGMtf-fVqvHoJXw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Ubuntu Mono` extends GoogleFont {
    override lazy val family: String = "Ubuntu Mono"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `greek-ext`: GoogleFontSubset = new GoogleFontSubset("greek-ext")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `greek-ext`, `latin-ext`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ubuntumono/v8/KFOjCneDtsqEr0keqCMhbBc9AMX6lJBP.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/ubuntumono/v8/KFOhCneDtsqEr0keqCMhbCc_CsHYkYBPY3o.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/ubuntumono/v8/KFO-CneDtsqEr0keqCMhbC-BL-Hyv4xGemO1.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/ubuntumono/v8/KFO8CneDtsqEr0keqCMhbCc_Mn33tYhkf3O1GVg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Ultra` extends GoogleFont {
    override lazy val family: String = "Ultra"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/ultra/v11/zOLy4prXmrtY-tT6yLOD6NxF.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Uncial Antiqua` extends GoogleFont {
    override lazy val family: String = "Uncial Antiqua"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/uncialantiqua/v6/N0bM2S5WOex4OUbESzoESK-i-PfRS5VBBSSF.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Underdog` extends GoogleFont {
    override lazy val family: String = "Underdog"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `cyrillic`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/underdog/v7/CHygV-jCElj7diMroVSiU14GN2Il.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Unica One` extends GoogleFont {
    override lazy val family: String = "Unica One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/unicaone/v6/DPEuYwWHyAYGVTSmalshdtffuEY7FA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `UnifrakturCook` extends GoogleFont {
    override lazy val family: String = "UnifrakturCook"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/unifrakturcook/v10/IurA6Yli8YOdcoky-0PTTdkm56n05Uw13ILXs-h6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`700`)
  }
  object `UnifrakturMaguntia` extends GoogleFont {
    override lazy val family: String = "UnifrakturMaguntia"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/unifrakturmaguntia/v9/WWXPlieVYwiGNomYU-ciRLRvEmK7oaVun2xNNgNa1A.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Unkempt` extends GoogleFont {
    override lazy val family: String = "Unkempt"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/unkempt/v10/2EbnL-Z2DFZue0DSSYYf8z2Yt_c.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/unkempt/v10/2EbiL-Z2DFZue0DScTow1zWzq_5uT84.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
  object `Unlock` extends GoogleFont {
    override lazy val family: String = "Unlock"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/unlock/v8/7Au-p_8ykD-cDl7GKAjSwkUVOQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Unna` extends GoogleFont {
    override lazy val family: String = "Unna"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/unna/v12/AYCEpXzofN0NCpgBlGHCWFM.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/unna/v12/AYCKpXzofN0NOpoLkEPHSFNyxw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/unna/v12/AYCLpXzofN0NMiQusGnpRFpr3vc.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/unna/v12/AYCJpXzofN0NOpozLGzjQHhuzvef5Q.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `VT323` extends GoogleFont {
    override lazy val family: String = "VT323"
    override lazy val category: String = "monospace"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vt323/v10/pxiKyp0ihIEF2hsYHpT2dkNE.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Vampiro One` extends GoogleFont {
    override lazy val family: String = "Vampiro One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vampiroone/v9/gokqH6DoDl5yXvJytFsdLkqnsvhIor3K.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Varela` extends GoogleFont {
    override lazy val family: String = "Varela"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/varela/v9/DPEtYwqExx0AWHXJBBQFfvzDsQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Varela Round` extends GoogleFont {
    override lazy val family: String = "Varela Round"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `hebrew`: GoogleFontSubset = new GoogleFontSubset("hebrew")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`hebrew`, `latin-ext`, `vietnamese`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/varelaround/v11/w8gdH283Tvk__Lua32TysjIvoMGOD9gxZw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Vast Shadow` extends GoogleFont {
    override lazy val family: String = "Vast Shadow"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vastshadow/v8/pe0qMImKOZ1V62ZwbVY9dfe6Kdpickwp.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Vesper Libre` extends GoogleFont {
    override lazy val family: String = "Vesper Libre"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vesperlibre/v10/bx6CNxyWnf-uxPdXDHUD_Rd4D0-N2qIWVQ.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/vesperlibre/v10/bx6dNxyWnf-uxPdXDHUD_RdA-2ap0okKXKvPlw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/vesperlibre/v10/bx6dNxyWnf-uxPdXDHUD_RdAs2Cp0okKXKvPlw.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/vesperlibre/v10/bx6dNxyWnf-uxPdXDHUD_RdAi2Kp0okKXKvPlw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `500`, `700`, `900`)
  }
  object `Vibur` extends GoogleFont {
    override lazy val family: String = "Vibur"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vibur/v9/DPEiYwmEzw0QRjTpLjoJd-Xa.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Vidaloka` extends GoogleFont {
    override lazy val family: String = "Vidaloka"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vidaloka/v11/7cHrv4c3ipenMKlEass8yn4hnCci.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Viga` extends GoogleFont {
    override lazy val family: String = "Viga"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/viga/v7/xMQbuFFdSaiX_QIjD4e2OX8.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Voces` extends GoogleFont {
    override lazy val family: String = "Voces"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/voces/v8/-F6_fjJyLyU8d4PBBG7YpzlJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Volkhov` extends GoogleFont {
    override lazy val family: String = "Volkhov"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/volkhov/v10/SlGQmQieoJcKemNeQTIOhHxzcD0.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/volkhov/v10/SlGSmQieoJcKemNecTAEgF52YD0NYw.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/volkhov/v10/SlGVmQieoJcKemNeeY4hoHRYbDQUego.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/volkhov/v10/SlGXmQieoJcKemNecTA8PHFSaBYRagrQrA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `700`, `700italic`)
  }
  object `Vollkorn` extends GoogleFont {
    override lazy val family: String = "Vollkorn"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `greek`: GoogleFontSubset = new GoogleFontSubset("greek")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`greek`, `latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vollkorn/v9/0yb9GDoxxrvAnPhYGykuYkw2rQg1.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/vollkorn/v9/0yb7GDoxxrvAnPhYGxksaEgUqBg15TY.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/vollkorn/v9/0yb6GDoxxrvAnPhYGxH2TGg-hhQ8_C_3.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/vollkorn/v9/0yb4GDoxxrvAnPhYGxksUJA6jBAe-T_34DM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/vollkorn/v9/0yb6GDoxxrvAnPhYGxGSTWg-hhQ8_C_3.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/vollkorn/v9/0yb4GDoxxrvAnPhYGxksUPQ7jBAe-T_34DM.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/vollkorn/v9/0yb6GDoxxrvAnPhYGxGqT2g-hhQ8_C_3.ttf"))
    lazy val `900italic`: GoogleFontWeight = GoogleFontWeight(this, "900italic", URL(s"$base/vollkorn/v9/0yb4GDoxxrvAnPhYGxksUMw5jBAe-T_34DM.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `italic`, `600`, `600italic`, `700`, `700italic`, `900`, `900italic`)
  }
  object `Vollkorn SC` extends GoogleFont {
    override lazy val family: String = "Vollkorn SC"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/vollkornsc/v2/j8_v6-zQ3rXpceZj9cqnVhF5NH-iSq_E.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/vollkornsc/v2/j8_y6-zQ3rXpceZj9cqnVimhGluqYbPN5Yjn.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/vollkornsc/v2/j8_y6-zQ3rXpceZj9cqnVinFG1uqYbPN5Yjn.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/vollkornsc/v2/j8_y6-zQ3rXpceZj9cqnVin9GVuqYbPN5Yjn.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `600`, `700`, `900`)
  }
  object `Voltaire` extends GoogleFont {
    override lazy val family: String = "Voltaire"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/voltaire/v8/1Pttg8PcRfSblAvGvQooYKVnBOif.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Waiting for the Sunrise` extends GoogleFont {
    override lazy val family: String = "Waiting for the Sunrise"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/waitingforthesunrise/v9/WBL1rFvOYl9CEv2i1mO6KUW8RKWJ2zoXoz5JsYZQ9h_ZYk5J.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Wallpoet` extends GoogleFont {
    override lazy val family: String = "Wallpoet"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/wallpoet/v10/f0X10em2_8RnXVVdUNbu7cXP8L8G.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Walter Turncoat` extends GoogleFont {
    override lazy val family: String = "Walter Turncoat"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/walterturncoat/v9/snfys0Gs98ln43n0d-14ULoToe67YB2dQ5ZPqQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Warnes` extends GoogleFont {
    override lazy val family: String = "Warnes"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/warnes/v8/pONn1hc0GsW6sW5OpiC2o6Lkqg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Wellfleet` extends GoogleFont {
    override lazy val family: String = "Wellfleet"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/wellfleet/v6/nuF7D_LfQJb3VYgX6eyT42aLDhO2HA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Wendy One` extends GoogleFont {
    override lazy val family: String = "Wendy One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/wendyone/v7/2sDcZGJOipXfgfXV5wgDb2-4C7wFZQ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Wire One` extends GoogleFont {
    override lazy val family: String = "Wire One"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/wireone/v9/qFdH35Wah5htUhV75WGiWdrCwwcJ.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Work Sans` extends GoogleFont {
    override lazy val family: String = "Work Sans"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/worksans/v4/QGYqz_wNahGAdqQ43Rh3H6DstfxA4OD3.ttf"))
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/worksans/v4/QGYpz_wNahGAdqQ43Rh3s4HMn9JM6fnuKg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/worksans/v4/QGYpz_wNahGAdqQ43Rh314LMn9JM6fnuKg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/worksans/v4/QGYsz_wNahGAdqQ43RhPe6rol_lQ4A.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/worksans/v4/QGYpz_wNahGAdqQ43Rh3j4PMn9JM6fnuKg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/worksans/v4/QGYpz_wNahGAdqQ43Rh3o4TMn9JM6fnuKg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/worksans/v4/QGYpz_wNahGAdqQ43Rh3x4XMn9JM6fnuKg.ttf"))
    lazy val `800`: GoogleFontWeight = GoogleFontWeight(this, "800", URL(s"$base/worksans/v4/QGYpz_wNahGAdqQ43Rh324bMn9JM6fnuKg.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/worksans/v4/QGYpz_wNahGAdqQ43Rh3_4fMn9JM6fnuKg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `200`, `300`, `regular`, `500`, `600`, `700`, `800`, `900`)
  }
  object `Yanone Kaffeesatz` extends GoogleFont {
    override lazy val family: String = "Yanone Kaffeesatz"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `latin`)
    }
    lazy val `200`: GoogleFontWeight = GoogleFontWeight(this, "200", URL(s"$base/yanonekaffeesatz/v10/3y9-6aknfjLm_3lMKjiMgmUUYBs04YfUPs-tNtKENeNp.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/yanonekaffeesatz/v10/3y9-6aknfjLm_3lMKjiMgmUUYBs04YewPc-tNtKENeNp.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yanonekaffeesatz/v10/3y976aknfjLm_3lMKjiMgmUUYBs04b8cFeulHc6N.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/yanonekaffeesatz/v10/3y9-6aknfjLm_3lMKjiMgmUUYBs04YegOs-tNtKENeNp.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`200`, `300`, `regular`, `700`)
  }
  object `Yantramanav` extends GoogleFont {
    override lazy val family: String = "Yantramanav"
    override lazy val category: String = "sans-serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `100`: GoogleFontWeight = GoogleFontWeight(this, "100", URL(s"$base/yantramanav/v4/flU-Rqu5zY00QEpyWJYWN5-QXeNzDB41rZg.ttf"))
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/yantramanav/v4/flUhRqu5zY00QEpyWJYWN59Yf8NZIhI8tIHh.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yantramanav/v4/flU8Rqu5zY00QEpyWJYWN6f0V-dRCQ41.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/yantramanav/v4/flUhRqu5zY00QEpyWJYWN58AfsNZIhI8tIHh.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/yantramanav/v4/flUhRqu5zY00QEpyWJYWN59IeMNZIhI8tIHh.ttf"))
    lazy val `900`: GoogleFontWeight = GoogleFontWeight(this, "900", URL(s"$base/yantramanav/v4/flUhRqu5zY00QEpyWJYWN59wesNZIhI8tIHh.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`100`, `300`, `regular`, `500`, `700`, `900`)
  }
  object `Yatra One` extends GoogleFont {
    override lazy val family: String = "Yatra One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `devanagari`: GoogleFontSubset = new GoogleFontSubset("devanagari")
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`devanagari`, `latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yatraone/v5/C8ch4copsHzj8p7NaF0xw1OBbRDvXw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Yellowtail` extends GoogleFont {
    override lazy val family: String = "Yellowtail"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yellowtail/v9/OZpGg_pnoDtINPfRIlLotlzNwED-b4g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Yeon Sung` extends GoogleFont {
    override lazy val family: String = "Yeon Sung"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `korean`: GoogleFontSubset = new GoogleFontSubset("korean")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`korean`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yeonsung/v7/QldMNTpbohAGtsJvUn6xSVNazqx2xg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Yeseva One` extends GoogleFont {
    override lazy val family: String = "Yeseva One"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `vietnamese`: GoogleFontSubset = new GoogleFontSubset("vietnamese")
      lazy val `cyrillic`: GoogleFontSubset = new GoogleFontSubset("cyrillic")
      lazy val `cyrillic-ext`: GoogleFontSubset = new GoogleFontSubset("cyrillic-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `vietnamese`, `cyrillic`, `cyrillic-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yesevaone/v13/OpNJno4ck8vc-xYpwWWxpipfWhXD00c.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Yesteryear` extends GoogleFont {
    override lazy val family: String = "Yesteryear"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yesteryear/v7/dg4g_p78rroaKl8kRKo1r7wHTwonmyw.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Yrsa` extends GoogleFont {
    override lazy val family: String = "Yrsa"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/yrsa/v4/wlpxgwnQFlxs3af93IQ73W5OcCk.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/yrsa/v4/wlp-gwnQFlxs5QvV-IwQwWc.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/yrsa/v4/wlpxgwnQFlxs3f_83IQ73W5OcCk.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/yrsa/v4/wlpxgwnQFlxs3dP73IQ73W5OcCk.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/yrsa/v4/wlpxgwnQFlxs3bf63IQ73W5OcCk.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `regular`, `500`, `600`, `700`)
  }
  object `ZCOOL KuaiLe` extends GoogleFont {
    override lazy val family: String = "ZCOOL KuaiLe"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `chinese-simplified`: GoogleFontSubset = new GoogleFontSubset("chinese-simplified")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`chinese-simplified`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/zcoolkuaile/v4/tssqApdaRQokwFjFJjvM6h2WpozzoXhC2g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `ZCOOL QingKe HuangYou` extends GoogleFont {
    override lazy val family: String = "ZCOOL QingKe HuangYou"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `chinese-simplified`: GoogleFontSubset = new GoogleFontSubset("chinese-simplified")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`chinese-simplified`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/zcoolqingkehuangyou/v4/2Eb5L_R5IXJEWhD3AOhSvFC554MOOahI4mRIi_28c8bHWA.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `ZCOOL XiaoWei` extends GoogleFont {
    override lazy val family: String = "ZCOOL XiaoWei"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `chinese-simplified`: GoogleFontSubset = new GoogleFontSubset("chinese-simplified")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`chinese-simplified`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/zcoolxiaowei/v4/i7dMIFFrTRywPpUVX9_RJyM1YFKQHwyVd3U.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Zeyada` extends GoogleFont {
    override lazy val family: String = "Zeyada"
    override lazy val category: String = "handwriting"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/zeyada/v8/11hAGpPTxVPUbgZDNGatWKaZ3g.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`)
  }
  object `Zilla Slab` extends GoogleFont {
    override lazy val family: String = "Zilla Slab"
    override lazy val category: String = "serif"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `300`: GoogleFontWeight = GoogleFontWeight(this, "300", URL(s"$base/zillaslab/v4/dFa5ZfeM_74wlPZtksIFYpEY2HSjWlhzbaw.ttf"))
    lazy val `300italic`: GoogleFontWeight = GoogleFontWeight(this, "300italic", URL(s"$base/zillaslab/v4/dFanZfeM_74wlPZtksIFaj8CVHapXnp2fazkfg.ttf"))
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/zillaslab/v4/dFa6ZfeM_74wlPZtksIFWj0w_HyIRlE.ttf"))
    lazy val `italic`: GoogleFontWeight = GoogleFontWeight(this, "italic", URL(s"$base/zillaslab/v4/dFa4ZfeM_74wlPZtksIFaj86-F6NVlFqdA.ttf"))
    lazy val `500`: GoogleFontWeight = GoogleFontWeight(this, "500", URL(s"$base/zillaslab/v4/dFa5ZfeM_74wlPZtksIFYskZ2HSjWlhzbaw.ttf"))
    lazy val `500italic`: GoogleFontWeight = GoogleFontWeight(this, "500italic", URL(s"$base/zillaslab/v4/dFanZfeM_74wlPZtksIFaj8CDHepXnp2fazkfg.ttf"))
    lazy val `600`: GoogleFontWeight = GoogleFontWeight(this, "600", URL(s"$base/zillaslab/v4/dFa5ZfeM_74wlPZtksIFYuUe2HSjWlhzbaw.ttf"))
    lazy val `600italic`: GoogleFontWeight = GoogleFontWeight(this, "600italic", URL(s"$base/zillaslab/v4/dFanZfeM_74wlPZtksIFaj8CIHCpXnp2fazkfg.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/zillaslab/v4/dFa5ZfeM_74wlPZtksIFYoEf2HSjWlhzbaw.ttf"))
    lazy val `700italic`: GoogleFontWeight = GoogleFontWeight(this, "700italic", URL(s"$base/zillaslab/v4/dFanZfeM_74wlPZtksIFaj8CRHGpXnp2fazkfg.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`300`, `300italic`, `regular`, `italic`, `500`, `500italic`, `600`, `600italic`, `700`, `700italic`)
  }
  object `Zilla Slab Highlight` extends GoogleFont {
    override lazy val family: String = "Zilla Slab Highlight"
    override lazy val category: String = "display"
    override object subsets extends GoogleFontSubsets {
      lazy val `latin-ext`: GoogleFontSubset = new GoogleFontSubset("latin-ext")
      lazy val `latin`: GoogleFontSubset = new GoogleFontSubset("latin")

      override lazy val all: Set[GoogleFontSubset] = Set(`latin-ext`, `latin`)
    }
    lazy val `regular`: GoogleFontWeight = GoogleFontWeight(this, "regular", URL(s"$base/zillaslabhighlight/v6/gNMbW2BrTpK8-inLtBJgMMfbm6uNVDvRxhtIY2DwSXlM.ttf"))
    lazy val `700`: GoogleFontWeight = GoogleFontWeight(this, "700", URL(s"$base/zillaslabhighlight/v6/gNMUW2BrTpK8-inLtBJgMMfbm6uNVDvRxiP0TET4YmVF0Mb6.ttf"))

    override lazy val weights: List[GoogleFontWeight] = List(`regular`, `700`)
  }
}
       