package jp.modal.soul.tonbejitest.build

import java.util
import jp.modal.soul.tonbeji.build.FrontMatter
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/08/08.
 */
class FrontMatterSpec extends FlatSpec with Matchers {
  class FM extends FrontMatter
  val fm = new FM
  "FrontMatter#frontMatterMap" should "success" in {
    val input = Seq("hoge:fuga", "fb:[foo,bar]")
    val hoge = "fuga"
    val fb = new util.ArrayList[String]()
    fb.add("foo")
    fb.add("bar")
    val expected = new util.HashMap[String, Object]()
    expected.put("hoge", hoge)
    expected.put("fb", fb)

    fm.frontMatterMap(input) should be(expected)
  }

}
