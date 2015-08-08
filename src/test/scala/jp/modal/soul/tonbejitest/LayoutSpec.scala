package jp.modal.soul.tonbejitest

import java.io.File

import jp.modal.soul.tonbeji.Layout
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/01/19.
 */
class LayoutSpec extends FlatSpec with Matchers {
  val layoutFile = new File("./src/test/resources/ok/layouts/post.html")
  val parentLayout = Layout(new File("./src/test/resources/ok/layouts/page.html"))
  val layout = Layout(layoutFile)

  "post layout" should "have parent layout" in {
    layout.hasParentLayout should be(true)
    layout.parentLayout should be(parentLayout)
  }

}
