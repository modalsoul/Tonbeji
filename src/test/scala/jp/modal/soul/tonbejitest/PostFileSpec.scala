package jp.modal.soul.tonbejitest

import java.io.File

import jp.modal.soul.tonbeji.{ Layout, PostFile }
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/01/17.
 */
class PostFileSpec extends FlatSpec with Matchers {
  val file = new File("./src/test/resources/ok/posts/2014-01-16-test.md")
  val postLayout = new File("./src/test/resources/ok/layouts/post.html")
  "post file" should "be PostFile instance" in {
    val postFile = PostFile(file, Map("post" -> Layout(postLayout)))

    postFile.file should be(file)
    postFile.layout should be(Layout(postLayout))
    postFile.title should be("test")
    postFile.tags should be(Seq("sample", "test"))
    postFile.categories should be(Seq("category"))
    postFile.others should be(Map("comment" -> "hello world"))
  }

  "contents" should "be generate" in {
    val postFile = PostFile(file, Map("post" -> Layout(postLayout)))
    val contents = postFile.markdownContents
    contents.isEmpty should be(false)
  }
}
