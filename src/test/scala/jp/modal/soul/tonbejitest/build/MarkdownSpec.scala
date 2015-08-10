package jp.modal.soul.tonbejitest.build

import jp.modal.soul.tonbeji.build.Markdown
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/08/09.
 */
class MarkdownSpec extends FlatSpec with Matchers {

  class MD extends Markdown

  val mdString =
    """
      |# Hello
      |* 1
      |* 2
      |* 3
    """.stripMargin

  it should "success" in {
    val md = new MD()
    val expected =
      """<h1>Hello</h1><ul><li>1
        |</li><li>2
        |</li><li>3
        |</li></ul>""".stripMargin
    md.parsedMarkdown(mdString) should be(expected.toString())
  }

}
