package jp.modal.soul.tonbejitest.build

import java.util

import jp.modal.soul.tonbeji.build.PostFileParser
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/08/09.
 */
class PostFileParserSpec extends FlatSpec with Matchers {

  it should "success" in {
    val fileBody =
      """
        |---
        |title:Hoge
        |tags:[foo,bar]
        |---
        |# Hello
        |* 1
        |* 2
        |* 3
      """.stripMargin

    val contents =
      """<h1>Hello</h1><ul><li>1
        |</li><li>2
        |</li><li>3
        |</li></ul>""".stripMargin

    val title = "Hoge"

    val tags = new util.ArrayList[String]()
    tags.add("foo")
    tags.add("bar")
    val expected = new util.HashMap[String, Object]()
    expected.put("title", title)
    expected.put("tags", tags)
    expected.put("contents", contents)

    val parser = new PostFileParser(fileBody)
    assert(parser.parsed == expected)
  }

}
