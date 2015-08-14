package jp.modal.soul.tonbejitest.build

import java.util

import jp.modal.soul.tonbeji.build.PostFileParser
import jp.modal.soul.tonbeji.entity.Post
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/08/09.
 */
class PostFileParserSpec extends FlatSpec with Matchers {

  it should "success" in {
    val fileBody =
      """
        |---
        |layout:post
        |title:Hoge
        |tags:[foo,bar]
        |categories:[sample]
        |comment:This is comment.
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

    val layout = "post"
    val title = "Hoge"
    val tags = new util.ArrayList[String]()
    tags.add("foo")
    tags.add("bar")
    val categories = new util.ArrayList[String]()
    categories.add("sample")
    val comment = "This is comment."
    val otherSettings = new util.HashMap[String, Object]()
    otherSettings.put("layout", layout)
    otherSettings.put("title", title)
    otherSettings.put("tags", tags)
    otherSettings.put("categories", categories)
    otherSettings.put("comment", comment)
    otherSettings.put("contents", contents)

    val expected = Post(
      name = "test",
      layout = Option("post"),
      title = "Hoge",
      tags = List("foo", "bar"),
      categories = List("sample"),
      settings = otherSettings
    )

    val parser = new PostFileParser("test", fileBody)
    assert(parser.parsed == expected)
  }

}
