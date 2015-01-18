package jp.modal.soul.tonbejitest

import java.io.File

import jp.modal.soul.tonbeji.{ SettingFormatException, SettingParser }
import org.scalatest.{ Matchers, FlatSpec }

/**
 * Created by imae on 2015/01/18.
 */
class TestFormatException() extends SettingFormatException
class SettingParserTest extends FlatSpec with Matchers {
  object SettingParserImpl extends SettingParser {
    override val settingKeys: Map[String, Boolean] = Map("string" -> false, "collection" -> true)
  }

  val file = new File("./src/test/resources/posts/2014-01-16-test.md")
  val illegalSettingFile = new File("./src/test/resources/posts/2015-01-16-illegal-setting-format.md")
  "parseSetting" should "success" in {
    SettingParserImpl.parseSetting("string", "this is value") should be("string" -> "this is value")
    SettingParserImpl.parseSetting("collection", "this is value") should be("collection" -> Seq("this", "is", "value"))
  }

  "parse" should "get settings" in {
    SettingParserImpl.getSettings[TestFormatException](file).keys.toSeq.length should be(5)
  }

  "parse" should "be failed" in {
    a[TestFormatException] should be thrownBy {
      SettingParserImpl.getSettings[TestFormatException](illegalSettingFile)
    }
  }
}
