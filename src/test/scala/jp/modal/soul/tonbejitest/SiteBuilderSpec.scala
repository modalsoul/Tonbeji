package jp.modal.soul.tonbejitest

import java.io.File

import jp.modal.soul.tonbeji.{ FileOperator, Layout, SiteBuilder, DirectoryStructure }
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/01/18.
 */
class SiteBuilderSpec extends FlatSpec with Matchers {
  val path = (s: String) => "./src/test/resources/ok/%s".format(s)

  val ds = DirectoryStructure(
    path("posts"),
    path("layouts"),
    path("includes"),
    path("site"),
    "./working"
  )

  val layout = Layout(new File("./src/test/resources/ok/layouts/post.html"))

  val builder = new SiteBuilder(ds)

  "buildLayout" should "be success" in {
    val res = builder.buildLayout(layout, "hoge")
    println("res = " + res)
    res.isEmpty should be(false)
  }
}
