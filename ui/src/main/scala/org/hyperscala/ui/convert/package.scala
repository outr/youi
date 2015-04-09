package org.hyperscala.ui

/**
 * Created by mmynsted on 4/10/15.
 * code@growingliberty.com
 */
package object convert {
  implicit val MapQueryStringable = new QueryStringable[Map[String, String]] {

    def toQueryString(a: Map[String, String]) = a.foldLeft(""){
      case (s, (k,v)) => s"${s}${k}=${(java.net.URLEncoder.encode(v, "UTF-8")).replaceAllLiterally("+", "%20")}&"
    }
  }
}
