package jp.modal.soul.tonbejitest.build

import java.util

import jp.modal.soul.tonbeji.build.{ Liquid, Markdown }
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/08/09.
 */
class LiquidSpec extends FlatSpec with Matchers {

  class Lq extends Liquid

  it should "success" in {
    val src =
      """
        |<ul id="products">
        |{% for product in products %}
        |<li>
        |<h2>{{ product.name }}</h2>
        |</li>
        |{% endfor %}
        |</ul>
      """.stripMargin

    val products: java.util.List[java.util.Map[String, Object]] = new java.util.ArrayList[java.util.Map[String, Object]]()
    val product: java.util.HashMap[String, Object] = new util.HashMap[String, Object]()
    product.put("name", "HOGE")
    products.add(product)
    val map: java.util.HashMap[String, Object] = new util.HashMap[String, Object]()
    map.put("products", products)

    val expected =
      """
        |<ul id="products">
        |
        |<li>
        |<h2>HOGE</h2>
        |</li>
        |
        |</ul>
      """.stripMargin

    val lq = new Lq
    assert(lq.parse(src, map) == expected)
  }

}
