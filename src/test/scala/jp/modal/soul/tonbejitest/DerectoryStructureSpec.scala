package jp.modal.soul.tonbejitest

import java.io.File

import jp.modal.soul.tonbeji.{ Layout, DirectoryStructure }
import org.scalatest._

/**
 * Created by imaemasatoshi on 2015/01/15.
 */
class DerectoryStructureSpec extends FlatSpec with Matchers {
  val layoutDir = new File("./src/test/resources/ok/layouts")
  val defaultLayout = Layout(new File("./src/test/resources/ok/layouts/default.html"))
  val pageLayout = Layout(new File("./src/test/resources/ok/layouts/page.html"))
  val postLayout = Layout(new File("./src/test/resources/ok/layouts/post.html"))
  "getLayouts" should "be success" in {
    DirectoryStructure.getLayouts(layoutDir) should be(Map("default" -> defaultLayout, "page" -> pageLayout, "post" -> postLayout))
  }
}
