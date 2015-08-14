package jp.modal.soul.tonbejitest.build

import java.util

import jp.modal.soul.tonbeji.build.LayoutFileParser
import jp.modal.soul.tonbeji.entity.Layout
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/08/11.
 */
class LayoutFileParserSpec extends FlatSpec with Matchers {

  it should "success" in {
    val fileBody =
      """
        |---
        |layout: default
        |---
        |<h1>Hello</h1>
      """.stripMargin

    val settings = new util.HashMap[String, Object]()
    settings.put("layout", "default")
    val expected = Layout(
      "test",
      Option("default"),
      """<h1>Hello</h1>
      """.stripMargin,
      settings)

    new LayoutFileParser("test", fileBody).parsed should be(expected)
  }

}
