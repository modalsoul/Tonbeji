package jp.modal.soul.tonbejitest.entity

import java.util

import jp.modal.soul.tonbeji.entity.Post
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/08/13.
 */
class PostSpec extends FlatSpec with Matchers {
  "Post#permalink" should "correct" in {
    val post = Post(
      name = "2015-08-13-this-is-test.md",
      layout = Option("post"),
      title = "This is Test.",
      tags = List("foo", "bar"),
      categories = List("test"),
      settings = new util.HashMap[String, Object]()
    )
    val expected = "/test/2015/08/13/this-is-test/"

    post.permalink should be(expected)
  }

}
